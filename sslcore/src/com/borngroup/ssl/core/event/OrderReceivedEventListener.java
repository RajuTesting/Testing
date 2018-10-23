package com.borngroup.ssl.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import com.borngroup.ssl.core.events.OrderReceivedEvent;


/**
 * @author t.balagopalan
 *
 * Listener for order received, to send email.
 */
public class OrderReceivedEventListener extends AbstractSiteEventListener<OrderReceivedEvent>
{

    /**
     * Private members
     */
    private ModelService           modelService;
    private BusinessProcessService businessProcessService;

    /* (non-Javadoc)
     * @see de.hybris.platform.commerceservices.event.AbstractSiteEventListener#onSiteEvent(de.hybris.platform.servicelayer.event.events.AbstractEvent)
     */
    @Override
    protected void onSiteEvent(final OrderReceivedEvent orderReceivedEvent)
    {
        final OrderModel orderModel = orderReceivedEvent.getProcess().getOrder();
        final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
                "orderReceivedEmailProcess-" + orderModel.getCode() + "-" + System.currentTimeMillis(),
                "orderReceivedEmailProcess");
        orderProcessModel.setOrder(orderModel);
        getModelService().save(orderProcessModel);
        getBusinessProcessService().startProcess(orderProcessModel);
    }


    @Override
    protected boolean shouldHandleEvent(final OrderReceivedEvent orderReceivedEvent)
    {
        final OrderModel order = orderReceivedEvent.getProcess().getOrder();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order", order);
        final BaseSiteModel site = order.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
        return SiteChannel.B2C.equals(site.getChannel());
    }

    /**
     * @return the modelService
     */
    public ModelService getModelService()
    {
        return modelService;
    }

    /**
     * @param modelService
     *            the modelService to set
     */
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

    /**
     * @return the businessProcessService
     */
    public BusinessProcessService getBusinessProcessService()
    {
        return businessProcessService;
    }

    /**
     * @param businessProcessService
     *            the businessProcessService to set
     */
    public void setBusinessProcessService(final BusinessProcessService businessProcessService)
    {
        this.businessProcessService = businessProcessService;
    }
}
