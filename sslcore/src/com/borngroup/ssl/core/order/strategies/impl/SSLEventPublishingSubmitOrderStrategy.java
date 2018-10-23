/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.borngroup.ssl.core.order.strategies.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.strategies.SubmitOrderStrategy;
import de.hybris.platform.servicelayer.event.EventService;

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.events.SubmitOrderEvent;


/**
 * This implementation sends {@link SubmitOrderEvent} event when order is submitted.
 */
public class SSLEventPublishingSubmitOrderStrategy implements SubmitOrderStrategy
{
    private EventService eventService;

    @Override
    public void submitOrder(final OrderModel order)
    {
        eventService.publishEvent(new SubmitOrderEvent(order));
    }

    @Required
    public void setEventService(final EventService eventService)
    {
        this.eventService = eventService;
    }


}
