/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleBannerComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.PromotionBannerComponent PromotionBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionBannerComponent extends SimpleBannerComponent
{
	/** Qualifier of the <code>PromotionBannerComponent.header</code> attribute **/
	public static final String HEADER = "header";
	/** Qualifier of the <code>PromotionBannerComponent.URL</code> attribute **/
	public static final String URL = "URL";
	/** Qualifier of the <code>PromotionBannerComponent.URLText</code> attribute **/
	public static final String URLTEXT = "URLText";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADER, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(URLTEXT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBannerComponent.header</code> attribute.
	 * @return the header - Promotion Banner Header
	 */
	public String getHeader(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionBannerComponent.getHeader requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBannerComponent.header</code> attribute.
	 * @return the header - Promotion Banner Header
	 */
	public String getHeader()
	{
		return getHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBannerComponent.header</code> attribute. 
	 * @return the localized header - Promotion Banner Header
	 */
	public Map<Language,String> getAllHeader(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADER,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBannerComponent.header</code> attribute. 
	 * @return the localized header - Promotion Banner Header
	 */
	public Map<Language,String> getAllHeader()
	{
		return getAllHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBannerComponent.header</code> attribute. 
	 * @param value the header - Promotion Banner Header
	 */
	public void setHeader(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionBannerComponent.setHeader requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBannerComponent.header</code> attribute. 
	 * @param value the header - Promotion Banner Header
	 */
	public void setHeader(final String value)
	{
		setHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBannerComponent.header</code> attribute. 
	 * @param value the header - Promotion Banner Header
	 */
	public void setAllHeader(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBannerComponent.header</code> attribute. 
	 * @param value the header - Promotion Banner Header
	 */
	public void setAllHeader(final Map<Language,String> value)
	{
		setAllHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBannerComponent.URL</code> attribute.
	 * @return the URL - Link to navigate to
	 */
	public String getURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBannerComponent.URL</code> attribute.
	 * @return the URL - Link to navigate to
	 */
	public String getURL()
	{
		return getURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBannerComponent.URL</code> attribute. 
	 * @param value the URL - Link to navigate to
	 */
	public void setURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBannerComponent.URL</code> attribute. 
	 * @param value the URL - Link to navigate to
	 */
	public void setURL(final String value)
	{
		setURL( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBannerComponent.URLText</code> attribute.
	 * @return the URLText - Link to navigate to
	 */
	public String getURLText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URLTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBannerComponent.URLText</code> attribute.
	 * @return the URLText - Link to navigate to
	 */
	public String getURLText()
	{
		return getURLText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBannerComponent.URLText</code> attribute. 
	 * @param value the URLText - Link to navigate to
	 */
	public void setURLText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URLTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBannerComponent.URLText</code> attribute. 
	 * @param value the URLText - Link to navigate to
	 */
	public void setURLText(final String value)
	{
		setURLText( getSession().getSessionContext(), value );
	}
	
}
