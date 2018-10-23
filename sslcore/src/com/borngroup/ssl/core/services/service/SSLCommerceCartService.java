/**
 *
 */
package com.borngroup.ssl.core.services.service;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;

/**
 * @author nikhilbarar
 *
 */
public interface SSLCommerceCartService extends CommerceCartService {

    /**
     * @param guid
     * @param site
     * @param user
     * @return
     */
    CartModel getCartForSiteAndUserExcludingGuid(String guid, BaseSiteModel site, UserModel user);

    void setAppVersioninCart(String appVersion);

}
