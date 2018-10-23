/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.loyalty.model.BusinessTypeMappingModel;


/**
 * @author t.balagopalan
 *
 */
public interface SslBusinessTypeLookupDao
{
    /**
     * @param lookupCode
     *            - Input Business Type Code
     * @return BusinessTypeMappingModel - Return Business Type Mapping object
     */
    BusinessTypeMappingModel getBusinessTypeForInputString(final String lookupCode);

    /**
     * @return - List of BusinessTypeMappingModel
     */
    List<BusinessTypeMappingModel> getBusinessType();
}
