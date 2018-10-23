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
import de.hybris.platform.voucher.jalo.Restriction;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SslBinRestriction SslBinRestriction}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslBinRestriction extends Restriction
{
	/** Qualifier of the <code>SslBinRestriction.bin</code> attribute **/
	public static final String BIN = "bin";
	/** Qualifier of the <code>SslBinRestriction.associatedBank</code> attribute **/
	public static final String ASSOCIATEDBANK = "associatedBank";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Restriction.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BIN, AttributeMode.INITIAL);
		tmp.put(ASSOCIATEDBANK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBinRestriction.associatedBank</code> attribute.
	 * @return the associatedBank - The upper limit of an order value for which the given
	 *                             voucher is valid.
	 */
	public String getAssociatedBank(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ASSOCIATEDBANK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBinRestriction.associatedBank</code> attribute.
	 * @return the associatedBank - The upper limit of an order value for which the given
	 *                             voucher is valid.
	 */
	public String getAssociatedBank()
	{
		return getAssociatedBank( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBinRestriction.associatedBank</code> attribute. 
	 * @param value the associatedBank - The upper limit of an order value for which the given
	 *                             voucher is valid.
	 */
	public void setAssociatedBank(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ASSOCIATEDBANK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBinRestriction.associatedBank</code> attribute. 
	 * @param value the associatedBank - The upper limit of an order value for which the given
	 *                             voucher is valid.
	 */
	public void setAssociatedBank(final String value)
	{
		setAssociatedBank( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBinRestriction.bin</code> attribute.
	 * @return the bin - The lower limit of an order value for which the given
	 *                             voucher is valid.
	 */
	public String getBin(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BIN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBinRestriction.bin</code> attribute.
	 * @return the bin - The lower limit of an order value for which the given
	 *                             voucher is valid.
	 */
	public String getBin()
	{
		return getBin( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBinRestriction.bin</code> attribute. 
	 * @param value the bin - The lower limit of an order value for which the given
	 *                             voucher is valid.
	 */
	public void setBin(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BIN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBinRestriction.bin</code> attribute. 
	 * @param value the bin - The lower limit of an order value for which the given
	 *                             voucher is valid.
	 */
	public void setBin(final String value)
	{
		setBin( getSession().getSessionContext(), value );
	}
	
}
