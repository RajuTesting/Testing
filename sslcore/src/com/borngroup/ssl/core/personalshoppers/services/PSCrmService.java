/**
 *
 */
package com.borngroup.ssl.core.personalshoppers.services;

import de.hybris.platform.commercefacades.user.data.CustomerData;

import com.borngroup.ssl.core.model.PSAppointmentModel;
import com.ssl.core.ps.crm.dto.PSBookingAppointmentResponse;

/**
 * <p>
 * PSStoreMasterDAO.java : Service to connect Crm
 *
 * <p>
 * Created By : raju.p@techouts.com
 * <p>
 *
 * @author Techouts
 */
public interface PSCrmService {

	/**
	 * @Description :To post(create) the appointment data to crm and get the
	 *              response
	 * @param psAppointment
	 *            : PS appointment
	 * @param customerData
	 *            : Customer Data
	 * @return @{link PSBookingAppointmentResponse} : The PS booking appointment
	 *         response
	 */
	PSBookingAppointmentResponse createBookingAppointment(final PSAppointmentModel psAppointment,CustomerData customerData);
}
