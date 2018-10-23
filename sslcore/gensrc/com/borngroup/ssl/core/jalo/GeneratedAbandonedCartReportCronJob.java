/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.cronjob.jalo.CronJob AbandonedCartReportCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedAbandonedCartReportCronJob extends CronJob
{
	/** Qualifier of the <code>AbandonedCartReportCronJob.emailRecipients</code> attribute **/
	public static final String EMAILRECIPIENTS = "emailRecipients";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(EMAILRECIPIENTS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbandonedCartReportCronJob.emailRecipients</code> attribute.
	 * @return the emailRecipients - Email Recipients
	 */
	public String getEmailRecipients(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EMAILRECIPIENTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbandonedCartReportCronJob.emailRecipients</code> attribute.
	 * @return the emailRecipients - Email Recipients
	 */
	public String getEmailRecipients()
	{
		return getEmailRecipients( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbandonedCartReportCronJob.emailRecipients</code> attribute. 
	 * @param value the emailRecipients - Email Recipients
	 */
	public void setEmailRecipients(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EMAILRECIPIENTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbandonedCartReportCronJob.emailRecipients</code> attribute. 
	 * @param value the emailRecipients - Email Recipients
	 */
	public void setEmailRecipients(final String value)
	{
		setEmailRecipients( getSession().getSessionContext(), value );
	}
	
}
