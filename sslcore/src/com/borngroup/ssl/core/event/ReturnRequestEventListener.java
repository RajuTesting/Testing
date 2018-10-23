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
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.events.ReturnRequestEvent;

public class ReturnRequestEventListener extends
		AbstractSiteEventListener<ReturnRequestEvent> {

	private static final Logger LOG = Logger
			.getLogger(ReturnRequestEventListener.class);

	private ModelService modelService;
	private BusinessProcessService businessProcessService;

	@Override
	protected void onSiteEvent(final ReturnRequestEvent event) {

		final OrderModel orderModel = event.getProcess().getOrder();
		LOG.info("orderModel");
		final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService()
				.createProcess(
						"sendReturnRequestEmailProcess-" + orderModel.getCode()
								+ "-" + System.currentTimeMillis(),
						"sendReturnRequestEmailProcess");
		orderProcessModel.setOrder(orderModel);
		getModelService().save(orderProcessModel);
		getBusinessProcessService().startProcess(orderProcessModel);

	}

	@Override
	protected boolean shouldHandleEvent(final ReturnRequestEvent event) {
		final OrderModel order = event.getProcess().getOrder();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order",
				order);
		final BaseSiteModel site = order.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage(
				"event.order.site", site);
		return SiteChannel.B2C.equals(site.getChannel());
	}

	public ModelService getModelService() {
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService) {
		this.modelService = modelService;
	}

	public BusinessProcessService getBusinessProcessService() {
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(
			final BusinessProcessService businessProcessService) {
		this.businessProcessService = businessProcessService;
	}

}
