/**
 *
 */
package com.borngroup.ssl.fulfilmentprocess.strategy;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.promotions.util.Pair;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Calculates the invoice amount for the consignment.
 */
public interface TenderCalculationStrategy {
    /**
     * Calculates the invoice amount applicable for this consignment
     *
     * @param consignment The consignment to calculate
     */
    void calculateTenderAmount(ConsignmentModel consignment);

    /**
     * @param returnConsignment
     * @param salesDataEntryData
     * @param paymentAmounts
     */
    void calculateReturnTenderAmount(ConsignmentModel returnConsignment, Map<String, Pair<ConsignmentModel, Long>> salesDataEntryData,
            Map<String, BigDecimal> paymentAmounts);

    /**
     * @param transactions
     * @return
     */
    Map<String, BigDecimal> calculatePaymentAmountsForReturn(List<PaymentTransactionModel> transactions);

    /**
     * @param transactions
     * @return {@link Map}
     */
    public Map<String, de.hybris.platform.promotions.util.Pair<String, BigDecimal>> calculatePaymentAmountsForRecVoucher(
            List<PaymentTransactionModel> transactions);

    /**
     * @param receiptVoucherTenders
     * @param order
     * @param cancelledAmount
     */
    void removeCancelledTenderTypes(Map<String, de.hybris.platform.promotions.util.Pair<String, BigDecimal>> receiptVoucherTenders,
            AbstractOrderModel order, double cancelledAmount);
}
