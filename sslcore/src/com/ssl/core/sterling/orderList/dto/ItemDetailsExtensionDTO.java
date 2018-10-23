/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemDetailsExtensionDTO {

	@XmlAttribute(name = "ExtnBrandName")
	private String brandName;

	@XmlAttribute(name = "ExtnPickUpPersonName")
	private String pickPersonName;

	@XmlAttribute(name = "ExtnPickUpPersonMobile")
	private String pickPersonMobile;

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName
	 *            the brandName to set
	 */
	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return the pickPersonName
	 */
	public String getPickPersonName() {
		return pickPersonName;
	}

	/**
	 * @param pickPersonName
	 *            the pickPersonName to set
	 */
	public void setPickPersonName(final String pickPersonName) {
		this.pickPersonName = pickPersonName;
	}

	/**
	 * @return the pickPersonMobile
	 */
	public String getPickPersonMobile() {
		return pickPersonMobile;
	}

	/**
	 * @param pickPersonMobile
	 *            the pickPersonMobile to set
	 */
	public void setPickPersonMobile(final String pickPersonMobile) {
		this.pickPersonMobile = pickPersonMobile;
	}

}
