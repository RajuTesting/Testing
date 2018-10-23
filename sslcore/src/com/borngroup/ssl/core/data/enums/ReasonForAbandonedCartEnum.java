package com.borngroup.ssl.core.data.enums;

import com.borngroup.ssl.core.data.SslCoreCommonEnum;

/**
 * Enum : For storing the reason for Abandoned Cart.
 * <p/>
 * Created by osheen.gulati@nagarro.com.
 *
 * @author SSL
 */
public enum ReasonForAbandonedCartEnum implements SslCoreCommonEnum {

	CUSTOMER_NOT_INTERESTED("Customer Not Interested"), CALL_LATER("Asked to Call Later"), CALL_NOT_CONNECTED(
			"Call Not Connected");

	private final String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	private ReasonForAbandonedCartEnum(final String value) {
		name = value;
	}

	@Override
	public boolean equalsName(final String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	@Override
	public String toString() {
		return name;
	}

}
