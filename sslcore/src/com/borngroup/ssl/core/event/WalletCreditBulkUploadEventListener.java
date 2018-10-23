/**
 *
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.commerceservices.model.process.WalletCreditsBulkUploadStoreFrontProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.events.WalletCreditsBulkUploadEvent;

/**
 * Event listener for mail send event in Credits Bulk Upload Failure cases.
 *
 * @author shilpaverma
 *
 */
public class WalletCreditBulkUploadEventListener extends AbstractSiteEventListener<WalletCreditsBulkUploadEvent> {

    /** Log4j logger. */
    private static final Logger LOG = Logger.getLogger(WalletCreditBulkUploadEventListener.class);

    /** JOB EMAIL PROCESS NAME. */
    private static final String JOB_EMAIL_PROCESS_NAME = "walletCreditsBulkUploadResultEmailProcess";

    /** Model Service. */
    private ModelService modelService;

    /** Business Process Service. */
    private BusinessProcessService businessProcessService;

    @Override
    protected void onSiteEvent(final WalletCreditsBulkUploadEvent walletCreditsBulkUploadEvent) {
        if (null != walletCreditsBulkUploadEvent) {
            LOG.info("################### WalletCreditBulkUploadEventListener onSiteEvent  start ##########");
            final WalletCreditsBulkUploadStoreFrontProcessModel walletCreditsBulkUploadProcessModel = this.getBusinessProcessService()
                    .createProcess(JOB_EMAIL_PROCESS_NAME + System.currentTimeMillis(), "walletCreditsBulkUploadStoreFrontProcess");
            walletCreditsBulkUploadProcessModel.setSite(walletCreditsBulkUploadEvent.getSite());
            walletCreditsBulkUploadProcessModel.setUploadStatus(walletCreditsBulkUploadEvent.getUploadStatus().toUpperCase());
            walletCreditsBulkUploadProcessModel.setReportAttachmentCode(walletCreditsBulkUploadEvent.getReportAttachmentCode());

            getModelService().save(walletCreditsBulkUploadProcessModel);
            getBusinessProcessService().startProcess(walletCreditsBulkUploadProcessModel);
            LOG.info("################### WalletCreditBulkUploadEventListener onSiteEvent end  ##########");
        } else {
            LOG.info("################### WalletCreditBulkUploadEventListener cannot start onSiteEvent : NULL #########");
        }
    }

    @Override
    protected boolean shouldHandleEvent(final WalletCreditsBulkUploadEvent walletCreditsBulkUploadEvent) {
        LOG.info("################### WalletCreditBulkUploadEventListener shouldHandleEvent  ##########");
        LOG.info("BaseSite from event : " + walletCreditsBulkUploadEvent.getSite());
        final BaseSiteModel site = walletCreditsBulkUploadEvent.getSite();
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
     * @param service
     *        the modelService to set
     */
    public void setModelService(final ModelService service) {
        this.modelService = service;
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
     * @param aBusinessProcessService
     *        the businessProcessService to set
     */
    public void setBusinessProcessService(final BusinessProcessService aBusinessProcessService) {
        this.businessProcessService = aBusinessProcessService;
    }

}
