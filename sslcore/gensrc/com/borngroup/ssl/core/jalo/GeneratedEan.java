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
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem Ean}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedEan extends GenericItem
{
	/** Qualifier of the <code>Ean.eanNumber</code> attribute **/
	public static final String EANNUMBER = "eanNumber";
	/** Qualifier of the <code>Ean.products</code> attribute **/
	public static final String PRODUCTS = "products";
	/** Relation ordering override parameter constants for Product2EanRelation from ((sslcore))*/
	protected static String PRODUCT2EANRELATION_SRC_ORDERED = "relation.Product2EanRelation.source.ordered";
	protected static String PRODUCT2EANRELATION_TGT_ORDERED = "relation.Product2EanRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for Product2EanRelation from ((sslcore))*/
	protected static String PRODUCT2EANRELATION_MARKMODIFIED = "relation.Product2EanRelation.markmodified";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(EANNUMBER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ean.eanNumber</code> attribute.
	 * @return the eanNumber - Ean Number
	 */
	public String getEanNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EANNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ean.eanNumber</code> attribute.
	 * @return the eanNumber - Ean Number
	 */
	public String getEanNumber()
	{
		return getEanNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ean.eanNumber</code> attribute. 
	 * @param value the eanNumber - Ean Number
	 */
	public void setEanNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EANNUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ean.eanNumber</code> attribute. 
	 * @param value the eanNumber - Ean Number
	 */
	public void setEanNumber(final String value)
	{
		setEanNumber( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ean.products</code> attribute.
	 * @return the products
	 */
	public Set<Product> getProducts(final SessionContext ctx)
	{
		final List<Product> items = getLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.PRODUCT2EANRELATION,
			null,
			false,
			false
		);
		return new LinkedHashSet<Product>(items);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ean.products</code> attribute.
	 * @return the products
	 */
	public Set<Product> getProducts()
	{
		return getProducts( getSession().getSessionContext() );
	}
	
	public long getProductsCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			true,
			SslCoreConstants.Relations.PRODUCT2EANRELATION,
			null
		);
	}
	
	public long getProductsCount()
	{
		return getProductsCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ean.products</code> attribute. 
	 * @param value the products
	 */
	public void setProducts(final SessionContext ctx, final Set<Product> value)
	{
		setLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.PRODUCT2EANRELATION,
			null,
			value,
			false,
			false,
			Utilities.getMarkModifiedOverride(PRODUCT2EANRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ean.products</code> attribute. 
	 * @param value the products
	 */
	public void setProducts(final Set<Product> value)
	{
		setProducts( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to products. 
	 * @param value the item to add to products
	 */
	public void addToProducts(final SessionContext ctx, final Product value)
	{
		addLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.PRODUCT2EANRELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(PRODUCT2EANRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to products. 
	 * @param value the item to add to products
	 */
	public void addToProducts(final Product value)
	{
		addToProducts( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from products. 
	 * @param value the item to remove from products
	 */
	public void removeFromProducts(final SessionContext ctx, final Product value)
	{
		removeLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.PRODUCT2EANRELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(PRODUCT2EANRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from products. 
	 * @param value the item to remove from products
	 */
	public void removeFromProducts(final Product value)
	{
		removeFromProducts( getSession().getSessionContext(), value );
	}
	
}
