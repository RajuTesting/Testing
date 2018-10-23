/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 *  
 * [y] hybris Platform
 *  
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *  
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *  
 */
package de.hybris.platform.commerceservices.jalo.process;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.commerceservices.jalo.process.StoreFrontProcess;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.commerceservices.jalo.process.ContactUsStoreFrontProcess ContactUsStoreFrontProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedContactUsStoreFrontProcess extends StoreFrontProcess
{
	/** Qualifier of the <code>ContactUsStoreFrontProcess.firstName</code> attribute **/
	public static final String FIRSTNAME = "firstName";
	/** Qualifier of the <code>ContactUsStoreFrontProcess.lastName</code> attribute **/
	public static final String LASTNAME = "lastName";
	/** Qualifier of the <code>ContactUsStoreFrontProcess.orderNumber</code> attribute **/
	public static final String ORDERNUMBER = "orderNumber";
	/** Qualifier of the <code>ContactUsStoreFrontProcess.issueCategory</code> attribute **/
	public static final String ISSUECATEGORY = "issueCategory";
	/** Qualifier of the <code>ContactUsStoreFrontProcess.mobile</code> attribute **/
	public static final String MOBILE = "mobile";
	/** Qualifier of the <code>ContactUsStoreFrontProcess.mailID</code> attribute **/
	public static final String MAILID = "mailID";
	/** Qualifier of the <code>ContactUsStoreFrontProcess.comment</code> attribute **/
	public static final String COMMENT = "comment";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(StoreFrontProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(FIRSTNAME, AttributeMode.INITIAL);
		tmp.put(LASTNAME, AttributeMode.INITIAL);
		tmp.put(ORDERNUMBER, AttributeMode.INITIAL);
		tmp.put(ISSUECATEGORY, AttributeMode.INITIAL);
		tmp.put(MOBILE, AttributeMode.INITIAL);
		tmp.put(MAILID, AttributeMode.INITIAL);
		tmp.put(COMMENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.comment</code> attribute.
	 * @return the comment - comment
	 */
	public String getComment(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COMMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.comment</code> attribute.
	 * @return the comment - comment
	 */
	public String getComment()
	{
		return getComment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.comment</code> attribute. 
	 * @param value the comment - comment
	 */
	public void setComment(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COMMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.comment</code> attribute. 
	 * @param value the comment - comment
	 */
	public void setComment(final String value)
	{
		setComment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.firstName</code> attribute.
	 * @return the firstName - firstName
	 */
	public String getFirstName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FIRSTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.firstName</code> attribute.
	 * @return the firstName - firstName
	 */
	public String getFirstName()
	{
		return getFirstName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.firstName</code> attribute. 
	 * @param value the firstName - firstName
	 */
	public void setFirstName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FIRSTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.firstName</code> attribute. 
	 * @param value the firstName - firstName
	 */
	public void setFirstName(final String value)
	{
		setFirstName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.issueCategory</code> attribute.
	 * @return the issueCategory - issueCategory
	 */
	public String getIssueCategory(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ISSUECATEGORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.issueCategory</code> attribute.
	 * @return the issueCategory - issueCategory
	 */
	public String getIssueCategory()
	{
		return getIssueCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.issueCategory</code> attribute. 
	 * @param value the issueCategory - issueCategory
	 */
	public void setIssueCategory(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ISSUECATEGORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.issueCategory</code> attribute. 
	 * @param value the issueCategory - issueCategory
	 */
	public void setIssueCategory(final String value)
	{
		setIssueCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.lastName</code> attribute.
	 * @return the lastName - lastName
	 */
	public String getLastName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LASTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.lastName</code> attribute.
	 * @return the lastName - lastName
	 */
	public String getLastName()
	{
		return getLastName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.lastName</code> attribute. 
	 * @param value the lastName - lastName
	 */
	public void setLastName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LASTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.lastName</code> attribute. 
	 * @param value the lastName - lastName
	 */
	public void setLastName(final String value)
	{
		setLastName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.mailID</code> attribute.
	 * @return the mailID - mailID
	 */
	public String getMailID(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MAILID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.mailID</code> attribute.
	 * @return the mailID - mailID
	 */
	public String getMailID()
	{
		return getMailID( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.mailID</code> attribute. 
	 * @param value the mailID - mailID
	 */
	public void setMailID(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MAILID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.mailID</code> attribute. 
	 * @param value the mailID - mailID
	 */
	public void setMailID(final String value)
	{
		setMailID( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.mobile</code> attribute.
	 * @return the mobile - mobile
	 */
	public String getMobile(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MOBILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.mobile</code> attribute.
	 * @return the mobile - mobile
	 */
	public String getMobile()
	{
		return getMobile( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.mobile</code> attribute. 
	 * @param value the mobile - mobile
	 */
	public void setMobile(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MOBILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.mobile</code> attribute. 
	 * @param value the mobile - mobile
	 */
	public void setMobile(final String value)
	{
		setMobile( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.orderNumber</code> attribute.
	 * @return the orderNumber - orderNumber
	 */
	public String getOrderNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ORDERNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ContactUsStoreFrontProcess.orderNumber</code> attribute.
	 * @return the orderNumber - orderNumber
	 */
	public String getOrderNumber()
	{
		return getOrderNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.orderNumber</code> attribute. 
	 * @param value the orderNumber - orderNumber
	 */
	public void setOrderNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ORDERNUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ContactUsStoreFrontProcess.orderNumber</code> attribute. 
	 * @param value the orderNumber - orderNumber
	 */
	public void setOrderNumber(final String value)
	{
		setOrderNumber( getSession().getSessionContext(), value );
	}
	
}
