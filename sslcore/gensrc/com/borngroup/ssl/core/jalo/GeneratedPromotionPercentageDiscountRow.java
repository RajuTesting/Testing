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
import de.hybris.platform.jalo.c2l.Currency;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.PromotionPercentageDiscountRow PromotionPercentageDiscountRow}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionPercentageDiscountRow extends GenericItem
{
	/** Qualifier of the <code>PromotionPercentageDiscountRow.currency</code> attribute **/
	public static final String CURRENCY = "currency";
	/** Qualifier of the <code>PromotionPercentageDiscountRow.percentageDiscount</code> attribute **/
	public static final String PERCENTAGEDISCOUNT = "percentageDiscount";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CURRENCY, AttributeMode.INITIAL);
		tmp.put(PERCENTAGEDISCOUNT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionPercentageDiscountRow.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency(final SessionContext ctx)
	{
		return (Currency)getProperty( ctx, CURRENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionPercentageDiscountRow.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency()
	{
		return getCurrency( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionPercentageDiscountRow.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final SessionContext ctx, final Currency value)
	{
		setProperty(ctx, CURRENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionPercentageDiscountRow.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final Currency value)
	{
		setCurrency( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionPercentageDiscountRow.percentageDiscount</code> attribute.
	 * @return the percentageDiscount - Percentage discount.
	 */
	public Double getPercentageDiscount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, PERCENTAGEDISCOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionPercentageDiscountRow.percentageDiscount</code> attribute.
	 * @return the percentageDiscount - Percentage discount.
	 */
	public Double getPercentageDiscount()
	{
		return getPercentageDiscount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionPercentageDiscountRow.percentageDiscount</code> attribute. 
	 * @return the percentageDiscount - Percentage discount.
	 */
	public double getPercentageDiscountAsPrimitive(final SessionContext ctx)
	{
		Double value = getPercentageDiscount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionPercentageDiscountRow.percentageDiscount</code> attribute. 
	 * @return the percentageDiscount - Percentage discount.
	 */
	public double getPercentageDiscountAsPrimitive()
	{
		return getPercentageDiscountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionPercentageDiscountRow.percentageDiscount</code> attribute. 
	 * @param value the percentageDiscount - Percentage discount.
	 */
	public void setPercentageDiscount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, PERCENTAGEDISCOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionPercentageDiscountRow.percentageDiscount</code> attribute. 
	 * @param value the percentageDiscount - Percentage discount.
	 */
	public void setPercentageDiscount(final Double value)
	{
		setPercentageDiscount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionPercentageDiscountRow.percentageDiscount</code> attribute. 
	 * @param value the percentageDiscount - Percentage discount.
	 */
	public void setPercentageDiscount(final SessionContext ctx, final double value)
	{
		setPercentageDiscount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionPercentageDiscountRow.percentageDiscount</code> attribute. 
	 * @param value the percentageDiscount - Percentage discount.
	 */
	public void setPercentageDiscount(final double value)
	{
		setPercentageDiscount( getSession().getSessionContext(), value );
	}
	
}
