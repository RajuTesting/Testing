/**
 *
 */
package com.ssl.core.sterling.orderDetails.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetailsShipmentDTO {

	@XmlAttribute(name = "ShipmentNo")
	private String shipmentNumber;

	@XmlAttribute(name = "Status")
	private String status;

	@XmlAttribute(name = "StatusDate")
	private String statusDate;

	@XmlElement(name = "ShipmentLines")
	private ShipmentLinesDTO shipments;

	@XmlElement(name = "ShipNode")
	private OrderDetailShipNode shipNode;

	@XmlAttribute(name = "DocumentType")
	private String documentType;

	/**
	 * @return the shipmentNumber
	 */
	public String getShipmentNumber() {
		return shipmentNumber;
	}

	/**
	 * @param shipmentNumber
	 *            the shipmentNumber to set
	 */
	public void setShipmentNumber(final String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @return the statusDate
	 */
	public String getStatusDate() {
		return statusDate;
	}

	/**
	 * @param statusDate
	 *            the statusDate to set
	 */
	public void setStatusDate(final String statusDate) {
		this.statusDate = statusDate;
	}

	/**
	 * @return the shipments
	 */
	public ShipmentLinesDTO getShipments() {
		return shipments;
	}

	/**
	 * @param shipments
	 *            the shipments to set
	 */
	public void setShipments(final ShipmentLinesDTO shipments) {
		this.shipments = shipments;
	}

	/**
	 * @return the shipNode
	 */
	public OrderDetailShipNode getShipNode() {
		return shipNode;
	}

	/**
	 * @param shipNode
	 *            the shipNode to set
	 */
	public void setShipNode(final OrderDetailShipNode shipNode) {
		this.shipNode = shipNode;
	}

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType
	 *            the documentType to set
	 */
	public void setDocumentType(final String documentType) {
		this.documentType = documentType;
	}

}
