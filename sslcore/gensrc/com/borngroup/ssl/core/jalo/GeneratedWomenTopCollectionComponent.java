/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.WomenTopCollectionComponent WomenTopCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedWomenTopCollectionComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>WomenTopCollectionComponent.brandImage</code> attribute **/
	public static final String BRANDIMAGE = "brandImage";
	/** Qualifier of the <code>WomenTopCollectionComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>WomenTopCollectionComponent.URL</code> attribute **/
	public static final String URL = "URL";
	/** Qualifier of the <code>WomenTopCollectionComponent.linkText</code> attribute **/
	public static final String LINKTEXT = "linkText";
	/** Qualifier of the <code>WomenTopCollectionComponent.linkUrl</code> attribute **/
	public static final String LINKURL = "linkUrl";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BRANDIMAGE, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(LINKTEXT, AttributeMode.INITIAL);
		tmp.put(LINKURL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenTopCollectionComponent.brandImage</code> attribute.
	 * @return the brandImage - Image to display
	 */
	public CMSImageComponent getBrandImage(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, BRANDIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenTopCollectionComponent.brandImage</code> attribute.
	 * @return the brandImage - Image to display
	 */
	public CMSImageComponent getBrandImage()
	{
		return getBrandImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenTopCollectionComponent.brandImage</code> attribute. 
	 * @param value the brandImage - Image to display
	 */
	public void setBrandImage(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, BRANDIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenTopCollectionComponent.brandImage</code> attribute. 
	 * @param value the brandImage - Image to display
	 */
	public void setBrandImage(final CMSImageComponent value)
	{
		setBrandImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenTopCollectionComponent.linkText</code> attribute.
	 * @return the linkText - Url link for the image
	 */
	public String getLinkText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenTopCollectionComponent.linkText</code> attribute.
	 * @return the linkText - Url link for the image
	 */
	public String getLinkText()
	{
		return getLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenTopCollectionComponent.linkText</code> attribute. 
	 * @param value the linkText - Url link for the image
	 */
	public void setLinkText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenTopCollectionComponent.linkText</code> attribute. 
	 * @param value the linkText - Url link for the image
	 */
	public void setLinkText(final String value)
	{
		setLinkText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenTopCollectionComponent.linkUrl</code> attribute.
	 * @return the linkUrl - Url link for the image
	 */
	public String getLinkUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenTopCollectionComponent.linkUrl</code> attribute.
	 * @return the linkUrl - Url link for the image
	 */
	public String getLinkUrl()
	{
		return getLinkUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenTopCollectionComponent.linkUrl</code> attribute. 
	 * @param value the linkUrl - Url link for the image
	 */
	public void setLinkUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenTopCollectionComponent.linkUrl</code> attribute. 
	 * @param value the linkUrl - Url link for the image
	 */
	public void setLinkUrl(final String value)
	{
		setLinkUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenTopCollectionComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public CMSParagraphComponent getText(final SessionContext ctx)
	{
		return (CMSParagraphComponent)getProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenTopCollectionComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public CMSParagraphComponent getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenTopCollectionComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final SessionContext ctx, final CMSParagraphComponent value)
	{
		setProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenTopCollectionComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final CMSParagraphComponent value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenTopCollectionComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenTopCollectionComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL()
	{
		return getURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenTopCollectionComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenTopCollectionComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final String value)
	{
		setURL( getSession().getSessionContext(), value );
	}
	
}
