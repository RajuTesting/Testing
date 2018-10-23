/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.dao.SslMediaContainerDao;


/**
 * @author admin
 *
 */
public class SslMediaContainerDaoImpl extends AbstractItemDao implements SslMediaContainerDao
{
	private FlexibleSearchService flexibleSearchService;

	@Override
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	protected FlexibleSearchService getFlexibleSearchService()
	{
		return this.flexibleSearchService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.borngroup.ssl.core.dao.SslMediaContainerDao#findMediaContextByQualifier(de.hybris.platform.catalog.model.
	 * CatalogVersionModel, java.lang.String)
	 */
	@Override
	public List<MediaContainerModel> findMediaContextByQualifier(final CatalogVersionModel catalogVersion, final String qualifier)
	{
		final Map params = new HashMap();
		params.put("qualifier", qualifier);
		params.put(KeywordModel.CATALOGVERSION, catalogVersion);

		final StringBuilder builder = new StringBuilder();
		builder.append("SELECT {").append("pk").append("} ");
		builder.append("FROM {").append("MediaContainer").append("} ");
		builder.append("WHERE {").append("qualifier").append("}=?qualifier ");
		builder.append("and {").append(KeywordModel.CATALOGVERSION).append("} = ?").append(KeywordModel.CATALOGVERSION);
		builder.append(" ORDER BY {").append("pk").append("} ASC");
		final SearchResult result = this.flexibleSearchService.search(builder.toString(), params);
		return result.getResult();


	}

}
