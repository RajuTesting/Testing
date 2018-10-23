/**
 *
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.events.FeedBackEvent;
import com.borngroup.ssl.core.model.FeedBackEmailProcessModel;

/**
 * @author satyanarayana.naidu
 *
 */
public class FeebBackEventListener extends AbstractSiteEventListener<FeedBackEvent> {

	@Resource(name = "businessProcessService")
	private BusinessProcessService businessProcessService;
	private ModelService modelService;
	private static final Logger LOG = Logger.getLogger(FeebBackEventListener.class);

	@Resource(name = "baseStoreService")
	private BaseStoreService baseStoreService;

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.event.impl.AbstractEventListener#onEvent(
	 * de.hybris.platform.servicelayer.event.events.AbstractEvent)
	 */
	/*
	 * protected StoreFrontCustomerProcessModel createProcess() { return
	 * (StoreFrontCustomerProcessModel) getBusinessProcessService()
	 * .createProcess("feedBackEmailProcess" + System.currentTimeMillis(),
	 * "feedBackEmailProcess"); }
	 */

	@Override
	protected void onSiteEvent(final FeedBackEvent event) {

		LOG.info("FeedBackEvent listener ###############");
		final FeedBackEmailProcessModel feedBackEmailProcess = (FeedBackEmailProcessModel) getBusinessProcessService()
				.createProcess("feedBackEmailProcess" + System.currentTimeMillis(), "feedBackEmailProcess");
		feedBackEmailProcess.setSite(getBaseSiteService().getBaseSiteForUID("ssl"));
		LOG.info(" $$$$$$$$$$$" + getBaseSiteService().getBaseSiteForUID("ssl").getName() + "###############");
		feedBackEmailProcess.setCustomer(event.getCustomer());
		feedBackEmailProcess.setVisitOptions(event.getVisitOptions());
		feedBackEmailProcess.setEaseOfFindingInfo(event.getEaseOfFindingInfo());
		feedBackEmailProcess.setProductAvailability(event.getProductAvailability());
		feedBackEmailProcess.setVarietyOfProducts(event.getProductAvailability());
		feedBackEmailProcess.setOthers(event.getOthers());
		feedBackEmailProcess.setFeedbackcomments(event.getComments());

		final BaseStoreModel currentBaseStore = baseStoreService.getCurrentBaseStore();
		if (currentBaseStore != null) {
			feedBackEmailProcess.setStore(currentBaseStore);
		}

		getModelService().save(feedBackEmailProcess);
		getBusinessProcessService().startProcess(feedBackEmailProcess);

	}

	/**
	 * return Base store service instance
	 *
	 * @return baseStoreService
	 */
	public BaseStoreService getBaseStoreService() {
		return baseStoreService;
	}

	/**
	 * Set Base Store Service instance
	 *
	 * @param baseStoreService
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService) {
		this.baseStoreService = baseStoreService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commerceservices.event.AbstractSiteEventListener#
	 * shouldHandleEvent(de.hybris.platform.servicelayer.event.events.
	 * AbstractEvent)
	 */
	@Override
	protected boolean shouldHandleEvent(final FeedBackEvent event) {
		final BaseSiteModel site = getBaseSiteService().getBaseSiteForUID("ssl");

		LOG.info(" $$$$$$$$$$$222" + getBaseSiteService().getBaseSiteForUID("ssl").getName() + "###############22");
		ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
		return SiteChannel.B2C.equals(site.getChannel());
	}

}
