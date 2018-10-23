package com.borngroup.ssl.core.jobs;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.ProductFeatureModel;
import de.hybris.platform.catalog.model.classification.ClassificationAttributeValueModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.dao.SSLProductAttrMappingLookupDao;
import com.borngroup.ssl.core.enums.ProductAttributeTypeEnum;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.SSLProductAttrMappingModel;
import com.borngroup.ssl.core.util.SSLClassificationUtil;
import com.opencsv.CSVWriter;
import com.pcm.perf.model.SSLProductAttrTemplateCronJobModel;

/**
 * Job that creates csv files for common and tech attribute.
 */
public class SSLProductAttrTemplateJob extends AbstractJobPerformable<SSLProductAttrTemplateCronJobModel> {

    /**
     * Constant for logging error message
     */
    private static final String UNABLE_TO_CLOSE_RESOURCE_EXCEPTION = "Unable to close resource. Exception: ";

    /**
     *
     */
    private static final String FOR_PRODUCT_WITH_CODE = " for Product with code ";

    /**
     * Constant for Staging
     */
    private static final String STAGING = "Staging";

    /**
     * Class Logger.
     */
    private static final Logger LOG = Logger.getLogger(SSLProductAttrTemplateJob.class);

    /**
     * Constant representing empty string.
     */
    private static final String EMPTY_STRING = "";

    /**
     * Constant for underscore
     */
    private static final String UNDERSCORE = "_";
    /**
     * String constant used in file name.
     */
    private static final String MDM_FILE_NAME = "MDM_DATA";

    /**
     * Basic Query to get categories joined with category product relation and approval status.
     */
    private static final String QUERY_STRING = "select distinct {c:pk} from {ApparelProduct as p join CategoryProductRelation as rel on {rel:target}={p:pk} join category as c on {c:pk}={rel:source} join articleapprovalStatus as app on {app:pk}={p:approvalStatus} } ";
    /**
     * Column Name for final file.
     */
    private static final String EXTERNAL_ID = "ExternalId";
    /**
     * Column Name for final file.
     */
    private static final String ATTRIBUTE_NAME = "AttributeProduct Name";
    /**
     * Column Name for final file.
     */
    private static final String ATTRIBUTE_VALUE = "Attribute Value";
    /**
     * Column Name for final file.
     */
    private static final String ATTRIBUTE_PARENT_NAME = "Attribute Parent Name";
    /**
     * Name of the File for common attributes of product.
     */
    private static final String COMMON_ATTR_FILE_NAME = STAGING + UNDERSCORE + "Common" + UNDERSCORE + MDM_FILE_NAME;
    /**
     * Name of the File for product features.
     */
    private static final String TECHNICAL_ATTR_FILE_NAME = STAGING + UNDERSCORE + "Tech" + UNDERSCORE + MDM_FILE_NAME;
    /**
     * Master SKU number, product attribute(MDM NAME) for common attributes file, special case.
     */
    private static final String MASTER_SKU_NUMBER_MDM = "Master SKU Number";
    /**
     * Base product, product attribute(Hybris name) for common attributes file, special case.
     */
    private static final String BASE_PRODUCT_HYBRIS_ATTR = "baseProduct";
    /**
     * Season code, product attribute(Hybris name) for common attributes file, special case.
     */
    private static final String SEASON_CODE_HYBRIS_ATTR = "seasonCode";
    /**
     * Genders, Apparel product attribute(Hybris name) for common attributes file, special case.
     */
    private static final String GENDERS_HYBRIS_ATTR = "genders";

    /**
     * DOT
     */
    private static final String DOT = ".";

    /**
     * Default approval statuses to consider,if none provided.
     */
    private static final String DEFAULT_APPROVAL_STATUS = "approved, unapproved, check, enriched";
    private static final String STYLE_CODE = "Style code";
    private static final String PRIMARY_CATEGORY = "SSL_PM_Primary Category";
    private static final String BASIC_DATA = "SSL_PM_Basic Data";
    private static final String CATEGORY_DATA_FILE_NAME = STAGING + UNDERSCORE + "Category" + UNDERSCORE + MDM_FILE_NAME;
    private static final String SECONDARY_CATEGORIES = "SSL_PM_Secondary_Categories";

    @Autowired(required = true)
    CatalogService catalogService;

    @Resource(name = "sslProductAttrMappingLookupDao")
    SSLProductAttrMappingLookupDao sslProductAttrMappingLookupDao;

    @Override
    public PerformResult perform(final SSLProductAttrTemplateCronJobModel cronJob) {
        LOG.debug("Starting SSLProductAttrTemplateJob ");
        PerformResult result = null;
        if (StringUtils.isNotEmpty(cronJob.getCatalogId())) {
            final CatalogVersionModel catalogVersion = SSLClassificationUtil.getCatalogVersion(cronJob.getCatalogId(), modelService,
                    catalogService);
            final String catalogVersionPk = catalogVersion.getPk().getLongValueAsString();
            List<CategoryModel> l3Categories = new ArrayList<CategoryModel>();
            final String approvalStatus = StringUtils.isNotEmpty(cronJob.getApprovalStatus()) ? cronJob.getApprovalStatus()
                    : DEFAULT_APPROVAL_STATUS;

            final Set<String> approvalStatusSet = SSLClassificationUtil.getHashSetInLowerCaseForString(approvalStatus);
            final Set<String> categoriesToExport = SSLClassificationUtil.getHashSetForString(cronJob.getCategoryIds());
            final Set<String> seasonCodesSet = SSLClassificationUtil.getHashSetInUpperCaseForString(cronJob.getSeasonCodes());

            final String query = SSLClassificationUtil.buildSearchQuery(QUERY_STRING, catalogVersionPk, cronJob.getLimitedExport(),
                    SSLClassificationUtil.hashSetToSqlInCsvString(categoriesToExport),
                    SSLClassificationUtil.hashSetToSqlInCsvString(approvalStatusSet),
                    SSLClassificationUtil.hashSetToSqlInCsvString(seasonCodesSet));

            if (StringUtils.isNotEmpty(query)) {
                final SearchResult<CategoryModel> searchResult = flexibleSearchService.search(query);
                l3Categories = searchResult.getResult();
            }
            result = processAllCategories(l3Categories, cronJob, catalogVersion, approvalStatusSet);
        } else {
            LOG.error("Product Catalog id cannot be empty");
            result = new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }
        return result;
    }

    /**
     * Method to write product categorical and common atttributes for all the categories configured for cron job.
     *
     * @param l3Categories
     * @param cronJob
     * @param catalogVersion
     * @param approvalStatusSet
     * @return PerformResult
     */
    private PerformResult processAllCategories(final List<CategoryModel> l3Categories, final SSLProductAttrTemplateCronJobModel cronJob,
            final CatalogVersionModel catalogVersion, final Set<String> approvalStatusSet) {
        LOG.debug("Generating products attribute data for " + l3Categories.size() + " category");
        PerformResult result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        if (CollectionUtils.isNotEmpty(l3Categories)) {
            try {
				final List<MediaModel> targetFiles = new ArrayList<MediaModel>();
                final int count = l3Categories.size();
                for (int i = 1; i <= count; i++) {
                    final CategoryModel category = l3Categories.get(i - 1);
                    LOG.debug("Starting generating products attribute data for " + category.getCode() + " category");

                    final Set<String> seasonCodesSet = SSLClassificationUtil.getHashSetInUpperCaseForString(cronJob.getSeasonCodes());
                    // generating product features data File
                    genOrAppendToTechAttrFileForCategory(category, seasonCodesSet, approvalStatusSet, TECHNICAL_ATTR_FILE_NAME,
                            cronJob.getCreatedDateFilter());
                    // generating product attribute data File
                    genOrAppendToCommonAttrFileForCategory(category, seasonCodesSet, approvalStatusSet, COMMON_ATTR_FILE_NAME,
                            cronJob.getCreatedDateFilter());
                    // generating categories data file
                    genOrAppendToCategoryDataFile(category, seasonCodesSet, approvalStatusSet, CATEGORY_DATA_FILE_NAME,
                            cronJob.getCreatedDateFilter());
                }
                SSLClassificationUtil.createTargetMediaForFile(catalogVersion, TECHNICAL_ATTR_FILE_NAME, targetFiles, modelService,
                        flexibleSearchService);
                SSLClassificationUtil.createTargetMediaForFile(catalogVersion, COMMON_ATTR_FILE_NAME, targetFiles, modelService,
                        flexibleSearchService);
                SSLClassificationUtil.createTargetMediaForFile(catalogVersion, CATEGORY_DATA_FILE_NAME, targetFiles, modelService,
                        flexibleSearchService);
                if (CollectionUtils.isNotEmpty(targetFiles)) {
                    clearPreviousMedias(cronJob.getTargetSheets());
                    cronJob.setTargetSheets(targetFiles);
                    modelService.save(cronJob);
                }
            } catch (final Exception exception) {
                LOG.error("Creation of csv files is failed with error: ", exception);
                result = new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
            }
        } else {
            LOG.info("No Product found for given category code '" + cronJob.getCategoryIds() + "', approval status id '"
                    + cronJob.getApprovalStatus() + "', season code '" + cronJob.getSeasonCodes() + "' in '" + cronJob.getCatalogId()
                    + "'");
        }
        return result;
    }

    /**
     * Method to create or append product category data to the sheet
     *
     * @param category
     * @param seasonCodes
     * @param approvalStatusSet
     * @param categoryDataFileName
     * @param dateFilter
     */
    private void genOrAppendToCategoryDataFile(final CategoryModel category, final Set<String> seasonCodes,
            final Set<String> approvalStatusSet, final String categoryDataFileName, final Date dateFilter) {
        LOG.debug("Inside method genOrAppendToCategoryDataFile()");
        final File file = SSLClassificationUtil.getFileInTempDirForCode(categoryDataFileName);
        CSVWriter writer = null;
        try {
            if (file.exists()) {
				try (final FileWriter fileWriter = new FileWriter(file, true);) {
					writer = new CSVWriter(fileWriter);
				}
			} else {
				try (final FileWriter fileWriter = new FileWriter(file);) {
					writer = new CSVWriter(fileWriter);
				}
                createTableHeaderCategoryData(writer);
            }
            // update file data
            updateFileForCategoryData(category, writer, seasonCodes, approvalStatusSet, dateFilter);
        } catch (final IOException e) {
            LOG.error("Error creating Category data attribute file :" + e.getLocalizedMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (final IOException e) {
                    LOG.error(UNABLE_TO_CLOSE_RESOURCE_EXCEPTION + e.getLocalizedMessage());
                }
            }
        }

    }

    /**
     * Method to update product category data to sheet
     *
     * @param category
     * @param writer
     * @param seasonCodes
     * @param approvalStatusSet
     * @param dateFilter
     */
    private void updateFileForCategoryData(final CategoryModel category, final CSVWriter writer, final Set<String> seasonCodes,
            final Set<String> approvalStatusSet, final Date dateFilter) {
        LOG.debug("Inside method updateFileForCategoryData()");
        LOG.debug("Iterating over products for category code " + category.getCode());
        for (final ProductModel product : category.getProducts()) {
            final boolean seasonCodeMatch = isSeasonCodeMatched(seasonCodes, product);
            final boolean approvalStatusMatch = isApprovalStatusMatch(approvalStatusSet, product);
            if (approvalStatusMatch && (CollectionUtils.isEmpty(seasonCodes) || seasonCodeMatch)
                    && (null == dateFilter || SSLClassificationUtil.isDateFilterMatched(product, dateFilter))) {
                LOG.debug("Reading category data for product " + product.getCode());
                populateCategoryDataRow(writer, product);
            }
        }
    }

    /**
     * Method to filter product based on season code, if value for season code is passed in cronjob
     *
     * @param seasonCodes
     * @param product
     */
    private boolean isSeasonCodeMatched(final Set<String> seasonCodes, final ProductModel product) {
        boolean seasonCodeMatch = false;
        if (CollectionUtils.isNotEmpty(seasonCodes) && StringUtils.isNotEmpty(product.getSeasonCode())) {
            for (final String seasonCode : seasonCodes) {
                if (seasonCode.contains(product.getSeasonCode().toUpperCase())) {
                    seasonCodeMatch = true;
                    break;
                }
            }
        }
        return seasonCodeMatch;
    }

    /**
     * Method to populate product category data to sheet
     *
     * @param writer
     * @param product
     */
    private void populateCategoryDataRow(final CSVWriter writer, final ProductModel product) {
        String primaryCategoryString = null;
        final StringJoiner secondoryCategoriesList = new StringJoiner(",");
        // fetching product primary and secondary categories
        for (final CategoryModel category : product.getSupercategories()) {
            if (!(category instanceof ClassificationClassModel)) {
                if (StringUtils.isEmpty(primaryCategoryString)) {
                    primaryCategoryString = category.getCode();
                } else {
                    secondoryCategoriesList.add(category.getCode());
                }
            }
        }

        final Set<String> styleCodes = new HashSet<>();
        for (final VariantProductModel variant : product.getVariants()) {
            styleCodes.add(variant.getStyleCode());
        }
        for (final String styleCode : styleCodes) {
            final String[] rowData = { product.getCode(), styleCode, primaryCategoryString, secondoryCategoriesList.toString() };
            writeRowToCsv(writer, rowData);
        }
    }

    /**
     * @param writer
     */
    private void createTableHeaderCategoryData(final CSVWriter writer) {
        LOG.debug("Inside method createTableHeaderCategoryData()");
        // TableHeader style
        final String[] rowData = { EXTERNAL_ID, STYLE_CODE, PRIMARY_CATEGORY, SECONDARY_CATEGORIES };
        writeRowToCsv(writer, rowData);
    }

    /**
     * @param targetFiles
     */
    private void clearPreviousMedias(final List<MediaModel> targetFiles) {
        LOG.debug("Clearing previously created medias.");
        for (final MediaModel targetFile : targetFiles) {
            modelService.remove(targetFile);
        }

    }

    /**
     * Method to generate or append to product features file (Tech Attributes).
     *
     * @param category {@link CategoryModel}
     * @param seasonCodes HashSet of season codes (in upper case).
     * @param approvalStatusSet Set of Approval status codes
     * @param dateFilter
     */
    private void genOrAppendToTechAttrFileForCategory(final CategoryModel category, final Set<String> seasonCodes,
            final Set<String> approvalStatusSet, final String mediaCode, final Date dateFilter) {
        LOG.debug("Inside method generateTechAttrFileForCategory()");
        final File file = SSLClassificationUtil.getFileInTempDirForCode(mediaCode);
        CSVWriter writer = null;
        try {
            if (file.exists()) {
				try (final FileWriter fileWriter = new FileWriter(file, true);) {
					writer = new CSVWriter(fileWriter);
				}
            } else {
				try (final FileWriter fileWriter = new FileWriter(file);) {
					writer = new CSVWriter(fileWriter);
				}
				createTableHeaderTechAttr(writer);
            }

            createFileDataTechAttr(category, writer, seasonCodes, approvalStatusSet, dateFilter);
            writer.close();
        } catch (final IOException e) {
            LOG.error("Error creating Tech attribute file for category " + category.getCode() + "Exception: " + e.getLocalizedMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (final IOException e) {
                    LOG.error(UNABLE_TO_CLOSE_RESOURCE_EXCEPTION + e.getLocalizedMessage());
                }
            }

        }
    }

    /**
     * Method to generate header for product features File, Tech Attr.
     *
     * @param writer
     */
    private void createTableHeaderTechAttr(final CSVWriter writer) {
        LOG.debug("Inside method createTableHeaderTechAttr()");
        // TableHeader style
        final String[] rowData = { EXTERNAL_ID, STYLE_CODE, ATTRIBUTE_NAME, ATTRIBUTE_PARENT_NAME, ATTRIBUTE_VALUE };
        writeRowToCsv(writer, rowData);
    }

    /**
     * Method to generate product feature data for a given category and append to file using writer.
     *
     * @param category {@link CategoryModel}.
     * @param writer
     * @param approvalStatusSet Set of Approval status codes
     * @param dateFilter
     */
    private void createFileDataTechAttr(final CategoryModel category, final CSVWriter writer, final Set<String> seasonCodes,
            final Set<String> approvalStatusSet, final Date dateFilter) {
        LOG.debug("Inside method createFileDataTechAttr()");

        final List<SSLProductAttrMappingModel> productAttrMappingList = fetchProductAttrMappingListByType(ProductAttributeTypeEnum.CATEGORY,
                Boolean.TRUE);

        if (!productAttrMappingList.isEmpty()) {
            final Map<String, SSLProductAttrMappingModel> hybrisMethods = createAttrMapFromList(productAttrMappingList);
            LOG.debug("Map containing features and corresponding models :" + hybrisMethods);

            LOG.debug("Iterating over products for category code " + category.getCode());
            for (final ProductModel product : category.getProducts()) {
                // filtering product based on season code and approval status, if value for season code is passed in cronjob
                final boolean seasonCodeMatch = isSeasonCodeMatched(seasonCodes, product);
                final boolean approvalStatusMatch = isApprovalStatusMatch(approvalStatusSet, product);
                // final boolean dateFilterMatch = isDateFilterMatched(product, dateFilter);

                if (approvalStatusMatch && (CollectionUtils.isEmpty(seasonCodes) || seasonCodeMatch)
                        && (null == dateFilter || SSLClassificationUtil.isDateFilterMatched(product, dateFilter))) {
                    populateProductFeatureRow(writer, product, hybrisMethods);
                }
            }
        } else {
            LOG.info("Hybris MDM attribute mapping list is empty - productAttrMappingList  ");
        }
    }

    /**
     * Method to check whether product approval status matches with given apporval status
     *
     * @param approvalStatusSet
     * @param product
     * @return boolean approvalStatusMatch
     */
    private boolean isApprovalStatusMatch(final Set<String> approvalStatusSet, final ProductModel product) {
        boolean approvalStatusMatch = false;
        final String approvalStatusForProduct = product.getApprovalStatus() != null ? product.getApprovalStatus().getCode() : null;
        if (StringUtils.isNotEmpty(approvalStatusForProduct) && approvalStatusSet.contains(approvalStatusForProduct.toLowerCase())) {
            approvalStatusMatch = true;
        }
        return approvalStatusMatch;
    }

    /**
     * Method to create map with key as hybris feature name and value as corresponding attribute mapping model
     *
     * @param productAttrMappingList
     * @return Map<String, SSLProductAttrMappingModel>
     */
    private Map<String, SSLProductAttrMappingModel> createAttrMapFromList(final List<SSLProductAttrMappingModel> productAttrMappingList) {
        final Map<String, SSLProductAttrMappingModel> hybrisMethods = new HashMap<>();
        for (final SSLProductAttrMappingModel attrMappingModel : productAttrMappingList) {
            hybrisMethods.put(attrMappingModel.getHybrisName().toUpperCase(), attrMappingModel);
        }
        return hybrisMethods;
    }

    /**
     * Method to fetch product attribute mapping list from dao based on attribute type
     *
     * @param productAttributeTypeEnum
     * @return List<SSLProductAttrMappingModel>
     */
    private List<SSLProductAttrMappingModel> fetchProductAttrMappingListByType(final ProductAttributeTypeEnum productAttributeTypeEnum,
            final Boolean onlyBaseAttrReq) {

        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(SSLProductAttrMappingModel.PRODUCTATTRIBUTETYPE, productAttributeTypeEnum);
        if (onlyBaseAttrReq.booleanValue()) {
            paramMap.put(SSLProductAttrMappingModel.ISVARIANTATTRIBUTE, Boolean.FALSE);
        }
        // Else all the attributes of that ProductAttributeType will be given.
        return sslProductAttrMappingLookupDao.getProductAttrMappingListByType(paramMap);
    }

    /**
     * Method to populate product features in file for a given product
     *
     * @param writer
     * @param product
     * @param hybrisMethods
     */
    private void populateProductFeatureRow(final CSVWriter writer, final ProductModel product,
            final Map<String, SSLProductAttrMappingModel> hybrisMethods) {

        LOG.debug("Iterating list of product features for product code " + product.getCode());
        for (final ProductFeatureModel featureModel : product.getFeatures()) {
            if (StringUtils.isNotEmpty(featureModel.getQualifier())) {
                final String feature = StringUtils.substringAfterLast(featureModel.getQualifier(), DOT).toUpperCase();
                LOG.debug("Searching feature '" + feature + "' in hybris MDM attribute mapping model");

                if (hybrisMethods.containsKey(feature)) {
                    LOG.debug("Feature '" + feature + "' present in hybris MDM attribute mapping model");
                    final SSLProductAttrMappingModel sslProductAttrMappingModel = hybrisMethods.get(feature);
                    LOG.debug("Writing product feature '" + feature + "' data to File");

                    // fetching attribute value
                    final Object value = getFeatureValueFromModel(featureModel);

                    final Set<String> styleCodes = new HashSet<>();
                    for (final VariantProductModel variant : product.getVariants()) {
                        styleCodes.add(variant.getStyleCode());
                    }
                    for (final String styleCode : styleCodes) {
                        final String[] rowData = { product.getCode(), styleCode, sslProductAttrMappingModel.getMdmName(),
                                sslProductAttrMappingModel.getParentName(), value == null ? StringUtils.EMPTY : value.toString() };
                        writeRowToCsv(writer, rowData);
                    }
                }
            }
        }
    }

    /**
     * Method to add primary category row for product in sheet
     *
     * @param writer
     * @param product
     */
    private void addPrimaryCategoryRowToSheet(final CSVWriter writer, final ProductModel product) {
        CategoryModel primaryCategory = null;
        String categoryString = null;
        // Adding product primary category to sheet
        for (final CategoryModel category : product.getSupercategories()) {
            if (!(category instanceof ClassificationClassModel)) {
                primaryCategory = category;
                categoryString = primaryCategory.getCode();
                break;
            }
        }
        final String[] row = { product.getCode(), product.getStyleCode(), PRIMARY_CATEGORY, BASIC_DATA, categoryString };
        writeRowToCsv(writer, row);
    }

    /**
     * Method to get value of the feature from feature model
     *
     * @param featureModel
     * @return Object feature value
     */
    private Object getFeatureValueFromModel(final ProductFeatureModel featureModel) {
        Object value = null;
        if (featureModel.getValue() != null) {
            if (featureModel.getValue() instanceof ClassificationAttributeValueModel) {
                value = ((ClassificationAttributeValueModel) featureModel.getValue()).getCode();
            } else {
                value = featureModel.getValue();
            }
        }
        return value;
    }

    /**
     * Method to generate file with common attributes of product.
     *
     * @param category {@link CategoryModel}
     * @param seasonCodes Set of Season codes in UpperCase
     * @param approvalStatusSet Set of Approval status codes
     * @param dateFilter
     */
    private void genOrAppendToCommonAttrFileForCategory(final CategoryModel category, final Set<String> seasonCodes,
            final Set<String> approvalStatusSet, final String mediaCode, final Date dateFilter) {
        LOG.debug("Creating Common Attribute File.");
        final File file = SSLClassificationUtil.getFileInTempDirForCode(mediaCode);
        CSVWriter writer = null;
        try {
            if (file.exists()) {
				try (final FileWriter fileWriter = new FileWriter(file, true);) {
				writer = new CSVWriter(fileWriter);
				}
            } else {
				try (final FileWriter fileWriter = new FileWriter(file);) {
				writer = new CSVWriter(fileWriter);
				}
                createTableHeaderCommonAttr(writer);
            }
            createFileDataCommonAttr(category, seasonCodes, writer, approvalStatusSet, dateFilter);
        } catch (final IOException e) {
            LOG.error("Error creating Tech attribute file for category " + category.getCode());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (final IOException e) {
                    LOG.error(UNABLE_TO_CLOSE_RESOURCE_EXCEPTION + e.getLocalizedMessage());
                }
            }
        }

    }

    /**
     * Method to create header for common Attribute File.
     *
     * @param writer
     */
    private void createTableHeaderCommonAttr(final CSVWriter writer) {
        // TableHeader style
        final String[] rowData = { EXTERNAL_ID, STYLE_CODE, ATTRIBUTE_NAME, ATTRIBUTE_PARENT_NAME, ATTRIBUTE_VALUE };
        writeRowToCsv(writer, rowData);
    }

    /**
     * Method to Create data for products in a given l3 category in common attribute File.
     *
     * @param category {@link CategoryModel}
     * @param seasonCodes Set of Season Codes in upperCase.
     * @param writer
     * @param approvalStatusSet Set of Approval Statuses.
     * @param dateFilter
     */
    private void createFileDataCommonAttr(final CategoryModel category, final Set<String> seasonCodes, final CSVWriter writer,
            final Set<String> approvalStatusSet, final Date dateFilter) {
        LOG.debug("Writing data for Category " + category.getCode());
        final List<ProductModel> products = category.getProducts();

        final List<SSLProductAttrMappingModel> baseAttributeMappings = fetchProductAttrMappingListByType(ProductAttributeTypeEnum.COMMON,
                Boolean.TRUE);

        final List<SSLProductAttrMappingModel> variantAttributeMappings = fetchProductAttrMappingListByType(ProductAttributeTypeEnum.COMMON,
                Boolean.FALSE);

        for (final ProductModel product : products) {
            // filtering product based on season code and approval status, if value for season code is passed in cronjob
            final boolean seasonCodeMatch = isSeasonCodeMatched(seasonCodes, product);

            final boolean approvalStatusMatch = isApprovalStatusMatch(approvalStatusSet, product);

            if (approvalStatusMatch && (CollectionUtils.isEmpty(seasonCodes) || seasonCodeMatch)
                    && (null == dateFilter || SSLClassificationUtil.isDateFilterMatched(product, dateFilter))) {
                LOG.debug("Writing product attribute data for product " + product.getCode());

                LOG.debug("Adding primary category row to sheet");
                addPrimaryCategoryRowToSheet(writer, product);
                writeProductCommonAttrData(product, baseAttributeMappings, writer);
                writeVariantCommonAttrData(product, variantAttributeMappings, writer);
            }
        }
    }

    /**
     * Method to create data of variants recursively.
     *
     * @param product
     * @param variantAttributeMappings
     * @param writer
     */
    private void writeVariantCommonAttrData(final ProductModel product, final List<SSLProductAttrMappingModel> variantAttributeMappings,
            final CSVWriter writer) {
        if (product instanceof ApparelStyleVariantProductModel) {
            // Do Nothing just return, as we don't want data for Size Variants.
        } else {
            LOG.debug("Fetching product variants for product " + product.getCode());
            final Collection<VariantProductModel> variants = product.getVariants();
            for (final VariantProductModel variant : variants) {
                writeProductCommonAttrData(variant, variantAttributeMappings, writer);
                // Just add a recursion call to include Size
                // variants.Change the exiting condition too to
                // ApparelSizeVariantProductModel above.
            }
        }
    }

    /**
     * Method to write common attribute data for a product.
     *
     * @param product
     * @param attributeMappings
     * @param writer
     */
    private void writeProductCommonAttrData(final ProductModel product, final List<SSLProductAttrMappingModel> attributeMappings,
            final CSVWriter writer) {
        String externalID;
        boolean isVariantProduct = false;
        if (product instanceof VariantProductModel) {
            final VariantProductModel variantProduct = (VariantProductModel) product;
            final ProductModel baseProduct = variantProduct.getBaseProduct();
            externalID = baseProduct.getCode();
            isVariantProduct = true;
        } else {
            externalID = product.getCode();
        }
        writeProductCommonAttrAsRows(product, attributeMappings, writer, isVariantProduct, externalID);
    }

    /**
     * Method to create row for required attributes of product.
     *
     * @param product
     * @param attributeMappings
     * @param writer
     * @param isVariantProduct
     * @param externalID
     */
    private void writeProductCommonAttrAsRows(final ProductModel product, final List<SSLProductAttrMappingModel> attributeMappings,
            final CSVWriter writer, final boolean isVariantProduct, final String externalID) {
        for (final SSLProductAttrMappingModel attributeMapping : attributeMappings) {
            final String attrValString = getAttrValForProduct(product, attributeMapping, isVariantProduct, externalID);

            final String[] rowData = { externalID, product.getStyleCode(), attributeMapping.getMdmName(), attributeMapping.getParentName(),
                    attrValString };
            writeRowToCsv(writer, rowData);
        }
    }

    /**
     * @param product
     * @param attributeMapping
     * @param isVariantProduct
     * @param externalID
     * @return Method returns a string, which is value of the given attribute for a product.
     */
    private String getAttrValForProduct(final ProductModel product, final SSLProductAttrMappingModel attributeMapping,
            final boolean isVariantProduct, final String externalID) {
        String attrValString = EMPTY_STRING;
        try {
            if (attributeMapping.getMdmName().equalsIgnoreCase(MASTER_SKU_NUMBER_MDM)
                    || attributeMapping.getHybrisName().equalsIgnoreCase(BASE_PRODUCT_HYBRIS_ATTR)) {
                attrValString = isVariantProduct ? externalID : EMPTY_STRING;
            } else if (attributeMapping.getHybrisName().equalsIgnoreCase(SEASON_CODE_HYBRIS_ATTR)
                    && (product instanceof VariantProductModel)) {
                final VariantProductModel variant = (VariantProductModel) product;
                final ProductModel baseProduct = variant.getBaseProduct();
                attrValString = baseProduct.getSeasonCode();
            } else if (attributeMapping.getHybrisName().equalsIgnoreCase(GENDERS_HYBRIS_ATTR)) {
                ApparelProductModel apparelProduct = null;
                if (product instanceof VariantProductModel) {
                    apparelProduct = (ApparelProductModel) ((VariantProductModel) product).getBaseProduct();
                } else {
                    apparelProduct = (ApparelProductModel) product;
                }
                final Object attrVal = apparelProduct.getGenders();
                attrValString = collectionToStringOfPk(attrVal);
            } else {
                final String hybrisName = attributeMapping.getHybrisName();
                Object attrVal = null;
                if (hybrisName != null && !hybrisName.isEmpty() && !hybrisName.equals("")) {
                    attrVal = PropertyUtils.getProperty(product, hybrisName);
                }

                if (attrVal == null) {
                    attrValString = EMPTY_STRING;
                } else if (Collection.class.isAssignableFrom(attrVal.getClass())) {
                    attrValString = collectionToStringOfPk(attrVal);
                } else if (HybrisEnumValue.class.isAssignableFrom(attrVal.getClass())) {
                    attrValString = ((HybrisEnumValue) attrVal).getCode();
                } else if (attrVal instanceof ItemModel) {
                    final ItemModel item = (ItemModel) attrVal;
                    attrValString = item.getPk().toString();
                } else {
                    attrValString = attrVal.toString();
                }
            }
        } catch (final IllegalAccessException e) {
            LOG.warn("IllegalAccess to attribute " + attributeMapping.getHybrisName() + FOR_PRODUCT_WITH_CODE + product.getCode()
                    + " Exception occurred: " + e.getLocalizedMessage());
        } catch (final InvocationTargetException e) {
            LOG.warn("InvocationTargetException for attribute " + attributeMapping.getHybrisName() + FOR_PRODUCT_WITH_CODE
                    + product.getCode() + " Exception occurred: " + e.getLocalizedMessage());
        } catch (final NoSuchMethodException e) {
            LOG.debug("NoSuchMethodException for attribute " + attributeMapping.getHybrisName() + FOR_PRODUCT_WITH_CODE + product.getCode()
                    + " Exception occurred: " + e.getLocalizedMessage());
        } catch (final Exception e) {
            LOG.error("ERROR OCCURED!! for attribute " + attributeMapping.getHybrisName() + FOR_PRODUCT_WITH_CODE + product.getCode()
                    + " Exception occurred: " + e.getLocalizedMessage());
        }
        return attrValString;
    }

    /**
     * @param rowData
     * @param writer
     */
    private void writeRowToCsv(final CSVWriter writer, final String[] rowData) {
        writer.writeNext(rowData);
    }

    /**
     * @param attrVal
     * @return String
     */
    private String collectionToStringOfPk(final Object attrVal) {
        final Collection<?> values = (Collection<?>) attrVal;
        String returnStr = "";
        final StringJoiner stringJoiner = new StringJoiner(",");
        for (final Object val : values) {
            if (HybrisEnumValue.class.isAssignableFrom(val.getClass())) {
                stringJoiner.add(((HybrisEnumValue) val).getCode());
            } else if (val instanceof ItemModel) {
                final ItemModel item = (ItemModel) val;
                stringJoiner.add(item.getPk().toString());
            } else {
                stringJoiner.add(val.toString());
            }

        }
        returnStr = stringJoiner.toString();
        return returnStr;
    }

}