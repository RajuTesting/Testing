/**
 *
 */
package com.borngroup.ssl.core.services.wishlist;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.wishlist2.Wishlist2Service;

/**
 * @author Nagarro-Dev
 *
 */
public interface SslWishlist2Service extends Wishlist2Service {

    /**
     *
     * @param pageable
     * @param customer
     * @return paginated wishlist data
     */
    public SearchPageData<ProductModel> getPagedWishlistData(final PageableData pageable, final CustomerModel customer);
}
