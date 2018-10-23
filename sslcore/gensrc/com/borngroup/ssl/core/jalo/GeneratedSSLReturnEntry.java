/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.returns.jalo.ReturnEntry;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLReturnEntry SSLReturnEntry}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLReturnEntry extends ReturnEntry
{
	/** Qualifier of the <code>SSLReturnEntry.returnEligible</code> attribute **/
	public static final String RETURNELIGIBLE = "returnEligible";
	/** Qualifier of the <code>SSLReturnEntry.returnReason</code> attribute **/
	public static final String RETURNREASON = "returnReason";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(ReturnEntry.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(RETURNELIGIBLE, AttributeMode.INITIAL);
		tmp.put(RETURNREASON, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLReturnEntry.returnEligible</code> attribute.
	 * @return the returnEligible - Check if return eligibility has been overridden or not
	 */
	public EnumerationValue getReturnEligible(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, RETURNELIGIBLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLReturnEntry.returnEligible</code> attribute.
	 * @return the returnEligible - Check if return eligibility has been overridden or not
	 */
	public EnumerationValue getReturnEligible()
	{
		return getReturnEligible( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLReturnEntry.returnEligible</code> attribute. 
	 * @param value the returnEligible - Check if return eligibility has been overridden or not
	 */
	public void setReturnEligible(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, RETURNELIGIBLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLReturnEntry.returnEligible</code> attribute. 
	 * @param value the returnEligible - Check if return eligibility has been overridden or not
	 */
	public void setReturnEligible(final EnumerationValue value)
	{
		setReturnEligible( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLReturnEntry.returnReason</code> attribute.
	 * @return the returnReason
	 */
	public EnumerationValue getReturnReason(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, RETURNREASON);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLReturnEntry.returnReason</code> attribute.
	 * @return the returnReason
	 */
	public EnumerationValue getReturnReason()
	{
		return getReturnReason( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLReturnEntry.returnReason</code> attribute. 
	 * @param value the returnReason
	 */
	public void setReturnReason(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, RETURNREASON,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLReturnEntry.returnReason</code> attribute. 
	 * @param value the returnReason
	 */
	public void setReturnReason(final EnumerationValue value)
	{
		setReturnReason( getSession().getSessionContext(), value );
	}
	
}
