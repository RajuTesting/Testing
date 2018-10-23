/**
 * 
 */
package com.borngroup.ssl.core.search.solrfacetsearch.impl;

import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.impl.DefaultFacetSearchService;

import com.borngroup.ssl.core.search.solrfacetsearch.search.SSLSearchQuery;

public class SSLDefaultFacetSearchService extends DefaultFacetSearchService {

	@Override
	public SearchQuery createSearchQuery(
			final FacetSearchConfig facetSearchConfig,
			final IndexedType indexedType) {
		return new SSLSearchQuery(facetSearchConfig, indexedType);
	}
}
