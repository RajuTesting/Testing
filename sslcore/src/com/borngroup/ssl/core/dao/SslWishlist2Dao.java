/**
 *
 */
package com.borngroup.ssl.core.dao;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.wishlist2.impl.daos.Wishlist2Dao;

/**
 * @author Nagarro-Dev
 *
 */
public interface SslWishlist2Dao extends Wishlist2Dao {

    public SearchPageData<ProductModel> getPagedWishlistData(PageableData pageable, final CustomerModel currentCustomer);
}
