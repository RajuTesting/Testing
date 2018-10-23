/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.DepartmentBufferStock;
import com.borngroup.ssl.core.jalo.SeasonGroup;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem Season}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSeason extends GenericItem
{
	/** Qualifier of the <code>Season.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>Season.seasonGroup</code> attribute **/
	public static final String SEASONGROUP = "seasonGroup";
	/** Qualifier of the <code>Season.departmentBufferStocks</code> attribute **/
	public static final String DEPARTMENTBUFFERSTOCKS = "departmentBufferStocks";
	/** Relation ordering override parameter constants for DBS2SeasonRelation from ((sslcore))*/
	protected static String DBS2SEASONRELATION_SRC_ORDERED = "relation.DBS2SeasonRelation.source.ordered";
	protected static String DBS2SEASONRELATION_TGT_ORDERED = "relation.DBS2SeasonRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for DBS2SeasonRelation from ((sslcore))*/
	protected static String DBS2SEASONRELATION_MARKMODIFIED = "relation.DBS2SeasonRelation.markmodified";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n SEASONGROUP's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedSeason> SEASONGROUPHANDLER = new BidirectionalOneToManyHandler<GeneratedSeason>(
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
		tmp.put(SEASONGROUP, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Season.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Season.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Season.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Season.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		SEASONGROUPHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Season.departmentBufferStocks</code> attribute.
	 * @return the departmentBufferStocks
	 */
	public Set<DepartmentBufferStock> getDepartmentBufferStocks(final SessionContext ctx)
	{
		final List<DepartmentBufferStock> items = getLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.DBS2SEASONRELATION,
			null,
			false,
			false
		);
		return new LinkedHashSet<DepartmentBufferStock>(items);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Season.departmentBufferStocks</code> attribute.
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
			SslCoreConstants.Relations.DBS2SEASONRELATION,
			null
		);
	}
	
	public long getDepartmentBufferStocksCount()
	{
		return getDepartmentBufferStocksCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Season.departmentBufferStocks</code> attribute. 
	 * @param value the departmentBufferStocks
	 */
	public void setDepartmentBufferStocks(final SessionContext ctx, final Set<DepartmentBufferStock> value)
	{
		setLinkedItems( 
			ctx,
			false,
			SslCoreConstants.Relations.DBS2SEASONRELATION,
			null,
			value,
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Season.departmentBufferStocks</code> attribute. 
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
			SslCoreConstants.Relations.DBS2SEASONRELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONRELATION_MARKMODIFIED)
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
			SslCoreConstants.Relations.DBS2SEASONRELATION,
			null,
			Collections.singletonList(value),
			false,
			false,
			Utilities.getMarkModifiedOverride(DBS2SEASONRELATION_MARKMODIFIED)
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
	 * <i>Generated method</i> - Getter of the <code>Season.seasonGroup</code> attribute.
	 * @return the seasonGroup
	 */
	public SeasonGroup getSeasonGroup(final SessionContext ctx)
	{
		return (SeasonGroup)getProperty( ctx, SEASONGROUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Season.seasonGroup</code> attribute.
	 * @return the seasonGroup
	 */
	public SeasonGroup getSeasonGroup()
	{
		return getSeasonGroup( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Season.seasonGroup</code> attribute. 
	 * @param value the seasonGroup
	 */
	public void setSeasonGroup(final SessionContext ctx, final SeasonGroup value)
	{
		SEASONGROUPHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Season.seasonGroup</code> attribute. 
	 * @param value the seasonGroup
	 */
	public void setSeasonGroup(final SeasonGroup value)
	{
		setSeasonGroup( getSession().getSessionContext(), value );
	}
	
}
