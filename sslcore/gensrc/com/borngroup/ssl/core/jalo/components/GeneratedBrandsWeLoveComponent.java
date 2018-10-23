/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.BrandsWeLoveComponent BrandsWeLoveComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBrandsWeLoveComponent extends CMSImageComponent
{
	/** Qualifier of the <code>BrandsWeLoveComponent.brands</code> attribute **/
	public static final String BRANDS = "brands";
	/** Qualifier of the <code>BrandsWeLoveComponent.customUrl</code> attribute **/
	public static final String CUSTOMURL = "customUrl";
	/** Qualifier of the <code>BrandsWeLoveComponent.header</code> attribute **/
	public static final String HEADER = "header";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BRANDS, AttributeMode.INITIAL);
		tmp.put(CUSTOMURL, AttributeMode.INITIAL);
		tmp.put(HEADER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveComponent.brands</code> attribute.
	 * @return the brands - Brands to display
	 */
	public List<Category> getBrands(final SessionContext ctx)
	{
		List<Category> coll = (List<Category>)getProperty( ctx, BRANDS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveComponent.brands</code> attribute.
	 * @return the brands - Brands to display
	 */
	public List<Category> getBrands()
	{
		return getBrands( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveComponent.brands</code> attribute. 
	 * @param value the brands - Brands to display
	 */
	public void setBrands(final SessionContext ctx, final List<Category> value)
	{
		setProperty(ctx, BRANDS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveComponent.brands</code> attribute. 
	 * @param value the brands - Brands to display
	 */
	public void setBrands(final List<Category> value)
	{
		setBrands( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveComponent.customUrl</code> attribute.
	 * @return the customUrl - Brands to display
	 */
	public List<String> getCustomUrl(final SessionContext ctx)
	{
		List<String> coll = (List<String>)getProperty( ctx, CUSTOMURL);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveComponent.customUrl</code> attribute.
	 * @return the customUrl - Brands to display
	 */
	public List<String> getCustomUrl()
	{
		return getCustomUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveComponent.customUrl</code> attribute. 
	 * @param value the customUrl - Brands to display
	 */
	public void setCustomUrl(final SessionContext ctx, final List<String> value)
	{
		setProperty(ctx, CUSTOMURL,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveComponent.customUrl</code> attribute. 
	 * @param value the customUrl - Brands to display
	 */
	public void setCustomUrl(final List<String> value)
	{
		setCustomUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveComponent.header</code> attribute.
	 * @return the header - Brands Header
	 */
	public String getHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandsWeLoveComponent.header</code> attribute.
	 * @return the header - Brands Header
	 */
	public String getHeader()
	{
		return getHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveComponent.header</code> attribute. 
	 * @param value the header - Brands Header
	 */
	public void setHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandsWeLoveComponent.header</code> attribute. 
	 * @param value the header - Brands Header
	 */
	public void setHeader(final String value)
	{
		setHeader( getSession().getSessionContext(), value );
	}
	
}
