package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.jalo.AbstractPromotionAction;
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
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

public class SslProductBOGODiscountPromotion extends GeneratedSslProductBOGODiscountPromotion {
    @SuppressWarnings("unused")
    private final static Logger LOG = Logger.getLogger(SslProductBOGODiscountPromotion.class.getName());

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
        final List results = new ArrayList();

        final PromotionsManager.RestrictionSetResult restrictResult = findEligibleProductsInBasket(ctx, promoContext);

        if ((restrictResult.isAllowedToContinue()) && (!(restrictResult.getAllowedProducts().isEmpty()))) {
            final int qualifyingCount = getQualifyingCount(ctx).intValue();
            final int discountedCount = getDiscountedCount(ctx).intValue();
            final Double promotionDiscountValue = getDiscountForOrder(ctx, getDiscounts(ctx), promoContext.getOrder(), "discounts");
            final PromotionsManager promotionsManager = PromotionsManager.getInstance();

            final PromotionOrderView orderView = promoContext.createView(ctx, this, restrictResult.getAllowedProducts());

            while (orderView.getTotalQuantity(ctx) >= qualifyingCount) {
                promoContext.startLoggingConsumed(this);

                final Comparator comparator = PromotionEvaluationContext.createPriceComparator(ctx);

                orderView.consumeFromTail(ctx, comparator, qualifyingCount - discountedCount);

                final List<PromotionOrderEntryConsumed> discountedItems = orderView.consumeFromHead(ctx, comparator, discountedCount);

                final List<AbstractPromotionAction> actions = new ArrayList<AbstractPromotionAction>();
                Double adjustedUnitPrice = new Double(0.0D);
                for (final PromotionOrderEntryConsumed poec : discountedItems) {
                    adjustedUnitPrice = Double.valueOf(((100D - promotionDiscountValue.doubleValue()) * poec.getEntryPrice(ctx)) / 100);
                    poec.setAdjustedUnitPrice(ctx, adjustedUnitPrice);

                    final double adjustment = adjustedUnitPrice.doubleValue() - poec.getEntryPrice(ctx);

                    actions.add(promotionsManager.createPromotionOrderEntryAdjustAction(ctx, poec.getOrderEntry(ctx), adjustment));
                }

                final PromotionResult result = promotionsManager.createPromotionResult(ctx, this, promoContext.getOrder(), 1.0F);

                final List consumed = promoContext.finishLoggingAndGetConsumed(this, true);
                result.setConsumedEntries(ctx, consumed);

                result.setActions(ctx, actions);

                results.add(result);
            }

            final long remainingCount = orderView.getTotalQuantity(ctx);
            if (orderView.getTotalQuantity(ctx) > 0L) {
                promoContext.startLoggingConsumed(this);

                orderView.consume(ctx, remainingCount);

                final float certainty = (float) remainingCount / qualifyingCount;

                final PromotionResult result = promotionsManager.createPromotionResult(ctx, this, promoContext.getOrder(), certainty);

                result.setConsumedEntries(promoContext.finishLoggingAndGetConsumed(this, false));

                results.add(result);
            }
        }

        return results;
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

            final Integer qualifyingCount = getQualifyingCount(ctx);
            final Integer discountedCount = getDiscountedCount(ctx);
            final Double promotionDiscountValue = getDiscountForOrder(ctx, getDiscounts(ctx), promotionResult.getOrder(ctx), "discounts");
            if (promotionDiscountValue != null) {
                if (promotionResult.getFired(ctx)) {
                    final double totalDiscount = promotionResult.getTotalDiscount(ctx);

                    final Object[] args = { qualifyingCount, discountedCount, promotionDiscountValue, Double.valueOf(totalDiscount),
                            Helper.formatCurrencyAmount(ctx, locale, orderCurrency, totalDiscount) };
                    return formatMessage(getMessageFired(ctx), args, locale);
                }
                if (promotionResult.getCouldFire(ctx)) {
                    final Object[] args = {
                            Long.valueOf(getQualifyingCount(ctx).longValue() - promotionResult.getConsumedCount(ctx, true)),
                            qualifyingCount, discountedCount, promotionDiscountValue };
                    return formatMessage(getMessageCouldHaveFired(ctx), args, locale);
                }
            }
        }
        return "";
    }

    private final Double getDiscountForOrder(final SessionContext ctx, final Collection<PromotionPercentageDiscountRow> prices,
            final AbstractOrder order, final String fieldLabel) {
        if (order != null) {
            final Currency currency = order.getCurrency(ctx);
            if (currency == null) {
                LOG.warn("Order [" + order + "] has null currency");
            } else {
                if (prices != null) {
                    for (final PromotionPercentageDiscountRow ppr : prices) {
                        if (currency.equals(ppr.getCurrency(ctx))) {
                            return ppr.getPercentageDiscount(ctx);
                        }
                    }
                }

                LOG.warn("Missing currency row [" + currency.getName(ctx) + "] for [" + fieldLabel + "] on promotion [" + this + "]");
            }
        }
        return null;
    }

    @Override
    protected void buildDataUniqueKey(final SessionContext ctx, final StringBuilder builder) {
        super.buildDataUniqueKey(ctx, builder);

        builder.append(getQualifyingCount(ctx)).append('|');
        builder.append(getDiscountedCount(ctx)).append('|');
        buildDataUniqueKeyForDiscountRows(ctx, builder, getDiscounts(ctx));
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
