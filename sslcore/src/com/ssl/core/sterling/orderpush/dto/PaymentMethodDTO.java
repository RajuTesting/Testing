/**
 *
 */
package com.ssl.core.sterling.orderpush.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author manikmalhotra
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentMethodDTO {

    @XmlAttribute(name = "CreditCardNo")
    private String creditCardNo;

    @XmlAttribute(name = "FirstName")
    private String firstName;

    @XmlAttribute(name = "LastName")
    private String lastName;

    @XmlAttribute(name = "PaymentReference1")
    private String paymentReference1;

    @XmlAttribute(name = "PaymentReference2")
    private String paymentReference2;

    @XmlAttribute(name = "PaymentReference3")
    private String paymentReference3;

    @XmlAttribute(name = "PaymentReference4")
    private String paymentReference4;

    @XmlAttribute(name = "PaymentReference5")
    private String paymentReference5;

    @XmlAttribute(name = "PaymentReference6")
    private String paymentReference6;

    @XmlAttribute(name = "PaymentReference7")
    private String paymentReference7;

    @XmlAttribute(name = "PaymentReference8")
    private String paymentReference8;

    @XmlAttribute(name = "PaymentType")
    private String paymentType;

    @XmlElement(name = "PaymentDetails")
    private List<PaymentDetailsDTO> paymentDetails;

    @XmlAttribute(name = "TotalCharged")
    private String totalCharged;

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(final String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPaymentReference1() {
        return paymentReference1;
    }

    public void setPaymentReference1(final String paymentReference1) {
        this.paymentReference1 = paymentReference1;
    }

    public String getPaymentReference2() {
        return paymentReference2;
    }

    public void setPaymentReference2(final String paymentReference2) {
        this.paymentReference2 = paymentReference2;
    }

    public String getPaymentReference3() {
        return paymentReference3;
    }

    public void setPaymentReference3(final String paymentReference3) {
        this.paymentReference3 = paymentReference3;
    }

    public String getPaymentReference4() {
        return paymentReference4;
    }

    public void setPaymentReference4(final String paymentReference4) {
        this.paymentReference4 = paymentReference4;
    }

    public String getPaymentReference5() {
        return paymentReference5;
    }

    public void setPaymentReference5(final String paymentReference5) {
        this.paymentReference5 = paymentReference5;
    }

    public String getPaymentReference6() {
        return paymentReference6;
    }

    public void setPaymentReference6(final String paymentReference6) {
        this.paymentReference6 = paymentReference6;
    }

    public String getPaymentReference7() {
        return paymentReference7;
    }

    public void setPaymentReference7(final String paymentReference7) {
        this.paymentReference7 = paymentReference7;
    }

    public String getPaymentReference8() {
        return paymentReference8;
    }

    public void setPaymentReference8(final String paymentReference8) {
        this.paymentReference8 = paymentReference8;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(final String paymentType) {
        this.paymentType = paymentType;
    }

    public List<PaymentDetailsDTO> getPaymentDetails() {
        if (paymentDetails == null) {
            this.paymentDetails = new ArrayList<>();
        }
        return paymentDetails;
    }

    public void setPaymentDetails(final List<PaymentDetailsDTO> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    /**
     * @return the totalCharged
     */
    public String getTotalCharged() {
        return totalCharged;
    }

    /**
     * @param totalCharged
     *        the totalCharged to set
     */
    public void setTotalCharged(final String totalCharged) {
        this.totalCharged = totalCharged;
    }

}
