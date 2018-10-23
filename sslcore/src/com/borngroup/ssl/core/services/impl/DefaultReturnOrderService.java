package com.borngroup.ssl.core.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.store.BaseStoreModel;

import com.borngroup.ssl.core.dao.SSLReturnOrderDao;
import com.borngroup.ssl.core.services.ReturnOrderService;

/**
 * Created by nikhiljagtiani on 11/23/2016.
 */
public class DefaultReturnOrderService implements ReturnOrderService {

    SSLReturnOrderDao sslReturnOrderDao;

    public SSLReturnOrderDao getSslReturnOrderDao() {
        return sslReturnOrderDao;
    }

    public void setSslReturnOrderDao(final SSLReturnOrderDao sslReturnOrderDao) {
        this.sslReturnOrderDao = sslReturnOrderDao;
    }

    @Override
    public SearchPageData<OrderModel> getReturnOrderList(final CustomerModel customerModel, final BaseStoreModel store,
            final OrderStatus[] status, final PageableData pageableData) {
        validateParameterNotNull(customerModel, "Customer model cannot be null");
        validateParameterNotNull(store, "Store must not be null");
        validateParameterNotNull(pageableData, "PageableData must not be null");
        return getSslReturnOrderDao().getReturnOrders(customerModel, store, status, pageableData);
    }

    @Override
    public SearchPageData<ReturnRequestModel> getReturnOrderDetails(final CustomerModel customerModel, final BaseStoreModel store,
            final PageableData pageableData) {
        validateParameterNotNull(customerModel, "Customer model cannot be null");
        validateParameterNotNull(store, "Store must not be null");
        validateParameterNotNull(pageableData, "PageableData must not be null");
        return getSslReturnOrderDao().getReturnOrdersDetails(customerModel, store, pageableData);
    }

}
