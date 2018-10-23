/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import com.borngroup.ssl.core.model.SslProductValueDiscountPromotionModel;
import de.hybris.platform.acceleratorservices.model.promotions.AcceleratorProductBOGOFPromotionModel;
import de.hybris.platform.acceleratorservices.model.promotions.AcceleratorProductMultiBuyPromotionModel;
import de.hybris.platform.promotions.model.ProductBOGOFPromotionModel;
import de.hybris.platform.promotions.model.ProductBundlePromotionModel;
import de.hybris.platform.promotions.model.ProductFixedPricePromotionModel;
import de.hybris.platform.promotions.model.ProductMultiBuyPromotionModel;
import de.hybris.platform.promotions.model.ProductOneToOnePerfectPartnerPromotionModel;
import de.hybris.platform.promotions.model.ProductPercentageDiscountPromotionModel;
import de.hybris.platform.promotions.model.ProductPerfectPartnerBundlePromotionModel;
import de.hybris.platform.promotions.model.ProductPerfectPartnerPromotionModel;
import de.hybris.platform.promotions.model.ProductPromotionModel;
import de.hybris.platform.promotions.model.ProductSteppedMultiBuyPromotionModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import java.text.MessageFormat;
import java.util.Locale;

import javax.annotation.Resource;

import com.borngroup.ssl.core.model.SslProductBOGODiscountPromotionModel;
import com.borngroup.ssl.core.model.SslProductBOGOFPromotionModel;
import com.borngroup.ssl.core.model.SslProductMultiBuyDiscountPromotionModel;
import com.borngroup.ssl.core.model.SslProductPerfectPartnerDiscountPromotionModel;
import com.borngroup.ssl.core.model.SslProductSteppedMultiBuyDiscountPromotionModel;

/**
 * @author guneetsingh
 *
 */
public class SslProductPromotionValidateInterceptor implements ValidateInterceptor<ProductPromotionModel> {

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
	public void onValidate(final ProductPromotionModel promotionResult, final InterceptorContext ctx)
			throws InterceptorException {

		final Locale locale = i18nService.getCurrentLocale();
		String firedMessage = null;
		String couldFiredMessage = null;
		switch (promotionResult.getItemtype()) {
		case "AcceleratorProductBOGOFPromotion":
			firedMessage = ((AcceleratorProductBOGOFPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((AcceleratorProductBOGOFPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "AcceleratorProductMultiBuyPromotion":
			firedMessage = ((AcceleratorProductMultiBuyPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((AcceleratorProductMultiBuyPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "ProductBOGOFPromotion":
			firedMessage = ((ProductBOGOFPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((ProductBOGOFPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "ProductBundlePromotion":
			firedMessage = ((ProductBundlePromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((ProductBundlePromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "ProductFixedPricePromotion":
			firedMessage = ((ProductFixedPricePromotionModel) promotionResult).getMessageFired();
			break;
		case "ProductMultiBuyPromotion":
			firedMessage = ((ProductMultiBuyPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((ProductMultiBuyPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "ProductOneToOnePerfectPartnerPromotion":
			firedMessage = ((ProductOneToOnePerfectPartnerPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((ProductOneToOnePerfectPartnerPromotionModel) promotionResult)
					.getMessageCouldHaveFired();
			break;
		case "ProductPercentageDiscountPromotion":
			firedMessage = ((ProductPercentageDiscountPromotionModel) promotionResult).getMessageFired();
			break;
		case "ProductPerfectPartnerBundlePromotion":
			firedMessage = ((ProductPerfectPartnerBundlePromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((ProductPerfectPartnerBundlePromotionModel) promotionResult)
					.getMessageCouldHaveFired();
			break;
		case "ProductPerfectPartnerPromotion":
			firedMessage = ((ProductPerfectPartnerPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((ProductPerfectPartnerPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "ProductSteppedMultiBuyPromotion":
			firedMessage = ((ProductSteppedMultiBuyPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((ProductSteppedMultiBuyPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "SslProductBOGODiscountPromotion":
			firedMessage = ((SslProductBOGODiscountPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((SslProductBOGODiscountPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "SslProductMultiBuyDiscountPromotion":
			firedMessage = ((SslProductMultiBuyDiscountPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((SslProductMultiBuyDiscountPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "SslProductPerfectPartnerDiscountPromotion":
			firedMessage = ((SslProductPerfectPartnerDiscountPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((SslProductPerfectPartnerDiscountPromotionModel) promotionResult)
					.getMessageCouldHaveFired();
			break;
		case "SslProductSteppedMultiBuyDiscountPromotion":
			firedMessage = ((SslProductSteppedMultiBuyDiscountPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((SslProductSteppedMultiBuyDiscountPromotionModel) promotionResult)
					.getMessageCouldHaveFired();
			break;
		case "SslProductBOGOFPromotion":
			firedMessage = ((SslProductBOGOFPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((SslProductBOGOFPromotionModel) promotionResult).getMessageCouldHaveFired();
			break;
		case "SslProductValueDiscountPromotion":
			firedMessage = ((SslProductValueDiscountPromotionModel) promotionResult).getMessageFired();
			couldFiredMessage = ((SslProductValueDiscountPromotionModel) promotionResult).getMessageCouldHaveFired();
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
				throw new InterceptorException("Please correct the message/messages arguments for the promotion");
			}
			// YTODO: handle exception
		}
	}
}
