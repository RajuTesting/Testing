/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.pcm.perf.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.constants.CoreConstants;
import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.pcm.perf.jalo.SSLClassificationAttrTemplateCronJob SSLClassificationAttrTemplateCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLClassificationAttrTemplateCronJob extends CronJob
{
	/** Qualifier of the <code>SSLClassificationAttrTemplateCronJob.template</code> attribute **/
	public static final String TEMPLATE = "template";
	/** Qualifier of the <code>SSLClassificationAttrTemplateCronJob.catalogId</code> attribute **/
	public static final String CATALOGID = "catalogId";
	/** Qualifier of the <code>SSLClassificationAttrTemplateCronJob.categoryIds</code> attribute **/
	public static final String CATEGORYIDS = "categoryIds";
	/** Qualifier of the <code>SSLClassificationAttrTemplateCronJob.seasonCodes</code> attribute **/
	public static final String SEASONCODES = "seasonCodes";
	/** Qualifier of the <code>SSLClassificationAttrTemplateCronJob.approvalStatus</code> attribute **/
	public static final String APPROVALSTATUS = "approvalStatus";
	/** Qualifier of the <code>SSLClassificationAttrTemplateCronJob.limitedExport</code> attribute **/
	public static final String LIMITEDEXPORT = "limitedExport";
	/** Qualifier of the <code>SSLClassificationAttrTemplateCronJob.createdDateFilter</code> attribute **/
	public static final String CREATEDDATEFILTER = "createdDateFilter";
	/** Qualifier of the <code>SSLClassificationAttrTemplateCronJob.targetSheets</code> attribute **/
	public static final String TARGETSHEETS = "targetSheets";
	/**
	* {@link OneToManyHandler} for handling 1:n TARGETSHEETS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<Media> TARGETSHEETSHANDLER = new OneToManyHandler<Media>(
	CoreConstants.TC.MEDIA,
	false,
	"sslClCron",
	null,
	false,
	true,
	CollectionType.LIST
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TEMPLATE, AttributeMode.INITIAL);
		tmp.put(CATALOGID, AttributeMode.INITIAL);
		tmp.put(CATEGORYIDS, AttributeMode.INITIAL);
		tmp.put(SEASONCODES, AttributeMode.INITIAL);
		tmp.put(APPROVALSTATUS, AttributeMode.INITIAL);
		tmp.put(LIMITEDEXPORT, AttributeMode.INITIAL);
		tmp.put(CREATEDDATEFILTER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.approvalStatus</code> attribute.
	 * @return the approvalStatus - status ids to export
	 */
	public String getApprovalStatus(final SessionContext ctx)
	{
		return (String)getProperty( ctx, APPROVALSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.approvalStatus</code> attribute.
	 * @return the approvalStatus - status ids to export
	 */
	public String getApprovalStatus()
	{
		return getApprovalStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.approvalStatus</code> attribute. 
	 * @param value the approvalStatus - status ids to export
	 */
	public void setApprovalStatus(final SessionContext ctx, final String value)
	{
		setProperty(ctx, APPROVALSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.approvalStatus</code> attribute. 
	 * @param value the approvalStatus - status ids to export
	 */
	public void setApprovalStatus(final String value)
	{
		setApprovalStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.catalogId</code> attribute.
	 * @return the catalogId - Data exported from Staged version of catalog id
	 */
	public String getCatalogId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CATALOGID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.catalogId</code> attribute.
	 * @return the catalogId - Data exported from Staged version of catalog id
	 */
	public String getCatalogId()
	{
		return getCatalogId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.catalogId</code> attribute. 
	 * @param value the catalogId - Data exported from Staged version of catalog id
	 */
	public void setCatalogId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CATALOGID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.catalogId</code> attribute. 
	 * @param value the catalogId - Data exported from Staged version of catalog id
	 */
	public void setCatalogId(final String value)
	{
		setCatalogId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.categoryIds</code> attribute.
	 * @return the categoryIds - Category ids to limited export
	 */
	public String getCategoryIds(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CATEGORYIDS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.categoryIds</code> attribute.
	 * @return the categoryIds - Category ids to limited export
	 */
	public String getCategoryIds()
	{
		return getCategoryIds( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.categoryIds</code> attribute. 
	 * @param value the categoryIds - Category ids to limited export
	 */
	public void setCategoryIds(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CATEGORYIDS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.categoryIds</code> attribute. 
	 * @param value the categoryIds - Category ids to limited export
	 */
	public void setCategoryIds(final String value)
	{
		setCategoryIds( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.createdDateFilter</code> attribute.
	 * @return the createdDateFilter - Product Created Date filter
	 */
	public Date getCreatedDateFilter(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, CREATEDDATEFILTER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.createdDateFilter</code> attribute.
	 * @return the createdDateFilter - Product Created Date filter
	 */
	public Date getCreatedDateFilter()
	{
		return getCreatedDateFilter( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.createdDateFilter</code> attribute. 
	 * @param value the createdDateFilter - Product Created Date filter
	 */
	public void setCreatedDateFilter(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, CREATEDDATEFILTER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.createdDateFilter</code> attribute. 
	 * @param value the createdDateFilter - Product Created Date filter
	 */
	public void setCreatedDateFilter(final Date value)
	{
		setCreatedDateFilter( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.limitedExport</code> attribute.
	 * @return the limitedExport - categoryIds should be considered in case this option
	 *                             is true
	 */
	public Boolean isLimitedExport(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, LIMITEDEXPORT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.limitedExport</code> attribute.
	 * @return the limitedExport - categoryIds should be considered in case this option
	 *                             is true
	 */
	public Boolean isLimitedExport()
	{
		return isLimitedExport( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.limitedExport</code> attribute. 
	 * @return the limitedExport - categoryIds should be considered in case this option
	 *                             is true
	 */
	public boolean isLimitedExportAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isLimitedExport( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.limitedExport</code> attribute. 
	 * @return the limitedExport - categoryIds should be considered in case this option
	 *                             is true
	 */
	public boolean isLimitedExportAsPrimitive()
	{
		return isLimitedExportAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.limitedExport</code> attribute. 
	 * @param value the limitedExport - categoryIds should be considered in case this option
	 *                             is true
	 */
	public void setLimitedExport(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, LIMITEDEXPORT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.limitedExport</code> attribute. 
	 * @param value the limitedExport - categoryIds should be considered in case this option
	 *                             is true
	 */
	public void setLimitedExport(final Boolean value)
	{
		setLimitedExport( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.limitedExport</code> attribute. 
	 * @param value the limitedExport - categoryIds should be considered in case this option
	 *                             is true
	 */
	public void setLimitedExport(final SessionContext ctx, final boolean value)
	{
		setLimitedExport( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.limitedExport</code> attribute. 
	 * @param value the limitedExport - categoryIds should be considered in case this option
	 *                             is true
	 */
	public void setLimitedExport(final boolean value)
	{
		setLimitedExport( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.seasonCodes</code> attribute.
	 * @return the seasonCodes - Season Codes to limited export
	 */
	public String getSeasonCodes(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SEASONCODES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.seasonCodes</code> attribute.
	 * @return the seasonCodes - Season Codes to limited export
	 */
	public String getSeasonCodes()
	{
		return getSeasonCodes( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.seasonCodes</code> attribute. 
	 * @param value the seasonCodes - Season Codes to limited export
	 */
	public void setSeasonCodes(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SEASONCODES,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.seasonCodes</code> attribute. 
	 * @param value the seasonCodes - Season Codes to limited export
	 */
	public void setSeasonCodes(final String value)
	{
		setSeasonCodes( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.targetSheets</code> attribute.
	 * @return the targetSheets
	 */
	public List<Media> getTargetSheets(final SessionContext ctx)
	{
		return (List<Media>)TARGETSHEETSHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.targetSheets</code> attribute.
	 * @return the targetSheets
	 */
	public List<Media> getTargetSheets()
	{
		return getTargetSheets( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.targetSheets</code> attribute. 
	 * @param value the targetSheets
	 */
	public void setTargetSheets(final SessionContext ctx, final List<Media> value)
	{
		TARGETSHEETSHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.targetSheets</code> attribute. 
	 * @param value the targetSheets
	 */
	public void setTargetSheets(final List<Media> value)
	{
		setTargetSheets( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to targetSheets. 
	 * @param value the item to add to targetSheets
	 */
	public void addToTargetSheets(final SessionContext ctx, final Media value)
	{
		TARGETSHEETSHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to targetSheets. 
	 * @param value the item to add to targetSheets
	 */
	public void addToTargetSheets(final Media value)
	{
		addToTargetSheets( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from targetSheets. 
	 * @param value the item to remove from targetSheets
	 */
	public void removeFromTargetSheets(final SessionContext ctx, final Media value)
	{
		TARGETSHEETSHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from targetSheets. 
	 * @param value the item to remove from targetSheets
	 */
	public void removeFromTargetSheets(final Media value)
	{
		removeFromTargetSheets( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.template</code> attribute.
	 * @return the template - Source Empty Template
	 */
	public Media getTemplate(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, TEMPLATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLClassificationAttrTemplateCronJob.template</code> attribute.
	 * @return the template - Source Empty Template
	 */
	public Media getTemplate()
	{
		return getTemplate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.template</code> attribute. 
	 * @param value the template - Source Empty Template
	 */
	public void setTemplate(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, TEMPLATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLClassificationAttrTemplateCronJob.template</code> attribute. 
	 * @param value the template - Source Empty Template
	 */
	public void setTemplate(final Media value)
	{
		setTemplate( getSession().getSessionContext(), value );
	}
	
}
