/**
 *
 */
package com.ssl.core.sterling.orderDetails.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetailLinesDTO {

	@XmlElement(name = "OrderLine")
	private List<OrderDetailLineDTO> orderLines;

	/**
	 * @return the orderLines
	 */
	public List<OrderDetailLineDTO> getOrderLines() {
		return orderLines;
	}

	/**
	 * @param orderLines
	 *            the orderLines to set
	 */
	public void setOrderLines(final List<OrderDetailLineDTO> orderLines) {
		this.orderLines = orderLines;
	}

}
