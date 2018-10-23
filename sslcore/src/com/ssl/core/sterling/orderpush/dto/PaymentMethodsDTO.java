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
public class PaymentMethodsDTO {

	@XmlElement(name = "PaymentMethod")
	private List<PaymentMethodDTO> paymentMethod;

	public List<PaymentMethodDTO> getPaymentMethod() {
		if (paymentMethod == null) {
			paymentMethod = new ArrayList<>();
		}
		return paymentMethod;
	}

	public void setPaymentMethod(final List<PaymentMethodDTO> paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
