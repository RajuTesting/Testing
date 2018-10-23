/**
 *
 */
package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.ExtensibleItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.price.Discount;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.voucher.jalo.SerialVoucher;
import de.hybris.platform.voucher.jalo.Voucher;
import de.hybris.platform.voucher.jalo.util.VoucherValue;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author NagarroDev
 *
 */
public class SSLSerialVoucher extends SerialVoucher {

    private static final Logger LOG = Logger.getLogger(SSLSerialVoucher.class);

    @Override
    protected Item createItem(final SessionContext ctx, final ComposedType type, final Item.ItemAttributeMap allAttributes)
            throws JaloBusinessException {
        String code = (String) allAttributes.get("code");
        if (StringUtils.isNotBlank(code)) {
            code = code.toUpperCase();
            allAttributes.put("code", code);
        }
        return super.createItem(ctx, type, allAttributes);
    }

    @Override
    public VoucherValue getVoucherValue(final AbstractOrder anOrder) {
        final Iterator discounts = anOrder.getDiscounts().iterator();
        boolean found = false;
        boolean applyFreeShipping = false;
        String voucherCode = StringUtils.EMPTY;
        while ((discounts.hasNext()) && (!(found))) {
            final Discount discount = (Discount) discounts.next();
            if (!(discount instanceof Voucher)) {
                continue;
            }
            final Voucher voucher = (Voucher) discount;
            if ((!(voucher.isApplicable(anOrder))) || (!(voucher.isFreeShippingAsPrimitive()))) {
                voucherCode = voucher.getCode();
                continue;
            }
            if (voucher.equals(this)) {
                applyFreeShipping = true;
            }
            found = true;

        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Processed voucher: [" + getCode() + "] " + getName());
            LOG.debug("Free shipping is: " + isFreeShippingAsPrimitive());
            LOG.debug("Free shipping will apply: " + applyFreeShipping);
        }

        VoucherValue applicableValue = getApplicableValue(anOrder);
        Object totalDiscounts = ((ExtensibleItem) anOrder).getProperty("totalDiscounts");

        double voucherValue = 0.0;
        if (anOrder.getGlobalDiscountValues() != null) {
            final List<DiscountValue> discountValues = anOrder.getGlobalDiscountValues();
            for (final DiscountValue discountValue : discountValues) {
                if (discountValue.getCode() != null && discountValue.getCode().equals(voucherCode)) {
                    voucherValue = discountValue.getValue();
                }
            }
        }
        totalDiscounts = (Double) totalDiscounts - voucherValue;
        final Double totalDiscountsDouble = null != totalDiscounts ? ((Double) totalDiscounts) : Double.valueOf(0.0D);
        applicableValue = new VoucherValue(applicableValue.getValue() - totalDiscountsDouble.doubleValue(), anOrder.getCurrency());

        final Currency resultCurrency;
        double resultValue;
        if (isAbsolute().booleanValue()) {
            resultValue = Math.min(applicableValue.getCurrency().convertAndRound(getCurrency(), applicableValue.getValue()), getValue()
                    .doubleValue());
            resultCurrency = getCurrency();
        } else {
            resultValue = applicableValue.getValue() * getValue().doubleValue() / 100.0D;
            resultCurrency = applicableValue.getCurrency();
        }
        if ((isFreeShippingAsPrimitive()) && (applyFreeShipping)) {
            resultValue += anOrder.getCurrency().convertAndRound(resultCurrency, anOrder.getDeliveryCosts());
        }
        return new VoucherValue(resultValue, resultCurrency);
    }

    @Override
    public boolean checkVoucherCode(String aVoucherCode) {
        aVoucherCode = aVoucherCode.toUpperCase();
        final boolean valid = Pattern.matches(".{3}-.{4}-.{4}-.{4}", aVoucherCode);
        if (!(valid)) {
            // log.warn("Pattern of voucher code not valid: [" + aVoucherCode + "]");
            return false;

        }

        final String voucherCode = removeDividers(aVoucherCode);
        final String code = extractCode(voucherCode);
        if (getCode().equals(code)) {
            final String clearText = voucherCode.substring(0, voucherCode.length() - 6);
            final String sig = voucherCode.substring(voucherCode.length() - 6);
            try {
                if (!(threeByteSig(clearText).equals(sig))) {
                    return false;
                }
                final int lastVoucherNumber = getLastVoucherNumber(getSession().getSessionContext());
                try {
                    final int voucherNumber = getVoucherNumber(voucherCode);
                    return ((voucherNumber >= 0) && (voucherNumber <= lastVoucherNumber));
                } catch (final Voucher.InvalidVoucherKeyException localInvalidVoucherKeyException) {
                    return false;
                }

            } catch (final NoSuchAlgorithmException e) {
                throw new JaloSystemException(e, "!!", 0);
            }
        }
        return false;
        // label158: return false;
    }

    private int getLastVoucherNumber(final SessionContext ctx) {
        final Object lastVoucherNumber = getProperty(ctx, "lastVoucherNumber");
        if (lastVoucherNumber != null) {
            return ((Integer) lastVoucherNumber).intValue();
        }
        return -1;
    }
}
