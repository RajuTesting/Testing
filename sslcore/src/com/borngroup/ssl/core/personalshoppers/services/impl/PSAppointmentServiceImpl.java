/**
 *
 */
package com.borngroup.ssl.core.personalshoppers.services.impl;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.mail.MailUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.borngroup.ssl.core.enums.PSAppointmentStatus;
import com.borngroup.ssl.core.model.PSAppointmentFeedbackModel;
import com.borngroup.ssl.core.model.PSAppointmentModel;
import com.borngroup.ssl.core.model.PSPersonalShopperModel;
import com.borngroup.ssl.core.model.PSServicesModel;
import com.borngroup.ssl.core.personalshoppers.dao.PSAppointmentDao;
import com.borngroup.ssl.core.personalshoppers.services.PSAppointmentService;
import com.borngroup.ssl.personalshoppers.data.PSAppointmentData;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * @author S53299
 *
 */
public class PSAppointmentServiceImpl implements PSAppointmentService {

	Logger log = Logger.getLogger(PSAppointmentServiceImpl.class);

	private PSAppointmentDao psAppointmentDao;

	private ModelService modelService;
	
	@Resource(name="flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	public PSAppointmentDao getPsAppointmentDao() {
		return psAppointmentDao;
	}

	public void setPsAppointmentDao(final PSAppointmentDao psAppointmentDao) {
		this.psAppointmentDao = psAppointmentDao;
	}

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(final ModelService modelService) {
		this.modelService = modelService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.borngroup.ssl.core.personalshoppers.services.PSAppointmentService#
	 * searchAppointmentsPagewise(com.borngroup.ssl.personalshoppers.data.
	 * PSAppointmentData,
	 * de.hybris.platform.commerceservices.search.pagedata.PaginationData)
	 */
	@Override
	public SearchPageData<PSAppointmentModel> searchAppointmentsPagewise(final PSAppointmentModel appointmentModel,
			final List<PSAppointmentStatus> appointmentStatusList, final boolean onlyRescheduledAppointments,
			final Long modelPk, final PageableData pageableData) {

		log.info("-------------PSAppointmentServiceImpl/searchAppointmentsPagewise----------------------");

		return psAppointmentDao.searchAppointmentsPagewise(appointmentModel, appointmentStatusList,
				onlyRescheduledAppointments, modelPk, pageableData);
	}

	@Override
	public void saveOrUpdateAppointment(final PSAppointmentModel appointmentModel) {
		log.info("-------------PSAppointmentServiceImpl/saveOrUpdateAppointment----------------------"
				+ appointmentModel.getCode());

		modelService.save(appointmentModel);
		modelService.refresh(appointmentModel);

	}
	
	
	@Override
	public void assignPersonalShopper(final PSAppointmentModel appointmentModel,
			final PSAppointmentData appointmentData) {

		final List<PSPersonalShopperModel> personalShoppers = appointmentModel
				.getPsStoreMaster().getPersonalShoppers();

		if (CollectionUtils.isEmpty(personalShoppers)) {
			return;
		}
		List<PSPersonalShopperModel> pss=new ArrayList<>(personalShoppers);
		List<PSPersonalShopperModel> result = pss.stream()                
	                .filter(ps -> ps.getIsPSActive())     
	                .collect(Collectors.toList()); 
		if(CollectionUtils.isEmpty(result)){
			return;
		}
		Collections.sort(result, new Comparator() {
			@Override
			public int compare(final Object o1, final Object o2) {
				final PSPersonalShopperModel service1 = (PSPersonalShopperModel) o1;
				final PSPersonalShopperModel service2 = (PSPersonalShopperModel) o2;
				return service1.getPsPriority().compareTo(service2.getPsPriority());
			}
		});
		if (!CollectionUtils.isEmpty(result)) {
			appointmentModel.setCrmPersonalShopper(result.get(0));
		}
	}
	

	@Override
	public SearchPageData<PSServicesModel> searchPersonalShopperServicesPagewise(final PSServicesModel servicesModel,
			final Long modelPk, final PageableData pageableData) {
		log.info("-------------PSAppointmentServiceImpl/searchPersonalShopperServicesPagewise----------------------");

		return psAppointmentDao.searchPersonalShopperServicesPagewise(servicesModel, modelPk, pageableData);
	}

	@Override
	public void saveAppointmentFeedback(final PSAppointmentFeedbackModel psAppointmentFeedbackModel) {
		log.info("----------PSAppointmentServiceImpl/saveAppointment----------------------");
		modelService.save(psAppointmentFeedbackModel);
		modelService.refresh(psAppointmentFeedbackModel);

	}

	@Override
	public SearchPageData<PSAppointmentFeedbackModel> searchAppointmentFeedbackByPage(final PageableData pageableData) {
		log.info("----------PSAppointmentServiceImpl/searchAppointmentFeedbackByPage----------------------");
		return psAppointmentDao.searchAppointmentFeedbackByPage(pageableData);
	}

	/*
	 * @Override public void sendEmailToCustomer(final PSAppointmentModel
	 * appointmentModel) {
	 *
	 * log.
	 * info("------inside personal shopper sendEmailToCustomer---------------");
	 *
	 * try { final HtmlEmail htmlEmail = (HtmlEmail)
	 * MailUtils.getPreConfiguredEmail();
	 *
	 * htmlEmail.addTo(appointmentModel.getCustomerEmail() == null ?
	 * Config.getParameter("ps.email.backup.emailid") :
	 * appointmentModel.getCustomerEmail());
	 *
	 * htmlEmail.setSubject(Config.getParameter("ps.email.subject"));
	 *
	 * String finalEmailBody = Config.getParameter("ps.email.body");
	 *
	 * finalEmailBody = StringUtils.replace(finalEmailBody, "${customerName}",
	 * appointmentModel.getCustomerName());
	 *
	 * finalEmailBody = StringUtils.replace(finalEmailBody, "${service}",
	 * appointmentModel.getServiceType().getServiceName());
	 *
	 * finalEmailBody = StringUtils.replace(finalEmailBody,
	 * "${appointmentDate}", appointmentModel.getAppointmnetDate().toString());
	 *
	 * finalEmailBody = StringUtils.replace(finalEmailBody, "${store}",
	 * appointmentModel.getStore().getDisplayName());
	 *
	 * finalEmailBody = StringUtils.replace(finalEmailBody,
	 * "${appointmentStatus}",
	 * appointmentModel.getAppoinmentStatus().getCode());
	 *
	 * if (!PSAppointmentStatus.CANCELLED.equals(appointmentModel.
	 * getAppoinmentStatus()) &&
	 * !PSAppointmentStatus.COMPLETED.equals(appointmentModel.
	 * getAppoinmentStatus())) {
	 *
	 * final String emailControls = Config.getParameter("ps.email.controls");
	 *
	 * finalEmailBody = finalEmailBody + emailControls;
	 *
	 * final String resheduleUrl = Config.getParameter("website.ssl.https") +
	 * Config.getParameter("ps.cust.resheduleUrl");
	 *
	 * finalEmailBody = StringUtils.replace(finalEmailBody, "${resheduleUrl}",
	 * resheduleUrl + appointmentModel.getPk());
	 *
	 * final String cancelUrl = Config.getParameter("website.ssl.https") +
	 * Config.getParameter("ps.cust.cancelUrl");
	 *
	 * finalEmailBody = StringUtils.replace(finalEmailBody, "${cancelUrl}",
	 * cancelUrl + appointmentModel.getPk());
	 *
	 * }
	 *
	 * log.info("final Email Body=== " + finalEmailBody);
	 *
	 * htmlEmail.setHtmlMsg(finalEmailBody);
	 *
	 * htmlEmail.send();
	 *
	 * } catch (final Exception e) {
	 *
	 * log.info("Exception while sending Notification to customer " + e);
	 * e.printStackTrace(); } }
	 */
	@Override
	public void sendEmailToCustomer(final PSAppointmentModel appointmentModel) {
		log.info("------inside personal shopper sendEmailToCustomer---------------");

		try {

			final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();

			// final String path =
			// request.getRealPath("/psemailtemplates/20171005_v1.html");

			final String filepath = request.getRealPath("/psemailtemplates");
			String finalEmailBody = Files.toString(new File(filepath + "/psEmailTempalte.html"), Charsets.UTF_8);
			final String path = request.getScheme() + "://" + request.getServerName();
			// String finalEmailBody = Files.toString(new File(path),
			// Charsets.UTF_8);

			final HtmlEmail htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail();

			htmlEmail.addTo(appointmentModel.getCustomerEmail() == null ? Config.getParameter("ps.email.backup.emailid")
					: appointmentModel.getCustomerEmail());

			// looping business user

			final String businessUserEmailIds = Config.getParameter("ps.businessuser.emailids");

			final String businessUsersEmailIdsArr[] = businessUserEmailIds.split(",");

			for (int i = 0; i < businessUsersEmailIdsArr.length; i++) {

				htmlEmail.addBcc(businessUsersEmailIdsArr[i]);
			}

			htmlEmail.setSubject(Config.getParameter("ps.email.subject"));

			// finalEmailBody = Config.getParameter("ps.email.body");

			finalEmailBody = StringUtils.replace(finalEmailBody, "${customerName}", appointmentModel.getCustomerName());

			finalEmailBody = StringUtils.replace(finalEmailBody, "${service}",
					appointmentModel.getServiceType().getServiceName());

			finalEmailBody = StringUtils.replace(finalEmailBody, "${appointmentDate}",
					appointmentModel.getAppointmnetDate().toString());

			finalEmailBody = StringUtils.replace(finalEmailBody, "${store}",
					appointmentModel.getStore().getDisplayName());

			finalEmailBody = StringUtils.replace(finalEmailBody, "${appointmentStatus}",
					appointmentModel.getAppoinmentStatus().getCode());

			finalEmailBody = StringUtils.replace(finalEmailBody, "${imagePath}", path);

			if (!PSAppointmentStatus.CANCELLED.equals(appointmentModel.getAppoinmentStatus())
					&& !PSAppointmentStatus.COMPLETED.equals(appointmentModel.getAppoinmentStatus())) {

				// final String emailControls =
				// Config.getParameter("ps.email.controls");

				// finalEmailBody = finalEmailBody + emailControls;

				final String resheduleUrl = Config.getParameter("website.ssl.https")
						+ Config.getParameter("ps.cust.resheduleUrl");

				finalEmailBody = StringUtils.replace(finalEmailBody, "${resheduleUrl}",
						resheduleUrl + appointmentModel.getPk());

				final String cancelUrl = Config.getParameter("website.ssl.https")
						+ Config.getParameter("ps.cust.cancelUrl");

				finalEmailBody = StringUtils.replace(finalEmailBody, "${cancelUrl}",
						cancelUrl + appointmentModel.getPk());

			} else {
				// hiding Reschedule and Cancel Buttons in email for cancelled
				// and completed appointments
				finalEmailBody = finalEmailBody.replaceAll("display:contents", "display:none");
			}

			// log.info("final Email Body=== " + finalEmailBody);

			htmlEmail.setHtmlMsg(finalEmailBody);

			htmlEmail.send();

		} catch (final Exception e) {

			log.info("Exception while sending Notification to customer " + e);
			e.printStackTrace();
		}

	}

	@Override
	public List<Object> getAppointmentsCount() {

		// log.info("-------------PSAppointmentServiceImpl/getAppointmentsCount----------------------");

		return psAppointmentDao.getAppointmentsCount();
	}
}
