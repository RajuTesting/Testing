/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLProductDetailSimilarProductComponent SSLProductDetailSimilarProductComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLProductDetailSimilarProductComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLProductDetailSimilarProductComponent.maxLimit</code> attribute **/
	public static final String MAXLIMIT = "maxLimit";
	/** Qualifier of the <code>SSLProductDetailSimilarProductComponent.minLimit</code> attribute **/
	public static final String MINLIMIT = "minLimit";
	/** Qualifier of the <code>SSLProductDetailSimilarProductComponent.filterByStock</code> attribute **/
	public static final String FILTERBYSTOCK = "filterByStock";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(MAXLIMIT, AttributeMode.INITIAL);
		tmp.put(MINLIMIT, AttributeMode.INITIAL);
		tmp.put(FILTERBYSTOCK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.filterByStock</code> attribute.
	 * @return the filterByStock - To apply check of stock availailty on the similar products
	 */
	public Boolean isFilterByStock(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, FILTERBYSTOCK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.filterByStock</code> attribute.
	 * @return the filterByStock - To apply check of stock availailty on the similar products
	 */
	public Boolean isFilterByStock()
	{
		return isFilterByStock( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.filterByStock</code> attribute. 
	 * @return the filterByStock - To apply check of stock availailty on the similar products
	 */
	public boolean isFilterByStockAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isFilterByStock( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.filterByStock</code> attribute. 
	 * @return the filterByStock - To apply check of stock availailty on the similar products
	 */
	public boolean isFilterByStockAsPrimitive()
	{
		return isFilterByStockAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.filterByStock</code> attribute. 
	 * @param value the filterByStock - To apply check of stock availailty on the similar products
	 */
	public void setFilterByStock(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, FILTERBYSTOCK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.filterByStock</code> attribute. 
	 * @param value the filterByStock - To apply check of stock availailty on the similar products
	 */
	public void setFilterByStock(final Boolean value)
	{
		setFilterByStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.filterByStock</code> attribute. 
	 * @param value the filterByStock - To apply check of stock availailty on the similar products
	 */
	public void setFilterByStock(final SessionContext ctx, final boolean value)
	{
		setFilterByStock( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.filterByStock</code> attribute. 
	 * @param value the filterByStock - To apply check of stock availailty on the similar products
	 */
	public void setFilterByStock(final boolean value)
	{
		setFilterByStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.maxLimit</code> attribute.
	 * @return the maxLimit - Limit for similar products to be displayed
	 */
	public Integer getMaxLimit(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, MAXLIMIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.maxLimit</code> attribute.
	 * @return the maxLimit - Limit for similar products to be displayed
	 */
	public Integer getMaxLimit()
	{
		return getMaxLimit( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.maxLimit</code> attribute. 
	 * @return the maxLimit - Limit for similar products to be displayed
	 */
	public int getMaxLimitAsPrimitive(final SessionContext ctx)
	{
		Integer value = getMaxLimit( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.maxLimit</code> attribute. 
	 * @return the maxLimit - Limit for similar products to be displayed
	 */
	public int getMaxLimitAsPrimitive()
	{
		return getMaxLimitAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.maxLimit</code> attribute. 
	 * @param value the maxLimit - Limit for similar products to be displayed
	 */
	public void setMaxLimit(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, MAXLIMIT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.maxLimit</code> attribute. 
	 * @param value the maxLimit - Limit for similar products to be displayed
	 */
	public void setMaxLimit(final Integer value)
	{
		setMaxLimit( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.maxLimit</code> attribute. 
	 * @param value the maxLimit - Limit for similar products to be displayed
	 */
	public void setMaxLimit(final SessionContext ctx, final int value)
	{
		setMaxLimit( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.maxLimit</code> attribute. 
	 * @param value the maxLimit - Limit for similar products to be displayed
	 */
	public void setMaxLimit(final int value)
	{
		setMaxLimit( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.minLimit</code> attribute.
	 * @return the minLimit - Limit for recently viewed products to be displayed
	 */
	public Integer getMinLimit(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, MINLIMIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.minLimit</code> attribute.
	 * @return the minLimit - Limit for recently viewed products to be displayed
	 */
	public Integer getMinLimit()
	{
		return getMinLimit( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.minLimit</code> attribute. 
	 * @return the minLimit - Limit for recently viewed products to be displayed
	 */
	public int getMinLimitAsPrimitive(final SessionContext ctx)
	{
		Integer value = getMinLimit( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailSimilarProductComponent.minLimit</code> attribute. 
	 * @return the minLimit - Limit for recently viewed products to be displayed
	 */
	public int getMinLimitAsPrimitive()
	{
		return getMinLimitAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.minLimit</code> attribute. 
	 * @param value the minLimit - Limit for recently viewed products to be displayed
	 */
	public void setMinLimit(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, MINLIMIT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.minLimit</code> attribute. 
	 * @param value the minLimit - Limit for recently viewed products to be displayed
	 */
	public void setMinLimit(final Integer value)
	{
		setMinLimit( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.minLimit</code> attribute. 
	 * @param value the minLimit - Limit for recently viewed products to be displayed
	 */
	public void setMinLimit(final SessionContext ctx, final int value)
	{
		setMinLimit( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailSimilarProductComponent.minLimit</code> attribute. 
	 * @param value the minLimit - Limit for recently viewed products to be displayed
	 */
	public void setMinLimit(final int value)
	{
		setMinLimit( getSession().getSessionContext(), value );
	}
	
}
