/**
 *
 */
package com.borngroup.ssl.core.stock.strategies.impl;

import de.hybris.platform.commerceservices.stock.strategies.impl.DefaultCommerceAvailabilityCalculationStrategy;
import de.hybris.platform.ordersplitting.model.StockLevelModel;

import com.borngroup.ssl.core.constants.SslCoreConstants;

/**
 * @author kanagaraj.k
 *
 */
public class SSLCommerceAvailabilityCalculationStrategy extends DefaultCommerceAvailabilityCalculationStrategy {
    @Override
    protected long getAvailableToSellQuantity(final StockLevelModel stockLevel) {
        if (SslCoreConstants.getSterlingSwitch()) {
            return stockLevel.getAvailable();
        } else {
            if (stockLevel.getOnlineSuspense() > 0) {
                return 0;
            }
            return stockLevel.getAvailable() - (stockLevel.getHoldQuantity() < 0 ? 0 : stockLevel.getHoldQuantity())
                    - (stockLevel.getNonSellableQuantity() < 0 ? 0 : stockLevel.getNonSellableQuantity())
                    - (stockLevel.getReserved() < 0 ? 0 : stockLevel.getReserved())
                    - (stockLevel.getBufferQuantity() < 0 ? 0 : stockLevel.getBufferQuantity())
                    - (stockLevel.getOnlineDamaged() < 0 ? 0 : stockLevel.getOnlineDamaged())
                    - (stockLevel.getOnlineReserved() < 0 ? 0 : stockLevel.getOnlineReserved())
                    - (stockLevel.getOnlineHold() < 0 ? 0 : stockLevel.getOnlineHold())
                    - (stockLevel.getTotalDamaged() < 0 ? 0 : stockLevel.getTotalDamaged())
                    - (stockLevel.getTotalUnavailable() < 0 ? 0 : stockLevel.getTotalUnavailable());
        }
    }
}
