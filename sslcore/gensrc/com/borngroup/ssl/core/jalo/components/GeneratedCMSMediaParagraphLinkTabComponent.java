/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.CMSMediaParagraphLinkComponent;
import de.hybris.platform.acceleratorcms.jalo.components.CMSTabParagraphComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.CMSMediaParagraphLinkTabComponent CMSMediaParagraphLinkTabComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCMSMediaParagraphLinkTabComponent extends CMSMediaParagraphLinkComponent
{
	/** Qualifier of the <code>CMSMediaParagraphLinkTabComponent.tabs</code> attribute **/
	public static final String TABS = "tabs";
	/** Qualifier of the <code>CMSMediaParagraphLinkTabComponent.tabHeadline</code> attribute **/
	public static final String TABHEADLINE = "tabHeadline";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSMediaParagraphLinkComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TABS, AttributeMode.INITIAL);
		tmp.put(TABHEADLINE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkTabComponent.tabHeadline</code> attribute.
	 * @return the tabHeadline - Tab headline for tab component for instant gifting
	 *                             paragraph.
	 */
	public String getTabHeadline(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSMediaParagraphLinkTabComponent.getTabHeadline requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TABHEADLINE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkTabComponent.tabHeadline</code> attribute.
	 * @return the tabHeadline - Tab headline for tab component for instant gifting
	 *                             paragraph.
	 */
	public String getTabHeadline()
	{
		return getTabHeadline( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkTabComponent.tabHeadline</code> attribute. 
	 * @return the localized tabHeadline - Tab headline for tab component for instant gifting
	 *                             paragraph.
	 */
	public Map<Language,String> getAllTabHeadline(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TABHEADLINE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkTabComponent.tabHeadline</code> attribute. 
	 * @return the localized tabHeadline - Tab headline for tab component for instant gifting
	 *                             paragraph.
	 */
	public Map<Language,String> getAllTabHeadline()
	{
		return getAllTabHeadline( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkTabComponent.tabHeadline</code> attribute. 
	 * @param value the tabHeadline - Tab headline for tab component for instant gifting
	 *                             paragraph.
	 */
	public void setTabHeadline(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCMSMediaParagraphLinkTabComponent.setTabHeadline requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TABHEADLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkTabComponent.tabHeadline</code> attribute. 
	 * @param value the tabHeadline - Tab headline for tab component for instant gifting
	 *                             paragraph.
	 */
	public void setTabHeadline(final String value)
	{
		setTabHeadline( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkTabComponent.tabHeadline</code> attribute. 
	 * @param value the tabHeadline - Tab headline for tab component for instant gifting
	 *                             paragraph.
	 */
	public void setAllTabHeadline(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TABHEADLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkTabComponent.tabHeadline</code> attribute. 
	 * @param value the tabHeadline - Tab headline for tab component for instant gifting
	 *                             paragraph.
	 */
	public void setAllTabHeadline(final Map<Language,String> value)
	{
		setAllTabHeadline( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkTabComponent.tabs</code> attribute.
	 * @return the tabs - Tab component for instant gifting paragraph.
	 */
	public List<CMSTabParagraphComponent> getTabs(final SessionContext ctx)
	{
		List<CMSTabParagraphComponent> coll = (List<CMSTabParagraphComponent>)getProperty( ctx, TABS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphLinkTabComponent.tabs</code> attribute.
	 * @return the tabs - Tab component for instant gifting paragraph.
	 */
	public List<CMSTabParagraphComponent> getTabs()
	{
		return getTabs( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkTabComponent.tabs</code> attribute. 
	 * @param value the tabs - Tab component for instant gifting paragraph.
	 */
	public void setTabs(final SessionContext ctx, final List<CMSTabParagraphComponent> value)
	{
		setProperty(ctx, TABS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphLinkTabComponent.tabs</code> attribute. 
	 * @param value the tabs - Tab component for instant gifting paragraph.
	 */
	public void setTabs(final List<CMSTabParagraphComponent> value)
	{
		setTabs( getSession().getSessionContext(), value );
	}
	
}
