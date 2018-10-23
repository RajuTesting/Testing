/**
 *
 */
package com.borngroup.ssl.core.crm.dao;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Date;
import java.util.List;

/**
 * @author S53299
 *
 */
public interface SSLOrderDao
{

	/*
	 * CRM CRM-R1 API to search for orders in Hybris based on Hybris email id
	 */
	public List<OrderModel> getOrdersByEmailId(String email, Date startDate, Date endDate, PageableData pageableData);

	public OrderModel getOrderDetailsByOrderNumber(String orderNumber);

    /**
     * @param customer
     * @param count
     * @return required number of orders for customer sorted by date
     */
    List<OrderModel> getOrdersByCustomer(CustomerModel customer, int count);

	//ECD-198 CRM Field Creation
	public List<CustomerModel> getCustomers()throws Exception;
	public List<OrderModel> getOrders(String customer)throws Exception;
	//ECD-198 CRM Field Creation
}
