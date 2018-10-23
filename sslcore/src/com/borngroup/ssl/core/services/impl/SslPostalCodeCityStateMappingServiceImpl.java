/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.borngroup.ssl.core.dao.SslPostalCodeCityStateMappingDao;
import com.borngroup.ssl.core.model.SSLPostalCodeCityStateMappingModel;
import com.borngroup.ssl.core.services.SslPostalCodeCityStateMappingService;

/**
 * @author gokulpandey
 *
 */
public class SslPostalCodeCityStateMappingServiceImpl implements SslPostalCodeCityStateMappingService {

    private SslPostalCodeCityStateMappingDao sslPostalCodeCityStateMappingDao;

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.impl.SslPostalCodeCityStateMappingService#getPostalCodeMapping(java.lang.String)
     */
    @Override
    public List<SSLPostalCodeCityStateMappingModel> getPostalCodeMapping(final String pinCode) {
        if (StringUtils.isNotEmpty(pinCode)) {
            return sslPostalCodeCityStateMappingDao.getPostalCodeMapping(pinCode);
        }
        return (List<SSLPostalCodeCityStateMappingModel>) CollectionUtils.EMPTY_COLLECTION;
    }

    /**
     * @param sslPostalCodeCityStateMappingDao
     *        the sslPostalCodeCityStateMappingDao to set
     */
    public void setSslPostalCodeCityStateMappingDao(final SslPostalCodeCityStateMappingDao sslPostalCodeCityStateMappingDao) {
        this.sslPostalCodeCityStateMappingDao = sslPostalCodeCityStateMappingDao;
    }

}
