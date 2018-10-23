/**
 *
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.commerceservices.model.process.SslWalletRefundEmailProcessModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.util.DiscountValue;

import java.util.List;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.events.SslWalletRefundEmailEvent;

/**
 * @author osheengulati
 *
 */
public class SslWalletRefundEmailEventListener extends AbstractSiteEventListener<SslWalletRefundEmailEvent> {

	/** Log4j logger. */
	private static final Logger LOG = Logger.getLogger(OrderCompletionFeedMailEventListener.class);

	/** JOB EMAIL PROCESS NAME. */
	private static final String JOB_EMAIL_PROCESS_NAME = "sslWalletRefundEmailProcess";

	private ModelService modelService;
	private BusinessProcessService businessProcessService;

	private EventService eventService;

	/**
	 * @return the eventService
	 */
	public EventService getEventService() {
		return eventService;
	}

	/**
	 * @param eventService
	 *            the eventService to set
	 */
	public void setEventService(final EventService eventService) {
		this.eventService = eventService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService() {
		return modelService;
	}

	/**
	 * @param modelService
	 *            the modelService to set
	 */
	public void setModelService(final ModelService modelService) {
		this.modelService = modelService;
	}

	/**
	 * @return the businessProcessService
	 */
	public BusinessProcessService getBusinessProcessService() {
		return businessProcessService;
	}

	/**
	 * @param businessProcessService
	 *            the businessProcessService to set
	 */
	public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
		this.businessProcessService = businessProcessService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commerceservices.event.AbstractSiteEventListener#
	 * onSiteEvent(de.hybris.platform.servicelayer.event.events.AbstractEvent)
	 */
	@Override
	protected void onSiteEvent(final SslWalletRefundEmailEvent event) {
		// YTODO Auto-generated method stub
		if (null != event) {
			LOG.info("################### OrderCompletionFeedMailEventListener onSiteEvent  start ##########");
			final SslWalletRefundEmailProcessModel sslWalletRefundEmailProcessModel = this.getBusinessProcessService()
					.createProcess(JOB_EMAIL_PROCESS_NAME + System.currentTimeMillis(), JOB_EMAIL_PROCESS_NAME);

			final OrderModel order = event.getOrder();
			// if (order.getPaymentStatus() != null &&
			// order.getPaymentStatus().equals(PaymentStatus.PAID)) {
			// final List<ReturnRequestModel> returnRequests =
			// order.getReturnRequests();
			// double totalRefundAmount = 0;
			// for (final ReturnRequestModel returnRequest : returnRequests) {
			// final List<ReturnEntryModel> returnEntries =
			// returnRequest.getReturnEntries();
			// for (final ReturnEntryModel returnEntry : returnEntries) {
			// final OrderEntryModel orderEntry = (OrderEntryModel)
			// returnEntry.getOrderEntry();
			// totalRefundAmount += calculateReturnAmountForEntry(orderEntry,
			// returnEntry.getOrderEntry().getQuantity().longValue());
			// }
			// }
			order.setOrderCompleteMailsent(Boolean.TRUE);
			this.getModelService().save(order);

			this.getModelService().save(sslWalletRefundEmailProcessModel);

			this.getBusinessProcessService().startProcess(sslWalletRefundEmailProcessModel);

			LOG.info("################### OrderCompletionFeedMailEventListener onSiteEvent end  ##########");
		} else {
			LOG.error(
					"################### OrderCompletionFeedMailEventListener cannot start onSiteEvent : NULL #########");
		}
	}

	private double calculateReturnAmountForEntry(final OrderEntryModel orderEntry, final long returnQty) {
		final Double orderEntryTotalPrice = orderEntry.getTotalPrice();
		double totalDiscount = 0;
		final List<DiscountValue> totalDiscounts = orderEntry.getDiscountValues();
		for (final DiscountValue discount : totalDiscounts) {
			totalDiscount = totalDiscount + discount.getValue();
		}
		final double refundAmount = orderEntryTotalPrice.doubleValue() - (totalDiscount * returnQty);
		return refundAmount;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commerceservices.event.AbstractSiteEventListener#
	 * shouldHandleEvent(de.hybris.platform.servicelayer.event.events.
	 * AbstractEvent)
	 */
	@Override
	protected boolean shouldHandleEvent(final SslWalletRefundEmailEvent event) {
		// YTODO Auto-generated method stub
		LOG.info("################### OrderCompletionFeedMailEventListener shouldHandleEvent  ##########");
		final OrderModel order = event.getOrder();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order", order);
		final BaseSiteModel site = order.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
		return SiteChannel.B2C.equals(site.getChannel());

	}

}
