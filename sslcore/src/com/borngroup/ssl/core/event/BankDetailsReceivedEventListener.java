/**
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

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.events.BankDetailsReceivedEvent;

public class BankDetailsReceivedEventListener extends AbstractSiteEventListener<BankDetailsReceivedEvent> {

    private static final Logger LOG = Logger.getLogger(BankDetailsReceivedEventListener.class);

    private ModelService modelService;
    private BusinessProcessService businessProcessService;

    @Override
    protected void onSiteEvent(final BankDetailsReceivedEvent event) {
        LOG.debug(String.format("Attempting to start SendBankDetailsReceivedEmailProcess for order %s",
                event.getProcess().getOrder().getCode()));
        final OrderModel orderModel = event.getProcess().getOrder();
        final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
                "sendBankDetailsReceivedEmailProcess-" + orderModel.getCode() + "-" + System.currentTimeMillis(),
                "sendBankDetailsReceivedEmailProcess");
        orderProcessModel.setOrder(orderModel);
        orderProcessModel.setReturnReqNumber(new HashSet<String>(event.getProcess().getReturnReqNumber()));
        getModelService().save(orderProcessModel);
        getBusinessProcessService().startProcess(orderProcessModel);
    }

    @Override
    protected boolean shouldHandleEvent(final BankDetailsReceivedEvent event) {
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

}
