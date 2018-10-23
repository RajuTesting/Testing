/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.commerceservices.jalo.process.StoreFrontCustomerProcess;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.commerceservices.jalo.process.StoreFrontCustomerProcess FeedBackEmailProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedFeedBackEmailProcess extends StoreFrontCustomerProcess
{
	/** Qualifier of the <code>FeedBackEmailProcess.visitOptions</code> attribute **/
	public static final String VISITOPTIONS = "visitOptions";
	/** Qualifier of the <code>FeedBackEmailProcess.easeOfFindingInfo</code> attribute **/
	public static final String EASEOFFINDINGINFO = "easeOfFindingInfo";
	/** Qualifier of the <code>FeedBackEmailProcess.productAvailability</code> attribute **/
	public static final String PRODUCTAVAILABILITY = "productAvailability";
	/** Qualifier of the <code>FeedBackEmailProcess.varietyOfProducts</code> attribute **/
	public static final String VARIETYOFPRODUCTS = "varietyOfProducts";
	/** Qualifier of the <code>FeedBackEmailProcess.others</code> attribute **/
	public static final String OTHERS = "others";
	/** Qualifier of the <code>FeedBackEmailProcess.feedbackcomments</code> attribute **/
	public static final String FEEDBACKCOMMENTS = "feedbackcomments";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(StoreFrontCustomerProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(VISITOPTIONS, AttributeMode.INITIAL);
		tmp.put(EASEOFFINDINGINFO, AttributeMode.INITIAL);
		tmp.put(PRODUCTAVAILABILITY, AttributeMode.INITIAL);
		tmp.put(VARIETYOFPRODUCTS, AttributeMode.INITIAL);
		tmp.put(OTHERS, AttributeMode.INITIAL);
		tmp.put(FEEDBACKCOMMENTS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.easeOfFindingInfo</code> attribute.
	 * @return the easeOfFindingInfo
	 */
	public String getEaseOfFindingInfo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EASEOFFINDINGINFO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.easeOfFindingInfo</code> attribute.
	 * @return the easeOfFindingInfo
	 */
	public String getEaseOfFindingInfo()
	{
		return getEaseOfFindingInfo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.easeOfFindingInfo</code> attribute. 
	 * @param value the easeOfFindingInfo
	 */
	public void setEaseOfFindingInfo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EASEOFFINDINGINFO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.easeOfFindingInfo</code> attribute. 
	 * @param value the easeOfFindingInfo
	 */
	public void setEaseOfFindingInfo(final String value)
	{
		setEaseOfFindingInfo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.feedbackcomments</code> attribute.
	 * @return the feedbackcomments
	 */
	public String getFeedbackcomments(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FEEDBACKCOMMENTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.feedbackcomments</code> attribute.
	 * @return the feedbackcomments
	 */
	public String getFeedbackcomments()
	{
		return getFeedbackcomments( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.feedbackcomments</code> attribute. 
	 * @param value the feedbackcomments
	 */
	public void setFeedbackcomments(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FEEDBACKCOMMENTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.feedbackcomments</code> attribute. 
	 * @param value the feedbackcomments
	 */
	public void setFeedbackcomments(final String value)
	{
		setFeedbackcomments( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.others</code> attribute.
	 * @return the others
	 */
	public String getOthers(final SessionContext ctx)
	{
		return (String)getProperty( ctx, OTHERS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.others</code> attribute.
	 * @return the others
	 */
	public String getOthers()
	{
		return getOthers( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.others</code> attribute. 
	 * @param value the others
	 */
	public void setOthers(final SessionContext ctx, final String value)
	{
		setProperty(ctx, OTHERS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.others</code> attribute. 
	 * @param value the others
	 */
	public void setOthers(final String value)
	{
		setOthers( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.productAvailability</code> attribute.
	 * @return the productAvailability
	 */
	public String getProductAvailability(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PRODUCTAVAILABILITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.productAvailability</code> attribute.
	 * @return the productAvailability
	 */
	public String getProductAvailability()
	{
		return getProductAvailability( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.productAvailability</code> attribute. 
	 * @param value the productAvailability
	 */
	public void setProductAvailability(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PRODUCTAVAILABILITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.productAvailability</code> attribute. 
	 * @param value the productAvailability
	 */
	public void setProductAvailability(final String value)
	{
		setProductAvailability( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.varietyOfProducts</code> attribute.
	 * @return the varietyOfProducts
	 */
	public String getVarietyOfProducts(final SessionContext ctx)
	{
		return (String)getProperty( ctx, VARIETYOFPRODUCTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.varietyOfProducts</code> attribute.
	 * @return the varietyOfProducts
	 */
	public String getVarietyOfProducts()
	{
		return getVarietyOfProducts( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.varietyOfProducts</code> attribute. 
	 * @param value the varietyOfProducts
	 */
	public void setVarietyOfProducts(final SessionContext ctx, final String value)
	{
		setProperty(ctx, VARIETYOFPRODUCTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.varietyOfProducts</code> attribute. 
	 * @param value the varietyOfProducts
	 */
	public void setVarietyOfProducts(final String value)
	{
		setVarietyOfProducts( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.visitOptions</code> attribute.
	 * @return the visitOptions
	 */
	public String getVisitOptions(final SessionContext ctx)
	{
		return (String)getProperty( ctx, VISITOPTIONS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeedBackEmailProcess.visitOptions</code> attribute.
	 * @return the visitOptions
	 */
	public String getVisitOptions()
	{
		return getVisitOptions( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.visitOptions</code> attribute. 
	 * @param value the visitOptions
	 */
	public void setVisitOptions(final SessionContext ctx, final String value)
	{
		setProperty(ctx, VISITOPTIONS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeedBackEmailProcess.visitOptions</code> attribute. 
	 * @param value the visitOptions
	 */
	public void setVisitOptions(final String value)
	{
		setVisitOptions( getSession().getSessionContext(), value );
	}
	
}
