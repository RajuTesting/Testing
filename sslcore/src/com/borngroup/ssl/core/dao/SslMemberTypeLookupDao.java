/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.loyalty.model.MemberTypeMappingModel;


/**
 * @author t.balagopalan
 *
 */
public interface SslMemberTypeLookupDao
{
    /**
     * @param lookupCode
     *            - Input Member Type Code
     * @return - Return Member Type Mapping object
     */
    MemberTypeMappingModel getMemberTypeForInputString(final String lookupCode);

    /**
     * @return - List of EducationMappingModel
     */
    List<MemberTypeMappingModel> getMemberType();
}
