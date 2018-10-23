/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.DepartmentBufferStock;
import com.borngroup.ssl.core.jalo.Season;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SeasonGroup}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSeasonGroup extends GenericItem
{
	/** Qualifier of the <code>SeasonGroup.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>SeasonGroup.departmentBufferStocks</code> attribute **/
	public static final String DEPARTMENTBUFFERSTOCKS = "departmentBufferStocks";
	/** Relation ordering override parameter constants for DBS2SeasonGroupRelation from ((sslcore))*/
	protected static String DBS2SEASONGROUPRELATION_SRC_ORDERED = "relation.DBS2SeasonGroupRelation.source.ordered";
	protected static String DBS2SEASONGROUPRELATION_TGT_ORDERED = "relation.DBS2SeasonGroupRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for DBS2SeasonGroupRelation from ((sslcore))*/
	protected static String DBS2SEASONGROUPRELATION_MARKMODIFIED = "relation.DBS2SeasonGroupRelation.markmodified";
	/** Qualifier of the <code>SeasonGroup.seasons</code> attribute **/
	public static final String SEASONS = "seasons";
	/**
	* {@link OneToManyHandler} for handling 1:n SEASONS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<Season> SEASONSHANDLER = new OneToManyHandler<Season>(
	SslCoreConstants.TC.SEASON,
	false,
	"seasonGroup",
	null,
	false,
	true,
	CollectionType.SET
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SeasonGroup.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SeasonGroup.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SeasonGroup.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SeasonGroup.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SeasonGroup.departmentBufferStocks</code> attribute.
	 * @return the departmentBufferStocks
	 */
	public Set<DepartmentBufferStock> getDepartmentBufferStocks(final SessionContext ctx)
	{
		final List<DepartmentBufferStock> items = getLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.DBS2SEASONGROUPRELATION,
			null,
			false,
			false
		);
		return new LinkedHashSet<DepartmentBufferStock>(items);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SeasonGroup.departmentBufferStocks</code> attribute.
	 * @return the departmentBufferStocks
	 */
	public Set<DepartmentBufferStock> getDepartmentBufferStocks()
	{
		return getDepartmentBufferStocks( getSession().getSessionContext() );
	}
	
	public long getDepartmentBufferStocksCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			false,
			SslCoreConstants.Relations.DBS2SEASONGROUPRELATION,
			null
		);
	}
	
	public long getDepartmentBufferStocksCount()
	{
		return getDepartmentBufferStocksCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SeasonGroup.departmentBufferStocks</code> attribute. 
	 * @param value the departmentBufferStocks
	 */
	public void setDepartmentBufferStocks(final SessionContext ctx, final Set<DepartmentBufferStock> value)
	{
		setLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.DBS2SEASONGROUPRELATION,
			null,
			value,
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONGROUPRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SeasonGroup.departmentBufferStocks</code> attribute. 
	 * @param value the departmentBufferStocks
	 */
	public void setDepartmentBufferStocks(final Set<DepartmentBufferStock> value)
	{
		setDepartmentBufferStocks( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to departmentBufferStocks. 
	 * @param value the item to add to departmentBufferStocks
	 */
	public void addToDepartmentBufferStocks(final SessionContext ctx, final DepartmentBufferStock value)
	{
		addLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.DBS2SEASONGROUPRELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONGROUPRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to departmentBufferStocks. 
	 * @param value the item to add to departmentBufferStocks
	 */
	public void addToDepartmentBufferStocks(final DepartmentBufferStock value)
	{
		addToDepartmentBufferStocks( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from departmentBufferStocks. 
	 * @param value the item to remove from departmentBufferStocks
	 */
	public void removeFromDepartmentBufferStocks(final SessionContext ctx, final DepartmentBufferStock value)
	{
		removeLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.DBS2SEASONGROUPRELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONGROUPRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from departmentBufferStocks. 
	 * @param value the item to remove from departmentBufferStocks
	 */
	public void removeFromDepartmentBufferStocks(final DepartmentBufferStock value)
	{
		removeFromDepartmentBufferStocks( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SeasonGroup.seasons</code> attribute.
	 * @return the seasons
	 */
	public Set<Season> getSeasons(final SessionContext ctx)
	{
		return (Set<Season>)SEASONSHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SeasonGroup.seasons</code> attribute.
	 * @return the seasons
	 */
	public Set<Season> getSeasons()
	{
		return getSeasons( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SeasonGroup.seasons</code> attribute. 
	 * @param value the seasons
	 */
	public void setSeasons(final SessionContext ctx, final Set<Season> value)
	{
		SEASONSHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SeasonGroup.seasons</code> attribute. 
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
		SEASONSHANDLER.addValue( ctx, this, value );
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
		SEASONSHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from seasons. 
	 * @param value the item to remove from seasons
	 */
	public void removeFromSeasons(final Season value)
	{
		removeFromSeasons( getSession().getSessionContext(), value );
	}
	
}
