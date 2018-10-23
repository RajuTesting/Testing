/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.ssl.core.jalo.BestSellerComponent BestSellerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBestSellerComponent extends GenericItem
{
	/** Qualifier of the <code>BestSellerComponent.bestSellerProducts</code> attribute **/
	public static final String BESTSELLERPRODUCTS = "bestSellerProducts";
	/** Qualifier of the <code>BestSellerComponent.date</code> attribute **/
	public static final String DATE = "date";
	/** Qualifier of the <code>BestSellerComponent.category</code> attribute **/
	public static final String CATEGORY = "category";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(BESTSELLERPRODUCTS, AttributeMode.INITIAL);
		tmp.put(DATE, AttributeMode.INITIAL);
		tmp.put(CATEGORY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellerComponent.bestSellerProducts</code> attribute.
	 * @return the bestSellerProducts
	 */
	public String getBestSellerProducts(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BESTSELLERPRODUCTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellerComponent.bestSellerProducts</code> attribute.
	 * @return the bestSellerProducts
	 */
	public String getBestSellerProducts()
	{
		return getBestSellerProducts( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellerComponent.bestSellerProducts</code> attribute. 
	 * @param value the bestSellerProducts
	 */
	public void setBestSellerProducts(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BESTSELLERPRODUCTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellerComponent.bestSellerProducts</code> attribute. 
	 * @param value the bestSellerProducts
	 */
	public void setBestSellerProducts(final String value)
	{
		setBestSellerProducts( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellerComponent.category</code> attribute.
	 * @return the category
	 */
	public String getCategory(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CATEGORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellerComponent.category</code> attribute.
	 * @return the category
	 */
	public String getCategory()
	{
		return getCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellerComponent.category</code> attribute. 
	 * @param value the category
	 */
	public void setCategory(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CATEGORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellerComponent.category</code> attribute. 
	 * @param value the category
	 */
	public void setCategory(final String value)
	{
		setCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellerComponent.date</code> attribute.
	 * @return the date
	 */
	public Date getDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, DATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellerComponent.date</code> attribute.
	 * @return the date
	 */
	public Date getDate()
	{
		return getDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellerComponent.date</code> attribute. 
	 * @param value the date
	 */
	public void setDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, DATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellerComponent.date</code> attribute. 
	 * @param value the date
	 */
	public void setDate(final Date value)
	{
		setDate( getSession().getSessionContext(), value );
	}
	
}
