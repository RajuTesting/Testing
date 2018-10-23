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
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem UserOTPConfigRules}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedUserOTPConfigRules extends GenericItem
{
	/** Qualifier of the <code>UserOTPConfigRules.expiryTime</code> attribute **/
	public static final String EXPIRYTIME = "expiryTime";
	/** Qualifier of the <code>UserOTPConfigRules.maxNoOfAttemptsToVerify</code> attribute **/
	public static final String MAXNOOFATTEMPTSTOVERIFY = "maxNoOfAttemptsToVerify";
	/** Qualifier of the <code>UserOTPConfigRules.lockPeriodForVerifyLimit</code> attribute **/
	public static final String LOCKPERIODFORVERIFYLIMIT = "lockPeriodForVerifyLimit";
	/** Qualifier of the <code>UserOTPConfigRules.maxNoOfAttemptsToResend</code> attribute **/
	public static final String MAXNOOFATTEMPTSTORESEND = "maxNoOfAttemptsToResend";
	/** Qualifier of the <code>UserOTPConfigRules.lockPeriodForResend</code> attribute **/
	public static final String LOCKPERIODFORRESEND = "lockPeriodForResend";
	/** Qualifier of the <code>UserOTPConfigRules.otpType</code> attribute **/
	public static final String OTPTYPE = "otpType";
	/** Qualifier of the <code>UserOTPConfigRules.medium</code> attribute **/
	public static final String MEDIUM = "medium";
	/** Qualifier of the <code>UserOTPConfigRules.otpChannel</code> attribute **/
	public static final String OTPCHANNEL = "otpChannel";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(EXPIRYTIME, AttributeMode.INITIAL);
		tmp.put(MAXNOOFATTEMPTSTOVERIFY, AttributeMode.INITIAL);
		tmp.put(LOCKPERIODFORVERIFYLIMIT, AttributeMode.INITIAL);
		tmp.put(MAXNOOFATTEMPTSTORESEND, AttributeMode.INITIAL);
		tmp.put(LOCKPERIODFORRESEND, AttributeMode.INITIAL);
		tmp.put(OTPTYPE, AttributeMode.INITIAL);
		tmp.put(MEDIUM, AttributeMode.INITIAL);
		tmp.put(OTPCHANNEL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.expiryTime</code> attribute.
	 * @return the expiryTime
	 */
	public Long getExpiryTime(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, EXPIRYTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.expiryTime</code> attribute.
	 * @return the expiryTime
	 */
	public Long getExpiryTime()
	{
		return getExpiryTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.expiryTime</code> attribute. 
	 * @return the expiryTime
	 */
	public long getExpiryTimeAsPrimitive(final SessionContext ctx)
	{
		Long value = getExpiryTime( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.expiryTime</code> attribute. 
	 * @return the expiryTime
	 */
	public long getExpiryTimeAsPrimitive()
	{
		return getExpiryTimeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.expiryTime</code> attribute. 
	 * @param value the expiryTime
	 */
	public void setExpiryTime(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, EXPIRYTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.expiryTime</code> attribute. 
	 * @param value the expiryTime
	 */
	public void setExpiryTime(final Long value)
	{
		setExpiryTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.expiryTime</code> attribute. 
	 * @param value the expiryTime
	 */
	public void setExpiryTime(final SessionContext ctx, final long value)
	{
		setExpiryTime( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.expiryTime</code> attribute. 
	 * @param value the expiryTime
	 */
	public void setExpiryTime(final long value)
	{
		setExpiryTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.lockPeriodForResend</code> attribute.
	 * @return the lockPeriodForResend
	 */
	public Integer getLockPeriodForResend(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, LOCKPERIODFORRESEND);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.lockPeriodForResend</code> attribute.
	 * @return the lockPeriodForResend
	 */
	public Integer getLockPeriodForResend()
	{
		return getLockPeriodForResend( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.lockPeriodForResend</code> attribute. 
	 * @return the lockPeriodForResend
	 */
	public int getLockPeriodForResendAsPrimitive(final SessionContext ctx)
	{
		Integer value = getLockPeriodForResend( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.lockPeriodForResend</code> attribute. 
	 * @return the lockPeriodForResend
	 */
	public int getLockPeriodForResendAsPrimitive()
	{
		return getLockPeriodForResendAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.lockPeriodForResend</code> attribute. 
	 * @param value the lockPeriodForResend
	 */
	public void setLockPeriodForResend(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, LOCKPERIODFORRESEND,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.lockPeriodForResend</code> attribute. 
	 * @param value the lockPeriodForResend
	 */
	public void setLockPeriodForResend(final Integer value)
	{
		setLockPeriodForResend( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.lockPeriodForResend</code> attribute. 
	 * @param value the lockPeriodForResend
	 */
	public void setLockPeriodForResend(final SessionContext ctx, final int value)
	{
		setLockPeriodForResend( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.lockPeriodForResend</code> attribute. 
	 * @param value the lockPeriodForResend
	 */
	public void setLockPeriodForResend(final int value)
	{
		setLockPeriodForResend( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.lockPeriodForVerifyLimit</code> attribute.
	 * @return the lockPeriodForVerifyLimit
	 */
	public Integer getLockPeriodForVerifyLimit(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, LOCKPERIODFORVERIFYLIMIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.lockPeriodForVerifyLimit</code> attribute.
	 * @return the lockPeriodForVerifyLimit
	 */
	public Integer getLockPeriodForVerifyLimit()
	{
		return getLockPeriodForVerifyLimit( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.lockPeriodForVerifyLimit</code> attribute. 
	 * @return the lockPeriodForVerifyLimit
	 */
	public int getLockPeriodForVerifyLimitAsPrimitive(final SessionContext ctx)
	{
		Integer value = getLockPeriodForVerifyLimit( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.lockPeriodForVerifyLimit</code> attribute. 
	 * @return the lockPeriodForVerifyLimit
	 */
	public int getLockPeriodForVerifyLimitAsPrimitive()
	{
		return getLockPeriodForVerifyLimitAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.lockPeriodForVerifyLimit</code> attribute. 
	 * @param value the lockPeriodForVerifyLimit
	 */
	public void setLockPeriodForVerifyLimit(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, LOCKPERIODFORVERIFYLIMIT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.lockPeriodForVerifyLimit</code> attribute. 
	 * @param value the lockPeriodForVerifyLimit
	 */
	public void setLockPeriodForVerifyLimit(final Integer value)
	{
		setLockPeriodForVerifyLimit( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.lockPeriodForVerifyLimit</code> attribute. 
	 * @param value the lockPeriodForVerifyLimit
	 */
	public void setLockPeriodForVerifyLimit(final SessionContext ctx, final int value)
	{
		setLockPeriodForVerifyLimit( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.lockPeriodForVerifyLimit</code> attribute. 
	 * @param value the lockPeriodForVerifyLimit
	 */
	public void setLockPeriodForVerifyLimit(final int value)
	{
		setLockPeriodForVerifyLimit( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.maxNoOfAttemptsToResend</code> attribute.
	 * @return the maxNoOfAttemptsToResend
	 */
	public Integer getMaxNoOfAttemptsToResend(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, MAXNOOFATTEMPTSTORESEND);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.maxNoOfAttemptsToResend</code> attribute.
	 * @return the maxNoOfAttemptsToResend
	 */
	public Integer getMaxNoOfAttemptsToResend()
	{
		return getMaxNoOfAttemptsToResend( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.maxNoOfAttemptsToResend</code> attribute. 
	 * @return the maxNoOfAttemptsToResend
	 */
	public int getMaxNoOfAttemptsToResendAsPrimitive(final SessionContext ctx)
	{
		Integer value = getMaxNoOfAttemptsToResend( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.maxNoOfAttemptsToResend</code> attribute. 
	 * @return the maxNoOfAttemptsToResend
	 */
	public int getMaxNoOfAttemptsToResendAsPrimitive()
	{
		return getMaxNoOfAttemptsToResendAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.maxNoOfAttemptsToResend</code> attribute. 
	 * @param value the maxNoOfAttemptsToResend
	 */
	public void setMaxNoOfAttemptsToResend(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, MAXNOOFATTEMPTSTORESEND,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.maxNoOfAttemptsToResend</code> attribute. 
	 * @param value the maxNoOfAttemptsToResend
	 */
	public void setMaxNoOfAttemptsToResend(final Integer value)
	{
		setMaxNoOfAttemptsToResend( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.maxNoOfAttemptsToResend</code> attribute. 
	 * @param value the maxNoOfAttemptsToResend
	 */
	public void setMaxNoOfAttemptsToResend(final SessionContext ctx, final int value)
	{
		setMaxNoOfAttemptsToResend( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.maxNoOfAttemptsToResend</code> attribute. 
	 * @param value the maxNoOfAttemptsToResend
	 */
	public void setMaxNoOfAttemptsToResend(final int value)
	{
		setMaxNoOfAttemptsToResend( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.maxNoOfAttemptsToVerify</code> attribute.
	 * @return the maxNoOfAttemptsToVerify
	 */
	public Integer getMaxNoOfAttemptsToVerify(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, MAXNOOFATTEMPTSTOVERIFY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.maxNoOfAttemptsToVerify</code> attribute.
	 * @return the maxNoOfAttemptsToVerify
	 */
	public Integer getMaxNoOfAttemptsToVerify()
	{
		return getMaxNoOfAttemptsToVerify( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.maxNoOfAttemptsToVerify</code> attribute. 
	 * @return the maxNoOfAttemptsToVerify
	 */
	public int getMaxNoOfAttemptsToVerifyAsPrimitive(final SessionContext ctx)
	{
		Integer value = getMaxNoOfAttemptsToVerify( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.maxNoOfAttemptsToVerify</code> attribute. 
	 * @return the maxNoOfAttemptsToVerify
	 */
	public int getMaxNoOfAttemptsToVerifyAsPrimitive()
	{
		return getMaxNoOfAttemptsToVerifyAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.maxNoOfAttemptsToVerify</code> attribute. 
	 * @param value the maxNoOfAttemptsToVerify
	 */
	public void setMaxNoOfAttemptsToVerify(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, MAXNOOFATTEMPTSTOVERIFY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.maxNoOfAttemptsToVerify</code> attribute. 
	 * @param value the maxNoOfAttemptsToVerify
	 */
	public void setMaxNoOfAttemptsToVerify(final Integer value)
	{
		setMaxNoOfAttemptsToVerify( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.maxNoOfAttemptsToVerify</code> attribute. 
	 * @param value the maxNoOfAttemptsToVerify
	 */
	public void setMaxNoOfAttemptsToVerify(final SessionContext ctx, final int value)
	{
		setMaxNoOfAttemptsToVerify( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.maxNoOfAttemptsToVerify</code> attribute. 
	 * @param value the maxNoOfAttemptsToVerify
	 */
	public void setMaxNoOfAttemptsToVerify(final int value)
	{
		setMaxNoOfAttemptsToVerify( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.medium</code> attribute.
	 * @return the medium
	 */
	public EnumerationValue getMedium(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, MEDIUM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.medium</code> attribute.
	 * @return the medium
	 */
	public EnumerationValue getMedium()
	{
		return getMedium( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.medium</code> attribute. 
	 * @param value the medium
	 */
	public void setMedium(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, MEDIUM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.medium</code> attribute. 
	 * @param value the medium
	 */
	public void setMedium(final EnumerationValue value)
	{
		setMedium( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.otpChannel</code> attribute.
	 * @return the otpChannel
	 */
	public EnumerationValue getOtpChannel(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, OTPCHANNEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.otpChannel</code> attribute.
	 * @return the otpChannel
	 */
	public EnumerationValue getOtpChannel()
	{
		return getOtpChannel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.otpChannel</code> attribute. 
	 * @param value the otpChannel
	 */
	public void setOtpChannel(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, OTPCHANNEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.otpChannel</code> attribute. 
	 * @param value the otpChannel
	 */
	public void setOtpChannel(final EnumerationValue value)
	{
		setOtpChannel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.otpType</code> attribute.
	 * @return the otpType
	 */
	public EnumerationValue getOtpType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, OTPTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserOTPConfigRules.otpType</code> attribute.
	 * @return the otpType
	 */
	public EnumerationValue getOtpType()
	{
		return getOtpType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.otpType</code> attribute. 
	 * @param value the otpType
	 */
	public void setOtpType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, OTPTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserOTPConfigRules.otpType</code> attribute. 
	 * @param value the otpType
	 */
	public void setOtpType(final EnumerationValue value)
	{
		setOtpType( getSession().getSessionContext(), value );
	}
	
}
