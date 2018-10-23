/** * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Ankit			      NA						Base version : Override AbstractImpexRunnerTask to send email for product update
 *
 *
 */
package com.borngroup.ssl.core.dataimport.batch.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.AbstractImpexRunnerTask;
import de.hybris.platform.impex.model.cronjob.ImpExImportCronJobModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.impex.ImpExResource;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.mail.MailUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.borngroup.ssl.core.constants.SslCoreConstants;

public abstract class SslImpexRunnerTask extends AbstractImpexRunnerTask {

    private static final Logger LOG = Logger.getLogger(SslImpexRunnerTask.class);
    private static final String MEDIA_SSL_HTTPS = "media.ssl.https";
    private static final String DOWNLOAD_UNRESOLVED_LINES_TITTLE = "Impex import had UNRESOLVED lines. URL to download the same:";
    private static final String DOWNLOAD_LOG_FILES_TITTLE = "URL to download the LOG file :";
    private static final String DOWNLOAD_INPUT_FILE_TITTLE = "URL to download the INPUT ImpEx file :";
    private static final String JOB_CURRENT_STATUS_TITTLE = "Job Current Status:";
    private static final String JOB_RESULT_TITTLE = "Job Result :";
    private static final String END_TIME_TITTLE = "End Time:";
    private static final String START_TIME_TITTLE = "Start Time:";
    private static final String JOB_TYPE_TITTLE = "Job Type:";
    public static final String CRON_JOB_CODE_TITTLE = "Cron Job Code:";
    public static final String HOTFOLDER_IMPORT_TITTLE = "Hotfolder Import:";
    private static String DATA_DIR = Config.getParameter("HYBRIS_DATA_DIR");

    private SessionService sessionService;
    private ImportService importService;

    private int numberOfUnresolvedLines = 0;
    private int impexFileLineCount = 0;

    /**
     * @return the eventService
     */
    public EventService getEventService() {
        return eventService;
    }

    /**
     * @param eventService
     *        the eventService to set
     */
    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }

    private EventService eventService;

    @Override
    public BatchHeader execute(final BatchHeader header) throws FileNotFoundException {
        Assert.notNull(header);
        Assert.notNull(header.getEncoding());
        if (CollectionUtils.isNotEmpty(header.getTransformedFiles())) {
            final Session localSession = getSessionService().createNewSession();
            try {
                for (final File file : header.getTransformedFiles()) {
                    processFile(file, header.getEncoding());
                }
            } finally {
                getSessionService().closeSession(localSession);
            }
        }
        return header;
    }

    /**
     * Process an impex file using the given encoding
     *
     * @param file
     * @param encoding
     * @throws FileNotFoundException
     */
    @Override
    protected void processFile(final File file, final String encoding) throws FileNotFoundException {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            final ImportConfig config = getImportConfig();
            final ImpExResource resource = new StreamBasedImpExResource(fis, encoding);
            config.setScript(resource);
            // Keep Cronjob Instance alive
            config.setRemoveOnSuccess(false);

            final ImportResult importResult = getImportService().importData(config);
            // Process Product Price ImpEx File
            if (file.getName().contains(SslCoreConstants.PRODUCT_PRICE_CSV_NAME)) {
                sendEmail(
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.PRICE_IMPORT_FROM_ADDRESS)) ? Config.getParameter(SslCoreConstants.PRICE_IMPORT_FROM_ADDRESS)
                                : Config.getParameter(SslCoreConstants.PRODUCT_IMPORT_FROM_ADDRESS_FALLBACK),
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.PRICE_IMPORT_TO_ADDRESS)) ? Config
                                .getParameter(SslCoreConstants.PRICE_IMPORT_TO_ADDRESS) : Config
                                .getParameter(SslCoreConstants.PRODUCT_IMPORT_TO_ADDRESS_FALLBACK),
                        SslCoreConstants.PRICE_IMPORT_EMAIL_SUBJECT, prepareEmailBody(importResult, SslCoreConstants.PRICE_CSV_IMPORT_TYPE));
            }

            // Process Product Stock ImpEx File
            else if (file.getName().contains(SslCoreConstants.PRODUCT_STOCK_CSV_NAME)) {
                sendEmail(
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.STOCK_IMPORT_FROM_ADDRESS)) ? Config.getParameter(SslCoreConstants.STOCK_IMPORT_FROM_ADDRESS)
                                : Config.getParameter(SslCoreConstants.PRODUCT_IMPORT_FROM_ADDRESS_FALLBACK),
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.STOCK_IMPORT_TO_ADDRESS)) ? Config
                                .getParameter(SslCoreConstants.STOCK_IMPORT_TO_ADDRESS) : Config
                                .getParameter(SslCoreConstants.PRODUCT_IMPORT_TO_ADDRESS_FALLBACK),
                        SslCoreConstants.STOCK_IMPORT_EMAIL_SUBJECT, prepareEmailBody(importResult, SslCoreConstants.STOCK_CSV_IMPORT_TYPE));
            }

            // Process Product Discount ImpEx File
            else if (file.getName().contains(SslCoreConstants.PRODUCT_DISCOUNT_CSV_NAME)) {
                sendEmail(
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.DISCOUNT_IMPORT_FROM_ADDRESS)) ? Config.getParameter(SslCoreConstants.DISCOUNT_IMPORT_FROM_ADDRESS)
                                : Config.getParameter(SslCoreConstants.PRODUCT_IMPORT_FROM_ADDRESS_FALLBACK),
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.DISCOUNT_IMPORT_TO_ADDRESS)) ? Config
                                .getParameter(SslCoreConstants.DISCOUNT_IMPORT_TO_ADDRESS) : Config
                                .getParameter(SslCoreConstants.PRODUCT_IMPORT_TO_ADDRESS_FALLBACK),
                        SslCoreConstants.DISCOUNT_IMPORT_EMAIL_SUBJECT,
                        prepareEmailBody(importResult, SslCoreConstants.DISCOUNT_CSV_IMPORT_TYPE));
            }
            // Process Size Mapping ImpEx File
            else if (file.getName().contains(SslCoreConstants.SIZE_MAPPING_CSV_NAME)) {
                sendEmail(
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.SIZE_IMPORT_FROM_ADDRESS)) ? Config.getParameter(SslCoreConstants.SIZE_IMPORT_FROM_ADDRESS)
                                : Config.getParameter(SslCoreConstants.PRODUCT_IMPORT_FROM_ADDRESS_FALLBACK),
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.SIZE_IMPORT_TO_ADDRESS)) ? Config
                                .getParameter(SslCoreConstants.SIZE_IMPORT_TO_ADDRESS) : Config
                                .getParameter(SslCoreConstants.PRODUCT_IMPORT_TO_ADDRESS_FALLBACK),
                        SslCoreConstants.SIZE_IMPORT_EMAIL_SUBJECT, prepareEmailBody(importResult, SslCoreConstants.SIZE_CSV_IMPORT_TYPE));
            }
            // Process Brand Mapping ImpEx File
            else if (file.getName().contains(SslCoreConstants.BRAND_MAPPING_CSV_NAME)) {
                sendEmail(
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.BRAND_IMPORT_FROM_ADDRESS)) ? Config.getParameter(SslCoreConstants.BRAND_IMPORT_FROM_ADDRESS)
                                : Config.getParameter(SslCoreConstants.PRODUCT_IMPORT_FROM_ADDRESS_FALLBACK),
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.BRAND_IMPORT_TO_ADDRESS)) ? Config
                                .getParameter(SslCoreConstants.BRAND_IMPORT_TO_ADDRESS) : Config
                                .getParameter(SslCoreConstants.PRODUCT_IMPORT_TO_ADDRESS_FALLBACK),
                        SslCoreConstants.BRAND_IMPORT_EMAIL_SUBJECT, prepareEmailBody(importResult, SslCoreConstants.BRAND_CSV_IMPORT_TYPE));
            }
            // Process Color Mapping ImpEx File
            else if (file.getName().contains(SslCoreConstants.COLOR_MAPPING_CSV_NAME)) {
                sendEmail(
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.COLOR_IMPORT_FROM_ADDRESS)) ? Config.getParameter(SslCoreConstants.COLOR_IMPORT_FROM_ADDRESS)
                                : Config.getParameter(SslCoreConstants.PRODUCT_IMPORT_FROM_ADDRESS_FALLBACK),
                        StringUtils.isNotEmpty(Config.getParameter(SslCoreConstants.COLOR_IMPORT_TO_ADDRESS)) ? Config
                                .getParameter(SslCoreConstants.COLOR_IMPORT_TO_ADDRESS) : Config
                                .getParameter(SslCoreConstants.PRODUCT_IMPORT_TO_ADDRESS_FALLBACK),
                        SslCoreConstants.COLOR_IMPORT_EMAIL_SUBJECT, prepareEmailBody(importResult, SslCoreConstants.COLOR_CSV_IMPORT_TYPE));
            } else {
                if (importResult.isSuccessful()) {

                    if (file.getName().contains(SslCoreConstants.VARIANT_PRODUCT) || file.getName().contains(SslCoreConstants.BASE_PRODUCT)) {

                        findupdatedProducts(null, file);
                    }

                } else if (importResult.hasUnresolvedLines()) {
                    // If unresolved lines are present, remove unresolved lines
                    // from
                    // product impex and send list of updated products through
                    // email

                    if (file.getName().contains(SslCoreConstants.BASE_PRODUCT) || file.getName().contains(SslCoreConstants.VARIANT_PRODUCT)) {
                        final Set<String> unresolvedProducts = extractUnresolvedProductCodes(importResult);
                        findupdatedProducts(unresolvedProducts, file);
                    }
                } else if (importResult.isError()) {
                    sendMail(null, null);
                }
            }

        } catch (final IOException ex) {
            LOG.error("IO Exception caught : ex.Message", ex);
        }

        // End

        finally {
            IOUtils.closeQuietly(fis);
        }
    }

    /**
     * Function to prepare HTML Body for the email from cronjob importResult and input CSV file uploaded.
     *
     * @param importResult
     * @param csvUploadType
     * @return String: HTML email body
     */
    protected String prepareEmailBody(final ImportResult importResult, final String csvUploadType) {
        final ImpExImportCronJobModel cronjob = importResult.getCronJob();

        final StringBuffer emailBody = new StringBuffer();

        emailBody.append(SslCoreConstants.PARAGRAPH_TAG_START);
        emailBody.append(SslCoreConstants.HTML_BOLD_TAG_START);
        emailBody.append(HOTFOLDER_IMPORT_TITTLE);
        emailBody.append(csvUploadType);
        emailBody.append(SslCoreConstants.HTML_BOLD_TAG_END);
        emailBody.append(SslCoreConstants.PARAGRAPH_TAG_END);
        emailBody.append(CRON_JOB_CODE_TITTLE);
        emailBody.append(cronjob.getCode());
        emailBody.append(SslCoreConstants.HTML_BREAK_LINE_TAG);
        emailBody.append(JOB_TYPE_TITTLE);
        emailBody.append(cronjob.getJob().getCode());
        emailBody.append(SslCoreConstants.HTML_BREAK_LINE_TAG);
        emailBody.append(START_TIME_TITTLE);
        emailBody.append(cronjob.getStartTime().toString());
        emailBody.append(SslCoreConstants.HTML_BREAK_LINE_TAG);
        emailBody.append(END_TIME_TITTLE);
        emailBody.append(cronjob.getEndTime());
        emailBody.append(SslCoreConstants.HTML_BREAK_LINE_TAG);
        emailBody.append(SslCoreConstants.HTML_BREAK_LINE_TAG);
        emailBody.append(JOB_RESULT_TITTLE);
        emailBody.append(cronjob.getResult().getCode());
        emailBody.append(SslCoreConstants.HTML_BREAK_LINE_TAG);
        emailBody.append(JOB_CURRENT_STATUS_TITTLE);
        emailBody.append(cronjob.getStatus().getCode());
        emailBody.append(SslCoreConstants.HTML_BREAK_LINE_TAG);
        emailBody.append(SslCoreConstants.HTML_BREAK_LINE_TAG);
        if (null != cronjob.getJobMedia()) {
            emailBody.append(SslCoreConstants.PARAGRAPH_TAG_START);
            emailBody.append(DOWNLOAD_INPUT_FILE_TITTLE);
            emailBody.append(SslCoreConstants.HTML_BREAK_LINE_TAG);
            if (StringUtils.isNotEmpty(Config.getParameter(MEDIA_SSL_HTTPS))) {
                emailBody.append(Config.getParameter(MEDIA_SSL_HTTPS));
                emailBody.append("/");
            }
            emailBody.append(cronjob.getJobMedia().getDownloadURL());
            emailBody.append(SslCoreConstants.PARAGRAPH_TAG_END);
        }
        if (CollectionUtils.isNotEmpty(cronjob.getLogFiles())) {
            emailBody.append(SslCoreConstants.PARAGRAPH_TAG_START);
            emailBody.append(DOWNLOAD_LOG_FILES_TITTLE);
            emailBody.append(SslCoreConstants.HTML_BREAK_LINE_TAG);
            if (StringUtils.isNotEmpty(Config.getParameter(MEDIA_SSL_HTTPS))) {
                emailBody.append(Config.getParameter(MEDIA_SSL_HTTPS));
                emailBody.append("/");
            }
            emailBody.append(cronjob.getLogFiles().iterator().next().getDownloadURL());
            emailBody.append(SslCoreConstants.PARAGRAPH_TAG_END);
        }
        if (importResult.hasUnresolvedLines()) {
            emailBody.append(SslCoreConstants.PARAGRAPH_TAG_START);
            emailBody.append(DOWNLOAD_UNRESOLVED_LINES_TITTLE);
            emailBody.append(SslCoreConstants.HTML_BREAK_LINE_TAG);
            if (StringUtils.isNotEmpty(Config.getParameter(MEDIA_SSL_HTTPS))) {
                emailBody.append(Config.getParameter(MEDIA_SSL_HTTPS));
                emailBody.append("/");
            }
            emailBody.append(importResult.getUnresolvedLines().getDownloadURL());
            emailBody.append(SslCoreConstants.PARAGRAPH_TAG_END);
        }

        return emailBody.toString();
    }

    /**
     * Function to send an HTML email using the given params.
     *
     * @param reciepintList
     * @param from
     * @param subject
     * @param msgBody
     */
    protected void sendEmail(final String from, final String reciepintList, final String subject, final String msgBody) {
        try {
            final HtmlEmail htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail();
            List<String> ToAddresses;
            final List<InternetAddress> internetAddressList = new ArrayList<InternetAddress>();
            // Added fix for multiple to addresses
            if (reciepintList.contains(",")) {
                ToAddresses = Arrays.asList(reciepintList.split(","));
                final Iterator<String> it = ToAddresses.iterator();

                while (it.hasNext()) {
                    final InternetAddress emailAddr = new InternetAddress(it.next());
                    internetAddressList.add(emailAddr);
                }
                htmlEmail.setTo(internetAddressList);
                // Added fix for multiple to addresses
            } else {
                htmlEmail.addTo(reciepintList);
            }
            htmlEmail.setSubject(subject);
            htmlEmail.setHtmlMsg(msgBody);
            htmlEmail.setFrom(from);
            htmlEmail.send();
        } catch (final EmailException ex) {
            LOG.error("Email Exception", ex);
            LOG.error(ex.getMessage());
        } catch (final AddressException ex) {
            LOG.error("Address Exception", ex);
        }

    }

    // Function to extract product codes from unresolved lines file created by
    // out-of-box code
    /*
     * Params : * @importResult : The improtResult object returned by out of box import* Returns list of product codes
     */

    protected Set<String> extractUnresolvedProductCodes(final ImportResult importResult) {
        BufferedReader br = null;
        String line;

        boolean isSizeVariant = false;

        final Set<String> productCodeArray = new HashSet<String>();

        try {

            br = new BufferedReader(new FileReader((DATA_DIR + SslCoreConstants.UNRESOLVED_FILE_PATH + importResult.getUnresolvedLines()
                    .getLocation()).replace("\\", File.separator).replace("/", File.separator)));

            while ((line = br.readLine()) != null) {

                if (line.contains("ApparelSizeVariantProduct") && !line.contains("ApparelStyleVariantProduct")) {
                    isSizeVariant = true;

                }

                if (!line.isEmpty() && line.contains("unresolved")) {
                    final String[] lineArray = line.split(";");
                    numberOfUnresolvedLines = numberOfUnresolvedLines + 1;
                    if (lineArray.length > 1) {

                        final String productCode = lineArray[SslCoreConstants.PROD_CODE_COLUMN].replace(";", "");

                        if (productCode != null && productCode.contains(SslCoreConstants.SEPARATOR_UNDERSCORE)) {

                            productCodeArray.add(productCode.split(SslCoreConstants.SEPARATOR_UNDERSCORE)[0]);
                        } else {
                            productCodeArray.add(productCode);
                        }
                    }
                }

                if (!line.isEmpty() && line.contains(SslCoreConstants.IGNORE)) {
                    if (isSizeVariant) {
                        final String[] lineArray = line.split(";");
                        numberOfUnresolvedLines = numberOfUnresolvedLines + 1;
                        if (lineArray.length > 1) {

                            final String productCode = lineArray[SslCoreConstants.PROD_CODE_COLUMN].replace(";", "").replace(
                                    SslCoreConstants.IGNORE, "");

                            if (productCode != null && productCode.contains(SslCoreConstants.SEPARATOR_UNDERSCORE)) {

                                productCodeArray.add(productCode.split(SslCoreConstants.SEPARATOR_UNDERSCORE)[0]);
                            } else {

                                productCodeArray.add(productCode);
                            }

                        }

                    } else {
                        final String[] lineArray = line.split(SslCoreConstants.IGNORE);
                        numberOfUnresolvedLines = numberOfUnresolvedLines + 1;
                        if (lineArray.length > 1) {
                            productCodeArray.add(lineArray[SslCoreConstants.PROD_CODE_COLUMN].replace(";", ""));
                        }

                    }

                }
            }

        } catch (final IOException ex) {
            LOG.error("Exception while opening Unresolved lines file", ex);
        } finally {

            try {
                if (br != null) {
                    br.close();
                }
            } catch (final Exception ex) {
                LOG.error("Exception while closing buffered reader", ex);
            }

        }
        // numberOfUnresolvedLines = numberOfUnresolvedLines - 2; //Subtract
        // first blank row and header row
        return productCodeArray;
    }

    /*
     * Function to get list of updated products from system created impex by removing list of unresolved products
     *
     * Params
     *
     * @productCodeArray : List of product codes to be ignored*
     *
     * file : the impex file created by out-of-code*
     */
    public void findupdatedProducts(final Set<String> productCodeArray, final File file) {

        BufferedReader inReader = null;

        final boolean isVariantProduct = file.getName().contains(SslCoreConstants.VARIANT_PRODUCT) ? true : false;

        final Set<String> updatedProdCodesList = new HashSet<String>();
        final Set<String> updateFailedProdCodesList = new HashSet<String>();
        try {
            inReader = new BufferedReader(new FileReader(file));
            String row;
            while ((row = inReader.readLine()) != null) {
                final List<String> baseNVariantCode = getProductCodeFromImpexRow(row, isVariantProduct);
                if (CollectionUtils.isNotEmpty(baseNVariantCode)) {
                    final String baseProductCode = baseNVariantCode.get(0);
                    final String variantProductCode = baseNVariantCode.get(1);

                    if (productCodeArray == null) {
                        updatedProdCodesList.add(variantProductCode);
                    } else if (!productCodeArray.isEmpty() && !productCodeArray.contains(baseProductCode)) {
                        updatedProdCodesList.add(variantProductCode);
                    } else if (productCodeArray.contains(baseProductCode)) {
                        updateFailedProdCodesList.add(variantProductCode);
                    }
                }
                impexFileLineCount = impexFileLineCount + 1;

            }
            impexFileLineCount = impexFileLineCount - 8;

            if (impexFileLineCount == numberOfUnresolvedLines) {
                updatedProdCodesList.clear();
            }
        } catch (final FileNotFoundException e) {
            LOG.error("Impex file not found exception", e);
        } catch (final IOException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                if (inReader != null) {
                    inReader.close();
                }
            } catch (final Exception ex) {
                LOG.error(ex.getMessage());
            }
        }

        sendMail(updatedProdCodesList, updateFailedProdCodesList);
    }

    /*
     * Helper function to email product list
     */

    protected void sendMail(final Set<String> updatedProdCodesList, final Set<String> updateFailedProdCodesList) {
        boolean isProdUpdated = false;
        boolean isProdUpdatefailed = false;
        final StringBuffer emailMessage = new StringBuffer();
        emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_FIRST_LINE
                + SslCoreConstants.PARAGRAPH_TAG_END);

        final String PRODUCT_IMPORT_FROM_ADDRESS = Config.getParameter(SslCoreConstants.PRODUCT_IMPORT_FROM_ADDRESS) != null
                && !Config.getParameter(SslCoreConstants.PRODUCT_IMPORT_FROM_ADDRESS).isEmpty() ? Config
                .getParameter(SslCoreConstants.PRODUCT_IMPORT_FROM_ADDRESS) : SslCoreConstants.PRODUCT_IMPORT_FROM_ADDRESS_FALLBACK;

        final String PRODUCT_IMPORT_TO_ADDRESS = Config.getParameter(SslCoreConstants.PRODUCT_IMPORT_TO_ADDRESS) != null
                && !Config.getParameter(SslCoreConstants.PRODUCT_IMPORT_TO_ADDRESS).isEmpty() ? Config
                .getParameter(SslCoreConstants.PRODUCT_IMPORT_TO_ADDRESS) : SslCoreConstants.PRODUCT_IMPORT_TO_ADDRESS_FALLBACK;

        List<String> ToAddresses;
        final List<InternetAddress> internetAddressList = new ArrayList<InternetAddress>();

        try (FileWriter writer = new FileWriter(DATA_DIR + SslCoreConstants.UNRESOLVED_FILE_PATH
                + SslCoreConstants.UPLOADED_PRODUCTS_FILE_NAME)) {

            if (updatedProdCodesList != null && updatedProdCodesList.size() > 0) {
                isProdUpdated = true;
                emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_SECOND_LINE
                        + SslCoreConstants.PARAGRAPH_TAG_END);

                for (final String prodCode : updatedProdCodesList) {
                    writer.append(prodCode);

                    writer.append(SslCoreConstants.NEW_LINE_CHARACTER);

                }
            } else {
                emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_ALT_SECOND_LINE
                        + SslCoreConstants.PARAGRAPH_TAG_END);
            }

            if (updateFailedProdCodesList != null && updateFailedProdCodesList.size() > 0) {

                isProdUpdatefailed = true;
                try (FileWriter failedProdWriter = new FileWriter(DATA_DIR + SslCoreConstants.UNRESOLVED_FILE_PATH
                        + SslCoreConstants.UPLOADED_FAILED_PRODUCTS_FILE_NAME)) {

                    emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_ALT2_SECOND_LINE
                            + SslCoreConstants.PARAGRAPH_TAG_END);

                    for (final String failedProdCode : updateFailedProdCodesList) {
                        failedProdWriter.append(failedProdCode);

                        failedProdWriter.append(SslCoreConstants.NEW_LINE_CHARACTER);

                    }
                } catch (final Exception e) {

                    LOG.error("File Writer error while creating updated products attachment", e);
                }

            }
            emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_THIRD_LINE
                    + SslCoreConstants.PARAGRAPH_TAG_END);
            emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_THANKS
                    + SslCoreConstants.PARAGRAPH_TAG_END);

        } catch (final IOException e) {
            LOG.error("File Writer error while creating updated products attachment");
        }
        try {
            final HtmlEmail htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail(); // creates a mail instance with
                                                                                       // set mail properties read from
                                                                                       // project.properties
            // Added fix for multiple to addresses
            if (PRODUCT_IMPORT_TO_ADDRESS.contains(",")) {
                ToAddresses = Arrays.asList(PRODUCT_IMPORT_TO_ADDRESS.split(","));
                final Iterator<String> it = ToAddresses.iterator();

                while (it.hasNext()) {
                    final InternetAddress emailAddr = new InternetAddress(it.next());
                    internetAddressList.add(emailAddr);
                }
                htmlEmail.setTo(internetAddressList);
                // Added fix for multiple to addresses
            } else {
                htmlEmail.addTo(PRODUCT_IMPORT_TO_ADDRESS);
            }
            if (updatedProdCodesList != null && updatedProdCodesList.size() > 0) {
                htmlEmail.setSubject(updatedProdCodesList.size() + SslCoreConstants.EMAIL_SUBJECT);

            } else {
                htmlEmail.setSubject("No " + SslCoreConstants.EMAIL_SUBJECT);

            }

            if (isProdUpdated) {
                final String filename = DATA_DIR + SslCoreConstants.UNRESOLVED_FILE_PATH + SslCoreConstants.UPLOADED_PRODUCTS_FILE_NAME;
                final DataSource source = new FileDataSource(filename);
                htmlEmail.attach(source, SslCoreConstants.UPLOADED_PRODUCTS_FILE_NAME, SslCoreConstants.UPLOADED_PRODUCTS_FILE_NAME);
            }
            if (isProdUpdatefailed) {
                final String filename = DATA_DIR + SslCoreConstants.UNRESOLVED_FILE_PATH
                        + SslCoreConstants.UPLOADED_FAILED_PRODUCTS_FILE_NAME;
                final DataSource source = new FileDataSource(filename);
                htmlEmail.attach(source, SslCoreConstants.UPLOADED_FAILED_PRODUCTS_FILE_NAME,
                        SslCoreConstants.UPLOADED_FAILED_PRODUCTS_FILE_NAME);
            }

            htmlEmail.setHtmlMsg(emailMessage.toString());
            htmlEmail.setFrom(PRODUCT_IMPORT_FROM_ADDRESS);

            htmlEmail.send();
        } catch (final EmailException ex) {
            LOG.error("Email Exception", ex);
            LOG.error(ex.getMessage());
        } catch (final AddressException ex) {
            LOG.error("Address Exception", ex);
        }

    }

    /*
     * Helper function to extract product code from an impex row Param : impexRow Return : product code
     */
    protected List<String> getProductCodeFromImpexRow(final String impexRow, final boolean isVariantProduct) {

        List<String> productcodes = null;

        String colArray[];
        String productVariantCode = null;
        String baseProductCode = null;
        colArray = impexRow.split(";");

        if (impexRow.startsWith(";") && colArray.length > 1) {

            if (isVariantProduct) {

                productVariantCode = colArray[SslCoreConstants.PROD_SIZEVARIANT_CODE_COLUMN];
                baseProductCode = colArray[SslCoreConstants.PROD_CODE_COLUMN];

                if (baseProductCode != null && baseProductCode.contains("_")) {

                    baseProductCode = baseProductCode.split("_")[0];
                }

            } else {
                productVariantCode = colArray[SslCoreConstants.PROD_CODE_COLUMN];
                baseProductCode = colArray[SslCoreConstants.PROD_CODE_COLUMN];
            }
            productcodes = new ArrayList<String>();

            productcodes.add(0, baseProductCode);
            productcodes.add(1, productVariantCode);
        }

        /*
         * if (productCode != null && productCode.contains("_")) { productCode = colArray[SslCoreConstants.PROD_SIZEVARIANT_CODE_COLUMN]; }
         */

        return productcodes;
    }

    @Override
    public SessionService getSessionService() {
        return sessionService;
    }

    @Override
    @Required
    public void setSessionService(final SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public ImportService getImportService() {
        return importService;
    }

    @Override
    @Required
    public void setImportService(final ImportService importService) {
        this.importService = importService;
    }

    /**
     * Lookup method to return the import config *
     *
     * @return import config
     */
    @Override
    public abstract ImportConfig getImportConfig();

}
