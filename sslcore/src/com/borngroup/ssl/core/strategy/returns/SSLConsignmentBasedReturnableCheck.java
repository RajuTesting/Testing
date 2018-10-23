/**
 *
 */
package com.borngroup.ssl.core.strategy.returns;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.returns.strategy.impl.DefaultConsignmentBasedReturnableCheck;

import java.util.Set;

import org.fest.util.Collections;

import com.borngroup.ssl.core.util.GSTUtil;
import com.borngroup.ssl.fulfilmentprocess.model.InternalConsignmentEntryModel;

/**
 * @author nikhilbarar
 *
 */
public class SSLConsignmentBasedReturnableCheck extends DefaultConsignmentBasedReturnableCheck {

    @Override
    public boolean perform(final OrderModel order, final AbstractOrderEntryModel orderentry, final long returnQuantity) {
        if ((returnQuantity < 1L) || (orderentry.getQuantity().longValue() < returnQuantity)) {
            return false;
        }

        final Set<ConsignmentEntryModel> consignmentEntries = orderentry.getConsignmentEntries();

        boolean isReturnable = false;
        
        //ECD-2988 : Open RMA for Consignment status Delivered as well with Completed
        if (!(consignmentEntries.isEmpty())) {
            long totalShippedQuantity = 0;
            for (final ConsignmentEntryModel consignmentEntry : consignmentEntries) {
				if (!(consignmentEntry.getConsignment().getStatus().getCode()
						.equals(ConsignmentStatus.COMPLETE.getCode())
						|| consignmentEntry.getConsignment().getStatus().getCode()
								.equals(ConsignmentStatus.DELIVERED.getCode()))) {
                    continue;
                }
                if (GSTUtil.isReturnDisabled(consignmentEntry)) {
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
            isReturnable = totalShippedQuantity >= returnQuantity;
        } else {
            return false;
        }
        return isReturnable;
    }
}
