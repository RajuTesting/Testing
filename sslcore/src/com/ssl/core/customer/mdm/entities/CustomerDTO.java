/**
 *
 */
package com.ssl.core.customer.mdm.entities;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * @author Techouts
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDTO implements Serializable {

	@XmlElement(name = "SSL_CM_Date_Of_Birth")
	private String dob;

	@XmlElement(name = "SSL_CM_Gender")
	private String gender;

	@XmlElement(name = "SSL_CM_Residence_Phone")
	private String residencePhone;

	@XmlElement(name = "SSL_CM_eMail_Id")
	private String emailId;

	@XmlElement(name = "SSL_CM_Has_Children")
	private String hasChildren;

	@XmlElement(name = "SSL_CM_Mobile_Phone")
	private String mobile;

	@XmlElement(name = "SSL_CM_Is_City_Tier")
	private String cityTier;

	@XmlElement(name = "SSL_CM_GST_Number")
	private String gstNumber;

	@XmlElement(name = "SSL_CM_Customer_Home_Store")
	private String homeStore;

	@XmlElement(name = "SSL_CM_Home_Store_Modified_Date")
	private String homeStoreModifiedDate;

	@XmlElement(name = "SSL_CM_Is_Social_Pic_Uploaded")
	private String socialPicUploaded;

	@XmlElement(name = "SSL_CM_Is_Subscribed")
	private String isSubscribed;

	@XmlElement(name = "SSL_CM_Marital_Status")
	private String maritalStatus;

	@XmlElement(name = "SSL_CM_Secondary_Mobile_Number")
	private String secondaryMobileNumber;

	@XmlElement(name = "SSL_CM_Price_Conscious")
	private String priceConscious;
	
	@XmlElementWrapper(name = "SSL_CM_Social_Accounts") 
	@XmlElement(name = "SSL_CM_Social_Account")
	private List<SocialAccount> socialAccount;

	@XmlElement(name = "SSL_CM_Style_Preference")
	private String stylePreference;

	@XmlElement(name = "SSL_CM_Customer_Title")
	private String customerTitle;

	@XmlElement(name = "SSL_CM_Type")
	private String userType;

	@XmlElement(name = "SSL_CM_COD_Verification_Date")
	private String codVerificationDate;

	@XmlElement(name = "SSL_CM_Verified_For_COD")
	private String verifiedForCod;

	@XmlElement(name = "SSL_CM_Wallet_Number")
	private String walletNumber;

	@XmlElement(name = "SSL_CM_Hybris_Displayname")
	private String displayName;

	@XmlElement(name = "SSL_CM_Name")
	private String name;

	@XmlElement(name = "SSL_CM_Pk")
	private String pk;

	@XmlElement(name = "SSL_CM_Profile_Picture")
	private String profilePicture;

	@XmlElement(name = "SSL_CM_Uid")
	private String uid;

	@XmlElement(name = "SSL_CM_Address_Line_1")
	private String addressLine1;

	@XmlElement(name = "SSL_CM_Address_Line_2")
	private String addressLine2;

	@XmlElement(name = "SSL_CM_Address_Line_3")
	private String addressLine3;

	@XmlElement(name = "SSL_CM_City")
	private String city;

	@XmlElement(name = "SSL_CM_State")
	private String state;

	@XmlElement(name = "SSL_CM_Pin")
	private String pincode;

	@XmlElement(name = "SSL_CM_Country")
	private String country;

	@XmlElement(name = "SSL_CM_Res_Landmark")
	private String landmark;
	
	@XmlElementWrapper(name = "SSL_CM_Payment_Addresses") 
	@XmlElement(name = "SSL_CM_Payment_Address")
	private List<PaymentAddresses> paymentAddresses;
	

	@XmlElementWrapper(name = "SSL_CM_Shipment_Addresses") 
	@XmlElement(name = "SSL_CM_Shipment_Address")
	private List<ShipmentAddress> shipmentAddresses;

	@XmlElement(name = "SSL_CM_MDM_Size_Preferences")
	private List<SizePreferences> sizePreferences;

	@XmlElement(name = "SSL_CM_MDM_Preferences_Brands")
	private String preferencesBrands;

	@XmlElement(name = "SSL_CM_MDM_Preferences_Discount")
	private String preferencesDiscount;

	@XmlElement(name = "SSL_CM_MDM_Preferences_Payment")
	private String preferencesPayment;

	@XmlElement(name = "SSL_CM_MDM_Preferences_Channel")
	private String preferencesChannel;

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return the residencePhone
	 */
	public String getResidencePhone() {
		return residencePhone;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @return the hasChildren
	 */
	public String getHasChildren() {
		return hasChildren;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @return the cityTier
	 */
	public String getCityTier() {
		return cityTier;
	}

	/**
	 * @return the gstNumber
	 */
	public String getGstNumber() {
		return gstNumber;
	}

	/**
	 * @return the homeStore
	 */
	public String getHomeStore() {
		return homeStore;
	}

	/**
	 * @return the homeStoreModifiedDate
	 */
	public String getHomeStoreModifiedDate() {
		return homeStoreModifiedDate;
	}

	/**
	 * @return the socialPicUploaded
	 */
	public String getSocialPicUploaded() {
		return socialPicUploaded;
	}

	/**
	 * @return the isSubscribed
	 */
	public String getIsSubscribed() {
		return isSubscribed;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @return the secondaryMobileNumber
	 */
	public String getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}

	/**
	 * @return the priceConscious
	 */
	public String getPriceConscious() {
		return priceConscious;
	}

	/**
	 * @return the socialAccount
	 */
	public List<SocialAccount> getSocialAccount() {
		return socialAccount;
	}

	/**
	 * @return the stylePreference
	 */
	public String getStylePreference() {
		return stylePreference;
	}

	/**
	 * @return the customerTitle
	 */
	public String getCustomerTitle() {
		return customerTitle;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @return the codVerificationDate
	 */
	public String getCodVerificationDate() {
		return codVerificationDate;
	}

	/**
	 * @return the verifiedForCod
	 */
	public String getVerifiedForCod() {
		return verifiedForCod;
	}

	/**
	 * @return the walletNumber
	 */
	public String getWalletNumber() {
		return walletNumber;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}

	/**
	 * @return the profilePicture
	 */
	public String getProfilePicture() {
		return profilePicture;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
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
	 * @return the addressLine3
	 */
	public String getAddressLine3() {
		return addressLine3;
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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the landmark
	 */
	public String getLandmark() {
		return landmark;
	}

	/**
	 * @return the paymentAddresses
	 */
	public List<PaymentAddresses> getPaymentAddresses() {
		return paymentAddresses;
	}

	/**
	 * @return the shipmentAddresses
	 */
	public List<ShipmentAddress> getShipmentAddresses() {
		return shipmentAddresses;
	}

	/**
	 * @return the sizePreferences
	 */
	public List<SizePreferences> getSizePreferences() {
		return sizePreferences;
	}

	/**
	 * @return the preferencesBrands
	 */
	public String getPreferencesBrands() {
		return preferencesBrands;
	}

	/**
	 * @return the preferencesDiscount
	 */
	public String getPreferencesDiscount() {
		return preferencesDiscount;
	}

	/**
	 * @return the preferencesPayment
	 */
	public String getPreferencesPayment() {
		return preferencesPayment;
	}

	/**
	 * @return the preferencesChannel
	 */
	public String getPreferencesChannel() {
		return preferencesChannel;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(final String dob) {
		this.dob = dob;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(final String gender) {
		this.gender = gender;
	}

	/**
	 * @param residencePhone
	 *            the residencePhone to set
	 */
	public void setResidencePhone(final String residencePhone) {
		this.residencePhone = residencePhone;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @param hasChildren
	 *            the hasChildren to set
	 */
	public void setHasChildren(final String hasChildren) {
		this.hasChildren = hasChildren;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @param cityTier
	 *            the cityTier to set
	 */
	public void setCityTier(final String cityTier) {
		this.cityTier = cityTier;
	}

	/**
	 * @param gstNumber
	 *            the gstNumber to set
	 */
	public void setGstNumber(final String gstNumber) {
		this.gstNumber = gstNumber;
	}

	/**
	 * @param homeStore
	 *            the homeStore to set
	 */
	public void setHomeStore(final String homeStore) {
		this.homeStore = homeStore;
	}

	/**
	 * @param homeStoreModifiedDate
	 *            the homeStoreModifiedDate to set
	 */
	public void setHomeStoreModifiedDate(final String homeStoreModifiedDate) {
		this.homeStoreModifiedDate = homeStoreModifiedDate;
	}

	/**
	 * @param socialPicUploaded
	 *            the socialPicUploaded to set
	 */
	public void setSocialPicUploaded(final String socialPicUploaded) {
		this.socialPicUploaded = socialPicUploaded;
	}

	/**
	 * @param isSubscribed
	 *            the isSubscribed to set
	 */
	public void setIsSubscribed(final String isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(final String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @param secondaryMobileNumber
	 *            the secondaryMobileNumber to set
	 */
	public void setSecondaryMobileNumber(final String secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
	}

	/**
	 * @param priceConscious
	 *            the priceConscious to set
	 */
	public void setPriceConscious(final String priceConscious) {
		this.priceConscious = priceConscious;
	}

	/**
	 * @param socialAccount
	 *            the socialAccount to set
	 */
	public void setSocialAccount(final List<SocialAccount> socialAccount) {
		this.socialAccount = socialAccount;
	}

	/**
	 * @param stylePreference
	 *            the stylePreference to set
	 */
	public void setStylePreference(final String stylePreference) {
		this.stylePreference = stylePreference;
	}

	/**
	 * @param customerTitle
	 *            the customerTitle to set
	 */
	public void setCustomerTitle(final String customerTitle) {
		this.customerTitle = customerTitle;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(final String userType) {
		this.userType = userType;
	}

	/**
	 * @param codVerificationDate
	 *            the codVerificationDate to set
	 */
	public void setCodVerificationDate(final String codVerificationDate) {
		this.codVerificationDate = codVerificationDate;
	}

	/**
	 * @param verifiedForCod
	 *            the verifiedForCod to set
	 */
	public void setVerifiedForCod(final String verifiedForCod) {
		this.verifiedForCod = verifiedForCod;
	}

	/**
	 * @param walletNumber
	 *            the walletNumber to set
	 */
	public void setWalletNumber(final String walletNumber) {
		this.walletNumber = walletNumber;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(final String pk) {
		this.pk = pk;
	}

	/**
	 * @param profilePicture
	 *            the profilePicture to set
	 */
	public void setProfilePicture(final String profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(final String uid) {
		this.uid = uid;
	}

	/**
	 * @param addressLine1
	 *            the addressLine1 to set
	 */
	public void setAddressLine1(final String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @param addressLine2
	 *            the addressLine2 to set
	 */
	public void setAddressLine2(final String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @param addressLine3
	 *            the addressLine3 to set
	 */
	public void setAddressLine3(final String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @param pincode
	 *            the pincode to set
	 */
	public void setPincode(final String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * @param landmark
	 *            the landmark to set
	 */
	public void setLandmark(final String landmark) {
		this.landmark = landmark;
	}

	/**
	 * @param paymentAddresses
	 *            the paymentAddresses to set
	 */
	public void setPaymentAddresses(
			final List<PaymentAddresses> paymentAddresses) {
		this.paymentAddresses = paymentAddresses;
	}

	/**
	 * @param shipmentAddresses
	 *            the shipmentAddresses to set
	 */
	public void setShipmentAddresses(
			final List<ShipmentAddress> shipmentAddresses) {
		this.shipmentAddresses = shipmentAddresses;
	}

	/**
	 * @param sizePreferences
	 *            the sizePreferences to set
	 */
	public void setSizePreferences(final List<SizePreferences> sizePreferences) {
		this.sizePreferences = sizePreferences;
	}

	/**
	 * @param preferencesBrands
	 *            the preferencesBrands to set
	 */
	public void setPreferencesBrands(final String preferencesBrands) {
		this.preferencesBrands = preferencesBrands;
	}

	/**
	 * @param preferencesDiscount
	 *            the preferencesDiscount to set
	 */
	public void setPreferencesDiscount(final String preferencesDiscount) {
		this.preferencesDiscount = preferencesDiscount;
	}

	/**
	 * @param preferencesPayment
	 *            the preferencesPayment to set
	 */
	public void setPreferencesPayment(final String preferencesPayment) {
		this.preferencesPayment = preferencesPayment;
	}

	/**
	 * @param preferencesChannel
	 *            the preferencesChannel to set
	 */
	public void setPreferencesChannel(final String preferencesChannel) {
		this.preferencesChannel = preferencesChannel;
	}

}
