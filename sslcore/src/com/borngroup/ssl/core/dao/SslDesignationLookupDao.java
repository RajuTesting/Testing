/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.loyalty.model.DesignationMappingModel;


/**
 * @author t.balagopalan
 *
 */
public interface SslDesignationLookupDao
{
    /**
     * @param lookupCode
     *            - Input Designation Code
     * @return - Return Designation Mapping object
     */
    DesignationMappingModel getDesignationForInputString(final String lookupCode);

    /**
     * @return - List of DesignationMappingModel
     */
    List<DesignationMappingModel> getDesignation();
}
