package com.borngroup.ssl.core.jobs;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.exceptions.NoValidSolrConfigException;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.impl.DefaultSolrFacetSearchConfigSelectionStrategy;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfigService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.search.Document;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchResult;
import de.hybris.platform.util.Config;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.SSLProductLinkSimilarProductsCronJobModel;
import com.borngroup.ssl.core.product.service.SslProductService;
import com.borngroup.ssl.core.search.solrfacetsearch.impl.SSLSimilarProductsSearchStrategy;

/**
 * Cron Job : Cron Job to attach similar products.
 * <p/>
 * Created by shilpa.verma@nagarro.com.
 *
 * @author SSL
 */
public class SSLProductLinkSimilarProductsCronJob extends AbstractJobPerformable<SSLProductLinkSimilarProductsCronJobModel> {

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(SSLProductLinkSimilarProductsCronJob.class);

    @Resource(name = "sslProductService")
    private SslProductService sslProductService;

    @Resource(name = "defaultSolrFacetSearchConfigSelectionStrategy")
    private DefaultSolrFacetSearchConfigSelectionStrategy defaultSolrFacetSearchConfigSelectionStrategy;

    /**
     * Facet Search Config Service {@link FacetSearchConfigService}.
     */
    @Resource(name = "facetSearchConfigService")
    private FacetSearchConfigService facetSearchConfigService;

    @Resource(name = "sslSimilarProductsSearchStrategy")
    private SSLSimilarProductsSearchStrategy sslSimilarProductsSearchStrategy;

    @Resource(name = "catalogVersionService")
    private CatalogVersionService catalogVersionService;

    @Override
    public PerformResult perform(final SSLProductLinkSimilarProductsCronJobModel cronJobModel) {

        LOG.info("Product - Related Products linking Cron Job");
        final boolean functionalityOn = Config.getBoolean("similar.products.functionality.enabled", true);
        if (!functionalityOn) {
            LOG.info("Similar Products functionality is disabled. ");
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }
        final Instant start = Instant.now();
        final StringBuilder categoryString = new StringBuilder();
        FacetSearchConfig searchConfig;
        final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME,
                SslCoreConstants.ONLINE_CATALOG_VERSION);

        try {
            searchConfig = getFacetSearchConfig();
        } catch (final NoValidSolrConfigException e) {
            throw new ConversionException("No valid solrFacetSearchConfig found for the current context", e);
        } catch (final FacetConfigServiceException e) {
            throw new ConversionException(e.getMessage(), e);
        }
        final IndexedType indexType = getIndexedType(searchConfig);
        for (final CategoryModel category : cronJobModel.getCategories()) {
            categoryString.append(category.getCode()).append("-").append(category.getName()).append(" ,");
            this.addSimilarProductsForCategory(searchConfig, catalogVersion, indexType, category);
        }
        final Instant end = Instant.now();
        LOG.info(String.format("Spent %s during this cron job call for categories/category -" + categoryString.toString(),
                Duration.between(start, end)));
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    /**
     * @param searchConfig
     * @param catalogVersion
     * @param indexType
     * @param category
     */
    private void addSimilarProductsForCategory(final FacetSearchConfig searchConfig, final CatalogVersionModel catalogVersion,
            final IndexedType indexType, final CategoryModel category) {

        if (sslSimilarProductsSearchStrategy.isValidCategory(category)) {
            LOG.info("addSimilarProductsForCategory Category name is " + category.getName());
            final List<ApparelProductModel> baseProductList = sslProductService.getBaseProductsForCategory(category.getCode());
            LOG.info("Products List size is " + baseProductList.size());
            final List<String> similarProductsForNoResult = new ArrayList<>();

            for (final ApparelProductModel product : baseProductList) {

                final SearchQuery searchQuery = new SearchQuery(searchConfig, indexType);
                searchQuery.setPageSize(40);
                searchQuery.setCatalogVersions(Collections.singletonList(catalogVersion));
                try {
                    final SearchResult searchResult = sslSimilarProductsSearchStrategy.search(searchQuery,
                            Collections.<String, String> emptyMap(), product, category, false);
                    final List<String> similarProducts = new ArrayList<>(0);
                    if (similarProductsForNoResult.isEmpty()) {
                        final SearchQuery categorySearchQuery = new SearchQuery(searchConfig, indexType);

                        categorySearchQuery.setPageSize(40);
                        categorySearchQuery.setCatalogVersions(Collections.singletonList(catalogVersion));
                        final SearchResult categorySearchResult = sslSimilarProductsSearchStrategy.search(categorySearchQuery,
                                Collections.<String, String> emptyMap(), product, category, true);
                        for (final Document each : categorySearchResult.getDocuments()) {
                            final String productCode = (String) each.getFieldValue("code");
                            similarProductsForNoResult.add(productCode);
                        }
                    }
                    for (final Document each : searchResult.getDocuments()) {
                        final String productCode = (String) each.getFieldValue("code");
                        if (!product.getCode().equals(productCode)) {
                            similarProducts.add(productCode);
                        }
                    }

                    if (!similarProducts.isEmpty()) {
                        if (similarProducts.size() < 39) {
                            final int noOfProductsToBeAdded = 40 - similarProducts.size();
                            final List<String> listExcludingAlreadyPresentIds = new ArrayList<>(similarProductsForNoResult);
                            listExcludingAlreadyPresentIds.removeAll(similarProducts);
                            listExcludingAlreadyPresentIds.remove(product.getCode());
                            LOG.info("Size of Category is" + similarProductsForNoResult.size());
                            if (similarProductsForNoResult.size() < noOfProductsToBeAdded) {
                                similarProducts.addAll(similarProductsForNoResult);
                            } else {
                                similarProducts.addAll(similarProductsForNoResult.subList(0, noOfProductsToBeAdded));
                            }
                        }
                        product.setSimilarProducts(similarProducts);
                    } else {
                        final List<String> listExcludingOwnID = new ArrayList<>(similarProductsForNoResult);
                        listExcludingOwnID.remove(product.getCode());
                        product.setSimilarProducts(listExcludingOwnID);
                    }

                    modelService.save(product);
                    LOG.debug("Similar Products for Product -" + product.getCode() + " are : " + StringUtils.join(similarProducts, ","));

                } catch (final Exception e) {
                    LOG.error("Search Failed", e);
                }
            }
            LOG.info("Total Products :" + baseProductList.size());
        }
    }

    private FacetSearchConfig getFacetSearchConfig() throws NoValidSolrConfigException, FacetConfigServiceException {
        final SolrFacetSearchConfigModel solrFacetSearchConfigModel = defaultSolrFacetSearchConfigSelectionStrategy
                .getCurrentSolrFacetSearchConfig();
        return facetSearchConfigService.getConfiguration(solrFacetSearchConfigModel.getName());
    }

    private IndexedType getIndexedType(final FacetSearchConfig config) {
        final IndexConfig indexConfig = config.getIndexConfig();

        // Strategy for working out which of the available indexed types to use
        final Collection<IndexedType> indexedTypes = indexConfig.getIndexedTypes().values();
        if (!indexedTypes.isEmpty()) {
            // When there are multiple - select the first
            return indexedTypes.iterator().next();
        }

        // No indexed types
        return null;
    }

}
