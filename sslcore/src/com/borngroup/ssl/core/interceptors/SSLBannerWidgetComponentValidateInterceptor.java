package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import com.borngroup.ssl.core.model.SSLBannerWidgetComponentModel;

/**
 * @author tusharkansal
 *
 */
public class SSLBannerWidgetComponentValidateInterceptor implements ValidateInterceptor<SSLBannerWidgetComponentModel> {

	@Override
	public void onValidate(final SSLBannerWidgetComponentModel model, final InterceptorContext ctx)
			throws InterceptorException {
		if (null != model) {
			if (null == model.getMedia().getMediaFormat()) {
				throw new InterceptorException("Banner Media Format cannot be null");
			}
		}
	}
}
