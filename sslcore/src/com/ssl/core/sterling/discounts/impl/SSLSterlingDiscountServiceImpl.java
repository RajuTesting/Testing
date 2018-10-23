/**
 *
 */
package com.ssl.core.sterling.discounts.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.promotions.util.Pair;
import de.hybris.platform.util.DiscountValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.ssl.core.sterling.discounts.SSLDiscountCalculationService;
import com.ssl.core.sterling.discounts.SSLSterlingDiscountService;

/**
 * @author manikmalhotra
 *
 */
public class SSLSterlingDiscountServiceImpl implements SSLSterlingDiscountService {

    @Resource(name = "itemPromotionCalculationService")
    SSLDiscountCalculationService itemPromotionCalculationService;

    @Resource(name = "orderPromotionCalculationService")
    SSLDiscountCalculationService orderPromotionCalculationService;

    @Resource(name = "voucherCalculationService")
    SSLDiscountCalculationService voucherCalculationService;

    @Override
    public Map<String, Map<AbstractOrderEntryModel, List<Pair<Double, String>>>> calculateOrderDiscounts(final OrderModel order) {

        final Map<String, Double> globalDiscountValues = new HashMap<>();

        final Map<AbstractOrderEntryModel, Map<String, Double>> entryDiscountValues = new HashMap<>();

        // Order and Item Discounts
        for (final DiscountValue val : order.getGlobalDiscountValues()) {
            globalDiscountValues.put(val.getCode().toLowerCase(), Double.valueOf(val.getValue()));
        }

        // Iterate all order entry discounts
        for (final AbstractOrderEntryModel entry : order.getEntries()) {
            for (final DiscountValue val : entry.getDiscountValues()) {
                if (entryDiscountValues.get(entry) == null) {
                    entryDiscountValues.put(entry, new HashMap<String, Double>());
                }
                entryDiscountValues.get(entry).put(val.getCode().toLowerCase(),
                        Double.valueOf(val.getValue() * entry.getQuantity().doubleValue()));
            }
        }

        final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> itemPromotions = itemPromotionCalculationService.calculateDiscount(
                order, globalDiscountValues, entryDiscountValues, null, null);

        final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> orderPromotions = orderPromotionCalculationService
                .calculateDiscount(order, globalDiscountValues, entryDiscountValues, itemPromotions, null);

        final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> vouchers = voucherCalculationService.calculateDiscount(
                order, globalDiscountValues, entryDiscountValues, itemPromotions, orderPromotions);

        final Map<String, Map<AbstractOrderEntryModel, List<Pair<Double, String>>>> promotionDetailsMap = new HashMap<>();
        promotionDetailsMap.put(SslCoreConstants.SterlingConstants.ITEMPROMOTIONS, itemPromotions);
        promotionDetailsMap.put(SslCoreConstants.SterlingConstants.ORDERPROMOTIONS, orderPromotions);
        promotionDetailsMap.put(SslCoreConstants.SterlingConstants.VOUCHERS, vouchers);

        return promotionDetailsMap;
    }

}
