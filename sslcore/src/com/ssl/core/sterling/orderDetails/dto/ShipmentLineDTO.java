/**
 *
 */
package com.ssl.core.sterling.orderDetails.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ShipmentLineDTO {

	@XmlAttribute(name = "ActualQuantity")
	private String actualQuantity;

	@XmlAttribute(name = "Quantity")
	private String qty;

	@XmlAttribute(name = "CustomerPickedQuantity")
	private String pickedQty;

	@XmlAttribute(name = "ItemID")
	private String itemId;

	@XmlAttribute(name = "ItemDesc")
	private String description;

	@XmlAttribute(name = "ShipmentLineNo")
	private String shipmentLine;

	@XmlAttribute(name = "PrimeLineNo")
	private String primeLine;

	/**
	 * @return the actualQuantity
	 */
	public String getActualQuantity() {
		return actualQuantity;
	}

	/**
	 * @param actualQuantity
	 *            the actualQuantity to set
	 */
	public void setActualQuantity(final String actualQuantity) {
		this.actualQuantity = actualQuantity;
	}

	/**
	 * @return the qty
	 */
	public String getQty() {
		return qty;
	}

	/**
	 * @param qty
	 *            the qty to set
	 */
	public void setQty(final String qty) {
		this.qty = qty;
	}

	/**
	 * @return the pickedQty
	 */
	public String getPickedQty() {
		return pickedQty;
	}

	/**
	 * @param pickedQty
	 *            the pickedQty to set
	 */
	public void setPickedQty(final String pickedQty) {
		this.pickedQty = pickedQty;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(final String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the shipmentLine
	 */
	public String getShipmentLine() {
		return shipmentLine;
	}

	/**
	 * @param shipmentLine
	 *            the shipmentLine to set
	 */
	public void setShipmentLine(final String shipmentLine) {
		this.shipmentLine = shipmentLine;
	}

	/**
	 * @return the primeLine
	 */
	public String getPrimeLine() {
		return primeLine;
	}

	/**
	 * @param primeLine
	 *            the primeLine to set
	 */
	public void setPrimeLine(final String primeLine) {
		this.primeLine = primeLine;
	}

}
