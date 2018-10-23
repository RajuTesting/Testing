/**
 *
 */
package com.borngroup.ssl.core.exceptions;

/**
 * @author t.balagopalan
 *
 */
public class SslCommonServiceException extends Exception
{

    /**
     * @param strException
     * @param ex
     */


    public SslCommonServiceException(final String strException, final Exception ex)
    {
        super(strException, ex);
    }

    public SslCommonServiceException(final String strException)
    {
        super(strException);
    }
}
