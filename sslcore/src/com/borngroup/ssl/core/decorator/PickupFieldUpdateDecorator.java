/**
 *
 */
package com.borngroup.ssl.core.decorator;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.ProductDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.CSVCellDecorator;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.ApparelProductModel;

/**
 * @author Techouts
 *
 */
public class PickupFieldUpdateDecorator implements CSVCellDecorator {
	private static final Logger LOG = Logger.getLogger(PickupFieldUpdateDecorator.class);
	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.util.CSVCellDecorator#decorate(int,
	 * java.util.Map)
	 */
	@Override
	public String decorate(final int position, final Map<Integer, String> srcLine) {
		final ModelService modelService = Registry.getApplicationContext().getBean("modelService", ModelService.class);
		final ProductDao productDao = Registry.getApplicationContext().getBean("productDao", ProductDao.class);
		try {
		final String value = srcLine.get(position);
		final String productCode = srcLine.get(position - 1);
		final List<ItemModel> items = new ArrayList<>();
		final List<ProductModel> products =productDao.findProductsByCode(productCode);
                 for(final ProductModel product:products) {
                	 product.setIsAvailable(new Boolean(value));
                	 items.add(product);
					if (product instanceof ApparelProductModel) {
						for (final VariantProductModel style : product.getVariants()) {
							style.setIsAvailable(new Boolean(value));
							items.add(style);
							for (final VariantProductModel size : style.getVariants()) {
								size.setIsAvailable(new Boolean(value));
									items.add(size);
							}
					}

						}
				}
			modelService.saveAll(items);
			return value;
		} catch (final Exception ex) {
			LOG.error("Exception occured while updating the pickup available flag,Reason:" + ex.getMessage());
		}
		return StringUtils.EMPTY;
	}

}
