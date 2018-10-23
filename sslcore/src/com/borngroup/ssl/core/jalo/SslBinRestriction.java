package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;

import org.apache.log4j.Logger;

public class SslBinRestriction extends GeneratedSslBinRestriction {
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(SslBinRestriction.class
			.getName());

	@Override
	protected Item createItem(final SessionContext ctx,
			final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException {
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem(ctx, type, allAttributes);
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.voucher.jalo.Restriction#isFulfilledInternal(de.hybris
	 * .platform.jalo.order.AbstractOrder)
	 */
	@Override
	protected boolean isFulfilledInternal(final AbstractOrder order) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.voucher.jalo.Restriction#isFulfilledInternal(de.hybris
	 * .platform.jalo.product.Product)
	 */
	@Override
	protected boolean isFulfilledInternal(final Product product) {

		return true;
	}

}
