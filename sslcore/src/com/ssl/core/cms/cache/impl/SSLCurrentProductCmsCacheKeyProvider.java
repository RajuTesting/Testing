/**
 *
 */
package com.ssl.core.cms.cache.impl;

import de.hybris.platform.acceleratorcms.component.cache.impl.DefaultCmsCacheKeyProvider;
import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import de.hybris.platform.core.model.product.ProductModel;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @author Nagarro Dev
 *
 */
public class SSLCurrentProductCmsCacheKeyProvider extends DefaultCmsCacheKeyProvider {

    @Override
    public StringBuilder getKeyInternal(final HttpServletRequest request, final SimpleCMSComponentModel component) {
        final StringBuilder buffer = new StringBuilder(super.getKeyInternal(request, component));
        final ProductModel product = getRequestContextData(request).getProduct();
        if (null != product) {
            final String currentProduct = product.getPk().getLongValueAsString();
            if (StringUtils.isNotEmpty(currentProduct)) {
                buffer.append(currentProduct);
            }
        }
        return buffer;
    }

}
