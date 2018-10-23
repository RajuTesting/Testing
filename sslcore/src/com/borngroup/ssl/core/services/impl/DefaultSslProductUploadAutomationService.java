/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaFolderModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.cronjob.model.LogFileModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.util.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.dao.ProductUploadAutomationDao;
import com.borngroup.ssl.core.data.ProductUploadValidationDTO;
import com.borngroup.ssl.core.events.ProductUploadAutomationEvent;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.services.ProductUploadAutomationService;

/*
 * Service class implementation to provide methods to product upload automation cronjobs
 *
 * mailId : ashish.sabal@nagarro.com
 *
 * @author ashishsabal
 *
 */
public class DefaultSslProductUploadAutomationService implements ProductUploadAutomationService {

    /** Log4j logger */
    private static final Logger LOG = Logger.getLogger(DefaultSslProductUploadAutomationService.class);

    /** HYBRIS_TEMP_DIR location */
    private static final String TEMP_DIR = Config.getParameter("HYBRIS_DATA_DIR");

    /** media dir location */
    private static final String MEDIA_SSL_HTTPS = "media.ssl.https";

    /** base product log file name */
    private static final String BASE_JOB_LOG_FILE_NAME = "SslBaseProductEnrichmentCronjob";

    /** variant product log file name */
    private static final String VARIANT_JOB_LOG_FILE_NAME = "SslVariantProductApprovalCronjob";

    /** log file name timestamp */
    private static final String LOG_FILE_NAME_TIMESTAMP = "yyyy-MM-dd.HHmmssmmm";

    /** log file dir name */
    private static final String LOG_DIR_NAME = "ProductUploads";

    private static final String BASE_SKU = "BASE SKU";
    private static final String PROD_SKU = "PRODUCT SKU";
    private static final String PROD_SKU_DATE = "PRODUCT SKU Created Date";
    private static final String BASE_SKU_STATUS = "Base SKU Status (Approved / Enriched / Unapproved)";
    private static final String PROD_SKU_STATUS = "Product SKU Status (Approved / Enriched / Unapproved)";
    private static final String REASON_FAILURE = "Reason for Approval Failure";
    private static final String XLSX = ".xlsx";

    /** Product Upload Automation Dao */
    @Resource(name = "productUploadAutomationDao")
    private ProductUploadAutomationDao productUploadAutomationDao;

    /** Base Site Service */
    @Resource(name = "baseSiteService")
    private BaseSiteService baseSiteService;

    /** Media Service */
    @Resource
    private MediaService mediaService;

    /** Event Service */
    @Resource(name = "eventService")
    private EventService eventService;

    /** Model Service */
    @Resource
    private ModelService modelService;

    /** Catalog Version Service */
    @Resource
    CatalogVersionService catalogVersionService;

    @Override
    public List<ApparelProductModel> getProductsWithStatus(final CatalogVersionModel catalogVersion,
            final ArticleApprovalStatus productStatus) {
        return this.getProductUploadAutomationDao().getProductsWithStatus(catalogVersion, productStatus);
    }

    @Override
    public String generateSendXlsReport(final List<ProductUploadValidationDTO> recordsList, final CronJobModel paramT, final String jobType,String reportType) {
        final Workbook workbook = new XSSFWorkbook();
        final Sheet sheet = workbook.createSheet("VariantProductList");
        // if (SslCoreConstants.BASE_PRODUCT_ENRICHMENT_JOB.equalsIgnoreCase(jobType)) {
        // isBase = true;
        // }
        createHeader(workbook, sheet);
        Row row = null;
        int startRowCount = 1;
        for (final ProductUploadValidationDTO productUploadValidationDTO : recordsList) {
            row = sheet.createRow(startRowCount);
            row.createCell(0).setCellValue(productUploadValidationDTO.getBaseSKU());
            row.createCell(1).setCellValue(productUploadValidationDTO.getProductSKU());
            row.createCell(2).setCellValue(productUploadValidationDTO.getProductSKUDateCreated());
            row.createCell(3).setCellValue(productUploadValidationDTO.getBaseSKUStatus());
            row.createCell(4).setCellValue(productUploadValidationDTO.getProdSKUStatus());
            row.createCell(5).setCellValue(
                    productUploadValidationDTO.getErrorType() + "[ " + productUploadValidationDTO.getMissingFields() + " ]");
            startRowCount++;
        }
        sheet.autoSizeColumn((short) 1);
        return sendErrorReport(paramT, jobType, workbook,reportType);
    }

    private void createHeader(final Workbook workbook, final Sheet sheet) {
        Row row = null;
        Cell cell = null;

        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue(BASE_SKU);

        cell = row.createCell(1);
        cell.setCellValue(PROD_SKU);

        cell = row.createCell(2);
        final CellStyle cellStyle = workbook.createCellStyle();
        final CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-mm-yy h:mm"));
        cell.setCellStyle(cellStyle);
        cell.setCellValue(PROD_SKU_DATE);

        cell = row.createCell(3);
        cell.setCellValue(BASE_SKU_STATUS);

        cell = row.createCell(4);
        cell.setCellValue(PROD_SKU_STATUS);

        cell = row.createCell(5);
        cell.setCellValue(REASON_FAILURE);

    }

    @Override
    public String sendErrorReport(final CronJobModel paramT, final String jobType, final Workbook workbook, String reportType) {
        final String logFileParentDir = TEMP_DIR + File.separatorChar + LOG_DIR_NAME;
        String fileName = "";
        if (SslCoreConstants.BASE_PRODUCT_ENRICHMENT_JOB.equalsIgnoreCase(jobType)) {
            fileName = BASE_JOB_LOG_FILE_NAME;
        } else {
            fileName = VARIANT_JOB_LOG_FILE_NAME;
        }
        final String xlsFileName = logFileParentDir + File.separatorChar + fileName +"_"+reportType+ "."
                + new SimpleDateFormat(LOG_FILE_NAME_TIMESTAMP).format(new Date()) + XLSX;
        this.addErrorRecordsToXlsFile(logFileParentDir, xlsFileName, workbook);

        // LogFileModel logFileModel = null;
        // try {
        // logFileModel = this.createLogFileMediaForUpload(tempLogFIle, SslCoreConstants.CRONJOB_LOG_STORE_LOCATION,
        // SslCoreConstants.CRONJOB_LOG_FORMAT, SslCoreConstants.ZIP_MIME_TYPE, paramT);
        // } catch (final IOException e) {
        // LOG.info("Error creating LOGs media");
        // }
        LOG.info("Successfully send report on path -->" + xlsFileName);
        //this.sendErrorReport(paramT.getCode(), paramT.getStartTime(), CronJobResult.SUCCESS, CronJobStatus.FINISHED, xlsFileName);
		return xlsFileName;
    }

    /**
     * method to add error records to file
     *
     * @return File with all error records
     */
    public File addErrorRecordsToXlsFile(final String folderpath, final String filePath, final Workbook workbook) {
        final File fileObj = new File(filePath);
        final File folderPath = new File(folderpath);
        try {
            if (!folderPath.exists()) {
                folderPath.mkdir();
            }
            fileObj.createNewFile();
            final FileOutputStream out = new FileOutputStream(fileObj);
            workbook.write(out);
            out.close();
        } catch (final Exception e) {
            LOG.error("Exception occured while genereating detailed Base/Variant excel" + e.getCause());
        }
        return fileObj;
    }

    /**
     * method will create media file with file object
     *
     * @param file
     * @param location
     * @param fileFormat
     * @param mimeType
     * @param paramT
     * @return LogFileModel
     * @throws IOException
     */
    public LogFileModel createLogFileMediaForUpload(final File file, final String location, final String fileFormat, final String mimeType,
            final CronJobModel paramT) throws IOException {
        final InputStream inStream = new FileInputStream(file);
        final LogFileModel logFileModel = modelService.create(LogFileModel.class);
        logFileModel.setOwner(paramT);
        final MediaFolderModel configFolderModel = mediaService.getFolder(location);
        logFileModel.setCode(file.getName());
        final MediaFormatModel format = mediaService.getFormat(fileFormat);
        logFileModel.setMediaFormat(format);
        logFileModel.setFolder(configFolderModel);
        logFileModel.setCatalogVersion(this.getCatalogVersionModel());
        logFileModel.setMime(mimeType);
        try {
            final MediaModel oldMediaModel = mediaService.getMedia(this.getCatalogVersionModel(), file.getName());
            modelService.remove(oldMediaModel.getPk());
        } catch (final Exception e) {
            LOG.info(file.getName() + " media not exist. Creating new Media" + e);
        }
        modelService.save(logFileModel);
        mediaService.setStreamForMedia(logFileModel, inStream);
        inStream.close();

        return logFileModel;
    }

    /**
     * method to send email containing error log file
     *
     * @param cronJobCode
     * @param startTime
     * @param cronJobResult
     * @param cronJobStatus
     * @param downloadURL
     */
    @Override
    public void sendErrorReport(final String cronJobCode, final Date startTime, final CronJobResult cronJobResult,
            final CronJobStatus cronJobStatus, final List<String> downloadURL) {
        final ProductUploadAutomationEvent productUploadErrorReportEvent = this.initializeEvent(cronJobCode, startTime, cronJobResult,
                cronJobStatus, downloadURL);
        if (null != productUploadErrorReportEvent) {
            this.getEventService().publishEvent(productUploadErrorReportEvent);
        } else {
            LOG.info("No base site available : Cannot publish mail send event.");
        }
    }

    /**
     * Event init method
     *
     * @param cronJobCode
     * @param startTime
     * @param cronJobResult
     * @param cronJobStatus
     * @param downloadURL
     * @return mail event
     */
    private ProductUploadAutomationEvent initializeEvent(final String cronJobCode, final Date startTime, final CronJobResult cronJobResult,
            final CronJobStatus cronJobStatus, final List<String> downloadURL) {
        final ProductUploadAutomationEvent productUploadAutomationEvent = new ProductUploadAutomationEvent();
        productUploadAutomationEvent.setCronJobCode(cronJobCode);
        productUploadAutomationEvent.setStartTime(startTime);
        productUploadAutomationEvent.setCronJobResult(cronJobResult);
        productUploadAutomationEvent.setCronJobStatus(cronJobStatus);
        productUploadAutomationEvent.setUrlForReport(downloadURL);

        final Collection<BaseSiteModel> sites = this.getBaseSiteService().getAllBaseSites();
        if (CollectionUtils.isNotEmpty(sites)) {
            for (final BaseSiteModel bs : sites) {
                LOG.info("Setting site : " + bs.getName() + " to Product Upload Automation Event.");
                productUploadAutomationEvent.setSite(bs);
            }
        } else {
            LOG.info("No site availbale to set in Product Upload Automation Event.");
            return null;
        }

        return productUploadAutomationEvent;
    }

    /**
     * Catalog version getter method
     *
     * @return staged product catalog version
     */
    private CatalogVersionModel getCatalogVersionModel() {
        return catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME, SslCoreConstants.CATALOG_VERSION);
    }

    /**
     * getter for ProductUploadAutomationDao
     *
     * @return the baseProductAttributionDao
     */
    private ProductUploadAutomationDao getProductUploadAutomationDao() {
        return productUploadAutomationDao;
    }

    /**
     * getter for BaseSiteService
     *
     * @return the baseSiteService
     */
    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    /**
     * getter for EventService
     *
     * @return the eventService
     */
    public EventService getEventService() {
        return eventService;
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.services.ProductUploadAutomationService#getStyleVariantsWithSizeVariantStatus(de.hybris.platform.catalog.model.CatalogVersionModel, de.hybris.platform.catalog.enums.ArticleApprovalStatus)
     */
    @Override
    public List<ApparelStyleVariantProductModel> getStyleVariantsWithSizeVariantStatus(
            CatalogVersionModel catalogVersion,
            ArticleApprovalStatus approvalStatus)
    {
        return this.getProductUploadAutomationDao().getStyleVariantsWithSizeVariantStatus(catalogVersion, approvalStatus);
    }
}
