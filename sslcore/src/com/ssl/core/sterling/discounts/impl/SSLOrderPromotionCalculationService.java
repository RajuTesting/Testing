/**
 *
 */
package com.ssl.core.sterling.discounts.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.promotions.model.AbstractPromotionActionModel;
import de.hybris.platform.promotions.model.OrderPromotionModel;
import de.hybris.platform.promotions.model.PromotionResultModel;
import de.hybris.platform.promotions.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.util.CommonHelper;
import com.ssl.core.sterling.discounts.SSLDiscountCalculationService;

/**
 * @author manikmalhotra
 *
 */
public class SSLOrderPromotionCalculationService implements SSLDiscountCalculationService {

    private final CommonHelper commonHelper = CommonHelper.getInstance();
    private static final Logger LOG = Logger.getLogger(SSLOrderPromotionCalculationService.class);

    @Override
    public Map<AbstractOrderEntryModel, List<Pair<Double, String>>> calculateDiscount(final OrderModel order,
            final Map<String, Double> globalDiscountValues, final Map<AbstractOrderEntryModel, Map<String, Double>> entryDiscountValues,
            final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> itemPromotions,
            final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> orderPromotions) {

        final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> orderPromotionDetailsMap = new LinkedHashMap();
        final Map<AbstractOrderEntryModel, Double> entryExcludingItemPromotionsDetail = new HashMap<>();

        final List<PromotionResultModel> orderPromotionResults = getAllOrderPromotions(order);
        double apportionedOrderPromotions = 0d;
        if (CollectionUtils.isNotEmpty(orderPromotionResults)) {
            orderPromotionResults.stream().forEach(promo -> {
                LOG.debug("Order Code: " + order.getCode() + " Applicable Order Promotions PK are > " + promo.getPromotion().getPk());
            });
            final double totalOrderPromotionValue = getTotalOrderPromotionValue(globalDiscountValues, orderPromotionResults);

            // Total Entries amount on which order promotion is to be apportioned.
            double totalEntryValueExcludingItemPromotion = 0d;
            if (totalOrderPromotionValue > 0) {
                for (final AbstractOrderEntryModel orderEntry : order.getEntries()) {

                    double itemPromotionValue = 0d;
                    if (itemPromotions.get(orderEntry) != null && itemPromotions.get(orderEntry).get(0).getKey() != null) {
                        itemPromotionValue = itemPromotions.get(orderEntry).get(0).getKey().doubleValue();
                    }
                    final double orderEntryValueExcludingItemPromotion = commonHelper.getRoundedAmount(orderEntry.getBasePrice()
                            .doubleValue() * orderEntry.getQuantity().doubleValue() - itemPromotionValue);
                    totalEntryValueExcludingItemPromotion += orderEntryValueExcludingItemPromotion;
                    entryExcludingItemPromotionsDetail.put(orderEntry, Double.valueOf(orderEntryValueExcludingItemPromotion));
                }
                if (totalEntryValueExcludingItemPromotion > 0d) {
                    for (final AbstractOrderEntryModel entry : order.getEntries()) {

                        final double entryValueExcludingItemPromotion = entryExcludingItemPromotionsDetail.get(entry).doubleValue();

                        final double orderPromotion = (entryValueExcludingItemPromotion * totalOrderPromotionValue)
                                / totalEntryValueExcludingItemPromotion;

                        prePopulatePromotionDetails(orderPromotionDetailsMap, orderPromotionResults.get(0), entry);

                        final double orderPrmValue = commonHelper.getRoundedAmount(orderPromotion);
                        apportionedOrderPromotions += orderPrmValue;
                        orderPromotionDetailsMap.get(entry).get(0).setKey(Double.valueOf(orderPrmValue));
                    }
                    apportionResidualAmount(order, orderPromotionDetailsMap, apportionedOrderPromotions, totalOrderPromotionValue);
                }
            }

        }
        LOG.info("Order Code: " + order.getCode() + " ,Order Promotions Details are:");
        if (LOG.isDebugEnabled()) {
            orderPromotionDetailsMap
                    .entrySet()
                    .stream()
                    .forEach(
                            entry -> {
                                LOG.debug("Order Code: " + order.getCode() + "Product> "
                                        + (entry.getKey() != null ? entry.getKey().getProduct().getCode() : StringUtils.EMPTY));
                                LOG.debug("Order Code: " + order.getCode() + "Promotion Amount > "
                                        + (entry.getValue() != null && entry.getValue().get(0) != null ? entry.getValue().get(0).getKey()
                                                : StringUtils.EMPTY));
                                LOG.debug("Order Code: " + order.getCode() + "Scheme Code > "
                                        + (entry.getValue() != null && entry.getValue().get(0) != null ? entry.getValue().get(0).getValue()
                                                : StringUtils.EMPTY));
                            });
        }
        return orderPromotionDetailsMap;
    }

    private double getTotalOrderPromotionValue(final Map<String, Double> globalDiscountValues,
            final List<PromotionResultModel> orderPromotionResults) {
        double totalOrderPromotionValue = 0d;
        for (final PromotionResultModel orderPromotionResult : orderPromotionResults) {
            for (final AbstractPromotionActionModel promotionAction : orderPromotionResult.getActions()) {
                totalOrderPromotionValue += globalDiscountValues.get(promotionAction.getGuid().toLowerCase()).doubleValue();
            }
        }
        return totalOrderPromotionValue;
    }

    /**
     * @param order
     * @param orderPromotionDetailsMap
     * @param apportionedOrderPromotions
     * @param totalOrderPromotionValue
     */
    private void apportionResidualAmount(final OrderModel order,
            final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> orderPromotionDetailsMap,
            final double apportionedOrderPromotions, final double totalOrderPromotionValue) {
        final double residualAmount = totalOrderPromotionValue - apportionedOrderPromotions;
        if (residualAmount != 0d) {
            final AbstractOrderEntryModel entry = order.getEntries().get(order.getEntries().size() - 1);
            final double previousValue = orderPromotionDetailsMap.get(entry).get(0).getKey().doubleValue();
            orderPromotionDetailsMap.get(entry).get(0)
                    .setKey(Double.valueOf(commonHelper.getRoundedAmount(previousValue + residualAmount)));
        }
    }

    private void prePopulatePromotionDetails(final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> itemPromotionsDetailsMap,
            final PromotionResultModel promotionResult, final AbstractOrderEntryModel orderEntry) {
        if (itemPromotionsDetailsMap.get(orderEntry) == null) {
            final ArrayList pairList = new ArrayList<Pair<Double, String>>(2);
            pairList.add(new Pair<Double, String>(Double.valueOf(0), promotionResult.getPromotion().getSchemeCode()));
            itemPromotionsDetailsMap.put(orderEntry, pairList);
        }
    }

    private List<PromotionResultModel> getAllOrderPromotions(final OrderModel order) {
        List<PromotionResultModel> orderPromotionResults = null;
        if (CollectionUtils.isNotEmpty(order.getAllPromotionResults())) {
            // Iterate all Promotions
            orderPromotionResults = order.getAllPromotionResults().stream().filter(pr -> pr.getPromotion() instanceof OrderPromotionModel)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return orderPromotionResults;
    }
}
