/**
 * 
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;


/**
 * @author Deepak
 * 
 */
public class WorkflowActionDelayEvent extends AbstractCommerceUserEvent<BaseSiteModel>
{
	private WorkflowActionModel workflowActionModel;
	private UserModel userModel;

	/**
	 * @return the workflowActionModel
	 */
	public WorkflowActionModel getWorkflowActionModel()
	{
		return workflowActionModel;
	}

	/**
	 * @param workflowActionModel
	 *           the workflowActionModel to set
	 */
	public void setWorkflowActionModel(final WorkflowActionModel workflowActionModel)
	{
		this.workflowActionModel = workflowActionModel;
	}

	/**
	 * @return the userModel
	 */
	public UserModel getUserModel()
	{
		return userModel;
	}

	/**
	 * @param userModel
	 *           the userModel to set
	 */
	public void setUserModel(final UserModel userModel)
	{
		this.userModel = userModel;
	}
}
