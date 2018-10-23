/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Midhun Bose 			SSL-19 					Base Version
 *
 */
package com.borngroup.ssl.core.dao;

import com.borngroup.ssl.core.model.BrandMappingModel;


/**
 * @author midhun.bose
 * 
 */
public interface SSLBrandLookupDao
{
    public String getBrandCodeForInputString(String lookupString);

    public String getBrandCodeForActualCode(String lookupString);

    public BrandMappingModel getBrandModelForInputString(String lookupString);

    public BrandMappingModel getBrandModelForActualCode(String lookupString);
}
