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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem CustomerFeedback}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCustomerFeedback extends GenericItem
{
	/** Qualifier of the <code>CustomerFeedback.orderNo</code> attribute **/
	public static final String ORDERNO = "orderNo";
	/** Qualifier of the <code>CustomerFeedback.starRating</code> attribute **/
	public static final String STARRATING = "starRating";
	/** Qualifier of the <code>CustomerFeedback.feedbackResponse</code> attribute **/
	public static final String FEEDBACKRESPONSE = "feedbackResponse";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(ORDERNO, AttributeMode.INITIAL);
		tmp.put(STARRATING, AttributeMode.INITIAL);
		tmp.put(FEEDBACKRESPONSE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedback.feedbackResponse</code> attribute.
	 * @return the feedbackResponse - Customer Feedback Response
	 */
	public String getFeedbackResponse(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FEEDBACKRESPONSE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedback.feedbackResponse</code> attribute.
	 * @return the feedbackResponse - Customer Feedback Response
	 */
	public String getFeedbackResponse()
	{
		return getFeedbackResponse( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedback.feedbackResponse</code> attribute. 
	 * @param value the feedbackResponse - Customer Feedback Response
	 */
	public void setFeedbackResponse(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FEEDBACKRESPONSE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedback.feedbackResponse</code> attribute. 
	 * @param value the feedbackResponse - Customer Feedback Response
	 */
	public void setFeedbackResponse(final String value)
	{
		setFeedbackResponse( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedback.orderNo</code> attribute.
	 * @return the orderNo - Order Number
	 */
	public String getOrderNo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ORDERNO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedback.orderNo</code> attribute.
	 * @return the orderNo - Order Number
	 */
	public String getOrderNo()
	{
		return getOrderNo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedback.orderNo</code> attribute. 
	 * @param value the orderNo - Order Number
	 */
	public void setOrderNo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ORDERNO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedback.orderNo</code> attribute. 
	 * @param value the orderNo - Order Number
	 */
	public void setOrderNo(final String value)
	{
		setOrderNo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedback.starRating</code> attribute.
	 * @return the starRating - Star Rating
	 */
	public String getStarRating(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STARRATING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedback.starRating</code> attribute.
	 * @return the starRating - Star Rating
	 */
	public String getStarRating()
	{
		return getStarRating( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedback.starRating</code> attribute. 
	 * @param value the starRating - Star Rating
	 */
	public void setStarRating(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STARRATING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedback.starRating</code> attribute. 
	 * @param value the starRating - Star Rating
	 */
	public void setStarRating(final String value)
	{
		setStarRating( getSession().getSessionContext(), value );
	}
	
}
