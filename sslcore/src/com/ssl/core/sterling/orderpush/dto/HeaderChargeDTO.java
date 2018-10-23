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
public class HeaderChargeDTO {

	@XmlAttribute(name = "ChargeName")
	private String chargeName;

	@XmlAttribute(name = "ChargeCategory")
	private String chargeCategory;

	@XmlAttribute(name = "ChargeAmount")
	private String chargeAmount;

	@XmlAttribute(name = "Reference")
	private String reference;

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(final String chargeName) {
		this.chargeName = chargeName;
	}

	public String getChargeCategory() {
		return chargeCategory;
	}

	public void setChargeCategory(final String chargeCategory) {
		this.chargeCategory = chargeCategory;
	}

	public String getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(final String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(final String reference) {
		this.reference = reference;
	}

}
