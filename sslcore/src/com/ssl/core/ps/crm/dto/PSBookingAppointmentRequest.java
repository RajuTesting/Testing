/**
 *
 */
package com.ssl.core.ps.crm.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author techouts
 *
 * Root object to create Appointment
 *
 *
 */
public class PSBookingAppointmentRequest implements Serializable {

	@JsonProperty("appointment")
	private PSAppointmentRequest appointment;
	@JsonProperty("customer")
	private PSAppointmentCustomerRequest customer;
	@JsonProperty("hardBook")
	private Boolean hardBook;
	@JsonProperty("action")
	private String action;
	

	/**
	 * @return the appointment
	 */
	@JsonProperty("appointment")
	public PSAppointmentRequest getAppointment() {
		return appointment;
	}

	/**
	 * @param appointment the appointment to set
	 */
	@JsonProperty("appointment")
	public void setAppointment(final PSAppointmentRequest appointment) {
		this.appointment = appointment;
	}

	@JsonProperty("customer")
	public PSAppointmentCustomerRequest getCustomer() {
		return customer;
	}

	@JsonProperty("customer")
	public void setCustomer(final PSAppointmentCustomerRequest customer) {
		this.customer = customer;
	}

	@JsonProperty("hardBook")
	public Boolean getHardBook() {
		return hardBook;
	}

	@JsonProperty("hardBook")
	public void setHardBook(final Boolean hardBook) {
		this.hardBook = hardBook;
	}

	/**
	 * @return the action
	 */
	@JsonProperty("action")
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	@JsonProperty("action")
	public void setAction(String action) {
		this.action = action;
	}
	
	
}
