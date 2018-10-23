/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Midhun Bose 			SSL-19 					Base Version
 *
 */
package com.borngroup.ssl.core.services;

import java.util.List;
import java.util.Map;

import com.borngroup.ssl.core.model.BrandMappingModel;
import com.borngroup.ssl.core.model.SizeMappingModel;
import com.borngroup.ssl.core.model.SslShopBySizeMappingModel;

/**
 * @author midhun.bose
 *
 */
public interface SSLLookupService {
    public String getBrandCodeForInputString(String lookupString);

    public BrandMappingModel getBrandModelForInputString(String lookupString);

    public BrandMappingModel getBrandModelForActualCode(String lookupString);

    public String getBrandCodeForActualCode(String lookupString);

    public String getColourCodeForInputString(String lookupString);

    public String getColourCodeForActualCode(String lookupString);

    public String getColourFamilyForActualCode(String lookupString);

    public String getColourFamilyForAltDesc(String lookupString);

    public SizeMappingModel getSizeCodeForInputStrings(String lookupString, String deptCode, String brandCode, String subDeptCode);

    public SizeMappingModel getSizeCodeForActualCode(String lookupString, String deptCode, String brandCode, String subDeptCode);

    // shop by size START
    public List<SslShopBySizeMappingModel> getShopBySizePriorityList();

    public Map<String, Integer> getSizePriorityMap();

    // shop by size END
}
