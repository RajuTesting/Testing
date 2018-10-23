/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.news;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.news.NewsLetterSubscribeForCustomer NewsLetterSubscribeForCustomer}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedNewsLetterSubscribeForCustomer extends GenericItem
{
	/** Qualifier of the <code>NewsLetterSubscribeForCustomer.email</code> attribute **/
	public static final String EMAIL = "email";
	/** Qualifier of the <code>NewsLetterSubscribeForCustomer.services</code> attribute **/
	public static final String SERVICES = "services";
	/** Qualifier of the <code>NewsLetterSubscribeForCustomer.FrequentPromotional</code> attribute **/
	public static final String FREQUENTPROMOTIONAL = "FrequentPromotional";
	/** Qualifier of the <code>NewsLetterSubscribeForCustomer.subscribe</code> attribute **/
	public static final String SUBSCRIBE = "subscribe";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(EMAIL, AttributeMode.INITIAL);
		tmp.put(SERVICES, AttributeMode.INITIAL);
		tmp.put(FREQUENTPROMOTIONAL, AttributeMode.INITIAL);
		tmp.put(SUBSCRIBE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewsLetterSubscribeForCustomer.email</code> attribute.
	 * @return the email - Email of customer
	 */
	public String getEmail(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewsLetterSubscribeForCustomer.email</code> attribute.
	 * @return the email - Email of customer
	 */
	public String getEmail()
	{
		return getEmail( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewsLetterSubscribeForCustomer.email</code> attribute. 
	 * @param value the email - Email of customer
	 */
	public void setEmail(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewsLetterSubscribeForCustomer.email</code> attribute. 
	 * @param value the email - Email of customer
	 */
	public void setEmail(final String value)
	{
		setEmail( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewsLetterSubscribeForCustomer.FrequentPromotional</code> attribute.
	 * @return the FrequentPromotional - frequently would you want to receive promotional
	 *                             mails from Shoppers Stop
	 */
	public EnumerationValue getFrequentPromotional(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, FREQUENTPROMOTIONAL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewsLetterSubscribeForCustomer.FrequentPromotional</code> attribute.
	 * @return the FrequentPromotional - frequently would you want to receive promotional
	 *                             mails from Shoppers Stop
	 */
	public EnumerationValue getFrequentPromotional()
	{
		return getFrequentPromotional( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewsLetterSubscribeForCustomer.FrequentPromotional</code> attribute. 
	 * @param value the FrequentPromotional - frequently would you want to receive promotional
	 *                             mails from Shoppers Stop
	 */
	public void setFrequentPromotional(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, FREQUENTPROMOTIONAL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewsLetterSubscribeForCustomer.FrequentPromotional</code> attribute. 
	 * @param value the FrequentPromotional - frequently would you want to receive promotional
	 *                             mails from Shoppers Stop
	 */
	public void setFrequentPromotional(final EnumerationValue value)
	{
		setFrequentPromotional( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewsLetterSubscribeForCustomer.services</code> attribute.
	 * @return the services - services would you like to receive from Stoppers Stop
	 */
	public List<String> getServices(final SessionContext ctx)
	{
		List<String> coll = (List<String>)getProperty( ctx, SERVICES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewsLetterSubscribeForCustomer.services</code> attribute.
	 * @return the services - services would you like to receive from Stoppers Stop
	 */
	public List<String> getServices()
	{
		return getServices( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewsLetterSubscribeForCustomer.services</code> attribute. 
	 * @param value the services - services would you like to receive from Stoppers Stop
	 */
	public void setServices(final SessionContext ctx, final List<String> value)
	{
		setProperty(ctx, SERVICES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewsLetterSubscribeForCustomer.services</code> attribute. 
	 * @param value the services - services would you like to receive from Stoppers Stop
	 */
	public void setServices(final List<String> value)
	{
		setServices( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewsLetterSubscribeForCustomer.subscribe</code> attribute.
	 * @return the subscribe - Unsubscribe or subscribe Customer from all the mails
	 */
	public Boolean isSubscribe(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, SUBSCRIBE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewsLetterSubscribeForCustomer.subscribe</code> attribute.
	 * @return the subscribe - Unsubscribe or subscribe Customer from all the mails
	 */
	public Boolean isSubscribe()
	{
		return isSubscribe( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewsLetterSubscribeForCustomer.subscribe</code> attribute. 
	 * @return the subscribe - Unsubscribe or subscribe Customer from all the mails
	 */
	public boolean isSubscribeAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isSubscribe( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewsLetterSubscribeForCustomer.subscribe</code> attribute. 
	 * @return the subscribe - Unsubscribe or subscribe Customer from all the mails
	 */
	public boolean isSubscribeAsPrimitive()
	{
		return isSubscribeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewsLetterSubscribeForCustomer.subscribe</code> attribute. 
	 * @param value the subscribe - Unsubscribe or subscribe Customer from all the mails
	 */
	public void setSubscribe(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, SUBSCRIBE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewsLetterSubscribeForCustomer.subscribe</code> attribute. 
	 * @param value the subscribe - Unsubscribe or subscribe Customer from all the mails
	 */
	public void setSubscribe(final Boolean value)
	{
		setSubscribe( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewsLetterSubscribeForCustomer.subscribe</code> attribute. 
	 * @param value the subscribe - Unsubscribe or subscribe Customer from all the mails
	 */
	public void setSubscribe(final SessionContext ctx, final boolean value)
	{
		setSubscribe( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewsLetterSubscribeForCustomer.subscribe</code> attribute. 
	 * @param value the subscribe - Unsubscribe or subscribe Customer from all the mails
	 */
	public void setSubscribe(final boolean value)
	{
		setSubscribe( getSession().getSessionContext(), value );
	}
	
}
