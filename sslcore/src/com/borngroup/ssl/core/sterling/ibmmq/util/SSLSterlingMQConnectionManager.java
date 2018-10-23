package com.borngroup.ssl.core.sterling.ibmmq.util;

import de.hybris.platform.util.Config;

import java.io.InputStream;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.sterling.ibmmq.exception.SSLSterlingMQHandlerException;

public class SSLSterlingMQConnectionManager {

    private static final Logger LOG = Logger.getLogger(SSLSterlingMQConnectionManager.class);

    private Connection conn = null;
    private QueueConnection queueConn = null;
    private Session session = null;
    private QueueSession queueSession = null;

    public static final String ERROR_CODE_102 = "ERROR_CODE_102";
    public static final String ERROR_CODE_103 = "ERROR_CODE_103";

    Properties prop = new Properties();
    InputStream input = null;

    public Session getSession() throws SSLSterlingMQHandlerException {
        conn = SSLSterlingMQConnectionUtils.getInstance();
        if (conn != null) {
            try {
                session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
                return session;
            } catch (final JMSException e) {
                throw new SSLSterlingMQHandlerException(Config.getParameter(ERROR_CODE_102) + e.getCause());

            }
        }
        return session;

    }

    public QueueSession getQueueSession() throws SSLSterlingMQHandlerException {
        queueConn = SSLSterlingMQConnectionUtils.getQueueInstance();
        if (queueConn != null) {
            try {
                queueSession = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                return queueSession;
            } catch (final JMSException e) {
                throw new SSLSterlingMQHandlerException(Config.getParameter(ERROR_CODE_102) + e.getCause());

            }
        }
        return queueSession;

    }

    public Queue getQueue(final String queueName) throws SSLSterlingMQHandlerException {
        try {
            return session.createQueue(queueName);
        } catch (final JMSException e) {
            LOG.error("Error while getting Queue from Session: " + e.getMessage());
        }
        return null;
    }

    public Queue getMQQueue(final String queueName) throws SSLSterlingMQHandlerException {
        try {
            return queueSession.createQueue(queueName);
        } catch (final JMSException e) {
            LOG.error("Error while getting MQ Queue from Session: " + e.getMessage());
        }
        return null;
    }

    public void closeConnection() throws SSLSterlingMQHandlerException {
        try {
            conn.close();
        } catch (final JMSException e) {
            throw new SSLSterlingMQHandlerException(Config.getParameter(ERROR_CODE_103) + e.getCause());
        }
    }

}
