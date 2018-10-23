package com.borngroup.ssl.core.services.email.impl;

import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.basecommerce.enums.PointOfServiceTypeEnum;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.model.media.MediaFolderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.util.Config;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.data.SSLReturnEntryDTO;
import com.borngroup.ssl.core.data.SSLWarehouseAddressDTO;
import com.borngroup.ssl.core.gst.services.SslGSTDocumentGenerationService;
import com.borngroup.ssl.core.services.impl.DefaultSSLReturnDocumentGeneratorService;
import com.borngroup.tax.enums.GSTDocumentType;

/**
 * This service class consists of methods used to generate attachments and send email with attachments
 *
 * @author nikhilbarar
 *
 */
public class SSLSendEmailWithAttachmentService {

    private static final Logger LOG = Logger.getLogger(SSLSendEmailWithAttachmentService.class);
    private List<EmailAttachmentModel> emailAttachments;

    private BusinessProcessService businessProcessService;
    private MediaService mediaService;
    private ModelService modelService;
    private DefaultSSLReturnDocumentGeneratorService defaultSSLReturnDocumentGeneratorService;

    @Resource(name = "sslGSTDocumentGenerationService")
    private SslGSTDocumentGenerationService sslGSTDocumentGenerationService;

    @Resource(name = "catalogVersionService")
    private CatalogVersionService catalogVersionService;

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    public DefaultSSLReturnDocumentGeneratorService getDefaultSSLReturnDocumentGeneratorService() {
        return defaultSSLReturnDocumentGeneratorService;
    }

    @Required
    public void setDefaultSSLReturnDocumentGeneratorService(
            final DefaultSSLReturnDocumentGeneratorService defaultSSLReturnDocumentGeneratorService) {
        this.defaultSSLReturnDocumentGeneratorService = defaultSSLReturnDocumentGeneratorService;
    }

    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    @Required
    public void setBusinessProcessService(final BusinessProcessService businesssProcessService) {
        this.businessProcessService = businesssProcessService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    @Required
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    public MediaService getMediaService() {
        return mediaService;
    }

    @Required
    public void setMediaService(final MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public List<EmailAttachmentModel> getEmailAttachments() {
        return emailAttachments;
    }

    public void setEmailAttachments(final List<EmailAttachmentModel> emailAttachments) {
        this.emailAttachments = emailAttachments;
    }

    private String getEmailAttachmentsMediaFolderName() {
        return "email-attachments";
    }

    private MediaFolderModel getEmailAttachmentsMediaFolder() {
        return getMediaService().getFolder(getEmailAttachmentsMediaFolderName());
    }

    private EmailAttachmentModel createEmailAttachment(final DataInputStream masterDataStream, final String filename,
            final String mimeType, final CatalogVersionModel activeCatalogVersion, final String emailAttachmentCode) {

        try {
            final EmailAttachmentModel exampleAttachment = new EmailAttachmentModel();
            exampleAttachment.setCode(emailAttachmentCode);
            exampleAttachment.setCatalogVersion(activeCatalogVersion);
            final EmailAttachmentModel attachmentSaved = flexibleSearchService.getModelByExample(exampleAttachment);
            if (null != attachmentSaved) {
                getMediaService()
                        .setStreamForMedia(attachmentSaved, masterDataStream, filename, mimeType, getEmailAttachmentsMediaFolder());
                return attachmentSaved;
            }
        } catch (ModelNotFoundException | AmbiguousIdentifierException e) {
            // Attachment Not Found
        }

        try {
            final EmailAttachmentModel attachment = getModelService().create(EmailAttachmentModel.class);
            attachment.setCode(emailAttachmentCode);
            attachment.setMime(mimeType);
            attachment.setRealFileName(filename);
            attachment.setCatalogVersion(activeCatalogVersion);

            getModelService().save(attachment);

            getMediaService().setStreamForMedia(attachment, masterDataStream, filename, mimeType, getEmailAttachmentsMediaFolder());
            return attachment;
        } catch (final ModelSavingException e) {
            LOG.error("Error saving attachment model", e);
        }

        return null;
    }

    private ProductModel getBaseProduct(final ProductModel product) {
        if (product instanceof VariantProductModel) {
            return getBaseProduct(((VariantProductModel) product).getBaseProduct());
        }
        return product;
    }

    public List<EmailAttachmentModel> generateCreditNote(final String returnConsignmentInvoiceNumber) {

        final List<EmailAttachmentModel> emailAttachments = new ArrayList<>();
        final String creditNotePrefix = Config.getString(SslCoreConstants.CREDIT_NOTE_PREFIX, "CreditNote-");

        final ResponseEntity<byte[]> documentByteArray = sslGSTDocumentGenerationService.generateGSTDocument(
                returnConsignmentInvoiceNumber, creditNotePrefix, GSTDocumentType.CREDIT_NOTE);

        if (null != documentByteArray) {
            final ByteArrayInputStream documentStream = new ByteArrayInputStream(documentByteArray.getBody());
            final DataInputStream documentInputStream = new DataInputStream(documentStream);

            final CatalogVersionModel activeCatalogVersion = catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME,
                    SslCoreConstants.ONLINE_CATALOG_VERSION);

            final EmailAttachmentModel emailAttachment = createEmailAttachment(documentInputStream, creditNotePrefix
                    + returnConsignmentInvoiceNumber + SslCoreConstants.PDF, SslCoreConstants.APPLICATION_PDF, activeCatalogVersion,
                    returnConsignmentInvoiceNumber);

            if (null != emailAttachment) {
                emailAttachments.add(emailAttachment);
            }
        }

        return emailAttachments;
    }

    public List<EmailAttachmentModel> generateInvoicePDF(final String invoiceNumber, final ResponseEntity<byte[]> documentByteArray) {

        final List<EmailAttachmentModel> emailAttachmentsList = new ArrayList<>();

        final ByteArrayInputStream documentStream = new ByteArrayInputStream(documentByteArray.getBody());
        final DataInputStream documentInputStream = new DataInputStream(documentStream);

        final CatalogVersionModel activeCatalogVersion = catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME,
                SslCoreConstants.ONLINE_CATALOG_VERSION);

        final EmailAttachmentModel emailAttachment = createEmailAttachment(documentInputStream, SslCoreConstants.BILLINGTEMPLATENAME
                + invoiceNumber + SslCoreConstants.PDF, SslCoreConstants.APPLICATION_PDF, activeCatalogVersion, invoiceNumber);

        if (null != emailAttachment) {
            emailAttachmentsList.add(emailAttachment);
        }
        return emailAttachmentsList;
    }

    /**
     * This method is used to generate a list of email attachments from return requests
     *
     * @param returnRequests
     *        : {@link List} of {@link ReturnRequestModel} used to create attachments for each return request
     *
     * @return {@link List} of {@link EmailAttachmentModel} generated
     *
     * @author nikhilbarar
     */
    public List<EmailAttachmentModel> generateAttachments(final List<ReturnRequestModel> returnRequests) {
        final List<EmailAttachmentModel> emailAttachments = new ArrayList<>();

        if (null != returnRequests && !returnRequests.isEmpty()) {
            try {
                for (final ReturnRequestModel returnRequest : returnRequests) {
                    String returnNumber = "";
                    if (null != returnRequest.getRMA()) {
                        returnNumber = returnRequest.getRMA();
                    }

                    String orderNumber = "";
                    if (null != returnRequest.getOrder().getAwbNumber()) {
                        orderNumber = returnRequest.getOrder().getAwbNumber();
                    } else if (null != returnRequest.getOrder().getCode()) {
                        orderNumber = returnRequest.getOrder().getCode();
                    }

                    Date deliveredDate = null;
                    DateTime deliveredDateTime = null;
                    for (final ConsignmentModel consignment : returnRequest.getOrder().getConsignments()) {
                        if (consignment.getReturnRequest() != returnRequest) {
                            continue;
                        }
                        for (final ConsignmentEntryModel consignmentEntry : consignment.getConsignmentEntries()) {
                            deliveredDate = consignmentEntry.getConsignment().getShippingDate();
                            if (null == deliveredDate) {
                                continue;
                            }
                            if (null == deliveredDateTime) {
                                deliveredDateTime = new DateTime(deliveredDate);
                            } else {
                                final DateTime newDateTime = new DateTime(deliveredDate);
                                if (newDateTime.isAfter(deliveredDateTime)) {
                                    deliveredDateTime = newDateTime;
                                }
                            }
                        }
                    }

                    String orderShippingDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
                    if (null != returnRequest.getOrder().getDate()) {
                        orderShippingDate = new SimpleDateFormat("ddMMyyyy").format(returnRequest.getOrder().getDate());
                    }
                    if (null != deliveredDateTime) {
                        orderShippingDate = new SimpleDateFormat("ddMMyyyy").format(deliveredDateTime.toDate());
                    }

                    final List<SSLReturnEntryDTO> returnEntryList = new ArrayList<>();

                    for (final ReturnEntryModel returnEntry : returnRequest.getReturnEntries()) {
                        final SSLReturnEntryDTO entry = new SSLReturnEntryDTO();
                        entry.setEntryName(getBaseProduct(returnEntry.getOrderEntry().getProduct()).getName());
                        entry.setEntrySKU(returnEntry.getOrderEntry().getProduct().getCode());
                        entry.setQuantity(returnEntry.getExpectedQuantity().toString());
                        returnEntryList.add(entry);
                    }

                    final WarehouseModel warehouse = returnRequest.getWarehouse();

                    String warehouseName = "";
                    AddressModel warehouseAddress = null;
                    if (warehouse.getPointsOfService().stream().filter((w1) -> PointOfServiceTypeEnum.ODC.equals(w1.getType())).findFirst()
                            .isPresent()) {
                        warehouseAddress = warehouse.getPointsOfService().stream()
                                .filter((w1) -> PointOfServiceTypeEnum.ODC.equals(w1.getType())).findFirst().get().getAddress();
                        warehouseName = warehouse.getPointsOfService().stream()
                                .filter((w1) -> PointOfServiceTypeEnum.ODC.equals(w1.getType())).findFirst().get().getDisplayName();
                    }

                    final SSLWarehouseAddressDTO warehouseDTO = new SSLWarehouseAddressDTO();
                    if (null != warehouseName) {
                        warehouseDTO.setName(warehouseName);
                    } else {
                        warehouseDTO.setName("");
                    }
                    if(warehouseAddress != null) {
                        if (null != warehouseAddress.getLine1()) {
                            warehouseDTO.setLine1(warehouseAddress.getLine1());
                        } else {
                            warehouseDTO.setLine1("");
                        }
                        if (null != warehouseAddress.getLine2()) {
                            warehouseDTO.setLine2(warehouseAddress.getLine2());
                        } else {
                            warehouseDTO.setLine2("");
                        }
                        if (null != warehouseAddress.getTown()) {
                            warehouseDTO.setCity(warehouseAddress.getTown());
                        } else {
                            warehouseDTO.setCity("");
                        }
                        if (null != warehouseAddress.getDistrict()) {
                            warehouseDTO.setDistrict(warehouseAddress.getDistrict());
                        } else {
                            warehouseDTO.setDistrict("");
                        }
                        if (null != warehouseAddress.getPostalcode()) {
                            warehouseDTO.setPinCode(warehouseAddress.getPostalcode());
                        } else {
                            warehouseDTO.setPinCode("");
                        }
                        if (null != warehouseAddress.getState()) {
                            warehouseDTO.setState(warehouseAddress.getState());
                        } else {
                            warehouseDTO.setState("");
                        }
                        if (null != warehouseAddress.getPhone1() && !warehouseAddress.getPhone1().isEmpty()) {
                            warehouseDTO.setContact(warehouseAddress.getPhone1());
                        } else if (null != warehouseAddress.getPhone2() && !warehouseAddress.getPhone2().isEmpty()) {
                            warehouseDTO.setContact(warehouseAddress.getPhone2());
                        } else {
                            warehouseDTO.setContact("020 6719 0400");
                        }
                        if (null != warehouseAddress.getCellphone()) {
                            warehouseDTO.setTollFree(warehouseAddress.getCellphone());
                        } else {
                            warehouseDTO.setTollFree("1-800-419-6648");
                        }
                    }
                    final CatalogVersionModel activeCatalogVersion = ((CMSSiteModel) (returnRequest.getOrder().getSite()))
                            .getDefaultCatalog().getActiveCatalogVersion();

                    final ResponseEntity<byte[]> documentByteArray = getDefaultSSLReturnDocumentGeneratorService().printReturnRequestForm(
                            returnNumber, orderNumber, orderShippingDate, returnEntryList, warehouseDTO);

                    final ByteArrayInputStream documentStream = new ByteArrayInputStream(documentByteArray.getBody());
                    final DataInputStream documentInputStream = new DataInputStream(documentStream);

                    final EmailAttachmentModel emailAttachment = createEmailAttachment(documentInputStream, returnNumber
                            + SslCoreConstants.PDF, SslCoreConstants.APPLICATION_PDF, activeCatalogVersion, returnNumber);

                    if (null != emailAttachment) {
                        emailAttachments.add(emailAttachment);
                    }
                }
            } catch (final NullPointerException e) {
                LOG.error("NullPointerException in creating attachment: " + e);
                LOG.error(ExceptionUtils.getStackTrace(e));
            }
        }

        return emailAttachments;
    }

    /**
     * This method is used to trigger a process {@link OrderProcessModel} with process definition sendReturnRequestEmailWithAttachments
     *
     * @param order
     *        : {@link OrderModel} required by the OrderProcessModel process
     * @param emailAttachments
     *        : {@link List} of {@link EmailAttachmentModel} generated using
     *        {@link SSLSendEmailWithAttachmentService#generateAttachments(List)}
     *
     * @author nikhilbarar
     */
    public void sendEmail(final OrderModel order, final List<EmailAttachmentModel> emailAttachments,
            final List<ReturnRequestModel> returnRequestList) {

        if (null == order) {
            return;
        }

        final Map<String, Object> emailAttachmentsMap = new HashMap<String, Object>();
        if (null != emailAttachments && !emailAttachments.isEmpty()) {
            emailAttachmentsMap.put(order.getCode(), emailAttachments);
        }

        final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
                "sendReturnRequestEmailWithAttachmentProcess-" + order.getCode() + "-" + System.currentTimeMillis(),
                "sendReturnRequestEmailWithAttachmentProcess", emailAttachmentsMap);

        // Inward zone email
        /*
         * final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
         * "sendReturnRequestInwardZoneEmailProcess-" + order.getCode() + "-" + System.currentTimeMillis(),
         * "sendReturnRequestInwardZoneEmailProcess");
         */

        // RM process refund email
        /*
         * final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
         * "sendReturnRequestRMRefundProcessEmailProcess-" + order.getCode() + "-" + System.currentTimeMillis(),
         * "sendReturnRequestRMRefundProcessEmailProcess");
         */

        // RM process refund email
        /*
         * final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
         * "sendReturnRequestRejectEmailProcess-" + order.getCode() + "-" + System.currentTimeMillis(),
         * "sendReturnRequestRejectEmailProcess");
         */

        // RM process refund partially accepted
        /*
         * final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
         * "sendReturnRequestPartiallyAcceptedEmailProcess-" + order.getCode() + "-" + System.currentTimeMillis(),
         * "sendReturnRequestPartiallyAcceptedEmailProcess");
         */
        final Set<String> returnReqSet = new HashSet<>(returnRequestList.size());
        for (final ReturnRequestModel returnReq : returnRequestList) {
            returnReqSet.add(returnReq.getCode());
        }
        orderProcessModel.setReturnReqNumber(returnReqSet);
        orderProcessModel.setOrder(order);
        getBusinessProcessService().startProcess(orderProcessModel);
    }

    /**
     * The method sends the email containing bank details Update Link to customer.
     *
     * @param order
     *        {@link OrderModel} required by the OrderProcessModel process
     * @param returnRequest
     *        {@link ReturnRequestModel}
     * @param isPriceDifferenceBankDetails
     *        the boolean
     */
    public void sendBankDetailsUpdateLinkEmail(final OrderModel order, final ReturnRequestModel returnRequest,
            final boolean isPriceDifferenceBankDetails) {

        if (null == order) {
            return;
        }
        returnRequest.setConvertedRefund(Boolean.valueOf(isPriceDifferenceBankDetails));
        getModelService().save(returnRequest);
        final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
                "sendBankDetailsUpdateLinkEmailProcess-" + order.getCode() + "-" + System.currentTimeMillis(),
                "sendBankDetailsUpdateLinkEmailProcess", new HashMap());
        final Set<String> returnReqSet = new HashSet<>(1);
        returnReqSet.add(returnRequest.getCode());
        orderProcessModel.setReturnReqNumber(returnReqSet);
        orderProcessModel.setOrder(order);
        getBusinessProcessService().startProcess(orderProcessModel);
    }
}
