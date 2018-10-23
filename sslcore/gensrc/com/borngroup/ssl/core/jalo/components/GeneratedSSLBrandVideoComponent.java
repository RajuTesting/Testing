/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.BrandVideoComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLBrandVideoComponent SSLBrandVideoComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBrandVideoComponent extends BrandVideoComponent
{
	/** Qualifier of the <code>SSLBrandVideoComponent.height</code> attribute **/
	public static final String HEIGHT = "height";
	/** Qualifier of the <code>SSLBrandVideoComponent.width</code> attribute **/
	public static final String WIDTH = "width";
	/** Qualifier of the <code>SSLBrandVideoComponent.activeFrom</code> attribute **/
	public static final String ACTIVEFROM = "activeFrom";
	/** Qualifier of the <code>SSLBrandVideoComponent.activeUntil</code> attribute **/
	public static final String ACTIVEUNTIL = "activeUntil";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(BrandVideoComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEIGHT, AttributeMode.INITIAL);
		tmp.put(WIDTH, AttributeMode.INITIAL);
		tmp.put(ACTIVEFROM, AttributeMode.INITIAL);
		tmp.put(ACTIVEUNTIL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandVideoComponent.activeFrom</code> attribute.
	 * @return the activeFrom - video will be active from
	 */
	public Date getActiveFrom(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEFROM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandVideoComponent.activeFrom</code> attribute.
	 * @return the activeFrom - video will be active from
	 */
	public Date getActiveFrom()
	{
		return getActiveFrom( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandVideoComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom - video will be active from
	 */
	public void setActiveFrom(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEFROM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandVideoComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom - video will be active from
	 */
	public void setActiveFrom(final Date value)
	{
		setActiveFrom( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandVideoComponent.activeUntil</code> attribute.
	 * @return the activeUntil - video will be active until
	 */
	public Date getActiveUntil(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEUNTIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandVideoComponent.activeUntil</code> attribute.
	 * @return the activeUntil - video will be active until
	 */
	public Date getActiveUntil()
	{
		return getActiveUntil( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandVideoComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil - video will be active until
	 */
	public void setActiveUntil(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEUNTIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandVideoComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil - video will be active until
	 */
	public void setActiveUntil(final Date value)
	{
		setActiveUntil( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandVideoComponent.height</code> attribute.
	 * @return the height - Height of video
	 */
	public String getHeight(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandVideoComponent.height</code> attribute.
	 * @return the height - Height of video
	 */
	public String getHeight()
	{
		return getHeight( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandVideoComponent.height</code> attribute. 
	 * @param value the height - Height of video
	 */
	public void setHeight(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandVideoComponent.height</code> attribute. 
	 * @param value the height - Height of video
	 */
	public void setHeight(final String value)
	{
		setHeight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandVideoComponent.width</code> attribute.
	 * @return the width - width of video
	 */
	public String getWidth(final SessionContext ctx)
	{
		return (String)getProperty( ctx, WIDTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandVideoComponent.width</code> attribute.
	 * @return the width - width of video
	 */
	public String getWidth()
	{
		return getWidth( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandVideoComponent.width</code> attribute. 
	 * @param value the width - width of video
	 */
	public void setWidth(final SessionContext ctx, final String value)
	{
		setProperty(ctx, WIDTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandVideoComponent.width</code> attribute. 
	 * @param value the width - width of video
	 */
	public void setWidth(final String value)
	{
		setWidth( getSession().getSessionContext(), value );
	}
	
}
