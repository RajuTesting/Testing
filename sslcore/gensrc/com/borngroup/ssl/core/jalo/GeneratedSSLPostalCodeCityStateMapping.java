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
import de.hybris.platform.jalo.c2l.Country;
import de.hybris.platform.jalo.c2l.Region;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SSLPostalCodeCityStateMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLPostalCodeCityStateMapping extends GenericItem
{
	/** Qualifier of the <code>SSLPostalCodeCityStateMapping.pinCode</code> attribute **/
	public static final String PINCODE = "pinCode";
	/** Qualifier of the <code>SSLPostalCodeCityStateMapping.city</code> attribute **/
	public static final String CITY = "city";
	/** Qualifier of the <code>SSLPostalCodeCityStateMapping.state</code> attribute **/
	public static final String STATE = "state";
	/** Qualifier of the <code>SSLPostalCodeCityStateMapping.region</code> attribute **/
	public static final String REGION = "region";
	/** Qualifier of the <code>SSLPostalCodeCityStateMapping.country</code> attribute **/
	public static final String COUNTRY = "country";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PINCODE, AttributeMode.INITIAL);
		tmp.put(CITY, AttributeMode.INITIAL);
		tmp.put(STATE, AttributeMode.INITIAL);
		tmp.put(REGION, AttributeMode.INITIAL);
		tmp.put(COUNTRY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPostalCodeCityStateMapping.city</code> attribute.
	 * @return the city
	 */
	public String getCity(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPostalCodeCityStateMapping.city</code> attribute.
	 * @return the city
	 */
	public String getCity()
	{
		return getCity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPostalCodeCityStateMapping.city</code> attribute. 
	 * @param value the city
	 */
	public void setCity(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPostalCodeCityStateMapping.city</code> attribute. 
	 * @param value the city
	 */
	public void setCity(final String value)
	{
		setCity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPostalCodeCityStateMapping.country</code> attribute.
	 * @return the country
	 */
	public Country getCountry(final SessionContext ctx)
	{
		return (Country)getProperty( ctx, COUNTRY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPostalCodeCityStateMapping.country</code> attribute.
	 * @return the country
	 */
	public Country getCountry()
	{
		return getCountry( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPostalCodeCityStateMapping.country</code> attribute. 
	 * @param value the country
	 */
	public void setCountry(final SessionContext ctx, final Country value)
	{
		setProperty(ctx, COUNTRY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPostalCodeCityStateMapping.country</code> attribute. 
	 * @param value the country
	 */
	public void setCountry(final Country value)
	{
		setCountry( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPostalCodeCityStateMapping.pinCode</code> attribute.
	 * @return the pinCode
	 */
	public String getPinCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PINCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPostalCodeCityStateMapping.pinCode</code> attribute.
	 * @return the pinCode
	 */
	public String getPinCode()
	{
		return getPinCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPostalCodeCityStateMapping.pinCode</code> attribute. 
	 * @param value the pinCode
	 */
	public void setPinCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PINCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPostalCodeCityStateMapping.pinCode</code> attribute. 
	 * @param value the pinCode
	 */
	public void setPinCode(final String value)
	{
		setPinCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPostalCodeCityStateMapping.region</code> attribute.
	 * @return the region
	 */
	public Region getRegion(final SessionContext ctx)
	{
		return (Region)getProperty( ctx, REGION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPostalCodeCityStateMapping.region</code> attribute.
	 * @return the region
	 */
	public Region getRegion()
	{
		return getRegion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPostalCodeCityStateMapping.region</code> attribute. 
	 * @param value the region
	 */
	public void setRegion(final SessionContext ctx, final Region value)
	{
		setProperty(ctx, REGION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPostalCodeCityStateMapping.region</code> attribute. 
	 * @param value the region
	 */
	public void setRegion(final Region value)
	{
		setRegion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPostalCodeCityStateMapping.state</code> attribute.
	 * @return the state
	 */
	public String getState(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPostalCodeCityStateMapping.state</code> attribute.
	 * @return the state
	 */
	public String getState()
	{
		return getState( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPostalCodeCityStateMapping.state</code> attribute. 
	 * @param value the state
	 */
	public void setState(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPostalCodeCityStateMapping.state</code> attribute. 
	 * @param value the state
	 */
	public void setState(final String value)
	{
		setState( getSession().getSessionContext(), value );
	}
	
}
