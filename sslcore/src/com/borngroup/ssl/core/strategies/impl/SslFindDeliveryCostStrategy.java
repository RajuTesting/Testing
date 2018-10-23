package com.borngroup.ssl.core.strategies.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.delivery.DeliveryMode;
import de.hybris.platform.order.strategies.calculation.impl.DefaultFindDeliveryCostStrategy;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.util.PriceValue;

import org.apache.log4j.Logger;

/**
 * Strategy : To get delivery cost.
 * <p/>
 * Created by ravi.yadav@nagarro.com
 *
 * @author SSL
 */
public class SslFindDeliveryCostStrategy extends DefaultFindDeliveryCostStrategy {
    /**
     * Logger instance.
     */
    private static final Logger LOG = Logger.getLogger(SslFindDeliveryCostStrategy.class);

    /**
     * This method used to find delivery cost.
     * 
     * @param order - Instance of AbstractOrderModel.
     * @return PriceValue
     */
    @Override
    public PriceValue getDeliveryCost(final AbstractOrderModel order) {
        ServicesUtil.validateParameterNotNullStandardMessage("order", order);
        try {
            getModelService().save(order);
            final AbstractOrder orderItem = getModelService().getSource(order);
            final DeliveryMode dMode = orderItem.getDeliveryMode();
            if (null != dMode) {
                return dMode.getCost(orderItem);
            } else {
                return new PriceValue(order.getCurrency().getIsocode(), 0.0, order.getNet().booleanValue());
            }
        } catch (final Exception e) {
            LOG.error("Could not find deliveryCost for order [" + order.getCode() + "] due to : " + e.getMessage()
                    + "... skipping!, Exception is : " + e);
            return new PriceValue(order.getCurrency().getIsocode(), 0.0, order.getNet().booleanValue());
        }
    }
}
