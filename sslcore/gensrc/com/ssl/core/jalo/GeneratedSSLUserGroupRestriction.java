/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.security.PrincipalGroup;
import de.hybris.platform.voucher.jalo.Restriction;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Generated class for type {@link com.ssl.core.jalo.SSLUserGroupRestriction SSLUserGroupRestriction}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLUserGroupRestriction extends Restriction
{
	/** Qualifier of the <code>SSLUserGroupRestriction.userGroups</code> attribute **/
	public static final String USERGROUPS = "userGroups";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Restriction.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(USERGROUPS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserGroupRestriction.userGroups</code> attribute.
	 * @return the userGroups
	 */
	public Set<PrincipalGroup> getUserGroups(final SessionContext ctx)
	{
		Set<PrincipalGroup> coll = (Set<PrincipalGroup>)getProperty( ctx, USERGROUPS);
		return coll != null ? coll : Collections.EMPTY_SET;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserGroupRestriction.userGroups</code> attribute.
	 * @return the userGroups
	 */
	public Set<PrincipalGroup> getUserGroups()
	{
		return getUserGroups( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserGroupRestriction.userGroups</code> attribute. 
	 * @param value the userGroups
	 */
	public void setUserGroups(final SessionContext ctx, final Set<PrincipalGroup> value)
	{
		setProperty(ctx, USERGROUPS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserGroupRestriction.userGroups</code> attribute. 
	 * @param value the userGroups
	 */
	public void setUserGroups(final Set<PrincipalGroup> value)
	{
		setUserGroups( getSession().getSessionContext(), value );
	}
	
}
