/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SSLMobileWidgetLimitDao;
import com.borngroup.ssl.core.enums.SSLMobileWidgetAttribute;
import com.borngroup.ssl.core.enums.SSLMobileWidgetTypeEnum;
import com.borngroup.ssl.core.model.SSLMobileWidgetLimitModel;

/**
 * @author tusharkansal
 */
public class SSLMobileWidgetLimitDaoImpl implements SSLMobileWidgetLimitDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    final String query = new StringBuilder().append("SELECT {").append(SSLMobileWidgetLimitModel.PK).append("} FROM {")
            .append(SSLMobileWidgetLimitModel._TYPECODE).append("} WHERE {").append(SSLMobileWidgetLimitModel.COMPONENTTYPE)
            .append("} IN (?widgetTypes) AND { ").append(SSLMobileWidgetLimitModel.COMPONENTATTRIBUTE)
            .append(" } IN (?attributes) ORDER BY {").append(SSLMobileWidgetLimitModel.COMPONENTTYPE).append("}").toString();

    @Override
    public List<SSLMobileWidgetLimitModel> getMobileWidgetLimitModels(final List<SSLMobileWidgetTypeEnum> widgetTypes,
            final List<SSLMobileWidgetAttribute> attributes) {
        final Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("widgetTypes", widgetTypes);
        queryParams.put("attributes", attributes);
        final SearchResult<SSLMobileWidgetLimitModel> result = getFlexibleSearchService().search(query, queryParams);
        return result.getResult();

    }

    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * @param flexibleSearchService
     *        the flexibleSearchService to set
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

}
