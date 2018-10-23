/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.util.Config;

import java.io.Serializable;

import com.amazonaws.util.StringUtils;


/**
 * @author kanagaraj.k
 *
 */
public class SubmitOrderEvent extends de.hybris.platform.order.events.SubmitOrderEvent implements ClusterAwareEvent
{

    public SubmitOrderEvent(final Serializable source)
    {
        super(source);
    }

    /* (non-Javadoc)
     * @see de.hybris.platform.servicelayer.event.ClusterAwareEvent#publish(int, int)
     */
    @Override
    public boolean publish(final int sourceNodeId, final int targetNodeId)
    {
        final String nodeId = Config.getParameter("order.process.event.node.id");
        if (!StringUtils.isNullOrEmpty(nodeId))
        {
            return targetNodeId == Integer.valueOf(nodeId);
        }
        return true;
    }

}
