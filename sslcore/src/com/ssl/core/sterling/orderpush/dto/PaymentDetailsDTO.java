/**
 *
 */
package com.ssl.core.sterling.orderpush.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author manikmalhotra
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentDetailsDTO {

    @XmlAttribute(name = "ChargeType")
    private String chargeType;

    @XmlAttribute(name = "ProcessedAmount")
    private String processedAmount;

    @XmlAttribute(name = "RequestAmount")
    private String requestAmount;

    @XmlAttribute(name = "AuthorizationID")
    private String authorizationID;

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(final String chargeType) {
        this.chargeType = chargeType;
    }

    public String getProcessedAmount() {
        return processedAmount;
    }

    public void setProcessedAmount(final String processedAmount) {
        this.processedAmount = processedAmount;
    }

    public String getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(final String requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getAuthorizationID() {
        return authorizationID;
    }

    public void setAuthorizationID(final String authorizationID) {
        this.authorizationID = authorizationID;
    }

}
