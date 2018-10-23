/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.StyleAndInspirationCMSComponent StyleAndInspirationCMSComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedStyleAndInspirationCMSComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>StyleAndInspirationCMSComponent.media</code> attribute **/
	public static final String MEDIA = "media";
	/** Qualifier of the <code>StyleAndInspirationCMSComponent.mediaList</code> attribute **/
	public static final String MEDIALIST = "mediaList";
	/** Qualifier of the <code>StyleAndInspirationCMSComponent.sideImageText</code> attribute **/
	public static final String SIDEIMAGETEXT = "sideImageText";
	/** Qualifier of the <code>StyleAndInspirationCMSComponent.bottomText</code> attribute **/
	public static final String BOTTOMTEXT = "bottomText";
	/** Qualifier of the <code>StyleAndInspirationCMSComponent.sideImageDescription</code> attribute **/
	public static final String SIDEIMAGEDESCRIPTION = "sideImageDescription";
	/** Qualifier of the <code>StyleAndInspirationCMSComponent.URL</code> attribute **/
	public static final String URL = "URL";
	/** Qualifier of the <code>StyleAndInspirationCMSComponent.mainImageUrl</code> attribute **/
	public static final String MAINIMAGEURL = "mainImageUrl";
	/** Qualifier of the <code>StyleAndInspirationCMSComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(MEDIA, AttributeMode.INITIAL);
		tmp.put(MEDIALIST, AttributeMode.INITIAL);
		tmp.put(SIDEIMAGETEXT, AttributeMode.INITIAL);
		tmp.put(BOTTOMTEXT, AttributeMode.INITIAL);
		tmp.put(SIDEIMAGEDESCRIPTION, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(MAINIMAGEURL, AttributeMode.INITIAL);
		tmp.put(DEFAULTBANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.bottomText</code> attribute.
	 * @return the bottomText - Text to display for the image
	 */
	public String getBottomText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStyleAndInspirationCMSComponent.getBottomText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, BOTTOMTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.bottomText</code> attribute.
	 * @return the bottomText - Text to display for the image
	 */
	public String getBottomText()
	{
		return getBottomText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.bottomText</code> attribute. 
	 * @return the localized bottomText - Text to display for the image
	 */
	public Map<Language,String> getAllBottomText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,BOTTOMTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.bottomText</code> attribute. 
	 * @return the localized bottomText - Text to display for the image
	 */
	public Map<Language,String> getAllBottomText()
	{
		return getAllBottomText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.bottomText</code> attribute. 
	 * @param value the bottomText - Text to display for the image
	 */
	public void setBottomText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStyleAndInspirationCMSComponent.setBottomText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, BOTTOMTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.bottomText</code> attribute. 
	 * @param value the bottomText - Text to display for the image
	 */
	public void setBottomText(final String value)
	{
		setBottomText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.bottomText</code> attribute. 
	 * @param value the bottomText - Text to display for the image
	 */
	public void setAllBottomText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,BOTTOMTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.bottomText</code> attribute. 
	 * @param value the bottomText - Text to display for the image
	 */
	public void setAllBottomText(final Map<Language,String> value)
	{
		setAllBottomText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStyleAndInspirationCMSComponent.getDefaultBanner requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,DEFAULTBANNER,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner()
	{
		return getAllDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStyleAndInspirationCMSComponent.setDefaultBanner requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final Media value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final Map<Language,Media> value)
	{
		setAllDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.mainImageUrl</code> attribute.
	 * @return the mainImageUrl - Url link for the image
	 */
	public String getMainImageUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MAINIMAGEURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.mainImageUrl</code> attribute.
	 * @return the mainImageUrl - Url link for the image
	 */
	public String getMainImageUrl()
	{
		return getMainImageUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.mainImageUrl</code> attribute. 
	 * @param value the mainImageUrl - Url link for the image
	 */
	public void setMainImageUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MAINIMAGEURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.mainImageUrl</code> attribute. 
	 * @param value the mainImageUrl - Url link for the image
	 */
	public void setMainImageUrl(final String value)
	{
		setMainImageUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.media</code> attribute.
	 * @return the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Media getMedia(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStyleAndInspirationCMSComponent.getMedia requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, MEDIA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.media</code> attribute.
	 * @return the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Media getMedia()
	{
		return getMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.media</code> attribute. 
	 * @return the localized media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,Media> getAllMedia(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,MEDIA,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.media</code> attribute. 
	 * @return the localized media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,Media> getAllMedia()
	{
		return getAllMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setMedia(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStyleAndInspirationCMSComponent.setMedia requires a session language", 0 );
		}
		setLocalizedProperty(ctx, MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setMedia(final Media value)
	{
		setMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllMedia(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllMedia(final Map<Language,Media> value)
	{
		setAllMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.mediaList</code> attribute.
	 * @return the mediaList
	 */
	public List<Media> getMediaList(final SessionContext ctx)
	{
		List<Media> coll = (List<Media>)getProperty( ctx, MEDIALIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.mediaList</code> attribute.
	 * @return the mediaList
	 */
	public List<Media> getMediaList()
	{
		return getMediaList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.mediaList</code> attribute. 
	 * @param value the mediaList
	 */
	public void setMediaList(final SessionContext ctx, final List<Media> value)
	{
		setProperty(ctx, MEDIALIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.mediaList</code> attribute. 
	 * @param value the mediaList
	 */
	public void setMediaList(final List<Media> value)
	{
		setMediaList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.sideImageDescription</code> attribute.
	 * @return the sideImageDescription - Text to display for the image
	 */
	public String getSideImageDescription(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStyleAndInspirationCMSComponent.getSideImageDescription requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, SIDEIMAGEDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.sideImageDescription</code> attribute.
	 * @return the sideImageDescription - Text to display for the image
	 */
	public String getSideImageDescription()
	{
		return getSideImageDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.sideImageDescription</code> attribute. 
	 * @return the localized sideImageDescription - Text to display for the image
	 */
	public Map<Language,String> getAllSideImageDescription(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,SIDEIMAGEDESCRIPTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.sideImageDescription</code> attribute. 
	 * @return the localized sideImageDescription - Text to display for the image
	 */
	public Map<Language,String> getAllSideImageDescription()
	{
		return getAllSideImageDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.sideImageDescription</code> attribute. 
	 * @param value the sideImageDescription - Text to display for the image
	 */
	public void setSideImageDescription(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStyleAndInspirationCMSComponent.setSideImageDescription requires a session language", 0 );
		}
		setLocalizedProperty(ctx, SIDEIMAGEDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.sideImageDescription</code> attribute. 
	 * @param value the sideImageDescription - Text to display for the image
	 */
	public void setSideImageDescription(final String value)
	{
		setSideImageDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.sideImageDescription</code> attribute. 
	 * @param value the sideImageDescription - Text to display for the image
	 */
	public void setAllSideImageDescription(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,SIDEIMAGEDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.sideImageDescription</code> attribute. 
	 * @param value the sideImageDescription - Text to display for the image
	 */
	public void setAllSideImageDescription(final Map<Language,String> value)
	{
		setAllSideImageDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.sideImageText</code> attribute.
	 * @return the sideImageText - Text to display for the image
	 */
	public String getSideImageText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStyleAndInspirationCMSComponent.getSideImageText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, SIDEIMAGETEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.sideImageText</code> attribute.
	 * @return the sideImageText - Text to display for the image
	 */
	public String getSideImageText()
	{
		return getSideImageText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.sideImageText</code> attribute. 
	 * @return the localized sideImageText - Text to display for the image
	 */
	public Map<Language,String> getAllSideImageText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,SIDEIMAGETEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.sideImageText</code> attribute. 
	 * @return the localized sideImageText - Text to display for the image
	 */
	public Map<Language,String> getAllSideImageText()
	{
		return getAllSideImageText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.sideImageText</code> attribute. 
	 * @param value the sideImageText - Text to display for the image
	 */
	public void setSideImageText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStyleAndInspirationCMSComponent.setSideImageText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, SIDEIMAGETEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.sideImageText</code> attribute. 
	 * @param value the sideImageText - Text to display for the image
	 */
	public void setSideImageText(final String value)
	{
		setSideImageText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.sideImageText</code> attribute. 
	 * @param value the sideImageText - Text to display for the image
	 */
	public void setAllSideImageText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,SIDEIMAGETEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.sideImageText</code> attribute. 
	 * @param value the sideImageText - Text to display for the image
	 */
	public void setAllSideImageText(final Map<Language,String> value)
	{
		setAllSideImageText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StyleAndInspirationCMSComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL()
	{
		return getURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StyleAndInspirationCMSComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final String value)
	{
		setURL( getSession().getSessionContext(), value );
	}
	
}
