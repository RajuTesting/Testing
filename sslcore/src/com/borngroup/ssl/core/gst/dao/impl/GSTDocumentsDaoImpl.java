/**
 *
 */
package com.borngroup.ssl.core.gst.dao.impl;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

import com.borngroup.ssl.core.gst.dao.GSTDocumentsDao;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;
import com.borngroup.ssl.fulfilmentprocess.model.InvoiceNumGeneratorModel;
import com.borngroup.ssl.fulfilmentprocess.model.SSLAdvanceReceiptModel;
import com.borngroup.ssl.fulfilmentprocess.model.SSLRefundVoucherModel;
import com.borngroup.ssl.fulfilmentprocess.model.SalesDataEntryModel;

/**
 * @author arunverma
 *
 */
public class GSTDocumentsDaoImpl implements GSTDocumentsDao {
    private static final Logger LOG = Logger.getLogger(GSTDocumentsDaoImpl.class);

    private FlexibleSearchService flexibleSearchService;

    private ModelService modelService;

    @Override
    public SSLAdvanceReceiptModel getAdvanceReceiptModelByNumber(final ConsignmentEntryModel consignmentEntry) {
        final String query = "SELECT {s." + SSLAdvanceReceiptModel.PK + "} FROM {" + SSLAdvanceReceiptModel._TYPECODE + " as s }"
                + " WHERE {s.invoiceNumber}=?invoiceNumber";
        final FlexibleSearchQuery fpQuery = new FlexibleSearchQuery(query);
        fpQuery.addQueryParameter("invoiceNumber", consignmentEntry.getConsignment().getInvoiceNumber());
        final SearchResult<SSLAdvanceReceiptModel> searchResult = getFlexibleSearchService().search(fpQuery);
        if (searchResult.getResult() != null && !searchResult.getResult().isEmpty() && searchResult.getCount() > 0) {
            return searchResult.getResult().get(0);
        }
        return null;

    }

    public List<InvoiceNumGeneratorModel> getInvoiceGenratorList(final PointOfServiceModel centralLoc) {
        final LocalDate localDate = new LocalDate();
        final Date currentDate = localDate.toDate();
        final FlexibleSearchQuery query = new FlexibleSearchQueryBuilder().from(InvoiceNumGeneratorModel._TYPECODE, "i")
                .select("i", ItemModel.PK).whereEquals("i", InvoiceNumGeneratorModel.STOREID, centralLoc.getStockLocationCode())
                .whereEquals("i", InvoiceNumGeneratorModel.DATEOFINVOICE, currentDate).build();
        final SearchResult<InvoiceNumGeneratorModel> result = flexibleSearchService.search(query);
        final List<InvoiceNumGeneratorModel> repository = result.getResult();
        return repository;
    }

    @Override
    public List<SSLAdvanceReceiptModel> getAdvanceReceiptModelByOrderNumber(final String orderCode) {
        final FlexibleSearchQuery query = new FlexibleSearchQueryBuilder().from(SSLAdvanceReceiptModel._TYPECODE, "i")
                .select("i", ItemModel.PK).whereEquals("i", SSLAdvanceReceiptModel.ORDERNUMBER, orderCode).build();
        final SearchResult<SSLAdvanceReceiptModel> searchResult = getFlexibleSearchService().search(query);
        if (searchResult.getResult() != null && !searchResult.getResult().isEmpty() && searchResult.getCount() > 0) {
            return searchResult.getResult();
        }
        return null;
    }

    @Override
    public List<SSLRefundVoucherModel> getRefundVoucherModels(final String orderNumber) {
        final FlexibleSearchQuery query = new FlexibleSearchQueryBuilder().from(SSLRefundVoucherModel._TYPECODE, "i")
                .select("i", ItemModel.PK).whereEquals("i", SSLRefundVoucherModel.ORDERNUMBER, orderNumber).build();

        final SearchResult<SSLRefundVoucherModel> result = flexibleSearchService.search(query);
        final List<SSLRefundVoucherModel> refundVouchers = result.getResult();

        return refundVouchers;
    }

    @Override
    public SalesDataEntryModel getSalesDataModel(final String product, final String invoiceNumber) {
        final FlexibleSearchQuery query = new FlexibleSearchQueryBuilder().from(SalesDataEntryModel._TYPECODE, "i")
                .select("i", ItemModel.PK).whereEquals("i", SalesDataEntryModel.PRODUCTCODE, product)
                .whereEquals("i", SalesDataEntryModel.INVOICENUMBER, invoiceNumber).build();

        final SearchResult<SalesDataEntryModel> salesData = flexibleSearchService.search(query);
        if (null != salesData && CollectionUtils.isNotEmpty(salesData.getResult())) {
            return salesData.getResult().get(0);
        }
        return null;
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public SSLAdvanceReceiptModel getAdvanceReceiptModelByReceiptNumber(final OrderModel orderModel) {
        final String query = "SELECT {s." + SSLAdvanceReceiptModel.PK + "} FROM {" + SSLAdvanceReceiptModel._TYPECODE + " as s }"
                + " WHERE {s.orderNumber}=?orderNumber";
        final FlexibleSearchQuery fpQuery = new FlexibleSearchQuery(query);
        fpQuery.addQueryParameter("orderNumber", orderModel.getCode());
        final SearchResult<SSLAdvanceReceiptModel> searchResult = getFlexibleSearchService().search(fpQuery);
        if (searchResult.getResult() != null && !searchResult.getResult().isEmpty() && searchResult.getCount() > 0) {
            return searchResult.getResult().get(0);
        }
        return null;
    }

    @Override
    public List<SSLAdvanceReceiptModel> getAdvanceReceiptModelByARNumber(final OrderModel orderModel) {
        // YTODO Auto-generated method stub
        return null;
    }

}
