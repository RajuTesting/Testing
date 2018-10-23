/**
 *
 */
package com.borngroup.ssl.core.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.mail.MailUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.review.service.SslCustomerReviewService;

/**
 * Cron Job: CronJob to generate a weekly report of all the Customer Product Reviews with Approval Status as Pending
 *
 * @author Nagarro-Dev
 *
 */
public class SSLCustomerProductReviewsReportCronJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(SSLCustomerProductReviewsReportCronJob.class);
    public static final String CUSTOMER_PRODUCT_REVIEW_REPORT_SHEET_NAME = "Customer Product Reviews Report";
    public static final String EMAIL_MESSAGE = "Please find attached a copy of the file conatining customers' product reviews of products.";
    public static final String EMAIL_SUBJECT = "Customer Product Reviews";
    public static final String CUSTOMER_PRODUCT_REVIEWS_REPORT_FILE_NAME = "CustomerProductReviewsReport.xlsx";

    public static final String SERIAL_NUMBER_COLUMN_NAME = "S.No.";
    public static final String PRODUCT_CATEGORY_COLUMN_NAME = "Product Category";
    public static final String PRODUCT_COLUMN_NAME = "Product";
    public static final String RATING_COLUMN_NAME = "Rating";
    public static final String TIME_CREATED_COLUMN_NAME = "Time created";
    public static final String USER_COLUMN_NAME = "User";
    public static final String HEADLINE_COLUMN_NAME = "Headline";
    public static final String COMMENT_COLUMN_NAME = "Comment";
    public static final String APPROVAL_STATUS_COLUMN_NAME = "Approval Status";
    public static final String SIMPLE_DATE_FORMAT = "DD-MM-YYYY HH:MM:SS";

    private static String dataDirectory = Config.getParameter("HYBRIS_DATA_DIR");

    private SslCustomerReviewService sslCustomerReviewService;

    /*
     * (non-Javadoc)
     * 
     * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel)
     */
    @Override
    public PerformResult perform(final CronJobModel arg0) {

        final List<CustomerReviewModel> listOfProductReviews = getSslCustomerReviewService().getWeeklyProductReviews();
        createCustomerProductReviewsReport(listOfProductReviews);
        sendEmail();
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    /**
     * Create an excel sheet report of weekly product reviews
     *
     * @param listOfProductReviews
     */
    private void createCustomerProductReviewsReport(final List<CustomerReviewModel> listOfProductReviews) {

        try {
            LOG.info("listOfProductReviews: " + listOfProductReviews.size());

            final XSSFWorkbook workbook = new XSSFWorkbook();
            final XSSFSheet sheet = workbook.createSheet(CUSTOMER_PRODUCT_REVIEW_REPORT_SHEET_NAME);
            final FileOutputStream fos = new FileOutputStream(dataDirectory + SslCoreConstants.UNRESOLVED_FILE_PATH
                    + CUSTOMER_PRODUCT_REVIEWS_REPORT_FILE_NAME);

            final Row row1 = sheet.createRow(0);
            int cellIndex = 0;

            row1.createCell(cellIndex++).setCellValue(SERIAL_NUMBER_COLUMN_NAME);
            row1.createCell(cellIndex++).setCellValue(PRODUCT_CATEGORY_COLUMN_NAME);
            row1.createCell(cellIndex++).setCellValue(PRODUCT_COLUMN_NAME);
            row1.createCell(cellIndex++).setCellValue(RATING_COLUMN_NAME);
            row1.createCell(cellIndex++).setCellValue(USER_COLUMN_NAME);
            row1.createCell(cellIndex++).setCellValue(TIME_CREATED_COLUMN_NAME);
            row1.createCell(cellIndex++).setCellValue(HEADLINE_COLUMN_NAME);
            row1.createCell(cellIndex++).setCellValue(COMMENT_COLUMN_NAME);
            row1.createCell(cellIndex++).setCellValue(APPROVAL_STATUS_COLUMN_NAME);
            if (!CollectionUtils.isEmpty(listOfProductReviews)) {
                populateDataInExcelSheet(listOfProductReviews, workbook, sheet);
            }
            workbook.write(fos);
        } catch (final IOException ex) {
            LOG.error("Error Message: " + ex.getMessage() + " Error cause: " + ex.getCause());
        }

    }

    /**
     * Populate weekly customers' products reviews in the excel sheet
     *
     * @param listOfProductReviews
     * @param workbook
     * @param sheet
     */
    private void populateDataInExcelSheet(final List<CustomerReviewModel> listOfProductReviews, final XSSFWorkbook workbook,
            final XSSFSheet sheet) {
        int rowIndex = 1;
        int cellIndex = 0;
        final CellStyle cellStyle = workbook.createCellStyle();
        final CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(SIMPLE_DATE_FORMAT));

        for (final CustomerReviewModel customerReview : listOfProductReviews) {
            final Row row = sheet.createRow(rowIndex++);
            cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(rowIndex - 1);
            row.createCell(cellIndex++).setCellValue(customerReview.getProduct().getDepartmentCode());
            row.createCell(cellIndex++).setCellValue(customerReview.getProduct().getCode());
            row.createCell(cellIndex++).setCellValue(customerReview.getRating().doubleValue());
            row.createCell(cellIndex++).setCellValue(customerReview.getUser().getUid());
            final Cell cell = row.createCell(cellIndex++);
            cell.setCellValue(customerReview.getCreationtime());
            cell.setCellStyle(cellStyle);
            row.createCell(cellIndex++).setCellValue(customerReview.getHeadline());
            row.createCell(cellIndex++).setCellValue(customerReview.getComment());
            row.createCell(cellIndex++).setCellValue(customerReview.getApprovalStatus().getCode());
        }

        for (int i = 0; i <= cellIndex; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    /**
     * Mail weekly customers' product reviews report
     */
    private void sendEmail() {

        final List<String> toAddresses;
        final List<InternetAddress> internetAddressList = new ArrayList<InternetAddress>();

        final StringBuffer emailMessage = new StringBuffer();

        try {

            final String SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_TO_ADDRESS = (SslCoreConstants.SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_TO_ADDRESS != null && !SslCoreConstants.SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_TO_ADDRESS
                    .isEmpty()) ? SslCoreConstants.SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_TO_ADDRESS
                    : SslCoreConstants.SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_TO_ADDRESS_FALLBACK;

            final String SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_FROM_ADDRESS = (SslCoreConstants.SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_FROM_ADDRESS != null && !SslCoreConstants.SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_FROM_ADDRESS
                    .isEmpty()) ? SslCoreConstants.SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_TO_ADDRESS
                    : SslCoreConstants.SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_FROM_ADDRESS_FALLBACK;

            final HtmlEmail htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail();
            if (SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_TO_ADDRESS.contains(",")) {
                toAddresses = Arrays.asList(SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_TO_ADDRESS.split(","));
                final Iterator<String> iterator = toAddresses.iterator();

                while (iterator.hasNext()) {
                    final InternetAddress emailAddr = new InternetAddress(iterator.next());
                    internetAddressList.add(emailAddr);
                }

                htmlEmail.setTo(internetAddressList);

            } else {
                htmlEmail.addTo(SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_TO_ADDRESS);
            }

            htmlEmail.setSubject(SslCoreConstants.CUSTOMER_PRODUCT_REVIEWS_EMAIL_SUBJECT);
            emailMessage.append(EMAIL_MESSAGE);

            final DataSource source = new FileDataSource(dataDirectory + SslCoreConstants.UNRESOLVED_FILE_PATH
                    + CUSTOMER_PRODUCT_REVIEWS_REPORT_FILE_NAME);
            htmlEmail.attach(source, CUSTOMER_PRODUCT_REVIEWS_REPORT_FILE_NAME, CUSTOMER_PRODUCT_REVIEWS_REPORT_FILE_NAME);

            htmlEmail.setSubject(EMAIL_SUBJECT);
            htmlEmail.setHtmlMsg(emailMessage.toString());
            htmlEmail.setFrom(SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_FROM_ADDRESS);
            htmlEmail.send();
            LOG.info("####  SSLCustomerProductReviewsReportCronJob: Customer's Product Reviews List Mailed ####");

            try {

                final File fileToBeDeleted = new File(dataDirectory + SslCoreConstants.UNRESOLVED_FILE_PATH,
                        CUSTOMER_PRODUCT_REVIEWS_REPORT_FILE_NAME);
                if (fileToBeDeleted.exists()) {
                    try (FileOutputStream fos = new FileOutputStream(fileToBeDeleted)) {
                        fos.flush();
                        fos.close();
                        fileToBeDeleted.delete();
                    } catch (final IOException e) {
                        LOG.error("Error while flushing");
                    }
                }

            } catch (final Exception ex) {
                LOG.error("Error Message: " + ex.getMessage() + " Error cause: " + ex.getCause());
            }

        } catch (final EmailException ex) {
            LOG.error("Email Exception");
            LOG.error("Error Message: " + ex.getMessage() + " Error cause: " + ex.getCause());

        } catch (final AddressException ex) {
            LOG.error("Address Exception");
            LOG.error("Error Message: " + ex.getMessage() + " Error cause: " + ex.getCause());

        }

    }

    /**
     * @return the sslCustomerReviewService
     */
    public SslCustomerReviewService getSslCustomerReviewService() {
        return sslCustomerReviewService;
    }

    /**
     * @param sslCustomerReviewService
     *        the sslCustomerReviewService to set
     */
    public void setSslCustomerReviewService(final SslCustomerReviewService sslCustomerReviewService) {
        this.sslCustomerReviewService = sslCustomerReviewService;
    }

}
