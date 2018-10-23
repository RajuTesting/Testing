/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Midhun Bose 			SSL-19 					Base Version
 *
 */
package com.borngroup.ssl.core.dao;

import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.Collection;
import java.util.List;

/**
 * @author midhun.bose
 *
 */
public interface SSLStockLevelDao {
    public List<StockLevelModel> getStockLevels(int pageNo, int pageLimit);

    /**
     * @param productCode
     * @param warehouses
     * @return
     */
    Collection<StockLevelModel> findStockLevelsWithoutOnline(String productCode, Collection<WarehouseModel> warehouses);

	/**
	 * @param productCode
	 * @param warehouses
	 * @param bufferIncluded
	 * @return
	 */
	Collection<StockLevelModel> findStockLevelWithBuffer(String productCode, Collection<WarehouseModel> warehouses,
			Boolean bufferIncluded);
}
