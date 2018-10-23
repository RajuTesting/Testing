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
import de.hybris.platform.task.Task;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.task.Task PayuOrderDropTask}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPayuOrderDropTask extends Task
{
	/** Qualifier of the <code>PayuOrderDropTask.cartGuid</code> attribute **/
	public static final String CARTGUID = "cartGuid";
	/** Qualifier of the <code>PayuOrderDropTask.cartNumber</code> attribute **/
	public static final String CARTNUMBER = "cartNumber";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Task.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CARTGUID, AttributeMode.INITIAL);
		tmp.put(CARTNUMBER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PayuOrderDropTask.cartGuid</code> attribute.
	 * @return the cartGuid
	 */
	public String getCartGuid(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CARTGUID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PayuOrderDropTask.cartGuid</code> attribute.
	 * @return the cartGuid
	 */
	public String getCartGuid()
	{
		return getCartGuid( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PayuOrderDropTask.cartGuid</code> attribute. 
	 * @param value the cartGuid
	 */
	public void setCartGuid(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CARTGUID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PayuOrderDropTask.cartGuid</code> attribute. 
	 * @param value the cartGuid
	 */
	public void setCartGuid(final String value)
	{
		setCartGuid( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PayuOrderDropTask.cartNumber</code> attribute.
	 * @return the cartNumber
	 */
	public String getCartNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CARTNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PayuOrderDropTask.cartNumber</code> attribute.
	 * @return the cartNumber
	 */
	public String getCartNumber()
	{
		return getCartNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PayuOrderDropTask.cartNumber</code> attribute. 
	 * @param value the cartNumber
	 */
	public void setCartNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CARTNUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PayuOrderDropTask.cartNumber</code> attribute. 
	 * @param value the cartNumber
	 */
	public void setCartNumber(final String value)
	{
		setCartNumber( getSession().getSessionContext(), value );
	}
	
}
