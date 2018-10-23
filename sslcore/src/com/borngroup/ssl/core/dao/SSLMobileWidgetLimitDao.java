/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.core.enums.SSLMobileWidgetAttribute;
import com.borngroup.ssl.core.enums.SSLMobileWidgetTypeEnum;
import com.borngroup.ssl.core.model.SSLMobileWidgetLimitModel;

/**
 * @author tusharkansal
 *
 */
public interface SSLMobileWidgetLimitDao {
    /**
     * @param widgetTypes
     *        type of widget
     * @param attributes
     *        list of attributes
     * @return the list of mobile widgets
     */
    public List<SSLMobileWidgetLimitModel> getMobileWidgetLimitModels(final List<SSLMobileWidgetTypeEnum> widgetTypes,
            List<SSLMobileWidgetAttribute> attributes);

}
