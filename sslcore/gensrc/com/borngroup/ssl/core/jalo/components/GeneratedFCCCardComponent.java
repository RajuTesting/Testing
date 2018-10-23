/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleBannerComponent;
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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.FCCCardComponent FCCMainNavComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedFCCCardComponent extends SimpleBannerComponent
{
	/** Qualifier of the <code>FCCMainNavComponent.content</code> attribute **/
	public static final String CONTENT = "content";
	/** Qualifier of the <code>FCCMainNavComponent.link</code> attribute **/
	public static final String LINK = "link";
	/** Qualifier of the <code>FCCMainNavComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CONTENT, AttributeMode.INITIAL);
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
	 * <i>Generated method</i> - Getter of the <code>FCCMainNavComponent.content</code> attribute.
	 * @return the content - Localized content of the component.
	 */
	public String getContent(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedFCCCardComponent.getContent requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, CONTENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FCCMainNavComponent.content</code> attribute.
	 * @return the content - Localized content of the component.
	 */
	public String getContent()
	{
		return getContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FCCMainNavComponent.content</code> attribute. 
	 * @return the localized content - Localized content of the component.
	 */
	public Map<Language,String> getAllContent(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,CONTENT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FCCMainNavComponent.content</code> attribute. 
	 * @return the localized content - Localized content of the component.
	 */
	public Map<Language,String> getAllContent()
	{
		return getAllContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMainNavComponent.content</code> attribute. 
	 * @param value the content - Localized content of the component.
	 */
	public void setContent(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedFCCCardComponent.setContent requires a session language", 0 );
		}
		setLocalizedProperty(ctx, CONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMainNavComponent.content</code> attribute. 
	 * @param value the content - Localized content of the component.
	 */
	public void setContent(final String value)
	{
		setContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMainNavComponent.content</code> attribute. 
	 * @param value the content - Localized content of the component.
	 */
	public void setAllContent(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,CONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMainNavComponent.content</code> attribute. 
	 * @param value the content - Localized content of the component.
	 */
	public void setAllContent(final Map<Language,String> value)
	{
		setAllContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FCCMainNavComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedFCCCardComponent.getDefaultBanner requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FCCMainNavComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FCCMainNavComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,DEFAULTBANNER,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FCCMainNavComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner()
	{
		return getAllDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMainNavComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedFCCCardComponent.setDefaultBanner requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMainNavComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final Media value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMainNavComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMainNavComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final Map<Language,Media> value)
	{
		setAllDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FCCMainNavComponent.link</code> attribute.
	 * @return the link - Localized link of the component.
	 */
	public CMSLinkComponent getLink(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, LINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FCCMainNavComponent.link</code> attribute.
	 * @return the link - Localized link of the component.
	 */
	public CMSLinkComponent getLink()
	{
		return getLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMainNavComponent.link</code> attribute. 
	 * @param value the link - Localized link of the component.
	 */
	public void setLink(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMainNavComponent.link</code> attribute. 
	 * @param value the link - Localized link of the component.
	 */
	public void setLink(final CMSLinkComponent value)
	{
		setLink( getSession().getSessionContext(), value );
	}
	
}
