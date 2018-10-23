/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.voucher.jalo.PromotionVoucher;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.OrderValuePromotionVoucher OrderValuePromotionVoucher}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOrderValuePromotionVoucher extends PromotionVoucher
{
	/** Qualifier of the <code>OrderValuePromotionVoucher.upperThreshold</code> attribute **/
	public static final String UPPERTHRESHOLD = "upperThreshold";
	/** Qualifier of the <code>OrderValuePromotionVoucher.lowerThreshold</code> attribute **/
	public static final String LOWERTHRESHOLD = "lowerThreshold";
	/** Qualifier of the <code>OrderValuePromotionVoucher.violationMessage</code> attribute **/
	public static final String VIOLATIONMESSAGE = "violationMessage";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(PromotionVoucher.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(UPPERTHRESHOLD, AttributeMode.INITIAL);
		tmp.put(LOWERTHRESHOLD, AttributeMode.INITIAL);
		tmp.put(VIOLATIONMESSAGE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.lowerThreshold</code> attribute.
	 * @return the lowerThreshold - The lower threshold of an Order Value
	 */
	public Double getLowerThreshold(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, LOWERTHRESHOLD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.lowerThreshold</code> attribute.
	 * @return the lowerThreshold - The lower threshold of an Order Value
	 */
	public Double getLowerThreshold()
	{
		return getLowerThreshold( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.lowerThreshold</code> attribute. 
	 * @return the lowerThreshold - The lower threshold of an Order Value
	 */
	public double getLowerThresholdAsPrimitive(final SessionContext ctx)
	{
		Double value = getLowerThreshold( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.lowerThreshold</code> attribute. 
	 * @return the lowerThreshold - The lower threshold of an Order Value
	 */
	public double getLowerThresholdAsPrimitive()
	{
		return getLowerThresholdAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.lowerThreshold</code> attribute. 
	 * @param value the lowerThreshold - The lower threshold of an Order Value
	 */
	public void setLowerThreshold(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, LOWERTHRESHOLD,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.lowerThreshold</code> attribute. 
	 * @param value the lowerThreshold - The lower threshold of an Order Value
	 */
	public void setLowerThreshold(final Double value)
	{
		setLowerThreshold( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.lowerThreshold</code> attribute. 
	 * @param value the lowerThreshold - The lower threshold of an Order Value
	 */
	public void setLowerThreshold(final SessionContext ctx, final double value)
	{
		setLowerThreshold( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.lowerThreshold</code> attribute. 
	 * @param value the lowerThreshold - The lower threshold of an Order Value
	 */
	public void setLowerThreshold(final double value)
	{
		setLowerThreshold( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.upperThreshold</code> attribute.
	 * @return the upperThreshold - The upper threshold of an Order Value
	 */
	public Double getUpperThreshold(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, UPPERTHRESHOLD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.upperThreshold</code> attribute.
	 * @return the upperThreshold - The upper threshold of an Order Value
	 */
	public Double getUpperThreshold()
	{
		return getUpperThreshold( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.upperThreshold</code> attribute. 
	 * @return the upperThreshold - The upper threshold of an Order Value
	 */
	public double getUpperThresholdAsPrimitive(final SessionContext ctx)
	{
		Double value = getUpperThreshold( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.upperThreshold</code> attribute. 
	 * @return the upperThreshold - The upper threshold of an Order Value
	 */
	public double getUpperThresholdAsPrimitive()
	{
		return getUpperThresholdAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.upperThreshold</code> attribute. 
	 * @param value the upperThreshold - The upper threshold of an Order Value
	 */
	public void setUpperThreshold(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, UPPERTHRESHOLD,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.upperThreshold</code> attribute. 
	 * @param value the upperThreshold - The upper threshold of an Order Value
	 */
	public void setUpperThreshold(final Double value)
	{
		setUpperThreshold( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.upperThreshold</code> attribute. 
	 * @param value the upperThreshold - The upper threshold of an Order Value
	 */
	public void setUpperThreshold(final SessionContext ctx, final double value)
	{
		setUpperThreshold( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.upperThreshold</code> attribute. 
	 * @param value the upperThreshold - The upper threshold of an Order Value
	 */
	public void setUpperThreshold(final double value)
	{
		setUpperThreshold( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.violationMessage</code> attribute.
	 * @return the violationMessage - The message to show when the promotion is not applicable.
	 */
	public String getViolationMessage(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderValuePromotionVoucher.getViolationMessage requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, VIOLATIONMESSAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.violationMessage</code> attribute.
	 * @return the violationMessage - The message to show when the promotion is not applicable.
	 */
	public String getViolationMessage()
	{
		return getViolationMessage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.violationMessage</code> attribute. 
	 * @return the localized violationMessage - The message to show when the promotion is not applicable.
	 */
	public Map<Language,String> getAllViolationMessage(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,VIOLATIONMESSAGE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderValuePromotionVoucher.violationMessage</code> attribute. 
	 * @return the localized violationMessage - The message to show when the promotion is not applicable.
	 */
	public Map<Language,String> getAllViolationMessage()
	{
		return getAllViolationMessage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.violationMessage</code> attribute. 
	 * @param value the violationMessage - The message to show when the promotion is not applicable.
	 */
	public void setViolationMessage(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderValuePromotionVoucher.setViolationMessage requires a session language", 0 );
		}
		setLocalizedProperty(ctx, VIOLATIONMESSAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.violationMessage</code> attribute. 
	 * @param value the violationMessage - The message to show when the promotion is not applicable.
	 */
	public void setViolationMessage(final String value)
	{
		setViolationMessage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.violationMessage</code> attribute. 
	 * @param value the violationMessage - The message to show when the promotion is not applicable.
	 */
	public void setAllViolationMessage(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,VIOLATIONMESSAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderValuePromotionVoucher.violationMessage</code> attribute. 
	 * @param value the violationMessage - The message to show when the promotion is not applicable.
	 */
	public void setAllViolationMessage(final Map<Language,String> value)
	{
		setAllViolationMessage( getSession().getSessionContext(), value );
	}
	
}
