package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.voucher.jalo.util.VoucherEntry;
import de.hybris.platform.voucher.jalo.util.VoucherEntrySet;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

public class SslAltBrandCodeVoucherOrderRestriction extends GeneratedSslAltBrandCodeVoucherOrderRestriction {
    @SuppressWarnings("unused")
    private final static Logger LOG = Logger.getLogger(SslAltBrandCodeVoucherOrderRestriction.class.getName());

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
     * @see de.hybris.platform.voucher.jalo.Restriction#isFulfilledInternal(de.hybris.platform.jalo.order.AbstractOrder)
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

        final boolean include = isInclude().booleanValue();
        final Collection<String> altBrandDescriptionList = getAltBrandCodeList();
        double currentTotal = 0.0;

        if (CollectionUtils.isNotEmpty(altBrandDescriptionList)) {
            for (final AbstractOrderEntry entry : anOrder.getEntries()) {
                final Product product = entry.getProduct();
                if (isApplicableProduct(product, include, altBrandDescriptionList)) {
                    currentTotal += entry.getTotalPrice().doubleValue();
                }
            }
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
     * @see de.hybris.platform.voucher.jalo.Restriction#isFulfilledInternal(de.hybris.platform.jalo.product.Product)
     */
    @Override
    protected boolean isFulfilledInternal(final Product paramProduct) {
        return false;
    }

    private boolean isApplicableProduct(final Product product, final boolean include, final Collection<String> altBrandDescriptionList) {
        try {
            if (include) {
                return altBrandDescriptionList.contains(product.getAttribute("altBrandCode"));
            } else {
                return !altBrandDescriptionList.contains(product.getAttribute("altBrandCode"));
            }
        } catch (final JaloInvalidParameterException e) {
            LOG.info("Exception occured while checking brandCode : " + e.getMessage());
        } catch (final JaloSecurityException e) {
            LOG.info("Exception occured while checking brandCode : " + e.getMessage());
        }
        return false;

    }

    @Override
    public VoucherEntrySet getApplicableEntries(final AbstractOrder anOrder) {
        // YTODO Auto-generated method stub
        final VoucherEntrySet entries = new VoucherEntrySet();
        LOG.info("######SslOrderRestriction ApplicableEntries########");
        if (isFulfilledInternal(anOrder)) {

            final boolean include = isInclude().booleanValue();
            final Collection<String> altBrandDescriptionList = getAltBrandCodeList();

            if (CollectionUtils.isNotEmpty(altBrandDescriptionList)) {
                for (final AbstractOrderEntry entry : anOrder.getEntries()) {
                    final Product product = entry.getProduct();
                    if (isApplicableProduct(product, include, altBrandDescriptionList)) {
                        entries.add(new VoucherEntry(entry, entry.getQuantity().longValue(), entry.getUnit()));
                    }
                }
            }
        }

        return entries;
    }

}
