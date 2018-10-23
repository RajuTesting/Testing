package com.borngroup.ssl.core.event;

import com.borngroup.ssl.core.events.ProfilePictureUpdateEvent;
import com.borngroup.ssl.core.sslsocialconnectaddon.CustomerSocialAccountService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

/**
 * Fires when profile picture needs to be updated for social customer.
 */
public class ProfilePictureUpdateEventListener extends AbstractEventListener<ProfilePictureUpdateEvent> {

	private static final Logger LOG = Logger.getLogger(ProfilePictureUpdateEventListener.class);

	/** The customerSocialAccountService. */
	@Resource(name = "customerSocialAccountService")
	private CustomerSocialAccountService customerSocialAccountService;

	@Override
	protected void onEvent(final ProfilePictureUpdateEvent event) {
		final CustomerModel customerModel = event.getCustomer();
		customerSocialAccountService.updateProfilePicture(event.getSocialId(), event.getImageUrl(), customerModel);
		if(LOG.isDebugEnabled()) {
			LOG.debug(String.format("Updating Profile picture for %s, current thread = %s", customerModel.getName(),
					Thread.currentThread().getId()));
		}
	}



}
