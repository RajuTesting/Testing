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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.FirstCitizenMembershipAmazon FirstCitizenMembershipAmazon}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedFirstCitizenMembershipAmazon extends GenericItem
{
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.firstName</code> attribute **/
	public static final String FIRSTNAME = "firstName";
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.lastName</code> attribute **/
	public static final String LASTNAME = "lastName";
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.nameOnCard</code> attribute **/
	public static final String NAMEONCARD = "nameOnCard";
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.dateOfBirth</code> attribute **/
	public static final String DATEOFBIRTH = "dateOfBirth";
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.mobile</code> attribute **/
	public static final String MOBILE = "mobile";
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.emailId</code> attribute **/
	public static final String EMAILID = "emailId";
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.gender</code> attribute **/
	public static final String GENDER = "gender";
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.city</code> attribute **/
	public static final String CITY = "city";
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.stateCode</code> attribute **/
	public static final String STATECODE = "stateCode";
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.stateDescription</code> attribute **/
	public static final String STATEDESCRIPTION = "stateDescription";
	/** Qualifier of the <code>FirstCitizenMembershipAmazon.pincode</code> attribute **/
	public static final String PINCODE = "pincode";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(TITLE, AttributeMode.INITIAL);
		tmp.put(FIRSTNAME, AttributeMode.INITIAL);
		tmp.put(LASTNAME, AttributeMode.INITIAL);
		tmp.put(NAMEONCARD, AttributeMode.INITIAL);
		tmp.put(DATEOFBIRTH, AttributeMode.INITIAL);
		tmp.put(MOBILE, AttributeMode.INITIAL);
		tmp.put(EMAILID, AttributeMode.INITIAL);
		tmp.put(GENDER, AttributeMode.INITIAL);
		tmp.put(CITY, AttributeMode.INITIAL);
		tmp.put(STATECODE, AttributeMode.INITIAL);
		tmp.put(STATEDESCRIPTION, AttributeMode.INITIAL);
		tmp.put(PINCODE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.city</code> attribute.
	 * @return the city - City
	 */
	public String getCity(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.city</code> attribute.
	 * @return the city - City
	 */
	public String getCity()
	{
		return getCity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.city</code> attribute. 
	 * @param value the city - City
	 */
	public void setCity(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.city</code> attribute. 
	 * @param value the city - City
	 */
	public void setCity(final String value)
	{
		setCity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.dateOfBirth</code> attribute.
	 * @return the dateOfBirth - Date Of Birth
	 */
	public Date getDateOfBirth(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, DATEOFBIRTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.dateOfBirth</code> attribute.
	 * @return the dateOfBirth - Date Of Birth
	 */
	public Date getDateOfBirth()
	{
		return getDateOfBirth( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.dateOfBirth</code> attribute. 
	 * @param value the dateOfBirth - Date Of Birth
	 */
	public void setDateOfBirth(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, DATEOFBIRTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.dateOfBirth</code> attribute. 
	 * @param value the dateOfBirth - Date Of Birth
	 */
	public void setDateOfBirth(final Date value)
	{
		setDateOfBirth( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.emailId</code> attribute.
	 * @return the emailId - Email ID
	 */
	public String getEmailId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EMAILID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.emailId</code> attribute.
	 * @return the emailId - Email ID
	 */
	public String getEmailId()
	{
		return getEmailId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.emailId</code> attribute. 
	 * @param value the emailId - Email ID
	 */
	public void setEmailId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EMAILID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.emailId</code> attribute. 
	 * @param value the emailId - Email ID
	 */
	public void setEmailId(final String value)
	{
		setEmailId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.firstName</code> attribute.
	 * @return the firstName - First Name
	 */
	public String getFirstName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FIRSTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.firstName</code> attribute.
	 * @return the firstName - First Name
	 */
	public String getFirstName()
	{
		return getFirstName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.firstName</code> attribute. 
	 * @param value the firstName - First Name
	 */
	public void setFirstName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FIRSTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.firstName</code> attribute. 
	 * @param value the firstName - First Name
	 */
	public void setFirstName(final String value)
	{
		setFirstName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.gender</code> attribute.
	 * @return the gender - Gender
	 */
	public String getGender(final SessionContext ctx)
	{
		return (String)getProperty( ctx, GENDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.gender</code> attribute.
	 * @return the gender - Gender
	 */
	public String getGender()
	{
		return getGender( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.gender</code> attribute. 
	 * @param value the gender - Gender
	 */
	public void setGender(final SessionContext ctx, final String value)
	{
		setProperty(ctx, GENDER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.gender</code> attribute. 
	 * @param value the gender - Gender
	 */
	public void setGender(final String value)
	{
		setGender( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.lastName</code> attribute.
	 * @return the lastName - Last Name
	 */
	public String getLastName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LASTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.lastName</code> attribute.
	 * @return the lastName - Last Name
	 */
	public String getLastName()
	{
		return getLastName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.lastName</code> attribute. 
	 * @param value the lastName - Last Name
	 */
	public void setLastName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LASTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.lastName</code> attribute. 
	 * @param value the lastName - Last Name
	 */
	public void setLastName(final String value)
	{
		setLastName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.mobile</code> attribute.
	 * @return the mobile - Mobile Number
	 */
	public String getMobile(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MOBILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.mobile</code> attribute.
	 * @return the mobile - Mobile Number
	 */
	public String getMobile()
	{
		return getMobile( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.mobile</code> attribute. 
	 * @param value the mobile - Mobile Number
	 */
	public void setMobile(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MOBILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.mobile</code> attribute. 
	 * @param value the mobile - Mobile Number
	 */
	public void setMobile(final String value)
	{
		setMobile( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.nameOnCard</code> attribute.
	 * @return the nameOnCard - Name on Card
	 */
	public String getNameOnCard(final SessionContext ctx)
	{
		return (String)getProperty( ctx, NAMEONCARD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.nameOnCard</code> attribute.
	 * @return the nameOnCard - Name on Card
	 */
	public String getNameOnCard()
	{
		return getNameOnCard( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.nameOnCard</code> attribute. 
	 * @param value the nameOnCard - Name on Card
	 */
	public void setNameOnCard(final SessionContext ctx, final String value)
	{
		setProperty(ctx, NAMEONCARD,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.nameOnCard</code> attribute. 
	 * @param value the nameOnCard - Name on Card
	 */
	public void setNameOnCard(final String value)
	{
		setNameOnCard( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.pincode</code> attribute.
	 * @return the pincode - Pincode
	 */
	public String getPincode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PINCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.pincode</code> attribute.
	 * @return the pincode - Pincode
	 */
	public String getPincode()
	{
		return getPincode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.pincode</code> attribute. 
	 * @param value the pincode - Pincode
	 */
	public void setPincode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PINCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.pincode</code> attribute. 
	 * @param value the pincode - Pincode
	 */
	public void setPincode(final String value)
	{
		setPincode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.stateCode</code> attribute.
	 * @return the stateCode - State
	 */
	public String getStateCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STATECODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.stateCode</code> attribute.
	 * @return the stateCode - State
	 */
	public String getStateCode()
	{
		return getStateCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.stateCode</code> attribute. 
	 * @param value the stateCode - State
	 */
	public void setStateCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STATECODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.stateCode</code> attribute. 
	 * @param value the stateCode - State
	 */
	public void setStateCode(final String value)
	{
		setStateCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.stateDescription</code> attribute.
	 * @return the stateDescription - State
	 */
	public String getStateDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STATEDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.stateDescription</code> attribute.
	 * @return the stateDescription - State
	 */
	public String getStateDescription()
	{
		return getStateDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.stateDescription</code> attribute. 
	 * @param value the stateDescription - State
	 */
	public void setStateDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STATEDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.stateDescription</code> attribute. 
	 * @param value the stateDescription - State
	 */
	public void setStateDescription(final String value)
	{
		setStateDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.title</code> attribute.
	 * @return the title - Title
	 */
	public String getTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenMembershipAmazon.title</code> attribute.
	 * @return the title - Title
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.title</code> attribute. 
	 * @param value the title - Title
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenMembershipAmazon.title</code> attribute. 
	 * @param value the title - Title
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
}
