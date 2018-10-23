/**
 *
 */
package com.ssl.core.ps.crm.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * PSBookingAppointmentResponse.java : Contains booking appointment response
 * details details
 *
 * <p>
 * Created By : raju.p@techouts.com
 * <p>
 *
 * @author Techouts
 */
public class PSBookingAppointmentResponse implements Serializable {

	@JsonProperty("messageCode")
	private String responseCode;
	@JsonProperty("messageType")
	private String responseType;
	@JsonProperty("messageText")
	private String responseText;
	
	/**
	 * @return the responseCode
	 */
	@JsonProperty("messageCode")
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	@JsonProperty("messageCode")
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseType
	 */
	@JsonProperty("messageType")
	public String getResponseType() {
		return responseType;
	}

	/**
	 * @param responseType the responseType to set
	 */
	@JsonProperty("messageType")
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	/**
	 * @return the responseText
	 */
	@JsonProperty("messageText")
	public String getResponseText() {
		return responseText;
	}

	/**
	 * @param responseText the responseText to set
	 */
	@JsonProperty("messageText")
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PSBookingAppointmentResponse [responseCode=" + responseCode + ", responseType=" + responseType
				+ ", responseText=" + responseText + "]";
	}
}
