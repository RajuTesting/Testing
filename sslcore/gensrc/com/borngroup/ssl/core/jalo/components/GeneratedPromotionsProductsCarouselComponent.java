/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.PromotionOfferComponent;
import com.borngroup.ssl.core.jalo.components.PromotionsProductsBannerComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.PromotionsProductsCarouselComponent PromotionsProductsCarouselComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionsProductsCarouselComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>PromotionsProductsCarouselComponent.children</code> attribute **/
	public static final String CHILDREN = "children";
	/** Qualifier of the <code>PromotionsProductsCarouselComponent.promotionOffers</code> attribute **/
	public static final String PROMOTIONOFFERS = "promotionOffers";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CHILDREN, AttributeMode.INITIAL);
		tmp.put(PROMOTIONOFFERS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsCarouselComponent.children</code> attribute.
	 * @return the children - Promotion Product Banner Component
	 */
	public List<PromotionsProductsBannerComponent> getChildren(final SessionContext ctx)
	{
		List<PromotionsProductsBannerComponent> coll = (List<PromotionsProductsBannerComponent>)getProperty( ctx, CHILDREN);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsCarouselComponent.children</code> attribute.
	 * @return the children - Promotion Product Banner Component
	 */
	public List<PromotionsProductsBannerComponent> getChildren()
	{
		return getChildren( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsCarouselComponent.children</code> attribute. 
	 * @param value the children - Promotion Product Banner Component
	 */
	public void setChildren(final SessionContext ctx, final List<PromotionsProductsBannerComponent> value)
	{
		setProperty(ctx, CHILDREN,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsCarouselComponent.children</code> attribute. 
	 * @param value the children - Promotion Product Banner Component
	 */
	public void setChildren(final List<PromotionsProductsBannerComponent> value)
	{
		setChildren( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsCarouselComponent.promotionOffers</code> attribute.
	 * @return the promotionOffers - Promotion's Category
	 */
	public List<PromotionOfferComponent> getPromotionOffers(final SessionContext ctx)
	{
		List<PromotionOfferComponent> coll = (List<PromotionOfferComponent>)getProperty( ctx, PROMOTIONOFFERS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsCarouselComponent.promotionOffers</code> attribute.
	 * @return the promotionOffers - Promotion's Category
	 */
	public List<PromotionOfferComponent> getPromotionOffers()
	{
		return getPromotionOffers( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsCarouselComponent.promotionOffers</code> attribute. 
	 * @param value the promotionOffers - Promotion's Category
	 */
	public void setPromotionOffers(final SessionContext ctx, final List<PromotionOfferComponent> value)
	{
		setProperty(ctx, PROMOTIONOFFERS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsCarouselComponent.promotionOffers</code> attribute. 
	 * @param value the promotionOffers - Promotion's Category
	 */
	public void setPromotionOffers(final List<PromotionOfferComponent> value)
	{
		setPromotionOffers( getSession().getSessionContext(), value );
	}
	
}
