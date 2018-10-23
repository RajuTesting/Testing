package com.borngroup.ssl.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.events.FccLinkEvent;
import com.borngroup.ssl.core.sms.service.SslCustomerTouchPointService;

/**
 * <p>
 * FccLinkEventListener.java : Listener for FCC Linking notification.
 * <p>
 * Created By : Techouts
 * <p>
 *
 * @author Ssl
 */
public class FccLinkEventListener extends
		AbstractSiteEventListener<FccLinkEvent> {
	private static final Logger LOG = org.apache.log4j.Logger
			.getLogger(FccLinkEventListener.class);
	private BusinessProcessService businessProcessService;
	private ModelService modelService;

	/**
	 * SslCustomerTouchPointService instance.
	 */
	@Resource(name = "sslCustomerTouchPointService")
	private SslCustomerTouchPointService sslCustomerTouchPointService;

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
	public void setBusinessProcessService(
			final BusinessProcessService businessProcessService) {
		this.businessProcessService = businessProcessService;
	}

	@Override
	protected void onSiteEvent(final FccLinkEvent event) {
		final StoreFrontCustomerProcessModel storeFrontCustomerProcess = getBusinessProcessService()
				.createProcess(
						"fccLinkNotificationEmailProcessID-"
								+ event.getCustomer().getUid() + "-"
								+ System.currentTimeMillis(),
						"fccLinkNotificationEmailProcess");
		storeFrontCustomerProcess.setSite(event.getSite());
		storeFrontCustomerProcess.setStore(event.getBaseStore());
		storeFrontCustomerProcess.setLanguage(event.getLanguage());
		storeFrontCustomerProcess.setCurrency(event.getCurrency());
		storeFrontCustomerProcess.setCustomer(event.getCustomer());
		getModelService().save(storeFrontCustomerProcess);
		getBusinessProcessService().startProcess(storeFrontCustomerProcess);
		// send FCC linking confirmation SMS
		LOG.info("Sending FCC linking confirmation SMS");
		sslCustomerTouchPointService.sendFccCardLinkedOrUnlinkSuccessSMS(
				storeFrontCustomerProcess.getCustomer(),"sslloyalty.fcc.link.success.message");
	}

	@Override
	protected boolean shouldHandleEvent(final FccLinkEvent event) {
		final BaseSiteModel site = event.getSite();
		ServicesUtil
				.validateParameterNotNullStandardMessage("event.site", site);
		return SiteChannel.B2C.equals(site.getChannel());
	}

}
