/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLMobileWidgetComponent SSLMobileWidgetComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLMobileWidgetComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLMobileWidgetComponent.version</code> attribute **/
	public static final String VERSION = "version";
	/** Qualifier of the <code>SSLMobileWidgetComponent.componentType</code> attribute **/
	public static final String COMPONENTTYPE = "componentType";
	/** Qualifier of the <code>SSLMobileWidgetComponent.backgroundColor</code> attribute **/
	public static final String BACKGROUNDCOLOR = "backgroundColor";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(VERSION, AttributeMode.INITIAL);
		tmp.put(COMPONENTTYPE, AttributeMode.INITIAL);
		tmp.put(BACKGROUNDCOLOR, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetComponent.backgroundColor</code> attribute.
	 * @return the backgroundColor - BackGround Color of every Widget
	 */
	public String getBackgroundColor(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BACKGROUNDCOLOR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetComponent.backgroundColor</code> attribute.
	 * @return the backgroundColor - BackGround Color of every Widget
	 */
	public String getBackgroundColor()
	{
		return getBackgroundColor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetComponent.backgroundColor</code> attribute. 
	 * @param value the backgroundColor - BackGround Color of every Widget
	 */
	public void setBackgroundColor(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BACKGROUNDCOLOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetComponent.backgroundColor</code> attribute. 
	 * @param value the backgroundColor - BackGround Color of every Widget
	 */
	public void setBackgroundColor(final String value)
	{
		setBackgroundColor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetComponent.componentType</code> attribute.
	 * @return the componentType - Type of cms component
	 */
	public EnumerationValue getComponentType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, COMPONENTTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetComponent.componentType</code> attribute.
	 * @return the componentType - Type of cms component
	 */
	public EnumerationValue getComponentType()
	{
		return getComponentType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetComponent.componentType</code> attribute. 
	 * @param value the componentType - Type of cms component
	 */
	public void setComponentType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, COMPONENTTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetComponent.componentType</code> attribute. 
	 * @param value the componentType - Type of cms component
	 */
	public void setComponentType(final EnumerationValue value)
	{
		setComponentType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetComponent.version</code> attribute.
	 * @return the version - Component Version
	 */
	public Long getVersion(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, VERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetComponent.version</code> attribute.
	 * @return the version - Component Version
	 */
	public Long getVersion()
	{
		return getVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetComponent.version</code> attribute. 
	 * @return the version - Component Version
	 */
	public long getVersionAsPrimitive(final SessionContext ctx)
	{
		Long value = getVersion( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileWidgetComponent.version</code> attribute. 
	 * @return the version - Component Version
	 */
	public long getVersionAsPrimitive()
	{
		return getVersionAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetComponent.version</code> attribute. 
	 * @param value the version - Component Version
	 */
	public void setVersion(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, VERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetComponent.version</code> attribute. 
	 * @param value the version - Component Version
	 */
	public void setVersion(final Long value)
	{
		setVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetComponent.version</code> attribute. 
	 * @param value the version - Component Version
	 */
	public void setVersion(final SessionContext ctx, final long value)
	{
		setVersion( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileWidgetComponent.version</code> attribute. 
	 * @param value the version - Component Version
	 */
	public void setVersion(final long value)
	{
		setVersion( getSession().getSessionContext(), value );
	}
	
}
