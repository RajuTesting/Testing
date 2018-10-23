/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.core.model.SSLUniqueDeviceIdentifierModel;

/**
 * @author gokulpandey
 *
 */
public interface SSLUniqueDeviceIdentifierDao {

    public abstract List<SSLUniqueDeviceIdentifierModel> getDeviceIdentifierModelByCode(String code);

    public abstract SSLUniqueDeviceIdentifierModel getDeviceIdentifierModelByCodeAndEmail(String code, String email);

}