/**
 *
 */
package com.borngroup.ssl.core.services.customer.otp.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.borngroup.ssl.core.enums.MediumType;
import com.borngroup.ssl.core.enums.OTPChannel;
import com.borngroup.ssl.core.enums.OTPType;
import com.borngroup.ssl.core.events.FccOTPEvent;
import com.borngroup.ssl.core.exceptions.OTPException;
import com.borngroup.ssl.core.model.UserOTPConfigRulesModel;
import com.borngroup.ssl.core.model.UserOTPModel;
import com.borngroup.ssl.core.services.customer.otp.SSLUserOTPService;

/**
 * @author vinaygupta
 *
 */
public class SSLUserOTPServiceImpl implements SSLUserOTPService {

    @Resource(name = "modelService")
    private ModelService modelService;
    @Resource(name = "userOTPDao")
    private GenericDao<UserOTPModel> userOTPDao;
    @Resource(name = "userOTPConfigRulesDao")
    private GenericDao<UserOTPConfigRulesModel> userOTPConfigRulesDao;
    @Resource(name = "eventService")
    private EventService eventService;
    @Resource(name = "baseSiteService")
    private BaseSiteService baseSiteService;
    @Resource(name = "commonI18NService")
    private CommonI18NService commonI18NService;
    @Resource(name = "baseStoreService")
    private BaseStoreService baseStoreService;

    private Long defaultExpiryTime;
    private Long defaultLockingPeriod;

    private static final String MAX_VERIFICATION_REACHED = "Maximum number of verification attempts reached. Please try after %s hrs";

    public enum CodOtpVerificationResponseEnum {
        OTP_SUCCESS, OTP_VERIFICATION_FAILURE, OTP_VALIDITY_FAILURE
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.customer.otp.SSLSUserOTPService#updateUserOTPData(java.lang.String, java.lang.String,
     * java.lang.String, com.borngroup.ssl.core.enums.OTPType, com.borngroup.ssl.core.enums.OTPChannel)
     */
    @Override
    public void updateUserOTPData(final String email, final String mobile, final String otp, final OTPType otpType,
            final OTPChannel otpChannel) {
        ServicesUtil.validateParameterNotNull(email, "Email Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(mobile, "Mobile Number Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(otpType, "OTP Type Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(otpChannel, "OTP Channel Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(otp, "OTP Cannot be null to verify OTP");

        UserOTPModel userOTPModel = getUserOTPData(email, mobile, otpType, otpChannel);
        if (null != userOTPModel) {
            userOTPModel.setOtp(otp);
        } else {
            userOTPModel = modelService.create(UserOTPModel.class);
            userOTPModel.setEmail(email);
            userOTPModel.setMobile(mobile);
            userOTPModel.setOtp(otp);
            userOTPModel.setOtpType(otpType);
            userOTPModel.setOtpChannel(otpChannel);
            userOTPModel.setNoOfResendAttempt(new Integer(1));
        }
        modelService.save(userOTPModel);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.customer.otp.SSLSUserOTPService#verifyUserOTP(java.lang.String, java.lang.String,
     * com.borngroup.ssl.core.enums.OTPType, java.lang.String, com.borngroup.ssl.core.enums.OTPChannel)
     */
    @Override
    public boolean verifyUserOTP(final String email, final String mobile, final OTPType otpType, final String otp,
            final OTPChannel otpChannel) throws OTPException {
        ServicesUtil.validateParameterNotNull(email, "Email Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(mobile, "Mobile Number Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(otpType, "OTP Type Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(otpChannel, "OTP Channel Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(otp, "OTP Cannot be null to verify OTP");

        final UserOTPModel userOTPModel = getUserOTPData(email, mobile, otpType, otpChannel);
        if (null != userOTPModel) {
            final String savedOtp = userOTPModel.getOtp();

            final UserOTPConfigRulesModel userOTPConfigRulesModel = getUserOTPConfigRulesData(MediumType.APP_ONLY, otpType, otpChannel);
            Long expiryTime = defaultExpiryTime;
            if (null != userOTPConfigRulesModel) {
                expiryTime = userOTPConfigRulesModel.getExpiryTime();
                checkOTPVerificationValidity(userOTPModel, userOTPConfigRulesModel);
            }

            if (StringUtils.isNotEmpty(savedOtp) && StringUtils.isNotEmpty(otp) && savedOtp.equals(otp) && null != expiryTime
                    && (new Date().getTime() - userOTPModel.getModifiedtime().getTime()) / 1000 <= expiryTime.longValue()) {
                modelService.remove(userOTPModel);
                return true;
            } else {
                userOTPModel.setLastVerifiedTime(new Date());
                userOTPModel.setNoOfVerificationAttempt(new Integer(userOTPModel.getNoOfVerificationAttempt().intValue() + 1));
                modelService.save(userOTPModel);
            }
        }
        return false;

    }

    private void checkOTPVerificationValidity(final UserOTPModel userOTPModel, final UserOTPConfigRulesModel userOTPConfigRulesModel)
            throws OTPException {
        final Integer maxVerificationAttemptsAllowed = userOTPConfigRulesModel.getMaxNoOfAttemptsToVerify();
        if (null != maxVerificationAttemptsAllowed && null != userOTPModel.getNoOfVerificationAttempt()
                && userOTPModel.getNoOfVerificationAttempt().intValue() >= maxVerificationAttemptsAllowed.intValue()) {
            final long lockingPeriodToVerify = (null != userOTPConfigRulesModel.getLockPeriodForVerifyLimit())
                    ? userOTPConfigRulesModel.getLockPeriodForVerifyLimit().longValue() : defaultLockingPeriod.longValue();
            if ((new Date().getTime() - userOTPModel.getLastVerifiedTime().getTime()) / 1000 <= lockingPeriodToVerify) {
                throw new OTPException(String.format(MAX_VERIFICATION_REACHED,
                        String.valueOf(userOTPConfigRulesModel.getLockPeriodForResend().doubleValue() / (60 * 60))));
            } else {
                userOTPModel.setNoOfVerificationAttempt(new Integer(0));
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.borngroup.ssl.core.services.customer.otp.SSLSUserOTPService#getUserOTPConfigRulesDataForAppSignUp(com.borngroup.ssl.core.enums.
     * MediumType, com.borngroup.ssl.core.enums.OTPType, com.borngroup.ssl.core.enums.OTPChannel)
     */
    @Override
    public UserOTPConfigRulesModel getUserOTPConfigRulesData(final MediumType medium, final OTPType otpType, final OTPChannel otpChannel) {
        final Map<String, Object> params = new HashMap<>();
        params.put(UserOTPConfigRulesModel.MEDIUM, medium);
        params.put(UserOTPConfigRulesModel.OTPTYPE, otpType);
        params.put(UserOTPConfigRulesModel.OTPCHANNEL, otpChannel);
        final List<UserOTPConfigRulesModel> recordEntries = this.userOTPConfigRulesDao.find(params);
        if (CollectionUtils.isNotEmpty(recordEntries)) {
            return recordEntries.get(0);
        } else {
            return null;
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.customer.otp.SSLSUserOTPService#getUserOTPData(java.lang.String, java.lang.String,
     * com.borngroup.ssl.core.enums.OTPType, com.borngroup.ssl.core.enums.OTPChannel)
     */
    @Override
    public UserOTPModel getUserOTPData(final String email, final String mobile, final OTPType otpType, final OTPChannel otpChannel) {
        final Map<String, Object> params = new HashMap<>();
        params.put(UserOTPModel.EMAIL, email);
        params.put(UserOTPModel.MOBILE, mobile);
        params.put(UserOTPModel.OTPTYPE, otpType);
        params.put(UserOTPModel.OTPCHANNEL, otpChannel);
        final List<UserOTPModel> recordEntries = this.userOTPDao.find(params);
        if (CollectionUtils.isNotEmpty(recordEntries)) {
            return recordEntries.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void sendOTPEmail(final CustomerModel customer, final String otp, final OTPType otpType, final String email) {
        validateParameterNotNullStandardMessage("customerModel", customer);
        customer.getSslLoyaltyDetail().setOneTimePassword(otp);
        customer.getSslLoyaltyDetail().setEmailIdForOtp(email);
        modelService.saveAll();
        eventService.publishEvent(initializeEvent(new FccOTPEvent(), customer));
    }

    /**
     * initializes an {@link AbstractCommerceUserEvent}
     *
     * @param event
     * @param customerModel
     * @return the event
     */
    protected AbstractCommerceUserEvent initializeEvent(final AbstractCommerceUserEvent event, final CustomerModel customerModel) {
        event.setBaseStore(baseStoreService.getCurrentBaseStore());
        event.setSite(baseSiteService.getCurrentBaseSite());
        event.setCustomer(customerModel);
        event.setLanguage(commonI18NService.getCurrentLanguage());
        event.setCurrency(commonI18NService.getCurrentCurrency());
        return event;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.customer.otp.SSLUserOTPService#saveUserOTPModel(com.borngroup.ssl.core.model.UserOTPModel)
     */
    @Override
    public void saveUserOTPModel(final UserOTPModel userOTPModel) {
        modelService.save(userOTPModel);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.customer.otp.SSLUserOTPService#populateUserOTPModel(java.lang.String, java.lang.String,
     * java.lang.String, com.borngroup.ssl.core.enums.OTPType, com.borngroup.ssl.core.enums.OTPChannel)
     */
    @Override
    public UserOTPModel populateUserOTPModel(final String email, final String mobile, final String otp, final OTPType otpType,
            final OTPChannel otpChannel) {
        final UserOTPModel userOTPModel = modelService.create(UserOTPModel.class);
        userOTPModel.setEmail(email);
        userOTPModel.setMobile(mobile);
        userOTPModel.setOtp(otp);
        userOTPModel.setOtpType(otpType);
        userOTPModel.setOtpChannel(otpChannel);
        userOTPModel.setNoOfResendAttempt(new Integer(1));
        userOTPModel.setNoOfVerificationAttempt(new Integer(0));
        return userOTPModel;
    }

    /**
     * @param defaultExpiryTime the defaultExpiryTime to set
     */
    public void setDefaultExpiryTime(final Long defaultExpiryTime) {
        this.defaultExpiryTime = defaultExpiryTime;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.customer.otp.SSLUserOTPService#updateUserMobileData(java.lang.String, java.lang.String,
     * com.borngroup.ssl.core.enums.OTPType, com.borngroup.ssl.core.enums.OTPChannel, java.lang.String)
     */
    @Override
    public void updateUserMobileData(final String currentMobile, final String updatedMobile, final OTPType otpType,
            final OTPChannel otpChannel, final String email, final String otp) {
        ServicesUtil.validateParameterNotNull(email, "Email Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(currentMobile, "Mobile Number Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(updatedMobile, "Mobile Number Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(otpType, "OTP Type Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(otpChannel, "OTP Channel Cannot be null to verify OTP");

        final UserOTPModel userCodOTPModel = getUserOTPData(email, updatedMobile, otpType, otpChannel);
        if (null != userCodOTPModel) {
            modelService.remove(userCodOTPModel);
        }

        UserOTPModel userOTPModel = getUserOTPData(email, currentMobile, otpType, otpChannel);
        if (null != userOTPModel) {
            userOTPModel.setMobile(updatedMobile);
            modelService.save(userOTPModel);

        } else {
            userOTPModel = modelService.create(UserOTPModel.class);
            userOTPModel.setEmail(email);
            userOTPModel.setMobile(updatedMobile);
            userOTPModel.setOtp(otp);
            userOTPModel.setOtpType(otpType);
            userOTPModel.setOtpChannel(otpChannel);
            userOTPModel.setNoOfResendAttempt(Integer.valueOf(1));
            modelService.save(userOTPModel);
        }

    }

    /**
     * @param defaultLockingPeriod the defaultLockingPeriod to set
     */
    public void setDefaultLockingPeriod(final Long defaultLockingPeriod) {
        this.defaultLockingPeriod = defaultLockingPeriod;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.customer.otp.SSLUserOTPService#verifyCodUserOTP(java.lang.String, java.lang.String,
     * com.borngroup.ssl.core.enums.OTPType, java.lang.String, com.borngroup.ssl.core.enums.OTPChannel,
     * com.borngroup.ssl.core.enums.MediumType)
     */
    @Override
    public CodOtpVerificationResponseEnum verifyCodUserOTP(final String email, final String mobile, final OTPType otpType, final String otp,
            final OTPChannel otpChannel, final MediumType mediumType) throws OTPException {
        ServicesUtil.validateParameterNotNull(email, "Email Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(mobile, "Mobile Number Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(otpType, "OTP Type Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(otpChannel, "OTP Channel Cannot be null to verify OTP");
        ServicesUtil.validateParameterNotNull(otp, "OTP Cannot be null to verify OTP");
        CodOtpVerificationResponseEnum result = null;
        final UserOTPModel userOTPModel = getUserOTPData(email, mobile, otpType, otpChannel);
        if (null != userOTPModel) {

            final String savedOtp = userOTPModel.getOtp();

            final UserOTPConfigRulesModel userOTPConfigRulesModel = getUserOTPConfigRulesData(mediumType, otpType, otpChannel);
            Long expiryTime = defaultExpiryTime;
            if (null != userOTPConfigRulesModel) {
                expiryTime = userOTPConfigRulesModel.getExpiryTime();
                checkOTPVerificationValidity(userOTPModel, userOTPConfigRulesModel);
            }

            if (StringUtils.isNotEmpty(savedOtp) && StringUtils.isNotEmpty(otp) && savedOtp.equals(otp) && null != expiryTime
                    && (new Date().getTime() - userOTPModel.getModifiedtime().getTime()) / 1000 <= expiryTime.longValue()) {
                modelService.remove(userOTPModel);
                result = CodOtpVerificationResponseEnum.OTP_SUCCESS;
            } else {
                userOTPModel.setLastVerifiedTime(new Date());
                modelService.save(userOTPModel);
                if (null != expiryTime
                        && (new Date().getTime() - userOTPModel.getModifiedtime().getTime()) / 1000 >= expiryTime.longValue()) {
                    result = CodOtpVerificationResponseEnum.OTP_VALIDITY_FAILURE;
                } else if (null != expiryTime
                        && (new Date().getTime() - userOTPModel.getModifiedtime().getTime()) / 1000 <= expiryTime.longValue()
                        && StringUtils.isNotEmpty(savedOtp) && StringUtils.isNotEmpty(otp) && !savedOtp.equals(otp)) {
                    result = CodOtpVerificationResponseEnum.OTP_VERIFICATION_FAILURE;
                }
            }

        } else {
            result = CodOtpVerificationResponseEnum.OTP_VERIFICATION_FAILURE;
        }
        return result;
    }
}
