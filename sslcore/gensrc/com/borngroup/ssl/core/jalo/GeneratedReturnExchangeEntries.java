/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.DisableReturnEntries;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem ReturnExchangeEntries}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedReturnExchangeEntries extends GenericItem
{
	/** Qualifier of the <code>ReturnExchangeEntries.returnReason</code> attribute **/
	public static final String RETURNREASON = "returnReason";
	/** Qualifier of the <code>ReturnExchangeEntries.returnComments</code> attribute **/
	public static final String RETURNCOMMENTS = "returnComments";
	/** Qualifier of the <code>ReturnExchangeEntries.exchangeEntries</code> attribute **/
	public static final String EXCHANGEENTRIES = "exchangeEntries";
	/** Qualifier of the <code>ReturnExchangeEntries.exchangedColorEntries</code> attribute **/
	public static final String EXCHANGEDCOLORENTRIES = "exchangedColorEntries";
	/** Qualifier of the <code>ReturnExchangeEntries.exchangedStockEntries</code> attribute **/
	public static final String EXCHANGEDSTOCKENTRIES = "exchangedStockEntries";
	/** Qualifier of the <code>ReturnExchangeEntries.quantity</code> attribute **/
	public static final String QUANTITY = "quantity";
	/** Qualifier of the <code>ReturnExchangeEntries.consignmentCode</code> attribute **/
	public static final String CONSIGNMENTCODE = "consignmentCode";
	/** Qualifier of the <code>ReturnExchangeEntries.entryNumber</code> attribute **/
	public static final String ENTRYNUMBER = "entryNumber";
	/** Qualifier of the <code>ReturnExchangeEntries.disableEntriesList</code> attribute **/
	public static final String DISABLEENTRIESLIST = "disableEntriesList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(RETURNREASON, AttributeMode.INITIAL);
		tmp.put(RETURNCOMMENTS, AttributeMode.INITIAL);
		tmp.put(EXCHANGEENTRIES, AttributeMode.INITIAL);
		tmp.put(EXCHANGEDCOLORENTRIES, AttributeMode.INITIAL);
		tmp.put(EXCHANGEDSTOCKENTRIES, AttributeMode.INITIAL);
		tmp.put(QUANTITY, AttributeMode.INITIAL);
		tmp.put(CONSIGNMENTCODE, AttributeMode.INITIAL);
		tmp.put(ENTRYNUMBER, AttributeMode.INITIAL);
		tmp.put(DISABLEENTRIESLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.consignmentCode</code> attribute.
	 * @return the consignmentCode - Consignment Code
	 */
	public String getConsignmentCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CONSIGNMENTCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.consignmentCode</code> attribute.
	 * @return the consignmentCode - Consignment Code
	 */
	public String getConsignmentCode()
	{
		return getConsignmentCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.consignmentCode</code> attribute. 
	 * @param value the consignmentCode - Consignment Code
	 */
	public void setConsignmentCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CONSIGNMENTCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.consignmentCode</code> attribute. 
	 * @param value the consignmentCode - Consignment Code
	 */
	public void setConsignmentCode(final String value)
	{
		setConsignmentCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.disableEntriesList</code> attribute.
	 * @return the disableEntriesList - Disable Return Entries
	 */
	public List<DisableReturnEntries> getDisableEntriesList(final SessionContext ctx)
	{
		List<DisableReturnEntries> coll = (List<DisableReturnEntries>)getProperty( ctx, DISABLEENTRIESLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.disableEntriesList</code> attribute.
	 * @return the disableEntriesList - Disable Return Entries
	 */
	public List<DisableReturnEntries> getDisableEntriesList()
	{
		return getDisableEntriesList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.disableEntriesList</code> attribute. 
	 * @param value the disableEntriesList - Disable Return Entries
	 */
	public void setDisableEntriesList(final SessionContext ctx, final List<DisableReturnEntries> value)
	{
		setProperty(ctx, DISABLEENTRIESLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.disableEntriesList</code> attribute. 
	 * @param value the disableEntriesList - Disable Return Entries
	 */
	public void setDisableEntriesList(final List<DisableReturnEntries> value)
	{
		setDisableEntriesList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.entryNumber</code> attribute.
	 * @return the entryNumber - Entry Number
	 */
	public String getEntryNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ENTRYNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.entryNumber</code> attribute.
	 * @return the entryNumber - Entry Number
	 */
	public String getEntryNumber()
	{
		return getEntryNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.entryNumber</code> attribute. 
	 * @param value the entryNumber - Entry Number
	 */
	public void setEntryNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ENTRYNUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.entryNumber</code> attribute. 
	 * @param value the entryNumber - Entry Number
	 */
	public void setEntryNumber(final String value)
	{
		setEntryNumber( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.exchangedColorEntries</code> attribute.
	 * @return the exchangedColorEntries - Exchanged Color Entries
	 */
	public List<String> getExchangedColorEntries(final SessionContext ctx)
	{
		List<String> coll = (List<String>)getProperty( ctx, EXCHANGEDCOLORENTRIES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.exchangedColorEntries</code> attribute.
	 * @return the exchangedColorEntries - Exchanged Color Entries
	 */
	public List<String> getExchangedColorEntries()
	{
		return getExchangedColorEntries( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.exchangedColorEntries</code> attribute. 
	 * @param value the exchangedColorEntries - Exchanged Color Entries
	 */
	public void setExchangedColorEntries(final SessionContext ctx, final List<String> value)
	{
		setProperty(ctx, EXCHANGEDCOLORENTRIES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.exchangedColorEntries</code> attribute. 
	 * @param value the exchangedColorEntries - Exchanged Color Entries
	 */
	public void setExchangedColorEntries(final List<String> value)
	{
		setExchangedColorEntries( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.exchangedStockEntries</code> attribute.
	 * @return the exchangedStockEntries - Exchanged Stock Entries
	 */
	public List<String> getExchangedStockEntries(final SessionContext ctx)
	{
		List<String> coll = (List<String>)getProperty( ctx, EXCHANGEDSTOCKENTRIES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.exchangedStockEntries</code> attribute.
	 * @return the exchangedStockEntries - Exchanged Stock Entries
	 */
	public List<String> getExchangedStockEntries()
	{
		return getExchangedStockEntries( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.exchangedStockEntries</code> attribute. 
	 * @param value the exchangedStockEntries - Exchanged Stock Entries
	 */
	public void setExchangedStockEntries(final SessionContext ctx, final List<String> value)
	{
		setProperty(ctx, EXCHANGEDSTOCKENTRIES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.exchangedStockEntries</code> attribute. 
	 * @param value the exchangedStockEntries - Exchanged Stock Entries
	 */
	public void setExchangedStockEntries(final List<String> value)
	{
		setExchangedStockEntries( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.exchangeEntries</code> attribute.
	 * @return the exchangeEntries - Exchanged Prod Entries
	 */
	public List<String> getExchangeEntries(final SessionContext ctx)
	{
		List<String> coll = (List<String>)getProperty( ctx, EXCHANGEENTRIES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.exchangeEntries</code> attribute.
	 * @return the exchangeEntries - Exchanged Prod Entries
	 */
	public List<String> getExchangeEntries()
	{
		return getExchangeEntries( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.exchangeEntries</code> attribute. 
	 * @param value the exchangeEntries - Exchanged Prod Entries
	 */
	public void setExchangeEntries(final SessionContext ctx, final List<String> value)
	{
		setProperty(ctx, EXCHANGEENTRIES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.exchangeEntries</code> attribute. 
	 * @param value the exchangeEntries - Exchanged Prod Entries
	 */
	public void setExchangeEntries(final List<String> value)
	{
		setExchangeEntries( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.quantity</code> attribute.
	 * @return the quantity - Quantity
	 */
	public String getQuantity(final SessionContext ctx)
	{
		return (String)getProperty( ctx, QUANTITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.quantity</code> attribute.
	 * @return the quantity - Quantity
	 */
	public String getQuantity()
	{
		return getQuantity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.quantity</code> attribute. 
	 * @param value the quantity - Quantity
	 */
	public void setQuantity(final SessionContext ctx, final String value)
	{
		setProperty(ctx, QUANTITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.quantity</code> attribute. 
	 * @param value the quantity - Quantity
	 */
	public void setQuantity(final String value)
	{
		setQuantity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.returnComments</code> attribute.
	 * @return the returnComments - Return Comments
	 */
	public String getReturnComments(final SessionContext ctx)
	{
		return (String)getProperty( ctx, RETURNCOMMENTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.returnComments</code> attribute.
	 * @return the returnComments - Return Comments
	 */
	public String getReturnComments()
	{
		return getReturnComments( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.returnComments</code> attribute. 
	 * @param value the returnComments - Return Comments
	 */
	public void setReturnComments(final SessionContext ctx, final String value)
	{
		setProperty(ctx, RETURNCOMMENTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.returnComments</code> attribute. 
	 * @param value the returnComments - Return Comments
	 */
	public void setReturnComments(final String value)
	{
		setReturnComments( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.returnReason</code> attribute.
	 * @return the returnReason - Return Reason
	 */
	public String getReturnReason(final SessionContext ctx)
	{
		return (String)getProperty( ctx, RETURNREASON);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ReturnExchangeEntries.returnReason</code> attribute.
	 * @return the returnReason - Return Reason
	 */
	public String getReturnReason()
	{
		return getReturnReason( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.returnReason</code> attribute. 
	 * @param value the returnReason - Return Reason
	 */
	public void setReturnReason(final SessionContext ctx, final String value)
	{
		setProperty(ctx, RETURNREASON,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ReturnExchangeEntries.returnReason</code> attribute. 
	 * @param value the returnReason - Return Reason
	 */
	public void setReturnReason(final String value)
	{
		setReturnReason( getSession().getSessionContext(), value );
	}
	
}
