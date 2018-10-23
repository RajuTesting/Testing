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
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.events.OrderPlacedAysncEvent;
import com.borngroup.ssl.core.sms.service.SslCustomerTouchPointService;

import java.time.Duration;
import java.time.Instant;

/**
 * Listener for order confirmation events.
 */
public class OrderConfirmationEventListener extends AbstractSiteEventListener<OrderPlacedAysncEvent> {

    private static final Logger LOG = Logger.getLogger(OrderConfirmationEventListener.class);
    private ModelService modelService;
    private BusinessProcessService businessProcessService;
    /**
     * SslCustomerTouchPointService instance.
     */
    private SslCustomerTouchPointService sslCustomerTouchPointService;

    protected BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    @Required
    public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    protected ModelService getModelService() {
        return modelService;
    }

    @Required
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

	@Override
	protected void onSiteEvent(final OrderPlacedAysncEvent orderPlacedEvent) {
        final Instant start = Instant.now();
		final OrderModel orderModel = orderPlacedEvent.getProcess().getOrder();
        final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService()
                .createProcess("orderConfirmationEmailProcess-" + orderModel.getCode() + "-" + System.currentTimeMillis(),
                        "orderConfirmationEmailProcess");
        orderProcessModel.setOrder(orderModel);
        getModelService().save(orderProcessModel);
        getBusinessProcessService().startProcess(orderProcessModel);
        String message;
        if (orderModel.getPaymentMode().getCode().equalsIgnoreCase("cod")) {
            message = SslCoreConstants.COD_ORDER_CONFIRMATION_MESSAGE;
            if (CustomerType.GUEST.equals(((CustomerModel) (orderModel.getUser())).getType())) {
                message = SslCoreConstants.COD_ORDER_CONFIRMATION_MESSAGE_GUEST;
            }
            this.sslCustomerTouchPointService.orderConfirmationSMSForCod(orderModel, message);

        } else {
            message = SslCoreConstants.ORDER_CONFIRMATION_MSG_REGISTERED;
            if (null != ((CustomerModel) (orderModel.getUser())).getType()
                    && ((CustomerModel) (orderModel.getUser())).getType().equals(CustomerType.GUEST)) {
                message = SslCoreConstants.ORDER_CONFIRMATION_MSG_GUEST;
            }
            this.sslCustomerTouchPointService.orderConfirmationSMS(orderProcessModel, message);
        }
        final Instant end = Instant.now();
        LOG.info(String.format("Spent %s time to send order confirmation for %s", Duration.between(start, end), orderModel.getCode()));
    }

	@Override
	protected boolean shouldHandleEvent(final OrderPlacedAysncEvent event) {
		final OrderModel order = event.getProcess().getOrder();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order", order);
		final BaseSiteModel site = order.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
		return SiteChannel.B2C.equals(site.getChannel());
	}

    /**
     * Get instance of SslCustomerTouchPointService.
     *
     * @return sslCustomerTouchPointService - SslCustomerTouchPointService instance
     */
    public SslCustomerTouchPointService getSslCustomerTouchPointService() {
        return sslCustomerTouchPointService;
    }

    /**
     * Set instance of SslCustomerTouchPointService.
     *
     * @param sslCustomerTouchPointService1
     *        - SslCustomerTouchPointService instance.
     */
    public void setSslCustomerTouchPointService(final SslCustomerTouchPointService sslCustomerTouchPointService1) {
        this.sslCustomerTouchPointService = sslCustomerTouchPointService1;
    }

}
