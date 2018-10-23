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
 * Generated class for type {@link de.hybris.platform.commerceservices.jalo.process.ProductUploadAutoApprovalStoreFrontProcess ProductUploadAutoApprovalStoreFrontProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedProductUploadAutoApprovalStoreFrontProcess extends StoreFrontProcess
{
	/** Qualifier of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobCode</code> attribute **/
	public static final String CRONJOBCODE = "cronJobCode";
	/** Qualifier of the <code>ProductUploadAutoApprovalStoreFrontProcess.startTime</code> attribute **/
	public static final String STARTTIME = "startTime";
	/** Qualifier of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobResult</code> attribute **/
	public static final String CRONJOBRESULT = "cronJobResult";
	/** Qualifier of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobStatus</code> attribute **/
	public static final String CRONJOBSTATUS = "cronJobStatus";
	/** Qualifier of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport</code> attribute **/
	public static final String URLFORREPORT = "urlForReport";
	/** Qualifier of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport2</code> attribute **/
	public static final String URLFORREPORT2 = "urlForReport2";
	/** Qualifier of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport3</code> attribute **/
	public static final String URLFORREPORT3 = "urlForReport3";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(StoreFrontProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CRONJOBCODE, AttributeMode.INITIAL);
		tmp.put(STARTTIME, AttributeMode.INITIAL);
		tmp.put(CRONJOBRESULT, AttributeMode.INITIAL);
		tmp.put(CRONJOBSTATUS, AttributeMode.INITIAL);
		tmp.put(URLFORREPORT, AttributeMode.INITIAL);
		tmp.put(URLFORREPORT2, AttributeMode.INITIAL);
		tmp.put(URLFORREPORT3, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobCode</code> attribute.
	 * @return the cronJobCode - CronJob Code
	 */
	public String getCronJobCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CRONJOBCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobCode</code> attribute.
	 * @return the cronJobCode - CronJob Code
	 */
	public String getCronJobCode()
	{
		return getCronJobCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobCode</code> attribute. 
	 * @param value the cronJobCode - CronJob Code
	 */
	public void setCronJobCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CRONJOBCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobCode</code> attribute. 
	 * @param value the cronJobCode - CronJob Code
	 */
	public void setCronJobCode(final String value)
	{
		setCronJobCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobResult</code> attribute.
	 * @return the cronJobResult - CronJob Result
	 */
	public String getCronJobResult(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CRONJOBRESULT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobResult</code> attribute.
	 * @return the cronJobResult - CronJob Result
	 */
	public String getCronJobResult()
	{
		return getCronJobResult( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobResult</code> attribute. 
	 * @param value the cronJobResult - CronJob Result
	 */
	public void setCronJobResult(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CRONJOBRESULT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobResult</code> attribute. 
	 * @param value the cronJobResult - CronJob Result
	 */
	public void setCronJobResult(final String value)
	{
		setCronJobResult( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobStatus</code> attribute.
	 * @return the cronJobStatus - CronJob Status
	 */
	public String getCronJobStatus(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CRONJOBSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobStatus</code> attribute.
	 * @return the cronJobStatus - CronJob Status
	 */
	public String getCronJobStatus()
	{
		return getCronJobStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobStatus</code> attribute. 
	 * @param value the cronJobStatus - CronJob Status
	 */
	public void setCronJobStatus(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CRONJOBSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.cronJobStatus</code> attribute. 
	 * @param value the cronJobStatus - CronJob Status
	 */
	public void setCronJobStatus(final String value)
	{
		setCronJobStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.startTime</code> attribute.
	 * @return the startTime - Cronjob StartTime
	 */
	public String getStartTime(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STARTTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.startTime</code> attribute.
	 * @return the startTime - Cronjob StartTime
	 */
	public String getStartTime()
	{
		return getStartTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.startTime</code> attribute. 
	 * @param value the startTime - Cronjob StartTime
	 */
	public void setStartTime(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STARTTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.startTime</code> attribute. 
	 * @param value the startTime - Cronjob StartTime
	 */
	public void setStartTime(final String value)
	{
		setStartTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport</code> attribute.
	 * @return the urlForReport - URL to download Report
	 */
	public String getUrlForReport(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URLFORREPORT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport</code> attribute.
	 * @return the urlForReport - URL to download Report
	 */
	public String getUrlForReport()
	{
		return getUrlForReport( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport</code> attribute. 
	 * @param value the urlForReport - URL to download Report
	 */
	public void setUrlForReport(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URLFORREPORT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport</code> attribute. 
	 * @param value the urlForReport - URL to download Report
	 */
	public void setUrlForReport(final String value)
	{
		setUrlForReport( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport2</code> attribute.
	 * @return the urlForReport2 - URL to download Report
	 */
	public String getUrlForReport2(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URLFORREPORT2);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport2</code> attribute.
	 * @return the urlForReport2 - URL to download Report
	 */
	public String getUrlForReport2()
	{
		return getUrlForReport2( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport2</code> attribute. 
	 * @param value the urlForReport2 - URL to download Report
	 */
	public void setUrlForReport2(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URLFORREPORT2,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport2</code> attribute. 
	 * @param value the urlForReport2 - URL to download Report
	 */
	public void setUrlForReport2(final String value)
	{
		setUrlForReport2( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport3</code> attribute.
	 * @return the urlForReport3 - URL to download Report
	 */
	public String getUrlForReport3(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URLFORREPORT3);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport3</code> attribute.
	 * @return the urlForReport3 - URL to download Report
	 */
	public String getUrlForReport3()
	{
		return getUrlForReport3( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport3</code> attribute. 
	 * @param value the urlForReport3 - URL to download Report
	 */
	public void setUrlForReport3(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URLFORREPORT3,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductUploadAutoApprovalStoreFrontProcess.urlForReport3</code> attribute. 
	 * @param value the urlForReport3 - URL to download Report
	 */
	public void setUrlForReport3(final String value)
	{
		setUrlForReport3( getSession().getSessionContext(), value );
	}
	
}
