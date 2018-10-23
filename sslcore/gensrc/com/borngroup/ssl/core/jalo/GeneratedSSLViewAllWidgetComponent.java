/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLTextWidgetComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLViewAllWidgetComponent SSLViewAllWidgetComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLViewAllWidgetComponent extends SSLTextWidgetComponent
{
	/** Qualifier of the <code>SSLViewAllWidgetComponent.visiblity</code> attribute **/
	public static final String VISIBLITY = "visiblity";
	/** Qualifier of the <code>SSLViewAllWidgetComponent.linkComponent</code> attribute **/
	public static final String LINKCOMPONENT = "linkComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SSLTextWidgetComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(VISIBLITY, AttributeMode.INITIAL);
		tmp.put(LINKCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLViewAllWidgetComponent.linkComponent</code> attribute.
	 * @return the linkComponent - Link
	 */
	public CMSLinkComponent getLinkComponent(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, LINKCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLViewAllWidgetComponent.linkComponent</code> attribute.
	 * @return the linkComponent - Link
	 */
	public CMSLinkComponent getLinkComponent()
	{
		return getLinkComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLViewAllWidgetComponent.linkComponent</code> attribute. 
	 * @param value the linkComponent - Link
	 */
	public void setLinkComponent(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, LINKCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLViewAllWidgetComponent.linkComponent</code> attribute. 
	 * @param value the linkComponent - Link
	 */
	public void setLinkComponent(final CMSLinkComponent value)
	{
		setLinkComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLViewAllWidgetComponent.visiblity</code> attribute.
	 * @return the visiblity - Check for visibility
	 */
	public Boolean isVisiblity(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, VISIBLITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLViewAllWidgetComponent.visiblity</code> attribute.
	 * @return the visiblity - Check for visibility
	 */
	public Boolean isVisiblity()
	{
		return isVisiblity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLViewAllWidgetComponent.visiblity</code> attribute. 
	 * @return the visiblity - Check for visibility
	 */
	public boolean isVisiblityAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isVisiblity( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLViewAllWidgetComponent.visiblity</code> attribute. 
	 * @return the visiblity - Check for visibility
	 */
	public boolean isVisiblityAsPrimitive()
	{
		return isVisiblityAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLViewAllWidgetComponent.visiblity</code> attribute. 
	 * @param value the visiblity - Check for visibility
	 */
	public void setVisiblity(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, VISIBLITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLViewAllWidgetComponent.visiblity</code> attribute. 
	 * @param value the visiblity - Check for visibility
	 */
	public void setVisiblity(final Boolean value)
	{
		setVisiblity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLViewAllWidgetComponent.visiblity</code> attribute. 
	 * @param value the visiblity - Check for visibility
	 */
	public void setVisiblity(final SessionContext ctx, final boolean value)
	{
		setVisiblity( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLViewAllWidgetComponent.visiblity</code> attribute. 
	 * @param value the visiblity - Check for visibility
	 */
	public void setVisiblity(final boolean value)
	{
		setVisiblity( getSession().getSessionContext(), value );
	}
	
}
