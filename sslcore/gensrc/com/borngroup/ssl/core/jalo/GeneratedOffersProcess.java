/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.commerceservices.jalo.process.StoreFrontCustomerProcess;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.user.Customer;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.commerceservices.jalo.process.StoreFrontCustomerProcess OffersProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOffersProcess extends StoreFrontCustomerProcess
{
	/** Qualifier of the <code>OffersProcess.customers</code> attribute **/
	public static final String CUSTOMERS = "customers";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(StoreFrontCustomerProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CUSTOMERS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersProcess.customers</code> attribute.
	 * @return the customers - Attribute contains token that is used in this
	 *                             process.
	 */
	public List<Customer> getCustomers(final SessionContext ctx)
	{
		List<Customer> coll = (List<Customer>)getProperty( ctx, CUSTOMERS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OffersProcess.customers</code> attribute.
	 * @return the customers - Attribute contains token that is used in this
	 *                             process.
	 */
	public List<Customer> getCustomers()
	{
		return getCustomers( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersProcess.customers</code> attribute. 
	 * @param value the customers - Attribute contains token that is used in this
	 *                             process.
	 */
	public void setCustomers(final SessionContext ctx, final List<Customer> value)
	{
		setProperty(ctx, CUSTOMERS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OffersProcess.customers</code> attribute. 
	 * @param value the customers - Attribute contains token that is used in this
	 *                             process.
	 */
	public void setCustomers(final List<Customer> value)
	{
		setCustomers( getSession().getSessionContext(), value );
	}
	
}
