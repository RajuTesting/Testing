package com.borngroup.ssl.core.factories;

import com.google.common.collect.Iterables;
import de.hybris.platform.commerceservices.order.dao.CommerceCartDao;
import de.hybris.platform.commerceservices.order.dao.impl.DefaultCommerceCartDao;
import de.hybris.platform.commerceservices.strategies.NetGrossStrategy;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.order.impl.DefaultCartFactory;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.apache.log4j.Logger;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * Overrides default cart creation to check if already cart with entries is present.
 * Default behaviour for anonymous user.
 * In case of logged in user. Returns last cart with entries.
 */
public class SslCommerceCartFactory extends DefaultCartFactory
{
	private NetGrossStrategy netGrossStrategy;
	private BaseSiteService baseSiteService;
	private BaseStoreService baseStoreService;
	private KeyGenerator guidKeyGenerator;
	private ModelService modelService;
	private UserService userService;
	private CommonI18NService commonI18NService;
	private KeyGenerator keyGenerator;
	private CommerceCartDao commerceCartDao;

	private static final Logger LOG = Logger.getLogger(SslCommerceCartFactory.class);
	private static final String BASE_SITE = "ssl";

	@Override
	public CartModel createCart()
	{
		final CartModel cart = createCartInternal();
		modelService.save(cart);
		return cart;
	}

	@Override
	protected CartModel createCartInternal()
	{
		final CartModel cart = this.createCartForUser();
		cart.setNet(Boolean.valueOf(getNetGrossStrategy().isNet()));
		cart.setSite(getBaseSiteService().getCurrentBaseSite());
		cart.setStore(getBaseStoreService().getCurrentBaseStore());
		cart.setGuid(getGuidKeyGenerator().generate().toString());
		return cart;
	}

	private CartModel createCartForUser() {
		final UserModel user = userService.getCurrentUser();
		Collection<CartModel> userCarts = null;
		CartModel cart;
		if(userService.isAnonymousUser(user)) {
			cart = getLastCart(null);
		}
		else {
			userCarts = commerceCartDao.getCartsForSiteAndUser(getBaseSiteService().getBaseSiteForUID(BASE_SITE), user);
			if (userCarts.size() == 1) {
			cart = Iterables.getOnlyElement(userCarts, null);
			} else {
				cart = getLastCart(userCarts);
			}
		}
		return cart;
	}

	/**
	 * Returns last cart with entries.
	 * In case of no such cart, we return new empty cart.
	 * Scenario to handle - If there are currently >2 non empty carts.
	 * And last one is used to place order, then the previous one will be returned.
	 *
	 * @param userCarts
	 * @return
	 */
	private CartModel getLastCart(final Collection<CartModel> userCarts) {
		CartModel cart = null;
		if (userCarts != null) {
			for (CartModel oldCart : userCarts) {
				//exchange carts should not be considered.
				if(oldCart.getUserCart().booleanValue() && CollectionUtils.isNotEmpty(oldCart.getEntries())) {
					//first non empty cart is restored
					if(LOG.isDebugEnabled()){
						LOG.debug(String.format("Cart with entries found: %s, for user: %s",
								oldCart.getCode(), oldCart.getUser().getUid()));
					}
					cart = oldCart;
					break;
				}
			}
		}
		if(cart == null){
			final String cartModelTypeCode = Config.getString(JaloSession.CART_TYPE, "Cart");
			cart = modelService.create(cartModelTypeCode);
			cart.setCode(String.valueOf(keyGenerator.generate()));
			final UserModel user = userService.getCurrentUser();
			cart.setUser(user);
			final CurrencyModel currency = commonI18NService.getCurrentCurrency();
			cart.setCurrency(currency);
			cart.setDate(new Date());
		}
		return cart;
	}

	protected NetGrossStrategy getNetGrossStrategy()
	{
		return netGrossStrategy;
	}

	@Required
	public void setNetGrossStrategy(final NetGrossStrategy netGrossStrategy)
	{
		this.netGrossStrategy = netGrossStrategy;
	}

	protected BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService siteService)
	{
		this.baseSiteService = siteService;
	}

	protected BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(final BaseStoreService service)
	{
		this.baseStoreService = service;
	}

	protected KeyGenerator getGuidKeyGenerator()
	{
		return guidKeyGenerator;
	}

	@Required
	public void setGuidKeyGenerator(final KeyGenerator guidKeyGenerator)
	{
		this.guidKeyGenerator = guidKeyGenerator;
	}

	@Required
	public void setModelService(final ModelService modelService) {
		this.modelService = modelService;
	}

	@Required
	public void setUserService(final UserService userService) {
		this.userService = userService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService) {
		this.commonI18NService = commonI18NService;
	}

	@Required
	public void setKeyGenerator(final KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	@Required
	public void setCommerceCartDao(CommerceCartDao commerceCartDao) {
		this.commerceCartDao = commerceCartDao;
	}
}
