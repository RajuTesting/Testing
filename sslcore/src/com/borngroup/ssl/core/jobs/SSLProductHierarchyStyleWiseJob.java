package com.borngroup.ssl.core.jobs;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.util.SSLClassificationUtil;
import com.opencsv.CSVWriter;
import com.pcm.perf.model.SSLProductHierarchyStyleWiseCronJobModel;

/**
 * @author pankajaggarwal
 *
 */
public class SSLProductHierarchyStyleWiseJob extends AbstractJobPerformable<SSLProductHierarchyStyleWiseCronJobModel> {

    private static final Logger LOG = Logger.getLogger(SSLClassificationAttrTemplateJob.class);

    private static final String L3_CATEGORY = "L3 Category";
    private static final String L2_CATEGORY = "L2 Category";
    private static final String L1_CATEGORY = "L1 Category";
    private static final String PRETTY_PROD_NAME = "Pretty_Prod_Name";
    private static final String MASTER_SKU = "Master_SKU";
    private static final String ALT_BRAND_DESC = "ALT_BRAND_DESC";
    private static final String APPROVAL = "Approval Status";
    private static final String STYLE = "Style #";
    private static final String QUERY_STRING = "select distinct {p:pk} from {ApparelProduct as p join CategoryProductRelation as rel on {rel:target}={p:pk} join category as c on {c:pk}={rel:source} join articleapprovalStatus as app on {app:pk}={p:approvalStatus} } ";
    private static final String DEFAULT_APPROVAL_STATUS = "approved, unapproved, check, enriched";
    private static final String FILE_NAME = "Style_Wise_Hierarchy";

    @Autowired(required = true)
    private CatalogService catalogService;

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel)
     */
    @Override
    public PerformResult perform(final SSLProductHierarchyStyleWiseCronJobModel cronJob) {
        LOG.debug("Starting SSLProductHierarchyStyleWiseJob ");
        PerformResult result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        if (StringUtils.isNotEmpty(cronJob.getCatalogId())) {
            final CatalogVersionModel catalogVersion = SSLClassificationUtil.getCatalogVersion(cronJob.getCatalogId(), modelService,
                    catalogService);
            final String catalogVersionPk = catalogVersion.getPk().getLongValueAsString();
            final String approvalStatus = StringUtils.isNotEmpty(cronJob.getApprovalStatus()) ? cronJob.getApprovalStatus()
                    : DEFAULT_APPROVAL_STATUS;

            final Set<String> approvalStatusSet = SSLClassificationUtil.getHashSetInLowerCaseForString(approvalStatus);
            final Set<String> categoriesToExport = SSLClassificationUtil.getHashSetForString(cronJob.getCategoryIds());
            final Set<String> seasonCodesSet = SSLClassificationUtil.getHashSetInUpperCaseForString(cronJob.getSeasonCodes());

            // Building products search query
            final String query = SSLClassificationUtil.buildSearchQuery(QUERY_STRING, catalogVersionPk, cronJob.getLimitedExport(),
                    SSLClassificationUtil.hashSetToSqlInCsvString(categoriesToExport),
                    SSLClassificationUtil.hashSetToSqlInCsvString(approvalStatusSet),
                    SSLClassificationUtil.hashSetToSqlInCsvString(seasonCodesSet));
            LOG.debug("Final Search query to be executed : " + query);

            List<ProductModel> products = new ArrayList<ProductModel>();
            if (StringUtils.isNotEmpty(query)) {
                final SearchResult<ProductModel> searchResult = flexibleSearchService.search(query);
                products = searchResult.getResult();
            }
            result = processResult(products, catalogVersion, cronJob, categoriesToExport);
        } else {
            LOG.error("Product Catalog id cannot be empty");
            result = new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }
        return result;
    }

    /**
     * Method to write product hierarchy and style code for all the categories configured for cron job.
     *
     * @param products
     * @param catalogVersion
     * @param cronJob
     * @param categoriesToExport
     * @return PerformResult
     */
    private PerformResult processResult(final List<ProductModel> products, final CatalogVersionModel catalogVersion,
            final SSLProductHierarchyStyleWiseCronJobModel cronJob, final Set<String> categoriesToExport) {
        PerformResult result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        final List<MediaModel> targetFiles = new ArrayList<MediaModel>();
        if (CollectionUtils.isNotEmpty(products)) {
            try {
                // generating category data sheet
                genOrUpdateSheetData(products, FILE_NAME, categoriesToExport, cronJob.getCreatedDateFilter());
                // creating media model target sheets for generated sheet
                SSLClassificationUtil.createTargetMediaForFile(catalogVersion, FILE_NAME, targetFiles, modelService, flexibleSearchService);
                if (CollectionUtils.isNotEmpty(targetFiles)) {
                    cronJob.setTargetSheets(targetFiles);
                    modelService.save(cronJob);
                }
            } catch (final Exception exception) {
                LOG.error("Creation of csv file template is failed with error: ", exception);
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
     * Method to create or append product hierarchy data to the sheet
     *
     * @param products
     * @param mediaCode
     * @param categoriesToExport
     * @param dateFilter
     */
    private void genOrUpdateSheetData(final List<ProductModel> products, final String mediaCode, final Set<String> categoriesToExport,
            final Date dateFilter) {
        LOG.debug("Inside method genOrUpdateSheetData()");
        final File file = SSLClassificationUtil.getFileInTempDirForCode(mediaCode);
        try {
            final CSVWriter writer = SSLClassificationUtil.getWriterForFile(file);

            // creating file header
            createTableHeader(writer);

            // updating sheet data
            updateSheetData(products, writer, categoriesToExport, dateFilter);
            writer.close();
        } catch (final IOException e) {
            LOG.error("Error creating category data file");
        }
    }

    /**
     * Method to create sheet header
     *
     * @param csvWriter
     */
    private void createTableHeader(final CSVWriter csvWriter) {
        final String[] rowData = { MASTER_SKU, STYLE, PRETTY_PROD_NAME, L1_CATEGORY, L2_CATEGORY, L3_CATEGORY, ALT_BRAND_DESC, APPROVAL };
        csvWriter.writeNext(rowData);
    }

    /**
     * Method to write product hierarchy data in the sheet
     *
     * @param products
     * @param csvWriter
     * @param categoriesToExport
     * @param dateFilter
     */
    private void updateSheetData(final List<ProductModel> products, final CSVWriter csvWriter, final Set<String> categoriesToExport,
            final Date dateFilter) {
        LOG.debug("Iterating over " + products.size() + " products");
        for (final ProductModel product : products) {
            if (null == dateFilter || SSLClassificationUtil.isDateFilterMatched(product, dateFilter)) {
                processProductCategories(product, csvWriter, categoriesToExport);
            }

        }
    }

    /**
     * Method to iterate over product super categories and write to file
     * 
     * @param product
     * @param csvWriter
     * @param categoriesToExport
     */
    private void processProductCategories(final ProductModel product, final CSVWriter csvWriter, final Set<String> categoriesToExport) {
        if (CollectionUtils.isNotEmpty(product.getSupercategories())) {
            for (final CategoryModel category : product.getSupercategories()) {
                if (!(category instanceof ClassificationClassModel)) {
                    if (categoriesToExport.contains(category.getCode())) {
                        writeCategoryDataToFile(csvWriter, product, category);
                    }
                }
            }
        }
    }

    /**
     * Method to write category data to file
     *
     * @param csvWriter
     * @param product
     * @param category
     */
    private void writeCategoryDataToFile(final CSVWriter csvWriter, final ProductModel product, final CategoryModel category) {

        final CategoryModel l2Category = SSLClassificationUtil.getSuperCategory(category);
        final CategoryModel l1Category = SSLClassificationUtil.getSuperCategory(l2Category);
        final String l2CategoryCode = l2Category != null ? l2Category.getCode() : StringUtils.EMPTY;
        final String l1CategoryCode = l1Category != null ? l1Category.getCode() : StringUtils.EMPTY;
        final String approvalStatusCode = product.getApprovalStatus() != null ? product.getApprovalStatus().getCode() : null;

        LOG.debug("Iterating over variants for product " + product.getCode());
        final Set<String> styleCodes = new HashSet<>();
        for (final VariantProductModel variant : product.getVariants()) {
            styleCodes.add(variant.getStyleCode());
        }
        for (final String styleCode : styleCodes) {
            final String[] rowData = { product.getCode(), styleCode, product.getName(Locale.ENGLISH), l1CategoryCode, l2CategoryCode,
                    category.getCode(), product.getBrandCode(), approvalStatusCode };
            csvWriter.writeNext(rowData);
        }
    }

}
