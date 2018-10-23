/**
 *
 */
package com.borngroup.ssl.core.gst.services.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.price.DiscountModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.util.Config;
import de.hybris.platform.variants.model.VariantProductModel;
import de.hybris.platform.voucher.model.VoucherModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.crm.dao.SSLOrderDao;
import com.borngroup.ssl.core.crm.service.impl.SSLDefaultOrderService;
import com.borngroup.ssl.core.enums.GstTaxTypes;
import com.borngroup.ssl.core.gst.services.CoreGSTTaxCalculationService;
import com.borngroup.ssl.core.gst.services.GSTDocumentsService;
import com.borngroup.ssl.core.gst.services.SslGSTDocumentGenerationService;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.services.gst.SslGSTService;
import com.borngroup.ssl.core.util.FCCGiftUtil;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;
import com.borngroup.ssl.core.util.GSTUtil;
import com.borngroup.ssl.core.util.SslUtilityForAddingDigitalSignature;
import com.borngroup.ssl.core.util.SslUtilityForPDF;
import com.borngroup.ssl.fulfilmentprocess.enums.ConsignmentEntryStatus;
import com.borngroup.ssl.fulfilmentprocess.model.ConsignmentTenderEntriesModel;
import com.borngroup.ssl.fulfilmentprocess.model.SSLAdvanceReceiptModel;
import com.borngroup.ssl.fulfilmentprocess.model.SSLRefundVoucherModel;
import com.borngroup.ssl.fulfilmentprocess.model.SalesDataEntryModel;
import com.borngroup.ssl.fulfilmentprocess.order.data.gst.SslGSTConsignmentDetailsData;
import com.borngroup.ssl.fulfilmentprocess.order.data.gst.SslGSTDocumentData;
import com.borngroup.ssl.loyalty.model.SslLoyaltyFeeBreakupModel;
import com.borngroup.tax.enums.GSTDocumentType;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.exceptions.RuntimeWorkerException;
import com.itextpdf.tool.xml.parser.XMLParser;

/**
 * @author manikmalhotra
 *
 */
public class SslGSTDocumentGenerationServiceImpl implements SslGSTDocumentGenerationService {

    private static final int TWO = 2;

    private static final double ZERO = 0.0d;

    private static final float FONT_SIZE = 9.0f;

    private static final String YES = "yes";

    class ParagraphBorder extends PdfPageEventHelper {

        /** active. **/
        private boolean active = false;

        public void setActive(final boolean active) {
            this.active = active;
        }

        private static final float OFFSET = 5;

        private float startPosition;

        @Override
        public void onParagraph(final PdfWriter writer, final Document document, final float paragraphPosition) {
            this.startPosition = paragraphPosition;
        }

        @Override
        public void onParagraphEnd(final PdfWriter writer, final Document document, final float paragraphPosition) {
            if (this.active) {
                final PdfContentByte contentByte = writer.getDirectContentUnder();
                contentByte.rectangle(document.left(), paragraphPosition - OFFSET, document.right() - document.left(), this.startPosition
                        - paragraphPosition);
                contentByte.stroke();
            }
        }
    }

    private static final Logger LOG = Logger.getLogger(SslGSTDocumentGenerationService.class);

    private static final String INCLUDE_SIGNATURE = "invoice.include.digital.signature";
    private static final String PDFEXTENSION = ".pdf";
    private static final float MARGIN = 15.0f;
    private static final float MARGIN_BOTTOM = 75.0f;

    private final SslUtilityForAddingDigitalSignature sslUtilityForDigitalSignature = SslUtilityForAddingDigitalSignature.getInstance();
    private static final String VMPATH = "/templates/";
    private static final String VMREGEX = "%s%s%s";
    private static final String VMEXTENSION = ".vm";

    private static final String STATE_CODE = "stateCode";
    private static final String GST_DOCUMENT_STATE_CODE = "gst.document.stateCode";
    public static final String SDI_TENDER_TYPE_COD = "sdi.tender.code.cod";
    private static final String BARCODE = "barCode";
    private static final String TRACE = " ,trace> ";
    private static final String ERROR_GENERATING = "Error Generating ";

    private final SslUtilityForPDF sslUtilityForPDF = SslUtilityForPDF.getInstance();

    @Resource(name = "sslGSTService")
    public SslGSTService sslGSTService;

    @Resource(name = "sslDefaultOrderService")
    private SSLDefaultOrderService sslDefaultOrderService;

    @Autowired
    private FlexibleSearchService flexibleSearchService;
    @Autowired
    private ModelService modelService;

    @Autowired
    private ProductService productService;

    @Resource(name = "gstDocumentsService")
    private GSTDocumentsService gstDocumentsService;

    final SimpleDateFormat currentDate = new SimpleDateFormat("dd MMM yyyy");

    @Resource(name = "catalogVersionService")
    private CatalogVersionService catalogVersionService;

    @Resource(name = "sslDefaultOrderDao")
    private SSLOrderDao sslDefaultOrderDao;

    @Resource(name = "coreGSTTaxCalculationService")
    private CoreGSTTaxCalculationService coreGSTTaxCalculationService;

    @Override
    public ResponseEntity<byte[]> generateGSTDocument(final String documentNumber, final String documentPrefix,
            final GSTDocumentType documentType) {

        List<StringReader> stringReaderList = null;
        try {
            String pdfFileName = String.format(VMREGEX, documentPrefix, documentNumber, PDFEXTENSION);
            final boolean includeSignature = YES.equalsIgnoreCase(Config.getString(INCLUDE_SIGNATURE, StringUtils.EMPTY));
            if (includeSignature) {
                pdfFileName = String.format(VMREGEX, documentPrefix + "_temp", documentNumber, PDFEXTENSION);
            }
            final String targetPdfFileName = String.format(VMREGEX, documentPrefix, documentNumber, PDFEXTENSION);
            final Document document = new Document(PageSize.A4.rotate());
            final FileOutputStream fileOutputStream = new FileOutputStream(pdfFileName);
            final PdfWriter pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
            document.setMargins(MARGIN, MARGIN, MARGIN, MARGIN_BOTTOM);
            final ParagraphBorder border = new ParagraphBorder();
            pdfWriter.setPageEvent(border);
            document.open();

            final XMLWorker worker = new XMLWorker(sslUtilityForPDF.getPipeline(document, pdfWriter, FONT_SIZE), true);
            final XMLParser parser = new XMLParser(worker);

            if (GSTDocumentType.CREDIT_NOTE.equals(documentType)) {
                stringReaderList = getCreditNotePdfData(documentNumber, documentType);
            } else if (GSTDocumentType.ADVANCE_RECEIPT.equals(documentType) || GSTDocumentType.ADVANCE_RECEIPT_BOS.equals(documentType)) {
                stringReaderList = populateReceiptVoucherPdfData(documentNumber, documentType);
            } else if (GSTDocumentType.REFUND_VOUCHER.equals(documentType) || GSTDocumentType.REFUND_VOUCHER_BOS.equals(documentType)) {
                stringReaderList = populateRefundVoucherPdfData(documentNumber, documentType);
            }

            if (stringReaderList != null) {
                for (final StringReader reader : stringReaderList) {
                    document.newPage();
                    parser.parse(reader);
                }
            }
            document.close();
            if (includeSignature) {
                sslUtilityForDigitalSignature.addDigitalSignature(pdfFileName, targetPdfFileName);
            }

            return sslUtilityForPDF.getPdfFileResponse(targetPdfFileName, targetPdfFileName);
        } catch (final RuntimeWorkerException rwe) {
            if (stringReaderList != null) {
                for (final StringReader stringReader : stringReaderList) {
                    LOG.error(stringReader.toString());
                }
            }
            LOG.error("HTML Parser error with incorrect content.  Please see log above for HTML", rwe);
        } catch (final Exception ex) {
            LOG.error("Error Generating Invoice:" + ex.getMessage(), ex);
        }

        return null;

    }

    private void populateBOSDocumentData(final ConsignmentModel source, final SslGSTDocumentData target, final GSTDocumentType documentType,final boolean isFCCInvoice) {
        if (null != source) {
            setPointofServiceDetails(source.getWarehouse().getPointsOfService().iterator().next(), target);
            target.setInternalReferenceNumber(source.getInvoiceNumber());
            if (isFCCInvoice) {
                target.setDocumentNumber(source.getGstInvoiceNumber());
            } else {
                target.setDocumentNumber(source.getBosNumber());
            }
            target.setDocumentIssueDate(currentDate.format(source.getInvoiceTime()));
            target.setCustomerDetails(source.getDeliveryAddress());

            target.setTotalDeliveryCost(String.valueOf(getRoundedAmount(source.getBosDeliveryCost() == null ? ZERO : source
                    .getBosDeliveryCost().doubleValue())));

            BigDecimal bosAmount = BigDecimal.ZERO;
            BigDecimal invoiceAmount = BigDecimal.ZERO;
            if (source.getBosAmount() != null) {
                bosAmount = source.getBosAmount();
            }
            if (source.getBosVoucherDiscountAmount() != null) {
                bosAmount = bosAmount.add(source.getBosVoucherDiscountAmount());
            }
            if (source.getInvoiceAmount() != null) {
                invoiceAmount = source.getInvoiceAmount();
            }
            if (source.getVoucherDiscountAmount() != null) {
                invoiceAmount = invoiceAmount.add(source.getVoucherDiscountAmount());
            }
            if (isFCCInvoice) {
                target.setSubTotal(invoiceAmount.setScale(TWO, RoundingMode.HALF_UP).doubleValue());
            } else {
                target.setSubTotal(bosAmount.setScale(TWO, RoundingMode.HALF_UP).doubleValue());
            }
            double grandTotal;
            if (!isFCCInvoice && source.getBosAmount() != null) {
                grandTotal = source.getBosAmount().setScale(TWO, RoundingMode.HALF_UP).doubleValue();
                target.setGrandTotal(grandTotal);
            } else if (isFCCInvoice && source.getInvoiceAmount() != null) {
                grandTotal = source.getInvoiceAmount().setScale(TWO, RoundingMode.HALF_UP).doubleValue();
                target.setGrandTotal(grandTotal);
            }

            final List<SslGSTConsignmentDetailsData> consignmentDetailsData = new ArrayList<>();
            populateDocumentConsignmentDetails(source, consignmentDetailsData, documentType, isFCCInvoice);
            target.setEntryDetails(consignmentDetailsData);
            generateTenderDetails(source, target);
            calculateVoucherDiscount(source, target, isFCCInvoice);
            addVoucherName(source, target);
            setOrderDetails(source, target);
        }

    }

    private SslGSTDocumentData populateCreditNoteDocumentData(final ConsignmentModel source, final Boolean isBos,
            final SalesDataEntryModel salesDataEntryModel, final boolean ismultipleConsignmentDoc) {

        final List<SalesDataEntryModel> creditNoteItems = flexibleSearchService.getModelsByExample(salesDataEntryModel);

        SslGSTDocumentData target = null;

        if (CollectionUtils.isEmpty(creditNoteItems)) {
            return null;
        }

        String consignmentSearchCondition = ConsignmentModel.INVOICENUMBER;

        if (isBos.booleanValue()) {
            consignmentSearchCondition = ConsignmentModel.BOSNUMBER;
        }
        final String shippingSku = Config.getParameter("ssl.shippingFee.sku");
        final List<SalesDataEntryModel> filteredCreditNoteItems = creditNoteItems.stream()
                .filter(cn -> !shippingSku.equals(cn.getProductCode())).collect(Collectors.toList());

        if (filteredCreditNoteItems.isEmpty()) {
            return target;
        }

        target = populateCreditNoteItems(filteredCreditNoteItems, consignmentSearchCondition);

        setPointofServiceDetails(source.getWarehouse().getPointsOfService().iterator().next(), target);
        setOrderDetails(source, target);
        target.setCustomerDetails(source.getShippingAddress());

        final BigDecimal voucherDiscount = isBos.booleanValue() ? source.getBosVoucherDiscountAmount() : source.getVoucherDiscountAmount();

        if (voucherDiscount != null && voucherDiscount.doubleValue() > ZERO) {
            double applicableVoucher = voucherDiscount.doubleValue();
            if (ismultipleConsignmentDoc) {
                double documentsubTotal;
                if (isBos.booleanValue()) {
                    documentsubTotal = source.getBosAmount() == null ? ZERO : source.getBosAmount().doubleValue();
                } else {
                    documentsubTotal = source.getInvoiceAmount() == null ? ZERO : source.getInvoiceAmount().doubleValue();
                }
                applicableVoucher = (target.getSubTotal() * voucherDiscount.doubleValue())
                        / (voucherDiscount.doubleValue() + documentsubTotal);
            }

            target.setVoucherApplied(true);
            target.setVoucherDiscount(getRoundedAmount(applicableVoucher));

        }
        target.setGrandTotal(getRoundedAmount(target.getSubTotal() - target.getVoucherDiscount()));
        setOrderDetails(source, target);
        return target;
    }

    /**
     * @param source
     * @param target
     */
    private void setOrderDetails(final ConsignmentModel source, final SslGSTDocumentData target) {
        target.setOrderDate(currentDate.format(source.getOrder().getCreationtime()));
        target.setOrderNumber(source.getOrder().getCode());
        target.setRecipientDetails(source.getShippingAddress());
    }

    private SslGSTDocumentData populateCreditNoteItems(final List<SalesDataEntryModel> creditNoteItems,
            final String consignmentSearchCondition) {

        final SslGSTDocumentData target = new SslGSTDocumentData();
        double totalAmountRefunded = ZERO;

        target.setDocumentNumber(creditNoteItems.get(0).getDocumentNumber());
        target.setDocumentIssueDate(currentDate.format(creditNoteItems.get(0).getCreationtime()));
        target.setInternalReferenceNumber(creditNoteItems.get(0).getGstReferenceNumber());
        final ConsignmentModel consignmentModel = getConsignment(consignmentSearchCondition, creditNoteItems.get(0).getInvoiceNumber());
        target.setInternalReferenceDocumentIssueDate(currentDate.format(consignmentModel.getInvoiceTime()));

        target.setEntryDetails(new ArrayList<SslGSTConsignmentDetailsData>());
        for (final SalesDataEntryModel salesDataEntry : creditNoteItems) {
            final SslGSTConsignmentDetailsData consignmentDetailsData = new SslGSTConsignmentDetailsData();
            final String productSku = salesDataEntry.getProductCode();

            if (Config.getString(SslCoreConstants.FCC_PRODUCT_CODE, "999").equals(productSku)) {
                final List<SslGSTConsignmentDetailsData> consignmentDetails = new ArrayList<>();
                this.populateFCCProductDetails(consignmentModel.getConsignmentEntries().iterator().next(), consignmentDetails);
                for (final SslGSTConsignmentDetailsData consignmentDetail : consignmentDetails) {
                    totalAmountRefunded += consignmentDetail.getTotalValueOfGoods();
                }
                target.getEntryDetails().addAll(consignmentDetails);
            } else {
                populateProductDetails(productSku, consignmentDetailsData);

                final double basePrice = salesDataEntry.getBasePrice();
                final double itemDiscount = salesDataEntry.getItemDiscount();
                final double pieceDiscount = salesDataEntry.getPieceDiscount();
                final double priceAfterDiscount = basePrice - pieceDiscount - itemDiscount;

                final long quantity = salesDataEntry.getQuantity();
                consignmentDetailsData.setQuantity(quantity);
                consignmentDetailsData.setDeliveryCharges(getRoundedAmount(salesDataEntry.getShippingCost() * quantity));
                final double totalPayable = (priceAfterDiscount * quantity) + consignmentDetailsData.getDeliveryCharges();

                totalAmountRefunded += totalPayable;

                consignmentDetailsData.setTotalPayable(getRoundedAmount(totalPayable));

                final String billedConsignmentInvoiceNumber = salesDataEntry.getInvoiceNumber();
                final ConsignmentModel billedConsignment = getConsignment(consignmentSearchCondition, billedConsignmentInvoiceNumber);

                final ConsignmentEntryModel originalConsignmentEntry = billedConsignment.getConsignmentEntries().stream()
                        .filter(ce -> ce.getOrderEntry().getProduct().getCode().equals(productSku)).findAny().get();

                final Map<GstTaxTypes, String> taxComponents = originalConsignmentEntry.getTaxComponents();
                if (GSTUtil.isZeroTax(taxComponents) > 0) {
                    final double appliedGSTTax = originalConsignmentEntry.getAppliedGSTTaxPercent() == null ? ZERO
                            : originalConsignmentEntry.getAppliedGSTTaxPercent().doubleValue();
                    final double totalTaxableValue = (totalPayable * 100) / (100 + appliedGSTTax);
                    consignmentDetailsData.setTaxableValueOfGoods(getRoundedAmount(totalTaxableValue));
                    consignmentDetailsData.setApplicableTaxes(gstDocumentsService
                            .populateGSTTaxComponents(taxComponents, totalTaxableValue));
                    consignmentDetailsData.setTotalTax(getRoundedAmount((totalTaxableValue * appliedGSTTax) / 100));
                }
                target.getEntryDetails().add(consignmentDetailsData);
            }

        }

        target.setSubTotal(getRoundedAmount(totalAmountRefunded));
        return target;
    }

    private void setPointofServiceDetails(final PointOfServiceModel pos, final SslGSTDocumentData target) {
        if (pos != null) {
            final PointOfServiceData posData = new PointOfServiceData();
            final AddressData posAddress = new AddressData();
            if (pos.getAddress() != null) {
                posAddress.setLine1(pos.getAddress().getStreetname());
                posAddress.setLine2(pos.getAddress().getStreetnumber());
                posAddress.setPostalCode(pos.getAddress().getPostalcode());
                posAddress.setTown(pos.getAddress().getTown());
                posAddress.setState(pos.getAddress().getRegion().getName());

                posAddress.setStateCode(pos.getAddress().getRegion().getStateCode());

                if (pos.getAddress().getCountry() != null) {
                    final CountryData countryData = new CountryData();
                    countryData.setName(pos.getAddress().getCountry().getName());
                    posAddress.setCountry(countryData);
                }
            }

            posData.setName(pos.getDisplayName());
            posData.setAddress(posAddress);
            posData.setDisclaimer(pos.getPosDisclaimer());
            posData.setReturnNote(pos.getReturnNote());
            posData.setGtin(pos.getGtinNumber() == null ? "" : pos.getGtinNumber());
            posData.setCin(pos.getCin() == null ? "" : pos.getCin());
            target.setDeliveryPointOfService(posData);

        }
    }

    public ConsignmentModel getConsignment(final String whereCondition, final String value) {
        final FlexibleSearchQuery query = new FlexibleSearchQueryBuilder().from(ConsignmentModel._TYPECODE, "c").select("c", ItemModel.PK)
                .whereEquals("c", whereCondition, value).build();
        final SearchResult<ConsignmentModel> result = flexibleSearchService.search(query);
        if (result.getResult().isEmpty()) {
            return null;
        }
        return result.getResult().get(0);
    }

    private void generateTenderDetails(final ConsignmentModel consignment, final SslGSTDocumentData data) {
        data.setCod(false);
        double prepaidAmount = ZERO;
        final Map<String, BigDecimal> tenderDetails = new HashMap<>();
        double codTenderAmount = ZERO;
        final Set<String> codTenderTypes = new HashSet<>();
        codTenderTypes.add(getSDITenderTypeCOD());
        final FlexibleSearchQuery tenderQuery = new FlexibleSearchQueryBuilder().from(ConsignmentTenderEntriesModel._TYPECODE, "c")
                .select("c", ItemModel.PK).whereEquals("c", ConsignmentTenderEntriesModel.CONSIGNMENTCODE, consignment.getCode())
                .whereIn("c", ConsignmentTenderEntriesModel.TENDERTYPE, codTenderTypes).build();
        final List<ConsignmentTenderEntriesModel> result = flexibleSearchService.<ConsignmentTenderEntriesModel> search(tenderQuery)
                .getResult();
        if (!result.isEmpty()) {
            final Double tenderAmount = result.get(0).getTenderAmount();
            tenderDetails.put("Cash on Delivery", BigDecimal.valueOf(tenderAmount.doubleValue()));
            codTenderAmount = tenderAmount.doubleValue();

            final double invoiceAmount = consignment.getInvoiceAmount().doubleValue();
            final double bosAmount = consignment.getBosAmount().doubleValue();
         // 800 + 700 - 500
            final double amountPaidOnline = invoiceAmount + bosAmount - codTenderAmount;

            if (amountPaidOnline >= invoiceAmount) {
                prepaidAmount = amountPaidOnline - invoiceAmount;
                codTenderAmount = bosAmount - prepaidAmount;
            } else {
                final double codAmountAdjustedInInvoice = invoiceAmount - amountPaidOnline;
                codTenderAmount = codTenderAmount - codAmountAdjustedInInvoice;
            }
        }
        if (codTenderAmount > ZERO) {
            data.setCod(true);
            data.setCodAmount(getRoundedAmount(codTenderAmount));
        }

        if (prepaidAmount > ZERO) {
            data.setAmountPaid(true);
            data.setPrepaidAmount(getRoundedAmount(prepaidAmount));
        } else {
            data.setAmountPaid(false);
        }

    }

    private void calculateVoucherDiscount(final ConsignmentModel consignment, final SslGSTDocumentData data,final boolean isFCCInvoice) {
        double voucherDiscount = ZERO;

        if (!isFCCInvoice && consignment.getBosVoucherDiscountAmount() != null) {
            voucherDiscount = consignment.getBosVoucherDiscountAmount().setScale(TWO, RoundingMode.HALF_UP).doubleValue();
        }else if(isFCCInvoice && consignment.getVoucherDiscountAmount()!=null){
        	voucherDiscount = consignment.getVoucherDiscountAmount().setScale(TWO, RoundingMode.HALF_UP).doubleValue();
        }
        if (voucherDiscount > ZERO) {
            data.setVoucherDiscount(voucherDiscount);
            data.setVoucherApplied(true);
        }

    }

    private void addVoucherName(final ConsignmentModel consignment, final SslGSTDocumentData data) {
        for (final DiscountModel disc : consignment.getOrder().getDiscounts()) {
            if (disc instanceof VoucherModel) {
                data.setVoucherName(((VoucherModel) disc).getDescription());
                break;
            }
        }
    }

    private VelocityContext getVelocityContextForDocument(final SslGSTDocumentData gstDocumentData) {
        final VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("document", gstDocumentData);
        velocityContext.put(STATE_CODE, Config.getString(GST_DOCUMENT_STATE_CODE, "State_Code"));
        return velocityContext;

    }

    private void populateDocumentConsignmentDetails(final ConsignmentModel source, final List<SslGSTConsignmentDetailsData> target,
            final GSTDocumentType documentType,final boolean isFCCInvoice) {
        final Map<String, SslGSTConsignmentDetailsData> skuMap = new HashMap<>();

        for (final ConsignmentEntryModel consignmentEntryModel : source.getConsignmentEntries()) {
            if (!ConsignmentEntryStatus.COMPLETE.equals(consignmentEntryModel.getStatus())
                    || (GSTDocumentType.BILL_OF_SUPPLY.equals(documentType) && GSTUtil.isZeroTax(consignmentEntryModel.getTaxComponents()) > 0)) {
                continue;
            }
            final String productCode = consignmentEntryModel.getOrderEntry().getProduct().getCode();
            final boolean isFCCProduct=FCCGiftUtil.isFCCProduct(consignmentEntryModel.getOrderEntry().getProduct());
            if (isFCCProduct && !isFCCInvoice) {
                //populateFCCProductDetails(consignmentEntryModel, target);
            	continue;
            } else if (isFCCProduct && isFCCInvoice
                    && NumberUtils.compare(GSTUtil.isZeroTax(consignmentEntryModel.getTaxComponents()), 0) == 0) {
            	populateFCCProductDetails(consignmentEntryModel, target);
            }else if(isFCCInvoice && !isFCCProduct)
            {
            	continue;
            }
            else {

                SslGSTConsignmentDetailsData data;
                final boolean existingSku;

                if (skuMap.containsKey(productCode)) {
                    existingSku = true;
                    data = skuMap.get(productCode);
                } else {
                    data = new SslGSTConsignmentDetailsData();
                    existingSku = false;
                }

                if (existingSku) {
                    populateDocumentProductOfExistingSku(consignmentEntryModel, data);
                } else {
                    populateDocumentProduct(consignmentEntryModel, data, productCode);
                    target.add(data);
                    skuMap.put(productCode, data);
                }
            }
        }
    }

    private void populateDocumentProduct(final ConsignmentEntryModel consignmentEntryModel,
            final SslGSTConsignmentDetailsData consignmentDetailsData, final String productCode) {

        final long allocatedQuantity = GSTUtil.getAllocatedQuantityForConsignmentEntry(consignmentEntryModel);
        final long consignmentEntryQuantity = consignmentEntryModel.getQuantity().longValue();

        consignmentDetailsData.setSku(productCode);
        final ProductModel product = consignmentEntryModel.getOrderEntry().getProduct();
        final ProductModel baseProd = getBaseProduct(product);
        consignmentDetailsData.setHsnCode(product.getHsnCode());
        consignmentDetailsData.setDescription(baseProd.getName());
        setColorSizeDetails(consignmentDetailsData, product);
        final double pieceDiscount = getPieceDiscount(consignmentEntryModel, allocatedQuantity);
        consignmentDetailsData.setQuantity(allocatedQuantity);
        consignmentDetailsData.setUnitOfGoods(consignmentEntryModel.getOrderEntry().getUnit().getName());
        consignmentDetailsData.setTotalValueOfGoods(BigDecimal
                .valueOf(consignmentEntryModel.getOrderEntry().getBasePrice().doubleValue() * allocatedQuantity)
                .setScale(TWO, RoundingMode.HALF_UP).doubleValue());
        consignmentDetailsData.setTotalPieceAndItemDiscount(getRoundedAmount(pieceDiscount));
        consignmentDetailsData
                .setDeliveryCharges(getRoundedAmount((consignmentEntryModel.getTaxInclusiveShippingValue().doubleValue() * allocatedQuantity)
                        / consignmentEntryQuantity));
        final double totalInclusiveValue = ((consignmentEntryModel.getTaxInclusiveItemValue().doubleValue() * allocatedQuantity) / consignmentEntryQuantity)
                + consignmentDetailsData.getDeliveryCharges();
        consignmentDetailsData.setTotalPayable(getRoundedAmount(totalInclusiveValue));
    }

    private void populateFCCProductDetails(final ConsignmentEntryModel consignmentEntryModel,
            final List<SslGSTConsignmentDetailsData> target) {
        SslGSTConsignmentDetailsData consignmentDetailsData;
        final long allocatedQuantity = GSTUtil.getAllocatedQuantityForConsignmentEntry(consignmentEntryModel);

        final boolean intraStateTransfer = consignmentEntryModel.getConsignment().getDeliveryAddress().getRegion().getIsocode()
                .equals(consignmentEntryModel.getConsignment().getWarehouse().getRegionCode());

        final SslLoyaltyFeeBreakupModel model = new SslLoyaltyFeeBreakupModel();
        model.setIntraStateFulfillment(intraStateTransfer);

        final List<SslLoyaltyFeeBreakupModel> sslLoyaltyFeeBreakupList = flexibleSearchService.getModelsByExample(model);

        final List<SslLoyaltyFeeBreakupModel> sortedLoyaltyFeeBreakupList = new ArrayList<>(sslLoyaltyFeeBreakupList);
        Collections.sort(sortedLoyaltyFeeBreakupList, Comparator.comparing(SslLoyaltyFeeBreakupModel::getCode));

        final ProductModel product = consignmentEntryModel.getOrderEntry().getProduct();

        final String hsnCode = product.getHsnCode();

        for (final SslLoyaltyFeeBreakupModel sslLoyaltyFeeBreakup : sortedLoyaltyFeeBreakupList) {
            consignmentDetailsData = new SslGSTConsignmentDetailsData();
            consignmentDetailsData.setSku(sslLoyaltyFeeBreakup.getSubSku());
            consignmentDetailsData.setHsnCode(hsnCode);
            consignmentDetailsData.setDescription(sslLoyaltyFeeBreakup.getSkuDescription());
            setColorSizeDetails(consignmentDetailsData, product);
            consignmentDetailsData.setQuantity(allocatedQuantity);
            consignmentDetailsData.setUnitOfGoods(Config.getString(SslCoreConstants.FCC_PRODUCT_UNIT_CODE, "U"));
            if (Config.getString(SslCoreConstants.FCC_PRODUCT_CODE, "999").equals(product.getCode())) {
                consignmentDetailsData.setTotalAdvanceReceived(sslLoyaltyFeeBreakup.getPrice());
            }
            consignmentDetailsData.setTotalValueOfGoods(sslLoyaltyFeeBreakup.getPrice());
            // Discounts and Delivery cost ideally should be 0 in case of FCC.
            final double pieceDiscount = getRoundedAmount(getPieceDiscount(consignmentEntryModel, allocatedQuantity));
            consignmentDetailsData.setTotalPieceAndItemDiscount(pieceDiscount);
            final long consignmentEntryQuantity = consignmentEntryModel.getQuantity().longValue();
            consignmentDetailsData
                    .setDeliveryCharges(getRoundedAmount((consignmentEntryModel.getTaxInclusiveShippingValue().doubleValue() * allocatedQuantity)
                            / consignmentEntryQuantity));
            final double totalInclusiveValue = sslLoyaltyFeeBreakup.getPrice() + consignmentDetailsData.getDeliveryCharges() - pieceDiscount;
            consignmentDetailsData.setTotalPayable(getRoundedAmount(totalInclusiveValue));
            target.add(consignmentDetailsData);
        }

    }

    /**
     * @param consignmentDetailsData
     * @param product
     */
    private void setColorSizeDetails(final SslGSTConsignmentDetailsData consignmentDetailsData, final ProductModel product) {
        if (product instanceof ApparelSizeVariantProductModel) {
            final String size = ((ApparelSizeVariantProductModel) product).getSize();
            final String style = ((ApparelSizeVariantProductModel) product).getStyle();
            String sizeColor = "";
            if(style == null || size==null)
            {
            	sizeColor = (style == null ? "" : style).concat(size == null ? "" : size);
            } else {
            	sizeColor = (style).concat(" / ").concat(size);
            }
            consignmentDetailsData.setColorSize(sizeColor);
        }
    }

    private double getPieceDiscount(final ConsignmentEntryModel consignmentEntryModel, final long quantity) {
        double pieceDiscount = consignmentEntryModel.getOrderEntry().getPieceDiscount() == null ? 0.0 : consignmentEntryModel
                .getOrderEntry().getPieceDiscount().doubleValue();
        final Double basePrice = consignmentEntryModel.getOrderEntry().getBasePrice();
        final Long orderEntryQuantity = consignmentEntryModel.getOrderEntry().getQuantity();
        final Double totalPrice = consignmentEntryModel.getOrderEntry().getTotalPrice();
        final double orderEntryDiscount = (basePrice.doubleValue() * orderEntryQuantity.longValue()) - totalPrice.doubleValue();
        if (orderEntryDiscount > ZERO) {
            pieceDiscount += (orderEntryDiscount / orderEntryQuantity.longValue());
        }
        return pieceDiscount * quantity;
    }

    private void populateDocumentProductOfExistingSku(final ConsignmentEntryModel consignmentEntryModel,
            final SslGSTConsignmentDetailsData data) {
        final long allocatedQuantity = GSTUtil.getAllocatedQuantityForConsignmentEntry(consignmentEntryModel);
        final long newQuantity = data.getQuantity() + allocatedQuantity;
        data.setTotalValueOfGoods(consignmentEntryModel.getOrderEntry().getBasePrice().doubleValue() * newQuantity);
        data.setTotalPieceAndItemDiscount(getRoundedAmount((data.getTotalPieceAndItemDiscount() / data.getQuantity()) * newQuantity));
        data.setDeliveryCharges(getRoundedAmount((data.getDeliveryCharges() / data.getQuantity()) * newQuantity));
        data.setTotalPayable(getRoundedAmount((data.getTotalPayable() / data.getQuantity()) * newQuantity));
        data.setQuantity(newQuantity);
    }

    protected ProductModel getBaseProduct(final ProductModel product) {
        if (product instanceof VariantProductModel) {
            return getBaseProduct(((VariantProductModel) product).getBaseProduct());
        }
        return product;
    }

    @Override
    public StringReader getBOSPDFData(final String invoiceNumber, final GSTDocumentType documentType, final boolean isDuplicateCopy,final boolean isFCCInvoice) {
        final ConsignmentModel consignmentModel = getConsignment(ConsignmentModel.INVOICENUMBER, invoiceNumber);
        // send null data in case BOS do not exist for consignment.
        if (consignmentModel == null || (StringUtils.isEmpty(consignmentModel.getBosNumber()))) {
            if (!FCCGiftUtil.isFCCConsignment(consignmentModel)&& !isFCCInvoice) {
                return null;
            }
        }

        String bosData = StringUtils.EMPTY;
        try {
            SslGSTDocumentData gstDocumentData = null;
            if(!isFCCInvoice && FCCGiftUtil.isFCCConsignment(consignmentModel)){
            	return null;
            }
            else if(isFCCInvoice && !FCCGiftUtil.hasFCCProduct(consignmentModel)){
            	return null;
            }
            gstDocumentData = new SslGSTDocumentData();
            populateBOSDocumentData(consignmentModel, gstDocumentData, documentType,isFCCInvoice);
            gstDocumentData.setDuplicate(isDuplicateCopy);
            final VelocityContext velocityContext = this.getVelocityContextForDocument(gstDocumentData);

            velocityContext.put(BARCODE, sslUtilityForPDF.getiTextBarcode128AsBase64(invoiceNumber));
         // Added for ECD-2185
			if (isFCCInvoice) {
				velocityContext.put("documentHeader", "Tax Invoice");
				velocityContext.put("headerOfDocumentNumber", "Invoice Number");
				velocityContext.put("headerOfDocumnentIssueDate", "Invoice Date");
			} else {
				velocityContext.put("documentHeader", "Bill of Supply");
				velocityContext.put("headerOfDocumentNumber", "Bill Of Supply Number");
				velocityContext.put("headerOfDocumnentIssueDate", "Bill Of Supply Date");
			}
            bosData = sslUtilityForPDF
                    .mergeVmTemplate(String.format(VMREGEX, VMPATH, documentType.getCode(), VMEXTENSION), velocityContext);
         // Added for ECD-2185
        } catch (final IOException e) {
            LOG.error(ERROR_GENERATING + documentType.getCode() + " for Invoice Number: " + invoiceNumber + TRACE + e.getMessage());
            if(LOG.isDebugEnabled())
            {
            	e.printStackTrace();
            }
        }
        return new StringReader(bosData);
    }

    private List<StringReader> getCreditNotePdfData(final String invoiceNumber, final GSTDocumentType documentType) {

        final ConsignmentModel returnConsignment = getConsignment(ConsignmentModel.INVOICENUMBER, invoiceNumber);
        if (returnConsignment == null) {
            return Collections.emptyList();
        }
        GSTDocumentType docType = documentType;
        final List<StringReader> stringReaderList = new ArrayList<>();

        try {
            final Boolean[] creditNoteValues = { Boolean.FALSE, Boolean.TRUE };
            for (final Boolean isBos : creditNoteValues) {
                final SalesDataEntryModel salesDataEntryModel = new SalesDataEntryModel();
                salesDataEntryModel.setInvoiceNumber(returnConsignment.getInvoiceNumber());
                salesDataEntryModel.setIsBOS(isBos);
                final List<SalesDataEntryModel> creditNoteItems = flexibleSearchService.getModelsByExample(salesDataEntryModel);

                final Set<String> gstReferenceDocNumbers = creditNoteItems.stream().map(SalesDataEntryModel::getGstReferenceNumber)
                        .collect(Collectors.toSet());
                final boolean ismultipleConsignmentDoc = gstReferenceDocNumbers.size() > 1;
                if(CollectionUtils.isNotEmpty(gstReferenceDocNumbers)){
                for (final String gstRefNum : gstReferenceDocNumbers) {
                    salesDataEntryModel.setGstReferenceNumber(gstRefNum);
                    final SslGSTDocumentData gstDocumentData = populateCreditNoteDocumentData(returnConsignment, isBos,
                            salesDataEntryModel, ismultipleConsignmentDoc);
                    if (gstDocumentData != null) {
                        final VelocityContext velocityContext = this.getVelocityContextForDocument(gstDocumentData);

                        if (isBos.booleanValue()) {
                            docType = GSTDocumentType.CREDIT_NOTE_BOS;
                        }

                        final String documentData = sslUtilityForPDF.mergeVmTemplate(
                                String.format(VMREGEX, VMPATH, docType.getCode(), VMEXTENSION), velocityContext);
                        stringReaderList.add(new StringReader(documentData));
                    }
                }
                }
            }
        } catch (final Exception e) {
            LOG.error(ERROR_GENERATING + docType.getCode() + " for Invoice Number: " + invoiceNumber + TRACE + e.getMessage());
            if(LOG.isDebugEnabled())
            {
            	e.printStackTrace();
            }
        }
        return stringReaderList;
    }

    private List<StringReader> populateReceiptVoucherPdfData(final String invoiceNumber, final GSTDocumentType documentType) {

        StringReader stringReader = null;

        final Boolean isBOS = GSTDocumentType.ADVANCE_RECEIPT.equals(documentType) ? Boolean.FALSE : Boolean.TRUE;

        try {
            final SSLAdvanceReceiptModel receiptVoucherModel = new SSLAdvanceReceiptModel();
            receiptVoucherModel.setInternalRefNumber(invoiceNumber);
            receiptVoucherModel.setIsBOS(isBOS);
            final SSLAdvanceReceiptModel receiptVoucherItems = flexibleSearchService.getModelsByExample(receiptVoucherModel).get(0);
            final SslGSTDocumentData target = new SslGSTDocumentData();

            final String orderCode = receiptVoucherItems.getOrderNumber();
            final OrderModel order = sslDefaultOrderDao.getOrderDetailsByOrderNumber(orderCode);

            target.setDocumentNumber(receiptVoucherItems.getGstDocNumber());
            if (receiptVoucherItems.getReceiptVoucherDate() != null) {
                target.setDocumentIssueDate(currentDate.format(receiptVoucherItems.getReceiptVoucherDate()));
            } else {
                target.setDocumentIssueDate(currentDate.format(order.getCreationtime()));
            }
            populateDocumentSKUDetails(receiptVoucherItems.getSalesDataEntry(), order, target,
                    receiptVoucherItems.getReceivedAdvanceAmount() == null ? ZERO : receiptVoucherItems.getReceivedAdvanceAmount()
                            .doubleValue());

            final VelocityContext velocityContext = this.getVelocityContextForDocument(target);
            stringReader = new StringReader(sslUtilityForPDF.mergeVmTemplate(
                    String.format(VMREGEX, VMPATH, documentType.getCode(), VMEXTENSION), velocityContext));

        } catch (final Exception e) {
            LOG.error(ERROR_GENERATING + documentType.getCode() + " for Receipt Voucher Number: " + invoiceNumber + TRACE + e.getMessage());
            if(LOG.isDebugEnabled())
            {
            	e.printStackTrace();
            }
        }
        if (stringReader == null) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(stringReader);
        }
    }

    private void populateDocumentSKUDetails(final Collection<SalesDataEntryModel> salesDataEntryList, final OrderModel order,
            final SslGSTDocumentData target, final double advanceReceived) {
        target.setEntryDetails(new ArrayList<SslGSTConsignmentDetailsData>());
        double totalItemsValue = ZERO;
        for (final SalesDataEntryModel salesDataEntry : salesDataEntryList) {
            final String productSku = salesDataEntry.getProductCode();

            if (Config.getString(SslCoreConstants.FCC_PRODUCT_CODE, "999").equals(productSku)) {

                final List<SslGSTConsignmentDetailsData> consignmentDetailsDataList = new ArrayList<>();
                this.populateFCCProductDetailsForReceiptRefundVoucher(salesDataEntry, consignmentDetailsDataList,order);
                target.getEntryDetails().addAll(consignmentDetailsDataList);

            } else {
                if (Config.getParameter("ssl.shippingFee.sku").equals(productSku)) {
                    continue;
                }

                final SslGSTConsignmentDetailsData consignmentDetailsData = new SslGSTConsignmentDetailsData();

                final ProductModel sizeVariantProductModel = populateProductDetails(productSku, consignmentDetailsData);

                final double basePrice = salesDataEntry.getBasePrice();
                final double itemDiscount = salesDataEntry.getItemDiscount();
                final double pieceDiscount = salesDataEntry.getPieceDiscount();
                final double shippingCost = salesDataEntry.getGstReceiptShippingAmount();
                final double skuPriceAfterDiscount = basePrice + shippingCost - pieceDiscount - itemDiscount;

                final long quantity = salesDataEntry.getQuantity();
                consignmentDetailsData.setQuantity(quantity);

                final double totalValueOfGoods = getRoundedAmount(skuPriceAfterDiscount * quantity);

                totalItemsValue += totalValueOfGoods;

                consignmentDetailsData.setTotalValueOfGoods(totalValueOfGoods);

                final double taxInclusiveAdvanceReceived = (salesDataEntry.getGstReceiptAmount() + salesDataEntry
                        .getGstReceiptShippingAmount()) * quantity;

                consignmentDetailsData.setTotalAdvanceReceived(getRoundedAmount(taxInclusiveAdvanceReceived));

                OrderEntryModel orderEntry = new OrderEntryModel();
                orderEntry.setProduct(sizeVariantProductModel);
                orderEntry.setOrder(order);
                orderEntry = flexibleSearchService.getModelByExample(orderEntry);

                final Map<GstTaxTypes, String> taxComponents = orderEntry.getTaxComponents();
                if (GSTUtil.isZeroTax(taxComponents) > 0) {
                    final double appliedGSTTax = taxComponents.values().stream()
                            .mapToDouble(val -> NumberUtils.isNumber(val) ? Double.parseDouble(val) : 0).sum();
                    final double totalTaxableAdvanceValue = (taxInclusiveAdvanceReceived * 100) / (100 + appliedGSTTax);
                    consignmentDetailsData.setTaxExcludedAdvanceReceived(getRoundedAmount(totalTaxableAdvanceValue));
                    consignmentDetailsData.setApplicableTaxes(gstDocumentsService.populateGSTTaxComponents(taxComponents,
                            totalTaxableAdvanceValue));
                    consignmentDetailsData.setTotalTax(getRoundedAmount(taxInclusiveAdvanceReceived - totalTaxableAdvanceValue));
                }
                target.getEntryDetails().add(consignmentDetailsData);
            }

        }
        target.setSubTotal(getRoundedAmount(advanceReceived));

        if (totalItemsValue - target.getSubTotal() == ZERO) {
            target.setPartialAdvanceReceived(false);
        } else {
            target.setPartialAdvanceReceived(true);
        }

        setPointofServiceDetails(sslGSTService.getCentralLocation(), target);
        setOrderDetails(target, order);

    }

    private List<StringReader> populateRefundVoucherPdfData(final String invoiceNumber, final GSTDocumentType documentType) {

        StringReader stringReader = null;

        final Boolean isBOS = GSTDocumentType.REFUND_VOUCHER.equals(documentType) ? Boolean.FALSE : Boolean.TRUE;

        try {
            final SSLRefundVoucherModel refundVoucherModel = new SSLRefundVoucherModel();
            refundVoucherModel.setInternalRefNumber(invoiceNumber);
            refundVoucherModel.setIsBOS(isBOS);
            final SSLRefundVoucherModel refundVoucherItems = flexibleSearchService.getModelsByExample(refundVoucherModel).get(0);

            final String orderCode = refundVoucherItems.getOrderNumber();
            final OrderModel order = sslDefaultOrderDao.getOrderDetailsByOrderNumber(orderCode);
            if (order == null) {
                return Collections.emptyList();
            }
            final SslGSTDocumentData target = new SslGSTDocumentData();

            target.setDocumentNumber(refundVoucherItems.getGstDocumentNumber());
            if (refundVoucherItems.getRefundVoucherDate() != null) {
                target.setDocumentIssueDate(currentDate.format(refundVoucherItems.getRefundVoucherDate()));
            } else {
                target.setDocumentIssueDate(currentDate.format(refundVoucherItems.getCreationtime()));
            }
            populateDocumentSKUDetails(refundVoucherItems.getSalesDataEntry(), order, target, refundVoucherItems.getAmountRec() == null ? 0
                    : refundVoucherItems.getAmountRec().doubleValue());

            final String advanceReceiptNumber = refundVoucherItems.getAdvReceiptInternalRefNo();
            final SSLAdvanceReceiptModel advanceReceiptModel = new SSLAdvanceReceiptModel();
            advanceReceiptModel.setInternalRefNumber(advanceReceiptNumber);
            advanceReceiptModel.setIsBOS(isBOS);
            final SSLAdvanceReceiptModel advanceReceiptRef = flexibleSearchService.getModelsByExample(advanceReceiptModel).get(0);
            if (advanceReceiptRef.getReceiptVoucherDate() != null) {
                target.setInternalReferenceDocumentIssueDate(currentDate.format(advanceReceiptRef.getReceiptVoucherDate()));
            } else {
                target.setInternalReferenceDocumentIssueDate(currentDate.format(advanceReceiptRef.getCreationtime()));
            }
            target.setInternalReferenceNumber(advanceReceiptRef.getGstDocNumber());

            final VelocityContext velocityContext = this.getVelocityContextForDocument(target);
            stringReader = new StringReader(sslUtilityForPDF.mergeVmTemplate(
                    String.format(VMREGEX, VMPATH, documentType.getCode(), VMEXTENSION), velocityContext));

        } catch (final Exception e) {
            LOG.error(ERROR_GENERATING + documentType.getCode() + " for Refund Voucher Number: " + invoiceNumber + TRACE + e.getMessage());
            if(LOG.isDebugEnabled())
            {
            	e.printStackTrace();
            }
        }
        if (stringReader == null) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(stringReader);
        }
    }

    /**
     * @param target
     * @param order
     */
    private void setOrderDetails(final SslGSTDocumentData target, final OrderModel order) {
        target.setOrderDate(currentDate.format(order.getCreationtime()));
        target.setOrderNumber(order.getCode());
        target.setRecipientDetails(order.getDeliveryAddress());
        target.setCustomerDetails(order.getPaymentAddress() != null ? order.getPaymentAddress() : order.getDeliveryAddress());
    }

    private double getRoundedAmount(final double value) {
        return BigDecimal.valueOf(value).setScale(TWO, RoundingMode.HALF_UP).doubleValue();
    }

    public static String getSDITenderTypeCOD() {
        return Config.getString(SDI_TENDER_TYPE_COD, "37");
    }

    private ProductModel populateProductDetails(final String productSku, final SslGSTConsignmentDetailsData consignmentDetailsData) {

        ProductModel sizeVariantProductModel = null;
        try {
            sizeVariantProductModel = productService.getProductForCode(
                    catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME, SslCoreConstants.ONLINE_CATALOG_VERSION),
                    productSku);
        } catch (final Exception e) {
            LOG.error("Product not found for SKU: " + productSku);
            return null;
        }
        final ProductModel baseProduct = getBaseProduct(sizeVariantProductModel);
        consignmentDetailsData.setSku(productSku);
        consignmentDetailsData.setHsnCode(sizeVariantProductModel.getHsnCode());
        consignmentDetailsData.setDescription(baseProduct.getName());
        setColorSizeDetails(consignmentDetailsData, sizeVariantProductModel);
        consignmentDetailsData.setUnitOfGoods(sizeVariantProductModel.getUnit().getName());
        return sizeVariantProductModel;
    }

    private void populateFCCProductDetailsForReceiptRefundVoucher(final SalesDataEntryModel salesDataEntry,
            final List<SslGSTConsignmentDetailsData> target,final OrderModel order) {
    		// Ideally Discounts and Delivery cost should be 0 in case of FCC and quantity of FCC in Order should be 1.
            SslGSTConsignmentDetailsData consignmentDetailsData;
            final long quantity = salesDataEntry.getQuantity();

            final boolean intraStateTransfer = sslGSTService.getCentralLocation().getAddress().getRegion().getIsocode().equals(order.getDeliveryAddress().getRegion().getIsocode());

            final SslLoyaltyFeeBreakupModel model = new SslLoyaltyFeeBreakupModel();
            model.setIntraStateFulfillment(intraStateTransfer);

            final List<SslLoyaltyFeeBreakupModel> sslLoyaltyFeeBreakupList = flexibleSearchService.getModelsByExample(model);

            final List<SslLoyaltyFeeBreakupModel> sortedLoyaltyFeeBreakupList = new ArrayList<>(sslLoyaltyFeeBreakupList);
            Collections.sort(sortedLoyaltyFeeBreakupList, Comparator.comparing(SslLoyaltyFeeBreakupModel::getCode));

            final ProductModel product = productService.getProductForCode(
                    catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME, SslCoreConstants.ONLINE_CATALOG_VERSION),
                    salesDataEntry.getProductCode());

            final String hsnCode = product.getHsnCode();

            for (final SslLoyaltyFeeBreakupModel sslLoyaltyFeeBreakup : sortedLoyaltyFeeBreakupList) {
                consignmentDetailsData = new SslGSTConsignmentDetailsData();
                consignmentDetailsData.setSku(sslLoyaltyFeeBreakup.getSubSku());
                consignmentDetailsData.setHsnCode(hsnCode);
                consignmentDetailsData.setDescription(sslLoyaltyFeeBreakup.getSkuDescription());
                setColorSizeDetails(consignmentDetailsData, product);
                consignmentDetailsData.setQuantity(quantity);
                consignmentDetailsData.setUnitOfGoods(Config.getString(SslCoreConstants.FCC_PRODUCT_UNIT_CODE, "U"));
                consignmentDetailsData.setTotalAdvanceReceived(sslLoyaltyFeeBreakup.getPrice());
                consignmentDetailsData.setTotalValueOfGoods(sslLoyaltyFeeBreakup.getPrice());
                target.add(consignmentDetailsData);
            }
        }
}
