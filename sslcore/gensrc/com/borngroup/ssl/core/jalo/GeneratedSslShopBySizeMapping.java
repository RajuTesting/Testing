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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SslShopBySizeMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslShopBySizeMapping extends GenericItem
{
	/** Qualifier of the <code>SslShopBySizeMapping.size</code> attribute **/
	public static final String SIZE = "size";
	/** Qualifier of the <code>SslShopBySizeMapping.priority</code> attribute **/
	public static final String PRIORITY = "priority";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(SIZE, AttributeMode.INITIAL);
		tmp.put(PRIORITY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslShopBySizeMapping.priority</code> attribute.
	 * @return the priority - Priority of the size of the varient
	 */
	public Integer getPriority(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, PRIORITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslShopBySizeMapping.priority</code> attribute.
	 * @return the priority - Priority of the size of the varient
	 */
	public Integer getPriority()
	{
		return getPriority( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslShopBySizeMapping.priority</code> attribute. 
	 * @return the priority - Priority of the size of the varient
	 */
	public int getPriorityAsPrimitive(final SessionContext ctx)
	{
		Integer value = getPriority( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslShopBySizeMapping.priority</code> attribute. 
	 * @return the priority - Priority of the size of the varient
	 */
	public int getPriorityAsPrimitive()
	{
		return getPriorityAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslShopBySizeMapping.priority</code> attribute. 
	 * @param value the priority - Priority of the size of the varient
	 */
	public void setPriority(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, PRIORITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslShopBySizeMapping.priority</code> attribute. 
	 * @param value the priority - Priority of the size of the varient
	 */
	public void setPriority(final Integer value)
	{
		setPriority( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslShopBySizeMapping.priority</code> attribute. 
	 * @param value the priority - Priority of the size of the varient
	 */
	public void setPriority(final SessionContext ctx, final int value)
	{
		setPriority( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslShopBySizeMapping.priority</code> attribute. 
	 * @param value the priority - Priority of the size of the varient
	 */
	public void setPriority(final int value)
	{
		setPriority( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslShopBySizeMapping.size</code> attribute.
	 * @return the size - Size of the product varient
	 */
	public String getSize(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SIZE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslShopBySizeMapping.size</code> attribute.
	 * @return the size - Size of the product varient
	 */
	public String getSize()
	{
		return getSize( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslShopBySizeMapping.size</code> attribute. 
	 * @param value the size - Size of the product varient
	 */
	public void setSize(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SIZE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslShopBySizeMapping.size</code> attribute. 
	 * @param value the size - Size of the product varient
	 */
	public void setSize(final String value)
	{
		setSize( getSession().getSessionContext(), value );
	}
	
}
