/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SslPostalCodeCityStateMappingDao;
import com.borngroup.ssl.core.model.SSLPostalCodeCityStateMappingModel;

/**
 * @author gokulpandey
 *
 */
public class SslPostalCodeCityStateMappingDaoImpl implements SslPostalCodeCityStateMappingDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<SSLPostalCodeCityStateMappingModel> getPostalCodeMapping(final String pinCode) {
        final String query = "SELECT {" + SSLPostalCodeCityStateMappingModel.PK + "} FROM {" + SSLPostalCodeCityStateMappingModel._TYPECODE
                + "} WHERE {" + SSLPostalCodeCityStateMappingModel.PINCODE + "}=?pinCode";
        final Map<String, Object> params = new HashMap<>();
        params.put("pinCode", pinCode);
        final SearchResult<SSLPostalCodeCityStateMappingModel> searchResult = getFlexibleSearchService().search(query, params);
        return searchResult.getResult();
    }

    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

}
