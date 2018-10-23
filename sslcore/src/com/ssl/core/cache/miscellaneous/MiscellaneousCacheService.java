/**
 *
 */
package com.ssl.core.cache.miscellaneous;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.regioncache.key.CacheKey;

import com.borngroup.ssl.core.enums.MiscellaneousCacheTypeEnum;

/**
 * @author Nagarro Dev
 *
 */
public interface MiscellaneousCacheService {

	boolean useCache(final ItemModel item);

	Object get(final CacheKey key);

	void put(final CacheKey key, final Object content);

	/**
	 * @param item
	 * @param typeCode
	 * @return CacheKey
	 */
	CacheKey getKey(final ItemModel item, final MiscellaneousCacheTypeEnum typeCode);

}
