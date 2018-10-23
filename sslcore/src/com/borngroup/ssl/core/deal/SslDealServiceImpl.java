/**
 *
 */
package com.borngroup.ssl.core.deal;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.model.ProductPromotionModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.DealComponentModel;

/**
 * @author jasleensaini
 *
 */
public class SslDealServiceImpl implements SslDealService {
	private static final Logger LOG = Logger.getLogger(SslDealServiceImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.borngroup.ssl.core.deal.SslDealService#getDealsFromPromotion(com.
	 * borngroup.ssl.core.model.DealComponentModel)
	 */
	@Override
	public List<ProductModel> getProductsFromDealComponent(final DealComponentModel component) {
		// YTODO Auto-generated method stub
		final List<ProductPromotionModel> promotionList = component.getPromotionList();
		final List<ProductModel> productList = new ArrayList<ProductModel>();
		try {
			if (null != promotionList) {
				for (final ProductPromotionModel promotion : promotionList) {
					productList.addAll(new ArrayList(promotion.getProducts()));
				}
			}
			// sslDealConverter.convert(component);
			if (!productList.isEmpty()) {
				return productList;
			}
		} catch (final Exception e) {
			LOG.error("Failed to get products in deal");
		}

		return Collections.EMPTY_LIST;
	}

}
