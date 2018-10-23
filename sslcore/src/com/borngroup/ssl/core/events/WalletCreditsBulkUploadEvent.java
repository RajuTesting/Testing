/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

/**
 * Event to send mail in Wallet Credits Bulk Upload Failure.
 *
 * @author shilpaverma
 *
 * @param <T>
 */
public class WalletCreditsBulkUploadEvent<T extends BaseSiteModel> extends AbstractEvent {

    /** Upload Status. */
    private String uploadStatus;

    /** Base Site. */
    private T site;

    /** Report Attachment Code. **/
    private String reportAttachmentCode;

    /**
     * @return the reportAttachmentCode
     */
    public String getReportAttachmentCode() {
        return reportAttachmentCode;
    }

    /**
     * @param attachmentCode
     *        the reportAttachmentCode to set
     */
    public void setReportAttachmentCode(final String attachmentCode) {
        this.reportAttachmentCode = attachmentCode;
    }

    /**
     * Getter for uploadStatus.
     *
     * @return the uploadStatus
     */
    public String getUploadStatus() {
        return uploadStatus;
    }

    /**
     * Setter for uploadStatus.
     *
     * @param anUploadStatus
     *        the uploadStatus to set
     */
    public void setUploadStatus(final String anUploadStatus) {
        this.uploadStatus = anUploadStatus;
    }

    /**
     * Getter for base site.
     *
     * @return the site
     */
    public T getSite() {
        return site;
    }

    /**
     * Setter for base site.
     *
     * @param aSite
     *        the site to set
     */
    public void setSite(final T aSite) {
        this.site = aSite;
    }

}
