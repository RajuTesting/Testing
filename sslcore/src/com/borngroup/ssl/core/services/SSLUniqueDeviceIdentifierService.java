/**
 *
 */
package com.borngroup.ssl.core.services;

import java.util.List;

import com.borngroup.ssl.core.model.SSLUniqueDeviceIdentifierModel;
import com.borngroup.ssl.facades.data.SSLUniqueDeviceIdentifierData;

/**
 * @author gokulpandey
 *
 */
public interface SSLUniqueDeviceIdentifierService {

    public abstract List<SSLUniqueDeviceIdentifierModel> getDeviceIdentifierByCode(String code);

    public abstract SSLUniqueDeviceIdentifierModel getDeviceIdentifierByCodeAndEmail(String code, String email);

    public abstract void updateEmail(String code, String email);

    /**
     * @param deviceIdentifierData
     * @return
     */
    public abstract String save(SSLUniqueDeviceIdentifierData deviceIdentifierData);

    /**
     * @param uniqueId
     * @param email
     * @return
     */
    public abstract void updateEmailTouniqueIdentifier(String uniqueId, String email);

}