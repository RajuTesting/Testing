package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
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

public class SslProductPerfectPartnerDiscountPromotion extends GeneratedSslProductPerfectPartnerDiscountPromotion {
    @SuppressWarnings("unused")
    private final static Logger LOG = Logger.getLogger(SslProductPerfectPartnerDiscountPromotion.class.getName());
    private static float TRIGGER_BUT_NO_PARTNER = 0.75F;

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
     * @see de.hybris.platform.promotions.jalo.AbstractPromotion#evaluate(de.hybris. platform.jalo.SessionContext,
     * de.hybris.platform.promotions.result.PromotionEvaluationContext)
     */
    @Override
    public List<PromotionResult> evaluate(final SessionContext ctx, final PromotionEvaluationContext promoContext) {
        final List promotionResults = new ArrayList();
        final Double partnerProductDiscount = getDiscountForOrder(ctx, getPartnerPercentageDiscounts(ctx), promoContext.getOrder(),
                "partnerPercentageDiscounts");
        Double partnerProductPrice = 0.0D;
        if (partnerProductDiscount != null) {
            final PromotionsManager.RestrictionSetResult rsr = findEligibleProductsInBasket(ctx, promoContext);
            if ((rsr.isAllowedToContinue()) && (!(rsr.getAllowedProducts().isEmpty()))) {
                final PromotionOrderView triggerItemView = promoContext.createView(ctx, this, rsr.getAllowedProducts());
                final PromotionOrderView partnerItemView = promoContext.createView(ctx, this, getPartnerProductList(ctx));

                while (triggerItemView.getTotalQuantity(ctx) > 0L) {
                    promoContext.startLoggingConsumed(this);

                    triggerItemView.consume(ctx, 1L);
                    if (partnerItemView.getTotalQuantity(ctx) > 0L) {
                        final Comparator comparator = PromotionEvaluationContext.createPriceComparator(ctx);
                        final PromotionOrderEntryConsumed poec = partnerItemView.consumeFromHead(ctx, comparator, 1L).get(0);

                        final double partnerProductRetailPrice = poec.getUnitPrice(ctx);
                        partnerProductPrice = Double.valueOf(partnerProductRetailPrice
                                * ((100 - partnerProductDiscount.doubleValue()) / 100));

                        final double adjustment = partnerProductPrice.doubleValue() - partnerProductRetailPrice;

                        poec.setAdjustedUnitPrice(partnerProductPrice);

                        final AbstractPromotionAction action = PromotionsManager.getInstance().createPromotionOrderEntryAdjustAction(ctx,
                                poec.getOrderEntry(ctx), adjustment);

                        final PromotionResult result = PromotionsManager.getInstance().createPromotionResult(ctx, this,
                                promoContext.getOrder(), 1.0F);
                        result.setConsumedEntries(promoContext.finishLoggingAndGetConsumed(this, true));
                        result.addAction(ctx, action);

                        promotionResults.add(result);
                    } else {
                        final PromotionResult result = PromotionsManager.getInstance().createPromotionResult(ctx, this,
                                promoContext.getOrder(), TRIGGER_BUT_NO_PARTNER);
                        result.setConsumedEntries(ctx, promoContext.finishLoggingAndGetConsumed(this, false));
                        promotionResults.add(result);
                        break;
                    }
                }
            }
        }
        return promotionResults;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.hybris.platform.promotions.jalo.AbstractPromotion#getResultDescription (de.hybris.platform.jalo.SessionContext,
     * de.hybris.platform.promotions.jalo.PromotionResult, java.util.Locale)
     */
    @Override
    public String getResultDescription(final SessionContext ctx, final PromotionResult promotionResult, final Locale locale) {
        final AbstractOrder order = promotionResult.getOrder(ctx);
        if (order != null) {
            final Currency orderCurrency = order.getCurrency(ctx);

            final Double offerDiscount = getDiscountForOrder(ctx, getPartnerPercentageDiscounts(ctx), promotionResult.getOrder(ctx),
                    "partnerPercentageDiscounts");
            if (offerDiscount != null) {
                if (promotionResult.getFired(ctx)) {
                    final double totalDiscount = promotionResult.getTotalDiscount(ctx);

                    final Object[] args = { offerDiscount, Double.valueOf(totalDiscount),
                            Helper.formatCurrencyAmount(ctx, locale, orderCurrency, totalDiscount) };
                    return formatMessage(getMessageFired(ctx), args, locale);
                }
                if (promotionResult.getCouldFire(ctx)) {
                    final Object[] args = { offerDiscount };
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
        buildDataUniqueKeyForProductsNew(ctx, builder, getPartnerProductList(ctx));
        buildDataUniqueKeyForDiscountRows(ctx, builder, getPartnerPercentageDiscounts(ctx));
    }

    protected static final void buildDataUniqueKeyForProductsNew(final SessionContext ctx, final StringBuilder builder,
            final Collection<Product> products) {
        final StringBuilder productBuilder = new StringBuilder();

        if ((products != null) && (!(products.isEmpty()))) {
            final List<Product> sortedProducts = new ArrayList(products);
            Collections.sort(sortedProducts, Comparators.productComparator);

            for (final Product p : sortedProducts) {
                productBuilder.append(p.getCode(ctx)).append(',');
            }

        }

        builder.append(buildMD5Hash(productBuilder.toString()));

        builder.append('|');
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
                builder.append(row.getCurrency(ctx).getIsoCode(ctx)).append('=').append(row.getPercentageDiscount(ctx)).append(',');
            }
        }
        builder.append('|');
    }
}
