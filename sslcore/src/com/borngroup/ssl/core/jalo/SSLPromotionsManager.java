package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.ExtensibleItem;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.promotions.jalo.AbstractPromotion;
import de.hybris.platform.promotions.jalo.AbstractPromotionAction;
import de.hybris.platform.promotions.jalo.OrderPromotion;
import de.hybris.platform.promotions.jalo.ProductPromotion;
import de.hybris.platform.promotions.jalo.PromotionGroup;
import de.hybris.platform.promotions.jalo.PromotionOrderEntryConsumed;
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.jalo.PromotionsManager;
import de.hybris.platform.promotions.result.PromotionEvaluationContext;
import de.hybris.platform.promotions.result.PromotionOrderResults;
import de.hybris.platform.promotions.util.Helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author osheengulati
 *
 */
public class SSLPromotionsManager extends PromotionsManager {

    private static final Logger LOG = Logger.getLogger(SSLPromotionsManager.class.getName());

    @Override
    public PromotionOrderResults updatePromotions(final SessionContext ctx, final Collection<PromotionGroup> promotionGroups,
            final AbstractOrder order, final boolean evaluateRestrictions, final AutoApplyMode productPromotionMode,
            final AutoApplyMode orderPromotionMode, Date date) {
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("updatePromotions for [" + order + "] promotionGroups=[" + Helper.join(promotionGroups)
                        + "] evaluateRestrictions=[" + evaluateRestrictions + "] productPromotionMode=[" + productPromotionMode
                        + "] orderPromotionMode=[" + orderPromotionMode + "] date=[" + date + "]");
            }

            if (promotionGroups != null && order != null) {
                if (date == null) {
                    date = Helper.getDateNowRoundedToMinute();
                }

                if (!order.isCalculated(ctx).booleanValue()) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("updatePromotions order [" + order + "] not calculated, calculating");
                    }

                    order.calculate(date);
                }

                final ArrayList ex = new ArrayList();
                double oldTotalAppliedDiscount = 0.0D;
                final LinkedList results1 = new LinkedList();
                double newTotalAppliedDiscount1 = 0.0D;

                synchronized (order) {
                    final List results = this.getPromotionResultsInternal(ctx, order);
                    if (results != null && !results.isEmpty()) {
                        final Iterator arg13 = results.iterator();

                        label181: while (true) {
                            PromotionResult newTotalAppliedDiscount;
                            boolean appliedDiscountChange;
                            do {
                                do {
                                    do {
                                        if (!arg13.hasNext()) {
                                            break label181;
                                        }

                                        newTotalAppliedDiscount = (PromotionResult) arg13.next();
                                    } while (!newTotalAppliedDiscount.getFired(ctx));

                                    appliedDiscountChange = newTotalAppliedDiscount.isApplied(ctx);
                                    if (appliedDiscountChange) {
                                        oldTotalAppliedDiscount += newTotalAppliedDiscount.getTotalDiscount(ctx);
                                    }
                                } while (isValid(newTotalAppliedDiscount, ctx));
                            } while ((productPromotionMode != AutoApplyMode.KEEP_APPLIED
                                    || !(newTotalAppliedDiscount.getPromotion(ctx) instanceof ProductPromotion))
                                    && (orderPromotionMode != AutoApplyMode.KEEP_APPLIED
                                            || !(newTotalAppliedDiscount.getPromotion(ctx) instanceof OrderPromotion)));

                            if (appliedDiscountChange) {
                                final String activePromotions = getDataUnigueKey(ctx, newTotalAppliedDiscount);
                                if (activePromotions != null && activePromotions.length() > 0) {
                                    if (LOG.isDebugEnabled()) {
                                        LOG.debug("updatePromotions found applied PromotionResult [" + newTotalAppliedDiscount + "] key ["
                                                + activePromotions + "] that should be reapplied");
                                    }

                                    ex.add(activePromotions);
                                }
                            }
                        }
                    }

                    deleteStoredPromotionResults(ctx, order, true);
                    final Collection products = getBaseProductsForOrder(ctx, order);

                    final List activePromotions1 = findOrderAndProductPromotionsSortByPriority(ctx, this.getSession(), promotionGroups,
                            products, date);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("updatePromotions found [" + activePromotions1.size() + "] promotions to run");
                    }

                    if (!activePromotions1.isEmpty()) {
                        final ArrayList prKey = fixupVouchersRemoveVouchers(ctx, order);
                        final PromotionEvaluationContext promoContext = new PromotionEvaluationContext(order, evaluateRestrictions, date);

                        List promoResults;
                        for (final Iterator arg19 = activePromotions1.iterator(); arg19.hasNext(); results1.addAll(promoResults)) {
                            final AbstractPromotion promotion = (AbstractPromotion) arg19.next();
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("updatePromotions evaluating promotion [" + promotion + "]");
                            }

                            promoResults = this.evaluatePromotion(ctx, promoContext, promotion);
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("updatePromotions promotion [" + promotion + "] returned [" + promoResults.size() + "] results");
                            }

                            final boolean autoApply = this.autoApplyApplies(productPromotionMode, orderPromotionMode, promotion);
                            final boolean keepApplied = this.keepApplied(productPromotionMode, orderPromotionMode, promotion, autoApply);
                            boolean needsCalculateTotals = false;
                            if (autoApply || keepApplied) {
                                final Iterator arg25 = promoResults.iterator();

                                label141: while (true) {
                                    while (true) {
                                        PromotionResult pr;
                                        do {
                                            if (!arg25.hasNext()) {
                                                break label141;
                                            }

                                            pr = (PromotionResult) arg25.next();
                                        } while (!pr.getFired(ctx));

                                        if (autoApply) {
                                            if (LOG.isDebugEnabled()) {
                                                LOG.debug("updatePromotions auto applying result [" + pr + "] from promotion [" + promotion
                                                        + "]");
                                            }

                                            needsCalculateTotals |= pr.apply(ctx);
                                            newTotalAppliedDiscount1 += pr.getTotalDiscount(ctx);
                                        } else if (keepApplied) {
                                            final String prKey1 = getDataUnigueKey(ctx, pr);
                                            if (prKey1 != null && prKey1.length() != 0) {
                                                if (ex.remove(prKey1)) {
                                                    if (LOG.isDebugEnabled()) {
                                                        LOG.debug("updatePromotions keeping applied the result [" + pr
                                                                + "] from promotion [" + promotion + "]");
                                                    }

                                                    needsCalculateTotals |= pr.apply(ctx);
                                                    newTotalAppliedDiscount1 += pr.getTotalDiscount(ctx);
                                                }
                                            } else {
                                                LOG.error("updatePromotions promotion result [" + pr + "] from promotion [" + promotion
                                                        + "] returned NULL or Empty DataUnigueKey");
                                            }
                                        }
                                    }
                                }
                            }

                            if (needsCalculateTotals) {
                                order.calculateTotals(true);
                            }
                        }

                        fixupVouchersReapplyVouchers(ctx, order, prKey);
                    }

                    if (LOG.isDebugEnabled()) {
                        final Iterator promoContext1 = ex.iterator();

                        while (promoContext1.hasNext()) {
                            final String prKey2 = (String) promoContext1.next();
                            LOG.debug("updatePomrotions PromotionResult not reapplied because it did not fire [" + prKey2 + "]");
                        }
                    }
                }

                final double appliedDiscountChange1 = newTotalAppliedDiscount1 - oldTotalAppliedDiscount;
                if (LOG.isDebugEnabled()) {
                    LOG.debug("updatePromotions for [" + order + "] returned [" + results1.size()
                            + "] PromotionResults appliedDiscountChange=[" + appliedDiscountChange1 + "]");
                }

                return new PromotionOrderResults(ctx, order, Collections.unmodifiableList(results1), appliedDiscountChange1);
            }
        } catch (final Exception arg29) {
            LOG.error("Failed to updatePromotions", arg29);
        }

        return null;
    }

    /*
     * public AbstractPromotion getPromotion(final SessionContext ctx) { return (AbstractPromotion)
     * de.hybris.platform.jalo.ExtensibleItem.getProperty(ctx, "promotion"); }
     */

    protected String getDataUnigueKey(final SessionContext ctx, final PromotionResult newTotalAppliedDiscount) {
        final AbstractPromotion promotion = (AbstractPromotion) ((ExtensibleItem) newTotalAppliedDiscount).getProperty("promotion");
        return promotion != null ? getPromotionResultDataUnigueKey(promotion, ctx, newTotalAppliedDiscount) : null;
    }

    protected final String getPromotionResultDataUnigueKey(final AbstractPromotion promotion, final SessionContext ctx,
            final PromotionResult promotionResult) {
        final StringBuilder builder = new StringBuilder(255);
        builder.append(promotion.getClass().getSimpleName()).append('|');
        builder.append(promotion.getCode(ctx)).append('|');
        this.buildPromotionResultDataUnigueKey(ctx, promotionResult, builder);
        return builder.toString();
    }

    protected boolean isValid(final PromotionResult promotionResult, final SessionContext ctx) {
        try {
            final AbstractPromotion promotion = promotionResult.getPromotion(ctx);
            return promotion != null;
        } catch (final Exception arg2) {
            return false;
        }
    }

    protected void buildPromotionResultDataUnigueKey(final SessionContext ctx, final PromotionResult promotionResult,
            final StringBuilder builder) {
        builder.append(promotionResult.getCertainty(ctx)).append('|');
        builder.append(promotionResult.getCustom(ctx)).append('|');
        final Collection entries = promotionResult.getConsumedEntries(ctx);
        if (entries != null && !entries.isEmpty()) {
            final Iterator action = entries.iterator();

            while (action.hasNext()) {
                final PromotionOrderEntryConsumed actions = (PromotionOrderEntryConsumed) action.next();
                builder.append(actions.getOrderEntry(ctx).getProduct(ctx).getCode(ctx)).append(',');
                builder.append(actions.getQuantity(ctx)).append('|');
            }
        }

        final Collection actions1 = promotionResult.getActions(ctx);
        if (actions1 != null && !actions1.isEmpty()) {
            final Iterator arg6 = actions1.iterator();

            while (arg6.hasNext()) {
                final AbstractPromotionAction action1 = (AbstractPromotionAction) arg6.next();
                builder.append(action1.getClass().getSimpleName()).append('|');
            }
        }

    }
}
