/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleResponsiveBannerComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslImageMapComponent SslImageMapComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslImageMapComponent extends SimpleResponsiveBannerComponent
{
	/** Qualifier of the <code>SslImageMapComponent.imageMapHTML</code> attribute **/
	public static final String IMAGEMAPHTML = "imageMapHTML";
	/** Qualifier of the <code>SslImageMapComponent.headline</code> attribute **/
	public static final String HEADLINE = "headline";
	/** Qualifier of the <code>SslImageMapComponent.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>SslImageMapComponent.listLink</code> attribute **/
	public static final String LISTLINK = "listLink";
	/** Qualifier of the <code>SslImageMapComponent.additionalText</code> attribute **/
	public static final String ADDITIONALTEXT = "additionalText";
	/** Qualifier of the <code>SslImageMapComponent.activeFrom</code> attribute **/
	public static final String ACTIVEFROM = "activeFrom";
	/** Qualifier of the <code>SslImageMapComponent.activeUntil</code> attribute **/
	public static final String ACTIVEUNTIL = "activeUntil";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleResponsiveBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(IMAGEMAPHTML, AttributeMode.INITIAL);
		tmp.put(HEADLINE, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(LISTLINK, AttributeMode.INITIAL);
		tmp.put(ADDITIONALTEXT, AttributeMode.INITIAL);
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
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEFROM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom()
	{
		return getActiveFrom( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEFROM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final Date value)
	{
		setActiveFrom( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEUNTIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil()
	{
		return getActiveUntil( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEUNTIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final Date value)
	{
		setActiveUntil( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.additionalText</code> attribute.
	 * @return the additionalText - Text to display for the image
	 */
	public String getAdditionalText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslImageMapComponent.getAdditionalText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, ADDITIONALTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.additionalText</code> attribute.
	 * @return the additionalText - Text to display for the image
	 */
	public String getAdditionalText()
	{
		return getAdditionalText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.additionalText</code> attribute. 
	 * @return the localized additionalText - Text to display for the image
	 */
	public Map<Language,String> getAllAdditionalText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,ADDITIONALTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.additionalText</code> attribute. 
	 * @return the localized additionalText - Text to display for the image
	 */
	public Map<Language,String> getAllAdditionalText()
	{
		return getAllAdditionalText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.additionalText</code> attribute. 
	 * @param value the additionalText - Text to display for the image
	 */
	public void setAdditionalText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslImageMapComponent.setAdditionalText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, ADDITIONALTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.additionalText</code> attribute. 
	 * @param value the additionalText - Text to display for the image
	 */
	public void setAdditionalText(final String value)
	{
		setAdditionalText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.additionalText</code> attribute. 
	 * @param value the additionalText - Text to display for the image
	 */
	public void setAllAdditionalText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,ADDITIONALTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.additionalText</code> attribute. 
	 * @param value the additionalText - Text to display for the image
	 */
	public void setAllAdditionalText(final Map<Language,String> value)
	{
		setAllAdditionalText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.description</code> attribute.
	 * @return the description - Localized First Content of the component.
	 */
	public String getDescription(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslImageMapComponent.getDescription requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.description</code> attribute.
	 * @return the description - Localized First Content of the component.
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.description</code> attribute. 
	 * @return the localized description - Localized First Content of the component.
	 */
	public Map<Language,String> getAllDescription(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,DESCRIPTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.description</code> attribute. 
	 * @return the localized description - Localized First Content of the component.
	 */
	public Map<Language,String> getAllDescription()
	{
		return getAllDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.description</code> attribute. 
	 * @param value the description - Localized First Content of the component.
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslImageMapComponent.setDescription requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.description</code> attribute. 
	 * @param value the description - Localized First Content of the component.
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.description</code> attribute. 
	 * @param value the description - Localized First Content of the component.
	 */
	public void setAllDescription(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.description</code> attribute. 
	 * @param value the description - Localized First Content of the component.
	 */
	public void setAllDescription(final Map<Language,String> value)
	{
		setAllDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.headline</code> attribute.
	 * @return the headline - Text to display for the image
	 */
	public String getHeadline(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslImageMapComponent.getHeadline requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADLINE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.headline</code> attribute.
	 * @return the headline - Text to display for the image
	 */
	public String getHeadline()
	{
		return getHeadline( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.headline</code> attribute. 
	 * @return the localized headline - Text to display for the image
	 */
	public Map<Language,String> getAllHeadline(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADLINE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.headline</code> attribute. 
	 * @return the localized headline - Text to display for the image
	 */
	public Map<Language,String> getAllHeadline()
	{
		return getAllHeadline( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.headline</code> attribute. 
	 * @param value the headline - Text to display for the image
	 */
	public void setHeadline(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslImageMapComponent.setHeadline requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.headline</code> attribute. 
	 * @param value the headline - Text to display for the image
	 */
	public void setHeadline(final String value)
	{
		setHeadline( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.headline</code> attribute. 
	 * @param value the headline - Text to display for the image
	 */
	public void setAllHeadline(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.headline</code> attribute. 
	 * @param value the headline - Text to display for the image
	 */
	public void setAllHeadline(final Map<Language,String> value)
	{
		setAllHeadline( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.imageMapHTML</code> attribute.
	 * @return the imageMapHTML - It is localized HTML string that is displayed under
	 *                             the banner.
	 */
	public String getImageMapHTML(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslImageMapComponent.getImageMapHTML requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, IMAGEMAPHTML);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.imageMapHTML</code> attribute.
	 * @return the imageMapHTML - It is localized HTML string that is displayed under
	 *                             the banner.
	 */
	public String getImageMapHTML()
	{
		return getImageMapHTML( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.imageMapHTML</code> attribute. 
	 * @return the localized imageMapHTML - It is localized HTML string that is displayed under
	 *                             the banner.
	 */
	public Map<Language,String> getAllImageMapHTML(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,IMAGEMAPHTML,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.imageMapHTML</code> attribute. 
	 * @return the localized imageMapHTML - It is localized HTML string that is displayed under
	 *                             the banner.
	 */
	public Map<Language,String> getAllImageMapHTML()
	{
		return getAllImageMapHTML( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.imageMapHTML</code> attribute. 
	 * @param value the imageMapHTML - It is localized HTML string that is displayed under
	 *                             the banner.
	 */
	public void setImageMapHTML(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslImageMapComponent.setImageMapHTML requires a session language", 0 );
		}
		setLocalizedProperty(ctx, IMAGEMAPHTML,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.imageMapHTML</code> attribute. 
	 * @param value the imageMapHTML - It is localized HTML string that is displayed under
	 *                             the banner.
	 */
	public void setImageMapHTML(final String value)
	{
		setImageMapHTML( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.imageMapHTML</code> attribute. 
	 * @param value the imageMapHTML - It is localized HTML string that is displayed under
	 *                             the banner.
	 */
	public void setAllImageMapHTML(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,IMAGEMAPHTML,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.imageMapHTML</code> attribute. 
	 * @param value the imageMapHTML - It is localized HTML string that is displayed under
	 *                             the banner.
	 */
	public void setAllImageMapHTML(final Map<Language,String> value)
	{
		setAllImageMapHTML( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.listLink</code> attribute.
	 * @return the listLink - List of Link
	 */
	public List<CMSLinkComponent> getListLink(final SessionContext ctx)
	{
		List<CMSLinkComponent> coll = (List<CMSLinkComponent>)getProperty( ctx, LISTLINK);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageMapComponent.listLink</code> attribute.
	 * @return the listLink - List of Link
	 */
	public List<CMSLinkComponent> getListLink()
	{
		return getListLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.listLink</code> attribute. 
	 * @param value the listLink - List of Link
	 */
	public void setListLink(final SessionContext ctx, final List<CMSLinkComponent> value)
	{
		setProperty(ctx, LISTLINK,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageMapComponent.listLink</code> attribute. 
	 * @param value the listLink - List of Link
	 */
	public void setListLink(final List<CMSLinkComponent> value)
	{
		setListLink( getSession().getSessionContext(), value );
	}
	
}
