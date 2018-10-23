/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLPDPSimpleCMSTabComponent SSLPDPSimpleCMSTabComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLPDPSimpleCMSTabComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLPDPSimpleCMSTabComponent.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>SSLPDPSimpleCMSTabComponent.tabComponent</code> attribute **/
	public static final String TABCOMPONENT = "tabComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TITLE, AttributeMode.INITIAL);
		tmp.put(TABCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPDPSimpleCMSTabComponent.tabComponent</code> attribute.
	 * @return the tabComponent
	 */
	public SimpleCMSComponent getTabComponent(final SessionContext ctx)
	{
		return (SimpleCMSComponent)getProperty( ctx, TABCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPDPSimpleCMSTabComponent.tabComponent</code> attribute.
	 * @return the tabComponent
	 */
	public SimpleCMSComponent getTabComponent()
	{
		return getTabComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPDPSimpleCMSTabComponent.tabComponent</code> attribute. 
	 * @param value the tabComponent
	 */
	public void setTabComponent(final SessionContext ctx, final SimpleCMSComponent value)
	{
		setProperty(ctx, TABCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPDPSimpleCMSTabComponent.tabComponent</code> attribute. 
	 * @param value the tabComponent
	 */
	public void setTabComponent(final SimpleCMSComponent value)
	{
		setTabComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPDPSimpleCMSTabComponent.title</code> attribute.
	 * @return the title
	 */
	public String getTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPDPSimpleCMSTabComponent.title</code> attribute.
	 * @return the title
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPDPSimpleCMSTabComponent.title</code> attribute. 
	 * @param value the title
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPDPSimpleCMSTabComponent.title</code> attribute. 
	 * @param value the title
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
}
