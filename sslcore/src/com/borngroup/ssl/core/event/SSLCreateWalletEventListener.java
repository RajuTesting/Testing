/**
 *
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.events.SSLCreateWalletEvent;

/**
 * <p>
 * <p>
 * SSLCreateWalletEventListener : Event listener for sending mail in wallet creation.
 * <p>
 * Created By : anupam.srivastava@nagarro.com
 *
 * @author Ssl
 */
public class SSLCreateWalletEventListener extends AbstractSiteEventListener<SSLCreateWalletEvent> {
    /** Log4j logger. */
    private static final Logger LOG = Logger.getLogger(SSLCreateWalletEventListener.class);

    /** Model Service. */
    private ModelService modelService;

    /** Business Process Service. */
    private BusinessProcessService businessProcessService;

    @Resource(name = "baseSiteService")
    private BaseSiteService baseSiteService;

    @Resource(name = "commerceCommonI18NService")
    private CommerceCommonI18NService commerceCommonI18NService;

    /**
     * @return the baseSiteService
     */
    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    /**
     * @param baseSiteService the baseSiteService to set
     */
    public void setBaseSiteService(final BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    /** Base store Service. */
    @Resource(name = "baseStoreService")
    private BaseStoreService baseStoreService;

    /** Job process name **/
    private static final String JOB_EMAIL_PROCESS_NAME = "sslCreateWalletEmailProcess";

    @Override
    protected void onSiteEvent(final SSLCreateWalletEvent sslCreateWalletEvent) {
        if (sslCreateWalletEvent != null) {
            final StoreFrontCustomerProcessModel storeFrontCustomerProcessModel = this.getBusinessProcessService().createProcess(
                    JOB_EMAIL_PROCESS_NAME + System.currentTimeMillis(), JOB_EMAIL_PROCESS_NAME);
            storeFrontCustomerProcessModel.setSite(getBaseSiteService().getBaseSiteForUID("ssl"));
            storeFrontCustomerProcessModel.setCustomer(sslCreateWalletEvent.getCustomer());
            storeFrontCustomerProcessModel.setLanguage(commerceCommonI18NService.getCurrentLanguage());
            storeFrontCustomerProcessModel.setCurrency(commerceCommonI18NService.getCurrentCurrency());
            final BaseStoreModel currentBaseStore = baseStoreService.getCurrentBaseStore();
            if (currentBaseStore != null) {
                storeFrontCustomerProcessModel.setStore(currentBaseStore);
            }

            getModelService().save(storeFrontCustomerProcessModel);
            this.getBusinessProcessService().startProcess(storeFrontCustomerProcessModel);
        } else {
            LOG.error("Exception occured in SSLCreateEvent Listener which is null");
        }
    }

    @Override
    protected boolean shouldHandleEvent(final SSLCreateWalletEvent event) {
        final BaseSiteModel site = event.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
        return SiteChannel.B2C.equals(site.getChannel());
    }

    /**
     * @return the modelService
     */
    public ModelService getModelService() {
        return modelService;
    }

    /**
     * @param modelService the modelService to set
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
     * @param businessProcessService the businessProcessService to set
     */
    public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
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

}
