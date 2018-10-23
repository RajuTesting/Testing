/**
 *
 */
package com.ss.job.services.mail;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Set;

/**
 * @author S52235
 *
 */
public interface SslEmailService {

    void sendAbandonedNotificationEmail(Set<CustomerModel> customers);

    void sendAbandonedEmailToBusiness(Set<CustomerModel> customers);

    void sendFccCrmTicketEmail(CustomerData customer, String dirPath);
}
