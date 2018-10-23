/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.Collections;
import java.util.List;

import com.borngroup.ssl.core.dao.SslOccupationLookupDao;
import com.borngroup.ssl.loyalty.model.OccupationMappingModel;


/**
 * @author t.balagopalan
 *
 */
public class SslOccupationLookupDaoImpl extends DefaultGenericDao<OccupationMappingModel>implements SslOccupationLookupDao
{

    public SslOccupationLookupDaoImpl()
    {
        super("OccupationMapping");
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslOccupationLookupDao#getOccupationForInputString(java.lang.String)
     */
    @Override
    public OccupationMappingModel getOccupationForInputString(final String lookupCode)
    {
        final List<OccupationMappingModel> occupation = find(Collections.singletonMap("occupationCode", lookupCode));
        return (occupation.iterator().hasNext() ? occupation.iterator().next() : null);
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslOccupationLookupDao#getOccupation()
     */
    @Override
    public List<OccupationMappingModel> getOccupation()
    {
        return find();
    }

}
