/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.SSLShopBySizeLookupDao;
import com.borngroup.ssl.core.model.SslShopBySizeMappingModel;

/**
 * @author gurkiratmohain
 *
 */
public class SSLShopBySizeLookupDaoImpl extends DefaultGenericDao<SslShopBySizeMappingModel>
		implements SSLShopBySizeLookupDao {

	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	public SSLShopBySizeLookupDaoImpl() {
		super(SslShopBySizeMappingModel._TYPECODE);
	}

	private static final Logger LOG = Logger.getLogger(SSLShopBySizeLookupDaoImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.borngroup.ssl.core.dao.SSLShopBySizeLookupDao#
	 * getShopBySizePriorityList()
	 */
	/**
	 * returns sizePriorityList Queries the Database and gets the Model values.
	 */
	@Override
	public List<SslShopBySizeMappingModel> getShopBySizePriorityList() {
		final StringBuilder builder = new StringBuilder();
		builder.append("SELECT {c:").append("pk").append("} ");
		builder.append("FROM {").append(SslShopBySizeMappingModel._TYPECODE).append(" AS c} ");
		final FlexibleSearchQuery query = new FlexibleSearchQuery(builder.toString());
		query.setNeedTotal(true);

		final SearchResult<Object> sizePriorityList = flexibleSearchService.search(query);
		final List<Object> ojs = sizePriorityList.getResult();

		final List<SslShopBySizeMappingModel> resultList = new ArrayList<>(0);
		for (final Object obj : ojs) {
			resultList.add((SslShopBySizeMappingModel) obj);
		}
		return resultList;
	}

}
