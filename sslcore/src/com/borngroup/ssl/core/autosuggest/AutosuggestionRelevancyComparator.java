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
public class AutosuggestionRelevancyComparator implements Comparator<SuggestionResponse> {

	private final String keyWord;
	private static final String IN = " in ";
	private final String FOR = " for ";

	public AutosuggestionRelevancyComparator(final String keyWord) {
		this.keyWord = keyWord;
	}

	@Override
	public int compare(final SuggestionResponse o1, final SuggestionResponse o2) {

		if (o1.getCategorySuggestionName() != null && o2.getCategorySuggestionName() != null) {

			final String[] categoryOb1 = o1.getCategorySuggestionName().split(" in ");
			final String[] categoryOb2 = o2.getCategorySuggestionName().split(" in ");

			final boolean o1Has = categoryOb1[0].toLowerCase().startsWith(keyWord.toLowerCase());
			final boolean o2Has = categoryOb2[0].toLowerCase().startsWith(keyWord.toLowerCase());

			if (o1Has && o2Has) {
				final boolean o1ExactlyMatchFound = categoryOb1[0].toLowerCase()
					.equalsIgnoreCase(keyWord.toLowerCase());
				final boolean o2ExactlyMatchFound = categoryOb2[0].toLowerCase()
					.equalsIgnoreCase(keyWord.toLowerCase());
				if ((!o1ExactlyMatchFound && !o2ExactlyMatchFound) || (o1ExactlyMatchFound && o2ExactlyMatchFound)) {
					return this.compareCategoriesfurthur(o1.getCategorySuggestionName(),o2.getCategorySuggestionName());
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
				if ((!o1ContainsKeyword && !o2ContainsKeyword) || (o1ContainsKeyword && o2ContainsKeyword)) {
					return this.compareCategoriesfurthur(o1.getCategorySuggestionName(),o2.getCategorySuggestionName());
				} else if (o1ContainsKeyword && !o2ContainsKeyword) {
					return -1;
				} else if (!o1ContainsKeyword && o2ContainsKeyword) {
					return 1;
				}
			}
		}

		else if (o1.getBrandSuggestionName() != null && o2.getBrandSuggestionName() != null) {

            final String[] brandOb1 = o1.getBrandSuggestionName().split(" in ");
            final String[] brandOb2 = o2.getBrandSuggestionName().split(" in ");

            final boolean o1Has = brandOb1[0].toLowerCase().startsWith(keyWord.toLowerCase());
            final boolean o2Has = brandOb2[0].toLowerCase().startsWith(keyWord.toLowerCase());

            if (o1Has && o2Has) {
                final boolean o1ExactlyMatchFound = brandOb1[0].toLowerCase().equalsIgnoreCase(keyWord.toLowerCase());
                final boolean o2ExactlyMatchFound = brandOb2[0].toLowerCase().equalsIgnoreCase(keyWord.toLowerCase());

                if ((o1ExactlyMatchFound && o2ExactlyMatchFound) || !o1ExactlyMatchFound && !o2ExactlyMatchFound) {
                    return this.compareCategoriesfurthur(o1.getBrandSuggestionName(), o2.getBrandSuggestionName());
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
                final boolean o1ContainsKeyword = brandOb1[0].toLowerCase().contains(keyWord.toLowerCase());
                final boolean o2ContainsKeyword = brandOb2[0].toLowerCase().contains(keyWord.toLowerCase());
                if ((!o1ContainsKeyword && !o2ContainsKeyword) || (o1ContainsKeyword && o2ContainsKeyword)) {
                    return this.compareCategoriesfurthur(o1.getBrandSuggestionName(), o2.getBrandSuggestionName());
                } else if (o1ContainsKeyword && !o2ContainsKeyword) {
                    return -1;
                } else if (!o1ContainsKeyword && o2ContainsKeyword) {
                    return 1;
                }
            }
		}

		else if (o1.getCategorySuggestionName() != null && o2.getBrandSuggestionName() != null) {

			final String[] categoryOb1 = o1.getCategorySuggestionName().split(" in ");
			o2.setCategorySuggestionName(o2.getBrandSuggestionName());
			o2.setCategorySuggestionURL(o2.getBrandSuggestionURL());
			final String[] brandOb2 = o2.getCategorySuggestionName().split(" in ");

			final boolean o1Has = categoryOb1[0].toLowerCase().startsWith(keyWord.toLowerCase());
			final boolean o2Has = brandOb2[0].toLowerCase().startsWith(keyWord.toLowerCase());

			if (o1Has && o2Has) {
				final boolean o1ExactlyMatchFound = categoryOb1[0].toLowerCase()
						.equalsIgnoreCase(keyWord.toLowerCase());
				final boolean o2ExactlyMatchFound = brandOb2[0].toLowerCase().equalsIgnoreCase(keyWord.toLowerCase());
				if ((o1ExactlyMatchFound && o2ExactlyMatchFound) || (!o1ExactlyMatchFound && !o2ExactlyMatchFound)) {
					return this.compareCategoriesfurthur(o1.getCategorySuggestionName(), o2.getBrandSuggestionName());
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
				final boolean o2ContainsKeyword = brandOb2[0].toLowerCase().contains(keyWord.toLowerCase());
				if ((!o1ContainsKeyword && !o2ContainsKeyword) || (o1ContainsKeyword && o2ContainsKeyword)) {
					// if keyword not at all in both objects at first position
					return this.compareCategoriesfurthur(o1.getCategorySuggestionName(), o2.getBrandSuggestionName());
				} else if (o1ContainsKeyword && !o2ContainsKeyword) {
					return -1;
				} else if (!o1ContainsKeyword && o2ContainsKeyword) {
					return 1;
				}
			}
		}

		else if (o1.getBrandSuggestionName() != null && o2.getCategorySuggestionName() != null) {

			final String[] categoryOb2 = o2.getCategorySuggestionName().split(" in ");
			o1.setCategorySuggestionName(o1.getBrandSuggestionName());
			o1.setCategorySuggestionURL(o1.getBrandSuggestionURL());
			final String[] brandOb1 = o1.getCategorySuggestionName().split(" in ");

			final boolean o1Has = brandOb1[0].toLowerCase().startsWith(keyWord.toLowerCase());
			final boolean o2Has = categoryOb2[0].toLowerCase().startsWith(keyWord.toLowerCase());

			if (o1Has && o2Has) {
				final boolean o1ExactlyMatchFound = brandOb1[0].toLowerCase().equalsIgnoreCase(keyWord.toLowerCase());
				final boolean o2ExactlyMatchFound = categoryOb2[0].toLowerCase()
						.equalsIgnoreCase(keyWord.toLowerCase());

				if (o1ExactlyMatchFound && o2ExactlyMatchFound || !o1ExactlyMatchFound && !o2ExactlyMatchFound) {
					return 0;
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
				final boolean o1ContainsKeyword = brandOb1[0].toLowerCase().contains(keyWord.toLowerCase());
				final boolean o2ContainsKeyword = categoryOb2[0].toLowerCase().contains(keyWord.toLowerCase());
				if ((!o1ContainsKeyword && !o2ContainsKeyword) || (o1ContainsKeyword && o2ContainsKeyword)) {
					return compareCategoriesfurthur(o1.getBrandSuggestionName(), o2.getCategorySuggestionName());
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
			return this.keywordComparer(o1SuggestionName.replace(" ", "").toLowerCase(),
				o2SuggestionName.replace(" ", "").toLowerCase());
		}
	}

	private int compareKeywordsAndCategories(final String o1SuggestionName, final String o2SuggestionName) {
		// passed full responses

		final String[] categoryOb1 = o1SuggestionName.toLowerCase().split(" in ");
		final String[] categoryOb2 = o2SuggestionName.toLowerCase().split(" in ");

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
				final String[] o1Categories = o1CategoryAfterIn.replace(" ", "").split(FOR);
				final String[] o2Categories = o2CategoryAfterIn.replace(" ", "").split(FOR);
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
