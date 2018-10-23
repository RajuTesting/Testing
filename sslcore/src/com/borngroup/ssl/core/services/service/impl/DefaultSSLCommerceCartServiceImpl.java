/**
 *
 */
package com.borngroup.ssl.core.services.service.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartService;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.services.dao.SSLCommerceCartDao;
import com.borngroup.ssl.core.services.service.SSLCommerceCartService;

/**
 * @author yamuna
 *
 */
public class DefaultSSLCommerceCartServiceImpl extends DefaultCommerceCartService implements SSLCommerceCartService {
    private static final Logger LOG = Logger.getLogger(DefaultSSLCommerceCartServiceImpl.class);

    @Resource
    private ModelService modelService;

    @Resource(name = "commerceCartDao")
    private SSLCommerceCartDao sslCommerceCartDao;

    @Resource(name = "cartService")
    private CartService cartService;

    public SSLCommerceCartDao getSslCommerceCartDao() {
        return sslCommerceCartDao;
    }

    @Override
    public void recalculateCart(final CommerceCartParameter parameters) {
        final CartModel cartModel = parameters.getCart();
        final List<AbstractOrderEntryModel> removeEntries = new ArrayList<AbstractOrderEntryModel>();
        for (final AbstractOrderEntryModel entry : cartModel.getEntries()) {
            if (!hasValidPrice(entry)) {
                removeEntries.add(entry);
                LOG.info("Removing entry :" + entry.getProduct().getCode() + " from cart : " + cartModel.getCode()
                        + " as entry has invalid pricerow ");
            }
        }
        if (removeEntries.size() > 0) {
            modelService.removeAll(removeEntries);
            modelService.refresh(cartModel);
            parameters.setCart(cartModel);
        }
        super.recalculateCart(parameters);
    }

    public boolean hasValidPrice(final AbstractOrderEntryModel entry) {
        if (entry.getProduct().getEurope1Prices().size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public CartModel getCartForSiteAndUserExcludingGuid(final String guid, final BaseSiteModel site, final UserModel user) {
        validateParameterNotNull(site, "site cannot be null");
        validateParameterNotNull(user, "user cannot be null");
        validateParameterNotNull(guid, "user cannot be null");

        return getSslCommerceCartDao().getCartForSiteAndUserExcludingGuid(guid, site, user);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.service.SSLCommerceCartService#setAppVersioninCart(java.lang.String)
     */
    @Override
    public void setAppVersioninCart(final String appVersion) {
        final CartModel cart = cartService.getSessionCart();
        cart.setMobileAppVersion(appVersion);
        modelService.save(cart);
    }
}
