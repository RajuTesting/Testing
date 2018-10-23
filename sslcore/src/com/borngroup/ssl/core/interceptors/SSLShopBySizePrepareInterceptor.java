/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import javax.annotation.Resource;

import com.borngroup.ssl.core.model.SslShopBySizeMappingModel;
import com.borngroup.ssl.core.services.SSLLookupService;

/**
 * @author gurkiratmohain
 *
 */
public class SSLShopBySizePrepareInterceptor implements PrepareInterceptor<SslShopBySizeMappingModel> {

	@Resource(name = "sslLookupService")
	SSLLookupService sslLookupService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(
	 * java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	/**
	 * gets called whenever model is saved and updates the change done.
	 */
	@Override
	public void onPrepare(final SslShopBySizeMappingModel model, final InterceptorContext paramInterceptorContext)
			throws InterceptorException {
		if (null != sslLookupService.getSizePriorityMap()) {
			if (sslLookupService.getSizePriorityMap().containsKey(model.getSize())) {
				sslLookupService.getSizePriorityMap().replace(model.getSize(), model.getPriority());
			} else {
				sslLookupService.getSizePriorityMap().put(model.getSize(), model.getPriority());
			}
		}
	}
}
