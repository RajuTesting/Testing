/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pankajgandhi
 *
 */
@XmlRootElement(name = "Input")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderInputRequestDTO {

    @XmlElement(name = "Order")
    private OrderDetailRequestDTO order;

    /**
     * @return the order
     */
    public OrderDetailRequestDTO getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(final OrderDetailRequestDTO order) {
        this.order = order;
    }

}
