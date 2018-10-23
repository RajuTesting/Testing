package com.borngroup.ssl.core.gst.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import javax.annotation.Resource;

import com.borngroup.ssl.core.gst.dao.SSLReceiptVoucherDao;
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
public class SSLReceiptVoucherDaoImpl implements SSLReceiptVoucherDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    /**
     * Method to fetch all Advance Receipts.
     */
    @Override
    public List<SSLAdvanceReceiptModel> getAdvanceReceipts() {
        final String query = "SELECT {p:" + SSLAdvanceReceiptModel.PK + "} FROM {" + SSLAdvanceReceiptModel._TYPECODE + " AS p}";
        final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query);
        return flexibleSearchService.<SSLAdvanceReceiptModel> search(flexQuery).getResult();
    }

    /**
     * Method to find Advance Receipts for order.
     */
    @Override
    public List<SSLAdvanceReceiptModel> getAdvanceReceiptsForOrder(final String orderNumber) {
        final String query = "SELECT {p:" + SSLAdvanceReceiptModel.PK + "} FROM {" + SSLAdvanceReceiptModel._TYPECODE
                + " AS p} where {p.orderNumber} = " + orderNumber;
        final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query);
        return flexibleSearchService.<SSLAdvanceReceiptModel> search(flexQuery).getResult();
    }

}
