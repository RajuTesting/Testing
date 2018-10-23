/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.DealComponentDao;
import com.borngroup.ssl.core.model.DealComponentModel;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;

/**
 * @author tejsharma
 *
 */
public class DealComponentDaoImpl implements DealComponentDao {
    private static final Logger LOG = Logger.getLogger(DealComponentDaoImpl.class);

    @Resource(name = "flexibleSearchService")
    FlexibleSearchService flexibleSearchService;

    /**
     * {@inheritDoc}
     */
    @Override
    public DealComponentModel getDealById(final String dealId) {
        final FlexibleSearchQueryBuilder queryBuilder = new FlexibleSearchQueryBuilder();
        DealComponentModel deal = null;
        queryBuilder.from(DealComponentModel._TYPECODE, "dcm").select("dcm", DealComponentModel.PK).whereEquals("dcm",
                DealComponentModel.UID, dealId);
        final FlexibleSearchQuery query = queryBuilder.build();
        final SearchResult<DealComponentModel> result = getFlexibleSearchService().search(query);
        if (result != null) {
            final List<DealComponentModel> deals = result.getResult();
            if (!CollectionUtils.isEmpty(deals)) {
                if (deals.size() > 1) {
                    LOG.error("Multiple deals exists with code/id " + dealId);
                }
                deal = deals.get(0);
            }
        }
        return deal;
    }

    /**
     * @return
     */
    private FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

}
