package com.borngroup.ssl.core.util;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.util.Config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.opencsv.CSVWriter;

/**
 * Utility Class : Contains common method for Classification cron job.
 * <p>
 * <p>
 * <p>
 * <p>
 * Created By : mayank.gupta@nagarro.com
 *
 * @author Ssl
 */
public class SSLClassificationUtil {

    /**
     * Message with media name when no media with given name exists and we create new one.
     */
    private static final String GET_MEDIA_INFO_MSG = "getMainMedia() : file does not exist hence creating new media..";

    /**
     * Message for media creatíon Exception.
     */
    private static final String EXCEPTION_MEDIA_MSG = "Exception during media saving";

    /**
     * Class Logger.
     */
    private static final Logger LOG = Logger.getLogger(SSLClassificationUtil.class);

    /**
     * Staged Catalog Version Qualifier.
     */
    private static final String STAGED = "Staged";

    private static final String HYBRIS_TEMP_PATH = "hybris.temp.path";
    private static final String FILE_SEPERATOR = "//";
    private static final String CSV = "csv";
    private static final String DOT = ".";

    /**
     * @param catalogVersion
     * @param mediaCode
     * @return MediaModel
     * @throws JaloBusinessException
     */
    public static MediaModel createMedia(final CatalogVersionModel catalogVersion, final String mediaCode, final File file,
            final ModelService modelService) throws JaloBusinessException {
        MediaModel mediaModel;
        mediaModel = modelService.create(MediaModel.class);
        mediaModel.setCode(mediaCode);
        mediaModel.setCatalogVersion(catalogVersion);
        modelService.save(mediaModel);
        final Media media = modelService.getSource(mediaModel);
        media.setFile(file);
        modelService.save(mediaModel);
        file.delete();

        return mediaModel;
    }

    /**
     * @param catalogVersion
     * @param mediaCode
     * @param file
     * @param targetFiles
     * @return MediaModel
     */
    public static MediaModel getTargetMedia(final CatalogVersionModel catalogVersion, final String mediaCode, final File file,
            final List<MediaModel> targetFiles, final ModelService modelService, final FlexibleSearchService flexibleSearchService) {
        MediaModel mediaModel = null;
        try {
            mediaModel = new MediaModel();
            mediaModel.setCode(mediaCode);
            mediaModel.setCatalogVersion(catalogVersion);
            mediaModel = flexibleSearchService.getModelByExample(mediaModel);
            modelService.remove(mediaModel);
            if (CollectionUtils.isNotEmpty(targetFiles) && targetFiles.contains(mediaModel)) {
                targetFiles.remove(mediaModel);
            }
            mediaModel = SSLClassificationUtil.createMedia(catalogVersion, mediaCode, file, modelService);
        } catch (final Exception ex) {
            try {

                LOG.info(LOG.getName() + GET_MEDIA_INFO_MSG);
                mediaModel = SSLClassificationUtil.createMedia(catalogVersion, mediaCode, file, modelService);

            } catch (final Exception exception) {
                LOG.error(EXCEPTION_MEDIA_MSG, exception);
            }

        }

        return mediaModel;
    }

    /**
     * @param catalogId
     * @return CatalogVersionModel
     */
    public static CatalogVersionModel getCatalogVersion(final String catalogId, final ModelService modelService,
            final CatalogService catalogService) {
        CatalogVersionModel catver = null;
        try {
            final CatalogModel catalog = catalogService.getCatalogForId(catalogId);
            final Set<CatalogVersionModel> catVersions = catalog.getCatalogVersions();
            if (!CollectionUtils.isEmpty(catVersions)) {
                for (final CatalogVersionModel catversion : catVersions) {
                    final String vesrion = catversion.getVersion();
                    if (StringUtils.equals(vesrion, STAGED)) {
                        catver = catversion;
                    }
                }
            }

        } catch (final UnknownIdentifierException exc) {
            final CatalogModel catalog = modelService.create(CatalogModel.class);
            catalog.setId(catalogId);
            catver = modelService.create(CatalogVersionModel.class);
            catver.setVersion(STAGED);
            catver.setCatalog(catalog);
            modelService.save(catver);
        }
        return catver;
    }

    /**
     * @param hashSet A set from which SQL IN type string is to be generated.
     * @return String to be used in sql or flexible search IN statement eg. ('approved', 'check').
     */
    public static String hashSetToSqlInCsvString(final Set<String> hashSet) {
        String sqlInCsvString = null;
        if (CollectionUtils.isNotEmpty(hashSet)) {
            final StringJoiner commaStrJoiner = new StringJoiner(", ");
            for (final String value : hashSet) {
                commaStrJoiner.add("'" + value + "'");
            }
            sqlInCsvString = " (" + commaStrJoiner.toString() + ") ";
        }
        return sqlInCsvString;
    }

    /**
     * This method just creates a File instance with path for file, it does not creates a new file on system.
     *
     * @param mediaCode
     * @return File instance for a csv file with name as mediaCode
     */
    public static File getFileInTempDirForCode(final String mediaCode) {
        final String tempPath = Config.getParameter(HYBRIS_TEMP_PATH);
        final String mediaName = mediaCode + DOT + CSV;
        return new File(tempPath + FILE_SEPERATOR + mediaName);
    }

    /**
     * Method to create media model target sheets for generated csv file
     *
     * @param catalogVersion
     * @param mediaCode
     * @param targetFiles
     */
    public static void createTargetMediaForFile(final CatalogVersionModel catalogVersion, final String mediaCode,
            final List<MediaModel> targetFiles, final ModelService modelService, final FlexibleSearchService flexibleSearchService) {
        final MediaModel targetMedia = getTargetMedia(catalogVersion, mediaCode, SSLClassificationUtil.getFileInTempDirForCode(mediaCode),
                targetFiles, modelService, flexibleSearchService);
        if (null != targetMedia) {
            targetFiles.add(targetMedia);
            LOG.info(LOG.getName() + " Successfully created target file with media id: " + targetMedia.getCode());
        } else {
            LOG.info(LOG.getName() + " Target Media is null for file : " + mediaCode);
        }
    }

    /**
     * Method to fetch category one level above for given category
     *
     * @param category
     * @return CategoryModel l1Category
     */
    public static CategoryModel getSuperCategory(final CategoryModel category) {
        CategoryModel superCategory = null;
        if ((null != category) && CollectionUtils.isNotEmpty(category.getSupercategories())) {
            for (final CategoryModel cat : category.getSupercategories()) {
                if (!(cat instanceof ClassificationClassModel)) {
                    superCategory = cat;
                    break;
                }
            }
        }
        return superCategory;
    }

    /**
     * Method to get csv file writer
     *
     * @param file
     * @return CSVWriter
     * @throws IOException
     */
    public static CSVWriter getWriterForFile(final File file) throws IOException {
        CSVWriter writer = null;
        if (file.exists()) {
            writer = new CSVWriter(new FileWriter(file, true));
        } else {
            writer = new CSVWriter(new FileWriter(file));
        }
        return writer;
    }

    /**
     * Method to create hashset from given string
     *
     * @param str
     * @return Set<String> setOfString
     */
    public static Set<String> getHashSetForString(final String str) {
        final Set<String> setOfString = StringUtils.isNotEmpty(str) ? new HashSet<String>(Arrays.asList(str.split("\\s*,\\s*"))) : null;
        return setOfString;
    }

    /**
     * Method to create hashset from given string
     *
     * @param str
     * @return Set<String> setOfString
     */
    public static Set<String> getHashSetInLowerCaseForString(final String str) {
        final Set<String> setOfString = StringUtils.isNotEmpty(str)
                ? new HashSet<String>(Arrays.asList(str.toLowerCase().split("\\s*,\\s*"))) : null;
        return setOfString;
    }

    /**
     * Method to create hashset from given string
     *
     * @param str
     * @return Set<String> setOfString
     */
    public static Set<String> getHashSetInUpperCaseForString(final String str) {
        final Set<String> setOfString = StringUtils.isNotEmpty(str)
                ? new HashSet<String>(Arrays.asList(str.toUpperCase().split("\\s*,\\s*"))) : null;
        return setOfString;
    }

    /**
     *
     * Method to build search query to find list of categories
     *
     * @param catalogVersionPk
     * @param limittedExport
     * @param categoryIds
     * @param approvalStatusIds
     * @param seasonCodes
     * @return String query
     */
    public static String buildSearchQuery(final String queryString, final String catalogVersionPk, final Boolean limittedExport,
            final String categoryIds, final String approvalStatusIds, final String seasonCodes) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(queryString);
        stringBuilder.append(" where {p:catalogversion}=" + catalogVersionPk + " and {c:catalogversion}=" + catalogVersionPk);
        if (BooleanUtils.isTrue(limittedExport)) {
            if (StringUtils.isNotEmpty(categoryIds)) {
                stringBuilder.append(" and {c:code} in " + categoryIds);
                if (StringUtils.isNotEmpty(approvalStatusIds)) {
                    stringBuilder.append(" and {app:code} in " + approvalStatusIds);
                }
                if (StringUtils.isNotEmpty(seasonCodes)) {
                    stringBuilder.append(" and {p:seasonCode} in " + seasonCodes);
                }
            } else {
                // setting string length to zero
                stringBuilder.setLength(0);
            }
        } else {
            if (StringUtils.isNotEmpty(approvalStatusIds)) {
                stringBuilder.append(" and {app:code} in " + approvalStatusIds);
            }
            if (StringUtils.isNotEmpty(seasonCodes)) {
                stringBuilder.append(" and {p:seasonCode} in " + seasonCodes);
            }
        }
        LOG.debug("Final Search query :" + stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * Method to check product creation date with given date filter
     *
     * @param product
     * @param dateFilter
     * @return boolean dateFilterMatch
     */
    public static boolean isDateFilterMatched(final ProductModel product, final Date dateFilter) {
        boolean dateFilterMatch = false;
        if (null != dateFilter) {
            if (product.getCreationtime().compareTo(dateFilter) >= 0) {
                dateFilterMatch = true;
            }
        }
        return dateFilterMatch;
    }

}
