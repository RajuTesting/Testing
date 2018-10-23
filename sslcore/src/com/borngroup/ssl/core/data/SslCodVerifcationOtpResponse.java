/**
 *
 */
package com.borngroup.ssl.core.data;

/**
 * @author Nagarro-Dev
 *
 */
public class SslCodVerifcationOtpResponse {

    private String responseStatus;
    private String maskedOtpMobileNumber;

    /**
     * @return the responseStatus
     */
    public String getResponseStatus() {
        return responseStatus;
    }

    /**
     * @param responseStatus the responseStatus to set
     */
    public void setResponseStatus(final String responseStatus) {
        this.responseStatus = responseStatus;
    }

    /**
     * @return the maskedOtpMobileNumber
     */
    public String getMaskedOtpMobileNumber() {
        return maskedOtpMobileNumber;
    }

    /**
     * @param maskedOtpMobileNumber the maskedOtpMobileNumber to set
     */
    public void setMaskedOtpMobileNumber(final String maskedOtpMobileNumber) {
        this.maskedOtpMobileNumber = maskedOtpMobileNumber;
    }

}
