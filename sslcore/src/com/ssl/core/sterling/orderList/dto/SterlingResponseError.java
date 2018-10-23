/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pankajgandhi
 *
 */
@XmlRootElement(name = "Errors")
@XmlAccessorType(XmlAccessType.FIELD)
public class SterlingResponseError {

	@XmlElement(name = "Error")
	private SterlingErrorDetails error;

	/**
	 * @return the error
	 */
	public SterlingErrorDetails getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(final SterlingErrorDetails error) {
		this.error = error;
	}

}
