package com.borngroup.ssl.core.events;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public class OffersEvent extends AbstractCommerceUserEvent<BaseSiteModel> {

	private List<CustomerModel> customerModels;

	/**
	 * @return the customerModels
	 */
	public List<CustomerModel> getCustomerModels() {
		return customerModels;
	}

	/**
	 * @param customerModels
	 *            the customerModels to set
	 */
	public void setCustomerModels(final List<CustomerModel> customerModels) {
		this.customerModels = customerModels;
	}

}