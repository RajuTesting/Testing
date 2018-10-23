/**
 * 
 */
package com.borngroup.ssl.core.exceptions;

/**
 * @author raju.p
 *
 */
public class CustomAuthenticationFailureException extends Exception
{
    public CustomAuthenticationFailureException(final Exception exception)
    {
        super(exception);
    }

    /**
     * @param message
     */
    public CustomAuthenticationFailureException(String message)
    {
        super(message);
    }
}
