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
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLImageLinkComponent SSLImageLinkComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLImageLinkComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLImageLinkComponent.media</code> attribute **/
	public static final String MEDIA = "media";
	/** Qualifier of the <code>SSLImageLinkComponent.activeFrom</code> attribute **/
	public static final String ACTIVEFROM = "activeFrom";
	/** Qualifier of the <code>SSLImageLinkComponent.activeUntil</code> attribute **/
	public static final String ACTIVEUNTIL = "activeUntil";
	/** Qualifier of the <code>SSLImageLinkComponent.rollOverText</code> attribute **/
	public static final String ROLLOVERTEXT = "rollOverText";
	/** Qualifier of the <code>SSLImageLinkComponent.link</code> attribute **/
	public static final String LINK = "link";
	/** Qualifier of the <code>SSLImageLinkComponent.linkType</code> attribute **/
	public static final String LINKTYPE = "linkType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(MEDIA, AttributeMode.INITIAL);
		tmp.put(ACTIVEFROM, AttributeMode.INITIAL);
		tmp.put(ACTIVEUNTIL, AttributeMode.INITIAL);
		tmp.put(ROLLOVERTEXT, AttributeMode.INITIAL);
		tmp.put(LINK, AttributeMode.INITIAL);
		tmp.put(LINKTYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEFROM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom()
	{
		return getActiveFrom( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEFROM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final Date value)
	{
		setActiveFrom( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEUNTIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil()
	{
		return getActiveUntil( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEUNTIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final Date value)
	{
		setActiveUntil( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.link</code> attribute.
	 * @return the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public String getLink(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSLImageLinkComponent.getLink requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, LINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.link</code> attribute.
	 * @return the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public String getLink()
	{
		return getLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.link</code> attribute. 
	 * @return the localized link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public Map<Language,String> getAllLink(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,LINK,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.link</code> attribute. 
	 * @return the localized link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public Map<Language,String> getAllLink()
	{
		return getAllLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setLink(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSLImageLinkComponent.setLink requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setLink(final String value)
	{
		setLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setAllLink(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setAllLink(final Map<Language,String> value)
	{
		setAllLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.linkType</code> attribute.
	 * @return the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public EnumerationValue getLinkType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, LINKTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.linkType</code> attribute.
	 * @return the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public EnumerationValue getLinkType()
	{
		return getLinkType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.linkType</code> attribute. 
	 * @param value the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public void setLinkType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, LINKTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.linkType</code> attribute. 
	 * @param value the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public void setLinkType(final EnumerationValue value)
	{
		setLinkType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.media</code> attribute.
	 * @return the media
	 */
	public Media getMedia(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSLImageLinkComponent.getMedia requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, MEDIA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.media</code> attribute.
	 * @return the media
	 */
	public Media getMedia()
	{
		return getMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.media</code> attribute. 
	 * @return the localized media
	 */
	public Map<Language,Media> getAllMedia(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,MEDIA,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.media</code> attribute. 
	 * @return the localized media
	 */
	public Map<Language,Media> getAllMedia()
	{
		return getAllMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.media</code> attribute. 
	 * @param value the media
	 */
	public void setMedia(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSLImageLinkComponent.setMedia requires a session language", 0 );
		}
		setLocalizedProperty(ctx, MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.media</code> attribute. 
	 * @param value the media
	 */
	public void setMedia(final Media value)
	{
		setMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.media</code> attribute. 
	 * @param value the media
	 */
	public void setAllMedia(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.media</code> attribute. 
	 * @param value the media
	 */
	public void setAllMedia(final Map<Language,Media> value)
	{
		setAllMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.rollOverText</code> attribute.
	 * @return the rollOverText - Attribute that stores the roll over text value.
	 */
	public String getRollOverText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ROLLOVERTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageLinkComponent.rollOverText</code> attribute.
	 * @return the rollOverText - Attribute that stores the roll over text value.
	 */
	public String getRollOverText()
	{
		return getRollOverText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.rollOverText</code> attribute. 
	 * @param value the rollOverText - Attribute that stores the roll over text value.
	 */
	public void setRollOverText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ROLLOVERTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageLinkComponent.rollOverText</code> attribute. 
	 * @param value the rollOverText - Attribute that stores the roll over text value.
	 */
	public void setRollOverText(final String value)
	{
		setRollOverText( getSession().getSessionContext(), value );
	}
	
}
