/**
 *
 */
package com.borngroup.ssl.core.crm.dao.impl;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.borngroup.ssl.core.crm.dao.SSLOrderDao;

/**
 * @author S53299
 *
 */
@Component("sslDefaultOrderDao")
public class SSLDefaultOrderDao implements SSLOrderDao
{

	private final static Logger log = Logger.getLogger(SSLDefaultOrderDao.class);

	@Autowired
	@Qualifier("flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.borngroup.ssl.core.crm.dao.SSLOrderDao#getOrdersByEmailId(java.lang
	 * .String)
	 */
	@Override
	public List<OrderModel> getOrdersByEmailId(final String email, final Date startDate, final Date endDate, final PageableData pageableData)
	{

		log.info("inside CRM/SSLDefaultOrderDao/getOrdersByEmailId......");

		final Map<String, Object> queryParams = new HashMap<String, Object>();

		// queryParams.put("email", email);

		// final String queryStr =
		// "select {o.pk} from {Order AS o},{User AS u}  where {o.user} = {u.pk}";

		final StringBuilder queryStr = new StringBuilder();

		queryStr.append(" select {o.pk} ");
		queryStr.append(" from {Order As o} , {Customer As c} ");
		queryStr.append(" where {o.user} = {c.pk} ");
		queryStr.append(" and {c.uid} like '%" + email + "'");
		queryStr.append(" and {o.versionID} IS NULL ");

		if (null != startDate && null != endDate)
		{
			queryStr.append(" and {o.creationtime} >= ?startDate ");
			queryStr.append(" and {o.creationtime} <= ?endDate ");

			queryParams.put("startDate", startDate);
			queryParams.put("endDate", endDate);
		}
		else if (null != startDate && null == endDate)
		{
			queryStr.append(" and {o.creationtime} >= ?startDate ");

			queryParams.put("startDate", startDate);
		}
		else if (null == startDate && null != endDate)
		{
			queryStr.append(" and {o.creationtime} <= ?endDate ");

			queryParams.put("endDate", endDate);
		}

		if (null != pageableData)
		{
			queryStr.append(" order by {o.creationtime}  " + pageableData.getSort());
		}
		else
		{
			queryStr.append(" order by {o.creationtime} desc ");
		}

		log.info("Query >> " + queryStr.toString());

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryStr);

		query.addQueryParameters(queryParams);
		query.setResultClassList(Collections.singletonList(OrderModel.class));

		if (null != pageableData)
		{
			final int start = 0;
			final int pageSize = pageableData.getPageSize();

			query.setCount(pageSize);
			query.setNeedTotal(true);
			query.setStart(start);
		}

		final SearchResult<OrderModel> result = flexibleSearchService.search(query);

		return result.getResult();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.borngroup.ssl.core.crm.dao.SSLOrderDao#getOrderDetailsByOrderNumber
	 * (java.lang.String,
	 * de.hybris.platform.commerceservices.search.pagedata.PageableData)
	 */
	@Override
	public OrderModel getOrderDetailsByOrderNumber(final String orderNumber)
	{
		log.info("inside CRM/SSLDefaultOrderDao/getOrderDetailsByOrderNumber......");

		final Map<String, Object> queryParams = new HashMap<String, Object>();

		final String formattedOrderNumber = String.format("%08d", Integer.parseInt(orderNumber));

		log.info("Formatted Order Number " + formattedOrderNumber);

		queryParams.put("orderNumber", formattedOrderNumber);

		final StringBuilder queryStr = new StringBuilder();

		queryStr.append(" select {o.pk} ");
		queryStr.append(" from {Order As o} ");
		queryStr.append(" where {o.code} = ?orderNumber ");
		queryStr.append(" and {o.versionID} IS NULL ");

		log.info("Query >> " + queryStr.toString());

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryStr);

		query.addQueryParameters(queryParams);
		query.setResultClassList(Collections.singletonList(OrderModel.class));

		final SearchResult<OrderModel> result = flexibleSearchService.search(query);

		final List<OrderModel> orderModelList = result.getResult();

		if (!CollectionUtils.isEmpty(orderModelList))
		{
			return orderModelList.get(0);
		}
		else
		{
			return null;
		}
	}

	
    @Override
    public List<OrderModel> getOrdersByCustomer(final CustomerModel customer, int count) {
        final Map<String, Object> queryParams = new HashMap<String, Object>();

        final StringBuilder queryStr = new StringBuilder();
        queryStr.append(" select {o.pk} ");
        queryStr.append(" from {Order As o} ");
        queryStr.append(" where {o.user} = ?customer and {o.versionId} is null");
        queryStr.append(" ORDER BY {o.creationtime} DESC");

        queryParams.put("customer", customer.getPk());
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryStr);
        if (count > 0) {
            query.setCount(count);
        }
        query.addQueryParameters(queryParams);
        final SearchResult<OrderModel> result = flexibleSearchService.search(query);
        return result.getResult();

    }

	//Added By Koteswara Rao for ECD-198
	@Override
	public List<CustomerModel> getCustomers()throws Exception{
        String query="select  distinct {C:pk} as from {Order as O  join Customer as C on {C:pk}={O:user}} where  {O:versionID} is null";
        List<CustomerModel> customerModelList=null;
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
		searchQuery.setResultClassList(Collections.singletonList(CustomerModel.class));
		final SearchResult<CustomerModel> result = flexibleSearchService.search(searchQuery);
		customerModelList = result.getResult();
		if (!CollectionUtils.isEmpty(customerModelList)){
			return customerModelList;
		}
		return customerModelList;
	}
	@Override
	public List<OrderModel> getOrders(String customer)throws Exception{
        String query="select distinct  pk,rank from ({{select {O:pk} as pk,dense_RANK() OVER (PARTITION BY {O:user} ORDER BY {O:creationtime} desc) as RANK from {Order as O  join Customer as C on {O:user}=?customer"
             		+ " left join ADDRESS as A on {O:deliveryaddress}={A:pk}} "
             		+ " where  {O:versionID} is null  order by {C:creationtime} }})  where rank < 6";
        List<OrderModel> ordersList=null;
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
		searchQuery.addQueryParameter("customer", customer);
		searchQuery.setResultClassList(Collections.singletonList(OrderModel.class));
		final SearchResult<OrderModel> result = flexibleSearchService.search(searchQuery);
		ordersList = result.getResult();
		if (!CollectionUtils.isEmpty(ordersList)){
			return ordersList;
		}
		return ordersList;
	}
	//End  for ECD-198
	

}
