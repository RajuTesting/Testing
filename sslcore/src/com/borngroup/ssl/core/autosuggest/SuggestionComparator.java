package com.borngroup.ssl.core.autosuggest;

import java.util.Comparator;

import com.borngroup.ssl.facades.product.data.SuggestionResponse;

/**
 * Suggestion Comparator : Comparator which sorts the results on the basis of
 * score i.e. suggestion having search term in the starting gets the highest
 * boost.
 * <p/>
 * Created by osheen.gulati@nagarro.com.
 *
 * @author SSL
 */
public class SuggestionComparator implements Comparator<SuggestionResponse> {

	@Override
	public int compare(final SuggestionResponse object1, final SuggestionResponse object2) {
		if (object1.getScore() < object2.getScore()) {
			return -1;
		} else if (object1.getScore() > object2.getScore()) {
			return 1;
		}
		return 0;
	}

}
