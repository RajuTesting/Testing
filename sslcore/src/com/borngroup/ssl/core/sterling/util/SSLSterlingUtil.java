package com.borngroup.ssl.core.sterling.util;

import de.hybris.platform.commerceservices.model.PickUpDeliveryModeModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.util.Config;

import org.apache.commons.lang.StringUtils;

public class SSLSterlingUtil {

    public static final String MQHOSTNAME = "sterling.mq.hostname";

    public static final String MQPORT = "sterling.mq.port";

    public static final String MQCHANNEL = "sterling.mq.channelName";

    public static final String MQQUEUEMANAGER = "sterling.mq.queueManager";

    public static final String DEFAULT_CREATE_SALES_ORDER_QUEUE = "IN_CREATE_ORDER_Q";

    public static String getCreateSalesOrderQueue() {
        String createSalesOrderQueue = Config.getParameter("sterling.hybris.createsalesorder.queue");
        if (StringUtils.isEmpty(createSalesOrderQueue)) {
            createSalesOrderQueue = DEFAULT_CREATE_SALES_ORDER_QUEUE;
        }
        return createSalesOrderQueue;
    }

    public static int getCreateSalesOrderRetryCount() {
        int retryCount = 3;
        if (StringUtils.isNotEmpty(Config.getParameter("sterling.createsalesorder.push.retry"))) {
            retryCount = Integer.parseInt(Config.getParameter("sterling.createsalesorder.push.retry"));
        }
        return retryCount;
    }

    public static boolean isPickupOrder(final AbstractOrderModel orderModel) {
        // Condition to be changed for Mixed orders(having both Pickup and Delivery Items).
        return orderModel.getEntries().get(0).getDeliveryMode() instanceof PickUpDeliveryModeModel;
    }
}
