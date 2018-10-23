/**
 * 
 */
package com.borngroup.ssl.core.exceptions;

import de.hybris.platform.servicelayer.interceptor.InterceptorException;


/**
 * @author Deepak
 * 
 */
public class DefaultInterceptorException extends InterceptorException
{
	String message;

	/**
	 * @param message
	 */
	public DefaultInterceptorException(final String message)
	{
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage()
	{
		return this.message;
	}
}
