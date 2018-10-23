/**
 *
 */
package com.borngroup.ssl.core.dao;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.promotions.util.Pair;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.store.BaseStoreModel;

import java.util.List;

/**
 * @author bhavya2486
 *
 */
public interface SSLReturnOrderDao {

    public SearchPageData<OrderModel> getReturnOrders(final CustomerModel customerModel, final BaseStoreModel store,
                                                      final OrderStatus[] status, PageableData pageableData);

    public SearchPageData<ReturnRequestModel> getReturnOrdersDetails(final CustomerModel customerModel, final BaseStoreModel store,
            PageableData pageableData);

}
