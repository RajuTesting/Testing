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
 * Generated class for type {@link de.hybris.platform.cronjob.jalo.CronJob SSLBestSellerProductsInL3CategoryCronjob}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBestSellerProductsInL3CategoryCronjob extends CronJob
{
	/** Qualifier of the <code>SSLBestSellerProductsInL3CategoryCronjob.dateOfOrderCreated</code> attribute **/
	public static final String DATEOFORDERCREATED = "dateOfOrderCreated";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(DATEOFORDERCREATED, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBestSellerProductsInL3CategoryCronjob.dateOfOrderCreated</code> attribute.
	 * @return the dateOfOrderCreated - Date Of Order Created.
	 */
	public Integer getDateOfOrderCreated(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, DATEOFORDERCREATED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBestSellerProductsInL3CategoryCronjob.dateOfOrderCreated</code> attribute.
	 * @return the dateOfOrderCreated - Date Of Order Created.
	 */
	public Integer getDateOfOrderCreated()
	{
		return getDateOfOrderCreated( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBestSellerProductsInL3CategoryCronjob.dateOfOrderCreated</code> attribute. 
	 * @return the dateOfOrderCreated - Date Of Order Created.
	 */
	public int getDateOfOrderCreatedAsPrimitive(final SessionContext ctx)
	{
		Integer value = getDateOfOrderCreated( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBestSellerProductsInL3CategoryCronjob.dateOfOrderCreated</code> attribute. 
	 * @return the dateOfOrderCreated - Date Of Order Created.
	 */
	public int getDateOfOrderCreatedAsPrimitive()
	{
		return getDateOfOrderCreatedAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBestSellerProductsInL3CategoryCronjob.dateOfOrderCreated</code> attribute. 
	 * @param value the dateOfOrderCreated - Date Of Order Created.
	 */
	public void setDateOfOrderCreated(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, DATEOFORDERCREATED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBestSellerProductsInL3CategoryCronjob.dateOfOrderCreated</code> attribute. 
	 * @param value the dateOfOrderCreated - Date Of Order Created.
	 */
	public void setDateOfOrderCreated(final Integer value)
	{
		setDateOfOrderCreated( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBestSellerProductsInL3CategoryCronjob.dateOfOrderCreated</code> attribute. 
	 * @param value the dateOfOrderCreated - Date Of Order Created.
	 */
	public void setDateOfOrderCreated(final SessionContext ctx, final int value)
	{
		setDateOfOrderCreated( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBestSellerProductsInL3CategoryCronjob.dateOfOrderCreated</code> attribute. 
	 * @param value the dateOfOrderCreated - Date Of Order Created.
	 */
	public void setDateOfOrderCreated(final int value)
	{
		setDateOfOrderCreated( getSession().getSessionContext(), value );
	}
	
}
