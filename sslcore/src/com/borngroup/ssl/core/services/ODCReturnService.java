package com.borngroup.ssl.core.services;

import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.promotions.util.Pair;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.borngroup.ssl.fulfilmentprocess.returns.data.ClientRMADataResponse;
import com.borngroup.ssl.fulfilmentprocess.returns.data.ClientReturnQualityData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.ExchangeProductVariants;
import com.borngroup.ssl.fulfilmentprocess.returns.data.ProductRefundData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.RMARefundData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.RMExchangeRequestData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.RMExchangeResponseData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.ReceivedQuantityDataList;
import com.borngroup.ssl.fulfilmentprocess.returns.data.SearchRMARequest;
import com.borngroup.ssl.logistics.enums.CourierEnum;

public interface ODCReturnService {

    /**
     * Searches for the Return Requests on the basis of Search Form and returns the populated Response
     *
     * @param request {@link SearchRMARequest}
     * @param stockLocationCode String
     *
     * @return Returns the Pair of List of ClientRMADAtaResponse with total count
     *
     */
    Pair<Integer, List<ClientRMADataResponse>> search(SearchRMARequest request, String stockLocationCode, String panel);

    /**
     * Assigns the selected value of Carrier with AWB Number to the Provided Return Request RMA Number
     *
     * @param rma String
     * @param awb String
     * @param courier String
     * @return Returns if Courier Assignment is successful
     */
    Boolean assignCourier(String rma, String awb, String courier);

    /**
     * Updates the Status of Return Request of given RMA Number with the status selected by Panel User
     *
     * @param rma String
     * @param status String
     * @return Returns if Status Update is successful
     */
    Boolean updateStatus(String rma, String status);

    /**
     * Updates the Status of Return Request of given RMA Number with the status & quantity selected by Panel User
     *
     * @param rma String
     * @param quantity String
     * @return Returns status if Status Update is successful
     */
    Pair<Boolean, String> updateInwardStatus(ReceivedQuantityDataList quantity);

    /**
     * This method is used to get list of ReturnRequestModel for a given carrier where status is not picked
     *
     * @param courierEnum CourierEnum
     * @return List of ReturnRequestModel
     */
    List<ReturnRequestModel> getAssignedReturnRequests(CourierEnum courierEnum);

    /**
     *
     * Updates Return Request as per the CSV
     *
     * @param rma String RMA Number
     * @param carrier String Courier
     * @param awb String AWB
     * @param status String Status
     * @return Status of Update
     */
    String updateRMAForCSVEntry(String rma, String carrier, String awb, String status);

    /**
     * @param request
     * @param stockLocationCode
     * @param panel
     * @return
     */
    Pair<Integer, List<ClientRMADataResponse>> searchReturnEntries(SearchRMARequest request, String stockLocationCode, String panelName);

    Pair<Boolean, String> updateInventory(ClientReturnQualityData qualityData);

    /**
     * @param productReturnData
     * @return
     */
    Pair<Boolean, Boolean> saveRefundDetails(ProductRefundData productReturnData);

    /**
     * @param rmaReturnData
     * @return
     */
    Pair<Boolean, Set<ReturnStatus>> processRefundDetails(RMARefundData rmaReturnData, ReturnRequestModel returnRequest);

    /**
     * Fetch return entry.
     *
     * @param qualityData the quality data
     * @return the return entry model
     */
    ReturnEntryModel fetchReturnEntry(final ClientReturnQualityData qualityData);

    /**
     * @param rmExchangeRequest
     * @return
     */
    Pair<RMExchangeResponseData, Boolean> saveExchangeDetails(RMExchangeRequestData rmExchangeRequest);

    /**
     * @param productSKU
     * @param rma
     * @return
     */
    ExchangeProductVariants getVariants(String productSKU, String rma);

    /**
     * @param rmaRefundData
     * @param returnRequest
     * @param isWalletFailureTicket
     */
    void createCSTicket(ReturnRequestModel returnRequest, String note);

    /**
     * @param rma
     * @return
     */
    Pair<Double, ReturnRequestModel> getPriceDiff(String rma);

    /**
     * @param returnRequest
     * @param rmaRefundData
     */
    void createPriceDiffCSTicket(ReturnRequestModel returnRequest, RMARefundData rmaRefundData);

    /**
     * @param rmaNumber
     * @return
     */
    Pair<CartModel, ReturnRequestModel> getCart(String rmaNumber);

    /**
     * @param returnRequest
     * @return
     */
    Pair<Boolean, Set> splitEntries(ReturnRequestModel returnRequest);

    /**
     * @param returnStatus
     * @param returnRequest
     */
    void sendEmail(Set<ReturnStatus> returnStatus, ReturnRequestModel returnRequest);

    /**
     * @param request
     * @param stockLocationCode
     * @param panelName
     * @return
     */
    Pair<Integer, List<ClientRMADataResponse>> searchDeliveryFailedConsignmentEntries(SearchRMARequest request, String stockLocationCode);

    /**
     * @param consignmentCode
     * @return
     */
    Pair<Boolean, String> cancelConsignment(String consignmentCode);

    /**
     * @param consignmentCode
     * @return
     */
    Pair<Boolean, String> reShipConsignment(String consignmentCode);

    /**
     * @param rma
     * @return
     */
    String regenerateRma(String rma);

    /**
     * Returns ReturnRequestModel having passed rma id.
     *
     * @param rma the rma id
     * @return {@link ReturnRequestModel }
     */
    ReturnRequestModel getReturnRequestByRMA(String rma);

    boolean validateStatus(final String currentStatus, final String status);

    /**
     * @param rmaNumber
     * @param refundMode
     * @param accountName
     * @param accountNumber
     * @param ifscCode
     * @param bankName
     * @return
     */
    String convertToReturn(String rmaNumber, String refundMode, String accountName, String accountNumber, String ifscCode, String bankName);

    /**
     * @param searchRMARequest
     * @param response
     * @param storeLocationCode
     * @throws Exception
     */
    void downloadRequest(SearchRMARequest searchRMARequest, HttpServletResponse response, String storeLocationCode) throws Exception;

    /**
     * @param response
     * @param storeLocationCode
     * @throws Exception
     */
    void downloadOpenRMAs(final HttpServletResponse response, final String storeLocationCode) throws Exception;

    /**
     * @param acNumber
     * @return
     */
    String maskAccountNumber(String acNumber);

    ReturnRequestModel getReturnRequestByExchangeOrder(String exchangeOrderCode);
}
