/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

/**
 * @author tejsharma
 *
 */
public class BankDetailsOTPEvent<T extends BaseSiteModel> extends AbstractEvent {
    private static final long serialVersionUID = 1L;

    private final OrderProcessModel process;

    /**
     * @return the process
     */
    public OrderProcessModel getProcess() {
        return process;
    }

    public BankDetailsOTPEvent(final OrderProcessModel process) {
        this.process = process;
    }

}
