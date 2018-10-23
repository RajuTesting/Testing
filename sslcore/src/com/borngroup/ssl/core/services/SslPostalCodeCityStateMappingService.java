/**
 * 
 */
package com.borngroup.ssl.core.services;

import java.util.List;

import com.borngroup.ssl.core.model.SSLPostalCodeCityStateMappingModel;

/**
 * @author gokulpandey
 *
 */
public interface SslPostalCodeCityStateMappingService {

    public abstract List<SSLPostalCodeCityStateMappingModel> getPostalCodeMapping(String pinCode);

}