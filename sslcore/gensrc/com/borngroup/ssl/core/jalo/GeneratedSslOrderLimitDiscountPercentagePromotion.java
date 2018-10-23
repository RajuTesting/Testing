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
import de.hybris.platform.promotions.jalo.OrderPromotion;
import de.hybris.platform.promotions.jalo.PromotionPriceRow;
import de.hybris.platform.util.PartOfHandler;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SslOrderLimitDiscountPercentagePromotion SslOrderLimitDiscountPercentagePromotion}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslOrderLimitDiscountPercentagePromotion extends OrderPromotion
{
	/** Qualifier of the <code>SslOrderLimitDiscountPercentagePromotion.upperThresholdTotals</code> attribute **/
	public static final String UPPERTHRESHOLDTOTALS = "upperThresholdTotals";
	/** Qualifier of the <code>SslOrderLimitDiscountPercentagePromotion.lowerThresholdTotals</code> attribute **/
	public static final String LOWERTHRESHOLDTOTALS = "lowerThresholdTotals";
	/** Qualifier of the <code>SslOrderLimitDiscountPercentagePromotion.percentageDiscount</code> attribute **/
	public static final String PERCENTAGEDISCOUNT = "percentageDiscount";
	/** Qualifier of the <code>SslOrderLimitDiscountPercentagePromotion.messageFired</code> attribute **/
	public static final String MESSAGEFIRED = "messageFired";
	/** Qualifier of the <code>SslOrderLimitDiscountPercentagePromotion.messageCouldHaveFired</code> attribute **/
	public static final String MESSAGECOULDHAVEFIRED = "messageCouldHaveFired";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(OrderPromotion.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(UPPERTHRESHOLDTOTALS, AttributeMode.INITIAL);
		tmp.put(LOWERTHRESHOLDTOTALS, AttributeMode.INITIAL);
		tmp.put(PERCENTAGEDISCOUNT, AttributeMode.INITIAL);
		tmp.put(MESSAGEFIRED, AttributeMode.INITIAL);
		tmp.put(MESSAGECOULDHAVEFIRED, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.lowerThresholdTotals</code> attribute.
	 * @return the lowerThresholdTotals - The cart total value lower threshold in specific
	 *                             currencies.
	 */
	public Collection<PromotionPriceRow> getLowerThresholdTotals(final SessionContext ctx)
	{
		Collection<PromotionPriceRow> coll = (Collection<PromotionPriceRow>)getProperty( ctx, LOWERTHRESHOLDTOTALS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.lowerThresholdTotals</code> attribute.
	 * @return the lowerThresholdTotals - The cart total value lower threshold in specific
	 *                             currencies.
	 */
	public Collection<PromotionPriceRow> getLowerThresholdTotals()
	{
		return getLowerThresholdTotals( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.lowerThresholdTotals</code> attribute. 
	 * @param value the lowerThresholdTotals - The cart total value lower threshold in specific
	 *                             currencies.
	 */
	public void setLowerThresholdTotals(final SessionContext ctx, final Collection<PromotionPriceRow> value)
	{
		new PartOfHandler<Collection<PromotionPriceRow>>()
		{
			@Override
			protected Collection<PromotionPriceRow> doGetValue(final SessionContext ctx)
			{
				return getLowerThresholdTotals( ctx );
			}
			@Override
			protected void doSetValue(final SessionContext ctx, final Collection<PromotionPriceRow> _value)
			{
				final Collection<PromotionPriceRow> value = _value;
				setProperty(ctx, LOWERTHRESHOLDTOTALS,value == null || !value.isEmpty() ? value : null );
			}
		}.setValue( ctx, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.lowerThresholdTotals</code> attribute. 
	 * @param value the lowerThresholdTotals - The cart total value lower threshold in specific
	 *                             currencies.
	 */
	public void setLowerThresholdTotals(final Collection<PromotionPriceRow> value)
	{
		setLowerThresholdTotals( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.messageCouldHaveFired</code> attribute.
	 * @return the messageCouldHaveFired - The message to show when the promotion could have
	 *                             potentially fire.
	 */
	public String getMessageCouldHaveFired(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslOrderLimitDiscountPercentagePromotion.getMessageCouldHaveFired requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, MESSAGECOULDHAVEFIRED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.messageCouldHaveFired</code> attribute.
	 * @return the messageCouldHaveFired - The message to show when the promotion could have
	 *                             potentially fire.
	 */
	public String getMessageCouldHaveFired()
	{
		return getMessageCouldHaveFired( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.messageCouldHaveFired</code> attribute. 
	 * @return the localized messageCouldHaveFired - The message to show when the promotion could have
	 *                             potentially fire.
	 */
	public Map<Language,String> getAllMessageCouldHaveFired(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,MESSAGECOULDHAVEFIRED,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.messageCouldHaveFired</code> attribute. 
	 * @return the localized messageCouldHaveFired - The message to show when the promotion could have
	 *                             potentially fire.
	 */
	public Map<Language,String> getAllMessageCouldHaveFired()
	{
		return getAllMessageCouldHaveFired( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.messageCouldHaveFired</code> attribute. 
	 * @param value the messageCouldHaveFired - The message to show when the promotion could have
	 *                             potentially fire.
	 */
	public void setMessageCouldHaveFired(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslOrderLimitDiscountPercentagePromotion.setMessageCouldHaveFired requires a session language", 0 );
		}
		setLocalizedProperty(ctx, MESSAGECOULDHAVEFIRED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.messageCouldHaveFired</code> attribute. 
	 * @param value the messageCouldHaveFired - The message to show when the promotion could have
	 *                             potentially fire.
	 */
	public void setMessageCouldHaveFired(final String value)
	{
		setMessageCouldHaveFired( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.messageCouldHaveFired</code> attribute. 
	 * @param value the messageCouldHaveFired - The message to show when the promotion could have
	 *                             potentially fire.
	 */
	public void setAllMessageCouldHaveFired(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,MESSAGECOULDHAVEFIRED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.messageCouldHaveFired</code> attribute. 
	 * @param value the messageCouldHaveFired - The message to show when the promotion could have
	 *                             potentially fire.
	 */
	public void setAllMessageCouldHaveFired(final Map<Language,String> value)
	{
		setAllMessageCouldHaveFired( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.messageFired</code> attribute.
	 * @return the messageFired - The message to show when the promotion has fired.
	 */
	public String getMessageFired(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslOrderLimitDiscountPercentagePromotion.getMessageFired requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, MESSAGEFIRED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.messageFired</code> attribute.
	 * @return the messageFired - The message to show when the promotion has fired.
	 */
	public String getMessageFired()
	{
		return getMessageFired( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.messageFired</code> attribute. 
	 * @return the localized messageFired - The message to show when the promotion has fired.
	 */
	public Map<Language,String> getAllMessageFired(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,MESSAGEFIRED,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.messageFired</code> attribute. 
	 * @return the localized messageFired - The message to show when the promotion has fired.
	 */
	public Map<Language,String> getAllMessageFired()
	{
		return getAllMessageFired( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.messageFired</code> attribute. 
	 * @param value the messageFired - The message to show when the promotion has fired.
	 */
	public void setMessageFired(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslOrderLimitDiscountPercentagePromotion.setMessageFired requires a session language", 0 );
		}
		setLocalizedProperty(ctx, MESSAGEFIRED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.messageFired</code> attribute. 
	 * @param value the messageFired - The message to show when the promotion has fired.
	 */
	public void setMessageFired(final String value)
	{
		setMessageFired( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.messageFired</code> attribute. 
	 * @param value the messageFired - The message to show when the promotion has fired.
	 */
	public void setAllMessageFired(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,MESSAGEFIRED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.messageFired</code> attribute. 
	 * @param value the messageFired - The message to show when the promotion has fired.
	 */
	public void setAllMessageFired(final Map<Language,String> value)
	{
		setAllMessageFired( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.percentageDiscount</code> attribute.
	 * @return the percentageDiscount - Percentage discount.
	 */
	public Double getPercentageDiscount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, PERCENTAGEDISCOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.percentageDiscount</code> attribute.
	 * @return the percentageDiscount - Percentage discount.
	 */
	public Double getPercentageDiscount()
	{
		return getPercentageDiscount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.percentageDiscount</code> attribute. 
	 * @return the percentageDiscount - Percentage discount.
	 */
	public double getPercentageDiscountAsPrimitive(final SessionContext ctx)
	{
		Double value = getPercentageDiscount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.percentageDiscount</code> attribute. 
	 * @return the percentageDiscount - Percentage discount.
	 */
	public double getPercentageDiscountAsPrimitive()
	{
		return getPercentageDiscountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.percentageDiscount</code> attribute. 
	 * @param value the percentageDiscount - Percentage discount.
	 */
	public void setPercentageDiscount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, PERCENTAGEDISCOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.percentageDiscount</code> attribute. 
	 * @param value the percentageDiscount - Percentage discount.
	 */
	public void setPercentageDiscount(final Double value)
	{
		setPercentageDiscount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.percentageDiscount</code> attribute. 
	 * @param value the percentageDiscount - Percentage discount.
	 */
	public void setPercentageDiscount(final SessionContext ctx, final double value)
	{
		setPercentageDiscount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.percentageDiscount</code> attribute. 
	 * @param value the percentageDiscount - Percentage discount.
	 */
	public void setPercentageDiscount(final double value)
	{
		setPercentageDiscount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.upperThresholdTotals</code> attribute.
	 * @return the upperThresholdTotals - The cart total value upper threshold in specific
	 *                             currencies.
	 */
	public Collection<PromotionPriceRow> getUpperThresholdTotals(final SessionContext ctx)
	{
		Collection<PromotionPriceRow> coll = (Collection<PromotionPriceRow>)getProperty( ctx, UPPERTHRESHOLDTOTALS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderLimitDiscountPercentagePromotion.upperThresholdTotals</code> attribute.
	 * @return the upperThresholdTotals - The cart total value upper threshold in specific
	 *                             currencies.
	 */
	public Collection<PromotionPriceRow> getUpperThresholdTotals()
	{
		return getUpperThresholdTotals( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.upperThresholdTotals</code> attribute. 
	 * @param value the upperThresholdTotals - The cart total value upper threshold in specific
	 *                             currencies.
	 */
	public void setUpperThresholdTotals(final SessionContext ctx, final Collection<PromotionPriceRow> value)
	{
		new PartOfHandler<Collection<PromotionPriceRow>>()
		{
			@Override
			protected Collection<PromotionPriceRow> doGetValue(final SessionContext ctx)
			{
				return getUpperThresholdTotals( ctx );
			}
			@Override
			protected void doSetValue(final SessionContext ctx, final Collection<PromotionPriceRow> _value)
			{
				final Collection<PromotionPriceRow> value = _value;
				setProperty(ctx, UPPERTHRESHOLDTOTALS,value == null || !value.isEmpty() ? value : null );
			}
		}.setValue( ctx, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderLimitDiscountPercentagePromotion.upperThresholdTotals</code> attribute. 
	 * @param value the upperThresholdTotals - The cart total value upper threshold in specific
	 *                             currencies.
	 */
	public void setUpperThresholdTotals(final Collection<PromotionPriceRow> value)
	{
		setUpperThresholdTotals( getSession().getSessionContext(), value );
	}
	
}
