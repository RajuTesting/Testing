/**
 *
 */
package com.borngroup.ssl.core.sslsocialconnectaddon.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import com.borngroup.ssl.core.model.CustomerSocialAccountModel;
import com.borngroup.ssl.core.sslsocialconnectaddon.daos.CustomerSocialAccountDAO;

/**
 * @author admin
 *
 */
public class CustomerSocialAccountDAOImpl implements CustomerSocialAccountDAO {
	private FlexibleSearchService flexibleSearchService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.borngroup.ssl.sslsocialconnectaddon.daos.CustomerSocialAccountDAO#
	 * findCustomerBySocialAccountId(java.lang. String)
	 */
	@Override
	public List<CustomerSocialAccountModel> findCustomerBySocialAccountId(final String code) {
		final String queryString = //
		"SELECT {p:" + CustomerSocialAccountModel.PK + "}" //
				+ "FROM {" + CustomerSocialAccountModel._TYPECODE + " AS p} "//
				+ "WHERE " + "{p:" + CustomerSocialAccountModel.SOCIALACCOUNTUID + "}=?code ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("code", code);

		return flexibleSearchService.<CustomerSocialAccountModel> search(query).getResult();
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *            the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}

}
