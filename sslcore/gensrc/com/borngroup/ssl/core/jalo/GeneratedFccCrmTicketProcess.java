/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.commerceservices.jalo.process.StoreFrontCustomerProcess;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.commerceservices.jalo.process.StoreFrontCustomerProcess FccCrmTicketProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedFccCrmTicketProcess extends StoreFrontCustomerProcess
{
	/** Qualifier of the <code>FccCrmTicketProcess.loyaltyAddress</code> attribute **/
	public static final String LOYALTYADDRESS = "loyaltyAddress";
	/** Qualifier of the <code>FccCrmTicketProcess.loyaltyDob</code> attribute **/
	public static final String LOYALTYDOB = "loyaltyDob";
	/** Qualifier of the <code>FccCrmTicketProcess.firstName</code> attribute **/
	public static final String FIRSTNAME = "firstName";
	/** Qualifier of the <code>FccCrmTicketProcess.lastName</code> attribute **/
	public static final String LASTNAME = "lastName";
	/** Qualifier of the <code>FccCrmTicketProcess.email</code> attribute **/
	public static final String EMAIL = "email";
	/** Qualifier of the <code>FccCrmTicketProcess.mobileNo</code> attribute **/
	public static final String MOBILENO = "mobileNo";
	/** Qualifier of the <code>FccCrmTicketProcess.filePath</code> attribute **/
	public static final String FILEPATH = "filePath";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(StoreFrontCustomerProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LOYALTYADDRESS, AttributeMode.INITIAL);
		tmp.put(LOYALTYDOB, AttributeMode.INITIAL);
		tmp.put(FIRSTNAME, AttributeMode.INITIAL);
		tmp.put(LASTNAME, AttributeMode.INITIAL);
		tmp.put(EMAIL, AttributeMode.INITIAL);
		tmp.put(MOBILENO, AttributeMode.INITIAL);
		tmp.put(FILEPATH, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.email</code> attribute.
	 * @return the email - Email Id
	 */
	public String getEmail(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.email</code> attribute.
	 * @return the email - Email Id
	 */
	public String getEmail()
	{
		return getEmail( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.email</code> attribute. 
	 * @param value the email - Email Id
	 */
	public void setEmail(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.email</code> attribute. 
	 * @param value the email - Email Id
	 */
	public void setEmail(final String value)
	{
		setEmail( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.filePath</code> attribute.
	 * @return the filePath - File Path
	 */
	public String getFilePath(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FILEPATH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.filePath</code> attribute.
	 * @return the filePath - File Path
	 */
	public String getFilePath()
	{
		return getFilePath( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.filePath</code> attribute. 
	 * @param value the filePath - File Path
	 */
	public void setFilePath(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FILEPATH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.filePath</code> attribute. 
	 * @param value the filePath - File Path
	 */
	public void setFilePath(final String value)
	{
		setFilePath( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.firstName</code> attribute.
	 * @return the firstName - First Name
	 */
	public String getFirstName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FIRSTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.firstName</code> attribute.
	 * @return the firstName - First Name
	 */
	public String getFirstName()
	{
		return getFirstName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.firstName</code> attribute. 
	 * @param value the firstName - First Name
	 */
	public void setFirstName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FIRSTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.firstName</code> attribute. 
	 * @param value the firstName - First Name
	 */
	public void setFirstName(final String value)
	{
		setFirstName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.lastName</code> attribute.
	 * @return the lastName - last Name
	 */
	public String getLastName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LASTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.lastName</code> attribute.
	 * @return the lastName - last Name
	 */
	public String getLastName()
	{
		return getLastName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.lastName</code> attribute. 
	 * @param value the lastName - last Name
	 */
	public void setLastName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LASTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.lastName</code> attribute. 
	 * @param value the lastName - last Name
	 */
	public void setLastName(final String value)
	{
		setLastName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.loyaltyAddress</code> attribute.
	 * @return the loyaltyAddress - Loyalty Address
	 */
	public String getLoyaltyAddress(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LOYALTYADDRESS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.loyaltyAddress</code> attribute.
	 * @return the loyaltyAddress - Loyalty Address
	 */
	public String getLoyaltyAddress()
	{
		return getLoyaltyAddress( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.loyaltyAddress</code> attribute. 
	 * @param value the loyaltyAddress - Loyalty Address
	 */
	public void setLoyaltyAddress(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LOYALTYADDRESS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.loyaltyAddress</code> attribute. 
	 * @param value the loyaltyAddress - Loyalty Address
	 */
	public void setLoyaltyAddress(final String value)
	{
		setLoyaltyAddress( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.loyaltyDob</code> attribute.
	 * @return the loyaltyDob - Loyalty DOB
	 */
	public String getLoyaltyDob(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LOYALTYDOB);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.loyaltyDob</code> attribute.
	 * @return the loyaltyDob - Loyalty DOB
	 */
	public String getLoyaltyDob()
	{
		return getLoyaltyDob( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.loyaltyDob</code> attribute. 
	 * @param value the loyaltyDob - Loyalty DOB
	 */
	public void setLoyaltyDob(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LOYALTYDOB,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.loyaltyDob</code> attribute. 
	 * @param value the loyaltyDob - Loyalty DOB
	 */
	public void setLoyaltyDob(final String value)
	{
		setLoyaltyDob( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.mobileNo</code> attribute.
	 * @return the mobileNo - Mobile Number
	 */
	public String getMobileNo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MOBILENO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FccCrmTicketProcess.mobileNo</code> attribute.
	 * @return the mobileNo - Mobile Number
	 */
	public String getMobileNo()
	{
		return getMobileNo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.mobileNo</code> attribute. 
	 * @param value the mobileNo - Mobile Number
	 */
	public void setMobileNo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MOBILENO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FccCrmTicketProcess.mobileNo</code> attribute. 
	 * @param value the mobileNo - Mobile Number
	 */
	public void setMobileNo(final String value)
	{
		setMobileNo( getSession().getSessionContext(), value );
	}
	
}
