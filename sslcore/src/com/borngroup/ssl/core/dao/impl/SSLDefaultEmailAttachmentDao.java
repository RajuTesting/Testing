/**
 * 
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import com.borngroup.ssl.core.dao.SSLEmailAttachmentDao;


/**
 * 
 */
public class SSLDefaultEmailAttachmentDao implements SSLEmailAttachmentDao
{

	private FlexibleSearchService flexibleSearchService;

	@Override
	public EmailAttachmentModel findByCode(final String code)
	{
		final String queryString = "SELECT {p:" + EmailAttachmentModel.PK + "} "//
				+ "FROM {" + EmailAttachmentModel._TYPECODE + " AS p} " + "WHERE " + "{p:" + EmailAttachmentModel.CODE + "}=?code ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("code", code);

		final List<EmailAttachmentModel> emailAttachmentModels = flexibleSearchService.<EmailAttachmentModel> search(query)
				.getResult();

		if (emailAttachmentModels.isEmpty())
		{
			return null;
		}
		return emailAttachmentModels.get(0);

	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
