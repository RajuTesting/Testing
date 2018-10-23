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
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem InvoiceAnalyzer}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedInvoiceAnalyzer extends GenericItem
{
	/** Qualifier of the <code>InvoiceAnalyzer.invoiceNumber</code> attribute **/
	public static final String INVOICENUMBER = "invoiceNumber";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(INVOICENUMBER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InvoiceAnalyzer.invoiceNumber</code> attribute.
	 * @return the invoiceNumber - Invoice number
	 */
	public String getInvoiceNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, INVOICENUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InvoiceAnalyzer.invoiceNumber</code> attribute.
	 * @return the invoiceNumber - Invoice number
	 */
	public String getInvoiceNumber()
	{
		return getInvoiceNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InvoiceAnalyzer.invoiceNumber</code> attribute. 
	 * @param value the invoiceNumber - Invoice number
	 */
	public void setInvoiceNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, INVOICENUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InvoiceAnalyzer.invoiceNumber</code> attribute. 
	 * @param value the invoiceNumber - Invoice number
	 */
	public void setInvoiceNumber(final String value)
	{
		setInvoiceNumber( getSession().getSessionContext(), value );
	}
	
}
