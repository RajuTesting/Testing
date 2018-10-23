/**
 *
 */
package com.borngroup.ssl.core.services;

import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Collection;
import java.util.List;

/**
 * @author Deepak
 *
 */
public interface SSLUserService extends UserService {
    Collection<PrincipalModel> getUsersByUserGroup(String groupUid);

    List<CustomerModel> getUserByEmail(final CustomerModel customerModel);

    CustomerModel getUserByUid(final String uid);

    public boolean isValidStoreAgent(final UserModel userModel);

}
