/**
 * 
 */
package com.borngroup.ssl.core.search.solrfacetsearch.search;

import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

public class SSLSearchQuery extends SearchQuery {

	private String startPrice;
	private String endPrice;

	/**
	 * @param facetSearchConfig
	 * @param indexedType
	 */
	public SSLSearchQuery(final FacetSearchConfig facetSearchConfig,
			final IndexedType indexedType) {
		super(facetSearchConfig, indexedType);
	}

	/**
	 * @return the startPrice
	 */
	public String getStartPrice() {
		return startPrice;
	}

	/**
	 * @param startPrice
	 *            the startPrice to set
	 */
	public void setStartPrice(final String startPrice) {
		this.startPrice = startPrice;
	}

	/**
	 * @return the endPrice
	 */
	public String getEndPrice() {
		return endPrice;
	}

	/**
	 * @param endPrice
	 *            the endPrice to set
	 */
	public void setEndPrice(final String endPrice) {
		this.endPrice = endPrice;
	}

}
