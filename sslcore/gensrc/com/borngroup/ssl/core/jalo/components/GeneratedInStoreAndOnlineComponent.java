/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.InStoreAndOnlineComponent InStoreAndOnlineComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedInStoreAndOnlineComponent extends CMSImageComponent
{
	/** Qualifier of the <code>InStoreAndOnlineComponent.header</code> attribute **/
	public static final String HEADER = "header";
	/** Qualifier of the <code>InStoreAndOnlineComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>InStoreAndOnlineComponent.URL</code> attribute **/
	public static final String URL = "URL";
	/** Qualifier of the <code>InStoreAndOnlineComponent.linkUrl</code> attribute **/
	public static final String LINKURL = "linkUrl";
	/** Qualifier of the <code>InStoreAndOnlineComponent.linkText</code> attribute **/
	public static final String LINKTEXT = "linkText";
	/** Qualifier of the <code>InStoreAndOnlineComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADER, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(LINKURL, AttributeMode.INITIAL);
		tmp.put(LINKTEXT, AttributeMode.INITIAL);
		tmp.put(DEFAULTBANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedInStoreAndOnlineComponent.getDefaultBanner requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,DEFAULTBANNER,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner()
	{
		return getAllDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedInStoreAndOnlineComponent.setDefaultBanner requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final Media value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final Map<Language,Media> value)
	{
		setAllDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.header</code> attribute.
	 * @return the header - Text to display for the image
	 */
	public String getHeader(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedInStoreAndOnlineComponent.getHeader requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.header</code> attribute.
	 * @return the header - Text to display for the image
	 */
	public String getHeader()
	{
		return getHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.header</code> attribute. 
	 * @return the localized header - Text to display for the image
	 */
	public Map<Language,String> getAllHeader(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADER,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.header</code> attribute. 
	 * @return the localized header - Text to display for the image
	 */
	public Map<Language,String> getAllHeader()
	{
		return getAllHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.header</code> attribute. 
	 * @param value the header - Text to display for the image
	 */
	public void setHeader(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedInStoreAndOnlineComponent.setHeader requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.header</code> attribute. 
	 * @param value the header - Text to display for the image
	 */
	public void setHeader(final String value)
	{
		setHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.header</code> attribute. 
	 * @param value the header - Text to display for the image
	 */
	public void setAllHeader(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.header</code> attribute. 
	 * @param value the header - Text to display for the image
	 */
	public void setAllHeader(final Map<Language,String> value)
	{
		setAllHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.linkText</code> attribute.
	 * @return the linkText - Url link for the image
	 */
	public String getLinkText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.linkText</code> attribute.
	 * @return the linkText - Url link for the image
	 */
	public String getLinkText()
	{
		return getLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.linkText</code> attribute. 
	 * @param value the linkText - Url link for the image
	 */
	public void setLinkText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.linkText</code> attribute. 
	 * @param value the linkText - Url link for the image
	 */
	public void setLinkText(final String value)
	{
		setLinkText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.linkUrl</code> attribute.
	 * @return the linkUrl - Url link for the image
	 */
	public String getLinkUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.linkUrl</code> attribute.
	 * @return the linkUrl - Url link for the image
	 */
	public String getLinkUrl()
	{
		return getLinkUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.linkUrl</code> attribute. 
	 * @param value the linkUrl - Url link for the image
	 */
	public void setLinkUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.linkUrl</code> attribute. 
	 * @param value the linkUrl - Url link for the image
	 */
	public void setLinkUrl(final String value)
	{
		setLinkUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedInStoreAndOnlineComponent.getText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText()
	{
		return getAllText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedInStoreAndOnlineComponent.setText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final Map<Language,String> value)
	{
		setAllText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InStoreAndOnlineComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL()
	{
		return getURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InStoreAndOnlineComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final String value)
	{
		setURL( getSession().getSessionContext(), value );
	}
	
}
