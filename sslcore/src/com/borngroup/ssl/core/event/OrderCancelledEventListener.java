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

import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.events.OrderCancelledEvent;
import com.borngroup.ssl.core.sms.service.SslCustomerTouchPointService;

public class OrderCancelledEventListener extends AbstractSiteEventListener<OrderCancelledEvent> {

    private ModelService modelService;
    private BusinessProcessService businessProcessService;
    /**
     * SslCustomerTouchPointService instance.
     */
    private SslCustomerTouchPointService sslCustomerTouchPointService;

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    protected void onSiteEvent(final OrderCancelledEvent event) {
        final OrderModel orderModel = event.getProcess().getOrder();
        final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
                "sendOrderCancelledEmailProcess-" + orderModel.getCode() + "-" + System.currentTimeMillis(),
                "sendOrderCancelledEmailProcess");
        orderProcessModel.setOrder(orderModel);
        getModelService().save(orderProcessModel);
        getBusinessProcessService().startProcess(orderProcessModel);
        this.sslCustomerTouchPointService.orderCancellationSMS(orderProcessModel, SslCoreConstants.ORDER_CANCELLATION_MSG);

        if (SalesApplication.CALLCENTER.equals(orderModel.getSalesApplication())) {
            ReturnRequestModel returnRequest = null;
            try {
                final ReturnRequestModel exampleReturnRequest = new ReturnRequestModel();
                exampleReturnRequest.setExchangeOrder(orderModel);
                returnRequest = flexibleSearchService.getModelByExample(exampleReturnRequest);
            } catch (final ModelNotFoundException e) {
                // Not an exchange order
            }
            if (null != returnRequest) {
                returnRequest.setStatus(ReturnStatus.COMPLETE);
                returnRequest.setRmaCompletionDate(new Date());
                getModelService().save(returnRequest);
            }
        }
    }

    @Override
    protected boolean shouldHandleEvent(final OrderCancelledEvent event) {
        final OrderModel order = event.getProcess().getOrder();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order", order);
        final BaseSiteModel site = order.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
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
    public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
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
