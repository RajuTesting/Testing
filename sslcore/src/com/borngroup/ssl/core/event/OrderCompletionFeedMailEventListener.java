package com.borngroup.ssl.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.commerceservices.model.process.OrderCompletionFeedbackStoreFrontProcessModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.events.OrderCompletionFeedMailEvent;

/**
 * The listener interface for receiving orderCompletionFeedMailEvent events. The
 * class that is interested in processing a orderCompletionFeedMailEvent event
 * implements this interface, and the object created with that class is
 * registered with a component using the component's
 * <code>addOrderCompletionFeedMailEventListener</code> method. When the
 * orderCompletionFeedMailEvent event occurs, that object's appropriate method
 * is invoked.
 *
 * created by : ashish.sabal@nagarro.com
 *
 * @author SSL
 */
public class OrderCompletionFeedMailEventListener extends AbstractSiteEventListener<OrderCompletionFeedMailEvent> {

	/** Log4j logger. */
	private static final Logger LOG = Logger.getLogger(OrderCompletionFeedMailEventListener.class);

	/** JOB EMAIL PROCESS NAME. */
	private static final String JOB_EMAIL_PROCESS_NAME = "OrderCompletionFeedbackStoreFrontProcess";

	/** Model Service. */
	private ModelService modelService;

	/** The business process service. */
	private BusinessProcessService businessProcessService;

	@Override
	protected void onSiteEvent(final OrderCompletionFeedMailEvent orderCompletionFeedMailEvent) {
		if (null != orderCompletionFeedMailEvent) {
			LOG.info("################### OrderCompletionFeedMailEventListener onSiteEvent  start ##########");
			final OrderCompletionFeedbackStoreFrontProcessModel orderFeedbackProcessModel = this
					.getBusinessProcessService()
					.createProcess(JOB_EMAIL_PROCESS_NAME + System.currentTimeMillis(), JOB_EMAIL_PROCESS_NAME);
			orderFeedbackProcessModel.setSite(orderCompletionFeedMailEvent.getSite());
			orderFeedbackProcessModel.setCurrentOrder(orderCompletionFeedMailEvent.getOrderCurrent());
			orderFeedbackProcessModel.setEncodedUniqueUrl(orderCompletionFeedMailEvent.getEncodedUniqueUrl());
			orderFeedbackProcessModel.setUserMailId(orderCompletionFeedMailEvent.getUserMailId());

			this.getModelService().save(orderFeedbackProcessModel);
			this.getBusinessProcessService().startProcess(orderFeedbackProcessModel);

			final OrderModel currentOrder = orderCompletionFeedMailEvent.getOrderCurrent();
			currentOrder.setOrderCompleteMailsent(Boolean.TRUE);
			this.getModelService().save(currentOrder);
			LOG.info("################### OrderCompletionFeedMailEventListener onSiteEvent end  ##########");
		} else {
			LOG.error(
					"################### OrderCompletionFeedMailEventListener cannot start onSiteEvent : NULL #########");
		}
	}

	@Override
	protected boolean shouldHandleEvent(final OrderCompletionFeedMailEvent orderCompletionFeedMailEvent) {
		LOG.info("################### OrderCompletionFeedMailEventListener shouldHandleEvent  ##########");
		final BaseSiteModel site = orderCompletionFeedMailEvent.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
		return SiteChannel.B2C.equals(site.getChannel());
	}

	/**
	 * Getter for ModelService.
	 *
	 * @return the modelService
	 */
	public ModelService getModelService() {
		return modelService;
	}

	/**
	 * Setter for ModelService.
	 *
	 * @param modelService
	 *            the modelService to set
	 */
	public void setModelService(final ModelService modelService) {
		this.modelService = modelService;
	}

	/**
	 * Getter for BusinessProcessService.
	 *
	 * @return the businessProcessService
	 */
	public BusinessProcessService getBusinessProcessService() {
		return businessProcessService;
	}

	/**
	 * Setter for BusinessProcessService.
	 *
	 * @param businessProcessService
	 *            the businessProcessService to set
	 */
	public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
		this.businessProcessService = businessProcessService;
	}
}
