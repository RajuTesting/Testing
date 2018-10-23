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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem PromotionalMessageInfoComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionalMessageInfoComponent extends GenericItem
{
	/** Qualifier of the <code>PromotionalMessageInfoComponent.message</code> attribute **/
	public static final String MESSAGE = "message";
	/** Qualifier of the <code>PromotionalMessageInfoComponent.startDate</code> attribute **/
	public static final String STARTDATE = "startDate";
	/** Qualifier of the <code>PromotionalMessageInfoComponent.endDate</code> attribute **/
	public static final String ENDDATE = "endDate";
	/** Qualifier of the <code>PromotionalMessageInfoComponent.isActive</code> attribute **/
	public static final String ISACTIVE = "isActive";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(MESSAGE, AttributeMode.INITIAL);
		tmp.put(STARTDATE, AttributeMode.INITIAL);
		tmp.put(ENDDATE, AttributeMode.INITIAL);
		tmp.put(ISACTIVE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionalMessageInfoComponent.endDate</code> attribute.
	 * @return the endDate - End Date of Promotion
	 */
	public Date getEndDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ENDDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionalMessageInfoComponent.endDate</code> attribute.
	 * @return the endDate - End Date of Promotion
	 */
	public Date getEndDate()
	{
		return getEndDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionalMessageInfoComponent.endDate</code> attribute. 
	 * @param value the endDate - End Date of Promotion
	 */
	public void setEndDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ENDDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionalMessageInfoComponent.endDate</code> attribute. 
	 * @param value the endDate - End Date of Promotion
	 */
	public void setEndDate(final Date value)
	{
		setEndDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionalMessageInfoComponent.isActive</code> attribute.
	 * @return the isActive - Whether Promotional Message is active or not
	 */
	public Boolean isIsActive(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ISACTIVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionalMessageInfoComponent.isActive</code> attribute.
	 * @return the isActive - Whether Promotional Message is active or not
	 */
	public Boolean isIsActive()
	{
		return isIsActive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionalMessageInfoComponent.isActive</code> attribute. 
	 * @return the isActive - Whether Promotional Message is active or not
	 */
	public boolean isIsActiveAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIsActive( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionalMessageInfoComponent.isActive</code> attribute. 
	 * @return the isActive - Whether Promotional Message is active or not
	 */
	public boolean isIsActiveAsPrimitive()
	{
		return isIsActiveAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionalMessageInfoComponent.isActive</code> attribute. 
	 * @param value the isActive - Whether Promotional Message is active or not
	 */
	public void setIsActive(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ISACTIVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionalMessageInfoComponent.isActive</code> attribute. 
	 * @param value the isActive - Whether Promotional Message is active or not
	 */
	public void setIsActive(final Boolean value)
	{
		setIsActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionalMessageInfoComponent.isActive</code> attribute. 
	 * @param value the isActive - Whether Promotional Message is active or not
	 */
	public void setIsActive(final SessionContext ctx, final boolean value)
	{
		setIsActive( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionalMessageInfoComponent.isActive</code> attribute. 
	 * @param value the isActive - Whether Promotional Message is active or not
	 */
	public void setIsActive(final boolean value)
	{
		setIsActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionalMessageInfoComponent.message</code> attribute.
	 * @return the message - Promotional Messgae to be displayed.
	 */
	public String getMessage(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MESSAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionalMessageInfoComponent.message</code> attribute.
	 * @return the message - Promotional Messgae to be displayed.
	 */
	public String getMessage()
	{
		return getMessage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionalMessageInfoComponent.message</code> attribute. 
	 * @param value the message - Promotional Messgae to be displayed.
	 */
	public void setMessage(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MESSAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionalMessageInfoComponent.message</code> attribute. 
	 * @param value the message - Promotional Messgae to be displayed.
	 */
	public void setMessage(final String value)
	{
		setMessage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionalMessageInfoComponent.startDate</code> attribute.
	 * @return the startDate - Start Date of Promotion
	 */
	public Date getStartDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, STARTDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionalMessageInfoComponent.startDate</code> attribute.
	 * @return the startDate - Start Date of Promotion
	 */
	public Date getStartDate()
	{
		return getStartDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionalMessageInfoComponent.startDate</code> attribute. 
	 * @param value the startDate - Start Date of Promotion
	 */
	public void setStartDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, STARTDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionalMessageInfoComponent.startDate</code> attribute. 
	 * @param value the startDate - Start Date of Promotion
	 */
	public void setStartDate(final Date value)
	{
		setStartDate( getSession().getSessionContext(), value );
	}
	
}
