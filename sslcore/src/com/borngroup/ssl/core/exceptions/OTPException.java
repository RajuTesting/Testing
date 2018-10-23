/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.borngroup.ssl.core.exceptions;

/**
 * Thrown when an user entered invalid OTP for verification or violated some OTP config rules
 */
public class OTPException extends Exception {

    /**
     * @param message
     */
    public OTPException(final String message) {
        super(message);
    }

    public OTPException(final Throwable cause) {
        super(cause);
    }

    public OTPException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public OTPException() {
        super("OTP is either Invalid or Expired");
    }

}
