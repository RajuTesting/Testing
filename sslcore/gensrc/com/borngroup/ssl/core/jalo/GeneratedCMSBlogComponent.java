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
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent CMSBlogComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCMSBlogComponent extends CMSParagraphComponent
{
	/** Qualifier of the <code>CMSBlogComponent.media</code> attribute **/
	public static final String MEDIA = "media";
	/** Qualifier of the <code>CMSBlogComponent.mediaList</code> attribute **/
	public static final String MEDIALIST = "mediaList";
	/** Qualifier of the <code>CMSBlogComponent.url</code> attribute **/
	public static final String URL = "url";
	/** Qualifier of the <code>CMSBlogComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>CMSBlogComponent.date</code> attribute **/
	public static final String DATE = "date";
	/** Qualifier of the <code>CMSBlogComponent.time</code> attribute **/
	public static final String TIME = "time";
	/** Qualifier of the <code>CMSBlogComponent.linkText</code> attribute **/
	public static final String LINKTEXT = "linkText";
	/** Qualifier of the <code>CMSBlogComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSParagraphComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(MEDIA, AttributeMode.INITIAL);
		tmp.put(MEDIALIST, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(DATE, AttributeMode.INITIAL);
		tmp.put(TIME, AttributeMode.INITIAL);
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
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.date</code> attribute.
	 * @return the date
	 */
	public String getDate(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.date</code> attribute.
	 * @return the date
	 */
	public String getDate()
	{
		return getDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.date</code> attribute. 
	 * @param value the date
	 */
	public void setDate(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.date</code> attribute. 
	 * @param value the date
	 */
	public void setDate(final String value)
	{
		setDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSBlogComponent.getDefaultBanner requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,DEFAULTBANNER,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner()
	{
		return getAllDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSBlogComponent.setDefaultBanner requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final Media value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final Map<Language,Media> value)
	{
		setAllDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.heading</code> attribute.
	 * @return the heading
	 */
	public String getHeading(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.heading</code> attribute.
	 * @return the heading
	 */
	public String getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.heading</code> attribute. 
	 * @param value the heading
	 */
	public void setHeading(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.heading</code> attribute. 
	 * @param value the heading
	 */
	public void setHeading(final String value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.linkText</code> attribute.
	 * @return the linkText
	 */
	public String getLinkText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.linkText</code> attribute.
	 * @return the linkText
	 */
	public String getLinkText()
	{
		return getLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.linkText</code> attribute. 
	 * @param value the linkText
	 */
	public void setLinkText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.linkText</code> attribute. 
	 * @param value the linkText
	 */
	public void setLinkText(final String value)
	{
		setLinkText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.media</code> attribute.
	 * @return the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Media getMedia(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSBlogComponent.getMedia requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, MEDIA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.media</code> attribute.
	 * @return the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Media getMedia()
	{
		return getMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.media</code> attribute. 
	 * @return the localized media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,Media> getAllMedia(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,MEDIA,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.media</code> attribute. 
	 * @return the localized media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,Media> getAllMedia()
	{
		return getAllMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setMedia(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSBlogComponent.setMedia requires a session language", 0 );
		}
		setLocalizedProperty(ctx, MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setMedia(final Media value)
	{
		setMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllMedia(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllMedia(final Map<Language,Media> value)
	{
		setAllMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.mediaList</code> attribute.
	 * @return the mediaList
	 */
	public List<Media> getMediaList(final SessionContext ctx)
	{
		List<Media> coll = (List<Media>)getProperty( ctx, MEDIALIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.mediaList</code> attribute.
	 * @return the mediaList
	 */
	public List<Media> getMediaList()
	{
		return getMediaList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.mediaList</code> attribute. 
	 * @param value the mediaList
	 */
	public void setMediaList(final SessionContext ctx, final List<Media> value)
	{
		setProperty(ctx, MEDIALIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.mediaList</code> attribute. 
	 * @param value the mediaList
	 */
	public void setMediaList(final List<Media> value)
	{
		setMediaList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.time</code> attribute.
	 * @return the time
	 */
	public String getTime(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.time</code> attribute.
	 * @return the time
	 */
	public String getTime()
	{
		return getTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.time</code> attribute. 
	 * @param value the time
	 */
	public void setTime(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.time</code> attribute. 
	 * @param value the time
	 */
	public void setTime(final String value)
	{
		setTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.url</code> attribute.
	 * @return the url
	 */
	public String getUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSBlogComponent.url</code> attribute.
	 * @return the url
	 */
	public String getUrl()
	{
		return getUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.url</code> attribute. 
	 * @param value the url
	 */
	public void setUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSBlogComponent.url</code> attribute. 
	 * @param value the url
	 */
	public void setUrl(final String value)
	{
		setUrl( getSession().getSessionContext(), value );
	}
	
}
