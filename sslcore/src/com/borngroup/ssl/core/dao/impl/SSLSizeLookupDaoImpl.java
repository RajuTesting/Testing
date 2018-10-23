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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.borngroup.ssl.core.dao.SSLSizeLookupDao;
import com.borngroup.ssl.core.model.SizeMappingModel;


/**
 * @author midhun.bose
 * 
 */
public class SSLSizeLookupDaoImpl extends DefaultGenericDao<SizeMappingModel> implements SSLSizeLookupDao
{

    /**
	 *
	 */
    public SSLSizeLookupDaoImpl()
    {
        super("SizeMapping");
    }

    /*
     * (non-Javadoc)
     *
     * @see Pass deptCode, subDeptCode, brandCode to lookup and get AltSizeCode
     * of matching row from SizeMapping table if such a row exists. Else return
     * empty string
     */
    @Override
    public SizeMappingModel getSizeCodeForInputStrings(final String lookupString, final String deptCode, final String brandCode,
            final String subDeptCode)
    {
        final Map<String, String> params = getParamsMap(deptCode, brandCode, subDeptCode);
        params.put("sizeCode", lookupString);

        final List<SizeMappingModel> sizes = find(params);
        return (sizes.iterator().hasNext() ? sizes.iterator().next() : null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.borngroup.ssl.core.dao.SSLSizeLookupDao#getSizeCodeForActualCode(java
     * .lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public SizeMappingModel getSizeCodeForActualCode(final String lookupString, final String deptCode, final String brandCode,
            final String subDeptCode)
    {
        final Map<String, String> params = getParamsMap(deptCode, brandCode, subDeptCode);
        params.put("altSizeCode", lookupString);

        final List<SizeMappingModel> sizes = find(params);
        return (sizes.iterator().hasNext() ? sizes.iterator().next() : null);
    }

    private Map<String, String> getParamsMap(final String deptCode, final String brandCode, final String subDeptCode)
    {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("departmentCode", deptCode);
        params.put("subDepartmentCode", subDeptCode);
        params.put("altBrandCode", brandCode);
        return params;
    }

}
