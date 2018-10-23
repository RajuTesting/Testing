/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.PromotionPercentageDiscountRow;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.util.PartOfHandler;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.PromotionQuantityAndDiscountRow PromotionQuantityAndDiscountRow}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionQuantityAndDiscountRow extends GenericItem
{
	/** Qualifier of the <code>PromotionQuantityAndDiscountRow.quantity</code> attribute **/
	public static final String QUANTITY = "quantity";
	/** Qualifier of the <code>PromotionQuantityAndDiscountRow.discounts</code> attribute **/
	public static final String DISCOUNTS = "discounts";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(QUANTITY, AttributeMode.INITIAL);
		tmp.put(DISCOUNTS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionQuantityAndDiscountRow.discounts</code> attribute.
	 * @return the discounts - Discounts in specific currencies.
	 */
	public Collection<PromotionPercentageDiscountRow> getDiscounts(final SessionContext ctx)
	{
		Collection<PromotionPercentageDiscountRow> coll = (Collection<PromotionPercentageDiscountRow>)getProperty( ctx, DISCOUNTS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionQuantityAndDiscountRow.discounts</code> attribute.
	 * @return the discounts - Discounts in specific currencies.
	 */
	public Collection<PromotionPercentageDiscountRow> getDiscounts()
	{
		return getDiscounts( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionQuantityAndDiscountRow.discounts</code> attribute. 
	 * @param value the discounts - Discounts in specific currencies.
	 */
	public void setDiscounts(final SessionContext ctx, final Collection<PromotionPercentageDiscountRow> value)
	{
		new PartOfHandler<Collection<PromotionPercentageDiscountRow>>()
		{
			@Override
			protected Collection<PromotionPercentageDiscountRow> doGetValue(final SessionContext ctx)
			{
				return getDiscounts( ctx );
			}
			@Override
			protected void doSetValue(final SessionContext ctx, final Collection<PromotionPercentageDiscountRow> _value)
			{
				final Collection<PromotionPercentageDiscountRow> value = _value;
				setProperty(ctx, DISCOUNTS,value == null || !value.isEmpty() ? value : null );
			}
		}.setValue( ctx, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionQuantityAndDiscountRow.discounts</code> attribute. 
	 * @param value the discounts - Discounts in specific currencies.
	 */
	public void setDiscounts(final Collection<PromotionPercentageDiscountRow> value)
	{
		setDiscounts( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionQuantityAndDiscountRow.quantity</code> attribute.
	 * @return the quantity
	 */
	public Long getQuantity(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, QUANTITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionQuantityAndDiscountRow.quantity</code> attribute.
	 * @return the quantity
	 */
	public Long getQuantity()
	{
		return getQuantity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionQuantityAndDiscountRow.quantity</code> attribute. 
	 * @return the quantity
	 */
	public long getQuantityAsPrimitive(final SessionContext ctx)
	{
		Long value = getQuantity( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionQuantityAndDiscountRow.quantity</code> attribute. 
	 * @return the quantity
	 */
	public long getQuantityAsPrimitive()
	{
		return getQuantityAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionQuantityAndDiscountRow.quantity</code> attribute. 
	 * @param value the quantity
	 */
	public void setQuantity(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, QUANTITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionQuantityAndDiscountRow.quantity</code> attribute. 
	 * @param value the quantity
	 */
	public void setQuantity(final Long value)
	{
		setQuantity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionQuantityAndDiscountRow.quantity</code> attribute. 
	 * @param value the quantity
	 */
	public void setQuantity(final SessionContext ctx, final long value)
	{
		setQuantity( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionQuantityAndDiscountRow.quantity</code> attribute. 
	 * @param value the quantity
	 */
	public void setQuantity(final long value)
	{
		setQuantity( getSession().getSessionContext(), value );
	}
	
}
