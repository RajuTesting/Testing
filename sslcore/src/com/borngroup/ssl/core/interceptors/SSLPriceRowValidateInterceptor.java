/**
 * 
 */

package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.commerceservices.promotion.CommercePromotionService;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.i18n.impl.DefaultCommonI18NService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

/**
 * @author sriharsha.karuturi
 * 
 */

public class SSLPriceRowValidateInterceptor implements
		ValidateInterceptor<PriceRowModel> {

	private static final Logger LOG = Logger
			.getLogger(SSLPriceRowValidateInterceptor.class);

	@Resource
	ModelService modelService;

	@Resource
	DefaultCommonI18NService commonI18NService;

	@Resource(name = "commercePromotionService")
	CommercePromotionService commercePromotionService;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Override
	public void onValidate(final PriceRowModel priceRow,
			final InterceptorContext arg1) throws InterceptorException {
		final String productCode = priceRow.getProductId();
		if (productCode == null) {
			throw new InterceptorException(
					"Please provide the productId for the price row. ");
		}
	}
}
