package com.borngroup.ssl.core.cron;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.Config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.borngroup.ssl.core.dao.SSLLatestCompletedOrderLookupDao;
import com.borngroup.ssl.core.model.CustomerFeedbackModel;

/**
 * The Class OrderCompletionFeedbackDataRemovalCronjob. Will be used to remove
 * feedback data older then 2 year.
 *
 * created by : ashish.sabal@nagarro.com
 *
 * @author SSL
 */
public class OrderCompletionFeedbackDataRemovalCronjob extends AbstractJobPerformable<CronJobModel> {

	/** Latest Order Lookup Dao. */
	@Resource(name = "sslLatestCompletedOrderLookupDao")
	private SSLLatestCompletedOrderLookupDao sslLatestCompletedOrderLookupDao;

	/** Log4j logger. */
	private static final Logger LOG = Logger.getLogger(OrderCompletionFeedbackDataRemovalCronjob.class);

	/** The Constant DAY_LIMIT_PROPERTY_NAME. */
	private static final String DAY_LIMIT_PROPERTY_NAME = "order.complete.removal.cronjob.day.limit";

	/** The Constant DAYS_LIMIT. */
	private static final int DAYS_LIMIT = -730;

	@Override
	public PerformResult perform(final CronJobModel paramT) {
		final Calendar cal = Calendar.getInstance();
		final String daylimit = Config.getParameter(DAY_LIMIT_PROPERTY_NAME);
		if (!StringUtils.isEmpty(daylimit)) {
			cal.add(Calendar.DATE, Integer.parseInt(daylimit) * (-1));
		} else {
			cal.add(Calendar.DATE, DAYS_LIMIT);
		}

		final Date startDateForFeedDataRemoval = cal.getTime();
		final List<CustomerFeedbackModel> feedbackDataForRemoval = this.getSslLatestCompletedOrderLookupDao()
				.getOldOrderFeedbackData(startDateForFeedDataRemoval);

		if (CollectionUtils.isNotEmpty(feedbackDataForRemoval)) {
			for (final CustomerFeedbackModel feedData : feedbackDataForRemoval) {
				LOG.debug("Feed for order: " + feedData.getOrderNo() + " removed.");
				modelService.remove(feedData);
			}
		} else {
			LOG.info("No order completion feedback data exist older than 2 years.");
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * @return the sslLatestCompletedOrderLookupDao
	 */
	public SSLLatestCompletedOrderLookupDao getSslLatestCompletedOrderLookupDao() {
		return sslLatestCompletedOrderLookupDao;
	}

	/**
	 * @param ssllatestorderlookupdao
	 *            the sslLatestCompletedOrderLookupDao to set
	 */
	public void setSslLatestCompletedOrderLookupDao(final SSLLatestCompletedOrderLookupDao ssllatestorderlookupdao) {
		this.sslLatestCompletedOrderLookupDao = ssllatestorderlookupdao;
	}

}
