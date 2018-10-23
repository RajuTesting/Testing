/**
 *
 */
package com.borngroup.ssl.core.strategies.impl;

import de.hybris.platform.commercefacades.product.data.VariantOptionData;
import de.hybris.platform.commercefacades.product.data.VariantOptionQualifierData;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.borngroup.ssl.core.services.SSLLookupService;
import com.borngroup.ssl.core.strategy.SSLVariantSortStrategy;

/**
 * @author gurkiratmohain
 *
 */
public class SSLVariantSortStrategyImpl implements SSLVariantSortStrategy {

	@Resource(name = "sslLookupService")
	SSLLookupService sslLookupService;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 *
	 * Gets the variants and compares them in order: 1. If both numeric,
	 * numeric-compare them 2. If one numeric and other non numeric, numeric is
	 * preferred 3. If both non-numeric, get the size priority map and the size
	 * with higher priority is preferred.
	 */
	@Override
	public int compare(final VariantOptionData param1, final VariantOptionData param2) {
		if (param1 == null || param2 == null) {
			return 0;
		}
		final String value1 = getVariantValue(param1);
		final String value2 = getVariantValue(param2);

		final boolean value1IsNumber = org.apache.commons.lang.math.NumberUtils.isNumber(value1);
		final boolean value2IsNumber = org.apache.commons.lang.math.NumberUtils.isNumber(value2);

		if (value1IsNumber && value2IsNumber) {
			return NumberUtils.compare(NumberUtils.toDouble(value1), NumberUtils.toDouble(value2));
		} else {
			if (value1IsNumber && !value2IsNumber) {
				return -1;
			} else if (!value1IsNumber && value2IsNumber) {
				return 1;
			} else {
				final Map<String, Integer> sizePriorityMap = sslLookupService.getSizePriorityMap();

				if (null != sizePriorityMap && MapUtils.isNotEmpty(sizePriorityMap)) {
					if ((null != value1) && (null != value2)) {
						if (sizePriorityMap.containsKey(value1) && sizePriorityMap.containsKey(value2)) {
							return NumberUtils.compare(sizePriorityMap.get(value2).intValue(),
									sizePriorityMap.get(value1).intValue());
						} else {
							if (sizePriorityMap.containsKey(value1) && !sizePriorityMap.containsKey(value2)) {
								return -1;
							} else if (!sizePriorityMap.containsKey(value1) && sizePriorityMap.containsKey(value2)) {
								return 1;
							} else {
								return (value1).compareToIgnoreCase(value2);
							}
						}
					}
				}
				return -1;
			}
		}
	}

	/**
	 *
	 * @param variant
	 * @return This method extracts the value of the last variantOptionQualifier
	 *         which is actually the size such as S / M / L etc
	 */
	protected String getVariantValue(final VariantOptionData variant) {
		String value = "";
		for (final VariantOptionQualifierData variantOptionQualifier : variant.getVariantOptionQualifiers()) {
			value = variantOptionQualifier.getValue();
		}
		return value;
	}

}
