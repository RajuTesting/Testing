/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.OrderDetailsComponent OrderDetailsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOrderDetailsComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>OrderDetailsComponent.orderDesc</code> attribute **/
	public static final String ORDERDESC = "orderDesc";
	/** Qualifier of the <code>OrderDetailsComponent.color</code> attribute **/
	public static final String COLOR = "color";
	/** Qualifier of the <code>OrderDetailsComponent.size</code> attribute **/
	public static final String SIZE = "size";
	/** Qualifier of the <code>OrderDetailsComponent.quantity</code> attribute **/
	public static final String QUANTITY = "quantity";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(ORDERDESC, AttributeMode.INITIAL);
		tmp.put(COLOR, AttributeMode.INITIAL);
		tmp.put(SIZE, AttributeMode.INITIAL);
		tmp.put(QUANTITY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.color</code> attribute.
	 * @return the color - Product Color to display
	 */
	public String getColor(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderDetailsComponent.getColor requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, COLOR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.color</code> attribute.
	 * @return the color - Product Color to display
	 */
	public String getColor()
	{
		return getColor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.color</code> attribute. 
	 * @return the localized color - Product Color to display
	 */
	public Map<Language,String> getAllColor(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,COLOR,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.color</code> attribute. 
	 * @return the localized color - Product Color to display
	 */
	public Map<Language,String> getAllColor()
	{
		return getAllColor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.color</code> attribute. 
	 * @param value the color - Product Color to display
	 */
	public void setColor(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderDetailsComponent.setColor requires a session language", 0 );
		}
		setLocalizedProperty(ctx, COLOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.color</code> attribute. 
	 * @param value the color - Product Color to display
	 */
	public void setColor(final String value)
	{
		setColor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.color</code> attribute. 
	 * @param value the color - Product Color to display
	 */
	public void setAllColor(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,COLOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.color</code> attribute. 
	 * @param value the color - Product Color to display
	 */
	public void setAllColor(final Map<Language,String> value)
	{
		setAllColor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.orderDesc</code> attribute.
	 * @return the orderDesc - Order Desc to display
	 */
	public String getOrderDesc(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderDetailsComponent.getOrderDesc requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, ORDERDESC);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.orderDesc</code> attribute.
	 * @return the orderDesc - Order Desc to display
	 */
	public String getOrderDesc()
	{
		return getOrderDesc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.orderDesc</code> attribute. 
	 * @return the localized orderDesc - Order Desc to display
	 */
	public Map<Language,String> getAllOrderDesc(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,ORDERDESC,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.orderDesc</code> attribute. 
	 * @return the localized orderDesc - Order Desc to display
	 */
	public Map<Language,String> getAllOrderDesc()
	{
		return getAllOrderDesc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.orderDesc</code> attribute. 
	 * @param value the orderDesc - Order Desc to display
	 */
	public void setOrderDesc(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderDetailsComponent.setOrderDesc requires a session language", 0 );
		}
		setLocalizedProperty(ctx, ORDERDESC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.orderDesc</code> attribute. 
	 * @param value the orderDesc - Order Desc to display
	 */
	public void setOrderDesc(final String value)
	{
		setOrderDesc( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.orderDesc</code> attribute. 
	 * @param value the orderDesc - Order Desc to display
	 */
	public void setAllOrderDesc(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,ORDERDESC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.orderDesc</code> attribute. 
	 * @param value the orderDesc - Order Desc to display
	 */
	public void setAllOrderDesc(final Map<Language,String> value)
	{
		setAllOrderDesc( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.quantity</code> attribute.
	 * @return the quantity - Product Quantity to display
	 */
	public Integer getQuantity(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderDetailsComponent.getQuantity requires a session language", 0 );
		}
		return (Integer)getLocalizedProperty( ctx, QUANTITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.quantity</code> attribute.
	 * @return the quantity - Product Quantity to display
	 */
	public Integer getQuantity()
	{
		return getQuantity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.quantity</code> attribute. 
	 * @return the quantity - Product Quantity to display
	 */
	public int getQuantityAsPrimitive(final SessionContext ctx)
	{
		Integer value = getQuantity( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.quantity</code> attribute. 
	 * @return the quantity - Product Quantity to display
	 */
	public int getQuantityAsPrimitive()
	{
		return getQuantityAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.quantity</code> attribute. 
	 * @return the localized quantity - Product Quantity to display
	 */
	public Map<Language,Integer> getAllQuantity(final SessionContext ctx)
	{
		return (Map<Language,Integer>)getAllLocalizedProperties(ctx,QUANTITY,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.quantity</code> attribute. 
	 * @return the localized quantity - Product Quantity to display
	 */
	public Map<Language,Integer> getAllQuantity()
	{
		return getAllQuantity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.quantity</code> attribute. 
	 * @param value the quantity - Product Quantity to display
	 */
	public void setQuantity(final SessionContext ctx, final Integer value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderDetailsComponent.setQuantity requires a session language", 0 );
		}
		setLocalizedProperty(ctx, QUANTITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.quantity</code> attribute. 
	 * @param value the quantity - Product Quantity to display
	 */
	public void setQuantity(final Integer value)
	{
		setQuantity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.quantity</code> attribute. 
	 * @param value the quantity - Product Quantity to display
	 */
	public void setQuantity(final SessionContext ctx, final int value)
	{
		setQuantity( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.quantity</code> attribute. 
	 * @param value the quantity - Product Quantity to display
	 */
	public void setQuantity(final int value)
	{
		setQuantity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.quantity</code> attribute. 
	 * @param value the quantity - Product Quantity to display
	 */
	public void setAllQuantity(final SessionContext ctx, final Map<Language,Integer> value)
	{
		setAllLocalizedProperties(ctx,QUANTITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.quantity</code> attribute. 
	 * @param value the quantity - Product Quantity to display
	 */
	public void setAllQuantity(final Map<Language,Integer> value)
	{
		setAllQuantity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.size</code> attribute.
	 * @return the size - Product Size to display
	 */
	public String getSize(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderDetailsComponent.getSize requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, SIZE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.size</code> attribute.
	 * @return the size - Product Size to display
	 */
	public String getSize()
	{
		return getSize( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.size</code> attribute. 
	 * @return the localized size - Product Size to display
	 */
	public Map<Language,String> getAllSize(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,SIZE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderDetailsComponent.size</code> attribute. 
	 * @return the localized size - Product Size to display
	 */
	public Map<Language,String> getAllSize()
	{
		return getAllSize( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.size</code> attribute. 
	 * @param value the size - Product Size to display
	 */
	public void setSize(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderDetailsComponent.setSize requires a session language", 0 );
		}
		setLocalizedProperty(ctx, SIZE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.size</code> attribute. 
	 * @param value the size - Product Size to display
	 */
	public void setSize(final String value)
	{
		setSize( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.size</code> attribute. 
	 * @param value the size - Product Size to display
	 */
	public void setAllSize(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,SIZE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderDetailsComponent.size</code> attribute. 
	 * @param value the size - Product Size to display
	 */
	public void setAllSize(final Map<Language,String> value)
	{
		setAllSize( getSession().getSessionContext(), value );
	}
	
}
