/**
 *
 */
package com.ssl.core.cache.miscellaneous.key.provider.blogs;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;

import com.ssl.core.cache.miscellaneous.key.provider.impl.MiscellaneousCacheKeyProviderImpl;

/**
 * @author Nagarro Dev
 *
 */
public class SSLBlogCacheKeyProvider extends MiscellaneousCacheKeyProviderImpl {

	/**
	 * @param item
	 * @return StringBuilder
	 */
	@Override
	protected StringBuilder getKeyInternal(final ItemModel item) {
		final StringBuilder key = new StringBuilder(super.getKeyInternal(item));
		if (item instanceof ProductModel) {
			key.append(((ProductModel) item).getWordPressTags().toString().toLowerCase().replaceAll(",", "")
					.replaceAll(" ", ""));
		}
		return key;
	}
}