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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.OrderSummeryComponent OrderSummeryComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOrderSummeryComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>OrderSummeryComponent.orderId</code> attribute **/
	public static final String ORDERID = "orderId";
	/** Qualifier of the <code>OrderSummeryComponent.orderDate</code> attribute **/
	public static final String ORDERDATE = "orderDate";
	/** Qualifier of the <code>OrderSummeryComponent.amountPaid</code> attribute **/
	public static final String AMOUNTPAID = "amountPaid";
	/** Qualifier of the <code>OrderSummeryComponent.deliveryAddress</code> attribute **/
	public static final String DELIVERYADDRESS = "deliveryAddress";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(ORDERID, AttributeMode.INITIAL);
		tmp.put(ORDERDATE, AttributeMode.INITIAL);
		tmp.put(AMOUNTPAID, AttributeMode.INITIAL);
		tmp.put(DELIVERYADDRESS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.amountPaid</code> attribute.
	 * @return the amountPaid - Amount Paid to display
	 */
	public Double getAmountPaid(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderSummeryComponent.getAmountPaid requires a session language", 0 );
		}
		return (Double)getLocalizedProperty( ctx, AMOUNTPAID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.amountPaid</code> attribute.
	 * @return the amountPaid - Amount Paid to display
	 */
	public Double getAmountPaid()
	{
		return getAmountPaid( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.amountPaid</code> attribute. 
	 * @return the amountPaid - Amount Paid to display
	 */
	public double getAmountPaidAsPrimitive(final SessionContext ctx)
	{
		Double value = getAmountPaid( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.amountPaid</code> attribute. 
	 * @return the amountPaid - Amount Paid to display
	 */
	public double getAmountPaidAsPrimitive()
	{
		return getAmountPaidAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.amountPaid</code> attribute. 
	 * @return the localized amountPaid - Amount Paid to display
	 */
	public Map<Language,Double> getAllAmountPaid(final SessionContext ctx)
	{
		return (Map<Language,Double>)getAllLocalizedProperties(ctx,AMOUNTPAID,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.amountPaid</code> attribute. 
	 * @return the localized amountPaid - Amount Paid to display
	 */
	public Map<Language,Double> getAllAmountPaid()
	{
		return getAllAmountPaid( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.amountPaid</code> attribute. 
	 * @param value the amountPaid - Amount Paid to display
	 */
	public void setAmountPaid(final SessionContext ctx, final Double value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderSummeryComponent.setAmountPaid requires a session language", 0 );
		}
		setLocalizedProperty(ctx, AMOUNTPAID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.amountPaid</code> attribute. 
	 * @param value the amountPaid - Amount Paid to display
	 */
	public void setAmountPaid(final Double value)
	{
		setAmountPaid( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.amountPaid</code> attribute. 
	 * @param value the amountPaid - Amount Paid to display
	 */
	public void setAmountPaid(final SessionContext ctx, final double value)
	{
		setAmountPaid( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.amountPaid</code> attribute. 
	 * @param value the amountPaid - Amount Paid to display
	 */
	public void setAmountPaid(final double value)
	{
		setAmountPaid( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.amountPaid</code> attribute. 
	 * @param value the amountPaid - Amount Paid to display
	 */
	public void setAllAmountPaid(final SessionContext ctx, final Map<Language,Double> value)
	{
		setAllLocalizedProperties(ctx,AMOUNTPAID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.amountPaid</code> attribute. 
	 * @param value the amountPaid - Amount Paid to display
	 */
	public void setAllAmountPaid(final Map<Language,Double> value)
	{
		setAllAmountPaid( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.deliveryAddress</code> attribute.
	 * @return the deliveryAddress - Delivery Address to display
	 */
	public String getDeliveryAddress(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderSummeryComponent.getDeliveryAddress requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, DELIVERYADDRESS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.deliveryAddress</code> attribute.
	 * @return the deliveryAddress - Delivery Address to display
	 */
	public String getDeliveryAddress()
	{
		return getDeliveryAddress( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.deliveryAddress</code> attribute. 
	 * @return the localized deliveryAddress - Delivery Address to display
	 */
	public Map<Language,String> getAllDeliveryAddress(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,DELIVERYADDRESS,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.deliveryAddress</code> attribute. 
	 * @return the localized deliveryAddress - Delivery Address to display
	 */
	public Map<Language,String> getAllDeliveryAddress()
	{
		return getAllDeliveryAddress( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.deliveryAddress</code> attribute. 
	 * @param value the deliveryAddress - Delivery Address to display
	 */
	public void setDeliveryAddress(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderSummeryComponent.setDeliveryAddress requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DELIVERYADDRESS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.deliveryAddress</code> attribute. 
	 * @param value the deliveryAddress - Delivery Address to display
	 */
	public void setDeliveryAddress(final String value)
	{
		setDeliveryAddress( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.deliveryAddress</code> attribute. 
	 * @param value the deliveryAddress - Delivery Address to display
	 */
	public void setAllDeliveryAddress(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,DELIVERYADDRESS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.deliveryAddress</code> attribute. 
	 * @param value the deliveryAddress - Delivery Address to display
	 */
	public void setAllDeliveryAddress(final Map<Language,String> value)
	{
		setAllDeliveryAddress( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.orderDate</code> attribute.
	 * @return the orderDate - Order Id to display to display
	 */
	public Date getOrderDate(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderSummeryComponent.getOrderDate requires a session language", 0 );
		}
		return (Date)getLocalizedProperty( ctx, ORDERDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.orderDate</code> attribute.
	 * @return the orderDate - Order Id to display to display
	 */
	public Date getOrderDate()
	{
		return getOrderDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.orderDate</code> attribute. 
	 * @return the localized orderDate - Order Id to display to display
	 */
	public Map<Language,Date> getAllOrderDate(final SessionContext ctx)
	{
		return (Map<Language,Date>)getAllLocalizedProperties(ctx,ORDERDATE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.orderDate</code> attribute. 
	 * @return the localized orderDate - Order Id to display to display
	 */
	public Map<Language,Date> getAllOrderDate()
	{
		return getAllOrderDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.orderDate</code> attribute. 
	 * @param value the orderDate - Order Id to display to display
	 */
	public void setOrderDate(final SessionContext ctx, final Date value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderSummeryComponent.setOrderDate requires a session language", 0 );
		}
		setLocalizedProperty(ctx, ORDERDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.orderDate</code> attribute. 
	 * @param value the orderDate - Order Id to display to display
	 */
	public void setOrderDate(final Date value)
	{
		setOrderDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.orderDate</code> attribute. 
	 * @param value the orderDate - Order Id to display to display
	 */
	public void setAllOrderDate(final SessionContext ctx, final Map<Language,Date> value)
	{
		setAllLocalizedProperties(ctx,ORDERDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.orderDate</code> attribute. 
	 * @param value the orderDate - Order Id to display to display
	 */
	public void setAllOrderDate(final Map<Language,Date> value)
	{
		setAllOrderDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.orderId</code> attribute.
	 * @return the orderId - Order Id to display
	 */
	public String getOrderId(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderSummeryComponent.getOrderId requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, ORDERID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.orderId</code> attribute.
	 * @return the orderId - Order Id to display
	 */
	public String getOrderId()
	{
		return getOrderId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.orderId</code> attribute. 
	 * @return the localized orderId - Order Id to display
	 */
	public Map<Language,String> getAllOrderId(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,ORDERID,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummeryComponent.orderId</code> attribute. 
	 * @return the localized orderId - Order Id to display
	 */
	public Map<Language,String> getAllOrderId()
	{
		return getAllOrderId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.orderId</code> attribute. 
	 * @param value the orderId - Order Id to display
	 */
	public void setOrderId(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderSummeryComponent.setOrderId requires a session language", 0 );
		}
		setLocalizedProperty(ctx, ORDERID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.orderId</code> attribute. 
	 * @param value the orderId - Order Id to display
	 */
	public void setOrderId(final String value)
	{
		setOrderId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.orderId</code> attribute. 
	 * @param value the orderId - Order Id to display
	 */
	public void setAllOrderId(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,ORDERID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummeryComponent.orderId</code> attribute. 
	 * @param value the orderId - Order Id to display
	 */
	public void setAllOrderId(final Map<Language,String> value)
	{
		setAllOrderId( getSession().getSessionContext(), value );
	}
	
}
