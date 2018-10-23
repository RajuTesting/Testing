/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.impl;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.FacetSearchException;
import de.hybris.platform.solrfacetsearch.search.OrderField;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchResult;
import de.hybris.platform.solrfacetsearch.search.context.FacetSearchContext;
import de.hybris.platform.solrfacetsearch.search.impl.DefaultFacetSearchStrategy;
import de.hybris.platform.solrfacetsearch.search.impl.SearchQueryConverterData;
import de.hybris.platform.solrfacetsearch.search.impl.SearchResultConverterData;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.borngroup.ssl.core.search.solrfacetsearch.search.SSLSearchQuery;

public class SSLDefaultFacetSearchStrategy extends DefaultFacetSearchStrategy {
    public static final Logger LOG = Logger.getLogger(SSLDefaultFacetSearchStrategy.class);

    public static final String FACET_COUNT = "facet.limit.default";
    public static final String MINIMUM_AMOUNT = "plp.min.amount";

    @Resource
    private ConfigurationService configurationService;

    @Override
    public SearchResult search(final SearchQuery searchQuery, final Map<String, String> searchHints)
            throws FacetSearchException {

        checkQuery(searchQuery);

        try {
            final FacetSearchConfig facetSearchConfig = searchQuery.getFacetSearchConfig();
            final IndexedType indexedType = searchQuery.getIndexedType();

            final FacetSearchContext facetSearchContext = getFacetSearchContextFactory().createContext(
                    facetSearchConfig, indexedType, searchQuery);
            facetSearchContext.getSearchHints().putAll(searchHints);
            getFacetSearchContextFactory().initializeContext();

            final SolrClient solrClient = getSolrService().getSolrClient(facetSearchConfig, indexedType);

			// For Search Page
			if (StringUtils.isNotEmpty(searchQuery.getUserQuery()) && CollectionUtils.isNotEmpty(searchQuery.getSorts())) {
                for (final OrderField sort : searchQuery.getSorts()) {
                    if (sort.getField().equals("popularityRank")) {
                        searchQuery.getSorts().remove(sort);
                        break;
                    }
                }
			}

            final SearchQueryConverterData searchQueryConverterData = new SearchQueryConverterData();
            searchQueryConverterData.setFacetSearchContext(facetSearchContext);
            searchQueryConverterData.setSearchQuery(searchQuery);

            final SolrQuery solrQuery = getFacetSearchQueryConverter().convert(searchQueryConverterData);
            solrQuery.addFilterQuery("NOT priceValue_inr_double:0");
            final String minimumAmount = String.valueOf(configurationService.getConfiguration().getInt(MINIMUM_AMOUNT, 10));
            solrQuery.addFilterQuery("priceValue_inr_double:["+ minimumAmount +" TO *]");
            solrQuery.addFilterQuery("inStockFlag_boolean:true");
            if (searchQuery instanceof SSLSearchQuery) {
                final SSLSearchQuery sslSearchQuery = (SSLSearchQuery) searchQuery;
                if (StringUtils.isNotEmpty(sslSearchQuery.getStartPrice())
                        && StringUtils.isNotEmpty(sslSearchQuery.getEndPrice())) {
                    solrQuery.addFilterQuery("priceValue_inr_double:[" + sslSearchQuery.getStartPrice() + " TO "
                            + sslSearchQuery.getEndPrice() + "]");
                }
            }

            final int count = configurationService.getConfiguration().getInt(FACET_COUNT, solrQuery.getFacetLimit());
            solrQuery.setFacetLimit(count);
            if (LOG.isDebugEnabled()) {
                LOG.debug(solrQuery);
            }

            final QueryResponse queryResponse = solrClient.query(solrQuery);

            final SearchResultConverterData searchResultConverterData = new SearchResultConverterData();
            searchResultConverterData.setFacetSearchContext(facetSearchContext);
            searchResultConverterData.setQueryResponse(queryResponse);

            final SearchResult searchResult = getFacetSearchResultConverter().convert(searchResultConverterData);

            getFacetSearchContextFactory().getContext().setSearchResult(searchResult);
            getFacetSearchContextFactory().destroyContext();

            return searchResult;
        } catch (final Exception e) {
            getFacetSearchContextFactory().destroyContext(e);
            throw new FacetSearchException(e.getMessage(), e);
        }
    }
}
