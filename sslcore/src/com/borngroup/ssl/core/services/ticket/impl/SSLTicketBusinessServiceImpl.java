/**
 *
 */
package com.borngroup.ssl.core.services.ticket.impl;

import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.ticket.enums.CsEventReason;
import de.hybris.platform.ticket.enums.CsInterventionType;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.impl.DefaultTicketBusinessService;
import de.hybris.platform.ticket.strategies.TicketEventStrategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author nikhilbarar
 *
 */
public class SSLTicketBusinessServiceImpl extends DefaultTicketBusinessService {

	@Autowired
	private TicketEventStrategy ticketEventStrategy;

	@Override
	public CsCustomerEventModel addNoteToTicket(final CsTicketModel ticket,
			final CsInterventionType intervention, final CsEventReason reason,
			final String note, final Collection<MediaModel> attachments) {
		if (ticket == null) {
			throw new IllegalArgumentException("Cannot add note to null ticket");
		}
		if ((intervention == null) || (reason == null) || (note == null)
				|| ("".equals(note))) {
			throw new IllegalArgumentException(
					"Missing arguments required to create note");
		}

		final CsCustomerEventModel ret = ticketEventStrategy
				.createNoteForTicket(ticket, intervention, reason, note,
						attachments);
		getModelService().save(ret);
		getModelService().saveAll(ret.getAttachments());
		getModelService().refresh(ticket);

		return ret;
	}

	@Override
	public CsTicketModel createTicket(final CsTicketModel ticket,
			final CsCustomerEventModel creationEvent) {
		this.ticketEventStrategy.ensureTicketEventSetupForCreationEvent(ticket,
				creationEvent);

		getModelService().saveAll(new Object[] { ticket, creationEvent });
		getModelService().refresh(ticket);

		return ticket;
	}
}
