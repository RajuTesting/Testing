/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.basecommerce.enums.RefundReason;
import de.hybris.platform.basecommerce.enums.ReplacementReason;
import de.hybris.platform.basecommerce.enums.ReturnAction;
import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.StockData;
import de.hybris.platform.commerceservices.impersonation.ImpersonationContext;
import de.hybris.platform.commerceservices.impersonation.ImpersonationService;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.order.CartFactory;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.promotions.util.Pair;
import de.hybris.platform.returns.model.RefundEntryModel;
import de.hybris.platform.returns.model.ReplacementEntryModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.user.AddressService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.stock.exception.StockLevelNotFoundException;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.ticket.enums.CsEventReason;
import de.hybris.platform.ticket.enums.CsInterventionType;
import de.hybris.platform.ticket.enums.CsTicketCategory;
import de.hybris.platform.ticket.enums.CsTicketPriority;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsAgentGroupModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketBusinessService;
import de.hybris.platform.tx.Transaction;
import de.hybris.platform.variants.model.VariantProductModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.dao.SSLODCReturnDao;
import com.borngroup.ssl.core.data.returns.ReturnableEntity;
import com.borngroup.ssl.core.enums.RMARejectionReason;
import com.borngroup.ssl.core.enums.ReturnRequestPickup;
import com.borngroup.ssl.core.enums.ReturnRequestRefundMode;
import com.borngroup.ssl.core.enums.ReturnRequestType;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.ExchangeStockLevelModel;
import com.borngroup.ssl.core.model.ReturnRequestBankDetailsModel;
import com.borngroup.ssl.core.services.ODCReturnService;
import com.borngroup.ssl.core.services.SSLReturnDocumentGeneratorService;
import com.borngroup.ssl.core.services.email.impl.SSLSendEmailWithAttachmentService;
import com.borngroup.ssl.core.services.returns.SSLExchangeService;
import com.borngroup.ssl.core.services.returns.SSLReturnService;
import com.borngroup.ssl.core.services.returns.impl.SSLReturnServiceImpl;
import com.borngroup.ssl.core.sms.service.SslCustomerTouchPointService;
import com.borngroup.ssl.core.util.ConsignmentTrackingUtils;
import com.borngroup.ssl.fulfilmentprocess.enums.ConsignmentEntryStatus;
import com.borngroup.ssl.fulfilmentprocess.enums.InternalConsignmentEntryStatus;
import com.borngroup.ssl.fulfilmentprocess.model.InternalConsignmentEntryModel;
import com.borngroup.ssl.fulfilmentprocess.order.data.ClientImageData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.ClientExchangeEntryData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.ClientRMADataResponse;
import com.borngroup.ssl.fulfilmentprocess.returns.data.ClientReturnExchangeData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.ClientReturnQualityData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.ExchangeProductVariants;
import com.borngroup.ssl.fulfilmentprocess.returns.data.ProductRefundData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.RMARefundData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.RMExchangeRequestData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.RMExchangeResponseData;
import com.borngroup.ssl.fulfilmentprocess.returns.data.ReceivedQuantityDataList;
import com.borngroup.ssl.fulfilmentprocess.returns.data.SearchRMARequest;
import com.borngroup.ssl.fulfilmentprocess.returns.data.SelectedExchangeProduct;
import com.borngroup.ssl.logistics.enums.CourierEnum;

/**
 * @author bhavya2486
 *
 */
public class DefaultODCReturnService implements ODCReturnService {

    private static final Logger LOG = Logger.getLogger(DefaultODCReturnService.class);
    public static final String EXCEL_NAME = "CourierRMA";
    public static final String OPEN_RMA_EXCEL_NAME = "OpenRMAs";

    /**
     * Character used to mask a digit in account number.
     */
    private static final char MASK_CHAR = '*';

    private Converter<ReturnRequestModel, ClientRMADataResponse> sslDefaultRMAConverter;
    private Converter<ReturnEntryModel, ClientRMADataResponse> sslDefaultRMAReturnEntryConverter;
    private Converter<ProductModel, ProductData> sslDefaultRMAProductConverter;
    private Converter<ProductData, ClientImageData> sslDefaultRMAProductImageDataConverter;
    private SSLODCReturnDao sslODCReturnDao;
    private ModelService modelService;
    private SSLSendEmailWithAttachmentService sslSendEmailWithAttachmentService;
    private BusinessProcessService businessProcessService;

    private Converter<ConsignmentModel, ClientRMADataResponse> sslRTOConverter;

    @Autowired
    private UserService userService;

    @Resource(name = "ticketBusinessService")
    private TicketBusinessService ticketBusinessService;

    @Autowired
    private SSLReturnService returnService;

    @Autowired
    private SSLExchangeService exchangeService;

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Autowired
    private CartFactory cartFactory;

    @Autowired
    private ProductService productService;

    @Autowired
    private CommerceCartService commerceCartService;

    @Autowired
    private ImpersonationService impersonationService;

    @Autowired
    private EnumerationService enumerationService;

    @Autowired
    private AddressService addressService;

    @Resource(name = "returnDocumentService")
    private SSLReturnDocumentGeneratorService returnDocumentService;

    @Resource
    private SslCustomerTouchPointService sslCustomerTouchPointService;

    @Resource(name = "stockService")
    private StockService stockService;

    @Resource
    private CatalogVersionService catalogVersionService;

    @Resource(name = "cartService")
    private CartService cartService;

    private SSLReturnServiceImpl sslReturnServiceImpl;

    public Converter<ProductModel, ProductData> getSslDefaultRMAProductConverter() {
        return sslDefaultRMAProductConverter;
    }

    public void setSslDefaultRMAProductConverter(final Converter<ProductModel, ProductData> sslDefaultRMAProductConverter) {
        this.sslDefaultRMAProductConverter = sslDefaultRMAProductConverter;
    }

    public Converter<ProductData, ClientImageData> getSslDefaultRMAProductImageDataConverter() {
        return sslDefaultRMAProductImageDataConverter;
    }

    public void setSslDefaultRMAProductImageDataConverter(
            final Converter<ProductData, ClientImageData> sslDefaultRMAProductImageDataConverter) {
        this.sslDefaultRMAProductImageDataConverter = sslDefaultRMAProductImageDataConverter;
    }

    public Converter<ReturnRequestModel, ClientRMADataResponse> getSslDefaultRMAConverter() {
        return sslDefaultRMAConverter;
    }

    public void setSslDefaultRMAConverter(final Converter<ReturnRequestModel, ClientRMADataResponse> sslDefaultRMAConverter) {
        this.sslDefaultRMAConverter = sslDefaultRMAConverter;
    }

    public Converter<ReturnEntryModel, ClientRMADataResponse> getSslDefaultRMAReturnEntryConverter() {
        return sslDefaultRMAReturnEntryConverter;
    }

    public void setSslDefaultRMAReturnEntryConverter(
            final Converter<ReturnEntryModel, ClientRMADataResponse> sslDefaultRMAReturnEntryConverter) {
        this.sslDefaultRMAReturnEntryConverter = sslDefaultRMAReturnEntryConverter;
    }

    public SSLODCReturnDao getSslODCReturnDao() {
        return sslODCReturnDao;
    }

    public void setSslODCReturnDao(final SSLODCReturnDao sslODCReturnDao) {
        this.sslODCReturnDao = sslODCReturnDao;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    public SSLSendEmailWithAttachmentService getSslSendEmailWithAttachmentService() {
        return sslSendEmailWithAttachmentService;
    }

    public void setSslSendEmailWithAttachmentService(final SSLSendEmailWithAttachmentService sslSendEmailWithAttachmentService) {
        this.sslSendEmailWithAttachmentService = sslSendEmailWithAttachmentService;
    }

    @Override
    public Pair<Integer, List<ClientRMADataResponse>> search(final SearchRMARequest request, final String stockLocationCode,
            final String panel) {
        final Pair<Integer, List<ReturnRequestModel>> returnRequestsPair = getSslODCReturnDao().search(request, stockLocationCode, panel);
        final List<ClientRMADataResponse> rmaDataResponses = new ArrayList<>();
        if (returnRequestsPair != null && returnRequestsPair.getKey().intValue() > 0
                && CollectionUtils.isNotEmpty(returnRequestsPair.getValue())) {
            for (final ReturnRequestModel returnRequest : returnRequestsPair.getValue()) {
                final ClientRMADataResponse clientRMADataResponse = getSslDefaultRMAConverter().convert(returnRequest);
                rmaDataResponses.add(clientRMADataResponse);
            }
            return new Pair<>(returnRequestsPair.getKey(), rmaDataResponses);
        }
        return new Pair<>(0, rmaDataResponses);
    }

    @Override
    public Pair<Integer, List<ClientRMADataResponse>> searchReturnEntries(final SearchRMARequest request, final String stockLocationCode,
            final String panelName) {
        final Pair<Integer, List<ReturnEntryModel>> returnRequestsPair = getSslODCReturnDao().searchForReturnEntries(request,
                stockLocationCode, panelName);
        final List<ClientRMADataResponse> rmaDataResponses = new ArrayList<>();
        if (returnRequestsPair != null && returnRequestsPair.getKey().intValue() > 0
                && CollectionUtils.isNotEmpty(returnRequestsPair.getValue())) {

            String previousRMAID = null;
            boolean previousRMAReadyForProcess = false;
            boolean canConvertToReturn = false;
            for (final ReturnEntryModel returnEntry : returnRequestsPair.getValue()) {
                final ClientRMADataResponse clientRMADataResponse = getSslDefaultRMAReturnEntryConverter().convert(returnEntry);
                LOG.info("Shipping Fee returned for RMA: " + request.getRmaNumber() + " is: "
                        + clientRMADataResponse.getShippingAmountToBeReturned());
                if ("RM".equals(panelName)) {
                    if (clientRMADataResponse.getEntries() != null && clientRMADataResponse.getEntries().size() != 0) {
                        for (final ClientReturnExchangeData list : clientRMADataResponse.getEntries()) {
                            if (list.getEntries() != null && list.getEntries().size() != 0) {
                                for (final ClientExchangeEntryData exchangedList : list.getEntries()) {
                                    final ProductModel productModel = productService.getProductForCode(catalogVersionService
                                            .getCatalogVersion(SslCoreConstants.CATALOG_NAME, SslCoreConstants.ONLINE_CATALOG_VERSION),
                                            exchangedList.getSku());
                                    exchangedList.setStock(stockService.getTotalStockLevelAmount(productModel));
                                }
                            }
                        }
                    }
                }
                rmaDataResponses.add(clientRMADataResponse);
                if ("RM".equals(panelName)) {
                    final String currentRMAID = returnEntry.getReturnRequest().getRMA();
                    if (!currentRMAID.equals(previousRMAID)) {
                        previousRMAID = currentRMAID;
                        previousRMAReadyForProcess = isRMAReadyForProcess(returnEntry);
                        canConvertToReturn = canConvertToReturn(returnEntry);
                        clientRMADataResponse.setRmaReadyForProcess(previousRMAReadyForProcess);
                        clientRMADataResponse.setConvertToReturn(canConvertToReturn);
                    } else {
                        clientRMADataResponse.setRmaReadyForProcess(previousRMAReadyForProcess);
                        clientRMADataResponse.setConvertToReturn(canConvertToReturn);
                    }
                }
            }
            return new Pair<>(returnRequestsPair.getKey(), rmaDataResponses);
        }
        return new Pair<>(0, rmaDataResponses);
    }

    @Override
    public Boolean assignCourier(final String rma, final String awb, final String courier) {
        if (null != rma && null != courier && null != awb) {
            final ReturnRequestModel returnRequest = getSslODCReturnDao().getReturnRequestByRMA(rma);
            if (null != returnRequest) {
                returnRequest.setAwbNumber(awb);
                returnRequest.setCarrier(CourierEnum.valueOf(courier));
                getModelService().save(returnRequest);
                for (final ConsignmentModel consignment : returnRequest.getOrder().getConsignments()) {
                    if (returnRequest.equals(consignment.getReturnRequest())) {
                        if (StringUtils.isNotBlank(consignment.getTrackingID())) {
                            ConsignmentTrackingUtils.saveInAwbNumberHistory(consignment.getTrackingID(), consignment);
                        }
                        consignment.setTrackingID(awb);
                        consignment.setCarrier(courier);
                        getModelService().save(consignment);
                        break;
                    }
                }
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean updateStatus(final String rma, final String status) {
        if (null != rma && null != status) {
            final ReturnRequestModel returnRequest = getSslODCReturnDao().getReturnRequestByRMA(rma);
            if (null != returnRequest) {
                LOG.info("  Status Updating for Return from RMA Panel For RMA: " + returnRequest.getRMA() + " from Current status "
                        + returnRequest.getStatus() + " to " + status);
                returnRequest.setStatus(ReturnStatus.valueOf(status));
                for (final ReturnEntryModel returnEntry : returnRequest.getReturnEntries()) {
                    if (ReturnStatus.valueOf(status).equals(ReturnStatus.PICKUP_FAILED)
                            && ReturnRequestType.EXCHANGE.equals(returnRequest.getType())) {
                        final ReplacementEntryModel replacementEntry = (ReplacementEntryModel) returnEntry;
                        for (final ExchangeStockLevelModel exchangeStock : replacementEntry.getExchangeStockLevels()) {
                            if (exchangeStock.getQuantity() > 0) {
                                final StockLevelModel stock = exchangeStock.getStockLevel();
                                stock.setOnlineHold(stock.getOnlineHold() - (int) exchangeStock.getQuantity());
                                modelService.save(stock);
                            }
                        }
                    }
                    returnEntry.setStatus(ReturnStatus.valueOf(status));
                    if (status.equalsIgnoreCase("NOT_NEEDED")) {
                        returnEntry.setReceivedQuantity(0l);
                        LOG.info("updateStatus: Received quantity set to 0 for rma : " + rma);
                    }
                    if (status.equalsIgnoreCase("UPLOAD_REJECTED")) {
                        LOG.info("Removing Courier Details for Upload Rejected Staus");
                        returnRequest.setAwbNumber(null);
                        returnRequest.setCarrier(null);
                        returnRequest.setCourierService(null);
                    }

                    getModelService().save(returnEntry);
                }
                getModelService().save(returnRequest);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public List<ReturnRequestModel> getAssignedReturnRequests(final CourierEnum courierEnum) {
        final List<ReturnRequestModel> finalReturnList = new ArrayList<ReturnRequestModel>();
        final List<ReturnRequestModel> notPickedList = getSslODCReturnDao().getReturnRequestsByTypeAndStatusForAWBCarrier(courierEnum,
                ReturnRequestType.RETURN, ReturnStatus.NOT_PICKED);
        if (CollectionUtils.isNotEmpty(notPickedList)) {
            finalReturnList.addAll(notPickedList);
        }
        final List<ReturnRequestModel> pickInitiatedList = getSslODCReturnDao().getReturnRequestsByTypeAndStatusForAWBCarrier(courierEnum,
                ReturnRequestType.RETURN, ReturnStatus.PICK_INITIATED);
        if (CollectionUtils.isNotEmpty(pickInitiatedList)) {
            finalReturnList.addAll(pickInitiatedList);
        }

        final List<ReturnRequestModel> pickedList = getSslODCReturnDao().getReturnRequestsByTypeAndStatusForAWBCarrier(courierEnum,
                ReturnRequestType.RETURN, ReturnStatus.PICKED);
        if (CollectionUtils.isNotEmpty(pickedList)) {
            finalReturnList.addAll(pickedList);
        }

        return finalReturnList;
    }

    @Override
    public String updateRMAForCSVEntry(final String rma, final String courier, final String awb, final String status) {
        boolean checkAWB = false;
        boolean checkCourier = false;
        boolean checkStatus = false;
        try {
            if (null != rma) {
                final ReturnRequestModel returnRequest = getSslODCReturnDao().getReturnRequestByRMA(rma);
                ConsignmentModel returnConsignment = null;
                for (final ConsignmentModel consignment : returnRequest.getOrder().getConsignments()) {
                    if (returnRequest.equals(consignment.getReturnRequest())) {
                        returnConsignment = consignment;
                        break;
                    }
                }
                if (returnRequest == null) {
                    return SslCoreConstants.NOT_UPDATED;
                } else if (returnRequest.getStatus() == ReturnStatus.NOT_PICKED || returnRequest.getStatus() == ReturnStatus.PICK_INITIATED
                        || returnRequest.getStatus() == ReturnStatus.PICKED) {

                    // Set AWB number if it is not blank
                    if (StringUtils.isNotBlank(awb)) {
                        returnRequest.setAwbNumber(awb);
                        if (null != returnConsignment) {
                            if (StringUtils.isNotBlank(returnConsignment.getTrackingID())) {
                                ConsignmentTrackingUtils.saveInAwbNumberHistory(returnConsignment.getTrackingID(), returnConsignment);
                            }
                            returnConsignment.setTrackingID(awb);
                        }
                        checkAWB = true;
                    }

                    // Set courier if it is in given LSPs
                    if (StringUtils.isNotBlank(courier)) {
                        if (courier.equals(CourierEnum.BLUEDART.toString()) || courier.equals(CourierEnum.DELHIVERY.toString())
                                || courier.equals(CourierEnum.DOTZOT.toString()) || courier.equals(CourierEnum.FIRSTFLIGHT.toString())
                                || courier.equals(CourierEnum.EKART.toString()) || courier.equals(CourierEnum.SHADOWFAX.toString())) {
                            returnRequest.setCarrier(CourierEnum.valueOf(courier));
                            if (null != returnConsignment) {
                                returnConsignment.setCarrier(courier);
                            }
                            checkCourier = true;
                        } else {
                            return SslCoreConstants.NOT_UPDATED;
                        }
                    }

                    // Set status if courier and AWB number is not blank
                    if (StringUtils.isNotBlank(status)) {
                        if (((checkAWB && checkCourier) && validateStatus(returnRequest.getStatus().toString(), status.toUpperCase()))) {
                            final List<ReturnStatus> returnStatus = enumerationService.getEnumerationValues(ReturnStatus.class);
                            ReturnStatus statusEnum = null;
                            for (final ReturnStatus returns : returnStatus) {
                                final String returnStatusName = enumerationService.getEnumerationName(returns);
                                if (returnStatusName.equalsIgnoreCase(status)) {
                                    statusEnum = returns;
                                    break;
                                }
                            }
                            returnRequest.setStatus(statusEnum);
                            checkStatus = true;
                        } else {
                            return SslCoreConstants.NOT_UPDATED;
                        }
                    }
                    if (checkAWB || checkCourier || checkStatus) {
                        getModelService().save(returnRequest);
                        getModelService().save(returnConsignment);
                        return SslCoreConstants.UPDATED;
                    } else {
                        return SslCoreConstants.NOT_UPDATED;
                    }

                } else {
                    return SslCoreConstants.NOT_UPDATED;
                }
            }
        } catch (

        final Exception exception)

        {
            return SslCoreConstants.NOT_UPDATED;
        }
        return SslCoreConstants.NOT_UPDATED;

    }

    @Override
    public Pair<Boolean, String> updateInventory(final ClientReturnQualityData qualityData) {

        final ReturnEntryModel returnEntry = getSslODCReturnDao().getReturnEntry(qualityData.getReturnEntryId());
        if (null != returnEntry) {
            final long inventoryQuantity = qualityData.getInventoryQuantity();
            final long damagedQuantity = qualityData.getDamagedQuantity();
            final String comments = qualityData.getComments();
            returnEntry.setInventoryZoneQuantity(inventoryQuantity);
            returnEntry.setDamagedZoneQuantity(damagedQuantity);
            returnEntry.setNotes(comments);
            setReturnEntryStatus(returnEntry, inventoryQuantity, damagedQuantity);
            final ReturnRequestModel rrm = returnEntry.getReturnRequest();
            rrm.setStatus(ReturnStatus.QUALITY_CHECK);
            getModelService().save(rrm);
            getModelService().save(returnEntry);
            return new Pair<>(Boolean.TRUE, enumerationService.getEnumerationName(returnEntry.getStatus()));
        }
        return new Pair<>(Boolean.FALSE, null);
    }

    @Override
    public ReturnEntryModel fetchReturnEntry(final ClientReturnQualityData qualityData) {
        return getSslODCReturnDao().getReturnEntry(qualityData.getReturnEntryId());

    }

    private void setReturnEntryStatus(final ReturnEntryModel returnEntry, final long inventoryQuantity, final long damagedQuantity) {

        final Long receivedQuantity = returnEntry.getReceivedQuantity();

        if (receivedQuantity == null || receivedQuantity.longValue() == 0 || receivedQuantity.longValue() == damagedQuantity) {
            returnEntry.setStatus(ReturnStatus.QC_FAIL);
        } else if (receivedQuantity.longValue() == inventoryQuantity) {
            returnEntry.setStatus(ReturnStatus.QC_PASS);
        } else if (damagedQuantity > 0) {
            returnEntry.setStatus(ReturnStatus.QC_PARTIAL);
        }

        if (ReturnRequestType.RETURN.equals(returnEntry.getReturnRequest().getType())) {
            // TODO Update Inventory in MMS System.
        }
    }

    @Override
    public Pair<Boolean, Boolean> saveRefundDetails(final ProductRefundData productReturnData) {

        final ReturnEntryModel returnEntry = getSslODCReturnDao().getReturnEntry(productReturnData.getReturnEntryId());
        if (null != returnEntry) {
            returnEntry.setFinalReturnQuantity(productReturnData.getFinalReturnQuantity());
            returnEntry.setFinalRefundAmount(productReturnData.getFinalRefundAmount());
            returnEntry.setShippingAmount(productReturnData.getShippingAmount());
            returnEntry.setNotes(productReturnData.getComments());
            returnEntry.setStatus(ReturnStatus.READY);
            if (isRMAReadyForProcess(returnEntry)) {
                returnEntry.getReturnRequest().setCanResendBankAcLink(Boolean.FALSE);
                getModelService().save(returnEntry.getReturnRequest());
            }
            getModelService().save(returnEntry);
            return new Pair<>(Boolean.TRUE, Boolean.valueOf(isRMAReadyForProcess(returnEntry)));
        }
        return new Pair<>(Boolean.FALSE, null);
    }

    private boolean isRMAReadyForProcess(final ReturnEntryModel returnEntry) {

        final List<ReturnEntryModel> returnEntries = returnEntry.getReturnRequest().getReturnEntries();
        boolean isAllEntryInReadyStatus = true;
        for (final ReturnEntryModel temp : returnEntries) {
            if (!ReturnStatus.READY.equals(temp.getStatus())) {
                isAllEntryInReadyStatus = false;
            }
        }
        return isAllEntryInReadyStatus;
    }

    private boolean canConvertToReturn(final ReturnEntryModel returnEntry) {
        if (!ReturnRequestType.EXCHANGE.equals(returnEntry.getReturnRequest().getType())) {
            return false;
        }

        final List<ReturnEntryModel> returnEntries = returnEntry.getReturnRequest().getReturnEntries();
        final Set<ReturnStatus> allowedStatusForConversion = new HashSet<ReturnStatus>() {
            {
                add(ReturnStatus.QC_PASS);
                add(ReturnStatus.QC_FAIL);
                add(ReturnStatus.QC_PARTIAL);
                add(ReturnStatus.NOT_NEEDED);
                add(ReturnStatus.NOT_RECEIVED);
            }
        };

        boolean canConvertToExchange = true;
        for (final ReturnEntryModel entry : returnEntries) {
            if (!allowedStatusForConversion.contains(entry.getStatus())) {
                canConvertToExchange = false;
                break;
            }
        }

        return canConvertToExchange;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.ODCReturnService#updateInwardStatus(com.
     * borngroup.ssl.fulfilmentprocess.returns.data.ReceivedQuantityDataList)
     */
    @Override
    public Pair<Boolean, String> updateInwardStatus(final ReceivedQuantityDataList quantity) {
        // YTODO Auto-generated method stub

        final int size = quantity.getQuantityList().size();
        final String rma = quantity.getQuantityList().get(0).getRma();
        if (null != rma) {
            final ReturnRequestModel returnRequest = getSslODCReturnDao().getReturnRequestByRMA(rma);
            long totalReceivedQuantity = 0l;
            long totalExpectedQuantity = 0l;
            if (null != returnRequest) {
                for (final ReturnEntryModel returnEntry : returnRequest.getReturnEntries()) {
                    final String pk = returnEntry.getPk().toString();
                    int i = 0;
                    for (i = 0; i < size; i++) {
                        final long receivedQuantityid = quantity.getQuantityList().get(i).getReceivedQuantityid();
                        if (receivedQuantityid == Long.parseLong(pk)) {
                            final long receivedQuantity = quantity.getQuantityList().get(i).getReceivedQuantity();
                            final long expectedQuantity = returnEntry.getExpectedQuantity();
                            totalExpectedQuantity += expectedQuantity;
                            totalReceivedQuantity += receivedQuantity;
                            if (receivedQuantity < 0) {
                                return new Pair<>(Boolean.FALSE, null);
                            }
                            if (receivedQuantity == 0) {
                                returnEntry.setReceivedQuantity(receivedQuantity);
                                LOG.info("case 1: Received quantity set to " + receivedQuantity + " for rma : " + rma);
                                returnEntry.setStatus(ReturnStatus.NOT_RECEIVED);
                                returnEntry.setInventoryZoneQuantity(receivedQuantity);
                                returnEntry.setDamagedZoneQuantity(receivedQuantity);
                                break;
                            } else if (receivedQuantity < expectedQuantity && receivedQuantity > 0) {
                                returnEntry.setReceivedQuantity(receivedQuantity);
                                LOG.info("case 2 : Received quantity set to " + receivedQuantity + " for rma : " + rma);
                                returnEntry.setStatus(ReturnStatus.PARTIAL_RECEIVED);
                                break;
                            } else if (receivedQuantity == expectedQuantity) {
                                returnEntry.setReceivedQuantity(receivedQuantity);
                                LOG.info("case 3: Received quantity set to " + receivedQuantity + " for rma : " + rma);
                                returnEntry.setStatus(ReturnStatus.RECEIVED);
                                break;
                            } else if (receivedQuantity > expectedQuantity) {
                                return new Pair<>(Boolean.FALSE, null);
                            }
                        }
                    }
                    getModelService().save(returnEntry);
                }
                if (totalReceivedQuantity == 0) {
                    returnRequest.setStatus(ReturnStatus.NOT_RECEIVED);
                } else if (totalReceivedQuantity < totalExpectedQuantity) {
                    returnRequest.setStatus(ReturnStatus.PARTIAL_RECEIVED);
                    if (!returnRequest.isRmaGeneratedByRTO()) {
                        sendInwardNotification(returnRequest);
                    }
                } else if (totalReceivedQuantity == totalExpectedQuantity) {
                    returnRequest.setStatus(ReturnStatus.RECEIVED);
                    // sendEmail Inward zone email
                    if (!returnRequest.isRmaGeneratedByRTO()) {
                        sendInwardNotification(returnRequest);
                    }
                } else {
                    return new Pair<>(Boolean.FALSE, null);
                }
                getModelService().save(returnRequest);
                return new Pair<>(Boolean.TRUE, enumerationService.getEnumerationName(returnRequest.getStatus()));

            }
        }
        return new Pair<>(Boolean.FALSE, null);
    }

    /**
     * @param returnRequest
     */
    private void sendInwardNotification(final ReturnRequestModel returnRequest) {

        sendSMS(returnRequest);

        final OrderProcessModel orderProcessModel = (OrderProcessModel) getSslSendEmailWithAttachmentService().getBusinessProcessService()
                .createProcess(
                        "sendReturnRequestInwardZoneEmailProcess-" + returnRequest.getOrder().getCode() + "-" + System.currentTimeMillis(),
                        "sendReturnRequestInwardZoneEmailProcess");
        LOG.debug("orderProcessModel for inward zone email" + orderProcessModel);

        final Set<String> returnReqSet = new HashSet<>();
        returnReqSet.add(returnRequest.getCode());
        orderProcessModel.setReturnReqNumber(returnReqSet);
        orderProcessModel.setOrder(returnRequest.getOrder());
        getBusinessProcessService().startProcess(orderProcessModel);
    }

    /**
     * @param returnRequest
     */
    private void sendSMS(final ReturnRequestModel returnRequest) {

        if (returnRequest.getType().equals(ReturnRequestType.RETURN)) {
            final String messageFormat = SslCoreConstants.ORDER_RETURN_REQUEST_RMA_RECEIVED_SMS;
            sslCustomerTouchPointService.sendReturnRecievedSMS(returnRequest, messageFormat);
        } else {
            final String messageFormat = SslCoreConstants.ORDER_EXCHANGE_REQUEST_RMA_RECIEVED_SMS;
            sslCustomerTouchPointService.sendExchangeReceivedSMS(returnRequest, messageFormat);
        }

    }

    @Override
    public Pair<Boolean, Set<ReturnStatus>> processRefundDetails(final RMARefundData rmaRefundData,
            final ReturnRequestModel returnRequest) {
        Set<ReturnStatus> returnEntryStatus = null;
        try {
            if (StringUtils.isNotEmpty(rmaRefundData.getRmaRejectionReason())
                    && RMARejectionReason.valueOf(rmaRefundData.getRmaRejectionReason()) != null) {
                returnRequest.setRmaRejectionReason(RMARejectionReason.valueOf(rmaRefundData.getRmaRejectionReason()));
            }
            splitConsignment(returnRequest);
            returnEntryStatus = splitReturnEntry(returnRequest);// Split Return
            // Entry
            // according to
            // Final Return
            // Quantity
            returnRequest.setStatus(ReturnStatus.COMPLETE);
            returnRequest.setRmaCompletionDate(new Date());
            modelService.save(returnRequest);

            sslCustomerTouchPointService.sendRefundInitiatedSMS(returnRequest, SslCoreConstants.ORDER_RETURN_REFUND_INITIATED_SMS_MSG);

            return new Pair<Boolean, Set<ReturnStatus>>(Boolean.TRUE, returnEntryStatus);

        } catch (final Exception e) {
            LOG.error("Error in Processing Refund Details  " + ExceptionUtils.getStackTrace(e));
        }
        return new Pair<Boolean, Set<ReturnStatus>>(Boolean.FALSE, returnEntryStatus);
    }

    private void splitConsignment(final ReturnRequestModel returnRequest) {
        boolean isUpdateRequired = false;
        boolean isCreateRequired = false;
        final List<ReturnEntryModel> returnEntries = returnRequest.getReturnEntries();
        for (final ReturnEntryModel returnEntry : returnEntries) {
            if (returnEntry.getFinalReturnQuantity() != 0) {
                isCreateRequired = true;
            }
            if (returnEntry.getFinalReturnQuantity() != returnEntry.getExpectedQuantity().longValue()) {
                isUpdateRequired = true;
            }
        }
        if (isUpdateRequired) {
            updateConsignment(returnRequest, isCreateRequired);
            if (isCreateRequired) {
                createConsignment(returnRequest);
            }
        }
    }

    private void updateConsignment(final ReturnRequestModel returnRequest, final boolean isCreateRequired) {
        final List<ReturnEntryModel> returnEntries = returnRequest.getReturnEntries();
        ConsignmentModel newConsignment = null;

        for (final ReturnEntryModel returnEntry : returnEntries) {
            final Set<ConsignmentEntryModel> consignmentEntries = returnEntry.getOrderEntry().getConsignmentEntries();
            for (final ConsignmentEntryModel consignmentEntry : consignmentEntries) {
                if (consignmentEntry.getConsignment().getReturnRequest() == returnRequest
                        && returnEntry.getReturnRequest().getWarehouse().getCode().equals(returnRequest.getWarehouse().getCode())
                        && consignmentEntry.getConsignment().getCode().equals(String.format("%s-%s",
                                returnEntry.getReturnRequest().getWarehouse().getCode(), returnEntry.getReturnRequest().getRMA()))) {
                    if (!isCreateRequired) {
                        consignmentEntry.setStatus(ConsignmentEntryStatus.CANCELLED);
                        if (returnRequest.getType().equals(ReturnRequestType.EXCHANGE)) {
                            consignmentEntry.getConsignment().setStatus(ConsignmentStatus.EXCHANGE_REJECTED);
                        } else if (returnRequest.getType().equals(ReturnRequestType.RETURN)) {
                            consignmentEntry.getConsignment().setStatus(ConsignmentStatus.RETURN_REJECTED);
                        }
                        modelService.save(consignmentEntry);
                    } else { // Consignment clone as Consignment can't be
                        // updated after creation
                        if (null == newConsignment) {
                            newConsignment = getModelService().clone(consignmentEntry.getConsignment());
                            consignmentEntry.getConsignment()
                                    .setInvoiceNumber("Cancelled_" + consignmentEntry.getConsignment().getInvoiceNumber());
                            consignmentEntry.getConsignment()
                                    .setProcessCode("Cancelled_" + consignmentEntry.getConsignment().getProcessCode());
                            consignmentEntry.getConsignment().setStatus(ConsignmentStatus.CANCELLED); // Cancel
                            // the
                            // Old
                            // Consignment
                            modelService.save(consignmentEntry.getConsignment());
                            if (returnRequest.getType().equals(ReturnRequestType.EXCHANGE)) {
                                newConsignment.setStatus(ConsignmentStatus.EXCHANGE_ISSUED);
                            } else if (returnRequest.getType().equals(ReturnRequestType.RETURN)) {
                                newConsignment.setStatus(ConsignmentStatus.RETURN_ISSUED);
                            }
                            final Collection<PointOfServiceModel> pointsOfService = returnRequest.getWarehouse().getPointsOfService();
                            final AddressModel deliveryAddress = pointsOfService.stream().findFirst().get().getAddress();
                            newConsignment.setDeliveryAddress(deliveryAddress);
                            AddressModel shippingAddress = returnRequest.getOrder().getDeliveryAddress();
                            if (null == shippingAddress) {
                                if (returnRequest.getOrder().getUser().getAddresses().stream().findFirst().isPresent()) {
                                    shippingAddress = returnRequest.getOrder().getUser().getAddresses().stream().findFirst().get();
                                }
                            }
                            newConsignment.setShippingAddress(shippingAddress);
                            newConsignment.setWarehouse(returnRequest.getWarehouse());
                            modelService.save(newConsignment);
                        }
                        final ConsignmentEntryModel newConsignmentEntry = getModelService().clone(consignmentEntry);
                        newConsignmentEntry.setConsignment(newConsignment);
                        newConsignmentEntry.setReturnedQuantity(returnEntry.getFinalReturnQuantity());
                        newConsignmentEntry.setQuantity(Long.valueOf(returnEntry.getFinalReturnQuantity()));
                        consignmentEntry.setStatus(ConsignmentEntryStatus.CANCELLED); // Cancel
                        // the
                        // Old
                        // Consignment
                        // Entry
                        for (final InternalConsignmentEntryModel internalEntry : consignmentEntry.getInternalEntries()) {
                            internalEntry.setParentConsignmentEntry(newConsignmentEntry);
                            modelService.save(internalEntry);
                        }
                        modelService.save(consignmentEntry);
                        modelService.save(newConsignmentEntry);
                        modelService.save(newConsignment);
                    }
                    modelService.save(consignmentEntry.getConsignment());
                    break;
                }
            }
        }
    }

    private ConsignmentModel createConsignment(final ReturnRequestModel returnRequest) {
        final ConsignmentModel consignment = getModelService().create(ConsignmentModel.class);

        final OrderModel order = returnRequest.getOrder();
        consignment.setOrder(order);

        consignment.setReturnRequest(returnRequest);

        final String code = String.format("%s-%s", returnRequest.getWarehouse().getCode(), returnRequest.getRMA());
        consignment.setCode(code);// Optional False

        consignment.setProcessCode("Damaged" + code);// Unique True, Optional
        // True
        consignment.setInvoiceNumber("Damaged" + code);// Unique True, Optional
        // True

        final Collection<PointOfServiceModel> pointsOfService = returnRequest.getWarehouse().getPointsOfService();
        final AddressModel shippingAddress = pointsOfService.stream().findFirst().get().getAddress();
        consignment.setShippingAddress(shippingAddress);// Optional False

        consignment.setDeliveryAddress(shippingAddress);// Optional False

        if (returnRequest.getType().equals(ReturnRequestType.EXCHANGE)) {
            consignment.setStatus(ConsignmentStatus.EXCHANGE_REJECTED);// Optional
            // False
        } else if (returnRequest.getType().equals(ReturnRequestType.RETURN)) {
            consignment.setStatus(ConsignmentStatus.RETURN_REJECTED);// Optional
            // False
        }

        consignment.setWarehouse(returnRequest.getWarehouse());

        getModelService().save(consignment);

        for (final ReturnEntryModel returnEntry : returnRequest.getReturnEntries()) {

            final List<ConsignmentEntryModel> originalConsignmentEntryList = returnEntry.getOrderEntry().getConsignmentEntries().stream()
                    .filter(r -> r.getConsignment().getReturnRequest() == null)
                    .filter(r -> r.getConsignment().getWarehouse().getInvoiceStoreId().equals(returnRequest.getWarehouse().getCode()))
                    .filter(r -> r.getOrderEntry().getProduct().equals(returnEntry.getOrderEntry().getProduct()))
                    .collect(Collectors.toList());

            final ConsignmentEntryModel consignmentEntry = getModelService().create(ConsignmentEntryModel.class);
            consignmentEntry.setConsignment(consignment);
            consignmentEntry
                    .setQuantity(Long.valueOf(returnEntry.getExpectedQuantity().longValue() - returnEntry.getFinalReturnQuantity()));
            consignmentEntry.setOrderEntry(returnEntry.getOrderEntry());
            consignmentEntry.setStatus(ConsignmentEntryStatus.CANCELLED);

            if (!originalConsignmentEntryList.isEmpty()) {
                final Double gstTaxData = originalConsignmentEntryList.stream().findFirst().get().getAppliedGSTTaxPercent();
                consignmentEntry.setAppliedGSTTaxPercent(gstTaxData == null ? Double.valueOf(0) : gstTaxData);
                consignmentEntry.setTaxComponents(originalConsignmentEntryList.stream().findFirst().get().getTaxComponents());
            }

            getModelService().save(consignmentEntry);
        }

        return consignment;
    }

    /**
    		*
    		*/
    private Set<ReturnStatus> splitReturnEntry(final ReturnRequestModel returnRequest) {
        final Set<ReturnStatus> refundStatus = new HashSet<>();
        try {
            final List<ReturnEntryModel> returnEntries = returnRequest.getReturnEntries();
            for (final ReturnEntryModel returnEntry : returnEntries) {
                if (!returnEntry.getStatus().equals(ReturnStatus.READY)) {
                    continue;
                }
                if (returnEntry.getFinalReturnQuantity() == 0l) {
                    returnEntry.setStatus(ReturnStatus.REFUND_REJECTED);
                    refundStatus.add(ReturnStatus.REFUND_REJECTED);
                } else if (returnEntry.getFinalReturnQuantity() < returnEntry.getExpectedQuantity().longValue()) {
                    // Create an entry for Refund rejected items
                    final RefundEntryModel refundEntryModel = modelService.create(RefundEntryModel.class);
                    refundEntryModel.setOrderEntry(returnEntry.getOrderEntry());
                    refundEntryModel.setStatus(ReturnStatus.REFUND_REJECTED);
                    refundEntryModel.setExpectedQuantity(
                            Long.valueOf(returnEntry.getExpectedQuantity().longValue() - returnEntry.getFinalReturnQuantity()));
                    refundEntryModel.setFinalReturnQuantity(0);
                    refundEntryModel.setReceivedQuantity(returnEntry.getReceivedQuantity());
                    LOG.info("splitReturnEntry : Received quantity set to " + returnEntry.getReceivedQuantity() + " for rma : "
                            + returnRequest.getCode());
                    refundEntryModel.setInventoryZoneQuantity(returnEntry.getInventoryZoneQuantity());
                    refundEntryModel.setDamagedZoneQuantity(returnEntry.getDamagedZoneQuantity());
                    refundEntryModel.setAction(ReturnAction.IMMEDIATE);
                    refundEntryModel.setReason(RefundReason.DAMAGEDINTRANSIT);
                    refundEntryModel.setNotes("Refund Rejected");
                    refundEntryModel.setReturnRequest(returnRequest);
                    modelService.save(refundEntryModel);

                    returnEntry.setExpectedQuantity(Long.valueOf(returnEntry.getFinalReturnQuantity()));
                    returnEntry.setStatus(ReturnStatus.REFUND_ISSUED);
                    refundStatus.add(ReturnStatus.REFUND_PARTIAL);
                } else if (returnEntry.getFinalReturnQuantity() == returnEntry.getExpectedQuantity().longValue()) {
                    returnEntry.setStatus(ReturnStatus.REFUND_ISSUED);
                    refundStatus.add(ReturnStatus.REFUND_ISSUED);
                }
                modelService.save(returnEntry);
            }

            // sendEmail(refundStatus, returnRequest);

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return refundStatus;
    }

    private void sendAcceptedRefundMail(final ReturnRequestModel returnRequest) {
        // RM process refund email
        final OrderModel order = returnRequest.getOrder();
        final OrderProcessModel orderProcessModel = (OrderProcessModel) getSslSendEmailWithAttachmentService().getBusinessProcessService()
                .createProcess("sendReturnRequestRMRefundProcessEmailProcess-" + order.getCode() + "-" + System.currentTimeMillis(),
                        "sendReturnRequestRMRefundProcessEmailProcess");
        LOG.debug("OrderProcessModel for Accepted RMA email" + orderProcessModel);
        initiateEmailProcess(order, returnRequest, orderProcessModel);

    }

    /**
     * Initiate email process.
     *
     * @param order
     *        the order
     * @param returnRequest
     *        the return request
     * @param orderProcessModel
     *        the order process model
     */
    private void initiateEmailProcess(final OrderModel order, final ReturnRequestModel returnRequest,
            final OrderProcessModel orderProcessModel) {
        final Set<String> returnReqSet = new HashSet<>();
        returnReqSet.add(returnRequest.getCode());
        orderProcessModel.setReturnReqNumber(returnReqSet);
        orderProcessModel.setOrder(order);
        getBusinessProcessService().startProcess(orderProcessModel);
    }

    private void sendRejectedRefundMail(final ReturnRequestModel returnRequest, final Map<String, Object> emailAttachmentsMap) {
        // RM process refund Rejected email
        final OrderModel order = returnRequest.getOrder();
        final OrderProcessModel orderProcessModel = (OrderProcessModel) getSslSendEmailWithAttachmentService().getBusinessProcessService()
                .createProcess("sendReturnRequestRejectEmailProcess-" + order.getCode() + "-" + System.currentTimeMillis(),
                        "sendReturnRequestRejectEmailProcess", emailAttachmentsMap);
        LOG.debug("OrderProcessModel for Rejected RMA email" + orderProcessModel);
        initiateEmailProcess(order, returnRequest, orderProcessModel);
    }

    private void sendReturnRefundMail(final ReturnRequestModel returnRequest, final Map<String, Object> emailAttachmentsMap) {
        // RM process refund email
        final OrderModel order = returnRequest.getOrder();
        final OrderProcessModel orderProcessModel = (OrderProcessModel) getSslSendEmailWithAttachmentService().getBusinessProcessService()
                .createProcess("sendReturnRequestRMRefundProcessEmailProcess-" + order.getCode() + "-" + System.currentTimeMillis(),
                        "sendReturnRequestRMRefundProcessEmailProcess", emailAttachmentsMap);
        LOG.debug("OrderProcessModel for Accepted RMA email" + orderProcessModel);
        initiateEmailProcess(order, returnRequest, orderProcessModel);

    }

    private void sendExchangeAcceptedMail(final ReturnRequestModel returnRequest, final Map<String, Object> emailAttachmentsMap) {
        // RM process refund partially accepted
        final OrderModel order = returnRequest.getOrder();
        final OrderProcessModel orderProcessModel = (OrderProcessModel) getSslSendEmailWithAttachmentService().getBusinessProcessService()
                .createProcess("sendReturnRequestPartiallyAcceptedEmailProcess-" + order.getCode() + "-" + System.currentTimeMillis(),
                        "sendReturnRequestPartiallyAcceptedEmailProcess", emailAttachmentsMap);
        LOG.debug("OrderProcessModel for partially accepted RMA email" + orderProcessModel);
        initiateEmailProcess(order, returnRequest, orderProcessModel);
    }

    /**
     * @param returnRequest
     */
    @Override
    public void createCSTicket(final ReturnRequestModel returnRequest, final String note) {

        final CsTicketModel csticketmodel = getModelService().create(CsTicketModel.class);
        if (returnRequest.getType().equals(ReturnRequestType.RETURN)) {
            csticketmodel.setHeadline("Return: Order-" + returnRequest.getOrder().getCode() + " RMA-" + returnRequest.getRMA());
            csticketmodel.setCategory(CsTicketCategory.RETURN);
        } else if (returnRequest.getType().equals(ReturnRequestType.EXCHANGE)) {
            csticketmodel.setHeadline("Exchange: Order-" + returnRequest.getOrder().getCode() + " RMA-" + returnRequest.getRMA());
            csticketmodel.setCategory(CsTicketCategory.EXCHANGE);
        }

        csticketmodel.setPriority(CsTicketPriority.HIGH);
        csticketmodel.setOrder(returnRequest.getOrder());
        csticketmodel.setCustomer(returnRequest.getOrder().getUser());
        csticketmodel.setState(CsTicketState.OPEN);
        // csticketmodel.setAssignedAgent((EmployeeModel)
        // userService.getCurrentUser());
        csticketmodel.setAssignedGroup((CsAgentGroupModel) userService.getUserGroupForUID("csgroup"));

        CsEventReason csEventReason = CsEventReason.RETURN;
        final CsCustomerEventModel csCustomerEventModel = getModelService().create(CsCustomerEventModel.class);
        csCustomerEventModel.setInterventionType(CsInterventionType.CALL);
        if (returnRequest.getType().equals(ReturnRequestType.RETURN)) {
            csCustomerEventModel.setSubject("Return Order Ticket");
            csCustomerEventModel.setText("Return Order");
        } else if (returnRequest.getType().equals(ReturnRequestType.EXCHANGE)) {
            csEventReason = CsEventReason.EXCHANGE;
            csCustomerEventModel.setSubject("Exchange Order Ticket");
            csCustomerEventModel.setText("Exchange Order");
        }
        csCustomerEventModel.setReason(csEventReason);

        final CsTicketModel csTicket = ticketBusinessService.createTicket(csticketmodel, csCustomerEventModel);
        ticketBusinessService.addNoteToTicket(csTicket, CsInterventionType.CALL, csEventReason, note, null);
        getModelService().save(csTicket);

        if (null == returnRequest.getCsTicket()) {
            returnRequest.setCsTicket(csTicket);
            getModelService().save(returnRequest);
        }
    }

    @Override
    public Pair<RMExchangeResponseData, Boolean> saveExchangeDetails(final RMExchangeRequestData rmExchangeRequest) {
        final ReplacementEntryModel replacementEntry = (ReplacementEntryModel) getSslODCReturnDao()
                .getReturnEntry(rmExchangeRequest.getReturnEntryId());

        boolean success = false;
        final Transaction tx = Transaction.current();
        tx.begin();
        try {
            getModelService().enableTransactions();
            final List<SelectedExchangeProduct> selectedProducts = rmExchangeRequest.getExchangedProducts();

            int approvedProductsCount = 0;
            final List<String> alreadyBookedProducts = new ArrayList<String>();
            replacementEntry.getExchangeStockLevels().forEach((a) -> {
                for (int i = 0; i < a.getQuantity(); i++) {
                    alreadyBookedProducts.add(a.getStockLevel().getProductCode());
                }
            });

            final List<String> stockReleaseCandidates = new ArrayList<String>();
            final List<String> stockHoldCandidates = new ArrayList<String>();
            final List<String> stockAlreadyBookedCandidates = new ArrayList<String>();
            final List<String> dummyCandidates = new ArrayList<String>();

            for (int i = 0; i < selectedProducts.size(); i++) {
                boolean found = false;
                for (int j = 0; j < alreadyBookedProducts.size(); j++) {
                    if (selectedProducts.get(i).getSku().equals(alreadyBookedProducts.get(j))) {
                        if (selectedProducts.get(i).getStatus().equals("Approve")) {
                            stockAlreadyBookedCandidates.add(alreadyBookedProducts.get(j));
                            alreadyBookedProducts.remove(j);
                            approvedProductsCount++;
                        } else if (selectedProducts.get(i).getStatus().equals("Reject")) {
                            stockReleaseCandidates.add(alreadyBookedProducts.get(j));
                            alreadyBookedProducts.remove(j);
                        }
                        found = true;
                        break;
                    }
                }

                // This is for Edit scenarios
                // If product has been edited and stock was not held for it, we
                // need to now hold stock.
                if (!found && selectedProducts.get(i).getStatus().equals("Approve")) {
                    stockHoldCandidates.add(selectedProducts.get(i).getSku());
                    approvedProductsCount++;
                }

                // User edited the product selection and rejected. Useless.
                if (!found && selectedProducts.get(i).getStatus().equals("Reject")) {
                    dummyCandidates.add(selectedProducts.get(i).getSku());
                }
            }

            final Map<String, Integer> productsToBeRemoved = new HashMap<>();
            for (final String productCode : alreadyBookedProducts) {
                if (productsToBeRemoved.containsKey(productCode)) {
                    productsToBeRemoved.put(productCode, Integer.valueOf(productsToBeRemoved.get(productCode).intValue() + 1));
                } else {
                    productsToBeRemoved.put(productCode, Integer.valueOf(1));
                }
            }

            Set<ExchangeStockLevelModel> exchangeStocks = replacementEntry.getExchangeStockLevels();
            final Set<ExchangeStockLevelModel> exhangeStocksToBeRemoved = new HashSet<ExchangeStockLevelModel>();
            for (final String productCode : productsToBeRemoved.keySet()) {
                for (final ExchangeStockLevelModel exchangeStock : exchangeStocks) {
                    if (exchangeStock.getStockLevel().getProductCode().equals(productCode)) {
                        if (productsToBeRemoved.get(productCode).intValue() == exchangeStock.getQuantity()) {
                            exhangeStocksToBeRemoved.add(exchangeStock);
                            break;
                        } else if (productsToBeRemoved.get(productCode).intValue() > exchangeStock.getQuantity()) {
                            exhangeStocksToBeRemoved.add(exchangeStock);
                            productsToBeRemoved.put(productCode,
                                    Integer.valueOf(productsToBeRemoved.get(productCode).intValue() - (int) exchangeStock.getQuantity()));
                            modelService.save(exchangeStock);
                            modelService.save(replacementEntry);
                        } else {
                            exchangeStock.setQuantity(exchangeStock.getQuantity() - productsToBeRemoved.get(productCode).longValue());
                            exchangeStock.getStockLevel().setOnlineHold(
                                    exchangeStock.getStockLevel().getOnlineHold() - (int) productsToBeRemoved.get(productCode).longValue());
                            modelService.save(exchangeStock.getStockLevel());
                            modelService.save(exchangeStock);
                            modelService.save(replacementEntry);
                            break;
                        }
                    }
                }
            }
            for (final ExchangeStockLevelModel exchangeStock : exhangeStocksToBeRemoved) {
                exchangeStock.getStockLevel()
                        .setOnlineHold(exchangeStock.getStockLevel().getOnlineHold() - (int) exchangeStock.getQuantity());
                modelService.save(exchangeStock.getStockLevel());
                modelService.remove(exchangeStock);
            }
            modelService.save(replacementEntry);

            // Consolidate StockHoldCandidates
            final Map<String, Integer> stockHoldCandidatesQuantityMap = new HashMap<String, Integer>();
            for (final String product : stockHoldCandidates) {
                if (stockHoldCandidatesQuantityMap.containsKey(product)) {
                    stockHoldCandidatesQuantityMap.put(product,
                            Integer.valueOf(stockHoldCandidatesQuantityMap.get(product).intValue() + 1));
                } else {
                    stockHoldCandidatesQuantityMap.put(product, Integer.valueOf(1));
                }
            }

            // Consolidate StockReleaseCandidates
            final Map<String, Integer> stockReleaseCandidatesQuantityMap = new HashMap<String, Integer>();
            for (final String product : stockReleaseCandidates) {
                if (stockReleaseCandidatesQuantityMap.containsKey(product)) {
                    stockReleaseCandidatesQuantityMap.put(product,
                            Integer.valueOf(stockReleaseCandidatesQuantityMap.get(product).intValue() + 1));
                } else {
                    stockReleaseCandidatesQuantityMap.put(product, Integer.valueOf(1));
                }
            }

            final List<String> outOfStockProducts = new ArrayList<String>();
            final CatalogVersionModel activeCatalogVersion = ((CMSSiteModel) (replacementEntry.getOrderEntry().getOrder().getSite()))
                    .getDefaultCatalog().getActiveCatalogVersion();
            for (final String productCode : stockHoldCandidatesQuantityMap.keySet()) {
                ProductModel exchangeProduct = null;
                try {
                    final ProductModel exampleProduct = new ProductModel();
                    exampleProduct.setCode(productCode);
                    exampleProduct.setCatalogVersion(activeCatalogVersion);
                    exchangeProduct = flexibleSearchService.getModelByExample(exampleProduct);
                } catch (ModelNotFoundException | AmbiguousIdentifierException e) {
                    LOG.error("Error in getting replacement product" + e);
                    LOG.error(ExceptionUtils.getStackTrace(e));
                }
                final long availableStock = exchangeService.getStock(exchangeProduct).getStockLevel().longValue();
                if (availableStock < stockHoldCandidatesQuantityMap.get(productCode).intValue()) {
                    outOfStockProducts.add(exchangeProduct.getCode());
                }
            }

            // SSLM-4407 : Begin
            exchangeStocks = replacementEntry.getExchangeStockLevels();
            final List<ExchangeStockLevelModel> exchangeStockLevelsToCheck = new ArrayList();
            for (final String product : stockAlreadyBookedCandidates) {
                for (final ExchangeStockLevelModel exchangeStock : exchangeStocks) {
                    if (exchangeStock.getStockLevel().getProductCode().equals(product)) {
                        exchangeStockLevelsToCheck.add(exchangeStock);
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(exchangeStockLevelsToCheck)) {
                for (final ExchangeStockLevelModel exchangeStockLevelToCheck : exchangeStockLevelsToCheck) {
                    final StockLevelModel stock = exchangeStockLevelToCheck.getStockLevel();
                    final String exchangeProductCode = stock.getProductCode();
                    ProductModel exchangeProduct = null;
                    try {
                        final ProductModel exampleProduct = new ProductModel();
                        exampleProduct.setCode(exchangeProductCode);
                        exampleProduct.setCatalogVersion(activeCatalogVersion);
                        exchangeProduct = flexibleSearchService.getModelByExample(exampleProduct);
                    } catch (ModelNotFoundException | AmbiguousIdentifierException e) {
                        LOG.error("Error in getting replacement product" + e);
                        LOG.error(ExceptionUtils.getStackTrace(e));
                    }

                    long exchangeProductQuantity = exchangeStockLevelToCheck.getQuantity();
                    final Integer releasedStock = stockReleaseCandidatesQuantityMap.get(exchangeProductCode);
                    if (null != releasedStock) {
                        exchangeProductQuantity = exchangeProductQuantity - releasedStock.longValue();
                    }
                    final int holdQuatityAfterReset = stock.getOnlineHold() - (int) exchangeProductQuantity;
                    stock.setOnlineHold(holdQuatityAfterReset);
                    getModelService().save(stock);
                    final List<StockLevelModel> stockLevels = exchangeService.getStockLevels(exchangeProduct);
                    long totalAvailableStock = 0L;
                    boolean stockStillAvailable = false;
                    for (final StockLevelModel stockLevel : stockLevels) {
                        totalAvailableStock += exchangeService.getAvailability(stockLevel);
                        if (totalAvailableStock >= exchangeProductQuantity) {
                            stockStillAvailable = true;
                            break;
                        }
                    }
                    if (!stockStillAvailable) {
                        outOfStockProducts.add(exchangeProduct.getCode());
                    }
                    stock.setOnlineHold(stock.getOnlineHold() + (int) exchangeProductQuantity);
                    getModelService().save(stock);
                }
            }
            // SSLM-4407 : End

            final RMExchangeResponseData rmExchangeResponse = new RMExchangeResponseData();
            rmExchangeResponse.setOutOfStockProducts(outOfStockProducts);
            if (outOfStockProducts.isEmpty()) {
                rmExchangeResponse.setResult("Success");
                exchangeStocks = replacementEntry.getExchangeStockLevels();
                // Hold Stock for stockHoldCandidatesQuantityMap
                for (final String productCode : stockHoldCandidatesQuantityMap.keySet()) {
                    ProductModel exchangeProduct = null;
                    try {
                        final ProductModel exampleProduct = new ProductModel();
                        exampleProduct.setCode(productCode);
                        exampleProduct.setCatalogVersion(activeCatalogVersion);
                        exchangeProduct = flexibleSearchService.getModelByExample(exampleProduct);
                    } catch (ModelNotFoundException | AmbiguousIdentifierException e) {
                        LOG.error("Error in getting replacement product" + e);
                        LOG.error(ExceptionUtils.getStackTrace(e));
                    }
                    long exchangeProductQuantity = stockHoldCandidatesQuantityMap.get(productCode).longValue();
                    final List<StockLevelModel> stockLevels = exchangeService.getStockLevels(exchangeProduct);
                    exchangeService.sortStockByAvailableQuantity(stockLevels);
                    for (final StockLevelModel stock : stockLevels) {
                        final long availableStock = exchangeService.getAvailability(stock);

                        if (availableStock > exchangeProductQuantity) {
                            stock.setOnlineHold((int) (stock.getOnlineHold() + exchangeProductQuantity));
                        } else {
                            stock.setOnlineHold((int) (stock.getOnlineHold() + availableStock));
                        }
                        getModelService().save(stock);
                        boolean isStockAlreadyPresent = false;
                        for (final ExchangeStockLevelModel exchangeStockLevel : exchangeStocks) {
                            if (exchangeStockLevel.getStockLevel().equals(stock)) {
                                if (availableStock > exchangeProductQuantity) {
                                    exchangeStockLevel.setQuantity(exchangeStockLevel.getQuantity() + exchangeProductQuantity);
                                } else {
                                    exchangeStockLevel.setQuantity(exchangeStockLevel.getQuantity() + availableStock);
                                }
                                isStockAlreadyPresent = true;
                                modelService.save(exchangeStockLevel);
                                modelService.save(replacementEntry);
                                break;
                            }
                        }
                        if (!isStockAlreadyPresent) {
                            final ExchangeStockLevelModel exchangeStockLevel = modelService.create(ExchangeStockLevelModel.class);
                            exchangeStockLevel.setReplacementEntry(replacementEntry);
                            exchangeStockLevel.setStockLevel(stock);
                            if (availableStock > exchangeProductQuantity) {
                                exchangeStockLevel.setQuantity(exchangeProductQuantity);
                            } else {
                                exchangeStockLevel.setQuantity(availableStock);
                            }
                            modelService.save(exchangeStockLevel);
                            modelService.save(replacementEntry);
                            // exchangeStocks.add(exchangeStockLevel); Added
                            // through relation
                        }

                        if (availableStock >= exchangeProductQuantity) {
                            break;
                        } else {
                            exchangeProductQuantity -= availableStock;
                        }
                    }
                }

                // Release Stock, Remove Entries from REM
                final Set<ExchangeStockLevelModel> stockEntriesToBeRemoved = new HashSet<>();
                for (final String productCode : stockReleaseCandidatesQuantityMap.keySet()) {
                    for (final ExchangeStockLevelModel exchangeStockLevel : exchangeStocks) {
                        if (exchangeStockLevel.getStockLevel().getProductCode().equals(productCode)) {
                            final long quantityToBeReleased = stockReleaseCandidatesQuantityMap.get(productCode).longValue();
                            final long quantityBookedCurrently = exchangeStockLevel.getQuantity();
                            if (quantityBookedCurrently == quantityToBeReleased) {
                                final StockLevelModel stock = exchangeStockLevel.getStockLevel();
                                stock.setOnlineHold(stock.getOnlineHold() - (int) quantityBookedCurrently);
                                modelService.save(stock);
                                stockEntriesToBeRemoved.add(exchangeStockLevel);
                                break;
                            } else if (quantityBookedCurrently > quantityToBeReleased) {
                                final StockLevelModel stock = exchangeStockLevel.getStockLevel();
                                stock.setOnlineHold(stock.getOnlineHold() - (int) quantityToBeReleased);
                                exchangeStockLevel.setQuantity(quantityBookedCurrently - quantityToBeReleased);
                                exchangeStockLevel.setRejectedQuantity(quantityToBeReleased);
                                modelService.save(exchangeStockLevel);
                                modelService.save(replacementEntry);
                                modelService.save(stock);
                                break;
                            } else if (quantityBookedCurrently < quantityToBeReleased) {
                                final StockLevelModel stock = exchangeStockLevel.getStockLevel();
                                stock.setOnlineHold(stock.getOnlineHold() - (int) quantityBookedCurrently);
                                modelService.save(stock);
                                stockEntriesToBeRemoved.add(exchangeStockLevel);
                            }
                        }
                    }
                }

                for (final ExchangeStockLevelModel exchangeStockLevel : stockEntriesToBeRemoved) {
                    exchangeStockLevel.setRejectedQuantity(exchangeStockLevel.getQuantity());
                    exchangeStockLevel.setQuantity(0);
                    modelService.save(exchangeStockLevel);
                    // exchangeStocks.remove(exchangeStockLevel);
                }
                // replacementEntry.setExchangeStockLevels(exchangeStocks);

                modelService.save(replacementEntry);

                if (!dummyCandidates.isEmpty()) { // Creating Dummy Entries for
                    // Left Out Rejected
                    // Products
                    final Map<String, Integer> productsToBeFulfilled = new HashMap<String, Integer>();
                    for (final String toBeFulfilledProduct : dummyCandidates) {
                        if (productsToBeFulfilled.containsKey(toBeFulfilledProduct)) {
                            productsToBeFulfilled.put(toBeFulfilledProduct,
                                    Integer.valueOf(productsToBeFulfilled.get(toBeFulfilledProduct).intValue() + 1));
                        } else {
                            productsToBeFulfilled.put(toBeFulfilledProduct, Integer.valueOf(1));
                        }
                    }

                    for (final String productCode : productsToBeFulfilled.keySet()) {
                        final ExchangeStockLevelModel exchangeStockLevel = modelService.create(ExchangeStockLevelModel.class);
                        exchangeStockLevel.setReplacementEntry(replacementEntry);
                        ProductModel exchangeProduct = null;
                        try {
                            final ProductModel exampleProduct = new ProductModel();
                            exampleProduct.setCode(productCode);
                            exampleProduct.setCatalogVersion(activeCatalogVersion);
                            exchangeProduct = flexibleSearchService.getModelByExample(exampleProduct);
                        } catch (ModelNotFoundException | AmbiguousIdentifierException e) {
                            LOG.error("Error in getting replacement product" + e);
                            LOG.error(ExceptionUtils.getStackTrace(e));
                        }
                        final List<StockLevelModel> stockLevels = exchangeService.getStockLevels(exchangeProduct);
                        exchangeStockLevel.setStockLevel(stockLevels.get(0));
                        exchangeStockLevel.setRejectedQuantity(productsToBeFulfilled.get(productCode).longValue());
                        modelService.save(exchangeStockLevel);
                        modelService.save(replacementEntry);
                    }
                }

                replacementEntry.setStatus(ReturnStatus.READY);
                replacementEntry.setFinalReturnQuantity(approvedProductsCount);

                replacementEntry.setFinalRefundAmount(
                        approvedProductsCount * replacementEntry.getOrderEntry().getCalculatedRefundPerUnit().doubleValue());
                replacementEntry
                        .setShippingAmount(BigDecimal.valueOf(getShippingAmountForReturnEntry(replacementEntry) * approvedProductsCount)
                                .setScale(2, RoundingMode.HALF_UP).doubleValue());

                modelService.save(replacementEntry);
            } else {
                rmExchangeResponse.setResult("Failure");
            }

            success = true;
            return new Pair<RMExchangeResponseData, Boolean>(rmExchangeResponse, Boolean.valueOf(isRMAReadyForProcess(replacementEntry)));
        } catch (final Exception e) {
            LOG.error(ExceptionUtils.getStackTrace(e));
            throw e;
        } finally {
            if (success) {
                tx.commit();
            } else {
                tx.rollback();
            }
            getModelService().disableTransactions();
        }
    }

    private double getShippingAmountForReturnEntry(final ReturnEntryModel returnEntry) {
        final Set<ConsignmentEntryModel> orderConsignmentEntriesWithSameBillingLocation = returnEntry.getOrderEntry()
                .getConsignmentEntries().stream().filter(ce -> ce.getConsignment().getReturnRequest() == null)
                .filter(ce -> ce.getConsignment().getWarehouse().getInvoiceStoreId()
                        .equals(returnEntry.getReturnRequest().getWarehouse().getCode()))
                .filter(ce -> ce.getStatus() != null && !ce.getStatus().equals(ConsignmentEntryStatus.CANCELLED))
                .collect(Collectors.toSet());
        double shippedQuantityPerUnit = 0d;
        if (CollectionUtils.isEmpty(orderConsignmentEntriesWithSameBillingLocation)) {
            return shippedQuantityPerUnit;
        }
        for (final ConsignmentEntryModel orderConsignmentEntryWithSameBillingLocation : orderConsignmentEntriesWithSameBillingLocation) {
            if (null != orderConsignmentEntryWithSameBillingLocation
                    && null != orderConsignmentEntryWithSameBillingLocation.getTaxInclusiveShippingValue()) {
                final long shippedQtyForConsignmentEntry = getShippedQuantityForConsignmentEntry(
                        orderConsignmentEntryWithSameBillingLocation);
                if (shippedQtyForConsignmentEntry != 0) {
                    shippedQuantityPerUnit = orderConsignmentEntryWithSameBillingLocation.getTaxInclusiveShippingValue()
                            / shippedQtyForConsignmentEntry;
                    break;
                }
            }
        }
        if (Double.isNaN(shippedQuantityPerUnit)) {
            shippedQuantityPerUnit = 0d;
        }
        return shippedQuantityPerUnit;
    }

    private long getShippedQuantityForConsignmentEntry(final ConsignmentEntryModel consignmentEntry) {
        long shippedQuantity = 0;
        if ((CollectionUtils.isEmpty(consignmentEntry.getInternalEntries()))) {
            if (!(consignmentEntry.getStatus().equals(ConsignmentEntryStatus.CANCELLED))) {
                shippedQuantity = shippedQuantity + consignmentEntry.getAllocatedQuantity().longValue();
            }
        } else {
            for (final InternalConsignmentEntryModel internalConsignmentEntryModel : consignmentEntry.getInternalEntries()) {
                if ((internalConsignmentEntryModel != null) && (internalConsignmentEntryModel.getQuantity() != null)
                        && (!(internalConsignmentEntryModel.getStatus().equals(InternalConsignmentEntryStatus.CANCELLED)))) {
                    shippedQuantity = shippedQuantity + internalConsignmentEntryModel.getQuantity().longValue();
                }
            }
        }
        if (Double.isNaN(shippedQuantity)) {
            shippedQuantity = 0;
        }
        return shippedQuantity;
    }

    @Override
    public ExchangeProductVariants getVariants(final String productSKU, final String returnEntryId) {
        final ReplacementEntryModel replacementEntry = (ReplacementEntryModel) getSslODCReturnDao()
                .getReturnEntry(Long.valueOf(returnEntryId).longValue());
        final CatalogVersionModel activeCatalogVersion = ((CMSSiteModel) (replacementEntry.getOrderEntry().getOrder().getSite()))
                .getDefaultCatalog().getActiveCatalogVersion();
        ProductModel product = null;
        try {
            final ProductModel exampleProduct = new ProductModel();
            exampleProduct.setCode(productSKU);
            exampleProduct.setCatalogVersion(activeCatalogVersion);
            product = flexibleSearchService.getModelByExample(exampleProduct);
        } catch (ModelNotFoundException | AmbiguousIdentifierException e) {
            LOG.error("Error in getting replacement product" + e);
            LOG.error(ExceptionUtils.getStackTrace(e));
        }

        final Map<String, Pair<ClientImageData, List<String>>> stylesizeMap = new HashMap<>();
        final Map<String, String> variantProductMap = new HashMap<>();
        final Map<String, Integer> variantProductStockDetailsMap = new HashMap<>();

        final ApparelSizeVariantProductModel sizeProduct = ((ApparelSizeVariantProductModel) product);
        final ApparelStyleVariantProductModel styleProduct = (ApparelStyleVariantProductModel) sizeProduct.getBaseProduct();
        final Collection<VariantProductModel> styleVariants = styleProduct.getBaseProduct().getVariants();

        if (styleProduct.getBaseProduct().getApprovalStatus().equals(ArticleApprovalStatus.APPROVED)) {
            for (final VariantProductModel sVariant : styleVariants) {
                if (sVariant instanceof ApparelStyleVariantProductModel) {
                    final ApparelStyleVariantProductModel styleVariant = (ApparelStyleVariantProductModel) sVariant;
                    final String bStyle = styleVariant.getStyle();

                    if (styleVariant.getApprovalStatus().equals(ArticleApprovalStatus.APPROVED)) {
                        final ProductData productData = sslDefaultRMAProductConverter.convert(styleVariant);
                        final ClientImageData imageData = sslDefaultRMAProductImageDataConverter.convert(productData);

                        final List<String> sizeList = new ArrayList<>();
                        final Collection<VariantProductModel> sizeVariants = styleVariant.getVariants();
                        for (final VariantProductModel siVariant : sizeVariants) {
                            if (siVariant instanceof ApparelSizeVariantProductModel) {
                                final ApparelSizeVariantProductModel sizeVariant = (ApparelSizeVariantProductModel) siVariant;
                                try {
                                    final int sizeVariantStock = stockService.getTotalStockLevelAmount(sizeVariant);
                                    if (sizeVariantStock > 0) {
                                        final String variantSize = sizeVariant.getSize();
                                        final String styleSize = bStyle + variantSize;
                                        variantProductMap.put(styleSize, sizeVariant.getCode());
                                        variantProductStockDetailsMap.put(sizeVariant.getCode(), sizeVariantStock);
                                        sizeList.add(sizeVariant.getSize());
                                    }
                                }catch(StockLevelNotFoundException exception){
                                    if(LOG.isDebugEnabled()){
                                        LOG.debug("Variant stock not found - " + sizeVariant.getCode());
                                    }
                                }
                            }
                        }

                        stylesizeMap.put(bStyle, new Pair<>(imageData, sizeList));
                    }
                }
            }
        }

        final ExchangeProductVariants exchangeProductVariants = new ExchangeProductVariants();
        exchangeProductVariants.setStyleSizeVariants(stylesizeMap);
        exchangeProductVariants.setVariantProductDetails(variantProductMap);
        exchangeProductVariants.setVariantProductStockDetails(variantProductStockDetailsMap);

        return exchangeProductVariants;
    }

    private static boolean isOutOfStock(final ProductModel productModel, final StockData stockData) {
        boolean isOutOfStock = true;
        final long availablelAmount = stockData.getStockLevel().longValue();
        final StockLevelStatus stockLevelStatus = stockData.getStockLevelStatus();
        if (availablelAmount > 0
                && (stockLevelStatus.equals(StockLevelStatus.INSTOCK) || stockLevelStatus.equals(StockLevelStatus.LOWSTOCK))
                && (productModel.getApprovalStatus().equals(ArticleApprovalStatus.APPROVED))) {
            isOutOfStock = false;
        }
        return isOutOfStock;
    }

    @Override
    public String convertToReturn(final String rmaNumber, final String refundMode, final String accountName, final String accountNumber,
            final String ifscCode, final String bankName) {
        final ReturnRequestModel returnRequest = getSslODCReturnDao().getReturnRequestByRMA(rmaNumber);
        if (null == returnRequest) {
            return "Failure";
        }

        try {
            // 1. Change Type
            returnRequest.setType(ReturnRequestType.RETURN);

            // 2. Release Stocks
            // 3. Change Reason Type
            // 4. Create Refund Entry
            final Set<ReturnEntryModel> returnEntries = new HashSet<ReturnEntryModel>(returnRequest.getReturnEntries());
            for (final ReturnEntryModel returnEntry : returnEntries) {
                final ReplacementEntryModel replacementEntry = (ReplacementEntryModel) returnEntry;

                // Releasing Stock
                final Set<ExchangeStockLevelModel> exchangeStocks = new HashSet<ExchangeStockLevelModel>(
                        replacementEntry.getExchangeStockLevels());
                for (final ExchangeStockLevelModel exchangeStock : exchangeStocks) {
                    final StockLevelModel stock = exchangeStock.getStockLevel();
                    stock.setOnlineHold(stock.getOnlineHold() - (int) exchangeStock.getQuantity());
                    modelService.save(stock);
                    modelService.remove(exchangeStock);
                }

                // Changing Reason
                final RefundReason refundReason = RefundReason.valueOf(replacementEntry.getReason().getCode());

                // Creating Refund Entry
                final RefundEntryModel refundEntry = modelService.create(RefundEntryModel.class);
                refundEntry.setOrderEntry(replacementEntry.getOrderEntry());
                refundEntry.setExpectedQuantity(replacementEntry.getExpectedQuantity());
                refundEntry.setReceivedQuantity(replacementEntry.getReceivedQuantity());
                LOG.info("convertToReturn : Received quantity set to " + returnEntry.getReceivedQuantity() + " for rma : " + rmaNumber);
                refundEntry.setInventoryZoneQuantity(replacementEntry.getInventoryZoneQuantity());
                refundEntry.setDamagedZoneQuantity(replacementEntry.getDamagedZoneQuantity());
                refundEntry.setStatus(replacementEntry.getStatus());
                refundEntry.setAction(replacementEntry.getAction());
                refundEntry.setReason(refundReason);
                refundEntry.setNotes(replacementEntry.getNotes());
                refundEntry.setReturnRequest(replacementEntry.getReturnRequest());
                modelService.save(refundEntry);

                modelService.save(replacementEntry);

                // Deleting Replacement Entry
                modelService.remove(replacementEntry);
            }

            returnRequest.setRefundMode(ReturnRequestRefundMode.valueOf(refundMode));
            if (refundMode.equals(ReturnRequestRefundMode.BANKTRANSFER.getCode()) && StringUtils.isNotEmpty(accountNumber)) {
                final ReturnRequestBankDetailsModel bankDetails = new ReturnRequestBankDetailsModel();
                bankDetails.setAccountName(accountName);
                bankDetails.setAccountNumber(accountNumber);
                bankDetails.setIfscCode(ifscCode);
                bankDetails.setBankName(bankName);
                returnRequest.setPriceDiffBankDetails(bankDetails);
            }

            modelService.save(returnRequest);

            returnService.updateCsTicket(returnRequest, 0.0);

            /*
             * returnService.sendEmail(new ArrayList() { { add(returnRequest); } });
             */

            sendExchangeToReturnEmail(returnRequest);

        } catch (final ModelSavingException e) {
            LOG.error("Error in converting Exchange to return" + e);
            return "Failure";
        }
        return "Success";
    }

    private void sendExchangeToReturnEmail(final ReturnRequestModel returnRequest) {
        final OrderProcessModel orderProcessModel = (OrderProcessModel) getSslSendEmailWithAttachmentService().getBusinessProcessService()
                .createProcess("sendExchangeToReturnEmailProcess-" + returnRequest.getOrder().getCode() + "-" + System.currentTimeMillis(),
                        "sendExchangeToReturnEmailProcess");
        LOG.debug("orderProcessModel for exchange to return email" + orderProcessModel);

        final Set<String> returnReqSet = new HashSet<>();
        returnReqSet.add(returnRequest.getCode());
        orderProcessModel.setReturnReqNumber(returnReqSet);
        orderProcessModel.setOrder(returnRequest.getOrder());
        getBusinessProcessService().startProcess(orderProcessModel);
    }

    @Override
    public Pair<CartModel, ReturnRequestModel> getCart(final String rmaNumber) {
        final ReturnRequestModel returnRequest = getSslODCReturnDao().getReturnRequestByRMA(rmaNumber);
        CartModel cart = null;
        try {
            if (null != returnRequest) {
                final CartModel exampleCart = new CartModel();
                exampleCart.setCartId(returnRequest.getRMA() + "-" + returnRequest.getWarehouse().getCode());
                cart = flexibleSearchService.getModelByExample(exampleCart);
            }
        } catch (final ModelNotFoundException e) {
            LOG.error("Error in getting cart");
        }
        return new Pair<>(cart, returnRequest);
    }

    @Override
    public Pair<Boolean, Set> splitEntries(final ReturnRequestModel returnRequest) {
        Set<ReturnStatus> returnStatus = new HashSet<>();
        try {
            splitConsignment(returnRequest);// Split Consignment
            returnStatus = splitExchangeEntry(returnRequest);// Split Return
            // Entry
            // according to
            // Final Return
            // Quantity
            getModelService().save(returnRequest);
        } catch (final Exception e) {
            LOG.error(ExceptionUtils.getStackTrace(e));
            return new Pair<>(Boolean.FALSE, null);
        }

        return new Pair<>(Boolean.TRUE, returnStatus);
    }

    private Set<ReturnStatus> splitExchangeEntry(final ReturnRequestModel returnRequest) {
        try {
            final List<ReturnEntryModel> returnEntries = returnRequest.getReturnEntries();
            final Set<ReturnStatus> refundStatus = new HashSet<>();
            for (final ReturnEntryModel returnEntry : returnEntries) {

                final ReplacementEntryModel replacementEntry = (ReplacementEntryModel) returnEntry;

                final long finalReturnQuantity = replacementEntry.getFinalReturnQuantity();
                final long expectedQuantity = replacementEntry.getExpectedQuantity().longValue();

                if (!replacementEntry.getStatus().equals(ReturnStatus.READY)) {
                    continue;
                }
                if (finalReturnQuantity == 0l) {
                    replacementEntry.setStatus(ReturnStatus.EXCHANGE_REJECTED);
                    refundStatus.add(ReturnStatus.EXCHANGE_REJECTED);
                } else if (finalReturnQuantity < expectedQuantity) {

                    final Set<ExchangeStockLevelModel> exchangeStocks = replacementEntry.getExchangeStockLevels();

                    final Set<ExchangeStockLevelModel> rejectedExchangeStocks = new HashSet<ExchangeStockLevelModel>();
                    final Set<ExchangeStockLevelModel> approvedExchangeStocks = new HashSet<ExchangeStockLevelModel>();

                    // replacementEntry.setExchangeStockLevels(approvedExchangeStocks);
                    replacementEntry.setStatus(ReturnStatus.EXCHANGE_ISSUED);
                    replacementEntry.setExpectedQuantity(Long.valueOf(replacementEntry.getFinalReturnQuantity()));
                    replacementEntry.setFinalReturnQuantity(replacementEntry.getFinalReturnQuantity());

                    // Create an entry for Exchange rejected items
                    final ReplacementEntryModel rejectedReplacementEntry = modelService.create(ReplacementEntryModel.class);
                    // rejectedReplacementEntry.setExchangeStockLevels(rejectedExchangeStocks);
                    rejectedReplacementEntry.setStatus(ReturnStatus.EXCHANGE_REJECTED);
                    rejectedReplacementEntry.setExpectedQuantity(Long.valueOf(expectedQuantity - finalReturnQuantity));
                    rejectedReplacementEntry.setFinalReturnQuantity(0);
                    rejectedReplacementEntry.setReceivedQuantity(replacementEntry.getReceivedQuantity());
                    LOG.info("splitExchangeEntry : Received quantity set to " + replacementEntry.getReceivedQuantity() + " for rma : "
                            + returnRequest.getCode());
                    rejectedReplacementEntry.setInventoryZoneQuantity(replacementEntry.getInventoryZoneQuantity());
                    rejectedReplacementEntry.setDamagedZoneQuantity(replacementEntry.getDamagedZoneQuantity());
                    rejectedReplacementEntry.setAction(ReturnAction.IMMEDIATE);
                    rejectedReplacementEntry.setReason(ReplacementReason.DAMAGEDINTRANSIT);
                    rejectedReplacementEntry.setNotes("Exchange Rejected");
                    rejectedReplacementEntry.setReturnRequest(returnRequest);
                    rejectedReplacementEntry.setOrderEntry(replacementEntry.getOrderEntry());
                    modelService.save(rejectedReplacementEntry);

                    for (final ExchangeStockLevelModel exchangeStock : exchangeStocks) {
                        if (exchangeStock.getQuantity() == 0) {
                            rejectedExchangeStocks.add(exchangeStock);
                            exchangeStock.setReplacementEntry(rejectedReplacementEntry);
                            modelService.save(exchangeStock);
                            modelService.save(rejectedReplacementEntry);
                        } else if (exchangeStock.getRejectedQuantity() == 0) {
                            approvedExchangeStocks.add(exchangeStock);
                            modelService.save(exchangeStock);
                            modelService.save(replacementEntry);
                        } else {
                            final ExchangeStockLevelModel rejectedExchangeStock = getModelService().clone(exchangeStock);
                            rejectedExchangeStock.setQuantity(0);
                            rejectedExchangeStock.setReplacementEntry(rejectedReplacementEntry);
                            modelService.save(rejectedExchangeStock);
                            modelService.save(rejectedReplacementEntry);
                            rejectedExchangeStocks.add(rejectedExchangeStock);

                            exchangeStock.setRejectedQuantity(0);
                            modelService.save(exchangeStock);
                            modelService.save(replacementEntry);
                            approvedExchangeStocks.add(exchangeStock);
                        }
                    }
                    refundStatus.add(ReturnStatus.REFUND_PARTIAL);
                } else if (replacementEntry.getFinalReturnQuantity() == replacementEntry.getExpectedQuantity().longValue()) {
                    replacementEntry.setStatus(ReturnStatus.EXCHANGE_ISSUED);
                    refundStatus.add(ReturnStatus.EXCHANGE_ISSUED);
                }
                modelService.save(replacementEntry);

            }

            return refundStatus;
        } catch (final Exception e) {
            LOG.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public void sendEmail(final Set<ReturnStatus> returnStatus, final ReturnRequestModel returnRequest) {

        ConsignmentModel returnedConsignment = null;
        final Map<String, Object> emailAttachmentsMap = new HashMap<String, Object>();
        for (final ConsignmentModel returnConsignment : returnRequest.getOrder().getConsignments()) {
            if (returnRequest.equals(returnConsignment.getReturnRequest())
                    && !ConsignmentStatus.CANCELLED.equals(returnConsignment.getStatus())) {
                returnedConsignment = returnConsignment;
                break;
            }
        }

        if (returnedConsignment != null) {
            if ((returnedConsignment.getInvoiceAmount() != null && returnedConsignment.getInvoiceAmount().doubleValue() > 0)
                    || (returnedConsignment.getBosAmount() != null && returnedConsignment.getBosAmount().doubleValue() > 0)) {
                emailAttachmentsMap.put(returnedConsignment.getOrder().getCode(),
                        sslSendEmailWithAttachmentService.generateCreditNote(returnedConsignment.getInvoiceNumber()));
            }

        }

        if (null != returnRequest.getType()) {
            if (returnStatus.size() == 1) {
                final ReturnStatus status = returnStatus.iterator().next();
                if (ReturnStatus.EXCHANGE_REJECTED.equals(status) || ReturnStatus.REFUND_REJECTED.equals(status)) {
                    sendRejectedRefundMail(returnRequest, emailAttachmentsMap);
                } else if ((ReturnRequestType.RETURN.getCode()).equals(returnRequest.getType().getCode())) {
                    // send return email
                    sendReturnRefundMail(returnRequest, emailAttachmentsMap);
                } else if ((ReturnRequestType.EXCHANGE.getCode()).equals(returnRequest.getType().getCode())) {
                    // send exchange email
                    sendExchangeAcceptedMail(returnRequest, emailAttachmentsMap);
                }
            } else {
                // always send partial accepted email
                if ((ReturnRequestType.RETURN.getCode()).equals(returnRequest.getType().getCode())) {
                    // send return email
                    sendReturnRefundMail(returnRequest, emailAttachmentsMap);
                } else if ((ReturnRequestType.EXCHANGE.getCode()).equals(returnRequest.getType().getCode())) {
                    // send exchange email
                    sendExchangeAcceptedMail(returnRequest, emailAttachmentsMap);
                }
            }
        }
    }

    @Override
    public Pair<Double, ReturnRequestModel> getPriceDiff(final String rma) {
        final ReturnRequestModel returnRequest = getSslODCReturnDao().getReturnRequestByRMA(rma);

        double originalPrice = 0.0;

        final List<ReturnEntryModel> returnEntries = returnRequest.getReturnEntries();

        final Map<String, Long> productQuantityMap = new HashMap<String, Long>();
        for (final ReturnEntryModel returnEntry : returnEntries) {
            final ReplacementEntryModel replacementEntry = (ReplacementEntryModel) returnEntry;
            final Set<ExchangeStockLevelModel> exchangeStocks = replacementEntry.getExchangeStockLevels();
            int returnQuantity = 0;
            for (final ExchangeStockLevelModel exchangeStock : exchangeStocks) {
                returnQuantity += exchangeStock.getQuantity();
                if (exchangeStock.getQuantity() != 0) {
                    if (productQuantityMap.containsKey(exchangeStock.getStockLevel().getProductCode())) {
                        productQuantityMap.put(exchangeStock.getStockLevel().getProductCode(),
                                Long.valueOf(productQuantityMap.get(exchangeStock.getStockLevel().getProductCode()).longValue()
                                        + exchangeStock.getQuantity()));
                    } else {
                        productQuantityMap.put(exchangeStock.getStockLevel().getProductCode(), Long.valueOf(exchangeStock.getQuantity()));
                    }
                }
            }
            originalPrice = originalPrice + (returnQuantity * replacementEntry.getOrderEntry().getCalculatedRefundPerUnit().doubleValue());
        }

        returnRequest.setTotalRefundAmount(originalPrice);

        getModelService().save(returnRequest);

        if (originalPrice == 0) {
            return null;
        }

        try {
            final CartModel exampleCart = new CartModel();
            exampleCart.setCartId(returnRequest.getRMA() + "-" + returnRequest.getWarehouse().getCode());
            final CartModel cart = flexibleSearchService.getModelByExample(exampleCart);
            commerceCartService.recalculateCart(cart);
            final Double replacementPrice = cart.getTotalPrice();
            return new Pair<>(Double.valueOf(replacementPrice.doubleValue() - originalPrice), returnRequest);
        } catch (ModelNotFoundException | AmbiguousIdentifierException | CalculationException e) {
            LOG.error("Error in getting cart " + e);
            LOG.error(ExceptionUtils.getStackTrace(e));
        }

        // Release Stocks temporarily to create cart
        for (final ReturnEntryModel returnEntry : returnEntries) {
            final ReplacementEntryModel replacementEntry = (ReplacementEntryModel) returnEntry;
            if (!replacementEntry.getStatus().equals(ReturnStatus.REFUND_REJECTED)) {
                final Set<ExchangeStockLevelModel> exchangeStocks = replacementEntry.getExchangeStockLevels();
                for (final ExchangeStockLevelModel exchangeStock : exchangeStocks) {
                    exchangeStock.getStockLevel()
                            .setOnlineHold(exchangeStock.getStockLevel().getOnlineHold() - (int) exchangeStock.getQuantity());
                    modelService.save(exchangeStock.getStockLevel());
                }
            }
        }

        final CartModel cart = cartFactory.createCart();
        cart.setUser(returnRequest.getOrder().getUser());
        cart.setCurrency(returnRequest.getOrder().getCurrency());
        cart.setCartId(returnRequest.getRMA() + "-" + returnRequest.getWarehouse().getCode());
        cart.setDeliveryAddress(addressService.cloneAddress(returnRequest.getOrder().getDeliveryAddress()));
        cart.setUserCart(Boolean.FALSE);
        //If we do not save cart here, and add to cart is not successful, employee cart gets created.
        modelService.save(cart);
        //session user was refund manager, if employee cart had entries remove them first.
        commerceCartService.removeAllEntries(cart);
        final CatalogVersionModel catalogVersion = ((CMSSiteModel) (returnRequest.getOrder().getSite())).getDefaultCatalog()
                .getActiveCatalogVersion();
        final ImpersonationContext context = new ImpersonationContext();
        context.setOrder(cart);
        context.setUser(cart.getUser());
        context.setCurrency(cart.getCurrency());
        if (catalogVersion != null) {
            context.setCatalogVersions(Arrays.asList(catalogVersion));
        }
        try {
            impersonationService.executeInContext(context, new ImpersonationService.Executor() {
                @Override
                public Object execute() {
                    //to prevent getsessioncart from creating new cart
                    cartService.setSessionCart(cart);
                    for (final String productCode : productQuantityMap.keySet()) {
                        try {
                            final ProductModel product = productService.getProductForCode(productCode);
                            final CommerceCartParameter parameter = new CommerceCartParameter();
                            parameter.setEnableHooks(true);
                            parameter.setCart(cart);
                            parameter.setProduct(product);
                            parameter.setQuantity(productQuantityMap.get(productCode).longValue());
                            parameter.setUnit(product.getUnit());
                            parameter.setCreateNewEntry(true);
                            commerceCartService.addToCart(parameter);
                        } catch (final CommerceCartModificationException e) {
                            LOG.error("Exception while adding product to cart");
                            LOG.error(ExceptionUtils.getStackTrace(e));
                        }
                    }
                    return null;
                }
            });
        } catch (final Exception e) {
            LOG.error("Exception while executing context in add to cart [" + cart + "]", e);
        }


        getModelService().save(cart);

        // Hold Stocks again
        for (final ReturnEntryModel returnEntry : returnEntries) {
            final ReplacementEntryModel replacementEntry = (ReplacementEntryModel) returnEntry;
            if (!replacementEntry.getStatus().equals(ReturnStatus.REFUND_REJECTED)) {
                final Set<ExchangeStockLevelModel> exchangeStocks = replacementEntry.getExchangeStockLevels();
                for (final ExchangeStockLevelModel exchangeStock : exchangeStocks) {
                    exchangeStock.getStockLevel()
                            .setOnlineHold(exchangeStock.getStockLevel().getOnlineHold() + (int) exchangeStock.getQuantity());
                    modelService.save(exchangeStock.getStockLevel());
                }
            }
        }

        final Double replacementPrice = cart.getTotalPrice();

        return new Pair<>(Double.valueOf(replacementPrice.doubleValue() - originalPrice), returnRequest);
    }

    @Override
    public void createPriceDiffCSTicket(final ReturnRequestModel returnRequest, final RMARefundData rmaRefundData) {
        final CsTicketModel csticketmodel = modelService.create(CsTicketModel.class);

        csticketmodel.setHeadline("Exchange: Order-" + returnRequest.getOrder().getCode() + " RMA-" + returnRequest.getRMA());
        csticketmodel.setCategory(CsTicketCategory.EXCHANGE);
        csticketmodel.setPriority(CsTicketPriority.HIGH);
        csticketmodel.setOrder(returnRequest.getOrder());
        csticketmodel.setCustomer(returnRequest.getOrder().getUser());
        csticketmodel.setState(CsTicketState.OPEN);
        csticketmodel.setAssignedGroup((CsAgentGroupModel) userService.getUserGroupForUID("csgroup"));

        final CsCustomerEventModel csCustomerEventModel = modelService.create(CsCustomerEventModel.class);
        csCustomerEventModel.setInterventionType(CsInterventionType.CALL);
        csCustomerEventModel.setSubject("Exchange Order Ticket");
        csCustomerEventModel.setText("Exchange Order");
        csCustomerEventModel.setReason(CsEventReason.EXCHANGE);

        final CsTicketModel csTicket = ticketBusinessService.createTicket(csticketmodel, csCustomerEventModel);

        final StringBuilder note = new StringBuilder();
        final String accountName = StringUtils.isEmpty(rmaRefundData.getPriceDiffAccountName()) ? ""
                : rmaRefundData.getPriceDiffAccountName();
        final String accountNumber = StringUtils.isEmpty(rmaRefundData.getPriceDiffAccountNumber()) ? ""
                : rmaRefundData.getPriceDiffAccountNumber();
        final String ifscCode = StringUtils.isEmpty(rmaRefundData.getPriceDiffIfscCode()) ? "" : rmaRefundData.getPriceDiffIfscCode();
        final String bankName = StringUtils.isEmpty(rmaRefundData.getPriceDiffBankName()) ? "" : rmaRefundData.getPriceDiffBankName();
        note.append("Account Name: " + accountName);
        note.append("\t\tAccount Number: " + accountNumber + "\r");
        if (StringUtils.isNotEmpty(bankName)) {
            note.append("Bank Name: " + bankName + "\t\t");
        }
        if (StringUtils.isNotEmpty(ifscCode)) {
            note.append("IFSC Code: " + ifscCode);
        }
        note.append("\rPrice Difference to be paid to user: " + Math.abs(Double.valueOf(rmaRefundData.getPriceDiff()).longValue()));

        ticketBusinessService.addNoteToTicket(csTicket, CsInterventionType.CALL, CsEventReason.EXCHANGE, note.toString(), null);

        modelService.save(csTicket);
    }

    @Override
    public Pair<Integer, List<ClientRMADataResponse>> searchDeliveryFailedConsignmentEntries(final SearchRMARequest request,
            final String stockLocationCode) {
        final Pair<Integer, List<ConsignmentModel>> returnRequestsPair = getSslODCReturnDao().searchDeliveryFailedConsignments(request,
                stockLocationCode);
        final List<ClientRMADataResponse> rmaDataResponses = new ArrayList<>();
        if (returnRequestsPair != null && returnRequestsPair.getKey().intValue() > 0
                && CollectionUtils.isNotEmpty(returnRequestsPair.getValue())) {

            for (final ConsignmentModel consignmentModel : returnRequestsPair.getValue()) {
                final ClientRMADataResponse clientRMADataResponse = getSslRTOConverter().convert(consignmentModel);
                rmaDataResponses.add(clientRMADataResponse);
            }

            return new Pair<>(returnRequestsPair.getKey(), rmaDataResponses);
        }
        return new Pair<>(0, rmaDataResponses);
    }

    @Override
    public Pair<Boolean, String> cancelConsignment(final String consignmentCode) {

        final ConsignmentModel consignmentModel = getSslODCReturnDao().getConsignmentModel(consignmentCode);
        if (null != consignmentModel) {
            final ConsignmentModel newConsignmentModel = getModelService().create(ConsignmentModel.class);
            newConsignmentModel.setShippingAddress(consignmentModel.getDeliveryAddress());
            newConsignmentModel.setDeliveryAddress(consignmentModel.getShippingAddress());
            newConsignmentModel.setWarehouse(consignmentModel.getWarehouse());
            newConsignmentModel.setStatus(ConsignmentStatus.RETURN_TO_VENDOR);
            newConsignmentModel.setOrder(consignmentModel.getOrder());

            final List<ReturnableEntity> returnableEntityList = new ArrayList<>();
            for (final ConsignmentEntryModel consignmentEntry : consignmentModel.getConsignmentEntries()) {
                final long shippedQuantity = getShippedQuantityForConsignmentEntry(consignmentEntry);
                if (shippedQuantity > 0) {
                    returnableEntityList
                            .add(createReturnableEntity(consignmentEntry, "Return To Origin", RefundReason.REFUSED, shippedQuantity));
                }
            }
            final ReturnRequestModel returnRequestModel = sslReturnServiceImpl.generateReturnRequestPerODC(returnableEntityList,
                    (OrderModel) consignmentModel.getOrder());

            returnRequestModel.setWarehouse(consignmentModel.getWarehouse());
            try {
                final WarehouseModel exampleWarehouse = new WarehouseModel();
                exampleWarehouse.setCode(consignmentModel.getWarehouse().getInvoiceStoreId());
                final WarehouseModel warehouse = flexibleSearchService.getModelByExample(exampleWarehouse);
                if (null != warehouse) {
                    returnRequestModel.setWarehouse(warehouse);
                }
            } catch (ModelNotFoundException | AmbiguousIdentifierException e) {
                LOG.error("Error in setting warehouse in return request " + e);
                LOG.error(ExceptionUtils.getStackTrace(e));
            }
            newConsignmentModel.setWarehouse(returnRequestModel.getWarehouse());

            final String processCode = String.format("%s-%s", newConsignmentModel.getWarehouse().getCode(), returnRequestModel.getRMA());
            final String invoiceNumber = String.format("%s-%s-%s", newConsignmentModel.getWarehouse().getCode(),
                    returnRequestModel.getRMA(), newConsignmentModel.getOrder().getCode());

            newConsignmentModel.setCode(processCode);
            newConsignmentModel.setProcessCode(processCode);
            newConsignmentModel.setInvoiceNumber(invoiceNumber);
            modelService.save(newConsignmentModel);

            for (final ConsignmentEntryModel consignmentEntry : consignmentModel.getConsignmentEntries()) {
                final long shippedQuantity = getShippedQuantityForConsignmentEntry(consignmentEntry);
                if (shippedQuantity > 0) {
                    final ConsignmentEntryModel newConsignmentEntry = getModelService().create(ConsignmentEntryModel.class);
                    newConsignmentEntry.setConsignment(newConsignmentModel);
                    newConsignmentEntry.setQuantity(shippedQuantity);
                    newConsignmentEntry.setOrderEntry(consignmentEntry.getOrderEntry());
                    newConsignmentEntry.setInternalEntries(new ArrayList<InternalConsignmentEntryModel>());
                    newConsignmentEntry.setStatus(ConsignmentEntryStatus.ALLOCATED);
                    modelService.save(newConsignmentEntry);
                }
            }

            returnRequestModel.setPickup(ReturnRequestPickup.PICKUPREQUIRED);
            returnRequestModel.setStatus(ReturnStatus.RECEIVED);
            if (null != consignmentModel.getTrackingID()) {
                returnRequestModel.setAwbNumber(consignmentModel.getTrackingID());
            }
            if (consignmentModel.getCarrier().equalsIgnoreCase("ARA") || consignmentModel.getCarrier().equalsIgnoreCase("BD")
                    || consignmentModel.getCarrier().equalsIgnoreCase("DOT") || consignmentModel.getCarrier().equalsIgnoreCase("FF")
                    || consignmentModel.getCarrier().equalsIgnoreCase("EK") || consignmentModel.getCarrier().equalsIgnoreCase("DEL")
                    || consignmentModel.getCarrier().equalsIgnoreCase("ECOM")) {
                returnRequestModel.setCarrier(CourierEnum
                        .valueOf(enumerationService.getEnumerationName(CourierEnum.valueOf(consignmentModel.getCarrier())).toString()));
            }
            returnRequestModel.setRefundMode(ReturnRequestRefundMode.ORIGINALMODE);
            returnRequestModel.setRmaGeneratedByRTO(true);
            final CsTicketModel csTicketModel = sslReturnServiceImpl.createCSTicket(returnRequestModel, consignmentModel.getOrder());
            returnRequestModel.setCsTicket(csTicketModel);
            newConsignmentModel.setReturnRequest(returnRequestModel);
            sslReturnServiceImpl.createModificationEntry(returnRequestModel, csTicketModel, csTicketModel.getAssignedAgent());
            modelService.save(returnRequestModel);

            consignmentModel.setStatus(ConsignmentStatus.RETURN_TO_VENDOR);
            getModelService().save(newConsignmentModel);
            getModelService().save(consignmentModel);
            sendRTONotification(returnRequestModel);

            return new Pair<>(Boolean.TRUE, enumerationService.getEnumerationName(consignmentModel.getStatus()));
        }
        return new Pair<>(Boolean.FALSE, null);
    }

    @Override
    public Pair<Boolean, String> reShipConsignment(final String consignmentCode) {
        final ConsignmentModel consignmentModel = getSslODCReturnDao().getConsignmentModel(consignmentCode);
        if (null != consignmentModel) {
            consignmentModel.setStatus(ConsignmentStatus.READY_TO_SHIP);
            consignmentModel.setCarrier(StringUtils.EMPTY);
            if (StringUtils.isNotBlank(consignmentModel.getTrackingID())) {
                ConsignmentTrackingUtils.saveInAwbNumberHistory(consignmentModel.getTrackingID(), consignmentModel);
            }
            consignmentModel.setTrackingID(StringUtils.EMPTY);
            consignmentModel.setShippingDate(null);
            getModelService().save(consignmentModel);

            /*
             * int index = 0; for (final ConsignmentModel consignment : consignmentModel.getOrder().getConsignments()) { index +=
             * consignment.getConsignmentProcesses().size(); } final ConsignmentProcessModel consignmentProcess =
             * getBusinessProcessService().<ConsignmentProcessModel> getProcess( consignmentModel.getProcessCode()); final OrderProcessModel
             * orderProcess = consignmentProcess.getParentProcess(); final ConsignmentProcessModel newProcess =
             * getBusinessProcessService().<ConsignmentProcessModel> createProcess( orderProcess.getCode() + "_" + index,
             * "consignment-process"); newProcess.setParentProcess(orderProcess); newProcess.setConsignment(consignmentModel);
             * getModelService().save(newProcess); consignmentModel.setProcessCode(newProcess.getCode());
             * getModelService().save(consignmentModel); // getBusinessProcessService().startProcess(newProcess);
             * getBusinessProcessService().restartProcess(newProcess, "takePayment");
             */
            getBusinessProcessService().restartProcess(consignmentModel.getConsignmentProcesses().iterator().next(), "takePayment");

            return new Pair<>(Boolean.TRUE, enumerationService.getEnumerationName(consignmentModel.getStatus()));
        }
        return new Pair<>(Boolean.FALSE, null);
    }

    private ReturnableEntity createReturnableEntity(final ConsignmentEntryModel consignmentEntry, final String notes,
            final RefundReason reason, final long shippedQuantity) {
        final ReturnableEntity entity = new ReturnableEntity();
        final RefundEntryModel refundEntryModel = modelService.create(RefundEntryModel.class);
        refundEntryModel.setOrderEntry(consignmentEntry.getOrderEntry());
        refundEntryModel.setExpectedQuantity(shippedQuantity);
        refundEntryModel.setStatus(ReturnStatus.RECEIVED);
        refundEntryModel.setAction(ReturnAction.IMMEDIATE);
        refundEntryModel.setReason(reason);
        refundEntryModel.setNotes(notes);
        refundEntryModel.setReceivedQuantity(shippedQuantity);
        LOG.info("createReturnableEntity : Received quantity set to " + shippedQuantity + " for ConsignmentEntryModel : "
                + consignmentEntry.getPk());
        modelService.save(refundEntryModel);
        entity.setRefundEntry(refundEntryModel);
        return entity;
    }

    @Override
    public String regenerateRma(final String rma) {
        final ReturnRequestModel returnRequest = getSslODCReturnDao().getReturnRequestByRMA(rma);

        final ReturnRequestModel regeneratedRMA = returnService.createReturnRequest(returnRequest.getOrder());
        regeneratedRMA.setType(returnRequest.getType());
        regeneratedRMA.setStatus(ReturnStatus.NOT_PICKED);
        if (ReturnRequestPickup.CUSTOMERCOURIER.equals(returnRequest.getPickup())) {
            regeneratedRMA.setStatus(ReturnStatus.CUSTOMER_DISPATCHED);
        }
        regeneratedRMA.setPickup(returnRequest.getPickup());
        regeneratedRMA.setRefundMode(returnRequest.getRefundMode());
        regeneratedRMA.setBankDetails(returnRequest.getBankDetails());
        regeneratedRMA.setWarehouse(returnRequest.getWarehouse());
        final String rmaNumber = returnService.createRMA(regeneratedRMA);
        regeneratedRMA.setRMA(rmaNumber);
        modelService.save(regeneratedRMA);

        final ConsignmentModel consignment = modelService.create(ConsignmentModel.class);
        try {
            consignment.setOrder(returnRequest.getOrder());
            consignment.setReturnRequest(regeneratedRMA);
            final String processCode = String.format("%s-%s", returnRequest.getWarehouse().getCode(), rmaNumber);
            final String invoiceNumber = String.format("%s-%s-%s", returnRequest.getWarehouse().getCode(), rmaNumber,
                    returnRequest.getOrder().getCode());
            consignment.setProcessCode(processCode);
            consignment.setInvoiceNumber(invoiceNumber);
            final Collection<PointOfServiceModel> pointsOfService = returnRequest.getWarehouse().getPointsOfService();
            final AddressModel deliveryAddress = pointsOfService.stream().findFirst().get().getAddress();
            consignment.setDeliveryAddress(deliveryAddress);
            consignment.setCode(processCode);
            AddressModel shippingAddress = returnRequest.getOrder().getDeliveryAddress();
            if (null == shippingAddress) {
                if (returnRequest.getOrder().getUser().getAddresses().stream().findFirst().isPresent()) {
                    shippingAddress = returnRequest.getOrder().getUser().getAddresses().stream().findFirst().get();
                }
            }
            consignment.setShippingAddress(shippingAddress);
            if (null != returnRequest.getType() && returnRequest.getType().equals(ReturnRequestType.EXCHANGE)) {
                consignment.setStatus(ConsignmentStatus.EXCHANGE_INITIATED);
            } else {
                consignment.setStatus(ConsignmentStatus.RETURN_INITIATED);
            }
            consignment.setWarehouse(returnRequest.getWarehouse());
            for (final ReturnEntryModel returnEntry : returnRequest.getReturnEntries()) {
                final Long quantity = returnEntry.getExpectedQuantity();
                final ConsignmentEntryModel consignmentEntry = getModelService().create(ConsignmentEntryModel.class);
                consignmentEntry.setConsignment(consignment);
                consignmentEntry.setQuantity(quantity);
                consignmentEntry.setOrderEntry(returnEntry.getOrderEntry());
                consignmentEntry.setInternalEntries(new ArrayList<InternalConsignmentEntryModel>());
                consignmentEntry.setStatus(ConsignmentEntryStatus.ALLOCATED);
                getModelService().save(consignmentEntry);
            }
            modelService.save(consignment);

            for (final ReturnEntryModel returnEntry : returnRequest.getReturnEntries()) {
                if (ReturnRequestType.RETURN.equals(returnRequest.getType())) {
                    final RefundEntryModel refundEntry = (RefundEntryModel) returnEntry;

                    final RefundEntryModel regeneratedRefundEntry = modelService.create(RefundEntryModel.class);

                    regeneratedRefundEntry.setOrderEntry(refundEntry.getOrderEntry());
                    regeneratedRefundEntry.setExpectedQuantity(refundEntry.getExpectedQuantity());
                    regeneratedRefundEntry.setStatus(ReturnStatus.NOT_PICKED);
                    regeneratedRefundEntry.setAction(ReturnAction.IMMEDIATE);
                    regeneratedRefundEntry.setReason(refundEntry.getReason());
                    regeneratedRefundEntry.setNotes(refundEntry.getNotes());
                    regeneratedRefundEntry.setReturnRequest(regeneratedRMA);
                    modelService.save(regeneratedRefundEntry);

                    refundEntry.setStatus(ReturnStatus.CANCELED);
                    modelService.save(refundEntry);
                } else if (ReturnRequestType.EXCHANGE.equals(returnRequest.getType())) {
                    final ReplacementEntryModel replacementEntry = (ReplacementEntryModel) returnEntry;

                    final ReplacementEntryModel regeneratedReplacementEntry = modelService.create(ReplacementEntryModel.class);

                    regeneratedReplacementEntry.setOrderEntry(replacementEntry.getOrderEntry());
                    regeneratedReplacementEntry.setExpectedQuantity(replacementEntry.getExpectedQuantity());
                    regeneratedReplacementEntry.setStatus(ReturnStatus.NOT_PICKED);
                    regeneratedReplacementEntry.setAction(ReturnAction.IMMEDIATE);
                    regeneratedReplacementEntry.setReason(replacementEntry.getReason());
                    regeneratedReplacementEntry.setNotes(replacementEntry.getNotes());
                    regeneratedReplacementEntry.setReturnRequest(regeneratedRMA);
                    modelService.save(regeneratedReplacementEntry);

                    for (final ExchangeStockLevelModel exchangeStock : replacementEntry.getExchangeStockLevels()) {
                        final ExchangeStockLevelModel regeneratedExchangeStock = modelService.create(ExchangeStockLevelModel.class);
                        regeneratedExchangeStock.setStockLevel(exchangeStock.getStockLevel());
                        regeneratedExchangeStock.setQuantity(exchangeStock.getQuantity());
                        regeneratedExchangeStock.setRejectedQuantity(exchangeStock.getRejectedQuantity());
                        regeneratedExchangeStock.setReplacementEntry(regeneratedReplacementEntry);
                        modelService.save(regeneratedExchangeStock);
                        modelService.save(regeneratedReplacementEntry);
                    }

                    replacementEntry.setStatus(ReturnStatus.CANCELED);
                    modelService.save(replacementEntry);
                }
            }

            modelService.save(regeneratedRMA);

            for (final ReturnEntryModel returnEntry : regeneratedRMA.getReturnEntries()) {
                for (final ConsignmentEntryModel consignmentEntry : returnEntry.getOrderEntry().getConsignmentEntries()) {
                    if (returnRequest.equals(consignmentEntry.getConsignment().getReturnRequest())) {
                        consignmentEntry.setStatus(ConsignmentEntryStatus.CANCELLED);
                        consignmentEntry.getConsignment().setStatus(ConsignmentStatus.CANCELLED);
                        modelService.save(consignmentEntry);
                        modelService.save(consignmentEntry.getConsignment());
                    }
                }
            }
            returnRequest.setStatus(ReturnStatus.CANCELED);

            modelService.save(returnRequest);

            CsTicketModel csTicket = null;
            if (ReturnRequestType.RETURN.equals(returnRequest.getType())) {
                csTicket = returnService.createCSTicket(regeneratedRMA, regeneratedRMA.getOrder());
                regeneratedRMA.setCsTicket(csTicket);
            } else if (ReturnRequestType.EXCHANGE.equals(returnRequest.getType())) {
                csTicket = exchangeService.createCSTicket(regeneratedRMA, regeneratedRMA.getOrder());
                regeneratedRMA.setCsTicket(csTicket);
            }

            returnService.createModificationEntry(returnRequest, csTicket, returnRequest.getCsTicket().getAssignedAgent());

            returnService.sendNotifications(new ArrayList() {
                {
                    add(regeneratedRMA);
                }
            });

            modelService.save(regeneratedRMA);
        } catch (ModelSavingException | ClassCastException e) {
            LOG.error("Error in regenerating RMA " + e);

            if (null != regeneratedRMA.getReturnEntries()) {
                modelService.removeAll(regeneratedRMA.getReturnEntries());
            }
            modelService.remove(regeneratedRMA);

            if (null != consignment) {
                if (null != consignment.getConsignmentEntries()) {
                    modelService.removeAll(consignment.getConsignmentEntries());
                }
                modelService.remove(consignment);
            }

            return "Failure";
        }

        return regeneratedRMA.getRMA();
    }

    /**
     * @return the businessProcessService
     */
    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    /**
     * @param businessProcessService
     *        the businessProcessService to set
     */
    @Required
    public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public Converter<ConsignmentModel, ClientRMADataResponse> getSslRTOConverter() {
        return sslRTOConverter;
    }

    public void setSslRTOConverter(final Converter<ConsignmentModel, ClientRMADataResponse> sslRTOConverter) {
        this.sslRTOConverter = sslRTOConverter;
    }

    /**
     * @return the sslReturnServiceImpl
     */
    public SSLReturnServiceImpl getSslReturnServiceImpl() {
        return sslReturnServiceImpl;
    }

    /**
     * @param sslReturnServiceImpl
     *        the sslReturnServiceImpl to set
     */
    public void setSslReturnServiceImpl(final SSLReturnServiceImpl sslReturnServiceImpl) {
        this.sslReturnServiceImpl = sslReturnServiceImpl;
    }

    @Override
    public ReturnRequestModel getReturnRequestByRMA(final String rma) {
        return getSslODCReturnDao().getReturnRequestByRMA(rma);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.ODCReturnService#validateStatus(java.lang .String)
     */
    @Override
    public boolean validateStatus(final String currentStatus, final String status) {
        if (currentStatus.equals(ReturnStatus.NOT_PICKED.toString())) {
            if (status.equals(enumerationService.getEnumerationName(ReturnStatus.PICKED).toUpperCase())
                    || status.equals(enumerationService.getEnumerationName(ReturnStatus.PICK_INITIATED).toUpperCase())
                    || status.equals(enumerationService.getEnumerationName(ReturnStatus.PICKUP_FAILED).toUpperCase())
                    || status.equals(enumerationService.getEnumerationName(ReturnStatus.NOT_NEEDED).toUpperCase())
                    || status.equals(enumerationService.getEnumerationName(ReturnStatus.NOT_PICKED).toUpperCase())) {
                return true;
            }
        } else if (currentStatus.equals(ReturnStatus.PICK_INITIATED.toString())) {
            if (status.equals(enumerationService.getEnumerationName(ReturnStatus.PICKED).toUpperCase())
                    || status.equals(enumerationService.getEnumerationName(ReturnStatus.NOT_PICKED).toUpperCase())
                    || status.equals(enumerationService.getEnumerationName(ReturnStatus.PICKUP_FAILED).toUpperCase())
                    || status.equals(enumerationService.getEnumerationName(ReturnStatus.PICK_INITIATED).toUpperCase())) {
                return true;
            }
        } else if (currentStatus.equals(ReturnStatus.PICKED.toString())) {
            if (status.equals(enumerationService.getEnumerationName(ReturnStatus.NOT_PICKED).toUpperCase())
                    || status.equals(enumerationService.getEnumerationName(ReturnStatus.PICK_INITIATED).toUpperCase())
                    || status.equals(enumerationService.getEnumerationName(ReturnStatus.PICKUP_FAILED).toUpperCase())
                    || status.equals(enumerationService.getEnumerationName(ReturnStatus.PICKED).toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void downloadRequest(final SearchRMARequest searchRMARequest, final HttpServletResponse response, final String storeLocationCode)
            throws Exception {
        final XSSFWorkbook workbook = returnDocumentService.getExcelForReturn(searchRMARequest, storeLocationCode);
        response.setHeader("Content-Type", "application/excel");
        final DateFormat df = new SimpleDateFormat("ddMMyy_HHmmss");
        final TimeZone timeZone = TimeZone.getTimeZone("IST");
        df.setTimeZone(timeZone);
        // Create a blank sheet
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + String.format("%s-%s_%s", EXCEL_NAME,
                        (storeLocationCode == null ? "Global" : storeLocationCode), df.format(new Date())) + ".xlsx\"");
        workbook.write(response.getOutputStream());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void downloadOpenRMAs(final HttpServletResponse response, final String storeLocationCode) throws Exception {
        final XSSFWorkbook workbook = returnDocumentService.getSheetForOpenRMAsWithBankDetails(storeLocationCode);
        response.setHeader("Content-Type", "application/excel");
        final DateFormat df = new SimpleDateFormat("ddMMyy_HHmmss");
        final TimeZone timeZone = TimeZone.getTimeZone("IST");
        df.setTimeZone(timeZone);
        // Create a blank sheet
        response.setHeader("Content-Disposition", "attachment; filename=\"" + String.format("%s-%s_%s", OPEN_RMA_EXCEL_NAME,
                (storeLocationCode == null ? "Global" : storeLocationCode), df.format(new Date())) + ".xlsx\"");
        workbook.write(response.getOutputStream());
    }

    /**
     * Method to get masked account number.
     *
     * @param acNumber
     *        : Account number to be masked.
     * @return String, Masked Account Number
     */
    @Override
    public String maskAccountNumber(final String acNumber) {
        String maskedAcNumStr = "";

        if (acNumber != null) {
            final int digToDisplayStart = Integer.parseInt(SslCoreConstants.DIGITS_TO_DISPLAY_IN_MASKED_AC_NUMBER_START);
            final int digToDisplayEnd = Integer.parseInt(SslCoreConstants.DIGITS_TO_DISPLAY_IN_MASKED_AC_NUMBER_END);
            final int acNumLen = acNumber.length();

            if (digToDisplayEnd + digToDisplayStart >= acNumLen) {
                maskedAcNumStr = acNumber;
            } else {
                final StringBuilder maskedAcNumber = new StringBuilder(acNumber.substring(0, digToDisplayStart));
                for (int i = 0; i < acNumLen - digToDisplayStart - digToDisplayEnd; i++) {
                    maskedAcNumber.append(MASK_CHAR);
                }
                maskedAcNumber.append(acNumber.substring(acNumLen - digToDisplayEnd));
                maskedAcNumStr = maskedAcNumber.toString();
            }
        }
        return maskedAcNumStr;
    }

    /**
     * @param returnRequest
     */
    private void sendRTONotification(final ReturnRequestModel returnRequest) {

        sendSMS(returnRequest);

        final OrderProcessModel orderProcessModel = getSslSendEmailWithAttachmentService().getBusinessProcessService().createProcess(
                "sendReturnToOriginEmailProcess-" + returnRequest.getOrder().getCode() + "-" + System.currentTimeMillis(),
                "sendReturnToOriginEmailProcess");
        LOG.debug("orderProcessModel for return to origin email" + orderProcessModel);

        final Set<String> returnReqSet = new HashSet<>();
        returnReqSet.add(returnRequest.getCode());
        orderProcessModel.setReturnReqNumber(returnReqSet);
        orderProcessModel.setOrder(returnRequest.getOrder());
        getBusinessProcessService().startProcess(orderProcessModel);
    }


    @Override public ReturnRequestModel getReturnRequestByExchangeOrder(String exchangeOrderCode) {
        return getSslODCReturnDao().getReturnRequestByExchangeOrder(exchangeOrderCode);
    }

}
