/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.user.UserGroup;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.ssl.core.jalo.UserDomains UserDomains}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedUserDomains extends GenericItem
{
	/** Qualifier of the <code>UserDomains.domainName</code> attribute **/
	public static final String DOMAINNAME = "domainName";
	/** Qualifier of the <code>UserDomains.users</code> attribute **/
	public static final String USERS = "users";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(DOMAINNAME, AttributeMode.INITIAL);
		tmp.put(USERS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserDomains.domainName</code> attribute.
	 * @return the domainName
	 */
	public String getDomainName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DOMAINNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserDomains.domainName</code> attribute.
	 * @return the domainName
	 */
	public String getDomainName()
	{
		return getDomainName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserDomains.domainName</code> attribute. 
	 * @param value the domainName
	 */
	public void setDomainName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DOMAINNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserDomains.domainName</code> attribute. 
	 * @param value the domainName
	 */
	public void setDomainName(final String value)
	{
		setDomainName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserDomains.users</code> attribute.
	 * @return the users
	 */
	public List<UserGroup> getUsers(final SessionContext ctx)
	{
		List<UserGroup> coll = (List<UserGroup>)getProperty( ctx, USERS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserDomains.users</code> attribute.
	 * @return the users
	 */
	public List<UserGroup> getUsers()
	{
		return getUsers( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserDomains.users</code> attribute. 
	 * @param value the users
	 */
	public void setUsers(final SessionContext ctx, final List<UserGroup> value)
	{
		setProperty(ctx, USERS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserDomains.users</code> attribute. 
	 * @param value the users
	 */
	public void setUsers(final List<UserGroup> value)
	{
		setUsers( getSession().getSessionContext(), value );
	}
	
}
