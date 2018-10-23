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

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.commerceservices.model.process.ProductUploadAutoApprovalStoreFrontProcessModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.orderprocessing.model.SterlingCustomerNotificationProcessModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.processengine.model.BusinessProcessParameterModel;
import de.hybris.platform.task.RetryLaterException;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.services.email.impl.SSLSendEmailWithAttachmentService;

/**
 * A process action to send emails with attachments.
 *
 * @author nikhilbarar
 */
public class SendEmailWithAttachmentAction extends AbstractProceduralAction {
    private EmailService emailService;
    private SSLSendEmailWithAttachmentService sslSendEmailWithAttachmentService;

    public SSLSendEmailWithAttachmentService getSslSendEmailWithAttachmentService() {
        return sslSendEmailWithAttachmentService;
    }

    @Required
    public void setSslSendEmailWithAttachmentService(final SSLSendEmailWithAttachmentService sslSendEmailWithAttachmentService) {
        this.sslSendEmailWithAttachmentService = sslSendEmailWithAttachmentService;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    @Required
    public void setEmailService(final EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void executeAction(final de.hybris.platform.processengine.model.BusinessProcessModel businessProcessModel)
            throws RetryLaterException {

        List<EmailAttachmentModel> emailAttachments = null;
        if (businessProcessModel instanceof OrderProcessModel && null != businessProcessModel.getContextParameters()) {
            for (final BusinessProcessParameterModel contextParam : businessProcessModel.getContextParameters()) {
                if (contextParam.getName().equals(((OrderProcessModel) businessProcessModel).getOrder().getCode())) {
                    emailAttachments = (List<EmailAttachmentModel>) contextParam.getValue();
                }
            }
        } else if (businessProcessModel instanceof ConsignmentProcessModel && null != businessProcessModel.getContextParameters()) {
            for (final BusinessProcessParameterModel contextParam : businessProcessModel.getContextParameters()) {
                if (contextParam.getName().equals(((ConsignmentProcessModel) businessProcessModel).getConsignment().getCode())) {
                    emailAttachments = (List<EmailAttachmentModel>) contextParam.getValue();
                }
            }
		} else if (businessProcessModel instanceof SterlingCustomerNotificationProcessModel
				&& null != businessProcessModel.getContextParameters()) {
			for (final BusinessProcessParameterModel contextParam : businessProcessModel.getContextParameters()) {
				if (contextParam.getName() != null && contextParam.getName().equals(
						((SterlingCustomerNotificationProcessModel) businessProcessModel).getOrder().getCode())) {
					emailAttachments = (List<EmailAttachmentModel>) contextParam.getValue();
				}
			}
        }
        else if (businessProcessModel instanceof ProductUploadAutoApprovalStoreFrontProcessModel
				&& null != businessProcessModel.getContextParameters()) {
			for (final BusinessProcessParameterModel contextParam : businessProcessModel.getContextParameters()) {
				if (contextParam.getName() != null && contextParam.getName().equals(
						("productAutoApproval"))) {
					emailAttachments = (List<EmailAttachmentModel>) contextParam.getValue();
				}
			}
        }

        for (final EmailMessageModel email : businessProcessModel.getEmails()) {
            if (null != emailAttachments && !emailAttachments.isEmpty()) {
                email.setAttachments(emailAttachments);
            }
            getEmailService().send(email);
        }
    }
}
