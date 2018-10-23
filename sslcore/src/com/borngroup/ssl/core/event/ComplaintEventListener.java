/**
 *
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.commerceservices.model.process.ContactUsStoreFrontProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.events.ComplaintEvent;

/**
 * @author ashishsabal
 *
 */
public class ComplaintEventListener extends AbstractSiteEventListener<ComplaintEvent> {

    private static final Logger LOG = Logger.getLogger(ComplaintEventListener.class);

    private ModelService modelService;
    private BusinessProcessService businessProcessService;

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.commerceservices.event.AbstractSiteEventListener#onSiteEvent(de.hybris.platform.servicelayer.event.events.
     * AbstractEvent)
     */
    @Override
    protected void onSiteEvent(final ComplaintEvent complaintEvent) {
        LOG.info("################### ComplaintEventListener onSiteEvent  start ##########");
        final ContactUsStoreFrontProcessModel complaintProcessModel = (ContactUsStoreFrontProcessModel) getBusinessProcessService()
                .createProcess("complaintSubmissionEmailProcess" + System.currentTimeMillis(), "submitComplaintEmailProcess");
        complaintProcessModel.setSite(complaintEvent.getSite());
        complaintProcessModel.setFirstName(complaintEvent.getComplaintData().getFirstName());
        complaintProcessModel.setLastName(complaintEvent.getComplaintData().getLastName());
        complaintProcessModel.setOrderNumber(complaintEvent.getComplaintData().getOrderNumber());
        complaintProcessModel.setIssueCategory(complaintEvent.getComplaintData().getIssueCategory());
        complaintProcessModel.setMobile(complaintEvent.getComplaintData().getMobile());
        complaintProcessModel.setMailID(complaintEvent.getComplaintData().getMailID());
        complaintProcessModel.setComment(complaintEvent.getComplaintData().getComment());
        getModelService().save(complaintProcessModel);
        getBusinessProcessService().startProcess(complaintProcessModel);
        LOG.info("################### ComplaintEventListener onSiteEvent end  ##########");
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * de.hybris.platform.commerceservices.event.AbstractSiteEventListener#shouldHandleEvent(de.hybris.platform.servicelayer.event.events
     * .AbstractEvent)
     */
    @Override
    protected boolean shouldHandleEvent(final ComplaintEvent complaintEvent) {
        LOG.info("################### ComplaintEventListener shouldHandleEvent  ##########");
        final BaseSiteModel site = complaintEvent.getSite();
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
     * @param modelService
     *        the modelService to set
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
     *        the businessProcessService to set
     */
    public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

}
