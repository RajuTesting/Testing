/**
 *
 */
package de.hybris.platform.voucher.jalo ;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;

import com.borngroup.ssl.core.jalo.SSLGenericCondition;

import de.hybris.platform.core.GenericCondition;
import de.hybris.platform.core.GenericQuery;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.Cart;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.voucher.constants.GeneratedVoucherConstants;
import org.apache.log4j.Logger;

/**
 * @author shilpiverma
 *
 */
public class SSLVoucherManager extends VoucherManager {
    private static final Logger LOG = Logger.getLogger(SSLVoucherManager.class);
    @Override
    public Collection getPromotionVouchers(final String voucherCode) {
        return getSession().search(
                new GenericQuery(GeneratedVoucherConstants.TC.PROMOTIONVOUCHER, SSLGenericCondition.caseInSensitive("voucherCode",
                        voucherCode))).getResult();
    }

    @Override
    public Collection getSerialVouchers(String aVoucherCode) {
        aVoucherCode = aVoucherCode.toUpperCase();
        final String code = SerialVoucher.extractCode(aVoucherCode);
        if (code == null) {
            return Collections.EMPTY_SET;
        }
        final Collection ret = new LinkedHashSet();
        final Iterator localIterator = getSession()
                .search(new GenericQuery(GeneratedVoucherConstants.TC.SERIALVOUCHER, GenericCondition.equals("code", code))).getResult()
                .iterator();
        while (localIterator.hasNext()) {

            final SerialVoucher voucher = (SerialVoucher) localIterator.next();

            if (!(voucher.checkVoucherCode(aVoucherCode))) {
                continue;
            }
            ret.add(voucher);
        }

        return Collections.unmodifiableCollection(ret);
    }
    @Override
    public boolean redeemVoucher(String aVoucherCode, Cart aCart) throws JaloPriceFactoryException {
        Voucher voucher = this.getVoucher(aVoucherCode);
        if(voucher != null){
            if (voucher.checkVoucherCode(aVoucherCode) && voucher.isReservable(aVoucherCode, (AbstractOrder)aCart)) {
                aCart.addDiscount(voucher);
                //aCart.recalculate();
                LOG.info(String.format("----Discount %s was added to Cart %s", aVoucherCode, aCart.getCode()));
                Collection<String> appliedVoucherCodes = new LinkedHashSet(VoucherManager.getInstance().getAppliedVoucherCodes(aCart));
                appliedVoucherCodes.add(aVoucherCode);
                VoucherManager.getInstance().setAppliedVoucherCodes(aCart, appliedVoucherCodes);
                LOG.info(String.format("----Discount %s was registered as applied to Cart %s", aVoucherCode, aCart.getCode()));
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    @Override
    public void releaseVoucher(String aVoucherCode, Cart aCart) throws JaloPriceFactoryException {
        Voucher voucher = this.getVoucher(aVoucherCode);
        if (voucher != null) {
            if (voucher.checkVoucherCode(aVoucherCode)) {
                aCart.removeDiscount(voucher);
                //aCart.recalculate();
                LOG.info(String.format("----Discount %s was removed from Cart %s", aVoucherCode, aCart.getCode()));
                Collection<String> appliedVoucherCodes = new LinkedHashSet(VoucherManager.getInstance().getAppliedVoucherCodes(aCart));
                appliedVoucherCodes.remove(aVoucherCode);
                VoucherManager.getInstance().setAppliedVoucherCodes(aCart, appliedVoucherCodes);
                LOG.info(String.format("----Discount %s was registered as removed from Cart %s", aVoucherCode, aCart.getCode()));
            }
        }
    }
}
