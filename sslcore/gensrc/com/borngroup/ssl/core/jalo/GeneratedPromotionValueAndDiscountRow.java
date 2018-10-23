/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.promotions.jalo.PromotionPriceRow;
import de.hybris.platform.util.PartOfHandler;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.PromotionValueAndDiscountRow PromotionValueAndDiscountRow}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionValueAndDiscountRow extends GenericItem
{
	/** Qualifier of the <code>PromotionValueAndDiscountRow.value</code> attribute **/
	public static final String VALUE = "value";
	/** Qualifier of the <code>PromotionValueAndDiscountRow.discount</code> attribute **/
	public static final String DISCOUNT = "discount";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(VALUE, AttributeMode.INITIAL);
		tmp.put(DISCOUNT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionValueAndDiscountRow.discount</code> attribute.
	 * @return the discount - Discount in specific currencies.
	 */
	public Collection<PromotionPriceRow> getDiscount(final SessionContext ctx)
	{
		Collection<PromotionPriceRow> coll = (Collection<PromotionPriceRow>)getProperty( ctx, DISCOUNT);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionValueAndDiscountRow.discount</code> attribute.
	 * @return the discount - Discount in specific currencies.
	 */
	public Collection<PromotionPriceRow> getDiscount()
	{
		return getDiscount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionValueAndDiscountRow.discount</code> attribute. 
	 * @param value the discount - Discount in specific currencies.
	 */
	public void setDiscount(final SessionContext ctx, final Collection<PromotionPriceRow> value)
	{
		new PartOfHandler<Collection<PromotionPriceRow>>()
		{
			@Override
			protected Collection<PromotionPriceRow> doGetValue(final SessionContext ctx)
			{
				return getDiscount( ctx );
			}
			@Override
			protected void doSetValue(final SessionContext ctx, final Collection<PromotionPriceRow> _value)
			{
				final Collection<PromotionPriceRow> value = _value;
				setProperty(ctx, DISCOUNT,value == null || !value.isEmpty() ? value : null );
			}
		}.setValue( ctx, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionValueAndDiscountRow.discount</code> attribute. 
	 * @param value the discount - Discount in specific currencies.
	 */
	public void setDiscount(final Collection<PromotionPriceRow> value)
	{
		setDiscount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionValueAndDiscountRow.value</code> attribute.
	 * @return the value - Value in specific currencies.
	 */
	public Collection<PromotionPriceRow> getValue(final SessionContext ctx)
	{
		Collection<PromotionPriceRow> coll = (Collection<PromotionPriceRow>)getProperty( ctx, VALUE);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionValueAndDiscountRow.value</code> attribute.
	 * @return the value - Value in specific currencies.
	 */
	public Collection<PromotionPriceRow> getValue()
	{
		return getValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionValueAndDiscountRow.value</code> attribute. 
	 * @param value the value - Value in specific currencies.
	 */
	public void setValue(final SessionContext ctx, final Collection<PromotionPriceRow> value)
	{
		new PartOfHandler<Collection<PromotionPriceRow>>()
		{
			@Override
			protected Collection<PromotionPriceRow> doGetValue(final SessionContext ctx)
			{
				return getValue( ctx );
			}
			@Override
			protected void doSetValue(final SessionContext ctx, final Collection<PromotionPriceRow> _value)
			{
				final Collection<PromotionPriceRow> value = _value;
				setProperty(ctx, VALUE,value == null || !value.isEmpty() ? value : null );
			}
		}.setValue( ctx, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionValueAndDiscountRow.value</code> attribute. 
	 * @param value the value - Value in specific currencies.
	 */
	public void setValue(final Collection<PromotionPriceRow> value)
	{
		setValue( getSession().getSessionContext(), value );
	}
	
}
