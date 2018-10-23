package com.borngroup.ssl.core.sterling.ibmmq.exception;

public class SSLSterlingMQHandlerException extends Exception {
    private String errorCode;
    private String errorMessage;

    public SSLSterlingMQHandlerException() {

    }

    public SSLSterlingMQHandlerException(final String message) {
        super(message);
    }

    public SSLSterlingMQHandlerException(final String errorCode, final String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
