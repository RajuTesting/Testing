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
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.product.Product;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.ApparelProduct ApparelProduct}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedApparelProduct extends Product
{
	/** Qualifier of the <code>ApparelProduct.genders</code> attribute **/
	public static final String GENDERS = "genders";
	/** Qualifier of the <code>ApparelProduct.attributionFlag</code> attribute **/
	public static final String ATTRIBUTIONFLAG = "attributionFlag";
	/** Qualifier of the <code>ApparelProduct.approvalDate</code> attribute **/
	public static final String APPROVALDATE = "approvalDate";
	/** Qualifier of the <code>ApparelProduct.primaryCategory</code> attribute **/
	public static final String PRIMARYCATEGORY = "primaryCategory";
	/** Qualifier of the <code>ApparelProduct.primaryVendor</code> attribute **/
	public static final String PRIMARYVENDOR = "primaryVendor";
	/** Qualifier of the <code>ApparelProduct.similarProducts</code> attribute **/
	public static final String SIMILARPRODUCTS = "similarProducts";
	/** Qualifier of the <code>ApparelProduct.shopTheLookSKUs</code> attribute **/
	public static final String SHOPTHELOOKSKUS = "shopTheLookSKUs";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Product.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(GENDERS, AttributeMode.INITIAL);
		tmp.put(ATTRIBUTIONFLAG, AttributeMode.INITIAL);
		tmp.put(APPROVALDATE, AttributeMode.INITIAL);
		tmp.put(PRIMARYCATEGORY, AttributeMode.INITIAL);
		tmp.put(PRIMARYVENDOR, AttributeMode.INITIAL);
		tmp.put(SIMILARPRODUCTS, AttributeMode.INITIAL);
		tmp.put(SHOPTHELOOKSKUS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.approvalDate</code> attribute.
	 * @return the approvalDate
	 */
	public Date getApprovalDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, APPROVALDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.approvalDate</code> attribute.
	 * @return the approvalDate
	 */
	public Date getApprovalDate()
	{
		return getApprovalDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.approvalDate</code> attribute. 
	 * @param value the approvalDate
	 */
	public void setApprovalDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, APPROVALDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.approvalDate</code> attribute. 
	 * @param value the approvalDate
	 */
	public void setApprovalDate(final Date value)
	{
		setApprovalDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.attributionFlag</code> attribute.
	 * @return the attributionFlag - Flag will be false by default, It will set to true if
	 *                             all mandatory fields have values
	 */
	public Boolean isAttributionFlag(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ATTRIBUTIONFLAG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.attributionFlag</code> attribute.
	 * @return the attributionFlag - Flag will be false by default, It will set to true if
	 *                             all mandatory fields have values
	 */
	public Boolean isAttributionFlag()
	{
		return isAttributionFlag( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.attributionFlag</code> attribute. 
	 * @return the attributionFlag - Flag will be false by default, It will set to true if
	 *                             all mandatory fields have values
	 */
	public boolean isAttributionFlagAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isAttributionFlag( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.attributionFlag</code> attribute. 
	 * @return the attributionFlag - Flag will be false by default, It will set to true if
	 *                             all mandatory fields have values
	 */
	public boolean isAttributionFlagAsPrimitive()
	{
		return isAttributionFlagAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.attributionFlag</code> attribute. 
	 * @param value the attributionFlag - Flag will be false by default, It will set to true if
	 *                             all mandatory fields have values
	 */
	public void setAttributionFlag(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ATTRIBUTIONFLAG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.attributionFlag</code> attribute. 
	 * @param value the attributionFlag - Flag will be false by default, It will set to true if
	 *                             all mandatory fields have values
	 */
	public void setAttributionFlag(final Boolean value)
	{
		setAttributionFlag( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.attributionFlag</code> attribute. 
	 * @param value the attributionFlag - Flag will be false by default, It will set to true if
	 *                             all mandatory fields have values
	 */
	public void setAttributionFlag(final SessionContext ctx, final boolean value)
	{
		setAttributionFlag( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.attributionFlag</code> attribute. 
	 * @param value the attributionFlag - Flag will be false by default, It will set to true if
	 *                             all mandatory fields have values
	 */
	public void setAttributionFlag(final boolean value)
	{
		setAttributionFlag( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.genders</code> attribute.
	 * @return the genders - List of genders that the ApparelProduct is designed
	 *                             for
	 */
	public List<EnumerationValue> getGenders(final SessionContext ctx)
	{
		List<EnumerationValue> coll = (List<EnumerationValue>)getProperty( ctx, GENDERS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.genders</code> attribute.
	 * @return the genders - List of genders that the ApparelProduct is designed
	 *                             for
	 */
	public List<EnumerationValue> getGenders()
	{
		return getGenders( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.genders</code> attribute. 
	 * @param value the genders - List of genders that the ApparelProduct is designed
	 *                             for
	 */
	public void setGenders(final SessionContext ctx, final List<EnumerationValue> value)
	{
		setProperty(ctx, GENDERS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.genders</code> attribute. 
	 * @param value the genders - List of genders that the ApparelProduct is designed
	 *                             for
	 */
	public void setGenders(final List<EnumerationValue> value)
	{
		setGenders( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.primaryCategory</code> attribute.
	 * @return the primaryCategory
	 */
	public String getPrimaryCategory(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PRIMARYCATEGORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.primaryCategory</code> attribute.
	 * @return the primaryCategory
	 */
	public String getPrimaryCategory()
	{
		return getPrimaryCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.primaryCategory</code> attribute. 
	 * @param value the primaryCategory
	 */
	public void setPrimaryCategory(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PRIMARYCATEGORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.primaryCategory</code> attribute. 
	 * @param value the primaryCategory
	 */
	public void setPrimaryCategory(final String value)
	{
		setPrimaryCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.primaryVendor</code> attribute.
	 * @return the primaryVendor - primary vendor
	 */
	public String getPrimaryVendor(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PRIMARYVENDOR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.primaryVendor</code> attribute.
	 * @return the primaryVendor - primary vendor
	 */
	public String getPrimaryVendor()
	{
		return getPrimaryVendor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.primaryVendor</code> attribute. 
	 * @param value the primaryVendor - primary vendor
	 */
	public void setPrimaryVendor(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PRIMARYVENDOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.primaryVendor</code> attribute. 
	 * @param value the primaryVendor - primary vendor
	 */
	public void setPrimaryVendor(final String value)
	{
		setPrimaryVendor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.shopTheLookSKUs</code> attribute.
	 * @return the shopTheLookSKUs - To indicate Shop the look Child SKUs
	 */
	public String getShopTheLookSKUs(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SHOPTHELOOKSKUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.shopTheLookSKUs</code> attribute.
	 * @return the shopTheLookSKUs - To indicate Shop the look Child SKUs
	 */
	public String getShopTheLookSKUs()
	{
		return getShopTheLookSKUs( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.shopTheLookSKUs</code> attribute. 
	 * @param value the shopTheLookSKUs - To indicate Shop the look Child SKUs
	 */
	public void setShopTheLookSKUs(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SHOPTHELOOKSKUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.shopTheLookSKUs</code> attribute. 
	 * @param value the shopTheLookSKUs - To indicate Shop the look Child SKUs
	 */
	public void setShopTheLookSKUs(final String value)
	{
		setShopTheLookSKUs( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.similarProducts</code> attribute.
	 * @return the similarProducts - Similar Products
	 */
	public List<String> getSimilarProducts(final SessionContext ctx)
	{
		List<String> coll = (List<String>)getProperty( ctx, SIMILARPRODUCTS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelProduct.similarProducts</code> attribute.
	 * @return the similarProducts - Similar Products
	 */
	public List<String> getSimilarProducts()
	{
		return getSimilarProducts( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.similarProducts</code> attribute. 
	 * @param value the similarProducts - Similar Products
	 */
	public void setSimilarProducts(final SessionContext ctx, final List<String> value)
	{
		setProperty(ctx, SIMILARPRODUCTS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelProduct.similarProducts</code> attribute. 
	 * @param value the similarProducts - Similar Products
	 */
	public void setSimilarProducts(final List<String> value)
	{
		setSimilarProducts( getSession().getSessionContext(), value );
	}
	
}
