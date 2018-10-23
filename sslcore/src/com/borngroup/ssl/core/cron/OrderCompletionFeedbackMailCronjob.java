package com.borngroup.ssl.core.cron;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.util.Config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.borngroup.ssl.core.events.OrderCompletionFeedMailEvent;
import com.borngroup.ssl.core.services.SSLOrderCompletionFeedbackJobService;

/**
 * The Class OrderCompletionFeedbackMailCronjob. Will be used to send feedback
 * mail to users when their order gets completed
 *
 * created by : ashish.sabal@nagarro.com
 *
 * @author SSL
 */
public class OrderCompletionFeedbackMailCronjob extends AbstractJobPerformable<CronJobModel> {

	/** Log4j logger. */
	private static final Logger LOG = Logger.getLogger(OrderCompletionFeedbackMailCronjob.class);

	/** The Constant DAY_LIMIT_PROPERTY_NAME. */
	private static final String DAY_LIMIT_PROPERTY_NAME = "order.complete.cronjob.day.limit";

	/** The Constant DATE_FORMAT. */
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	/** The Constant INPUT_STRING_PART_1. */
	private static final String INPUT_STRING_PART_1 = "Order:";

	/** The Constant INPUT_STRING_PART_2. */
	private static final String INPUT_STRING_PART_2 = "#TimeStamp:";

	/** The Constant INPUT_STRING_PART_3. */
	private static final String INPUT_STRING_PART_3 = "-";

	/** The Constant DAYS_LIMIT. */
	private static final int DAYS_LIMIT = -1;

	/** The ssl order completion feedback job service. */
	@Resource(name = "sslOrderCompletionFeedbackJobService")
	private SSLOrderCompletionFeedbackJobService sslOrderCompletionFeedbackJobService;

	/** Base Site Service. */
	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

	/** Event Service. */
	@Resource(name = "eventService")
	private EventService eventService;

	@Override
	public PerformResult perform(final CronJobModel paramT) {
		final Calendar cal = Calendar.getInstance();
		final String daylimit = Config.getParameter(DAY_LIMIT_PROPERTY_NAME);
		if (!StringUtils.isEmpty(daylimit)) {
			cal.add(Calendar.DATE, Integer.parseInt(daylimit) * (-1));
		} else {
			cal.add(Calendar.DATE, DAYS_LIMIT);
		}

		final Date startDateForGetCompletedOrder = cal.getTime();
		final List<OrderModel> latestCompletedOrders = this.getSslOrderCompletionFeedbackJobService()
				.getCompletedOrderLatest(startDateForGetCompletedOrder, OrderStatus.COMPLETED);

		if (CollectionUtils.isNotEmpty(latestCompletedOrders)) {
			for (final OrderModel currentOrder : latestCompletedOrders) {
				final String timeStamp = new SimpleDateFormat(DATE_FORMAT).format(new Date());
				final String inputStrForCipher = INPUT_STRING_PART_1 + currentOrder.getCode() + INPUT_STRING_PART_2
						+ timeStamp + INPUT_STRING_PART_3 + System.currentTimeMillis();
				final String encryptedUniqueStr = this.getSslOrderCompletionFeedbackJobService()
						.createUniqueHashUrlForOrder(inputStrForCipher);

				if (null != currentOrder.getUser()) {
					final String userMailId = currentOrder.getUser().getUid();
					if (!StringUtils.isEmpty(userMailId) && !StringUtils.isEmpty(encryptedUniqueStr)) {
						final OrderCompletionFeedMailEvent orderCompletionMailEvent = this.initializeEvent(currentOrder,
								encryptedUniqueStr, userMailId);
						if (null != orderCompletionMailEvent) {
							// This LOG need to be removed
							LOG.info("Clicked url for order " + currentOrder.getCode() + " : "
									+ Config.getParameter("website.ssl.https") + "/feedback?param="
									+ orderCompletionMailEvent.getEncodedUniqueUrl() + 1);

							this.getEventService().publishEvent(orderCompletionMailEvent);
						} else {
							LOG.error("No base site available : Cannot publish order completion mail event.");
						}
					} else {
						LOG.error("URL encryption error or user ID not available.");
					}
				} else {
					LOG.error("No user exists for order : " + currentOrder.getCode());
				}

			}
		} else {
			LOG.info("No completed orders exist in system from given date.");
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * Initialize event.
	 *
	 * @param ordr
	 *            the ordr
	 * @param encryptedUniqueStr
	 *            the encrypted unique str
	 * @param userMailId
	 *            the user mail id
	 * @return the order completion feed mail event
	 */
	private OrderCompletionFeedMailEvent initializeEvent(final OrderModel ordr, final String encryptedUniqueStr,
			final String userMailId) {
		final OrderCompletionFeedMailEvent orderCompletionFeedMailEvent = new OrderCompletionFeedMailEvent();
		orderCompletionFeedMailEvent.setOrderCurrent(ordr);
		orderCompletionFeedMailEvent.setEncodedUniqueUrl(encryptedUniqueStr);
		orderCompletionFeedMailEvent.setUserMailId(userMailId);

		if (null != this.getBaseSiteService()) {
			final Collection<BaseSiteModel> sites = this.getBaseSiteService().getAllBaseSites();
			if (CollectionUtils.isNotEmpty(sites)) {
				for (final BaseSiteModel bs : sites) {
					LOG.info("Setting site : " + bs.getName() + " to Order Completion Feedback Event.");
					orderCompletionFeedMailEvent.setSite(bs);
				}
				return orderCompletionFeedMailEvent;
			} else {
				LOG.error("No site availbale to set in Order Completion Feedback Event.");
			}
		} else {
			LOG.error("Base site service not available.");
		}
		return null;
	}

	/**
	 * Gets the ssl order completion feedback job service.
	 *
	 * @return the sslOrderCompletionFeedbackJobService
	 */
	public SSLOrderCompletionFeedbackJobService getSslOrderCompletionFeedbackJobService() {
		return sslOrderCompletionFeedbackJobService;
	}

	/**
	 * Sets the ssl order completion feedback job service.
	 *
	 * @param sslordercompletionfeedbackjobservice
	 *            the sslOrderCompletionFeedbackJobService to set
	 */
	public void setSslOrderCompletionFeedbackJobService(
			final SSLOrderCompletionFeedbackJobService sslordercompletionfeedbackjobservice) {
		this.sslOrderCompletionFeedbackJobService = sslordercompletionfeedbackjobservice;
	}

	/**
	 * Getter for BaseSiteService.
	 *
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService() {
		return baseSiteService;
	}

	/**
	 * Getter for EventService.
	 *
	 * @return the eventService
	 */
	public EventService getEventService() {
		return eventService;
	}
}
