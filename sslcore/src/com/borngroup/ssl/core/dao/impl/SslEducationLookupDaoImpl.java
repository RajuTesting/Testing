/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.Collections;
import java.util.List;

import com.borngroup.ssl.core.dao.SslEducationLookupDao;
import com.borngroup.ssl.loyalty.model.EducationMappingModel;


/**
 * @author t.balagopalan
 *
 */
public class SslEducationLookupDaoImpl extends DefaultGenericDao<EducationMappingModel>implements SslEducationLookupDao
{

    public SslEducationLookupDaoImpl()
    {
        super("EducationMapping");
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslEducationLookupDao#getEducationForInputString(java.lang.String)
     */
    @Override
    public EducationMappingModel getEducationForInputString(final String lookupCode)
    {
        final List<EducationMappingModel> education = find(Collections.singletonMap("educationCode", lookupCode));
        return (education.iterator().hasNext() ? education.iterator().next() : null);
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslEducationLookupDao#getEducation()
     */
    @Override
    public List<EducationMappingModel> getEducation()
    {
        return find();
    }

}
