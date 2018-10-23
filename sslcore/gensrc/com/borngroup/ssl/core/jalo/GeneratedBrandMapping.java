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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem BrandMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBrandMapping extends GenericItem
{
	/** Qualifier of the <code>BrandMapping.brandCode</code> attribute **/
	public static final String BRANDCODE = "brandCode";
	/** Qualifier of the <code>BrandMapping.brandDesc</code> attribute **/
	public static final String BRANDDESC = "brandDesc";
	/** Qualifier of the <code>BrandMapping.altBrandCode</code> attribute **/
	public static final String ALTBRANDCODE = "altBrandCode";
	/** Qualifier of the <code>BrandMapping.categoryCode</code> attribute **/
	public static final String CATEGORYCODE = "categoryCode";
	/** Qualifier of the <code>BrandMapping.altBrandDesc</code> attribute **/
	public static final String ALTBRANDDESC = "altBrandDesc";
	/** Qualifier of the <code>BrandMapping.brandInfo</code> attribute **/
	public static final String BRANDINFO = "brandInfo";
	/** Qualifier of the <code>BrandMapping.brandLogo</code> attribute **/
	public static final String BRANDLOGO = "brandLogo";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(BRANDCODE, AttributeMode.INITIAL);
		tmp.put(BRANDDESC, AttributeMode.INITIAL);
		tmp.put(ALTBRANDCODE, AttributeMode.INITIAL);
		tmp.put(CATEGORYCODE, AttributeMode.INITIAL);
		tmp.put(ALTBRANDDESC, AttributeMode.INITIAL);
		tmp.put(BRANDINFO, AttributeMode.INITIAL);
		tmp.put(BRANDLOGO, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.altBrandCode</code> attribute.
	 * @return the altBrandCode
	 */
	public String getAltBrandCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ALTBRANDCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.altBrandCode</code> attribute.
	 * @return the altBrandCode
	 */
	public String getAltBrandCode()
	{
		return getAltBrandCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.altBrandCode</code> attribute. 
	 * @param value the altBrandCode
	 */
	public void setAltBrandCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ALTBRANDCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.altBrandCode</code> attribute. 
	 * @param value the altBrandCode
	 */
	public void setAltBrandCode(final String value)
	{
		setAltBrandCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.altBrandDesc</code> attribute.
	 * @return the altBrandDesc
	 */
	public String getAltBrandDesc(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ALTBRANDDESC);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.altBrandDesc</code> attribute.
	 * @return the altBrandDesc
	 */
	public String getAltBrandDesc()
	{
		return getAltBrandDesc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.altBrandDesc</code> attribute. 
	 * @param value the altBrandDesc
	 */
	public void setAltBrandDesc(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ALTBRANDDESC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.altBrandDesc</code> attribute. 
	 * @param value the altBrandDesc
	 */
	public void setAltBrandDesc(final String value)
	{
		setAltBrandDesc( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.brandCode</code> attribute.
	 * @return the brandCode
	 */
	public String getBrandCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BRANDCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.brandCode</code> attribute.
	 * @return the brandCode
	 */
	public String getBrandCode()
	{
		return getBrandCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.brandCode</code> attribute. 
	 * @param value the brandCode
	 */
	public void setBrandCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BRANDCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.brandCode</code> attribute. 
	 * @param value the brandCode
	 */
	public void setBrandCode(final String value)
	{
		setBrandCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.brandDesc</code> attribute.
	 * @return the brandDesc
	 */
	public String getBrandDesc(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BRANDDESC);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.brandDesc</code> attribute.
	 * @return the brandDesc
	 */
	public String getBrandDesc()
	{
		return getBrandDesc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.brandDesc</code> attribute. 
	 * @param value the brandDesc
	 */
	public void setBrandDesc(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BRANDDESC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.brandDesc</code> attribute. 
	 * @param value the brandDesc
	 */
	public void setBrandDesc(final String value)
	{
		setBrandDesc( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.brandInfo</code> attribute.
	 * @return the brandInfo
	 */
	public String getBrandInfo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BRANDINFO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.brandInfo</code> attribute.
	 * @return the brandInfo
	 */
	public String getBrandInfo()
	{
		return getBrandInfo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.brandInfo</code> attribute. 
	 * @param value the brandInfo
	 */
	public void setBrandInfo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BRANDINFO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.brandInfo</code> attribute. 
	 * @param value the brandInfo
	 */
	public void setBrandInfo(final String value)
	{
		setBrandInfo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.brandLogo</code> attribute.
	 * @return the brandLogo
	 */
	public String getBrandLogo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BRANDLOGO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.brandLogo</code> attribute.
	 * @return the brandLogo
	 */
	public String getBrandLogo()
	{
		return getBrandLogo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.brandLogo</code> attribute. 
	 * @param value the brandLogo
	 */
	public void setBrandLogo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BRANDLOGO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.brandLogo</code> attribute. 
	 * @param value the brandLogo
	 */
	public void setBrandLogo(final String value)
	{
		setBrandLogo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.categoryCode</code> attribute.
	 * @return the categoryCode
	 */
	public String getCategoryCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CATEGORYCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandMapping.categoryCode</code> attribute.
	 * @return the categoryCode
	 */
	public String getCategoryCode()
	{
		return getCategoryCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.categoryCode</code> attribute. 
	 * @param value the categoryCode
	 */
	public void setCategoryCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CATEGORYCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandMapping.categoryCode</code> attribute. 
	 * @param value the categoryCode
	 */
	public void setCategoryCode(final String value)
	{
		setCategoryCode( getSession().getSessionContext(), value );
	}
	
}
