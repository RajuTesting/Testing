/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
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
 * Generated class for type {@link de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent CMSMediaParagraphComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCMSMediaParagraphComponent extends CMSParagraphComponent
{
	/** Qualifier of the <code>CMSMediaParagraphComponent.media</code> attribute **/
	public static final String MEDIA = "media";
	/** Qualifier of the <code>CMSMediaParagraphComponent.url</code> attribute **/
	public static final String URL = "url";
	/** Qualifier of the <code>CMSMediaParagraphComponent.style</code> attribute **/
	public static final String STYLE = "style";
	/** Qualifier of the <code>CMSMediaParagraphComponent.linkname</code> attribute **/
	public static final String LINKNAME = "linkname";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSParagraphComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(MEDIA, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(STYLE, AttributeMode.INITIAL);
		tmp.put(LINKNAME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.linkname</code> attribute.
	 * @return the linkname
	 */
	public String getLinkname(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSMediaParagraphComponent.getLinkname requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, LINKNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.linkname</code> attribute.
	 * @return the linkname
	 */
	public String getLinkname()
	{
		return getLinkname( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.linkname</code> attribute. 
	 * @return the localized linkname
	 */
	public Map<Language,String> getAllLinkname(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,LINKNAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.linkname</code> attribute. 
	 * @return the localized linkname
	 */
	public Map<Language,String> getAllLinkname()
	{
		return getAllLinkname( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.linkname</code> attribute. 
	 * @param value the linkname
	 */
	public void setLinkname(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSMediaParagraphComponent.setLinkname requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LINKNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.linkname</code> attribute. 
	 * @param value the linkname
	 */
	public void setLinkname(final String value)
	{
		setLinkname( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.linkname</code> attribute. 
	 * @param value the linkname
	 */
	public void setAllLinkname(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,LINKNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.linkname</code> attribute. 
	 * @param value the linkname
	 */
	public void setAllLinkname(final Map<Language,String> value)
	{
		setAllLinkname( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.media</code> attribute.
	 * @return the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Media getMedia(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSMediaParagraphComponent.getMedia requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, MEDIA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.media</code> attribute.
	 * @return the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Media getMedia()
	{
		return getMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.media</code> attribute. 
	 * @return the localized media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,Media> getAllMedia(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,MEDIA,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.media</code> attribute. 
	 * @return the localized media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,Media> getAllMedia()
	{
		return getAllMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setMedia(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSMediaParagraphComponent.setMedia requires a session language", 0 );
		}
		setLocalizedProperty(ctx, MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setMedia(final Media value)
	{
		setMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllMedia(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllMedia(final Map<Language,Media> value)
	{
		setAllMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.style</code> attribute.
	 * @return the style
	 */
	public String getStyle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STYLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.style</code> attribute.
	 * @return the style
	 */
	public String getStyle()
	{
		return getStyle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.style</code> attribute. 
	 * @param value the style
	 */
	public void setStyle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STYLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.style</code> attribute. 
	 * @param value the style
	 */
	public void setStyle(final String value)
	{
		setStyle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.url</code> attribute.
	 * @return the url
	 */
	public String getUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphComponent.url</code> attribute.
	 * @return the url
	 */
	public String getUrl()
	{
		return getUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.url</code> attribute. 
	 * @param value the url
	 */
	public void setUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphComponent.url</code> attribute. 
	 * @param value the url
	 */
	public void setUrl(final String value)
	{
		setUrl( getSession().getSessionContext(), value );
	}
	
}
