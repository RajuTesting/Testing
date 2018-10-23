/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.CustomerBirthdayDao;

/**
 * @author satyanarayana.naidu
 *
 */
public class DefaultCustomerBirthdayDao implements CustomerBirthdayDao {

	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.borngroup.ssl.core.dao.CustomerBirthdayDao#getDateOfBirth()
	 */
	@Override
	public List<CustomerModel> getDateOfBirth() {
		final Date d = new Date();
		final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd");
		final String customerDate = DATE_FORMAT.format(d);

		// Select {dateOfBirth} from {Customer} where {dateOfBirth} like
		// '%09-16%'

		/*
		 * final LIKE ?postcode searchQuery.addQueryParameter("customerName",
		 * (new StringBuilder("%")).append(name.trim()).append("%")
		 * .toString());
		 */

		final String query = "SELECT {" + CustomerModel.PK + "} FROM {" + CustomerModel._TYPECODE + "} WHERE {"
				+ CustomerModel.DATEOFBIRTH + "} like ?customerDate";

		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
		flexibleSearchQuery.addQueryParameter("customerDate",
				(new StringBuilder("%")).append(customerDate.trim()).append("%").toString());
		flexibleSearchQuery.setResultClassList(Collections.singletonList(CustomerModel.class));
		final SearchResult<CustomerModel> result = getFlexibleSearchService().search(flexibleSearchQuery);
		return result.getResult();
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *            the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}

}
