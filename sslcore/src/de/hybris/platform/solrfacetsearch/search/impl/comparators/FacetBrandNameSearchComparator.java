/**
 *
 */
package de.hybris.platform.solrfacetsearch.search.impl.comparators;

import de.hybris.platform.solrfacetsearch.search.FacetValue;

import java.util.Comparator;

/**
 * @author ravi2180
 *
 */

public class FacetBrandNameSearchComparator implements Comparator<FacetValue> {
    @Override
    public int compare(final FacetValue f1, final FacetValue f2) {
        final String displayValue1 = f1 == null ? null : f1.getDisplayName();
        final String displayValue2 = f2 == null ? null : f2.getDisplayName();

        if (displayValue1 == null && displayValue2 != null) {
            return -1;
        }

        if (displayValue1 != null && displayValue2 == null) {
            return 1;
        }

        if (displayValue1 == null && displayValue2 == null) {
            return 0;
        }

        return displayValue1.toLowerCase().compareTo(displayValue2.toLowerCase());
    }
}
