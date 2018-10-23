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
import de.hybris.platform.voucher.jalo.OrderRestriction;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLOrderRestriction SslOrderWithProductRestriction}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLOrderRestriction extends OrderRestriction
{
	/** Qualifier of the <code>SslOrderWithProductRestriction.include</code> attribute **/
	public static final String INCLUDE = "include";
	/** Qualifier of the <code>SslOrderWithProductRestriction.products</code> attribute **/
	public static final String PRODUCTS = "products";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(OrderRestriction.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(INCLUDE, AttributeMode.INITIAL);
		tmp.put(PRODUCTS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderWithProductRestriction.include</code> attribute.
	 * @return the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public Boolean isInclude(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, INCLUDE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderWithProductRestriction.include</code> attribute.
	 * @return the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public Boolean isInclude()
	{
		return isInclude( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderWithProductRestriction.include</code> attribute. 
	 * @return the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public boolean isIncludeAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isInclude( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderWithProductRestriction.include</code> attribute. 
	 * @return the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public boolean isIncludeAsPrimitive()
	{
		return isIncludeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderWithProductRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public void setInclude(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, INCLUDE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderWithProductRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public void setInclude(final Boolean value)
	{
		setInclude( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderWithProductRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public void setInclude(final SessionContext ctx, final boolean value)
	{
		setInclude( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderWithProductRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public void setInclude(final boolean value)
	{
		setInclude( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderWithProductRestriction.products</code> attribute.
	 * @return the products - the products the given voucher is valid for.
	 */
	public Collection<Product> getProducts(final SessionContext ctx)
	{
		Collection<Product> coll = (Collection<Product>)getProperty( ctx, PRODUCTS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslOrderWithProductRestriction.products</code> attribute.
	 * @return the products - the products the given voucher is valid for.
	 */
	public Collection<Product> getProducts()
	{
		return getProducts( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderWithProductRestriction.products</code> attribute. 
	 * @param value the products - the products the given voucher is valid for.
	 */
	public void setProducts(final SessionContext ctx, final Collection<Product> value)
	{
		setProperty(ctx, PRODUCTS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslOrderWithProductRestriction.products</code> attribute. 
	 * @param value the products - the products the given voucher is valid for.
	 */
	public void setProducts(final Collection<Product> value)
	{
		setProducts( getSession().getSessionContext(), value );
	}
	
}
