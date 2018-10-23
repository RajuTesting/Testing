/**
 * 
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;
import de.hybris.platform.workflow.model.WorkflowActionModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.events.WorkflowActionDelayEvent;


/**
 * @author Deepak
 * 
 */
public class WorkflowActionDelayEventListener extends AbstractEventListener<WorkflowActionDelayEvent>
{
	private static final Logger LOG = Logger.getLogger(WorkflowActionDelayEventListener.class);

	@Autowired
	private UserService userService;

	private ModelService modelService;

	@Autowired
	EmailService emailService;

	public BusinessProcessService getBusinessProcessService()
	{
		return (BusinessProcessService) Registry.getApplicationContext().getBean("businessProcessService");
	}

	@Override
	protected void onEvent(final WorkflowActionDelayEvent event)
	{
		try
		{
			emailService.send(createEmailMessage(event.getWorkflowActionModel(), event.getUserModel()));

			/*
			 * final StoreFrontCustomerProcessModel storeFrontCustomerProcessModel = (StoreFrontCustomerProcessModel)
			 * getBusinessProcessService() .createProcess("WorkflowActionDelayEmailProcess" + System.currentTimeMillis(),
			 * "workflowActionDelayEmailProcess"); storeFrontCustomerProcessModel.setSite(event.getSite());
			 * storeFrontCustomerProcessModel.setCustomer(event.getCustomer());
			 * modelService.save(storeFrontCustomerProcessModel);
			 * getBusinessProcessService().startProcess(storeFrontCustomerProcessModel);
			 */

		}
		catch (final Exception e)
		{
			LOG.error("Error occured while processing WorkflowActionDelayEvent :", e);
			LOG.debug(e.getStackTrace());
		}
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	private EmailMessageModel createEmailMessage(final WorkflowActionModel workflowActionModel, final UserModel userModel)
	{
		final List<EmailAddressModel> toEmailAddresses = new ArrayList<EmailAddressModel>();

		final ProductModel attachedProduct = (ProductModel) workflowActionModel.getAttachmentItems().get(0);

		if (userModel.getAddresses() != null)
		{
			final Collection<AddressModel> addressModels = userModel.getAddresses();
			for (final AddressModel addressModel : addressModels)
			{
				final EmailAddressModel toAddress = emailService.getOrCreateEmailAddressForEmail(addressModel.getEmail(),
						addressModel.getEmail());
				toEmailAddresses.add(toAddress);
			}
			final EmailAddressModel fromAddress = emailService.getOrCreateEmailAddressForEmail(
					Config.getParameter("workflow.delay.event.fromAddress"),
					Config.getParameter("workflow.delay.event.fromAddressDisplayName"));

			final EmailMessageModel message = emailService.createEmailMessage(toEmailAddresses, null, null, fromAddress, "",
					"Workflow Action Delay Alert", "Workflow action " + workflowActionModel.getName() + " with id :"
							+ workflowActionModel.getCode() + " Product Code: " + attachedProduct.getCode() + " Product name: "
							+ attachedProduct.getName() + " is taking long time to finish. ", null);

			return message;
		}
		return null;
	}
}