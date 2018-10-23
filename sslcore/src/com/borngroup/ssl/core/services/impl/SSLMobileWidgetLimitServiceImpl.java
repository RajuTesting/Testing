/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.dao.SSLMobileWidgetLimitDao;
import com.borngroup.ssl.core.enums.SSLMobileWidgetAttribute;
import com.borngroup.ssl.core.enums.SSLMobileWidgetTypeEnum;
import com.borngroup.ssl.core.model.SSLMobileWidgetLimitModel;
import com.borngroup.ssl.core.services.SSLMobileWidgetLimitService;

/**
 * @author tusharkansal
 *
 */
public class SSLMobileWidgetLimitServiceImpl implements SSLMobileWidgetLimitService {

    private SSLMobileWidgetLimitDao sslMobileWidgetLimitDao;

    @Override
    public Map<SSLMobileWidgetAttribute, Integer> getMobileWidgetLimitModels(final List<SSLMobileWidgetTypeEnum> widgetTypes,
            final List<SSLMobileWidgetAttribute> attributes) {
        final List<SSLMobileWidgetLimitModel> limitModelList = getSslMobileWidgetLimitDao().getMobileWidgetLimitModels(widgetTypes,
                attributes);
        if (CollectionUtils.isNotEmpty(limitModelList)) {
            return getLimitModelMap(limitModelList);
        }
        return null;
    }

    private Map<SSLMobileWidgetAttribute, Integer> getLimitModelMap(final List<SSLMobileWidgetLimitModel> limitModelList) {
        final Map<SSLMobileWidgetAttribute, Integer> limitModelMap = new HashMap<>();
        for (final SSLMobileWidgetLimitModel sslMobileWidgetLimitModel : limitModelList) {
            if (!limitModelMap.containsKey(sslMobileWidgetLimitModel.getComponentAttribute())
                    || (sslMobileWidgetLimitModel.getComponentType() != SSLMobileWidgetTypeEnum.ALL)) {
                limitModelMap.put(sslMobileWidgetLimitModel.getComponentAttribute(), sslMobileWidgetLimitModel.getLimit());
            }
        }
        return limitModelMap;

    }

    /**
     * @return the sslMobileWidgetLimitDao
     */
    public SSLMobileWidgetLimitDao getSslMobileWidgetLimitDao() {
        return sslMobileWidgetLimitDao;
    }

    /**
     * @param sslMobileWidgetLimitDao
     *        the sslMobileWidgetLimitDao to set
     */
    public void setSslMobileWidgetLimitDao(final SSLMobileWidgetLimitDao sslMobileWidgetLimitDao) {
        this.sslMobileWidgetLimitDao = sslMobileWidgetLimitDao;
    }

}
