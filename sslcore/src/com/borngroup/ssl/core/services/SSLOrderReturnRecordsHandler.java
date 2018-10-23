/**
 *
 */
package com.borngroup.ssl.core.services;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.returns.OrderReturnRecordHandler;
import de.hybris.platform.returns.OrderReturnRecordsHandlerException;
import de.hybris.platform.returns.model.OrderReturnRecordEntryModel;
import de.hybris.platform.returns.model.ReturnEntryModel;

import java.util.List;

/**
 * @author nikhilbarar
 *
 */
public interface SSLOrderReturnRecordsHandler extends OrderReturnRecordHandler {

	public OrderReturnRecordEntryModel createReplacementEntry(OrderModel order,
			List<ReturnEntryModel> replacements, String description)
			throws OrderReturnRecordsHandlerException;

}
