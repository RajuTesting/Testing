/**
 *
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.events.FccEnrollmentMessageEvent;

/**
 * @author techouts
 *
 */
public class FccEnrollmentEventListener extends
		AbstractSiteEventListener<FccEnrollmentMessageEvent> {
	private static final Logger LOG = org.apache.log4j.Logger
			.getLogger(FccLinkEventListener.class);
	private BusinessProcessService businessProcessService;
	private ModelService modelService;
	private BaseSiteService baseSiteService;
	private BaseStoreService baseStoreService;
	

	@Override
	protected void onSiteEvent(final FccEnrollmentMessageEvent event) {
		final StoreFrontCustomerProcessModel storeFrontCustomerProcess = getBusinessProcessService()
				.createProcess(
						"fccEnrollmentNotificationEmailProcessID-"
								+ event.getCustomer().getUid() + "-"
								+ System.currentTimeMillis(),
						"fccEnrollmentNotificationEmailProcess");
		
		storeFrontCustomerProcess.setSite(getBaseSiteService().getBaseSiteForUID("ssl"));
		final BaseStoreModel currentBaseStore = baseStoreService.getCurrentBaseStore();
		if (currentBaseStore != null) {
			storeFrontCustomerProcess.setStore(currentBaseStore);
		}
		storeFrontCustomerProcess.setLanguage(event.getLanguage());
		storeFrontCustomerProcess.setCurrency(event.getCurrency());
		storeFrontCustomerProcess.setCustomer(event.getCustomer());
		getModelService().save(storeFrontCustomerProcess);
		LOG.info("sending FCC enrollment notifications email ");
		getBusinessProcessService().startProcess(storeFrontCustomerProcess);
	}


	@Override
	protected boolean shouldHandleEvent(final FccEnrollmentMessageEvent event) {
		final BaseSiteModel site = getBaseSiteService().getBaseSiteForUID("ssl");
		ServicesUtil
				.validateParameterNotNullStandardMessage("event.site", site);
		return SiteChannel.B2C.equals(site.getChannel());
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
	public void setBusinessProcessService(
			final BusinessProcessService businessProcessService) {
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

	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService() {
		return baseSiteService;
	}

	/**
	 * @param baseSiteService the baseSiteService to set
	 */
	public void setBaseSiteService(BaseSiteService baseSiteService) {
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService() {
		return baseStoreService;
	}

	/**
	 * @param baseStoreService the baseStoreService to set
	 */
	public void setBaseStoreService(BaseStoreService baseStoreService) {
		this.baseStoreService = baseStoreService;
	}

}
