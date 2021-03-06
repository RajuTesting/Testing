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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem DeliveryPostalCode}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedDeliveryPostalCode extends GenericItem
{
	/** Qualifier of the <code>DeliveryPostalCode.pinCode</code> attribute **/
	public static final String PINCODE = "pinCode";
	/** Qualifier of the <code>DeliveryPostalCode.city</code> attribute **/
	public static final String CITY = "city";
	/** Qualifier of the <code>DeliveryPostalCode.state</code> attribute **/
	public static final String STATE = "state";
	/** Qualifier of the <code>DeliveryPostalCode.shipper</code> attribute **/
	public static final String SHIPPER = "shipper";
	/** Qualifier of the <code>DeliveryPostalCode.cod</code> attribute **/
	public static final String COD = "cod";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PINCODE, AttributeMode.INITIAL);
		tmp.put(CITY, AttributeMode.INITIAL);
		tmp.put(STATE, AttributeMode.INITIAL);
		tmp.put(SHIPPER, AttributeMode.INITIAL);
		tmp.put(COD, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.city</code> attribute.
	 * @return the city
	 */
	public String getCity(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.city</code> attribute.
	 * @return the city
	 */
	public String getCity()
	{
		return getCity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.city</code> attribute. 
	 * @param value the city
	 */
	public void setCity(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.city</code> attribute. 
	 * @param value the city
	 */
	public void setCity(final String value)
	{
		setCity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.cod</code> attribute.
	 * @return the cod
	 */
	public Boolean isCod(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, COD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.cod</code> attribute.
	 * @return the cod
	 */
	public Boolean isCod()
	{
		return isCod( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.cod</code> attribute. 
	 * @return the cod
	 */
	public boolean isCodAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isCod( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.cod</code> attribute. 
	 * @return the cod
	 */
	public boolean isCodAsPrimitive()
	{
		return isCodAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.cod</code> attribute. 
	 * @param value the cod
	 */
	public void setCod(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, COD,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.cod</code> attribute. 
	 * @param value the cod
	 */
	public void setCod(final Boolean value)
	{
		setCod( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.cod</code> attribute. 
	 * @param value the cod
	 */
	public void setCod(final SessionContext ctx, final boolean value)
	{
		setCod( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.cod</code> attribute. 
	 * @param value the cod
	 */
	public void setCod(final boolean value)
	{
		setCod( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.pinCode</code> attribute.
	 * @return the pinCode
	 */
	public String getPinCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PINCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.pinCode</code> attribute.
	 * @return the pinCode
	 */
	public String getPinCode()
	{
		return getPinCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.pinCode</code> attribute. 
	 * @param value the pinCode
	 */
	public void setPinCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PINCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.pinCode</code> attribute. 
	 * @param value the pinCode
	 */
	public void setPinCode(final String value)
	{
		setPinCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.shipper</code> attribute.
	 * @return the shipper
	 */
	public String getShipper(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SHIPPER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.shipper</code> attribute.
	 * @return the shipper
	 */
	public String getShipper()
	{
		return getShipper( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.shipper</code> attribute. 
	 * @param value the shipper
	 */
	public void setShipper(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SHIPPER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.shipper</code> attribute. 
	 * @param value the shipper
	 */
	public void setShipper(final String value)
	{
		setShipper( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.state</code> attribute.
	 * @return the state
	 */
	public String getState(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliveryPostalCode.state</code> attribute.
	 * @return the state
	 */
	public String getState()
	{
		return getState( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.state</code> attribute. 
	 * @param value the state
	 */
	public void setState(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliveryPostalCode.state</code> attribute. 
	 * @param value the state
	 */
	public void setState(final String value)
	{
		setState( getSession().getSessionContext(), value );
	}
	
}
