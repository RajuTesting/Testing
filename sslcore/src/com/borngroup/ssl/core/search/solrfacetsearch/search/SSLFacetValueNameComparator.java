/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.search;

import de.hybris.platform.solrfacetsearch.search.FacetValue;

import java.util.Comparator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.services.SSLLookupService;

/**
 * @author gurkiratmohain
 *
 */
public class SSLFacetValueNameComparator implements Comparator<FacetValue> {
	@Resource(name = "sslLookupService")
	SSLLookupService sslLookupService;

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(SSLFacetValueNameComparator.class);

	/**
	 * Gets the Facet Values and compares them in order: 1. If both numeric,
	 * numeric-compare them 2. If one numeric and other non numeric, numeric is
	 * preferred 3. If both non-numeric, get the size priority map and the size
	 * with higher priority is preferred.
	 */
	@Override
	public int compare(final FacetValue value1, final FacetValue value2) {
		if (value1 == null || value2 == null) {
			return 0;
		}

		final boolean value1IsNumber = org.apache.commons.lang.math.NumberUtils.isNumber(value1.getName());
		final boolean value2IsNumber = org.apache.commons.lang.math.NumberUtils.isNumber(value2.getName());

		if (value1IsNumber && value2IsNumber) {
			return NumberUtils.compare(NumberUtils.toDouble(value1.getName()), NumberUtils.toDouble(value2.getName()));
		} else {
			if (value1IsNumber && !value2IsNumber) {
				return -1;
			} else if (!value1IsNumber && value2IsNumber) {
				return 1;
			} else {
				final Map<String, Integer> sizePriorityMap = sslLookupService.getSizePriorityMap();

				if (null != sizePriorityMap && MapUtils.isNotEmpty(sizePriorityMap)) {
					if ((null != value1.getName()) && (null != value2.getName())) {
						if (sizePriorityMap.containsKey(value1.getName())
								&& sizePriorityMap.containsKey(value2.getName())) {
							return NumberUtils.compare(sizePriorityMap.get(value2.getName()).intValue(),
									sizePriorityMap.get(value1.getName()).intValue());
						} else {
							if (sizePriorityMap.containsKey(value1.getName())
									&& !sizePriorityMap.containsKey(value2.getName())) {
								return -1;
							} else if (!sizePriorityMap.containsKey(value1.getName())
									&& sizePriorityMap.containsKey(value2.getName())) {
								return 1;
							} else {
								return (value1.getName()).compareToIgnoreCase(value2.getName());
							}
						}
					}
				}
				return -1;
			}
		}

	}

}
