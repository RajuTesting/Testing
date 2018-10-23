/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2lib.components.ProductCarouselComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLBrandProductCarouselComponent SSLBrandProductCarouselComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBrandProductCarouselComponent extends ProductCarouselComponent
{
	/** Qualifier of the <code>SSLBrandProductCarouselComponent.header</code> attribute **/
	public static final String HEADER = "header";
	/** Qualifier of the <code>SSLBrandProductCarouselComponent.activeFrom</code> attribute **/
	public static final String ACTIVEFROM = "activeFrom";
	/** Qualifier of the <code>SSLBrandProductCarouselComponent.activeUntil</code> attribute **/
	public static final String ACTIVEUNTIL = "activeUntil";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(ProductCarouselComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADER, AttributeMode.INITIAL);
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
	 * <i>Generated method</i> - Getter of the <code>SSLBrandProductCarouselComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEFROM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandProductCarouselComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom()
	{
		return getActiveFrom( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandProductCarouselComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEFROM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandProductCarouselComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final Date value)
	{
		setActiveFrom( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandProductCarouselComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEUNTIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandProductCarouselComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil()
	{
		return getActiveUntil( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandProductCarouselComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEUNTIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandProductCarouselComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final Date value)
	{
		setActiveUntil( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandProductCarouselComponent.header</code> attribute.
	 * @return the header - Link
	 */
	public String getHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandProductCarouselComponent.header</code> attribute.
	 * @return the header - Link
	 */
	public String getHeader()
	{
		return getHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandProductCarouselComponent.header</code> attribute. 
	 * @param value the header - Link
	 */
	public void setHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandProductCarouselComponent.header</code> attribute. 
	 * @param value the header - Link
	 */
	public void setHeader(final String value)
	{
		setHeader( getSession().getSessionContext(), value );
	}
	
}
