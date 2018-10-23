/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import com.borngroup.ssl.core.model.SslShopBySizeMappingModel;

/**
 * @author gurkiratmohain
 *
 */
public class SSLShopBySizeValidateInterceptor implements ValidateInterceptor<SslShopBySizeMappingModel> {

	@Override
	public void onValidate(final SslShopBySizeMappingModel model, final InterceptorContext paramInterceptorContext)
			throws InterceptorException {
		if ((null == model.getSize() && null != model.getPriority())
				|| (null != model.getSize() && null == model.getPriority())) {
			if (null == model.getSize() && null != model.getPriority()) {
				throw new InterceptorException("Please add size value");
			} else {
				throw new InterceptorException("Please add priority value");
			}
		}
	}

}
