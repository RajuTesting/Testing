/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.Season;
import com.borngroup.ssl.core.jalo.SeasonGroup;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem DepartmentBufferStock}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedDepartmentBufferStock extends GenericItem
{
	/** Qualifier of the <code>DepartmentBufferStock.bufferStock</code> attribute **/
	public static final String BUFFERSTOCK = "bufferStock";
	/** Qualifier of the <code>DepartmentBufferStock.departmentCode</code> attribute **/
	public static final String DEPARTMENTCODE = "departmentCode";
	/** Qualifier of the <code>DepartmentBufferStock.subDepartmentCode</code> attribute **/
	public static final String SUBDEPARTMENTCODE = "subDepartmentCode";
	/** Qualifier of the <code>DepartmentBufferStock.seasonCode</code> attribute **/
	public static final String SEASONCODE = "seasonCode";
	/** Qualifier of the <code>DepartmentBufferStock.seasonGroupCode</code> attribute **/
	public static final String SEASONGROUPCODE = "seasonGroupCode";
	/** Qualifier of the <code>DepartmentBufferStock.seasonGroups</code> attribute **/
	public static final String SEASONGROUPS = "seasonGroups";
	/** Relation ordering override parameter constants for DBS2SeasonGroupRelation from ((sslcore))*/
	protected static String DBS2SEASONGROUPRELATION_SRC_ORDERED = "relation.DBS2SeasonGroupRelation.source.ordered";
	protected static String DBS2SEASONGROUPRELATION_TGT_ORDERED = "relation.DBS2SeasonGroupRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for DBS2SeasonGroupRelation from ((sslcore))*/
	protected static String DBS2SEASONGROUPRELATION_MARKMODIFIED = "relation.DBS2SeasonGroupRelation.markmodified";
	/** Qualifier of the <code>DepartmentBufferStock.seasons</code> attribute **/
	public static final String SEASONS = "seasons";
	/** Relation ordering override parameter constants for DBS2SeasonRelation from ((sslcore))*/
	protected static String DBS2SEASONRELATION_SRC_ORDERED = "relation.DBS2SeasonRelation.source.ordered";
	protected static String DBS2SEASONRELATION_TGT_ORDERED = "relation.DBS2SeasonRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for DBS2SeasonRelation from ((sslcore))*/
	protected static String DBS2SEASONRELATION_MARKMODIFIED = "relation.DBS2SeasonRelation.markmodified";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(BUFFERSTOCK, AttributeMode.INITIAL);
		tmp.put(DEPARTMENTCODE, AttributeMode.INITIAL);
		tmp.put(SUBDEPARTMENTCODE, AttributeMode.INITIAL);
		tmp.put(SEASONCODE, AttributeMode.INITIAL);
		tmp.put(SEASONGROUPCODE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.bufferStock</code> attribute.
	 * @return the bufferStock
	 */
	public Long getBufferStock(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, BUFFERSTOCK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.bufferStock</code> attribute.
	 * @return the bufferStock
	 */
	public Long getBufferStock()
	{
		return getBufferStock( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.bufferStock</code> attribute. 
	 * @return the bufferStock
	 */
	public long getBufferStockAsPrimitive(final SessionContext ctx)
	{
		Long value = getBufferStock( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.bufferStock</code> attribute. 
	 * @return the bufferStock
	 */
	public long getBufferStockAsPrimitive()
	{
		return getBufferStockAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.bufferStock</code> attribute. 
	 * @param value the bufferStock
	 */
	public void setBufferStock(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, BUFFERSTOCK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.bufferStock</code> attribute. 
	 * @param value the bufferStock
	 */
	public void setBufferStock(final Long value)
	{
		setBufferStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.bufferStock</code> attribute. 
	 * @param value the bufferStock
	 */
	public void setBufferStock(final SessionContext ctx, final long value)
	{
		setBufferStock( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.bufferStock</code> attribute. 
	 * @param value the bufferStock
	 */
	public void setBufferStock(final long value)
	{
		setBufferStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.departmentCode</code> attribute.
	 * @return the departmentCode
	 */
	public String getDepartmentCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEPARTMENTCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.departmentCode</code> attribute.
	 * @return the departmentCode
	 */
	public String getDepartmentCode()
	{
		return getDepartmentCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.departmentCode</code> attribute. 
	 * @param value the departmentCode
	 */
	public void setDepartmentCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEPARTMENTCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.departmentCode</code> attribute. 
	 * @param value the departmentCode
	 */
	public void setDepartmentCode(final String value)
	{
		setDepartmentCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.seasonCode</code> attribute.
	 * @return the seasonCode
	 */
	public String getSeasonCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SEASONCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.seasonCode</code> attribute.
	 * @return the seasonCode
	 */
	public String getSeasonCode()
	{
		return getSeasonCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.seasonCode</code> attribute. 
	 * @param value the seasonCode
	 */
	public void setSeasonCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SEASONCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.seasonCode</code> attribute. 
	 * @param value the seasonCode
	 */
	public void setSeasonCode(final String value)
	{
		setSeasonCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.seasonGroupCode</code> attribute.
	 * @return the seasonGroupCode
	 */
	public String getSeasonGroupCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SEASONGROUPCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.seasonGroupCode</code> attribute.
	 * @return the seasonGroupCode
	 */
	public String getSeasonGroupCode()
	{
		return getSeasonGroupCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.seasonGroupCode</code> attribute. 
	 * @param value the seasonGroupCode
	 */
	public void setSeasonGroupCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SEASONGROUPCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.seasonGroupCode</code> attribute. 
	 * @param value the seasonGroupCode
	 */
	public void setSeasonGroupCode(final String value)
	{
		setSeasonGroupCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.seasonGroups</code> attribute.
	 * @return the seasonGroups
	 */
	public Set<SeasonGroup> getSeasonGroups(final SessionContext ctx)
	{
		final List<SeasonGroup> items = getLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DBS2SEASONGROUPRELATION,
			null,
			false,
			false
		);
		return new LinkedHashSet<SeasonGroup>(items);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.seasonGroups</code> attribute.
	 * @return the seasonGroups
	 */
	public Set<SeasonGroup> getSeasonGroups()
	{
		return getSeasonGroups( getSession().getSessionContext() );
	}
	
	public long getSeasonGroupsCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			true,
			SslCoreConstants.Relations.DBS2SEASONGROUPRELATION,
			null
		);
	}
	
	public long getSeasonGroupsCount()
	{
		return getSeasonGroupsCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.seasonGroups</code> attribute. 
	 * @param value the seasonGroups
	 */
	public void setSeasonGroups(final SessionContext ctx, final Set<SeasonGroup> value)
	{
		setLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DBS2SEASONGROUPRELATION,
			null,
			value,
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONGROUPRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.seasonGroups</code> attribute. 
	 * @param value the seasonGroups
	 */
	public void setSeasonGroups(final Set<SeasonGroup> value)
	{
		setSeasonGroups( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to seasonGroups. 
	 * @param value the item to add to seasonGroups
	 */
	public void addToSeasonGroups(final SessionContext ctx, final SeasonGroup value)
	{
		addLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DBS2SEASONGROUPRELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONGROUPRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to seasonGroups. 
	 * @param value the item to add to seasonGroups
	 */
	public void addToSeasonGroups(final SeasonGroup value)
	{
		addToSeasonGroups( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from seasonGroups. 
	 * @param value the item to remove from seasonGroups
	 */
	public void removeFromSeasonGroups(final SessionContext ctx, final SeasonGroup value)
	{
		removeLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DBS2SEASONGROUPRELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONGROUPRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from seasonGroups. 
	 * @param value the item to remove from seasonGroups
	 */
	public void removeFromSeasonGroups(final SeasonGroup value)
	{
		removeFromSeasonGroups( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.seasons</code> attribute.
	 * @return the seasons
	 */
	public Set<Season> getSeasons(final SessionContext ctx)
	{
		final List<Season> items = getLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DBS2SEASONRELATION,
			null,
			false,
			false
		);
		return new LinkedHashSet<Season>(items);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.seasons</code> attribute.
	 * @return the seasons
	 */
	public Set<Season> getSeasons()
	{
		return getSeasons( getSession().getSessionContext() );
	}
	
	public long getSeasonsCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			true,
			SslCoreConstants.Relations.DBS2SEASONRELATION,
			null
		);
	}
	
	public long getSeasonsCount()
	{
		return getSeasonsCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.seasons</code> attribute. 
	 * @param value the seasons
	 */
	public void setSeasons(final SessionContext ctx, final Set<Season> value)
	{
		setLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DBS2SEASONRELATION,
			null,
			value,
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.seasons</code> attribute. 
	 * @param value the seasons
	 */
	public void setSeasons(final Set<Season> value)
	{
		setSeasons( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to seasons. 
	 * @param value the item to add to seasons
	 */
	public void addToSeasons(final SessionContext ctx, final Season value)
	{
		addLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DBS2SEASONRELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to seasons. 
	 * @param value the item to add to seasons
	 */
	public void addToSeasons(final Season value)
	{
		addToSeasons( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from seasons. 
	 * @param value the item to remove from seasons
	 */
	public void removeFromSeasons(final SessionContext ctx, final Season value)
	{
		removeLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DBS2SEASONRELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from seasons. 
	 * @param value the item to remove from seasons
	 */
	public void removeFromSeasons(final Season value)
	{
		removeFromSeasons( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.subDepartmentCode</code> attribute.
	 * @return the subDepartmentCode
	 */
	public String getSubDepartmentCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SUBDEPARTMENTCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DepartmentBufferStock.subDepartmentCode</code> attribute.
	 * @return the subDepartmentCode
	 */
	public String getSubDepartmentCode()
	{
		return getSubDepartmentCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.subDepartmentCode</code> attribute. 
	 * @param value the subDepartmentCode
	 */
	public void setSubDepartmentCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SUBDEPARTMENTCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DepartmentBufferStock.subDepartmentCode</code> attribute. 
	 * @param value the subDepartmentCode
	 */
	public void setSubDepartmentCode(final String value)
	{
		setSubDepartmentCode( getSession().getSessionContext(), value );
	}
	
}
