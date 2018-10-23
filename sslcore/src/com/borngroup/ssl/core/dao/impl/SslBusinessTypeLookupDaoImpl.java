/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.Collections;
import java.util.List;

import com.borngroup.ssl.core.dao.SslBusinessTypeLookupDao;
import com.borngroup.ssl.loyalty.model.BusinessTypeMappingModel;


/**
 * @author t.balagopalan
 *
 */
public class SslBusinessTypeLookupDaoImpl extends DefaultGenericDao<BusinessTypeMappingModel>implements SslBusinessTypeLookupDao
{

    public SslBusinessTypeLookupDaoImpl()
    {
        super("BusinessTypeMapping");
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslBusinessTypeLookupDao#getBusinessTypeForInputString(java.lang.String)
     */
    @Override
    public BusinessTypeMappingModel getBusinessTypeForInputString(final String lookupCode)
    {
        final List<BusinessTypeMappingModel> businessType = find(Collections.singletonMap("businessTypeCode", lookupCode));
        return (businessType.iterator().hasNext() ? businessType.iterator().next() : null);
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SslBusinessTypeLookupDao#getBusinessType()
     */
    @Override
    public List<BusinessTypeMappingModel> getBusinessType()
    {
        // return the entire BusinessType as list
        return find();
    }

}
