/**
 *
 */
package com.borngroup.ssl.core.order.strategies.impl;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceAddToCartStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @author S54606
 *
 */
public class SSLCommerceAddToCartStrategy extends DefaultCommerceAddToCartStrategy {

	@Resource(name = "stockService")
	private StockService stockService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commerceservices.order.impl.
	 * AbstractCommerceAddToCartStrategy#afterAddToCart(de.hybris.platform.
	 * commerceservices.service.data.CommerceCartParameter,
	 * de.hybris.platform.commerceservices.order.CommerceCartModification)
	 */
	@Override
	protected void afterAddToCart(final CommerceCartParameter parameters, final CommerceCartModification result)
			throws CommerceCartModificationException {
		// YTODO Auto-generated method stub
		super.afterAddToCart(parameters, result);
		final CartModel cartModel = parameters.getCart();
		cartModel.setLastUpdatedTime(new Date());
		cartModel.setIsApplicableForAbondedNotify(true);
		getModelService().save(cartModel);
	}

	@Override
	protected long getAllowedCartAdjustmentForProduct(final CartModel cartModel, final ProductModel productModel,
			final long quantityToAdd, final PointOfServiceModel pointOfServiceModel)
	{
		long stockLevel = 0;
		final long cartLevel = checkCartLevel(productModel, cartModel, pointOfServiceModel);
		if(!cartModel.getUserCart()){
			stockLevel = stockService.getTotalStockLevelAmount(productModel);
		}
		else {
			stockLevel = getAvailableStockLevel(productModel, pointOfServiceModel);
		}
		// How many will we have in our cart if we add quantity
		final long newTotalQuantity = cartLevel + quantityToAdd;

		// Now limit that to the total available in stock
		final long newTotalQuantityAfterStockLimit = Math.min(newTotalQuantity, stockLevel);

		// So now work out what the maximum allowed to be added is (note that
		// this may be negative!)
		final Integer maxOrderQuantity = productModel.getMaxOrderQuantity();

		if (isMaxOrderQuantitySet(maxOrderQuantity))
		{
			final long newTotalQuantityAfterProductMaxOrder = Math
					.min(newTotalQuantityAfterStockLimit, maxOrderQuantity.longValue());
			return newTotalQuantityAfterProductMaxOrder - cartLevel;
		}
		return newTotalQuantityAfterStockLimit - cartLevel;
	}
}
