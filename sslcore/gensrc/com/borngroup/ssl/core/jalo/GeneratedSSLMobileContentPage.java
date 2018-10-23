/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.pages.ContentPage;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLMobileContentPage SSLMobileContentPage}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLMobileContentPage extends ContentPage
{
	/** Qualifier of the <code>SSLMobileContentPage.version</code> attribute **/
	public static final String VERSION = "version";
	/** Qualifier of the <code>SSLMobileContentPage.backgroundColor</code> attribute **/
	public static final String BACKGROUNDCOLOR = "backgroundColor";
	/** Qualifier of the <code>SSLMobileContentPage.enabledForVersioning</code> attribute **/
	public static final String ENABLEDFORVERSIONING = "enabledForVersioning";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(ContentPage.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(VERSION, AttributeMode.INITIAL);
		tmp.put(BACKGROUNDCOLOR, AttributeMode.INITIAL);
		tmp.put(ENABLEDFORVERSIONING, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileContentPage.backgroundColor</code> attribute.
	 * @return the backgroundColor - BackGround Color of every page
	 */
	public String getBackgroundColor(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BACKGROUNDCOLOR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileContentPage.backgroundColor</code> attribute.
	 * @return the backgroundColor - BackGround Color of every page
	 */
	public String getBackgroundColor()
	{
		return getBackgroundColor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileContentPage.backgroundColor</code> attribute. 
	 * @param value the backgroundColor - BackGround Color of every page
	 */
	public void setBackgroundColor(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BACKGROUNDCOLOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileContentPage.backgroundColor</code> attribute. 
	 * @param value the backgroundColor - BackGround Color of every page
	 */
	public void setBackgroundColor(final String value)
	{
		setBackgroundColor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileContentPage.enabledForVersioning</code> attribute.
	 * @return the enabledForVersioning - flag to decide whether to send page in config service or not
	 */
	public Boolean isEnabledForVersioning(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ENABLEDFORVERSIONING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileContentPage.enabledForVersioning</code> attribute.
	 * @return the enabledForVersioning - flag to decide whether to send page in config service or not
	 */
	public Boolean isEnabledForVersioning()
	{
		return isEnabledForVersioning( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileContentPage.enabledForVersioning</code> attribute. 
	 * @return the enabledForVersioning - flag to decide whether to send page in config service or not
	 */
	public boolean isEnabledForVersioningAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isEnabledForVersioning( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileContentPage.enabledForVersioning</code> attribute. 
	 * @return the enabledForVersioning - flag to decide whether to send page in config service or not
	 */
	public boolean isEnabledForVersioningAsPrimitive()
	{
		return isEnabledForVersioningAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileContentPage.enabledForVersioning</code> attribute. 
	 * @param value the enabledForVersioning - flag to decide whether to send page in config service or not
	 */
	public void setEnabledForVersioning(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ENABLEDFORVERSIONING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileContentPage.enabledForVersioning</code> attribute. 
	 * @param value the enabledForVersioning - flag to decide whether to send page in config service or not
	 */
	public void setEnabledForVersioning(final Boolean value)
	{
		setEnabledForVersioning( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileContentPage.enabledForVersioning</code> attribute. 
	 * @param value the enabledForVersioning - flag to decide whether to send page in config service or not
	 */
	public void setEnabledForVersioning(final SessionContext ctx, final boolean value)
	{
		setEnabledForVersioning( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileContentPage.enabledForVersioning</code> attribute. 
	 * @param value the enabledForVersioning - flag to decide whether to send page in config service or not
	 */
	public void setEnabledForVersioning(final boolean value)
	{
		setEnabledForVersioning( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileContentPage.version</code> attribute.
	 * @return the version - Page Version
	 */
	public Long getVersion(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, VERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileContentPage.version</code> attribute.
	 * @return the version - Page Version
	 */
	public Long getVersion()
	{
		return getVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileContentPage.version</code> attribute. 
	 * @return the version - Page Version
	 */
	public long getVersionAsPrimitive(final SessionContext ctx)
	{
		Long value = getVersion( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMobileContentPage.version</code> attribute. 
	 * @return the version - Page Version
	 */
	public long getVersionAsPrimitive()
	{
		return getVersionAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileContentPage.version</code> attribute. 
	 * @param value the version - Page Version
	 */
	public void setVersion(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, VERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileContentPage.version</code> attribute. 
	 * @param value the version - Page Version
	 */
	public void setVersion(final Long value)
	{
		setVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileContentPage.version</code> attribute. 
	 * @param value the version - Page Version
	 */
	public void setVersion(final SessionContext ctx, final long value)
	{
		setVersion( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMobileContentPage.version</code> attribute. 
	 * @param value the version - Page Version
	 */
	public void setVersion(final long value)
	{
		setVersion( getSession().getSessionContext(), value );
	}
	
}
