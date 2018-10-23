/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemDetailsPrimaryInformationDTO {

	@XmlAttribute(name = "ShortDescription")
	private String shortDescription;

	@XmlAttribute(name = "ImageID")
	private String imageId;

	@XmlAttribute(name = "ImageLocation")
	private String imageLocation;

	@XmlAttribute(name = "SizeCode")
	private String sizeCode;

	@XmlAttribute(name = "ColorCode")
	private String colorCode;

	@XmlAttribute(name = "ReturnWindow")
	private String returnWindow;

	@XmlAttribute(name = "IsReturnable")
	private String isReturnable;

	@XmlAttribute(name = "ProductLine")
	private String productLine;

	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription
	 *            the shortDescription to set
	 */
	public void setShortDescription(final String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * @return the imageId
	 */
	public String getImageId() {
		return imageId;
	}

	/**
	 * @param imageId
	 *            the imageId to set
	 */
	public void setImageId(final String imageId) {
		this.imageId = imageId;
	}

	/**
	 * @return the imageLocation
	 */
	public String getImageLocation() {
		return imageLocation;
	}

	/**
	 * @param imageLocation
	 *            the imageLocation to set
	 */
	public void setImageLocation(final String imageLocation) {
		this.imageLocation = imageLocation;
	}

	/**
	 * @return the sizeCode
	 */
	public String getSizeCode() {
		return sizeCode;
	}

	/**
	 * @param sizeCode
	 *            the sizeCode to set
	 */
	public void setSizeCode(final String sizeCode) {
		this.sizeCode = sizeCode;
	}

	/**
	 * @return the colorCode
	 */
	public String getColorCode() {
		return colorCode;
	}

	/**
	 * @param colorCode
	 *            the colorCode to set
	 */
	public void setColorCode(final String colorCode) {
		this.colorCode = colorCode;
	}

	/**
	 * @return the returnWindow
	 */
	public String getReturnWindow() {
		return returnWindow;
	}

	/**
	 * @param returnWindow
	 *            the returnWindow to set
	 */
	public void setReturnWindow(final String returnWindow) {
		this.returnWindow = returnWindow;
	}

	/**
	 * @return the isReturnable
	 */
	public String getIsReturnable() {
		return isReturnable;
	}

	/**
	 * @param isReturnable
	 *            the isReturnable to set
	 */
	public void setIsReturnable(final String isReturnable) {
		this.isReturnable = isReturnable;
	}

	/**
	 * @return the productLine
	 */
	public String getProductLine() {
		return productLine;
	}

	/**
	 * @param productLine
	 *            the productLine to set
	 */
	public void setProductLine(final String productLine) {
		this.productLine = productLine;
	}

}
