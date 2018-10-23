package com.borngroup.ssl.core.events;

import de.hybris.platform.orderprocessing.events.OrderProcessingEvent;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;

/**
 * 
 * @author dien.nguyen
 * 
 */

public class RejectFraudOrderEvent extends OrderProcessingEvent {
    
    private static final long serialVersionUID = 1L;

    public RejectFraudOrderEvent(final OrderProcessModel process) {
        super(process);
    }
}
