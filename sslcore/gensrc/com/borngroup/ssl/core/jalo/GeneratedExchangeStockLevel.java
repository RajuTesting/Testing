/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.ordersplitting.jalo.StockLevel;
import de.hybris.platform.returns.jalo.ReplacementEntry;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem ExchangeStockLevel}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedExchangeStockLevel extends GenericItem
{
	/** Qualifier of the <code>ExchangeStockLevel.stockLevel</code> attribute **/
	public static final String STOCKLEVEL = "stockLevel";
	/** Qualifier of the <code>ExchangeStockLevel.quantity</code> attribute **/
	public static final String QUANTITY = "quantity";
	/** Qualifier of the <code>ExchangeStockLevel.rejectedQuantity</code> attribute **/
	public static final String REJECTEDQUANTITY = "rejectedQuantity";
	/** Qualifier of the <code>ExchangeStockLevel.replacementEntry</code> attribute **/
	public static final String REPLACEMENTENTRY = "replacementEntry";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n REPLACEMENTENTRY's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedExchangeStockLevel> REPLACEMENTENTRYHANDLER = new BidirectionalOneToManyHandler<GeneratedExchangeStockLevel>(
	SslCoreConstants.TC.EXCHANGESTOCKLEVEL,
	false,
	"replacementEntry",
	null,
	false,
	true,
	CollectionType.SET
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(STOCKLEVEL, AttributeMode.INITIAL);
		tmp.put(QUANTITY, AttributeMode.INITIAL);
		tmp.put(REJECTEDQUANTITY, AttributeMode.INITIAL);
		tmp.put(REPLACEMENTENTRY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		REPLACEMENTENTRYHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.quantity</code> attribute.
	 * @return the quantity - Quantity of the Replacement Product
	 */
	public Long getQuantity(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, QUANTITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.quantity</code> attribute.
	 * @return the quantity - Quantity of the Replacement Product
	 */
	public Long getQuantity()
	{
		return getQuantity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.quantity</code> attribute. 
	 * @return the quantity - Quantity of the Replacement Product
	 */
	public long getQuantityAsPrimitive(final SessionContext ctx)
	{
		Long value = getQuantity( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.quantity</code> attribute. 
	 * @return the quantity - Quantity of the Replacement Product
	 */
	public long getQuantityAsPrimitive()
	{
		return getQuantityAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.quantity</code> attribute. 
	 * @param value the quantity - Quantity of the Replacement Product
	 */
	public void setQuantity(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, QUANTITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.quantity</code> attribute. 
	 * @param value the quantity - Quantity of the Replacement Product
	 */
	public void setQuantity(final Long value)
	{
		setQuantity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.quantity</code> attribute. 
	 * @param value the quantity - Quantity of the Replacement Product
	 */
	public void setQuantity(final SessionContext ctx, final long value)
	{
		setQuantity( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.quantity</code> attribute. 
	 * @param value the quantity - Quantity of the Replacement Product
	 */
	public void setQuantity(final long value)
	{
		setQuantity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.rejectedQuantity</code> attribute.
	 * @return the rejectedQuantity - Rejected Quantity of the Replacement Product
	 */
	public Long getRejectedQuantity(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, REJECTEDQUANTITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.rejectedQuantity</code> attribute.
	 * @return the rejectedQuantity - Rejected Quantity of the Replacement Product
	 */
	public Long getRejectedQuantity()
	{
		return getRejectedQuantity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.rejectedQuantity</code> attribute. 
	 * @return the rejectedQuantity - Rejected Quantity of the Replacement Product
	 */
	public long getRejectedQuantityAsPrimitive(final SessionContext ctx)
	{
		Long value = getRejectedQuantity( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.rejectedQuantity</code> attribute. 
	 * @return the rejectedQuantity - Rejected Quantity of the Replacement Product
	 */
	public long getRejectedQuantityAsPrimitive()
	{
		return getRejectedQuantityAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.rejectedQuantity</code> attribute. 
	 * @param value the rejectedQuantity - Rejected Quantity of the Replacement Product
	 */
	public void setRejectedQuantity(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, REJECTEDQUANTITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.rejectedQuantity</code> attribute. 
	 * @param value the rejectedQuantity - Rejected Quantity of the Replacement Product
	 */
	public void setRejectedQuantity(final Long value)
	{
		setRejectedQuantity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.rejectedQuantity</code> attribute. 
	 * @param value the rejectedQuantity - Rejected Quantity of the Replacement Product
	 */
	public void setRejectedQuantity(final SessionContext ctx, final long value)
	{
		setRejectedQuantity( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.rejectedQuantity</code> attribute. 
	 * @param value the rejectedQuantity - Rejected Quantity of the Replacement Product
	 */
	public void setRejectedQuantity(final long value)
	{
		setRejectedQuantity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.replacementEntry</code> attribute.
	 * @return the replacementEntry
	 */
	public ReplacementEntry getReplacementEntry(final SessionContext ctx)
	{
		return (ReplacementEntry)getProperty( ctx, REPLACEMENTENTRY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.replacementEntry</code> attribute.
	 * @return the replacementEntry
	 */
	public ReplacementEntry getReplacementEntry()
	{
		return getReplacementEntry( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.replacementEntry</code> attribute. 
	 * @param value the replacementEntry
	 */
	public void setReplacementEntry(final SessionContext ctx, final ReplacementEntry value)
	{
		REPLACEMENTENTRYHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.replacementEntry</code> attribute. 
	 * @param value the replacementEntry
	 */
	public void setReplacementEntry(final ReplacementEntry value)
	{
		setReplacementEntry( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.stockLevel</code> attribute.
	 * @return the stockLevel - Stock Level for the Replacement Product
	 */
	public StockLevel getStockLevel(final SessionContext ctx)
	{
		return (StockLevel)getProperty( ctx, STOCKLEVEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExchangeStockLevel.stockLevel</code> attribute.
	 * @return the stockLevel - Stock Level for the Replacement Product
	 */
	public StockLevel getStockLevel()
	{
		return getStockLevel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.stockLevel</code> attribute. 
	 * @param value the stockLevel - Stock Level for the Replacement Product
	 */
	public void setStockLevel(final SessionContext ctx, final StockLevel value)
	{
		setProperty(ctx, STOCKLEVEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExchangeStockLevel.stockLevel</code> attribute. 
	 * @param value the stockLevel - Stock Level for the Replacement Product
	 */
	public void setStockLevel(final StockLevel value)
	{
		setStockLevel( getSession().getSessionContext(), value );
	}
	
}
