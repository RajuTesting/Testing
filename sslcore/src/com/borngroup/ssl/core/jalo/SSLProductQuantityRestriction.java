/**
 *
 */
package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.util.localization.Localization;
import de.hybris.platform.voucher.jalo.ProductQuantityRestriction;
import de.hybris.platform.voucher.jalo.util.VoucherEntry;
import de.hybris.platform.voucher.jalo.util.VoucherEntrySet;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

/**
 * @author shilpiverma
 *
 */
public class SSLProductQuantityRestriction extends ProductQuantityRestriction {

    private static final Logger LOG = Logger.getLogger(ProductQuantityRestriction.class.getName());

    @Override
    protected String[] getMessageAttributeValues() {
        return new String[] {
                Localization.getLocalizedString("type.restriction.positive." + ((isPositiveAsPrimitive()) ? "upto" : "from")),
                getQuantity().toString() + " " + getUnit().getName(), getProductNames() };
    }

    @Override
    public VoucherEntrySet getApplicableEntries(final AbstractOrder anOrder) {
        final VoucherEntrySet entries = new VoucherEntrySet();
        final Collection restrictedProducts = getProducts();
        final boolean isPositive = isPositiveAsPrimitive();

        if ((restrictedProducts.isEmpty())) {
            long totalValue = 0L;
            long quantity = 0L;
            for (final Iterator iterator = anOrder.getAllEntries().iterator(); iterator.hasNext();) {
                final AbstractOrderEntry entry = (AbstractOrderEntry) iterator.next();
                totalValue += entry.getQuantity().longValue();
            }

            quantity = totalValue - getQuantityAsPrimitive();
            if (isPositive && quantity <= 0L && totalValue > 0) {
                entries.addAll(anOrder.getAllEntries());
            } else if (!isPositive && quantity > 0L) {
                entries.addAll(anOrder.getAllEntries());
            }
        } else {
            for (final Iterator iterator = anOrder.getAllEntries().iterator(); iterator.hasNext();) {
                final AbstractOrderEntry entry = (AbstractOrderEntry) iterator.next();
                if (!(restrictedProducts.contains(entry.getProduct()))) {
                    continue;
                }
                if (isPositive) {
                    final long quantity = entry.getQuantity().longValue() - getQuantityAsPrimitive();
                    if (quantity > 0L) {
                        continue;
                    }
                    entries.add(new VoucherEntry(entry, getQuantityAsPrimitive(), getUnit()));
                } else {
                    final long quantity = entry.getQuantity().longValue() - getQuantityAsPrimitive();
                    if (quantity <= 0L) {
                        continue;
                    }
                    entries.add(new VoucherEntry(entry, quantity, getUnit()));
                }
            }

        }

        return entries;
    }

}
