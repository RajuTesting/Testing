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
public class LinePriceInfoDTO {

	@XmlAttribute(name = "IsPriceLocked")
	private String isPriceLocked;

	@XmlAttribute(name = "UnitPrice")
	private String unitPrice;

	public String getIsPriceLocked() {
		return isPriceLocked;
	}

	public void setIsPriceLocked(final String isPriceLocked) {
		this.isPriceLocked = isPriceLocked;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(final String unitPrice) {
		this.unitPrice = unitPrice;
	}

}
