/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.Collections;
import java.util.List;

import com.borngroup.ssl.core.dao.SslRelationshipLookupDao;
import com.borngroup.ssl.loyalty.model.RelationshipMappingModel;


/**
 * @author t.balagopalan
 *
 */
public class SslRelationshipLookupDaoImpl extends DefaultGenericDao<RelationshipMappingModel>implements SslRelationshipLookupDao
{

    public SslRelationshipLookupDaoImpl()
    {
        super("RelationshipMapping");
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslRelationshipLookupDao#getRelationshipForInputString(java.lang.String)
     */
    @Override
    public RelationshipMappingModel getRelationshipForInputString(final String lookupCode)
    {
        final List<RelationshipMappingModel> relationship = find(Collections.singletonMap("relationshipCode", lookupCode));
        return (relationship.iterator().hasNext() ? relationship.iterator().next() : null);
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslRelationshipLookupDao#getRelationship()
     */
    @Override
    public List<RelationshipMappingModel> getRelationship()
    {
        return find();
    }

}
