/**
 *
 */
package com.ssl.core.sterling.orderpush.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author manikmalhotra
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SterlingAddressDTO {

	@XmlAttribute(name = "AddressID")
	private String addressID;

	@XmlAttribute(name = "EMailID")
	private String eMailID;

	@XmlAttribute(name = "AddressLine1")
	private String addressLine1;

	@XmlAttribute(name = "AddressLine2")
	private String addressLine2;

	@XmlAttribute(name = "AddressLine3")
	private String addressLine3;

	@XmlAttribute(name = "AddressLine4")
	private String addressLine4;

	@XmlAttribute(name = "AddressLine5")
	private String addressLine5;

	@XmlAttribute(name = "AddressLine6")
	private String addressLine6;

	@XmlAttribute(name = "AddressLine7")
	private String addressLine7;

	@XmlAttribute(name = "AddressLine8")
	private String addressLine8;

	@XmlAttribute(name = "City")
	private String city;

	@XmlAttribute(name = "State")
	private String state;

	@XmlAttribute(name = "Country")
	private String country;

	@XmlAttribute(name = "FirstName")
	private String firstName;

	@XmlAttribute(name = "LastName")
	private String lastName;

	@XmlAttribute(name = "MobilePhone")
	private String mobile;

	@XmlAttribute(name = "Title")
	private String title;

	@XmlAttribute(name = "ZipCode")
	private String zipCode;

	public String getAddressID() {
		return addressID;
	}

	public void setAddressID(final String addressID) {
		this.addressID = addressID;
	}

	public String geteMailID() {
		return eMailID;
	}

	public void seteMailID(final String eMailID) {
		this.eMailID = eMailID;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(final String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(final String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(final String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressLine4() {
		return addressLine4;
	}

	public void setAddressLine4(final String addressLine4) {
		this.addressLine4 = addressLine4;
	}

	public String getAddressLine5() {
		return addressLine5;
	}

	public void setAddressLine5(final String addressLine5) {
		this.addressLine5 = addressLine5;
	}

	public String getAddressLine6() {
		return addressLine6;
	}

	public void setAddressLine6(final String addressLine6) {
		this.addressLine6 = addressLine6;
	}

	public String getAddressLine7() {
		return addressLine7;
	}

	public void setAddressLine7(final String addressLine7) {
		this.addressLine7 = addressLine7;
	}

	public String getAddressLine8() {
		return addressLine8;
	}

	public void setAddressLine8(final String addressLine8) {
		this.addressLine8 = addressLine8;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}

}
