/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderLineItemDetailsDTO {

	@XmlAttribute(name = "ItemID")
	private String itemId;

	@XmlElement(name = "PrimaryInformation")
	private ItemDetailsPrimaryInformationDTO itemDetails;

	@XmlElement(name = "Extn")
	private ItemDetailsExtensionDTO extension;

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(final String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the itemDetails
	 */
	public ItemDetailsPrimaryInformationDTO getItemDetails() {
		return itemDetails;
	}

	/**
	 * @param itemDetails
	 *            the itemDetails to set
	 */
	public void setItemDetails(final ItemDetailsPrimaryInformationDTO itemDetails) {
		this.itemDetails = itemDetails;
	}

	/**
	 * @return the extension
	 */
	public ItemDetailsExtensionDTO getExtension() {
		return extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	public void setExtension(final ItemDetailsExtensionDTO extension) {
		this.extension = extension;
	}

}
