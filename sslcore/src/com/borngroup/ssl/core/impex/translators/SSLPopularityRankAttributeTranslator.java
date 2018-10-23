package com.borngroup.ssl.core.impex.translators;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.impex.jalo.translators.AbstractSpecialValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.servicelayer.model.ModelService;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.product.service.SslProductService;

/**
 * @author nikhilbarar
 *
 */
public class SSLPopularityRankAttributeTranslator extends AbstractSpecialValueTranslator
{
	private static final Logger LOG = Logger.getLogger(SSLPopularityRankAttributeTranslator.class);

	private ModelService modelService;
	private SslProductService sslProductService;

	@Override
	public void performImport(final String cellValue, final Item processedItem) throws ImpExException {
		if (StringUtils.isNotEmpty(cellValue)) {
			modelService = (ModelService) Registry.getApplicationContext().getBean("modelService");
			sslProductService = (SslProductService) Registry.getApplicationContext().getBean("sslProductService");
			if (null != processedItem && processedItem instanceof Product) {
				final ProductModel productModel = modelService.get(processedItem);
				sslProductService.updatePopularity(productModel, NumberUtils.toInt(cellValue));
				LOG.debug("Increasing Popularity of product " + productModel.getCode() + " by " + cellValue);
			}
		}
	}
}
