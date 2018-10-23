package com.borngroup.ssl.core.events;

import de.hybris.platform.orderprocessing.events.ConsignmentProcessingEvent;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;


/**
 * 
 * @author dien.nguyen
 * 
 */

public class ConsignmentInvoiceEvent extends ConsignmentProcessingEvent
{

	private static final long serialVersionUID = 1L;

	public ConsignmentInvoiceEvent(final ConsignmentProcessModel process)
	{
		super(process);
	}
}
