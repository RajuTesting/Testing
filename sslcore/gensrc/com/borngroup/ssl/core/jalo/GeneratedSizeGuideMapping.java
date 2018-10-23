/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.ApparelProduct;
import com.borngroup.ssl.core.jalo.BrandMapping;
import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SizeGuideMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSizeGuideMapping extends GenericItem
{
	/** Qualifier of the <code>SizeGuideMapping.category</code> attribute **/
	public static final String CATEGORY = "category";
	/** Qualifier of the <code>SizeGuideMapping.brand</code> attribute **/
	public static final String BRAND = "brand";
	/** Qualifier of the <code>SizeGuideMapping.product</code> attribute **/
	public static final String PRODUCT = "product";
	/** Qualifier of the <code>SizeGuideMapping.sizeGuideHTML</code> attribute **/
	public static final String SIZEGUIDEHTML = "sizeGuideHTML";
	/** Qualifier of the <code>SizeGuideMapping.altBrandDesc</code> attribute **/
	public static final String ALTBRANDDESC = "altBrandDesc";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CATEGORY, AttributeMode.INITIAL);
		tmp.put(BRAND, AttributeMode.INITIAL);
		tmp.put(PRODUCT, AttributeMode.INITIAL);
		tmp.put(SIZEGUIDEHTML, AttributeMode.INITIAL);
		tmp.put(ALTBRANDDESC, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeGuideMapping.altBrandDesc</code> attribute.
	 * @return the altBrandDesc - Brand Desc
	 */
	public String getAltBrandDesc(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ALTBRANDDESC);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeGuideMapping.altBrandDesc</code> attribute.
	 * @return the altBrandDesc - Brand Desc
	 */
	public String getAltBrandDesc()
	{
		return getAltBrandDesc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeGuideMapping.altBrandDesc</code> attribute. 
	 * @param value the altBrandDesc - Brand Desc
	 */
	public void setAltBrandDesc(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ALTBRANDDESC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeGuideMapping.altBrandDesc</code> attribute. 
	 * @param value the altBrandDesc - Brand Desc
	 */
	public void setAltBrandDesc(final String value)
	{
		setAltBrandDesc( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeGuideMapping.brand</code> attribute.
	 * @return the brand - Brand field
	 */
	public BrandMapping getBrand(final SessionContext ctx)
	{
		return (BrandMapping)getProperty( ctx, BRAND);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeGuideMapping.brand</code> attribute.
	 * @return the brand - Brand field
	 */
	public BrandMapping getBrand()
	{
		return getBrand( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeGuideMapping.brand</code> attribute. 
	 * @param value the brand - Brand field
	 */
	public void setBrand(final SessionContext ctx, final BrandMapping value)
	{
		setProperty(ctx, BRAND,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeGuideMapping.brand</code> attribute. 
	 * @param value the brand - Brand field
	 */
	public void setBrand(final BrandMapping value)
	{
		setBrand( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeGuideMapping.category</code> attribute.
	 * @return the category - Category field
	 */
	public Category getCategory(final SessionContext ctx)
	{
		return (Category)getProperty( ctx, CATEGORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeGuideMapping.category</code> attribute.
	 * @return the category - Category field
	 */
	public Category getCategory()
	{
		return getCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeGuideMapping.category</code> attribute. 
	 * @param value the category - Category field
	 */
	public void setCategory(final SessionContext ctx, final Category value)
	{
		setProperty(ctx, CATEGORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeGuideMapping.category</code> attribute. 
	 * @param value the category - Category field
	 */
	public void setCategory(final Category value)
	{
		setCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeGuideMapping.product</code> attribute.
	 * @return the product - Product field
	 */
	public ApparelProduct getProduct(final SessionContext ctx)
	{
		return (ApparelProduct)getProperty( ctx, PRODUCT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeGuideMapping.product</code> attribute.
	 * @return the product - Product field
	 */
	public ApparelProduct getProduct()
	{
		return getProduct( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeGuideMapping.product</code> attribute. 
	 * @param value the product - Product field
	 */
	public void setProduct(final SessionContext ctx, final ApparelProduct value)
	{
		setProperty(ctx, PRODUCT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeGuideMapping.product</code> attribute. 
	 * @param value the product - Product field
	 */
	public void setProduct(final ApparelProduct value)
	{
		setProduct( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeGuideMapping.sizeGuideHTML</code> attribute.
	 * @return the sizeGuideHTML
	 */
	public String getSizeGuideHTML(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SIZEGUIDEHTML);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeGuideMapping.sizeGuideHTML</code> attribute.
	 * @return the sizeGuideHTML
	 */
	public String getSizeGuideHTML()
	{
		return getSizeGuideHTML( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeGuideMapping.sizeGuideHTML</code> attribute. 
	 * @param value the sizeGuideHTML
	 */
	public void setSizeGuideHTML(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SIZEGUIDEHTML,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeGuideMapping.sizeGuideHTML</code> attribute. 
	 * @param value the sizeGuideHTML
	 */
	public void setSizeGuideHTML(final String value)
	{
		setSizeGuideHTML( getSession().getSessionContext(), value );
	}
	
}
