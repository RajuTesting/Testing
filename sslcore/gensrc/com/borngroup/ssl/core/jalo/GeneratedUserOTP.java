/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem UserOTP}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedUserOTP extends GenericItem
{
	/** Qualifier of the <code>UserOTP.email</code> attribute **/
	public static final String EMAIL = "email";
	/** Qualifier of the <code>UserOTP.mobile</code> attribute **/
	public static final String MOBILE = "mobile";
	/** Qualifier of the <code>UserOTP.otp</code> attribute **/
	public static final String OTP = "otp";
	/** Qualifier of the <code>UserOTP.otpType</code> attribute **/
	public static final String OTPTYPE = "otpType";
	/** Qualifier of the <code>UserOTP.otpChannel</code> attribute **/
	public static final String OTPCHANNEL = "otpChannel";
	/** Qualifier of the <code>UserOTP.noOfVerificationAttempt</code> attribute **/
	public static final String NOOFVERIFICATIONATTEMPT = "noOfVerificationAttempt";
	/** Qualifier of the <code>UserOTP.noOfResendAttempt</code> attribute **/
	public static final String NOOFRESENDATTEMPT = "noOfResendAttempt";
	/** Qualifier of the <code>UserOTP.lastVerifiedTime</code> attribute **/
	public static final String LASTVERIFIEDTIME = "lastVerifiedTime";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(EMAIL, AttributeMode.INITIAL);
		tmp.put(MOBILE, AttributeMode.INITIAL);
		tmp.put(OTP, AttributeMode.INITIAL);
		tmp.put(OTPTYPE, AttributeMode.INITIAL);
		tmp.put(OTPCHANNEL, AttributeMode.INITIAL);
		tmp.put(NOOFVERIFICATIONATTEMPT, AttributeMode.INITIAL);
		tmp.put(NOOFRESENDATTEMPT, AttributeMode.INITIAL);
		tmp.put(LASTVERIFIEDTIME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.email</code> attribute.
	 * @return the email
	 */
	public String getEmail(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.email</code> attribute.
	 * @return the email
	 */
	public String getEmail()
	{
		return getEmail( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.email</code> attribute. 
	 * @param value the email
	 */
	public void setEmail(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.email</code> attribute. 
	 * @param value the email
	 */
	public void setEmail(final String value)
	{
		setEmail( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.lastVerifiedTime</code> attribute.
	 * @return the lastVerifiedTime
	 */
	public Date getLastVerifiedTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, LASTVERIFIEDTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.lastVerifiedTime</code> attribute.
	 * @return the lastVerifiedTime
	 */
	public Date getLastVerifiedTime()
	{
		return getLastVerifiedTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.lastVerifiedTime</code> attribute. 
	 * @param value the lastVerifiedTime
	 */
	public void setLastVerifiedTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, LASTVERIFIEDTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.lastVerifiedTime</code> attribute. 
	 * @param value the lastVerifiedTime
	 */
	public void setLastVerifiedTime(final Date value)
	{
		setLastVerifiedTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.mobile</code> attribute.
	 * @return the mobile
	 */
	public String getMobile(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MOBILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.mobile</code> attribute.
	 * @return the mobile
	 */
	public String getMobile()
	{
		return getMobile( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.mobile</code> attribute. 
	 * @param value the mobile
	 */
	public void setMobile(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MOBILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.mobile</code> attribute. 
	 * @param value the mobile
	 */
	public void setMobile(final String value)
	{
		setMobile( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.noOfResendAttempt</code> attribute.
	 * @return the noOfResendAttempt
	 */
	public Integer getNoOfResendAttempt(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, NOOFRESENDATTEMPT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.noOfResendAttempt</code> attribute.
	 * @return the noOfResendAttempt
	 */
	public Integer getNoOfResendAttempt()
	{
		return getNoOfResendAttempt( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.noOfResendAttempt</code> attribute. 
	 * @return the noOfResendAttempt
	 */
	public int getNoOfResendAttemptAsPrimitive(final SessionContext ctx)
	{
		Integer value = getNoOfResendAttempt( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.noOfResendAttempt</code> attribute. 
	 * @return the noOfResendAttempt
	 */
	public int getNoOfResendAttemptAsPrimitive()
	{
		return getNoOfResendAttemptAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.noOfResendAttempt</code> attribute. 
	 * @param value the noOfResendAttempt
	 */
	public void setNoOfResendAttempt(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, NOOFRESENDATTEMPT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.noOfResendAttempt</code> attribute. 
	 * @param value the noOfResendAttempt
	 */
	public void setNoOfResendAttempt(final Integer value)
	{
		setNoOfResendAttempt( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.noOfResendAttempt</code> attribute. 
	 * @param value the noOfResendAttempt
	 */
	public void setNoOfResendAttempt(final SessionContext ctx, final int value)
	{
		setNoOfResendAttempt( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.noOfResendAttempt</code> attribute. 
	 * @param value the noOfResendAttempt
	 */
	public void setNoOfResendAttempt(final int value)
	{
		setNoOfResendAttempt( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.noOfVerificationAttempt</code> attribute.
	 * @return the noOfVerificationAttempt
	 */
	public Integer getNoOfVerificationAttempt(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, NOOFVERIFICATIONATTEMPT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.noOfVerificationAttempt</code> attribute.
	 * @return the noOfVerificationAttempt
	 */
	public Integer getNoOfVerificationAttempt()
	{
		return getNoOfVerificationAttempt( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.noOfVerificationAttempt</code> attribute. 
	 * @return the noOfVerificationAttempt
	 */
	public int getNoOfVerificationAttemptAsPrimitive(final SessionContext ctx)
	{
		Integer value = getNoOfVerificationAttempt( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.noOfVerificationAttempt</code> attribute. 
	 * @return the noOfVerificationAttempt
	 */
	public int getNoOfVerificationAttemptAsPrimitive()
	{
		return getNoOfVerificationAttemptAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.noOfVerificationAttempt</code> attribute. 
	 * @param value the noOfVerificationAttempt
	 */
	public void setNoOfVerificationAttempt(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, NOOFVERIFICATIONATTEMPT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.noOfVerificationAttempt</code> attribute. 
	 * @param value the noOfVerificationAttempt
	 */
	public void setNoOfVerificationAttempt(final Integer value)
	{
		setNoOfVerificationAttempt( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.noOfVerificationAttempt</code> attribute. 
	 * @param value the noOfVerificationAttempt
	 */
	public void setNoOfVerificationAttempt(final SessionContext ctx, final int value)
	{
		setNoOfVerificationAttempt( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.noOfVerificationAttempt</code> attribute. 
	 * @param value the noOfVerificationAttempt
	 */
	public void setNoOfVerificationAttempt(final int value)
	{
		setNoOfVerificationAttempt( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.otp</code> attribute.
	 * @return the otp
	 */
	public String getOtp(final SessionContext ctx)
	{
		return (String)getProperty( ctx, OTP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.otp</code> attribute.
	 * @return the otp
	 */
	public String getOtp()
	{
		return getOtp( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.otp</code> attribute. 
	 * @param value the otp
	 */
	public void setOtp(final SessionContext ctx, final String value)
	{
		setProperty(ctx, OTP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.otp</code> attribute. 
	 * @param value the otp
	 */
	public void setOtp(final String value)
	{
		setOtp( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.otpChannel</code> attribute.
	 * @return the otpChannel
	 */
	public EnumerationValue getOtpChannel(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, OTPCHANNEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.otpChannel</code> attribute.
	 * @return the otpChannel
	 */
	public EnumerationValue getOtpChannel()
	{
		return getOtpChannel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.otpChannel</code> attribute. 
	 * @param value the otpChannel
	 */
	public void setOtpChannel(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, OTPCHANNEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.otpChannel</code> attribute. 
	 * @param value the otpChannel
	 */
	public void setOtpChannel(final EnumerationValue value)
	{
		setOtpChannel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.otpType</code> attribute.
	 * @return the otpType
	 */
	public EnumerationValue getOtpType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, OTPTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTP.otpType</code> attribute.
	 * @return the otpType
	 */
	public EnumerationValue getOtpType()
	{
		return getOtpType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.otpType</code> attribute. 
	 * @param value the otpType
	 */
	public void setOtpType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, OTPTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTP.otpType</code> attribute. 
	 * @param value the otpType
	 */
	public void setOtpType(final EnumerationValue value)
	{
		setOtpType( getSession().getSessionContext(), value );
	}
	
}
