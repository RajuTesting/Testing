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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SslUserTypeRestriction SslUserTypeRestriction}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslUserTypeRestriction extends Restriction
{
	/** Qualifier of the <code>SslUserTypeRestriction.userType</code> attribute **/
	public static final String USERTYPE = "userType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Restriction.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(USERTYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslUserTypeRestriction.userType</code> attribute.
	 * @return the userType - The user type for which restriction will be applied.
	 */
	public EnumerationValue getUserType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, USERTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslUserTypeRestriction.userType</code> attribute.
	 * @return the userType - The user type for which restriction will be applied.
	 */
	public EnumerationValue getUserType()
	{
		return getUserType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslUserTypeRestriction.userType</code> attribute. 
	 * @param value the userType - The user type for which restriction will be applied.
	 */
	public void setUserType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, USERTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslUserTypeRestriction.userType</code> attribute. 
	 * @param value the userType - The user type for which restriction will be applied.
	 */
	public void setUserType(final EnumerationValue value)
	{
		setUserType( getSession().getSessionContext(), value );
	}
	
}
