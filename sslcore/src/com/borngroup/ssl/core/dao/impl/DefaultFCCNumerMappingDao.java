/**
 * 
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.FCCNumerMappingDao;
import com.borngroup.ssl.core.model.FccNumberMappingModel;

/**
 * <p>
 * DefaultFCCNumerMappingDao.java : implementation of {@link FCCNumerMappingDao}
 * details.
 * <p>
 * Created By : raju.p@techouts.com
 * <p>
 *
 * @author Techouts
 */
public class DefaultFCCNumerMappingDao implements FCCNumerMappingDao {

	private static final Logger LOG = Logger
			.getLogger(DefaultFCCNumerMappingDao.class);

	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	private static final String NUMBER_MAPPING_QUERY = "select {"
			+ FccNumberMappingModel.PK + "} from {"
			+ FccNumberMappingModel._TYPECODE + "}";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FccNumberMappingModel> findAllFCCNumberMappings() {

		return doSearch(NUMBER_MAPPING_QUERY, null, FccNumberMappingModel.class);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FccNumberMappingModel> findFCCNumberMapping(String customerPk) {

		StringBuffer query = new StringBuffer(NUMBER_MAPPING_QUERY);
		query.append("where {" + FccNumberMappingModel.CUSTOMERPK
				+ "}=?customerPk");
		Map<String, Object> params = new HashMap<>();
		params.put("customerPk", customerPk);
		return doSearch(query.toString(), params, FccNumberMappingModel.class);
	}


	private <T> List<T> doSearch(final String query,
			final Map<String, Object> params, final Class<T> resultClass) {
		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
		if (params != null) {
			fQuery.addQueryParameters(params);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Flexible search " + fQuery.toString());
		}
		fQuery.setResultClassList(Collections.singletonList(resultClass));
		final SearchResult<T> searchResult = flexibleSearchService
				.search(fQuery);
		return searchResult.getResult();
	}

}
