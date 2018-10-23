/**
 *
 */
package com.borngroup.ssl.core.services.email.impl;

import de.hybris.platform.acceleratorservices.email.impl.DefaultEmailGenerationService;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.commerceservices.model.process.WalletCreditsBulkUploadStoreFrontProcessModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.SSLEmailAttachmentDao;

/**
 * @author satyanarayana.naidu
 *
 */
public class SSLDefaultEmailGenerationService extends DefaultEmailGenerationService {

    private static final Logger LOG = Logger.getLogger(SSLDefaultEmailGenerationService.class);

    private SSLEmailAttachmentDao sslEmailAttachmentDao;

    /*
     * (non-Javadoc)
     * 
     * @see de.hybris.platform.acceleratorservices.email.impl. DefaultEmailGenerationService#generate(de.hybris.platform.
     * processengine.model.BusinessProcessModel, de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel)
     */
    @Override
    public EmailMessageModel generate(final BusinessProcessModel businessProcessModel, final EmailPageModel emailPageModel)
            throws RuntimeException {
        final EmailMessageModel emailMessageModel = super.generate(businessProcessModel, emailPageModel);
        if (businessProcessModel instanceof ConsignmentProcessModel) {
            final ConsignmentProcessModel consignmentProcessModel = (ConsignmentProcessModel) businessProcessModel;
            if (consignmentProcessModel.getInvoiceAttachmentCode() != null) {
                final EmailAttachmentModel emailAttachmentModel = sslEmailAttachmentDao.findByCode(consignmentProcessModel
                        .getInvoiceAttachmentCode());
                if (emailMessageModel.getAttachments() == null) {
                    emailMessageModel.setAttachments(new ArrayList<EmailAttachmentModel>());
                }
                final List<EmailAttachmentModel> emailAttachmentModels = new ArrayList<EmailAttachmentModel>();
                emailAttachmentModels.add(emailAttachmentModel);
                emailMessageModel.setAttachments(emailAttachmentModels);
                getModelService().save(emailMessageModel);
            }
        }

        if (businessProcessModel instanceof WalletCreditsBulkUploadStoreFrontProcessModel) {
            this.addAttachmentForBulkUploadEmail(businessProcessModel, emailMessageModel);
        }

        /* included this for bcc */

        final EmailAddressModel toAddress1 = getEmailService().getOrCreateEmailAddressForEmail(

                Config.getParameter("website.backupemail.ToAddress") != null ? Config.getParameter("website.backupemail.ToAddress")
                        : "backupestore@shoppersstop.com",
                Config.getParameter("website.email.ToAddressDisplayName") != null ? Config
                        .getParameter("website.email.ToAddressDisplayName") : "Shopper Stop Team");
        final List<EmailAddressModel> emailAddressModels = new ArrayList<EmailAddressModel>();
        emailAddressModels.add(toAddress1);

        emailMessageModel.setBccAddresses(emailAddressModels);
        getModelService().save(emailMessageModel);

        return emailMessageModel;
    }

    @Override
    protected EmailMessageModel createEmailMessage(final String emailSubject, final String emailBody,
            final AbstractEmailContext<BusinessProcessModel> emailContext) {
        final List<EmailAddressModel> toEmails = new ArrayList<EmailAddressModel>();

        if (!emailContext.getClass().getName().contains("FeedBackEmailContext")
                && !emailContext.getClass().getName().contains("CustomerBirthdayEmailContext")) {
            if (emailContext.getClass().getName().contains("NewsLettersEmailContext")) {
                final EmailAddressModel toAddress = getEmailService().getOrCreateEmailAddressForEmail(emailContext.getToEmail(),
                        emailContext.getToEmail().substring(emailContext.getToEmail().lastIndexOf("@")));

                toEmails.add(toAddress);

            } else {
                final EmailAddressModel toAddress = getEmailService().getOrCreateEmailAddressForEmail(emailContext.getToEmail(),
                        emailContext.getToDisplayName());

                toEmails.add(toAddress);
            }

            final EmailAddressModel fromAddress = getEmailService().getOrCreateEmailAddressForEmail(
                    Config.getParameter("website.email.fromAddress") != null ? Config.getParameter("website.email.fromAddress")
                            : "customercare@shoppersstop.com",
                    Config.getParameter("website.email.fromAddressDisplayName") != null ? Config.getParameter("website.email.fromAddress")
                            : "Shopper Stop Team");
            return getEmailService().createEmailMessage(toEmails, new ArrayList<EmailAddressModel>(), new ArrayList<EmailAddressModel>(),
                    fromAddress, emailContext.getFromEmail(), emailSubject, emailBody, null);
        } else {

            if (emailContext.getClass().getName().contains("CustomerBirthdayEmailContext")) {

                final String[] emails = emailContext.getToEmail().split("\\|");

                LOG.info("&&&&&&&&&&&&&  emailContext.getToEmail()   &&&&&&&&&&&&&&&");
                for (final String email : emails) {
                    final EmailAddressModel addressModel = new EmailAddressModel();
                    addressModel.setDisplayName(email.substring(email.lastIndexOf("@")));
                    addressModel.setEmailAddress(email);
                    getModelService().save(addressModel);
                    toEmails.add(addressModel);
                }

                final EmailAddressModel fromAddress = getEmailService().getOrCreateEmailAddressForEmail(

                        Config.getParameter("website.email.fromAddress") != null ? Config.getParameter("website.email.fromAddress")
                                : "customercare@shoppersstop.com",
                        Config.getParameter("website.email.fromAddressDisplayName") != null ? Config
                                .getParameter("website.email.fromAddress") : "Shopper Stop Team");
                return getEmailService().createEmailMessage(toEmails, new ArrayList<EmailAddressModel>(),
                        new ArrayList<EmailAddressModel>(), fromAddress, emailContext.getFromEmail(), emailSubject, emailBody, null);
            } else {
                final EmailAddressModel toAddress = getEmailService().getOrCreateEmailAddressForEmail(

                        Config.getParameter("website.email.ToAddress") != null ? Config.getParameter("website.email.ToAddress")
                                : "customercare@shoppersstop.com",
                        Config.getParameter("website.email.ToAddressDisplayName") != null ? Config
                                .getParameter("website.email.ToAddressDisplayName") : "Shopper Stop Team");

                toEmails.add(toAddress);

                final EmailAddressModel fromAddress = getEmailService().getOrCreateEmailAddressForEmail(emailContext.getToEmail(),
                        emailContext.getToDisplayName());
                return getEmailService().createEmailMessage(toEmails, new ArrayList<EmailAddressModel>(),
                        new ArrayList<EmailAddressModel>(), fromAddress, emailContext.getFromEmail(), emailSubject, emailBody, null);
            }
        }

    }

    public SSLEmailAttachmentDao getSslEmailAttachmentDao() {
        return sslEmailAttachmentDao;
    }

    public void setSslEmailAttachmentDao(final SSLEmailAttachmentDao sslEmailAttachmentDao) {
        this.sslEmailAttachmentDao = sslEmailAttachmentDao;
    }

    /**
     * Adds Attachment to emailMessageModel.
     *
     * @param businessProcessModel
     *        the businessProcessModel
     * @param emailMessageModel
     *        the emailMessageModel
     */
    private void addAttachmentForBulkUploadEmail(final BusinessProcessModel businessProcessModel, final EmailMessageModel emailMessageModel) {
        final WalletCreditsBulkUploadStoreFrontProcessModel walletBusinessProcessModel = (WalletCreditsBulkUploadStoreFrontProcessModel) businessProcessModel;
        if (walletBusinessProcessModel.getReportAttachmentCode() != null) {
            final EmailAttachmentModel emailAttachmentModel = this.sslEmailAttachmentDao.findByCode(walletBusinessProcessModel
                    .getReportAttachmentCode());
            if (emailMessageModel.getAttachments() == null) {
                emailMessageModel.setAttachments(new ArrayList<EmailAttachmentModel>());
            }
            final List<EmailAttachmentModel> emailAttachmentModels = new ArrayList<EmailAttachmentModel>();
            emailAttachmentModels.add(emailAttachmentModel);
            emailMessageModel.setAttachments(emailAttachmentModels);
            this.getModelService().save(emailMessageModel);
        }
    }
}
