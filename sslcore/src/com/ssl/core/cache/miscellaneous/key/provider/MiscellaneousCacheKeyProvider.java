/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.ssl.core.cache.miscellaneous.key.provider;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.regioncache.key.CacheKey;

public interface MiscellaneousCacheKeyProvider<C extends ItemModel> {
	CacheKey getKey(final C item, final String typeCode);
}
