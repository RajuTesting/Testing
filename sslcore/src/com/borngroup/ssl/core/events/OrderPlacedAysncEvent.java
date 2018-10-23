package com.borngroup.ssl.core.events;

import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.util.Config;
import org.apache.log4j.Logger;

/**
 * Async Event representing placing of an order
 *
 */
public class OrderPlacedAysncEvent extends de.hybris.platform.orderprocessing.events.OrderPlacedEvent
		implements ClusterAwareEvent {
	private static final Logger LOG = Logger.getLogger(OrderPlacedAysncEvent.class);
	public OrderPlacedAysncEvent(final OrderProcessModel process) {
		super(process);
	}

	@Override
	public boolean publish(final int sourceNodeId, final int targetNodeId) {
		final int target = Config.getInt("order.process.event.node.id", sourceNodeId);
		return targetNodeId == target;
	}

}
