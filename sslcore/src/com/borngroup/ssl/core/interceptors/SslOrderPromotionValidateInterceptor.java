/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.promotions.model.OrderPromotionModel;
import de.hybris.platform.promotions.model.OrderThresholdChangeDeliveryModePromotionModel;
import de.hybris.platform.promotions.model.OrderThresholdDiscountPromotionModel;
import de.hybris.platform.promotions.model.OrderThresholdFreeGiftPromotionModel;
import de.hybris.platform.promotions.model.OrderThresholdFreeVoucherPromotionModel;
import de.hybris.platform.promotions.model.OrderThresholdPerfectPartnerPromotionModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import java.text.MessageFormat;
import java.util.Locale;

import javax.annotation.Resource;

import com.borngroup.ssl.core.model.SslOrderLimitDiscountPercentagePromotionModel;
import com.borngroup.ssl.core.model.SslOrderSteppedMultiBuyDiscountPromotionModel;
import com.borngroup.ssl.core.model.SslOrderThresholdFixedDiscountPromotionModel;

/**
 * @author guneetsingh
 *
 */
public class SslOrderPromotionValidateInterceptor implements ValidateInterceptor<OrderPromotionModel> {

	@Resource(name = "i18nService")
	private I18NService i18nService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.ValidateInterceptor#
	 * onValidate(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onValidate(final OrderPromotionModel promotionResult, final InterceptorContext ctx)
			throws InterceptorException {

		final Locale locale = i18nService.getCurrentLocale();
		String firedMessage = null;
		String couldFiredMessage = null;
		switch (promotionResult.getItemtype()) {
		case "OrderThresholdChangeDeliveryModePromotion":
			firedMessage = ((OrderThresholdChangeDeliveryModePromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((OrderThresholdChangeDeliveryModePromotionModel) promotionResult)
					.getMessageCouldHaveFired();
			break;
		case "OrderThresholdDiscountPromotion":
			firedMessage = ((OrderThresholdDiscountPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((OrderThresholdDiscountPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "OrderThresholdFreeGiftPromotion":
			firedMessage = ((OrderThresholdFreeGiftPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((OrderThresholdFreeGiftPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "OrderThresholdFreeVoucherPromotion":
			firedMessage = ((OrderThresholdFreeVoucherPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((OrderThresholdFreeVoucherPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "OrderThresholdPerfectPartnerPromotion":
			firedMessage = ((OrderThresholdPerfectPartnerPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((OrderThresholdPerfectPartnerPromotionModel) promotionResult)
					.getMessageCouldHaveFired();
			break;
		case "SslOrderLimitDiscountPercentagePromotion":
			firedMessage = ((SslOrderLimitDiscountPercentagePromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((SslOrderLimitDiscountPercentagePromotionModel) promotionResult)
					.getMessageCouldHaveFired();
			break;
		case "SslOrderThresholdFixedDiscountPromotion":
			firedMessage = ((SslOrderThresholdFixedDiscountPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((SslOrderThresholdFixedDiscountPromotionModel) promotionResult)
					.getMessageCouldHaveFired();
			break;
		case "SslOrderSteppedMultiBuyDiscountPromotion":
			firedMessage = ((SslOrderSteppedMultiBuyDiscountPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((SslOrderSteppedMultiBuyDiscountPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		}

		try {
			if (firedMessage != null) {
				new MessageFormat(firedMessage, locale);
			}
			if (couldFiredMessage != null) {
				new MessageFormat(couldFiredMessage, locale);
			}
		} catch (final Exception e) {
			if (e instanceof IllegalArgumentException) {
				throw new InterceptorException("Please correct the message/message arguements for the promotion");
			}
			// YTODO: handle exception
		}
	}
}
