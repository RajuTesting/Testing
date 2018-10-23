/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.LoadInterceptor;
import de.hybris.platform.servicelayer.model.ItemModelContextImpl;
import de.hybris.platform.servicelayer.model.ModelContextUtils;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.ApparelProductModel;

/**
 * @author tejsharma
 *
 */
public class SSLApparelProductLoadInterceptor implements LoadInterceptor {
    private static final Logger LOG = Logger.getLogger(SSLApparelProductLoadInterceptor.class);

    @Override
    public void onLoad(final Object model, final InterceptorContext ctx) throws InterceptorException {
        if (model instanceof ApparelProductModel) {
            final ApparelProductModel productModel = (ApparelProductModel) model;
            if (!ctx.isNew(model)) {
                try {
                    final ItemModelContextImpl imp = (ItemModelContextImpl) ModelContextUtils.getItemModelContext(productModel);
                    imp.getValueHistory().loadOriginalValue(ApparelProductModel.APPROVALSTATUS, productModel.getApprovalStatus());
                    imp.getValueHistory().loadOriginalValue(ApparelProductModel.APPROVALDATE, productModel.getApprovalDate());
                } catch (final Exception e) {
                    LOG.error("Exception occurred in ApparelProductModel's load interceptor with message: " + e.getMessage());
                }
            }
        }
    }
}
