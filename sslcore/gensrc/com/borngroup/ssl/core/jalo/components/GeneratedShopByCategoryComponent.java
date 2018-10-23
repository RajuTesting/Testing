/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.PromotionBannerComponent;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleBannerComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.ShopByCategoryComponent ShopByCategoryComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedShopByCategoryComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>ShopByCategoryComponent.onlyHeaderComponent</code> attribute **/
	public static final String ONLYHEADERCOMPONENT = "onlyHeaderComponent";
	/** Qualifier of the <code>ShopByCategoryComponent.children</code> attribute **/
	public static final String CHILDREN = "children";
	/** Qualifier of the <code>ShopByCategoryComponent.rightSideComponent</code> attribute **/
	public static final String RIGHTSIDECOMPONENT = "rightSideComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(ONLYHEADERCOMPONENT, AttributeMode.INITIAL);
		tmp.put(CHILDREN, AttributeMode.INITIAL);
		tmp.put(RIGHTSIDECOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByCategoryComponent.children</code> attribute.
	 * @return the children - Promotion Product Banner Component
	 */
	public List<SimpleBannerComponent> getChildren(final SessionContext ctx)
	{
		List<SimpleBannerComponent> coll = (List<SimpleBannerComponent>)getProperty( ctx, CHILDREN);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByCategoryComponent.children</code> attribute.
	 * @return the children - Promotion Product Banner Component
	 */
	public List<SimpleBannerComponent> getChildren()
	{
		return getChildren( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByCategoryComponent.children</code> attribute. 
	 * @param value the children - Promotion Product Banner Component
	 */
	public void setChildren(final SessionContext ctx, final List<SimpleBannerComponent> value)
	{
		setProperty(ctx, CHILDREN,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByCategoryComponent.children</code> attribute. 
	 * @param value the children - Promotion Product Banner Component
	 */
	public void setChildren(final List<SimpleBannerComponent> value)
	{
		setChildren( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByCategoryComponent.onlyHeaderComponent</code> attribute.
	 * @return the onlyHeaderComponent - Left Side Component - Containing inly a header
	 */
	public PromotionBannerComponent getOnlyHeaderComponent(final SessionContext ctx)
	{
		return (PromotionBannerComponent)getProperty( ctx, ONLYHEADERCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByCategoryComponent.onlyHeaderComponent</code> attribute.
	 * @return the onlyHeaderComponent - Left Side Component - Containing inly a header
	 */
	public PromotionBannerComponent getOnlyHeaderComponent()
	{
		return getOnlyHeaderComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByCategoryComponent.onlyHeaderComponent</code> attribute. 
	 * @param value the onlyHeaderComponent - Left Side Component - Containing inly a header
	 */
	public void setOnlyHeaderComponent(final SessionContext ctx, final PromotionBannerComponent value)
	{
		setProperty(ctx, ONLYHEADERCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByCategoryComponent.onlyHeaderComponent</code> attribute. 
	 * @param value the onlyHeaderComponent - Left Side Component - Containing inly a header
	 */
	public void setOnlyHeaderComponent(final PromotionBannerComponent value)
	{
		setOnlyHeaderComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByCategoryComponent.rightSideComponent</code> attribute.
	 * @return the rightSideComponent - Right Side Banner Component
	 */
	public PromotionBannerComponent getRightSideComponent(final SessionContext ctx)
	{
		return (PromotionBannerComponent)getProperty( ctx, RIGHTSIDECOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByCategoryComponent.rightSideComponent</code> attribute.
	 * @return the rightSideComponent - Right Side Banner Component
	 */
	public PromotionBannerComponent getRightSideComponent()
	{
		return getRightSideComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByCategoryComponent.rightSideComponent</code> attribute. 
	 * @param value the rightSideComponent - Right Side Banner Component
	 */
	public void setRightSideComponent(final SessionContext ctx, final PromotionBannerComponent value)
	{
		setProperty(ctx, RIGHTSIDECOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByCategoryComponent.rightSideComponent</code> attribute. 
	 * @param value the rightSideComponent - Right Side Banner Component
	 */
	public void setRightSideComponent(final PromotionBannerComponent value)
	{
		setRightSideComponent( getSession().getSessionContext(), value );
	}
	
}
