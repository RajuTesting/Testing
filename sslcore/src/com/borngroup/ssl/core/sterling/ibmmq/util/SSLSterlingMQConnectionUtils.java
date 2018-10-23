package com.borngroup.ssl.core.sterling.ibmmq.util;

import de.hybris.platform.util.Config;

import javax.jms.JMSException;
import javax.jms.QueueConnection;

import com.borngroup.ssl.core.sterling.ibmmq.exception.SSLSterlingMQHandlerException;
import com.borngroup.ssl.core.sterling.util.SSLSterlingUtil;
import com.ibm.mq.jms.MQConnection;
import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.common.CommonConstants;

public class SSLSterlingMQConnectionUtils {
    private static MQConnection conn = null;
    private static MQConnectionFactory cf = null;
    private static MQQueueConnectionFactory queueCF = null;
    private static QueueConnection queueConnection = null;

    public static final String ERROR_CODE_101 = "ERROR_CODE_101";

    private SSLSterlingMQConnectionUtils() {

    }

    public static QueueConnection getQueueInstance() throws SSLSterlingMQHandlerException {

        if (queueConnection == null) {
            queueCF = new MQQueueConnectionFactory();
            try {
                queueCF.setIntProperty(CommonConstants.WMQ_CONNECTION_MODE, CommonConstants.WMQ_CM_CLIENT);
                queueConnection = getQueueConnection();
            } catch (final JMSException e1) {
                throw new SSLSterlingMQHandlerException(Config.getParameter(ERROR_CODE_101) + e1.getCause());
            } catch (final SSLSterlingMQHandlerException e) {
                throw new SSLSterlingMQHandlerException(Config.getParameter(ERROR_CODE_101) + e.getCause());
            }
        }

        return queueConnection;
    }

    public static MQConnection getInstance() throws SSLSterlingMQHandlerException {
        if (conn == null) {
            synchronized (SSLSterlingMQConnectionUtils.class) {
                if (conn == null) {
                    cf = new MQConnectionFactory();
                    try {
                        cf.setIntProperty(CommonConstants.WMQ_CONNECTION_MODE, CommonConstants.WMQ_CM_CLIENT);
                        conn = getConnection();
                    } catch (final JMSException e1) {
                        throw new SSLSterlingMQHandlerException(Config.getParameter(ERROR_CODE_101) + e1.getCause());
                    } catch (final SSLSterlingMQHandlerException e) {
                        throw new SSLSterlingMQHandlerException(Config.getParameter(ERROR_CODE_101) + e.getCause());
                    }
                }
            }
        }
        return conn;
    }

    private static MQConnection getConnection() throws SSLSterlingMQHandlerException {
        try {
            cf.setHostName(Config.getParameter(SSLSterlingUtil.MQHOSTNAME));
            cf.setPort(Integer.parseInt(Config.getParameter(SSLSterlingUtil.MQPORT)));
            cf.setChannel(Config.getParameter(SSLSterlingUtil.MQCHANNEL));
            cf.setQueueManager(Config.getParameter(SSLSterlingUtil.MQQUEUEMANAGER));

            conn = (MQConnection) cf.createConnection();
        } catch (final JMSException e) {
            throw new SSLSterlingMQHandlerException(Config.getParameter(ERROR_CODE_101) + e.getCause());
        }
        return conn;
    }

    private static QueueConnection getQueueConnection() throws SSLSterlingMQHandlerException {
        try {
            queueCF.setHostName(Config.getParameter(SSLSterlingUtil.MQHOSTNAME));
            queueCF.setPort(Integer.parseInt(Config.getParameter(SSLSterlingUtil.MQPORT)));
            queueCF.setChannel(Config.getParameter(SSLSterlingUtil.MQCHANNEL));
            queueCF.setQueueManager(Config.getParameter(SSLSterlingUtil.MQQUEUEMANAGER));

            queueConnection = (QueueConnection) queueCF.createConnection();
        } catch (final JMSException e) {
            throw new SSLSterlingMQHandlerException(Config.getParameter(ERROR_CODE_101) + e.getCause());
        }
        return queueConnection;
    }

}