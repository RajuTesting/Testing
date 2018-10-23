/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;

import javax.annotation.Resource;

import com.borngroup.ssl.core.model.SslShopBySizeMappingModel;
import com.borngroup.ssl.core.services.SSLLookupService;

/**
 * @author gurkiratmohain
 *
 */
public class SSLShopBySizeRemoveInterceptor implements RemoveInterceptor<SslShopBySizeMappingModel> {

	@Resource(name = "sslLookupService")
	SSLLookupService sslLookupService;

	/**
	 * Updates the sizePriority map when a model is removed from the HMC
	 */
	@Override
	public void onRemove(final SslShopBySizeMappingModel model, final InterceptorContext arg1)
			throws InterceptorException {
		if (null != sslLookupService.getSizePriorityMap()) {
			if (sslLookupService.getSizePriorityMap().containsKey(model.getSize())) {
				sslLookupService.getSizePriorityMap().remove(model.getSize());
			}
		}
	}

}
