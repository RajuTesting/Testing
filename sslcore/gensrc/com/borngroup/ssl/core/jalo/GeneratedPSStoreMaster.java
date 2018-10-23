/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.PSBookingType;
import com.borngroup.ssl.core.jalo.PSPersonalShopper;
import com.borngroup.ssl.core.jalo.PSServices;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.storelocator.jalo.OpeningDay;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem PSStoreMaster}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPSStoreMaster extends GenericItem
{
	/** Qualifier of the <code>PSStoreMaster.storeId</code> attribute **/
	public static final String STOREID = "storeId";
	/** Qualifier of the <code>PSStoreMaster.storeCity</code> attribute **/
	public static final String STORECITY = "storeCity";
	/** Qualifier of the <code>PSStoreMaster.storeName</code> attribute **/
	public static final String STORENAME = "storeName";
	/** Qualifier of the <code>PSStoreMaster.storeAddress1</code> attribute **/
	public static final String STOREADDRESS1 = "storeAddress1";
	/** Qualifier of the <code>PSStoreMaster.stroreAddress2</code> attribute **/
	public static final String STROREADDRESS2 = "stroreAddress2";
	/** Qualifier of the <code>PSStoreMaster.storePincode</code> attribute **/
	public static final String STOREPINCODE = "storePincode";
	/** Qualifier of the <code>PSStoreMaster.storePhone1</code> attribute **/
	public static final String STOREPHONE1 = "storePhone1";
	/** Qualifier of the <code>PSStoreMaster.storePhone2</code> attribute **/
	public static final String STOREPHONE2 = "storePhone2";
	/** Qualifier of the <code>PSStoreMaster.storeLat</code> attribute **/
	public static final String STORELAT = "storeLat";
	/** Qualifier of the <code>PSStoreMaster.storeLong</code> attribute **/
	public static final String STORELONG = "storeLong";
	/** Qualifier of the <code>PSStoreMaster.storeState</code> attribute **/
	public static final String STORESTATE = "storeState";
	/** Qualifier of the <code>PSStoreMaster.storeTimeWeekday</code> attribute **/
	public static final String STORETIMEWEEKDAY = "storeTimeWeekday";
	/** Qualifier of the <code>PSStoreMaster.storeTimeWeekend</code> attribute **/
	public static final String STORETIMEWEEKEND = "storeTimeWeekend";
	/** Qualifier of the <code>PSStoreMaster.isStoreActive</code> attribute **/
	public static final String ISSTOREACTIVE = "isStoreActive";
	/** Qualifier of the <code>PSStoreMaster.availableBookingTypes</code> attribute **/
	public static final String AVAILABLEBOOKINGTYPES = "availableBookingTypes";
	/** Qualifier of the <code>PSStoreMaster.availableBookingServices</code> attribute **/
	public static final String AVAILABLEBOOKINGSERVICES = "availableBookingServices";
	/** Qualifier of the <code>PSStoreMaster.personalShoppers</code> attribute **/
	public static final String PERSONALSHOPPERS = "personalShoppers";
	/** Relation ordering override parameter constants for PSPersonalShopperStoreRelation from ((sslcore))*/
	protected static String PSPERSONALSHOPPERSTORERELATION_SRC_ORDERED = "relation.PSPersonalShopperStoreRelation.source.ordered";
	protected static String PSPERSONALSHOPPERSTORERELATION_TGT_ORDERED = "relation.PSPersonalShopperStoreRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for PSPersonalShopperStoreRelation from ((sslcore))*/
	protected static String PSPERSONALSHOPPERSTORERELATION_MARKMODIFIED = "relation.PSPersonalShopperStoreRelation.markmodified";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(STOREID, AttributeMode.INITIAL);
		tmp.put(STORECITY, AttributeMode.INITIAL);
		tmp.put(STORENAME, AttributeMode.INITIAL);
		tmp.put(STOREADDRESS1, AttributeMode.INITIAL);
		tmp.put(STROREADDRESS2, AttributeMode.INITIAL);
		tmp.put(STOREPINCODE, AttributeMode.INITIAL);
		tmp.put(STOREPHONE1, AttributeMode.INITIAL);
		tmp.put(STOREPHONE2, AttributeMode.INITIAL);
		tmp.put(STORELAT, AttributeMode.INITIAL);
		tmp.put(STORELONG, AttributeMode.INITIAL);
		tmp.put(STORESTATE, AttributeMode.INITIAL);
		tmp.put(STORETIMEWEEKDAY, AttributeMode.INITIAL);
		tmp.put(STORETIMEWEEKEND, AttributeMode.INITIAL);
		tmp.put(ISSTOREACTIVE, AttributeMode.INITIAL);
		tmp.put(AVAILABLEBOOKINGTYPES, AttributeMode.INITIAL);
		tmp.put(AVAILABLEBOOKINGSERVICES, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.availableBookingServices</code> attribute.
	 * @return the availableBookingServices
	 */
	public List<PSServices> getAvailableBookingServices(final SessionContext ctx)
	{
		List<PSServices> coll = (List<PSServices>)getProperty( ctx, AVAILABLEBOOKINGSERVICES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.availableBookingServices</code> attribute.
	 * @return the availableBookingServices
	 */
	public List<PSServices> getAvailableBookingServices()
	{
		return getAvailableBookingServices( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.availableBookingServices</code> attribute. 
	 * @param value the availableBookingServices
	 */
	public void setAvailableBookingServices(final SessionContext ctx, final List<PSServices> value)
	{
		setProperty(ctx, AVAILABLEBOOKINGSERVICES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.availableBookingServices</code> attribute. 
	 * @param value the availableBookingServices
	 */
	public void setAvailableBookingServices(final List<PSServices> value)
	{
		setAvailableBookingServices( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.availableBookingTypes</code> attribute.
	 * @return the availableBookingTypes
	 */
	public List<PSBookingType> getAvailableBookingTypes(final SessionContext ctx)
	{
		List<PSBookingType> coll = (List<PSBookingType>)getProperty( ctx, AVAILABLEBOOKINGTYPES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.availableBookingTypes</code> attribute.
	 * @return the availableBookingTypes
	 */
	public List<PSBookingType> getAvailableBookingTypes()
	{
		return getAvailableBookingTypes( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.availableBookingTypes</code> attribute. 
	 * @param value the availableBookingTypes
	 */
	public void setAvailableBookingTypes(final SessionContext ctx, final List<PSBookingType> value)
	{
		setProperty(ctx, AVAILABLEBOOKINGTYPES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.availableBookingTypes</code> attribute. 
	 * @param value the availableBookingTypes
	 */
	public void setAvailableBookingTypes(final List<PSBookingType> value)
	{
		setAvailableBookingTypes( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.isStoreActive</code> attribute.
	 * @return the isStoreActive
	 */
	public Boolean isIsStoreActive(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ISSTOREACTIVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.isStoreActive</code> attribute.
	 * @return the isStoreActive
	 */
	public Boolean isIsStoreActive()
	{
		return isIsStoreActive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.isStoreActive</code> attribute. 
	 * @return the isStoreActive
	 */
	public boolean isIsStoreActiveAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIsStoreActive( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.isStoreActive</code> attribute. 
	 * @return the isStoreActive
	 */
	public boolean isIsStoreActiveAsPrimitive()
	{
		return isIsStoreActiveAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.isStoreActive</code> attribute. 
	 * @param value the isStoreActive
	 */
	public void setIsStoreActive(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ISSTOREACTIVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.isStoreActive</code> attribute. 
	 * @param value the isStoreActive
	 */
	public void setIsStoreActive(final Boolean value)
	{
		setIsStoreActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.isStoreActive</code> attribute. 
	 * @param value the isStoreActive
	 */
	public void setIsStoreActive(final SessionContext ctx, final boolean value)
	{
		setIsStoreActive( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.isStoreActive</code> attribute. 
	 * @param value the isStoreActive
	 */
	public void setIsStoreActive(final boolean value)
	{
		setIsStoreActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.personalShoppers</code> attribute.
	 * @return the personalShoppers
	 */
	public List<PSPersonalShopper> getPersonalShoppers(final SessionContext ctx)
	{
		final List<PSPersonalShopper> items = getLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.PSPERSONALSHOPPERSTORERELATION,
			null,
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_TGT_ORDERED, true)
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.personalShoppers</code> attribute.
	 * @return the personalShoppers
	 */
	public List<PSPersonalShopper> getPersonalShoppers()
	{
		return getPersonalShoppers( getSession().getSessionContext() );
	}
	
	public long getPersonalShoppersCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			false,
			SslCoreConstants.Relations.PSPERSONALSHOPPERSTORERELATION,
			null
		);
	}
	
	public long getPersonalShoppersCount()
	{
		return getPersonalShoppersCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.personalShoppers</code> attribute. 
	 * @param value the personalShoppers
	 */
	public void setPersonalShoppers(final SessionContext ctx, final List<PSPersonalShopper> value)
	{
		setLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.PSPERSONALSHOPPERSTORERELATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(PSPERSONALSHOPPERSTORERELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.personalShoppers</code> attribute. 
	 * @param value the personalShoppers
	 */
	public void setPersonalShoppers(final List<PSPersonalShopper> value)
	{
		setPersonalShoppers( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to personalShoppers. 
	 * @param value the item to add to personalShoppers
	 */
	public void addToPersonalShoppers(final SessionContext ctx, final PSPersonalShopper value)
	{
		addLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.PSPERSONALSHOPPERSTORERELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(PSPERSONALSHOPPERSTORERELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to personalShoppers. 
	 * @param value the item to add to personalShoppers
	 */
	public void addToPersonalShoppers(final PSPersonalShopper value)
	{
		addToPersonalShoppers( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from personalShoppers. 
	 * @param value the item to remove from personalShoppers
	 */
	public void removeFromPersonalShoppers(final SessionContext ctx, final PSPersonalShopper value)
	{
		removeLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.PSPERSONALSHOPPERSTORERELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(PSPERSONALSHOPPERSTORERELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from personalShoppers. 
	 * @param value the item to remove from personalShoppers
	 */
	public void removeFromPersonalShoppers(final PSPersonalShopper value)
	{
		removeFromPersonalShoppers( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeAddress1</code> attribute.
	 * @return the storeAddress1
	 */
	public String getStoreAddress1(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STOREADDRESS1);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeAddress1</code> attribute.
	 * @return the storeAddress1
	 */
	public String getStoreAddress1()
	{
		return getStoreAddress1( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeAddress1</code> attribute. 
	 * @param value the storeAddress1
	 */
	public void setStoreAddress1(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STOREADDRESS1,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeAddress1</code> attribute. 
	 * @param value the storeAddress1
	 */
	public void setStoreAddress1(final String value)
	{
		setStoreAddress1( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeCity</code> attribute.
	 * @return the storeCity
	 */
	public String getStoreCity(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STORECITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeCity</code> attribute.
	 * @return the storeCity
	 */
	public String getStoreCity()
	{
		return getStoreCity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeCity</code> attribute. 
	 * @param value the storeCity
	 */
	public void setStoreCity(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STORECITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeCity</code> attribute. 
	 * @param value the storeCity
	 */
	public void setStoreCity(final String value)
	{
		setStoreCity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeId</code> attribute.
	 * @return the storeId
	 */
	public String getStoreId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STOREID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeId</code> attribute.
	 * @return the storeId
	 */
	public String getStoreId()
	{
		return getStoreId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeId</code> attribute. 
	 * @param value the storeId
	 */
	public void setStoreId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STOREID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeId</code> attribute. 
	 * @param value the storeId
	 */
	public void setStoreId(final String value)
	{
		setStoreId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeLat</code> attribute.
	 * @return the storeLat
	 */
	public Double getStoreLat(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, STORELAT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeLat</code> attribute.
	 * @return the storeLat
	 */
	public Double getStoreLat()
	{
		return getStoreLat( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeLat</code> attribute. 
	 * @return the storeLat
	 */
	public double getStoreLatAsPrimitive(final SessionContext ctx)
	{
		Double value = getStoreLat( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeLat</code> attribute. 
	 * @return the storeLat
	 */
	public double getStoreLatAsPrimitive()
	{
		return getStoreLatAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeLat</code> attribute. 
	 * @param value the storeLat
	 */
	public void setStoreLat(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, STORELAT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeLat</code> attribute. 
	 * @param value the storeLat
	 */
	public void setStoreLat(final Double value)
	{
		setStoreLat( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeLat</code> attribute. 
	 * @param value the storeLat
	 */
	public void setStoreLat(final SessionContext ctx, final double value)
	{
		setStoreLat( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeLat</code> attribute. 
	 * @param value the storeLat
	 */
	public void setStoreLat(final double value)
	{
		setStoreLat( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeLong</code> attribute.
	 * @return the storeLong
	 */
	public Double getStoreLong(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, STORELONG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeLong</code> attribute.
	 * @return the storeLong
	 */
	public Double getStoreLong()
	{
		return getStoreLong( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeLong</code> attribute. 
	 * @return the storeLong
	 */
	public double getStoreLongAsPrimitive(final SessionContext ctx)
	{
		Double value = getStoreLong( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeLong</code> attribute. 
	 * @return the storeLong
	 */
	public double getStoreLongAsPrimitive()
	{
		return getStoreLongAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeLong</code> attribute. 
	 * @param value the storeLong
	 */
	public void setStoreLong(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, STORELONG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeLong</code> attribute. 
	 * @param value the storeLong
	 */
	public void setStoreLong(final Double value)
	{
		setStoreLong( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeLong</code> attribute. 
	 * @param value the storeLong
	 */
	public void setStoreLong(final SessionContext ctx, final double value)
	{
		setStoreLong( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeLong</code> attribute. 
	 * @param value the storeLong
	 */
	public void setStoreLong(final double value)
	{
		setStoreLong( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeName</code> attribute.
	 * @return the storeName
	 */
	public String getStoreName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STORENAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeName</code> attribute.
	 * @return the storeName
	 */
	public String getStoreName()
	{
		return getStoreName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeName</code> attribute. 
	 * @param value the storeName
	 */
	public void setStoreName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STORENAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeName</code> attribute. 
	 * @param value the storeName
	 */
	public void setStoreName(final String value)
	{
		setStoreName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storePhone1</code> attribute.
	 * @return the storePhone1
	 */
	public String getStorePhone1(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STOREPHONE1);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storePhone1</code> attribute.
	 * @return the storePhone1
	 */
	public String getStorePhone1()
	{
		return getStorePhone1( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storePhone1</code> attribute. 
	 * @param value the storePhone1
	 */
	public void setStorePhone1(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STOREPHONE1,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storePhone1</code> attribute. 
	 * @param value the storePhone1
	 */
	public void setStorePhone1(final String value)
	{
		setStorePhone1( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storePhone2</code> attribute.
	 * @return the storePhone2
	 */
	public String getStorePhone2(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STOREPHONE2);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storePhone2</code> attribute.
	 * @return the storePhone2
	 */
	public String getStorePhone2()
	{
		return getStorePhone2( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storePhone2</code> attribute. 
	 * @param value the storePhone2
	 */
	public void setStorePhone2(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STOREPHONE2,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storePhone2</code> attribute. 
	 * @param value the storePhone2
	 */
	public void setStorePhone2(final String value)
	{
		setStorePhone2( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storePincode</code> attribute.
	 * @return the storePincode
	 */
	public String getStorePincode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STOREPINCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storePincode</code> attribute.
	 * @return the storePincode
	 */
	public String getStorePincode()
	{
		return getStorePincode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storePincode</code> attribute. 
	 * @param value the storePincode
	 */
	public void setStorePincode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STOREPINCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storePincode</code> attribute. 
	 * @param value the storePincode
	 */
	public void setStorePincode(final String value)
	{
		setStorePincode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeState</code> attribute.
	 * @return the storeState
	 */
	public String getStoreState(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STORESTATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeState</code> attribute.
	 * @return the storeState
	 */
	public String getStoreState()
	{
		return getStoreState( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeState</code> attribute. 
	 * @param value the storeState
	 */
	public void setStoreState(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STORESTATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeState</code> attribute. 
	 * @param value the storeState
	 */
	public void setStoreState(final String value)
	{
		setStoreState( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeTimeWeekday</code> attribute.
	 * @return the storeTimeWeekday
	 */
	public OpeningDay getStoreTimeWeekday(final SessionContext ctx)
	{
		return (OpeningDay)getProperty( ctx, STORETIMEWEEKDAY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeTimeWeekday</code> attribute.
	 * @return the storeTimeWeekday
	 */
	public OpeningDay getStoreTimeWeekday()
	{
		return getStoreTimeWeekday( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeTimeWeekday</code> attribute. 
	 * @param value the storeTimeWeekday
	 */
	public void setStoreTimeWeekday(final SessionContext ctx, final OpeningDay value)
	{
		setProperty(ctx, STORETIMEWEEKDAY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeTimeWeekday</code> attribute. 
	 * @param value the storeTimeWeekday
	 */
	public void setStoreTimeWeekday(final OpeningDay value)
	{
		setStoreTimeWeekday( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeTimeWeekend</code> attribute.
	 * @return the storeTimeWeekend
	 */
	public OpeningDay getStoreTimeWeekend(final SessionContext ctx)
	{
		return (OpeningDay)getProperty( ctx, STORETIMEWEEKEND);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.storeTimeWeekend</code> attribute.
	 * @return the storeTimeWeekend
	 */
	public OpeningDay getStoreTimeWeekend()
	{
		return getStoreTimeWeekend( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeTimeWeekend</code> attribute. 
	 * @param value the storeTimeWeekend
	 */
	public void setStoreTimeWeekend(final SessionContext ctx, final OpeningDay value)
	{
		setProperty(ctx, STORETIMEWEEKEND,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.storeTimeWeekend</code> attribute. 
	 * @param value the storeTimeWeekend
	 */
	public void setStoreTimeWeekend(final OpeningDay value)
	{
		setStoreTimeWeekend( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.stroreAddress2</code> attribute.
	 * @return the stroreAddress2
	 */
	public String getStroreAddress2(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STROREADDRESS2);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSStoreMaster.stroreAddress2</code> attribute.
	 * @return the stroreAddress2
	 */
	public String getStroreAddress2()
	{
		return getStroreAddress2( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.stroreAddress2</code> attribute. 
	 * @param value the stroreAddress2
	 */
	public void setStroreAddress2(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STROREADDRESS2,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSStoreMaster.stroreAddress2</code> attribute. 
	 * @param value the stroreAddress2
	 */
	public void setStroreAddress2(final String value)
	{
		setStroreAddress2( getSession().getSessionContext(), value );
	}
	
}
