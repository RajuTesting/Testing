/**
 *
 */
package com.ss.core.report.dao;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public interface UserReportDao {

	List<CustomerModel> getAllCustomers();

	List<CustomerModel> getAllCustomersOfLastSixMonthOrders();

	/**
	 * This method interacts with database to extract all the customers with
	 * abandoned cart.
	 *
	 * @return List<CustomerModel> List of customers with abandoned cart.
	 */
	List<CustomerModel> getAllCustomersAbandonedCartForOutbound();

}
