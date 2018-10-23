package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SslCustomerCCAvenueCardDao;
import com.borngroup.ssl.core.model.SslCCAvenueCustomerCardDetailModel;

/**
 * Dao Implementation for SslCCAvenueCustomerCardDetailModel
 * <p>
 * Created by shilpa.verma@nagarro.com.
 *
 * @author SSL
 */
public class SslCustomerCCAvenueCardDaoImpl implements SslCustomerCCAvenueCardDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    /**
     * Gets SslCCAvenueCustomerCardDetailModel for passed code.
     */
    @Override
    public SslCCAvenueCustomerCardDetailModel getCustomerCardDetailForCode(final String code) {
        final String query = "SELECT {" + SslCCAvenueCustomerCardDetailModel.PK + "} FROM {" + SslCCAvenueCustomerCardDetailModel._TYPECODE
                + "} WHERE {" + SslCCAvenueCustomerCardDetailModel.CUSTOMERCARDID + "}=?code ";
        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("code", code);

        final SearchResult<SslCCAvenueCustomerCardDetailModel> searchResult = getFlexibleSearchService().search(fsQuery);
        if (searchResult.getResult().isEmpty()) {
            return null;
        }
        return searchResult.getResult().get(0);
    }

    /**
     * Getter: Gets flexibleSearchService
     *
     * @return FlexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * Setter: Sets flexibleSearchService
     * 
     * @param flexibleSearchService
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
