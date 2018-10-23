/**
 *
 */
package com.borngroup.ssl.core.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.mail.MailUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;

/**
 * @author dikshabhatia
 *
 */

public class UnapprovedProductsMailCronJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(UnapprovedProductsMailCronJob.class);
    public static final String EMAIL_MESSAGE = "Please find attached a copy of the file conatining list of fault products";
    public static final String EMAIL_SUBJECT = "List of unapproved Products";

    /*
     * (non-Javadoc)
     * @see
     * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel
     * )
     */
    @Override
    public PerformResult perform(final CronJobModel paramT) {

        LOG.info("######## Start UnapprovedProductsMailCronJob #######");
        sendEmail();
        LOG.info("######## End UnapprovedProductsMailCronJob #######");
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    public void sendEmail() {

        final List<String> toAddresses;
        final List<InternetAddress> internetAddressList = new ArrayList<InternetAddress>();
        final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final Date date = new Date();
        final StringBuffer emailMessage = new StringBuffer();

        try {

            final String SEND_PRODUCT_REPORT_TO_ADDRESS = Config.getParameter(SslCoreConstants.PRODUCT_LIST_MAIL_TO);
            final String SEND_PRODUCT_REPORT_FROM_ADDRESS = Config
                    .getParameter(SslCoreConstants.PRODUCT_LIST_MAIL_FROM);
            final HtmlEmail htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail();
            if (SEND_PRODUCT_REPORT_TO_ADDRESS.contains(",")) {
                toAddresses = Arrays.asList(SEND_PRODUCT_REPORT_TO_ADDRESS.split(","));
                final Iterator<String> iterator = toAddresses.iterator();

                while (iterator.hasNext()) {
                    final InternetAddress emailAddr = new InternetAddress(iterator.next());
                    internetAddressList.add(emailAddr);
                }
                htmlEmail.setTo(internetAddressList);

            } else {
                htmlEmail.addTo(SEND_PRODUCT_REPORT_TO_ADDRESS);
            }

            htmlEmail.setSubject(SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_EMAIL_SUBJECT);
            emailMessage.append(EMAIL_MESSAGE);

            final String filename = Config.getParameter(SslCoreConstants.PRODUCT_FILE_PATH);
            final DataSource source = new FileDataSource(filename);
            htmlEmail.attach(source, "UnapprovedProducts." + dateFormat.format(date), "");

            htmlEmail.setSubject(EMAIL_SUBJECT + "(" + dateFormat.format(date) + ")");
            htmlEmail.setHtmlMsg(emailMessage.toString());
            htmlEmail.setFrom(SEND_PRODUCT_REPORT_FROM_ADDRESS);
            htmlEmail.send();
            LOG.info("########  UnapprovedProductsMailCronJob: Product List Mailed #######");

        } catch (final EmailException ex) {
            LOG.error("Email Exception");
            LOG.error(ex.getMessage());

        } catch (final AddressException ex) {
            LOG.error("Address Exception");
            LOG.error(ex.getMessage());

        }

    }

}
