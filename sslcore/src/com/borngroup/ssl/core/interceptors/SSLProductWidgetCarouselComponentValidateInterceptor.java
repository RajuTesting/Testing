package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;

import com.borngroup.ssl.core.enums.SSLMobileWidgetAttribute;
import com.borngroup.ssl.core.enums.SSLMobileWidgetTypeEnum;
import com.borngroup.ssl.core.model.SSLProductWidgetCarouselComponentModel;
import com.borngroup.ssl.core.services.SSLMobileWidgetLimitService;

/**
 * @author gokulpandey
 *
 */
public class SSLProductWidgetCarouselComponentValidateInterceptor implements ValidateInterceptor<SSLProductWidgetCarouselComponentModel> {

    @Resource
    private SSLMobileWidgetLimitService sslMobileWidgetLimitService;

    @Override
    public void onValidate(final SSLProductWidgetCarouselComponentModel model, final InterceptorContext ctx) throws InterceptorException {
        if (null != model) {
            final Map<SSLMobileWidgetAttribute, Integer> limitModelMap = getSslMobileWidgetLimitService().getMobileWidgetLimitModels(
                    Arrays.asList(SSLMobileWidgetTypeEnum.ALL, model.getComponentType()), Arrays.asList(SSLMobileWidgetAttribute.PRODUCTS,
                            SSLMobileWidgetAttribute.HEADING1, SSLMobileWidgetAttribute.HEADING2, SSLMobileWidgetAttribute.VIEWALL));
            if (null != limitModelMap) {
                checkAttributeLimit(model, limitModelMap);
            }
        }
    }

    /**
     * @param model
     * @param limitModelMap
     */
    private void checkAttributeLimit(final SSLProductWidgetCarouselComponentModel model,
            final Map<SSLMobileWidgetAttribute, Integer> limitModelMap) throws InterceptorException {
        if (model.getProductList() != null && limitModelMap.containsKey(SSLMobileWidgetAttribute.PRODUCTS)
                && model.getProductList().size() > limitModelMap.get(SSLMobileWidgetAttribute.PRODUCTS).intValue()) {
            throw new InterceptorException("Products Limit Exceeded");
        }
        if (model.getHeading() != null) {
            if ((model.getHeading().getHeading1() != null) && limitModelMap.containsKey(SSLMobileWidgetAttribute.HEADING1) && (model
                    .getHeading().getHeading1().getText().length() > limitModelMap.get(SSLMobileWidgetAttribute.HEADING1).intValue())) {
                throw new InterceptorException("Heading1 Text Limit Exceeded");
            }
            if ((model.getHeading().getHeading2() != null) && limitModelMap.containsKey(SSLMobileWidgetAttribute.HEADING2) && (model
                    .getHeading().getHeading2().getText().length() > limitModelMap.get(SSLMobileWidgetAttribute.HEADING2).intValue())) {
                throw new InterceptorException("Heading2 Text Limit Exceeded");
            }
            if ((model.getHeading().getViewAll() != null) && (limitModelMap.containsKey(SSLMobileWidgetAttribute.VIEWALL) && model
                    .getHeading().getViewAll().getText().length() > limitModelMap.get(SSLMobileWidgetAttribute.VIEWALL).intValue())) {
                throw new InterceptorException("View All Text Limit Exceeded");
            }
        }
    }

    /**
     * @return the sslMobileWidgetLimitService
     */
    public SSLMobileWidgetLimitService getSslMobileWidgetLimitService() {
        return sslMobileWidgetLimitService;
    }

}
