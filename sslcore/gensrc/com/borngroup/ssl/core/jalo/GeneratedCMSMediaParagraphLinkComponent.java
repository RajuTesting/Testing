/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.category.jalo.Category;
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
 * Generated class for type {@link de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent CMSMediaParagraphLinkComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCMSMediaParagraphLinkComponent extends CMSParagraphComponent
{
	/** Qualifier of the <code>CMSMediaParagraphLinkComponent.media</code> attribute **/
	public static final String MEDIA = "media";
	/** Qualifier of the <code>CMSMediaParagraphLinkComponent.url</code> attribute **/
	public static final String URL = "url";
	/** Qualifier of the <code>CMSMediaParagraphLinkComponent.linkName</code> attribute **/
	public static final String LINKNAME = "linkName";
	/** Qualifier of the <code>CMSMediaParagraphLinkComponent.appearInRow</code> attribute **/
	public static final String APPEARINROW = "appearInRow";
	/** Qualifier of the <code>CMSMediaParagraphLinkComponent.categoryList</code> attribute **/
	public static final String CATEGORYLIST = "categoryList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSParagraphComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(MEDIA, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(LINKNAME, AttributeMode.INITIAL);
		tmp.put(APPEARINROW, AttributeMode.INITIAL);
		tmp.put(CATEGORYLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.appearInRow</code> attribute.
	 * @return the appearInRow - Attribute that stores the place in which the
	 *                             component need to appear
	 */
	public String getAppearInRow(final SessionContext ctx)
	{
		return (String)getProperty( ctx, APPEARINROW);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.appearInRow</code> attribute.
	 * @return the appearInRow - Attribute that stores the place in which the
	 *                             component need to appear
	 */
	public String getAppearInRow()
	{
		return getAppearInRow( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.appearInRow</code> attribute. 
	 * @param value the appearInRow - Attribute that stores the place in which the
	 *                             component need to appear
	 */
	public void setAppearInRow(final SessionContext ctx, final String value)
	{
		setProperty(ctx, APPEARINROW,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.appearInRow</code> attribute. 
	 * @param value the appearInRow - Attribute that stores the place in which the
	 *                             component need to appear
	 */
	public void setAppearInRow(final String value)
	{
		setAppearInRow( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.categoryList</code> attribute.
	 * @return the categoryList - Attribute that stores the list of categories
	 */
	public List<Category> getCategoryList(final SessionContext ctx)
	{
		List<Category> coll = (List<Category>)getProperty( ctx, CATEGORYLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.categoryList</code> attribute.
	 * @return the categoryList - Attribute that stores the list of categories
	 */
	public List<Category> getCategoryList()
	{
		return getCategoryList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.categoryList</code> attribute. 
	 * @param value the categoryList - Attribute that stores the list of categories
	 */
	public void setCategoryList(final SessionContext ctx, final List<Category> value)
	{
		setProperty(ctx, CATEGORYLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.categoryList</code> attribute. 
	 * @param value the categoryList - Attribute that stores the list of categories
	 */
	public void setCategoryList(final List<Category> value)
	{
		setCategoryList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.linkName</code> attribute.
	 * @return the linkName - Attribute that stores the link name
	 */
	public String getLinkName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.linkName</code> attribute.
	 * @return the linkName - Attribute that stores the link name
	 */
	public String getLinkName()
	{
		return getLinkName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.linkName</code> attribute. 
	 * @param value the linkName - Attribute that stores the link name
	 */
	public void setLinkName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.linkName</code> attribute. 
	 * @param value the linkName - Attribute that stores the link name
	 */
	public void setLinkName(final String value)
	{
		setLinkName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.media</code> attribute.
	 * @return the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Media getMedia(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSMediaParagraphLinkComponent.getMedia requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, MEDIA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.media</code> attribute.
	 * @return the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Media getMedia()
	{
		return getMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.media</code> attribute. 
	 * @return the localized media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,Media> getAllMedia(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,MEDIA,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.media</code> attribute. 
	 * @return the localized media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,Media> getAllMedia()
	{
		return getAllMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setMedia(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSMediaParagraphLinkComponent.setMedia requires a session language", 0 );
		}
		setLocalizedProperty(ctx, MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setMedia(final Media value)
	{
		setMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllMedia(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.media</code> attribute. 
	 * @param value the media - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllMedia(final Map<Language,Media> value)
	{
		setAllMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.url</code> attribute.
	 * @return the url - Attribute that stores the url
	 */
	public String getUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkComponent.url</code> attribute.
	 * @return the url - Attribute that stores the url
	 */
	public String getUrl()
	{
		return getUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.url</code> attribute. 
	 * @param value the url - Attribute that stores the url
	 */
	public void setUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkComponent.url</code> attribute. 
	 * @param value the url - Attribute that stores the url
	 */
	public void setUrl(final String value)
	{
		setUrl( getSession().getSessionContext(), value );
	}
	
}
