/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetailDTO {

    @XmlAttribute(name = "BillToID")
    private String billToId;

    @XmlAttribute(name = "OrderNo")
    private String orderNumber;

    @XmlAttribute(name = "OrderDate")
    private String orderDate;

    @XmlAttribute(name = "MinOrderStatus")
    private String status;

    @XmlElement(name = "OverallTotals")
    private OrderTotalDTO orderTotal;

    @XmlAttribute(name = "OriginalTotalAmount")
    private String originalTotalAmount;

    /**
     * @return the originalTotalAmount
     */
    public String getOriginalTotalAmount() {
        return originalTotalAmount;
    }

    /**
     * @param originalTotalAmount the originalTotalAmount to set
     */
    public void setOriginalTotalAmount(final String originalTotalAmount) {
        this.originalTotalAmount = originalTotalAmount;
    }

    /**
     * @return the billToId
     */
    public String getBillToId() {
        return billToId;
    }

    /**
     * @param billToId the billToId to set
     */
    public void setBillToId(final String billToId) {
        this.billToId = billToId;
    }

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(final String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(final String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * @return the orderTotal
     */
    public OrderTotalDTO getOrderTotal() {
        return orderTotal;
    }

    /**
     * @param orderTotal the orderTotal to set
     */
    public void setOrderTotal(final OrderTotalDTO orderTotal) {
        this.orderTotal = orderTotal;
    }

}
