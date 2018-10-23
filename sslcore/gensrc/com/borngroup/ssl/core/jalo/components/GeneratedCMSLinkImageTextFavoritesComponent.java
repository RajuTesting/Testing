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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.CMSLinkImageTextFavoritesComponent CMSLinkImageTextFavoritesComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCMSLinkImageTextFavoritesComponent extends CMSImageComponent
{
	/** Qualifier of the <code>CMSLinkImageTextFavoritesComponent.imageUrl</code> attribute **/
	public static final String IMAGEURL = "imageUrl";
	/** Qualifier of the <code>CMSLinkImageTextFavoritesComponent.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>CMSLinkImageTextFavoritesComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>CMSLinkImageTextFavoritesComponent.linkText</code> attribute **/
	public static final String LINKTEXT = "linkText";
	/** Qualifier of the <code>CMSLinkImageTextFavoritesComponent.link</code> attribute **/
	public static final String LINK = "link";
	/** Qualifier of the <code>CMSLinkImageTextFavoritesComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(IMAGEURL, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(LINKTEXT, AttributeMode.INITIAL);
		tmp.put(LINK, AttributeMode.INITIAL);
		tmp.put(DEFAULTBANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSLinkImageTextFavoritesComponent.getDefaultBanner requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,DEFAULTBANNER,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner()
	{
		return getAllDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSLinkImageTextFavoritesComponent.setDefaultBanner requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final Media value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final Map<Language,Media> value)
	{
		setAllDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.description</code> attribute.
	 * @return the description - description for the image
	 */
	public String getDescription(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSLinkImageTextFavoritesComponent.getDescription requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.description</code> attribute.
	 * @return the description - description for the image
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.description</code> attribute. 
	 * @return the localized description - description for the image
	 */
	public Map<Language,String> getAllDescription(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,DESCRIPTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.description</code> attribute. 
	 * @return the localized description - description for the image
	 */
	public Map<Language,String> getAllDescription()
	{
		return getAllDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.description</code> attribute. 
	 * @param value the description - description for the image
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSLinkImageTextFavoritesComponent.setDescription requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.description</code> attribute. 
	 * @param value the description - description for the image
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.description</code> attribute. 
	 * @param value the description - description for the image
	 */
	public void setAllDescription(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.description</code> attribute. 
	 * @param value the description - description for the image
	 */
	public void setAllDescription(final Map<Language,String> value)
	{
		setAllDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.imageUrl</code> attribute.
	 * @return the imageUrl - Image to display for the link
	 */
	public String getImageUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, IMAGEURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.imageUrl</code> attribute.
	 * @return the imageUrl - Image to display for the link
	 */
	public String getImageUrl()
	{
		return getImageUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.imageUrl</code> attribute. 
	 * @param value the imageUrl - Image to display for the link
	 */
	public void setImageUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, IMAGEURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.imageUrl</code> attribute. 
	 * @param value the imageUrl - Image to display for the link
	 */
	public void setImageUrl(final String value)
	{
		setImageUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.link</code> attribute.
	 * @return the link - description for the image
	 */
	public String getLink(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSLinkImageTextFavoritesComponent.getLink requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, LINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.link</code> attribute.
	 * @return the link - description for the image
	 */
	public String getLink()
	{
		return getLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.link</code> attribute. 
	 * @return the localized link - description for the image
	 */
	public Map<Language,String> getAllLink(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,LINK,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.link</code> attribute. 
	 * @return the localized link - description for the image
	 */
	public Map<Language,String> getAllLink()
	{
		return getAllLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.link</code> attribute. 
	 * @param value the link - description for the image
	 */
	public void setLink(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSLinkImageTextFavoritesComponent.setLink requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.link</code> attribute. 
	 * @param value the link - description for the image
	 */
	public void setLink(final String value)
	{
		setLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.link</code> attribute. 
	 * @param value the link - description for the image
	 */
	public void setAllLink(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.link</code> attribute. 
	 * @param value the link - description for the image
	 */
	public void setAllLink(final Map<Language,String> value)
	{
		setAllLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.linkText</code> attribute.
	 * @return the linkText - description for the image
	 */
	public String getLinkText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSLinkImageTextFavoritesComponent.getLinkText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, LINKTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.linkText</code> attribute.
	 * @return the linkText - description for the image
	 */
	public String getLinkText()
	{
		return getLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.linkText</code> attribute. 
	 * @return the localized linkText - description for the image
	 */
	public Map<Language,String> getAllLinkText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,LINKTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.linkText</code> attribute. 
	 * @return the localized linkText - description for the image
	 */
	public Map<Language,String> getAllLinkText()
	{
		return getAllLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.linkText</code> attribute. 
	 * @param value the linkText - description for the image
	 */
	public void setLinkText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSLinkImageTextFavoritesComponent.setLinkText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.linkText</code> attribute. 
	 * @param value the linkText - description for the image
	 */
	public void setLinkText(final String value)
	{
		setLinkText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.linkText</code> attribute. 
	 * @param value the linkText - description for the image
	 */
	public void setAllLinkText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.linkText</code> attribute. 
	 * @param value the linkText - description for the image
	 */
	public void setAllLinkText(final Map<Language,String> value)
	{
		setAllLinkText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.text</code> attribute.
	 * @return the text - description for the image
	 */
	public String getText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSLinkImageTextFavoritesComponent.getText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.text</code> attribute.
	 * @return the text - description for the image
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.text</code> attribute. 
	 * @return the localized text - description for the image
	 */
	public Map<Language,String> getAllText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkImageTextFavoritesComponent.text</code> attribute. 
	 * @return the localized text - description for the image
	 */
	public Map<Language,String> getAllText()
	{
		return getAllText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.text</code> attribute. 
	 * @param value the text - description for the image
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSLinkImageTextFavoritesComponent.setText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.text</code> attribute. 
	 * @param value the text - description for the image
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.text</code> attribute. 
	 * @param value the text - description for the image
	 */
	public void setAllText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkImageTextFavoritesComponent.text</code> attribute. 
	 * @param value the text - description for the image
	 */
	public void setAllText(final Map<Language,String> value)
	{
		setAllText( getSession().getSessionContext(), value );
	}
	
}
