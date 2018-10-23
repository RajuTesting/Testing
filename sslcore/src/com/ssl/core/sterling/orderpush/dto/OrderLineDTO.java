/**
 *
 */
package com.ssl.core.sterling.orderpush.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssl.core.sterling.orderList.dto.OrderLineItemDetailsDTO;

/**
 * @author manikmalhotra
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderLineDTO {

    @XmlAttribute(name = "FulfillmentType")
    private String fulfillmentType;

    @XmlAttribute(name = "DeliveryMethod")
    private String deliveryMethod;

    @XmlAttribute(name = "OrderedQty")
    private String orderedQty;

    @XmlAttribute(name = "PrimeLineNo")
    private String primeLineNo;

    @XmlAttribute(name = "SubLineNo")
    private String subLineNo;

    @XmlAttribute(name = "ShipNode")
    private String shipNode;

    @XmlAttribute(name = "GiftWrap")
    private String giftWrap;

    @XmlAttribute(name = "Status")
    private String status;

    @XmlElement(name = "Instructions")
    private InstructionsDTO instructions;

    @XmlElement(name = "Item")
    private SterlingItemDTO item;

    @XmlElement(name = "ItemDetails")
    private OrderLineItemDetailsDTO itemDetails;

    @XmlElement(name = "LinePriceInfo")
    private LinePriceInfoDTO linePriceInfo;

    @XmlElement(name = "LineCharges")
    private LineChargesDTO lineCharges;

    public String getFulfillmentType() {
        return fulfillmentType;
    }

    public void setFulfillmentType(final String fulfillmentType) {
        this.fulfillmentType = fulfillmentType;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(final String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(final String orderedQty) {
        this.orderedQty = orderedQty;
    }

    public String getPrimeLineNo() {
        return primeLineNo;
    }

    public void setPrimeLineNo(final String primeLineNo) {
        this.primeLineNo = primeLineNo;
    }

    public String getSubLineNo() {
        return subLineNo;
    }

    public void setSubLineNo(final String subLineNo) {
        this.subLineNo = subLineNo;
    }

    public String getShipNode() {
        return shipNode;
    }

    public void setShipNode(final String shipNode) {
        this.shipNode = shipNode;
    }

    public String getGiftWrap() {
        return giftWrap;
    }

    public void setGiftWrap(final String giftWrap) {
        this.giftWrap = giftWrap;
    }

    public InstructionsDTO getInstructions() {
        return instructions;
    }

    public void setInstructions(final InstructionsDTO instructions) {
        this.instructions = instructions;
    }

    public SterlingItemDTO getItem() {
        return item;
    }

    public void setItem(final SterlingItemDTO item) {
        this.item = item;
    }

    public LinePriceInfoDTO getLinePriceInfo() {
        return linePriceInfo;
    }

    public void setLinePriceInfo(final LinePriceInfoDTO linePriceInfo) {
        this.linePriceInfo = linePriceInfo;
    }

    public LineChargesDTO getLineCharges() {
        return lineCharges;
    }

    public void setLineCharges(final LineChargesDTO lineCharges) {
        this.lineCharges = lineCharges;
    }

}
