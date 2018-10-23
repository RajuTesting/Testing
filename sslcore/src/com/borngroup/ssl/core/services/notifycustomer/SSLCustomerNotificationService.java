/**
 *
 */
package com.borngroup.ssl.core.services.notifycustomer;

import de.hybris.platform.core.model.product.ProductModel;

import org.apache.commons.mail.EmailException;

import com.borngroup.ssl.facades.data.SSLCustomerNotificationData;

/**
 * @author Nagarro Dev
 *
 */
public interface SSLCustomerNotificationService {

    /**
     * @param customerNotificationData
     * @param product
     * @throws EmailException
     */
    void notifyCustomer(SSLCustomerNotificationData customerNotificationData, ProductModel product) throws EmailException;

    /**
     * @param sslCustomerNotificationData
     * @return
     */
    boolean isCustomerAlreadyRequestedForNotification(SSLCustomerNotificationData sslCustomerNotificationData);

}
