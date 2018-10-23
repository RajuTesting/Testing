/**
 *
 */
package com.borngroup.ssl.core.util;

import de.hybris.platform.util.Config;

import javax.jms.JMSException;
import javax.jms.QueueConnection;

import com.borngroup.ssl.core.exceptions.EMSConnectionFailureException;
import com.tibco.tibjms.TibjmsQueueConnectionFactory;

/**
 * <p>
 * EMSConnectionUtils.java : class for establishing connection to queue Created
 * By : raju.p@techouts.com
 *
 * @author Techouts
 */
public class EMSConnectionUtils {
	private static QueueConnection queueConnection = null;
	private static TibjmsQueueConnectionFactory tCF = null;

	private EMSConnectionUtils() {
		//
	}

	/**
	 * Method for creating EMS queue connection
	 * 
	 * @return {@link QueueConnection}
	 * @throws EMSConnectionFailureException
	 */
	public static synchronized QueueConnection getInstance() throws EMSConnectionFailureException {
		if (queueConnection == null) {
			tCF = new TibjmsQueueConnectionFactory(Config.getParameter(CustomerMdmUtils.EMS_URL));
			queueConnection = getConnection();
			return queueConnection;
		}
		return queueConnection;
	}

	/**
	 * Method for creating EMS queue connection
	 * 
	 * @return {@link QueueConnection}
	 * @throws EMSConnectionFailureException
	 */
	private static QueueConnection getConnection() throws EMSConnectionFailureException {
		try {
			tCF.createQueueConnection(Config.getParameter(CustomerMdmUtils.EMS_USERNAME),
					CustomerMdmUtils.EMS_PASSWORD);
			queueConnection = (QueueConnection) tCF.createConnection();
		} catch (final JMSException exception) {
			throw new EMSConnectionFailureException(exception);
		}
		return queueConnection;
	}
}
