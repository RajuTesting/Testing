/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.Collections;
import java.util.List;

import com.borngroup.ssl.core.dao.SslMaritalStatusLookupDao;
import com.borngroup.ssl.loyalty.model.MaritalStatusMappingModel;


/**
 * @author t.balagopalan
 *
 */
public class SslMaritalStatusLookupDaoImpl extends DefaultGenericDao<MaritalStatusMappingModel>
        implements SslMaritalStatusLookupDao
{

    public SslMaritalStatusLookupDaoImpl()
    {
        super("MaritalStatusMapping");
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslMaritalStatusLookupDao#getMaritalStatusForInputString(java.lang.String)
     */
    @Override
    public MaritalStatusMappingModel getMaritalStatusForInputString(final String lookupCode)
    {
        final List<MaritalStatusMappingModel> maritalStatus = find(Collections.singletonMap("maritalStatusCode", lookupCode));
        return (maritalStatus.iterator().hasNext() ? maritalStatus.iterator().next() : null);
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslMaritalStatusLookupDao#getMaritalStatus()
     */
    @Override
    public List<MaritalStatusMappingModel> getMaritalStatus()
    {
        return find();
    }

}
