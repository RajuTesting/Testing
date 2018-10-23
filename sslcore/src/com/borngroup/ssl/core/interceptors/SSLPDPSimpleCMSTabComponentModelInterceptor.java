package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import org.apache.commons.lang.StringUtils;

import com.borngroup.ssl.core.exceptions.DefaultInterceptorException;
import com.borngroup.ssl.core.model.SSLPDPSimpleCMSTabComponentModel;

/**
 * @author Nagarro
 */
public class SSLPDPSimpleCMSTabComponentModelInterceptor implements ValidateInterceptor<SSLPDPSimpleCMSTabComponentModel>
{

    private static final String CHARACTER_LIMIT_ERROR = "Character limit is 18";

    @Override
    public void onValidate(final SSLPDPSimpleCMSTabComponentModel model, final InterceptorContext arg1) throws InterceptorException
    {
        if (StringUtils.length(model.getTitle()) > 18) {
            throw new DefaultInterceptorException(CHARACTER_LIMIT_ERROR);
        }
    }


}
