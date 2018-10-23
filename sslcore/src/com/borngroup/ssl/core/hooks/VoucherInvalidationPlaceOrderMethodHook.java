/**
 * 
 */
package com.borngroup.ssl.core.hooks;

import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.voucher.VoucherService;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author midhun.bose
 * 
 */
public class VoucherInvalidationPlaceOrderMethodHook implements CommercePlaceOrderMethodHook
{
    @Autowired
    private VoucherService voucherService;

    /**
     * @param voucherService
     *            the voucherService to set
     */
    public void setVoucherService(final VoucherService voucherService)
    {
        this.voucherService = voucherService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook#afterPlaceOrder(de.hybris.platform
     * .commerceservices.service.data.CommerceCheckoutParameter,test
     * de.hybris.platform.commerceservices.service.data.CommerceOrderResult)
     */
    @Override
    public void afterPlaceOrder(final CommerceCheckoutParameter arg0, final CommerceOrderResult arg1)
    {
        try
        {
            voucherService.afterOrderCreation(arg1.getOrder(), arg0.getCart());
        }
        catch (final Exception e)
        {
            //e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook#beforePlaceOrder(de.hybris.platform
     * .commerceservices.service.data.CommerceCheckoutParameter)
     */
    @Override
    public void beforePlaceOrder(final CommerceCheckoutParameter arg0)
    {
        // YTODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook#beforeSubmitOrder(de.hybris.platform
     * .commerceservices.service.data.CommerceCheckoutParameter,
     * de.hybris.platform.commerceservices.service.data.CommerceOrderResult)
     */
    @Override
    public void beforeSubmitOrder(final CommerceCheckoutParameter arg0, final CommerceOrderResult arg1)
    {
        // YTODO Auto-generated method stub

    }

}
