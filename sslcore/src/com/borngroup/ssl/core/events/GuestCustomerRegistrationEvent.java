/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;


/**
 * Forgotten password event, implementation of {@link AbstractCommerceUserEvent}
 */
public class GuestCustomerRegistrationEvent extends AbstractCommerceUserEvent<BaseSiteModel>
{

	private String otp;

	/**
	 * Default constructor
	 */
	public GuestCustomerRegistrationEvent()
	{
		super();
	}

	public GuestCustomerRegistrationEvent(final String otp)
	{
		super();
		this.otp = otp;
	}

	/**
	 * @return the otp
	 */
	public String getOtp()
	{
		return otp;
	}

	/**
	 * @param otp
	 *           the otp to set
	 */
	public void setOtp(final String otp)
	{
		this.otp = otp;
	}



}
