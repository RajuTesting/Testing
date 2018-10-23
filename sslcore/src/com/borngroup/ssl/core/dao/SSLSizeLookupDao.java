/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Midhun Bose 			SSL-19 					Base Version
 *
 */
package com.borngroup.ssl.core.dao;

import com.borngroup.ssl.core.model.SizeMappingModel;

/**
 * @author midhun.bose
 * 
 */
public interface SSLSizeLookupDao
{
    public SizeMappingModel getSizeCodeForInputStrings(String lookupString, String deptCode, String brandCode, String subDeptCode);

    public SizeMappingModel getSizeCodeForActualCode(String lookupString, String deptCode, String brandCode, String subDeptCode);
}
