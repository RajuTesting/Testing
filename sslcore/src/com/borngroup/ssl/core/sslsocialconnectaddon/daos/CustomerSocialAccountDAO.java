/**
 *
 */
package com.borngroup.ssl.core.sslsocialconnectaddon.daos;

import java.util.List;

import com.borngroup.ssl.core.model.CustomerSocialAccountModel;

/**
 * @author admin
 *
 */
public interface CustomerSocialAccountDAO {

	List<CustomerSocialAccountModel> findCustomerBySocialAccountId(String code);
}
