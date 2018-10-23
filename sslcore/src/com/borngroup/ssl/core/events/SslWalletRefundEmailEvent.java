/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

/**
 * @author osheengulati
 *
 */
public class SslWalletRefundEmailEvent<T extends BaseSiteModel> extends AbstractEvent {

	private final OrderModel order;

	public SslWalletRefundEmailEvent(final OrderModel order) {
		this.order = order;
	}

	/**
	 * @return the order
	 */
	public OrderModel getOrder() {
		return order;
	}

}
