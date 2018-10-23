/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.PromotionDealComponent PromotionDealComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionDealComponent extends CMSParagraphComponent
{
	/** Qualifier of the <code>PromotionDealComponent.logo</code> attribute **/
	public static final String LOGO = "logo";
	/** Qualifier of the <code>PromotionDealComponent.header</code> attribute **/
	public static final String HEADER = "header";
	/** Qualifier of the <code>PromotionDealComponent.link</code> attribute **/
	public static final String LINK = "link";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSParagraphComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LOGO, AttributeMode.INITIAL);
		tmp.put(HEADER, AttributeMode.INITIAL);
		tmp.put(LINK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionDealComponent.header</code> attribute.
	 * @return the header
	 */
	public String getHeader(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionDealComponent.getHeader requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionDealComponent.header</code> attribute.
	 * @return the header
	 */
	public String getHeader()
	{
		return getHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionDealComponent.header</code> attribute. 
	 * @return the localized header
	 */
	public Map<Language,String> getAllHeader(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADER,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionDealComponent.header</code> attribute. 
	 * @return the localized header
	 */
	public Map<Language,String> getAllHeader()
	{
		return getAllHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionDealComponent.header</code> attribute. 
	 * @param value the header
	 */
	public void setHeader(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionDealComponent.setHeader requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionDealComponent.header</code> attribute. 
	 * @param value the header
	 */
	public void setHeader(final String value)
	{
		setHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionDealComponent.header</code> attribute. 
	 * @param value the header
	 */
	public void setAllHeader(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionDealComponent.header</code> attribute. 
	 * @param value the header
	 */
	public void setAllHeader(final Map<Language,String> value)
	{
		setAllHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionDealComponent.link</code> attribute.
	 * @return the link
	 */
	public CMSLinkComponent getLink(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, LINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionDealComponent.link</code> attribute.
	 * @return the link
	 */
	public CMSLinkComponent getLink()
	{
		return getLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionDealComponent.link</code> attribute. 
	 * @param value the link
	 */
	public void setLink(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionDealComponent.link</code> attribute. 
	 * @param value the link
	 */
	public void setLink(final CMSLinkComponent value)
	{
		setLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionDealComponent.logo</code> attribute.
	 * @return the logo
	 */
	public Media getLogo(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionDealComponent.getLogo requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, LOGO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionDealComponent.logo</code> attribute.
	 * @return the logo
	 */
	public Media getLogo()
	{
		return getLogo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionDealComponent.logo</code> attribute. 
	 * @return the localized logo
	 */
	public Map<Language,Media> getAllLogo(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,LOGO,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionDealComponent.logo</code> attribute. 
	 * @return the localized logo
	 */
	public Map<Language,Media> getAllLogo()
	{
		return getAllLogo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionDealComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setLogo(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionDealComponent.setLogo requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LOGO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionDealComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setLogo(final Media value)
	{
		setLogo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionDealComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setAllLogo(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,LOGO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionDealComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setAllLogo(final Map<Language,Media> value)
	{
		setAllLogo( getSession().getSessionContext(), value );
	}
	
}
