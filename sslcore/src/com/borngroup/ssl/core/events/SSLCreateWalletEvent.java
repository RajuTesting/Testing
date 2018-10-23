package com.borngroup.ssl.core.events;

import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;

/**
 * <p>
 * <p>
 * SSLCreateWalletEvent.java : Create wallet event.
 * <p>
 * Created By : anupam.srivastava@nagarro.com
 *
 * @author Ssl
 */
public class SSLCreateWalletEvent extends AbstractCommerceUserEvent {

    /** customerModel **/
    private CustomerModel customerModel;

    public SSLCreateWalletEvent() {

    }

    /**
     * @return the customerModel
     */
    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    /**
     * @param customerModel the customerModel to set
     */
    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

}
