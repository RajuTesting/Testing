package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.enums.SSLBannerWidgetGridTypeEnum;
import com.borngroup.ssl.core.enums.SSLMobileWidgetAttribute;
import com.borngroup.ssl.core.enums.SSLMobileWidgetTypeEnum;
import com.borngroup.ssl.core.model.SSLBannerWidgetComponentModel;
import com.borngroup.ssl.core.model.SSLBannerWidgetGridComponentModel;
import com.borngroup.ssl.core.services.SSLMobileWidgetLimitService;

/**
 * @author tusharkansal
 *
 */
public class SSLBannerWidgetGridComponentValidateInterceptor implements ValidateInterceptor<SSLBannerWidgetGridComponentModel> {

    @Resource
    private SSLMobileWidgetLimitService sslMobileWidgetLimitService;

    @Override
    public void onValidate(final SSLBannerWidgetGridComponentModel model, final InterceptorContext ctx) throws InterceptorException {
        if (null != model) {
            final Map<SSLMobileWidgetAttribute, Integer> limitModelMap = getSslMobileWidgetLimitService().getMobileWidgetLimitModels(
                    Arrays.asList(SSLMobileWidgetTypeEnum.ALL, model.getComponentType()),
                    Arrays.asList(SSLMobileWidgetAttribute.HEADING1, SSLMobileWidgetAttribute.HEADING2, SSLMobileWidgetAttribute.VIEWALL));
            if (null != limitModelMap) {
                checkAttributeLimit(model, limitModelMap);
            }

            final List<SSLBannerWidgetComponentModel> bannerList = model.getBannerList();
            if (CollectionUtils.isNotEmpty(bannerList)) {
                if (null != model.getGridType() && !isBannerLimitAccurate(bannerList, model.getGridType())) {
                    throw new InterceptorException("The number of banners doesn't meets the grid size");
                }
                checkForMediaFormat(bannerList);
            }
        }
    }

    /**
     * @param model
     * @param limitModelMap
     */
    private void checkAttributeLimit(final SSLBannerWidgetGridComponentModel model,
            final Map<SSLMobileWidgetAttribute, Integer> limitModelMap) throws InterceptorException {
        if (model.getHeading() != null) {
            if ((model.getHeading().getHeading1() != null) && limitModelMap.containsKey(SSLMobileWidgetAttribute.HEADING1) && (model
                    .getHeading().getHeading1().getText().length() > limitModelMap.get(SSLMobileWidgetAttribute.HEADING1).intValue())) {
                throw new InterceptorException("Heading1 Text Limit Exceeded");
            }
            if ((model.getHeading().getHeading2() != null) && limitModelMap.containsKey(SSLMobileWidgetAttribute.HEADING2) && (model
                    .getHeading().getHeading2().getText().length() > limitModelMap.get(SSLMobileWidgetAttribute.HEADING2).intValue())) {
                throw new InterceptorException("Heading2 Text Limit Exceeded");
            }
            if ((model.getHeading().getViewAll() != null) && limitModelMap.containsKey(SSLMobileWidgetAttribute.VIEWALL) && (model
                    .getHeading().getViewAll().getText().length() > limitModelMap.get(SSLMobileWidgetAttribute.VIEWALL).intValue())) {
                throw new InterceptorException("View All Text Limit Exceeded");
            }
        }
    }

    /**
     * @param bannerList
     */
    private boolean isBannerLimitAccurate(final List<SSLBannerWidgetComponentModel> bannerList,
            final SSLBannerWidgetGridTypeEnum gridType) {
        boolean result = true;
        if (gridType.getCode().equals(SSLBannerWidgetGridTypeEnum.GRID1BY2.getCode())) {
            if (bannerList.size() > 2) {
                result = false;
            }
        } else if (gridType.getCode().equals(SSLBannerWidgetGridTypeEnum.GRID1BY3.getCode())) {
            if (bannerList.size() > 3) {
                result = false;
            }
        } else if (gridType.getCode().equals(SSLBannerWidgetGridTypeEnum.GRID2BY2.getCode())) {
            if (bannerList.size() > 4) {
                result = false;
            }
        } else if (gridType.getCode().equals(SSLBannerWidgetGridTypeEnum.GRID2BY3.getCode())) {
            if (bannerList.size() > 6) {
                result = false;
            }
        } else if (gridType.getCode().equals(SSLBannerWidgetGridTypeEnum.GRID3BY2.getCode())) {
            if (bannerList.size() > 6) {
                result = false;
            }
        } else if (gridType.getCode().equals(SSLBannerWidgetGridTypeEnum.GRID3BY3.getCode())) {
            if (bannerList.size() > 9) {
                result = false;
            }
        }
        return result;
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
