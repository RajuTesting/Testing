
package com.borngroup.ssl.core.cron;

import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.Config;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import com.borngroup.ssl.core.exceptions.EMSConnectionFailureException;
import com.borngroup.ssl.core.model.CustomerSocialAccountModel;
import com.borngroup.ssl.core.util.CommonHelper;
import com.borngroup.ssl.core.util.CustomerMdmUtils;
import com.borngroup.ssl.core.util.EMSConnectionManager;
import com.ssl.core.customer.mdm.entities.CustomerDTO;
import com.ssl.core.customer.mdm.entities.MDMEcomCustDTO;
import com.ssl.core.customer.mdm.entities.PaymentAddresses;
import com.ssl.core.customer.mdm.entities.ShipmentAddress;
import com.ssl.core.customer.mdm.entities.SizePreferences;
import com.ssl.core.customer.mdm.entities.SocialAccount;
import com.ssl.core.model.CustMdmBulkUploadModel;
import com.ssl.core.model.MDMEcomCustCronJobModel;

/**
 * <p>
 * MDMEcomCustCronjob.java : cron job for publishing customer data to mdm
 * <p>
 * Created By : raju.p@techouts.com
 *
 * @author Techouts
 */
public class MDMEcomCustCronjob extends AbstractJobPerformable<MDMEcomCustCronJobModel> {
	private static final Logger LOG = Logger
			.getLogger(MDMEcomCustCronjob.class);

	/**Log4 j Logger**/
	private static final Logger LOGGER = Logger
			.getLogger(MDMEcomCustCronjob.class);

	/**Common Helper**/
	private final CommonHelper commonHelper = CommonHelper.getInstance();

	/**Common Date format for publishing data to Queue**/
	private static final String COMMON_PUBLISH_DATE_FORMAT = "MM/dd/YYYY";

	private static final String YES = "YES";

	private static final String NO = "NO";
	
	private static final String FROM="} from {";

	/**Configuration Service**/
	@Resource(name = "configurationService")
	private ConfigurationService configurationService;

	/**CustomerModel Validator **/
	@Resource(name = "customerModelValidator")
	private Validator customerModelValidator;

	/**EMS Connection Manager **/
	@Resource(name = "emsConnectionManager")
	private EMSConnectionManager emsConnectionManager;

	@SuppressWarnings("boxing")
	@Override
	public PerformResult perform(final MDMEcomCustCronJobModel cronjob) {
		LOG.info(" ##########  MDMEcomCustCronjob started ################ ");
		Date cronJobStartTime = new Date();
		String fromDate = null;
		boolean isDateFromCronJob = false;
		int errorRecordsCount = 0;
		int publishSucceRecordsCount = 0;
		int publishFailedRecordsCount=0;
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (cronjob.getModifiedCustFetchDate() != null) {
			fromDate = dateFormat
					.format(cronjob.getModifiedCustFetchDate());
			isDateFromCronJob = true;
		}
		else{
			fromDate=dateFormat.format(cronJobStartTime);
			isDateFromCronJob=false;
		}
       
		/*******************************************
		 * Fetching Bulk or publish failed customers
		 *******************************************/
		List<CustMdmBulkUploadModel> custMdmBulkUploads = getPublishFailedCustomerDetails();
		Map<String,CustMdmBulkUploadModel> customerPkMap=new TreeMap<>();
		for (CustMdmBulkUploadModel model : custMdmBulkUploads) {
			customerPkMap.put(model.getCustomerPK(), model);
		}
		if (!MapUtils.isEmpty(customerPkMap)) {
			LOG.info(" Bulk upload customer records count "
					+ customerPkMap.size());
		}
		Set<String> customerPks=customerPkMap.keySet();
		LOG.info("Fetching modified customer details from date :" + fromDate);
		
		/******************************************
		 * Fetching customers based on modified time
		 ******************************************/
		final List<CustomerModel> customers = findModifiedCustomers(fromDate,
				0, isDateFromCronJob,customerPks);
		if (CollectionUtils.isEmpty(customers)) {
			LOGGER.info("No modified Customer  found ");
		LOG.info(" ##########  MDMEcomCustCronjob execution completed ################ ");
			return new PerformResult(CronJobResult.SUCCESS,
					CronJobStatus.FINISHED);
		}
		LOG.info("Modified and bulk upload customer records count ["
				+ customers.size()+"]");
		List<CustomerModel> publishFailedCustomers = new ArrayList<>();
		List<CustMdmBulkUploadModel> publishSucceCustomers=new ArrayList<>();
		for (final CustomerModel customer : customers) {
			final Errors errors = new BeanPropertyBindingResult(customer,"customer");
			/*********************
			 * Validating customer
			 *********************/
			customerModelValidator.validate(customer, errors);
			if (errors.hasErrors()) {
				LOGGER.info(String.format("Unable to process [%s] customer due to missing mandatory fields",customer.getPk().toString()));
				errorRecordsCount++;
				findPublishFailedCustomers(customerPks,customer,publishFailedCustomers);
			    printErrorLog(errors);
				continue;
			}

			MDMEcomCustDTO mdmEcomCustomer=null;
			try {
			CustomerDTO customerData = new CustomerDTO();
			customerData.setDob(customer.getDob()!=null?commonHelper.getFormattedDate(
					customer.getDob(), COMMON_PUBLISH_DATE_FORMAT):"");
			customerData.setGender(customer.getGender()!=null?"MALE"
					.equalsIgnoreCase(customer.getGender().getCode()) ? "M" : "FEMALE".equalsIgnoreCase(customer.getGender().getCode())?"F":"N":"N");
			customerData.setResidencePhone(customer.getLandlinenumber());
			customerData.setEmailId(customer.getContactEmail());
			customerData.setHasChildren(customer.getKids() != null
					&& customer.getKids().booleanValue() ? YES : NO);
			customerData.setMobile(getMobileNumber(customer));
			customerData
					.setCityTier(StringUtils.isEmpty(customer.getCityTier()) ? StringUtils.EMPTY
							: customer.getCityTier());
			customerData.setGstNumber(StringUtils.isEmpty(customer
					.getGstinNumber()) ? StringUtils.EMPTY : customer
					.getGstinNumber());
			customerData
					.setHomeStore(customer.getHomeStore() != null ? customer
							.getHomeStore().getCode() : StringUtils.EMPTY);
			customerData.setHomeStoreModifiedDate(customer
					.getHomeStoreModifiedDate() != null ? commonHelper
					.getFormattedDate(customer.getHomeStoreModifiedDate(),
							COMMON_PUBLISH_DATE_FORMAT) : StringUtils.EMPTY);
			customerData
					.setSocialPicUploaded(customer.getIsSocialPicUploaded() != null
							&& customer.getIsSocialPicUploaded().booleanValue() ? YES
							: NO);
			customerData
					.setIsSubscribed(customer.getIsSubscribed() != null ? customer
							.getIsSubscribed().toString() : StringUtils.EMPTY);
			customerData
					.setMaritalStatus(customer.getMarried() != null ? customer
							.getMarried().getCode() : StringUtils.EMPTY);
			customerData.setSecondaryMobileNumber(StringUtils.EMPTY);
			customerData.setPriceConscious(customer.getPriceConscious() != null
					&& customer.getPriceConscious().booleanValue() ? YES : NO);
			/************************************
			 * Populating customer social accounts
			 ************************************/
			populateCustomerSocialAccounts(customer, customerData);

			customerData.setStylePreference(customer.getStylePreference());
			customerData
					.setCustomerTitle(customer.getTitle() != null ? customer
							.getTitle().getCode() : StringUtils.EMPTY);
				customerData
						.setUserType(customer.getType() != null ? StringUtils
								.isNotEmpty(customer.getType().getCode()) ? "Guest"
								: "Registered"
								: "Registered");
			customerData
					.setCodVerificationDate(customer.getVerificationDate() != null ? commonHelper
							.getFormattedDate(customer.getVerificationDate(),
									COMMON_PUBLISH_DATE_FORMAT)
							: StringUtils.EMPTY);
			customerData.setVerifiedForCod(customer.getVerifiedForCOD() != null
					&& customer.getVerifiedForCOD().booleanValue() ? YES : NO);
			customerData.setWalletNumber(customer.getWalletNumber());
			customerData.setDisplayName(customer.getDisplayName());
			customerData.setName(customer.getName());
			customerData.setPk(customer.getPk().toString());
			customerData
					.setProfilePicture(customer.getProfilePicture() != null ? customer
							.getProfilePicture().getUrl() : StringUtils.EMPTY);
			customerData.setUid(customer.getUid());
			
			/********************************************
			 * Populating customer default payment address	
			}
			 ********************************************/
			if (customer.getDefaultPaymentAddress() != null && !isLoyaltyAddress(customer.getDefaultPaymentAddress())) {
				populateCustomerDefaultPaymentAddress(customer, customerData);
				populateCustomerPaymentAddress(customer, customerData);
			}
			/**************************************
			 * Populating customer shipping address
			 **************************************/
			populateCustomerShippingAddress(customer, customerData);		   
			mdmEcomCustomer = new MDMEcomCustDTO();
			mdmEcomCustomer.setCustomer(customerData);
			} catch (Exception exception) {
				LOG.error("Exception while populating customer data "
						+ exception);
			}
			if (mdmEcomCustomer == null) {
				findPublishFailedCustomers(customerPks, customer,
						publishFailedCustomers);
				errorRecordsCount++;
				continue;
			}
			if (!sendRequestToEMSQueue(createCustomerXmlRequest(mdmEcomCustomer))) {
				findPublishFailedCustomers(customerPks, customer,
						publishFailedCustomers);
				publishFailedRecordsCount++;
				if (LOG.isDebugEnabled()) {
					LOG.info("Failed to publish Customer ["
							+ customer.getPk().toString() + "] data to Queue");
				}
			} else {
				if (!MapUtils.isEmpty(customerPkMap)
						&& customerPkMap.containsKey(customer.getPk()
								.toString())) {
					publishSucceCustomers.add(customerPkMap.get(customer
							.getPk().toString()));
				}
				publishSucceRecordsCount++;
					LOG.info("Customer PK [" + customer.getPk().toString()
							+ "] data send to Queue sucessfully...");
			}
		}//for
		
		//Saving publish failed or mandatory fields missing customer information into BulkUpload table
		if (!CollectionUtils.isEmpty(publishFailedCustomers)) {
			savePublishFailedCustomers(publishFailedCustomers);
		}
		//Removing publish successful customer information from BulkUpload table
		if (!CollectionUtils.isEmpty(publishSucceCustomers)) {
			removePublishSucceCustomer(publishSucceCustomers);
		}
		LOG.info(String
				.format("Count of Successfully published records [%s],Publish failed records [%s],Mandatory fields missing records [%s] ",
						publishSucceRecordsCount,
						publishFailedRecordsCount, errorRecordsCount));
	
		//Updating Modified customers fetch date
		cronjob.setModifiedCustFetchDate(cronJobStartTime);
		modelService.save(cronjob);
		LOG.info(" ##########  MDMEcomCustCronjob execution completed ################ ");
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * Method for populating customer social accounts
	 * 
	 * @param customer
	 * @param customerData
	 */
	private void populateCustomerSocialAccounts(final CustomerModel customer,
			final CustomerDTO customerData) {
		final List<SocialAccount> socialAccounts = new ArrayList<>();
		for (final CustomerSocialAccountModel social : customer
				.getSocialAccounts()) {
			final SocialAccount socialAccount = new SocialAccount();
			socialAccount.setSocialAccountId(social.getPk().toString());
			socialAccount
					.setSocialAccountContactEmailid(StringUtils.isEmpty(social
							.getSocialAccountEmailId()) ? StringUtils.EMPTY
							: social.getSocialAccountEmailId());
			socialAccount.setSocialAccountsIdentifier(StringUtils
					.isEmpty(social.getSocialAccountUid()) ? StringUtils.EMPTY
					: social.getSocialAccountUid());
			socialAccounts.add(socialAccount);
		}
		if (!CollectionUtils.isEmpty(socialAccounts)) {
			customerData.setSocialAccount(socialAccounts);
		}
	}

	/**
	 * Method for populating customer shipping address
	 * 
	 * @param customer
	 * @param customerData
	 */
	private void populateCustomerShippingAddress(final CustomerModel customer,
			final CustomerDTO customerData) {
		final AddressModel defaultShipimentAddress = customer
				.getDefaultShipmentAddress();
		final List<ShipmentAddress> shipmentAddressList = new ArrayList<>();
		for (final AddressModel address : customer.getAddresses()) {
			if (isLoyaltyAddress(address)) {
				continue;
			}
			final ShipmentAddress shipmentAddress = new ShipmentAddress();
			shipmentAddress.setAddressId(address.getPk().toString());
			shipmentAddress.setFirstName(address.getFirstname());
			shipmentAddress.setLastName(address.getLastname());
			shipmentAddress.setPhone1(address.getPhone1());
			shipmentAddress.setStreetName(StringUtils.isEmpty(address
					.getStreetname()) ? StringUtils.EMPTY : address
					.getStreetname());
			shipmentAddress.setAddressLine1(address.getLine1());
			shipmentAddress.setAddressLine2(address.getLine2());
			shipmentAddress.setCity(address.getTown());
			shipmentAddress.setState(address.getRegion() != null ? address
					.getRegion().getIsocodeShort() : StringUtils.EMPTY);
			shipmentAddress.setPincode(address.getPostalcode());
			shipmentAddress.setRegion(address.getRegion() != null ? address
					.getRegion().getIsocodeShort() : StringUtils.EMPTY);
			if (defaultShipimentAddress != null
					&& defaultShipimentAddress.getPk() != null
					&& defaultShipimentAddress.getPk().equals(address.getPk())) {
				shipmentAddress.setDefaultShipmentAddress(YES);
			} else {
				shipmentAddress.setDefaultShipmentAddress(NO);
			}
			shipmentAddressList.add(shipmentAddress);
		}
		if (!CollectionUtils.isEmpty(shipmentAddressList)) {
			customerData.setShipmentAddresses(shipmentAddressList);
		}
	}

	/**
	 * Method for populating customer billing address
	 * 
	 * @param customer
	 * @param customerData
	 */
	private void populateCustomerPaymentAddress(final CustomerModel customer,
			final CustomerDTO customerData) {
		final AddressModel address = customer.getDefaultPaymentAddress();
			final PaymentAddresses add = new PaymentAddresses();
			add.setAddressId(address.getPk().toString());
			add.setFirstName(address.getFirstname());
			add.setLastName(address.getLastname());
			add.setPhone1(address.getPhone1());
			add.setStreetName(StringUtils.isEmpty(address.getStreetname()) ? StringUtils.EMPTY
					: address.getStreetname());
			add.setAddressLine1(address.getLine1());
			add.setAddressLine2(address.getLine2());
			add.setCity(address.getTown());
			add.setState(address.getRegion() != null ? address
					.getRegion().getIsocodeShort() : StringUtils.EMPTY);
			add.setPincode(address.getPostalcode());
			add.setRegion(address.getRegion() != null ? address
					.getRegion().getIsocodeShort() : StringUtils.EMPTY);
			add.setDefaultPaymentAddress(YES);
			final List<PaymentAddresses> paymentAddressList = new ArrayList<>();
			paymentAddressList.add(add);
			customerData.setPaymentAddresses(paymentAddressList);
	}

	/**
	 * Method for populating customer default default shipping address
	 * 
	 * @param customer
	 * @param customerData
	 */
	private void populateCustomerDefaultPaymentAddress(
			final CustomerModel customer, final CustomerDTO customerData) {
		final AddressModel defaultAddress = customer
				.getDefaultPaymentAddress();
		customerData.setAddressLine1(defaultAddress.getLine1());
		customerData.setAddressLine2(defaultAddress.getLine2());
		customerData.setAddressLine3(StringUtils.EMPTY);
		customerData.setCity(defaultAddress.getTown());
		customerData.setState(defaultAddress.getRegion()!=null?defaultAddress.getRegion().getIsocodeShort():StringUtils.EMPTY);
		customerData.setPincode(defaultAddress.getPostalcode());
		customerData
				.setCountry(defaultAddress.getCountry() != null ? "IN".equalsIgnoreCase(defaultAddress
						.getCountry().getIsocode())?"IND":StringUtils.EMPTY : StringUtils.EMPTY);
		customerData.setLandmark(StringUtils.EMPTY);
	}

	/**
	 * To find modified customer details
	 * 
	 * @param fromDate
	 * @param recordLimits
	 * @param isDateFromCronJob
	 * @param customerPks
	 * @return {@link List of CustomerModel}
	 */
	private List<CustomerModel> findModifiedCustomers(final String fromDate,
			final int recordLimits, boolean isDateFromCronJob,Set<String> customerPks) {
		try {
			Map<String, Object> params = null;
			final StringBuilder searchQuery = new StringBuilder();
			searchQuery.append("select {" + CustomerModel.PK + FROM
					+ CustomerModel._TYPECODE + "}");
			params = new HashMap<>();
			if (isDateFromCronJob) {
				searchQuery.append(" where {" + CustomerModel.MODIFIEDTIME
						+ "} > to_date(?modifiedTime,'yyyy-MM-dd HH24:mi:ss')");
			}
			else{
				searchQuery.append(" where {" + CustomerModel.MODIFIEDTIME
						+ "} <= to_date(?modifiedTime,'yyyy-MM-dd HH24:mi:ss')");
			}
			params.put("modifiedTime", fromDate);
			if (!CollectionUtils.isEmpty(customerPks)) {
				searchQuery.append(" OR {" + CustomerModel.PK + "} IN ({{select {" + CustMdmBulkUploadModel.CUSTOMERPK
						+ FROM + CustMdmBulkUploadModel._TYPECODE + "}}})");
			}
			return doSearch(searchQuery.toString(), params,
					CustomerModel.class, recordLimits);
		} catch (Exception exception) {
			LOG.error(exception);
		}
		return Collections.emptyList();
	}

	/**
	 * Method for saving queue publish failed customer records
	 * 
	 * @param customers
	 */
	private void savePublishFailedCustomers(final List<CustomerModel> customers) {
		List<CustMdmBulkUploadModel> bulkUploadList = new ArrayList<>();
		for (CustomerModel customer : customers) {
			CustMdmBulkUploadModel mdmCustBulkUpload = null;
			try {
				mdmCustBulkUpload = modelService
						.create(CustMdmBulkUploadModel.class);
				mdmCustBulkUpload.setCustomerEmailId(customer.getUid());
				mdmCustBulkUpload.setCustomerPK(customer.getPk().toString());
				mdmCustBulkUpload.setCustomerMobile(customer.getMobile());
			} catch (Exception exception) {
				LOG.info("Model Saving Exception " + exception);
			}
			bulkUploadList.add(mdmCustBulkUpload);
		}
		try {
			modelService.saveAll(bulkUploadList);
		} catch (Exception exception) {
			LOG.error(exception);
		}
	}

	/**
	 * Method for removing after queue publish successful
	 * 
	 * @param custMdmBulkUploads
	 */
	private void removePublishSucceCustomer(List<CustMdmBulkUploadModel> custMdmBulkUploads) {
		try {
			modelService.removeAll(custMdmBulkUploads);
		} catch (Exception exception) {
			LOG.error(exception);
		}
	}

	/**
	 * Method for finding queue publish failed customers
	 * 
	 * @return {@link List of CustMdmBulkUploadModel}
	 */
	private List<CustMdmBulkUploadModel> getPublishFailedCustomerDetails() {
		final StringBuilder searchQuery = new StringBuilder();
		searchQuery.append("select {" + CustMdmBulkUploadModel.PK
				+ FROM + CustMdmBulkUploadModel._TYPECODE + "}");
		return doSearch(searchQuery.toString(), null, CustMdmBulkUploadModel.class, 0);
	}

	private <T> List<T> doSearch(final String query,
			final Map<String, Object> params, final Class<T> resultClass,
			final int recordLimits) {

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
		if (params != null) {
			fQuery.addQueryParameters(params);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Flexible search " + fQuery.toString());
		}
		fQuery.setResultClassList(Collections.singletonList(resultClass));
		if (recordLimits > 0) {
			fQuery.setCount(recordLimits);
		}

		final SearchResult<T> searchResult = flexibleSearchService
				.search(fQuery);
		return searchResult.getResult();
	}
	
	
	/**
	 * Method for publishing request to queue
	 * 
	 * @param request
	 * @return publish success or fail
	 */
	private boolean sendRequestToEMSQueue(final String request) {
		Session session = null;
		MessageProducer producer = null;
		if (LOG.isInfoEnabled()) {
			LOG.info("Customer data request   :::" + request);
		}

		try {
			if (null != request) {
				session = emsConnectionManager.getSession();
				final Queue queue = emsConnectionManager.getQueue(Config
						.getParameter(CustomerMdmUtils.EMS_QUEUE));
				LOG.info("Queue Name  :::" + queue.getQueueName());
				producer = session.createProducer(queue);
				final TextMessage text = session.createTextMessage();
				text.setText(request);
				producer.send(text);
				return true;
			}
		} catch (EMSConnectionFailureException emConnectionFailureException) {
			LOG.error("EMS connection failure Exception :" + emConnectionFailureException);
		}
		catch (JMSException jmsException) {
			LOG.error("JMS Exception :" + jmsException);
		}
		catch (final Exception exception) {
			LOG.error("Exception while publishing customer data to MDM "
					+ ExceptionUtils.getFullStackTrace(exception));
		} finally {
			try {
				if (producer != null) {
					producer.close();
				}
				if (session != null) {
					session.close();
				}
			} catch (final JMSException e) {
				LOG.error("JMS exception "
						+ ExceptionUtils.getFullStackTrace(e));
			}
		}
		return false;
	}
	
	/**
	 * Method for creating XML string customer MDM request from DTO object
	 * 
	 * @param mdmEcomCustDto
	 * @return XML sting request
	 */
	private String createCustomerXmlRequest(final MDMEcomCustDTO mdmEcomCustDto) {
		try {
			final JAXBContext contextObj = JAXBContext
					.newInstance(MDMEcomCustDTO.class);
			final Marshaller marshallerObj = contextObj.createMarshaller();
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			final StringWriter writer = new StringWriter();
			marshallerObj.marshal(mdmEcomCustDto, writer);
			return writer.toString();
		} catch (final Exception exception) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Exception while parsing customer data into XML: ",
						exception);
			}
		}

		return null;
	}
	
	private void findPublishFailedCustomers(Set<String> customerPks,
			CustomerModel customer, List<CustomerModel> publishFailedCustomers) {
		if (CollectionUtils.isEmpty(customerPks) || !customerPks.contains(customer.getPk().toString())) {
			publishFailedCustomers.add(customer);
		}
	}

	/**
	 * To find customer mobile number
	 * @param customer
	 * @return customer mobile number
	 */
	private String getMobileNumber(CustomerModel customer) {
		if (StringUtils.isNotEmpty(customer.getMobile())) {
			return customer.getMobile();
		}
		if (customer.getDefaultShipmentAddress() != null
				&& StringUtils.isNotEmpty(customer.getDefaultShipmentAddress()
						.getCellphone())) {
			return customer.getDefaultShipmentAddress().getCellphone();
		}
		return customer.getDefaultPaymentAddress().getCellphone();
	}
	/**
	 * Method for checking Address is loyalty Address or not and region null check
	 * @param address
	 * @return loyalty address or not
	 */
	private boolean isLoyaltyAddress(AddressModel address) {

		if (address.getLoyaltyAddress() != null && address.getLoyaltyAddress().booleanValue()) {
			return true;
		}
		return address.getRegion() == null || StringUtils.isEmpty(address.getRegion().getIsocodeShort());

	}
	
	private void printErrorLog(Errors errors){
		for(ObjectError error:errors.getAllErrors()){
			LOG.info(error.getCode());
		}
	}
	
	@Override
	public boolean isAbortable() {
		return true;
	}

}