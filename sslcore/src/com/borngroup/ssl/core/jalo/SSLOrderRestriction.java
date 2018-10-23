package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.voucher.jalo.GeneratedOrderRestriction;
import de.hybris.platform.voucher.jalo.util.VoucherEntry;
import de.hybris.platform.voucher.jalo.util.VoucherEntrySet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class SSLOrderRestriction extends GeneratedSSLOrderRestriction {
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(SSLOrderRestriction.class.getName());

	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
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
	protected boolean isFulfilledInternal(final AbstractOrder anOrder) {
		final Currency minimumOrderValueCurrency = getCurrency();
		final Currency currentOrderCurrency = anOrder.getCurrency();
		LOG.info("######SslOrderRestriction FulfilledInternal########");
		if (minimumOrderValueCurrency == null || currentOrderCurrency == null) {
			return false;
		}
		final double minimumTotal = minimumOrderValueCurrency.convert(currentOrderCurrency, getTotalAsPrimitive());
		try {
			anOrder.calculateTotals(false);
		} catch (final JaloPriceFactoryException e) {
			e.printStackTrace();
		}
		double currentTotal = 0.0;
		final Collection<Product> products = getProducts();
		final Set<String> productCodes = new HashSet<String>();
		for (final Product product : products) {
			productCodes.add(product.getCode());
		}
		for (final AbstractOrderEntry entry : anOrder.getEntries()) {
			if (productCodes.contains(entry.getProduct().getCode())) {
				currentTotal = currentTotal + entry.getTotalPrice().doubleValue();
			}
		}
		final boolean include = isInclude().booleanValue();
		if (!include) {
			currentTotal = anOrder.getSubtotal().doubleValue() - currentTotal;
		}
		if (isNetAsPrimitive() != anOrder.isNet().booleanValue()) {
			if (anOrder.isNet().booleanValue()) {
				currentTotal += anOrder.getTotalTax().doubleValue();
			} else {
				currentTotal -= anOrder.getTotalTax().doubleValue();
			}
		}
		if (!isValueofgoodsonlyAsPrimitive()) {
			currentTotal += anOrder.getDeliveryCosts();
			currentTotal += anOrder.getPaymentCosts();
		}
		if (isPositiveAsPrimitive()) {
			return currentTotal >= minimumTotal;
		}
		return currentTotal <= minimumTotal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.voucher.jalo.Restriction#isFulfilledInternal(de.hybris
	 * .platform.jalo.product.Product)
	 */
	@Override
	protected boolean isFulfilledInternal(final Product arg0) {
		// YTODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.voucher.jalo.Restriction#getApplicableEntries(de.
	 * hybris.platform.jalo.order.AbstractOrder)
	 */
	@Override
	public VoucherEntrySet getApplicableEntries(final AbstractOrder anOrder) {
		// YTODO Auto-generated method stub
		final VoucherEntrySet entries = new VoucherEntrySet();
		LOG.info("######SslOrderRestriction ApplicableEntries########");
		if (isFulfilledInternal(anOrder)) {
			final Collection<Product> products = getProducts();
			final Set<String> productCodes = new HashSet<String>();
			final boolean include = isInclude().booleanValue();
			for (final Product product : products) {
				productCodes.add(product.getCode());
			}
			for (final AbstractOrderEntry entry : anOrder.getEntries()) {
				final Product product = entry.getProduct();
				if (include) {
				if (productCodes.contains(product.getCode())) {
					entries.add(new VoucherEntry(entry, entry.getQuantity().longValue(), entry.getUnit()));
				}
				}
				else
				{
					if (!productCodes.contains(product.getCode())) {
						entries.add(new VoucherEntry(entry, entry.getQuantity().longValue(), entry.getUnit()));
					}
				}
			}
		}

		return entries;
	}
}
