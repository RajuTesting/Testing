/**
 *
 */
package com.ssl.core.customer.mdm.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author amithp
 *
 */
@XmlRootElement(name = "SSL_CM_Shipment_Address")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShipmentAddress implements Serializable {

	@XmlElement(name = "SSl_CM_SA_Address_Id")
	private String addressId;

	@XmlElement(name = "SSl_CM_SA_Address_FirstName")
	private String firstName;

	@XmlElement(name = "SSl_CM_SA_Address_LastName")
	private String lastName;

	@XmlElement(name = "SSl_CM_SA_Address_Phone1")
	private String phone1;

	@XmlElement(name = "SSl_CM_SA_Address_StreetName")
	private String streetName;

	@XmlElement(name = "SSL_CM_SA_Address_Line1")
	private String addressLine1;

	@XmlElement(name = "SSL_CM_SA_Address_Line2")
	private String addressLine2;

	@XmlElement(name = "SSL_CM_SA_City")
	private String city;

	@XmlElement(name = "SSL_CM_SA_State")
	private String state;

	@XmlElement(name = "SSL_CM_SA_Pin")
	private String pincode;

	@XmlElement(name = "SSL_CM_SA_Region")
	private String region;

	@XmlElement(name = "SSL_CM_SA_Is_Default_Shipment_Address")
	private String defaultShipmentAddress;

	/**
	 * @return the addressId
	 */
	public String getAddressId() {
		return addressId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @return the defaultShipmentAddress
	 */
	public String getDefaultShipmentAddress() {
		return defaultShipmentAddress;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param phone1 the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @param defaultShipmentAddress the defaultShipmentAddress to set
	 */
	public void setDefaultShipmentAddress(String defaultShipmentAddress) {
		this.defaultShipmentAddress = defaultShipmentAddress;
	}

	
}
