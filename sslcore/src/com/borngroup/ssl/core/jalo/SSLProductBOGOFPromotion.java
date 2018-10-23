/**
 *
 */
package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.promotions.jalo.PromotionOrderEntryConsumed;
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.jalo.PromotionsManager;
import de.hybris.platform.promotions.result.PromotionEvaluationContext;
import de.hybris.platform.promotions.result.PromotionOrderView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author NagarroDev
 *
 */
public class SSLProductBOGOFPromotion extends GeneratedSSLProductBOGOFPromotion {

    @Override
    public List<PromotionResult> evaluate(final SessionContext ctx, final PromotionEvaluationContext promoContext) {
        final List results = new ArrayList();
        final PromotionsManager.RestrictionSetResult restrictResult = findEligibleProductsInBasket(ctx, promoContext);
        if ((restrictResult.isAllowedToContinue()) && (!(restrictResult.getAllowedProducts().isEmpty()))) {
            final int qualifyingCount = getQualifyingCount(ctx).intValue();
            final int freeCount = getFreeCount(ctx).intValue();
            final PromotionsManager promotionsManager = PromotionsManager.getInstance();
            final PromotionOrderView orderView = promoContext
                    .createView(ctx, this, restrictResult.getAllowedProducts());

            while (orderView.getTotalQuantity(ctx) >= qualifyingCount) {
                promoContext.startLoggingConsumed(this);
                final Comparator comparator = PromotionEvaluationContext.createPriceComparator(ctx);

                final List<PromotionOrderEntryConsumed> nonFreeConsumedItems = orderView.consumeFromTail(ctx,
                        comparator, qualifyingCount - freeCount);
                final List<PromotionOrderEntryConsumed> freeConsumedItems = orderView.consumeFromHead(ctx, comparator,
                        freeCount);

                if (CollectionUtils.isNotEmpty(nonFreeConsumedItems) && CollectionUtils.isNotEmpty(freeConsumedItems)) {
                    double totalNonFreeEntriesPrice = 0.0D;
                    double totalFreeEntriesPrice = 0.0D;
                    for (final PromotionOrderEntryConsumed nonFreeConsumedItem : nonFreeConsumedItems) {
                        totalNonFreeEntriesPrice += nonFreeConsumedItem.getEntryPrice();
                    }
                    for (final PromotionOrderEntryConsumed freeConsumedItem : freeConsumedItems) {
                        totalFreeEntriesPrice += freeConsumedItem.getEntryPrice();
                    }
                    final double ratio = totalFreeEntriesPrice / (totalFreeEntriesPrice + totalNonFreeEntriesPrice);

                    final List actions = new ArrayList();

                    for (final PromotionOrderEntryConsumed poec : freeConsumedItems) {
                        final double adjustedUnitPrice = poec.getUnitPrice() * (1.0D - ratio);
                        poec.setAdjustedUnitPrice(ctx, adjustedUnitPrice);
                        final double adjustment = (poec.getEntryPrice(ctx) - adjustedUnitPrice
                                * poec.getQuantity().doubleValue())
                                * -1.0D;
                        actions.add(promotionsManager.createPromotionOrderEntryAdjustAction(ctx,
                                poec.getOrderEntry(ctx), adjustment));
                    }

                    for (final PromotionOrderEntryConsumed poec : nonFreeConsumedItems) {
                        final double adjustedUnitPrice = poec.getUnitPrice() * (1.0D - ratio);
                        poec.setAdjustedUnitPrice(ctx, adjustedUnitPrice);
                        final double adjustment = (poec.getEntryPrice(ctx) - adjustedUnitPrice
                                * poec.getQuantity().doubleValue())
                                * -1.0D;
                        actions.add(promotionsManager.createPromotionOrderEntryAdjustAction(ctx,
                                poec.getOrderEntry(ctx), adjustment));
                    }
                    final PromotionResult result = promotionsManager.createPromotionResult(ctx, this,
                            promoContext.getOrder(), 1.0F);
                    final List consumed = promoContext.finishLoggingAndGetConsumed(this, true);
                    result.setConsumedEntries(ctx, consumed);
                    result.setActions(ctx, actions);
                    results.add(result);
                }
            }

            final long remainingCount = orderView.getTotalQuantity(ctx);
            if (orderView.getTotalQuantity(ctx) > 0L) {
                promoContext.startLoggingConsumed(this);
                orderView.consume(ctx, remainingCount);
                final float certainty = (float) remainingCount / qualifyingCount;
                final PromotionResult result = promotionsManager.createPromotionResult(ctx, this,
                        promoContext.getOrder(), certainty);
                result.setConsumedEntries(promoContext.finishLoggingAndGetConsumed(this, false));
                results.add(result);
            }
        }
        return results;
    }

}
