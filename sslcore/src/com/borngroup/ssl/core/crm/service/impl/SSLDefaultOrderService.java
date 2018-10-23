/**
 *
 */
package com.borngroup.ssl.core.crm.service.impl;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.borngroup.ssl.core.crm.dao.SSLOrderDao;
import com.borngroup.ssl.core.crm.service.SSLOrderService;

/**
 * @author S53299
 *
 */

@Service("sslDefaultOrderService")
public class SSLDefaultOrderService implements SSLOrderService
{

	private final static Logger log = Logger.getLogger(SSLDefaultOrderService.class);

	@Autowired
	@Qualifier("sslDefaultOrderDao")
	private SSLOrderDao sslDefaultOrderDao;

	@Autowired
	@Qualifier("orderConverter")
	private Converter<OrderModel, OrderData> orderConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.borngroup.ssl.core.crm.service.OrderService#getOrdersByEmailId(java
	 * .lang.String)
	 */
	@Override
	public List<OrderData> getOrdersByEmailId(final String email, final Date startDate, final Date endDate, final PageableData pageableData)
	{

		log.info("inside CRM/SSLDefaultOrderService/getOrdersByEmailId......");

		final List<OrderData> orderDataList = new ArrayList<OrderData>();

		final List<OrderModel> ordeModelList = sslDefaultOrderDao.getOrdersByEmailId(email, startDate, endDate, pageableData);

		if (!CollectionUtils.isEmpty(ordeModelList))
		{
			for (int i = 0; i < ordeModelList.size(); i++)
			{
				orderDataList.add(orderConverter.convert(ordeModelList.get(i)));
			}
		}

		return orderDataList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.borngroup.ssl.core.crm.service.SSLOrderService#
	 * getOrderDetailsByOrderNumber(java.lang.String,
	 * de.hybris.platform.commerceservices.search.pagedata.PageableData)
	 */
	@Override
	public OrderData getOrderDetailsByOrderNumber(final String orderNumber)
	{
		log.info("inside CRM/SSLDefaultOrderService/getOrderDetailsByOrderNumber......");

		final OrderModel orderModel = sslDefaultOrderDao.getOrderDetailsByOrderNumber(orderNumber);

		if (null != orderModel)
		{
			return orderConverter.convert(orderModel);
		}
		else
		{
			return null;
		}
	}

	
	@Override
    public List<OrderModel> getOrdersByCustomer(final CustomerModel customer, int count) {
        return sslDefaultOrderDao.getOrdersByCustomer(customer, count);
    }
	//ECD-198 CRM Field Creation added by Koteswara Rao 
	@Override
    public List<CustomerModel> getCustomers()throws Exception{
		List<CustomerModel> customersList=null;
		try{
			customersList= sslDefaultOrderDao.getCustomers();
		if (!CollectionUtils.isEmpty(customersList))
		{
			return customersList;
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return customersList;
	}
	@Override
    public List<OrderModel> getOrders(String customer)throws Exception{
		List<OrderModel> orders=null;
		try{
			orders= sslDefaultOrderDao.getOrders(customer);
		if (!CollectionUtils.isEmpty(orders))
		{
			return orders;
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return orders;
	}
	// end ECD-198 CRM Field Creation added by Koteswara Rao 

}
