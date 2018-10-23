/**
 * 
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.events.DataHubConsumptionFailedEvent;
import com.borngroup.ssl.core.services.SSLUserService;


/**
 * @author Deepak
 * 
 */
public class DataHubConsumptionFailedEventListener extends AbstractEventListener<DataHubConsumptionFailedEvent>
{
	private static final Logger LOG = Logger.getLogger(DataHubConsumptionFailedEventListener.class);

	@Autowired
	private EmailService emailService;

	@Autowired
	private SSLUserService userService;

	private final String fromAddressEmail = "DatahubManger@shoppersstop.com";
	private final String fromAddressDisplayName = "DatahubManger@shoppersstop.com";


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.event.impl.AbstractEventListener#onEvent(de.hybris.platform.servicelayer.event
	 * .events.AbstractEvent)
	 */
	@Override
	protected void onEvent(final DataHubConsumptionFailedEvent event)
	{
		// YTODO Auto-generated method stub
		final Collection<PrincipalModel> principalModels = userService.getUsersByUserGroup("productmanagergroup");
		for (final PrincipalModel principalModel : principalModels)
		{
			if (principalModel instanceof UserModel)
			{
				emailService.send(createEmailMessage((UserModel) principalModel, event.getFeedName(), event.getRawType(),
						event.getRawData()));
			}
		}
	}


	private EmailMessageModel createEmailMessage(final UserModel userModel, final String feedName, final String rawType,
			final String[] rawData)
	{
		final List<EmailAddressModel> toEmailAddresses = new ArrayList<EmailAddressModel>();
		final Collection<AddressModel> addressModels = userModel.getAddresses();
		for (final AddressModel addressModel : addressModels)
		{
			final EmailAddressModel toAddress = emailService.getOrCreateEmailAddressForEmail(addressModel.getEmail(),
					addressModel.getEmail());
			toEmailAddresses.add(toAddress);
		}
		final EmailAddressModel fromAddress = emailService
				.getOrCreateEmailAddressForEmail(fromAddressEmail, fromAddressDisplayName);

		final EmailMessageModel message = emailService.createEmailMessage(toEmailAddresses, null, null, fromAddress, "",
				"DataHub Consumption Failed", "Below record \n" + rawData.toString() + "\n failed to load in datahub for feed " + feedName
						+ " and type :" + rawType + " . ", null);

		return message;
	}
}