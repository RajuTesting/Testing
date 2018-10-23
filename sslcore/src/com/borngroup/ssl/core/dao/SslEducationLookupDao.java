/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.loyalty.model.EducationMappingModel;


/**
 * @author t.balagopalan
 *
 */
public interface SslEducationLookupDao
{
    /**
     * @param lookupCode
     *            - Input Education Code
     * @return - Return Education Mapping object
     */
    EducationMappingModel getEducationForInputString(final String lookupCode);

    /**
     * @return - List of EducationMappingModel
     */
    List<EducationMappingModel> getEducation();
}
