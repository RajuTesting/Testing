package com.borngroup.ssl.core.events;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;


/**
 * 
 * @author dien.nguyen
 * 
 */

public class OrderRefundAmountEvent extends AbstractEvent
{

	private static final long serialVersionUID = 1L;

	private final OrderModel order;

	public OrderRefundAmountEvent(final OrderModel order)
	{
		this.order = order;
	}

	public OrderModel getOrder()
	{
		return order;
	}
}
