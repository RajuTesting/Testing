/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLTextWidgetComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLTextWidgetListComponent SSLTextWidgetListComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLTextWidgetListComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLTextWidgetListComponent.components</code> attribute **/
	public static final String COMPONENTS = "components";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(COMPONENTS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTextWidgetListComponent.components</code> attribute.
	 * @return the components - For editing the text
	 */
	public List<SSLTextWidgetComponent> getComponents(final SessionContext ctx)
	{
		List<SSLTextWidgetComponent> coll = (List<SSLTextWidgetComponent>)getProperty( ctx, COMPONENTS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTextWidgetListComponent.components</code> attribute.
	 * @return the components - For editing the text
	 */
	public List<SSLTextWidgetComponent> getComponents()
	{
		return getComponents( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTextWidgetListComponent.components</code> attribute. 
	 * @param value the components - For editing the text
	 */
	public void setComponents(final SessionContext ctx, final List<SSLTextWidgetComponent> value)
	{
		setProperty(ctx, COMPONENTS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTextWidgetListComponent.components</code> attribute. 
	 * @param value the components - For editing the text
	 */
	public void setComponents(final List<SSLTextWidgetComponent> value)
	{
		setComponents( getSession().getSessionContext(), value );
	}
	
}
