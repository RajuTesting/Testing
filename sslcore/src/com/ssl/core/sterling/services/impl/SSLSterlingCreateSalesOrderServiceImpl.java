/**
 *
 */
package com.ssl.core.sterling.services.impl;

import de.hybris.platform.core.model.SSCreditsPaymentBreakupModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CCAvenueWalletPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.DebitCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.FreeChargePaymentInfoModel;
import de.hybris.platform.core.model.order.payment.GiftCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.LoyaltyCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.NetBankingPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.order.payment.SSCreditsPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.promotions.util.Pair;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.product.service.SslProductService;
import com.borngroup.ssl.core.sterling.util.SSLSterlingUtil;
import com.borngroup.ssl.core.util.CommonHelper;
import com.borngroup.ssl.fulfilmentprocess.enums.UnprocessedItemType;
import com.borngroup.ssl.fulfilmentprocess.model.UnprocessedItemModel;
import com.borngroup.ssl.loyalty.model.SslLoyaltyDetailModel;
import com.ssl.core.sterling.client.SSLSterlingRestClientUtil;
import com.ssl.core.sterling.discounts.SSLSterlingDiscountService;
import com.ssl.core.sterling.orderpush.dto.ExtensionDTO;
import com.ssl.core.sterling.orderpush.dto.InstructionDTO;
import com.ssl.core.sterling.orderpush.dto.InstructionsDTO;
import com.ssl.core.sterling.orderpush.dto.LineChargeDTO;
import com.ssl.core.sterling.orderpush.dto.LineChargesDTO;
import com.ssl.core.sterling.orderpush.dto.LinePriceInfoDTO;
import com.ssl.core.sterling.orderpush.dto.OrderLineDTO;
import com.ssl.core.sterling.orderpush.dto.OrderLinesDTO;
import com.ssl.core.sterling.orderpush.dto.PaymentDetailsDTO;
import com.ssl.core.sterling.orderpush.dto.PaymentMethodDTO;
import com.ssl.core.sterling.orderpush.dto.PaymentMethodsDTO;
import com.ssl.core.sterling.orderpush.dto.PriceInfoDTO;
import com.ssl.core.sterling.orderpush.dto.SSLSterlingCreateSalesOrderRequestDTO;
import com.ssl.core.sterling.orderpush.dto.SterlingAddressDTO;
import com.ssl.core.sterling.orderpush.dto.SterlingItemDTO;
import com.ssl.core.sterling.services.SSLSterlingCreateSalesOrderService;

/**
 * @author manikmalhotra
 *
 */
public class SSLSterlingCreateSalesOrderServiceImpl implements SSLSterlingCreateSalesOrderService {

    private static final String ORDER_CODE = "Order Code: ";
    private static final int START_COUNT = 1;

    private static final Logger LOG = Logger.getLogger(SSLSterlingCreateSalesOrderServiceImpl.class);

    private class SterlingLengthValidations {
        private SterlingLengthValidations() {
        }

        private static final int STERLING_ADDRESS_LENGTH = 70;
        private static final int STERLING_ENTRY_TYPE_LENGTH = 20;
        private static final int STERLING_CITY_STATE_ZIP_LENGTH = 35;
        private static final int STERLING_NAME_LENGTH = 64;
        private static final int STERLING_MOBILE_LENGTH = 40;
        private static final int STERLING_ITEM_ID_LENGTH = 40;
        private static final int STERLING_ITEM_DESC_LENGTH = 200;
        private static final int STERLING_TITLE_LENGTH = 10;
        private static final int SUBSTRING_INDEX = 0;
    }

    private final CommonHelper commonHelper = CommonHelper.getInstance();

    @Resource(name = "sslProductService")
    private SslProductService sslProductService;

    @Resource(name = "sslSterlingDiscountServiceImpl")
    private SSLSterlingDiscountService sslSterlingDiscountService;

    @Resource(name = "sslSterlingRestClientUtil")
    private SSLSterlingRestClientUtil sslSterlingRestClientUtil;

    @Resource(name = "modelService")
    private ModelService modelService;

    @Override
    public boolean pushSalesOrderToSterling(final OrderModel order) {
        final SSLSterlingCreateSalesOrderRequestDTO salesOrderDTO = getPushOrderRequestDTO(order);
        final String salesOrderXML = sslSterlingRestClientUtil.getMarshalledObject(salesOrderDTO);
        LOG.info("Order: " + order.getCode() + " ,XML being sent to Sterling: " + salesOrderXML);
        return pushCreateSalesOrderXMLToMQ(salesOrderXML, salesOrderDTO, START_COUNT);
    }

    @Override
    public SSLSterlingCreateSalesOrderRequestDTO getPushOrderRequestDTO(final OrderModel order) {
        final SSLSterlingCreateSalesOrderRequestDTO salesOrderDTO = new SSLSterlingCreateSalesOrderRequestDTO();
        try {

            populateBasicOrderDetails(order, salesOrderDTO);

            populateExtensionDetails(order, salesOrderDTO);

            populatePersonInfoBillToAddress(order, salesOrderDTO);

            populatePersonInfoShipToAddress(order, salesOrderDTO);

            populatePriceInfo(salesOrderDTO);

            populatePaymentMethod(order, salesOrderDTO);

            populateOrderLines(order, salesOrderDTO);

        } catch (final Exception e) {
            LOG.error("Error In Publishing Create Sales Order XML to Sterling. OrderId: " + order.getCode() + ", Exception is "
                    + e.getMessage());
        }
        return salesOrderDTO;
    }

    private boolean pushCreateSalesOrderXMLToMQ(final String salesOrderXML, final SSLSterlingCreateSalesOrderRequestDTO salesOrderDTO,
            final int currentRetryCount) {
        final int maxRetryCount = SSLSterlingUtil.getCreateSalesOrderRetryCount();
        final String createSalesOrderQueue = SSLSterlingUtil.getCreateSalesOrderQueue();
        final boolean success = sslSterlingRestClientUtil.sendRequestToMQ(salesOrderXML, createSalesOrderQueue);

        if (success) {
            LOG.info(ORDER_CODE + salesOrderDTO.getOrderNo() + " Sent successfully to Sterling MQ: " + createSalesOrderQueue);
        } else if (currentRetryCount < maxRetryCount) {
            LOG.info(ORDER_CODE + salesOrderDTO.getOrderNo() + " Retrying Order Push, Retry Count: " + (currentRetryCount + 1));
            pushCreateSalesOrderXMLToMQ(salesOrderXML, salesOrderDTO, currentRetryCount + 1);
        } else {
            LOG.info(ORDER_CODE + salesOrderDTO.getOrderNo() + " .Order Push Failed. Count Retried: " + currentRetryCount
                    + " ,Creating Unprocessed Item.");
            final UnprocessedItemModel unprocessedItemModel = modelService.create(UnprocessedItemModel.class);
            unprocessedItemModel.setItem(UnprocessedItemType.CREATE_SALES_ORDER);
            unprocessedItemModel.setTimeInMilli(Long.valueOf(System.currentTimeMillis()));
            unprocessedItemModel.setMmsMessageDto(salesOrderXML);
            modelService.saveAll(unprocessedItemModel);
        }
        return success;
    }

    private void populateOrderLines(final OrderModel order, final SSLSterlingCreateSalesOrderRequestDTO salesOrderDTO) {

        final Map<String, Map<AbstractOrderEntryModel, List<Pair<Double, String>>>> promotionDetailsMap = sslSterlingDiscountService
                .calculateOrderDiscounts(order);

        LOG.debug(ORDER_CODE + order.getCode() + " Populating OrderLine Details");
        final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> itemPromotionMap = promotionDetailsMap
                .get(SslCoreConstants.SterlingConstants.ITEMPROMOTIONS);
        final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> orderPromotionMap = promotionDetailsMap
                .get(SslCoreConstants.SterlingConstants.ORDERPROMOTIONS);
        final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> vouchersMap = promotionDetailsMap
                .get(SslCoreConstants.SterlingConstants.VOUCHERS);

        final OrderLinesDTO orderLinesDTO = new OrderLinesDTO();
        for (final AbstractOrderEntryModel orderEntry : order.getEntries()) {

            final OrderLineDTO orderLineDTO = new OrderLineDTO();

            populateOrderLineDTO(order, orderEntry, orderLineDTO);

            populateOrderInstructions(order, orderLineDTO);

            populateItemDTO(orderEntry, orderLineDTO);

            populateLinePriceInfo(orderEntry, orderLineDTO);

            final LineChargesDTO lineCharges = new LineChargesDTO();

            populateItemLineCharge(itemPromotionMap, orderEntry, lineCharges);

            populateOrderLineCharge(orderPromotionMap, orderEntry, lineCharges);

            populateVoucherLineCharge(vouchersMap, orderEntry, lineCharges);

            orderLineDTO.setLineCharges(lineCharges);

            orderLinesDTO.getOrderLine().add(orderLineDTO);

        }
        salesOrderDTO.setOrderLines(orderLinesDTO);

    }

    private OrderLineDTO populateOrderLineDTO(final OrderModel order, final AbstractOrderEntryModel orderEntry,
            final OrderLineDTO orderLineDTO) {

        if (SSLSterlingUtil.isPickupOrder(order)) {
            orderLineDTO.setFulfillmentType(SslCoreConstants.SterlingConstants.SSL_STORE_PICKUP);
            orderLineDTO.setDeliveryMethod(SslCoreConstants.SterlingConstants.PICK);
        }
        orderLineDTO.setOrderedQty(String.valueOf(orderEntry.getQuantity().longValue()));
        orderLineDTO.setPrimeLineNo(String.valueOf(orderEntry.getEntryNumber().intValue() + 1));
        orderLineDTO.setSubLineNo(SslCoreConstants.SterlingConstants.ONE);
        orderLineDTO.setShipNode(orderEntry.getPickupODC());
        return orderLineDTO;
    }

    /**
     * @param orderEntry
     * @param orderLineDTO
     */
    private void populateLinePriceInfo(final AbstractOrderEntryModel orderEntry, final OrderLineDTO orderLineDTO) {
        final LinePriceInfoDTO linePriceInfo = new LinePriceInfoDTO();
        linePriceInfo.setIsPriceLocked(SslCoreConstants.SterlingConstants.Y);
        if (orderEntry.getBasePrice() != null) {
            linePriceInfo.setUnitPrice(String.valueOf(orderEntry.getBasePrice().doubleValue()));
        }
        orderLineDTO.setLinePriceInfo(linePriceInfo);
    }

    private void populateVoucherLineCharge(final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> vouchersMap,
            final AbstractOrderEntryModel orderEntry, final LineChargesDTO lineCharges) {
        if (vouchersMap != null && vouchersMap.get(orderEntry) != null) {
            final LineChargeDTO lineCharge = new LineChargeDTO();
            lineCharge.setChargeCategory(SslCoreConstants.SterlingConstants.VOUCHER_DISCOUNT);
            lineCharge.setChargeName(SslCoreConstants.SterlingConstants.VOUCHER_PROMOTION_CHARGE_NAME);
            final List<Pair<Double, String>> voucherList = vouchersMap.get(orderEntry);
            lineCharge.setChargePerLine(String.valueOf(voucherList.get(0).getKey()));
            lineCharge.setReference(String.valueOf(voucherList.get(0).getValue()));
            lineCharges.getLineCharge().add(lineCharge);
        }
    }

    private void populateOrderLineCharge(final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> orderPromotionMap,
            final AbstractOrderEntryModel orderEntry, final LineChargesDTO lineCharges) {
        if (orderPromotionMap != null && orderPromotionMap.get(orderEntry) != null) {
            final LineChargeDTO lineCharge = new LineChargeDTO();
            lineCharge.setChargeCategory(SslCoreConstants.SterlingConstants.HEADER_DISCOUNT);
            lineCharge.setChargeName(SslCoreConstants.SterlingConstants.ORDER_PROMOTION_CHARGE_NAME);
            final List<Pair<Double, String>> orderPromotionList = orderPromotionMap.get(orderEntry);
            lineCharge.setChargePerLine(String.valueOf(orderPromotionList.get(0).getKey()));
            lineCharge.setReference(StringEscapeUtils.escapeXml(String.valueOf(orderPromotionList.get(0).getValue())));
            lineCharges.getLineCharge().add(lineCharge);
        }
    }

    private void populateItemLineCharge(final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> itemPromotionMap,
            final AbstractOrderEntryModel orderEntry, final LineChargesDTO lineCharges) {
        if (itemPromotionMap != null && itemPromotionMap.get(orderEntry) != null) {
            final LineChargeDTO lineCharge = new LineChargeDTO();
            lineCharge.setChargeCategory(SslCoreConstants.SterlingConstants.LINE_DISCOUNT);
            lineCharge.setChargeName(SslCoreConstants.SterlingConstants.ITEM_PROMOTION_CHARGE_NAME);
            final List<Pair<Double, String>> itemPromotionList = itemPromotionMap.get(orderEntry);
            lineCharge.setChargePerLine(String.valueOf(itemPromotionList.get(0).getKey()));
            lineCharge.setReference(StringEscapeUtils.escapeXml(String.valueOf(itemPromotionList.get(0).getValue())));
            lineCharges.getLineCharge().add(lineCharge);
        }
    }

    private void populateItemDTO(final AbstractOrderEntryModel orderEntry, final OrderLineDTO orderLine) {
        final ApparelSizeVariantProductModel sizeProduct = (ApparelSizeVariantProductModel) orderEntry.getProduct();

        final SterlingItemDTO item = new SterlingItemDTO();
        item.setItemID(substring(SterlingLengthValidations.STERLING_ITEM_ID_LENGTH, SterlingLengthValidations.SUBSTRING_INDEX,
                sizeProduct.getCode()));
        item.setItemShortDesc(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_ITEM_DESC_LENGTH,
                SterlingLengthValidations.SUBSTRING_INDEX, sslProductService.getBaseProduct(sizeProduct).getName()));
        item.setUnitOfMeasure(SslCoreConstants.SterlingConstants.PIECES);

        orderLine.setItem(item);
    }

    private void populateOrderInstructions(final OrderModel order, final OrderLineDTO orderLineDTO) {
        if (StringUtils.isNotEmpty(order.getGiftRecipientName()) && StringUtils.isNotEmpty(order.getGiftSenderName())) {
            orderLineDTO.setGiftWrap(SslCoreConstants.SterlingConstants.Y);
            final InstructionsDTO instructions = new InstructionsDTO();
            InstructionDTO instruction = new InstructionDTO();
            instruction.setSequenceNo(SslCoreConstants.SterlingConstants.ONE);
            instruction.setInstructionText(SslCoreConstants.SterlingConstants.TO + ": "
                    + StringEscapeUtils.escapeXml(order.getGiftRecipientName()));
            instruction.setInstructionType(SslCoreConstants.SterlingConstants.TO);
            instructions.getOrderInstruction().add(instruction);

            instruction = new InstructionDTO();
            instruction.setSequenceNo(SslCoreConstants.SterlingConstants.TWO);
            instruction.setInstructionText(StringEscapeUtils.escapeXml(order.getGiftMessage()));
            instruction.setInstructionType(SslCoreConstants.SterlingConstants.GIFT_MSG);
            instructions.getOrderInstruction().add(instruction);

            instruction = new InstructionDTO();
            instruction.setSequenceNo(SslCoreConstants.SterlingConstants.THREE);
            instruction.setInstructionText(SslCoreConstants.SterlingConstants.FROM + ": "
                    + StringEscapeUtils.escapeXml(order.getGiftSenderName()));
            instruction.setInstructionType(SslCoreConstants.SterlingConstants.FROM);
            instructions.getOrderInstruction().add(instruction);

            orderLineDTO.setInstructions(instructions);
        }
    }

    private void populatePaymentMethod(final OrderModel order, final SSLSterlingCreateSalesOrderRequestDTO salesOrderDTO) {
        LOG.debug(ORDER_CODE + order.getCode() + " Populating Order Payment Details");
        final PaymentMethodsDTO paymentMethods = new PaymentMethodsDTO();
        final List<PaymentMethodDTO> paymentMethodList = paymentMethods.getPaymentMethod();

        for (final PaymentTransactionModel paymentTransaction : order.getPaymentTransactions()) {
            final PaymentInfoModel paymentInfo = paymentTransaction.getInfo();
            if (paymentInfo instanceof LoyaltyCardPaymentInfoModel) {
                setLoyaltyDetails(paymentTransaction, paymentMethodList);
            } else if (paymentInfo instanceof GiftCardPaymentInfoModel) {
                setGiftCardDetails(paymentTransaction, paymentMethodList);
            } else if (paymentInfo instanceof SSCreditsPaymentInfoModel) {
                setSSLWalletDetails(paymentTransaction, paymentMethodList);
            } else if (isCCAvenueTransaction(paymentInfo)) {
                setCCAvenueDetails(paymentTransaction, paymentMethodList);
            }
        }
        salesOrderDTO.setPaymentMethods(paymentMethods);
    }

    private void setSSLWalletDetails(final PaymentTransactionModel paymentTransaction, final List<PaymentMethodDTO> paymentMethodList) {

        if (PaymentTransactionType.CAPTURE.equals(paymentTransaction.getEntries().get(0).getType())) {

            final SSCreditsPaymentInfoModel walletPaymentInfo = (SSCreditsPaymentInfoModel) paymentTransaction.getInfo();
            for (final SSCreditsPaymentBreakupModel redemptionBreakupDetail : walletPaymentInfo.getRedemptionBreakupList()) {

                final PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();

                paymentMethodDTO.setPaymentReference1(redemptionBreakupDetail.getCardNumber());
                paymentMethodDTO.setPaymentReference2(walletPaymentInfo.getInvoiceNumber());
                paymentMethodDTO.setPaymentReference3(walletPaymentInfo.getTransactionId());
                paymentMethodDTO.setPaymentReference4(walletPaymentInfo.getBatchNo());
                paymentMethodDTO.setPaymentReference5(walletPaymentInfo.getApprovalCode());

                if (redemptionBreakupDetail.getExpiryDate() != null) {
                    paymentMethodDTO.setPaymentReference6(commonHelper.getFormattedDate(redemptionBreakupDetail.getExpiryDate(),
                            SslCoreConstants.SterlingConstants.STERLING_DATE_FORMAT));
                }

                final String redeemedBucket = redemptionBreakupDetail.getBucketType();
                paymentMethodDTO.setPaymentReference7(redeemedBucket);
                paymentMethodDTO.setPaymentReference8(walletPaymentInfo.getWalletNumber());
                // Transformation required as Sterling configuration is done this way.
                if ("CREDIT".equalsIgnoreCase(redeemedBucket)) {
                    paymentMethodDTO.setPaymentType(String.format(SslCoreConstants.SterlingConstants.WALLET_PREFIX, "Credits"));
                } else {
                    paymentMethodDTO.setPaymentType(String.format(SslCoreConstants.SterlingConstants.WALLET_PREFIX,
                            WordUtils.capitalizeFully(redeemedBucket)));
                }
                setWalletPaymentDetails(paymentMethodDTO, redemptionBreakupDetail.getAmountRedeemed());

                paymentMethodList.add(paymentMethodDTO);
            }
        }
    }

    private void setWalletPaymentDetails(final PaymentMethodDTO paymentMethodDTO, final String redeemedAmount) {

        final PaymentDetailsDTO paymentDetails = new PaymentDetailsDTO();
        paymentDetails.setChargeType(SslCoreConstants.SterlingConstants.CHARGE);
        paymentDetails.setProcessedAmount(redeemedAmount);
        paymentDetails.setRequestAmount(redeemedAmount);
        paymentMethodDTO.getPaymentDetails().add(paymentDetails);
    }

    private void setPaymentDetails(final PaymentTransactionModel paymentTransaction, final PaymentMethodDTO paymentMethodDTO) {
        final PaymentTransactionEntryModel paymentTransactionEntry = paymentTransaction.getEntries().get(0);

        final PaymentDetailsDTO paymentDetails = new PaymentDetailsDTO();
        paymentDetails.setProcessedAmount(String.valueOf(paymentTransactionEntry.getAmount()));
        paymentDetails.setRequestAmount(String.valueOf(paymentTransactionEntry.getAmount()));

        if (PaymentTransactionType.CAPTURE.equals(paymentTransactionEntry.getType())) {
            paymentDetails.setChargeType(SslCoreConstants.SterlingConstants.CHARGE);
        } else if (PaymentTransactionType.AUTHORIZATION.equals(paymentTransactionEntry.getType())
                || PaymentTransactionType.AUTHORIZATION_PENDING.equals(paymentTransactionEntry.getType())) {
            paymentDetails.setChargeType(SslCoreConstants.SterlingConstants.AUTHORIZATION);
            paymentDetails.setAuthorizationID(paymentTransaction.getRequestId());
        }

        if (PaymentTransactionType.AUTHORIZATION_PENDING.equals(paymentTransactionEntry.getType())) {
            paymentDetails.setProcessedAmount(String.valueOf(0));
        }

        paymentMethodDTO.getPaymentDetails().add(paymentDetails);
    }

    private void setGiftCardDetails(final PaymentTransactionModel paymentTransaction, final List<PaymentMethodDTO> paymentMethodList) {
        final PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
        final GiftCardPaymentInfoModel giftCardPayment = (GiftCardPaymentInfoModel) paymentTransaction.getInfo();
        paymentMethodDTO.setPaymentReference1(giftCardPayment.getGiftCardNumber());
        paymentMethodDTO.setPaymentReference2(giftCardPayment.getInvoiceNumber());
        paymentMethodDTO.setPaymentReference3(giftCardPayment.getTransactionId());
        paymentMethodDTO.setPaymentReference4(giftCardPayment.getBatchNo());
        paymentMethodDTO.setPaymentReference5(giftCardPayment.getApprovalCode());

        if (giftCardPayment.getCardExpiryDate() != null) {
            paymentMethodDTO.setPaymentReference6(commonHelper.getFormattedDate(giftCardPayment.getCardExpiryDate(),
                    SslCoreConstants.SterlingConstants.STERLING_DATE_FORMAT));
        }
        if (giftCardPayment.isEgv()) {
            paymentMethodDTO.setPaymentType(SslCoreConstants.SterlingConstants.EGV);
        } else {
            paymentMethodDTO.setPaymentType(SslCoreConstants.SterlingConstants.GIFT_CARD);
        }

        setPaymentDetails(paymentTransaction, paymentMethodDTO);

        paymentMethodList.add(paymentMethodDTO);
    }

    private void setLoyaltyDetails(final PaymentTransactionModel paymentTransaction, final List<PaymentMethodDTO> paymentMethodList) {
        final PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
        final LoyaltyCardPaymentInfoModel loyaltyPayment = (LoyaltyCardPaymentInfoModel) paymentTransaction.getInfo();

        paymentMethodDTO.setPaymentReference1(loyaltyPayment.getPrimaryCardNumber());
        final CustomerModel customer = (CustomerModel) paymentTransaction.getOrder().getUser();
        if (customer.getSslLoyaltyAddress() != null && StringUtils.isNotEmpty(customer.getSslLoyaltyAddress().getCellphone())) {
            paymentMethodDTO.setPaymentReference3(customer.getSslLoyaltyAddress().getCellphone());
        }
        paymentMethodDTO.setPaymentReference4(loyaltyPayment.getCertificateNumber());
        paymentMethodDTO.setPaymentType(SslCoreConstants.SterlingConstants.LOYALTY_CARD);
        setPaymentDetails(paymentTransaction, paymentMethodDTO);
        paymentMethodList.add(paymentMethodDTO);
    }

    private void setCCAvenueDetails(final PaymentTransactionModel paymentTransaction, final List<PaymentMethodDTO> paymentMethodList) {

        final PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();

        paymentMethodDTO.setCreditCardNo(paymentTransaction.getRequestId());

        paymentMethodDTO.setPaymentReference1(paymentTransaction.getRequestId());

        paymentMethodDTO.setPaymentReference2(paymentTransaction.getOrder().getCcAvenueOrderNumber());

        paymentMethodDTO.setPaymentReference3(paymentTransaction.getInfo().getMerchantIdUsed());

        if (paymentTransaction.getInfo() instanceof DebitCardPaymentInfoModel) {
            paymentMethodDTO.setPaymentType(SslCoreConstants.SterlingConstants.DEBIT_CARD);
            final DebitCardPaymentInfoModel debitCardInfo = (DebitCardPaymentInfoModel) paymentTransaction.getInfo();
            paymentMethodDTO.setPaymentReference4(debitCardInfo.getCcOwner());
        } else if (paymentTransaction.getInfo() instanceof CreditCardPaymentInfoModel) {

            final AbstractOrderModel order = paymentTransaction.getOrder();
            final AddressModel paymentAddress = order.getPaymentAddress() != null ? order.getPaymentAddress() : order.getDeliveryAddress();

            paymentMethodDTO.setFirstName(StringEscapeUtils.escapeXml(paymentAddress.getFirstname()));
            paymentMethodDTO.setLastName(StringEscapeUtils.escapeXml(paymentAddress.getLastname()));

            paymentMethodDTO.setPaymentType(SslCoreConstants.SterlingConstants.CREDIT_CARD);
            final CreditCardPaymentInfoModel creditCardInfo = (CreditCardPaymentInfoModel) paymentTransaction.getInfo();
            paymentMethodDTO.setPaymentReference4(creditCardInfo.getCcOwner());
        } else if (paymentTransaction.getInfo() instanceof NetBankingPaymentInfoModel) {
            paymentMethodDTO.setPaymentType(SslCoreConstants.SterlingConstants.NET_BANKING);
        } else if (paymentTransaction.getInfo() instanceof FreeChargePaymentInfoModel) {
            paymentMethodDTO.setPaymentType(SslCoreConstants.SterlingConstants.FREE_CHARGE);
            paymentMethodDTO.setPaymentReference4(paymentTransaction.getInfo().getTypeOfPayment());
        } else if (paymentTransaction.getInfo() instanceof CCAvenueWalletPaymentInfoModel) {
            paymentMethodDTO.setPaymentType(SslCoreConstants.SterlingConstants.CC_AVENUE_WALLET);
            paymentMethodDTO.setPaymentReference4(paymentTransaction.getInfo().getTypeOfPayment());
        }

        setPaymentDetails(paymentTransaction, paymentMethodDTO);

        paymentMethodList.add(paymentMethodDTO);
    }

    private boolean isCCAvenueTransaction(final PaymentInfoModel paymentInfo) {

        return (paymentInfo instanceof CreditCardPaymentInfoModel || paymentInfo instanceof NetBankingPaymentInfoModel
                || paymentInfo instanceof FreeChargePaymentInfoModel || paymentInfo instanceof CCAvenueWalletPaymentInfoModel);
    }

    private void populatePriceInfo(final SSLSterlingCreateSalesOrderRequestDTO salesOrderDTO) {

        final PriceInfoDTO priceInfo = new PriceInfoDTO();
        priceInfo.setCurrency("INR");
        salesOrderDTO.setPriceInfo(priceInfo);
    }

    private void populatePersonInfoShipToAddress(final OrderModel order, final SSLSterlingCreateSalesOrderRequestDTO salesOrderDTO) {
        if (!(SSLSterlingUtil.isPickupOrder(order))) {
            final AddressModel deliveryAddress = order.getDeliveryAddress();

            if (deliveryAddress != null) {
                populateAddress(deliveryAddress, salesOrderDTO);
            }
        }
    }

    private void populatePersonInfoBillToAddress(final OrderModel order, final SSLSterlingCreateSalesOrderRequestDTO salesOrderDTO) {
        LOG.debug(ORDER_CODE + order.getCode() + " Populating Billing Address");
        final AddressModel paymentAddress = order.getPaymentAddress() != null ? order.getPaymentAddress() : order.getDeliveryAddress();
        if (paymentAddress != null) {
            populateAddress(paymentAddress, salesOrderDTO);
        }
    }

    private void populateAddress(final AddressModel addressModel, final SSLSterlingCreateSalesOrderRequestDTO salesOrderDTO) {
        final SterlingAddressDTO addressDto = new SterlingAddressDTO();
        addressDto.setAddressID(addressModel.getPk().getLongValueAsString());

        final String line1 = addressModel.getStreetname();
        final String line2 = addressModel.getStreetnumber();
        int streetNameIndex = 0;
        int streetNumberIndex = 0;
		addressDto.seteMailID(salesOrderDTO.getCustomerEMailID());
        addressDto.setAddressLine1(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_ADDRESS_LENGTH, streetNameIndex++,
                line1));
        addressDto.setAddressLine2(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_ADDRESS_LENGTH, streetNameIndex++,
                line1));
        addressDto.setAddressLine3(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_ADDRESS_LENGTH, streetNameIndex++,
                line1));
        addressDto.setAddressLine4(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_ADDRESS_LENGTH, streetNameIndex,
                line1));

        addressDto.setAddressLine5(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_ADDRESS_LENGTH, streetNumberIndex++,
                line2));
        addressDto.setAddressLine6(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_ADDRESS_LENGTH, streetNumberIndex++,
                line2));
        addressDto.setAddressLine7(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_ADDRESS_LENGTH, streetNumberIndex++,
                line2));
        addressDto.setAddressLine8(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_ADDRESS_LENGTH, streetNumberIndex,
                line2));

        addressDto.setCity(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_CITY_STATE_ZIP_LENGTH,
                SterlingLengthValidations.SUBSTRING_INDEX, addressModel.getTown()));
        if (addressModel.getRegion() != null) {
            addressDto.setState(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_CITY_STATE_ZIP_LENGTH,
                    SterlingLengthValidations.SUBSTRING_INDEX, addressModel.getRegion().getName()));
        }
        if (addressModel.getCountry() != null) {
            addressDto.setCountry(addressModel.getCountry().getIsocode());
        }
        addressDto.setFirstName(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_NAME_LENGTH,
                SterlingLengthValidations.SUBSTRING_INDEX, addressModel.getFirstname()));
        addressDto.setLastName(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_NAME_LENGTH,
                SterlingLengthValidations.SUBSTRING_INDEX, addressModel.getLastname()));
        addressDto.setMobile(substring(SterlingLengthValidations.STERLING_MOBILE_LENGTH, SterlingLengthValidations.SUBSTRING_INDEX,
                addressModel.getPhone1()));
        if (addressModel.getTitle() != null) {
            addressDto.setTitle(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_TITLE_LENGTH,
                    SterlingLengthValidations.SUBSTRING_INDEX, addressModel.getTitle().getName()));
        }
        addressDto.setZipCode(substring(SterlingLengthValidations.STERLING_CITY_STATE_ZIP_LENGTH,
                SterlingLengthValidations.SUBSTRING_INDEX, addressModel.getPostalcode()));
        salesOrderDTO.setBillingAddress(addressDto);
    }

    private static String substringAndEscapeXMLCharacters(final int sterlingFieldLength, final int startIndex, final String hybrisFieldValue) {
        return StringEscapeUtils.escapeXml(substring(sterlingFieldLength, startIndex, hybrisFieldValue));
    }

    private static String substring(final int sterlingFieldLength, final int startIndex, final String hybrisFieldValue) {
        String subStr = StringUtils.EMPTY;
        if (StringUtils.isBlank(hybrisFieldValue)) {
            return subStr;
        }

        final int hybrisFieldLength = hybrisFieldValue.length();
        final int startPosition = sterlingFieldLength * startIndex;

        if (hybrisFieldLength > startPosition && hybrisFieldLength < sterlingFieldLength) {
            subStr = hybrisFieldValue;
        } else {
            final int endingPosition = sterlingFieldLength * (startIndex + 1);
            if (hybrisFieldLength > endingPosition) {
                subStr = hybrisFieldValue.substring(startPosition, endingPosition);
            } else if (hybrisFieldLength > startPosition) {
                subStr = hybrisFieldValue.substring(startPosition, hybrisFieldLength);
            }
        }
        return subStr;
    }

    private void populateExtensionDetails(final OrderModel order, final SSLSterlingCreateSalesOrderRequestDTO salesOrderDTO) {
        LOG.debug(ORDER_CODE + order.getCode() + " Populating Extension Details");
        final ExtensionDTO extn = new ExtensionDTO();
        extn.setExtnIP(order.getIP());

        final String fccCard = order.getFccCardNumber();
        final CustomerModel cust = (CustomerModel) order.getUser();
        final SslLoyaltyDetailModel loyalty = cust.getSslLoyaltyDetail();
        final String loyaltyNumber = !StringUtils.isBlank(fccCard) ? fccCard : StringUtils.EMPTY;
        final String loyaltyCard = loyalty != null && !StringUtils.isBlank(loyalty.getPrimaryCardNumber()) ? loyalty.getPrimaryCardNumber()
                : loyaltyNumber;

        extn.setCustLoyaltyNo(loyaltyCard);
        if (StringUtils.isNotEmpty(order.getPickupPersonNumber())) {
            extn.setPickUpPersonMobile(order.getPickupPersonNumber());
        }
        if (StringUtils.isNotEmpty(order.getPickupPersonName())) {
            extn.setPickUpPersonName(StringEscapeUtils.escapeXml(order.getPickupPersonName()));
        }
        extn.setWalletId(cust.getWalletNumber());
		extn.setRegisteredCust(cust.getType() != null ? "N" : "Y");
        salesOrderDTO.setExtn(extn);
    }

    private void populateBasicOrderDetails(final OrderModel order, final SSLSterlingCreateSalesOrderRequestDTO salesOrderDTO) {
        LOG.debug(ORDER_CODE + order.getCode() + " Populating Basic Order Details");
        final CustomerModel customer = (CustomerModel) order.getUser();

        salesOrderDTO.setBillToID(order.getUser().getPk().getLongValueAsString());
        salesOrderDTO.setOrderNo(order.getCode());
        salesOrderDTO.setDocumentType(SslCoreConstants.SterlingConstants.ORDER_TYPE);
        salesOrderDTO.setCustomerContactID(customer.getMobile());
        salesOrderDTO.setCustomerEMailID(StringEscapeUtils.escapeXml(order.getUser().getUid().contains("|")
				? StringUtils.substringAfter(order.getUser().getUid(), "|") : order.getUser().getUid()));
        salesOrderDTO.setEnterpriseCode(SslCoreConstants.SterlingConstants.SSL);
        if (order.getSalesApplication() != null) {
            salesOrderDTO.setEntryType(substringAndEscapeXMLCharacters(SterlingLengthValidations.STERLING_ENTRY_TYPE_LENGTH,
                    SterlingLengthValidations.SUBSTRING_INDEX, order.getSalesApplication().getCode()));
        }
        salesOrderDTO.setSourcingClassification(SslCoreConstants.SterlingConstants.SSL);
        salesOrderDTO.setOrderDate(commonHelper.getFormattedDate(order.getCreationtime(),
                SslCoreConstants.SterlingConstants.STERLING_DATE_FORMAT));
        salesOrderDTO.setSellerOrganizationCode(SslCoreConstants.SterlingConstants.SSL);
        salesOrderDTO.setAllocationRuleID(SslCoreConstants.SterlingConstants.ALLOCATION_RULE_ID);

    }

}
