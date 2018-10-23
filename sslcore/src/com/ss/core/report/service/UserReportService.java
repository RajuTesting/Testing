/**
 *
 */
package com.ss.core.report.service;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;
import java.util.Set;

/**
 * @author S52235
 *
 */
public interface UserReportService {

	Set<CustomerModel> getCustomersWithAbondendCart();

	List<CustomerModel> getCustomersWithLastSixMonthOrder();

	/**
	 * Get customers with abandoned cart.
	 *
	 * @return Set<CustomerModel> set of customers with abandoned cart.
	 */
	Set<CustomerModel> getCustomersWithAbandonedCartOutBound();
}
