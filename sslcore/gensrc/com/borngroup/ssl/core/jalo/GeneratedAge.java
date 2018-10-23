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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem Age}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedAge extends GenericItem
{
	/** Qualifier of the <code>Age.ageCode</code> attribute **/
	public static final String AGECODE = "ageCode";
	/** Qualifier of the <code>Age.ageRange</code> attribute **/
	public static final String AGERANGE = "ageRange";
	/** Qualifier of the <code>Age.searchTermsLists</code> attribute **/
	public static final String SEARCHTERMSLISTS = "searchTermsLists";
	/** Qualifier of the <code>Age.productImprovedSearch</code> attribute **/
	public static final String PRODUCTIMPROVEDSEARCH = "productImprovedSearch";
	/** Relation ordering override parameter constants for ProductAgeRelation from ((sslcore))*/
	protected static String PRODUCTAGERELATION_SRC_ORDERED = "relation.ProductAgeRelation.source.ordered";
	protected static String PRODUCTAGERELATION_TGT_ORDERED = "relation.ProductAgeRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for ProductAgeRelation from ((sslcore))*/
	protected static String PRODUCTAGERELATION_MARKMODIFIED = "relation.ProductAgeRelation.markmodified";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(AGECODE, AttributeMode.INITIAL);
		tmp.put(AGERANGE, AttributeMode.INITIAL);
		tmp.put(SEARCHTERMSLISTS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Age.ageCode</code> attribute.
	 * @return the ageCode
	 */
	public String getAgeCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AGECODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Age.ageCode</code> attribute.
	 * @return the ageCode
	 */
	public String getAgeCode()
	{
		return getAgeCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Age.ageCode</code> attribute. 
	 * @param value the ageCode
	 */
	public void setAgeCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AGECODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Age.ageCode</code> attribute. 
	 * @param value the ageCode
	 */
	public void setAgeCode(final String value)
	{
		setAgeCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Age.ageRange</code> attribute.
	 * @return the ageRange
	 */
	public String getAgeRange(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AGERANGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Age.ageRange</code> attribute.
	 * @return the ageRange
	 */
	public String getAgeRange()
	{
		return getAgeRange( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Age.ageRange</code> attribute. 
	 * @param value the ageRange
	 */
	public void setAgeRange(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AGERANGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Age.ageRange</code> attribute. 
	 * @param value the ageRange
	 */
	public void setAgeRange(final String value)
	{
		setAgeRange( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Age.productImprovedSearch</code> attribute.
	 * @return the productImprovedSearch
	 */
	public Collection<Product> getProductImprovedSearch(final SessionContext ctx)
	{
		final List<Product> items = getLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.PRODUCTAGERELATION,
			null,
			false,
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Age.productImprovedSearch</code> attribute.
	 * @return the productImprovedSearch
	 */
	public Collection<Product> getProductImprovedSearch()
	{
		return getProductImprovedSearch( getSession().getSessionContext() );
	}
	
	public long getProductImprovedSearchCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			false,
			SslCoreConstants.Relations.PRODUCTAGERELATION,
			null
		);
	}
	
	public long getProductImprovedSearchCount()
	{
		return getProductImprovedSearchCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Age.productImprovedSearch</code> attribute. 
	 * @param value the productImprovedSearch
	 */
	public void setProductImprovedSearch(final SessionContext ctx, final Collection<Product> value)
	{
		setLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.PRODUCTAGERELATION,
			null,
			value,
			false,
			false,
			Utilities.getMarkModifiedOverride(PRODUCTAGERELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Age.productImprovedSearch</code> attribute. 
	 * @param value the productImprovedSearch
	 */
	public void setProductImprovedSearch(final Collection<Product> value)
	{
		setProductImprovedSearch( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to productImprovedSearch. 
	 * @param value the item to add to productImprovedSearch
	 */
	public void addToProductImprovedSearch(final SessionContext ctx, final Product value)
	{
		addLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.PRODUCTAGERELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(PRODUCTAGERELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to productImprovedSearch. 
	 * @param value the item to add to productImprovedSearch
	 */
	public void addToProductImprovedSearch(final Product value)
	{
		addToProductImprovedSearch( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from productImprovedSearch. 
	 * @param value the item to remove from productImprovedSearch
	 */
	public void removeFromProductImprovedSearch(final SessionContext ctx, final Product value)
	{
		removeLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.PRODUCTAGERELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(PRODUCTAGERELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from productImprovedSearch. 
	 * @param value the item to remove from productImprovedSearch
	 */
	public void removeFromProductImprovedSearch(final Product value)
	{
		removeFromProductImprovedSearch( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Age.searchTermsLists</code> attribute.
	 * @return the searchTermsLists
	 */
	public String getSearchTermsLists(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SEARCHTERMSLISTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Age.searchTermsLists</code> attribute.
	 * @return the searchTermsLists
	 */
	public String getSearchTermsLists()
	{
		return getSearchTermsLists( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Age.searchTermsLists</code> attribute. 
	 * @param value the searchTermsLists
	 */
	public void setSearchTermsLists(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SEARCHTERMSLISTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Age.searchTermsLists</code> attribute. 
	 * @param value the searchTermsLists
	 */
	public void setSearchTermsLists(final String value)
	{
		setSearchTermsLists( getSession().getSessionContext(), value );
	}
	
}
