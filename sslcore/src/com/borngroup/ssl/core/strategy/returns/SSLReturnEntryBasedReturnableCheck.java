/**
 *
 */
package com.borngroup.ssl.core.strategy.returns;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.strategy.impl.DefaultReturnEntryBasedReturnableCheck;

import java.util.ArrayList;
import java.util.List;

import org.fest.util.Collections;

import com.borngroup.ssl.fulfilmentprocess.model.InternalConsignmentEntryModel;

/**
 * @author nikhilbarar
 *
 */
public class SSLReturnEntryBasedReturnableCheck extends DefaultReturnEntryBasedReturnableCheck {
    /*
     * (non-Javadoc)
     *
     * @see
     * de.hybris.platform.returns.strategy.impl.DefaultReturnEntryBasedReturnableCheck#perform(de.hybris.platform.core.model.order.OrderModel
     * , de.hybris.platform.core.model.order.AbstractOrderEntryModel, long)
     */
    @Override
    public boolean perform(final OrderModel order, final AbstractOrderEntryModel orderentry, final long returnQuantity) {
        if ((returnQuantity < 1L) || (orderentry.getQuantity().longValue() < returnQuantity)) {
            return false;
        }

        boolean isReturnable = false;

        final List<ReturnEntryModel> allReturnEntries = getReturnEntry(orderentry);

        final List<ReturnEntryModel> returnsEntries = new ArrayList<ReturnEntryModel>();
        for (final ReturnEntryModel returnEntry : allReturnEntries) {
            if (null != returnEntry.getReturnRequest()
                    && !(ReturnStatus.PICKUP_FAILED.equals(returnEntry.getReturnRequest().getStatus()) || ReturnStatus.CANCELED
                            .equals(returnEntry.getReturnRequest().getStatus()))) {
                returnsEntries.add(returnEntry);
            }
        }

        if (returnsEntries.isEmpty()) {
            isReturnable = true;

        } else {
            long returnedEntriesSoFar = 0L;
            for (final ReturnEntryModel returnsEntry : returnsEntries) {
                returnedEntriesSoFar += returnsEntry.getExpectedQuantity().longValue();
            }

            long totalShippedQuantity = 0;
            //ECD-2988 : Open RMA for Consignment status Delivered as well with Completed
            for (final ConsignmentEntryModel consignmentEntry : orderentry.getConsignmentEntries()) {
				if (!(consignmentEntry.getConsignment().getStatus().getCode()
						.equals(ConsignmentStatus.COMPLETE.getCode())
						|| consignmentEntry.getConsignment().getStatus().getCode()
								.equals(ConsignmentStatus.DELIVERED.getCode()))) {
                    continue;
                }
                if (null != consignmentEntry.getConsignment().getReturnRequest()) {
                    continue;
                }
                totalShippedQuantity += consignmentEntry.getShippedQuantity().longValue();

                if (null != consignmentEntry.getInternalEntries() && !Collections.isEmpty(consignmentEntry.getInternalEntries())) {
                    for (final InternalConsignmentEntryModel internalEntry : consignmentEntry.getInternalEntries()) {
                        if (null != internalEntry.getShippedQuantity()) {
                            totalShippedQuantity += internalEntry.getShippedQuantity().longValue();
                        }
                    }
                }
            }

            isReturnable = totalShippedQuantity >= returnedEntriesSoFar + returnQuantity;
        }
        return isReturnable;
    }
}
