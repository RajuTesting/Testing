package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.jalo.PromotionOrderEntryAdjustAction;
import de.hybris.platform.promotions.jalo.PromotionOrderEntryConsumed;
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.jalo.PromotionsManager;
import de.hybris.platform.promotions.result.PromotionEvaluationContext;
import de.hybris.platform.promotions.result.PromotionOrderEntry;
import de.hybris.platform.promotions.result.PromotionOrderView;
import de.hybris.platform.promotions.jalo.PromotionsManager.RestrictionSetResult;
import de.hybris.platform.promotions.util.Helper;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


public class SslProductValueDiscountPromotion extends GeneratedSslProductValueDiscountPromotion
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger( SslProductValueDiscountPromotion.class.getName() );
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem( ctx, type, allAttributes );
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}

	public List<PromotionResult> evaluate(SessionContext ctx, PromotionEvaluationContext promoContext) {
		List<PromotionResult> results = new ArrayList();
		RestrictionSetResult rsr = this.findEligibleProductsInBasket(ctx, promoContext);
		if (rsr.isAllowedToContinue() && !rsr.getAllowedProducts().isEmpty()) {
			PromotionOrderView view = promoContext.createView(ctx, this, rsr.getAllowedProducts());
			PromotionsManager promotionsManager = PromotionsManager.getInstance();

			while(view.getTotalQuantity(ctx) > 0L) {
				promoContext.startLoggingConsumed(this);
				PromotionOrderEntry entry = view.peek(ctx);
				BigDecimal quantityToDiscount = BigDecimal.valueOf(entry.getQuantity(ctx));
				BigDecimal quantityOfOrderEntry = BigDecimal.valueOf(entry.getBaseOrderEntry().getQuantity(ctx).longValue());
				BigDecimal valueDiscount = 	BigDecimal.valueOf(this.getPriceForOrder(ctx, this.getValueDiscount(ctx),
						promoContext.getOrder(), "productValueDiscountPrice"));
				BigDecimal originalUnitPrice = new BigDecimal(entry.getBasePrice(ctx).toString());
				BigDecimal originalEntryPrice = originalUnitPrice.multiply(quantityToDiscount);
				Currency currency = promoContext.getOrder().getCurrency(ctx);
				BigDecimal adjustedEntryPrice = originalEntryPrice;
				BigDecimal adjustedUnitPrice = originalUnitPrice;
				if(originalUnitPrice.compareTo(valueDiscount) > 0) {
					adjustedEntryPrice = Helper.roundCurrencyValue(ctx, currency, originalEntryPrice.subtract(valueDiscount.multiply(quantityToDiscount)));
					adjustedUnitPrice = Helper.roundCurrencyValue(ctx, currency, adjustedEntryPrice.equals(BigDecimal.ZERO) ? BigDecimal.ZERO :
							adjustedEntryPrice.divide(quantityToDiscount, RoundingMode.HALF_EVEN));
				}
				BigDecimal fiddleAmount = adjustedEntryPrice.subtract(adjustedUnitPrice.multiply(quantityToDiscount));
				PromotionOrderEntryConsumed poec;
				Iterator var18;
				if (fiddleAmount.compareTo(BigDecimal.ZERO) == 0) {
					var18 = view.consume(ctx, quantityToDiscount.longValue()).iterator();

					while(var18.hasNext()) {
						poec = (PromotionOrderEntryConsumed)var18.next();
						poec.setAdjustedUnitPrice(ctx, adjustedUnitPrice.doubleValue());
					}
				} else {
					var18 = view.consume(ctx, quantityToDiscount.longValue() - 1L).iterator();

					while(var18.hasNext()) {
						poec = (PromotionOrderEntryConsumed)var18.next();
						poec.setAdjustedUnitPrice(ctx, adjustedUnitPrice.doubleValue());
					}

					var18 = view.consume(ctx, 1L).iterator();

					while(var18.hasNext()) {
						poec = (PromotionOrderEntryConsumed)var18.next();
						poec.setAdjustedUnitPrice(ctx, Helper.roundCurrencyValue(ctx, currency,
								adjustedUnitPrice.add(fiddleAmount)).doubleValue());
					}
				}

				PromotionResult result = promotionsManager.createPromotionResult(ctx, this, promoContext.getOrder(), 1.0F);
				result.setConsumedEntries(ctx, promoContext.finishLoggingAndGetConsumed(this, true));
				BigDecimal adjustment = Helper.roundCurrencyValue(ctx, currency, adjustedEntryPrice.
						subtract(originalEntryPrice));
				PromotionOrderEntryAdjustAction poeac = promotionsManager.createPromotionOrderEntryAdjustAction(ctx,
						entry.getBaseOrderEntry(),
						quantityOfOrderEntry.longValue(), adjustment.doubleValue());
				result.addAction(ctx, poeac);
				results.add(result);
			}

			return results;
		} else {
			return results;
		}

	}
	public String getResultDescription(SessionContext ctx, PromotionResult promotionResult, Locale locale) {
		AbstractOrder order = promotionResult.getOrder(ctx);
		if (order != null && promotionResult.getFired(ctx)) {
			Double totalDiscount = promotionResult.getTotalDiscount(ctx);
			Double valueDiscount = this.getPriceForOrder(ctx, this.getValueDiscount(ctx), order, "productValueDiscountPrice");
			if(valueDiscount != null) {
				Currency orderCurrency = order.getCurrency(ctx);
				Object[] args = new Object[] { valueDiscount, Helper.formatCurrencyAmount(ctx, locale, orderCurrency, valueDiscount.doubleValue()),
						totalDiscount, Helper.formatCurrencyAmount(ctx, locale, orderCurrency, totalDiscount.doubleValue()) };
				return formatMessage(this.getMessageFired(ctx), args, locale);
			}
		}
		return "";
	}
}
