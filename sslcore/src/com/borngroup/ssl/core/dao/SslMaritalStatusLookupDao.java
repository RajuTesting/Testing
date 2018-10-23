/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.loyalty.model.MaritalStatusMappingModel;


/**
 * @author t.balagopalan
 *
 */
public interface SslMaritalStatusLookupDao
{
    /**
     * @param lookupCode
     *            - Input Marital Status Code
     * @return - Return Marital Status Mapping object
     */
    MaritalStatusMappingModel getMaritalStatusForInputString(final String lookupCode);

    /**
     * @return - List of MaritalStatusMappingModel
     */
    List<MaritalStatusMappingModel> getMaritalStatus();
}
