package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.ExtensibleItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.order.GeneratedAbstractOrder;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.jalo.PromotionsManager;
import de.hybris.platform.promotions.result.PromotionEvaluationContext;
import de.hybris.platform.promotions.util.Helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

public class SslOrderLimitDiscountPercentagePromotion extends
		GeneratedSslOrderLimitDiscountPercentagePromotion {
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger
			.getLogger(SslOrderLimitDiscountPercentagePromotion.class.getName());

	@Override
	protected Item createItem(final SessionContext ctx,
			final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException {
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem(ctx, type, allAttributes);
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}

	@Override
	public List<PromotionResult> evaluate(final SessionContext ctx,
			final PromotionEvaluationContext promoContext) {
		final List<PromotionResult> promotionResults = new ArrayList<PromotionResult>();

		if (checkRestrictions(ctx, promoContext)) {
			final Double upperThreshold = this
					.getPriceForOrder(
							ctx,
							this.getUpperThresholdTotals(ctx),
							promoContext.getOrder(),
							SslOrderLimitDiscountPercentagePromotion.UPPERTHRESHOLDTOTALS);
			final Double lowerThreshold = this
					.getPriceForOrder(
							ctx,
							this.getLowerThresholdTotals(ctx),
							promoContext.getOrder(),
							SslOrderLimitDiscountPercentagePromotion.LOWERTHRESHOLDTOTALS);

			if (upperThreshold != null && lowerThreshold != null) {
				// Get the discount price
				final Double discountPriceValue = Double.valueOf(this
						.getPercentageDiscount(ctx).doubleValue() / 100D);
				if (discountPriceValue != null) {
					final AbstractOrder order = promoContext.getOrder();
					final double orderSubtotalAfterDiscounts = getOrderSubtotalAfterDiscount(
							ctx, order);

					// If we pass the threshold then fire, and add an action to
					// put a discount in the basket
					if (orderSubtotalAfterDiscounts >= lowerThreshold
							.doubleValue()
							&& orderSubtotalAfterDiscounts <= upperThreshold
									.doubleValue()) {
						if (LOG.isDebugEnabled()) {
							LOG.debug("("
									+ getPK()
									+ ") evaluate: Subtotal "
									+ orderSubtotalAfterDiscounts
									+ ">"
									+ lowerThreshold
									+ "<"
									+ upperThreshold
									+ ".  Creating a discount action for value:"
									+ discountPriceValue + ".");
						}
						final PromotionResult result = PromotionsManager
								.getInstance().createPromotionResult(ctx, this,
										promoContext.getOrder(), 1.0F);

						result.addAction(
								ctx,
								PromotionsManager
										.getInstance()
										.createPromotionOrderAdjustTotalAction(
												ctx,
												-(orderSubtotalAfterDiscounts * discountPriceValue
														.doubleValue())));

						promotionResults.add(result);
					} else if (orderSubtotalAfterDiscounts > upperThreshold
							.doubleValue()) {
						if (LOG.isDebugEnabled()) {
							LOG.debug("("
									+ getPK()
									+ ") evaluate: Subtotal "
									+ orderSubtotalAfterDiscounts
									+ ">"
									+ upperThreshold
									+ ".  Creating a discount action for value for upper threshold:"
									+ discountPriceValue + ".");
						}
						final PromotionResult result = PromotionsManager
								.getInstance().createPromotionResult(ctx, this,
										promoContext.getOrder(), 1.0F);

						result.addAction(
								ctx,
								PromotionsManager
										.getInstance()
										.createPromotionOrderAdjustTotalAction(
												ctx,
												-(upperThreshold.doubleValue() * discountPriceValue
														.doubleValue())));

						promotionResults.add(result);
					}
					// Otherwise calculate the certainty by seeing how close the
					// order is to meeting the threshold
					else {
						if (LOG.isDebugEnabled()) {
							LOG.debug("(" + getPK() + ") evaluate: Subtotal "
									+ orderSubtotalAfterDiscounts + "<"
									+ lowerThreshold
									+ ".  Skipping discount action.");
						}
						final float certainty = (float) (orderSubtotalAfterDiscounts / lowerThreshold
								.doubleValue());
						final PromotionResult result = PromotionsManager
								.getInstance().createPromotionResult(ctx, this,
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
	 * @see
	 * de.hybris.platform.promotions.jalo.AbstractPromotion#getResultDescription
	 * (de.hybris.platform.jalo.SessionContext,
	 * de.hybris.platform.promotions.jalo.PromotionResult, java.util.Locale)
	 */
	@Override
	public String getResultDescription(final SessionContext ctx,
			final PromotionResult promotionResult, final Locale locale) {
		final AbstractOrder order = promotionResult.getOrder(ctx);
		if (order != null) {
			final de.hybris.platform.jalo.c2l.Currency orderCurrency = order
					.getCurrency(ctx);

			/*
			 * final Double upperThreshold = this .getPriceForOrder( ctx,
			 * this.getUpperThresholdTotals(ctx), order,
			 * SslOrderLimitDiscountPercentagePromotion.UPPERTHRESHOLDTOTALS);
			 */
			final Double lowerThreshold = this
					.getPriceForOrder(
							ctx,
							this.getLowerThresholdTotals(ctx),
							order,
							SslOrderLimitDiscountPercentagePromotion.LOWERTHRESHOLDTOTALS);
			final double orderSubtotalAfterDiscounts = getOrderSubtotalAfterDiscount(
					ctx, order);

			if (lowerThreshold != null) {
				// final double discountPercentValue =
				// this.getPercentageDiscount(ctx).doubleValue() / 100D;

				// Get the discount price
				 Double discountPriceValue = Double
						.valueOf(promotionResult.getTotalDiscount(ctx));

				if(promotionResult.getTotalDiscount(ctx) == 0.0)
				{
					discountPriceValue=Double.valueOf(lowerThreshold.doubleValue()*this.getPercentageDiscount(ctx).doubleValue() / 100D);
				}
				if (discountPriceValue != null) {
					if (promotionResult.getFired(ctx)) {
						// "You saved {3} for spending over {1}"
						final Object[] args = {
								lowerThreshold,
								Helper.formatCurrencyAmount(ctx, locale,
										orderCurrency,
										lowerThreshold.doubleValue()),
								discountPriceValue,
								Helper.formatCurrencyAmount(ctx, locale,
										orderCurrency,
										discountPriceValue.doubleValue()) };
						return formatMessage(this.getMessageFired(ctx), args,
								locale);
					} else if (promotionResult.getCouldFire(ctx)) {
						final double amountRequired = lowerThreshold
								.doubleValue() - orderSubtotalAfterDiscounts;

						// "Spend {1} to get a discount of {3} - Spend another {5} to qualify"
						final Object[] args = {
								lowerThreshold,
								Helper.formatCurrencyAmount(ctx, locale,
										orderCurrency,
										lowerThreshold.doubleValue()),
								discountPriceValue,
								Helper.formatCurrencyAmount(ctx, locale,
										orderCurrency,
										discountPriceValue.doubleValue()),
								Double.valueOf(amountRequired),
								Helper.formatCurrencyAmount(ctx, locale,
										orderCurrency, amountRequired) };
						return formatMessage(
								this.getMessageCouldHaveFired(ctx), args,
								locale);
					}
				}

			}
		}
		return "";
	}

	@Override
	protected void buildDataUniqueKey(final SessionContext ctx,
			final StringBuilder builder) {
		super.buildDataUniqueKey(ctx, builder);
		buildDataUniqueKeyForPriceRows(ctx, builder,
				getLowerThresholdTotals(ctx));
		builder.append(getPercentageDiscount(ctx)).append('|');
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
