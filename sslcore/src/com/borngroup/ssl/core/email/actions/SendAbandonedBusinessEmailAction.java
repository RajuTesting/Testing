/**
 *
 */
package com.borngroup.ssl.core.email.actions;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.task.RetryLaterException;
import de.hybris.platform.util.Config;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author S53106
 *
 */

// SSLM-3389 Starts : New File for Send Email Action
public class SendAbandonedBusinessEmailAction extends AbstractProceduralAction {

    private EmailService emailService;
    private MediaService mediaService;
    private CatalogVersionService catalogVersionService;

    /**
     * @return the catalogVersionService
     */
    public CatalogVersionService getCatalogVersionService() {
        return catalogVersionService;
    }

    /**
     * @param catalogVersionService the catalogVersionService to set
     */
    public void setCatalogVersionService(final CatalogVersionService catalogVersionService) {
        this.catalogVersionService = catalogVersionService;
    }

    /**
     * @return the mediaService
     */
    public MediaService getMediaService() {
        return mediaService;
    }

    /**
     * @param mediaService the mediaService to set
     */
    public void setMediaService(final MediaService mediaService) {
        this.mediaService = mediaService;
    }

    protected EmailService getEmailService() {
        return emailService;
    }

    @Required
    public void setEmailService(final EmailService emailService) {
        this.emailService = emailService;
    }

    @Resource(name = "modelService")
    private ModelService modelService;

    @Override
    public void executeAction(final de.hybris.platform.processengine.model.BusinessProcessModel businessProcessModel)
            throws RetryLaterException, FileNotFoundException, ParseException {

        for (final EmailMessageModel email : businessProcessModel.getEmails()) {

            final Calendar cal = Calendar.getInstance();
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            final String abandonedReportFolder = dateFormat.format(cal.getTime());
            final File directory = new File(Config.getParameter("abandonedcart.report.local.path") + abandonedReportFolder);

            if (!directory.exists() || !directory.isDirectory()) {
                directory.mkdir();
            }
            final String filepath = directory + "/" + "AbandonedCartCustomers.csv";
            final File file = new File(filepath);
            final FileNameMap fileNameMap = URLConnection.getFileNameMap();

            final String fileName = file.getName();
            final String contentType = fileNameMap.getContentTypeFor(file.getName());
            final InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
            final DataInputStream dis = new DataInputStream(inputStream);
            final List<EmailAttachmentModel> emailAttachmentList = new ArrayList<EmailAttachmentModel>();
            final EmailAttachmentModel emailAttachment = createBusinessEmailAttachment(dis, fileName, contentType);
            modelService.save(emailAttachment);
            emailAttachmentList.add(emailAttachment);

            email.setAttachments(emailAttachmentList);
            email.setToAddresses(createBusinessEmailToList());

            modelService.save(email);
            getEmailService().send(email);

        }

    }

    public EmailAttachmentModel createBusinessEmailAttachment(final DataInputStream masterDataStream, final String filename,
            final String mimeType) throws ParseException {
        final EmailAttachmentModel attachment = getModelService().create(EmailAttachmentModel.class);
        final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dt = new Date();
        dt = df.parse(String.valueOf(dt.getTime()));
        attachment.setCode(dt + "_" + filename);
        attachment.setMime(mimeType);
        attachment.setRealFileName(filename);
        attachment.setCatalogVersion(getCatalogVersionService().getCatalogVersion(Config.getParameter("abandonedcart.content.catalog.id"),
                Config.getParameter("abandonedcart.content.catalog.version")));
        getModelService().save(attachment);

        getMediaService().setStreamForMedia(attachment, masterDataStream, filename, mimeType);
        return attachment;
    }

    public List<EmailAddressModel> createBusinessEmailToList() {

        final List<EmailAddressModel> emailToAddressesList = new ArrayList<EmailAddressModel>();

        final String businessEmails = Config.getParameter("abandonedcart.business.email.id.list");
        final List<String> listOfBusinessEmail = Arrays.asList(businessEmails.split(","));
        for (final String emails : listOfBusinessEmail) {

            final EmailAddressModel emailToAddresse = getEmailService().getOrCreateEmailAddressForEmail(emails,
                    emails.substring(0, emails.indexOf("@")));
            emailToAddressesList.add(emailToAddresse);
        }

        return emailToAddressesList;
    }

}

// SSLM-3389 Ends