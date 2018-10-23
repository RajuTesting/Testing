/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 *  
 * [y] hybris Platform
 *  
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *  
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *  
 */
package de.hybris.platform.commerceservices.jalo.process;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.commerceservices.jalo.process.StoreFrontProcess;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.Order;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.commerceservices.jalo.process.OrderCompletionFeedbackStoreFrontProcess OrderCompletionFeedbackStoreFrontProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOrderCompletionFeedbackStoreFrontProcess extends StoreFrontProcess
{
	/** Qualifier of the <code>OrderCompletionFeedbackStoreFrontProcess.currentOrder</code> attribute **/
	public static final String CURRENTORDER = "currentOrder";
	/** Qualifier of the <code>OrderCompletionFeedbackStoreFrontProcess.encodedUniqueUrl</code> attribute **/
	public static final String ENCODEDUNIQUEURL = "encodedUniqueUrl";
	/** Qualifier of the <code>OrderCompletionFeedbackStoreFrontProcess.userMailId</code> attribute **/
	public static final String USERMAILID = "userMailId";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(StoreFrontProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CURRENTORDER, AttributeMode.INITIAL);
		tmp.put(ENCODEDUNIQUEURL, AttributeMode.INITIAL);
		tmp.put(USERMAILID, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderCompletionFeedbackStoreFrontProcess.currentOrder</code> attribute.
	 * @return the currentOrder - Current Order model
	 */
	public Order getCurrentOrder(final SessionContext ctx)
	{
		return (Order)getProperty( ctx, CURRENTORDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderCompletionFeedbackStoreFrontProcess.currentOrder</code> attribute.
	 * @return the currentOrder - Current Order model
	 */
	public Order getCurrentOrder()
	{
		return getCurrentOrder( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderCompletionFeedbackStoreFrontProcess.currentOrder</code> attribute. 
	 * @param value the currentOrder - Current Order model
	 */
	public void setCurrentOrder(final SessionContext ctx, final Order value)
	{
		setProperty(ctx, CURRENTORDER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderCompletionFeedbackStoreFrontProcess.currentOrder</code> attribute. 
	 * @param value the currentOrder - Current Order model
	 */
	public void setCurrentOrder(final Order value)
	{
		setCurrentOrder( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderCompletionFeedbackStoreFrontProcess.encodedUniqueUrl</code> attribute.
	 * @return the encodedUniqueUrl - Encoded hash url for time restricted validity
	 */
	public String getEncodedUniqueUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ENCODEDUNIQUEURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderCompletionFeedbackStoreFrontProcess.encodedUniqueUrl</code> attribute.
	 * @return the encodedUniqueUrl - Encoded hash url for time restricted validity
	 */
	public String getEncodedUniqueUrl()
	{
		return getEncodedUniqueUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderCompletionFeedbackStoreFrontProcess.encodedUniqueUrl</code> attribute. 
	 * @param value the encodedUniqueUrl - Encoded hash url for time restricted validity
	 */
	public void setEncodedUniqueUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ENCODEDUNIQUEURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderCompletionFeedbackStoreFrontProcess.encodedUniqueUrl</code> attribute. 
	 * @param value the encodedUniqueUrl - Encoded hash url for time restricted validity
	 */
	public void setEncodedUniqueUrl(final String value)
	{
		setEncodedUniqueUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderCompletionFeedbackStoreFrontProcess.userMailId</code> attribute.
	 * @return the userMailId - Current user mail id
	 */
	public String getUserMailId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, USERMAILID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderCompletionFeedbackStoreFrontProcess.userMailId</code> attribute.
	 * @return the userMailId - Current user mail id
	 */
	public String getUserMailId()
	{
		return getUserMailId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderCompletionFeedbackStoreFrontProcess.userMailId</code> attribute. 
	 * @param value the userMailId - Current user mail id
	 */
	public void setUserMailId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, USERMAILID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderCompletionFeedbackStoreFrontProcess.userMailId</code> attribute. 
	 * @param value the userMailId - Current user mail id
	 */
	public void setUserMailId(final String value)
	{
		setUserMailId( getSession().getSessionContext(), value );
	}
	
}
