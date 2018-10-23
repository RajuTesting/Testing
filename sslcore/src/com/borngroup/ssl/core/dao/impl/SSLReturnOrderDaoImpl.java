package com.borngroup.ssl.core.dao.impl;

import com.borngroup.ssl.core.dao.SSLReturnOrderDao;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

/**
 * Created by nikhiljagtiani on 11/23/2016.
 */
public class SSLReturnOrderDaoImpl implements SSLReturnOrderDao {

    private static final Logger LOG = Logger.getLogger(SSLReturnOrderDaoImpl.class);



    protected static final String RETURN_ORDERS_CUSTOMER_STORE_QUERY = "SELECT {" + OrderModel.PK + "}, {" + OrderModel.CREATIONTIME + "}, {" + OrderModel.CODE
            + "} , max(p_rma) as rm FROM {" + OrderModel._TYPECODE + " AS o JOIN "+ ReturnRequestModel._TYPECODE+" AS r"
            +" ON {o."+OrderModel.PK + "} = {r." +ReturnRequestModel.ORDER+ "} } WHERE {"
            + OrderModel.USER + "} = ?customer AND {"
            + OrderModel.VERSIONID + "} IS NULL AND {"
            + OrderModel.STORE + "} = ?store AND {r." +ReturnRequestModel.ORDER+ "}  is not null group by {" + OrderModel.PK + "}, {" + OrderModel.CREATIONTIME + "}, {" + OrderModel.CODE
            + "}";




    protected static final String RETURN_ORDERS_CUSTOMER_STORE_STATUS_QUERY = RETURN_ORDERS_CUSTOMER_STORE_QUERY + " AND {"
            + OrderModel.STATUS + "} IN (?statusList)";

    private static final String SORT_ORDERS_BY_RMA_DATE = " ORDER BY rm DESC ";

    private static final String SORT_ORDERS_BY_DATE = " ORDER BY {" + OrderModel.CREATIONTIME + "} DESC, {" + OrderModel.PK + "}";

    private static final String SORT_ORDERS_BY_CODE = " ORDER BY {" + OrderModel.CODE + "},{" + OrderModel.CREATIONTIME + "} DESC, {"
            + OrderModel.PK + "}";

    private static final String FIND_RETURN_ORDER_DETAILS = new StringBuilder().append("SELECT DISTINCT {").append(ReturnRequestModel.PK)
            .append("} as pk, {").append(ReturnRequestModel.CREATIONTIME).append("} as time FROM {").append(ReturnRequestModel._TYPECODE)
            .append(" as r JOIN ").append(OrderModel._TYPECODE)
            .append(" as o on {o.PK}={r.order}} WHERE {o.user} = ?customer AND {o.store} = ?store AND {o.versionID} IS NULL").toString();

    private static final String BY_DATE = "byDate";

    private PagedFlexibleSearchService pagedFlexibleSearchService;

    protected PagedFlexibleSearchService getPagedFlexibleSearchService()
    {
        return pagedFlexibleSearchService;
    }

    @Required
    public void setPagedFlexibleSearchService(final PagedFlexibleSearchService pagedFlexibleSearchService)
    {
        this.pagedFlexibleSearchService = pagedFlexibleSearchService;
    }



    @Override
    public SearchPageData<OrderModel> getReturnOrders(final CustomerModel customerModel, final BaseStoreModel store,
                                                      final OrderStatus[] status, PageableData pageableData) {
        validateParameterNotNull(customerModel, "Customer must not be null");
        validateParameterNotNull(store, "Store must not be null");

        final Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("customer", customerModel);
        queryParams.put("store", store);

        final List<SortQueryData> sortQueries;

        if (status != null && status.length > 0)
        {
            queryParams.put("statusList", Arrays.asList(status));
            sortQueries = Arrays.asList(
                    createSortQueryData("byDate", RETURN_ORDERS_CUSTOMER_STORE_STATUS_QUERY + SORT_ORDERS_BY_RMA_DATE),
                    createSortQueryData("byOrderNumber", RETURN_ORDERS_CUSTOMER_STORE_STATUS_QUERY + SORT_ORDERS_BY_CODE));
        }
        else
        {
            sortQueries = Arrays.asList(
                    createSortQueryData("byDate", RETURN_ORDERS_CUSTOMER_STORE_QUERY + SORT_ORDERS_BY_RMA_DATE),
                    createSortQueryData("byOrderNumber", RETURN_ORDERS_CUSTOMER_STORE_QUERY + SORT_ORDERS_BY_CODE));
        }

        return getPagedFlexibleSearchService().search(sortQueries, "byDate", queryParams, pageableData);
    }
    protected SortQueryData createSortQueryData(final String sortCode, final String query)
    {
        final SortQueryData result = new SortQueryData();
        result.setSortCode(sortCode);
        result.setQuery(query);
        return result;
    }

    @Override
    public SearchPageData<ReturnRequestModel> getReturnOrdersDetails(final CustomerModel customerModel, final BaseStoreModel store,
            final PageableData pageableData) {
        final Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("customer", customerModel);
        queryParams.put("store", store);
        final List<SortQueryData> sortQueries = Arrays
                .asList(createSortQueryData(BY_DATE, FIND_RETURN_ORDER_DETAILS + SORT_ORDERS_BY_DATE));

        return getPagedFlexibleSearchService().search(sortQueries, BY_DATE, queryParams, pageableData);
    }
}
