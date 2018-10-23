/**
 *
 */
package com.borngroup.ssl.core.dao;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

/**
 * @author satyanarayana.naidu
 *
 */
public interface CustomerBirthdayDao {

	public List<CustomerModel> getDateOfBirth();

}
