/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SSLCustomerOrderDao;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;

/**
 * @author tusharkansal
 *
 */
public class SSLCustomerOrderDaoImpl extends AbstractItemDao implements SSLCustomerOrderDao {

    private static final String BY_DATE = "byDate";

    @Resource
    private PagedFlexibleSearchService pagedFlexibleSearchService;

    private static final String UNION_QUERY_START = "SELECT orders.pk FROM({{";
    private static final String UNION_QUERY_MID = "}} UNION ALL {{";
    private static final String UNION_QUERY_END = "}}) orders";

    private static final String FIND_ORDERS = new StringBuilder().append("SELECT DISTINCT {").append(OrderModel.PK).append("} as pk, {")
            .append(OrderModel.CREATIONTIME).append("} as time, {").append(OrderModel.CODE).append("} FROM {").append(OrderModel._TYPECODE)
            .append(" as o JOIN ").append(ConsignmentModel._TYPECODE).append(" as c on {o.PK}={c.order}} WHERE {").append(OrderModel.USER)
            .append("} = ?customer AND {").append(OrderModel.STORE).append("} = ?store AND {c.status} IN (?statusList)").append(" AND {")
            .append(OrderModel.VERSIONID).append("} IS NULL").toString();

    private static final String FIND_ORDERS_NULL_CONSIGNMENT = new StringBuilder().append("SELECT DISTINCT {").append(OrderModel.PK)
            .append("} as pk, {").append(OrderModel.CREATIONTIME).append("} as time, {").append(OrderModel.CODE).append("} FROM {")
            .append(OrderModel._TYPECODE).append(" as o LEFT JOIN ").append(ConsignmentModel._TYPECODE)
            .append(" as c on {o.PK}={c.order}} WHERE {").append(OrderModel.USER).append("} = ?customer AND {").append(OrderModel.STORE)
            .append("} = ?store AND ({c.pk} is null AND {o.status} IN (?orderStatusList))").append(" AND {").append(OrderModel.VERSIONID)
            .append("} IS NULL").toString();

    private static final String FIND_ORDERS_NOT_IN = new StringBuilder().append("SELECT DISTINCT {").append(OrderModel.PK)
            .append("}  as pk, {").append(OrderModel.CREATIONTIME).append("} as time, {").append(OrderModel.CODE).append("} FROM {")
            .append(OrderModel._TYPECODE).append(" as o JOIN ").append(ConsignmentModel._TYPECODE)
            .append(" as c on {o.PK}={c.order}} WHERE {").append(OrderModel.USER).append("} = ?customer AND {").append(OrderModel.STORE)
            .append("} = ?store AND {c.status} NOT IN (?statusList)").append(" AND {").append(OrderModel.VERSIONID).append("} IS NULL")
            .toString();

    private static final String FIND_ORDERS_NOT_IN_NULL_CONSIGNMENT = new StringBuilder().append("SELECT DISTINCT {").append(OrderModel.PK)
            .append("} as pk, {").append(OrderModel.CREATIONTIME).append("} as time, {").append(OrderModel.CODE).append("} FROM {")
            .append(OrderModel._TYPECODE).append(" as o LEFT JOIN ").append(ConsignmentModel._TYPECODE)
            .append(" as c on {o.PK}={c.order}} WHERE {").append(OrderModel.USER).append("} = ?customer AND {").append(OrderModel.STORE)
            .append("} = ?store AND ({c.pk} is null AND ({o.status} IS NULL OR {o.status} NOT IN (?orderStatusList)))").append(" AND {")
            .append(OrderModel.VERSIONID).append("} IS NULL").toString();

    private static final String SORT_ORDERS_BY_DATE = " ORDER BY orders.time DESC";

    private static final String SORT_DELIVERY_ORDERS_BY_DATE = " ORDER BY" + "{" + OrderModel.CREATIONTIME + "} DESC";

    private static final String FIND_ORDERS_BY_CUSTOMER_STORE_QUERY = "SELECT {" + OrderModel.PK + "}, {" + OrderModel.CREATIONTIME + "}, {"
            + OrderModel.CODE + "} FROM {" + OrderModel._TYPECODE + "} WHERE {" + OrderModel.USER + "} = ?customer AND {"
            + OrderModel.VERSIONID + "} IS NULL AND {" + OrderModel.STORE + "} = ?store";

    private static final String FIND_ORDERS_BY_CUSTOMER_STORE_QUERY_AND_DELIVERY_TYPE = FIND_ORDERS_BY_CUSTOMER_STORE_QUERY + " AND {"
            + OrderModel.PICKUPPERSONNAME + "} IS NULL";

    private static final String FIND_ORDERS_BY_CUSTOMER_STORE_QUERY_AND_STATUS = FIND_ORDERS_BY_CUSTOMER_STORE_QUERY + " AND {"
            + OrderModel.STATUS + "} IN (?statusList)";
    private static final String FIND_ORDERS_BY_CUSTOMER_STORE_QUERY_AND_STATUS_AND_DELIVERY_TYPE = FIND_ORDERS_BY_CUSTOMER_STORE_QUERY_AND_STATUS
            + " AND {" + OrderModel.PICKUPPERSONNAME + "} IS NULL";

    private static final String SORT_ORDERS_BY_CODE = " ORDER BY {" + OrderModel.CODE + "},{" + OrderModel.CREATIONTIME + "} DESC, {"
            + OrderModel.PK + "}";

    private static final String ORDERS_DUPLICATE_CART = "select { "+ OrderModel.PK + " } from { "+ OrderModel._TYPECODE + " } "
            + "where { "+ OrderModel.CODE + " } != ?orderCode and { "+ OrderModel.VERSIONID + " } is null "
            + "and {" + OrderModel.STORE + "} = ?store and (";
    private static final String DUPLICATE_CART_ID = "{ "+ OrderModel.CARTID + " } = ?cartID";
    private static final String DUPLICATE_CART_GUID = "{ "+ OrderModel.GUID + " } = ?guid";
    private static final String DUPLICATE_CART_CCAVENUEORDERNUMBER = "{ "+ OrderModel.CCAVENUEORDERNUMBER + " } = ?ccAvenueOrderNumber";
    private static final String DUPLICATE_CART_CLOSING = ")";

    @Override
    public SearchPageData<OrderModel> getOrderListbyConsignmentStatus(final CustomerModel customerModel, final BaseStoreModel store,
            final ConsignmentStatus[] status, final OrderStatus[] orderStatus, final PageableData pageableData, final boolean statusIn) {
        final Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("customer", customerModel);
        queryParams.put("store", store);
        queryParams.put("statusList", Arrays.asList(status));
        queryParams.put("orderStatusList", Arrays.asList(orderStatus));
        List<SortQueryData> sortQueries;
        if (statusIn) {
            sortQueries = Arrays.asList(
                    createSortQueryData(BY_DATE, new StringBuilder().append(UNION_QUERY_START).append(FIND_ORDERS).append(UNION_QUERY_MID)
                            .append(FIND_ORDERS_NULL_CONSIGNMENT).append(UNION_QUERY_END).append(SORT_ORDERS_BY_DATE).toString()));
        } else {
            sortQueries = Arrays.asList(createSortQueryData(BY_DATE,
                    new StringBuilder().append(UNION_QUERY_START).append(FIND_ORDERS_NOT_IN).append(UNION_QUERY_MID)
                            .append(FIND_ORDERS_NOT_IN_NULL_CONSIGNMENT).append(UNION_QUERY_END).append(SORT_ORDERS_BY_DATE).toString()));
        }
        return getPagedFlexibleSearchService().search(sortQueries, BY_DATE, queryParams, pageableData);
    }

    private SortQueryData createSortQueryData(final String sortCode, final String query) {
        final SortQueryData result = new SortQueryData();
        result.setSortCode(sortCode);
        result.setQuery(query);
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.dao.SSLCustomerOrderDao#findOrdersByCustomerAndStore(de.hybris.platform.core.model.user.CustomerModel,
     * de.hybris.platform.store.BaseStoreModel, de.hybris.platform.core.enums.OrderStatus[],
     * de.hybris.platform.commerceservices.search.pagedata.PageableData)
     */
    @Override
    public SearchPageData<OrderModel> findOrdersByCustomerAndStore(final CustomerModel customerModel, final BaseStoreModel store,
            final OrderStatus[] status, final PageableData pageableData) {
        validateParameterNotNull(customerModel, "Customer must not be null");
        validateParameterNotNull(store, "Store must not be null");

        final Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("customer", customerModel);
        queryParams.put("store", store);

        final List<SortQueryData> sortQueries;

        if (status != null && status.length > 0) {
            queryParams.put("statusList", Arrays.asList(status));
            sortQueries = Arrays.asList(
                    createSortQueryData(BY_DATE,
                            FIND_ORDERS_BY_CUSTOMER_STORE_QUERY_AND_STATUS_AND_DELIVERY_TYPE + SORT_DELIVERY_ORDERS_BY_DATE),
                    createSortQueryData("byOrderNumber",
                            FIND_ORDERS_BY_CUSTOMER_STORE_QUERY_AND_STATUS_AND_DELIVERY_TYPE + SORT_ORDERS_BY_CODE));
        } else {
            sortQueries = Arrays.asList(
                    createSortQueryData(BY_DATE, FIND_ORDERS_BY_CUSTOMER_STORE_QUERY_AND_DELIVERY_TYPE + SORT_DELIVERY_ORDERS_BY_DATE),
                    createSortQueryData("byOrderNumber", FIND_ORDERS_BY_CUSTOMER_STORE_QUERY_AND_DELIVERY_TYPE + SORT_ORDERS_BY_CODE));
        }

        return getPagedFlexibleSearchService().search(sortQueries, BY_DATE, queryParams, pageableData);
    }

    @Override public List<OrderModel> findDuplicateCartOrders(AbstractOrderModel order) {
        validateParameterNotNull(order, "Order must not be null");
        List<String> subQueries = new ArrayList<>();
        Map<String, Object> queryParams = new HashMap<>();
        StringBuilder query = new StringBuilder(ORDERS_DUPLICATE_CART);
        queryParams.put("store", order.getStore());
        queryParams.put("orderCode", order.getCode());
        queryParams.put("guid", order.getGuid());
        subQueries.add(DUPLICATE_CART_GUID);
        if(StringUtils.isNotEmpty(order.getCartId())){
            subQueries.add(DUPLICATE_CART_ID);
         queryParams.put("cartID", order.getCartId());
        }
        if(StringUtils.isNotEmpty(order.getCcAvenueOrderNumber())){
            subQueries.add(DUPLICATE_CART_CCAVENUEORDERNUMBER);
            queryParams.put("ccAvenueOrderNumber", order.getCcAvenueOrderNumber());
        }
        query.append(String.join(" or ", subQueries));
        query.append(DUPLICATE_CART_CLOSING);
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
        searchQuery.addQueryParameters(queryParams);
        final SearchResult<OrderModel> search = getFlexibleSearchService().search(searchQuery);
        return search.getResult();
    }
    /**
     * @return the pagedFlexibleSearchService
     */
    public PagedFlexibleSearchService getPagedFlexibleSearchService() {
        return pagedFlexibleSearchService;
    }

}
