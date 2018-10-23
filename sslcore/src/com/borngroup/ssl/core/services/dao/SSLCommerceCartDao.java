/**
 *
 */
package com.borngroup.ssl.core.services.dao;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.order.dao.CommerceCartDao;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;

/**
 * @author nikhilbarar
 *
 */
public interface SSLCommerceCartDao extends CommerceCartDao {

    /**
     * @param guid
     * @param site
     * @param user
     * @return
     */
    CartModel getCartForSiteAndUserExcludingGuid(String guid, BaseSiteModel site, UserModel user);

}
