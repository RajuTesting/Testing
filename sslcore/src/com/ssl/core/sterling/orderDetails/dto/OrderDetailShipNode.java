/**
 *
 */
package com.ssl.core.sterling.orderDetails.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.ssl.core.sterling.orderpush.dto.SterlingAddressDTO;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetailShipNode {

	@XmlAttribute(name = "Description")
	private String description;

	@XmlAttribute(name = "ShipNode")
	private String shipNode;

	@XmlElement(name = "ShipNodePersonInfo")
	private SterlingAddressDTO shipNodeInfo;

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
	 * @return the shipNode
	 */
	public String getShipNode() {
		return shipNode;
	}

	/**
	 * @param shipNode
	 *            the shipNode to set
	 */
	public void setShipNode(final String shipNode) {
		this.shipNode = shipNode;
	}

	/**
	 * @return the shipNodeInfo
	 */
	public SterlingAddressDTO getShipNodeInfo() {
		return shipNodeInfo;
	}

	/**
	 * @param shipNodeInfo
	 *            the shipNodeInfo to set
	 */
	public void setShipNodeInfo(final SterlingAddressDTO shipNodeInfo) {
		this.shipNodeInfo = shipNodeInfo;
	}

}
