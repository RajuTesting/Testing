/**
 *
 */
package com.borngroup.ssl.core.services.customer.otp;

import de.hybris.platform.core.model.user.CustomerModel;

import com.borngroup.ssl.core.enums.MediumType;
import com.borngroup.ssl.core.enums.OTPChannel;
import com.borngroup.ssl.core.enums.OTPType;
import com.borngroup.ssl.core.exceptions.OTPException;
import com.borngroup.ssl.core.model.UserOTPConfigRulesModel;
import com.borngroup.ssl.core.model.UserOTPModel;
import com.borngroup.ssl.core.services.customer.otp.impl.SSLUserOTPServiceImpl.CodOtpVerificationResponseEnum;

/**
 * @author vinaygupta
 *
 */
public interface SSLUserOTPService {

    public void updateUserOTPData(final String email, final String mobile, final String otp, final OTPType otpType, OTPChannel otpChannel);

    public boolean verifyUserOTP(final String email, final String mobile, final OTPType otpType, final String otp, OTPChannel otpChannel)
            throws OTPException;

    /**
     * @param medium
     * @param otpType
     * @param otpChannel
     * @return
     */
    UserOTPConfigRulesModel getUserOTPConfigRulesData(final MediumType medium, final OTPType otpType, OTPChannel otpChannel);

    /**
     * @param email
     * @param mobile
     * @param otpType
     * @param otpChannel
     * @return
     */
    UserOTPModel getUserOTPData(final String email, final String mobile, final OTPType otpType, final OTPChannel otpChannel);

    /**
     * @param customer
     * @param otp
     * @param otpType
     * @param email
     */
    public void sendOTPEmail(CustomerModel customer, String otp, OTPType otpType, String email);

    void saveUserOTPModel(UserOTPModel userOTPModel);

    public UserOTPModel populateUserOTPModel(final String email, final String mobile, final String otp, final OTPType otpType,
            final OTPChannel otpChannel);

    public void updateUserMobileData(final String currentMobile, final String updatedMobile, final OTPType otpType,
            final OTPChannel otpChannel, final String email, final String otp);

    public CodOtpVerificationResponseEnum verifyCodUserOTP(final String email, final String mobile, final OTPType otpType, final String otp,
            final OTPChannel otpChannel, final MediumType mediumType) throws OTPException;

}
