/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.core.model.SSLPostalCodeCityStateMappingModel;

/**
 * @author gokulpandey
 *
 */
public interface SslPostalCodeCityStateMappingDao {

    /**
     * This Dao Interface will fetch the List of model for a given pin code
     *
     * @param pinCode
     * @return
     */
    public List<SSLPostalCodeCityStateMappingModel> getPostalCodeMapping(final String pinCode);

}
