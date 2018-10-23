/**
 *
 */
package com.borngroup.ssl.core.order.strategies.impl;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceUpdateCartEntryStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;

import java.util.Date;

/**
 * @author S54606
 *
 */
public class SSLCommerceUpdateCartEntryStrategy extends DefaultCommerceUpdateCartEntryStrategy {

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commerceservices.order.impl.
	 * DefaultCommerceUpdateCartEntryStrategy#afterUpdateCartEntry(de.hybris.
	 * platform.commerceservices.service.data.CommerceCartParameter,
	 * de.hybris.platform.commerceservices.order.CommerceCartModification)
	 */
	@Override
	protected void afterUpdateCartEntry(final CommerceCartParameter parameter, final CommerceCartModification result) {
		// YTODO Auto-generated method stub
		super.afterUpdateCartEntry(parameter, result);
		final CartModel cartModel = parameter.getCart();
		cartModel.setLastUpdatedTime(new Date());
		cartModel.setIsApplicableForAbondedNotify(true);
		getModelService().save(cartModel);
	}
}
