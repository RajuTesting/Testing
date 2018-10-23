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
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetailRequestDTO {

    @XmlAttribute(name = "BillToID")
    private String billToId;

    @XmlAttribute(name = "CustomerContactID")
    private String customerContactId;

    @XmlAttribute(name = "CustomerEMailID")
    private String customerEmail;

    @XmlAttribute(name = "EnterpriseCode")
    private String enterpriseCode;

    @XmlAttribute(name = "IgnoreOrdering")
    private String ignoreOrdering;

    @XmlAttribute(name = "DocumentType")
    private String documentType;

    @XmlElement(name = "OrderBy")
    private OrderByDTO orderBy;

    /**
     * @return the billToId
     */
    public String getBillToId() {
        return billToId;
    }

    /**
     * @param billToId the billToId to set
     */
    public void setBillToId(final String billToId) {
        this.billToId = billToId;
    }

    /**
     * @return the customerContactId
     */
    public String getCustomerContactId() {
        return customerContactId;
    }

    /**
     * @param customerContactId
     *        the customerContactId to set
     */
    public void setCustomerContactId(final String customerContactId) {
        this.customerContactId = customerContactId;
    }

    /**
     * @return the customerEmail
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * @param customerEmail
     *        the customerEmail to set
     */
    public void setCustomerEmail(final String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * @return the enterpriseCode
     */
    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    /**
     * @param enterpriseCode
     *        the enterpriseCode to set
     */
    public void setEnterpriseCode(final String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    /**
     * @return the ignoreOrdering
     */
    public String getIgnoreOrdering() {
        return ignoreOrdering;
    }

    /**
     * @param ignoreOrdering
     *        the ignoreOrdering to set
     */
    public void setIgnoreOrdering(final String ignoreOrdering) {
        this.ignoreOrdering = ignoreOrdering;
    }

    /**
     * @return the documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType
     *        the documentType to set
     */
    public void setDocumentType(final String documentType) {
        this.documentType = documentType;
    }

    public OrderByDTO getOrderBy() {
        if (orderBy == null) {
            orderBy = new OrderByDTO();
        }
        return orderBy;
    }

    public void setOrderBy(final OrderByDTO orderBy) {
        this.orderBy = orderBy;
    }

}
