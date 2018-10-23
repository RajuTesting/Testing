/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.CMSLinkTextComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.ServiceLinksComponent ServiceLinksComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedServiceLinksComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>ServiceLinksComponent.serviceLinkList</code> attribute **/
	public static final String SERVICELINKLIST = "serviceLinkList";
	/** Qualifier of the <code>ServiceLinksComponent.headLine</code> attribute **/
	public static final String HEADLINE = "headLine";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SERVICELINKLIST, AttributeMode.INITIAL);
		tmp.put(HEADLINE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceLinksComponent.headLine</code> attribute.
	 * @return the headLine
	 */
	public String getHeadLine(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADLINE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceLinksComponent.headLine</code> attribute.
	 * @return the headLine
	 */
	public String getHeadLine()
	{
		return getHeadLine( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceLinksComponent.headLine</code> attribute. 
	 * @param value the headLine
	 */
	public void setHeadLine(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceLinksComponent.headLine</code> attribute. 
	 * @param value the headLine
	 */
	public void setHeadLine(final String value)
	{
		setHeadLine( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceLinksComponent.serviceLinkList</code> attribute.
	 * @return the serviceLinkList
	 */
	public List<CMSLinkTextComponent> getServiceLinkList(final SessionContext ctx)
	{
		List<CMSLinkTextComponent> coll = (List<CMSLinkTextComponent>)getProperty( ctx, SERVICELINKLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceLinksComponent.serviceLinkList</code> attribute.
	 * @return the serviceLinkList
	 */
	public List<CMSLinkTextComponent> getServiceLinkList()
	{
		return getServiceLinkList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceLinksComponent.serviceLinkList</code> attribute. 
	 * @param value the serviceLinkList
	 */
	public void setServiceLinkList(final SessionContext ctx, final List<CMSLinkTextComponent> value)
	{
		setProperty(ctx, SERVICELINKLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceLinksComponent.serviceLinkList</code> attribute. 
	 * @param value the serviceLinkList
	 */
	public void setServiceLinkList(final List<CMSLinkTextComponent> value)
	{
		setServiceLinkList( getSession().getSessionContext(), value );
	}
	
}
