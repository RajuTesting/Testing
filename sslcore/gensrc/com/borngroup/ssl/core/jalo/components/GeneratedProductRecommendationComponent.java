/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.ProductRecommendationComponent ProductRecommendationComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedProductRecommendationComponent extends CMSImageComponent
{
	/** Qualifier of the <code>ProductRecommendationComponent.productsList</code> attribute **/
	public static final String PRODUCTSLIST = "productsList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(PRODUCTSLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductRecommendationComponent.productsList</code> attribute.
	 * @return the productsList - List Of Products
	 */
	public List<Product> getProductsList(final SessionContext ctx)
	{
		List<Product> coll = (List<Product>)getProperty( ctx, PRODUCTSLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductRecommendationComponent.productsList</code> attribute.
	 * @return the productsList - List Of Products
	 */
	public List<Product> getProductsList()
	{
		return getProductsList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductRecommendationComponent.productsList</code> attribute. 
	 * @param value the productsList - List Of Products
	 */
	public void setProductsList(final SessionContext ctx, final List<Product> value)
	{
		setProperty(ctx, PRODUCTSLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductRecommendationComponent.productsList</code> attribute. 
	 * @param value the productsList - List Of Products
	 */
	public void setProductsList(final List<Product> value)
	{
		setProductsList( getSession().getSessionContext(), value );
	}
	
}
