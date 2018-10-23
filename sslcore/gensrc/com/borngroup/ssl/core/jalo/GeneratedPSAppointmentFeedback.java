/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.PSAppointment;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem PSAppointmentFeedback}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPSAppointmentFeedback extends GenericItem
{
	/** Qualifier of the <code>PSAppointmentFeedback.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>PSAppointmentFeedback.appointment</code> attribute **/
	public static final String APPOINTMENT = "appointment";
	/** Qualifier of the <code>PSAppointmentFeedback.customerArrivalStatus</code> attribute **/
	public static final String CUSTOMERARRIVALSTATUS = "customerArrivalStatus";
	/** Qualifier of the <code>PSAppointmentFeedback.shoppedPrice</code> attribute **/
	public static final String SHOPPEDPRICE = "shoppedPrice";
	/** Qualifier of the <code>PSAppointmentFeedback.customerComments</code> attribute **/
	public static final String CUSTOMERCOMMENTS = "customerComments";
	/** Qualifier of the <code>PSAppointmentFeedback.reason</code> attribute **/
	public static final String REASON = "reason";
	/** Qualifier of the <code>PSAppointmentFeedback.rating</code> attribute **/
	public static final String RATING = "rating";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(APPOINTMENT, AttributeMode.INITIAL);
		tmp.put(CUSTOMERARRIVALSTATUS, AttributeMode.INITIAL);
		tmp.put(SHOPPEDPRICE, AttributeMode.INITIAL);
		tmp.put(CUSTOMERCOMMENTS, AttributeMode.INITIAL);
		tmp.put(REASON, AttributeMode.INITIAL);
		tmp.put(RATING, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.appointment</code> attribute.
	 * @return the appointment
	 */
	public PSAppointment getAppointment(final SessionContext ctx)
	{
		return (PSAppointment)getProperty( ctx, APPOINTMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.appointment</code> attribute.
	 * @return the appointment
	 */
	public PSAppointment getAppointment()
	{
		return getAppointment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.appointment</code> attribute. 
	 * @param value the appointment
	 */
	public void setAppointment(final SessionContext ctx, final PSAppointment value)
	{
		setProperty(ctx, APPOINTMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.appointment</code> attribute. 
	 * @param value the appointment
	 */
	public void setAppointment(final PSAppointment value)
	{
		setAppointment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.customerArrivalStatus</code> attribute.
	 * @return the customerArrivalStatus
	 */
	public EnumerationValue getCustomerArrivalStatus(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, CUSTOMERARRIVALSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.customerArrivalStatus</code> attribute.
	 * @return the customerArrivalStatus
	 */
	public EnumerationValue getCustomerArrivalStatus()
	{
		return getCustomerArrivalStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.customerArrivalStatus</code> attribute. 
	 * @param value the customerArrivalStatus
	 */
	public void setCustomerArrivalStatus(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, CUSTOMERARRIVALSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.customerArrivalStatus</code> attribute. 
	 * @param value the customerArrivalStatus
	 */
	public void setCustomerArrivalStatus(final EnumerationValue value)
	{
		setCustomerArrivalStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.customerComments</code> attribute.
	 * @return the customerComments
	 */
	public String getCustomerComments(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CUSTOMERCOMMENTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.customerComments</code> attribute.
	 * @return the customerComments
	 */
	public String getCustomerComments()
	{
		return getCustomerComments( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.customerComments</code> attribute. 
	 * @param value the customerComments
	 */
	public void setCustomerComments(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CUSTOMERCOMMENTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.customerComments</code> attribute. 
	 * @param value the customerComments
	 */
	public void setCustomerComments(final String value)
	{
		setCustomerComments( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.rating</code> attribute.
	 * @return the rating
	 */
	public Double getRating(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, RATING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.rating</code> attribute.
	 * @return the rating
	 */
	public Double getRating()
	{
		return getRating( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.rating</code> attribute. 
	 * @return the rating
	 */
	public double getRatingAsPrimitive(final SessionContext ctx)
	{
		Double value = getRating( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.rating</code> attribute. 
	 * @return the rating
	 */
	public double getRatingAsPrimitive()
	{
		return getRatingAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.rating</code> attribute. 
	 * @param value the rating
	 */
	public void setRating(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, RATING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.rating</code> attribute. 
	 * @param value the rating
	 */
	public void setRating(final Double value)
	{
		setRating( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.rating</code> attribute. 
	 * @param value the rating
	 */
	public void setRating(final SessionContext ctx, final double value)
	{
		setRating( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.rating</code> attribute. 
	 * @param value the rating
	 */
	public void setRating(final double value)
	{
		setRating( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.reason</code> attribute.
	 * @return the reason
	 */
	public String getReason(final SessionContext ctx)
	{
		return (String)getProperty( ctx, REASON);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.reason</code> attribute.
	 * @return the reason
	 */
	public String getReason()
	{
		return getReason( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.reason</code> attribute. 
	 * @param value the reason
	 */
	public void setReason(final SessionContext ctx, final String value)
	{
		setProperty(ctx, REASON,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.reason</code> attribute. 
	 * @param value the reason
	 */
	public void setReason(final String value)
	{
		setReason( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.shoppedPrice</code> attribute.
	 * @return the shoppedPrice
	 */
	public Double getShoppedPrice(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, SHOPPEDPRICE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.shoppedPrice</code> attribute.
	 * @return the shoppedPrice
	 */
	public Double getShoppedPrice()
	{
		return getShoppedPrice( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.shoppedPrice</code> attribute. 
	 * @return the shoppedPrice
	 */
	public double getShoppedPriceAsPrimitive(final SessionContext ctx)
	{
		Double value = getShoppedPrice( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointmentFeedback.shoppedPrice</code> attribute. 
	 * @return the shoppedPrice
	 */
	public double getShoppedPriceAsPrimitive()
	{
		return getShoppedPriceAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.shoppedPrice</code> attribute. 
	 * @param value the shoppedPrice
	 */
	public void setShoppedPrice(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, SHOPPEDPRICE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.shoppedPrice</code> attribute. 
	 * @param value the shoppedPrice
	 */
	public void setShoppedPrice(final Double value)
	{
		setShoppedPrice( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.shoppedPrice</code> attribute. 
	 * @param value the shoppedPrice
	 */
	public void setShoppedPrice(final SessionContext ctx, final double value)
	{
		setShoppedPrice( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointmentFeedback.shoppedPrice</code> attribute. 
	 * @param value the shoppedPrice
	 */
	public void setShoppedPrice(final double value)
	{
		setShoppedPrice( getSession().getSessionContext(), value );
	}
	
}
