/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderListResponseDetails {

    @XmlElement(name = "Order")
    private List<OrderDetailDTO> orders;

    @XmlAttribute(name = "TotalOrderList")
    private String totalOrders;

    /**
     * @return the totalOrders
     */
    public String getTotalOrders() {
        return totalOrders;
    }

    /**
     * @param totalOrders the totalOrders to set
     */
    public void setTotalOrders(final String totalOrders) {
        this.totalOrders = totalOrders;
    }

    /**
     * @return the orders
     */
    public List<OrderDetailDTO> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(final List<OrderDetailDTO> orders) {
        this.orders = orders;
    }
}
