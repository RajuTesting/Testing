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
package de.hybris.platform.solrfacetsearch.jalo.indexer.cron;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.solrfacetsearch.jalo.indexer.cron.SolrExtIndexerCronJob;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.solrfacetsearch.jalo.indexer.cron.SslSolrExtIndexerCronJob SslSolrExtIndexerCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslSolrExtIndexerCronJob extends SolrExtIndexerCronJob
{
	/** Qualifier of the <code>SslSolrExtIndexerCronJob.lastSuccessfullyExecutedTime</code> attribute **/
	public static final String LASTSUCCESSFULLYEXECUTEDTIME = "lastSuccessfullyExecutedTime";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SolrExtIndexerCronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LASTSUCCESSFULLYEXECUTEDTIME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslSolrExtIndexerCronJob.lastSuccessfullyExecutedTime</code> attribute.
	 * @return the lastSuccessfullyExecutedTime
	 */
	public Date getLastSuccessfullyExecutedTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, LASTSUCCESSFULLYEXECUTEDTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslSolrExtIndexerCronJob.lastSuccessfullyExecutedTime</code> attribute.
	 * @return the lastSuccessfullyExecutedTime
	 */
	public Date getLastSuccessfullyExecutedTime()
	{
		return getLastSuccessfullyExecutedTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslSolrExtIndexerCronJob.lastSuccessfullyExecutedTime</code> attribute. 
	 * @param value the lastSuccessfullyExecutedTime
	 */
	public void setLastSuccessfullyExecutedTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, LASTSUCCESSFULLYEXECUTEDTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslSolrExtIndexerCronJob.lastSuccessfullyExecutedTime</code> attribute. 
	 * @param value the lastSuccessfullyExecutedTime
	 */
	public void setLastSuccessfullyExecutedTime(final Date value)
	{
		setLastSuccessfullyExecutedTime( getSession().getSessionContext(), value );
	}
	
}
