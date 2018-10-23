/**
 *
 */
package com.ssl.core.sterling.orderDetails.dto;

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
public class ShipmentLinesDTO {

	@XmlElement(name = "ShipmentLine")
	private List<ShipmentLineDTO> shipmentLine;

	@XmlAttribute(name = "TotalNumberOfRecords")
	private String totalRecords;

	/**
	 * @return the shipmentLine
	 */
	public List<ShipmentLineDTO> getShipmentLine() {
		return shipmentLine;
	}

	/**
	 * @param shipmentLine
	 *            the shipmentLine to set
	 */
	public void setShipmentLine(final List<ShipmentLineDTO> shipmentLine) {
		this.shipmentLine = shipmentLine;
	}

	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords
	 *            the totalRecords to set
	 */
	public void setTotalRecords(final String totalRecords) {
		this.totalRecords = totalRecords;
	}

}
