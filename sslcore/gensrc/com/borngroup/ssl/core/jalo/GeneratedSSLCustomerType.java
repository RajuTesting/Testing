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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SSLCustomerType}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLCustomerType extends GenericItem
{
	/** Qualifier of the <code>SSLCustomerType.orderCount</code> attribute **/
	public static final String ORDERCOUNT = "orderCount";
	/** Qualifier of the <code>SSLCustomerType.displayName</code> attribute **/
	public static final String DISPLAYNAME = "displayName";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(ORDERCOUNT, AttributeMode.INITIAL);
		tmp.put(DISPLAYNAME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCustomerType.displayName</code> attribute.
	 * @return the displayName
	 */
	public String getDisplayName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DISPLAYNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCustomerType.displayName</code> attribute.
	 * @return the displayName
	 */
	public String getDisplayName()
	{
		return getDisplayName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCustomerType.displayName</code> attribute. 
	 * @param value the displayName
	 */
	public void setDisplayName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DISPLAYNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCustomerType.displayName</code> attribute. 
	 * @param value the displayName
	 */
	public void setDisplayName(final String value)
	{
		setDisplayName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCustomerType.orderCount</code> attribute.
	 * @return the orderCount
	 */
	public Integer getOrderCount(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, ORDERCOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCustomerType.orderCount</code> attribute.
	 * @return the orderCount
	 */
	public Integer getOrderCount()
	{
		return getOrderCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCustomerType.orderCount</code> attribute. 
	 * @return the orderCount
	 */
	public int getOrderCountAsPrimitive(final SessionContext ctx)
	{
		Integer value = getOrderCount( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCustomerType.orderCount</code> attribute. 
	 * @return the orderCount
	 */
	public int getOrderCountAsPrimitive()
	{
		return getOrderCountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCustomerType.orderCount</code> attribute. 
	 * @param value the orderCount
	 */
	public void setOrderCount(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, ORDERCOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCustomerType.orderCount</code> attribute. 
	 * @param value the orderCount
	 */
	public void setOrderCount(final Integer value)
	{
		setOrderCount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCustomerType.orderCount</code> attribute. 
	 * @param value the orderCount
	 */
	public void setOrderCount(final SessionContext ctx, final int value)
	{
		setOrderCount( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCustomerType.orderCount</code> attribute. 
	 * @param value the orderCount
	 */
	public void setOrderCount(final int value)
	{
		setOrderCount( getSession().getSessionContext(), value );
	}
	
}
