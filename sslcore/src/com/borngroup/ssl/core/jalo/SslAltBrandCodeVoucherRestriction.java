package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.voucher.jalo.util.VoucherEntry;
import de.hybris.platform.voucher.jalo.util.VoucherEntrySet;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import groovy.util.logging.Log;

public class SslAltBrandCodeVoucherRestriction extends GeneratedSslAltBrandCodeVoucherRestriction {
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(SslAltBrandCodeVoucherRestriction.class.getName());

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
	protected boolean isFulfilledInternal(final AbstractOrder paramAbstractOrder) {
		// YTODO Auto-generated method stub
		final boolean include = isInclude().booleanValue();
		final Collection<String> altBrandDescriptionList = getAltBrandCodeList();
		if(CollectionUtils.isNotEmpty(altBrandDescriptionList))
		{
		for (final AbstractOrderEntry entry : paramAbstractOrder.getEntries()) {
			final Product product = entry.getProduct();

			if (isApplicableProduct(product, include, altBrandDescriptionList)) {
				return true;
			}
		}
			return false;
		}
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
	protected boolean isFulfilledInternal(final Product paramProduct) {
		// YTODO Auto-generated method stub

		final boolean include = isInclude().booleanValue();
		final Collection<String> altBrandDescriptionList = getAltBrandCodeList();
		if (CollectionUtils.isNotEmpty(altBrandDescriptionList) && !isApplicableProduct(paramProduct, include, altBrandDescriptionList)) {
			return false;
		}
		return true;
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

		for (final AbstractOrderEntry entry : anOrder.getEntries()) {
			final Product product = entry.getProduct();

			if (isFulfilledInternal(product)) {
				entries.add(new VoucherEntry(entry, entry.getQuantity().longValue(), entry.getUnit()));
			}
		}

		return entries;
	}

	private boolean isApplicableProduct(final Product product, final boolean include,
			final Collection<String> altBrandDescriptionList) {
		try {
			if (include) {
				return altBrandDescriptionList.contains(product.getAttribute("altBrandCode"));
			} else {
				return !altBrandDescriptionList.contains(product.getAttribute("altBrandCode"));
			}
		} catch (final JaloInvalidParameterException e) {
			LOG.info("Exception occured while checking brandCode : "+e.getMessage());
		} catch (final JaloSecurityException e) {
			LOG.info("Exception occured while checking brandCode : "+e.getMessage());
		}
		return false;
	}
}
