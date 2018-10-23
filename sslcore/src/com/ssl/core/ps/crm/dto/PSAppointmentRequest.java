/**
 *
 */
package com.ssl.core.ps.crm.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * <p>
 * PSAppointmentRequest.java : Contains appointment details
 * <p>
 * Created By : raju.p@techouts.com
 * <p>
 *
 * @author Techouts
 */
public class PSAppointmentRequest implements Serializable {

	@JsonProperty("storeNumber")
	private String storeNumber;

	@JsonProperty("AppointmentId")
	private String appointmentId;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("personalShopperId")
	private String personalShopperId;

	@JsonProperty("categoryId")
	private String categoryId;

	@JsonProperty("OccasionId")
	private String occasionId;

	@JsonProperty("TypeOfBookingId")
	private String typeOfBookingId;

	@JsonProperty("ServiceId")
	private String serviceId;
	
	@JsonProperty("personalShopperEmployeeId")
	private String psEmployeeId;

	@JsonProperty("storeNumber")
	public String getStoreNumber() {
		return storeNumber;
	}

	@JsonProperty("storeNumber")
	public void setStoreNumber(final String storeNumber) {
		this.storeNumber = storeNumber;
	}

	@JsonProperty("AppointmentId")
	public String getAppointmentId() {
		return appointmentId;
	}

	@JsonProperty("AppointmentId")
	public void setAppointmentId(final String appointmentId) {
		this.appointmentId = appointmentId;
	}

	@JsonProperty("startDate")
	public String getStartDate() {
		return startDate;
	}

	@JsonProperty("startDate")
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	@JsonProperty("personalShopperId")
	public String getPersonalShopperId() {
		return personalShopperId;
	}

	@JsonProperty("personalShopperId")
	public void setPersonalShopperId(final String personalShopperId) {
		this.personalShopperId = personalShopperId;
	}

	@JsonProperty("categoryId")
	public String getCategoryId() {
		return categoryId;
	}

	@JsonProperty("categoryId")
	public void setCategoryId(final String categoryId) {
		this.categoryId = categoryId;
	}

	@JsonProperty("OccasionId")
	public String getOccasionId() {
		return occasionId;
	}

	@JsonProperty("OccasionId")
	public void setOccasionId(final String occasionId) {
		this.occasionId = occasionId;
	}

	@JsonProperty("TypeOfBookingId")
	public String getTypeOfBookingId() {
		return typeOfBookingId;
	}

	@JsonProperty("TypeOfBookingId")
	public void setTypeOfBookingId(final String typeOfBookingId) {
		this.typeOfBookingId = typeOfBookingId;
	}

	@JsonProperty("ServiceId")
	public String getServiceId() {
		return serviceId;
	}

	@JsonProperty("ServiceId")
	public void setServiceId(final String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the psEmployeeId
	 */
	@JsonProperty("personalShopperEmployeeId")
	public String getPsEmployeeId() {
		return psEmployeeId;
	}

	/**
	 * @param psEmployeeId the psEmployeeId to set
	 */
	@JsonProperty("personalShopperEmployeeId")
	public void setPsEmployeeId(String psEmployeeId) {
		this.psEmployeeId = psEmployeeId;
	}
	
	
	
}
