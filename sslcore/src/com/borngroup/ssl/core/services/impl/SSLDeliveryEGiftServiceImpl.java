/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import com.borngroup.ssl.core.services.SSLDeliveryEGiftService;


/**
 * @author kkanagaraj
 *
 */
public class SSLDeliveryEGiftServiceImpl implements SSLDeliveryEGiftService
{


    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource(name = "cartService")
    private CartService cartService;

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.services.SSLPaymentTransactionService#saveCreditCardTransaction(java.util.Map)
     */
    @Override
    public void saveEGiftInformations(final CartData cartData)
    {
        final CartModel cartModel = cartService.getSessionCart();
        cartModel.setEGiftRecipientFirstName(cartData.getEGiftRecipientFirstName());
        cartModel.setEGiftRecipientLastName(cartData.getEGiftRecipientLastName());
        cartModel.setEGiftRecipientEmail(cartData.getEGiftRecipientEmail());
        modelService.save(cartModel);
        modelService.refresh(cartModel);
        cartService.setSessionCart(cartModel);
    }

}
