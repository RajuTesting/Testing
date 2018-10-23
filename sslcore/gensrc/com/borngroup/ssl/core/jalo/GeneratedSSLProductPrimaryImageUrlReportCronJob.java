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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.cronjob.jalo.CronJob SSLProductPrimaryImageUrlReportCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLProductPrimaryImageUrlReportCronJob extends CronJob
{
	/** Qualifier of the <code>SSLProductPrimaryImageUrlReportCronJob.categoryIds</code> attribute **/
	public static final String CATEGORYIDS = "categoryIds";
	/** Qualifier of the <code>SSLProductPrimaryImageUrlReportCronJob.seasonCode</code> attribute **/
	public static final String SEASONCODE = "seasonCode";
	/** Qualifier of the <code>SSLProductPrimaryImageUrlReportCronJob.approvalStatus</code> attribute **/
	public static final String APPROVALSTATUS = "approvalStatus";
	/** Qualifier of the <code>SSLProductPrimaryImageUrlReportCronJob.inventory</code> attribute **/
	public static final String INVENTORY = "inventory";
	/** Qualifier of the <code>SSLProductPrimaryImageUrlReportCronJob.productCreationDate</code> attribute **/
	public static final String PRODUCTCREATIONDATE = "productCreationDate";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CATEGORYIDS, AttributeMode.INITIAL);
		tmp.put(SEASONCODE, AttributeMode.INITIAL);
		tmp.put(APPROVALSTATUS, AttributeMode.INITIAL);
		tmp.put(INVENTORY, AttributeMode.INITIAL);
		tmp.put(PRODUCTCREATIONDATE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.approvalStatus</code> attribute.
	 * @return the approvalStatus - status of style Products to be included
	 */
	public String getApprovalStatus(final SessionContext ctx)
	{
		return (String)getProperty( ctx, APPROVALSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.approvalStatus</code> attribute.
	 * @return the approvalStatus - status of style Products to be included
	 */
	public String getApprovalStatus()
	{
		return getApprovalStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.approvalStatus</code> attribute. 
	 * @param value the approvalStatus - status of style Products to be included
	 */
	public void setApprovalStatus(final SessionContext ctx, final String value)
	{
		setProperty(ctx, APPROVALSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.approvalStatus</code> attribute. 
	 * @param value the approvalStatus - status of style Products to be included
	 */
	public void setApprovalStatus(final String value)
	{
		setApprovalStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.categoryIds</code> attribute.
	 * @return the categoryIds - L3 Category ids
	 */
	public String getCategoryIds(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CATEGORYIDS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.categoryIds</code> attribute.
	 * @return the categoryIds - L3 Category ids
	 */
	public String getCategoryIds()
	{
		return getCategoryIds( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.categoryIds</code> attribute. 
	 * @param value the categoryIds - L3 Category ids
	 */
	public void setCategoryIds(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CATEGORYIDS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.categoryIds</code> attribute. 
	 * @param value the categoryIds - L3 Category ids
	 */
	public void setCategoryIds(final String value)
	{
		setCategoryIds( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.inventory</code> attribute.
	 * @return the inventory - Inventory of Product to be included
	 */
	public Integer getInventory(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, INVENTORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.inventory</code> attribute.
	 * @return the inventory - Inventory of Product to be included
	 */
	public Integer getInventory()
	{
		return getInventory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.inventory</code> attribute. 
	 * @return the inventory - Inventory of Product to be included
	 */
	public int getInventoryAsPrimitive(final SessionContext ctx)
	{
		Integer value = getInventory( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.inventory</code> attribute. 
	 * @return the inventory - Inventory of Product to be included
	 */
	public int getInventoryAsPrimitive()
	{
		return getInventoryAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.inventory</code> attribute. 
	 * @param value the inventory - Inventory of Product to be included
	 */
	public void setInventory(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, INVENTORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.inventory</code> attribute. 
	 * @param value the inventory - Inventory of Product to be included
	 */
	public void setInventory(final Integer value)
	{
		setInventory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.inventory</code> attribute. 
	 * @param value the inventory - Inventory of Product to be included
	 */
	public void setInventory(final SessionContext ctx, final int value)
	{
		setInventory( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.inventory</code> attribute. 
	 * @param value the inventory - Inventory of Product to be included
	 */
	public void setInventory(final int value)
	{
		setInventory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.productCreationDate</code> attribute.
	 * @return the productCreationDate - Creation date of products to be included
	 */
	public Date getProductCreationDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, PRODUCTCREATIONDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.productCreationDate</code> attribute.
	 * @return the productCreationDate - Creation date of products to be included
	 */
	public Date getProductCreationDate()
	{
		return getProductCreationDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.productCreationDate</code> attribute. 
	 * @param value the productCreationDate - Creation date of products to be included
	 */
	public void setProductCreationDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, PRODUCTCREATIONDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.productCreationDate</code> attribute. 
	 * @param value the productCreationDate - Creation date of products to be included
	 */
	public void setProductCreationDate(final Date value)
	{
		setProductCreationDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.seasonCode</code> attribute.
	 * @return the seasonCode - Season Code of Style Product for the filtering
	 */
	public String getSeasonCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SEASONCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductPrimaryImageUrlReportCronJob.seasonCode</code> attribute.
	 * @return the seasonCode - Season Code of Style Product for the filtering
	 */
	public String getSeasonCode()
	{
		return getSeasonCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.seasonCode</code> attribute. 
	 * @param value the seasonCode - Season Code of Style Product for the filtering
	 */
	public void setSeasonCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SEASONCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductPrimaryImageUrlReportCronJob.seasonCode</code> attribute. 
	 * @param value the seasonCode - Season Code of Style Product for the filtering
	 */
	public void setSeasonCode(final String value)
	{
		setSeasonCode( getSession().getSessionContext(), value );
	}
	
}
