package com.borngroup.ssl.core.cron;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.VirtualCategoryEntryModel;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.jalo.SyncItemCronJob;
import de.hybris.platform.catalog.jalo.synchronization.CatalogVersionSyncCronJob;
import de.hybris.platform.catalog.jalo.synchronization.CatalogVersionSyncJob;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.ProductFeatureModel;
import de.hybris.platform.catalog.model.SyncItemJobModel;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.catalog.model.classification.ClassificationAttributeValueModel;
import de.hybris.platform.catalog.synchronization.CatalogSynchronizationService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.Config;

/**
 * <p>
 * <p>
 * CronJob : Maps Virtual Category and products.
 * <p>
 * Created By : mayank.gupta@nagarro.com
 *
 * @author Ssl
 */
public class VirtualCategoryAndProductMappingCronJob extends AbstractJobPerformable<CronJobModel> {

    /** Constant Logger. */
    private static final Logger LOGGER = Logger.getLogger(VirtualCategoryAndProductMappingCronJob.class);

    /** CATALOG_ID_KEY Constant. */
    private static final String CATALOG_ID_KEY = "report.product.catalog.id";

    /** CATALOG_VERSION_NAME_STAGED Constant. */
    private static final String CATALOG_VERSION_NAME_STAGED = "Staged";

    /** SYNC JOB CODE Constant. */
    private static final String SSL_PRODUCT_CATALOG_SYNC_JOB_CODE = "sync sslProductCatalog:Staged->Online";

    /** CATEGORY_PK Constant. */
    private static final String CATEGORY_PK = "categoryPK";

    /** CATALOG_VERSION Constant. */
    private static final String CATALOG_VERSION = "catalogVersion";

    /** BRAND_CODE Constant. */
    private static final String BRAND_CODE = "altBrandCode";

    /** ATTRIBUTE_CODE Constant. */
    private static final String ATTRIBUTE_CODE = "code";

    /** CATALOG_ID Constant. */
    private static final String CATALOG_ID = "sslProductCatalog";

    /** Model Service {@link ModelService}. */
    @Resource(name = "modelService")
    private ModelService modelService;

    /** Flexible Search Service {@link FlexibleSearchService}. */
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService searchService;

    /** Cron Job Service. */
    private CatalogSynchronizationService catalogSyncService;

    /** Catalog Version Service. */
    private CatalogVersionService catalogVersionService;

    @Override
    public PerformResult perform(final CronJobModel cronJobModel) {
        PerformResult result = this.doMappingForVirtualCategoriesAndProducts();
        // Start Product Catalogue Syncing Process.
        if (result.getStatus().equals(CronJobStatus.FINISHED)) {
            result = this.doProductCatalogSyncing();
        }
        return result;
    }

    /**
     * Do Product Catalog Syncing Process.
     *
     * @return {@link PerformResult}.
     */
    private PerformResult doProductCatalogSyncing() {
        SyncItemJobModel syncItemJobModel = new SyncItemJobModel();
        syncItemJobModel.setCode(SSL_PRODUCT_CATALOG_SYNC_JOB_CODE);
        syncItemJobModel = this.getSearchService().getModelByExample(syncItemJobModel);
        final CatalogVersionSyncJob versionSyncJob = this.getModelService().getSource(syncItemJobModel);

        if (versionSyncJob == null) {
            throw new IllegalArgumentException(String.format("Missing Sync Job with Code: '%s'", SSL_PRODUCT_CATALOG_SYNC_JOB_CODE));
        }
        final SyncItemCronJob itemCronJob = versionSyncJob.newExecution();
        if (itemCronJob instanceof CatalogVersionSyncCronJob) {
            final CatalogVersionSyncCronJob syncCronJob = (CatalogVersionSyncCronJob) versionSyncJob.newExecution();
            versionSyncJob.perform(syncCronJob, true);
        }

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    /**
     * Do Mapping for Products and Virtual Categories.
     *
     * @return {@link PerformResult}.
     */
    private PerformResult doMappingForVirtualCategoriesAndProducts() {
        LOGGER.info("####Automation for Mapping of Products and Virtual Category Started.####");
        final FlexibleSearchQueryBuilder queryBuilder = new FlexibleSearchQueryBuilder().from(CategoryModel._TYPECODE, "c")
                .select("c", ItemModel.PK).whereEquals("c", CategoryModel.ISVIRTUALCATEGORY, true)
                .whereEquals("c", CategoryModel.CATALOGVERSION, this.getCatalogVersionPK());
        final FlexibleSearchQuery searchQuery = queryBuilder.build();
        final SearchResult<CategoryModel> searchResult = searchService.search(searchQuery);
        final List<CategoryModel> categoryModels = searchResult.getResult();
        PerformResult performResult;
        if (CollectionUtils.isNotEmpty(categoryModels)) {
            categoryModels.forEach(this::filterProducts);
            LOGGER.info("####Automation for Mapping of Products and Virtual Category finished.####");
            performResult = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        } else {
            LOGGER.info(
                    "####Automation for Mapping of Products and Virtual Category is not successful as no Virtual Categories found.####");
            performResult = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.ABORTED);
        }
        return performResult;
    }

    /**
     * Gets Filtered Products.
     *
     * @param categoryModel {@link CategoryModel}.
     */
    private void filterProducts(final CategoryModel categoryModel) {
        final List<VirtualCategoryEntryModel> virtualCategoryEntryModels = categoryModel.getVirtualCategoryEntries();
        if (CollectionUtils.isNotEmpty(virtualCategoryEntryModels)) {
            final Set<ProductModel> productsSet = new HashSet<>();
            virtualCategoryEntryModels.forEach(entryModel -> productsSet.addAll(new HashSet<>(this.doFilterProducts(entryModel))));
            this.doMappingForVirtualCategoriesInProducts(productsSet, categoryModel);
        }
    }

    /**
     * Maps Category {@link CategoryModel} in Product Models{@link ProductModel}.
     *
     * @param productsSet set for Products {@link ProductModel}.
     * @param categoryModel {@link CategoryModel}.
     */
    private void doMappingForVirtualCategoriesInProducts(final Set<ProductModel> productsSet, final CategoryModel categoryModel) {
        // Sets new product into category model.
        categoryModel.setProducts(new ArrayList<>(productsSet));
        this.modelService.save(categoryModel);
        // adding virtual category into product super categories.
        productsSet.forEach((final ProductModel productModel) -> {
            LOGGER.debug("Start Mapping for Virtual Category in Product Model");
            final Collection<CategoryModel> categoryModels = productModel.getSupercategories();
            if (!categoryModels.contains(categoryModel)) {
                final List<CategoryModel> modelList = new ArrayList<>();
                modelList.addAll(categoryModels);
                modelList.add(categoryModel);
                productModel.setSupercategories(modelList);
                try {
                    this.modelService.save(productModel);
                } catch (final ModelSavingException e) {
                    LOGGER.error(String.format("Error While Mapping Virtual Category in Product Model : %s",
                            ExceptionUtils.getFullStackTrace(e)));
                }
            }
        });
    }

    /**
     * Gets Catalog Version.
     *
     * @return Object as PK for catalog version.
     */
    private Object getCatalogVersionPK() {
        final String catalogId = StringUtils.isEmpty(Config.getParameter(CATALOG_ID_KEY)) ? CATALOG_ID
                : Config.getParameter(CATALOG_ID_KEY);
        final String catalogVersionNameStaged = CATALOG_VERSION_NAME_STAGED;
        final CatalogVersionModel catalogStagedModel = this.getCatalogVersionService().getCatalogVersion(catalogId,
                catalogVersionNameStaged);
        return catalogStagedModel.getPk();
    }

    /**
     * Filter Products on basis of single row filters for Virtual Category.
     *
     * @param entryModel {@link VirtualCategoryEntryModel}.
     * @return list of {@link ProductModel}.
     */

    private List<ProductModel> doFilterProducts(final VirtualCategoryEntryModel entryModel) {
        final CategoryModel category = entryModel.getCategory();
        final String brandCode = entryModel.getBrandCode();
        final ClassificationAttributeValueModel attributeValueModel = entryModel.getClassificationAttributeValue();
        final ClassAttributeAssignmentModel attributeModel = entryModel.getClassAttributeAssignment();

        final Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_PK, category.getPk());
        params.put(CATALOG_VERSION, this.getCatalogVersionPK());
        final StringBuilder queryBuilder = new StringBuilder("SELECT ");
        final List<ProductModel> productModels = new ArrayList<>();
        if (attributeModel != null && attributeValueModel != null) {
            queryBuilder
                    .append("{pf:pk} FROM {ProductFeature AS pf JOIN Product AS p ON {pf:product}={p:PK} JOIN ClassAttributeAssignment AS caa ON "
                            + "{pf:classificationAttributeAssignment}={caa:PK} JOIN ClassificationAttribute AS ca ON {caa:classificationAttribute}={ca:PK} "
                            + "JOIN CategoryProductRelation as cpr on {cpr:target}={p:pk} JOIN CatalogVersion AS cv on {cv:PK} = {p:CatalogVersion}} "
                            + "WHERE {ca:code}=?code and {cpr:source}=?categoryPK and {cv:PK}=?catalogVersion");
            if (StringUtils.isNotEmpty(brandCode)) {
                queryBuilder.append(" AND {p:altBrandCode}=?altBrandCode");
                params.put(BRAND_CODE, brandCode);
            }
            params.put(ATTRIBUTE_CODE, attributeModel.getClassificationAttribute().getCode());
            final SearchResult<ProductFeatureModel> searchResult = searchService.search(queryBuilder.toString(), params);
            LOGGER.debug(String.format(
                    "Number of Products found with category '%s' , altBrandCode '%s' , classification attribute name '%s' and value '%s' is %d",
                    category.getCode(), brandCode, attributeValueModel.getCode(), attributeModel.getClassificationAttribute().getCode(),
                    searchResult.getTotalCount()));
            final List<ProductFeatureModel> productFeatureModels = searchResult.getResult();
            productModels.addAll(this.filterProductUsingAttributeValue(attributeValueModel, productFeatureModels));

        } else {
            queryBuilder
                    .append("{p:pk} FROM {Product AS p JOIN CatalogVersion AS cv on {cv:PK} = {p:CatalogVersion} JOIN CategoryProductRelation "
                            + "as cpr on {cpr:target}={p:pk}} WHERE {p:altBrandCode}=?altBrandCode AND {cpr:source}=?categoryPK and {cv:PK}=?catalogVersion");
            params.put(BRAND_CODE, brandCode);
            final SearchResult<ProductModel> searchResult = searchService.search(queryBuilder.toString(), params);
            LOGGER.debug(String.format("Number of Products found with category '%s' , altBrandCode '%s' is %d", category.getCode(), brandCode,
                    searchResult.getTotalCount()));
            productModels.addAll(searchResult.getResult());
        }
        return productModels;
    }

    /**
     * Checks whether attribute value exists in feature attribute.
     *
     * @param valueModel {@link ClassificationAttributeValueModel}.
     * @param productFeatureModels list {@link ProductFeatureModel}.
     * @return {@link Predicate}.
     */
    private List<ProductModel> filterProductUsingAttributeValue(final ClassificationAttributeValueModel valueModel,
            final List<ProductFeatureModel> productFeatureModels) {
        final List<ProductModel> productModels = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(productFeatureModels)) {
            productFeatureModels.stream().forEach(productFeatureModel -> {
                final ClassAttributeAssignmentModel attributeAssignmentModel = productFeatureModel.getClassificationAttributeAssignment();
                final List<ClassificationAttributeValueModel> valueModels = attributeAssignmentModel.getAttributeValues();
                final List<String> codeList = new ArrayList<>();
                valueModels.forEach(model -> codeList.add(model.getCode()));
                if (codeList.contains(valueModel.getCode())) {
                    productModels.add(productFeatureModel.getProduct());
                    LOGGER.debug(String.format("Product '%s' Matched with classification attribute value '%s'",
                            productFeatureModel.getProduct().getName(), valueModel.getCode()));
                }
            });
        }
        return productModels;
    }

    /**
     * Getter : Gets Model Service.
     *
     * @return {@link ModelService}.
     */
    private ModelService getModelService() {
        return modelService;
    }

    @Override
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    /**
     * Getter : Gets FlexibleSearchService.
     *
     * @return {@link FlexibleSearchService}.
     */
    private FlexibleSearchService getSearchService() {
        return searchService;
    }

    /**
     * Setter : Sets FlexibleSearchService.
     *
     * @param searchService {@link FlexibleSearchService}.
     */
    public void setSearchService(final FlexibleSearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Getter : Gets {@link CatalogSynchronizationService}.
     *
     * @return {@link CatalogSynchronizationService}.
     */
    public CatalogSynchronizationService getCatalogSyncService() {
        return catalogSyncService;
    }

    /**
     * Setter : Sets Cron Job Service {@link CatalogSynchronizationService}.
     *
     * @param catalogSyncService {@link CatalogSynchronizationService}.
     */
    public void setCatalogSyncService(final CatalogSynchronizationService catalogSyncService) {
        this.catalogSyncService = catalogSyncService;
    }

    /**
     * Getter : Gets {@link CatalogVersionService}.
     *
     * @return {@link CatalogVersionService}.
     */
    private CatalogVersionService getCatalogVersionService() {
        return catalogVersionService;
    }

    /**
     * Setter : Sets {@link CatalogVersionService}.
     *
     * @param catalogVersionService {@link CatalogVersionService}.
     */
    public void setCatalogVersionService(final CatalogVersionService catalogVersionService) {
        this.catalogVersionService = catalogVersionService;
    }
}
