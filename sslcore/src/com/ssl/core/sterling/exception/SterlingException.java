package com.ssl.core.sterling.exception;

public class SterlingException extends Exception {

    /** Error Description. **/
    private final String errorMsg;

    /**
     * @param errorMsg
     */
    public SterlingException(final String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @param cause
     *        : Actual cause
     */
    public SterlingException(final String errorMsg, final Throwable cause) {
        super(errorMsg, cause);
        this.errorMsg = errorMsg;
    }

    /**
     * @return the error description.
     */
    public String getErrorMsg() {
        return this.errorMsg;
    }

}
