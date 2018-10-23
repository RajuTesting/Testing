package com.borngroup.ssl.core.gst.dao;

import java.util.List;

import com.borngroup.ssl.fulfilmentprocess.model.SSLAdvanceReceiptModel;

/**
 * <p>
 * <p>
 * AdvanceReceiptDao.java : .  * 
 * <p>
 * Created By : anupam.srivastava@nagarro.com.
 *
 * @author Ssl  
 */
public interface SSLReceiptVoucherDao {
    /**
     * Method to fetch all Advance Receipts.
     */
    public List<SSLAdvanceReceiptModel> getAdvanceReceipts();

    /**
     * Method to find Advance Receipts for order.
     */
    public List<SSLAdvanceReceiptModel> getAdvanceReceiptsForOrder(String orderNumber);
}
