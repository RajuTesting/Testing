package com.borngroup.ssl.core.order.calculation.impl;

import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.order.impl.DefaultCalculationService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.voucher.VoucherService;
import de.hybris.platform.voucher.model.RestrictionModel;
import de.hybris.platform.voucher.model.VoucherModel;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.services.SSLPriceService;
import com.ssl.core.model.SSLUserDomainRestrictionModel;

/**
 * <p>
 * SSLCalculationServiceImpl.java : Service implementation created to calculate best price.  * 
 * <p>
 * Created By : anupam.srivastava@nagarro.com  
 *
 * @author Ssl  
 */
public class SSLCalculationServiceImpl extends DefaultCalculationService {

    @Resource(name = "customPriceService")
    SSLPriceService sslPriceService;

    /** Model Service. */
    @Resource(name = "modelService")
    private ModelService modelService;
    
    @Resource(name = "voucherService")
    private VoucherService voucherService;
    
    @Resource(name = "cartService")
    private CartService cartService;

    @Override
    public void recalculate(final AbstractOrderModel order) throws CalculationException {
        // -----------------------------
        // first force calc entries
        calculateEntries(order, true);
        getModelService().save(order);
        // -----------------------------
        // reset own values
        final Map taxValueMap = resetAllValues(order);
        // -----------------------------
        // now recalculate totals
        calculateTotals(order, true, taxValueMap);
        // notify discounts -- needed?
        // notifyDiscountsAboutCalculation();

    }

    @Override
    public void calculateTotals(final AbstractOrderEntryModel entry, final boolean recalculate) {
        if (recalculate) {
            final AbstractOrderModel order = entry.getOrder();
            final CurrencyModel curr = order.getCurrency();
            final int digits = curr.getDigits().intValue();
            PriceRowModel priceRow=sslPriceService.getBestPrice(entry.getProduct());
           /* Added for ECD-1911*/
            double bestPrice=0;
            if(isCartCalOnOriginalPrice() && priceRow.getWasPrice().doubleValue()>0)
            {
                bestPrice=priceRow.getWasPrice().doubleValue();
            }
            else
            {
                bestPrice=priceRow.getPrice().doubleValue();
            }
            /* Added for ECD-1911*/
            entry.setBasePrice(Double.valueOf(bestPrice));
            final double totalPriceWithoutDiscount = CoreAlgorithms.round(bestPrice * entry.getQuantity().longValue(), digits);
            final double quantity = entry.getQuantity().doubleValue();
            /*
             * apply discounts (will be rounded each) convert absolute discount values in case their currency doesn't match the order
             * currency
             */
            // YTODO : use CalculatinService methods to apply discounts
            final List appliedDiscounts = DiscountValue.apply(quantity, totalPriceWithoutDiscount, digits,
                    convertDiscountValues(order, entry.getDiscountValues()), curr.getIsocode());
            entry.setDiscountValues(appliedDiscounts);
            double totalPrice = totalPriceWithoutDiscount;
            for (final Iterator it = appliedDiscounts.iterator(); it.hasNext();) {
                totalPrice -= ((DiscountValue) it.next()).getAppliedValue();
            }
            // set total price
            entry.setTotalPrice(Double.valueOf(totalPrice));
            // apply tax values too
            // YTODO : use CalculatinService methods to apply taxes
            calculateTotalTaxValues(entry);
            getModelService().save(entry);
            // Set total price again
            entry.setBasePrice(Double.valueOf(bestPrice));
            entry.setTotalPrice(Double.valueOf(totalPrice));
            modelService.save(entry);
            setCalculatedStatus(entry);
        }
    }

    private void setCalculatedStatus(final AbstractOrderEntryModel entry) {
        entry.setCalculated(Boolean.TRUE);
        getModelService().save(entry);
    }

    /**
     * Gets the voucher applied for employee
     * 
     * @return {@link VoucherModel}
     */
    private VoucherModel getVoucherAppliedForEmp()
    {
        final CartModel cartModel = cartService.getSessionCart();
        if (cartModel == null && CollectionUtils.isEmpty(cartService.getSessionCart().getEntries()))
        {
            return null;
        }
        final Collection<String> voucherCodes = voucherService
                .getAppliedVoucherCodes(cartModel);
        if (CollectionUtils.isEmpty(voucherCodes))
        {
            return null;
        }
        for (String voucherCode : voucherCodes)
        {
                final VoucherModel voucher = voucherService.getVoucher(voucherCode);
                if (voucher==null || CollectionUtils.isEmpty(voucher.getRestrictions()))
                {
                    return null;
                }
                for (RestrictionModel restriction : voucher.getRestrictions())
                {
                    if (restriction instanceof SSLUserDomainRestrictionModel)
                    {
                       return voucher;
                    }
                }
        }
        return null;
    }

    /**
     * Method for finding cart calculation on offer price or original price(was
     * price)
     * 
     * @return true if restriction on original price otherwise return false
     */
    private boolean isCartCalOnOriginalPrice()
    {
        VoucherModel voucher = getVoucherAppliedForEmp();
        if (voucher == null)
        {
            return false;
        }
        for (RestrictionModel restriction : voucher.getRestrictions())
        {
            if (restriction instanceof SSLUserDomainRestrictionModel)
            {
                return ((SSLUserDomainRestrictionModel) restriction)
                        .isOriginalPrice();
            }
        }
        return false;
    }
}
