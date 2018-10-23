/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SSLBrandNavigationComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLBrandNavigationCollectionComponent SSLBrandNavigationCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBrandNavigationCollectionComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLBrandNavigationCollectionComponent.components</code> attribute **/
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
	 * <i>Generated method</i> - Getter of the <code>SSLBrandNavigationCollectionComponent.components</code> attribute.
	 * @return the components - A collection of Brand navigation components
	 */
	public List<SSLBrandNavigationComponent> getComponents(final SessionContext ctx)
	{
		List<SSLBrandNavigationComponent> coll = (List<SSLBrandNavigationComponent>)getProperty( ctx, COMPONENTS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandNavigationCollectionComponent.components</code> attribute.
	 * @return the components - A collection of Brand navigation components
	 */
	public List<SSLBrandNavigationComponent> getComponents()
	{
		return getComponents( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandNavigationCollectionComponent.components</code> attribute. 
	 * @param value the components - A collection of Brand navigation components
	 */
	public void setComponents(final SessionContext ctx, final List<SSLBrandNavigationComponent> value)
	{
		setProperty(ctx, COMPONENTS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandNavigationCollectionComponent.components</code> attribute. 
	 * @param value the components - A collection of Brand navigation components
	 */
	public void setComponents(final List<SSLBrandNavigationComponent> value)
	{
		setComponents( getSession().getSessionContext(), value );
	}
	
}
