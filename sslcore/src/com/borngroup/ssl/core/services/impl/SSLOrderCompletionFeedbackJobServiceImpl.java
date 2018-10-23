package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.SSLLatestCompletedOrderLookupDao;
import com.borngroup.ssl.core.data.OrderFeedMailURLDecryptionResponseData;
import com.borngroup.ssl.core.services.SSLOrderCompletionFeedbackJobService;

/**
 * The Class SSL Order Completion Feedback Job Service Impl.
 *
 * created by : ashish.sabal@nagarro.com
 *
 * @author SSL
 */
public class SSLOrderCompletionFeedbackJobServiceImpl implements SSLOrderCompletionFeedbackJobService {

	/** Log4j logger. */
	Logger LOG = Logger.getLogger(SSLOrderCompletionFeedbackJobServiceImpl.class);

	/** The Constant HEX_CHAR. */
	private static final byte[] HEX_CHAR = new byte[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
			'd', 'e', 'f' };

	/** The Constant CIPHER_KEY, 128 bit key. */
	private static final String CIPHER_KEY = "Bar12345Bar12345";

	/** The Constant DATE_FORMAT. */
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	/** The Constant INPUT_STRING_PART_0. */
	private static final String INPUT_STRING_PART_0 = "#";

	/** The Constant INPUT_STRING_PART_1. */
	private static final String INPUT_STRING_PART_1 = "Order:";

	/** The Constant INPUT_STRING_PART_2. */
	private static final String INPUT_STRING_PART_2 = "TimeStamp:";

	/** The Constant URL_DECRYPT_ERROR_CODE. */
	public static final String URL_DECRYPT_ERROR_CODE = "Url invalidated.";

	/** The Constant ENCRYPTION_METHOD. */
	public static final String ENCRYPTION_METHOD = "AES";

	/** Latest Order Lookup Dao. */
	@Resource(name = "sslLatestCompletedOrderLookupDao")
	private SSLLatestCompletedOrderLookupDao sslLatestCompletedOrderLookupDao;

	@Override
	public String createUniqueHashUrlForOrder(final String text) {
		String encryptedText = StringUtils.EMPTY;
		try {
			final String key = CIPHER_KEY;
			// Create key and cipher
			final Key aesKey = new SecretKeySpec(key.getBytes(), ENCRYPTION_METHOD);
			final Cipher cipher = Cipher.getInstance(ENCRYPTION_METHOD);
			// encrypt the text
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			final byte[] encrypted = cipher.doFinal(text.getBytes());
			encryptedText = hexByteArrayToString(encrypted);
		} catch (final NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			LOG.error(e.getStackTrace());
		}
		return encryptedText;
	}

	@Override
	public OrderFeedMailURLDecryptionResponseData decryptUrlGetResponseFromHash(final String encryptedUniqueStr) {
		if (StringUtils.isEmpty(encryptedUniqueStr)) {
			return null;
		}
		final OrderFeedMailURLDecryptionResponseData decryptURLResponse = new OrderFeedMailURLDecryptionResponseData();
		final String starRating = Character.toString(encryptedUniqueStr.charAt(encryptedUniqueStr.length() - 1));
		final String decryptedStringDataForOrder = this
				.decryptStringFromHexString(encryptedUniqueStr.substring(0, encryptedUniqueStr.length() - 1));

		if (StringUtils.isNotEmpty(decryptedStringDataForOrder)) {
			final String[] orderAndTimestamp = decryptedStringDataForOrder.split(INPUT_STRING_PART_0);

			if (orderAndTimestamp.length == 2
					&& Pattern.matches(INPUT_STRING_PART_1, orderAndTimestamp[0].substring(0, 6))
					&& Pattern.matches(INPUT_STRING_PART_2, orderAndTimestamp[1].substring(0, 10))) {
				String orderNum = StringUtils.EMPTY;
				Date encDateOfUrl = null;
				try {
					orderNum = orderAndTimestamp[0].substring(6);
					final String encDateOfUrlStr = orderAndTimestamp[1].substring(10);
					final DateFormat df = new SimpleDateFormat(DATE_FORMAT);
					encDateOfUrl = df.parse(encDateOfUrlStr);
				} catch (final NumberFormatException | ParseException e) {
					LOG.error(e.getStackTrace());
				}

				if (StringUtils.isNotEmpty(orderNum) && null != encDateOfUrl) {
					final Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -31);
					final Date lastMonthTodayDate = cal.getTime();

					if (encDateOfUrl.after(lastMonthTodayDate)) {
						decryptURLResponse.setOrderNumber(orderNum);
						decryptURLResponse.setStarRating(starRating);
					} else {
						decryptURLResponse.setErrorMessage(URL_DECRYPT_ERROR_CODE);
					}
					return decryptURLResponse;
				}
			} else {
				LOG.error("Encrypted string is wrong. Can't decrypt.");
			}
		} else {
			LOG.error("Error in URL decryption.");
		}
		return null;
	}

	/**
	 * Decrypt string from hex string.
	 *
	 * @param encryptedHexStr
	 *            the encrypted hex str
	 * @return the string
	 */
	private String decryptStringFromHexString(final String encryptedHexStr) {
		String decryptedText = StringUtils.EMPTY;
		try {
			final byte[] hexByteStr = hexStringToByteArray(encryptedHexStr);
			if (ArrayUtils.isEmpty(hexByteStr)) {
				return decryptedText;
			}
			final String key = CIPHER_KEY;
			// Create key and cipher
			final Key aesKey = new SecretKeySpec(key.getBytes(), ENCRYPTION_METHOD);
			final Cipher cipher = Cipher.getInstance(ENCRYPTION_METHOD);

			// encrypt the text
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decryptedText = new String(cipher.doFinal(hexByteStr));
		} catch (final NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			LOG.error(e.getStackTrace());
		}
		return decryptedText;
	}

	/**
	 * Hex byte array to string.
	 *
	 * @param hexByteArray
	 *            the hex byte array
	 * @return the string
	 */
	private String hexByteArrayToString(final byte[] hexByteArray) {
		if (hexByteArray == null) {
			return StringUtils.EMPTY;
		}
		final StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		for (final byte aHexByteArray : hexByteArray) {
			sb.append((char) (HEX_CHAR[(aHexByteArray & 0x00F0) >> 4]))
					.append((char) (HEX_CHAR[aHexByteArray & 0x000F]));
		}
		return sb.toString();
	}

	/**
	 * Hex string to byte array.
	 *
	 * @param hexStr
	 *            the hex string to be converted to byte array
	 * @return byte[] array
	 */
	private byte[] hexStringToByteArray(final String hexStr) {
		final int len = hexStr.length();
		final byte[] data = new byte[len / 2];
		try {
			for (int i = 0; i < len; i += 2) {
				data[i / 2] = (byte) ((Character.digit(hexStr.charAt(i), 16) << 4)
						+ Character.digit(hexStr.charAt(i + 1), 16));
			}
		} catch (final StringIndexOutOfBoundsException e) {
			LOG.error(e.getMessage());
			return ArrayUtils.EMPTY_BYTE_ARRAY;
		}
		return data;
	}

	/**
	 * Gets the ssl latest completed order lookup dao.
	 *
	 * @return the sslLatestCompletedOrderLookupDao
	 */
	public SSLLatestCompletedOrderLookupDao getSslLatestCompletedOrderLookupDao() {
		return sslLatestCompletedOrderLookupDao;
	}

	/**
	 * Sets the ssl latest completed order lookup dao.
	 *
	 * @param ssllatestcompletedorderlookupdao
	 *            the sslLatestCompletedOrderLookupDao to set
	 */
	public void setSslLatestCompletedOrderLookupDao(
			final SSLLatestCompletedOrderLookupDao ssllatestcompletedorderlookupdao) {
		this.sslLatestCompletedOrderLookupDao = ssllatestcompletedorderlookupdao;
	}

	@Override
	public List<OrderModel> getCompletedOrderLatest(final Date startDateForGetCompletedOrder,
			final OrderStatus orderStatus) {
		return this.getSslLatestCompletedOrderLookupDao().getCompletedOrderLatest(startDateForGetCompletedOrder,
				orderStatus);
	}
}
