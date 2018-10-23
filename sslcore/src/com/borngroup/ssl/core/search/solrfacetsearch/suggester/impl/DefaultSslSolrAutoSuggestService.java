/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.suggester.impl;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.indexer.SolrIndexedTypeCodeResolver;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel;
import de.hybris.platform.solrfacetsearch.solr.exceptions.SolrServiceException;
import de.hybris.platform.solrfacetsearch.suggester.SolrSuggestion;
import de.hybris.platform.solrfacetsearch.suggester.exceptions.SolrAutoSuggestException;
import de.hybris.platform.solrfacetsearch.suggester.impl.DefaultSolrAutoSuggestService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Collation;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;

import com.borngroup.ssl.core.constants.SslCoreConstants;

/**
 * @author shashank2484
 *
 */

public class DefaultSslSolrAutoSuggestService extends DefaultSolrAutoSuggestService {
    private SolrIndexedTypeCodeResolver solrIndexedTypeCodeResolver;

    @Override
    public SolrSuggestion getAutoSuggestionsForQuery(final LanguageModel language,
            final SolrIndexedTypeModel solrIndexedType, final String queryInput) throws SolrAutoSuggestException {
        try {
            final String e = solrIndexedType.getSolrFacetSearchConfig().getName();
            final FacetSearchConfig facetSearchConfig = this.facetSearchConfigService.getConfiguration(e);
            final IndexedType indexedType = facetSearchConfig.getIndexConfig().getIndexedTypes()
                    .get(this.solrIndexedTypeCodeResolver.resolveIndexedTypeCode(solrIndexedType));
            final SolrClient solrClient = this.solrService.getSolrClient(facetSearchConfig, indexedType);
            final SolrQuery query = new SolrQuery();
            query.setQuery(queryInput);
            query.setRequestHandler("/suggest");
            query.set("spellcheck.dictionary", new String[] { language.getIsocode() });
            query.set("spellcheck.q", new String[] { queryInput });
            query.set("spellcheck.maxCollations", new String[] { SslCoreConstants.AUTOCOMPLETE_COLLATIONS_COUNT });
            final QueryResponse response = solrClient.query(query);
            final HashMap resultSuggestionMap = new HashMap();
            final ArrayList resultCollations = new ArrayList();
            final SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();
            if (spellCheckResponse != null) {
                final List suggestions = spellCheckResponse.getSuggestions();
                final Iterator collation = suggestions.iterator();

                while (collation.hasNext()) {
                    final Suggestion collatedResults = (Suggestion) collation.next();
                    final List alternatives = collatedResults.getAlternatives();
                    resultSuggestionMap.put(collatedResults.getToken(), alternatives);
                }

                final List collatedResults1 = spellCheckResponse.getCollatedResults();
                if (collatedResults1 != null) {
                    final Iterator alternatives1 = collatedResults1.iterator();

                    while (alternatives1.hasNext()) {
                        final Collation collation1 = (Collation) alternatives1.next();
                        resultCollations.add(collation1.getCollationQueryString());
                    }
                }
            }

            return new SolrSuggestion(resultSuggestionMap, resultCollations);
        } catch (FacetConfigServiceException | SolrServerException | IOException | SolrServiceException arg16) {
            throw new SolrAutoSuggestException("Error issuing suggestion query", arg16);
        }
    }

    @Override
    public void setSolrIndexedTypeCodeResolver(final SolrIndexedTypeCodeResolver solrIndexedTypeCodeResolver) {
        this.solrIndexedTypeCodeResolver = solrIndexedTypeCodeResolver;
        super.setSolrIndexedTypeCodeResolver(solrIndexedTypeCodeResolver);
    }

}
