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
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.events.FccOTPEvent;


/**
 * Listener for "forgotten password" functionality event.
 */
public class FccOTPEventListener extends AbstractSiteEventListener<FccOTPEvent>
{

    private static final Logger LOG = Logger.getLogger(FccOTPEventListener.class);

    private ModelService           modelService;
    private BusinessProcessService businessProcessService;

    protected BusinessProcessService getBusinessProcessService()
    {
        return businessProcessService;
    }

    @Required
    public void setBusinessProcessService(final BusinessProcessService businessProcessService)
    {
        this.businessProcessService = businessProcessService;
    }

    /**
     * @return the modelService
     */
    protected ModelService getModelService()
    {
        return modelService;
    }

    /**
     * @param modelService
     *            the modelService to set
     */
    @Required
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

    @Override
    protected void onSiteEvent(final FccOTPEvent event)
    {

        LOG.info("################### FccOTPEventListener onSiteEvent  start ##########");
        final StoreFrontCustomerProcessModel fccOTPProcessModel = (StoreFrontCustomerProcessModel) getBusinessProcessService()
                .createProcess("fccOTPEmailProcess-" + event.getCustomer().getUid() + "-" + System.currentTimeMillis(),
                        "fccOTPEmailProcess");
        fccOTPProcessModel.setSite(event.getSite());
        fccOTPProcessModel.setCustomer(event.getCustomer());
        fccOTPProcessModel.setLanguage(event.getLanguage());
        fccOTPProcessModel.setCurrency(event.getCurrency());
        fccOTPProcessModel.setStore(event.getBaseStore());
        getModelService().save(fccOTPProcessModel);
        getBusinessProcessService().startProcess(fccOTPProcessModel);
        LOG.info("################### FccOTPEventListener onSiteEvent end  ##########");
    }

    @Override
    protected boolean shouldHandleEvent(final FccOTPEvent event)
    {
        LOG.info("################### FccOTPEventListener shouldHandleEvent  ##########");
        final BaseSiteModel site = event.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
        return SiteChannel.B2C.equals(site.getChannel());
    }
}
