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
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLLeftRightComponent SSLLeftRightComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLLeftRightComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLLeftRightComponent.leftComponent</code> attribute **/
	public static final String LEFTCOMPONENT = "leftComponent";
	/** Qualifier of the <code>SSLLeftRightComponent.rightComponent</code> attribute **/
	public static final String RIGHTCOMPONENT = "rightComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LEFTCOMPONENT, AttributeMode.INITIAL);
		tmp.put(RIGHTCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLeftRightComponent.leftComponent</code> attribute.
	 * @return the leftComponent
	 */
	public SimpleCMSComponent getLeftComponent(final SessionContext ctx)
	{
		return (SimpleCMSComponent)getProperty( ctx, LEFTCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLeftRightComponent.leftComponent</code> attribute.
	 * @return the leftComponent
	 */
	public SimpleCMSComponent getLeftComponent()
	{
		return getLeftComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLeftRightComponent.leftComponent</code> attribute. 
	 * @param value the leftComponent
	 */
	public void setLeftComponent(final SessionContext ctx, final SimpleCMSComponent value)
	{
		setProperty(ctx, LEFTCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLeftRightComponent.leftComponent</code> attribute. 
	 * @param value the leftComponent
	 */
	public void setLeftComponent(final SimpleCMSComponent value)
	{
		setLeftComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLeftRightComponent.rightComponent</code> attribute.
	 * @return the rightComponent
	 */
	public SimpleCMSComponent getRightComponent(final SessionContext ctx)
	{
		return (SimpleCMSComponent)getProperty( ctx, RIGHTCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLeftRightComponent.rightComponent</code> attribute.
	 * @return the rightComponent
	 */
	public SimpleCMSComponent getRightComponent()
	{
		return getRightComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLeftRightComponent.rightComponent</code> attribute. 
	 * @param value the rightComponent
	 */
	public void setRightComponent(final SessionContext ctx, final SimpleCMSComponent value)
	{
		setProperty(ctx, RIGHTCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLeftRightComponent.rightComponent</code> attribute. 
	 * @param value the rightComponent
	 */
	public void setRightComponent(final SimpleCMSComponent value)
	{
		setRightComponent( getSession().getSessionContext(), value );
	}
	
}
