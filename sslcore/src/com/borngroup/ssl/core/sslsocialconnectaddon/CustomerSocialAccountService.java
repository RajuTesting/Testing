/**
 *
 */
package com.borngroup.ssl.core.sslsocialconnectaddon;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.sslfacades.customer.data.CustomerSocialAccountData;

import org.apache.commons.httpclient.auth.AuthenticationException;

import com.borngroup.ssl.core.model.CustomerSocialAccountModel;


/**
 * @author admin
 *
 */
public interface CustomerSocialAccountService
{
    CustomerSocialAccountModel getCustomerBySocialAccountId(String code) throws AmbiguousIdentifierException;

    public CustomerSocialAccountData getUserIdFromAuthToken(final String auth_token, final String socialIDP)
            throws AuthenticationException, Exception;
    void updateProfilePicture(String socialId, String pictureUrl, CustomerModel userModel);
}
