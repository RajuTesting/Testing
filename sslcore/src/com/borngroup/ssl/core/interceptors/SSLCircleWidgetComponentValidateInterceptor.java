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
import com.borngroup.ssl.core.model.SSLBannerWidgetComponentModel;
import com.borngroup.ssl.core.model.SSLCircleWidgetComponentModel;
import com.borngroup.ssl.core.services.SSLMobileWidgetLimitService;

/**
 * @author tusharkansal
 *
 */
public class SSLCircleWidgetComponentValidateInterceptor implements ValidateInterceptor<SSLCircleWidgetComponentModel> {

    @Resource
    private SSLMobileWidgetLimitService sslMobileWidgetLimitService;

    @Override
    public void onValidate(final SSLCircleWidgetComponentModel model, final InterceptorContext ctx) throws InterceptorException {
        if (null != model) {
            final Map<SSLMobileWidgetAttribute, Integer> limitModelMap = getSslMobileWidgetLimitService().getMobileWidgetLimitModels(
                    Arrays.asList(SSLMobileWidgetTypeEnum.ALL, model.getComponentType()), Arrays.asList(SSLMobileWidgetAttribute.BANNERS));
            if (null != limitModelMap) {
                checkAttributeLimit(model, limitModelMap);
            }
            final List<SSLBannerWidgetComponentModel> bannerList = model.getBannerList();
            if (CollectionUtils.isNotEmpty(bannerList)) {
                checkForMediaFormat(bannerList);
            }
        }
    }

    /**
     * @param model
     * @param limitModelMap
     */
    private void checkAttributeLimit(final SSLCircleWidgetComponentModel model, final Map<SSLMobileWidgetAttribute, Integer> limitModelMap)
            throws InterceptorException {
        if (model.getBannerList() != null && limitModelMap.containsKey(SSLMobileWidgetAttribute.BANNERS)
                && model.getBannerList().size() > limitModelMap.get(SSLMobileWidgetAttribute.BANNERS).intValue()) {
            throw new InterceptorException("Banner Limit Exceeded");
        }
    }

    /**
     * @param bannerList
     * @throws InterceptorException
     */
    private void checkForMediaFormat(final List<SSLBannerWidgetComponentModel> bannerList) throws InterceptorException {
        for (final SSLBannerWidgetComponentModel banner : bannerList) {
            if (!banner.getMedia().getMediaFormat().equals(bannerList.get(0).getMedia().getMediaFormat())) {
                throw new InterceptorException(
                        " Media format used throughout all banners is not same. Please attach media with same media format.");
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
