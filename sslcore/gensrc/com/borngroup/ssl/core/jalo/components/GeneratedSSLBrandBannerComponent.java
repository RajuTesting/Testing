/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.AbstractMediaContainerComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLBrandBannerComponent SSLBrandBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBrandBannerComponent extends AbstractMediaContainerComponent
{
	/** Qualifier of the <code>SSLBrandBannerComponent.link</code> attribute **/
	public static final String LINK = "link";
	/** Qualifier of the <code>SSLBrandBannerComponent.activeFrom</code> attribute **/
	public static final String ACTIVEFROM = "activeFrom";
	/** Qualifier of the <code>SSLBrandBannerComponent.activeUntil</code> attribute **/
	public static final String ACTIVEUNTIL = "activeUntil";
	/** Qualifier of the <code>SSLBrandBannerComponent.imageStyle</code> attribute **/
	public static final String IMAGESTYLE = "imageStyle";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractMediaContainerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LINK, AttributeMode.INITIAL);
		tmp.put(ACTIVEFROM, AttributeMode.INITIAL);
		tmp.put(ACTIVEUNTIL, AttributeMode.INITIAL);
		tmp.put(IMAGESTYLE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEFROM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom()
	{
		return getActiveFrom( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEFROM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final Date value)
	{
		setActiveFrom( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEUNTIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil()
	{
		return getActiveUntil( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEUNTIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final Date value)
	{
		setActiveUntil( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerComponent.imageStyle</code> attribute.
	 * @return the imageStyle - This would depict which type of css needs to be applied to image
	 */
	public EnumerationValue getImageStyle(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, IMAGESTYLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerComponent.imageStyle</code> attribute.
	 * @return the imageStyle - This would depict which type of css needs to be applied to image
	 */
	public EnumerationValue getImageStyle()
	{
		return getImageStyle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerComponent.imageStyle</code> attribute. 
	 * @param value the imageStyle - This would depict which type of css needs to be applied to image
	 */
	public void setImageStyle(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, IMAGESTYLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerComponent.imageStyle</code> attribute. 
	 * @param value the imageStyle - This would depict which type of css needs to be applied to image
	 */
	public void setImageStyle(final EnumerationValue value)
	{
		setImageStyle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerComponent.link</code> attribute.
	 * @return the link - Link
	 */
	public String getLink(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerComponent.link</code> attribute.
	 * @return the link - Link
	 */
	public String getLink()
	{
		return getLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerComponent.link</code> attribute. 
	 * @param value the link - Link
	 */
	public void setLink(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerComponent.link</code> attribute. 
	 * @param value the link - Link
	 */
	public void setLink(final String value)
	{
		setLink( getSession().getSessionContext(), value );
	}
	
}
