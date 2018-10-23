/**
 * 
 */
package com.borngroup.ssl.core.search.solrfacetsearch.populators;

import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.populators.SearchSolrQueryPopulator;

import com.borngroup.ssl.core.search.solrfacetsearch.search.SSLSearchQuery;

public class SSLCommerceSearchSolrQueryPopulator extends
		SearchSolrQueryPopulator {
	@Override
	public void populate(final SearchQueryPageableData source,
			final SolrSearchRequest target) {
		super.populate(source, target);
		if (null != target.getSearchQuery()
				&& target.getSearchQuery() instanceof SSLSearchQuery) {

			final SSLSearchQuery query = (SSLSearchQuery) target
					.getSearchQuery();
			if (null != source
					&& null != source.getSearchQueryData()
					&& source.getSearchQueryData() instanceof SolrSearchQueryData) {
				final SolrSearchQueryData queryData = (SolrSearchQueryData) source
						.getSearchQueryData();
				query.setStartPrice(queryData.getPriceStartRange());
				query.setEndPrice(queryData.getPriceEndRange());
			}
		}
	}
}
