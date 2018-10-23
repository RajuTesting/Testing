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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslExclusiveBrandsComponent SslExclusiveBrandsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslExclusiveBrandsComponent extends SimpleBannerComponent
{
	/** Qualifier of the <code>SslExclusiveBrandsComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>SslExclusiveBrandsComponent.link</code> attribute **/
	public static final String LINK = "link";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(LINK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslExclusiveBrandsComponent.heading</code> attribute.
	 * @return the heading - Localized heading of the component.
	 */
	public String getHeading(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslExclusiveBrandsComponent.getHeading requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslExclusiveBrandsComponent.heading</code> attribute.
	 * @return the heading - Localized heading of the component.
	 */
	public String getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslExclusiveBrandsComponent.heading</code> attribute. 
	 * @return the localized heading - Localized heading of the component.
	 */
	public Map<Language,String> getAllHeading(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADING,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslExclusiveBrandsComponent.heading</code> attribute. 
	 * @return the localized heading - Localized heading of the component.
	 */
	public Map<Language,String> getAllHeading()
	{
		return getAllHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslExclusiveBrandsComponent.heading</code> attribute. 
	 * @param value the heading - Localized heading of the component.
	 */
	public void setHeading(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslExclusiveBrandsComponent.setHeading requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslExclusiveBrandsComponent.heading</code> attribute. 
	 * @param value the heading - Localized heading of the component.
	 */
	public void setHeading(final String value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslExclusiveBrandsComponent.heading</code> attribute. 
	 * @param value the heading - Localized heading of the component.
	 */
	public void setAllHeading(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslExclusiveBrandsComponent.heading</code> attribute. 
	 * @param value the heading - Localized heading of the component.
	 */
	public void setAllHeading(final Map<Language,String> value)
	{
		setAllHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslExclusiveBrandsComponent.link</code> attribute.
	 * @return the link - Link of the component.
	 */
	public CMSLinkComponent getLink(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, LINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslExclusiveBrandsComponent.link</code> attribute.
	 * @return the link - Link of the component.
	 */
	public CMSLinkComponent getLink()
	{
		return getLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslExclusiveBrandsComponent.link</code> attribute. 
	 * @param value the link - Link of the component.
	 */
	public void setLink(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslExclusiveBrandsComponent.link</code> attribute. 
	 * @param value the link - Link of the component.
	 */
	public void setLink(final CMSLinkComponent value)
	{
		setLink( getSession().getSessionContext(), value );
	}
	
}
