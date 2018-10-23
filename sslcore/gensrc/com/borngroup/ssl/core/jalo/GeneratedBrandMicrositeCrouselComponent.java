/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.BrandMicrositeDetailsComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.BrandMicrositeCrouselComponent BrandMicrositeCrouselComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBrandMicrositeCrouselComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>BrandMicrositeCrouselComponent.brandImage</code> attribute **/
	public static final String BRANDIMAGE = "brandImage";
	/** Qualifier of the <code>BrandMicrositeCrouselComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>BrandMicrositeCrouselComponent.URL</code> attribute **/
	public static final String URL = "URL";
	/** Qualifier of the <code>BrandMicrositeCrouselComponent.brandCategory</code> attribute **/
	public static final String BRANDCATEGORY = "brandCategory";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BRANDIMAGE, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(BRANDCATEGORY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMicrositeCrouselComponent.brandCategory</code> attribute.
	 * @return the brandCategory
	 */
	public List<BrandMicrositeDetailsComponent> getBrandCategory(final SessionContext ctx)
	{
		List<BrandMicrositeDetailsComponent> coll = (List<BrandMicrositeDetailsComponent>)getProperty( ctx, BRANDCATEGORY);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMicrositeCrouselComponent.brandCategory</code> attribute.
	 * @return the brandCategory
	 */
	public List<BrandMicrositeDetailsComponent> getBrandCategory()
	{
		return getBrandCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMicrositeCrouselComponent.brandCategory</code> attribute. 
	 * @param value the brandCategory
	 */
	public void setBrandCategory(final SessionContext ctx, final List<BrandMicrositeDetailsComponent> value)
	{
		setProperty(ctx, BRANDCATEGORY,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMicrositeCrouselComponent.brandCategory</code> attribute. 
	 * @param value the brandCategory
	 */
	public void setBrandCategory(final List<BrandMicrositeDetailsComponent> value)
	{
		setBrandCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMicrositeCrouselComponent.brandImage</code> attribute.
	 * @return the brandImage - Image to display
	 */
	public CMSImageComponent getBrandImage(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, BRANDIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMicrositeCrouselComponent.brandImage</code> attribute.
	 * @return the brandImage - Image to display
	 */
	public CMSImageComponent getBrandImage()
	{
		return getBrandImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMicrositeCrouselComponent.brandImage</code> attribute. 
	 * @param value the brandImage - Image to display
	 */
	public void setBrandImage(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, BRANDIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMicrositeCrouselComponent.brandImage</code> attribute. 
	 * @param value the brandImage - Image to display
	 */
	public void setBrandImage(final CMSImageComponent value)
	{
		setBrandImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMicrositeCrouselComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandMicrositeCrouselComponent.getText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMicrositeCrouselComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMicrositeCrouselComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMicrositeCrouselComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText()
	{
		return getAllText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMicrositeCrouselComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandMicrositeCrouselComponent.setText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMicrositeCrouselComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMicrositeCrouselComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMicrositeCrouselComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final Map<Language,String> value)
	{
		setAllText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMicrositeCrouselComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMicrositeCrouselComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL()
	{
		return getURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMicrositeCrouselComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMicrositeCrouselComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final String value)
	{
		setURL( getSession().getSessionContext(), value );
	}
	
}
