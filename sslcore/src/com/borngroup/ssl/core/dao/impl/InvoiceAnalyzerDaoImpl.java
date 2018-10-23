package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.Collections;
import java.util.List;

import com.borngroup.ssl.core.dao.InvoiceAnalyzerDao;
import com.borngroup.ssl.core.model.InvoiceAnalyzerModel;

/**
 * <p>
 * <p>
 * InvoiceAnalyzerDaoImpl.java : Implementation Class is designed to makesure no duplicate invoicenumber is inserted. 
 * <p>
 * Created By : anupam.srivastava@nagarro.com  
 *
 * @author Ssl  
 */
public class InvoiceAnalyzerDaoImpl extends DefaultGenericDao<InvoiceAnalyzerModel> implements InvoiceAnalyzerDao {

    public InvoiceAnalyzerDaoImpl() {
        super("InvoiceAnalyzer");
    }

    @Override
    public InvoiceAnalyzerModel findByInvoiceNumber(final String invoiceNumber) {
        final List<InvoiceAnalyzerModel> invoiceList = find(Collections.singletonMap("invoiceNumber", invoiceNumber));
        return (invoiceList.iterator().hasNext() ? invoiceList.iterator().next() : null);
    }
}
