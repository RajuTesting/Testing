/**
 *
 */
package com.borngroup.ssl.core.personalshoppers.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import java.util.List;

import com.borngroup.ssl.core.enums.PSAppointmentStatus;
import com.borngroup.ssl.core.model.PSAppointmentFeedbackModel;
import com.borngroup.ssl.core.model.PSAppointmentModel;
import com.borngroup.ssl.core.model.PSServicesModel;
import com.borngroup.ssl.personalshoppers.data.PSAppointmentData;

/**
 * @author S53299
 *
 */
public interface PSAppointmentService {

	public SearchPageData<PSAppointmentModel> searchAppointmentsPagewise(PSAppointmentModel appointmentModel,
			List<PSAppointmentStatus> appointmentStatusList, boolean onlyRescheduledAppointments, Long modelPk,
			PageableData pageableData);

	public void saveOrUpdateAppointment(PSAppointmentModel appointmentModel);

	public SearchPageData<PSServicesModel> searchPersonalShopperServicesPagewise(final PSServicesModel servicesModel,
			final Long modelPk, final PageableData pageableData);

	public void saveAppointmentFeedback(final PSAppointmentFeedbackModel psAppointmentFeedbackModel);

	public SearchPageData<PSAppointmentFeedbackModel> searchAppointmentFeedbackByPage(PageableData pageableData);

	public void sendEmailToCustomer(PSAppointmentModel appointmentModel);

	public List<Object> getAppointmentsCount();

	/**
	 * Method for assigning personal shopper
	 * @param appointmentModel
	 * @param appointmentData
	 */
	void assignPersonalShopper(PSAppointmentModel appointmentModel, PSAppointmentData appointmentData);

}