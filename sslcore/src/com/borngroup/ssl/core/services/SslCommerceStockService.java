/**
 *
 */
package com.borngroup.ssl.core.services;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commerceservices.stock.impl.DefaultCommerceStockService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.dao.SslDepartmentBufferstockDao;

/**
 * @author Nagarro-Dev
 *
 */
public class SslCommerceStockService extends DefaultCommerceStockService {

    SslDepartmentBufferstockDao sslDepartmentBufferstockDao;
    Logger LOG = Logger.getLogger(SslCommerceStockService.class);

    @Autowired
    ModelService modelService;
    
    @Autowired
    SslStockService sslStockService;

    public SslDepartmentBufferstockDao getSslDepartmentBufferstockDao() {
        return sslDepartmentBufferstockDao;
    }

    @Override
    public StockLevelStatus getStockLevelStatusForProductAndBaseStore(final ProductModel product,
            final BaseStoreModel baseStore) {
        validateParameterNotNull(product, "product cannot be null");
        validateParameterNotNull(baseStore, "baseStore cannot be null");
        final Long stockLevel = this.getStockLevelForProductAndBaseStore(product, baseStore);
        StockLevelStatus status = StockLevelStatus.INSTOCK;
        if (null != stockLevel && 0L >= stockLevel.longValue()) {
            if (StockLevelStatus.INSTOCK.equals(status)) {
                status = StockLevelStatus.OUTOFSTOCK;
            }
        }
        return status;
    }

    @Override
    public Long getStockLevelForProductAndBaseStore(final ProductModel product, final BaseStoreModel baseStore) {
        validateParameterNotNull(product, "product cannot be null");
        validateParameterNotNull(baseStore, "baseStore cannot be null");

        modelService.refresh(product);
        Long bufferstock = 0L;
        try {
            bufferstock = getSslDepartmentBufferstockDao().getBufferStock(product);
        } catch (final Exception e) {
            LOG.error("Incorrect Subdepartment : " + e.getMessage());
        }
        Long totalStockLevel = Long.valueOf(0);

		Long stockLevelWithBuffer = getCommerceStockLevelCalculationStrategy()
				.calculateAvailability(sslStockService.getStockLevelForBufferIncluded(product,
						getWarehouseSelectionStrategy().getWarehousesForBaseStore(baseStore)));

		Long stockLevelWithoutBuffer = getCommerceStockLevelCalculationStrategy()
				.calculateAvailability(sslStockService.getStockLevelsForBufferExcluded(product,
						getWarehouseSelectionStrategy().getWarehousesForBaseStore(baseStore)));

		// Decrease buffer stock
		if (null != stockLevelWithBuffer && (0L <= stockLevelWithBuffer.longValue())) {
			stockLevelWithBuffer = (stockLevelWithBuffer.longValue() - bufferstock.longValue()) >= 0L
					? (Long.valueOf(stockLevelWithBuffer.longValue() - bufferstock.longValue())) : Long.valueOf(0);
		}

        if (null != stockLevelWithBuffer && (0L <= stockLevelWithBuffer.longValue())) {
            totalStockLevel = Long.valueOf(totalStockLevel.longValue() + stockLevelWithBuffer.longValue());
        }

        if (null != stockLevelWithoutBuffer && (0L <= stockLevelWithoutBuffer.longValue())) {
            totalStockLevel = Long.valueOf(totalStockLevel.longValue() + stockLevelWithoutBuffer.longValue());
        }

        // Reset stock level
        if (totalStockLevel.longValue() <= 0L) {
            totalStockLevel = Long.valueOf(0L);
        }

        // Increase preorder stock
        if (null != product.getPreOrder() && null != product.getPreOrderQty() && product.getPreOrder().booleanValue()) {
            totalStockLevel = Long.valueOf(totalStockLevel.longValue() + product.getPreOrderQty().longValue());
        }
        
        return totalStockLevel;
    }

    public void setSslDepartmentBufferstockDao(final SslDepartmentBufferstockDao sslDepartmentBufferstockDao) {
        this.sslDepartmentBufferstockDao = sslDepartmentBufferstockDao;
    }

}
