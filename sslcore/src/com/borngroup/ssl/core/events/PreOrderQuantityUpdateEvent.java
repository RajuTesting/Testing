package com.borngroup.ssl.core.events;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import de.hybris.platform.util.Config;
import org.apache.log4j.Logger;

/**
 * @author ssl
 *
 * Pre order quantity update event.
 */
public class PreOrderQuantityUpdateEvent extends AbstractEvent implements ClusterAwareEvent
{
    private static final Logger LOG = Logger.getLogger(PreOrderQuantityUpdateEvent.class);

    private OrderModel order;

    public PreOrderQuantityUpdateEvent(final OrderModel order)
    {
        this.order = order;
    }

    @Override
    public boolean publish(final int sourceNodeId, final int targetNodeId)
    {
        final int target = Config.getInt("order.process.event.node.id", sourceNodeId);
        if(targetNodeId == target) {
            LOG.info(String.format("Executing event for order= %s on cluster.id =%s", order.getCode(), target));
        }
        return targetNodeId == target;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }
}
