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
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLProductWidgetCarouselComponent SSLProductWidgetCarouselComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLProductWidgetCarouselComponent extends SSLMobileWidgetComponent
{
	/** Qualifier of the <code>SSLProductWidgetCarouselComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>SSLProductWidgetCarouselComponent.productList</code> attribute **/
	public static final String PRODUCTLIST = "productList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SSLMobileWidgetComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(PRODUCTLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductWidgetCarouselComponent.heading</code> attribute.
	 * @return the heading - Heading Component
	 */
	public SSLHeadingWidgetComponent getHeading(final SessionContext ctx)
	{
		return (SSLHeadingWidgetComponent)getProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductWidgetCarouselComponent.heading</code> attribute.
	 * @return the heading - Heading Component
	 */
	public SSLHeadingWidgetComponent getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductWidgetCarouselComponent.heading</code> attribute. 
	 * @param value the heading - Heading Component
	 */
	public void setHeading(final SessionContext ctx, final SSLHeadingWidgetComponent value)
	{
		setProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductWidgetCarouselComponent.heading</code> attribute. 
	 * @param value the heading - Heading Component
	 */
	public void setHeading(final SSLHeadingWidgetComponent value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductWidgetCarouselComponent.productList</code> attribute.
	 * @return the productList - Products List
	 */
	public List<Product> getProductList(final SessionContext ctx)
	{
		List<Product> coll = (List<Product>)getProperty( ctx, PRODUCTLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductWidgetCarouselComponent.productList</code> attribute.
	 * @return the productList - Products List
	 */
	public List<Product> getProductList()
	{
		return getProductList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductWidgetCarouselComponent.productList</code> attribute. 
	 * @param value the productList - Products List
	 */
	public void setProductList(final SessionContext ctx, final List<Product> value)
	{
		setProperty(ctx, PRODUCTLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductWidgetCarouselComponent.productList</code> attribute. 
	 * @param value the productList - Products List
	 */
	public void setProductList(final List<Product> value)
	{
		setProductList( getSession().getSessionContext(), value );
	}
	
}
