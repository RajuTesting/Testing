/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

/**
 * @author ssl
 *
 * Social customer profile picture update event.
 */
public class ProfilePictureUpdateEvent extends AbstractEvent implements ClusterAwareEvent
{
    private CustomerModel customer;
    private String imageUrl;
    private String socialId;

    public ProfilePictureUpdateEvent(final CustomerModel customer, final String imageUrl, final String socialId)
    {
        this.customer = customer;
        this.imageUrl = imageUrl;
        this.socialId = socialId;
    }


    @Override
    public boolean publish(final int sourceNodeId, final int targetNodeId)
    {
            return targetNodeId == sourceNodeId;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }
}
