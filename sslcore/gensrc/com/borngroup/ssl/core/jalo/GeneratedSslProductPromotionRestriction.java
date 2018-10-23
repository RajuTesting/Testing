/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SslProductPromotionRestriction SslProductPromotionRestriction}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslProductPromotionRestriction extends AbstractPromotionRestriction
{
	/** Qualifier of the <code>SslProductPromotionRestriction.include</code> attribute **/
	public static final String INCLUDE = "include";
	/** Qualifier of the <code>SslProductPromotionRestriction.productCodeList</code> attribute **/
	public static final String PRODUCTCODELIST = "productCodeList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractPromotionRestriction.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(INCLUDE, AttributeMode.INITIAL);
		tmp.put(PRODUCTCODELIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductPromotionRestriction.include</code> attribute.
	 * @return the include - Specifies if this restriction is a positive (true) or
	 * 							negative (false) one.
	 */
	public Boolean isInclude(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, INCLUDE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductPromotionRestriction.include</code> attribute.
	 * @return the include - Specifies if this restriction is a positive (true) or
	 * 							negative (false) one.
	 */
	public Boolean isInclude()
	{
		return isInclude( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductPromotionRestriction.include</code> attribute. 
	 * @return the include - Specifies if this restriction is a positive (true) or
	 * 							negative (false) one.
	 */
	public boolean isIncludeAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isInclude( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductPromotionRestriction.include</code> attribute. 
	 * @return the include - Specifies if this restriction is a positive (true) or
	 * 							negative (false) one.
	 */
	public boolean isIncludeAsPrimitive()
	{
		return isIncludeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductPromotionRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 * 							negative (false) one.
	 */
	public void setInclude(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, INCLUDE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductPromotionRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 * 							negative (false) one.
	 */
	public void setInclude(final Boolean value)
	{
		setInclude( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductPromotionRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 * 							negative (false) one.
	 */
	public void setInclude(final SessionContext ctx, final boolean value)
	{
		setInclude( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductPromotionRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 * 							negative (false) one.
	 */
	public void setInclude(final boolean value)
	{
		setInclude( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductPromotionRestriction.productCodeList</code> attribute.
	 * @return the productCodeList - Specifies the Products for inclusion or exclusion.
	 */
	public Collection<Product> getProductCodeList(final SessionContext ctx)
	{
		Collection<Product> coll = (Collection<Product>)getProperty( ctx, PRODUCTCODELIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductPromotionRestriction.productCodeList</code> attribute.
	 * @return the productCodeList - Specifies the Products for inclusion or exclusion.
	 */
	public Collection<Product> getProductCodeList()
	{
		return getProductCodeList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductPromotionRestriction.productCodeList</code> attribute. 
	 * @param value the productCodeList - Specifies the Products for inclusion or exclusion.
	 */
	public void setProductCodeList(final SessionContext ctx, final Collection<Product> value)
	{
		setProperty(ctx, PRODUCTCODELIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductPromotionRestriction.productCodeList</code> attribute. 
	 * @param value the productCodeList - Specifies the Products for inclusion or exclusion.
	 */
	public void setProductCodeList(final Collection<Product> value)
	{
		setProductCodeList( getSession().getSessionContext(), value );
	}
	
}
