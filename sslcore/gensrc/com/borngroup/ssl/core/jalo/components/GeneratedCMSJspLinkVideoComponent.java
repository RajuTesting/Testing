/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.JspIncludeComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.CMSJspLinkVideoComponent CMSJspLinkVideoComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCMSJspLinkVideoComponent extends JspIncludeComponent
{
	/** Qualifier of the <code>CMSJspLinkVideoComponent.link</code> attribute **/
	public static final String LINK = "link";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(JspIncludeComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LINK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSJspLinkVideoComponent.link</code> attribute.
	 * @return the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public String getLink(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSJspLinkVideoComponent.getLink requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, LINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSJspLinkVideoComponent.link</code> attribute.
	 * @return the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public String getLink()
	{
		return getLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSJspLinkVideoComponent.link</code> attribute. 
	 * @return the localized link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public Map<Language,String> getAllLink(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,LINK,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSJspLinkVideoComponent.link</code> attribute. 
	 * @return the localized link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public Map<Language,String> getAllLink()
	{
		return getAllLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSJspLinkVideoComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setLink(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSJspLinkVideoComponent.setLink requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSJspLinkVideoComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setLink(final String value)
	{
		setLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSJspLinkVideoComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setAllLink(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSJspLinkVideoComponent.link</code> attribute. 
	 * @param value the link - Attribute that stores the localized link of the
	 *                             paragraph.
	 */
	public void setAllLink(final Map<Language,String> value)
	{
		setAllLink( getSession().getSessionContext(), value );
	}
	
}
