package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.borngroup.ssl.core.enums.SSLLinkGridWidgetTypeEnum;
import com.borngroup.ssl.core.enums.SSLMobileWidgetAttribute;
import com.borngroup.ssl.core.enums.SSLMobileWidgetTypeEnum;
import com.borngroup.ssl.core.model.SSLLinkGridWidgetComponentModel;
import com.borngroup.ssl.core.services.SSLMobileWidgetLimitService;

/**
 * @author Nagarro Dev
 *
 */
public class SSLLinkGridWidgetComponentValidateInterceptor implements ValidateInterceptor<SSLLinkGridWidgetComponentModel> {

    @Resource
    private SSLMobileWidgetLimitService sslMobileWidgetLimitService;

    private static final int MIN_LINK_GRID_LIMIT = 2;
    private static final int MAX_LINK_GRID_LIMIT = 10;
    private static final int MAX_LINK_TEXT = 10;

    @Override
    public void onValidate(final SSLLinkGridWidgetComponentModel model, final InterceptorContext ctx) throws InterceptorException {
        if (null != model && ctx.isNew(model)) {
            final Map<SSLMobileWidgetAttribute, Integer> limitModelMap = getSslMobileWidgetLimitService()
                    .getMobileWidgetLimitModels(Arrays.asList(SSLMobileWidgetTypeEnum.ALL, model.getComponentType()), Arrays.asList(
                            SSLMobileWidgetAttribute.LINKGRIDMIN, SSLMobileWidgetAttribute.LINKGRIDMAX, SSLMobileWidgetAttribute.LINKTEXT));
            validateAttributesLimit(model, limitModelMap);
        }
    }

    /**
     * @param model
     * @param limitModelMap
     */
    private void validateAttributesLimit(final SSLLinkGridWidgetComponentModel model,
            final Map<SSLMobileWidgetAttribute, Integer> limitModelMap) throws InterceptorException {
        if (CollectionUtils.isNotEmpty(model.getLinkGrid())) {
            validateLinkGridLimit(model, limitModelMap);
            validateLinkTextLimit(model, limitModelMap);
        }
    }

    /**
     * @param model
     * @param limitModelMap
     * @throws InterceptorException
     */
    private void validateLinkTextLimit(final SSLLinkGridWidgetComponentModel model,
            final Map<SSLMobileWidgetAttribute, Integer> limitModelMap) throws InterceptorException {
        for (final CMSLinkComponentModel link : model.getLinkGrid()) {
            if (StringUtils.isNotEmpty(link.getLinkName())
                    && ((null != limitModelMap && limitModelMap.containsKey(SSLMobileWidgetAttribute.LINKTEXT)
                            && link.getLinkName().length() > limitModelMap.get(SSLMobileWidgetAttribute.LINKGRIDMAX).intValue())
                            || (link.getLinkName().length() > MAX_LINK_TEXT))) {
                throw new InterceptorException("Link Text limit exceeded");
            }
        }
    }

    /**
     * @param model
     * @param limitModelMap
     * @throws InterceptorException
     */
    private void validateLinkGridLimit(final SSLLinkGridWidgetComponentModel model,
            final Map<SSLMobileWidgetAttribute, Integer> limitModelMap) throws InterceptorException {
        if ((null != limitModelMap && limitModelMap.containsKey(SSLMobileWidgetAttribute.LINKGRIDMIN)
                && model.getLinkGrid().size() < limitModelMap.get(SSLMobileWidgetAttribute.LINKGRIDMIN).intValue())
                || (model.getLinkGrid().size() < MIN_LINK_GRID_LIMIT)) {
            throw new InterceptorException("Please attach minimum no of links in Link Grid");
        }
        if ((null != limitModelMap && limitModelMap.containsKey(SSLMobileWidgetAttribute.LINKGRIDMAX)
                && model.getLinkGrid().size() > limitModelMap.get(SSLMobileWidgetAttribute.LINKGRIDMAX).intValue())
                || (model.getLinkGrid().size() > MAX_LINK_GRID_LIMIT)) {
            throw new InterceptorException("Link Grid Limit Exceeded");
        }
        if (model.getGridType().equals(SSLLinkGridWidgetTypeEnum.GRID1BY2) && model.getLinkGrid().size() % 2 != 0) {
            throw new InterceptorException("Link Grid must have even no of links");
        } else if (model.getGridType().equals(SSLLinkGridWidgetTypeEnum.GRID1BY3) && model.getLinkGrid().size() % 3 != 0) {
            throw new InterceptorException("Link Grid must have odd no of links");
        }
    }

    /**
     * @return the sslMobileWidgetLimitService
     */
    public SSLMobileWidgetLimitService getSslMobileWidgetLimitService() {
        return sslMobileWidgetLimitService;
    }

}
