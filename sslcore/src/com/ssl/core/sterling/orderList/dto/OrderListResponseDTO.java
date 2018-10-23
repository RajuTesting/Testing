/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pankajgandhi
 *
 */
@XmlRootElement(name = "Page")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderListResponseDTO {

	@XmlAttribute(name = "IsFirstPage")
	private String firstPage;

	@XmlAttribute(name = "IsLastPage")
	private String lastPage;

	@XmlAttribute(name = "PageNumber")
	private String pageNumber;

	@XmlAttribute(name = "PageSize")
	private String pageSize;

	@XmlElement(name = "Output")
	private OrderListOutputResponseDTO output;

	@XmlElement(name = "Errors")
	private SterlingResponseError errors;

	/**
	 * @return the firstPage
	 */
	public String getFirstPage() {
		return firstPage;
	}

	/**
	 * @param firstPage
	 *            the firstPage to set
	 */
	public void setFirstPage(final String firstPage) {
		this.firstPage = firstPage;
	}

	/**
	 * @return the lastPage
	 */
	public String getLastPage() {
		return lastPage;
	}

	/**
	 * @param lastPage
	 *            the lastPage to set
	 */
	public void setLastPage(final String lastPage) {
		this.lastPage = lastPage;
	}

	/**
	 * @return the pageNumber
	 */
	public String getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber
	 *            the pageNumber to set
	 */
	public void setPageNumber(final String pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the pageSize
	 */
	public String getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(final String pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the output
	 */
	public OrderListOutputResponseDTO getOutput() {
		return output;
	}

	/**
	 * @param output
	 *            the output to set
	 */
	public void setOutput(final OrderListOutputResponseDTO output) {
		this.output = output;
	}

	/**
	 * @return the errors
	 */
	public SterlingResponseError getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(final SterlingResponseError errors) {
		this.errors = errors;
	}

}
