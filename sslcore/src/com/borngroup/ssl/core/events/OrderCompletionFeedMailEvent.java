/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

/**
 * The Class OrderCompletionFeedMailEvent.
 *
 * created by : ashish.sabal@nagarro.com
 *
 * @author SSL
 * @param <T>
 *            the generic type
 */
public class OrderCompletionFeedMailEvent<T extends BaseSiteModel> extends AbstractEvent {

	/** The order current. */
	private OrderModel orderCurrent;

	/** The encoded unique url. */
	private String encodedUniqueUrl;

	/** The user mail id. */
	private String userMailId;

	/** The base site. */
	private T site;

	/**
	 * Gets the order current.
	 *
	 * @return the orderCurrent
	 */
	public OrderModel getOrderCurrent() {
		return orderCurrent;
	}

	/**
	 * Sets the order current.
	 *
	 * @param orderCurrent
	 *            the orderCurrent to set
	 */
	public void setOrderCurrent(final OrderModel orderCurrent) {
		this.orderCurrent = orderCurrent;
	}

	/**
	 * Gets the encoded unique url.
	 *
	 * @return the encodedUniqueUrl
	 */
	public String getEncodedUniqueUrl() {
		return encodedUniqueUrl;
	}

	/**
	 * Sets the encoded unique url.
	 *
	 * @param encodedUniqueUrl
	 *            the encodedUniqueUrl to set
	 */
	public void setEncodedUniqueUrl(final String encodedUniqueUrl) {
		this.encodedUniqueUrl = encodedUniqueUrl;
	}

	/**
	 * Gets the user mail id.
	 *
	 * @return the userMailId
	 */
	public String getUserMailId() {
		return userMailId;
	}

	/**
	 * Sets the user mail id.
	 *
	 * @param userMailId
	 *            the userMailId to set
	 */
	public void setUserMailId(final String userMailId) {
		this.userMailId = userMailId;
	}

	/**
	 * Gets the site.
	 *
	 * @return the site
	 */
	public T getSite() {
		return site;
	}

	/**
	 * Sets the site.
	 *
	 * @param site
	 *            the site to set
	 */
	public void setSite(final T site) {
		this.site = site;
	}
}
