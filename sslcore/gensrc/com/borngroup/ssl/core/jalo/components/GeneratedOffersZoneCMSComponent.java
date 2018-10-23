/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.OffersZoneCMSComponent OffersZoneCMSComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOffersZoneCMSComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>OffersZoneCMSComponent.offersZoneImage</code> attribute **/
	public static final String OFFERSZONEIMAGE = "offersZoneImage";
	/** Qualifier of the <code>OffersZoneCMSComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>OffersZoneCMSComponent.URL</code> attribute **/
	public static final String URL = "URL";
	/** Qualifier of the <code>OffersZoneCMSComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	/** Qualifier of the <code>OffersZoneCMSComponent.linkType</code> attribute **/
	public static final String LINKTYPE = "linkType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(OFFERSZONEIMAGE, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(DEFAULTBANNER, AttributeMode.INITIAL);
		tmp.put(LINKTYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public CMSImageComponent getDefaultBanner(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public CMSImageComponent getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final CMSImageComponent value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.linkType</code> attribute.
	 * @return the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public EnumerationValue getLinkType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, LINKTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.linkType</code> attribute.
	 * @return the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public EnumerationValue getLinkType()
	{
		return getLinkType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.linkType</code> attribute. 
	 * @param value the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public void setLinkType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, LINKTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.linkType</code> attribute. 
	 * @param value the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public void setLinkType(final EnumerationValue value)
	{
		setLinkType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.offersZoneImage</code> attribute.
	 * @return the offersZoneImage - Image to display
	 */
	public CMSImageComponent getOffersZoneImage(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, OFFERSZONEIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.offersZoneImage</code> attribute.
	 * @return the offersZoneImage - Image to display
	 */
	public CMSImageComponent getOffersZoneImage()
	{
		return getOffersZoneImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.offersZoneImage</code> attribute. 
	 * @param value the offersZoneImage - Image to display
	 */
	public void setOffersZoneImage(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, OFFERSZONEIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.offersZoneImage</code> attribute. 
	 * @param value the offersZoneImage - Image to display
	 */
	public void setOffersZoneImage(final CMSImageComponent value)
	{
		setOffersZoneImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOffersZoneCMSComponent.getText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText()
	{
		return getAllText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOffersZoneCMSComponent.setText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final Map<Language,String> value)
	{
		setAllText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersZoneCMSComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL()
	{
		return getURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersZoneCMSComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final String value)
	{
		setURL( getSession().getSessionContext(), value );
	}
	
}
