/**
 *
 */
package com.borngroup.ssl.core.personalshoppers.services.impl;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.data.enums.APIMethodEnum;
import com.borngroup.ssl.core.data.enums.ContentTypeEnum;
import com.borngroup.ssl.core.model.PSAppointmentModel;
import com.borngroup.ssl.core.personalshoppers.constants.PersonalShopperConstants;
import com.borngroup.ssl.core.personalshoppers.services.PSCrmService;
import com.borngroup.ssl.core.util.CommonHelper;
import com.borngroup.ssl.core.util.models.APIInputObject;
import com.ssl.core.ps.crm.dto.PSAppointmentCustomerRequest;
import com.ssl.core.ps.crm.dto.PSAppointmentRequest;
import com.ssl.core.ps.crm.dto.PSBookingAppointmentRequest;
import com.ssl.core.ps.crm.dto.PSBookingAppointmentResponse;

/**
 * <p>
 * PSCrmServiceImpl.java : Service to connect Crm
 * <p>
 * Created By : raju.p@techouts.com
 * <p>
 *
 * @author Techouts
 */
public class PSCrmServiceImpl implements PSCrmService {

	private static final CommonHelper commonHelper = CommonHelper.getInstance();

	@Resource(name = "configurationService")
	private ConfigurationService configurationService;
	private static final Logger LOG = Logger.getLogger(PSCrmServiceImpl.class);
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public PSBookingAppointmentResponse createBookingAppointment(final PSAppointmentModel psAppointment,
			final CustomerData customerData) {

		PSBookingAppointmentResponse bookingAppointmentResponse = null;
		final PSBookingAppointmentRequest bookingRequest = createAppointMentRequest(psAppointment, customerData);
		APIInputObject apiInputObject = null;
		try {
			
			apiInputObject = commonHelper.getAPIInputObject(
					String.format(configurationService.getConfiguration().getString(PersonalShopperConstants.PS_CRM_BASE_API),
							configurationService.getConfiguration().getString(PersonalShopperConstants.PS_CRM_CREATE_APPOINTMENT_API)),
					"", commonHelper.convertToJSONString(bookingRequest, false), ContentTypeEnum.JSON,
					APIMethodEnum.POST, false);
			apiInputObject.setBasicAuthorization(null);
			 LOG.info("Personal Shopper Book Appointment Request "+apiInputObject);
		} catch (final Exception e) {
			LOG.error("Cause is {}", e);
		}

		try {
			bookingAppointmentResponse = (PSBookingAppointmentResponse) commonHelper.callAPIJSONResponse(apiInputObject,
					PSBookingAppointmentResponse.class);
			LOG.info("Booking API Response "+bookingAppointmentResponse);
		} catch (final Exception e) {
			LOG.error("Cause is {}", e);
		}
		return bookingAppointmentResponse;
	}

	/**
	 * @Description : creates request to post to crm
	 * @param psAppointment
	 * @param customerData
	 * @return {@link PSBookingAppointmentRequest} :The PS booking appointment
	 *         request
	 */
	private PSBookingAppointmentRequest createAppointMentRequest(final PSAppointmentModel psAppointment,
			final CustomerData customerData) {
		final PSBookingAppointmentRequest booking = new PSBookingAppointmentRequest();
		final PSAppointmentRequest appointmentRequest = new PSAppointmentRequest();
		appointmentRequest.setStoreNumber(
				psAppointment.getPsStoreMaster() != null ? psAppointment.getPsStoreMaster().getStoreId() : "");
		appointmentRequest.setAppointmentId(psAppointment.getCode());
		appointmentRequest
				.setStartDate(commonHelper.getFormattedDate(psAppointment.getAppointmnetDate(), "yyyy-MM-dd HH:mm:ss"));
		appointmentRequest.setPersonalShopperId(psAppointment.getCrmPersonalShopper() != null &&  !StringUtils.isEmpty(psAppointment.getCrmPersonalShopper().getPsUniqueId())
				? psAppointment.getCrmPersonalShopper().getPsUniqueId() : "");
		appointmentRequest.setPsEmployeeId(psAppointment.getCrmPersonalShopper() != null &&  !StringUtils.isEmpty(psAppointment.getCrmPersonalShopper().getPsEmployeeId())
				? psAppointment.getCrmPersonalShopper().getPsEmployeeId() : configurationService.getConfiguration().getString(PersonalShopperConstants.PS_APPOINTMENT_DEFAULT_EMP_ID));
		appointmentRequest.setCategoryId(configurationService.getConfiguration().getString(PersonalShopperConstants.PS_APPONTMENT_DEFAULT_BOOKING_ID));
		appointmentRequest.setOccasionId(configurationService.getConfiguration().getString(PersonalShopperConstants.PS_APPONTMENT_DEFAULT_BOOKING_ID));  
		appointmentRequest.setTypeOfBookingId(
				psAppointment.getBookingType() != null ? psAppointment.getBookingType().getBookingTypeId() : configurationService.getConfiguration().getString(PersonalShopperConstants.PS_APPONTMENT_DEFAULT_BOOKING_ID));
		appointmentRequest
				.setServiceId(configurationService.getConfiguration().getString(PersonalShopperConstants.PS_APPOINTMENT_DEFAULT_SERVICE_ID));
		booking.setAppointment(appointmentRequest);
		booking.setHardBook(Boolean.TRUE);
		booking.setAction("");
		final PSAppointmentCustomerRequest customer = new PSAppointmentCustomerRequest();
		customer.setFirstName(psAppointment.getCustomerName());
		customer.setPhoneNumber(psAppointment.getCustomerMobileNo());
		customer.setEmailID(psAppointment.getCustomerEmail());
		if (customerData != null && !StringUtils.isEmpty(customerData.getUid())  && !customerData.getUid().equalsIgnoreCase("anonymous")) {
			customer.setHybrisID(customerData.getUid());
		}else{
			customer.setHybrisID(StringUtils.EMPTY);
		}
		booking.setCustomer(customer);
		return booking;
	}

}
