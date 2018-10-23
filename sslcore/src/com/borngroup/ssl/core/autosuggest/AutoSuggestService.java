package com.borngroup.ssl.core.autosuggest;

import de.hybris.platform.cms2.model.site.CMSSiteModel;

import java.io.IOException;
import java.util.List;

import org.springframework.ui.Model;

import com.borngroup.ssl.facades.product.data.SuggestionResponse;

/**
 * Autosuggest Service : Service which displays the auto suggestions.
 * <p/>
 * Created by osheen.gulati@nagarro.com.
 *
 * @author SSL
 */
public interface AutoSuggestService {

	public List<SuggestionResponse> getInSuggestions(final String searchTerm, final CMSSiteModel cmsSiteModel,
			final Model model) throws IOException;
}
