/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.services.SslDeliveryConsignmentService;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;


/**
 * @author t.balagopalan
 *
 */
public final class SslDeliveryConsignmentServiceImpl implements SslDeliveryConsignmentService
{
    private static final Logger LOG = Logger.getLogger(SslDeliveryConsignmentServiceImpl.class);

    private FlexibleSearchService  searchService;
    private BusinessProcessService businessProcessService;

    /**
     * @return the searchService
     */
    protected FlexibleSearchService getSearchService()
    {
        return searchService;
    }

    /**
     * @param searchService
     *            the searchService to set
     */
    @Required
    public void setSearchService(final FlexibleSearchService searchService)
    {
        this.searchService = searchService;
    }

    /**
     * @return the businessProcessService
     */
    protected BusinessProcessService getBusinessProcessService()
    {
        return businessProcessService;
    }

    /**
     * @param businessProcessService
     *            the businessProcessService to set
     */
    @Required
    public void setBusinessProcessService(final BusinessProcessService businessProcessService)
    {
        this.businessProcessService = businessProcessService;
    }

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.services.SslDeliveryConsignmentService#deliverConsignment(java.lang.String)
     */
    @Override
    public void deliverConsignment(final String code)
    {
        final FlexibleSearchQuery query = new FlexibleSearchQueryBuilder().from(ConsignmentModel._TYPECODE, "c")
                .select("c", ItemModel.PK).whereEquals("c", ConsignmentModel.CODE, code).build();
        final SearchResult<ConsignmentModel> result = getSearchService().search(query);
        if (result.getCount() <= 0)
        {
            LOG.warn("No consignment found for code: " + code);
            return;
        }
        final ConsignmentModel consignment = result.getResult().get(0);
        LOG.debug("Marking consignemnt[" + consignment.getCode() + "] - " + consignment.getProcessCode() + " as delivered");
        getBusinessProcessService().triggerEvent(consignment.getProcessCode() + "_" + SslCoreConstants.CONSIGNMENT_DELIVERED);
    }

}
