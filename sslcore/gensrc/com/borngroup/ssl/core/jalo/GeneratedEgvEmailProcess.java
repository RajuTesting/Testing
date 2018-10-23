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
import de.hybris.platform.jalo.order.Order;
import de.hybris.platform.processengine.jalo.BusinessProcess;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.processengine.jalo.BusinessProcess EgvEmailProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedEgvEmailProcess extends BusinessProcess
{
	/** Qualifier of the <code>EgvEmailProcess.done</code> attribute **/
	public static final String DONE = "done";
	/** Qualifier of the <code>EgvEmailProcess.order</code> attribute **/
	public static final String ORDER = "order";
	/** Qualifier of the <code>EgvEmailProcess.cardNo</code> attribute **/
	public static final String CARDNO = "cardNo";
	/** Qualifier of the <code>EgvEmailProcess.cardPin</code> attribute **/
	public static final String CARDPIN = "cardPin";
	/** Qualifier of the <code>EgvEmailProcess.amount</code> attribute **/
	public static final String AMOUNT = "amount";
	/** Qualifier of the <code>EgvEmailProcess.validity</code> attribute **/
	public static final String VALIDITY = "validity";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(BusinessProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(DONE, AttributeMode.INITIAL);
		tmp.put(ORDER, AttributeMode.INITIAL);
		tmp.put(CARDNO, AttributeMode.INITIAL);
		tmp.put(CARDPIN, AttributeMode.INITIAL);
		tmp.put(AMOUNT, AttributeMode.INITIAL);
		tmp.put(VALIDITY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.amount</code> attribute.
	 * @return the amount
	 */
	public String getAmount(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.amount</code> attribute.
	 * @return the amount
	 */
	public String getAmount()
	{
		return getAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.amount</code> attribute. 
	 * @param value the amount
	 */
	public void setAmount(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.amount</code> attribute. 
	 * @param value the amount
	 */
	public void setAmount(final String value)
	{
		setAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.cardNo</code> attribute.
	 * @return the cardNo
	 */
	public String getCardNo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CARDNO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.cardNo</code> attribute.
	 * @return the cardNo
	 */
	public String getCardNo()
	{
		return getCardNo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.cardNo</code> attribute. 
	 * @param value the cardNo
	 */
	public void setCardNo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CARDNO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.cardNo</code> attribute. 
	 * @param value the cardNo
	 */
	public void setCardNo(final String value)
	{
		setCardNo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.cardPin</code> attribute.
	 * @return the cardPin
	 */
	public String getCardPin(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CARDPIN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.cardPin</code> attribute.
	 * @return the cardPin
	 */
	public String getCardPin()
	{
		return getCardPin( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.cardPin</code> attribute. 
	 * @param value the cardPin
	 */
	public void setCardPin(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CARDPIN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.cardPin</code> attribute. 
	 * @param value the cardPin
	 */
	public void setCardPin(final String value)
	{
		setCardPin( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.done</code> attribute.
	 * @return the done - Mark process as done
	 */
	public Boolean isDone(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, DONE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.done</code> attribute.
	 * @return the done - Mark process as done
	 */
	public Boolean isDone()
	{
		return isDone( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.done</code> attribute. 
	 * @return the done - Mark process as done
	 */
	public boolean isDoneAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isDone( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.done</code> attribute. 
	 * @return the done - Mark process as done
	 */
	public boolean isDoneAsPrimitive()
	{
		return isDoneAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.done</code> attribute. 
	 * @param value the done - Mark process as done
	 */
	public void setDone(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, DONE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.done</code> attribute. 
	 * @param value the done - Mark process as done
	 */
	public void setDone(final Boolean value)
	{
		setDone( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.done</code> attribute. 
	 * @param value the done - Mark process as done
	 */
	public void setDone(final SessionContext ctx, final boolean value)
	{
		setDone( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.done</code> attribute. 
	 * @param value the done - Mark process as done
	 */
	public void setDone(final boolean value)
	{
		setDone( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.order</code> attribute.
	 * @return the order
	 */
	public Order getOrder(final SessionContext ctx)
	{
		return (Order)getProperty( ctx, ORDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.order</code> attribute.
	 * @return the order
	 */
	public Order getOrder()
	{
		return getOrder( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.order</code> attribute. 
	 * @param value the order
	 */
	public void setOrder(final SessionContext ctx, final Order value)
	{
		setProperty(ctx, ORDER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.order</code> attribute. 
	 * @param value the order
	 */
	public void setOrder(final Order value)
	{
		setOrder( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.validity</code> attribute.
	 * @return the validity
	 */
	public String getValidity(final SessionContext ctx)
	{
		return (String)getProperty( ctx, VALIDITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EgvEmailProcess.validity</code> attribute.
	 * @return the validity
	 */
	public String getValidity()
	{
		return getValidity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.validity</code> attribute. 
	 * @param value the validity
	 */
	public void setValidity(final SessionContext ctx, final String value)
	{
		setProperty(ctx, VALIDITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EgvEmailProcess.validity</code> attribute. 
	 * @param value the validity
	 */
	public void setValidity(final String value)
	{
		setValidity( getSession().getSessionContext(), value );
	}
	
}
