/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.DealCollectionComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.promotions.jalo.ProductPromotion;
import de.hybris.platform.util.Utilities;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.DealComponent DealComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedDealComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>DealComponent.startTime</code> attribute **/
	public static final String STARTTIME = "startTime";
	/** Qualifier of the <code>DealComponent.endTime</code> attribute **/
	public static final String ENDTIME = "endTime";
	/** Qualifier of the <code>DealComponent.sortProductsBy</code> attribute **/
	public static final String SORTPRODUCTSBY = "sortProductsBy";
	/** Qualifier of the <code>DealComponent.enabled</code> attribute **/
	public static final String ENABLED = "enabled";
	/** Qualifier of the <code>DealComponent.promotionList</code> attribute **/
	public static final String PROMOTIONLIST = "promotionList";
	/** Qualifier of the <code>DealComponent.productList</code> attribute **/
	public static final String PRODUCTLIST = "productList";
	/** Qualifier of the <code>DealComponent.collectionComponent</code> attribute **/
	public static final String COLLECTIONCOMPONENT = "collectionComponent";
	/** Relation ordering override parameter constants for DealCollection2DealComponentRelation from ((sslcore))*/
	protected static String DEALCOLLECTION2DEALCOMPONENTRELATION_SRC_ORDERED = "relation.DealCollection2DealComponentRelation.source.ordered";
	protected static String DEALCOLLECTION2DEALCOMPONENTRELATION_TGT_ORDERED = "relation.DealCollection2DealComponentRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for DealCollection2DealComponentRelation from ((sslcore))*/
	protected static String DEALCOLLECTION2DEALCOMPONENTRELATION_MARKMODIFIED = "relation.DealCollection2DealComponentRelation.markmodified";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(STARTTIME, AttributeMode.INITIAL);
		tmp.put(ENDTIME, AttributeMode.INITIAL);
		tmp.put(SORTPRODUCTSBY, AttributeMode.INITIAL);
		tmp.put(ENABLED, AttributeMode.INITIAL);
		tmp.put(PROMOTIONLIST, AttributeMode.INITIAL);
		tmp.put(PRODUCTLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.collectionComponent</code> attribute.
	 * @return the collectionComponent
	 */
	public Collection<DealCollectionComponent> getCollectionComponent(final SessionContext ctx)
	{
		final List<DealCollectionComponent> items = getLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.DEALCOLLECTION2DEALCOMPONENTRELATION,
			null,
			Utilities.getRelationOrderingOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.collectionComponent</code> attribute.
	 * @return the collectionComponent
	 */
	public Collection<DealCollectionComponent> getCollectionComponent()
	{
		return getCollectionComponent( getSession().getSessionContext() );
	}
	
	public long getCollectionComponentCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			false,
			SslCoreConstants.Relations.DEALCOLLECTION2DEALCOMPONENTRELATION,
			null
		);
	}
	
	public long getCollectionComponentCount()
	{
		return getCollectionComponentCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.collectionComponent</code> attribute. 
	 * @param value the collectionComponent
	 */
	public void setCollectionComponent(final SessionContext ctx, final Collection<DealCollectionComponent> value)
	{
		setLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.DEALCOLLECTION2DEALCOMPONENTRELATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.collectionComponent</code> attribute. 
	 * @param value the collectionComponent
	 */
	public void setCollectionComponent(final Collection<DealCollectionComponent> value)
	{
		setCollectionComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to collectionComponent. 
	 * @param value the item to add to collectionComponent
	 */
	public void addToCollectionComponent(final SessionContext ctx, final DealCollectionComponent value)
	{
		addLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.DEALCOLLECTION2DEALCOMPONENTRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to collectionComponent. 
	 * @param value the item to add to collectionComponent
	 */
	public void addToCollectionComponent(final DealCollectionComponent value)
	{
		addToCollectionComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from collectionComponent. 
	 * @param value the item to remove from collectionComponent
	 */
	public void removeFromCollectionComponent(final SessionContext ctx, final DealCollectionComponent value)
	{
		removeLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.DEALCOLLECTION2DEALCOMPONENTRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from collectionComponent. 
	 * @param value the item to remove from collectionComponent
	 */
	public void removeFromCollectionComponent(final DealCollectionComponent value)
	{
		removeFromCollectionComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.enabled</code> attribute.
	 * @return the enabled
	 */
	public Boolean isEnabled(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ENABLED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.enabled</code> attribute.
	 * @return the enabled
	 */
	public Boolean isEnabled()
	{
		return isEnabled( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.enabled</code> attribute. 
	 * @return the enabled
	 */
	public boolean isEnabledAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isEnabled( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.enabled</code> attribute. 
	 * @return the enabled
	 */
	public boolean isEnabledAsPrimitive()
	{
		return isEnabledAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.enabled</code> attribute. 
	 * @param value the enabled
	 */
	public void setEnabled(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ENABLED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.enabled</code> attribute. 
	 * @param value the enabled
	 */
	public void setEnabled(final Boolean value)
	{
		setEnabled( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.enabled</code> attribute. 
	 * @param value the enabled
	 */
	public void setEnabled(final SessionContext ctx, final boolean value)
	{
		setEnabled( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.enabled</code> attribute. 
	 * @param value the enabled
	 */
	public void setEnabled(final boolean value)
	{
		setEnabled( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.endTime</code> attribute.
	 * @return the endTime
	 */
	public Date getEndTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ENDTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.endTime</code> attribute.
	 * @return the endTime
	 */
	public Date getEndTime()
	{
		return getEndTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.endTime</code> attribute. 
	 * @param value the endTime
	 */
	public void setEndTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ENDTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.endTime</code> attribute. 
	 * @param value the endTime
	 */
	public void setEndTime(final Date value)
	{
		setEndTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.productList</code> attribute.
	 * @return the productList - Products List
	 */
	public List<Product> getProductList(final SessionContext ctx)
	{
		List<Product> coll = (List<Product>)getProperty( ctx, PRODUCTLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.productList</code> attribute.
	 * @return the productList - Products List
	 */
	public List<Product> getProductList()
	{
		return getProductList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.productList</code> attribute. 
	 * @param value the productList - Products List
	 */
	public void setProductList(final SessionContext ctx, final List<Product> value)
	{
		setProperty(ctx, PRODUCTLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.productList</code> attribute. 
	 * @param value the productList - Products List
	 */
	public void setProductList(final List<Product> value)
	{
		setProductList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.promotionList</code> attribute.
	 * @return the promotionList - Sort products by.
	 */
	public List<ProductPromotion> getPromotionList(final SessionContext ctx)
	{
		List<ProductPromotion> coll = (List<ProductPromotion>)getProperty( ctx, PROMOTIONLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.promotionList</code> attribute.
	 * @return the promotionList - Sort products by.
	 */
	public List<ProductPromotion> getPromotionList()
	{
		return getPromotionList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.promotionList</code> attribute. 
	 * @param value the promotionList - Sort products by.
	 */
	public void setPromotionList(final SessionContext ctx, final List<ProductPromotion> value)
	{
		setProperty(ctx, PROMOTIONLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.promotionList</code> attribute. 
	 * @param value the promotionList - Sort products by.
	 */
	public void setPromotionList(final List<ProductPromotion> value)
	{
		setPromotionList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.sortProductsBy</code> attribute.
	 * @return the sortProductsBy - Sort products by.
	 */
	public EnumerationValue getSortProductsBy(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, SORTPRODUCTSBY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.sortProductsBy</code> attribute.
	 * @return the sortProductsBy - Sort products by.
	 */
	public EnumerationValue getSortProductsBy()
	{
		return getSortProductsBy( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.sortProductsBy</code> attribute. 
	 * @param value the sortProductsBy - Sort products by.
	 */
	public void setSortProductsBy(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, SORTPRODUCTSBY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.sortProductsBy</code> attribute. 
	 * @param value the sortProductsBy - Sort products by.
	 */
	public void setSortProductsBy(final EnumerationValue value)
	{
		setSortProductsBy( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.startTime</code> attribute.
	 * @return the startTime
	 */
	public Date getStartTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, STARTTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealComponent.startTime</code> attribute.
	 * @return the startTime
	 */
	public Date getStartTime()
	{
		return getStartTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.startTime</code> attribute. 
	 * @param value the startTime
	 */
	public void setStartTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, STARTTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealComponent.startTime</code> attribute. 
	 * @param value the startTime
	 */
	public void setStartTime(final Date value)
	{
		setStartTime( getSession().getSessionContext(), value );
	}
	
}
