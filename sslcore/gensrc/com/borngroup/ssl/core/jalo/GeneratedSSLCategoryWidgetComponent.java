/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLHeadingWidgetComponent;
import com.borngroup.ssl.core.jalo.SSLMobileWidgetComponent;
import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLCategoryWidgetComponent SSLCategoryWidgetComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLCategoryWidgetComponent extends SSLMobileWidgetComponent
{
	/** Qualifier of the <code>SSLCategoryWidgetComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>SSLCategoryWidgetComponent.category</code> attribute **/
	public static final String CATEGORY = "category";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SSLMobileWidgetComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(CATEGORY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCategoryWidgetComponent.category</code> attribute.
	 * @return the category - Category field
	 */
	public Category getCategory(final SessionContext ctx)
	{
		return (Category)getProperty( ctx, CATEGORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCategoryWidgetComponent.category</code> attribute.
	 * @return the category - Category field
	 */
	public Category getCategory()
	{
		return getCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCategoryWidgetComponent.category</code> attribute. 
	 * @param value the category - Category field
	 */
	public void setCategory(final SessionContext ctx, final Category value)
	{
		setProperty(ctx, CATEGORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCategoryWidgetComponent.category</code> attribute. 
	 * @param value the category - Category field
	 */
	public void setCategory(final Category value)
	{
		setCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCategoryWidgetComponent.heading</code> attribute.
	 * @return the heading - Heading Component
	 */
	public SSLHeadingWidgetComponent getHeading(final SessionContext ctx)
	{
		return (SSLHeadingWidgetComponent)getProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCategoryWidgetComponent.heading</code> attribute.
	 * @return the heading - Heading Component
	 */
	public SSLHeadingWidgetComponent getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCategoryWidgetComponent.heading</code> attribute. 
	 * @param value the heading - Heading Component
	 */
	public void setHeading(final SessionContext ctx, final SSLHeadingWidgetComponent value)
	{
		setProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCategoryWidgetComponent.heading</code> attribute. 
	 * @param value the heading - Heading Component
	 */
	public void setHeading(final SSLHeadingWidgetComponent value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
}
