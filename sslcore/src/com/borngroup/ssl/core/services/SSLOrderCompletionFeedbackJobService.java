package com.borngroup.ssl.core.services;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.Date;
import java.util.List;

import com.borngroup.ssl.core.data.OrderFeedMailURLDecryptionResponseData;

/**
 * The Interface SSLOrderCompletionFeedbackJobService. Service to be used by
 * feedback mail cronjob, it contains methods to create unique encrypted URL for
 * user to click.
 *
 * created by : ashish.sabal@nagarro.com
 *
 * @author SSL
 */
public interface SSLOrderCompletionFeedbackJobService {
	/**
	 * Gets the completed order latest.
	 *
	 * @param startDateForGetCompletedOrder
	 *            the start date for get completed order
	 * @param completed
	 *            the completed
	 * @return the completed order latest
	 */
	List<OrderModel> getCompletedOrderLatest(Date startDateForGetCompletedOrder, OrderStatus completed);

	/**
	 * Creates the unique hash url for order.
	 *
	 * @param inputStrForCipher
	 *            the input str for cipher
	 * @return the string
	 */
	String createUniqueHashUrlForOrder(String inputStrForCipher);

	/**
	 * Decrypt url get response from hash.
	 *
	 * @param encryptedUniqueStr
	 *            the encrypted unique str
	 * @return the order feed mail URL decryption response data
	 */
	OrderFeedMailURLDecryptionResponseData decryptUrlGetResponseFromHash(String encryptedUniqueStr);
}