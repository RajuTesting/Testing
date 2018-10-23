package com.borngroup.ssl.core.dao;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.Date;
import java.util.List;

import com.borngroup.ssl.core.model.CustomerFeedbackModel;

/**
 * The Interface SSLLatestCompletedOrderLookupDao. DAO to access latest
 * completed orders
 *
 * created by : ashish.sabal@nagarro.com
 *
 * @author SSL
 */
public interface SSLLatestCompletedOrderLookupDao {

	/**
	 * method to retrieve Order with COMPLETED status within 30 days.
	 *
	 * @param startDate
	 *            the start date
	 * @param orderStatus
	 *            the order status
	 * @return list of orders that are completed with last 30 days
	 */
	List<OrderModel> getCompletedOrderLatest(Date startDate, OrderStatus orderStatus);

	/**
	 * method to remove feedback data older than 2 years.
	 *
	 * @param startDateForFeedDataRemoval
	 *            the start date for feed data removal
	 * @return list of all data older than 2 years
	 */
	List<CustomerFeedbackModel> getOldOrderFeedbackData(Date startDateForFeedDataRemoval);
}
