/**
 *
 */
package com.borngroup.ssl.core.services.customer.account;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.customer.TokenInvalidatedException;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.store.BaseStoreModel;

/**
 * @author vinaygupta
 *
 */
public interface SSLCustomerAccountService extends CustomerAccountService {

    public void updatePasswordAndSetCurrentUser(final String token, final String newPassword) throws TokenInvalidatedException;

    /**
     * Returns the order of the supplied user filtering by Consignment Status
     *
     * @param customerModel
     *        the user to retrieve orders for
     * @param store
     *        the current store
     * @param status
     *        One or more ConsignmentStatuses to include in the result
     * @param pageableData
     *        pagination information
     * @param statusIn
     *        check for status in given status
     * @return the list of orders
     */
    SearchPageData<OrderModel> getOrderListbyConsignmentStatus(CustomerModel customerModel, BaseStoreModel store,
            ConsignmentStatus[] status, OrderStatus[] orderStatus, PageableData pageableData, boolean statusIn);

}
