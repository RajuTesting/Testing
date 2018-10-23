/**
 *
 */
package com.borngroup.ssl.core.process.email.actions;

import de.hybris.platform.acceleratorservices.email.CMSEmailPageService;
import de.hybris.platform.acceleratorservices.email.EmailGenerationService;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.acceleratorservices.process.strategies.ProcessContextResolutionStrategy;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.task.RetryLaterException;
import de.hybris.platform.task.RetryLaterException.Method;
import de.hybris.platform.util.Config;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.EgvEmailProcessModel;

/**
 * @author
 *
 */
public class SendEmailAction extends de.hybris.platform.acceleratorservices.process.email.actions.SendEmailAction {

    Logger LOG = Logger.getLogger(SendEmailAction.class);
    private CMSEmailPageService cmsEmailPageService;
    private Map<String, String> frontendTemplateNameMapping;
    private ProcessContextResolutionStrategy contextResolutionStrategy;
    private EmailGenerationService emailGenerationService;
    private BusinessProcessService businessProcessService;

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

    protected CMSEmailPageService getCmsEmailPageService() {
        return cmsEmailPageService;
    }

    @Required
    public void setCmsEmailPageService(final CMSEmailPageService cmsEmailPageService) {
        this.cmsEmailPageService = cmsEmailPageService;
    }

    protected Map<String, String> getfrontendTemplateNameMapping() {
        return frontendTemplateNameMapping;
    }

    @Required
    public void setfrontendTemplateNameMapping(final Map<String, String> frontendTemplateName) {
        this.frontendTemplateNameMapping = frontendTemplateName;
    }

    protected ProcessContextResolutionStrategy getContextResolutionStrategy() {
        return contextResolutionStrategy;
    }

    @Required
    public void setContextResolutionStrategy(final ProcessContextResolutionStrategy contextResolutionStrategy) {
        this.contextResolutionStrategy = contextResolutionStrategy;
    }

    protected EmailGenerationService getEmailGenerationService() {
        return emailGenerationService;
    }

    @Required
    public void setEmailGenerationService(final EmailGenerationService emailGenerationService) {
        this.emailGenerationService = emailGenerationService;
    }

    public static final String FRONTEND_TEMPLATE_NAME = "frontendTemplateName";
    public static final long MIN_WAIT_TIME = 60000L;
    public static final String ACTION_RETRY_WAIT_TIME = "eGV.actions.wait.time.seconds";

    @Override
    public void executeAction(final de.hybris.platform.processengine.model.BusinessProcessModel businessProcessModel)
            throws RetryLaterException {
        for (final EmailMessageModel email : businessProcessModel.getEmails()) {
            if (!(businessProcessModel instanceof EgvEmailProcessModel)) {
                getEmailService().send(email);
            } else if (email.getBodyMedia() != null) {
                LOG.debug("Sending Egv Email for process with code " + businessProcessModel.getCode());
                getEmailService().send(email);
            }
            if ((businessProcessModel instanceof EgvEmailProcessModel) && (!email.isSent())) {
                LOG.debug("EGV email not sent for process " + businessProcessModel.getCode()
                        + " due to absence of body media. Process will be restarted in " + MIN_WAIT_TIME / 1000 + " seconds.");
                try {
                    synchronized (this) {
                        Thread.currentThread().wait(MIN_WAIT_TIME);
                    }
                } catch (final InterruptedException e) {
                    LOG.info("Thread was interrupted. Restarting process with code " + businessProcessModel.getCode() + " now.");
                }
                getBusinessProcessService().restartProcess(businessProcessModel, "generateeGiftVoucherEmail");
            }
        }
    }

    protected RetryLaterException retryLater(final long timeInMillis) {
        final long waitTime = timeInMillis > 0 ? timeInMillis : getActionWaitTime();
        final long actualTime = waitTime > MIN_WAIT_TIME ? waitTime : MIN_WAIT_TIME;
        final RetryLaterException rex = new RetryLaterException();
        rex.setDelay(actualTime);
        rex.setRollBack(false);
        rex.setMethod(Method.LINEAR);
        return rex;
    }

    protected RetryLaterException retryLater() {
        return retryLater(0L);
    }

    public static long getActionWaitTime() {
        return Config.getLong(ACTION_RETRY_WAIT_TIME, 60) * 1000L;
    }
}
