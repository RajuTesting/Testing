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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.WomenCollectionComponent WomenCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedWomenCollectionComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>WomenCollectionComponent.brandImage</code> attribute **/
	public static final String BRANDIMAGE = "brandImage";
	/** Qualifier of the <code>WomenCollectionComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>WomenCollectionComponent.URL</code> attribute **/
	public static final String URL = "URL";
	/** Qualifier of the <code>WomenCollectionComponent.textUrl</code> attribute **/
	public static final String TEXTURL = "textUrl";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BRANDIMAGE, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(TEXTURL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenCollectionComponent.brandImage</code> attribute.
	 * @return the brandImage - Image to display
	 */
	public CMSImageComponent getBrandImage(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, BRANDIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenCollectionComponent.brandImage</code> attribute.
	 * @return the brandImage - Image to display
	 */
	public CMSImageComponent getBrandImage()
	{
		return getBrandImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenCollectionComponent.brandImage</code> attribute. 
	 * @param value the brandImage - Image to display
	 */
	public void setBrandImage(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, BRANDIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenCollectionComponent.brandImage</code> attribute. 
	 * @param value the brandImage - Image to display
	 */
	public void setBrandImage(final CMSImageComponent value)
	{
		setBrandImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenCollectionComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public CMSParagraphComponent getText(final SessionContext ctx)
	{
		return (CMSParagraphComponent)getProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenCollectionComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public CMSParagraphComponent getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenCollectionComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final SessionContext ctx, final CMSParagraphComponent value)
	{
		setProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenCollectionComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final CMSParagraphComponent value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenCollectionComponent.textUrl</code> attribute.
	 * @return the textUrl - Url link for the image
	 */
	public String getTextUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXTURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenCollectionComponent.textUrl</code> attribute.
	 * @return the textUrl - Url link for the image
	 */
	public String getTextUrl()
	{
		return getTextUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenCollectionComponent.textUrl</code> attribute. 
	 * @param value the textUrl - Url link for the image
	 */
	public void setTextUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXTURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenCollectionComponent.textUrl</code> attribute. 
	 * @param value the textUrl - Url link for the image
	 */
	public void setTextUrl(final String value)
	{
		setTextUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenCollectionComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WomenCollectionComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL()
	{
		return getURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenCollectionComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WomenCollectionComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final String value)
	{
		setURL( getSession().getSessionContext(), value );
	}
	
}
