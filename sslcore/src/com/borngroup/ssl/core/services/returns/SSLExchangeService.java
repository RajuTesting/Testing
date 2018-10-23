/**
 *
 */
package com.borngroup.ssl.core.services.returns;

import de.hybris.platform.commercefacades.product.data.StockData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.returns.model.ReplacementEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;

import java.util.List;

/**
 * @author nikhilbarar
 *
 */
public interface SSLExchangeService extends SSLReturnService {

	public StockData getStock(ProductModel productModel);

	public void sortStockByAvailableQuantity(List<StockLevelModel> consignments);

	public long getAvailability(StockLevelModel stockLevel);

	public List<StockLevelModel> getStockLevels(ProductModel product);

	public boolean isOutOfStock(ProductModel productModel);

	public ReturnRequestModel generateExchangeRequestPerODC(
			List<ReplacementEntryModel> replacableEntities,
			OrderModel orderModel);
}
