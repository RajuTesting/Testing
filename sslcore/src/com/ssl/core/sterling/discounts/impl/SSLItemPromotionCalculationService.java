/**
 *
 */
package com.ssl.core.sterling.discounts.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.promotions.model.AbstractPromotionActionModel;
import de.hybris.platform.promotions.model.ProductPromotionModel;
import de.hybris.platform.promotions.model.PromotionOrderEntryConsumedModel;
import de.hybris.platform.promotions.model.PromotionResultModel;
import de.hybris.platform.promotions.util.Pair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
public class SSLItemPromotionCalculationService implements SSLDiscountCalculationService {

    private final CommonHelper commonHelper = CommonHelper.getInstance();
    private static final Logger LOG = Logger.getLogger(SSLItemPromotionCalculationService.class);

    @Override
    public Map<AbstractOrderEntryModel, List<Pair<Double, String>>> calculateDiscount(final OrderModel order,
            final Map<String, Double> globalDiscountValues, final Map<AbstractOrderEntryModel, Map<String, Double>> entryDiscountValues,
            final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> itemPromotions,
            final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> orderPromotions) {

        final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> itemPromotionsDetailsMap = new LinkedHashMap();

        final List<PromotionResultModel> itemPromotionResults = getAllItemPromotions(order);

        if (CollectionUtils.isNotEmpty(itemPromotionResults)) {
            itemPromotionResults.stream().forEach(promo -> {
                LOG.debug("Order Code: " + order.getCode() + " ,Applicable Item Promotions PK are > " + promo.getPromotion().getPk());
            });

            for (final PromotionResultModel itempromotionResult : itemPromotionResults) {

                double apportionedItemDiscount = 0d;

                for (final AbstractPromotionActionModel promotionAction : itempromotionResult.getActions()) {

                    final double totalPromotionApportionableEntriesAmount = getPromotionApportionableEntriesAmount(itempromotionResult);

                    final Double itemPromotionAmount = globalDiscountValues.get(promotionAction.getGuid().toLowerCase());
                    // Iterate all global discounts
                    if (itemPromotionAmount != null && totalPromotionApportionableEntriesAmount > 0d) {
                        for (final PromotionOrderEntryConsumedModel prmotionOrderEntryConsumed : itempromotionResult.getConsumedEntries()) {
                            final AbstractOrderEntryModel prmotionOrderEntryModel = prmotionOrderEntryConsumed.getOrderEntry();
                            final double apportionedAmountOnOrderEntry = ((prmotionOrderEntryModel.getBasePrice().doubleValue() * prmotionOrderEntryConsumed
                                    .getQuantity().doubleValue()) * itemPromotionAmount.doubleValue())
                                    / totalPromotionApportionableEntriesAmount;
                            prePopulatePromotionDetails(itemPromotionsDetailsMap, itempromotionResult, prmotionOrderEntryModel);
                            final double existingItemPromotionValue = itemPromotionsDetailsMap
                                    .get(prmotionOrderEntryConsumed.getOrderEntry()).get(0).getKey().doubleValue();

                            final double apportionedDiscount = commonHelper.getRoundedAmount(existingItemPromotionValue
                                    + apportionedAmountOnOrderEntry);
                            apportionedItemDiscount += apportionedDiscount;

                            itemPromotionsDetailsMap.get(prmotionOrderEntryConsumed.getOrderEntry()).get(0)
                                    .setKey(Double.valueOf(apportionedDiscount));
                        }
                        apportionResidualAmount(itemPromotionsDetailsMap, itempromotionResult, apportionedItemDiscount, itemPromotionAmount);
                    } else { // Iterate all Entry level discounts
                        populateEntryDiscountValues(order, entryDiscountValues, itemPromotionsDetailsMap, itempromotionResult,
                                promotionAction);
                    }
                }
            }

        }
        LOG.info("Order Code: " + order.getCode() + " ,Item Promotions Details are:");
        if (LOG.isDebugEnabled()) {
            itemPromotionsDetailsMap
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
        return itemPromotionsDetailsMap;
    }

    private void apportionResidualAmount(final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> itemPromotionsDetailsMap,
            final PromotionResultModel itempromotionResult, final double apportionedItemDiscount, final Double itemPromotionAmount) {
        final Optional<PromotionOrderEntryConsumedModel> poec = itempromotionResult.getConsumedEntries().stream()
                .sorted((poec1, poec2) -> poec2.getOrderEntry().getEntryNumber().compareTo(poec1.getOrderEntry().getEntryNumber()))
                .findFirst();

        // Round off remaining amount on last order entry.
        final double residualAmount = itemPromotionAmount.doubleValue() - apportionedItemDiscount;
        if (residualAmount != 0d) {
            final AbstractOrderEntryModel entry = poec.isPresent() ? poec.get().getOrderEntry() : itempromotionResult.getConsumedEntries()
                    .stream().findFirst().get().getOrderEntry();
            final double existingItemPromotionValue = itemPromotionsDetailsMap.get(entry).get(0).getKey().doubleValue();
            itemPromotionsDetailsMap.get(entry).get(0)
                    .setKey(Double.valueOf(commonHelper.getRoundedAmount(existingItemPromotionValue + residualAmount)));
        }
    }

    private void populateEntryDiscountValues(final OrderModel order,
            final Map<AbstractOrderEntryModel, Map<String, Double>> entryDiscountValues,
            final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> itemPromotionsDetailsMap,
            final PromotionResultModel itempromotionResult, final AbstractPromotionActionModel promotionAction) {
        for (final AbstractOrderEntryModel orderEntry : order.getEntries()) {
            final Map<String, Double> entryLevelDiscountValues = entryDiscountValues.get(orderEntry);
            if (entryLevelDiscountValues == null) {
                continue;
            }
            final Double promotionEntryValue = entryLevelDiscountValues.get(promotionAction.getGuid().toLowerCase());
            if (promotionEntryValue != null) {
                prePopulatePromotionDetails(itemPromotionsDetailsMap, itempromotionResult, orderEntry);
                final double existingItemPromotionValue = itemPromotionsDetailsMap.get(orderEntry).get(0).getKey().doubleValue();

                itemPromotionsDetailsMap.get(orderEntry).get(0)
                        .setKey(Double.valueOf(promotionEntryValue.doubleValue() + existingItemPromotionValue));
            }
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

    private List<PromotionResultModel> getAllItemPromotions(final OrderModel order) {
        List<PromotionResultModel> itemPromotionResults = null;
        if (CollectionUtils.isNotEmpty(order.getAllPromotionResults())) {
            // Iterate all Product Promotions
            itemPromotionResults = order.getAllPromotionResults().stream().filter(pr -> pr.getPromotion() instanceof ProductPromotionModel)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return itemPromotionResults;
    }

    private double getPromotionApportionableEntriesAmount(final PromotionResultModel itempromotion) {
        // get all applicable Order Entries for apportioning promotion values.
        double totalApportionableEntriesAmount = 0d;
        for (final PromotionOrderEntryConsumedModel consumedEntries : itempromotion.getConsumedEntries()) {
            totalApportionableEntriesAmount += consumedEntries.getOrderEntry().getBasePrice().doubleValue()
                    * consumedEntries.getQuantity().doubleValue();
        }
        return totalApportionableEntriesAmount;
    }
}
