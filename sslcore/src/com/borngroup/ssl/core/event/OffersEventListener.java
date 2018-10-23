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
package com.borngroup.ssl.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.site.BaseSiteService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.events.OffersEvent;
import com.borngroup.ssl.core.model.CustomerBirthdayProcessModel;

/**
 * Listener for customer registration events.
 */
public class OffersEventListener extends AbstractSiteEventListener<OffersEvent> {

	private ModelService modelService;
	private BusinessProcessService businessProcessService;
	private static final Logger LOG = Logger.getLogger(OffersEventListener.class);

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService() {
		return baseSiteService;
	}

	/**
	 * @param baseSiteService
	 *            the baseSiteService to set
	 */
	public void setBaseSiteService(final BaseSiteService baseSiteService) {
		this.baseSiteService = baseSiteService;
	}

	protected BusinessProcessService getBusinessProcessService() {
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return the modelService
	 */
	protected ModelService getModelService() {
		return modelService;
	}

	/**
	 * @param modelService
	 *            the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService) {
		this.modelService = modelService;
	}

	@Override
	protected void onSiteEvent(final OffersEvent offersEvent) {
		final CustomerBirthdayProcessModel customerBirthdayProcessModel = (CustomerBirthdayProcessModel) getBusinessProcessService()
				.createProcess("offersEmailProcess-" + offersEvent.getCustomerModels().get(0).getUid() + "-"
						+ System.currentTimeMillis(), "offersEmailProcess");

		getModelService().save(customerBirthdayProcessModel);
		getBusinessProcessService().startProcess(customerBirthdayProcessModel);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commerceservices.event.AbstractSiteEventListener#
	 * shouldHandleEvent(de.hybris.platform.servicelayer.event.events.
	 * AbstractEvent)
	 */
	@Override
	protected boolean shouldHandleEvent(final OffersEvent event) {
		final BaseSiteModel site = getBaseSiteService().getBaseSiteForUID("ssl");

		LOG.info(" $$$$$$$$$$$222" + getBaseSiteService().getBaseSiteForUID("ssl").getName() + "###############22");
		ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
		return SiteChannel.B2C.equals(site.getChannel());
	}

	// @Override
	// protected boolean shouldHandleEvent(final CustomerBirthdayEvent event) {
	//
	// //
	// }
}
