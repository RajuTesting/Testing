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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem PSServices}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPSServices extends GenericItem
{
	/** Qualifier of the <code>PSServices.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>PSServices.serviceName</code> attribute **/
	public static final String SERVICENAME = "serviceName";
	/** Qualifier of the <code>PSServices.serviceDescription</code> attribute **/
	public static final String SERVICEDESCRIPTION = "serviceDescription";
	/** Qualifier of the <code>PSServices.serviceDuration</code> attribute **/
	public static final String SERVICEDURATION = "serviceDuration";
	/** Qualifier of the <code>PSServices.shoppedPrice</code> attribute **/
	public static final String SHOPPEDPRICE = "shoppedPrice";
	/** Qualifier of the <code>PSServices.active</code> attribute **/
	public static final String ACTIVE = "active";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(SERVICENAME, AttributeMode.INITIAL);
		tmp.put(SERVICEDESCRIPTION, AttributeMode.INITIAL);
		tmp.put(SERVICEDURATION, AttributeMode.INITIAL);
		tmp.put(SHOPPEDPRICE, AttributeMode.INITIAL);
		tmp.put(ACTIVE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.active</code> attribute.
	 * @return the active
	 */
	public Boolean isActive(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ACTIVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.active</code> attribute.
	 * @return the active
	 */
	public Boolean isActive()
	{
		return isActive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.active</code> attribute. 
	 * @return the active
	 */
	public boolean isActiveAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isActive( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.active</code> attribute. 
	 * @return the active
	 */
	public boolean isActiveAsPrimitive()
	{
		return isActiveAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ACTIVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final Boolean value)
	{
		setActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final SessionContext ctx, final boolean value)
	{
		setActive( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final boolean value)
	{
		setActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.serviceDescription</code> attribute.
	 * @return the serviceDescription
	 */
	public String getServiceDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SERVICEDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.serviceDescription</code> attribute.
	 * @return the serviceDescription
	 */
	public String getServiceDescription()
	{
		return getServiceDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.serviceDescription</code> attribute. 
	 * @param value the serviceDescription
	 */
	public void setServiceDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SERVICEDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.serviceDescription</code> attribute. 
	 * @param value the serviceDescription
	 */
	public void setServiceDescription(final String value)
	{
		setServiceDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.serviceDuration</code> attribute.
	 * @return the serviceDuration
	 */
	public Date getServiceDuration(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, SERVICEDURATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.serviceDuration</code> attribute.
	 * @return the serviceDuration
	 */
	public Date getServiceDuration()
	{
		return getServiceDuration( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.serviceDuration</code> attribute. 
	 * @param value the serviceDuration
	 */
	public void setServiceDuration(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, SERVICEDURATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.serviceDuration</code> attribute. 
	 * @param value the serviceDuration
	 */
	public void setServiceDuration(final Date value)
	{
		setServiceDuration( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.serviceName</code> attribute.
	 * @return the serviceName
	 */
	public String getServiceName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SERVICENAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.serviceName</code> attribute.
	 * @return the serviceName
	 */
	public String getServiceName()
	{
		return getServiceName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.serviceName</code> attribute. 
	 * @param value the serviceName
	 */
	public void setServiceName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SERVICENAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.serviceName</code> attribute. 
	 * @param value the serviceName
	 */
	public void setServiceName(final String value)
	{
		setServiceName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.shoppedPrice</code> attribute.
	 * @return the shoppedPrice
	 */
	public Double getShoppedPrice(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, SHOPPEDPRICE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.shoppedPrice</code> attribute.
	 * @return the shoppedPrice
	 */
	public Double getShoppedPrice()
	{
		return getShoppedPrice( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.shoppedPrice</code> attribute. 
	 * @return the shoppedPrice
	 */
	public double getShoppedPriceAsPrimitive(final SessionContext ctx)
	{
		Double value = getShoppedPrice( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSServices.shoppedPrice</code> attribute. 
	 * @return the shoppedPrice
	 */
	public double getShoppedPriceAsPrimitive()
	{
		return getShoppedPriceAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.shoppedPrice</code> attribute. 
	 * @param value the shoppedPrice
	 */
	public void setShoppedPrice(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, SHOPPEDPRICE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.shoppedPrice</code> attribute. 
	 * @param value the shoppedPrice
	 */
	public void setShoppedPrice(final Double value)
	{
		setShoppedPrice( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.shoppedPrice</code> attribute. 
	 * @param value the shoppedPrice
	 */
	public void setShoppedPrice(final SessionContext ctx, final double value)
	{
		setShoppedPrice( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSServices.shoppedPrice</code> attribute. 
	 * @param value the shoppedPrice
	 */
	public void setShoppedPrice(final double value)
	{
		setShoppedPrice( getSession().getSessionContext(), value );
	}
	
}
