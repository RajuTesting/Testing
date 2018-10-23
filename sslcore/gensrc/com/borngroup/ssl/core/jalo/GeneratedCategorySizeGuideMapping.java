/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem CategorySizeGuideMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCategorySizeGuideMapping extends GenericItem
{
	/** Qualifier of the <code>CategorySizeGuideMapping.category</code> attribute **/
	public static final String CATEGORY = "category";
	/** Qualifier of the <code>CategorySizeGuideMapping.brand</code> attribute **/
	public static final String BRAND = "brand";
	/** Qualifier of the <code>CategorySizeGuideMapping.sizeguide</code> attribute **/
	public static final String SIZEGUIDE = "sizeguide";
	/** Qualifier of the <code>CategorySizeGuideMapping.categorySizeGuideHTML</code> attribute **/
	public static final String CATEGORYSIZEGUIDEHTML = "categorySizeGuideHTML";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CATEGORY, AttributeMode.INITIAL);
		tmp.put(BRAND, AttributeMode.INITIAL);
		tmp.put(SIZEGUIDE, AttributeMode.INITIAL);
		tmp.put(CATEGORYSIZEGUIDEHTML, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategorySizeGuideMapping.brand</code> attribute.
	 * @return the brand - Brand field
	 */
	public Category getBrand(final SessionContext ctx)
	{
		return (Category)getProperty( ctx, BRAND);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategorySizeGuideMapping.brand</code> attribute.
	 * @return the brand - Brand field
	 */
	public Category getBrand()
	{
		return getBrand( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategorySizeGuideMapping.brand</code> attribute. 
	 * @param value the brand - Brand field
	 */
	public void setBrand(final SessionContext ctx, final Category value)
	{
		setProperty(ctx, BRAND,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategorySizeGuideMapping.brand</code> attribute. 
	 * @param value the brand - Brand field
	 */
	public void setBrand(final Category value)
	{
		setBrand( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategorySizeGuideMapping.category</code> attribute.
	 * @return the category - Category field
	 */
	public Category getCategory(final SessionContext ctx)
	{
		return (Category)getProperty( ctx, CATEGORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategorySizeGuideMapping.category</code> attribute.
	 * @return the category - Category field
	 */
	public Category getCategory()
	{
		return getCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategorySizeGuideMapping.category</code> attribute. 
	 * @param value the category - Category field
	 */
	public void setCategory(final SessionContext ctx, final Category value)
	{
		setProperty(ctx, CATEGORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategorySizeGuideMapping.category</code> attribute. 
	 * @param value the category - Category field
	 */
	public void setCategory(final Category value)
	{
		setCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategorySizeGuideMapping.categorySizeGuideHTML</code> attribute.
	 * @return the categorySizeGuideHTML
	 */
	public String getCategorySizeGuideHTML(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CATEGORYSIZEGUIDEHTML);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategorySizeGuideMapping.categorySizeGuideHTML</code> attribute.
	 * @return the categorySizeGuideHTML
	 */
	public String getCategorySizeGuideHTML()
	{
		return getCategorySizeGuideHTML( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategorySizeGuideMapping.categorySizeGuideHTML</code> attribute. 
	 * @param value the categorySizeGuideHTML
	 */
	public void setCategorySizeGuideHTML(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CATEGORYSIZEGUIDEHTML,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategorySizeGuideMapping.categorySizeGuideHTML</code> attribute. 
	 * @param value the categorySizeGuideHTML
	 */
	public void setCategorySizeGuideHTML(final String value)
	{
		setCategorySizeGuideHTML( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategorySizeGuideMapping.sizeguide</code> attribute.
	 * @return the sizeguide - size guide html
	 */
	public String getSizeguide(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SIZEGUIDE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategorySizeGuideMapping.sizeguide</code> attribute.
	 * @return the sizeguide - size guide html
	 */
	public String getSizeguide()
	{
		return getSizeguide( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategorySizeGuideMapping.sizeguide</code> attribute. 
	 * @param value the sizeguide - size guide html
	 */
	public void setSizeguide(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SIZEGUIDE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategorySizeGuideMapping.sizeguide</code> attribute. 
	 * @param value the sizeguide - size guide html
	 */
	public void setSizeguide(final String value)
	{
		setSizeguide( getSession().getSessionContext(), value );
	}
	
}
