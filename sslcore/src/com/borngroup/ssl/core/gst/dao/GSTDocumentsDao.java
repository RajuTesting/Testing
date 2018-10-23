/**
 *
 */
package com.borngroup.ssl.core.gst.dao;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.List;

import com.borngroup.ssl.fulfilmentprocess.model.InvoiceNumGeneratorModel;
import com.borngroup.ssl.fulfilmentprocess.model.SSLAdvanceReceiptModel;
import com.borngroup.ssl.fulfilmentprocess.model.SSLRefundVoucherModel;
import com.borngroup.ssl.fulfilmentprocess.model.SalesDataEntryModel;

/**
 * @author arunverma
 *
 */
public interface GSTDocumentsDao {
    /**
     * Method to find SSLAdvanceReceiptModel based on invoice number
     *
     * @param ConsignmentEntryModel
     * @return SSLAdvanceReceiptModel
     */
    SSLAdvanceReceiptModel getAdvanceReceiptModelByNumber(ConsignmentEntryModel consignmentEntryModel);

    /**
     * Method to find SSLAdvanceReceiptModel based on advance receipt number
     */
    List<SSLAdvanceReceiptModel> getAdvanceReceiptModelByARNumber(OrderModel orderModel);

    /**
     * Method to find SSLAdvanceReceiptModel based on advance receipt number
     */
    List<SSLAdvanceReceiptModel> getAdvanceReceiptModelByOrderNumber(String orderCode);

    /**
     * @param centralLoc
     * @return List<InvoiceNumGeneratorModel>
     */
    List<InvoiceNumGeneratorModel> getInvoiceGenratorList(PointOfServiceModel centralLoc);

    /**
     * Method to fetch list of refund vouchers for a particular order
     *
     * @param advanceReceiptNo
     * @return
     */
    List<SSLRefundVoucherModel> getRefundVoucherModels(String orderNumber);

    /**
     * Method to get Sales Data Models based on product and invoiceNumber
     *
     * @param product
     * @param invoiceNumber
     * @return
     */
    SalesDataEntryModel getSalesDataModel(String product, String invoiceNumber);

    /**
     * Method to fetch Advance Receipt model using order
     *
     * @param orderModel
     * @return
     */
    SSLAdvanceReceiptModel getAdvanceReceiptModelByReceiptNumber(final OrderModel orderModel);

}
