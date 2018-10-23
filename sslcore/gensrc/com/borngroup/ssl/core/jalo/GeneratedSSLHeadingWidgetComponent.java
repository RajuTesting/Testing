/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLTextWidgetComponent;
import com.borngroup.ssl.core.jalo.SSLViewAllWidgetComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLHeadingWidgetComponent SSLHeadingWidgetComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLHeadingWidgetComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLHeadingWidgetComponent.heading1</code> attribute **/
	public static final String HEADING1 = "heading1";
	/** Qualifier of the <code>SSLHeadingWidgetComponent.heading2</code> attribute **/
	public static final String HEADING2 = "heading2";
	/** Qualifier of the <code>SSLHeadingWidgetComponent.viewAll</code> attribute **/
	public static final String VIEWALL = "viewAll";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADING1, AttributeMode.INITIAL);
		tmp.put(HEADING2, AttributeMode.INITIAL);
		tmp.put(VIEWALL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLHeadingWidgetComponent.heading1</code> attribute.
	 * @return the heading1 - Heading 1
	 */
	public SSLTextWidgetComponent getHeading1(final SessionContext ctx)
	{
		return (SSLTextWidgetComponent)getProperty( ctx, HEADING1);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLHeadingWidgetComponent.heading1</code> attribute.
	 * @return the heading1 - Heading 1
	 */
	public SSLTextWidgetComponent getHeading1()
	{
		return getHeading1( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLHeadingWidgetComponent.heading1</code> attribute. 
	 * @param value the heading1 - Heading 1
	 */
	public void setHeading1(final SessionContext ctx, final SSLTextWidgetComponent value)
	{
		setProperty(ctx, HEADING1,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLHeadingWidgetComponent.heading1</code> attribute. 
	 * @param value the heading1 - Heading 1
	 */
	public void setHeading1(final SSLTextWidgetComponent value)
	{
		setHeading1( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLHeadingWidgetComponent.heading2</code> attribute.
	 * @return the heading2 - Heading 2
	 */
	public SSLTextWidgetComponent getHeading2(final SessionContext ctx)
	{
		return (SSLTextWidgetComponent)getProperty( ctx, HEADING2);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLHeadingWidgetComponent.heading2</code> attribute.
	 * @return the heading2 - Heading 2
	 */
	public SSLTextWidgetComponent getHeading2()
	{
		return getHeading2( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLHeadingWidgetComponent.heading2</code> attribute. 
	 * @param value the heading2 - Heading 2
	 */
	public void setHeading2(final SessionContext ctx, final SSLTextWidgetComponent value)
	{
		setProperty(ctx, HEADING2,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLHeadingWidgetComponent.heading2</code> attribute. 
	 * @param value the heading2 - Heading 2
	 */
	public void setHeading2(final SSLTextWidgetComponent value)
	{
		setHeading2( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLHeadingWidgetComponent.viewAll</code> attribute.
	 * @return the viewAll - View All
	 */
	public SSLViewAllWidgetComponent getViewAll(final SessionContext ctx)
	{
		return (SSLViewAllWidgetComponent)getProperty( ctx, VIEWALL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLHeadingWidgetComponent.viewAll</code> attribute.
	 * @return the viewAll - View All
	 */
	public SSLViewAllWidgetComponent getViewAll()
	{
		return getViewAll( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLHeadingWidgetComponent.viewAll</code> attribute. 
	 * @param value the viewAll - View All
	 */
	public void setViewAll(final SessionContext ctx, final SSLViewAllWidgetComponent value)
	{
		setProperty(ctx, VIEWALL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLHeadingWidgetComponent.viewAll</code> attribute. 
	 * @param value the viewAll - View All
	 */
	public void setViewAll(final SSLViewAllWidgetComponent value)
	{
		setViewAll( getSession().getSessionContext(), value );
	}
	
}
