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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem ReturnRequestBankDetails}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedReturnRequestBankDetails extends GenericItem
{
	/** Qualifier of the <code>ReturnRequestBankDetails.accountName</code> attribute **/
	public static final String ACCOUNTNAME = "accountName";
	/** Qualifier of the <code>ReturnRequestBankDetails.accountNumber</code> attribute **/
	public static final String ACCOUNTNUMBER = "accountNumber";
	/** Qualifier of the <code>ReturnRequestBankDetails.ifscCode</code> attribute **/
	public static final String IFSCCODE = "ifscCode";
	/** Qualifier of the <code>ReturnRequestBankDetails.bankName</code> attribute **/
	public static final String BANKNAME = "bankName";
	/** Qualifier of the <code>ReturnRequestBankDetails.updateLinkUID</code> attribute **/
	public static final String UPDATELINKUID = "updateLinkUID";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(ACCOUNTNAME, AttributeMode.INITIAL);
		tmp.put(ACCOUNTNUMBER, AttributeMode.INITIAL);
		tmp.put(IFSCCODE, AttributeMode.INITIAL);
		tmp.put(BANKNAME, AttributeMode.INITIAL);
		tmp.put(UPDATELINKUID, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnRequestBankDetails.accountName</code> attribute.
	 * @return the accountName - Product to be exchanged
	 */
	public String getAccountName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ACCOUNTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnRequestBankDetails.accountName</code> attribute.
	 * @return the accountName - Product to be exchanged
	 */
	public String getAccountName()
	{
		return getAccountName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnRequestBankDetails.accountName</code> attribute. 
	 * @param value the accountName - Product to be exchanged
	 */
	public void setAccountName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ACCOUNTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnRequestBankDetails.accountName</code> attribute. 
	 * @param value the accountName - Product to be exchanged
	 */
	public void setAccountName(final String value)
	{
		setAccountName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnRequestBankDetails.accountNumber</code> attribute.
	 * @return the accountNumber - Product to be exchanged
	 */
	public String getAccountNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ACCOUNTNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnRequestBankDetails.accountNumber</code> attribute.
	 * @return the accountNumber - Product to be exchanged
	 */
	public String getAccountNumber()
	{
		return getAccountNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnRequestBankDetails.accountNumber</code> attribute. 
	 * @param value the accountNumber - Product to be exchanged
	 */
	public void setAccountNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ACCOUNTNUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnRequestBankDetails.accountNumber</code> attribute. 
	 * @param value the accountNumber - Product to be exchanged
	 */
	public void setAccountNumber(final String value)
	{
		setAccountNumber( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnRequestBankDetails.bankName</code> attribute.
	 * @return the bankName - Product to be exchanged
	 */
	public String getBankName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BANKNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnRequestBankDetails.bankName</code> attribute.
	 * @return the bankName - Product to be exchanged
	 */
	public String getBankName()
	{
		return getBankName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnRequestBankDetails.bankName</code> attribute. 
	 * @param value the bankName - Product to be exchanged
	 */
	public void setBankName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BANKNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnRequestBankDetails.bankName</code> attribute. 
	 * @param value the bankName - Product to be exchanged
	 */
	public void setBankName(final String value)
	{
		setBankName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnRequestBankDetails.ifscCode</code> attribute.
	 * @return the ifscCode - Product to be exchanged
	 */
	public String getIfscCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, IFSCCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnRequestBankDetails.ifscCode</code> attribute.
	 * @return the ifscCode - Product to be exchanged
	 */
	public String getIfscCode()
	{
		return getIfscCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnRequestBankDetails.ifscCode</code> attribute. 
	 * @param value the ifscCode - Product to be exchanged
	 */
	public void setIfscCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, IFSCCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnRequestBankDetails.ifscCode</code> attribute. 
	 * @param value the ifscCode - Product to be exchanged
	 */
	public void setIfscCode(final String value)
	{
		setIfscCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnRequestBankDetails.updateLinkUID</code> attribute.
	 * @return the updateLinkUID - Bank Details Update Link UID
	 */
	public String getUpdateLinkUID(final SessionContext ctx)
	{
		return (String)getProperty( ctx, UPDATELINKUID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnRequestBankDetails.updateLinkUID</code> attribute.
	 * @return the updateLinkUID - Bank Details Update Link UID
	 */
	public String getUpdateLinkUID()
	{
		return getUpdateLinkUID( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnRequestBankDetails.updateLinkUID</code> attribute. 
	 * @param value the updateLinkUID - Bank Details Update Link UID
	 */
	public void setUpdateLinkUID(final SessionContext ctx, final String value)
	{
		setProperty(ctx, UPDATELINKUID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnRequestBankDetails.updateLinkUID</code> attribute. 
	 * @param value the updateLinkUID - Bank Details Update Link UID
	 */
	public void setUpdateLinkUID(final String value)
	{
		setUpdateLinkUID( getSession().getSessionContext(), value );
	}
	
}
