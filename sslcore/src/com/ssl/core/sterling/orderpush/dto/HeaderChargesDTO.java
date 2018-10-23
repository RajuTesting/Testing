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
public class HeaderChargesDTO {

	@XmlElement(name = "HeaderCharge")
	private List<HeaderChargeDTO> headerCharge;

	public List<HeaderChargeDTO> getHeaderCharge() {
		if (headerCharge == null) {
			headerCharge = new ArrayList<>();
		}
		return headerCharge;
	}

	public void setHeaderCharge(final List<HeaderChargeDTO> headerCharge) {
		this.headerCharge = headerCharge;
	}

}
