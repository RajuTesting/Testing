/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.populators;

import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

/**
 * @author kanagaraj.k
 * 
 */
public class SSLSearchFiltersPopulator<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_SORT_TYPE>
		extends
		de.hybris.platform.commerceservices.search.solrfacetsearch.populators.SearchFiltersPopulator<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_SORT_TYPE> {

	/**
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.search.solrfacetsearch.populators.SearchFiltersPopulator#populate(de.hybris
	 *      .platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData,
	 *      de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest)
	 */
	@Override
	public void populate(
			final SearchQueryPageableData<SolrSearchQueryData> source,
			final SolrSearchRequest<FACET_SEARCH_CONFIG_TYPE, IndexedType, IndexedProperty, SearchQuery, INDEXED_TYPE_SORT_TYPE> target) {
		super.populate(source, target);
		final SearchQuery searchQuery = target.getSearchQuery();
		// Setting the dynamic filter query
		/*
		 * if (source.getSearchQueryData() != null &&
		 * StringUtils.isNotEmpty(source
		 * .getSearchQueryData().getPriceStartRange()) &&
		 * StringUtils.isNotEmpty(
		 * source.getSearchQueryData().getPriceEndRange())) {
		 * searchQuery.setUserQuery(" priceValue_inr_double:[" +
		 * source.getSearchQueryData().getPriceStartRange() + " TO " +
		 * source.getSearchQueryData().getPriceEndRange() + "]"); }
		 */

		target.setSearchQuery(searchQuery);
	}
}
