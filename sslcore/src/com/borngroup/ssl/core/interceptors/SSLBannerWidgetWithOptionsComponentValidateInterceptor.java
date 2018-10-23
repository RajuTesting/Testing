package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.enums.SSLMobileWidgetAttribute;
import com.borngroup.ssl.core.enums.SSLMobileWidgetTypeEnum;
import com.borngroup.ssl.core.model.SSLBannerWidgetWithOptionsComponentModel;
import com.borngroup.ssl.core.model.SSLButtonWidgetComponentModel;
import com.borngroup.ssl.core.services.SSLMobileWidgetLimitService;

/**
 * @author tusharkansal
 *
 */
public class SSLBannerWidgetWithOptionsComponentValidateInterceptor
        implements ValidateInterceptor<SSLBannerWidgetWithOptionsComponentModel> {

    @Resource
    private SSLMobileWidgetLimitService sslMobileWidgetLimitService;

    @Override
    public void onValidate(final SSLBannerWidgetWithOptionsComponentModel model, final InterceptorContext ctx) throws InterceptorException {
        if (null != model) {
            final Map<SSLMobileWidgetAttribute, Integer> limitModelMap = getSslMobileWidgetLimitService().getMobileWidgetLimitModels(
                    Arrays.asList(SSLMobileWidgetTypeEnum.ALL, model.getComponentType()), Arrays.asList(SSLMobileWidgetAttribute.BUTTONS));
            if (null != limitModelMap) {
                checkAttributeLimit(model, limitModelMap);
            }
            final List<SSLButtonWidgetComponentModel> buttonList = model.getButtonList();
            if (CollectionUtils.isEmpty(buttonList) || buttonList.size() < 2) {
                throw new InterceptorException("The widget must have atleast two button.");
            }
        }
    }

    /**
     * @param model
     * @param limitModelMap
     */
    private void checkAttributeLimit(final SSLBannerWidgetWithOptionsComponentModel model,
            final Map<SSLMobileWidgetAttribute, Integer> limitModelMap) throws InterceptorException {
        if (model.getButtonList() != null && limitModelMap.containsKey(SSLMobileWidgetAttribute.BUTTONS)
                && model.getButtonList().size() > limitModelMap.get(SSLMobileWidgetAttribute.BUTTONS).intValue()) {
            throw new InterceptorException("Button Limit Exceeded");
        }
    }

    /**
     * @return the sslMobileWidgetLimitService
     */
    public SSLMobileWidgetLimitService getSslMobileWidgetLimitService() {
        return sslMobileWidgetLimitService;
    }

}
