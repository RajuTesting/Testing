/**
 *
 */
package com.borngroup.ssl.core.personalshoppers.dao;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import java.util.List;

import com.borngroup.ssl.core.enums.PSAppointmentStatus;
import com.borngroup.ssl.core.model.PSAppointmentFeedbackModel;
import com.borngroup.ssl.core.model.PSAppointmentModel;
import com.borngroup.ssl.core.model.PSServicesModel;

/**
 * @author S53299
 *
 */
public interface PSAppointmentDao {

	public SearchPageData<PSAppointmentModel> searchAppointmentsPagewise(PSAppointmentModel appointmentModel,
			List<PSAppointmentStatus> appointmentStatusList, boolean onlyRescheduledAppointments, Long modelPk,
			PageableData pageableData);

	public SearchPageData<PSServicesModel> searchPersonalShopperServicesPagewise(final PSServicesModel servicesModel,
			final Long modelPk, final PageableData pageableData);

	public SearchPageData<PSAppointmentFeedbackModel> searchAppointmentFeedbackByPage(PageableData pageableData);

	/* public List<PSServicesModel> getServiceTypeList(); */

	public List<Object> getAppointmentsCount();

}
