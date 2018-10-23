/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

import java.util.Date;
import java.util.List;

/*
 * Event to send mail in product upload automation cronjobs
 *
 * mailId : ashish.sabal@nagarro.com
 *
 * @author ashishsabal
 *
 */
public class ProductUploadAutomationEvent<T extends BaseSiteModel> extends AbstractEvent {
    /** CronJob Code */
    private String cronJobCode;
    /** JOB start Time */
    private Date startTime;
    /** CronJob Result */
    private CronJobResult cronJobResult;
    /** CronJob Status */
    private CronJobStatus cronJobStatus;
    /** URL to download Report */
    private List<String> urlForReport;

    /** Base Site */
    private T site;

    /**
     * getter for CronJob Code
     *
     * @return the cronJobCode
     */
    public String getCronJobCode() {
        return cronJobCode;
    }

    /**
     * setter for cronJob Code
     *
     * @param cronJobCode the cronJobCode to set
     */
    public void setCronJobCode(final String cronJobCode) {
        this.cronJobCode = cronJobCode;
    }

    /**
     * getter for startTime
     *
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * setter for startTime
     *
     * @param startTime the startTime to set
     */
    public void setStartTime(final Date startTime) {
        this.startTime = startTime;
    }

    /**
     * getter for cronJobResult
     *
     * @return the cronJobResult
     */
    public CronJobResult getCronJobResult() {
        return cronJobResult;
    }

    /**
     * setter for cronJobResult
     *
     * @param cronJobResult the cronJobResult to set
     */
    public void setCronJobResult(final CronJobResult cronJobResult) {
        this.cronJobResult = cronJobResult;
    }

    /**
     * getter for cronJobStatus
     *
     * @return the cronJobStatus
     */
    public CronJobStatus getCronJobStatus() {
        return cronJobStatus;
    }

    /**
     * setter for cronJobStatus
     *
     * @param cronJobStatus the cronJobStatus to set
     */
    public void setCronJobStatus(final CronJobStatus cronJobStatus) {
        this.cronJobStatus = cronJobStatus;
    }

    /**
	 * @return the urlForReport
	 */
	public List<String> getUrlForReport() {
		return urlForReport;
	}

	/**
	 * @param urlForReport the urlForReport to set
	 */
	public void setUrlForReport(List<String> urlForReport) {
		this.urlForReport = urlForReport;
	}

	/**
     * getter for base site
     *
     * @return the site
     */
    public T getSite() {
        return site;
    }

    /**
     * setter for base site
     *
     * @param site the site to set
     */
    public void setSite(final T site) {
        this.site = site;
    }

}
