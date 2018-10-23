/**
 *
 */
package com.borngroup.ssl.core.services;

import java.util.List;
import java.util.Map;

import com.borngroup.ssl.core.enums.SSLMobileWidgetAttribute;
import com.borngroup.ssl.core.enums.SSLMobileWidgetTypeEnum;

/**
 * @author tusharkansal
 *
 */
public interface SSLMobileWidgetLimitService {

    /**
     * @param widgetTypes
     *        type of widget
     * @param attributes
     *        list of attributes
     * @return the map of mobile widgets attribute with limit
     */
    public Map<SSLMobileWidgetAttribute, Integer> getMobileWidgetLimitModels(final List<SSLMobileWidgetTypeEnum> widgetTypes,
            List<SSLMobileWidgetAttribute> attributes);
}
