/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SSLImageRollOverSubComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLImageRollOverComponent SSLImageRollOverComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLImageRollOverComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLImageRollOverComponent.webComponent</code> attribute **/
	public static final String WEBCOMPONENT = "webComponent";
	/** Qualifier of the <code>SSLImageRollOverComponent.responsiveComponent</code> attribute **/
	public static final String RESPONSIVECOMPONENT = "responsiveComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(WEBCOMPONENT, AttributeMode.INITIAL);
		tmp.put(RESPONSIVECOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverComponent.responsiveComponent</code> attribute.
	 * @return the responsiveComponent
	 */
	public SSLImageRollOverSubComponent getResponsiveComponent(final SessionContext ctx)
	{
		return (SSLImageRollOverSubComponent)getProperty( ctx, RESPONSIVECOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverComponent.responsiveComponent</code> attribute.
	 * @return the responsiveComponent
	 */
	public SSLImageRollOverSubComponent getResponsiveComponent()
	{
		return getResponsiveComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverComponent.responsiveComponent</code> attribute. 
	 * @param value the responsiveComponent
	 */
	public void setResponsiveComponent(final SessionContext ctx, final SSLImageRollOverSubComponent value)
	{
		setProperty(ctx, RESPONSIVECOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverComponent.responsiveComponent</code> attribute. 
	 * @param value the responsiveComponent
	 */
	public void setResponsiveComponent(final SSLImageRollOverSubComponent value)
	{
		setResponsiveComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverComponent.webComponent</code> attribute.
	 * @return the webComponent
	 */
	public SSLImageRollOverSubComponent getWebComponent(final SessionContext ctx)
	{
		return (SSLImageRollOverSubComponent)getProperty( ctx, WEBCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverComponent.webComponent</code> attribute.
	 * @return the webComponent
	 */
	public SSLImageRollOverSubComponent getWebComponent()
	{
		return getWebComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverComponent.webComponent</code> attribute. 
	 * @param value the webComponent
	 */
	public void setWebComponent(final SessionContext ctx, final SSLImageRollOverSubComponent value)
	{
		setProperty(ctx, WEBCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverComponent.webComponent</code> attribute. 
	 * @param value the webComponent
	 */
	public void setWebComponent(final SSLImageRollOverSubComponent value)
	{
		setWebComponent( getSession().getSessionContext(), value );
	}
	
}
