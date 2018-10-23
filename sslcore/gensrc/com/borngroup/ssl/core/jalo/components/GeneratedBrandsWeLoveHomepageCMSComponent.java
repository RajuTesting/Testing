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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.BrandsWeLoveHomepageCMSComponent BrandsWeLoveHomepageCMSComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBrandsWeLoveHomepageCMSComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>BrandsWeLoveHomepageCMSComponent.brandsWeLoveImage</code> attribute **/
	public static final String BRANDSWELOVEIMAGE = "brandsWeLoveImage";
	/** Qualifier of the <code>BrandsWeLoveHomepageCMSComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>BrandsWeLoveHomepageCMSComponent.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>BrandsWeLoveHomepageCMSComponent.buttonText</code> attribute **/
	public static final String BUTTONTEXT = "buttonText";
	/** Qualifier of the <code>BrandsWeLoveHomepageCMSComponent.buttonUrl</code> attribute **/
	public static final String BUTTONURL = "buttonUrl";
	/** Qualifier of the <code>BrandsWeLoveHomepageCMSComponent.URL</code> attribute **/
	public static final String URL = "URL";
	/** Qualifier of the <code>BrandsWeLoveHomepageCMSComponent.activeFrom</code> attribute **/
	public static final String ACTIVEFROM = "activeFrom";
	/** Qualifier of the <code>BrandsWeLoveHomepageCMSComponent.activeUntil</code> attribute **/
	public static final String ACTIVEUNTIL = "activeUntil";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BRANDSWELOVEIMAGE, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(BUTTONTEXT, AttributeMode.INITIAL);
		tmp.put(BUTTONURL, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(ACTIVEFROM, AttributeMode.INITIAL);
		tmp.put(ACTIVEUNTIL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEFROM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom()
	{
		return getActiveFrom( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEFROM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final Date value)
	{
		setActiveFrom( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEUNTIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil()
	{
		return getActiveUntil( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEUNTIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final Date value)
	{
		setActiveUntil( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.brandsWeLoveImage</code> attribute.
	 * @return the brandsWeLoveImage - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Media getBrandsWeLoveImage(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandsWeLoveHomepageCMSComponent.getBrandsWeLoveImage requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, BRANDSWELOVEIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.brandsWeLoveImage</code> attribute.
	 * @return the brandsWeLoveImage - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Media getBrandsWeLoveImage()
	{
		return getBrandsWeLoveImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.brandsWeLoveImage</code> attribute. 
	 * @return the localized brandsWeLoveImage - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,Media> getAllBrandsWeLoveImage(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,BRANDSWELOVEIMAGE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.brandsWeLoveImage</code> attribute. 
	 * @return the localized brandsWeLoveImage - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public Map<Language,Media> getAllBrandsWeLoveImage()
	{
		return getAllBrandsWeLoveImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.brandsWeLoveImage</code> attribute. 
	 * @param value the brandsWeLoveImage - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setBrandsWeLoveImage(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandsWeLoveHomepageCMSComponent.setBrandsWeLoveImage requires a session language", 0 );
		}
		setLocalizedProperty(ctx, BRANDSWELOVEIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.brandsWeLoveImage</code> attribute. 
	 * @param value the brandsWeLoveImage - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setBrandsWeLoveImage(final Media value)
	{
		setBrandsWeLoveImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.brandsWeLoveImage</code> attribute. 
	 * @param value the brandsWeLoveImage - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllBrandsWeLoveImage(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,BRANDSWELOVEIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.brandsWeLoveImage</code> attribute. 
	 * @param value the brandsWeLoveImage - Attribute that stores the localized media of the
	 *                             paragraph.
	 */
	public void setAllBrandsWeLoveImage(final Map<Language,Media> value)
	{
		setAllBrandsWeLoveImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.buttonText</code> attribute.
	 * @return the buttonText - Button Text to display for the image
	 */
	public String getButtonText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandsWeLoveHomepageCMSComponent.getButtonText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, BUTTONTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.buttonText</code> attribute.
	 * @return the buttonText - Button Text to display for the image
	 */
	public String getButtonText()
	{
		return getButtonText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.buttonText</code> attribute. 
	 * @return the localized buttonText - Button Text to display for the image
	 */
	public Map<Language,String> getAllButtonText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,BUTTONTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.buttonText</code> attribute. 
	 * @return the localized buttonText - Button Text to display for the image
	 */
	public Map<Language,String> getAllButtonText()
	{
		return getAllButtonText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.buttonText</code> attribute. 
	 * @param value the buttonText - Button Text to display for the image
	 */
	public void setButtonText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandsWeLoveHomepageCMSComponent.setButtonText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, BUTTONTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.buttonText</code> attribute. 
	 * @param value the buttonText - Button Text to display for the image
	 */
	public void setButtonText(final String value)
	{
		setButtonText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.buttonText</code> attribute. 
	 * @param value the buttonText - Button Text to display for the image
	 */
	public void setAllButtonText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,BUTTONTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.buttonText</code> attribute. 
	 * @param value the buttonText - Button Text to display for the image
	 */
	public void setAllButtonText(final Map<Language,String> value)
	{
		setAllButtonText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.buttonUrl</code> attribute.
	 * @return the buttonUrl - Button url to display for the image
	 */
	public String getButtonUrl(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandsWeLoveHomepageCMSComponent.getButtonUrl requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, BUTTONURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.buttonUrl</code> attribute.
	 * @return the buttonUrl - Button url to display for the image
	 */
	public String getButtonUrl()
	{
		return getButtonUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.buttonUrl</code> attribute. 
	 * @return the localized buttonUrl - Button url to display for the image
	 */
	public Map<Language,String> getAllButtonUrl(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,BUTTONURL,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.buttonUrl</code> attribute. 
	 * @return the localized buttonUrl - Button url to display for the image
	 */
	public Map<Language,String> getAllButtonUrl()
	{
		return getAllButtonUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.buttonUrl</code> attribute. 
	 * @param value the buttonUrl - Button url to display for the image
	 */
	public void setButtonUrl(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandsWeLoveHomepageCMSComponent.setButtonUrl requires a session language", 0 );
		}
		setLocalizedProperty(ctx, BUTTONURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.buttonUrl</code> attribute. 
	 * @param value the buttonUrl - Button url to display for the image
	 */
	public void setButtonUrl(final String value)
	{
		setButtonUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.buttonUrl</code> attribute. 
	 * @param value the buttonUrl - Button url to display for the image
	 */
	public void setAllButtonUrl(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,BUTTONURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.buttonUrl</code> attribute. 
	 * @param value the buttonUrl - Button url to display for the image
	 */
	public void setAllButtonUrl(final Map<Language,String> value)
	{
		setAllButtonUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.description</code> attribute.
	 * @return the description - Text to display for the image
	 */
	public String getDescription(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandsWeLoveHomepageCMSComponent.getDescription requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.description</code> attribute.
	 * @return the description - Text to display for the image
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.description</code> attribute. 
	 * @return the localized description - Text to display for the image
	 */
	public Map<Language,String> getAllDescription(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,DESCRIPTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.description</code> attribute. 
	 * @return the localized description - Text to display for the image
	 */
	public Map<Language,String> getAllDescription()
	{
		return getAllDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.description</code> attribute. 
	 * @param value the description - Text to display for the image
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandsWeLoveHomepageCMSComponent.setDescription requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.description</code> attribute. 
	 * @param value the description - Text to display for the image
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.description</code> attribute. 
	 * @param value the description - Text to display for the image
	 */
	public void setAllDescription(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.description</code> attribute. 
	 * @param value the description - Text to display for the image
	 */
	public void setAllDescription(final Map<Language,String> value)
	{
		setAllDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandsWeLoveHomepageCMSComponent.getText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText()
	{
		return getAllText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandsWeLoveHomepageCMSComponent.setText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final Map<Language,String> value)
	{
		setAllText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveHomepageCMSComponent.URL</code> attribute.
	 * @return the URL - Url link for the image
	 */
	public String getURL()
	{
		return getURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveHomepageCMSComponent.URL</code> attribute. 
	 * @param value the URL - Url link for the image
	 */
	public void setURL(final String value)
	{
		setURL( getSession().getSessionContext(), value );
	}
	
}
