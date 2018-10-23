/**
 *
 */
package com.borngroup.ssl.core.crm.service;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Date;
import java.util.List;

/**
 * @author S53299
 *
 */
public interface SSLOrderService
{

	/*
	 * CRM CRM-R1 API to search for orders in Hybris based on Hybris email id
	 */
	public List<OrderData> getOrdersByEmailId(String email, Date startDate, Date endDate, PageableData pageableData);
	public OrderData getOrderDetailsByOrderNumber(String orderNumber);


    /**
     * @param customer
     * @param count
     * @return list of particular number of orders of customers
     */
    public List<OrderModel> getOrdersByCustomer(CustomerModel customer, int count);


	//ECD-119 for CRM Customer Profile
	public List<CustomerModel> getCustomers()throws Exception;
	public List<OrderModel> getOrders(String customer)throws Exception;
	//end ECD-119 for CRM Customer Profile
	

}
