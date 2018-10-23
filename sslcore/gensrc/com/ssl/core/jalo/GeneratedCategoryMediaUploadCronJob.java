/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.ssl.core.jalo.CategoryMediaUploadCronJob CategoryMediaUploadCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCategoryMediaUploadCronJob extends CronJob
{
	/** Qualifier of the <code>CategoryMediaUploadCronJob.category</code> attribute **/
	public static final String CATEGORY = "category";
	/** Qualifier of the <code>CategoryMediaUploadCronJob.includeUnapprovedProducts</code> attribute **/
	public static final String INCLUDEUNAPPROVEDPRODUCTS = "includeUnapprovedProducts";
	/** Qualifier of the <code>CategoryMediaUploadCronJob.extractAltImages</code> attribute **/
	public static final String EXTRACTALTIMAGES = "extractAltImages";
	/** Qualifier of the <code>CategoryMediaUploadCronJob.includezeroInventoryProducts</code> attribute **/
	public static final String INCLUDEZEROINVENTORYPRODUCTS = "includezeroInventoryProducts";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CATEGORY, AttributeMode.INITIAL);
		tmp.put(INCLUDEUNAPPROVEDPRODUCTS, AttributeMode.INITIAL);
		tmp.put(EXTRACTALTIMAGES, AttributeMode.INITIAL);
		tmp.put(INCLUDEZEROINVENTORYPRODUCTS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.category</code> attribute.
	 * @return the category - Category to download media
	 */
	public String getCategory(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CATEGORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.category</code> attribute.
	 * @return the category - Category to download media
	 */
	public String getCategory()
	{
		return getCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.category</code> attribute. 
	 * @param value the category - Category to download media
	 */
	public void setCategory(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CATEGORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.category</code> attribute. 
	 * @param value the category - Category to download media
	 */
	public void setCategory(final String value)
	{
		setCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.extractAltImages</code> attribute.
	 * @return the extractAltImages
	 */
	public Boolean isExtractAltImages(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, EXTRACTALTIMAGES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.extractAltImages</code> attribute.
	 * @return the extractAltImages
	 */
	public Boolean isExtractAltImages()
	{
		return isExtractAltImages( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.extractAltImages</code> attribute. 
	 * @return the extractAltImages
	 */
	public boolean isExtractAltImagesAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isExtractAltImages( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.extractAltImages</code> attribute. 
	 * @return the extractAltImages
	 */
	public boolean isExtractAltImagesAsPrimitive()
	{
		return isExtractAltImagesAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.extractAltImages</code> attribute. 
	 * @param value the extractAltImages
	 */
	public void setExtractAltImages(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, EXTRACTALTIMAGES,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.extractAltImages</code> attribute. 
	 * @param value the extractAltImages
	 */
	public void setExtractAltImages(final Boolean value)
	{
		setExtractAltImages( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.extractAltImages</code> attribute. 
	 * @param value the extractAltImages
	 */
	public void setExtractAltImages(final SessionContext ctx, final boolean value)
	{
		setExtractAltImages( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.extractAltImages</code> attribute. 
	 * @param value the extractAltImages
	 */
	public void setExtractAltImages(final boolean value)
	{
		setExtractAltImages( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.includeUnapprovedProducts</code> attribute.
	 * @return the includeUnapprovedProducts
	 */
	public Boolean isIncludeUnapprovedProducts(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, INCLUDEUNAPPROVEDPRODUCTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.includeUnapprovedProducts</code> attribute.
	 * @return the includeUnapprovedProducts
	 */
	public Boolean isIncludeUnapprovedProducts()
	{
		return isIncludeUnapprovedProducts( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.includeUnapprovedProducts</code> attribute. 
	 * @return the includeUnapprovedProducts
	 */
	public boolean isIncludeUnapprovedProductsAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIncludeUnapprovedProducts( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.includeUnapprovedProducts</code> attribute. 
	 * @return the includeUnapprovedProducts
	 */
	public boolean isIncludeUnapprovedProductsAsPrimitive()
	{
		return isIncludeUnapprovedProductsAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.includeUnapprovedProducts</code> attribute. 
	 * @param value the includeUnapprovedProducts
	 */
	public void setIncludeUnapprovedProducts(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, INCLUDEUNAPPROVEDPRODUCTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.includeUnapprovedProducts</code> attribute. 
	 * @param value the includeUnapprovedProducts
	 */
	public void setIncludeUnapprovedProducts(final Boolean value)
	{
		setIncludeUnapprovedProducts( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.includeUnapprovedProducts</code> attribute. 
	 * @param value the includeUnapprovedProducts
	 */
	public void setIncludeUnapprovedProducts(final SessionContext ctx, final boolean value)
	{
		setIncludeUnapprovedProducts( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.includeUnapprovedProducts</code> attribute. 
	 * @param value the includeUnapprovedProducts
	 */
	public void setIncludeUnapprovedProducts(final boolean value)
	{
		setIncludeUnapprovedProducts( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.includezeroInventoryProducts</code> attribute.
	 * @return the includezeroInventoryProducts
	 */
	public Boolean isIncludezeroInventoryProducts(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, INCLUDEZEROINVENTORYPRODUCTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.includezeroInventoryProducts</code> attribute.
	 * @return the includezeroInventoryProducts
	 */
	public Boolean isIncludezeroInventoryProducts()
	{
		return isIncludezeroInventoryProducts( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.includezeroInventoryProducts</code> attribute. 
	 * @return the includezeroInventoryProducts
	 */
	public boolean isIncludezeroInventoryProductsAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIncludezeroInventoryProducts( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryMediaUploadCronJob.includezeroInventoryProducts</code> attribute. 
	 * @return the includezeroInventoryProducts
	 */
	public boolean isIncludezeroInventoryProductsAsPrimitive()
	{
		return isIncludezeroInventoryProductsAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.includezeroInventoryProducts</code> attribute. 
	 * @param value the includezeroInventoryProducts
	 */
	public void setIncludezeroInventoryProducts(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, INCLUDEZEROINVENTORYPRODUCTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.includezeroInventoryProducts</code> attribute. 
	 * @param value the includezeroInventoryProducts
	 */
	public void setIncludezeroInventoryProducts(final Boolean value)
	{
		setIncludezeroInventoryProducts( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.includezeroInventoryProducts</code> attribute. 
	 * @param value the includezeroInventoryProducts
	 */
	public void setIncludezeroInventoryProducts(final SessionContext ctx, final boolean value)
	{
		setIncludezeroInventoryProducts( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryMediaUploadCronJob.includezeroInventoryProducts</code> attribute. 
	 * @param value the includezeroInventoryProducts
	 */
	public void setIncludezeroInventoryProducts(final boolean value)
	{
		setIncludezeroInventoryProducts( getSession().getSessionContext(), value );
	}
	
}
