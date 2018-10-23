/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.borngroup.ssl.core.process.email.actions;

import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.processengine.model.BusinessProcessModel;

/**
 * A process action to remove emails along with attachments that were sent
 * successfully.
 *
 * @author nikhilbarar
 */
public class RemoveSentEmailWithAttachmentAction extends AbstractProceduralAction {
	@Override
	public void executeAction(final BusinessProcessModel businessProcessModel) {
		for (final EmailMessageModel emailMessage : businessProcessModel.getEmails()) {
			if (emailMessage.isSent()) {
				if (null != emailMessage.getAttachments() && !emailMessage.getAttachments().isEmpty()) {
					for (final EmailAttachmentModel emailAttachment : emailMessage.getAttachments()) {
						getModelService().remove(emailAttachment);
					}
				}
				getModelService().remove(emailMessage);
			}
		}
	}
}
