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
 * @author techouts
 *
 */
@XmlRootElement(name = "SSL_CM_MDM_Size_Preference")
@XmlAccessorType(XmlAccessType.FIELD)
public class SizePreferences  implements Serializable {

	@XmlElement(name = "SSL_CM_MDM_SP_Category")
	private String category;

	@XmlElement(name = "SSL_CM_MDM_SP_Length")
	private String length;

	@XmlElement(name = "SSL_CM_MDM_Size")
	private String size;

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(final String category) {
		this.category = category;
	}

	/**
	 * @return the length
	 */
	public String getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(final String length) {
		this.length = length;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(final String size) {
		this.size = size;
	}


}
