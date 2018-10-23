/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.BestSellerCMSComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.BrandsBestSellersComponent BrandsBestSellersComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBrandsBestSellersComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>BrandsBestSellersComponent.bestSellerList</code> attribute **/
	public static final String BESTSELLERLIST = "bestSellerList";
	/** Qualifier of the <code>BrandsBestSellersComponent.productsList</code> attribute **/
	public static final String PRODUCTSLIST = "productsList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BESTSELLERLIST, AttributeMode.INITIAL);
		tmp.put(PRODUCTSLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsBestSellersComponent.bestSellerList</code> attribute.
	 * @return the bestSellerList
	 */
	public List<BestSellerCMSComponent> getBestSellerList(final SessionContext ctx)
	{
		List<BestSellerCMSComponent> coll = (List<BestSellerCMSComponent>)getProperty( ctx, BESTSELLERLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsBestSellersComponent.bestSellerList</code> attribute.
	 * @return the bestSellerList
	 */
	public List<BestSellerCMSComponent> getBestSellerList()
	{
		return getBestSellerList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsBestSellersComponent.bestSellerList</code> attribute. 
	 * @param value the bestSellerList
	 */
	public void setBestSellerList(final SessionContext ctx, final List<BestSellerCMSComponent> value)
	{
		setProperty(ctx, BESTSELLERLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsBestSellersComponent.bestSellerList</code> attribute. 
	 * @param value the bestSellerList
	 */
	public void setBestSellerList(final List<BestSellerCMSComponent> value)
	{
		setBestSellerList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsBestSellersComponent.productsList</code> attribute.
	 * @return the productsList - List Of Products
	 */
	public List<Product> getProductsList(final SessionContext ctx)
	{
		List<Product> coll = (List<Product>)getProperty( ctx, PRODUCTSLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsBestSellersComponent.productsList</code> attribute.
	 * @return the productsList - List Of Products
	 */
	public List<Product> getProductsList()
	{
		return getProductsList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsBestSellersComponent.productsList</code> attribute. 
	 * @param value the productsList - List Of Products
	 */
	public void setProductsList(final SessionContext ctx, final List<Product> value)
	{
		setProperty(ctx, PRODUCTSLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsBestSellersComponent.productsList</code> attribute. 
	 * @param value the productsList - List Of Products
	 */
	public void setProductsList(final List<Product> value)
	{
		setProductsList( getSession().getSessionContext(), value );
	}
	
}
