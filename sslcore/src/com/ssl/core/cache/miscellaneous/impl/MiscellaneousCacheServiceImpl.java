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
package com.ssl.core.cache.miscellaneous.impl;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.regioncache.CacheController;
import de.hybris.platform.regioncache.CacheValueLoadException;
import de.hybris.platform.regioncache.CacheValueLoader;
import de.hybris.platform.regioncache.key.CacheKey;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.enums.MiscellaneousCacheTypeEnum;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.ssl.core.cache.miscellaneous.MiscellaneousCacheService;
import com.ssl.core.cache.miscellaneous.key.provider.MiscellaneousCacheKeyProvider;

public class MiscellaneousCacheServiceImpl implements MiscellaneousCacheService {
	private static final String MISCELLANEOUS_CACHE_ENABLED_KEY = "miscellaneous.cache.enabled";

	private Map<String, MiscellaneousCacheKeyProvider<? extends ItemModel>> cacheKeyProviders;
	private ConfigurationService configurationService;
	private CacheController cacheController;

	private final Supplier<Boolean> useCache = Suppliers.memoizeWithExpiration(new Supplier<Boolean>() {
		@Override
		public Boolean get() {
			return Boolean.valueOf(
					getConfigurationService().getConfiguration().getBoolean(MISCELLANEOUS_CACHE_ENABLED_KEY, false));
		}
	}, 1, TimeUnit.MINUTES);

	@Override
	public CacheKey getKey(final ItemModel item, final MiscellaneousCacheTypeEnum typeCode) {
		if (null != typeCode) {
			final MiscellaneousCacheKeyProvider provider = getCacheKeyProviders().get(typeCode.getCode());
			if (provider != null) {
				return provider.getKey(item, typeCode.getCode());
			}
		}
		return null;
	}

	@Override
	public Object get(final CacheKey key) {
		return getCacheController().get(key);
	}

	@Override
	public void put(final CacheKey key, final Object content) {
		getCacheController().getWithLoader(key, new MiscellaneousCacheValueLoader(content));
	}

	@Override
	public boolean useCache(final ItemModel item) {
		return null != item && useCacheInternal();
	}

	protected Supplier<Boolean> getUseCache() {
		return useCache;
	}

	protected boolean useCacheInternal() {
		return Boolean.TRUE.equals(getUseCache().get());
	}

	/**
	 * @return the cacheKeyProviders
	 */
	public Map<String, MiscellaneousCacheKeyProvider<? extends ItemModel>> getCacheKeyProviders() {
		return cacheKeyProviders;
	}

	/**
	 * @param cacheKeyProviders
	 *            the cacheKeyProviders to set
	 */
	public void setCacheKeyProviders(
			final Map<String, MiscellaneousCacheKeyProvider<? extends ItemModel>> cacheKeyProviders) {
		this.cacheKeyProviders = cacheKeyProviders;
	}

	protected ConfigurationService getConfigurationService() {
		return configurationService;
	}

	@Required
	public void setConfigurationService(final ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	protected CacheController getCacheController() {
		return cacheController;
	}

	@Required
	public void setCacheController(final CacheController cacheController) {
		this.cacheController = cacheController;
	}

	static class MiscellaneousCacheValueLoader implements CacheValueLoader<Object> {
		private final Object content;

		public MiscellaneousCacheValueLoader(final Object content) {
			this.content = content;
		}

		@Override
		public Object load(final CacheKey paramCacheKey) throws CacheValueLoadException {
			return content;
		}
	}

}
