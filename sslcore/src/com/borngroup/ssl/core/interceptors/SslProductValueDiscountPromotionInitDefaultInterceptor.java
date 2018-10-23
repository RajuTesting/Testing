/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import com.borngroup.ssl.core.model.SslProductValueDiscountPromotionModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import org.springframework.context.MessageSource;

import javax.annotation.Resource;

/**
 * @author nikhil.jagtiani
 *
 */
public class SslProductValueDiscountPromotionInitDefaultInterceptor implements
        InitDefaultsInterceptor<SslProductValueDiscountPromotionModel> {

    @Resource(name = "coreMessageSource")
    private MessageSource coreMessageSource;

    @Resource(name = "i18nService")
    private I18NService i18nService;

    @Override
    public void onInitDefaults(final SslProductValueDiscountPromotionModel paramMODEL,
            final InterceptorContext paramInterceptorContext) throws InterceptorException {
        paramMODEL
                .setMessageCouldHaveFired(coreMessageSource.getMessage(
                        "type.sslproductvaluediscountpromotion.messageCouldHaveFired.defaultvalue", null,
                        i18nService.getCurrentLocale()));
        paramMODEL.setMessageFired(coreMessageSource.getMessage(
                "type.sslproductvaluediscountpromotion.messageFired.defaultvalue", null, i18nService.getCurrentLocale()));
    }

}
