package com.borngroup.ssl.core.jobs;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.mail.MailUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.AbandonedCartReportCronJobModel;
import com.ss.core.report.service.UserReportService;

/**
 * Cron Job : Cron Job to create report for abandoned cart.
 * <p/>
 * Created by osheen.gulati@nagarro.com.
 *
 * @author SSL
 */
public class AbandonedCartReportForOutboundCronJob extends AbstractJobPerformable<AbandonedCartReportCronJobModel> {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(AbandonedCartReportForOutboundCronJob.class);

	/** The Constant DATA_DIR. */
	private static String dataDirectory = Config.getParameter("HYBRIS_DATA_DIR");

	/** The Constant CART_PAGE_RECALCULATION_RANGE. */
	public static final String CART_PAGE_RECALCULATION_RANGE = "cart.page.recalculation.range";

	/** The Constant ABANDONED_CART_REPORT_SHEET_NAME. */
	public static final String ABANDONED_CART_REPORT_SHEET_NAME = "Abandoned Cart Report";

	/**
	 * User Report Service {@link UserReportService}.
	 */
	@Resource(name = "userReportService")
	private UserReportService userReportService;

	public UserReportService getUserReportService() {
		return userReportService;
	}

	@Override
	public PerformResult perform(final AbandonedCartReportCronJobModel cronJobModel) {

		LOG.info("Media Upload Cronjob Started.");

		final Set<CustomerModel> customerList = getUserReportService().getCustomersWithAbandonedCartOutBound();

		sendMail(customerList, cronJobModel);

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * sending report which has list of all customers with abandoned cart.
	 *
	 * @param customers customers whose cart have been abandoned.
	 */
	protected void sendMail(final Set<CustomerModel> customers, final AbandonedCartReportCronJobModel cronJobModel) {
		boolean isSuccessFile = false;

		final StringBuffer emailMessage = new StringBuffer();
		emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_ABANDONED_CART_REPORT
				+ SslCoreConstants.PARAGRAPH_TAG_END);

		final String SEND_REPORT_FROM_ADDRESS = Config
				.getParameter(SslCoreConstants.ABANDONED_CART_REPORT_MAIL_FROM_ADDRESS) != null
				&& !Config.getParameter(SslCoreConstants.ABANDONED_CART_REPORT_MAIL_FROM_ADDRESS).isEmpty()
						? Config.getParameter(SslCoreConstants.ABANDONED_CART_REPORT_MAIL_FROM_ADDRESS)
						: SslCoreConstants.ABANDONED_CART_REPORT_MAIL_FROM_ADDRESS_FALLBACK;

		String SEND_REPORT_TO_ADDRESS = cronJobModel.getEmailRecipients();
		if (SEND_REPORT_TO_ADDRESS == null) {
			SEND_REPORT_TO_ADDRESS = SslCoreConstants.ABANDONED_CART_REPORT_MAIL_FROM_ADDRESS_FALLBACK;
		}

		final List<String> toAddresses;
		final List<InternetAddress> internetAddressList = new ArrayList<InternetAddress>();
		final XSSFWorkbook workbook = new XSSFWorkbook();
		final XSSFSheet sheet = workbook.createSheet(ABANDONED_CART_REPORT_SHEET_NAME);

		try {

			final FileOutputStream fos = new FileOutputStream(dataDirectory + SslCoreConstants.UNRESOLVED_FILE_PATH
					+ SslCoreConstants.ABANDONED_CART_REPORT_FILE_NAME);
			if (customers != null && !customers.isEmpty()) {
				isSuccessFile = true;

				emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START
						+ SslCoreConstants.EMAIL_BODY_ABANDONED_CART_REPORT_SECOND_LINE
						+ SslCoreConstants.PARAGRAPH_TAG_END);

				int rowIndex = 0;
				for (final CustomerModel customer : customers) {
					final Row row = sheet.createRow(rowIndex++);
					int cellIndex = 0;
					row.createCell(cellIndex++).setCellValue(customer.getDisplayName());
					row.createCell(cellIndex++).setCellValue(customer.getMobile());
					row.createCell(cellIndex++).setCellValue(customer.getContactEmail());
				}

				workbook.write(fos);
			} else {
				emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START
						+ SslCoreConstants.EMAIL_BODY_ABANDONED_CART_REPORT_ALT_SECOND_LINE
						+ SslCoreConstants.PARAGRAPH_TAG_END);
			}

			emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_THANKS
					+ SslCoreConstants.PARAGRAPH_TAG_END);
			fos.close();

		} catch (final IOException exception) {
			LOG.error(exception.getMessage());
			LOG.error("File Writer error while creating updated products attachment");
		}
		try {
			final HtmlEmail htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail();
			if (SEND_REPORT_TO_ADDRESS.contains(",")) {
				toAddresses = Arrays.asList(SEND_REPORT_TO_ADDRESS.split(","));
				final Iterator<String> iterator = toAddresses.iterator();

				while (iterator.hasNext()) {
					final InternetAddress emailAddr = new InternetAddress(iterator.next());
					internetAddressList.add(emailAddr);
				}
				htmlEmail.setTo(internetAddressList);
				// Added fix for multiple to addresses
			} else {
				htmlEmail.addTo(SEND_REPORT_TO_ADDRESS);
			}

			htmlEmail.setSubject(SslCoreConstants.EMAIL_SUBJECT_ABANDONED_CART_REPORT);

			if (isSuccessFile) {

				final String filename = dataDirectory + SslCoreConstants.UNRESOLVED_FILE_PATH
						+ SslCoreConstants.ABANDONED_CART_REPORT_FILE_NAME;
				final DataSource source = new FileDataSource(filename);
				htmlEmail.attach(source, SslCoreConstants.ABANDONED_CART_REPORT_FILE_NAME,
						SslCoreConstants.ABANDONED_CART_REPORT_FILE_NAME);
			}

			htmlEmail.setHtmlMsg(emailMessage.toString());
			htmlEmail.setFrom(SEND_REPORT_FROM_ADDRESS);
			htmlEmail.send();
		} catch (final EmailException ex) {
			LOG.error("Email Exception");
			LOG.error(ex.getMessage());
		} catch (final AddressException ex) {
			LOG.error("Address Exception");
			LOG.error(ex.getMessage());
		}

	}

}
