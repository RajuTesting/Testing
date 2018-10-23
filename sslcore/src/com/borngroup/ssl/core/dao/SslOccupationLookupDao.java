/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.loyalty.model.OccupationMappingModel;


/**
 * @author t.balagopalan
 *
 */
public interface SslOccupationLookupDao
{
    /**
     * @param lookupCode
     *            - Input Occupation Code
     * @return - Return Occupation Mapping object
     */
    OccupationMappingModel getOccupationForInputString(final String lookupCode);

    /**
     * @return - List of OccupationMappingModel
     */
    List<OccupationMappingModel> getOccupation();
}
