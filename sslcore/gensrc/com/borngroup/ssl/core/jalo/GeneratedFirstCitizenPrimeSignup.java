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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.FirstCitizenPrimeSignup FirstCitizenPrimeSignup}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedFirstCitizenPrimeSignup extends GenericItem
{
	/** Qualifier of the <code>FirstCitizenPrimeSignup.firstName</code> attribute **/
	public static final String FIRSTNAME = "firstName";
	/** Qualifier of the <code>FirstCitizenPrimeSignup.lastName</code> attribute **/
	public static final String LASTNAME = "lastName";
	/** Qualifier of the <code>FirstCitizenPrimeSignup.mobile</code> attribute **/
	public static final String MOBILE = "mobile";
	/** Qualifier of the <code>FirstCitizenPrimeSignup.mailID</code> attribute **/
	public static final String MAILID = "mailID";
	/** Qualifier of the <code>FirstCitizenPrimeSignup.firstCitizenNumber</code> attribute **/
	public static final String FIRSTCITIZENNUMBER = "firstCitizenNumber";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(FIRSTNAME, AttributeMode.INITIAL);
		tmp.put(LASTNAME, AttributeMode.INITIAL);
		tmp.put(MOBILE, AttributeMode.INITIAL);
		tmp.put(MAILID, AttributeMode.INITIAL);
		tmp.put(FIRSTCITIZENNUMBER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenPrimeSignup.firstCitizenNumber</code> attribute.
	 * @return the firstCitizenNumber - firstCitizenNumber
	 */
	public String getFirstCitizenNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FIRSTCITIZENNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenPrimeSignup.firstCitizenNumber</code> attribute.
	 * @return the firstCitizenNumber - firstCitizenNumber
	 */
	public String getFirstCitizenNumber()
	{
		return getFirstCitizenNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenPrimeSignup.firstCitizenNumber</code> attribute. 
	 * @param value the firstCitizenNumber - firstCitizenNumber
	 */
	public void setFirstCitizenNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FIRSTCITIZENNUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenPrimeSignup.firstCitizenNumber</code> attribute. 
	 * @param value the firstCitizenNumber - firstCitizenNumber
	 */
	public void setFirstCitizenNumber(final String value)
	{
		setFirstCitizenNumber( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenPrimeSignup.firstName</code> attribute.
	 * @return the firstName - firstName
	 */
	public String getFirstName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FIRSTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenPrimeSignup.firstName</code> attribute.
	 * @return the firstName - firstName
	 */
	public String getFirstName()
	{
		return getFirstName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenPrimeSignup.firstName</code> attribute. 
	 * @param value the firstName - firstName
	 */
	public void setFirstName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FIRSTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenPrimeSignup.firstName</code> attribute. 
	 * @param value the firstName - firstName
	 */
	public void setFirstName(final String value)
	{
		setFirstName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenPrimeSignup.lastName</code> attribute.
	 * @return the lastName - lastName
	 */
	public String getLastName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LASTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenPrimeSignup.lastName</code> attribute.
	 * @return the lastName - lastName
	 */
	public String getLastName()
	{
		return getLastName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenPrimeSignup.lastName</code> attribute. 
	 * @param value the lastName - lastName
	 */
	public void setLastName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LASTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenPrimeSignup.lastName</code> attribute. 
	 * @param value the lastName - lastName
	 */
	public void setLastName(final String value)
	{
		setLastName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenPrimeSignup.mailID</code> attribute.
	 * @return the mailID - mailID
	 */
	public String getMailID(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MAILID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenPrimeSignup.mailID</code> attribute.
	 * @return the mailID - mailID
	 */
	public String getMailID()
	{
		return getMailID( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenPrimeSignup.mailID</code> attribute. 
	 * @param value the mailID - mailID
	 */
	public void setMailID(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MAILID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenPrimeSignup.mailID</code> attribute. 
	 * @param value the mailID - mailID
	 */
	public void setMailID(final String value)
	{
		setMailID( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenPrimeSignup.mobile</code> attribute.
	 * @return the mobile - mobile
	 */
	public String getMobile(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MOBILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenPrimeSignup.mobile</code> attribute.
	 * @return the mobile - mobile
	 */
	public String getMobile()
	{
		return getMobile( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenPrimeSignup.mobile</code> attribute. 
	 * @param value the mobile - mobile
	 */
	public void setMobile(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MOBILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenPrimeSignup.mobile</code> attribute. 
	 * @param value the mobile - mobile
	 */
	public void setMobile(final String value)
	{
		setMobile( getSession().getSessionContext(), value );
	}
	
}
