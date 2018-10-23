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
import de.hybris.platform.ordersplitting.jalo.Warehouse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem PincodePickupStoreMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPincodePickupStoreMapping extends GenericItem
{
	/** Qualifier of the <code>PincodePickupStoreMapping.pinCode</code> attribute **/
	public static final String PINCODE = "pinCode";
	/** Qualifier of the <code>PincodePickupStoreMapping.pickupStoreLocation</code> attribute **/
	public static final String PICKUPSTORELOCATION = "pickupStoreLocation";
	/** Qualifier of the <code>PincodePickupStoreMapping.odc</code> attribute **/
	public static final String ODC = "odc";
	/** Qualifier of the <code>PincodePickupStoreMapping.pickupStorePriority</code> attribute **/
	public static final String PICKUPSTOREPRIORITY = "pickupStorePriority";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PINCODE, AttributeMode.INITIAL);
		tmp.put(PICKUPSTORELOCATION, AttributeMode.INITIAL);
		tmp.put(ODC, AttributeMode.INITIAL);
		tmp.put(PICKUPSTOREPRIORITY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PincodePickupStoreMapping.odc</code> attribute.
	 * @return the odc - ODC
	 */
	public String getOdc(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ODC);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PincodePickupStoreMapping.odc</code> attribute.
	 * @return the odc - ODC
	 */
	public String getOdc()
	{
		return getOdc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PincodePickupStoreMapping.odc</code> attribute. 
	 * @param value the odc - ODC
	 */
	public void setOdc(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ODC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PincodePickupStoreMapping.odc</code> attribute. 
	 * @param value the odc - ODC
	 */
	public void setOdc(final String value)
	{
		setOdc( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PincodePickupStoreMapping.pickupStoreLocation</code> attribute.
	 * @return the pickupStoreLocation - pickup-store mapped to Pincode
	 */
	public Warehouse getPickupStoreLocation(final SessionContext ctx)
	{
		return (Warehouse)getProperty( ctx, PICKUPSTORELOCATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PincodePickupStoreMapping.pickupStoreLocation</code> attribute.
	 * @return the pickupStoreLocation - pickup-store mapped to Pincode
	 */
	public Warehouse getPickupStoreLocation()
	{
		return getPickupStoreLocation( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PincodePickupStoreMapping.pickupStoreLocation</code> attribute. 
	 * @param value the pickupStoreLocation - pickup-store mapped to Pincode
	 */
	public void setPickupStoreLocation(final SessionContext ctx, final Warehouse value)
	{
		setProperty(ctx, PICKUPSTORELOCATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PincodePickupStoreMapping.pickupStoreLocation</code> attribute. 
	 * @param value the pickupStoreLocation - pickup-store mapped to Pincode
	 */
	public void setPickupStoreLocation(final Warehouse value)
	{
		setPickupStoreLocation( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PincodePickupStoreMapping.pickupStorePriority</code> attribute.
	 * @return the pickupStorePriority - Priority for pickup-stores mapped against respective Pincodes
	 */
	public Integer getPickupStorePriority(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, PICKUPSTOREPRIORITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PincodePickupStoreMapping.pickupStorePriority</code> attribute.
	 * @return the pickupStorePriority - Priority for pickup-stores mapped against respective Pincodes
	 */
	public Integer getPickupStorePriority()
	{
		return getPickupStorePriority( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PincodePickupStoreMapping.pickupStorePriority</code> attribute. 
	 * @return the pickupStorePriority - Priority for pickup-stores mapped against respective Pincodes
	 */
	public int getPickupStorePriorityAsPrimitive(final SessionContext ctx)
	{
		Integer value = getPickupStorePriority( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PincodePickupStoreMapping.pickupStorePriority</code> attribute. 
	 * @return the pickupStorePriority - Priority for pickup-stores mapped against respective Pincodes
	 */
	public int getPickupStorePriorityAsPrimitive()
	{
		return getPickupStorePriorityAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PincodePickupStoreMapping.pickupStorePriority</code> attribute. 
	 * @param value the pickupStorePriority - Priority for pickup-stores mapped against respective Pincodes
	 */
	public void setPickupStorePriority(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, PICKUPSTOREPRIORITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PincodePickupStoreMapping.pickupStorePriority</code> attribute. 
	 * @param value the pickupStorePriority - Priority for pickup-stores mapped against respective Pincodes
	 */
	public void setPickupStorePriority(final Integer value)
	{
		setPickupStorePriority( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PincodePickupStoreMapping.pickupStorePriority</code> attribute. 
	 * @param value the pickupStorePriority - Priority for pickup-stores mapped against respective Pincodes
	 */
	public void setPickupStorePriority(final SessionContext ctx, final int value)
	{
		setPickupStorePriority( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PincodePickupStoreMapping.pickupStorePriority</code> attribute. 
	 * @param value the pickupStorePriority - Priority for pickup-stores mapped against respective Pincodes
	 */
	public void setPickupStorePriority(final int value)
	{
		setPickupStorePriority( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PincodePickupStoreMapping.pinCode</code> attribute.
	 * @return the pinCode - Pin Code to be mapped
	 */
	public String getPinCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PINCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PincodePickupStoreMapping.pinCode</code> attribute.
	 * @return the pinCode - Pin Code to be mapped
	 */
	public String getPinCode()
	{
		return getPinCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PincodePickupStoreMapping.pinCode</code> attribute. 
	 * @param value the pinCode - Pin Code to be mapped
	 */
	public void setPinCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PINCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PincodePickupStoreMapping.pinCode</code> attribute. 
	 * @param value the pinCode - Pin Code to be mapped
	 */
	public void setPinCode(final String value)
	{
		setPinCode( getSession().getSessionContext(), value );
	}
	
}
