/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.basecommerce.enums.OrderModificationEntryStatus;
import de.hybris.platform.basecommerce.enums.RefundReason;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.ordercancel.exceptions.OrderCancelDaoException;
import de.hybris.platform.orderhistory.model.OrderHistoryEntryModel;
import de.hybris.platform.promotions.model.PromotionResultModel;
import de.hybris.platform.refund.model.OrderRefundRecordEntryModel;
import de.hybris.platform.returns.OrderReturnRecordsHandlerException;
import de.hybris.platform.returns.impl.DefaultOrderReturnRecordsHandler;
import de.hybris.platform.returns.model.OrderEntryReturnRecordEntryModel;
import de.hybris.platform.returns.model.OrderReplacementRecordEntryModel;
import de.hybris.platform.returns.model.OrderReturnRecordEntryModel;
import de.hybris.platform.returns.model.OrderReturnRecordModel;
import de.hybris.platform.returns.model.RefundEntryModel;
import de.hybris.platform.returns.model.ReplacementEntryModel;
import de.hybris.platform.returns.model.ReturnEntryModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.services.SSLOrderReturnRecordsHandler;

/**
 * @author nikhilbarar
 *
 */
public class SSLOrderReturnRecordsHandlerImpl extends DefaultOrderReturnRecordsHandler implements SSLOrderReturnRecordsHandler {

    private static final Logger LOG = Logger.getLogger(SSLOrderReturnRecordsHandler.class);

    @Override
    public OrderReturnRecordEntryModel createRefundEntry(final OrderModel order, final List<RefundEntryModel> refunds,
            final String description) throws OrderReturnRecordsHandlerException {
        //remove ssl promo results
        final Set<PromotionResultModel> promotionResults = order.getAllPromotionResults();
        order.setAllPromotionResults(null);
        final OrderHistoryEntryModel snapshot = this.createSnaphot(order, description);
        //add them back.
        order.setAllPromotionResults(promotionResults);
        try {
            final OrderReturnRecordModel e = this.getOrCreateReturnRecord(order);
            e.setInProgress(false);
            if (e.isInProgress()) {
                throw new IllegalStateException(
                        "Cannot create new Order return request - the order return record indicates: Return already in progress");
            } else {
                // e.setInProgress(true);
                this.getModelService().save(e);
                return this.createRefundRecordEntry(order, e, snapshot, refunds, (UserModel) null);
            }
        } catch (final OrderCancelDaoException arg5) {
            throw new OrderReturnRecordsHandlerException(order.getCode(), arg5);
        } catch (final IllegalStateException e) {
            LOG.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    protected OrderReturnRecordEntryModel createRefundRecordEntry(final OrderModel order, final OrderReturnRecordModel returnRecord,
            final OrderHistoryEntryModel snapshot, final List<RefundEntryModel> refunds, final UserModel principal)
            throws OrderReturnRecordsHandlerException {

        final OrderRefundRecordEntryModel refundRecordEntry = (OrderRefundRecordEntryModel) getModelService().create(
                OrderRefundRecordEntryModel.class);
        double refundAmount = 0d;
        returnRecord.setInProgress(false);
        refundRecordEntry.setTimestamp(new Date());
        refundRecordEntry.setCode(generateEntryCode(snapshot));
        refundRecordEntry.setOriginalVersion(snapshot);
        refundRecordEntry.setModificationRecord(returnRecord);
        refundRecordEntry.setPrincipal(principal);
        refundRecordEntry.setOwner(returnRecord);
        refundRecordEntry.setStatus(OrderModificationEntryStatus.SUCCESSFULL);

        getModelService().save(refundRecordEntry);

        final List orderEntriesRecords = new ArrayList();

        for (final RefundEntryModel refundEntry : refunds) {
            final OrderEntryReturnRecordEntryModel orderEntryRefundEntry = (OrderEntryReturnRecordEntryModel) getModelService().create(
                    OrderEntryReturnRecordEntryModel.class);
            orderEntryRefundEntry.setCode(refundEntry.getOrderEntry().getPk().toString());
            orderEntryRefundEntry.setExpectedQuantity(Long.valueOf(refundEntry.getExpectedQuantity().longValue()));
            orderEntryRefundEntry.setModificationRecordEntry(refundRecordEntry);
            orderEntryRefundEntry.setOriginalOrderEntry(getOriginalOrderEntry(snapshot, refundEntry));
            orderEntryRefundEntry.setReason(refundEntry.getReason());
            // set refunded amount per unit
            orderEntryRefundEntry.setRefundedPerUnitAmount(refundEntry.getOrderEntry().getCalculatedRefundPerUnit());
            orderEntryRefundEntry.setNotes(refundEntry.getNotes());
            getModelService().save(orderEntryRefundEntry);
            orderEntriesRecords.add(orderEntryRefundEntry);
            refundAmount += (refundEntry.getExpectedQuantity().doubleValue() * refundEntry.getOrderEntry().getCalculatedRefundPerUnit()
                    .doubleValue());
        }

        refundRecordEntry.setRefundAmount(Double.valueOf(refundAmount));
        refundRecordEntry.setOrderEntriesModificationEntries(orderEntriesRecords);

        getModelService().saveAll();

        return refundRecordEntry;
    }

    @Override
    public OrderReturnRecordEntryModel createReplacementEntry(final OrderModel order, final List<ReturnEntryModel> replacements,
            final String description) throws OrderReturnRecordsHandlerException {
        //remove order's promo results
        final Set<PromotionResultModel> promotionResults = order.getAllPromotionResults();
        order.setAllPromotionResults(null);
        final OrderHistoryEntryModel snapshot = this.createSnaphot(order, description);
        //add them back.
        order.setAllPromotionResults(promotionResults);
        try {
            final OrderReturnRecordModel e = this.getOrCreateReturnRecord(order);
            e.setInProgress(false);
            if (e.isInProgress()) {
                throw new IllegalStateException(
                        "Cannot create new Order return request - the order return record indicates: Exchange already in progress");
            } else {
                // e.setInProgress(true);
                this.getModelService().save(e);
                return this.createReplacementRecordEntry(order, e, snapshot, replacements, (UserModel) null);
            }
        } catch (final OrderCancelDaoException arg5) {
            throw new OrderReturnRecordsHandlerException(order.getCode(), arg5);
        } catch (final IllegalStateException e) {
            LOG.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    private OrderReturnRecordEntryModel createReplacementRecordEntry(final OrderModel order, final OrderReturnRecordModel returnRecord,
            final OrderHistoryEntryModel snapshot, final List<ReturnEntryModel> replacements, final UserModel principal)
            throws OrderReturnRecordsHandlerException {

        final OrderReplacementRecordEntryModel replacementRecordEntry = (OrderReplacementRecordEntryModel) getModelService().create(
                OrderReplacementRecordEntryModel.class);
        returnRecord.setInProgress(false);
        replacementRecordEntry.setTimestamp(new Date());
        replacementRecordEntry.setCode(generateEntryCode(snapshot));
        replacementRecordEntry.setOriginalVersion(snapshot);
        replacementRecordEntry.setModificationRecord(returnRecord);
        replacementRecordEntry.setPrincipal(principal);
        replacementRecordEntry.setOwner(returnRecord);
        replacementRecordEntry.setStatus(OrderModificationEntryStatus.SUCCESSFULL);

        getModelService().save(replacementRecordEntry);

        final List orderEntriesRecords = new ArrayList();

        for (final ReturnEntryModel returnEntry : replacements) {
            final ReplacementEntryModel replacementEntry = (ReplacementEntryModel) returnEntry;
            final OrderEntryReturnRecordEntryModel orderEntryRefundEntry = (OrderEntryReturnRecordEntryModel) getModelService().create(
                    OrderEntryReturnRecordEntryModel.class);
            orderEntryRefundEntry.setCode(replacementEntry.getOrderEntry().getPk().toString());
            orderEntryRefundEntry.setExpectedQuantity(Long.valueOf(replacementEntry.getExpectedQuantity().longValue()));
            orderEntryRefundEntry.setModificationRecordEntry(replacementRecordEntry);
            orderEntryRefundEntry.setOriginalOrderEntry(getOriginalOrderEntry(snapshot, replacementEntry));
            orderEntryRefundEntry.setReason(RefundReason.valueOf(replacementEntry.getReason().getCode()));
            // set refunded amount per unit
            orderEntryRefundEntry.setRefundedPerUnitAmount(replacementEntry.getOrderEntry().getCalculatedRefundPerUnit());
            orderEntryRefundEntry.setNotes(replacementEntry.getNotes());
            getModelService().save(orderEntryRefundEntry);
            orderEntriesRecords.add(orderEntryRefundEntry);
        }

        replacementRecordEntry.setRefundAmount(Double.valueOf(0));
        replacementRecordEntry.setOrderEntriesModificationEntries(orderEntriesRecords);

        getModelService().saveAll();

        return replacementRecordEntry;
    }

    protected OrderEntryModel getOriginalOrderEntry(final OrderHistoryEntryModel snapshot, final ReturnEntryModel refundEntry)
            throws OrderReturnRecordsHandlerException {
        OrderEntryModel originalOrderEntry = null;
        try {
            final int entryPos = refundEntry.getOrderEntry().getEntryNumber().intValue();
            final OrderModel previousOrder = snapshot.getPreviousOrderVersion();
            if (null != previousOrder) {
                final List<AbstractOrderEntryModel> orderEntries = previousOrder.getEntries();
                if (!CollectionUtils.isEmpty(orderEntries)) {
                    for (final AbstractOrderEntryModel orderEntryModel : orderEntries) {
                        if (orderEntryModel.getEntryNumber().intValue() == entryPos) {
                            originalOrderEntry = (OrderEntryModel) orderEntryModel;
                        }
                    }
                }
            }
        } catch (final IndexOutOfBoundsException localIndexOutOfBoundsException) {
            LOG.error("Exception : " + localIndexOutOfBoundsException);
            throw new IllegalStateException("Cloned and original order have different number of entries");
        } catch (final Exception e) {
            throw new OrderReturnRecordsHandlerException(refundEntry.getOrderEntry().getOrder().getCode(),
                    "Error during getting historical orderEntry", e);
        }
        return originalOrderEntry;
    }
}
