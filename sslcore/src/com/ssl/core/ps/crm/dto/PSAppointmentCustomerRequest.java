/**
 *
 */
package com.ssl.core.ps.crm.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * PSAppointmentCustomerRequest.java : It contains customer appointment request details 
 * <p>
 * Created By : raju.p@techouts.com
 * <p>
 *
 * @author Techouts
 */
public class PSAppointmentCustomerRequest implements Serializable{


	@JsonProperty("HybrisID")
	private String hybrisID;
	@JsonProperty("LoyaltyID")
	private String loyaltyID;
	@JsonProperty("FirstName")
	private String firstName;
	@JsonProperty("LastName")
	private String lastName;
	@JsonProperty("EmailID")
	private String emailID;
	@JsonProperty("PhoneNumber")
	private String phoneNumber;

	@JsonProperty("HybrisID")
	public String getHybrisID() {
	return hybrisID;
	}

	@JsonProperty("HybrisID")
	public void setHybrisID(final String hybrisID) {
	this.hybrisID = hybrisID;
	}

	@JsonProperty("LoyaltyID")
	public String getLoyaltyID() {
	return loyaltyID;
	}

	@JsonProperty("LoyaltyID")
	public void setLoyaltyID(final String loyaltyID) {
	this.loyaltyID = loyaltyID;
	}

	@JsonProperty("FirstName")
	public String getFirstName() {
	return firstName;
	}

	@JsonProperty("FirstName")
	public void setFirstName(final String firstName) {
	this.firstName = firstName;
	}

	@JsonProperty("LastName")
	public String getLastName() {
	return lastName;
	}

	@JsonProperty("LastName")
	public void setLastName(final String lastName) {
	this.lastName = lastName;
	}

	@JsonProperty("EmailID")
	public String getEmailID() {
	return emailID;
	}

	@JsonProperty("EmailID")
	public void setEmailID(final String emailID) {
	this.emailID = emailID;
	}

	@JsonProperty("PhoneNumber")
	public String getPhoneNumber() {
	return phoneNumber;
	}

	@JsonProperty("PhoneNumber")
	public void setPhoneNumber(final String phoneNumber) {
	this.phoneNumber = phoneNumber;
	}
}
