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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.MobileAppReleaseDetails MobileAppReleaseDetails}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedMobileAppReleaseDetails extends GenericItem
{
	/** Qualifier of the <code>MobileAppReleaseDetails.deviceName</code> attribute **/
	public static final String DEVICENAME = "deviceName";
	/** Qualifier of the <code>MobileAppReleaseDetails.versionId</code> attribute **/
	public static final String VERSIONID = "versionId";
	/** Qualifier of the <code>MobileAppReleaseDetails.forceUpdate</code> attribute **/
	public static final String FORCEUPDATE = "forceUpdate";
	/** Qualifier of the <code>MobileAppReleaseDetails.serviceType</code> attribute **/
	public static final String SERVICETYPE = "serviceType";
	/** Qualifier of the <code>MobileAppReleaseDetails.releaseNotes</code> attribute **/
	public static final String RELEASENOTES = "releaseNotes";
	/** Qualifier of the <code>MobileAppReleaseDetails.appType</code> attribute **/
	public static final String APPTYPE = "appType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(DEVICENAME, AttributeMode.INITIAL);
		tmp.put(VERSIONID, AttributeMode.INITIAL);
		tmp.put(FORCEUPDATE, AttributeMode.INITIAL);
		tmp.put(SERVICETYPE, AttributeMode.INITIAL);
		tmp.put(RELEASENOTES, AttributeMode.INITIAL);
		tmp.put(APPTYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.appType</code> attribute.
	 * @return the appType - Mobile App Device Type
	 */
	public EnumerationValue getAppType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, APPTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.appType</code> attribute.
	 * @return the appType - Mobile App Device Type
	 */
	public EnumerationValue getAppType()
	{
		return getAppType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.appType</code> attribute. 
	 * @param value the appType - Mobile App Device Type
	 */
	public void setAppType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, APPTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.appType</code> attribute. 
	 * @param value the appType - Mobile App Device Type
	 */
	public void setAppType(final EnumerationValue value)
	{
		setAppType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.deviceName</code> attribute.
	 * @return the deviceName - Name which uniquely identifies
	 */
	public String getDeviceName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEVICENAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.deviceName</code> attribute.
	 * @return the deviceName - Name which uniquely identifies
	 */
	public String getDeviceName()
	{
		return getDeviceName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.deviceName</code> attribute. 
	 * @param value the deviceName - Name which uniquely identifies
	 */
	public void setDeviceName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEVICENAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.deviceName</code> attribute. 
	 * @param value the deviceName - Name which uniquely identifies
	 */
	public void setDeviceName(final String value)
	{
		setDeviceName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.forceUpdate</code> attribute.
	 * @return the forceUpdate - Force Update Required or Not
	 */
	public Boolean isForceUpdate(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, FORCEUPDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.forceUpdate</code> attribute.
	 * @return the forceUpdate - Force Update Required or Not
	 */
	public Boolean isForceUpdate()
	{
		return isForceUpdate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.forceUpdate</code> attribute. 
	 * @return the forceUpdate - Force Update Required or Not
	 */
	public boolean isForceUpdateAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isForceUpdate( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.forceUpdate</code> attribute. 
	 * @return the forceUpdate - Force Update Required or Not
	 */
	public boolean isForceUpdateAsPrimitive()
	{
		return isForceUpdateAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.forceUpdate</code> attribute. 
	 * @param value the forceUpdate - Force Update Required or Not
	 */
	public void setForceUpdate(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, FORCEUPDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.forceUpdate</code> attribute. 
	 * @param value the forceUpdate - Force Update Required or Not
	 */
	public void setForceUpdate(final Boolean value)
	{
		setForceUpdate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.forceUpdate</code> attribute. 
	 * @param value the forceUpdate - Force Update Required or Not
	 */
	public void setForceUpdate(final SessionContext ctx, final boolean value)
	{
		setForceUpdate( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.forceUpdate</code> attribute. 
	 * @param value the forceUpdate - Force Update Required or Not
	 */
	public void setForceUpdate(final boolean value)
	{
		setForceUpdate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.releaseNotes</code> attribute.
	 * @return the releaseNotes - Release Notes For Mobile
	 */
	public String getReleaseNotes(final SessionContext ctx)
	{
		return (String)getProperty( ctx, RELEASENOTES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.releaseNotes</code> attribute.
	 * @return the releaseNotes - Release Notes For Mobile
	 */
	public String getReleaseNotes()
	{
		return getReleaseNotes( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.releaseNotes</code> attribute. 
	 * @param value the releaseNotes - Release Notes For Mobile
	 */
	public void setReleaseNotes(final SessionContext ctx, final String value)
	{
		setProperty(ctx, RELEASENOTES,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.releaseNotes</code> attribute. 
	 * @param value the releaseNotes - Release Notes For Mobile
	 */
	public void setReleaseNotes(final String value)
	{
		setReleaseNotes( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.serviceType</code> attribute.
	 * @return the serviceType - Version of the Mobile App
	 */
	public String getServiceType(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SERVICETYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.serviceType</code> attribute.
	 * @return the serviceType - Version of the Mobile App
	 */
	public String getServiceType()
	{
		return getServiceType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.serviceType</code> attribute. 
	 * @param value the serviceType - Version of the Mobile App
	 */
	public void setServiceType(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SERVICETYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.serviceType</code> attribute. 
	 * @param value the serviceType - Version of the Mobile App
	 */
	public void setServiceType(final String value)
	{
		setServiceType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.versionId</code> attribute.
	 * @return the versionId - The version Id of Mobile App
	 */
	public String getVersionId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, VERSIONID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileAppReleaseDetails.versionId</code> attribute.
	 * @return the versionId - The version Id of Mobile App
	 */
	public String getVersionId()
	{
		return getVersionId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.versionId</code> attribute. 
	 * @param value the versionId - The version Id of Mobile App
	 */
	public void setVersionId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, VERSIONID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileAppReleaseDetails.versionId</code> attribute. 
	 * @param value the versionId - The version Id of Mobile App
	 */
	public void setVersionId(final String value)
	{
		setVersionId( getSession().getSessionContext(), value );
	}
	
}
