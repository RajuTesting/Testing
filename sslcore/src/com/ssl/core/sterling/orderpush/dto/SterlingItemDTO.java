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
public class SterlingItemDTO {

	@XmlAttribute(name = "ItemID")
	private String itemID;

	@XmlAttribute(name = "ItemShortDesc")
	private String itemShortDesc;

	@XmlAttribute(name = "ItemDesc")
	private String itemDesc;

	@XmlAttribute(name = "UnitOfMeasure")
	private String unitOfMeasure;

	public String getItemID() {
		return itemID;
	}

	public void setItemID(final String itemID) {
		this.itemID = itemID;
	}

	public String getItemShortDesc() {
		return itemShortDesc;
	}

	public void setItemShortDesc(final String itemShortDesc) {
		this.itemShortDesc = itemShortDesc;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(final String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(final String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

}
