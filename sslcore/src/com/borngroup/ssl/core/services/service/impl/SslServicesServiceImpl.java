package com.borngroup.ssl.core.services.service.impl;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.sslfacades.services.data.DeliverableData;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.enums.FrequentPromotionalEnum;
import com.borngroup.ssl.core.model.DeliveryPostalCodeModel;
import com.borngroup.ssl.core.model.MobileAppReleaseDetailsModel;
import com.borngroup.ssl.core.model.MobileVersionUpdateInfoModel;
import com.borngroup.ssl.core.model.SSLCheckoutMessagesModel;
import com.borngroup.ssl.core.model.news.NewsLetterSubscribeForCustomerModel;
import com.borngroup.ssl.core.services.dao.SslServiceDao;
import com.borngroup.ssl.core.services.service.SslServicesService;
import com.borngroup.ssl.facades.mobile.data.MobileAppReleaseData;
import com.ssl.paymentgateways.model.PaymentGatewayMappingModel;

/**
 * @author srikanth.ramaiah
 *
 */
public class SslServicesServiceImpl implements SslServicesService {
    protected static final Logger LOG = Logger.getLogger(SslServicesServiceImpl.class);

    private static final String SPLIT_PATTERN = "\\.";

    private UserService userService;
    private CustomerAccountService customerAccountService;
    private BaseStoreService baseStoreService;
    private CheckoutCustomerStrategy checkoutCustomerStrategy;

    private Converter<OrderModel, OrderData> orderConverter;

    private SslServiceDao sslServiceDao;

    private ModelService modelService;

    private TypeService typeService;

    private Converter<MobileAppReleaseDetailsModel, MobileAppReleaseData> mobileAppBuildConverter;

    /**
     * @return the mobileAppBuildConverter
     */
    protected Converter<MobileAppReleaseDetailsModel, MobileAppReleaseData> getMobileAppBuildConverter() {
        return mobileAppBuildConverter;
    }

    /**
     * @param mobileAppBuildConverter
     *        the mobileAppBuildConverter to set
     */
    @Required
    public void setMobileAppBuildConverter(final Converter<MobileAppReleaseDetailsModel, MobileAppReleaseData> mobileAppBuildConverter) {
        this.mobileAppBuildConverter = mobileAppBuildConverter;
    }

    /**
     * @return the userService
     */
    protected UserService getUserService() {
        return userService;
    }

    /**
     * @param userService
     *        the userService to set
     */
    @Required
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    /**
     * @return the customerAccountService
     */
    protected CustomerAccountService getCustomerAccountService() {
        return customerAccountService;
    }

    /**
     * @param customerAccountService
     *        the customerAccountService to set
     */
    @Required
    public void setCustomerAccountService(final CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    /**
     * @return the baseStoreService
     */
    protected BaseStoreService getBaseStoreService() {
        return baseStoreService;
    }

    /**
     * @param baseStoreService
     *        the baseStoreService to set
     */
    @Required
    public void setBaseStoreService(final BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }

    /**
     * @return the checkoutCustomerStrategy
     */
    protected CheckoutCustomerStrategy getCheckoutCustomerStrategy() {
        return checkoutCustomerStrategy;
    }

    /**
     * @param checkoutCustomerStrategy
     *        the checkoutCustomerStrategy to set
     */
    @Required
    public void setCheckoutCustomerStrategy(final CheckoutCustomerStrategy checkoutCustomerStrategy) {
        this.checkoutCustomerStrategy = checkoutCustomerStrategy;
    }

    /**
     * @return the orderConverter
     */
    protected Converter<OrderModel, OrderData> getOrderConverter() {
        return orderConverter;
    }

    /**
     * @param orderConverter
     *        the orderConverter to set
     */
    @Required
    public void setOrderConverter(final Converter<OrderModel, OrderData> orderConverter) {
        this.orderConverter = orderConverter;
    }

    /**
     * @return the sslServiceDao
     */
    protected SslServiceDao getSslServiceDao() {
        return sslServiceDao;
    }

    /**
     * @param sslServiceDao
     *        the sslServiceDao to set
     */

    @Required
    public void setSslServiceDao(final SslServiceDao sslServiceDao) {
        this.sslServiceDao = sslServiceDao;
    }

    /**
     * @return the typeService
     */
    protected TypeService getTypeService() {
        return typeService;
    }

    /**
     * @param typeService
     *        the typeService to set
     */
    @Required
    public void setTypeService(final TypeService typeService) {
        this.typeService = typeService;
    }

    @SuppressWarnings("boxing")
    @Override
    public String checkDeliverable(final Integer pincode) {
        final DeliverableData deliverableData = new DeliverableData();
        final DeliveryPostalCodeModel deliveryPostalCodeModel = sslServiceDao.checkPinCodeForDeliverable(pincode);
        if (deliveryPostalCodeModel != null) {
            deliverableData.setPinCodeAvailable(true);
            deliverableData.setCOD(deliveryPostalCodeModel.getCod());
        } else {
            deliverableData.setPinCodeAvailable(false);
            deliverableData.setCOD(false);
        }

        return getJsonForDeliverable(deliverableData);
    }

    public String getJsonForDeliverable(final DeliverableData deliverableData) {
        final ObjectMapper mapper = new ObjectMapper();
        final Writer w = new StringWriter();
        try {
            mapper.writeValue(w, deliverableData);
        } catch (final IOException e) {
            LOG.error("Failed to convert deliverable data to json response", e);
        }
        return w.toString();
    }

    @SuppressWarnings("boxing")
    @Override
    public String addNewsLetter(final String email, final List<String> serviceLinks) {
        final NewsLetterSubscribeForCustomerModel newsLetterSubscribeModel = getNewsLetterServicesForCustomer(email);

        String response = StringUtils.EMPTY;
        if (newsLetterSubscribeModel != null) {
            if (!newsLetterSubscribeModel.getServices().contains("All")) {
                newsLetterSubscribeModel.setSubscribe(true);

                newsLetterSubscribeModel.setServices(serviceLinks);
                getModelService().save(newsLetterSubscribeModel);
                response = "{status:Sucessfully Updated}";
            } else {
                response = "{status:already_subscribed}";
            }

        } else {
            final NewsLetterSubscribeForCustomerModel newnewsLetterSubscribeModel = new NewsLetterSubscribeForCustomerModel();
            newnewsLetterSubscribeModel.setEmail(email);
            newnewsLetterSubscribeModel.setSubscribe(true);
            newnewsLetterSubscribeModel.setServices(new ArrayList<String>(serviceLinks));
            getModelService().save(newnewsLetterSubscribeModel);
            response = "{status:Successfully Added}";
        }
        return response;
    }

    @Override
    public NewsLetterSubscribeForCustomerModel getNewsLetterServicesForCustomer(final String email) {
        final NewsLetterSubscribeForCustomerModel newsLetterSubscribeModel = sslServiceDao.checkForAllReadySubscribe(email);
        return newsLetterSubscribeModel;
    }

    @Override
    public String updateNewsLetter(final String email, final List<String> serviceLinks, final String frequencyCode) {
        final NewsLetterSubscribeForCustomerModel newsLetterSubscribeModel = sslServiceDao.checkForAllReadySubscribe(email);

        if (newsLetterSubscribeModel != null) {

            if (serviceLinks == null) {
                newsLetterSubscribeModel.setSubscribe(Boolean.FALSE);
                newsLetterSubscribeModel.setServices(null);
                newsLetterSubscribeModel.setFrequentPromotional(null);
            } else {
                newsLetterSubscribeModel.setSubscribe(Boolean.TRUE);
                newsLetterSubscribeModel.setServices(serviceLinks);
                newsLetterSubscribeModel.setFrequentPromotional(FrequentPromotionalEnum.valueOf(frequencyCode));
            }
            getModelService().save(newsLetterSubscribeModel);
            return "{status:Sucessfully Updated}";

        } else {

            final NewsLetterSubscribeForCustomerModel newnewsLetterSubscribeModel = new NewsLetterSubscribeForCustomerModel();
            newnewsLetterSubscribeModel.setEmail(email);
            newnewsLetterSubscribeModel.setSubscribe(Boolean.TRUE);
            newnewsLetterSubscribeModel.setFrequentPromotional(FrequentPromotionalEnum.valueOf(frequencyCode));
            newnewsLetterSubscribeModel.setServices(new ArrayList<String>(serviceLinks));
            getModelService().save(newnewsLetterSubscribeModel);
            return "{status:Successfully Added}";
        }

    }

    /**
     * @return the modelService
     */
    protected ModelService getModelService() {
        return modelService;
    }

    /**
     * @param modelService
     *        the modelService to set
     */
    @Required
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public OrderData getOrderByCode(final String email, final String orderCode) {

        final OrderModel orderModel = getSslServiceDao().getOrderDetailsForEmailAndId(email, orderCode);
        return getOrderConverter().convert(orderModel);
    }

    @SuppressWarnings("boxing")
    @Override
    public Boolean isExistingCustomer(final String email) {
        return getUserService().isUserExisting(email);
    }

    @SuppressWarnings("boxing")
    @Override
    public Boolean isOrderAvailable(final String email, final String orderCode) {
        final BaseStoreModel baseStoreModel = getBaseStoreService().getCurrentBaseStore();
        final CustomerModel customerModel = (CustomerModel) getUserService().getUserForUID(email);
        return getSslServiceDao().isOrderAvailableForCustomer(orderCode, customerModel, baseStoreModel);
    }

    @Override
    public OrderModel getOrderByEmailAndId(final String email, final String orderCode) {
        return getSslServiceDao().getOrderDetailsForEmailAndId(email, orderCode);
    }

    @Override
    public MobileAppReleaseData getLatestBuildDetails(final String mobileType) {
        final MobileAppReleaseDetailsModel appReleaseDetailsModel = getSslServiceDao().getLatestBuildDetails(mobileType);
        if (appReleaseDetailsModel != null) {
            return getMobileAppBuildConverter().convert(appReleaseDetailsModel);
        } else {
            return null;
        }

    }

    @Override
    public List<SSLCheckoutMessagesModel> getAllCheckoutMessages() {
        return sslServiceDao.getAllCheckoutMessages();
    }

    @Override
    public List<SSLCheckoutMessagesModel> getMessage(final String code) {
        return sslServiceDao.getMessage(code);
    }

	@Override
	public Map<String, String> getProductDetailLinkMessages(final List<String> codes) {
		final List<SSLCheckoutMessagesModel> messages = this.sslServiceDao.getProductDetailLinkMessages(codes);
		final Map<String, String> messageMap = new HashMap<String, String>();
		if (CollectionUtils.isNotEmpty(messages)) {
			for (final SSLCheckoutMessagesModel message : messages) {
				messageMap.put(message.getMessageCode(), message.getMessageValue());
			}
			return messageMap;
		}
		return null;
	}

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.service.SslServicesService#getLatestBuildDetailsMobile(java.lang.String, java.lang.String,
     * java.lang.Double)
     */
    @Override
    public MobileAppReleaseData getLatestBuildDetailsMobile(final String mobileType, final String serviceType, final String versionNumber) {
        MobileAppReleaseData mobileAppReleaseData = null;

        LOG.info("Calling dao layer to fetch latest build details !!");
        final MobileAppReleaseDetailsModel appReleaseDetailsModel = getSslServiceDao().getLatestBuildDetailsMobile(mobileType, serviceType);
        if (appReleaseDetailsModel != null) {
            LOG.info("Converting MobileAppReleaseDetailsModel data to MobileAppReleaseData DTO");
            mobileAppReleaseData = getMobileAppBuildConverter().convert(appReleaseDetailsModel);
            mobileAppReleaseData.setForceUpdate(Boolean.FALSE);
            mobileAppReleaseData.setSoftUpdate(Boolean.FALSE);

            final MobileVersionUpdateInfoModel versionUpdateInfoModel = getSslServiceDao().getVersionUpdateInfoModel(mobileType);
            if (versionUpdateInfoModel != null && (StringUtils.isNotEmpty(versionUpdateInfoModel.getForceUpdateVersion())
                    || StringUtils.isNotEmpty(versionUpdateInfoModel.getSoftUpdateVersion()))) {
                if (StringUtils.isNotEmpty(versionNumber)) {
                    if (isValidVersionNumber(versionNumber)) {
                        updateVersionInfo(mobileAppReleaseData, versionNumber, versionUpdateInfoModel.getForceUpdateVersion(),
                                versionUpdateInfoModel.getSoftUpdateVersion());
                    } else {
                        LOG.error("Version number provided in the request URL '" + versionNumber
                                + "' is not valid. Version number should contain at least two numeric values in following format: [ '1.12' or '1.8.4' or '0.8.1.2' ]");
                    }

                } else {
                    LOG.info("Version number in the request URL is empty. Therefore setting force update flag to true ");
                    mobileAppReleaseData.setForceUpdate(Boolean.TRUE);
                }
            } else {
                LOG.warn("No record found in database for force and soft version update for given mobile type '" + mobileType + "'");
            }
        } else {
            LOG.warn("No record found in database for build detail information for given mobile type '" + mobileType
                    + "' and service type '" + serviceType + "'");
        }
        return mobileAppReleaseData;
    }

    /**
     * Method to update force and soft update version flag in MobileAppReleaseData DTO based on pre-configured values
     *
     * @param mobileAppReleaseData
     * @param versionNumber
     * @param forceUpdateVersion
     * @param softUpdateVersion
     */
    private void updateVersionInfo(final MobileAppReleaseData mobileAppReleaseData, final String versionNumber,
            final String forceUpdateVersion, final String softUpdateVersion) {

        LOG.info("Inside updateVersionInfo() method - Comparing given version number with pre-configured force and soft update version");
        final boolean isValidForceUpdateVersion = isValidVersionNumber(forceUpdateVersion);

        final String[] versionNumberDigits = versionNumber.split(SPLIT_PATTERN);
        if (isValidForceUpdateVersion) {
            LOG.debug("Comparing given version number with force update version");
            final String[] forceUpdateVersionDigits = forceUpdateVersion.split(SPLIT_PATTERN);
            final int maxNumberOfIteration = Math.min(versionNumberDigits.length, forceUpdateVersionDigits.length);
            boolean isEqualVersion = true;
            for (int i = 0; i < maxNumberOfIteration; i++) {
                if (Integer.parseInt(versionNumberDigits[i]) < Integer.parseInt(forceUpdateVersionDigits[i])) {
                    LOG.debug("Setting force update flag to true");
                    mobileAppReleaseData.setForceUpdate(Boolean.TRUE);
                    isEqualVersion = false;
                    break;
                } else if (Integer.parseInt(versionNumberDigits[i]) > Integer.parseInt(forceUpdateVersionDigits[i])) {
                    mobileAppReleaseData.setForceUpdate(Boolean.FALSE);
                    isEqualVersion = false;
                    break;
                }
            }
            if (isEqualVersion == true) {
                if (versionNumberDigits.length <= forceUpdateVersionDigits.length) {
                    LOG.debug("Setting force update flag to true");
                    mobileAppReleaseData.setForceUpdate(Boolean.TRUE);
                } else {
                    mobileAppReleaseData.setForceUpdate(Boolean.FALSE);
                }
            }
        } else {
            LOG.warn(
                    "Force update version number is either empty or not a valid version number, Version number should contain at least two numeric values in following format: [ '1.12.0' or '1.8' or '0.8.1.2' ]");
        }

        if (mobileAppReleaseData.getForceUpdate().booleanValue() == false) {
            final boolean isValidSoftUpdateVersion = isValidVersionNumber(softUpdateVersion);
            LOG.debug("Comparing given version number with soft update version");
            if (isValidSoftUpdateVersion) {
                final String[] softUpdateVersionDigits = softUpdateVersion.split(SPLIT_PATTERN);
                final int maximumNumberOfIteration = Math.min(versionNumberDigits.length, softUpdateVersionDigits.length);
                boolean isEqualVersion = true;
                for (int i = 0; i < maximumNumberOfIteration; i++) {
                    if (Integer.parseInt(versionNumberDigits[i]) < Integer.parseInt(softUpdateVersionDigits[i])) {
                        LOG.debug("Setting soft update flag to true");
                        mobileAppReleaseData.setSoftUpdate(Boolean.TRUE);
                        isEqualVersion = false;
                        break;
                    } else if (Integer.parseInt(versionNumberDigits[i]) > Integer.parseInt(softUpdateVersionDigits[i])) {
                        mobileAppReleaseData.setSoftUpdate(Boolean.FALSE);
                        isEqualVersion = false;
                        break;
                    }
                }
                if (isEqualVersion == true) {
                    if (versionNumberDigits.length <= softUpdateVersionDigits.length) {
                        LOG.debug("Setting soft update flag to true");
                        mobileAppReleaseData.setSoftUpdate(Boolean.TRUE);
                    } else {
                        mobileAppReleaseData.setSoftUpdate(Boolean.FALSE);
                    }
                }
            } else {
                LOG.warn(
                        "Soft update version number is either empty or not a valid version number, Version number should contain at least two numeric values in following format : [ '1.12.0' or '1.8' or '0.8.1.2' ]");
            }
        }
    }

    /**
     * Method to validate version number format. Version number should contain only numeric values like [ 1.2.0 or 1.8 or 0.8.1.2 ]
     *
     * @param versionNumber
     * @return boolean status
     */
    private boolean isValidVersionNumber(final String versionNumber) {

        boolean isValidVersion = false;
        if (StringUtils.isNotEmpty(versionNumber)) {
            // Regex to validate version number.
            // Version number should contain only numeric values like [ 1.2.0 or 1.14 or 0.8.11.2 ] and minimum number of digits should be
            // two
            // seperated by dot(.)
            isValidVersion = versionNumber.matches("\\d+(\\.)((\\d+\\.))*\\d+");
        }
        return isValidVersion;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.service.SslServicesService#getLatestBuildDetailsMobile(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public MobileAppReleaseData getLatestBuildDetailsMobile(final String mobileType, final String serviceType, final String versionNumber,
            final String currentOsVersion) {
        MobileAppReleaseData mobileAppReleaseData = null;
        LOG.info("Calling dao layer to fetch latest build details for V2_1 services!!");
        final MobileAppReleaseDetailsModel appReleaseDetailsModel = getSslServiceDao().getLatestBuildDetailsMobile(mobileType, serviceType);
        if (appReleaseDetailsModel != null) {
            LOG.info("Converting MobileAppReleaseDetailsModel data to MobileAppReleaseData DTO");
            mobileAppReleaseData = getMobileAppBuildConverter().convert(appReleaseDetailsModel);
            mobileAppReleaseData.setForceUpdate(Boolean.FALSE);
            mobileAppReleaseData.setSoftUpdate(Boolean.FALSE);
            final MobileVersionUpdateInfoModel versionUpdateInfoModel = getSslServiceDao().getVersionUpdateInfoModel(mobileType);
            if (versionUpdateInfoModel != null && (StringUtils.isNotEmpty(versionUpdateInfoModel.getForceUpdateVersion())
                    || StringUtils.isNotEmpty(versionUpdateInfoModel.getSoftUpdateVersion()))) {
                if (StringUtils.isNotEmpty(versionNumber)) {
                    if (isValidVersionNumber(versionNumber)) {
                        if (isupgradeIsForOs(currentOsVersion, versionUpdateInfoModel.getOsLowerVersion(),
                                versionUpdateInfoModel.getOsUpperVersion())) {
                            updateVersionInfo(mobileAppReleaseData, versionNumber, versionUpdateInfoModel.getForceUpdateVersion(),
                                    versionUpdateInfoModel.getSoftUpdateVersion());
                        }
                    } else {
                        LOG.error("Version number provided in the request URL '" + versionNumber
                                + "' is not valid. Version number should contain at least two numeric values in following format: [ '1.12' or '1.8.4' or '0.8.1.2' ]");
                    }

                } else {
                    LOG.info("Version number in the request URL is empty. Therefore setting force update flag to true ");
                    mobileAppReleaseData.setForceUpdate(Boolean.TRUE);
                }
            } else {
                LOG.warn("No record found in database for force and soft version update for given mobile type '" + mobileType + "'");
            }
        } else {
            LOG.warn("No record found in database for build detail information for given mobile type '" + mobileType
                    + "' and service type '" + serviceType + "'");
        }
        return mobileAppReleaseData;
    }

    /**
     * @param currentOsVersion
     * @param osLowerVersion
     * @param osUpperVersion
     * @return
     */
    private boolean isupgradeIsForOs(final String currentOsVersion, final String osLowerVersion, final String osUpperVersion) {
        if (StringUtils.isEmpty(osLowerVersion) && StringUtils.isEmpty(osUpperVersion)) {
            return true;
        } else if (StringUtils.isNotEmpty(osLowerVersion) && StringUtils.isNotEmpty(osUpperVersion)) {
            if (compare(currentOsVersion, osLowerVersion) >= 0 && compare(currentOsVersion, osUpperVersion) <= 0) {
                return true;
            }
        } else {
            if (StringUtils.isNotEmpty(osLowerVersion)) {
                if (compare(currentOsVersion, osLowerVersion) > 0) {
                    return true;
                }
            } else {
                if (compare(currentOsVersion, osUpperVersion) < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int compare(final String osversion, final String storedOsVersion) {
        final String[] versionNumberDigits = osversion.split(SPLIT_PATTERN);
        final String[] storedOsVersionNumberDigits = storedOsVersion.split(SPLIT_PATTERN);
        final int maxNumberOfIteration = Math.min(versionNumberDigits.length, storedOsVersionNumberDigits.length);
        int returnValue = 0;
        for (int i = 0; i < maxNumberOfIteration; i++) {
            if (Integer.parseInt(versionNumberDigits[i]) < Integer.parseInt(storedOsVersionNumberDigits[i])) {
                returnValue = -1;
                break;
            } else if (Integer.parseInt(versionNumberDigits[i]) > Integer.parseInt(storedOsVersionNumberDigits[i])) {
                returnValue = 1;
                break;
            }
        }
        return returnValue;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<PaymentGatewayMappingModel> getAllCheckoutSubtitles() {
    	return sslServiceDao.getAllCheckoutSubtitles();
    }
}
