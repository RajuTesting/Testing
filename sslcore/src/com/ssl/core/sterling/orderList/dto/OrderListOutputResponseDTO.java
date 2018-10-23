/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderListOutputResponseDTO {

    @XmlElement(name = "OrderList")
    private OrderListResponseDetails orders;

    /**
     * @return the orders
     */
    public OrderListResponseDetails getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(final OrderListResponseDetails orders) {
        this.orders = orders;
    }

}
