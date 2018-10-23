package com.borngroup.ssl.core.jalo;

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
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.jalo.PromotionsManager;
import de.hybris.platform.promotions.result.PromotionEvaluationContext;
import de.hybris.platform.promotions.util.Helper;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class OrderThresholdFixedDiscountPromotion extends GeneratedOrderThresholdFixedDiscountPromotion
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger( OrderThresholdFixedDiscountPromotion.class.getName() );
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem( ctx, type, allAttributes );
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.promotions.jalo.OrderThresholdDiscountPromotion#
	 * evaluate(de.hybris.platform.jalo.SessionContext,
	 * de.hybris.platform.promotions.result.PromotionEvaluationContext)
	 */
	@Override
	public List<PromotionResult> evaluate(final SessionContext ctx, final PromotionEvaluationContext promoContext) {
		final List promotionResults = new ArrayList();

		if (checkRestrictions(ctx, promoContext)) {

			final AbstractOrder order = promoContext.getOrder();
			
			final double orderSubtotalAfterDiscounts = getOrderSubtotalAfterDiscount(ctx, order);
			final Double threshold = getPriceForOrder(ctx, getThresholdTotals(ctx), promoContext.getOrder(),
					"thresholdTotals");
			if (threshold != null) {
				final Double discountPriceValue = getPriceForOrder(ctx, getDiscountPrices(ctx), promoContext.getOrder(),
						"discountPrices");
				if (discountPriceValue != null) {

					if (orderSubtotalAfterDiscounts >= threshold.doubleValue()) {
						if (LOG.isDebugEnabled()) {
							LOG.debug("(" + getPK() + ") evaluate: Subtotal " + orderSubtotalAfterDiscounts + ">"
									+ threshold + ".  Creating a discount action for value:" + discountPriceValue
									+ ".");
						}
						final PromotionResult result = PromotionsManager.getInstance().createPromotionResult(ctx, this,
								promoContext.getOrder(), 1.0F);

						double realDiscountPriceValue = discountPriceValue.doubleValue();
						if (realDiscountPriceValue > orderSubtotalAfterDiscounts) {
							realDiscountPriceValue = orderSubtotalAfterDiscounts;
						}
						result.addAction(ctx, PromotionsManager.getInstance().createPromotionOrderAdjustTotalAction(ctx,
								-realDiscountPriceValue));

						promotionResults.add(result);
					} else {
						if (LOG.isDebugEnabled()) {
							LOG.debug("(" + getPK() + ") evaluate: Subtotal " + orderSubtotalAfterDiscounts + "<"
									+ threshold + ".  Skipping discount action.");
						}
						final float certainty = (float) (orderSubtotalAfterDiscounts / threshold.doubleValue());
						final PromotionResult result = PromotionsManager.getInstance().createPromotionResult(ctx, this,
								promoContext.getOrder(), certainty);
						promotionResults.add(result);
					}
				}
			}
		}

		return promotionResults;
	}

	@Override
	public String getResultDescription(final SessionContext ctx, final PromotionResult result, final Locale locale) {
		final AbstractOrder order = result.getOrder(ctx);
		if (order != null) {
			final Currency orderCurrency = order.getCurrency(ctx);

			final Double threshold = getPriceForOrder(ctx, getThresholdTotals(ctx), order, "thresholdTotals");
			if (threshold != null) {
				final Double discountPriceValue = getPriceForOrder(ctx, getDiscountPrices(ctx), order,
						"discountPrices");
				if (discountPriceValue != null) {
					if (result.getFired(ctx)) {
						final Object[] args = { threshold,
								Helper.formatCurrencyAmount(ctx, locale, orderCurrency, threshold.doubleValue()),
								discountPriceValue, Helper.formatCurrencyAmount(ctx, locale, orderCurrency,
										discountPriceValue.doubleValue()) };
						String message=getMessageFired(ctx);
						if( null == message)
						{
							message=StringUtils.isNotBlank(Config.getParameter("SslOrderThresholdFixedDiscountPromotion.messageFired")) ? Config.getParameter("SslOrderThresholdFixedDiscountPromotion.messageFired") : "You saved {3} for spending over {1}";
						}
						return formatMessage(message, args, locale);
					}
					if (result.getCouldFire(ctx)) {
						final double orderSubtotalAfterDiscounts = getOrderSubtotalAfterDiscount(ctx, order);
						final double amountRequired = threshold.doubleValue() - orderSubtotalAfterDiscounts;

						final Object[] args = { threshold,
								Helper.formatCurrencyAmount(ctx, locale, orderCurrency, threshold.doubleValue()),
								discountPriceValue,
								Helper.formatCurrencyAmount(ctx, locale, orderCurrency,
										discountPriceValue.doubleValue()),
								Double.valueOf(amountRequired),
								Helper.formatCurrencyAmount(ctx, locale, orderCurrency, amountRequired) };
						String message=getMessageCouldHaveFired(ctx);
						if( null ==  message)
						{
							message=StringUtils.isNotBlank(Config.getParameter("SslOrderThresholdFixedDiscountPromotion.messagecouldhavefired")) ? Config.getParameter("SslOrderThresholdFixedDiscountPromotion.messagecouldhavefired") : "Spend {1} to get a discount of {3} - Spend another {5} to qualify";
						}
						return formatMessage(message, args,locale);
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
			final List products = new ArrayList();
			for (final Iterator localIterator = order.getAllEntries().iterator(); localIterator.hasNext();) {
				final Object entry = localIterator.next();

				final AbstractOrderEntry _entry = (AbstractOrderEntry) entry;
				products.add(_entry.getProduct());
			}
			final GeneratedAbstractOrder promoContext = order;
			final PromotionsManager.RestrictionSetResult evaluateRestrictions = PromotionsManager.getInstance()
					.evaluateRestrictions(ctx, products, order, this, promoContext.getDate());
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
			double discount=(orderSubtotalAfterDiscounts * order.getTotalDiscounts(ctx).doubleValue())/order.getSubtotal(ctx).doubleValue();
			orderSubtotalAfterDiscounts = orderSubtotalAfterDiscounts - discount;
		}
		return orderSubtotalAfterDiscounts;
	}
	
}
