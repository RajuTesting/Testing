/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLMobileWidgetComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLBannerWidgetComponent SSLBannerWidgetComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBannerWidgetComponent extends SSLMobileWidgetComponent
{
	/** Qualifier of the <code>SSLBannerWidgetComponent.linkComponent</code> attribute **/
	public static final String LINKCOMPONENT = "linkComponent";
	/** Qualifier of the <code>SSLBannerWidgetComponent.media</code> attribute **/
	public static final String MEDIA = "media";
	/** Qualifier of the <code>SSLBannerWidgetComponent.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SSLMobileWidgetComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LINKCOMPONENT, AttributeMode.INITIAL);
		tmp.put(MEDIA, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetComponent.description</code> attribute.
	 * @return the description - Description for Banner
	 */
	public String getDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetComponent.description</code> attribute.
	 * @return the description - Description for Banner
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetComponent.description</code> attribute. 
	 * @param value the description - Description for Banner
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetComponent.description</code> attribute. 
	 * @param value the description - Description for Banner
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetComponent.linkComponent</code> attribute.
	 * @return the linkComponent - Link
	 */
	public CMSLinkComponent getLinkComponent(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, LINKCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetComponent.linkComponent</code> attribute.
	 * @return the linkComponent - Link
	 */
	public CMSLinkComponent getLinkComponent()
	{
		return getLinkComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetComponent.linkComponent</code> attribute. 
	 * @param value the linkComponent - Link
	 */
	public void setLinkComponent(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, LINKCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetComponent.linkComponent</code> attribute. 
	 * @param value the linkComponent - Link
	 */
	public void setLinkComponent(final CMSLinkComponent value)
	{
		setLinkComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetComponent.media</code> attribute.
	 * @return the media - Media
	 */
	public Media getMedia(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSLBannerWidgetComponent.getMedia requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, MEDIA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetComponent.media</code> attribute.
	 * @return the media - Media
	 */
	public Media getMedia()
	{
		return getMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetComponent.media</code> attribute. 
	 * @return the localized media - Media
	 */
	public Map<Language,Media> getAllMedia(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,MEDIA,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetComponent.media</code> attribute. 
	 * @return the localized media - Media
	 */
	public Map<Language,Media> getAllMedia()
	{
		return getAllMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetComponent.media</code> attribute. 
	 * @param value the media - Media
	 */
	public void setMedia(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSLBannerWidgetComponent.setMedia requires a session language", 0 );
		}
		setLocalizedProperty(ctx, MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetComponent.media</code> attribute. 
	 * @param value the media - Media
	 */
	public void setMedia(final Media value)
	{
		setMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetComponent.media</code> attribute. 
	 * @param value the media - Media
	 */
	public void setAllMedia(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetComponent.media</code> attribute. 
	 * @param value the media - Media
	 */
	public void setAllMedia(final Map<Language,Media> value)
	{
		setAllMedia( getSession().getSessionContext(), value );
	}
	
}
