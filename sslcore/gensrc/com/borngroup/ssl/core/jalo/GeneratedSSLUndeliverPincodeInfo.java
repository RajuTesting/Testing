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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SSLUndeliverPincodeInfo}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLUndeliverPincodeInfo extends GenericItem
{
	/** Qualifier of the <code>SSLUndeliverPincodeInfo.pincode</code> attribute **/
	public static final String PINCODE = "pincode";
	/** Qualifier of the <code>SSLUndeliverPincodeInfo.totalCODFailureCount</code> attribute **/
	public static final String TOTALCODFAILURECOUNT = "totalCODFailureCount";
	/** Qualifier of the <code>SSLUndeliverPincodeInfo.totalfailureCount</code> attribute **/
	public static final String TOTALFAILURECOUNT = "totalfailureCount";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PINCODE, AttributeMode.INITIAL);
		tmp.put(TOTALCODFAILURECOUNT, AttributeMode.INITIAL);
		tmp.put(TOTALFAILURECOUNT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUndeliverPincodeInfo.pincode</code> attribute.
	 * @return the pincode
	 */
	public String getPincode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PINCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUndeliverPincodeInfo.pincode</code> attribute.
	 * @return the pincode
	 */
	public String getPincode()
	{
		return getPincode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUndeliverPincodeInfo.pincode</code> attribute. 
	 * @param value the pincode
	 */
	public void setPincode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PINCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUndeliverPincodeInfo.pincode</code> attribute. 
	 * @param value the pincode
	 */
	public void setPincode(final String value)
	{
		setPincode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUndeliverPincodeInfo.totalCODFailureCount</code> attribute.
	 * @return the totalCODFailureCount
	 */
	public Integer getTotalCODFailureCount(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, TOTALCODFAILURECOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUndeliverPincodeInfo.totalCODFailureCount</code> attribute.
	 * @return the totalCODFailureCount
	 */
	public Integer getTotalCODFailureCount()
	{
		return getTotalCODFailureCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUndeliverPincodeInfo.totalCODFailureCount</code> attribute. 
	 * @return the totalCODFailureCount
	 */
	public int getTotalCODFailureCountAsPrimitive(final SessionContext ctx)
	{
		Integer value = getTotalCODFailureCount( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUndeliverPincodeInfo.totalCODFailureCount</code> attribute. 
	 * @return the totalCODFailureCount
	 */
	public int getTotalCODFailureCountAsPrimitive()
	{
		return getTotalCODFailureCountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUndeliverPincodeInfo.totalCODFailureCount</code> attribute. 
	 * @param value the totalCODFailureCount
	 */
	public void setTotalCODFailureCount(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, TOTALCODFAILURECOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUndeliverPincodeInfo.totalCODFailureCount</code> attribute. 
	 * @param value the totalCODFailureCount
	 */
	public void setTotalCODFailureCount(final Integer value)
	{
		setTotalCODFailureCount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUndeliverPincodeInfo.totalCODFailureCount</code> attribute. 
	 * @param value the totalCODFailureCount
	 */
	public void setTotalCODFailureCount(final SessionContext ctx, final int value)
	{
		setTotalCODFailureCount( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUndeliverPincodeInfo.totalCODFailureCount</code> attribute. 
	 * @param value the totalCODFailureCount
	 */
	public void setTotalCODFailureCount(final int value)
	{
		setTotalCODFailureCount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUndeliverPincodeInfo.totalfailureCount</code> attribute.
	 * @return the totalfailureCount
	 */
	public Integer getTotalfailureCount(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, TOTALFAILURECOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUndeliverPincodeInfo.totalfailureCount</code> attribute.
	 * @return the totalfailureCount
	 */
	public Integer getTotalfailureCount()
	{
		return getTotalfailureCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUndeliverPincodeInfo.totalfailureCount</code> attribute. 
	 * @return the totalfailureCount
	 */
	public int getTotalfailureCountAsPrimitive(final SessionContext ctx)
	{
		Integer value = getTotalfailureCount( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUndeliverPincodeInfo.totalfailureCount</code> attribute. 
	 * @return the totalfailureCount
	 */
	public int getTotalfailureCountAsPrimitive()
	{
		return getTotalfailureCountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUndeliverPincodeInfo.totalfailureCount</code> attribute. 
	 * @param value the totalfailureCount
	 */
	public void setTotalfailureCount(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, TOTALFAILURECOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUndeliverPincodeInfo.totalfailureCount</code> attribute. 
	 * @param value the totalfailureCount
	 */
	public void setTotalfailureCount(final Integer value)
	{
		setTotalfailureCount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUndeliverPincodeInfo.totalfailureCount</code> attribute. 
	 * @param value the totalfailureCount
	 */
	public void setTotalfailureCount(final SessionContext ctx, final int value)
	{
		setTotalfailureCount( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUndeliverPincodeInfo.totalfailureCount</code> attribute. 
	 * @param value the totalfailureCount
	 */
	public void setTotalfailureCount(final int value)
	{
		setTotalfailureCount( getSession().getSessionContext(), value );
	}
	
}
