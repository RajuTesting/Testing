package com.borngroup.ssl.core.event;

import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.events.ConsignmentInvoiceEvent;


/**
 * 
 * @author dien.nguyen
 * 
 */

public class ConsignmentInvoiceEventListener extends AbstractSiteEventListener<ConsignmentInvoiceEvent>
{

	private ModelService modelService;
	private BusinessProcessService businessProcessService;

	@Override
	protected void onSiteEvent(final ConsignmentInvoiceEvent event)
	{
		final ConsignmentModel consignmentModel = event.getProcess().getConsignment();
		final ConsignmentProcessModel consignmentProcessModel = (ConsignmentProcessModel) getBusinessProcessService()
				.createProcess("sendConsignmentInvoiceEmailProcess-" + consignmentModel.getCode() + "-" + System.currentTimeMillis(),
						"sendConsignmentInvoiceEmailProcess");
		consignmentProcessModel.setConsignment(consignmentModel);
		getModelService().save(consignmentProcessModel);
		consignmentProcessModel.setInvoiceAttachmentCode(event.getProcess().getInvoiceAttachmentCode());
		getBusinessProcessService().startProcess(consignmentProcessModel);
	}

	@Override
	protected boolean shouldHandleEvent(final ConsignmentInvoiceEvent event)
	{
		return true;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}
}
