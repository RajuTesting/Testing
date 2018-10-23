/**
 *
 */
package com.borngroup.ssl.core.login.magento.service;

/**
 * @author t.balagopalan
 *
 */
public interface SslMagentoLoginService
{
    /**
     * Check and validate existing Magento users
     *
     * @param username
     * @param password
     * @return Boolean
     */
    Boolean validateExistingMagentoUserLogin(final String username, final String password);
}
