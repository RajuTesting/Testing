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
import de.hybris.platform.voucher.jalo.Restriction;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SslMediumTypeRestriction SslMediumTypeRestriction}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslMediumTypeRestriction extends Restriction
{
	/** Qualifier of the <code>SslMediumTypeRestriction.mediumType</code> attribute **/
	public static final String MEDIUMTYPE = "mediumType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Restriction.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(MEDIUMTYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslMediumTypeRestriction.mediumType</code> attribute.
	 * @return the mediumType - The medium for which restriction will be applied.
	 */
	public EnumerationValue getMediumType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, MEDIUMTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslMediumTypeRestriction.mediumType</code> attribute.
	 * @return the mediumType - The medium for which restriction will be applied.
	 */
	public EnumerationValue getMediumType()
	{
		return getMediumType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslMediumTypeRestriction.mediumType</code> attribute. 
	 * @param value the mediumType - The medium for which restriction will be applied.
	 */
	public void setMediumType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, MEDIUMTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslMediumTypeRestriction.mediumType</code> attribute. 
	 * @param value the mediumType - The medium for which restriction will be applied.
	 */
	public void setMediumType(final EnumerationValue value)
	{
		setMediumType( getSession().getSessionContext(), value );
	}
	
}
