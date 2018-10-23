package com.borngroup.ssl.core.gst.services.impl;

import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.tx.Transaction;
import de.hybris.platform.util.Config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.borngroup.ssl.core.enums.GstTaxTypes;
import com.borngroup.ssl.core.gst.dao.GSTDocumentsDao;
import com.borngroup.ssl.core.gst.services.CoreGSTTaxCalculationService;
import com.borngroup.ssl.core.gst.services.GSTDocumentsService;
import com.borngroup.ssl.core.services.gst.SslGSTService;
import com.borngroup.ssl.core.util.CommonHelper;
import com.borngroup.ssl.fulfilmentprocess.model.SSLAdvanceReceiptModel;
import com.borngroup.ssl.fulfilmentprocess.model.SSLRefundVoucherModel;
import com.borngroup.ssl.fulfilmentprocess.model.SalesDataEntryModel;
import com.borngroup.ssl.fulfilmentprocess.order.data.gst.SslGSTTaxData;
import com.borngroup.tax.enums.GSTDocumentType;

/**
 * <p>
 * <p>
 * GSTDocumentsServiceImpl.java : .  * 
 * <p>
 * Created By : anupam.srivastava@nagarro.com.
 *
 * @author Ssl  
 */

public class GSTDocumentsServiceImpl implements GSTDocumentsService {

    private static final Logger LOG = Logger.getLogger(GSTDocumentsServiceImpl.class);

    public static final String DOCREGNUMBER = Config.getParameter("fulfillment.document.register.number");
    public static final String REFUNDVOUCHERTAXABLE = Config.getParameter("gst.refundvoucher.taxable.name");
    public static final String REFUNDVOUCHERNONTAXABLE = Config.getParameter("gst.refundvoucher.exempted.name");
    public static final String CREDITNOTETAXABLE = Config.getParameter("gst.creditnote.taxinvoice.name");
    public static final String CREDITNOTENONTAXABLE = Config.getParameter("gst.creditnote.billofsupply.name");
    public static final String GST_NUMBER_FORMAT = "gst.reg.number.format";

    public static final String INVOICE_FORMAT = "fulfillment.invoice.format";
    public static final String INVOICE_DATE_FORMAT = "fulfillment.invoice.date.format";
    public static final String INVOICE_ROLLOVER_START = "fulfillment.invoice.rollover.start";
    public static final String INVOICE_CENTURY_CODE = "fulfillment.invoice.century.code";
    public static final String INVOICE_REGISTER_NUMBER = "fulfillment.invoice.register.number";
    public static final String INVOICE_TRANSACTION_START = "fulfillment.invoice.transaction.start";
    public static final String INVOICE_TRANSACTION_END = "fulfillment.invoice.transaction.end";
    public static final String INVOICE_TRANSACTION_FORMAT = "fulfillment.invoice.transaction.format";
    public static final String GST_START_NUMBER = "gst.document.startNumber";
    /** resttemplate {@link RestTemplate}. */
    @Resource(name = "defaultSolrRestTemplate")
    private RestTemplate restTemplate;

    @Resource(name = "sslGSTService")
    private SslGSTService sslGSTService;

    @Resource(name = "coreGSTTaxCalculationService")
    private CoreGSTTaxCalculationService coreGSTTaxCalculationService;

    private ModelService modelService;

    private FlexibleSearchService flexibleSearchService;
    private CommonHelper commonHelper;

    private GSTDocumentsDao gstDocumentsDao;

    @Autowired
    private EnumerationService enumerationService;

    @Override
    public String createRefundVoucher(final String orderCode, final Boolean isBos) {
        String refundVoucherNumber = null;
        boolean success = false;

            final Transaction tx = Transaction.current();
            tx.begin();
            try {
                getModelService().enableTransactions();

                if (!isBos.booleanValue()) {
                    refundVoucherNumber = generateDocumentNumber(GSTDocumentType.REFUND_VOUCHER.toString(), sslGSTService
                            .getCentralLocation().getStockLocationCode());
                } else {
                    refundVoucherNumber = generateDocumentNumber(GSTDocumentType.REFUND_VOUCHER_BOS.toString(),
                            sslGSTService.getCentralLocation().getStockLocationCode());
                }
                success = true;
            } catch (final Exception e) {
                LOG.error("Exception occured while generating document invoice number" + e);
            } finally {
                if (success) {
                    tx.commit();
                } else {
                    tx.rollback();
                }
                getModelService().disableTransactions();
            }

        return refundVoucherNumber;
    }

    @Override
    public String generateCreditNoteNumber(final boolean isBos, final String stockLocationCode) {
        String creditNote = null;
        boolean success = false;
        final Transaction tx = Transaction.current();
        tx.begin();
        try {
            getModelService().enableTransactions();
            creditNote = generateDocumentNumber(GSTDocumentType.CREDIT_NOTE.toString(), stockLocationCode);
            if (!isBos) {
                creditNote = generateDocumentNumber(GSTDocumentType.CREDIT_NOTE.toString(), stockLocationCode);
            } else {
                creditNote = generateDocumentNumber(GSTDocumentType.CREDIT_NOTE_BOS.toString(), stockLocationCode);
            }
            success = true;
        } catch (final Exception e) {
            LOG.info(e.getStackTrace());
        } finally {
            if (success) {
                tx.commit();
            } else {
                tx.rollback();
            }
            getModelService().disableTransactions();
        }

        return creditNote;
    }

    @Override
    public String generateDocumentInvoiceNumber() {
        final String uri = Config.getParameter("invoice.document.generation.url");
        LOG.debug("Calling Service Invoice Generation.");
        LOG.debug("Invoice Generation URL" + uri);
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.TEXT_PLAIN_VALUE);
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);
        final HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<>(headers),
                String.class);
        LOG.debug("Response From Service:" + response);
        if (StringUtils.isEmpty(response.getBody())) {
            LOG.error("Unable to generate Document Invoice Number.");
        }
        return response.getBody();
    }

    @Override
    public String generateDocumentNumber(final String type, final String stockLocation) {
        final String uri = Config.getParameter("document.number.generation.url");
        LOG.debug("Calling Service Document Generation.");
        LOG.debug("Invoice Generation URL" + uri);
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.TEXT_PLAIN_VALUE);
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri).queryParam("type", type).queryParam("stockLocation",
                stockLocation);
        final HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<>(headers),
                String.class);
        LOG.debug("Response From Service:" + response);
        if (StringUtils.isEmpty(response.getBody())) {
            LOG.error("Unable to generate document Number.");
        }
        return response.getBody();
    }

    @Override
    public List<SslGSTTaxData> populateGSTTaxComponents(final Map<GstTaxTypes, String> taxComponents,
            final double totalTaxableValue) {

        final Iterator<Entry<GstTaxTypes, String>> taxComponentsIterator = taxComponents.entrySet().iterator();
        final List<SslGSTTaxData> populatedList = new ArrayList<>(taxComponents.size());
        SslGSTTaxData cessTaxData = null;
        while (taxComponentsIterator.hasNext()) {
            final SslGSTTaxData data = new SslGSTTaxData();
            final Entry<GstTaxTypes, String> entrySet = taxComponentsIterator.next();
            data.setTaxType(enumerationService.getEnumerationName(entrySet.getKey()));
            data.setPercentage(Double.parseDouble(entrySet.getValue()));

            data.setTaxAmount(BigDecimal.valueOf(
                    (totalTaxableValue * Double.valueOf(entrySet.getValue()).doubleValue()) / 100)
                    .setScale(2, RoundingMode.HALF_UP).doubleValue());
            if (GstTaxTypes.CESS.equals(entrySet.getKey())) {
                cessTaxData = data;
                continue;
            }
            populatedList.add(data);
        }
        if (cessTaxData != null) {
            populatedList.add(cessTaxData);
        }
        return populatedList;
    }

    @Override
    public List<SSLRefundVoucherModel> getRefundVoucherModels(final String orderNumber) {
        return gstDocumentsDao.getRefundVoucherModels(orderNumber);
    }

    @Override
    public List<SSLAdvanceReceiptModel> getAdvanceReceiptModelByOrderNumber(final String orderCode) {
        return gstDocumentsDao.getAdvanceReceiptModelByOrderNumber(orderCode);
    }

    @Override
    public SalesDataEntryModel getSalesDataModels(final String product, final String invoiceNumber) {
        return gstDocumentsDao.getSalesDataModel(product, invoiceNumber);
    }

    public SslGSTService getSslGSTService() {
        return sslGSTService;
    }

    public void setSslGSTService(final SslGSTService sslGSTService) {
        this.sslGSTService = sslGSTService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    public CommonHelper getCommonHelper() {
        return commonHelper;
    }

    public void setCommonHelper(final CommonHelper commonHelper) {
        this.commonHelper = commonHelper;
    }

    public CoreGSTTaxCalculationService getCoreGSTTaxCalculationService() {
        return coreGSTTaxCalculationService;
    }

    public void setCoreGSTTaxCalculationService(final CoreGSTTaxCalculationService coreGSTTaxCalculationService) {
        this.coreGSTTaxCalculationService = coreGSTTaxCalculationService;
    }

    public GSTDocumentsDao getGstDocumentsDao() {
        return gstDocumentsDao;
    }

    public void setGstDocumentsDao(final GSTDocumentsDao gstDocumentsDao) {
        this.gstDocumentsDao = gstDocumentsDao;
    }

}
