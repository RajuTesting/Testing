/**
 * 
 */
package com.borngroup.ssl.core.jobs;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.Config;
import de.hybris.platform.workflow.enums.WorkflowActionStatus;
import de.hybris.platform.workflow.model.WorkflowActionModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.events.WorkflowActionDelayEvent;
import com.borngroup.ssl.core.services.SSLUserService;


/**
 * @author Deepak
 * 
 */
public class ProductEnrichmentWorkflowMonitorJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(ProductEnrichmentWorkflowMonitorJob.class);

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Autowired
	private SSLUserService userService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private EventService eventService;

	public BusinessProcessService getBusinessProcessService()
	{
		return (BusinessProcessService) Registry.getApplicationContext().getBean("businessProcessService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel
	 * )
	 */
	@Override
	public PerformResult perform(final CronJobModel arg0)
	{
		LOG.debug("Product Enrichment Workflow Monitor Job Started.");
		List<WorkflowActionModel> inprogressWorkflowActionList = new LinkedList<WorkflowActionModel>();
		final Collection<WorkflowActionStatus> actionStatuses = new ArrayList<WorkflowActionStatus>();


		try
		{
			actionStatuses.add(WorkflowActionStatus.IN_PROGRESS);

			inprogressWorkflowActionList = findWorkflowActionsByStatus(actionStatuses);

			final Collection<PrincipalModel> principalModels = userService.getUsersByUserGroup("productmanagergroup");

			for (final WorkflowActionModel inprogressAction : inprogressWorkflowActionList)
			{
				final long diff = inprogressAction.getModifiedtime().getTime() - inprogressAction.getCreationtime().getTime();

				if (diff > getDelayThresholdTime())
				{
					LOG.info("Work flow action : " + inprogressAction.getName() + " assigned to : "
							+ inprogressAction.getPrincipalAssigned().getDisplayName() + " is getting long time to process.");

					for (final PrincipalModel principalModel : principalModels)
					{
						if (principalModel instanceof UserModel)
						{
							final UserModel userModel = (UserModel) principalModel;
							final WorkflowActionDelayEvent workflowActionDelayEvent = new WorkflowActionDelayEvent();

							workflowActionDelayEvent.setUserModel(userModel);
							workflowActionDelayEvent.setWorkflowActionModel(inprogressAction);

							eventService.publishEvent(workflowActionDelayEvent);
						}
					}
				}
			}
		}
		catch (final Exception e)
		{
			LOG.error("Unexpected error occured while performing ProductEnrichmentWorkflowMonitorJob", e);
			LOG.debug(e.getStackTrace());
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	public List<WorkflowActionModel> findWorkflowActionsByStatus(final Collection<WorkflowActionStatus> actionStatuses)
	{
		final Map params = new HashMap();
		params.put("status", actionStatuses);

		final SearchResult res = flexibleSearchService.search("SELECT {action:pk} FROM {" + WorkflowActionModel._TYPECODE
				+ " as action} " + "WHERE {action:" + WorkflowActionModel.STATUS + "} IN (?status) ORDER BY {action:"
				+ WorkflowActionModel.MODIFIEDTIME + "} ASC", params);
		return res.getResult();
	}

	/**
	 * @return the delayThresholdTime
	 */
	public long getDelayThresholdTime()
	{
		return Long.parseLong(Config.getParameter("productEnrichmentWorkflow.workflowAction.delayThresholdTime"));
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	@Override
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}