/**
 *
 */
package com.ssl.core.customer.mdm.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Techouts
 *
 */
@XmlRootElement(name = "SSL_CMS")
@XmlAccessorType(XmlAccessType.FIELD)
public class MDMEcomCustDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@XmlElement(name = "SSL_CM")
	private CustomerDTO customer;
	/**
	 * @return the customer
	 */
	public CustomerDTO getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}


}
