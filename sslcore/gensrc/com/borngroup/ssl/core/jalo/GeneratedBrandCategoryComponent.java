/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.ShopByTrendComponent;
import com.borngroup.ssl.core.jalo.WomenCollectionComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.BrandCategoryComponent BrandsCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBrandCategoryComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>BrandsCollectionComponent.womenCollectionComponent</code> attribute **/
	public static final String WOMENCOLLECTIONCOMPONENT = "womenCollectionComponent";
	/** Qualifier of the <code>BrandsCollectionComponent.shopByTrendComponent</code> attribute **/
	public static final String SHOPBYTRENDCOMPONENT = "shopByTrendComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(WOMENCOLLECTIONCOMPONENT, AttributeMode.INITIAL);
		tmp.put(SHOPBYTRENDCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsCollectionComponent.shopByTrendComponent</code> attribute.
	 * @return the shopByTrendComponent
	 */
	public ShopByTrendComponent getShopByTrendComponent(final SessionContext ctx)
	{
		return (ShopByTrendComponent)getProperty( ctx, SHOPBYTRENDCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsCollectionComponent.shopByTrendComponent</code> attribute.
	 * @return the shopByTrendComponent
	 */
	public ShopByTrendComponent getShopByTrendComponent()
	{
		return getShopByTrendComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsCollectionComponent.shopByTrendComponent</code> attribute. 
	 * @param value the shopByTrendComponent
	 */
	public void setShopByTrendComponent(final SessionContext ctx, final ShopByTrendComponent value)
	{
		setProperty(ctx, SHOPBYTRENDCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsCollectionComponent.shopByTrendComponent</code> attribute. 
	 * @param value the shopByTrendComponent
	 */
	public void setShopByTrendComponent(final ShopByTrendComponent value)
	{
		setShopByTrendComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsCollectionComponent.womenCollectionComponent</code> attribute.
	 * @return the womenCollectionComponent
	 */
	public WomenCollectionComponent getWomenCollectionComponent(final SessionContext ctx)
	{
		return (WomenCollectionComponent)getProperty( ctx, WOMENCOLLECTIONCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsCollectionComponent.womenCollectionComponent</code> attribute.
	 * @return the womenCollectionComponent
	 */
	public WomenCollectionComponent getWomenCollectionComponent()
	{
		return getWomenCollectionComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsCollectionComponent.womenCollectionComponent</code> attribute. 
	 * @param value the womenCollectionComponent
	 */
	public void setWomenCollectionComponent(final SessionContext ctx, final WomenCollectionComponent value)
	{
		setProperty(ctx, WOMENCOLLECTIONCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsCollectionComponent.womenCollectionComponent</code> attribute. 
	 * @param value the womenCollectionComponent
	 */
	public void setWomenCollectionComponent(final WomenCollectionComponent value)
	{
		setWomenCollectionComponent( getSession().getSessionContext(), value );
	}
	
}
