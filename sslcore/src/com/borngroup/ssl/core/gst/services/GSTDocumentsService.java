package com.borngroup.ssl.core.gst.services;

import java.util.List;
import java.util.Map;

import com.borngroup.ssl.core.enums.GstTaxTypes;
import com.borngroup.ssl.fulfilmentprocess.model.SSLAdvanceReceiptModel;
import com.borngroup.ssl.fulfilmentprocess.model.SSLRefundVoucherModel;
import com.borngroup.ssl.fulfilmentprocess.model.SalesDataEntryModel;
import com.borngroup.ssl.fulfilmentprocess.order.data.gst.SslGSTTaxData;

/**
 * <p>
 * <p>
 * GSTDocumentsService.java : .  * 
 * <p>
 * Created By : anupam.srivastava@nagarro.com.
 *
 * @author Ssl  
 */

public interface GSTDocumentsService {

    /**
     * Method to generate document invoice number using invoice calculation strategy
     *
     * @return
     */
    public String generateDocumentInvoiceNumber();

    /**
     * Method to create refund voucher number in case of cancellation
     *
     * @return
     */
    public String createRefundVoucher(String orderCode, Boolean isBos);

    public String generateCreditNoteNumber(boolean isBos, String billingLocation);

    /**
     * Method to find SSLAdvanceReceiptModel based on order number
     *
     * @param advanceReceipt
     * @return
     */
    List<SSLAdvanceReceiptModel> getAdvanceReceiptModelByOrderNumber(String orderCode);

    /**
     *
     * @param taxComponents
     * @param totalTaxableValue
     * @return
     */
    List<SslGSTTaxData> populateGSTTaxComponents(final Map<GstTaxTypes, String> taxComponents,
            final double totalTaxableValue);

    /**
     * @param type
     * @param stockLocation
     * @return
     */
    String generateDocumentNumber(String type, String stockLocation);

    /**
     * Method to fetch refund vouchers for a particular order
     *
     * @param advanceReceiptNo
     * @return
     */
    List<SSLRefundVoucherModel> getRefundVoucherModels(String orderNumber);

    /**
     * Method to get Sales Data Models based on SKU and invoice number
     *
     * @param product
     * @param invoiceNumber
     * @return
     */
    SalesDataEntryModel getSalesDataModels(String product, String invoiceNumber);

}
