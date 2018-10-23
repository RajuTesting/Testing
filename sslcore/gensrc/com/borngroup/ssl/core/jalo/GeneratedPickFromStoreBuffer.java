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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem PickFromStoreBuffer}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPickFromStoreBuffer extends GenericItem
{
	/** Qualifier of the <code>PickFromStoreBuffer.bufferStock</code> attribute **/
	public static final String BUFFERSTOCK = "bufferStock";
	/** Qualifier of the <code>PickFromStoreBuffer.departmentCode</code> attribute **/
	public static final String DEPARTMENTCODE = "departmentCode";
	/** Qualifier of the <code>PickFromStoreBuffer.subDepartmentCode</code> attribute **/
	public static final String SUBDEPARTMENTCODE = "subDepartmentCode";
	/** Qualifier of the <code>PickFromStoreBuffer.classCode</code> attribute **/
	public static final String CLASSCODE = "classCode";
	/** Qualifier of the <code>PickFromStoreBuffer.subClassCode</code> attribute **/
	public static final String SUBCLASSCODE = "subClassCode";
	/** Qualifier of the <code>PickFromStoreBuffer.seasonCode</code> attribute **/
	public static final String SEASONCODE = "seasonCode";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(BUFFERSTOCK, AttributeMode.INITIAL);
		tmp.put(DEPARTMENTCODE, AttributeMode.INITIAL);
		tmp.put(SUBDEPARTMENTCODE, AttributeMode.INITIAL);
		tmp.put(CLASSCODE, AttributeMode.INITIAL);
		tmp.put(SUBCLASSCODE, AttributeMode.INITIAL);
		tmp.put(SEASONCODE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.bufferStock</code> attribute.
	 * @return the bufferStock
	 */
	public Long getBufferStock(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, BUFFERSTOCK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.bufferStock</code> attribute.
	 * @return the bufferStock
	 */
	public Long getBufferStock()
	{
		return getBufferStock( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.bufferStock</code> attribute. 
	 * @return the bufferStock
	 */
	public long getBufferStockAsPrimitive(final SessionContext ctx)
	{
		Long value = getBufferStock( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.bufferStock</code> attribute. 
	 * @return the bufferStock
	 */
	public long getBufferStockAsPrimitive()
	{
		return getBufferStockAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.bufferStock</code> attribute. 
	 * @param value the bufferStock
	 */
	public void setBufferStock(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, BUFFERSTOCK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.bufferStock</code> attribute. 
	 * @param value the bufferStock
	 */
	public void setBufferStock(final Long value)
	{
		setBufferStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.bufferStock</code> attribute. 
	 * @param value the bufferStock
	 */
	public void setBufferStock(final SessionContext ctx, final long value)
	{
		setBufferStock( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.bufferStock</code> attribute. 
	 * @param value the bufferStock
	 */
	public void setBufferStock(final long value)
	{
		setBufferStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.classCode</code> attribute.
	 * @return the classCode
	 */
	public String getClassCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CLASSCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.classCode</code> attribute.
	 * @return the classCode
	 */
	public String getClassCode()
	{
		return getClassCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.classCode</code> attribute. 
	 * @param value the classCode
	 */
	public void setClassCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CLASSCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.classCode</code> attribute. 
	 * @param value the classCode
	 */
	public void setClassCode(final String value)
	{
		setClassCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.departmentCode</code> attribute.
	 * @return the departmentCode
	 */
	public String getDepartmentCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEPARTMENTCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.departmentCode</code> attribute.
	 * @return the departmentCode
	 */
	public String getDepartmentCode()
	{
		return getDepartmentCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.departmentCode</code> attribute. 
	 * @param value the departmentCode
	 */
	public void setDepartmentCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEPARTMENTCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.departmentCode</code> attribute. 
	 * @param value the departmentCode
	 */
	public void setDepartmentCode(final String value)
	{
		setDepartmentCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.seasonCode</code> attribute.
	 * @return the seasonCode
	 */
	public String getSeasonCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SEASONCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.seasonCode</code> attribute.
	 * @return the seasonCode
	 */
	public String getSeasonCode()
	{
		return getSeasonCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.seasonCode</code> attribute. 
	 * @param value the seasonCode
	 */
	public void setSeasonCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SEASONCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.seasonCode</code> attribute. 
	 * @param value the seasonCode
	 */
	public void setSeasonCode(final String value)
	{
		setSeasonCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.subClassCode</code> attribute.
	 * @return the subClassCode
	 */
	public String getSubClassCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SUBCLASSCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.subClassCode</code> attribute.
	 * @return the subClassCode
	 */
	public String getSubClassCode()
	{
		return getSubClassCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.subClassCode</code> attribute. 
	 * @param value the subClassCode
	 */
	public void setSubClassCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SUBCLASSCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.subClassCode</code> attribute. 
	 * @param value the subClassCode
	 */
	public void setSubClassCode(final String value)
	{
		setSubClassCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.subDepartmentCode</code> attribute.
	 * @return the subDepartmentCode
	 */
	public String getSubDepartmentCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SUBDEPARTMENTCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PickFromStoreBuffer.subDepartmentCode</code> attribute.
	 * @return the subDepartmentCode
	 */
	public String getSubDepartmentCode()
	{
		return getSubDepartmentCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.subDepartmentCode</code> attribute. 
	 * @param value the subDepartmentCode
	 */
	public void setSubDepartmentCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SUBDEPARTMENTCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PickFromStoreBuffer.subDepartmentCode</code> attribute. 
	 * @param value the subDepartmentCode
	 */
	public void setSubDepartmentCode(final String value)
	{
		setSubDepartmentCode( getSession().getSessionContext(), value );
	}
	
}
