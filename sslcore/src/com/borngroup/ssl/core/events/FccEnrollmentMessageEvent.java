/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;

/**
 * @author techouts
 *
 */
public class FccEnrollmentMessageEvent extends AbstractCommerceUserEvent<BaseSiteModel>  {

	  private CustomerModel customer;
	public FccEnrollmentMessageEvent() {
		super();
	}
	/**
	 * @return the customer
	 */
	@Override
	public CustomerModel getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	@Override
	public void setCustomer(final CustomerModel customer) {
		this.customer = customer;
	}




}
