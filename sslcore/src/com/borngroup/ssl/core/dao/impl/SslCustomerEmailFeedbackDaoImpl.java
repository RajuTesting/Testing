package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SslCustomerEmailFeedbackDao;
import com.borngroup.ssl.core.model.CustomerFeedbackModel;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;

/**
 * Customer email feedback dao implementation.
 * <p/>
 * Created by ravi.yadav@nagarro.com
 *
 * @author SSL
 */
public class SslCustomerEmailFeedbackDaoImpl implements SslCustomerEmailFeedbackDao {
    /**
     * FlexibleSearchService instance.
     */
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    /**
     * This method used to get customer feedback details based on orderNo.
     * 
     * @param orderNo - Instance of String
     * @return List of {@link CustomerFeedbackModel}
     */
    @Override
    public List<CustomerFeedbackModel> getCustomerFeedbackDetails(final String orderNo) {
        final FlexibleSearchQuery query = new FlexibleSearchQueryBuilder().from(CustomerFeedbackModel._TYPECODE, "i")
                .select("i", ItemModel.PK).whereEquals("i", CustomerFeedbackModel.ORDERNO, orderNo).build();
        final SearchResult<CustomerFeedbackModel> result = this.flexibleSearchService.search(query);
        return result.getResult();
    }

    /**
     * Gets flexibleSearchService instance.
     *
     * @return FlexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * Sets flexibleSearchService instance.
     * 
     * @param flexibleSearchService1 - Instance of FlexibleSearchService
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService1) {
        this.flexibleSearchService = flexibleSearchService1;
    }

}
