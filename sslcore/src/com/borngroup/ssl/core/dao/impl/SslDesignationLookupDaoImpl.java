/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.Collections;
import java.util.List;

import com.borngroup.ssl.core.dao.SslDesignationLookupDao;
import com.borngroup.ssl.loyalty.model.DesignationMappingModel;


/**
 * @author t.balagopalan
 *
 */
public class SslDesignationLookupDaoImpl extends DefaultGenericDao<DesignationMappingModel>implements SslDesignationLookupDao
{

    public SslDesignationLookupDaoImpl()
    {
        super("DesignationMapping");
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslDesignationLookupDao#getDesignationForInputString(java.lang.String)
     */
    @Override
    public DesignationMappingModel getDesignationForInputString(final String lookupCode)
    {
        final List<DesignationMappingModel> designation = find(Collections.singletonMap("designationCode", lookupCode));
        return (designation.iterator().hasNext() ? designation.iterator().next() : null);
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslDesignationLookupDao#getDesignation()
     */
    @Override
    public List<DesignationMappingModel> getDesignation()
    {
        return find();
    }

}
