/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.wishlist2.impl.daos.impl.DefaultWishlist2Dao;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.dao.SslWishlist2Dao;

/**
 * @author Nagarro-Dev
 *
 */
public class SslWishlist2DaoImpl extends DefaultWishlist2Dao implements SslWishlist2Dao {

    private PagedFlexibleSearchService pagedFlexibleSearchService;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.borngroup.ssl.core.dao.SslWishlist2Dao#getPagedWishlistData(de.hybris.platform.commerceservices.search.pagedata.PageableData)
     */
    @Override
    public SearchPageData<ProductModel> getPagedWishlistData(final PageableData pageable, final CustomerModel currentCustomer) {
        validateParameterNotNull(currentCustomer, "Customer model cannot be null");
        validateParameterNotNull(pageable, "PageableData must not be null");
        SearchPageData<ProductModel> searchPageData = new SearchPageData<>();
        final Map<String, Object> queryParams = new HashMap<String, Object>();
        final Wishlist2Model wishlist = findDefaultWishlist(currentCustomer);

        if (null != wishlist) {
            queryParams.put("customer", currentCustomer);
            queryParams.put("defaultWishlist", Boolean.TRUE);
            queryParams.put("wishlist", wishlist);

            final String query = "SELECT {w2e.product} FROM {Wishlist2Entry AS w2e} WHERE {w2e:wishlist}=?wishlist";
            searchPageData = pagedFlexibleSearchService.search(query, queryParams, pageable);
        }
        return searchPageData;
    }

    protected PagedFlexibleSearchService getPagedFlexibleSearchService() {
        return pagedFlexibleSearchService;
    }

    @Required
    public void setPagedFlexibleSearchService(final PagedFlexibleSearchService pagedFlexibleSearchService) {
        this.pagedFlexibleSearchService = pagedFlexibleSearchService;
    }

}
