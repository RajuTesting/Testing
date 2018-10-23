/**
 *
 */
package com.borngroup.ssl.core.autosuggest;

import java.util.Comparator;
import org.apache.log4j.Logger;
import com.borngroup.ssl.commercewebservices.dto.AutoSuggestionResponseDTO;

/**
 * @author osheengulati
 *
 */
public class AutosuggestRelevancyComparatorForMobile implements Comparator<AutoSuggestionResponseDTO> {
	private static final Logger LOG = Logger.getLogger(AutosuggestRelevancyComparatorForMobile.class);
	private final String keyWord;
	private static final String IN = " in ";
	private static final String FOR = " for ";
	private static final String SPACE = " ";
	private static final String EMPTY = "";


	public AutosuggestRelevancyComparatorForMobile(final String keyWord) {
		this.keyWord = keyWord;
	}

	@Override
	public int compare(final AutoSuggestionResponseDTO o1, final AutoSuggestionResponseDTO o2) {
		LOG.debug(String.format("AutoSuggest-1-SuggestionName= %s, Autosuggest-2-SuggestionName= %s", o1.getAutoSuggestionName(),o2.getAutoSuggestionName()));
		if (o1.getAutoSuggestionName() != null && o2.getAutoSuggestionName() != null) {
			final String[] categoryOb1 = o1.getAutoSuggestionName().split(IN);
			final String[] categoryOb2 = o2.getAutoSuggestionName().split(IN);

			final boolean o1Has = categoryOb1[0].toLowerCase().startsWith(keyWord.toLowerCase());
			final boolean o2Has = categoryOb2[0].toLowerCase().startsWith(keyWord.toLowerCase());

			if (o1Has && o2Has) {
				final boolean o1ExactlyMatchFound = categoryOb1[0].equalsIgnoreCase(keyWord);
				final boolean o2ExactlyMatchFound = categoryOb2[0].equalsIgnoreCase(keyWord);
				if (o1ExactlyMatchFound && o2ExactlyMatchFound || !o1ExactlyMatchFound && !o2ExactlyMatchFound) {
					return this.compareCategoriesfurthur(o1.getAutoSuggestionName(), o2.getAutoSuggestionName());
				}
				if (o1ExactlyMatchFound && !o2ExactlyMatchFound) {
					return -1;
				}
				if (!o1ExactlyMatchFound && o2ExactlyMatchFound) {
					return 1;
				}
			}
			if (o1Has && !o2Has) {
				return -1;
			} else if (o2Has && !o1Has) {
				return 1;
			} else if (!o2Has && !o1Has) {
				final boolean o1ContainsKeyword = categoryOb1[0].toLowerCase().contains(keyWord.toLowerCase());
				final boolean o2ContainsKeyword = categoryOb2[0].toLowerCase().contains(keyWord.toLowerCase());
				if ((o1ContainsKeyword && o2ContainsKeyword) || (!o1ContainsKeyword && !o2ContainsKeyword)) {
					return this.compareCategoriesfurthur(o1.getAutoSuggestionName(), o2.getAutoSuggestionName());
				} else if (o1ContainsKeyword && !o2ContainsKeyword) {
					return -1;
				} else if (!o1ContainsKeyword && o2ContainsKeyword) {
					return 1;
				}
			}
		}
		return 0;
	}

	private int compareCategoriesfurthur(final String o1SuggestionName, final String o2SuggestionName) {

		if ((o1SuggestionName.toLowerCase().contains(IN)) && (o2SuggestionName.toLowerCase().contains(IN))) {
			return compareKeywordsAndCategories(o1SuggestionName, o2SuggestionName);
		} else {
			return this.keywordComparer(o1SuggestionName.replace(SPACE, EMPTY).toLowerCase(),
				o2SuggestionName.replace(SPACE, EMPTY).toLowerCase());
		}
	}

	private int compareKeywordsAndCategories(final String o1SuggestionName, final String o2SuggestionName) {
		// passed full responses

		final String[] categoryOb1 = o1SuggestionName.toLowerCase().split(IN);
		final String[] categoryOb2 = o2SuggestionName.toLowerCase().split(IN);

		final String o1Category = categoryOb1[0].toLowerCase();
		final String o2Category = categoryOb2[0].toLowerCase();

		final String o1CategoryAfterIn = categoryOb1[1].toLowerCase();
		final String o2CategoryAfterIn = categoryOb2[1].toLowerCase();

		final boolean o1CategorycontainsFor = o1CategoryAfterIn.contains(FOR);
		final boolean o2CategorycontainsFor = o2CategoryAfterIn.contains(FOR);

		if (o1Category.equalsIgnoreCase(o2Category)) {
			if ((!o1CategorycontainsFor && !o2CategorycontainsFor) || (o1CategorycontainsFor && !o2CategorycontainsFor)
				|| (!o1CategorycontainsFor && o2CategorycontainsFor)) {
				return keywordComparer(o1CategoryAfterIn, o2CategoryAfterIn);
			} else if (o1CategorycontainsFor && o2CategorycontainsFor) {
				final String[] o1Categories = o1CategoryAfterIn.replace(SPACE, EMPTY).split(FOR);
				final String[] o2Categories = o2CategoryAfterIn.replace(SPACE, EMPTY).split(FOR);
				if (o1Categories[0].equalsIgnoreCase(o2Categories[0])) {
					return keywordComparer(o1Categories[1], o2Categories[1]);
				} else {
					return keywordComparer(o1CategoryAfterIn, o2CategoryAfterIn);
				}
			}
		} else {
			return keywordComparer(o1Category, o2Category);
		}

		return 0;
	}

	private int keywordComparer(final String o1, final String o2) {
		if (o1.contains(keyWord.toLowerCase())) {
			return o2.contains(keyWord.toLowerCase()) ? compareToComparer(o1, o2) : -1;
		} else {
			return o2.contains(keyWord.toLowerCase()) ? 1 : compareToComparer(o1, o2);
		}
	}

	private int compareToComparer(final String o1, final String o2) {
		final int afterCompare = o1.compareTo(o2);
		if (afterCompare == 0) {
			return 0;
		} else if (afterCompare < 0) {
			return -1;
		} else if (afterCompare > 0) {
			return 1;
		}
		return 0;
	}
}
