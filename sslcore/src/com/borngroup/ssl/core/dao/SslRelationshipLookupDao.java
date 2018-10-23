/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.loyalty.model.RelationshipMappingModel;


/**
 * @author t.balagopalan
 *
 */
public interface SslRelationshipLookupDao
{
    /**
     * @param lookupCode
     *            - Input Relationship Code
     * @return - Return Relationship Mapping object
     */
    RelationshipMappingModel getRelationshipForInputString(final String lookupCode);

    /**
     * @return - List of RelationshipMappingModel
     */
    List<RelationshipMappingModel> getRelationship();
}
