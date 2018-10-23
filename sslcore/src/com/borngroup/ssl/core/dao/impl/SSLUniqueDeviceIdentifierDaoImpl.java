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

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.dao.SSLUniqueDeviceIdentifierDao;
import com.borngroup.ssl.core.model.SSLUniqueDeviceIdentifierModel;

/**
 * @author gokulpandey
 *
 */
public class SSLUniqueDeviceIdentifierDaoImpl implements SSLUniqueDeviceIdentifierDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.dao.impl.SSLUniqueDeviceIdentifier#getDeviceIdentifierModelByCode(java.lang.String)
     */
    @Override
    public List<SSLUniqueDeviceIdentifierModel> getDeviceIdentifierModelByCode(final String code) {
        final String query = "SELECT {" + SSLUniqueDeviceIdentifierModel.PK + "} FROM {" + SSLUniqueDeviceIdentifierModel._TYPECODE
                + "} WHERE {" + SSLUniqueDeviceIdentifierModel.UNIQUEID + "} =?code";
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        final SearchResult<SSLUniqueDeviceIdentifierModel> searchResult = getFlexibleSearchService().search(query, params);
        if (CollectionUtils.isNotEmpty(searchResult.getResult())) {
            return searchResult.getResult();
        }
        return null;
    }

    @Override
    public SSLUniqueDeviceIdentifierModel getDeviceIdentifierModelByCodeAndEmail(final String code, final String email) {
        final String query = "SELECT {" + SSLUniqueDeviceIdentifierModel.PK + "} FROM {" + SSLUniqueDeviceIdentifierModel._TYPECODE
                + "} ORDER BY {" + SSLUniqueDeviceIdentifierModel.UNIQUEID + "}=?code AND {" + SSLUniqueDeviceIdentifierModel.EMAIL
                + "}=?email";
        final Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("email", email);
        final SearchResult<SSLUniqueDeviceIdentifierModel> searchResult = getFlexibleSearchService().search(query, params);
        if (CollectionUtils.isNotEmpty(searchResult.getResult())) {
            return searchResult.getResult().get(0);
        }
        return null;

    }

    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * @param flexibleSearchService
     *        the flexibleSearchService to set
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

}
