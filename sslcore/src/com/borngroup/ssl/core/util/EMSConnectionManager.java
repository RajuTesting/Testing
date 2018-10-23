/**
 *
 */
package com.borngroup.ssl.core.util;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

import com.borngroup.ssl.core.exceptions.EMSConnectionFailureException;

/**
 * <p>
 * EMSConnectionManager.java : class for connecting and publishing data to EMS
 * <p>
 * Created By : raju.p@techouts.com
 *
 * @author Techouts
 */
public class EMSConnectionManager {
	private Session session = null;

	/**
	 * Method for creating JMS session
	 * 
	 * @return {@link Session}
	 * @throws EMSConnectionFailureException
	 * @throws JMSException
	 */
	public Session getSession() throws EMSConnectionFailureException, JMSException {
		Connection conn = EMSConnectionUtils.getInstance();
		session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		return session;

	}

	/**
	 * Method for connecting Queue
	 * 
	 * @param queueName
	 * @return queue
	 * @throws JMSException
	 */
	public Queue getQueue(final String queueName) throws JMSException {
		return session.createQueue(queueName);

	}
}
