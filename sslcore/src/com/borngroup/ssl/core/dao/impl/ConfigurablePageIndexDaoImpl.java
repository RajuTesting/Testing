/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.dao.ConfigurablePageIndexDao;
import com.borngroup.ssl.core.enums.ConfigurablePageType;
import com.borngroup.ssl.core.model.ConfigurablePageIndexModel;

/**
 * @author gokulpandey
 *
 */
public class ConfigurablePageIndexDaoImpl implements ConfigurablePageIndexDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Override
    public ConfigurablePageIndexModel getIndexModel(final ConfigurablePageType pageType) {
        final String query = "SELECT {" + ConfigurablePageIndexModel.PK + "} FROM {" + ConfigurablePageIndexModel._TYPECODE + "} WHERE {"
                + ConfigurablePageIndexModel.PAGETYPE + "} =?pageType";
        final Map<String, Object> params = new HashMap<>();
        params.put("pageType", pageType);
        final SearchResult<ConfigurablePageIndexModel> searchResult = getFlexibleSearchService().search(query, params);
        if (CollectionUtils.isNotEmpty(searchResult.getResult())) {
            return searchResult.getResult().get(0);
        }
        return null;
    }

    @Override
    public List<ConfigurablePageIndexModel> getAllIndexModel() {
        final String query = "SELECT {" + ConfigurablePageIndexModel.PK + "} FROM {" + ConfigurablePageIndexModel._TYPECODE + "}";
        final SearchResult<ConfigurablePageIndexModel> searchResult = getFlexibleSearchService().search(query);
        return searchResult.getResult();
    }

    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

}
