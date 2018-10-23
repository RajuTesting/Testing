package com.borngroup.ssl.core.events;

import de.hybris.platform.orderprocessing.events.OrderProcessingEvent;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.util.Config;
import org.apache.log4j.Logger;

/**
 * Async Event representing completion of order process.
 */
public class OrderReceivedEvent extends OrderProcessingEvent implements ClusterAwareEvent {

	private static final Logger LOG = Logger.getLogger(OrderReceivedEvent.class);
	private static final long serialVersionUID = -293422455711438189L;

	public OrderReceivedEvent(final OrderProcessModel process)
	{
		super(process);
	}

	@Override
	public boolean publish(final int sourceNodeId, final int targetNodeId) {
		final int target = Config.getInt("order.process.event.node.id", sourceNodeId);
		return targetNodeId == target;
	}
}
