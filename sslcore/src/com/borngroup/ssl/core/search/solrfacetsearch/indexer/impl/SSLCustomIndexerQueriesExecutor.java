/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.indexer.impl;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.indexer.IndexerQueriesExecutor;
import de.hybris.platform.solrfacetsearch.indexer.IndexerQueryContext;
import de.hybris.platform.solrfacetsearch.indexer.IndexerQueryContextFactory;
import de.hybris.platform.solrfacetsearch.indexer.exceptions.IndexerException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.StopWatch;

/**
 * @author tejsharma
 *
 */
public class SSLCustomIndexerQueriesExecutor implements IndexerQueriesExecutor {
	private static final Logger LOG = Logger
			.getLogger(SSLCustomIndexerQueriesExecutor.class);

	private IndexerQueryContextFactory<IndexerQueryContext> indexerQueryContextFactory;
	private FlexibleSearchService flexibleSearchService;
	private SearchRestrictionService searchRestrictionService;

	public FlexibleSearchService getFlexibleSearchService() {
		return this.flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(
			final FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}

	public IndexerQueryContextFactory<IndexerQueryContext> getIndexerQueryContextFactory() {
		return this.indexerQueryContextFactory;
	}

	@Required
	public void setIndexerQueryContextFactory(
			final IndexerQueryContextFactory<IndexerQueryContext> indexerQueryContextFactory) {
		this.indexerQueryContextFactory = indexerQueryContextFactory;
	}

	@Required
	public void setSearchRestrictionService(
			final SearchRestrictionService searchRestrictionService) {
		this.searchRestrictionService = searchRestrictionService;
	}

	public SearchRestrictionService getSearchRestrictionService() {
		return this.searchRestrictionService;
	}

	@Override
	public List<PK> getPks(final FacetSearchConfig facetSearchConfig,
			final IndexedType indexedType, final String query,
			final Map<String, Object> queryParameters) throws IndexerException {
		try {
			this.indexerQueryContextFactory.createContext(facetSearchConfig,
					indexedType, query, queryParameters);
			this.indexerQueryContextFactory.initializeContext();

			if (LOG.isDebugEnabled()) {
				LOG.debug("Query: " + query);
			}

			final StopWatch timer = new StopWatch();
			timer.start();
			this.searchRestrictionService.disableSearchRestrictions();
			final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query,
					queryParameters);
			fsQuery.setResultClassList(Arrays.asList(new Class[] { PK.class }));
			final SearchResult fsResult = this.flexibleSearchService
					.search(fsQuery);

			timer.stop();

			if (LOG.isDebugEnabled()) {
				LOG.debug("Number of pks: " + fsResult.getTotalCount()
						+ ", query time: " + timer.getTotalTimeSeconds() + "s.");
			}

			this.indexerQueryContextFactory.destroyContext();
			this.searchRestrictionService.enableSearchRestrictions();
			return fsResult.getResult();
		} catch (final java.lang.RuntimeException e) {
			this.indexerQueryContextFactory.destroyContext(e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.solrfacetsearch.indexer.IndexerQueriesExecutor#getItems
	 * (de.hybris.platform.solrfacetsearch.config.FacetSearchConfig,
	 * de.hybris.platform.solrfacetsearch.config.IndexedType,
	 * java.util.Collection)
	 */
	@Override
	public List<ItemModel> getItems(final FacetSearchConfig facetSearchConfig,
			final IndexedType indexedType, final Collection<PK> pks)
			throws IndexerException {
		final String query = "SELECT {pk} FROM {" + indexedType.getCode()
				+ "} where {pk} in (?pks)";
		final Map queryParameters = Collections.singletonMap("pks", pks);

		try {
			this.indexerQueryContextFactory.createContext(facetSearchConfig,
					indexedType, query, queryParameters);
			this.indexerQueryContextFactory.initializeContext();

			if (LOG.isDebugEnabled()) {
				LOG.debug("Query: " + query);
			}

			final StopWatch timer = new StopWatch();
			timer.start();
			this.searchRestrictionService.disableSearchRestrictions();
			final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query,
					queryParameters);
			final SearchResult fsResult = this.flexibleSearchService
					.search(fsQuery);
			this.searchRestrictionService.enableSearchRestrictions();
			timer.stop();

			if (LOG.isDebugEnabled()) {
				LOG.debug("Number of items: " + fsResult.getTotalCount()
						+ ", query time: " + timer.getTotalTimeSeconds() + "s.");
			}

			this.indexerQueryContextFactory.destroyContext();
			return fsResult.getResult();
		} catch (final java.lang.RuntimeException e) {
			this.indexerQueryContextFactory.destroyContext(e);
			throw e;
		}
	}
}
