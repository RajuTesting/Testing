/**
 *
 */
package com.borngroup.ssl.core.strategy;

import de.hybris.platform.commercefacades.product.data.VariantOptionData;

import java.util.Comparator;

/**
 * @author gurkiratmohain
 *
 */
public interface SSLVariantSortStrategy extends Comparator<VariantOptionData> {
	// only the compare function of the comparator is used
}
