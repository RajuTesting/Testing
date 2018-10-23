/**
 *
 */
package com.ssl.core.sterling.orderpush.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author manikmalhotra
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderLinesDTO {

	@XmlElement(name = "OrderLine")
	private List<OrderLineDTO> orderLine;

	public List<OrderLineDTO> getOrderLine() {
		if (orderLine == null) {
			orderLine = new ArrayList<>();
		}
		return orderLine;
	}

	public void setOrderLine(final List<OrderLineDTO> orderLine) {
		this.orderLine = orderLine;
	}

}
