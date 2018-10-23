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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem MobileVersionUpdateInfo}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedMobileVersionUpdateInfo extends GenericItem
{
	/** Qualifier of the <code>MobileVersionUpdateInfo.mobileType</code> attribute **/
	public static final String MOBILETYPE = "mobileType";
	/** Qualifier of the <code>MobileVersionUpdateInfo.forceUpdateVersion</code> attribute **/
	public static final String FORCEUPDATEVERSION = "forceUpdateVersion";
	/** Qualifier of the <code>MobileVersionUpdateInfo.softUpdateVersion</code> attribute **/
	public static final String SOFTUPDATEVERSION = "softUpdateVersion";
	/** Qualifier of the <code>MobileVersionUpdateInfo.osLowerVersion</code> attribute **/
	public static final String OSLOWERVERSION = "osLowerVersion";
	/** Qualifier of the <code>MobileVersionUpdateInfo.osUpperVersion</code> attribute **/
	public static final String OSUPPERVERSION = "osUpperVersion";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(MOBILETYPE, AttributeMode.INITIAL);
		tmp.put(FORCEUPDATEVERSION, AttributeMode.INITIAL);
		tmp.put(SOFTUPDATEVERSION, AttributeMode.INITIAL);
		tmp.put(OSLOWERVERSION, AttributeMode.INITIAL);
		tmp.put(OSUPPERVERSION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileVersionUpdateInfo.forceUpdateVersion</code> attribute.
	 * @return the forceUpdateVersion - Software Version for force update
	 */
	public String getForceUpdateVersion(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FORCEUPDATEVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileVersionUpdateInfo.forceUpdateVersion</code> attribute.
	 * @return the forceUpdateVersion - Software Version for force update
	 */
	public String getForceUpdateVersion()
	{
		return getForceUpdateVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileVersionUpdateInfo.forceUpdateVersion</code> attribute. 
	 * @param value the forceUpdateVersion - Software Version for force update
	 */
	public void setForceUpdateVersion(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FORCEUPDATEVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileVersionUpdateInfo.forceUpdateVersion</code> attribute. 
	 * @param value the forceUpdateVersion - Software Version for force update
	 */
	public void setForceUpdateVersion(final String value)
	{
		setForceUpdateVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileVersionUpdateInfo.mobileType</code> attribute.
	 * @return the mobileType - Type of the Mobile eg. ANDROID, IOS
	 */
	public EnumerationValue getMobileType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, MOBILETYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileVersionUpdateInfo.mobileType</code> attribute.
	 * @return the mobileType - Type of the Mobile eg. ANDROID, IOS
	 */
	public EnumerationValue getMobileType()
	{
		return getMobileType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileVersionUpdateInfo.mobileType</code> attribute. 
	 * @param value the mobileType - Type of the Mobile eg. ANDROID, IOS
	 */
	public void setMobileType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, MOBILETYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileVersionUpdateInfo.mobileType</code> attribute. 
	 * @param value the mobileType - Type of the Mobile eg. ANDROID, IOS
	 */
	public void setMobileType(final EnumerationValue value)
	{
		setMobileType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileVersionUpdateInfo.osLowerVersion</code> attribute.
	 * @return the osLowerVersion - Software Version for soft update
	 */
	public String getOsLowerVersion(final SessionContext ctx)
	{
		return (String)getProperty( ctx, OSLOWERVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileVersionUpdateInfo.osLowerVersion</code> attribute.
	 * @return the osLowerVersion - Software Version for soft update
	 */
	public String getOsLowerVersion()
	{
		return getOsLowerVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileVersionUpdateInfo.osLowerVersion</code> attribute. 
	 * @param value the osLowerVersion - Software Version for soft update
	 */
	public void setOsLowerVersion(final SessionContext ctx, final String value)
	{
		setProperty(ctx, OSLOWERVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileVersionUpdateInfo.osLowerVersion</code> attribute. 
	 * @param value the osLowerVersion - Software Version for soft update
	 */
	public void setOsLowerVersion(final String value)
	{
		setOsLowerVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileVersionUpdateInfo.osUpperVersion</code> attribute.
	 * @return the osUpperVersion - Software Version for soft update
	 */
	public String getOsUpperVersion(final SessionContext ctx)
	{
		return (String)getProperty( ctx, OSUPPERVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileVersionUpdateInfo.osUpperVersion</code> attribute.
	 * @return the osUpperVersion - Software Version for soft update
	 */
	public String getOsUpperVersion()
	{
		return getOsUpperVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileVersionUpdateInfo.osUpperVersion</code> attribute. 
	 * @param value the osUpperVersion - Software Version for soft update
	 */
	public void setOsUpperVersion(final SessionContext ctx, final String value)
	{
		setProperty(ctx, OSUPPERVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileVersionUpdateInfo.osUpperVersion</code> attribute. 
	 * @param value the osUpperVersion - Software Version for soft update
	 */
	public void setOsUpperVersion(final String value)
	{
		setOsUpperVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileVersionUpdateInfo.softUpdateVersion</code> attribute.
	 * @return the softUpdateVersion - Software Version for soft update
	 */
	public String getSoftUpdateVersion(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SOFTUPDATEVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MobileVersionUpdateInfo.softUpdateVersion</code> attribute.
	 * @return the softUpdateVersion - Software Version for soft update
	 */
	public String getSoftUpdateVersion()
	{
		return getSoftUpdateVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileVersionUpdateInfo.softUpdateVersion</code> attribute. 
	 * @param value the softUpdateVersion - Software Version for soft update
	 */
	public void setSoftUpdateVersion(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SOFTUPDATEVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MobileVersionUpdateInfo.softUpdateVersion</code> attribute. 
	 * @param value the softUpdateVersion - Software Version for soft update
	 */
	public void setSoftUpdateVersion(final String value)
	{
		setSoftUpdateVersion( getSession().getSessionContext(), value );
	}
	
}
