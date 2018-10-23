/**
 *
 */
package com.borngroup.ssl.core.data;

/**
 * @author ashishsabal
 *
 */
public class OrderFeedMailURLDecryptionResponseData {

	/** The Order number. */
	private String OrderNumber;

	/** The Star rating. */
	private String StarRating;

	/** The Error message. */
	private String ErrorMessage;

	/**
	 * Gets the order number.
	 *
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return OrderNumber;
	}

	/**
	 * Sets the order number.
	 *
	 * @param orderNumber
	 *            the orderNumber to set
	 */
	public void setOrderNumber(final String orderNumber) {
		OrderNumber = orderNumber;
	}

	/**
	 * Gets the star rating.
	 *
	 * @return the starRating
	 */
	public String getStarRating() {
		return StarRating;
	}

	/**
	 * Sets the star rating.
	 *
	 * @param starRating
	 *            the starRating to set
	 */
	public void setStarRating(final String starRating) {
		StarRating = starRating;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return ErrorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		ErrorMessage = errorMessage;
	}
}
