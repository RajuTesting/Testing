/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.cms2.jalo.navigation.CMSNavigationNode;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.MSiteChildCategoriesComponent MSiteChildCategoriesComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedMSiteChildCategoriesComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>MSiteChildCategoriesComponent.navigationNode</code> attribute **/
	public static final String NAVIGATIONNODE = "navigationNode";
	/** Qualifier of the <code>MSiteChildCategoriesComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(NAVIGATIONNODE, AttributeMode.INITIAL);
		tmp.put(HEADING, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MSiteChildCategoriesComponent.heading</code> attribute.
	 * @return the heading - Heading to display for the component.
	 */
	public String getHeading(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MSiteChildCategoriesComponent.heading</code> attribute.
	 * @return the heading - Heading to display for the component.
	 */
	public String getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MSiteChildCategoriesComponent.heading</code> attribute. 
	 * @param value the heading - Heading to display for the component.
	 */
	public void setHeading(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MSiteChildCategoriesComponent.heading</code> attribute. 
	 * @param value the heading - Heading to display for the component.
	 */
	public void setHeading(final String value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MSiteChildCategoriesComponent.navigationNode</code> attribute.
	 * @return the navigationNode - The cms navigation node of this component.
	 */
	public CMSNavigationNode getNavigationNode(final SessionContext ctx)
	{
		return (CMSNavigationNode)getProperty( ctx, NAVIGATIONNODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MSiteChildCategoriesComponent.navigationNode</code> attribute.
	 * @return the navigationNode - The cms navigation node of this component.
	 */
	public CMSNavigationNode getNavigationNode()
	{
		return getNavigationNode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MSiteChildCategoriesComponent.navigationNode</code> attribute. 
	 * @param value the navigationNode - The cms navigation node of this component.
	 */
	public void setNavigationNode(final SessionContext ctx, final CMSNavigationNode value)
	{
		setProperty(ctx, NAVIGATIONNODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MSiteChildCategoriesComponent.navigationNode</code> attribute. 
	 * @param value the navigationNode - The cms navigation node of this component.
	 */
	public void setNavigationNode(final CMSNavigationNode value)
	{
		setNavigationNode( getSession().getSessionContext(), value );
	}
	
}
