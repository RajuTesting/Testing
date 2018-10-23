/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;
import de.hybris.platform.util.Config;
import de.hybris.platform.voucher.model.VoucherModel;

import org.apache.commons.collections.CollectionUtils;

/**
 * Remove Interceptor: To check that the vocuhers who have any order(s)/cart(s) associated with it cannot be deleted.
 *
 * @author Nagarro-Dev
 *
 */
public class SslVoucherRemoveInterceptor implements RemoveInterceptor<VoucherModel> {

    private static final String DELETE_VOUCHER_EXCEPTION_MESSAGE = "delete.voucher.exception.message";

    @Override
    public void onRemove(final VoucherModel model, final InterceptorContext context) throws InterceptorException {
        if (CollectionUtils.isNotEmpty(model.getOrders())) {
            throw new InterceptorException(Config.getString(DELETE_VOUCHER_EXCEPTION_MESSAGE, "\"This voucher cannot be deleted\""));
        }
    }
}
