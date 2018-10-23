/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.promotions.util.Pair;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.dao.SSLODCReturnDao;
import com.borngroup.ssl.core.enums.ReturnRequestType;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;
import com.borngroup.ssl.fulfilmentprocess.returns.data.SearchRMARequest;
import com.borngroup.ssl.logistics.enums.CourierEnum;

/**
 * DAO Implementation class {@link SSLODCReturnDao}.
 */
public class SSLODCReturnDaoImpl implements SSLODCReturnDao {

    private static final Logger LOG = Logger.getLogger(SSLODCReturnDaoImpl.class);
    protected static final String RETURN_REQUEST_RMA_QUERY = "SELECT {" + ReturnRequestModel.PK + "} FROM {" + ReturnRequestModel._TYPECODE
            + "} WHERE {" + ReturnRequestModel.RMA + "} = ?rma";

    protected static final String RETURN_REQUEST_QUERY = "SELECT {" + ReturnRequestModel.PK + "} FROM {" + ReturnRequestModel._TYPECODE
            + "} WHERE {" + ReturnRequestModel.AWBNUMBER + "} IS NOT NULL AND {" + ReturnRequestModel.CARRIER + "} IS NOT NULL AND {"
            + ReturnRequestModel.TYPE + "} = ?type AND {" + ReturnRequestModel.STATUS + "} = ?status AND {" + ReturnRequestModel.CARRIER
            + "}" + " = ?carrier";

    protected static final String ASSIGNED_RETURN_REQUESTS = "SELECT {" + ReturnRequestModel.PK + "} FROM {" + ReturnRequestModel._TYPECODE
            + "} WHERE {" + ReturnRequestModel.AWBNUMBER + "} IS NOT NULL AND {" + ReturnRequestModel.CARRIER + "} IS NOT NULL AND {"
            + ReturnRequestModel.STATUS + "} = ?status";

    protected static final String RETURN_ENTRY_QUERY = "SELECT {" + ReturnEntryModel.PK + "} FROM {" + ReturnEntryModel._TYPECODE
            + "} WHERE {" + ReturnEntryModel.PK + "} = ?returnEntryId";

    protected static final String CONSIGNMENT_MODEL_QUERY = "SELECT {" + ConsignmentModel.PK + "} FROM {" + ConsignmentModel._TYPECODE
            + "} WHERE {" + ConsignmentModel.CODE + "} = ?consignmentCode";
    protected static final String POSMODEL_BY_WAREHOUSE_QUERY = "SELECT {" + PointOfServiceModel.PK + "} FROM {"
            + PointOfServiceModel._TYPECODE + "} WHERE {" + PointOfServiceModel.CODE + "} = ?wareHouseCode";
    
    protected static final String POSMODEL_BY_INVOICE_ID_QUERY = "SELECT {" + PointOfServiceModel.PK + "} FROM {"
            + PointOfServiceModel._TYPECODE + "} WHERE {" + PointOfServiceModel.CODE + "} = ?invoiceStoreId";

    protected static final String RETURN_REQUEST_EXCHANGE_ORDER_QUERY =
            "SELECT {rr.PK} FROM {ReturnRequest as rr JOIN Order as o on {rr.exchangeOrder} = {o.pk}} WHERE {o.code} = ?exchangeOrder";

    private FlexibleSearchService searchService;

    protected FlexibleSearchService getSearchService() {
        return searchService;
    }

    @Required
    public void setSearchService(final FlexibleSearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public List<ReturnRequestModel> getReturnRequestsByTypeAndStatusForAWBCarrier(final CourierEnum courierEnum,
            final ReturnRequestType requestType, final ReturnStatus requestStatus) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(RETURN_REQUEST_QUERY);
        query.addQueryParameter("type", requestType);
        query.addQueryParameter("status", requestStatus);
        query.addQueryParameter("carrier", courierEnum);
        final SearchResult<ReturnRequestModel> result = getSearchService().search(query);
        if (null != result && result.getResult().size() > 0) {
            return result.getResult();
        }
        return null;
    }

    @Override
    public Pair<Integer, List<ReturnRequestModel>> search(final SearchRMARequest request, final String stockLocationCode,
            final String panel) {
        // Prepare a Flexible Search Query on the basis of Search Parameters
        // populated by User
        final FlexibleSearchQueryBuilder queryBuilder = new FlexibleSearchQueryBuilder().from(ReturnRequestModel._TYPECODE, "c")
                .join(OrderModel._TYPECODE, "o", ReturnRequestModel.ORDER).join(WarehouseModel._TYPECODE, "w", ReturnRequestModel.WAREHOUSE)
                .select("c", ItemModel.PK);
        if (StringUtils.isNotEmpty(stockLocationCode)) {

            final FlexibleSearchQueryBuilder subQueryBuilder = new FlexibleSearchQueryBuilder().from(WarehouseModel._TYPECODE, "w");
            subQueryBuilder.whereEquals("w", WarehouseModel.INVOICESTOREID, stockLocationCode.trim()).select("w", WarehouseModel.PK);
            final FlexibleSearchQuery subQuery = subQueryBuilder.build();
            final SearchResult<WarehouseModel> subQueryResult = getSearchService().search(subQuery);
            final Set<String> warehouseCodes = new HashSet<>();
            if (subQueryResult.getCount() > 0) {
                final List<WarehouseModel> subQueryResultData = subQueryResult.getResult();
                final Set<WarehouseModel> subQueryResultDataSet = new HashSet<>(subQueryResultData);
                for (final WarehouseModel warehouse : subQueryResultDataSet) {
                    warehouseCodes.add(warehouse.getCode());
                }
            }
            queryBuilder.whereIn("w", WarehouseModel.CODE, warehouseCodes);
        }

        if (StringUtils.isNotEmpty(request.getAwbNumber())) {
            queryBuilder.whereEquals("c", ReturnRequestModel.AWBNUMBER, request.getAwbNumber().trim());
        }
        if (StringUtils.isNotEmpty(request.getCourier()) && CourierEnum.valueOf(request.getCourier()) != null) {
            queryBuilder.whereEquals("c", ReturnRequestModel.CARRIER, CourierEnum.valueOf(request.getCourier()));
        }
        if (StringUtils.isNotEmpty(request.getRmaNumber())) {
            queryBuilder.whereEquals("c", ReturnRequestModel.RMA, request.getRmaNumber().trim());
        }
        if (StringUtils.isNotEmpty(request.getOrderNumber())) {
            queryBuilder.whereEquals("o", OrderModel.CODE, request.getOrderNumber().trim());
        }
        if (request.getOrderDate() != null) {
            queryBuilder.whereLessThanEqualsDate("o", OrderModel.DATE, Long.parseLong(request.getOrderDate()));
        }
        if (request.getRmaDate() != null) {
            queryBuilder.whereLessThanEqualsDate("c", ReturnRequestModel.CREATIONTIME, Long.parseLong(request.getRmaDate()));
        }
        if (request.getStatus() != null && ReturnStatus.valueOf(request.getStatus()) != null) {
            queryBuilder.whereEquals("c", ReturnRequestModel.STATUS, ReturnStatus.valueOf(request.getStatus()));
        } else {
            final Set<ReturnStatus> returnStatus = new HashSet<>();
            if (panel.equalsIgnoreCase("RMA")) {
                if (request.isPageLoadOrRefresh()) {
                    returnStatus.add(ReturnStatus.NOT_PICKED);
                    returnStatus.add(ReturnStatus.UPLOAD_REJECTED);
                } else {
                    returnStatus.add(ReturnStatus.NOT_PICKED);
                    returnStatus.add(ReturnStatus.UPLOAD_PENDING);
                    returnStatus.add(ReturnStatus.UPLOAD_REJECTED);
                    returnStatus.add(ReturnStatus.PICK_INITIATED);
                    returnStatus.add(ReturnStatus.PICKED);
                    returnStatus.add(ReturnStatus.PICKUP_FAILED);
                    returnStatus.add(ReturnStatus.CUSTOMER_DISPATCHED);
                }
            } else {
                if (request.isPageLoadOrRefresh()) {
                    returnStatus.add(ReturnStatus.NOT_PICKED);
                    returnStatus.add(ReturnStatus.UPLOAD_PENDING);
                    returnStatus.add(ReturnStatus.UPLOAD_REJECTED);
                    returnStatus.add(ReturnStatus.PICK_INITIATED);
                    returnStatus.add(ReturnStatus.PICKED);
                    returnStatus.add(ReturnStatus.PICKUP_FAILED);
                    returnStatus.add(ReturnStatus.CUSTOMER_DISPATCHED);
                } else {
                    returnStatus.add(ReturnStatus.NOT_PICKED);
                    returnStatus.add(ReturnStatus.UPLOAD_PENDING);
                    returnStatus.add(ReturnStatus.UPLOAD_REJECTED);
                    returnStatus.add(ReturnStatus.PICK_INITIATED);
                    returnStatus.add(ReturnStatus.PICKED);
                    returnStatus.add(ReturnStatus.PICKUP_FAILED);
                    returnStatus.add(ReturnStatus.CUSTOMER_DISPATCHED);
                    returnStatus.add(ReturnStatus.RECEIVED);
                    returnStatus.add(ReturnStatus.PARTIAL_RECEIVED);
                    returnStatus.add(ReturnStatus.NOT_RECEIVED);
                }
            }
            queryBuilder.whereIn("c", ReturnRequestModel.STATUS, returnStatus);
        }
        if (request.getType() != null && ReturnRequestType.valueOf(request.getType()) != null) {
            queryBuilder.whereEquals("c", ReturnRequestModel.TYPE, ReturnRequestType.valueOf(request.getType()));
        }
        if (request.getPageNumber() != null) {
            queryBuilder.page(request.getPageNumber().intValue());
        }
        if (request.getRowsPerPage() != null) {
            queryBuilder.limit(request.getRowsPerPage().intValue());
        }
        queryBuilder.order("c", ReturnRequestModel.MODIFIEDTIME, false);
        final FlexibleSearchQuery query = queryBuilder.build();
        final SearchResult<ReturnRequestModel> result = getSearchService().search(query);
        if (result.getCount() > 0) {
            final Integer totalCount = Integer.valueOf(result.getTotalCount());
            final List<ReturnRequestModel> resultData = result.getResult();
            return new Pair<>(totalCount, resultData);
        }
        return new Pair<>(Integer.valueOf(0), null);
    }

    @Override
    public Pair<Integer, List<ReturnEntryModel>> searchForReturnEntries(final SearchRMARequest request, final String stockLocationCode,
            final String panelName) {
        // Prepare a Flexible Search Query on the basis of Search Parameters
        // populated by User
        final FlexibleSearchQueryBuilder queryBuilder = new FlexibleSearchQueryBuilder().from(ReturnEntryModel._TYPECODE, "c")
                .join(ReturnRequestModel._TYPECODE, "r", ReturnEntryModel.RETURNREQUEST)
                .join(OrderModel._TYPECODE, "o", ReturnRequestModel.ORDER, "r")
                .join(WarehouseModel._TYPECODE, "w", ReturnRequestModel.WAREHOUSE, "r").select("c", ItemModel.PK);
        final Set<ReturnStatus> returnStatus = new HashSet<>();
        if (StringUtils.isNotEmpty(stockLocationCode)) {
            final FlexibleSearchQueryBuilder subQueryBuilder = new FlexibleSearchQueryBuilder().from(WarehouseModel._TYPECODE, "w");
            subQueryBuilder.whereEquals("w", WarehouseModel.INVOICESTOREID, stockLocationCode.trim()).select("w", WarehouseModel.PK);
            final FlexibleSearchQuery subQuery = subQueryBuilder.build();
            final SearchResult<WarehouseModel> subQueryResult = getSearchService().search(subQuery);
            final Set<String> warehouseCodes = new HashSet<>();
            if (subQueryResult.getCount() > 0) {
                final List<WarehouseModel> subQueryResultData = subQueryResult.getResult();
                final Set<WarehouseModel> subQueryResultDataSet = new HashSet<>(subQueryResultData);
                for (final WarehouseModel warehouse : subQueryResultDataSet) {
                    warehouseCodes.add(warehouse.getCode());
                }
            }
            queryBuilder.whereIn("w", WarehouseModel.CODE, warehouseCodes);
        }
        if (StringUtils.isNotEmpty(request.getAwbNumber())) {
            queryBuilder.whereEquals("r", ReturnRequestModel.AWBNUMBER, request.getAwbNumber().trim());
        }
        if (StringUtils.isNotEmpty(request.getCourier()) && CourierEnum.valueOf(request.getCourier()) != null) {
            queryBuilder.whereEquals("r", ReturnRequestModel.CARRIER, CourierEnum.valueOf(request.getCourier()));
        }
        if (StringUtils.isNotEmpty(request.getRmaNumber())) {
            queryBuilder.whereEquals("r", ReturnRequestModel.RMA, request.getRmaNumber().trim());
        }
        if (StringUtils.isNotEmpty(request.getOrderNumber())) {
            queryBuilder.whereEquals("o", OrderModel.CODE, request.getOrderNumber().trim());
        }
        if (request.getOrderDate() != null) {
            queryBuilder.whereLessThanEqualsDate("o", OrderModel.DATE, Long.parseLong(request.getOrderDate()));
        }
        if (request.getRmaDate() != null) {
            queryBuilder.whereLessThanEqualsDate("c", ReturnRequestModel.CREATIONTIME, Long.parseLong(request.getRmaDate()));
        }
        if (request.getStatus() != null && ReturnStatus.valueOf(request.getStatus()) != null) {
            returnStatus.add(ReturnStatus.valueOf(request.getStatus()));
        }
        // TODO move in a method, better fetch from Facade.
        else if ("QC".equals(panelName)) {
            if (request.isPageLoadOrRefresh()) {
                returnStatus.add(ReturnStatus.RECEIVED);
                returnStatus.add(ReturnStatus.PARTIAL_RECEIVED);
            } else {
                returnStatus.add(ReturnStatus.RECEIVED);
                returnStatus.add(ReturnStatus.PARTIAL_RECEIVED);
                returnStatus.add(ReturnStatus.QC_PASS);
                returnStatus.add(ReturnStatus.QC_FAIL);
                returnStatus.add(ReturnStatus.QC_PARTIAL);
            }
        } else if ("RM".equals(panelName)) {
            if (request.isPageLoadOrRefresh()) {
                returnStatus.add(ReturnStatus.QC_PASS);
                returnStatus.add(ReturnStatus.QC_FAIL);
                returnStatus.add(ReturnStatus.QC_PARTIAL);
                returnStatus.add(ReturnStatus.READY);
                returnStatus.add(ReturnStatus.NOT_RECEIVED);
            } else {
                returnStatus.add(ReturnStatus.QC_PASS);
                returnStatus.add(ReturnStatus.QC_FAIL);
                returnStatus.add(ReturnStatus.QC_PARTIAL);
                returnStatus.add(ReturnStatus.READY);
                returnStatus.add(ReturnStatus.NOT_RECEIVED);
                returnStatus.add(ReturnStatus.REFUND_ISSUED);
                returnStatus.add(ReturnStatus.REFUND_REJECTED);
                returnStatus.add(ReturnStatus.EXCHANGE_ISSUED);
                returnStatus.add(ReturnStatus.EXCHANGE_REJECTED);
                returnStatus.add(ReturnStatus.NOT_NEEDED);
            }
        }
        queryBuilder.whereIn("c", ReturnEntryModel.STATUS, returnStatus);
        if (request.getType() != null && ReturnRequestType.valueOf(request.getType()) != null) {
            queryBuilder.whereEquals("r", ReturnRequestModel.TYPE, ReturnRequestType.valueOf(request.getType()));
        }
        if (request.getPageNumber() != null) {
            queryBuilder.page(request.getPageNumber().intValue());
        }
        if (request.getRowsPerPage() != null) {
            queryBuilder.limit(request.getRowsPerPage().intValue());
        }
        queryBuilder.order("c", ReturnEntryModel.MODIFIEDTIME, false);
        final FlexibleSearchQuery query = queryBuilder.build();
        final SearchResult<ReturnEntryModel> result = getSearchService().search(query);
        if (result.getCount() > 0) {
            final Integer totalCount = Integer.valueOf(result.getTotalCount());
            final List<ReturnEntryModel> resultData = result.getResult();
            if (CollectionUtils.isNotEmpty(resultData)) {
                return new Pair<>(totalCount, resultData);
            }
        }
        return new Pair<>(Integer.valueOf(0), null);
    }

    @Override
    public ReturnRequestModel getReturnRequestByRMA(final String rma) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(RETURN_REQUEST_RMA_QUERY);
        query.addQueryParameter("rma", rma);
        final SearchResult<ReturnRequestModel> result = getSearchService().search(query);
        if (null != result && result.getResult().size() > 0) {
            return result.getResult().get(0);
        }
        return null;
    }

    @Override
    public List<ReturnRequestModel> getReturnRequests() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(ASSIGNED_RETURN_REQUESTS);
        query.addQueryParameter("status", ReturnStatus.NOT_PICKED);
        final SearchResult<ReturnRequestModel> result = getSearchService().search(query);
        if (null != result && result.getResult().size() > 0) {
            return result.getResult();
        }
        return null;
    }

    @Override
    public ReturnEntryModel getReturnEntry(final long returnEntryId) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(RETURN_ENTRY_QUERY);
        query.addQueryParameter("returnEntryId", returnEntryId);
        final SearchResult<ReturnEntryModel> result = getSearchService().search(query);
        if (null != result && result.getResult().size() > 0) {
            return result.getResult().get(0);
        }
        return null;
    }

    @Override
    public Pair<Integer, List<ConsignmentModel>> searchDeliveryFailedConsignments(final SearchRMARequest request,
            final String stockLocationCode) {
        // Prepare a Flexible Search Query on the basis of Search Parameters
        // populated by User
        final FlexibleSearchQueryBuilder queryBuilder = new FlexibleSearchQueryBuilder().from(ConsignmentModel._TYPECODE, "c")
                .join(OrderModel._TYPECODE, "o", ConsignmentModel.ORDER)
                .join(WarehouseModel._TYPECODE, "w", ConsignmentModel.WAREHOUSE, "c").select("c", ItemModel.PK);
        final Set<ConsignmentStatus> consignmentStatus = new HashSet<>();
        if (StringUtils.isNotEmpty(stockLocationCode)) {
            final FlexibleSearchQueryBuilder subQueryBuilder = new FlexibleSearchQueryBuilder().from(WarehouseModel._TYPECODE, "w");
            subQueryBuilder.whereEquals("w", WarehouseModel.INVOICESTOREID, stockLocationCode.trim()).select("w", WarehouseModel.PK);
            final FlexibleSearchQuery subQuery = subQueryBuilder.build();
            final SearchResult<WarehouseModel> subQueryResult = getSearchService().search(subQuery);
            final Set<String> warehouseCodes = new HashSet<>();
            if (subQueryResult.getCount() > 0) {
                final List<WarehouseModel> subQueryResultData = subQueryResult.getResult();
                final Set<WarehouseModel> subQueryResultDataSet = new HashSet<>(subQueryResultData);
                for (final WarehouseModel warehouse : subQueryResultDataSet) {
                    warehouseCodes.add(warehouse.getCode());
                }
            }
            queryBuilder.whereIn("w", WarehouseModel.CODE, warehouseCodes);
        }
        // Invoice Number
        if (StringUtils.isNotEmpty(request.getInvoiceNumber())) {
            queryBuilder.whereEquals("c", ConsignmentModel.INVOICENUMBER, request.getInvoiceNumber().trim());
        }
        // Order Number
        if (StringUtils.isNotEmpty(request.getOrderNumber())) {
            queryBuilder.whereEquals("o", OrderModel.CODE, request.getOrderNumber().trim());
        }
        // Order Date
        if (request.getOrderDate() != null) {
            queryBuilder.whereLessThanEqualsDate("o", OrderModel.DATE, Long.parseLong(request.getOrderDate()));
        }
        // Courier Num

        if (StringUtils.isNotEmpty(request.getCourier()) && request.getCourier().equalsIgnoreCase(CourierEnum.ARAMAX.getCode())) {
            queryBuilder.whereEquals("c", ConsignmentModel.CARRIER, "ARA");
        } else if (StringUtils.isNotEmpty(request.getCourier()) && request.getCourier().equalsIgnoreCase(CourierEnum.BLUEDART.getCode())) {
            queryBuilder.whereEquals("c", ConsignmentModel.CARRIER, "BD");
        } else if (StringUtils.isNotEmpty(request.getCourier()) && request.getCourier().equalsIgnoreCase(CourierEnum.DOTZOT.getCode())) {
            queryBuilder.whereEquals("c", ConsignmentModel.CARRIER, "DOT");
        } else
            if (StringUtils.isNotEmpty(request.getCourier()) && request.getCourier().equalsIgnoreCase(CourierEnum.FIRSTFLIGHT.getCode())) {
            queryBuilder.whereEquals("c", ConsignmentModel.CARRIER, "FF");
        } else if (StringUtils.isNotEmpty(request.getCourier()) && request.getCourier().equalsIgnoreCase(CourierEnum.DELHIVERY.getCode())) {
            queryBuilder.whereEquals("c", ConsignmentModel.CARRIER, "DEL");
        } else if (StringUtils.isNotEmpty(request.getCourier()) && request.getCourier().equalsIgnoreCase(CourierEnum.EKART.getCode())) {
            queryBuilder.whereEquals("c", ConsignmentModel.CARRIER, "EK");
        } else {
            queryBuilder.whereEquals("c", ConsignmentModel.CARRIER, request.getCourier());
        }

        // AWB Number
        if (StringUtils.isNotEmpty(request.getAwbNumber())) {
            queryBuilder.whereEquals("c", ConsignmentModel.TRACKINGID, request.getAwbNumber().trim());
        }
        // Ship Date
        if (request.getShipDate() != null) {
            queryBuilder.whereLessThanEqualsDate("c", ConsignmentModel.SHIPPINGDATE, Long.parseLong(request.getShipDate()));
        }

        if (StringUtils.isNotEmpty(request.getConsignmentCode())) {
            queryBuilder.whereEquals("c", ConsignmentModel.CODE, request.getConsignmentCode().trim());
        }

        if (request.getStatus() != null && ReturnStatus.valueOf(request.getStatus()) != null) {
            consignmentStatus.add(ConsignmentStatus.valueOf(request.getStatus()));
        } else if (request.isPageLoadOrRefresh()) {
            consignmentStatus.add(ConsignmentStatus.DELIVERY_FAILED);
        } else {
            consignmentStatus.add(ConsignmentStatus.DELIVERY_FAILED);
            consignmentStatus.add(ConsignmentStatus.RETURN_TO_VENDOR);
            consignmentStatus.add(ConsignmentStatus.READY_TO_SHIP);
        }
        queryBuilder.whereIn("c", ConsignmentModel.STATUS, consignmentStatus);

        if (request.getPageNumber() != null) {
            queryBuilder.page(request.getPageNumber().intValue());
        }
        if (request.getRowsPerPage() != null) {
            queryBuilder.limit(request.getRowsPerPage().intValue());
        }
        queryBuilder.order("c", ConsignmentModel.MODIFIEDTIME, false);
        queryBuilder.whereNull("c", ConsignmentModel.RETURNREQUEST);
        final FlexibleSearchQuery query = queryBuilder.build();
        final SearchResult<ConsignmentModel> result = getSearchService().search(query);
        if (result.getCount() > 0) {
            final Integer totalCount = Integer.valueOf(result.getTotalCount());
            final List<ConsignmentModel> resultData = result.getResult();
            if (CollectionUtils.isNotEmpty(resultData)) {
                return new Pair<>(totalCount, resultData);
            }
        }
        return new Pair<>(Integer.valueOf(0), null);
    }

    @Override
    public ConsignmentModel getConsignmentModel(final String consignmentCode) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(CONSIGNMENT_MODEL_QUERY);
        query.addQueryParameter("consignmentCode", consignmentCode);
        final SearchResult<ConsignmentModel> result = getSearchService().search(query);
        if (null != result && result.getResult().size() > 0) {
            return result.getResult().get(0);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReturnRequestModel> getOpenRmasForStore(final String stockLocationCode) {
        final FlexibleSearchQueryBuilder queryBuilder = new FlexibleSearchQueryBuilder().from(ReturnRequestModel._TYPECODE, "r")
                .join(WarehouseModel._TYPECODE, "w", ReturnRequestModel.WAREHOUSE)
                .join(ReturnStatus._TYPECODE, "rs", ReturnRequestModel.STATUS).select("r", ItemModel.PK);
        if (StringUtils.isNotEmpty(stockLocationCode)) {
            queryBuilder.whereEquals("w", WarehouseModel.INVOICESTOREID, stockLocationCode.trim());
        }
        final Set<String> returnStatusSet = new HashSet<>();
        returnStatusSet.add(ReturnStatus.COMPLETE.getCode());
        queryBuilder.whereNotIn("rs", "code", returnStatusSet);
        queryBuilder.whereNotNull("r", ReturnRequestModel.BANKDETAILS);
        try {
            final FlexibleSearchQuery openRmaQuery = queryBuilder.build();
            LOG.debug("---------------------------------------------------------");
            LOG.debug(openRmaQuery.toString());
            LOG.debug("---------------------------------------------------------");
            final SearchResult<ReturnRequestModel> result = getSearchService().search(openRmaQuery);
            if (result.getCount() > 0) {
                final List<ReturnRequestModel> openRMAs = result.getResult();
                return openRMAs;
            }
        } catch (final Exception e) {
            LOG.error("No result for openRmas as exception was thrown by flexibleSearchService. Exception Message: " + e.getMessage());
            return null;
        }
        return Collections.EMPTY_LIST;
    }
    @Override
    public PointOfServiceModel getPosByWarehousecode(final String wareHouseCode)
    {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(POSMODEL_BY_WAREHOUSE_QUERY);
        query.addQueryParameter("wareHouseCode", wareHouseCode);
        final SearchResult<PointOfServiceModel> result = getSearchService().search(query);
        if (CollectionUtils.isNotEmpty(result.getResult()))
        {
            return result.getResult().get(0);
        }
        return null;
    }
    @Override
    public PointOfServiceModel getPosByInvoiceStoreID(final String invoiceStoreId)
    {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(POSMODEL_BY_INVOICE_ID_QUERY);
        query.addQueryParameter("invoiceStoreId", invoiceStoreId);
        final SearchResult<PointOfServiceModel> result = getSearchService().search(query);
        if (CollectionUtils.isNotEmpty(result.getResult()))
        {
            return result.getResult().get(0);
        }
        return null;
    }
    @Override
    public ReturnRequestModel getReturnRequestByExchangeOrder(final String exchangeOrderCode) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(RETURN_REQUEST_EXCHANGE_ORDER_QUERY);
        query.addQueryParameter("exchangeOrder", exchangeOrderCode);
        final SearchResult<ReturnRequestModel> result = getSearchService().search(query);
        if (null != result && result.getResult().size() > 0) {
            return result.getResult().get(0);
        }
        return null;
    }
}