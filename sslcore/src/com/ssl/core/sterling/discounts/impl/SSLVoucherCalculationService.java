/**
 *
 */
package com.ssl.core.sterling.discounts.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.price.DiscountModel;
import de.hybris.platform.promotions.model.AbstractPromotionActionModel;
import de.hybris.platform.promotions.model.OrderPromotionModel;
import de.hybris.platform.promotions.model.ProductPromotionModel;
import de.hybris.platform.promotions.model.PromotionResultModel;
import de.hybris.platform.promotions.util.Pair;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.voucher.model.VoucherModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.util.CommonHelper;
import com.ssl.core.sterling.discounts.SSLDiscountCalculationService;

/**
 * @author manikmalhotra
 *
 */
public class SSLVoucherCalculationService implements SSLDiscountCalculationService {

    private static final String ORDER_CODE = "Order Code: ";
    private final CommonHelper commonHelper = CommonHelper.getInstance();
    private static final Logger LOG = Logger.getLogger(SSLVoucherCalculationService.class);

    @Resource(name = "modelService")
    private ModelService modelService;

    /*
     * (non-Javadoc)
     *
     * @see com.ssl.core.sterling.discounts.SSLDiscountCalculationService#calculateDiscount(de.hybris.platform.core.model.order.OrderModel,
     * java.util.Map, java.util.Map, java.util.Map, java.util.Map)
     */
    @Override
    public Map<AbstractOrderEntryModel, List<Pair<Double, String>>> calculateDiscount(final OrderModel order,
            final Map<String, Double> globalDiscountValues, final Map<AbstractOrderEntryModel, Map<String, Double>> entryDiscountValues,
            final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> itemPromotions,
            final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> orderPromotions) {

        modelService.refresh(order);

        double voucherDiscountValue = 0d;

        final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> voucherPromotions = new HashMap<>();

        final Map<AbstractOrderEntryModel, Double> entryExcludingPromotions = new HashMap<>();

        order.getGlobalDiscountValues().forEach(
                discountVal -> {
                    LOG.info(ORDER_CODE + order.getCode() + " Global Discount Code> " + discountVal.getCode() + " ,Global Discount Value "
                            + discountVal.getValue());
                });

        order.getDiscounts().forEach(discount -> {
            LOG.info(ORDER_CODE + order.getCode() + " Discount Code> " + discount.getCode());
        });

        for (final DiscountValue val : order.getGlobalDiscountValues()) {
            // Iterate in Discount Model to get the Voucher discounts
            for (final DiscountModel disc : order.getDiscounts()) {
                if (disc.getCode().equals(val.getCode()) && disc instanceof VoucherModel) {
                    voucherDiscountValue += val.getValue();
                }
            }
        }

        if (voucherDiscountValue == 0 && CollectionUtils.isNotEmpty(order.getGlobalDiscountValues())) {

            // Fail Over for calculating voucher amount if the details are not present in OrderModel.getDiscount
            LOG.info(ORDER_CODE + order.getCode() + " ,Voucher Not found, Executing Fail Over Strategy.");
            final Set<String> allPromotionGuid = getAllItemOrderPromotionsGuid(order);
            int voucherCount = 0;
            for (final DiscountValue val : order.getGlobalDiscountValues()) {
                if (!allPromotionGuid.contains(val.getCode().toLowerCase())) {
                    voucherDiscountValue += val.getValue();
                    voucherCount++;
                }
            }
            if (voucherCount > 1) {
                LOG.info(ORDER_CODE + order.getCode()
                        + " ,More than one Global Discounts were found in order not having promotion results. Skipping voucher value.");
                voucherDiscountValue = 0d;
            }
            if (voucherDiscountValue > 0) {
                LOG.info(ORDER_CODE + order.getCode() + " , voucher Amount found using Fail Over Strategy : " + voucherDiscountValue);
            }
        }

        double totalApportionedVoucherValue = 0d;
        if (voucherDiscountValue > 0d) {
            // Total Value on which voucher is to be apportioned
            double totalOrderValueExcludingPromotions = 0d;
            for (final AbstractOrderEntryModel entry : order.getEntries()) {
                double itemPromotionValue = 0d;
                double orderPromotionValue = 0d;
                if (itemPromotions.get(entry) != null) {
                    itemPromotionValue = itemPromotions.get(entry).get(0).getKey().doubleValue();
                }

                if (orderPromotions.get(entry) != null) {
                    orderPromotionValue = orderPromotions.get(entry).get(0).getKey().doubleValue();
                }

                final double orderEntryValueExcludingPromotions = (entry.getBasePrice().doubleValue() * entry.getQuantity().doubleValue())
                        - itemPromotionValue - orderPromotionValue;
                entryExcludingPromotions.put(entry, Double.valueOf(orderEntryValueExcludingPromotions));
                totalOrderValueExcludingPromotions += orderEntryValueExcludingPromotions;
            }
            if (totalOrderValueExcludingPromotions > 0d) {
                for (final AbstractOrderEntryModel entry : order.getEntries()) {
                    final double orderEntryValueExcludingPromotions = entryExcludingPromotions.get(entry).doubleValue();

                    final double apportionedVoucherValue = commonHelper
                            .getRoundedAmount((orderEntryValueExcludingPromotions * voucherDiscountValue)
                                    / totalOrderValueExcludingPromotions);

                    totalApportionedVoucherValue += apportionedVoucherValue;

                    final List<Pair<Double, String>> voucherList = new ArrayList<>(2);
                    voucherList.add(new Pair<>(Double.valueOf(apportionedVoucherValue), StringUtils.EMPTY));
                    voucherPromotions.put(entry, voucherList);
                }
                apportionResidualAmount(order, voucherDiscountValue, voucherPromotions, totalApportionedVoucherValue);
            }
        }
        LOG.info(ORDER_CODE + order.getCode() + " Voucher Promotion Amount to be apportioned is :" + voucherDiscountValue
                + " ,Details are: ");
        if (LOG.isDebugEnabled()) {

            voucherPromotions
                    .entrySet()
                    .stream()
                    .forEach(
                            entry -> {
                                LOG.debug(ORDER_CODE + order.getCode() + "Product> "
                                        + (entry.getKey() != null ? entry.getKey().getProduct().getCode() : StringUtils.EMPTY));
                                LOG.debug(ORDER_CODE
                                        + order.getCode()
                                        + "Voucher Amount > "
                                        + (entry.getValue() != null && entry.getValue().get(0) != null ? entry.getValue().get(0).getKey()
                                                : StringUtils.EMPTY));
                            });
        }
        return voucherPromotions;
    }

    /**
     * @param order
     * @param voucherDiscountValue
     * @param voucherPromotions
     * @param totalApportionedVoucherValue
     */
    private void apportionResidualAmount(final OrderModel order, final double voucherDiscountValue,
            final Map<AbstractOrderEntryModel, List<Pair<Double, String>>> voucherPromotions, final double totalApportionedVoucherValue) {
        final double residualAmount = voucherDiscountValue - totalApportionedVoucherValue;
        if (residualAmount != 0d) {
            final AbstractOrderEntryModel entry = order.getEntries().get(order.getEntries().size() - 1);
            final double apportionedAmount = voucherPromotions.get(entry).get(0).getKey().doubleValue();
            voucherPromotions.get(entry).get(0).setKey(Double.valueOf(commonHelper.getRoundedAmount(apportionedAmount + residualAmount)));
        }
    }

    private Set<String> getAllItemOrderPromotionsGuid(final OrderModel order) {
        final Set<String> allPromotionActionGuid = new HashSet<>();
        List<PromotionResultModel> itemOrderPromotionResults = null;
        if (CollectionUtils.isNotEmpty(order.getAllPromotionResults())) {
            // Iterate all Product Promotions
            itemOrderPromotionResults = order.getAllPromotionResults().stream()
                    .filter(pr -> pr.getPromotion() instanceof ProductPromotionModel || pr.getPromotion() instanceof OrderPromotionModel)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        for (final PromotionResultModel itemOrderPromotionResult : itemOrderPromotionResults) {
            for (final AbstractPromotionActionModel promotionAction : itemOrderPromotionResult.getActions()) {
                allPromotionActionGuid.add(promotionAction.getGuid().toLowerCase());
            }
        }

        return allPromotionActionGuid;
    }

}
