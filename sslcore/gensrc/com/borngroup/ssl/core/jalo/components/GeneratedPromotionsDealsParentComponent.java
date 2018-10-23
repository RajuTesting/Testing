/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.PromotionDealComponent;
import com.borngroup.ssl.core.jalo.components.SslExclusiveBrandsComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.PromotionsDealsParentComponent PromotionsDealsParentComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionsDealsParentComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>PromotionsDealsParentComponent.exclusiveBrands</code> attribute **/
	public static final String EXCLUSIVEBRANDS = "exclusiveBrands";
	/** Qualifier of the <code>PromotionsDealsParentComponent.promotionDeal1</code> attribute **/
	public static final String PROMOTIONDEAL1 = "promotionDeal1";
	/** Qualifier of the <code>PromotionsDealsParentComponent.promotionDeal2</code> attribute **/
	public static final String PROMOTIONDEAL2 = "promotionDeal2";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(EXCLUSIVEBRANDS, AttributeMode.INITIAL);
		tmp.put(PROMOTIONDEAL1, AttributeMode.INITIAL);
		tmp.put(PROMOTIONDEAL2, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsDealsParentComponent.exclusiveBrands</code> attribute.
	 * @return the exclusiveBrands - Banner component
	 */
	public SslExclusiveBrandsComponent getExclusiveBrands(final SessionContext ctx)
	{
		return (SslExclusiveBrandsComponent)getProperty( ctx, EXCLUSIVEBRANDS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsDealsParentComponent.exclusiveBrands</code> attribute.
	 * @return the exclusiveBrands - Banner component
	 */
	public SslExclusiveBrandsComponent getExclusiveBrands()
	{
		return getExclusiveBrands( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsDealsParentComponent.exclusiveBrands</code> attribute. 
	 * @param value the exclusiveBrands - Banner component
	 */
	public void setExclusiveBrands(final SessionContext ctx, final SslExclusiveBrandsComponent value)
	{
		setProperty(ctx, EXCLUSIVEBRANDS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsDealsParentComponent.exclusiveBrands</code> attribute. 
	 * @param value the exclusiveBrands - Banner component
	 */
	public void setExclusiveBrands(final SslExclusiveBrandsComponent value)
	{
		setExclusiveBrands( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsDealsParentComponent.promotionDeal1</code> attribute.
	 * @return the promotionDeal1 - Deal Component1
	 */
	public PromotionDealComponent getPromotionDeal1(final SessionContext ctx)
	{
		return (PromotionDealComponent)getProperty( ctx, PROMOTIONDEAL1);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsDealsParentComponent.promotionDeal1</code> attribute.
	 * @return the promotionDeal1 - Deal Component1
	 */
	public PromotionDealComponent getPromotionDeal1()
	{
		return getPromotionDeal1( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsDealsParentComponent.promotionDeal1</code> attribute. 
	 * @param value the promotionDeal1 - Deal Component1
	 */
	public void setPromotionDeal1(final SessionContext ctx, final PromotionDealComponent value)
	{
		setProperty(ctx, PROMOTIONDEAL1,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsDealsParentComponent.promotionDeal1</code> attribute. 
	 * @param value the promotionDeal1 - Deal Component1
	 */
	public void setPromotionDeal1(final PromotionDealComponent value)
	{
		setPromotionDeal1( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsDealsParentComponent.promotionDeal2</code> attribute.
	 * @return the promotionDeal2 - Deal Component2
	 */
	public PromotionDealComponent getPromotionDeal2(final SessionContext ctx)
	{
		return (PromotionDealComponent)getProperty( ctx, PROMOTIONDEAL2);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsDealsParentComponent.promotionDeal2</code> attribute.
	 * @return the promotionDeal2 - Deal Component2
	 */
	public PromotionDealComponent getPromotionDeal2()
	{
		return getPromotionDeal2( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsDealsParentComponent.promotionDeal2</code> attribute. 
	 * @param value the promotionDeal2 - Deal Component2
	 */
	public void setPromotionDeal2(final SessionContext ctx, final PromotionDealComponent value)
	{
		setProperty(ctx, PROMOTIONDEAL2,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsDealsParentComponent.promotionDeal2</code> attribute. 
	 * @param value the promotionDeal2 - Deal Component2
	 */
	public void setPromotionDeal2(final PromotionDealComponent value)
	{
		setPromotionDeal2( getSession().getSessionContext(), value );
	}
	
}
