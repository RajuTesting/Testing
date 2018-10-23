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
public class OrderListDetailsDTO {

    @XmlAttribute(name = "BillToID")
    private String billToId;

    @XmlAttribute(name = "CustomerEmailID")
    private String customerEmailId;

    @XmlAttribute(name = "CustomerContactID")
    private String contactNumber;

    @XmlAttribute(name = "DocumentType")
    private String documentType;

    @XmlAttribute(name = "EnterpriseCode")
    private String enterpriseCode;

    @XmlAttribute(name = "IgnoreOrdering")
    private String IgnoreOrdering;

    /**
     * @return the billToId
     */
    public String getBillToId() {
        return billToId;
    }

    /**
     * @param billToId
     *        the billToId to set
     */
    public void setBillToId(final String billToId) {
        this.billToId = billToId;
    }

    /**
     * @return the customerEmailId
     */
    public String getCustomerEmailId() {
        return customerEmailId;
    }

    /**
     * @param customerEmailId
     *        the customerEmailId to set
     */
    public void setCustomerEmailId(final String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber
     *        the contactNumber to set
     */
    public void setContactNumber(final String contactNumber) {
        this.contactNumber = contactNumber;
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
        return IgnoreOrdering;
    }

    /**
     * @param ignoreOrdering
     *        the ignoreOrdering to set
     */
    public void setIgnoreOrdering(final String ignoreOrdering) {
        IgnoreOrdering = ignoreOrdering;
    }

}
