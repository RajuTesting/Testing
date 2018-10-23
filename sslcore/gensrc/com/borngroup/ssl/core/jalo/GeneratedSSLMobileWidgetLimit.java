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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLMobileWidgetLimit SSLMobileWidgetLimit}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLMobileWidgetLimit extends GenericItem
{
	/** Qualifier of the <code>SSLMobileWidgetLimit.componentType</code> attribute **/
	public static final String COMPONENTTYPE = "componentType";
	/** Qualifier of the <code>SSLMobileWidgetLimit.componentAttribute</code> attribute **/
	public static final String COMPONENTATTRIBUTE = "componentAttribute";
	/** Qualifier of the <code>SSLMobileWidgetLimit.limit</code> attribute **/
	public static final String LIMIT = "limit";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(COMPONENTTYPE, AttributeMode.INITIAL);
		tmp.put(COMPONENTATTRIBUTE, AttributeMode.INITIAL);
		tmp.put(LIMIT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetLimit.componentAttribute</code> attribute.
	 * @return the componentAttribute
	 */
	public EnumerationValue getComponentAttribute(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, COMPONENTATTRIBUTE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetLimit.componentAttribute</code> attribute.
	 * @return the componentAttribute
	 */
	public EnumerationValue getComponentAttribute()
	{
		return getComponentAttribute( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetLimit.componentAttribute</code> attribute. 
	 * @param value the componentAttribute
	 */
	public void setComponentAttribute(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, COMPONENTATTRIBUTE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetLimit.componentAttribute</code> attribute. 
	 * @param value the componentAttribute
	 */
	public void setComponentAttribute(final EnumerationValue value)
	{
		setComponentAttribute( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetLimit.componentType</code> attribute.
	 * @return the componentType
	 */
	public EnumerationValue getComponentType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, COMPONENTTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetLimit.componentType</code> attribute.
	 * @return the componentType
	 */
	public EnumerationValue getComponentType()
	{
		return getComponentType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetLimit.componentType</code> attribute. 
	 * @param value the componentType
	 */
	public void setComponentType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, COMPONENTTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetLimit.componentType</code> attribute. 
	 * @param value the componentType
	 */
	public void setComponentType(final EnumerationValue value)
	{
		setComponentType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetLimit.limit</code> attribute.
	 * @return the limit
	 */
	public Integer getLimit(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, LIMIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetLimit.limit</code> attribute.
	 * @return the limit
	 */
	public Integer getLimit()
	{
		return getLimit( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetLimit.limit</code> attribute. 
	 * @return the limit
	 */
	public int getLimitAsPrimitive(final SessionContext ctx)
	{
		Integer value = getLimit( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetLimit.limit</code> attribute. 
	 * @return the limit
	 */
	public int getLimitAsPrimitive()
	{
		return getLimitAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetLimit.limit</code> attribute. 
	 * @param value the limit
	 */
	public void setLimit(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, LIMIT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetLimit.limit</code> attribute. 
	 * @param value the limit
	 */
	public void setLimit(final Integer value)
	{
		setLimit( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetLimit.limit</code> attribute. 
	 * @param value the limit
	 */
	public void setLimit(final SessionContext ctx, final int value)
	{
		setLimit( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetLimit.limit</code> attribute. 
	 * @param value the limit
	 */
	public void setLimit(final int value)
	{
		setLimit( getSession().getSessionContext(), value );
	}
	
}
