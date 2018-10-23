/**
 *
 */
package com.borngroup.ssl.core.services.returns.impl;

import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.returns.OrderReturnRecordsHandlerException;
import de.hybris.platform.returns.impl.DefaultReturnService;
import de.hybris.platform.returns.model.OrderReturnRecordEntryModel;
import de.hybris.platform.returns.model.RefundEntryModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.ticket.enums.CsEventReason;
import de.hybris.platform.ticket.enums.CsInterventionType;
import de.hybris.platform.ticket.enums.CsTicketCategory;
import de.hybris.platform.ticket.enums.CsTicketPriority;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.events.model.CsTicketEventModel;
import de.hybris.platform.ticket.model.CsAgentGroupModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketBusinessService;
import de.hybris.platform.ticket.service.TicketService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.data.returns.ReturnableEntity;
import com.borngroup.ssl.core.enums.ReturnRequestPickup;
import com.borngroup.ssl.core.enums.ReturnRequestRefundMode;
import com.borngroup.ssl.core.enums.ReturnRequestType;
import com.borngroup.ssl.core.gst.services.GSTDocumentsService;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.ReturnRequestBankDetailsModel;
import com.borngroup.ssl.core.services.SSLOrderReturnRecordsHandler;
import com.borngroup.ssl.core.services.email.impl.SSLSendEmailWithAttachmentService;
import com.borngroup.ssl.core.services.returns.SSLReturnService;
import com.borngroup.ssl.core.sms.service.SslCustomerTouchPointService;
import com.borngroup.ssl.fulfilmentprocess.enums.ConsignmentEntryStatus;
import com.borngroup.ssl.fulfilmentprocess.model.InternalConsignmentEntryModel;

/**
 * @author atul2455
 *
 */
public class SSLReturnServiceImpl extends DefaultReturnService implements SSLReturnService {
    private static final Logger LOG = Logger.getLogger(SSLReturnServiceImpl.class);
    private static final String PAYBACK = "payback";

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    private TicketBusinessService ticketBusinessService;

    @Autowired
    private SSLSendEmailWithAttachmentService mailService;

    private SSLOrderReturnRecordsHandler modificationHandler;

    @Autowired
    private EnumerationService enumerationService;

    @Resource
    private SslCustomerTouchPointService sslCustomerTouchPointService;

    @Autowired
    private GSTDocumentsService gstDocumentsService;

    @Autowired
    private BusinessProcessService businessProcessService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    public TicketBusinessService getTicketBusinessService() {
        return ticketBusinessService;
    }

    public void setTicketBusinessService(final TicketBusinessService ticketBusinessService) {
        this.ticketBusinessService = ticketBusinessService;
    }

    public SSLOrderReturnRecordsHandler getModificationHandler() {
        return modificationHandler;
    }

    public void setModificationHandler(final SSLOrderReturnRecordsHandler modificationHandler) {
        this.modificationHandler = modificationHandler;
    }

    public SSLSendEmailWithAttachmentService getMailService() {
        return mailService;
    }

    public void setMailService(final SSLSendEmailWithAttachmentService mailService) {
        this.mailService = mailService;
    }

    @Override
    public ReturnRequestModel generateReturnRequestPerODC(final List<ReturnableEntity> returnableEntities, final OrderModel orderModel) {
        ReturnRequestModel returnRequest = null;
        if (CollectionUtils.isNotEmpty(returnableEntities)) {
            returnRequest = this.createReturnRequest(returnableEntities, orderModel);
            final String rmaNumber = createRMA(returnRequest);
            returnRequest.setRMA(rmaNumber);
        }
        return returnRequest;
    }

    private ReturnRequestModel createReturnRequest(final List<ReturnableEntity> returnableEntities, final OrderModel orderModel) {
        final ReturnRequestModel returnRequest = createReturnRequest(orderModel);
        returnRequest.setType(ReturnRequestType.RETURN);
        returnRequest.setStatus(ReturnStatus.NOT_PICKED);
        for (final ReturnableEntity returnableEntity : returnableEntities) {
            final RefundEntryModel refundEntry = returnableEntity.getRefundEntry();
            refundEntry.setReturnRequest(returnRequest);
            getModelService().save(refundEntry);
        }
        return returnRequest;
    }

    @Override
    public CsTicketModel createCSTicket(final ReturnRequestModel returnRequest, final AbstractOrderModel orderModel) {
        if (orderModel != null && returnRequest != null) {
            final CsTicketModel csticketmodel = getModelService().create(CsTicketModel.class);
            csticketmodel.setHeadline("Return: Order-" + returnRequest.getOrder().getCode() + " RMA-" + returnRequest.getRMA());
            csticketmodel.setCategory(CsTicketCategory.RETURN);

            csticketmodel.setPriority(CsTicketPriority.HIGH);
            csticketmodel.setOrder(orderModel);
            csticketmodel.setCustomer(orderModel.getUser());
            csticketmodel.setState(CsTicketState.OPEN);
            if (userService.getCurrentUser() instanceof EmployeeModel) {
                csticketmodel.setAssignedAgent((EmployeeModel) userService.getCurrentUser());
            }
            csticketmodel.setAssignedGroup((CsAgentGroupModel) userService.getUserGroupForUID("csgroup"));

            final CsCustomerEventModel csCustomerEventModel = getModelService().create(CsCustomerEventModel.class);
            csCustomerEventModel.setInterventionType(CsInterventionType.CALL);
            csCustomerEventModel.setReason(CsEventReason.RETURN);
            csCustomerEventModel.setSubject("Return Order Ticket");
            csCustomerEventModel.setText("Return Order");

            final CsTicketModel csTicket = ticketBusinessService.createTicket(csticketmodel, csCustomerEventModel);

            if (null != returnRequest.getRefundMode() && null != returnRequest.getPickup()) {
                final StringBuilder note = new StringBuilder();
                if (returnRequest.getRefundMode().equals(ReturnRequestRefundMode.BANKTRANSFER)) {
                    final String accountName = null == returnRequest.getBankDetails()
                            || StringUtils.isEmpty(returnRequest.getBankDetails().getAccountName()) ? ""
                                    : returnRequest.getBankDetails().getAccountName();
                    final String accountNumber = null == returnRequest.getBankDetails()
                            || StringUtils.isEmpty(returnRequest.getBankDetails().getAccountNumber()) ? ""
                                    : returnRequest.getBankDetails().getAccountNumber();
                    final String ifscCode = null == returnRequest.getBankDetails()
                            || StringUtils.isEmpty(returnRequest.getBankDetails().getIfscCode()) ? ""
                                    : returnRequest.getBankDetails().getIfscCode();
                    final String bankName = null == returnRequest.getBankDetails()
                            || StringUtils.isEmpty(returnRequest.getBankDetails().getBankName()) ? ""
                                    : returnRequest.getBankDetails().getBankName();
                    note.append("Account Name: " + accountName);
                    note.append("\t\tAccount Number: " + accountNumber + "\r");
                    note.append("Bank Name: " + bankName + "\t\t");
                    note.append("IFSC Code: " + ifscCode);
                }

                note.append("\rRefund Mode: " + returnRequest.getRefundMode().getCode());
                if (null != returnRequest.getPickup()) {
                    note.append("\t\tPickup: " + returnRequest.getPickup().getCode());
                }
                if (null != returnRequest.getInStoreLocation() && StringUtils.isNotEmpty(returnRequest.getInStoreLocation().getName())) {
                    note.append("\rStore Location : " + returnRequest.getInStoreLocation().getName());
                }
                if (null != returnRequest.getCreditNoteNumber() && StringUtils.isNotEmpty(returnRequest.getCreditNoteNumber())) {
                    note.append("\nCredit Note Number : " + returnRequest.getCreditNoteNumber());
                }
                if (null != returnRequest.getCreditNoteAmount() && StringUtils.isNotEmpty(returnRequest.getCreditNoteAmount())) {
                    note.append("\rCredit Note Amount : " + returnRequest.getCreditNoteAmount());
                }
                ticketBusinessService.addNoteToTicket(csTicket, CsInterventionType.CALL, CsEventReason.RETURN, note.toString(), null);
            }

            if (CollectionUtils.isNotEmpty(returnRequest.getReturnEntries())) {
                final StringBuilder products = new StringBuilder();
                for (final ReturnEntryModel returnEntry : returnRequest.getReturnEntries()) {
                    final ProductModel product = returnEntry.getOrderEntry().getProduct();
                    final ApparelSizeVariantProductModel sizeVariant = (ApparelSizeVariantProductModel) product;
                    final String size = (sizeVariant).getSize();
                    final String style = ((ApparelStyleVariantProductModel) (sizeVariant).getBaseProduct()).getStyle();
                    products.append("Product: " + getBaseProduct(product).getName() + "-" + style + "-" + size + "\tReturn Quantity: "
                            + returnEntry.getExpectedQuantity() + "\tReason: "
                            + enumerationService.getEnumerationName(((RefundEntryModel) returnEntry).getReason()) + "\r");
                }
                ticketBusinessService.addNoteToTicket(csTicket, CsInterventionType.CALL, CsEventReason.RETURN, products.toString(), null);
            }

            getModelService().save(csTicket);

            return csTicket;

        }
        return null;
    }

    private ProductModel getBaseProduct(final ProductModel product) {
        if (product instanceof VariantProductModel) {
            return getBaseProduct(((VariantProductModel) product).getBaseProduct());
        }
        return product;
    }

    @Override
    public void setBankDetails(final ReturnRequestModel request, final String accountName, final String accountNumber,
            final String bankName, final String ifscCode) {
        final ReturnRequestBankDetailsModel bankDetails = (ReturnRequestBankDetailsModel) getModelService()
                .create(ReturnRequestBankDetailsModel.class);
        bankDetails.setAccountName(accountName);
        bankDetails.setAccountNumber(accountNumber);
        bankDetails.setBankName(bankName);
        bankDetails.setIfscCode(ifscCode);
        getModelService().save(bankDetails);
        request.setBankDetails(bankDetails);
        getModelService().save(request);
    }

    @Override
    public void createModificationEntry(final ReturnRequestModel returnRequest, final CsTicketModel csTicket, final UserModel csAgent) {
        final OrderReturnRecordEntryModel orderReturnRecord;
        try {
            orderReturnRecord = getReturnRecordEntryForOrder(returnRequest, csAgent);
            if (null == orderReturnRecord) {
                return;
            }
            returnRequest.setOrderReturnRecordEntry(orderReturnRecord);
            getModelService().save(returnRequest);

            orderReturnRecord.setTicket(csTicket);
            getModelService().save(orderReturnRecord);
        } catch (final OrderReturnRecordsHandlerException e) {
            LOG.error("Failed to create Modification History " + e);
            LOG.error(ExceptionUtils.getStackTrace(e));
        }
    }

    private OrderReturnRecordEntryModel getReturnRecordEntryForOrder(final ReturnRequestModel returnRequest, final UserModel currentUser)
            throws OrderReturnRecordsHandlerException {
        final OrderModel order = returnRequest.getOrder();

        OrderReturnRecordEntryModel returnRecordEntryModel = null;
        if (null != returnRequest.getType() && returnRequest.getType().equals(ReturnRequestType.EXCHANGE)) {
            returnRecordEntryModel = getModificationHandler().createReplacementEntry(order, returnRequest.getReturnEntries(),
                    "Exchange request for order : " + order.getCode());
        } else {
            returnRecordEntryModel = getModificationHandler().createRefundEntry(order, getRefundService().getRefunds(returnRequest),
                    "Return request for order : " + order.getCode());
        }
        if (null == returnRecordEntryModel) {
            return null;
        }
        returnRecordEntryModel.setPrincipal(currentUser);
        final boolean isShippingChargeApplicable = areOrderEntriesDead(order.getEntries());
        double refundAmount = returnRecordEntryModel.getRefundAmount().doubleValue();
        if (isShippingChargeApplicable) {
            refundAmount += order.getDeliveryCost().doubleValue();
            refundAmount -= getPaybackAmount(order);
        }
        if (null != returnRequest.getType() && returnRequest.getType().equals(ReturnRequestType.EXCHANGE)) {
            refundAmount = 0;
        }
        returnRecordEntryModel.setRefundAmount(new Double(refundAmount));
        getModelService().save(returnRecordEntryModel);
        return returnRecordEntryModel;
    }

    private double getPaybackAmount(final OrderModel order) {

        double paybackAmount = 0d;
        final List<PaymentTransactionModel> paymentTransactions = order.getPaymentTransactions();
        if (null != paymentTransactions && !CollectionUtils.isEmpty(paymentTransactions)) {
            for (final PaymentTransactionModel paymentTransaction : paymentTransactions) {
                if (PAYBACK.equals(paymentTransaction.getPaymentProvider())) {
                    final List<PaymentTransactionEntryModel> paymentTransactionEntries = paymentTransaction.getEntries();
                    paybackAmount = calculatePaybackAmountFronTxns(paybackAmount, paymentTransactionEntries);
                }
            }
        }
        return paybackAmount;
    }

    private double calculatePaybackAmountFronTxns(double paybackAmount,
            final List<PaymentTransactionEntryModel> paymentTransactionEntries) {
        if (null != paymentTransactionEntries && !CollectionUtils.isEmpty(paymentTransactionEntries)) {
            for (final PaymentTransactionEntryModel paymentTransactionEntry : paymentTransactionEntries) {
                if (paymentTransactionEntry.getType() == PaymentTransactionType.REFUND_FOLLOW_ON) {
                    paybackAmount -= paymentTransactionEntry.getAmount().doubleValue();
                }
                if (paymentTransactionEntry.getType() == PaymentTransactionType.CAPTURE) {
                    paybackAmount += paymentTransactionEntry.getAmount().doubleValue();
                }
            }
        }
        return paybackAmount;
    }

    private static boolean areOrderEntriesDead(final List<AbstractOrderEntryModel> orderEntries) {
        boolean areOrderEntriesDead = false;

        for (final AbstractOrderEntryModel orderCancelEntry : orderEntries) {
            if (null != orderCancelEntry.getQuantity()) {
                if (orderCancelEntry.getQuantity().intValue() == 0) {
                    areOrderEntriesDead = true;
                } else {
                    areOrderEntriesDead = false;
                    break;
                }
            }
        }
        return areOrderEntriesDead;
    }

    @Override
    public void createConsignments(final ReturnRequestModel returnRequestModel) {
        final List<ReturnEntryModel> returnEntries = returnRequestModel.getReturnEntries();
        final OrderModel order = returnRequestModel.getOrder();
        if (CollectionUtils.isNotEmpty(returnEntries)) {
            final ConsignmentModel consignment = getModelService().create(ConsignmentModel.class);
            consignment.setOrder(order);
            consignment.setReturnRequest(returnRequestModel);

            final String processCode = String.format("%s-%s", returnRequestModel.getWarehouse().getCode(), returnRequestModel.getRMA());
            final String invoiceNumber = String.format("%s-%s-%s", returnRequestModel.getWarehouse().getCode(), returnRequestModel.getRMA(),
                    order.getCode());

            consignment.setProcessCode(processCode);// ODC + RMA
            consignment.setInvoiceNumber(invoiceNumber);// ODC + RMA + Order
                                                        // Code

            final Collection<PointOfServiceModel> pointsOfService = returnRequestModel.getWarehouse().getPointsOfService();
            final AddressModel deliveryAddress = pointsOfService.stream().findFirst().get().getAddress();
            consignment.setDeliveryAddress(deliveryAddress);

            consignment.setCode(processCode);

            AddressModel shippingAddress = returnRequestModel.getOrder().getDeliveryAddress();
            if (null == shippingAddress) {
                if (returnRequestModel.getOrder().getUser().getAddresses().stream().findFirst().isPresent()) {
                    shippingAddress = returnRequestModel.getOrder().getUser().getAddresses().stream().findFirst().get();
                }
            }
            consignment.setShippingAddress(shippingAddress);

            if (null != returnRequestModel && returnRequestModel.getPickup().equals(ReturnRequestPickup.INSTORE)
                    && returnRequestModel.getRefundMode().equals(ReturnRequestRefundMode.STORECREDITNOTE)) {
                consignment.setStatus(ConsignmentStatus.RETURN_ISSUED);
            } else if (null != returnRequestModel.getType() && returnRequestModel.getType().equals(ReturnRequestType.EXCHANGE)) {
                consignment.setStatus(ConsignmentStatus.EXCHANGE_INITIATED);
            } else {
                consignment.setStatus(ConsignmentStatus.RETURN_INITIATED);
            }

            consignment.setWarehouse(returnRequestModel.getWarehouse());

            for (final ReturnEntryModel returnEntry : returnEntries) {
                // final ProductModel product =
                // returnEntry.getOrderEntry().getProduct();
                final Long quantity = returnEntry.getExpectedQuantity();
                final List<ConsignmentEntryModel> originalConsignmentEntryList = returnEntry.getOrderEntry().getConsignmentEntries()
                        .stream().filter(r -> r.getConsignment().getReturnRequest() == null)
                        .filter(r -> r.getConsignment().getWarehouse().getInvoiceStoreId()
                                .equals(returnRequestModel.getWarehouse().getCode()))
                        .filter(r -> r.getOrderEntry().getProduct().equals(returnEntry.getOrderEntry().getProduct()))
                        .collect(Collectors.toList());
                final ConsignmentEntryModel consignmentEntry = getModelService().create(ConsignmentEntryModel.class);
                consignmentEntry.setConsignment(consignment);
                consignmentEntry.setQuantity(quantity);
                consignmentEntry.setOrderEntry(returnEntry.getOrderEntry());
                consignmentEntry.setInternalEntries(new ArrayList<InternalConsignmentEntryModel>());
                consignmentEntry.setStatus(ConsignmentEntryStatus.ALLOCATED);
                consignmentEntry.setReturnedQuantity(returnEntry.getExpectedQuantity());
                if (CollectionUtils.isNotEmpty(originalConsignmentEntryList)) {
                    final Double gstTaxData = originalConsignmentEntryList.stream().findFirst().get().getAppliedGSTTaxPercent();
                    consignmentEntry.setAppliedGSTTaxPercent(gstTaxData == null ? Double.valueOf(0) : gstTaxData);
                    consignmentEntry.setTaxComponents(originalConsignmentEntryList.stream().findFirst().get().getTaxComponents());
                    consignmentEntry.setTaxCode(originalConsignmentEntryList.stream().findFirst().get().getTaxCode());
                }
                getModelService().save(consignmentEntry);
            }
            getModelService().save(consignment);
        }
    }

    @Override
    public void sendNotifications(final List<ReturnRequestModel> returnRequestList) {
        try {
            final OrderModel orderModel = returnRequestList.get(0).getOrder();

            if (CollectionUtils.isNotEmpty(returnRequestList) && ReturnRequestPickup.INSTORE.getCode().equalsIgnoreCase(returnRequestList.get(0).getPickup().getCode())) {

                sslCustomerTouchPointService.sendReturnInStoreSMS(returnRequestList.get(0), SslCoreConstants.ORDER_RETURN_INSTORE_SMS_MSG);
                this.sendReturnInStoreEmail(returnRequestList.get(0), orderModel);
            } else {
                sslCustomerTouchPointService.sendReturnCreatedSMS(orderModel, SslCoreConstants.ORDER_RETURN_REQUEST_CREATED_SMS);

                final List<EmailAttachmentModel> emailAttachments = getMailService().generateAttachments(returnRequestList);
                getMailService().sendEmail(orderModel, emailAttachments, returnRequestList);
            }

        } catch (final Exception e) {
            LOG.error("Failed to send email " + e);
            LOG.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public void updateCsTicket(final ReturnRequestModel returnRequest, final double codAmount) {
        CsTicketModel csTicket = returnRequest.getCsTicket();
        if (null == csTicket) {
            csTicket = createCSTicket(returnRequest, returnRequest.getOrder());
        }

        final List<CsTicketEventModel> events = new ArrayList<CsTicketEventModel>(ticketService.getEventsForTicket(csTicket));
        Collections.sort(events, (event1, event2) -> event2.getCreationtime().compareTo(event1.getCreationtime()));
        CsCustomerEventModel csBankDetailsNote = null;
        for (final CsTicketEventModel csTicketEvent : events) {
            if ((csTicketEvent instanceof CsCustomerEventModel) && (csTicketEvent.getText().contains("Bank"))) {
                csBankDetailsNote = (CsCustomerEventModel) csTicketEvent;
                break;
            }
        }

        if (null != returnRequest.getRefundMode() && null != returnRequest.getPickup()) {
            final StringBuilder note = new StringBuilder();
            if (returnRequest.getRefundMode().equals(ReturnRequestRefundMode.BANKTRANSFER)
                    || returnRequest.getRefundMode().equals(ReturnRequestRefundMode.ORIGINALMODE)) {
                final String accountName = returnRequest.getBankDetails() == null
                        || StringUtils.isEmpty(returnRequest.getBankDetails().getAccountName()) ? ""
                                : returnRequest.getBankDetails().getAccountName();
                final String accountNumber = returnRequest.getBankDetails() == null
                        || StringUtils.isEmpty(returnRequest.getBankDetails().getAccountNumber()) ? ""
                                : returnRequest.getBankDetails().getAccountNumber();
                final String ifscCode = returnRequest.getBankDetails() == null
                        || StringUtils.isEmpty(returnRequest.getBankDetails().getIfscCode()) ? ""
                                : returnRequest.getBankDetails().getIfscCode();
                final String bankName = returnRequest.getBankDetails() == null
                        || StringUtils.isEmpty(returnRequest.getBankDetails().getBankName()) ? ""
                                : returnRequest.getBankDetails().getBankName();
                note.append("Account Name: " + accountName);
                note.append("\t\tAccount Number: " + accountNumber + "\r");
                note.append("Bank Name: " + bankName + "\t\t");
                note.append("IFSC Code: " + ifscCode);
            }

            note.append("\rRefund Mode: " + returnRequest.getRefundMode().getCode());
            if (null != returnRequest.getPickup()) {
                note.append("\t\tPickup: " + returnRequest.getPickup().getCode());
            }

            if (0 != codAmount && ReturnRequestRefundMode.ORIGINALMODE.equals(returnRequest.getRefundMode())) {
                note.append("\nCOD Amount: " + codAmount);
            } else if (ReturnRequestRefundMode.BANKTRANSFER.equals(returnRequest.getRefundMode())) {
                double refundAmount = returnRequest.getTotalRefundAmount();
                if (returnRequest.isRmaGeneratedByRTO()) {// In case of RTO RMA reduce COD amount from return amount since that is not
                                                          // received.
                    refundAmount -= codAmount;
                }
                note.append("\nTotal Refund Amount: " + refundAmount);
            }

            String prefix = "";
            if (null != csBankDetailsNote) {
                prefix = "Updated Details\r";
            }
            ticketBusinessService.addNoteToTicket(csTicket, CsInterventionType.CALL, CsEventReason.RETURN, prefix + note.toString(), null);

            getModelService().save(csTicket);
            getModelService().save(returnRequest);
        }
    }

    @Override
    public Integer getReturnDays(final ProductModel product) {
        if (product instanceof ApparelSizeVariantProductModel) {
            final ApparelSizeVariantProductModel sizeVariant = (ApparelSizeVariantProductModel) product;
            if (sizeVariant.getReturnDays() != null) {
                return sizeVariant.getReturnDays();
            } else {
                return getReturnDays(sizeVariant.getBaseProduct());
            }
        } else if (product instanceof ApparelStyleVariantProductModel) {
            final ApparelStyleVariantProductModel styleVariant = (ApparelStyleVariantProductModel) product;
            if (styleVariant.getReturnDays() != null) {
                return styleVariant.getReturnDays();
            } else {
                return getReturnDays(styleVariant.getBaseProduct());
            }
        } else {
            return product.getReturnDays();
        }
    }

    @Override
    public boolean sendBankDetailsUpdateLinkEmail(final ReturnRequestModel returnRequest, final boolean converted) {
        try {
            final OrderModel orderModel = returnRequest.getOrder();
            returnRequest.setBankDetailsLinkID(getUniqueBankDetailsUpdateID());
            getModelService().save(returnRequest);
            getMailService().sendBankDetailsUpdateLinkEmail(orderModel, returnRequest, converted);
            return true;
        } catch (final Exception e) {
            LOG.error("Failed to send email " + e);
            LOG.error(ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    /**
     * Get unqiue Link Update ID.
     *
     * @return link update id
     */
    private String getUniqueBankDetailsUpdateID() {
        final Random rnd = new Random();
        final int uid = 10000000 + rnd.nextInt(90000000);
        return String.valueOf(uid);
    }

    public GSTDocumentsService getGstDocumentsService() {
        return gstDocumentsService;
    }

    public void setGstDocumentsService(final GSTDocumentsService gstDocumentsService) {
        this.gstDocumentsService = gstDocumentsService;
    }

    /**
    	 *
    	 */
    private void sendReturnInStoreEmail(final ReturnRequestModel returnRequest, final OrderModel order) {
        LOG.info(" sendReturnInStoreEmail Started ");
        final Set<String> returnReqSet = new HashSet<>();
        final OrderProcessModel orderProcessModel = (OrderProcessModel) businessProcessService.createProcess(
                "sendReturnInStoreEmailProcess-" + order.getCode() + "-" + System.currentTimeMillis(), "sendReturnInStoreEmailProcess");
        LOG.debug("OrderProcessModel for  Return In Store email" + orderProcessModel);
        returnReqSet.add(returnRequest.getCode());
        orderProcessModel.setReturnReqNumber(returnReqSet);
        orderProcessModel.setOrder(order);
        orderProcessModel.setStoreLocation(returnRequest.getInStoreLocation());
        orderProcessModel.setCreditNoteNumber(returnRequest.getCreditNoteNumber());
        orderProcessModel.setCreditNoteAmount(returnRequest.getCreditNoteAmount());
        businessProcessService.startProcess(orderProcessModel);
    }

}
