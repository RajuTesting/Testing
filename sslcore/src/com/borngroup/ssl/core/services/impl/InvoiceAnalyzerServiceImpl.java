package com.borngroup.ssl.core.services.impl;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.InvoiceAnalyzerDao;
import com.borngroup.ssl.core.model.InvoiceAnalyzerModel;
import com.borngroup.ssl.core.services.InvoiceAnalyzerService;

/**
 * <p>
 * <p>
 * InvoiceAnalyzerServiceImpl.java : Invoice analyzer service impl. 
 * <p>
 * Created By : anupam.srivastava@nagarro.com
 *
 * @author Ssl  
 */
public class InvoiceAnalyzerServiceImpl implements InvoiceAnalyzerService {

    @Resource(name = "invoiceAnalyzerDao")
    private InvoiceAnalyzerDao invoiceAnalyzerDao;

    @Override
    public InvoiceAnalyzerModel findByInvoiceNumber(final String invoiceNumber) {
        final InvoiceAnalyzerModel invoiceAnalyzerModel = invoiceAnalyzerDao.findByInvoiceNumber(invoiceNumber);
        return invoiceAnalyzerModel;
    }
}
