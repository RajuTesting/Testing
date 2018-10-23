/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.user.data.ComplaintData;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

/**
 * @author ashishsabal
 *
 */
public class ComplaintEvent<T extends BaseSiteModel> extends AbstractEvent {
    private ComplaintData complaintData;
    private T site;

    /**
     * @return the complaintData
     */
    public ComplaintData getComplaintData() {
        return complaintData;
    }

    /**
     * @param complaintData
     *        the complaintData to set
     */
    public void setComplaintData(final ComplaintData complaintData) {
        this.complaintData = complaintData;
    }

    /**
     * @return the site
     */
    public T getSite() {
        return site;
    }

    /**
     * @param site the site to set
     */
    public void setSite(T site) {
        this.site = site;
    }
}
