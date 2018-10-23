package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.impl.DefaultTicketService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class DefaultSSLTicketService extends DefaultTicketService
{

    @Override
    public List getTicketsForOrder(final OrderModel order)
    {
        final List tickets = super.getTicketsForOrder(order);

        if (tickets.size() > 1)
        {
            final List ticketsList = new ArrayList<CsTicketModel>(tickets);

            Collections.sort(ticketsList, new Comparator<CsTicketModel>()
            {
                @Override
                public int compare(final CsTicketModel p1, final CsTicketModel p2)
                {
                    if (p1 == null || p2 == null)
                    {
                        return 0;
                    }
                    return p2.getModifiedtime().compareTo(p1.getModifiedtime());
                }
            });
            return ticketsList;
        }

        return tickets;


    }

}
