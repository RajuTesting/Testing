/**
 *
 */
package com.borngroup.ssl.core.services.wishlist.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.util.Config;
import de.hybris.platform.wishlist2.impl.DefaultWishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

import javax.annotation.Resource;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.dao.SslWishlist2Dao;
import com.borngroup.ssl.core.services.wishlist.SslWishlist2Service;

/**
 * @author Nagarro-Dev
 *
 */
public class SslWishlist2ServiceImpl extends DefaultWishlist2Service implements SslWishlist2Service {

    @Resource(name = "wishlist2Dao")
    private SslWishlist2Dao sslWishlist2Dao;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.borngroup.ssl.core.services.wishlist.SslWishlist2Service#getPagedWishlistData(de.hybris.platform.commerceservices.search.pagedata
     * .PageableData)
     */
    @Override
    public SearchPageData<ProductModel> getPagedWishlistData(final PageableData pageable, final CustomerModel currentCustomer) {
        validateParameterNotNull(currentCustomer, "Customer model cannot be null");
        validateParameterNotNull(pageable, "PageableData must not be null");
        return sslWishlist2Dao.getPagedWishlistData(pageable, currentCustomer);
    }

    @Override
    public void addWishlistEntry(final Wishlist2Model wishlist, final Wishlist2EntryModel entry) {
        super.addWishlistEntry(wishlist, entry);
        final int expectedWishlistSize = Config.getInt(SslCoreConstants.WISHLIST_SIZE, 20);
        final int actualWishlistSize = wishlist.getEntries().size();
        if (expectedWishlistSize > 0 && actualWishlistSize > expectedWishlistSize) {
            removeExcessProductsFromWishlist(wishlist, actualWishlistSize - expectedWishlistSize);
        }
    }

    /**
     * @param wishlist
     * @param productsToRemove
     */
    private void removeExcessProductsFromWishlist(final Wishlist2Model wishlist, final int productsToRemove) {
        final List<Wishlist2EntryModel> entries = wishlist.getEntries();
        final List<Wishlist2EntryModel> excessEntries = entries.subList(0, productsToRemove);
        excessEntries.stream().forEach(entry -> removeWishlistEntry(wishlist, entry));
    }

}
