package com.borngroup.ssl.core.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.store.BaseStoreModel;

/**
 * Created by nikhiljagtiani on 11/23/2016.
 */
public interface ReturnOrderService {

    public SearchPageData<OrderModel> getReturnOrderList(final CustomerModel customerModel, final BaseStoreModel store,
            final OrderStatus[] status, final PageableData pageableData);

    public SearchPageData<ReturnRequestModel> getReturnOrderDetails(final CustomerModel customerModel, final BaseStoreModel store,
            final PageableData pageableData);

}
