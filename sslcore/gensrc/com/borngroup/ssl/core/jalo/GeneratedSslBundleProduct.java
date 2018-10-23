/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.ApparelProduct;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SslBundleProduct SslBundleProduct}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslBundleProduct extends ApparelProduct
{
	/** Qualifier of the <code>SslBundleProduct.bundleItems</code> attribute **/
	public static final String BUNDLEITEMS = "bundleItems";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(ApparelProduct.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BUNDLEITEMS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBundleProduct.bundleItems</code> attribute.
	 * @return the bundleItems - Items in the bundle
	 */
	public List<Product> getBundleItems(final SessionContext ctx)
	{
		List<Product> coll = (List<Product>)getProperty( ctx, BUNDLEITEMS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBundleProduct.bundleItems</code> attribute.
	 * @return the bundleItems - Items in the bundle
	 */
	public List<Product> getBundleItems()
	{
		return getBundleItems( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBundleProduct.bundleItems</code> attribute. 
	 * @param value the bundleItems - Items in the bundle
	 */
	public void setBundleItems(final SessionContext ctx, final List<Product> value)
	{
		setProperty(ctx, BUNDLEITEMS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBundleProduct.bundleItems</code> attribute. 
	 * @param value the bundleItems - Items in the bundle
	 */
	public void setBundleItems(final List<Product> value)
	{
		setBundleItems( getSession().getSessionContext(), value );
	}
	
}
