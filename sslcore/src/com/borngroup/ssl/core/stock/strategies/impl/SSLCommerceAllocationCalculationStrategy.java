package com.borngroup.ssl.core.stock.strategies.impl;

import de.hybris.platform.commerceservices.stock.strategies.impl.DefaultCommerceAvailabilityCalculationStrategy;
import de.hybris.platform.ordersplitting.model.StockLevelModel;

import org.apache.commons.lang.BooleanUtils;

import com.borngroup.ssl.core.constants.SslCoreConstants;

/**
 * @author anupamsrivastava
 *
 */
public class SSLCommerceAllocationCalculationStrategy extends DefaultCommerceAvailabilityCalculationStrategy {

    private boolean simulatedSourcing;

    @Override
    protected long getAvailableToSellQuantity(final StockLevelModel stockLevel) {
        if (SslCoreConstants.getSterlingSwitch()) {
            return stockLevel.getAvailable();
        } else {
            if (stockLevel.getOnlineSuspense() > 0) {
                return 0;
            }
            long totalAva = stockLevel.getAvailable() - stockLevel.getHoldQuantity() - stockLevel.getNonSellableQuantity()
                    - stockLevel.getReserved() - stockLevel.getBufferQuantity() - stockLevel.getOnlineDamaged()
                    - stockLevel.getOnlineReserved() - stockLevel.getTotalDamaged() - stockLevel.getTotalUnavailable();
            if (BooleanUtils.isTrue(isSimulatedSourcing())) {
                totalAva -= stockLevel.getOnlineHold();
            }
            return totalAva;
        }
    }

    /**
     * @return the simulatedSourcing
     */
    public boolean isSimulatedSourcing() {
        return simulatedSourcing;
    }

    /**
     * @param simulatedSourcing the simulatedSourcing to set
     */
    public void setSimulatedSourcing(final boolean simulatedSourcing) {
        this.simulatedSourcing = simulatedSourcing;
    }
}
