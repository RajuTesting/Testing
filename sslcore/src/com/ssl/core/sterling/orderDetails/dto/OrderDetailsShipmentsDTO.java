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
public class OrderDetailsShipmentsDTO {

	@XmlElement(name = "Shipment")
	List<OrderDetailsShipmentDTO> shipment;

	/**
	 * @return the shipment
	 */
	public List<OrderDetailsShipmentDTO> getShipment() {
		return shipment;
	}

	/**
	 * @param shipment
	 *            the shipment to set
	 */
	public void setShipment(final List<OrderDetailsShipmentDTO> shipment) {
		this.shipment = shipment;
	}

}
