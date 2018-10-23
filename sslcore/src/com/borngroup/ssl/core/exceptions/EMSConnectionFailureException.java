/**
 * 
 */
package com.borngroup.ssl.core.exceptions;

/**
 * @author raju.p
 *
 */
public class EMSConnectionFailureException extends Exception {
	public EMSConnectionFailureException(final Exception exception) {
		super(exception);
	}

	/**
	 * @param message
	 */
	public EMSConnectionFailureException(String message) {
		super(message);
	}
}
