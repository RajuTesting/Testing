/**
 *
 */
package com.borngroup.ssl.core.dao;

import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.promotions.util.Pair;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.List;

import com.borngroup.ssl.core.enums.ReturnRequestType;
import com.borngroup.ssl.fulfilmentprocess.returns.data.SearchRMARequest;
import com.borngroup.ssl.logistics.enums.CourierEnum;

/**
 * @author bhavya2486
 *
 */
public interface SSLODCReturnDao {

    /**
     * This method is used to get the Pair of Return Requests with total count for Search performed by User
     *
     * @param request
     *        {@link SearchRMARequest}
     * @param stockLocationCode
     *        String
     * @return Pair of Integer, ReturnRequestModel
     */
    public Pair<Integer, List<ReturnRequestModel>> search(final SearchRMARequest request, final String stockLocationCode, final String panel);

    /**
     * This method is used to get the List of Assigned, Not Picked, Return Requests
     *
     * @return List of ReturnRequestModel
     */
    public List<ReturnRequestModel> getReturnRequests();

    /**
     * This method is used to get the Return Request for a given return type and status where AWB and Carrier is not null
     *
     * @param courierEnum
     *        CourierEnum
     * @param requestType
     *        ReturnRequestType
     * @param requestStatus
     *        ReturnStatus
     * @return List of ReturnRequestModel
     */
    public List<ReturnRequestModel> getReturnRequestsByTypeAndStatusForAWBCarrier(final CourierEnum courierEnum,
            final ReturnRequestType requestType, final ReturnStatus requestStatus);

    /**
     * This method returns the Return Request for a given RMA Number
     *
     * @param rma
     *        String
     * @return ReturnRequestModel
     */
    public ReturnRequestModel getReturnRequestByRMA(final String rma);

    /**
     * This method is used to get the Pair of Return Request Entries with total count for Search performed by User
     *
     * @param request
     *        {@link SearchRMARequest}
     * @param stockLocationCode
     *        String
     * @return Pair of Integer, ReturnRequestEntry
     */
    public Pair<Integer, List<ReturnEntryModel>> searchForReturnEntries(final SearchRMARequest request, final String stockLocationCode,
            final String panelName);

    /**
     * @param returnEntryId
     * @return
     */
    ReturnEntryModel getReturnEntry(long returnEntryId);

    /**
     * @param request
     * @param stockLocationCode
     * @return
     */
    Pair<Integer, List<ConsignmentModel>> searchDeliveryFailedConsignments(SearchRMARequest request, String stockLocationCode);

    /**
     * @param consignmentCode
     * @return
     */
    ConsignmentModel getConsignmentModel(String consignmentCode);
    /**
     * Method returns all the open RMAs i.e. RMA with bank details associated which are not processed for the store whose location code is
     * passed as argument. If no location code is passed all the open RMAs will be returned.
     *
     * @param stockLocationCode : Location Code of the store for which we want all the open RMAs.
     * @return : List of Open RMAs.
     */
    List<ReturnRequestModel> getOpenRmasForStore(String stockLocationCode);
 /**
     * @param wareHouseCode
     * @return ConsignmentModel
     */
    PointOfServiceModel getPosByWarehousecode(String wareHouseCode);
    /**
     * This method is used to get the point of Service by Invoice Store id
     *
     * @param invoiceStoreId
     *            invoice StoreId
     *
     * @return {@link PointOfServiceModel}
     */
    PointOfServiceModel getPosByInvoiceStoreID(String invoiceStoreId);

    ReturnRequestModel getReturnRequestByExchangeOrder(final String exchangeOrderCode);
}
