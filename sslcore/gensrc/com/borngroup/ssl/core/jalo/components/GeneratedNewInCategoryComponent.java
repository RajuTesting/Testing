/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleBannerComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.NewInCategoryComponent NewInCategoryComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedNewInCategoryComponent extends CMSImageComponent
{
	/** Qualifier of the <code>NewInCategoryComponent.URL</code> attribute **/
	public static final String URL = "URL";
	/** Qualifier of the <code>NewInCategoryComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>NewInCategoryComponent.header</code> attribute **/
	public static final String HEADER = "header";
	/** Qualifier of the <code>NewInCategoryComponent.linkText</code> attribute **/
	public static final String LINKTEXT = "linkText";
	/** Qualifier of the <code>NewInCategoryComponent.linkUrl</code> attribute **/
	public static final String LINKURL = "linkUrl";
	/** Qualifier of the <code>NewInCategoryComponent.banners</code> attribute **/
	public static final String BANNERS = "banners";
	/** Qualifier of the <code>NewInCategoryComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(HEADER, AttributeMode.INITIAL);
		tmp.put(LINKTEXT, AttributeMode.INITIAL);
		tmp.put(LINKURL, AttributeMode.INITIAL);
		tmp.put(BANNERS, AttributeMode.INITIAL);
		tmp.put(DEFAULTBANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.banners</code> attribute.
	 * @return the banners - List Of Banners
	 */
	public List<SimpleBannerComponent> getBanners(final SessionContext ctx)
	{
		List<SimpleBannerComponent> coll = (List<SimpleBannerComponent>)getProperty( ctx, BANNERS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.banners</code> attribute.
	 * @return the banners - List Of Banners
	 */
	public List<SimpleBannerComponent> getBanners()
	{
		return getBanners( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.banners</code> attribute. 
	 * @param value the banners - List Of Banners
	 */
	public void setBanners(final SessionContext ctx, final List<SimpleBannerComponent> value)
	{
		setProperty(ctx, BANNERS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.banners</code> attribute. 
	 * @param value the banners - List Of Banners
	 */
	public void setBanners(final List<SimpleBannerComponent> value)
	{
		setBanners( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public SimpleBannerComponent getDefaultBanner(final SessionContext ctx)
	{
		return (SimpleBannerComponent)getProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public SimpleBannerComponent getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final SimpleBannerComponent value)
	{
		setProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SimpleBannerComponent value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.header</code> attribute.
	 * @return the header - Url link for the image
	 */
	public String getHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.header</code> attribute.
	 * @return the header - Url link for the image
	 */
	public String getHeader()
	{
		return getHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.header</code> attribute. 
	 * @param value the header - Url link for the image
	 */
	public void setHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.header</code> attribute. 
	 * @param value the header - Url link for the image
	 */
	public void setHeader(final String value)
	{
		setHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.linkText</code> attribute.
	 * @return the linkText - Url link for the image
	 */
	public String getLinkText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.linkText</code> attribute.
	 * @return the linkText - Url link for the image
	 */
	public String getLinkText()
	{
		return getLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.linkText</code> attribute. 
	 * @param value the linkText - Url link for the image
	 */
	public void setLinkText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.linkText</code> attribute. 
	 * @param value the linkText - Url link for the image
	 */
	public void setLinkText(final String value)
	{
		setLinkText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.linkUrl</code> attribute.
	 * @return the linkUrl - Url link for the image
	 */
	public String getLinkUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.linkUrl</code> attribute.
	 * @return the linkUrl - Url link for the image
	 */
	public String getLinkUrl()
	{
		return getLinkUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.linkUrl</code> attribute. 
	 * @param value the linkUrl - Url link for the image
	 */
	public void setLinkUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.linkUrl</code> attribute. 
	 * @param value the linkUrl - Url link for the image
	 */
	public void setLinkUrl(final String value)
	{
		setLinkUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.text</code> attribute.
	 * @return the text - Url link for the image
	 */
	public String getText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.text</code> attribute.
	 * @return the text - Url link for the image
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.text</code> attribute. 
	 * @param value the text - Url link for the image
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.text</code> attribute. 
	 * @param value the text - Url link for the image
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewInCategoryComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL()
	{
		return getURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewInCategoryComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final String value)
	{
		setURL( getSession().getSessionContext(), value );
	}
	
}
