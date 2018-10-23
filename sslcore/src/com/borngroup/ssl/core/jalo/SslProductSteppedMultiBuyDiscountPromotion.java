package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.jalo.PromotionOrderEntryConsumed;
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.jalo.PromotionsManager;
import de.hybris.platform.promotions.result.PromotionEvaluationContext;
import de.hybris.platform.promotions.result.PromotionOrderView;
import de.hybris.platform.promotions.util.Comparators;
import de.hybris.platform.promotions.util.Helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;

public class SslProductSteppedMultiBuyDiscountPromotion extends GeneratedSslProductSteppedMultiBuyDiscountPromotion {
    @SuppressWarnings("unused")
    private final static Logger log = Logger.getLogger(SslProductSteppedMultiBuyDiscountPromotion.class.getName());

    @Override
    protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
            throws JaloBusinessException {
        // business code placed here will be executed before the item is created
        // then create the item
        final Item item = super.createItem(ctx, type, allAttributes);
        // business code placed here will be executed after the item was created
        // and return the item
        return item;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.promotions.jalo.AbstractPromotion#evaluate(de.hybris.platform.jalo.SessionContext,
     * de.hybris.platform.promotions.result.PromotionEvaluationContext)
     */
    @Override
    public List<PromotionResult> evaluate(final SessionContext ctx, final PromotionEvaluationContext promoContext) {
        final List promotionResults = new ArrayList();

        final PromotionsManager.RestrictionSetResult restrictRes = findEligibleProductsInBasket(ctx, promoContext);
        final List products = restrictRes.getAllowedProducts();
        if ((restrictRes.isAllowedToContinue()) && (!(restrictRes.getAllowedProducts().isEmpty()))) {
            final SortedSet steps = getSteps(ctx, promoContext.getOrder(), getQualifyingCountsAndBundleDiscounts(ctx));
            if ((steps != null) && (!(steps.isEmpty()))) {
                final PromotionOrderView pov = promoContext.createView(ctx, this, products);

                QuantityDiscountPercentage lastTriggeredStep = null;
                while (true) {
                    final QuantityDiscountPercentage triggeredStep = findStep(steps, pov.getTotalQuantity(ctx));
                    if (triggeredStep == null) {
                        break;
                    }

                    lastTriggeredStep = triggeredStep;

                    promoContext.startLoggingConsumed(this);

                    pov.consumeFromHead(ctx, PromotionEvaluationContext.createPriceComparator(ctx), triggeredStep.quantity);

                    double thisPromoTotal = 0.0D;
                    final List<PromotionOrderEntryConsumed> consumedEntries = promoContext.finishLoggingAndGetConsumed(this, true);
                    if ((consumedEntries != null) && (!(consumedEntries.isEmpty()))) {
                        for (final PromotionOrderEntryConsumed poec : consumedEntries) {
                            thisPromoTotal += poec.getEntryPrice(ctx);
                        }
                    }
                    final Double promotionPriceValue = Double.valueOf(((100.0D - triggeredStep.discountPercentage) * thisPromoTotal) / 100);
                    final double adjustment = promotionPriceValue.doubleValue() - thisPromoTotal;
                    if (log.isDebugEnabled()) {
                        log.debug("(" + getPK() + ") evaluate: triggeredStep quantity=[" + triggeredStep.quantity
                                + "] totalValueOfConsumedEntries=[" + thisPromoTotal + "] promotionDiscountValue=["
                                + triggeredStep.discountPercentage + "] adjustment=[" + adjustment + "]");
                    }

                    Helper.adjustUnitPrices(ctx, promoContext, consumedEntries, promotionPriceValue.doubleValue(), thisPromoTotal);

                    final PromotionResult currResult = PromotionsManager.getInstance().createPromotionResult(ctx, this,
                            promoContext.getOrder(), 1.0F);
                    currResult.setConsumedEntries(ctx, consumedEntries);
                    currResult.addAction(ctx, PromotionsManager.getInstance().createPromotionOrderAdjustTotalAction(ctx, adjustment));
                    promotionResults.add(currResult);
                }

                final long remaining = pov.getTotalQuantity(ctx);
                if (remaining > 0L) {
                    final QuantityDiscountPercentage nextStep = findNextStep(steps, lastTriggeredStep);
                    if (log.isDebugEnabled()) {
                        log.debug("(" + getPK() + ") evaluate: nextStep for protential promotion quantity=[" + nextStep.quantity + "]");
                    }

                    if (remaining >= nextStep.quantity) {
                        log.error("(" + getPK() + ") evaluate: nextStep for protential promotion, remaining=[" + remaining
                                + "] is greater than or equal to nextStep.quantity=[" + nextStep.quantity + "]");
                    }

                    promoContext.startLoggingConsumed(this);
                    pov.consume(ctx, remaining);
                    final float certainty;
                    if ((lastTriggeredStep != null) && (lastTriggeredStep != steps.first())) {
                        certainty = (remaining + lastTriggeredStep.quantity) / (float) (nextStep.quantity + lastTriggeredStep.quantity);
                    } else {
                        certainty = remaining / (float) nextStep.quantity;
                    }

                    final PromotionResult currResult = PromotionsManager.getInstance().createPromotionResult(ctx, this,
                            promoContext.getOrder(), certainty);
                    currResult.setConsumedEntries(ctx, promoContext.finishLoggingAndGetConsumed(this, false));
                    currResult.setCustom(ctx, String.valueOf(nextStep.quantity));
                    promotionResults.add(currResult);
                }
            }
        }

        return promotionResults;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.promotions.jalo.AbstractPromotion#getResultDescription(de.hybris.platform.jalo.SessionContext,
     * de.hybris.platform.promotions.jalo.PromotionResult, java.util.Locale)
     */
    @Override
    public String getResultDescription(final SessionContext ctx, final PromotionResult promotionResult, final Locale locale) {
        final AbstractOrder order = promotionResult.getOrder(ctx);
        if (order != null) {
            final Currency orderCurrency = order.getCurrency(ctx);

            final SortedSet<QuantityDiscountPercentage> steps = getSteps(ctx, promotionResult.getOrder(),
                    getQualifyingCountsAndBundleDiscounts(ctx));
            if ((steps != null) && (!steps.isEmpty())) {
                ArrayList<QuantityDiscountPercentage> ascendingSteps;
                if (promotionResult.getFired(ctx)) {
                    double promotionDiscountValue = 0.0D;
                    final long consumedCount = promotionResult.getConsumedCount(ctx, false);
                    for (final QuantityDiscountPercentage step : steps) {
                        if (step.quantity == consumedCount) {
                            promotionDiscountValue = step.discountPercentage;
                        }
                    }
                    final double totalDiscount = promotionResult.getTotalDiscount(ctx);

                    final ArrayList args = new ArrayList();
                    args.add(Long.valueOf(consumedCount));
                    args.add(Double.valueOf(promotionDiscountValue));
                    args.add(Double.valueOf(totalDiscount));
                    args.add(Helper.formatCurrencyAmount(ctx, locale, orderCurrency, totalDiscount));

                    ascendingSteps = new ArrayList(steps);
                    Collections.reverse(ascendingSteps);
                    for (final QuantityDiscountPercentage step : ascendingSteps) {
                        args.add(Long.valueOf(step.quantity));
                        args.add(Double.valueOf(step.discountPercentage));
                    }
                    return formatMessage(getMessageFired(ctx), args.toArray(), locale);
                }
                if (promotionResult.getCouldFire(ctx)) {
                    final String customData = promotionResult.getCustom(ctx);
                    if ((customData != null) && (customData.length() > 0)) {
                        final long nextStepQunatity = Long.parseLong(customData);
                        double nextStepDiscount = 0.0D;
                        QuantityDiscountPercentage nextStep = null;
                        for (final QuantityDiscountPercentage step : steps) {
                            if (step.quantity == nextStepQunatity) {
                                nextStep = step;
                                nextStepDiscount = step.discountPercentage;
                            }
                        }
                        QuantityDiscountPercentage currentStep = null;
                        if (nextStep != null) {
                            final SortedSet<QuantityDiscountPercentage> lowerSteps = steps.tailSet(nextStep);
                            if (lowerSteps.size() > 1) {
                                final Iterator<QuantityDiscountPercentage> iter = lowerSteps.iterator();

                                iter.next();

                                currentStep = iter.next();
                            }
                        }
                        long currentStepQuantity = 0L;
                        if (currentStep != null) {
                            currentStepQuantity = currentStep.quantity;
                        }
                        final List args = new ArrayList();
                        args.add(Long.valueOf(nextStepQunatity));
                        args.add(Double.valueOf(nextStepDiscount));
                        args.add(Long.valueOf(nextStepQunatity - currentStepQuantity - promotionResult.getConsumedCount(ctx, true)));

                        ascendingSteps = new ArrayList(steps);
                        Collections.reverse(ascendingSteps);
                        for (final QuantityDiscountPercentage step : ascendingSteps) {
                            args.add(Long.valueOf(step.quantity));
                            args.add(Double.valueOf(step.discountPercentage));
                        }
                        return formatMessage(getMessageCouldHaveFired(ctx), args.toArray(), locale);
                    }
                }
            }
        }
        return "";
    }

    private SortedSet<QuantityDiscountPercentage> getSteps(final SessionContext ctx, final AbstractOrder order,
            final Collection<PromotionQuantityAndDiscountRow> rows) {
        final SortedSet qualifyingCountAndDiscounts = new TreeSet(
                new Comparator<SslProductSteppedMultiBuyDiscountPromotion.QuantityDiscountPercentage>() {
                    @Override
                    public int compare(final SslProductSteppedMultiBuyDiscountPromotion.QuantityDiscountPercentage a,
                            final SslProductSteppedMultiBuyDiscountPromotion.QuantityDiscountPercentage b) {
                        return Long.valueOf(b.quantity).compareTo(Long.valueOf(a.quantity));
                    }

                });
        if ((rows != null) && (!(rows.isEmpty()))) {
            for (final PromotionQuantityAndDiscountRow row : rows) {
                final long quantity = row.getQuantity(ctx).longValue();
                if (quantity <= 0L) {
                    continue;
                }
                final Double promotionDiscountValue = getDiscountForOrder(ctx, row.getDiscounts(ctx), order,
                        "qualifyingCountsAndBundleDiscounts");
                if (promotionDiscountValue == null) {
                    continue;
                }
                qualifyingCountAndDiscounts.add(new QuantityDiscountPercentage(quantity, promotionDiscountValue.doubleValue()));
            }

        }

        return qualifyingCountAndDiscounts;
    }

    static class QuantityDiscountPercentage {
        public long quantity;
        public double discountPercentage;

        QuantityDiscountPercentage(final long quantity, final double discountPercentage) {
            this.quantity = quantity;
            this.discountPercentage = discountPercentage;
        }
    }

    private final Double getDiscountForOrder(final SessionContext ctx, final Collection<PromotionPercentageDiscountRow> prices,
            final AbstractOrder order, final String fieldLabel) {
        if (order != null) {
            final Currency currency = order.getCurrency(ctx);
            if (currency == null) {
                log.warn("Order [" + order + "] has null currency");
            } else {
                if (prices != null) {
                    for (final PromotionPercentageDiscountRow ppr : prices) {
                        if (currency.equals(ppr.getCurrency(ctx))) {
                            return ppr.getPercentageDiscount(ctx);
                        }
                    }
                }

                log.warn("Missing currency row [" + currency.getName(ctx) + "] for [" + fieldLabel + "] on promotion [" + this + "]");
            }
        }
        return null;
    }

    private static QuantityDiscountPercentage findStep(final SortedSet<QuantityDiscountPercentage> steps, final long count) {
        for (final QuantityDiscountPercentage step : steps) {
            if (step.quantity <= count) {
                return step;
            }
        }
        return null;
    }

    private static QuantityDiscountPercentage findNextStep(final SortedSet<QuantityDiscountPercentage> steps,
            final QuantityDiscountPercentage lastTriggeredStep) {
        QuantityDiscountPercentage nextStep;
        if (lastTriggeredStep == null) {
            nextStep = steps.last();
        } else {
            final SortedSet higherQuantitySteps = steps.headSet(lastTriggeredStep);
            if ((higherQuantitySteps != null) && (!(higherQuantitySteps.isEmpty()))) {
                nextStep = (QuantityDiscountPercentage) higherQuantitySteps.last();
            } else {
                nextStep = steps.last();
            }
        }

        return nextStep;
    }

    @Override
    protected void buildDataUniqueKey(final SessionContext ctx, final StringBuilder builder) {
        super.buildDataUniqueKey(ctx, builder);

        final Collection<PromotionQuantityAndDiscountRow> rows = getQualifyingCountsAndBundleDiscounts(ctx);
        if ((rows != null) && (!rows.isEmpty())) {
            builder.append(rows.size()).append('|');
            for (final PromotionQuantityAndDiscountRow row : rows) {
                builder.append(row.getQuantity(ctx)).append('|');
                buildDataUniqueKeyForDiscountRows(ctx, builder, row.getDiscounts(ctx));
            }
        } else {
            builder.append('0').append('|');
        }
    }

    protected static final void buildDataUniqueKeyForDiscountRows(final SessionContext ctx, final StringBuilder builder,
            final Collection<PromotionPercentageDiscountRow> priceRows) {
        if ((priceRows != null) && (!(priceRows.isEmpty()))) {
            final List<PromotionPercentageDiscountRow> sortedPriceRows = new ArrayList(priceRows);
            Collections.sort(sortedPriceRows, new Comparator<PromotionPercentageDiscountRow>() {
                @Override
                public int compare(final PromotionPercentageDiscountRow a, final PromotionPercentageDiscountRow b) {
                    if ((a == null) && (b == null)) {
                        return 0;
                    }
                    if (a == null) {
                        return -1;
                    }
                    if (b == null) {
                        return 1;
                    }

                    final SessionContext ctx = JaloSession.getCurrentSession().createSessionContext();

                    final int currencyResult = Comparators.currencyComparator.compare(a.getCurrency(ctx), b.getCurrency(ctx));
                    if (currencyResult != 0) {
                        return currencyResult;
                    }

                    return Comparators.doubleComparator.compare(a.getPercentageDiscount(ctx), b.getPercentageDiscount(ctx));
                }
            });

            for (final PromotionPercentageDiscountRow row : sortedPriceRows) {
                builder.append(row.getCurrency(ctx).getIsocode(ctx)).append('=').append(row.getPercentageDiscount(ctx)).append(',');
            }
        }
        builder.append('|');
    }

}
