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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslDiscoverBlogUpdateParentComponent SslDynamicListComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslDiscoverBlogUpdateParentComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SslDynamicListComponent.style</code> attribute **/
	public static final String STYLE = "style";
	/** Qualifier of the <code>SslDynamicListComponent.headerLabel</code> attribute **/
	public static final String HEADERLABEL = "headerLabel";
	/** Qualifier of the <code>SslDynamicListComponent.componentList</code> attribute **/
	public static final String COMPONENTLIST = "componentList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(STYLE, AttributeMode.INITIAL);
		tmp.put(HEADERLABEL, AttributeMode.INITIAL);
		tmp.put(COMPONENTLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslDynamicListComponent.componentList</code> attribute.
	 * @return the componentList - List of components for unordered list
	 */
	public List<SimpleCMSComponent> getComponentList(final SessionContext ctx)
	{
		List<SimpleCMSComponent> coll = (List<SimpleCMSComponent>)getProperty( ctx, COMPONENTLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslDynamicListComponent.componentList</code> attribute.
	 * @return the componentList - List of components for unordered list
	 */
	public List<SimpleCMSComponent> getComponentList()
	{
		return getComponentList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslDynamicListComponent.componentList</code> attribute. 
	 * @param value the componentList - List of components for unordered list
	 */
	public void setComponentList(final SessionContext ctx, final List<SimpleCMSComponent> value)
	{
		setProperty(ctx, COMPONENTLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslDynamicListComponent.componentList</code> attribute. 
	 * @param value the componentList - List of components for unordered list
	 */
	public void setComponentList(final List<SimpleCMSComponent> value)
	{
		setComponentList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslDynamicListComponent.headerLabel</code> attribute.
	 * @return the headerLabel - Header including HTML
	 */
	public String getHeaderLabel(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslDiscoverBlogUpdateParentComponent.getHeaderLabel requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADERLABEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslDynamicListComponent.headerLabel</code> attribute.
	 * @return the headerLabel - Header including HTML
	 */
	public String getHeaderLabel()
	{
		return getHeaderLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslDynamicListComponent.headerLabel</code> attribute. 
	 * @return the localized headerLabel - Header including HTML
	 */
	public Map<Language,String> getAllHeaderLabel(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADERLABEL,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslDynamicListComponent.headerLabel</code> attribute. 
	 * @return the localized headerLabel - Header including HTML
	 */
	public Map<Language,String> getAllHeaderLabel()
	{
		return getAllHeaderLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslDynamicListComponent.headerLabel</code> attribute. 
	 * @param value the headerLabel - Header including HTML
	 */
	public void setHeaderLabel(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslDiscoverBlogUpdateParentComponent.setHeaderLabel requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADERLABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslDynamicListComponent.headerLabel</code> attribute. 
	 * @param value the headerLabel - Header including HTML
	 */
	public void setHeaderLabel(final String value)
	{
		setHeaderLabel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslDynamicListComponent.headerLabel</code> attribute. 
	 * @param value the headerLabel - Header including HTML
	 */
	public void setAllHeaderLabel(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADERLABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslDynamicListComponent.headerLabel</code> attribute. 
	 * @param value the headerLabel - Header including HTML
	 */
	public void setAllHeaderLabel(final Map<Language,String> value)
	{
		setAllHeaderLabel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslDynamicListComponent.style</code> attribute.
	 * @return the style - Ampersand separated CSS classes for nested div
	 */
	public String getStyle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STYLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslDynamicListComponent.style</code> attribute.
	 * @return the style - Ampersand separated CSS classes for nested div
	 */
	public String getStyle()
	{
		return getStyle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslDynamicListComponent.style</code> attribute. 
	 * @param value the style - Ampersand separated CSS classes for nested div
	 */
	public void setStyle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STYLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslDynamicListComponent.style</code> attribute. 
	 * @param value the style - Ampersand separated CSS classes for nested div
	 */
	public void setStyle(final String value)
	{
		setStyle( getSession().getSessionContext(), value );
	}
	
}
