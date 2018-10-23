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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SizeMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSizeMapping extends GenericItem
{
	/** Qualifier of the <code>SizeMapping.departmentCode</code> attribute **/
	public static final String DEPARTMENTCODE = "departmentCode";
	/** Qualifier of the <code>SizeMapping.subDepartmentCode</code> attribute **/
	public static final String SUBDEPARTMENTCODE = "subDepartmentCode";
	/** Qualifier of the <code>SizeMapping.altBrandCode</code> attribute **/
	public static final String ALTBRANDCODE = "altBrandCode";
	/** Qualifier of the <code>SizeMapping.sizeCode</code> attribute **/
	public static final String SIZECODE = "sizeCode";
	/** Qualifier of the <code>SizeMapping.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>SizeMapping.altSizeCode</code> attribute **/
	public static final String ALTSIZECODE = "altSizeCode";
	/** Qualifier of the <code>SizeMapping.altSizeDesc</code> attribute **/
	public static final String ALTSIZEDESC = "altSizeDesc";
	/** Qualifier of the <code>SizeMapping.newSize</code> attribute **/
	public static final String NEWSIZE = "newSize";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(DEPARTMENTCODE, AttributeMode.INITIAL);
		tmp.put(SUBDEPARTMENTCODE, AttributeMode.INITIAL);
		tmp.put(ALTBRANDCODE, AttributeMode.INITIAL);
		tmp.put(SIZECODE, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(ALTSIZECODE, AttributeMode.INITIAL);
		tmp.put(ALTSIZEDESC, AttributeMode.INITIAL);
		tmp.put(NEWSIZE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.altBrandCode</code> attribute.
	 * @return the altBrandCode
	 */
	public String getAltBrandCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ALTBRANDCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.altBrandCode</code> attribute.
	 * @return the altBrandCode
	 */
	public String getAltBrandCode()
	{
		return getAltBrandCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.altBrandCode</code> attribute. 
	 * @param value the altBrandCode
	 */
	public void setAltBrandCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ALTBRANDCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.altBrandCode</code> attribute. 
	 * @param value the altBrandCode
	 */
	public void setAltBrandCode(final String value)
	{
		setAltBrandCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.altSizeCode</code> attribute.
	 * @return the altSizeCode
	 */
	public String getAltSizeCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ALTSIZECODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.altSizeCode</code> attribute.
	 * @return the altSizeCode
	 */
	public String getAltSizeCode()
	{
		return getAltSizeCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.altSizeCode</code> attribute. 
	 * @param value the altSizeCode
	 */
	public void setAltSizeCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ALTSIZECODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.altSizeCode</code> attribute. 
	 * @param value the altSizeCode
	 */
	public void setAltSizeCode(final String value)
	{
		setAltSizeCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.altSizeDesc</code> attribute.
	 * @return the altSizeDesc
	 */
	public String getAltSizeDesc(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ALTSIZEDESC);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.altSizeDesc</code> attribute.
	 * @return the altSizeDesc
	 */
	public String getAltSizeDesc()
	{
		return getAltSizeDesc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.altSizeDesc</code> attribute. 
	 * @param value the altSizeDesc
	 */
	public void setAltSizeDesc(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ALTSIZEDESC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.altSizeDesc</code> attribute. 
	 * @param value the altSizeDesc
	 */
	public void setAltSizeDesc(final String value)
	{
		setAltSizeDesc( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.departmentCode</code> attribute.
	 * @return the departmentCode
	 */
	public String getDepartmentCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEPARTMENTCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.departmentCode</code> attribute.
	 * @return the departmentCode
	 */
	public String getDepartmentCode()
	{
		return getDepartmentCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.departmentCode</code> attribute. 
	 * @param value the departmentCode
	 */
	public void setDepartmentCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEPARTMENTCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.departmentCode</code> attribute. 
	 * @param value the departmentCode
	 */
	public void setDepartmentCode(final String value)
	{
		setDepartmentCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.description</code> attribute.
	 * @return the description
	 */
	public String getDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.description</code> attribute.
	 * @return the description
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.newSize</code> attribute.
	 * @return the newSize
	 */
	public String getNewSize(final SessionContext ctx)
	{
		return (String)getProperty( ctx, NEWSIZE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.newSize</code> attribute.
	 * @return the newSize
	 */
	public String getNewSize()
	{
		return getNewSize( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.newSize</code> attribute. 
	 * @param value the newSize
	 */
	public void setNewSize(final SessionContext ctx, final String value)
	{
		setProperty(ctx, NEWSIZE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.newSize</code> attribute. 
	 * @param value the newSize
	 */
	public void setNewSize(final String value)
	{
		setNewSize( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.sizeCode</code> attribute.
	 * @return the sizeCode
	 */
	public String getSizeCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SIZECODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.sizeCode</code> attribute.
	 * @return the sizeCode
	 */
	public String getSizeCode()
	{
		return getSizeCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.sizeCode</code> attribute. 
	 * @param value the sizeCode
	 */
	public void setSizeCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SIZECODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.sizeCode</code> attribute. 
	 * @param value the sizeCode
	 */
	public void setSizeCode(final String value)
	{
		setSizeCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.subDepartmentCode</code> attribute.
	 * @return the subDepartmentCode
	 */
	public String getSubDepartmentCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SUBDEPARTMENTCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SizeMapping.subDepartmentCode</code> attribute.
	 * @return the subDepartmentCode
	 */
	public String getSubDepartmentCode()
	{
		return getSubDepartmentCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.subDepartmentCode</code> attribute. 
	 * @param value the subDepartmentCode
	 */
	public void setSubDepartmentCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SUBDEPARTMENTCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SizeMapping.subDepartmentCode</code> attribute. 
	 * @param value the subDepartmentCode
	 */
	public void setSubDepartmentCode(final String value)
	{
		setSubDepartmentCode( getSession().getSessionContext(), value );
	}
	
}
