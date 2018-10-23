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

import com.borngroup.ssl.core.services.ODCReturnService;
import de.hybris.platform.basecommerce.enums.OrderEntryStatus;
import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.events.OrderCompletedEvent;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;

/**
 * Listener for order confirmation events. Code for sending an email is
 * commented out. Add this process and uncomment to use this.
 */
public class OrderCompletedEventListener extends AbstractSiteEventListener<OrderCompletedEvent> {
	private ModelService modelService;
	private BusinessProcessService businessProcessService;
	@Resource(name = "odcReturnService")
	private ODCReturnService odcReturnService;

	/**
	 * @return the businessProcessService
	 */
	protected BusinessProcessService getBusinessProcessService() {
		return businessProcessService;
	}

	/**
	 * @param businessProcessService
	 *            the businessProcessService to set
	 */
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
	protected void onSiteEvent(final OrderCompletedEvent orderCompletedEvent) {
		final OrderModel orderModel = orderCompletedEvent.getProcess().getOrder();
		if (isWholeOrderCancelled(orderModel)) {
			orderModel.setStatus(OrderStatus.CANCELLED);
		} else if ((orderModel.getStatus() != null) && !(orderModel.getStatus().equals(OrderStatus.CANCELLING)
				|| (orderModel.getStatus().equals(OrderStatus.PARTIALLY_CANCELLING)
						|| (orderModel.getStatus().equals(OrderStatus.PARTIALLY_CANCELLED))))) {
			orderModel.setStatus(OrderStatus.COMPLETED);
			//check if it is exchange order, and then set return request status as complete.
			final ReturnRequestModel returnRequest = odcReturnService.getReturnRequestByExchangeOrder(orderModel.getCode());
			if(returnRequest != null){
				//update ReturnRequest status and consignment.
				returnRequest.setStatus(ReturnStatus.COMPLETE);
				modelService.save(returnRequest);
			}
		}
		getModelService().save(orderModel);
	}

	@Override
	protected boolean shouldHandleEvent(final OrderCompletedEvent event) {
		final OrderModel order = event.getProcess().getOrder();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order", order);
		final BaseSiteModel site = order.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
		return SiteChannel.B2C.equals(site.getChannel());
	}

	protected boolean isWholeOrderCancelled(final OrderModel orderModel) {
		if (CollectionUtils.isNotEmpty(orderModel.getEntries())) {
			for (final AbstractOrderEntryModel orderEntry : orderModel.getEntries()) {
				if ((orderEntry.getQuantityStatus() != null)
						&& (!(orderEntry.getQuantityStatus().equals(OrderEntryStatus.CANCELLED)
								|| (orderEntry.getQuantityStatus().equals(OrderEntryStatus.PARTIALLY_CANCELLED))))) {
					return false;
				}
				if (!(orderEntry.getCancelledQty().equals(orderEntry.getQuantity()))) {
					return false;
				}
			}
		}
		return true;
	}
}
