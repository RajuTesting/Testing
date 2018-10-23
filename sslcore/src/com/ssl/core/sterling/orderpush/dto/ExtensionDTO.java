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
public class ExtensionDTO {

    @XmlAttribute(name = "ExtnIP")
    private String extnIP;

    @XmlAttribute(name = "ExtnCustLoyaltyNo")
    private String custLoyaltyNo;

    @XmlAttribute(name = "ExtnPickUpPersonName")
    private String pickUpPersonName;

    @XmlAttribute(name = "ExtnPickUpPersonMobile")
    private String pickUpPersonMobile;
    
    @XmlAttribute(name = "ExtnSSWalletId")
	private String walletId;

	@XmlAttribute(name = "ExtnRegisteredCust")
	private String registeredCust;
	 /**
     * @return the extnIP
     */
    public String getExtnIP() {
        return extnIP;
    }
    /**
     * @param extnIP the extnIP to set
     */
    public void setExtnIP(final String extnIP) {
        this.extnIP = extnIP;
    }
    /**
     * @return the custLoyaltyNo
     */
    public String getCustLoyaltyNo() {
        return custLoyaltyNo;
    }
    /**
     * @param custLoyaltyNo the custLoyaltyNo to set
     */
    public void setCustLoyaltyNo(final String custLoyaltyNo) {
        this.custLoyaltyNo = custLoyaltyNo;
    }
    /**
     * @return the pickUpPersonName
     */
    public String getPickUpPersonName() {
        return pickUpPersonName;
    }
    /**
     * @param pickUpPersonName the pickUpPersonName to set
     */
    public void setPickUpPersonName(final String pickUpPersonName) {
        this.pickUpPersonName = pickUpPersonName;
    }
    /**
     * @return the pickUpPersonMobile
     */
    public String getPickUpPersonMobile() {
        return pickUpPersonMobile;
    }
    /**
     * @param pickUpPersonMobile the pickUpPersonMobile to set
     */
    public void setPickUpPersonMobile(final String pickUpPersonMobile) {
        this.pickUpPersonMobile = pickUpPersonMobile;
    }
    /**
     * @return the walletId
     */
	public String getWalletId() {
		return walletId;
	}
	 /**
     * @param walletId the walletId to set
     */
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	 /**
     * @return the registeredCust
     */
	public String getRegisteredCust() {
		return registeredCust;
	}
	 /**
     * @param registeredCust the registeredCust to set
     */
	public void setRegisteredCust(String registeredCust) {
		this.registeredCust = registeredCust;
	}

}
