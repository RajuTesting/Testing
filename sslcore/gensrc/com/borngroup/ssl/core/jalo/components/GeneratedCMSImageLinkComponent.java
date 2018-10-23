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
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.CMSImageLinkComponent CMSImageLinkComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCMSImageLinkComponent extends CMSImageComponent
{
	/** Qualifier of the <code>CMSImageLinkComponent.link</code> attribute **/
	public static final String LINK = "link";
	/** Qualifier of the <code>CMSImageLinkComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	/** Qualifier of the <code>CMSImageLinkComponent.linkType</code> attribute **/
	public static final String LINKTYPE = "linkType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LINK, AttributeMode.INITIAL);
		tmp.put(DEFAULTBANNER, AttributeMode.INITIAL);
		tmp.put(LINKTYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSImageLinkComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSImageLinkComponent.getDefaultBanner requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSImageLinkComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public Media getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSImageLinkComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,DEFAULTBANNER,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSImageLinkComponent.defaultBanner</code> attribute. 
	 * @return the localized defaultBanner
	 */
	public Map<Language,Media> getAllDefaultBanner()
	{
		return getAllDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSImageLinkComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSImageLinkComponent.setDefaultBanner requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSImageLinkComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final Media value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSImageLinkComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSImageLinkComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setAllDefaultBanner(final Map<Language,Media> value)
	{
		setAllDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSImageLinkComponent.link</code> attribute.
	 * @return the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public String getLink(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSImageLinkComponent.getLink requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, LINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSImageLinkComponent.link</code> attribute.
	 * @return the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public String getLink()
	{
		return getLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSImageLinkComponent.link</code> attribute. 
	 * @return the localized link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public Map<Language,String> getAllLink(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,LINK,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSImageLinkComponent.link</code> attribute. 
	 * @return the localized link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public Map<Language,String> getAllLink()
	{
		return getAllLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSImageLinkComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setLink(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSImageLinkComponent.setLink requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSImageLinkComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setLink(final String value)
	{
		setLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSImageLinkComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setAllLink(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSImageLinkComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setAllLink(final Map<Language,String> value)
	{
		setAllLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSImageLinkComponent.linkType</code> attribute.
	 * @return the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public EnumerationValue getLinkType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, LINKTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSImageLinkComponent.linkType</code> attribute.
	 * @return the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public EnumerationValue getLinkType()
	{
		return getLinkType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSImageLinkComponent.linkType</code> attribute. 
	 * @param value the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public void setLinkType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, LINKTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSImageLinkComponent.linkType</code> attribute. 
	 * @param value the linkType - Describe the Url type for Mobile
	 *                             Application(category/product/fcc)
	 */
	public void setLinkType(final EnumerationValue value)
	{
		setLinkType( getSession().getSessionContext(), value );
	}
	
}
