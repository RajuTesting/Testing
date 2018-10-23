/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.ProductSkuList ProductSkuList}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedProductSkuList extends GenericItem
{
	/** Qualifier of the <code>ProductSkuList.skuList</code> attribute **/
	public static final String SKULIST = "skuList";
	/** Qualifier of the <code>ProductSkuList.filterbyStock</code> attribute **/
	public static final String FILTERBYSTOCK = "filterbyStock";
	/** Qualifier of the <code>ProductSkuList.requestId</code> attribute **/
	public static final String REQUESTID = "requestId";
	/** Qualifier of the <code>ProductSkuList.label</code> attribute **/
	public static final String LABEL = "label";
	/** Qualifier of the <code>ProductSkuList.countFilter</code> attribute **/
	public static final String COUNTFILTER = "countFilter";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(SKULIST, AttributeMode.INITIAL);
		tmp.put(FILTERBYSTOCK, AttributeMode.INITIAL);
		tmp.put(REQUESTID, AttributeMode.INITIAL);
		tmp.put(LABEL, AttributeMode.INITIAL);
		tmp.put(COUNTFILTER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.countFilter</code> attribute.
	 * @return the countFilter - Filter count max recommendations.
	 */
	public Integer getCountFilter(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, COUNTFILTER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.countFilter</code> attribute.
	 * @return the countFilter - Filter count max recommendations.
	 */
	public Integer getCountFilter()
	{
		return getCountFilter( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.countFilter</code> attribute. 
	 * @return the countFilter - Filter count max recommendations.
	 */
	public int getCountFilterAsPrimitive(final SessionContext ctx)
	{
		Integer value = getCountFilter( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.countFilter</code> attribute. 
	 * @return the countFilter - Filter count max recommendations.
	 */
	public int getCountFilterAsPrimitive()
	{
		return getCountFilterAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.countFilter</code> attribute. 
	 * @param value the countFilter - Filter count max recommendations.
	 */
	public void setCountFilter(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, COUNTFILTER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.countFilter</code> attribute. 
	 * @param value the countFilter - Filter count max recommendations.
	 */
	public void setCountFilter(final Integer value)
	{
		setCountFilter( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.countFilter</code> attribute. 
	 * @param value the countFilter - Filter count max recommendations.
	 */
	public void setCountFilter(final SessionContext ctx, final int value)
	{
		setCountFilter( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.countFilter</code> attribute. 
	 * @param value the countFilter - Filter count max recommendations.
	 */
	public void setCountFilter(final int value)
	{
		setCountFilter( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.filterbyStock</code> attribute.
	 * @return the filterbyStock - Boolean to filter by stock
	 */
	public Boolean isFilterbyStock(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, FILTERBYSTOCK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.filterbyStock</code> attribute.
	 * @return the filterbyStock - Boolean to filter by stock
	 */
	public Boolean isFilterbyStock()
	{
		return isFilterbyStock( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.filterbyStock</code> attribute. 
	 * @return the filterbyStock - Boolean to filter by stock
	 */
	public boolean isFilterbyStockAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isFilterbyStock( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.filterbyStock</code> attribute. 
	 * @return the filterbyStock - Boolean to filter by stock
	 */
	public boolean isFilterbyStockAsPrimitive()
	{
		return isFilterbyStockAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.filterbyStock</code> attribute. 
	 * @param value the filterbyStock - Boolean to filter by stock
	 */
	public void setFilterbyStock(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, FILTERBYSTOCK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.filterbyStock</code> attribute. 
	 * @param value the filterbyStock - Boolean to filter by stock
	 */
	public void setFilterbyStock(final Boolean value)
	{
		setFilterbyStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.filterbyStock</code> attribute. 
	 * @param value the filterbyStock - Boolean to filter by stock
	 */
	public void setFilterbyStock(final SessionContext ctx, final boolean value)
	{
		setFilterbyStock( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.filterbyStock</code> attribute. 
	 * @param value the filterbyStock - Boolean to filter by stock
	 */
	public void setFilterbyStock(final boolean value)
	{
		setFilterbyStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.label</code> attribute.
	 * @return the label - Label for recommendations.
	 */
	public String getLabel(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LABEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.label</code> attribute.
	 * @return the label - Label for recommendations.
	 */
	public String getLabel()
	{
		return getLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.label</code> attribute. 
	 * @param value the label - Label for recommendations.
	 */
	public void setLabel(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.label</code> attribute. 
	 * @param value the label - Label for recommendations.
	 */
	public void setLabel(final String value)
	{
		setLabel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.requestId</code> attribute.
	 * @return the requestId - RequestId for recommendations.
	 */
	public String getRequestId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, REQUESTID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.requestId</code> attribute.
	 * @return the requestId - RequestId for recommendations.
	 */
	public String getRequestId()
	{
		return getRequestId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.requestId</code> attribute. 
	 * @param value the requestId - RequestId for recommendations.
	 */
	public void setRequestId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, REQUESTID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.requestId</code> attribute. 
	 * @param value the requestId - RequestId for recommendations.
	 */
	public void setRequestId(final String value)
	{
		setRequestId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.skuList</code> attribute.
	 * @return the skuList - Size Variant Product Skus to be shown
	 */
	public List<String> getSkuList(final SessionContext ctx)
	{
		List<String> coll = (List<String>)getProperty( ctx, SKULIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductSkuList.skuList</code> attribute.
	 * @return the skuList - Size Variant Product Skus to be shown
	 */
	public List<String> getSkuList()
	{
		return getSkuList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.skuList</code> attribute. 
	 * @param value the skuList - Size Variant Product Skus to be shown
	 */
	public void setSkuList(final SessionContext ctx, final List<String> value)
	{
		setProperty(ctx, SKULIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductSkuList.skuList</code> attribute. 
	 * @param value the skuList - Size Variant Product Skus to be shown
	 */
	public void setSkuList(final List<String> value)
	{
		setSkuList( getSession().getSessionContext(), value );
	}
	
}
