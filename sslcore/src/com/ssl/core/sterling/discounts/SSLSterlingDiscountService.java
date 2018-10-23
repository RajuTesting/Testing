/**
 *
 */
package com.ssl.core.sterling.discounts;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.promotions.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * @author manikmalhotra
 *
 */
public interface SSLSterlingDiscountService {
    Map<String, Map<AbstractOrderEntryModel, List<Pair<Double, String>>>> calculateOrderDiscounts(OrderModel order);
}
