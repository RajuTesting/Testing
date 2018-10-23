/**
 *
 */
package com.ssl.core.sterling.orderDetails.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.ssl.core.sterling.orderList.dto.OrderLineItemDetailsDTO;
import com.ssl.core.sterling.orderpush.dto.LineChargesDTO;
import com.ssl.core.sterling.orderpush.dto.LinePriceInfoDTO;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetailLineDTO {

	@XmlAttribute(name = "DeliveryMethod")
	private String deliveryMethod;

	@XmlAttribute(name = "OrderedQty")
	private String orderedQty;

	@XmlAttribute(name = "OriginalOrderedQty")
	private String originalOrderedQty;

	@XmlAttribute(name = "PrimeLineNo")
	private String primeLineNo;

	@XmlAttribute(name = "SubLineNo")
	private String subLineNo;

	@XmlAttribute(name = "MinLineStatus")
	private String status;

	@XmlElement(name = "Shipnode")
	private OrderDetailShipNode shipNode;

	@XmlAttribute(name = "ProcureFromNode")
	private String procureFromNode;

	@XmlElement(name = "LinePriceInfo")
	private LinePriceInfoDTO linePriceInfo;

	@XmlElement(name = "LineCharges")
	private LineChargesDTO lineCharges;

	@XmlElement(name = "ItemDetails")
	private OrderLineItemDetailsDTO itemDetails;

	@XmlElement(name = "Shipments")
	private OrderDetailsShipmentsDTO shipments;


	/**
	 * @return the deliveryMethod
	 */
	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	/**
	 * @param deliveryMethod
	 *            the deliveryMethod to set
	 */
	public void setDeliveryMethod(final String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	/**
	 * @return the orderedQty
	 */
	public String getOrderedQty() {
		return orderedQty;
	}

	/**
	 * @return the originalOrderedQty
	 */
	public String getOriginalOrderedQty() {
		return originalOrderedQty;
	}

	/**
	 * @param originalOrderedQty
	 *            the originalOrderedQty to set
	 */
	public void setOriginalOrderedQty(final String originalOrderedQty) {
		this.originalOrderedQty = originalOrderedQty;
	}

	/**
	 * @param orderedQty
	 *            the orderedQty to set
	 */
	public void setOrderedQty(final String orderedQty) {
		this.orderedQty = orderedQty;
	}

	/**
	 * @return the primeLineNo
	 */
	public String getPrimeLineNo() {
		return primeLineNo;
	}

	/**
	 * @param primeLineNo
	 *            the primeLineNo to set
	 */
	public void setPrimeLineNo(final String primeLineNo) {
		this.primeLineNo = primeLineNo;
	}

	/**
	 * @return the subLineNo
	 */
	public String getSubLineNo() {
		return subLineNo;
	}

	/**
	 * @param subLineNo
	 *            the subLineNo to set
	 */
	public void setSubLineNo(final String subLineNo) {
		this.subLineNo = subLineNo;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
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
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}


	/**
	 * @return the procureFromNode
	 */
	public String getProcureFromNode() {
		return procureFromNode;
	}

	/**
	 * @param procureFromNode
	 *            the procureFromNode to set
	 */
	public void setProcureFromNode(final String procureFromNode) {
		this.procureFromNode = procureFromNode;
	}

	/**
	 * @return the linePriceInfo
	 */
	public LinePriceInfoDTO getLinePriceInfo() {
		return linePriceInfo;
	}

	/**
	 * @param linePriceInfo
	 *            the linePriceInfo to set
	 */
	public void setLinePriceInfo(final LinePriceInfoDTO linePriceInfo) {
		this.linePriceInfo = linePriceInfo;
	}

	/**
	 * @return the lineCharges
	 */
	public LineChargesDTO getLineCharges() {
		return lineCharges;
	}

	/**
	 * @param lineCharges
	 *            the lineCharges to set
	 */
	public void setLineCharges(final LineChargesDTO lineCharges) {
		this.lineCharges = lineCharges;
	}

	/**
	 * @return the itemDetails
	 */
	public OrderLineItemDetailsDTO getItemDetails() {
		return itemDetails;
	}

	/**
	 * @param itemDetails
	 *            the itemDetails to set
	 */
	public void setItemDetails(final OrderLineItemDetailsDTO itemDetails) {
		this.itemDetails = itemDetails;
	}

	/**
	 * @return the shipments
	 */
	public OrderDetailsShipmentsDTO getShipments() {
		return shipments;
	}

	/**
	 * @param shipments
	 *            the shipments to set
	 */
	public void setShipments(final OrderDetailsShipmentsDTO shipments) {
		this.shipments = shipments;
	}

}
