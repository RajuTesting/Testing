/**
 *
 */
package com.borngroup.ssl.core.dao;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.store.BaseStoreModel;

import java.util.List;

/**
 * @author tusharkansal
 *
 */
public interface SSLCustomerOrderDao {

    /**
     * Finds orders for the specified user in the current session's active catalog versions
     *
     * @param customerModel the customer
     * @param store The current store
     * @param status A list of order statuses to include in the result, if null or empty then all statuses are included
     * @param pageableData
     *
     * @return The search page data
     *
     */
    public SearchPageData<OrderModel> getOrderListbyConsignmentStatus(CustomerModel customerModel, BaseStoreModel store,
            ConsignmentStatus[] status, OrderStatus[] orderStatus, PageableData pageableData, boolean statusIn);

    /**
     * Finds orders for the specified user in the current session's active catalog versions
     *
     * @param customerModel the customer
     * @param store The current store
     * @param status A list of order statuses to include in the result, if null or empty then all statuses are included
     * @param pageableData The pagination data
     * @return The list of orders owned by the customer associated with the store
     */
    SearchPageData<OrderModel> findOrdersByCustomerAndStore(CustomerModel customerModel, BaseStoreModel store, OrderStatus[] status,
            PageableData pageableData);

    /**
     * Fetch duplicate orders
     * @param order
     * @return
     */
    List<OrderModel> findDuplicateCartOrders(AbstractOrderModel order);
}
