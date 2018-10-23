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
 * @author Techouts
 *
 */
@XmlRootElement(name = "SSL_CM_Social_Account")
@XmlAccessorType(XmlAccessType.FIELD)
public class SocialAccount implements Serializable {

	@XmlElement(name = "SSL_CM_Social_Account_Id")
	private String socialAccountId;

	@XmlElement(name = "SSL_CM_Social_Account_Contact_emailid")
	private String socialAccountContactEmailid;

	@XmlElement(name = "SSL_CM_Social_Accounts_Identifier")
	private String socialAccountsIdentifier;

	/**
	 * @return the socialAccountId
	 */
	public String getSocialAccountId() {
		return socialAccountId;
	}

	/**
	 * @return the socialAccountContactEmailid
	 */
	public String getSocialAccountContactEmailid() {
		return socialAccountContactEmailid;
	}

	/**
	 * @return the socialAccountsIdentifier
	 */
	public String getSocialAccountsIdentifier() {
		return socialAccountsIdentifier;
	}

	/**
	 * @param socialAccountId the socialAccountId to set
	 */
	public void setSocialAccountId(String socialAccountId) {
		this.socialAccountId = socialAccountId;
	}

	/**
	 * @param socialAccountContactEmailid the socialAccountContactEmailid to set
	 */
	public void setSocialAccountContactEmailid(String socialAccountContactEmailid) {
		this.socialAccountContactEmailid = socialAccountContactEmailid;
	}

	/**
	 * @param socialAccountsIdentifier the socialAccountsIdentifier to set
	 */
	public void setSocialAccountsIdentifier(String socialAccountsIdentifier) {
		this.socialAccountsIdentifier = socialAccountsIdentifier;
	}

}
