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
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SSLUniqueDeviceIdentifier}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLUniqueDeviceIdentifier extends GenericItem
{
	/** Qualifier of the <code>SSLUniqueDeviceIdentifier.deviceBrand</code> attribute **/
	public static final String DEVICEBRAND = "deviceBrand";
	/** Qualifier of the <code>SSLUniqueDeviceIdentifier.deviceModel</code> attribute **/
	public static final String DEVICEMODEL = "deviceModel";
	/** Qualifier of the <code>SSLUniqueDeviceIdentifier.osVersion</code> attribute **/
	public static final String OSVERSION = "osVersion";
	/** Qualifier of the <code>SSLUniqueDeviceIdentifier.osName</code> attribute **/
	public static final String OSNAME = "osName";
	/** Qualifier of the <code>SSLUniqueDeviceIdentifier.appVersion</code> attribute **/
	public static final String APPVERSION = "appVersion";
	/** Qualifier of the <code>SSLUniqueDeviceIdentifier.email</code> attribute **/
	public static final String EMAIL = "email";
	/** Qualifier of the <code>SSLUniqueDeviceIdentifier.screenSize</code> attribute **/
	public static final String SCREENSIZE = "screenSize";
	/** Qualifier of the <code>SSLUniqueDeviceIdentifier.uniqueId</code> attribute **/
	public static final String UNIQUEID = "uniqueId";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(DEVICEBRAND, AttributeMode.INITIAL);
		tmp.put(DEVICEMODEL, AttributeMode.INITIAL);
		tmp.put(OSVERSION, AttributeMode.INITIAL);
		tmp.put(OSNAME, AttributeMode.INITIAL);
		tmp.put(APPVERSION, AttributeMode.INITIAL);
		tmp.put(EMAIL, AttributeMode.INITIAL);
		tmp.put(SCREENSIZE, AttributeMode.INITIAL);
		tmp.put(UNIQUEID, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.appVersion</code> attribute.
	 * @return the appVersion
	 */
	public Double getAppVersion(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, APPVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.appVersion</code> attribute.
	 * @return the appVersion
	 */
	public Double getAppVersion()
	{
		return getAppVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.appVersion</code> attribute. 
	 * @return the appVersion
	 */
	public double getAppVersionAsPrimitive(final SessionContext ctx)
	{
		Double value = getAppVersion( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.appVersion</code> attribute. 
	 * @return the appVersion
	 */
	public double getAppVersionAsPrimitive()
	{
		return getAppVersionAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.appVersion</code> attribute. 
	 * @param value the appVersion
	 */
	public void setAppVersion(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, APPVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.appVersion</code> attribute. 
	 * @param value the appVersion
	 */
	public void setAppVersion(final Double value)
	{
		setAppVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.appVersion</code> attribute. 
	 * @param value the appVersion
	 */
	public void setAppVersion(final SessionContext ctx, final double value)
	{
		setAppVersion( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.appVersion</code> attribute. 
	 * @param value the appVersion
	 */
	public void setAppVersion(final double value)
	{
		setAppVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.deviceBrand</code> attribute.
	 * @return the deviceBrand
	 */
	public String getDeviceBrand(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEVICEBRAND);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.deviceBrand</code> attribute.
	 * @return the deviceBrand
	 */
	public String getDeviceBrand()
	{
		return getDeviceBrand( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.deviceBrand</code> attribute. 
	 * @param value the deviceBrand
	 */
	public void setDeviceBrand(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEVICEBRAND,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.deviceBrand</code> attribute. 
	 * @param value the deviceBrand
	 */
	public void setDeviceBrand(final String value)
	{
		setDeviceBrand( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.deviceModel</code> attribute.
	 * @return the deviceModel
	 */
	public String getDeviceModel(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEVICEMODEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.deviceModel</code> attribute.
	 * @return the deviceModel
	 */
	public String getDeviceModel()
	{
		return getDeviceModel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.deviceModel</code> attribute. 
	 * @param value the deviceModel
	 */
	public void setDeviceModel(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEVICEMODEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.deviceModel</code> attribute. 
	 * @param value the deviceModel
	 */
	public void setDeviceModel(final String value)
	{
		setDeviceModel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.email</code> attribute.
	 * @return the email
	 */
	public String getEmail(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.email</code> attribute.
	 * @return the email
	 */
	public String getEmail()
	{
		return getEmail( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.email</code> attribute. 
	 * @param value the email
	 */
	public void setEmail(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.email</code> attribute. 
	 * @param value the email
	 */
	public void setEmail(final String value)
	{
		setEmail( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.osName</code> attribute.
	 * @return the osName
	 */
	public EnumerationValue getOsName(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, OSNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.osName</code> attribute.
	 * @return the osName
	 */
	public EnumerationValue getOsName()
	{
		return getOsName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.osName</code> attribute. 
	 * @param value the osName
	 */
	public void setOsName(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, OSNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.osName</code> attribute. 
	 * @param value the osName
	 */
	public void setOsName(final EnumerationValue value)
	{
		setOsName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.osVersion</code> attribute.
	 * @return the osVersion
	 */
	public String getOsVersion(final SessionContext ctx)
	{
		return (String)getProperty( ctx, OSVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.osVersion</code> attribute.
	 * @return the osVersion
	 */
	public String getOsVersion()
	{
		return getOsVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.osVersion</code> attribute. 
	 * @param value the osVersion
	 */
	public void setOsVersion(final SessionContext ctx, final String value)
	{
		setProperty(ctx, OSVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.osVersion</code> attribute. 
	 * @param value the osVersion
	 */
	public void setOsVersion(final String value)
	{
		setOsVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.screenSize</code> attribute.
	 * @return the screenSize
	 */
	public Double getScreenSize(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, SCREENSIZE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.screenSize</code> attribute.
	 * @return the screenSize
	 */
	public Double getScreenSize()
	{
		return getScreenSize( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.screenSize</code> attribute. 
	 * @return the screenSize
	 */
	public double getScreenSizeAsPrimitive(final SessionContext ctx)
	{
		Double value = getScreenSize( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.screenSize</code> attribute. 
	 * @return the screenSize
	 */
	public double getScreenSizeAsPrimitive()
	{
		return getScreenSizeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.screenSize</code> attribute. 
	 * @param value the screenSize
	 */
	public void setScreenSize(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, SCREENSIZE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.screenSize</code> attribute. 
	 * @param value the screenSize
	 */
	public void setScreenSize(final Double value)
	{
		setScreenSize( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.screenSize</code> attribute. 
	 * @param value the screenSize
	 */
	public void setScreenSize(final SessionContext ctx, final double value)
	{
		setScreenSize( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.screenSize</code> attribute. 
	 * @param value the screenSize
	 */
	public void setScreenSize(final double value)
	{
		setScreenSize( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.uniqueId</code> attribute.
	 * @return the uniqueId
	 */
	public String getUniqueId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, UNIQUEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUniqueDeviceIdentifier.uniqueId</code> attribute.
	 * @return the uniqueId
	 */
	public String getUniqueId()
	{
		return getUniqueId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.uniqueId</code> attribute. 
	 * @param value the uniqueId
	 */
	public void setUniqueId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, UNIQUEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUniqueDeviceIdentifier.uniqueId</code> attribute. 
	 * @param value the uniqueId
	 */
	public void setUniqueId(final String value)
	{
		setUniqueId( getSession().getSessionContext(), value );
	}
	
}
