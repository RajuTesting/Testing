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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SterlingHybrisStatusMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSterlingHybrisStatusMapping extends GenericItem
{
	/** Qualifier of the <code>SterlingHybrisStatusMapping.statusCode</code> attribute **/
	public static final String STATUSCODE = "statusCode";
	/** Qualifier of the <code>SterlingHybrisStatusMapping.hybrisStatus</code> attribute **/
	public static final String HYBRISSTATUS = "hybrisStatus";
	/** Qualifier of the <code>SterlingHybrisStatusMapping.sterlingStatusDesc</code> attribute **/
	public static final String STERLINGSTATUSDESC = "sterlingStatusDesc";
	/** Qualifier of the <code>SterlingHybrisStatusMapping.statusType</code> attribute **/
	public static final String STATUSTYPE = "statusType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(STATUSCODE, AttributeMode.INITIAL);
		tmp.put(HYBRISSTATUS, AttributeMode.INITIAL);
		tmp.put(STERLINGSTATUSDESC, AttributeMode.INITIAL);
		tmp.put(STATUSTYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingHybrisStatusMapping.hybrisStatus</code> attribute.
	 * @return the hybrisStatus
	 */
	public EnumerationValue getHybrisStatus(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, HYBRISSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingHybrisStatusMapping.hybrisStatus</code> attribute.
	 * @return the hybrisStatus
	 */
	public EnumerationValue getHybrisStatus()
	{
		return getHybrisStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingHybrisStatusMapping.hybrisStatus</code> attribute. 
	 * @param value the hybrisStatus
	 */
	public void setHybrisStatus(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, HYBRISSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingHybrisStatusMapping.hybrisStatus</code> attribute. 
	 * @param value the hybrisStatus
	 */
	public void setHybrisStatus(final EnumerationValue value)
	{
		setHybrisStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingHybrisStatusMapping.statusCode</code> attribute.
	 * @return the statusCode
	 */
	public String getStatusCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STATUSCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingHybrisStatusMapping.statusCode</code> attribute.
	 * @return the statusCode
	 */
	public String getStatusCode()
	{
		return getStatusCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingHybrisStatusMapping.statusCode</code> attribute. 
	 * @param value the statusCode
	 */
	public void setStatusCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STATUSCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingHybrisStatusMapping.statusCode</code> attribute. 
	 * @param value the statusCode
	 */
	public void setStatusCode(final String value)
	{
		setStatusCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingHybrisStatusMapping.statusType</code> attribute.
	 * @return the statusType
	 */
	public EnumerationValue getStatusType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, STATUSTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingHybrisStatusMapping.statusType</code> attribute.
	 * @return the statusType
	 */
	public EnumerationValue getStatusType()
	{
		return getStatusType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingHybrisStatusMapping.statusType</code> attribute. 
	 * @param value the statusType
	 */
	public void setStatusType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, STATUSTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingHybrisStatusMapping.statusType</code> attribute. 
	 * @param value the statusType
	 */
	public void setStatusType(final EnumerationValue value)
	{
		setStatusType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingHybrisStatusMapping.sterlingStatusDesc</code> attribute.
	 * @return the sterlingStatusDesc
	 */
	public String getSterlingStatusDesc(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STERLINGSTATUSDESC);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingHybrisStatusMapping.sterlingStatusDesc</code> attribute.
	 * @return the sterlingStatusDesc
	 */
	public String getSterlingStatusDesc()
	{
		return getSterlingStatusDesc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingHybrisStatusMapping.sterlingStatusDesc</code> attribute. 
	 * @param value the sterlingStatusDesc
	 */
	public void setSterlingStatusDesc(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STERLINGSTATUSDESC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingHybrisStatusMapping.sterlingStatusDesc</code> attribute. 
	 * @param value the sterlingStatusDesc
	 */
	public void setSterlingStatusDesc(final String value)
	{
		setSterlingStatusDesc( getSession().getSessionContext(), value );
	}
	
}
