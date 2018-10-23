/**
 *
 */
package com.ssl.core.sterling.orderDetails.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ssl.core.sterling.orderList.dto.ItemDetailsExtensionDTO;
import com.ssl.core.sterling.orderList.dto.OrderTotalDTO;
import com.ssl.core.sterling.orderList.dto.SterlingResponseError;
import com.ssl.core.sterling.orderpush.dto.PaymentMethodsDTO;
import com.ssl.core.sterling.orderpush.dto.SterlingAddressDTO;

/**
 * @author pankajgandhi
 *
 */
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetailsResponseDTO {

	@XmlAttribute(name = "BillToID")
	private String billToId;

	@XmlAttribute(name = "OrderNo")
	private String orderNo;

	@XmlAttribute(name = "CustomerEMailID")
	private String customerEmail;

	@XmlAttribute(name = "OrderDate")
	private String orderDate;

	@XmlAttribute(name = "ReqShipDate")
	private String shipDate;

	@XmlAttribute(name = "MinOrderStatus")
	private String status;

	@XmlElement(name = "OverallTotals")
	private OrderTotalDTO orderTotal;

	@XmlElement(name = "Extn")
	private ItemDetailsExtensionDTO extension;

	@XmlElement(name = "PersonInfoBillTo")
	private SterlingAddressDTO billToAddress;

	@XmlElement(name = "PaymentMethods")
	private PaymentMethodsDTO paymentMedhods;

	@XmlElement(name = "OrderLines")
	private OrderDetailLinesDTO orderLines;

	@XmlElement(name = "Errors")
	private SterlingResponseError errors;

	@XmlElement(name = "Shipments")
	private OrderDetailsShipmentsDTO shipments;

	@XmlAttribute(name = "OriginalTotalAmount")
	private String originalTotalAmount;

	/**
	 * @return the billToId
	 */
	public String getBillToId() {
		return billToId;
	}

	/**
	 * @param billToId
	 *            the billToId to set
	 */
	public void setBillToId(final String billToId) {
		this.billToId = billToId;
	}

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo
	 *            the orderNo to set
	 */
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * @param customerEmail
	 *            the customerEmail to set
	 */
	public void setCustomerEmail(final String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(final String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the shipDate
	 */
	public String getShipDate() {
		return shipDate;
	}

	/**
	 * @param shipDate
	 *            the shipDate to set
	 */
	public void setShipDate(final String shipDate) {
		this.shipDate = shipDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @return the orderTotal
	 */
	public OrderTotalDTO getOrderTotal() {
		return orderTotal;
	}

	/**
	 * @param orderTotal
	 *            the orderTotal to set
	 */
	public void setOrderTotal(final OrderTotalDTO orderTotal) {
		this.orderTotal = orderTotal;
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

	/**
	 * @return the billToAddress
	 */
	public SterlingAddressDTO getBillToAddress() {
		return billToAddress;
	}

	/**
	 * @param billToAddress
	 *            the billToAddress to set
	 */
	public void setBillToAddress(final SterlingAddressDTO billToAddress) {
		this.billToAddress = billToAddress;
	}

	/**
	 * @return the paymentMedhods
	 */
	public PaymentMethodsDTO getPaymentMedhods() {
		return paymentMedhods;
	}

	/**
	 * @param paymentMedhods
	 *            the paymentMedhods to set
	 */
	public void setPaymentMedhods(final PaymentMethodsDTO paymentMedhods) {
		this.paymentMedhods = paymentMedhods;
	}

	/**
	 * @return the orderLines
	 */
	public OrderDetailLinesDTO getOrderLines() {
		return orderLines;
	}

	/**
	 * @param orderLines
	 *            the orderLines to set
	 */
	public void setOrderLines(final OrderDetailLinesDTO orderLines) {
		this.orderLines = orderLines;
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

	/**
	 * @return the shipments
	 */
	public OrderDetailsShipmentsDTO getShipments() {
		return shipments;
	}

	/**
	 * @param shipments
	 *            the shipments to set
	 */
	public void setShipments(final OrderDetailsShipmentsDTO shipments) {
		this.shipments = shipments;
	}

	/**
	 * @return the originalTotalAmount
	 */
	public String getOriginalTotalAmount() {
		return originalTotalAmount;
	}

	/**
	 * @param originalTotalAmount
	 *            the originalTotalAmount to set
	 */
	public void setOriginalTotalAmount(final String originalTotalAmount) {
		this.originalTotalAmount = originalTotalAmount;
	}

}
