package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.jalo.PromotionValueAndDiscountRow;
import de.hybris.platform.jalo.ExtensibleItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.order.GeneratedAbstractOrder;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.jalo.PromotionPriceRow;
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.jalo.PromotionsManager;
import de.hybris.platform.promotions.result.PromotionEvaluationContext;
import de.hybris.platform.promotions.util.Helper;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class SslOrderSteppedMultiBuyDiscountPromotion extends GeneratedSslOrderSteppedMultiBuyDiscountPromotion {
    @SuppressWarnings("unused")
    private final static Logger LOG = Logger.getLogger(SslOrderSteppedMultiBuyDiscountPromotion.class.getName());

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
        // YTODO Auto-generated method stub
        final List promotionResults = new ArrayList();
        if (checkRestrictions(ctx, promoContext)) {
            final AbstractOrder order = promoContext.getOrder();
            final double orderSubtotalAfterDiscounts = getOrderSubtotalAfterDiscount(ctx, order);

            if (orderSubtotalAfterDiscounts >= 0) {
                final SortedSet steps = getSteps(ctx, promoContext.getOrder(), getQualifyingValueAndBundleDiscounts(ctx));
                if ((steps != null) && (!(steps.isEmpty()))) {
                    ValueDiscount lastStep = (ValueDiscount) steps.last();
                    double lastThreshold = lastStep.value;
                    double totalDiscount = 0d;
                    Double consumedThreshold = 0D;
                    while(true){
                        final ValueDiscount triggeredStep = findStep(steps, orderSubtotalAfterDiscounts - consumedThreshold);
                        Double threshold = 0D;
                        if (null != triggeredStep) {
                            threshold = triggeredStep.value;
                            if ((orderSubtotalAfterDiscounts - consumedThreshold) >= threshold.doubleValue() && threshold != 0d ) {
                                totalDiscount += triggeredStep.discountValue;
                                consumedThreshold += threshold;
                                if (LOG.isDebugEnabled()) {
                                    LOG.debug("(" + getPK() + ") evaluate: Subtotal " + orderSubtotalAfterDiscounts + ">"
                                            + ".Creating a discount action for value:" + totalDiscount + ".");
                                }
                            } else {
                                if (LOG.isDebugEnabled()) {
                                    LOG.debug("(" + getPK() + ") evaluate: Subtotal " + orderSubtotalAfterDiscounts + "<" + threshold
                                            + ".  Skipping discount action.");
                                }
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    if(totalDiscount > 0){
                        final PromotionResult result = PromotionsManager.getInstance().createPromotionResult(ctx, this,
                                promoContext.getOrder(), 1.0F);
                        result.addAction(ctx,
                                PromotionsManager.getInstance().createPromotionOrderAdjustTotalAction(ctx, - totalDiscount));
                        promotionResults.add(result);
                    }
                    else{
                        final float certainty = (float) (orderSubtotalAfterDiscounts / lastThreshold);
                        final PromotionResult result = PromotionsManager.getInstance().createPromotionResult(ctx, this,
                                promoContext.getOrder(), certainty);
                        promotionResults.add(result);
                    }
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
    public String getResultDescription(final SessionContext ctx, final PromotionResult result, final Locale locale) {
        final AbstractOrder order = result.getOrder(ctx);
        if (order != null) {
            final Currency orderCurrency = order.getCurrency(ctx);
            double totalDiscount = result.getTotalDiscount(ctx);
            final SortedSet steps = getSteps(ctx, order, getQualifyingValueAndBundleDiscounts(ctx));
            if (steps != null) {
                final double orderSubtotalAfterDiscounts = getOrderSubtotalAfterDiscount(ctx, order);

                if (totalDiscount >= 0) {
                    if (result.getFired(ctx)) {

                        final Object[] args = { totalDiscount,
                                Helper.formatCurrencyAmount(ctx, locale, orderCurrency, totalDiscount) };

                        String message = getMessageFired(ctx);
                        if (null == message) {
                            message = StringUtils
                                    .isNotBlank(Config.getParameter("SslOrderSteppedMultiBuyDiscountPromotion.messageFired"))
                                            ? Config.getParameter("SslOrderSteppedMultiBuyDiscountPromotion.messageFired")
                                            : "You saved - {1}";
                        }
                        return formatMessage(message, args, locale);
                    }
                    if (result.getCouldFire(ctx)) {
                        ValueDiscount lastStep = (ValueDiscount) steps.last();
                        double lastThreshold = lastStep.value;
                        double lastDiscount = lastStep.discountValue;
                        final double amountRequired = lastThreshold - orderSubtotalAfterDiscounts;

                        final Object[] args = { lastThreshold, Helper.formatCurrencyAmount(ctx, locale, orderCurrency, lastThreshold),
                                lastDiscount, Helper.formatCurrencyAmount(ctx, locale, orderCurrency, lastDiscount),
                                Double.valueOf(amountRequired), Helper.formatCurrencyAmount(ctx, locale, orderCurrency, amountRequired) };
                        String message = getMessageCouldHaveFired(ctx);
                        if (null == message) {
                            message = StringUtils
                                    .isNotBlank(Config.getParameter("SslOrderSteppedMultiBuyDiscountPromotion.messagecouldhavefired"))
                                            ? Config.getParameter("SslOrderSteppedMultiBuyDiscountPromotion.messagecouldhavefired")
                                            : "Spend {1} to get a discount of {3} - Spend another {5} to qualify";
                        }
                        return formatMessage(message, args, locale);
                    }
                }
            }
        }
        return "";
    }

    private double getOrderSubtotalAfterDiscount(final SessionContext ctx, final AbstractOrder order) {
        double orderSubtotalAfterDiscounts = 0.0D;
        if(order != null) {
            if ((ctx != null)) {
                try {
                    order.calculateTotals(false);
                } catch (final JaloPriceFactoryException ex) {
                    LOG.error("orderSubtotalAfterDiscounts - failed to calculateTotals on order [" + order + "]", ex);
                }
            }
            final List products = this.getProductsforOrder(order);

            final GeneratedAbstractOrder promoContext = order;
            final PromotionsManager.RestrictionSetResult evaluateRestrictions = PromotionsManager.getInstance().evaluateRestrictions(ctx,
                    products, order, this, promoContext.getDate());
            final List allowedProducts = evaluateRestrictions.getAllowedProducts();
            for (final Object product : allowedProducts) {
                for (final Iterator localIterator = order.getAllEntries().iterator(); localIterator.hasNext();) {
                    final Object entry = localIterator.next();

                    final AbstractOrderEntry _entry = (AbstractOrderEntry) entry;
                    if (_entry.getProduct().getCode().equals(((ExtensibleItem) product).getProperty("code"))) {
                        orderSubtotalAfterDiscounts = orderSubtotalAfterDiscounts + _entry.getTotalPrice();
                    }
                }
            }
            final double discount = (orderSubtotalAfterDiscounts * order.getTotalDiscounts(ctx).doubleValue())
                    / order.getSubtotal(ctx).doubleValue();
            orderSubtotalAfterDiscounts = orderSubtotalAfterDiscounts - discount;
        }
        return orderSubtotalAfterDiscounts;
    }

    /**
     * @param order
     * @return products from order
     */
    private List getProductsforOrder(final AbstractOrder order) {
        final List products = new ArrayList();
        if(order != null) {
            for (final Iterator localIterator = order.getAllEntries().iterator(); localIterator.hasNext(); ) {
                final Object entry = localIterator.next();

                final AbstractOrderEntry _entry = (AbstractOrderEntry) entry;
                products.add(_entry.getProduct());
            }
        }
        return products;
    }

    /**
     * Returns Value and Percentage Value Pair Steps.
     *
     * @param ctx
     * @param order
     * @param rows
     * @return
     */
    private SortedSet<ValueDiscount> getSteps(final SessionContext ctx, final AbstractOrder order,
            final Collection<PromotionValueAndDiscountRow> rows) {
        final SortedSet qualifyingValueAndDiscounts = new TreeSet(
                new Comparator<ValueDiscount>() {
                    @Override
                    public int compare(final ValueDiscount a,
                            final ValueDiscount b) {
                        return Double.valueOf(b.value).compareTo(Double.valueOf(a.value));
                    }

                });

        if ((rows != null) && (!(rows.isEmpty()))) {

            for (final PromotionValueAndDiscountRow row : rows) {
                final Double promotionOrderValue = getValueForOrder(ctx, row.getValue(ctx), order, "qualifyingValueAndBundleDiscounts");
                if (promotionOrderValue == null) {
                    continue;
                }
                final Double promotionDiscountValue = getDiscountForOrder(ctx, row.getDiscount(ctx), order,
                        "qualifyingValueAndBundleDiscounts");
                if (promotionDiscountValue == null) {
                    continue;
                }
                qualifyingValueAndDiscounts
                        .add(new ValueDiscount(promotionOrderValue.doubleValue(), promotionDiscountValue.doubleValue()));
            }

        }
        return qualifyingValueAndDiscounts;
    }

    /**
     * Returns Discount Percentage Value for steps
     *
     * @param ctx
     * @param prices
     * @param order
     * @param fieldLabel
     * @return discountfor order
     */
    private final Double getDiscountForOrder(final SessionContext ctx, final Collection<PromotionPriceRow> prices,
            final AbstractOrder order, final String fieldLabel) {
        if (order != null) {
            final Currency currency = order.getCurrency(ctx);
            if (currency == null) {
                LOG.warn("Order [" + order + "] has null currency");
            } else {
                if (prices != null) {
                    for (final PromotionPriceRow ppr : prices) {
                        if (currency.equals(ppr.getCurrency(ctx))) {
                            return ppr.getPrice(ctx);
                        }
                    }
                }

                LOG.warn("Missing currency row [" + currency.getName(ctx) + "] for [" + fieldLabel + "] on promotion [" + this + "]");
            }
        }
        return null;
    }

    private final Double getValueForOrder(final SessionContext ctx, final Collection<PromotionPriceRow> prices, final AbstractOrder order,
            final String fieldLabel) {
        if (order != null) {
            final Currency currency = order.getCurrency(ctx);
            if (currency == null) {
                LOG.warn("Order [" + order + "] has null currency");
            } else {
                if (prices != null) {
                    for (final PromotionPriceRow ppr : prices) {
                        if (currency.equals(ppr.getCurrency(ctx))) {
                            return ppr.getPrice(ctx);
                        }
                    }
                }

                LOG.warn("Missing currency row [" + currency.getName(ctx) + "] for [" + fieldLabel + "] on promotion [" + this + "]");
            }
        }
        return null;
    }

    private static ValueDiscount findStep(final SortedSet<ValueDiscount> steps, final double ordervalue) {
        for (final ValueDiscount step : steps) {
            if (step.value <= ordervalue) {
                return step;
            }
        }
        return null;
    }

    private static ValueDiscount findNextStep(final SortedSet<ValueDiscount> steps,
            final ValueDiscount lastTriggeredStep) {
        ValueDiscount nextStep;
        if (lastTriggeredStep == null) {
            nextStep = steps.last();
        } else {
            final SortedSet higherQuantitySteps = steps.headSet(lastTriggeredStep);
            if ((higherQuantitySteps != null) && (!(higherQuantitySteps.isEmpty()))) {
                nextStep = (ValueDiscount) higherQuantitySteps.last();
            } else {
                nextStep = steps.last();
            }
        }

        return nextStep;
    }

    static class ValueDiscount {
        public double value;
        public double discountValue;

        ValueDiscount(final double value, final double discountValue) {
            this.value = value;
            this.discountValue = discountValue;
        }
    }

}
