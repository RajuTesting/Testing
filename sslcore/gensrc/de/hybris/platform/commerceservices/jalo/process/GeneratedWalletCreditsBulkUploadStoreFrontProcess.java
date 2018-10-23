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
 * Generated class for type {@link de.hybris.platform.commerceservices.jalo.process.WalletCreditsBulkUploadStoreFrontProcess WalletCreditsBulkUploadStoreFrontProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedWalletCreditsBulkUploadStoreFrontProcess extends StoreFrontProcess
{
	/** Qualifier of the <code>WalletCreditsBulkUploadStoreFrontProcess.uploadStatus</code> attribute **/
	public static final String UPLOADSTATUS = "uploadStatus";
	/** Qualifier of the <code>WalletCreditsBulkUploadStoreFrontProcess.reportAttachmentCode</code> attribute **/
	public static final String REPORTATTACHMENTCODE = "reportAttachmentCode";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(StoreFrontProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(UPLOADSTATUS, AttributeMode.INITIAL);
		tmp.put(REPORTATTACHMENTCODE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WalletCreditsBulkUploadStoreFrontProcess.reportAttachmentCode</code> attribute.
	 * @return the reportAttachmentCode - report attachment code
	 */
	public String getReportAttachmentCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, REPORTATTACHMENTCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WalletCreditsBulkUploadStoreFrontProcess.reportAttachmentCode</code> attribute.
	 * @return the reportAttachmentCode - report attachment code
	 */
	public String getReportAttachmentCode()
	{
		return getReportAttachmentCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WalletCreditsBulkUploadStoreFrontProcess.reportAttachmentCode</code> attribute. 
	 * @param value the reportAttachmentCode - report attachment code
	 */
	public void setReportAttachmentCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, REPORTATTACHMENTCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WalletCreditsBulkUploadStoreFrontProcess.reportAttachmentCode</code> attribute. 
	 * @param value the reportAttachmentCode - report attachment code
	 */
	public void setReportAttachmentCode(final String value)
	{
		setReportAttachmentCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WalletCreditsBulkUploadStoreFrontProcess.uploadStatus</code> attribute.
	 * @return the uploadStatus - uploadStatus
	 */
	public String getUploadStatus(final SessionContext ctx)
	{
		return (String)getProperty( ctx, UPLOADSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WalletCreditsBulkUploadStoreFrontProcess.uploadStatus</code> attribute.
	 * @return the uploadStatus - uploadStatus
	 */
	public String getUploadStatus()
	{
		return getUploadStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WalletCreditsBulkUploadStoreFrontProcess.uploadStatus</code> attribute. 
	 * @param value the uploadStatus - uploadStatus
	 */
	public void setUploadStatus(final SessionContext ctx, final String value)
	{
		setProperty(ctx, UPLOADSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WalletCreditsBulkUploadStoreFrontProcess.uploadStatus</code> attribute. 
	 * @param value the uploadStatus - uploadStatus
	 */
	public void setUploadStatus(final String value)
	{
		setUploadStatus( getSession().getSessionContext(), value );
	}
	
}
