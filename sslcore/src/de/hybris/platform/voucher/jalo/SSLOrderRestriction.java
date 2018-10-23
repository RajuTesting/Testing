package de.hybris.platform.voucher.jalo;

import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.AbstractOrder;

import org.apache.log4j.Logger;

public class SSLOrderRestriction extends OrderRestriction {
    private static final Logger LOG = Logger.getLogger(SSLOrderRestriction.class);

    @Override
    protected boolean isFulfilledInternal(final AbstractOrder anOrder) {
        final Currency minimumOrderValueCurrency = this.getCurrency();
        final Currency currentOrderCurrency = anOrder.getCurrency();
        if (minimumOrderValueCurrency != null && currentOrderCurrency != null) {
            final double minimumTotal = minimumOrderValueCurrency.convert(currentOrderCurrency, this.getTotalAsPrimitive());

            double currentTotal = anOrder.getTotalPrice().doubleValue();
            LOG.info("##############################currentTotal::" + currentTotal + "###################");
            if (this.isNetAsPrimitive() != anOrder.isNet().booleanValue()) {
                if (anOrder.isNet().booleanValue()) {
                    currentTotal += anOrder.getTotalTax().doubleValue();
                } else {
                    currentTotal -= anOrder.getTotalTax().doubleValue();
                }
            }

            if (!this.isValueofgoodsonlyAsPrimitive()) {
                currentTotal += anOrder.getDeliveryCosts();
                currentTotal += anOrder.getPaymentCosts();
            }

            return this.isPositiveAsPrimitive() ? currentTotal >= minimumTotal : currentTotal <= minimumTotal;
        } else {
            return false;
        }
    }
}