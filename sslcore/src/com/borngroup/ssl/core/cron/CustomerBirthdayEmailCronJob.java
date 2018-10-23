/**
 *
 */
package com.borngroup.ssl.core.cron;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.CustomerBirthdayDao;
import com.borngroup.ssl.core.events.CustomerBirthdayEvent;

/**
 * @author satyanarayana.naidu
 *
 */
public class CustomerBirthdayEmailCronJob extends AbstractJobPerformable<CronJobModel> {

	@Resource(name = "customerBirthdayDao")
	private CustomerBirthdayDao customerBirthdayDao;

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

	private EventService eventService;

	private static final Logger LOG = Logger.getLogger(CustomerBirthdayEmailCronJob.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de
	 * .hybris.platform.cronjob.model.CronJobModel)
	 */
	@Override
	public PerformResult perform(final CronJobModel paramT) {
		final java.util.List<CustomerModel> customerModels = getCustomerBirthdayDao().getDateOfBirth();
		final java.util.List<String> emailAddresses = new ArrayList<>();
		if (customerModels != null && !customerModels.isEmpty()) {
			for (final CustomerModel customer : customerModels) {
				emailAddresses.add(customer.getUid());
			}
			if (!emailAddresses.isEmpty()) {

				LOG.info(" ##########  CustomerBirthdayEmailCronJob started ################ ");

				final CustomerBirthdayEvent event = new CustomerBirthdayEvent();
				event.setCustomerModels(customerModels);
				event.setSite(getBaseSiteService().getCurrentBaseSite());

				getEventService().publishEvent(event);

				// getEventService().publishEvent(initializeEvent(new
				// CustomerBirthdayEvent(), customerModels));

			}
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);

	}

	protected CustomerBirthdayEvent initializeEvent(final CustomerBirthdayEvent event,
			final List<CustomerModel> customerModels) {
		event.setCustomerModels(customerModels);
		return event;
	}

	/**
	 * @return the customerBirthdayDao
	 */
	public CustomerBirthdayDao getCustomerBirthdayDao() {
		return customerBirthdayDao;
	}

	/**
	 * @param customerBirthdayDao
	 *            the customerBirthdayDao to set
	 */
	public void setCustomerBirthdayDao(final CustomerBirthdayDao customerBirthdayDao) {
		this.customerBirthdayDao = customerBirthdayDao;
	}

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
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService() {
		return baseSiteService;
	}

	/**
	 * @param baseSiteService
	 *            the baseSiteService to set
	 */
	public void setBaseSiteService(final BaseSiteService baseSiteService) {
		this.baseSiteService = baseSiteService;
	}

}
