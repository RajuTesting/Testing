/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleBannerComponent;
import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.OurFavoritesBrandsComponent OurFavoritesBrandsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOurFavoritesBrandsComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>OurFavoritesBrandsComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>OurFavoritesBrandsComponent.banner</code> attribute **/
	public static final String BANNER = "banner";
	/** Qualifier of the <code>OurFavoritesBrandsComponent.customUrl</code> attribute **/
	public static final String CUSTOMURL = "customUrl";
	/** Qualifier of the <code>OurFavoritesBrandsComponent.categoryList</code> attribute **/
	public static final String CATEGORYLIST = "categoryList";
	/** Qualifier of the <code>OurFavoritesBrandsComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(BANNER, AttributeMode.INITIAL);
		tmp.put(CUSTOMURL, AttributeMode.INITIAL);
		tmp.put(CATEGORYLIST, AttributeMode.INITIAL);
		tmp.put(DEFAULTBANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.banner</code> attribute.
	 * @return the banner - Image to display for the link
	 */
	public SimpleBannerComponent getBanner(final SessionContext ctx)
	{
		return (SimpleBannerComponent)getProperty( ctx, BANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.banner</code> attribute.
	 * @return the banner - Image to display for the link
	 */
	public SimpleBannerComponent getBanner()
	{
		return getBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.banner</code> attribute. 
	 * @param value the banner - Image to display for the link
	 */
	public void setBanner(final SessionContext ctx, final SimpleBannerComponent value)
	{
		setProperty(ctx, BANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.banner</code> attribute. 
	 * @param value the banner - Image to display for the link
	 */
	public void setBanner(final SimpleBannerComponent value)
	{
		setBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.categoryList</code> attribute.
	 * @return the categoryList - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public List<Category> getCategoryList(final SessionContext ctx)
	{
		List<Category> coll = (List<Category>)getProperty( ctx, CATEGORYLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.categoryList</code> attribute.
	 * @return the categoryList - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public List<Category> getCategoryList()
	{
		return getCategoryList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.categoryList</code> attribute. 
	 * @param value the categoryList - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setCategoryList(final SessionContext ctx, final List<Category> value)
	{
		setProperty(ctx, CATEGORYLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.categoryList</code> attribute. 
	 * @param value the categoryList - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setCategoryList(final List<Category> value)
	{
		setCategoryList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.customUrl</code> attribute.
	 * @return the customUrl - Image to display for the link
	 */
	public List<String> getCustomUrl(final SessionContext ctx)
	{
		List<String> coll = (List<String>)getProperty( ctx, CUSTOMURL);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.customUrl</code> attribute.
	 * @return the customUrl - Image to display for the link
	 */
	public List<String> getCustomUrl()
	{
		return getCustomUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.customUrl</code> attribute. 
	 * @param value the customUrl - Image to display for the link
	 */
	public void setCustomUrl(final SessionContext ctx, final List<String> value)
	{
		setProperty(ctx, CUSTOMURL,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.customUrl</code> attribute. 
	 * @param value the customUrl - Image to display for the link
	 */
	public void setCustomUrl(final List<String> value)
	{
		setCustomUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner - Image to display for the link
	 */
	public SimpleBannerComponent getDefaultBanner(final SessionContext ctx)
	{
		return (SimpleBannerComponent)getProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner - Image to display for the link
	 */
	public SimpleBannerComponent getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner - Image to display for the link
	 */
	public void setDefaultBanner(final SessionContext ctx, final SimpleBannerComponent value)
	{
		setProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner - Image to display for the link
	 */
	public void setDefaultBanner(final SimpleBannerComponent value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.text</code> attribute.
	 * @return the text - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public String getText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOurFavoritesBrandsComponent.getText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.text</code> attribute.
	 * @return the text - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.text</code> attribute. 
	 * @return the localized text - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,String> getAllText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OurFavoritesBrandsComponent.text</code> attribute. 
	 * @return the localized text - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,String> getAllText()
	{
		return getAllText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.text</code> attribute. 
	 * @param value the text - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOurFavoritesBrandsComponent.setText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.text</code> attribute. 
	 * @param value the text - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.text</code> attribute. 
	 * @param value the text - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OurFavoritesBrandsComponent.text</code> attribute. 
	 * @param value the text - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllText(final Map<Language,String> value)
	{
		setAllText( getSession().getSessionContext(), value );
	}
	
}
