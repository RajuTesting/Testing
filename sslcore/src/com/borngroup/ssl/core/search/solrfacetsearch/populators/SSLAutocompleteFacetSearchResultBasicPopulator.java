/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.impl.SearchResultConverterData;
import de.hybris.platform.solrfacetsearch.search.impl.SolrSearchResult;

/**
 * @author shashank2484
 *
 */
public class SSLAutocompleteFacetSearchResultBasicPopulator
        implements Populator<SearchResultConverterData, SolrSearchResult> {

    @Override

    public void populate(final SearchResultConverterData source, final SolrSearchResult target)
            throws ConversionException {
        final SearchQuery searchQuery = source.getFacetSearchContext().getSearchQuery();
        target.setSearchQuery(searchQuery);
        target.setQueryResponse(source.getQueryResponse());
        target.setBreadcrumbs(searchQuery.getBreadcrumbs());
    }

}
