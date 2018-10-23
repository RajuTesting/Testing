/**
 *
 */
package com.ss.core.report.service.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.data.enums.ReasonForAbandonedCartEnum;
import com.ss.core.report.dao.UserReportDao;
import com.ss.core.report.service.UserReportService;

public class DefaultUserReportService implements UserReportService {

	/**
	 * Constant Logger.
	 */
	Logger LOG = Logger.getLogger(DefaultUserReportService.class);

	/**
	 * User Report DAO {@link UserReportDao}.
	 */
	private UserReportDao userReportDao;

	/**
	 * Reason For Abandoned Cart Enum {@link ReasonForAbandonedCartEnum}.
	 */
	ReasonForAbandonedCartEnum reasonForAbandonedCartEnum;

	/**
	 *
	 * @return UserReportDao
	 */
	public UserReportDao getUserReportDao() {
		return userReportDao;
	}

	/**
	 * @param userReportDao
	 *            the userReportDao to set
	 */
	public void setUserReportDao(final UserReportDao userReportDao) {
		this.userReportDao = userReportDao;
	}

	@Override
	public Set<CustomerModel> getCustomersWithAbondendCart() {
		LOG.info("################## Calling Service for Abandoned Cart User##################");
		final List<CustomerModel> customers = getUserReportDao().getAllCustomers();

		final Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DATE, -1);
		calendar2.set(Calendar.HOUR, 0);
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		final Date date2 = calendar2.getTime();
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		final Date currentDate = calendar.getTime();
		final Set<CustomerModel> filteredList = new LinkedHashSet();

		if (CollectionUtils.isNotEmpty(customers)) {

			for (final CustomerModel customer : customers) {

				if (!customer.getCarts().isEmpty()) {

					if (CollectionUtils.isNotEmpty(customer.getCarts())) {
						for (final CartModel cart : customer.getCarts()) {
							if ((cart.getTotalPrice().doubleValue() != 0.00 || cart.getEntries().size() > 0)) {

								if (null != cart.getLastUpdatedTime() && cart.getIsApplicableForAbondedNotify() != null
										&& cart.getIsApplicableForAbondedNotify().booleanValue()
										&& cart.getIsApplicableForAbondedNotify() != null) {
									if (cart.getLastUpdatedTime().before(currentDate)
											&& cart.getLastUpdatedTime().after(date2)) {
										filteredList.add(customer);

										break;
									}

								}

							}
						}
					} else {
						LOG.info("################## null/empty cart with customers #### " + customer.getPk()
								+ "##################");
					}
				}
			}
		} else {
			LOG.info("################## customers null/emtpty from dao ##################");
		}

		return filteredList;
	}

	@Override
	public List<CustomerModel> getCustomersWithLastSixMonthOrder() {

		final List<CustomerModel> customers = getUserReportDao().getAllCustomersOfLastSixMonthOrders();

		for (final CustomerModel customer : customers) {
			if (customer.getCarts().isEmpty()) {

				// TODO LOGIC
			}
		}
		// getSslEmailService().sendAbandonedNotificationEmail(customers);
		return customers;
	}

	/**
	 *
	 * This method interacts with DAO which brings customers with abandoned cart
	 * in the database and then filters on the basis of modified time of cart.
	 *
	 * @return Set<CustomerModel> set of customers with abandoned cart.
	 */
	@Override
	public Set<CustomerModel> getCustomersWithAbandonedCartOutBound() {
		LOG.info("################## Calling Service for Abandoned Cart User##################");
		final List<CustomerModel> customers = getUserReportDao().getAllCustomersAbandonedCartForOutbound();
		final Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DATE, -1);
		calendar2.set(Calendar.HOUR, +3);
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		final Date startDate = calendar2.getTime();
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		// calendar.set(Calendar.HOUR, +10);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		final Date currentDate = calendar.getTime();
		final Set<CustomerModel> filteredList = new LinkedHashSet();
		for (final CustomerModel customer : customers) {
			if (!customer.getCarts().isEmpty()) {
				for (final CartModel cart : customer.getCarts()) {
					if ((cart.getTotalPrice().doubleValue() != 0.00 || cart.getEntries().size() > 0)) {
						if (cart.getModifiedtime().before(currentDate) && cart.getModifiedtime().after(startDate)
								&& !ReasonForAbandonedCartEnum.CUSTOMER_NOT_INTERESTED.getName()
										.equalsIgnoreCase(cart.getCustomerService())) {
							filteredList.add(customer);
							break;
						}

					}
				}
			}
		}
		return filteredList;
	}
}
