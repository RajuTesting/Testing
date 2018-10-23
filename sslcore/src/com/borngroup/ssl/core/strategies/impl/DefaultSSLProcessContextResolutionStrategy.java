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
package com.borngroup.ssl.core.strategies.impl;

import de.hybris.platform.acceleratorservices.process.strategies.impl.DefaultProcessContextResolutionStrategy;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontProcessModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.orderprocessing.model.SterlingCustomerNotificationProcessModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import com.borngroup.ssl.core.model.CustomerBirthdayProcessModel;
import com.borngroup.ssl.core.model.EgvEmailProcessModel;
import com.borngroup.ssl.core.model.FeedBackEmailProcessModel;
import com.borngroup.ssl.core.model.NewsLettersProcessModel;

/**
 * Default strategy to impersonate site and initialize session context from the process model.
 */
public class DefaultSSLProcessContextResolutionStrategy extends DefaultProcessContextResolutionStrategy {

    @Override
    public BaseSiteModel getCmsSite(final BusinessProcessModel businessProcess) {
        ServicesUtil.validateParameterNotNull(businessProcess, "businessProcess must not be null");

        if (businessProcess instanceof NewsLettersProcessModel) {
            return ((NewsLettersProcessModel) businessProcess).getSite();
        }

        if (businessProcess instanceof FeedBackEmailProcessModel) {
            return ((FeedBackEmailProcessModel) businessProcess).getSite();
        }

        if (businessProcess instanceof CustomerBirthdayProcessModel) {
            return ((CustomerBirthdayProcessModel) businessProcess).getSite();
        }

        if (businessProcess instanceof StoreFrontCustomerProcessModel) {
            return ((StoreFrontCustomerProcessModel) businessProcess).getSite();
        }

        if (businessProcess instanceof StoreFrontProcessModel) {
            return ((StoreFrontProcessModel) businessProcess).getSite();
        }

        if (businessProcess instanceof OrderProcessModel) {
            return ((OrderProcessModel) businessProcess).getOrder().getSite();
        }

        if (businessProcess instanceof ConsignmentProcessModel) {
            return ((ConsignmentProcessModel) businessProcess).getConsignment().getOrder().getSite();
        }
        if (businessProcess instanceof EgvEmailProcessModel) {
            return ((EgvEmailProcessModel) businessProcess).getOrder().getSite();
        }
        if (businessProcess instanceof SterlingCustomerNotificationProcessModel) {
            return ((SterlingCustomerNotificationProcessModel) businessProcess).getOrder().getSite();
        }

        return null;
    }

}
