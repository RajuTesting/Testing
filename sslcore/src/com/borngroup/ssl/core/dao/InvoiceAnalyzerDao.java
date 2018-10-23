package com.borngroup.ssl.core.dao;

import com.borngroup.ssl.core.model.InvoiceAnalyzerModel;

/**
 * <p>
 * <p>
 * InvoiceAnalyzerDao.java : Class is designed to makesure no duplicate invoicenumber is inserted. 
 * <p>
 * Created By : anupam.srivastava@nagarro.com  
 *
 * @author Ssl  
 */
public interface InvoiceAnalyzerDao {
    /**
     * Method to get if invoice number is already available
     *
     * @param invoiceNumber : Invoice number
     * @return InvoiceAnalyzerModel
     */
    InvoiceAnalyzerModel findByInvoiceNumber(String invoiceNumber);
}
