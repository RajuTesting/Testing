/**
 * 
 */
package com.borngroup.ssl.core.workflow;

import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.workflow.enums.WorkflowActionStatus;
import de.hybris.platform.workflow.jobs.AutomatedWorkflowTemplateJob;
import de.hybris.platform.workflow.model.AbstractWorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Deepak
 * 
 *         This class will be used to do any validation required on the task performed Further this class will
 *         explicitly mark the next action to in-progress status
 */
public class DefaultConfirmationActionJob implements AutomatedWorkflowTemplateJob
{
	private static final Logger LOG = Logger.getLogger(DefaultConfirmationActionJob.class);

	@Autowired
	private ModelService modelService;

	@Override
	public WorkflowDecisionModel perform(final WorkflowActionModel action)
	{
		LOG.debug("Starting DefaultConfirmationActionJob.......");
		for (final WorkflowDecisionModel decision : action.getDecisions())
		{
			decision.setComments(null);
			markSuccessorToInprogress(decision, "");
			return decision;
		}
		return null;
	}

	public void markSuccessorToInprogress(final WorkflowDecisionModel decision, final String comments)
	{
		final WorkflowActionModel actionModel = decision.getAction();
		for (final AbstractWorkflowActionModel successorActionModel : actionModel.getWorkflow().getActions())
		{
			final WorkflowActionModel workflowActionModel = (WorkflowActionModel) successorActionModel;
			if (workflowActionModel.getIncomingDecisions().contains(decision))
			{
				workflowActionModel.setComment(comments);
				workflowActionModel.setStatus(WorkflowActionStatus.IN_PROGRESS);
				modelService.save(workflowActionModel);
			}
		}
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
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}