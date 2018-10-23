/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Midhun Bose 			SSL-19 					Base Version
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.Collections;
import java.util.List;

import com.borngroup.ssl.core.dao.SSLBrandLookupDao;
import com.borngroup.ssl.core.model.BrandMappingModel;


/**
 * @author midhun.bose
 * 
 */
public class SSLBrandLookupDaoImpl extends DefaultGenericDao<BrandMappingModel> implements SSLBrandLookupDao
{

    /**
	 *
	 */
    public SSLBrandLookupDaoImpl()
    {
        super("BrandMapping");
    }

    /*
     * (non-Javadoc)
     *
     * @see Pass Brand_Cd to lookup and get Alt_Brand_Cd of matching row from
     * brand mapping table if such a row exists. Else return empty string
     */
    @Override
    public String getBrandCodeForInputString(final String lookupString)
    {
        final List<BrandMappingModel> brands = find(Collections.singletonMap("brandCode", lookupString));
        return (brands.iterator().hasNext() ? brands.iterator().next().getAltBrandDesc() : "");
    }

    /*
     * (non-Javadoc)
     *
     * @see Pass Alt_Brand_Cd to get Alt_Brand_Cd from brand mapping table if
     * such a row exists. Else return empty string
     */
    @Override
    public String getBrandCodeForActualCode(final String lookupString)
    {
        final List<BrandMappingModel> brands = find(Collections.singletonMap("altBrandDesc", lookupString));
        return (brands.iterator().hasNext() ? brands.iterator().next().getAltBrandDesc() : "");
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SSLBrandLookupDao#getBrandModelForInputString(java.lang.String)
     */
    @Override
    public BrandMappingModel getBrandModelForInputString(final String lookupString)
    {
        final List<BrandMappingModel> brands = find(Collections.singletonMap("brandCode", lookupString));
        return (brands.iterator().hasNext() ? brands.iterator().next() : null);
    }
    
    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.SSLBrandLookupDao#getBrandModelForActualCode(java.lang.String)
     */
    @Override
    public BrandMappingModel getBrandModelForActualCode(final String lookupString)
    {
        final List<BrandMappingModel> brands = find(Collections.singletonMap("altBrandDesc", lookupString));
        return (brands.iterator().hasNext() ? brands.iterator().next() : null);
    }

}
