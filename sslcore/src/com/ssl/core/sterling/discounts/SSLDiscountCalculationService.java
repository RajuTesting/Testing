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
public interface SSLDiscountCalculationService {
    Map<AbstractOrderEntryModel, List<Pair<Double, String>>> calculateDiscount(OrderModel order, Map<String, Double> globalDiscountValues,
            Map<AbstractOrderEntryModel, Map<String, Double>> entryDiscountValues,
            final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> itemPromotions,
            final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> orderPromotions);
}
