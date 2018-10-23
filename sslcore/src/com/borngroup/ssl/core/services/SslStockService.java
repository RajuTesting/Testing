/**
 *
 */
package com.borngroup.ssl.core.services;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.stock.impl.DefaultStockService;
import de.hybris.platform.stock.strategy.StockLevelProductStrategy;

import java.util.Collection;

import com.borngroup.ssl.core.dao.SSLStockLevelDao;

/**
 * @author Nagarro-Dev
 *
 */
public class SslStockService extends DefaultStockService {

    private SSLStockLevelDao sslStockLevelDao;

    private StockLevelProductStrategy stockLevelProductStrategy;

    @Override
    public Collection<StockLevelModel> getStockLevels(final ProductModel product, final Collection<WarehouseModel> warehouses) {
        final Collection stockLevels = this.sslStockLevelDao.findStockLevelsWithoutOnline(product.getCode(), warehouses);
        return stockLevels;
    }

    public Collection<StockLevelModel> getStockLevelForBufferIncluded(final ProductModel product, final Collection<WarehouseModel> warehouses) {
		return getSslStockLevelDao().findStockLevelWithBuffer(product.getCode(), warehouses, Boolean.TRUE);
	}
    
    public Collection<StockLevelModel> getStockLevelsForBufferExcluded(final ProductModel product, final Collection<WarehouseModel> warehouses) {
		return getSslStockLevelDao().findStockLevelWithBuffer(product.getCode(), warehouses, Boolean.FALSE);
	}
    
    /**
     * @return the stockLevelProductStrategy
     */
    public StockLevelProductStrategy getStockLevelProductStrategy() {
        return stockLevelProductStrategy;
    }

    /**
     * @param stockLevelProductStrategy the stockLevelProductStrategy to set
     */
    @Override
    public void setStockLevelProductStrategy(final StockLevelProductStrategy stockLevelProductStrategy) {
        this.stockLevelProductStrategy = stockLevelProductStrategy;
    }

    /**
     * @return the sslStockLevelDao
     */
    public SSLStockLevelDao getSslStockLevelDao() {
        return sslStockLevelDao;
    }

    /**
     * @param sslStockLevelDao the sslStockLevelDao to set
     */
    public void setSslStockLevelDao(final SSLStockLevelDao sslStockLevelDao) {
        this.sslStockLevelDao = sslStockLevelDao;
    }

}
