/**
 *
 */
package com.ssl.core.cache.miscellaneous.key.provider.impl;

import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.regioncache.key.CacheKey;

import com.ssl.core.cache.miscellaneous.key.MiscellaneousCacheKey;
import com.ssl.core.cache.miscellaneous.key.provider.MiscellaneousCacheKeyProvider;

/**
 * @author Nagarro Dev
 *
 */
public class MiscellaneousCacheKeyProviderImpl implements MiscellaneousCacheKeyProvider {

	private CommerceCommonI18NService commerceCommonI18NService;

	/**
	 * @param item
	 * @return CacheKey
	 */
	@Override
	public CacheKey getKey(final ItemModel item, final String typeCode) {
		return new MiscellaneousCacheKey(getKeyInternal(item).toString(),
				Registry.getCurrentTenant().getTenantID(), typeCode);
	}

	/**
	 * @param item
	 * @return StringBuilder
	 */
	protected StringBuilder getKeyInternal(final ItemModel item) {
		final StringBuilder key = new StringBuilder();
		final CurrencyModel currentCurrency = getCommerceCommonI18NService().getCurrentCurrency();
		key.append(currentCurrency.getIsocode());
		final LanguageModel currentLanguage = getCommerceCommonI18NService().getCurrentLanguage();
		key.append(currentLanguage.getIsocode());
		return key;
	}

	/**
	 * @return the commerceCommonI18NService
	 */
	public CommerceCommonI18NService getCommerceCommonI18NService() {
		return commerceCommonI18NService;
	}

	/**
	 * @param commerceCommonI18NService
	 *            the commerceCommonI18NService to set
	 */
	public void setCommerceCommonI18NService(final CommerceCommonI18NService commerceCommonI18NService) {
		this.commerceCommonI18NService = commerceCommonI18NService;
	}

}
