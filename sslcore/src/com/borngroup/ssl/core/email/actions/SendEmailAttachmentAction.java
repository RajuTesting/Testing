/**
 *
 */
package com.borngroup.ssl.core.email.actions;

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

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.task.RetryLaterException;
import de.hybris.platform.util.Config;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.FccCrmTicketProcessModel;

/**
 * A process action to send emails.
 */
public class SendEmailAttachmentAction extends AbstractProceduralAction {

    private static final Logger LOG = Logger.getLogger(SendEmailAttachmentAction.class);

    private EmailService emailService;
    private CatalogService catalogService;
    private CatalogVersionService catalogVersionService;

    public CatalogVersionService getCatalogVersionService() {
        return catalogVersionService;
    }

    public void setCatalogVersionService(final CatalogVersionService catalogVersionService) {
        this.catalogVersionService = catalogVersionService;
    }

    public CatalogService getCatalogService() {
        return catalogService;
    }

    public void setCatalogService(final CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    protected EmailService getEmailService() {
        return emailService;
    }

    @Required
    public void setEmailService(final EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void executeAction(final BusinessProcessModel businessProcessModel) throws RetryLaterException, FileNotFoundException {

        LOG.info("indside new send fcc action:::");

        final FccCrmTicketProcessModel crmTicketProcessModel = (FccCrmTicketProcessModel) businessProcessModel;

        LOG.info("get File Path:::" + crmTicketProcessModel.getFilePath());

        for (final EmailMessageModel email : businessProcessModel.getEmails()) {
            final FileNameMap fileNameMap = URLConnection.getFileNameMap();
            final EmailAddressModel addressModel = new EmailAddressModel();
            addressModel.setEmailAddress(Config.getParameter("crm.emailid"));
            addressModel.setDisplayName(Config.getParameter("crm.displayname"));
            final List<EmailAddressModel> listEmailAddress = new ArrayList<EmailAddressModel>();
            listEmailAddress.add(addressModel);
            email.setToAddresses(listEmailAddress);
            final File firstLocalFile = new File(crmTicketProcessModel.getFilePath());
            final File[] files = firstLocalFile.listFiles();
            for (final File file : files) {

                final String fileName = file.getName();
                final String contentType = fileNameMap.getContentTypeFor(file.getName());
                LOG.info("contentType=" + contentType + "mimeType=" + contentType);
                final InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
                final DataInputStream dis = new DataInputStream(inputStream);
                final List<EmailAttachmentModel> emailAttachmentList = new ArrayList<EmailAttachmentModel>();
                LOG.info("indside new send fcc action :: dis : " + dis);
                LOG.info("indside new send file name action :: fileName : " + fileName);
                final EmailAttachmentModel emailAttachment = emailService.createEmailAttachment(dis, fileName, contentType);
                // emailAttachment.setCatalogVersion(getCatalogVersion());
                modelService.save(emailAttachment);
                emailAttachmentList.add(emailAttachment);

                email.setAttachments(emailAttachmentList);

            }
            modelService.save(email);
            getEmailService().send(email);

        }
    }

    protected CatalogVersionModel getCatalogVersion() {

        final CatalogVersionModel catalogVersion = getCatalogVersionService().getCatalogVersion("sslContentCatalog", "Online");

        return catalogVersion;
    }
}
