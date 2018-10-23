/**
 *
 */
package com.borngroup.ssl.core.jobs;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.mediaconversion.MediaConversionService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.mail.MailUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;

import com.borngroup.ssl.core.constants.SslCoreConstants;

/**
 * @author gurkiratmohain
 *
 */
public class SslMediaConversionCronJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(SslMediaConversionCronJob.class);

    public static final String FAILED_MEDIA_CONTAINER_REPORT_SHEET_NAME = "Failed Media Container Report";

    private static final String MEDIAS_MEDIACONTAINER_JOIN = " medias ON medias.p_mediacontainer = mediacontainer.PK where mediacontainer.p_catalogversion = '";

    private static String dataDirectory = Config.getParameter("HYBRIS_DATA_DIR");

    @Resource
    private MediaConversionService mediaConversionService;

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource
    private ConfigurationService configurationService;

    @Resource
    protected CatalogVersionService catalogVersionService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private ProductService productService;

    @Override
    public PerformResult perform(final CronJobModel arg0) {

        final Map<MediaContainerModel, String> failedContainerList = new LinkedHashMap();
        LOG.info("Media conversion cronjob starts");
        int mediasSize = 0;

        final String sql0 = "SELECT count(1) FROM conversiongroups item_t0 JOIN mediaconttypeformats item_t1 ON  item_t0.PK  =  item_t1.SourcePK  JOIN mediaformat item_t2 ON  item_t1.TargetPK  =  item_t2.PK  WHERE ( item_t0.p_code  = '"
                + SslCoreConstants.CONVERSION_GROUP_CODE + "')";
        final List<String> mediaFormats = jdbcTemplate.queryForList(sql0, String.class);
        if (CollectionUtils.isNotEmpty(mediaFormats)) {
            mediasSize = Integer.parseInt(mediaFormats.get(0)) + 1;
        }
        LOG.info("Conversion formats count : " + mediasSize);
        final CatalogVersionModel onlineCatalogVersion = catalogVersionService.getCatalogVersion("sslProductCatalog", "Online");
        final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion("sslProductCatalog", "Staged");
        final String mediaContainerTable = Config.getParameter(SslCoreConstants.Media_Container);
        final String mediasTable = Config.getParameter(SslCoreConstants.Medias);
        LOG.info("Creating Backup Table for Medias");
        final String sql1 = "truncate table " + mediasTable;
        jdbcTemplate.update(sql1);
        LOG.info("Medias Table Backup" + mediasTable);
        final String sql2 = "insert into " + mediasTable + "(select * from medias m where m.p_catalogversion in ('" + catalogVersion.getPk()
                + "'))";
        jdbcTemplate.update(sql2);
        LOG.info("Creating Backup Table for Media Container");
        final String sql3 = "truncate table " + mediaContainerTable;
        jdbcTemplate.update(sql3);
        LOG.info("Media Container Table Backup" + mediaContainerTable);
        final String sql4 = "insert into " + mediaContainerTable + "(select * from mediacontainer mc where mc.p_catalogversion in ('"
                + catalogVersion.getPk() + "'))";
        jdbcTemplate.update(sql4);
        LOG.info("Cronjob working for all products in database in Backup Table");
        final String sql5 = new StringBuffer().append("SELECT mediacontainer.PK FROM ").append(mediaContainerTable)
                .append(" mediacontainer LEFT JOIN ").append(mediasTable).append(MEDIAS_MEDIACONTAINER_JOIN).append(catalogVersion.getPk())
                .append("' GROUP BY mediacontainer.PK having count(distinct( medias.p_mediaFormat )) != ").append(mediasSize)
                .append(" UNION SELECT mediacontainer.PK FROM ").append(mediaContainerTable).append(" mediacontainer LEFT JOIN ")
                .append(mediasTable).append(MEDIAS_MEDIACONTAINER_JOIN).append(catalogVersion.getPk())
                .append("' GROUP BY mediacontainer.PK having count(medias.p_mediaFormat ) > ").append(mediasSize).toString();
        LOG.info("Query to get media container count which do not have 11 distinct media" + sql5);
        final List<String> mediaContainers = jdbcTemplate.queryForList(sql5, String.class);
        LOG.info("mediaContainers count : " + mediaContainers.size());

        final String sql6 = "SELECT p.p_galleryimages from products p where p.p_catalogversion = '" + catalogVersion.getPk()
                + "' and p.p_galleryimages is not null";
        LOG.info("Query to get all media container count which are not null" + sql6);
        final List<String> products = jdbcTemplate.queryForList(sql6, String.class);
        LOG.info("Products count : " + products.size());

        for (final String product : products) {
            LOG.info("Current Product: " + product);
            final String[] medias = product.split(",");
            final List<String> galleryImages = new ArrayList<>();
            for (final String temp : medias) {
                if (0 != temp.length() && !temp.contains("#")) {
                    galleryImages.add(temp);
                }
            }
            LOG.info("Gallery Images count : " + galleryImages.size());
            for (final String container : galleryImages) {
                if (mediaContainers.contains(container)) {
                    LOG.info("Processing Container : " + container);
                    final String sql7 = new StringBuffer().append("Update ").append(mediaContainerTable)
                            .append(" mc2 set modifiedts = (select Sysdate from dual) where mc2.PK='").append(container).append("'")
                            .toString();
                    LOG.info("Query for Updating Mediacontainer with Modified Time" + sql7);
                    LOG.info("Updating Mediacontainer with Modified Time");
                    jdbcTemplate.update(sql7);
                    final String sql8 = new StringBuffer().append("select m.p_qualifier from ").append(mediaContainerTable)
                            .append(" m where m.pk = '").append(container).append("'").toString();
                    LOG.info("Query for finding qualifer for media container" + sql8);
                    final List<String> temp = jdbcTemplate.queryForList(sql8, String.class);
                    String mediaName = "";
                    if (CollectionUtils.isNotEmpty(temp)) {
                        mediaName = temp.get(0);
                    }
                    if (mediaName.equals("")) {
                        LOG.info("Skipping container " + container + " due to empty mediaName.");
                        continue;
                    }
                    final String sql9 = new StringBuffer().append("SELECT item_t0.PK FROM ").append(mediasTable)
                            .append(" item_t0 WHERE item_t0.p_code  like NVL('").append(mediaName)
                            .append("','123TestnotUsing')||'%' and item_t0.p_catalogversion =  ").append(catalogVersion.getPk()).toString();
                    LOG.info("Query for finding media for mediacontainer qualifier Staged" + sql9);
                    final List<String> mediaModelsStaged = jdbcTemplate.queryForList(sql9, String.class);
                    LOG.info("MediaModels list size : " + mediaModelsStaged.size());
                    if (mediaModelsStaged.size() <= 11) {
                        for (final String media : mediaModelsStaged) {
                            LOG.info("Processing Media : " + media);
                            final String sql10 = new StringBuffer().append("UPDATE ").append(mediasTable)
                                    .append(" medias SET medias.p_mediaContainer = '").append(container).append("' WHERE medias.pk = '")
                                    .append(media).append("'").toString();
                            LOG.info("Query for setting mediacontainer for medias" + sql10);
                            LOG.info("Updating Media " + media + "with container" + container);
                            jdbcTemplate.update(sql10);
                        }

                        final String sql11 = new StringBuffer().append("SELECT item_t0.PK FROM ").append(mediasTable)
                                .append(" item_t0 WHERE item_t0.p_code  like NVL('").append(mediaName)
                                .append("','123TestnotUsing')||'%' and item_t0.p_catalogversion !=  ").append(catalogVersion.getPk())
                                .toString();
                        LOG.info("Query for finding media for mediacontainer qualifier Online" + sql11);
                        final List<String> mediaModelsOnline = jdbcTemplate.queryForList(sql11, String.class);
                        LOG.info("MediaModels list size : " + mediaModelsOnline.size());
                        if (mediaModelsOnline.size() > 11) {
                            LOG.info("Skipping container " + container + " as Number of medias (Online) are greater than 11.");
                            continue;
                        }
                        for (final String media : mediaModelsOnline) {
                            LOG.info("Processing Media : " + media + "setting container NULL");
                            final String sql12 = new StringBuffer().append("UPDATE ").append(mediasTable)
                                    .append(" medias SET medias.p_mediaContainer =  null  WHERE medias.pk = '").append(media).append("'")
                                    .toString();
                            LOG.info("Query for setting mediacontainer for medias as NULL" + sql12);
                            jdbcTemplate.update(sql12);
                        }
                    } else {
                        LOG.info("Skipping container " + container + " container not found in media containers.");
                        continue;
                    }
                }
            }
        }
        final String query = "UPDATE medias m set m.p_mediacontainer=( select mo.p_mediacontainer from " + mediasTable
                + " mo where mo.pk=m.pk and mo.p_catalogversion in ('" + catalogVersion.getPk() + "')) where m.p_catalogversion in ('"
                + catalogVersion.getPk() + "')";
        jdbcTemplate.update(query);
        LOG.info("Query to update media from backup table to original" + query);

        final String sql13 = new StringBuffer()
                .append("SELECT mediacontainer.PK FROM mediacontainer LEFT JOIN medias ON medias.p_mediacontainer = mediacontainer.PK where mediacontainer.p_catalogversion = '")
                .append(catalogVersion.getPk()).append("' GROUP BY mediacontainer.PK having count(distinct( medias.p_mediaFormat )) != ")
                .append(mediasSize)
                .append(" UNION SELECT mediacontainer.PK FROM mediacontainer LEFT JOIN medias ON medias.p_mediacontainer = mediacontainer.PK where mediacontainer.p_catalogversion = '")
                .append(catalogVersion.getPk()).append("' GROUP BY mediacontainer.PK having count(distinct( medias.p_mediaFormat )) > ")
                .append(mediasSize).toString();
        LOG.info("Query to get count of media containers which doesn't have 11 medias attached, after finding medias from db: " + sql13);
        final List<String> mediaContiners = jdbcTemplate.queryForList(sql13, String.class);
        LOG.info("Count of media containers which doesn't have 11 medias attached, after finding medias from db: " + mediaContiners.size());
        for (final String mediaContainer : mediaContiners) {
            final StringBuilder query2 = new StringBuilder();
            query2.append("SELECT {mc.PK} from {" + MediaContainerModel._TYPECODE + " AS mc} ");
            query2.append("where {mc.PK} = '" + mediaContainer + "'");

            LOG.info("flexible query to get media container with pk " + mediaContainer + ": " + query2.toString());
            final FlexibleSearchQuery searchQuery2 = new FlexibleSearchQuery(query2.toString());
            searchQuery2.setResultClassList(Arrays.asList(MediaContainerModel.class));
            final SearchResult searchResult2 = getFlexibleSearchService().search(searchQuery2);
            final List<MediaContainerModel> model = searchResult2.getResult();
            LOG.info("Number of items in list of containers with pk " + mediaContainer + ": " + model.size());
            try {
                if (CollectionUtils.isNotEmpty(model)) {
                    final MediaContainerModel MediaContainer = model.get(0);
                    LOG.info("Deleting converted medias and Converting them");
                    if (CollectionUtils.isNotEmpty(MediaContainer.getMedias())) {
                        getMediaConversionService().deleteConvertedMedias(MediaContainer);
                        modelService.save(MediaContainer);
                        if (null != MediaContainer.getMedias()) {
                            if (MediaContainer.getMedias().size() > 1) {
                                final List<MediaModel> mediaModels = (List<MediaModel>) MediaContainer.getMedias();
                                final int size = MediaContainer.getMedias().size();

                                for (int i = 1; i < size; i++) {
                                    if (null != mediaModels.get(i)) {
                                        modelService.remove(mediaModels.get(i));
                                    }
                                }

                            }
                        }
                        modelService.save(MediaContainer);
                        getMediaConversionService().convertMedias(MediaContainer);
                        modelService.save(MediaContainer);
                    } else {
                        failedContainerList.put(model.get(0), SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_REASON_FIRST);
                    }
                }
            } catch (final Exception e) {
                LOG.info("Conversion failed for container : " + model);
                failedContainerList.put(model.get(0), SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_REASON_SECOND);
                LOG.debug(e.getMessage());
            }
        }

        final StringBuilder query3 = new StringBuilder();
        query3.append("SELECT {p.PK} from {" + ProductModel._TYPECODE + " AS p} ");
        query3.append(
                " where {p.catalogversion}='" + catalogVersion.getPk() + "' and {p.thumbnail} is null and {p.galleryimages} is not null");
        final FlexibleSearchQuery searchQuery3 = new FlexibleSearchQuery(query3.toString());
        LOG.info("Query to find product whose thumbnail is null but gallery images is/are not: " + query3.toString());
        searchQuery3.setResultClassList(Arrays.asList(ProductModel.class));
        final SearchResult searchResult3 = getFlexibleSearchService().search(searchQuery3);
        final List<ProductModel> productModels = searchResult3.getResult();
        LOG.info("Number of products whose thumbnail is null but gallery images is/are not: " + productModels.size());

        for (final ProductModel product : productModels) {
            LOG.info("product code " + product.getCode());
            final List<MediaContainerModel> galleryImagesList = product.getGalleryImages();
            for (final MediaContainerModel image : galleryImagesList) {
                if (!image.getQualifier().contains(SslCoreConstants.SEPARATOR_ALT)
                        && !image.getQualifier().contains(SslCoreConstants.SEPARATOR_SWATCH)) {
                    if (CollectionUtils.isNotEmpty(image.getMedias())) {
                        if (image.getMedias().size() == mediasSize) {
                            for (final MediaModel media : image.getMedias()) {
                                if (null != media.getMediaFormat().getQualifier()) {
                                    final List<MediaModel> otherImages = new ArrayList<MediaModel>();
                                    final List<MediaModel> thumbnails = new ArrayList<MediaModel>();
                                    otherImages.add(media);
                                    if (media.getMediaFormat().getQualifier().contains(SslCoreConstants.FORMAT_NORMAL)) {
                                        LOG.info("Setting and saving Thumbnail");
                                        product.setThumbnail(media);
                                        thumbnails.add(media);
                                    } else if (media.getMediaFormat().getQualifier().contains(SslCoreConstants.FORMAT_SMALL)) {
                                        LOG.info("Setting and saving Thumbnail");
                                        product.setThumbnail(media);
                                        thumbnails.add(media);

                                    }
                                    LOG.info("Setting and saving Other Images");
                                    product.setThumbnails(thumbnails);
                                    product.setOthers(otherImages);
                                    modelService.save(product);
                                    LOG.info("Product " + product.getCode() + " saved");
                                    break;
                                } else {
                                    LOG.info("No Qualifier for mediaFormat");
                                }
                            }
                        }
                    } else {
                        LOG.info("No medias in mediaContainer");
                        failedContainerList.put(image, SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_REASON_THIRD);
                    }
                }
            }
        }
        final String sql14 = "Update " + mediaContainerTable
                + " mc2 set modifiedts = (select Sysdate from dual) where mc2.p_qualifier in (SELECT mediacontainer.p_qualifier FROM mediacontainer LEFT JOIN medias ON medias.p_mediacontainer = mediacontainer.PK where mediacontainer.p_catalogversion = '"
                + onlineCatalogVersion.getPk()
                + "' GROUP BY mediacontainer.p_qualifier having count(distinct( medias.p_mediaFormat )) != 11 UNION SELECT mediacontainer.p_qualifier FROM  mediacontainer LEFT JOIN "
                + mediasTable + " medias ON medias.p_mediacontainer = mediacontainer.PK where mediacontainer.p_catalogversion = '"
                + onlineCatalogVersion.getPk()
                + "' GROUP BY mediacontainer.p_qualifier having count(medias.p_mediaFormat) > 11) and mc2.p_catalogversion='"
                + catalogVersion.getPk() + "'";
        LOG.info("Query for updating modified time for staged mediacontainer" + sql14);
        jdbcTemplate.update(sql14);

        sendMail(failedContainerList);

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    protected void sendMail(final Map<MediaContainerModel, String> mediaContainers) {
        boolean isSuccessFile = false;

        final StringBuffer emailMessage = new StringBuffer();
        emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_EMAIL_BODY
                + SslCoreConstants.PARAGRAPH_TAG_END);

        final String SEND_REPORT_FROM_ADDRESS = Config
                .getParameter(SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_REPORT_MAIL_FROM_ADDRESS) != null
                && !Config.getParameter(SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_REPORT_MAIL_FROM_ADDRESS).isEmpty()
                        ? Config.getParameter(SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_REPORT_MAIL_FROM_ADDRESS)
                        : SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_REPORT_MAIL_FROM_ADDRESS_FALLBACK;

        final String SEND_REPORT_TO_ADDRESS = Config.getParameter(SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_REPORT_MAIL_TO_ADDRESS);

        final List<String> toAddresses;
        final List<InternetAddress> internetAddressList = new ArrayList<InternetAddress>();
        final XSSFWorkbook workbook = new XSSFWorkbook();
        final XSSFSheet sheet = workbook.createSheet(FAILED_MEDIA_CONTAINER_REPORT_SHEET_NAME);
        int rowIndex = 0;
        int cellIndex = 0;
        Row row = sheet.createRow(rowIndex);
        row.createCell(cellIndex++).setCellValue("Media Container PK");
        row.createCell(cellIndex++).setCellValue("Media Container Name ");
        row.createCell(cellIndex++).setCellValue("Reason");
        rowIndex++;

        try {

            final FileOutputStream fos = new FileOutputStream(
                    dataDirectory + SslCoreConstants.UNRESOLVED_FILE_PATH + SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_FILE_NAME);
            if (mediaContainers.keySet() != null && !mediaContainers.keySet().isEmpty()) {
                isSuccessFile = true;

                emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START
                        + SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_EMAIL_BODY_SECOND_LINE + SslCoreConstants.PARAGRAPH_TAG_END);

                for (final MediaContainerModel mediaContainer : mediaContainers.keySet()) {
                    row = sheet.createRow(rowIndex++);
                    cellIndex = 0;
                    row.createCell(cellIndex++).setCellValue(mediaContainer.getPk().toString());
                    row.createCell(cellIndex++).setCellValue(mediaContainer.getQualifier());
                    row.createCell(cellIndex++).setCellValue(mediaContainers.get(mediaContainer));
                }

                workbook.write(fos);
            } else {
                row = sheet.createRow(rowIndex++);
                cellIndex = 0;
                row.createCell(cellIndex++).setCellValue("No Container Found in list");
                workbook.write(fos);
                emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START
                        + SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_EMAIL_BODY_ALT_SECOND_LINE + SslCoreConstants.PARAGRAPH_TAG_END);
            }

            emailMessage
                    .append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_THANKS + SslCoreConstants.PARAGRAPH_TAG_END);
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

            htmlEmail.setSubject(SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_EMAIL_SUBJECT);

            if (isSuccessFile) {

                final String filename = dataDirectory + SslCoreConstants.UNRESOLVED_FILE_PATH
                        + SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_FILE_NAME;
                final DataSource source = new FileDataSource(filename);
                htmlEmail.attach(source, SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_FILE_NAME,
                        SslCoreConstants.FAILED_MEDIA_CONTAINER_REPORT_FILE_NAME);
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

    public MediaConversionService getMediaConversionService() {
        return mediaConversionService;
    }

    public void setMediaConversionService(final MediaConversionService mediaConversionService) {
        this.mediaConversionService = mediaConversionService;
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    @Override
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(final ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

}
