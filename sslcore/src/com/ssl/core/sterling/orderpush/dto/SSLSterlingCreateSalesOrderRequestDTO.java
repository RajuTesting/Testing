/**
 *
 */
package com.ssl.core.sterling.orderpush.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author manikmalhotra
 *
 */
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class SSLSterlingCreateSalesOrderRequestDTO {

	@XmlAttribute(name = "BillToID")
	private String billToID;

	@XmlAttribute(name = "OrderNo")
	private String orderNo;

	@XmlAttribute(name = "DocumentType")
	private String documentType;

	@XmlAttribute(name = "CustomerContactID")
	private String customerContactID;

	@XmlAttribute(name = "CustomerEMailID")
	private String customerEMailID;

	@XmlAttribute(name = "EnterpriseCode")
	private String enterpriseCode;

	@XmlAttribute(name = "EntryType")
	private String entryType;

	@XmlAttribute(name = "SourcingClassification")
	private String sourcingClassification;

	@XmlAttribute(name = "OrderDate")
	private String orderDate;

	@XmlAttribute(name = "SellerOrganizationCode")
	private String sellerOrganizationCode;

	@XmlAttribute(name = "ReqShipDate")
	private String reqShipDate;

	@XmlAttribute(name = "AllocationRuleID")
	private String allocationRuleID;

	@XmlElement(name = "Extn")
	private ExtensionDTO extn;

	@XmlElement(name = "PersonInfoBillTo")
	private SterlingAddressDTO billingAddress;

	@XmlElement(name = "PersonInfoShipTo")
	private SterlingAddressDTO shippingAddress;

	@XmlElement(name = "PriceInfo")
	private PriceInfoDTO priceInfo;

	@XmlElement(name = "PaymentMethods")
	private PaymentMethodsDTO paymentMethods;

	@XmlElement(name = "HeaderCharges")
	private HeaderChargesDTO headerCharges;

	@XmlElement(name = "OrderLines")
	private OrderLinesDTO orderLines;

	public String getBillToID() {
		return billToID;
	}

	public void setBillToID(final String billToID) {
		this.billToID = billToID;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(final String documentType) {
		this.documentType = documentType;
	}

	public String getCustomerContactID() {
		return customerContactID;
	}

	public void setCustomerContactID(final String customerContactID) {
		this.customerContactID = customerContactID;
	}

	public String getCustomerEMailID() {
		return customerEMailID;
	}

	public void setCustomerEMailID(final String customerEMailID) {
		this.customerEMailID = customerEMailID;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(final String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(final String entryType) {
		this.entryType = entryType;
	}

	public String getSourcingClassification() {
		return sourcingClassification;
	}

	public void setSourcingClassification(final String sourcingClassification) {
		this.sourcingClassification = sourcingClassification;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(final String orderDate) {
		this.orderDate = orderDate;
	}

	public String getSellerOrganizationCode() {
		return sellerOrganizationCode;
	}

	public void setSellerOrganizationCode(final String sellerOrganizationCode) {
		this.sellerOrganizationCode = sellerOrganizationCode;
	}

	public String getReqShipDate() {
		return reqShipDate;
	}

	public void setReqShipDate(final String reqShipDate) {
		this.reqShipDate = reqShipDate;
	}

	public String getAllocationRuleID() {
		return allocationRuleID;
	}

	public void setAllocationRuleID(final String allocationRuleID) {
		this.allocationRuleID = allocationRuleID;
	}

	public ExtensionDTO getExtn() {
		return extn;
	}

	public void setExtn(final ExtensionDTO extn) {
		this.extn = extn;
	}

	public SterlingAddressDTO getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(final SterlingAddressDTO billingAddress) {
		this.billingAddress = billingAddress;
	}

	public SterlingAddressDTO getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(final SterlingAddressDTO shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public PriceInfoDTO getPriceInfo() {
		return priceInfo;
	}

	public void setPriceInfo(final PriceInfoDTO priceInfo) {
		this.priceInfo = priceInfo;
	}

	public PaymentMethodsDTO getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(final PaymentMethodsDTO paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public HeaderChargesDTO getHeaderCharges() {
		return headerCharges;
	}

	public void setHeaderCharges(final HeaderChargesDTO headerCharges) {
		this.headerCharges = headerCharges;
	}

	public OrderLinesDTO getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(final OrderLinesDTO orderLines) {
		this.orderLines = orderLines;
	}

}
