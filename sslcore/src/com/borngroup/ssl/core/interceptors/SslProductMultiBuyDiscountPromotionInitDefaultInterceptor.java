/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;

import com.borngroup.ssl.core.model.SslProductMultiBuyDiscountPromotionModel;

/**
 * @author shilpaverma
 *
 */
public class SslProductMultiBuyDiscountPromotionInitDefaultInterceptor implements
        InitDefaultsInterceptor<SslProductMultiBuyDiscountPromotionModel> {

    @Resource(name = "coreMessageSource")
    private MessageSource coreMessageSource;

    @Resource(name = "i18nService")
    private I18NService i18nService;

    /*
     * (non-Javadoc)
     * 
     * @see de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor#onInitDefaults(java.lang.Object,
     * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
     */
    @Override
    public void onInitDefaults(final SslProductMultiBuyDiscountPromotionModel paramMODEL, final InterceptorContext paramInterceptorContext)
            throws InterceptorException {
        paramMODEL.setMessageCouldHaveFired(coreMessageSource.getMessage(
                "type.sslproductmultibuydiscountpromotion.messageCouldHaveFired.defaultvalue", null, i18nService.getCurrentLocale()));
        paramMODEL.setMessageFired(coreMessageSource.getMessage("type.sslproductmultibuydiscountpromotion.messageFired.defaultvalue", null,
                i18nService.getCurrentLocale()));
    }

}
