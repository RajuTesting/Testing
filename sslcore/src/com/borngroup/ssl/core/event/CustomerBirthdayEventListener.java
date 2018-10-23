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
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.events.CustomerBirthdayEvent;
import com.borngroup.ssl.core.model.CustomerBirthdayProcessModel;

/**
 * Listener for customer registration events.
 */
public class CustomerBirthdayEventListener extends AbstractSiteEventListener<CustomerBirthdayEvent> {

	private ModelService modelService;
	private BusinessProcessService businessProcessService;

	@Resource(name = "baseStoreService")
	private BaseStoreService baseStoreService;

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

	private static final Logger LOG = Logger.getLogger(CustomerBirthdayEventListener.class);

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

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commerceservices.event.AbstractSiteEventListener#
	 * onSiteEvent(de.hybris.platform.servicelayer.event.events.AbstractEvent)
	 */
	@Override
	protected void onSiteEvent(final CustomerBirthdayEvent customerBirthdayEvent) {

		LOG.info(" ##########  CustomerBirthdayEmailCronJob listener ################ ");

		final CustomerBirthdayProcessModel customerBirthdayProcessModel = (CustomerBirthdayProcessModel) getBusinessProcessService()
				.createProcess("customerBirthdayEmailProcess-"
						+ customerBirthdayEvent.getCustomerModels().get(0).getUid() + "-" + System.currentTimeMillis(),
						"customerBirthdayEmailProcess");
		customerBirthdayProcessModel.setCustomers(customerBirthdayEvent.getCustomerModels());
		customerBirthdayProcessModel.setSite(getBaseSiteService().getBaseSiteForUID("ssl"));
		final BaseStoreModel currentBaseStore = baseStoreService.getCurrentBaseStore();
		if (currentBaseStore != null) {
			customerBirthdayProcessModel.setStore(currentBaseStore);
		}
		getModelService().save(customerBirthdayProcessModel);
		getBusinessProcessService().startProcess(customerBirthdayProcessModel);

	}

	@Override
	protected boolean shouldHandleEvent(final CustomerBirthdayEvent event) {
		final BaseSiteModel site = getBaseSiteService().getBaseSiteForUID("ssl");

		LOG.info(" $$$$$$$$$$$222" + getBaseSiteService().getBaseSiteForUID("ssl").getName() + "###############22");
		ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
		return SiteChannel.B2C.equals(site.getChannel());
	}

}
