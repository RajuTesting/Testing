/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.PSBookingType;
import com.borngroup.ssl.core.jalo.PSServices;
import com.borngroup.ssl.core.jalo.PSStoreMaster;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem PSPersonalShopper}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPSPersonalShopper extends GenericItem
{
	/** Qualifier of the <code>PSPersonalShopper.psUniqueId</code> attribute **/
	public static final String PSUNIQUEID = "psUniqueId";
	/** Qualifier of the <code>PSPersonalShopper.psEmployeeId</code> attribute **/
	public static final String PSEMPLOYEEID = "psEmployeeId";
	/** Qualifier of the <code>PSPersonalShopper.firstName</code> attribute **/
	public static final String FIRSTNAME = "firstName";
	/** Qualifier of the <code>PSPersonalShopper.lastName</code> attribute **/
	public static final String LASTNAME = "lastName";
	/** Qualifier of the <code>PSPersonalShopper.gender</code> attribute **/
	public static final String GENDER = "gender";
	/** Qualifier of the <code>PSPersonalShopper.isPSActive</code> attribute **/
	public static final String ISPSACTIVE = "isPSActive";
	/** Qualifier of the <code>PSPersonalShopper.limited</code> attribute **/
	public static final String LIMITED = "limited";
	/** Qualifier of the <code>PSPersonalShopper.psPriority</code> attribute **/
	public static final String PSPRIORITY = "psPriority";
	/** Qualifier of the <code>PSPersonalShopper.psPrimaryEmail</code> attribute **/
	public static final String PSPRIMARYEMAIL = "psPrimaryEmail";
	/** Qualifier of the <code>PSPersonalShopper.psMobileNumber</code> attribute **/
	public static final String PSMOBILENUMBER = "psMobileNumber";
	/** Qualifier of the <code>PSPersonalShopper.psBookingTypes</code> attribute **/
	public static final String PSBOOKINGTYPES = "psBookingTypes";
	/** Qualifier of the <code>PSPersonalShopper.psBookingServices</code> attribute **/
	public static final String PSBOOKINGSERVICES = "psBookingServices";
	/** Qualifier of the <code>PSPersonalShopper.imageUrl</code> attribute **/
	public static final String IMAGEURL = "imageUrl";
	/** Qualifier of the <code>PSPersonalShopper.default</code> attribute **/
	public static final String DEFAULT = "default";
	/** Qualifier of the <code>PSPersonalShopper.psStoreMasters</code> attribute **/
	public static final String PSSTOREMASTERS = "psStoreMasters";
	/** Relation ordering override parameter constants for PSPersonalShopperStoreRelation from ((sslcore))*/
	protected static String PSPERSONALSHOPPERSTORERELATION_SRC_ORDERED = "relation.PSPersonalShopperStoreRelation.source.ordered";
	protected static String PSPERSONALSHOPPERSTORERELATION_TGT_ORDERED = "relation.PSPersonalShopperStoreRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for PSPersonalShopperStoreRelation from ((sslcore))*/
	protected static String PSPERSONALSHOPPERSTORERELATION_MARKMODIFIED = "relation.PSPersonalShopperStoreRelation.markmodified";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PSUNIQUEID, AttributeMode.INITIAL);
		tmp.put(PSEMPLOYEEID, AttributeMode.INITIAL);
		tmp.put(FIRSTNAME, AttributeMode.INITIAL);
		tmp.put(LASTNAME, AttributeMode.INITIAL);
		tmp.put(GENDER, AttributeMode.INITIAL);
		tmp.put(ISPSACTIVE, AttributeMode.INITIAL);
		tmp.put(LIMITED, AttributeMode.INITIAL);
		tmp.put(PSPRIORITY, AttributeMode.INITIAL);
		tmp.put(PSPRIMARYEMAIL, AttributeMode.INITIAL);
		tmp.put(PSMOBILENUMBER, AttributeMode.INITIAL);
		tmp.put(PSBOOKINGTYPES, AttributeMode.INITIAL);
		tmp.put(PSBOOKINGSERVICES, AttributeMode.INITIAL);
		tmp.put(IMAGEURL, AttributeMode.INITIAL);
		tmp.put(DEFAULT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.default</code> attribute.
	 * @return the default - For one store code, only one personal shopper can be set as Y
	 */
	public Boolean isDefault(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, DEFAULT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.default</code> attribute.
	 * @return the default - For one store code, only one personal shopper can be set as Y
	 */
	public Boolean isDefault()
	{
		return isDefault( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.default</code> attribute. 
	 * @return the default - For one store code, only one personal shopper can be set as Y
	 */
	public boolean isDefaultAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isDefault( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.default</code> attribute. 
	 * @return the default - For one store code, only one personal shopper can be set as Y
	 */
	public boolean isDefaultAsPrimitive()
	{
		return isDefaultAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.default</code> attribute. 
	 * @param value the default - For one store code, only one personal shopper can be set as Y
	 */
	public void setDefault(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, DEFAULT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.default</code> attribute. 
	 * @param value the default - For one store code, only one personal shopper can be set as Y
	 */
	public void setDefault(final Boolean value)
	{
		setDefault( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.default</code> attribute. 
	 * @param value the default - For one store code, only one personal shopper can be set as Y
	 */
	public void setDefault(final SessionContext ctx, final boolean value)
	{
		setDefault( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.default</code> attribute. 
	 * @param value the default - For one store code, only one personal shopper can be set as Y
	 */
	public void setDefault(final boolean value)
	{
		setDefault( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.firstName</code> attribute.
	 * @return the firstName
	 */
	public String getFirstName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FIRSTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.firstName</code> attribute.
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return getFirstName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.firstName</code> attribute. 
	 * @param value the firstName
	 */
	public void setFirstName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FIRSTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.firstName</code> attribute. 
	 * @param value the firstName
	 */
	public void setFirstName(final String value)
	{
		setFirstName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.gender</code> attribute.
	 * @return the gender
	 */
	public EnumerationValue getGender(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, GENDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.gender</code> attribute.
	 * @return the gender
	 */
	public EnumerationValue getGender()
	{
		return getGender( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.gender</code> attribute. 
	 * @param value the gender
	 */
	public void setGender(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, GENDER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.gender</code> attribute. 
	 * @param value the gender
	 */
	public void setGender(final EnumerationValue value)
	{
		setGender( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.imageUrl</code> attribute.
	 * @return the imageUrl - This will be used for storing profile picture of PS
	 */
	public String getImageUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, IMAGEURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.imageUrl</code> attribute.
	 * @return the imageUrl - This will be used for storing profile picture of PS
	 */
	public String getImageUrl()
	{
		return getImageUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.imageUrl</code> attribute. 
	 * @param value the imageUrl - This will be used for storing profile picture of PS
	 */
	public void setImageUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, IMAGEURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.imageUrl</code> attribute. 
	 * @param value the imageUrl - This will be used for storing profile picture of PS
	 */
	public void setImageUrl(final String value)
	{
		setImageUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.isPSActive</code> attribute.
	 * @return the isPSActive
	 */
	public Boolean isIsPSActive(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ISPSACTIVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.isPSActive</code> attribute.
	 * @return the isPSActive
	 */
	public Boolean isIsPSActive()
	{
		return isIsPSActive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.isPSActive</code> attribute. 
	 * @return the isPSActive
	 */
	public boolean isIsPSActiveAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIsPSActive( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.isPSActive</code> attribute. 
	 * @return the isPSActive
	 */
	public boolean isIsPSActiveAsPrimitive()
	{
		return isIsPSActiveAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.isPSActive</code> attribute. 
	 * @param value the isPSActive
	 */
	public void setIsPSActive(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ISPSACTIVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.isPSActive</code> attribute. 
	 * @param value the isPSActive
	 */
	public void setIsPSActive(final Boolean value)
	{
		setIsPSActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.isPSActive</code> attribute. 
	 * @param value the isPSActive
	 */
	public void setIsPSActive(final SessionContext ctx, final boolean value)
	{
		setIsPSActive( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.isPSActive</code> attribute. 
	 * @param value the isPSActive
	 */
	public void setIsPSActive(final boolean value)
	{
		setIsPSActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.lastName</code> attribute.
	 * @return the lastName
	 */
	public String getLastName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LASTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.lastName</code> attribute.
	 * @return the lastName
	 */
	public String getLastName()
	{
		return getLastName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.lastName</code> attribute. 
	 * @param value the lastName
	 */
	public void setLastName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LASTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.lastName</code> attribute. 
	 * @param value the lastName
	 */
	public void setLastName(final String value)
	{
		setLastName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.limited</code> attribute.
	 * @return the limited
	 */
	public Boolean isLimited(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, LIMITED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.limited</code> attribute.
	 * @return the limited
	 */
	public Boolean isLimited()
	{
		return isLimited( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.limited</code> attribute. 
	 * @return the limited
	 */
	public boolean isLimitedAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isLimited( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.limited</code> attribute. 
	 * @return the limited
	 */
	public boolean isLimitedAsPrimitive()
	{
		return isLimitedAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.limited</code> attribute. 
	 * @param value the limited
	 */
	public void setLimited(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, LIMITED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.limited</code> attribute. 
	 * @param value the limited
	 */
	public void setLimited(final Boolean value)
	{
		setLimited( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.limited</code> attribute. 
	 * @param value the limited
	 */
	public void setLimited(final SessionContext ctx, final boolean value)
	{
		setLimited( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.limited</code> attribute. 
	 * @param value the limited
	 */
	public void setLimited(final boolean value)
	{
		setLimited( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psBookingServices</code> attribute.
	 * @return the psBookingServices
	 */
	public List<PSServices> getPsBookingServices(final SessionContext ctx)
	{
		List<PSServices> coll = (List<PSServices>)getProperty( ctx, PSBOOKINGSERVICES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psBookingServices</code> attribute.
	 * @return the psBookingServices
	 */
	public List<PSServices> getPsBookingServices()
	{
		return getPsBookingServices( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psBookingServices</code> attribute. 
	 * @param value the psBookingServices
	 */
	public void setPsBookingServices(final SessionContext ctx, final List<PSServices> value)
	{
		setProperty(ctx, PSBOOKINGSERVICES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psBookingServices</code> attribute. 
	 * @param value the psBookingServices
	 */
	public void setPsBookingServices(final List<PSServices> value)
	{
		setPsBookingServices( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psBookingTypes</code> attribute.
	 * @return the psBookingTypes
	 */
	public List<PSBookingType> getPsBookingTypes(final SessionContext ctx)
	{
		List<PSBookingType> coll = (List<PSBookingType>)getProperty( ctx, PSBOOKINGTYPES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psBookingTypes</code> attribute.
	 * @return the psBookingTypes
	 */
	public List<PSBookingType> getPsBookingTypes()
	{
		return getPsBookingTypes( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psBookingTypes</code> attribute. 
	 * @param value the psBookingTypes
	 */
	public void setPsBookingTypes(final SessionContext ctx, final List<PSBookingType> value)
	{
		setProperty(ctx, PSBOOKINGTYPES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psBookingTypes</code> attribute. 
	 * @param value the psBookingTypes
	 */
	public void setPsBookingTypes(final List<PSBookingType> value)
	{
		setPsBookingTypes( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psEmployeeId</code> attribute.
	 * @return the psEmployeeId
	 */
	public String getPsEmployeeId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PSEMPLOYEEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psEmployeeId</code> attribute.
	 * @return the psEmployeeId
	 */
	public String getPsEmployeeId()
	{
		return getPsEmployeeId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psEmployeeId</code> attribute. 
	 * @param value the psEmployeeId
	 */
	public void setPsEmployeeId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PSEMPLOYEEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psEmployeeId</code> attribute. 
	 * @param value the psEmployeeId
	 */
	public void setPsEmployeeId(final String value)
	{
		setPsEmployeeId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psMobileNumber</code> attribute.
	 * @return the psMobileNumber
	 */
	public String getPsMobileNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PSMOBILENUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psMobileNumber</code> attribute.
	 * @return the psMobileNumber
	 */
	public String getPsMobileNumber()
	{
		return getPsMobileNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psMobileNumber</code> attribute. 
	 * @param value the psMobileNumber
	 */
	public void setPsMobileNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PSMOBILENUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psMobileNumber</code> attribute. 
	 * @param value the psMobileNumber
	 */
	public void setPsMobileNumber(final String value)
	{
		setPsMobileNumber( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psPrimaryEmail</code> attribute.
	 * @return the psPrimaryEmail
	 */
	public String getPsPrimaryEmail(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PSPRIMARYEMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psPrimaryEmail</code> attribute.
	 * @return the psPrimaryEmail
	 */
	public String getPsPrimaryEmail()
	{
		return getPsPrimaryEmail( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psPrimaryEmail</code> attribute. 
	 * @param value the psPrimaryEmail
	 */
	public void setPsPrimaryEmail(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PSPRIMARYEMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psPrimaryEmail</code> attribute. 
	 * @param value the psPrimaryEmail
	 */
	public void setPsPrimaryEmail(final String value)
	{
		setPsPrimaryEmail( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psPriority</code> attribute.
	 * @return the psPriority
	 */
	public Integer getPsPriority(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, PSPRIORITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psPriority</code> attribute.
	 * @return the psPriority
	 */
	public Integer getPsPriority()
	{
		return getPsPriority( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psPriority</code> attribute. 
	 * @return the psPriority
	 */
	public int getPsPriorityAsPrimitive(final SessionContext ctx)
	{
		Integer value = getPsPriority( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psPriority</code> attribute. 
	 * @return the psPriority
	 */
	public int getPsPriorityAsPrimitive()
	{
		return getPsPriorityAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psPriority</code> attribute. 
	 * @param value the psPriority
	 */
	public void setPsPriority(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, PSPRIORITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psPriority</code> attribute. 
	 * @param value the psPriority
	 */
	public void setPsPriority(final Integer value)
	{
		setPsPriority( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psPriority</code> attribute. 
	 * @param value the psPriority
	 */
	public void setPsPriority(final SessionContext ctx, final int value)
	{
		setPsPriority( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psPriority</code> attribute. 
	 * @param value the psPriority
	 */
	public void setPsPriority(final int value)
	{
		setPsPriority( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psStoreMasters</code> attribute.
	 * @return the psStoreMasters
	 */
	public List<PSStoreMaster> getPsStoreMasters(final SessionContext ctx)
	{
		final List<PSStoreMaster> items = getLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.PSPERSONALSHOPPERSTORERELATION,
			null,
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_TGT_ORDERED, true)
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psStoreMasters</code> attribute.
	 * @return the psStoreMasters
	 */
	public List<PSStoreMaster> getPsStoreMasters()
	{
		return getPsStoreMasters( getSession().getSessionContext() );
	}
	
	public long getPsStoreMastersCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			true,
			SslCoreConstants.Relations.PSPERSONALSHOPPERSTORERELATION,
			null
		);
	}
	
	public long getPsStoreMastersCount()
	{
		return getPsStoreMastersCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psStoreMasters</code> attribute. 
	 * @param value the psStoreMasters
	 */
	public void setPsStoreMasters(final SessionContext ctx, final List<PSStoreMaster> value)
	{
		setLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.PSPERSONALSHOPPERSTORERELATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(PSPERSONALSHOPPERSTORERELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psStoreMasters</code> attribute. 
	 * @param value the psStoreMasters
	 */
	public void setPsStoreMasters(final List<PSStoreMaster> value)
	{
		setPsStoreMasters( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to psStoreMasters. 
	 * @param value the item to add to psStoreMasters
	 */
	public void addToPsStoreMasters(final SessionContext ctx, final PSStoreMaster value)
	{
		addLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.PSPERSONALSHOPPERSTORERELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(PSPERSONALSHOPPERSTORERELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to psStoreMasters. 
	 * @param value the item to add to psStoreMasters
	 */
	public void addToPsStoreMasters(final PSStoreMaster value)
	{
		addToPsStoreMasters( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from psStoreMasters. 
	 * @param value the item to remove from psStoreMasters
	 */
	public void removeFromPsStoreMasters(final SessionContext ctx, final PSStoreMaster value)
	{
		removeLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.PSPERSONALSHOPPERSTORERELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(PSPERSONALSHOPPERSTORERELATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(PSPERSONALSHOPPERSTORERELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from psStoreMasters. 
	 * @param value the item to remove from psStoreMasters
	 */
	public void removeFromPsStoreMasters(final PSStoreMaster value)
	{
		removeFromPsStoreMasters( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psUniqueId</code> attribute.
	 * @return the psUniqueId
	 */
	public String getPsUniqueId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PSUNIQUEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSPersonalShopper.psUniqueId</code> attribute.
	 * @return the psUniqueId
	 */
	public String getPsUniqueId()
	{
		return getPsUniqueId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psUniqueId</code> attribute. 
	 * @param value the psUniqueId
	 */
	public void setPsUniqueId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PSUNIQUEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSPersonalShopper.psUniqueId</code> attribute. 
	 * @param value the psUniqueId
	 */
	public void setPsUniqueId(final String value)
	{
		setPsUniqueId( getSession().getSessionContext(), value );
	}
	
}
