/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.suggester.impl;

import de.hybris.platform.commerceservices.search.solrfacetsearch.data.AutocompleteSuggestion;
import de.hybris.platform.commerceservices.search.solrfacetsearch.impl.DefaultSolrProductSearchAutocompleteService;
import de.hybris.platform.solrfacetsearch.suggester.SolrSuggestion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author shashank2484
 *
 */
public class SslSolrProductSearchAutocompleteService extends DefaultSolrProductSearchAutocompleteService {
    @Override
    protected List<AutocompleteSuggestion> findBestSuggestions(final SolrSuggestion solrSuggestion,
            final String input) {

        final List<AutocompleteSuggestion> result = new ArrayList<AutocompleteSuggestion>();

        // Get the suggestions from collations
        final Collection<String> collations = solrSuggestion.getCollates();
        if (collations != null) {
            for (final String collation : collations) {
                final AutocompleteSuggestion autocompleteSuggestion = createAutocompleteSuggestion();
                autocompleteSuggestion.setTerm(collation);
                result.add(autocompleteSuggestion);
            }
        }

        return result;
    }
}
