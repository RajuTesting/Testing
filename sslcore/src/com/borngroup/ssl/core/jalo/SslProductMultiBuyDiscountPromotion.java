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
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

public class SslProductMultiBuyDiscountPromotion extends GeneratedSslProductMultiBuyDiscountPromotion {
    @SuppressWarnings("unused")
    private final static Logger LOG = Logger.getLogger(SslProductMultiBuyDiscountPromotion.class.getName());

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
    public List<PromotionResult> evaluate(final SessionContext paramSessionContext, final PromotionEvaluationContext promoContext) {
        final List promotionResults = new ArrayList();
        final PromotionsManager.RestrictionSetResult restrictRes = findEligibleProductsInBasket(paramSessionContext, promoContext);
        final List products = restrictRes.getAllowedProducts();

        if ((restrictRes.isAllowedToContinue()) && (!(restrictRes.getAllowedProducts().isEmpty()))) {
            Double promotionPriceValue = new Double(0.0D);
            final Double promotionDiscountValue = getDiscountForOrder(paramSessionContext, getBundleDiscounts(paramSessionContext),
                    promoContext.getOrder(), "bundleDiscounts");
            if (promotionDiscountValue != null) {
                final int triggerSize = getQualifyingCount(paramSessionContext).intValue();
                final PromotionOrderView pov = promoContext.createView(paramSessionContext, this, products);

                while (pov.getTotalQuantity(paramSessionContext) >= triggerSize) {
                    promoContext.startLoggingConsumed(this);
                    pov.consumeFromHead(paramSessionContext, PromotionEvaluationContext.createPriceComparator(paramSessionContext),
                            triggerSize);

                    double thisPromoTotal = 0.0D;
                    final List<PromotionOrderEntryConsumed> consumedEntries = promoContext.finishLoggingAndGetConsumed(this, true);
                    if ((consumedEntries != null) && (!(consumedEntries.isEmpty()))) {
                        for (final PromotionOrderEntryConsumed poec : consumedEntries) {
                            thisPromoTotal += poec.getEntryPrice(paramSessionContext);
                        }
                    }

                    promotionPriceValue = Double.valueOf(((100.0D - promotionDiscountValue.doubleValue()) * thisPromoTotal) / 100);

                    final double adjustment = promotionPriceValue.doubleValue() - thisPromoTotal;
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("(" + getPK() + ") evaluate: totalValueOfConsumedEntries=[" + thisPromoTotal + "] promotionPriceValue=["
                                + promotionPriceValue + "] adjustment=[" + adjustment + "]");
                    }

                    Helper.adjustUnitPrices(paramSessionContext, promoContext, consumedEntries, promotionPriceValue.doubleValue(),
                            thisPromoTotal);

                    final PromotionResult currResult = PromotionsManager.getInstance().createPromotionResult(paramSessionContext, this,
                            promoContext.getOrder(), 1.0F);
                    currResult.setConsumedEntries(paramSessionContext, consumedEntries);
                    currResult.addAction(paramSessionContext,
                            PromotionsManager.getInstance().createPromotionOrderAdjustTotalAction(paramSessionContext, adjustment));
                    promotionResults.add(currResult);
                }

                final long remaining = pov.getTotalQuantity(paramSessionContext);
                if (remaining > 0L) {
                    promoContext.startLoggingConsumed(this);
                    pov.consume(paramSessionContext, remaining);

                    final float certainty = (float) remaining / triggerSize;
                    final PromotionResult currResult = PromotionsManager.getInstance().createPromotionResult(paramSessionContext, this,
                            promoContext.getOrder(), certainty);
                    currResult.setConsumedEntries(paramSessionContext, promoContext.finishLoggingAndGetConsumed(this, false));
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
            final int qualifyingCount = getQualifyingCount(ctx).intValue();

            final Double promotionDiscountValue = getDiscountForOrder(ctx, getBundleDiscounts(ctx), promotionResult.getOrder(ctx),
                    "bundleDiscounts");

            if (promotionDiscountValue != null) {
                if (promotionResult.getFired(ctx)) {
                    final double totalDiscount = promotionResult.getTotalDiscount(ctx);

                    final Object[] args = { Integer.valueOf(qualifyingCount), promotionDiscountValue, Double.valueOf(totalDiscount),
                            Helper.formatCurrencyAmount(ctx, locale, orderCurrency, totalDiscount) };
                    return formatMessage(getMessageFired(ctx), args, locale);
                }
                if (promotionResult.getCouldFire(ctx)) {
                    final Object[] args = { Integer.valueOf(qualifyingCount), promotionDiscountValue,
                            Long.valueOf(qualifyingCount - promotionResult.getConsumedCount(ctx, true)) };
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
        buildDataUniqueKeyForDiscountRows(ctx, builder, getBundleDiscounts(ctx));
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
