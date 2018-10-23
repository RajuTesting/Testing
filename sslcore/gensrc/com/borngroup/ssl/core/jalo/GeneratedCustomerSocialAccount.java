/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem CustomerSocialAccount}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCustomerSocialAccount extends GenericItem
{
	/** Qualifier of the <code>CustomerSocialAccount.socialAccountUid</code> attribute **/
	public static final String SOCIALACCOUNTUID = "socialAccountUid";
	/** Qualifier of the <code>CustomerSocialAccount.socialAccountEmailId</code> attribute **/
	public static final String SOCIALACCOUNTEMAILID = "socialAccountEmailId";
	/** Qualifier of the <code>CustomerSocialAccount.currentIDP</code> attribute **/
	public static final String CURRENTIDP = "currentIDP";
	/** Qualifier of the <code>CustomerSocialAccount.customer</code> attribute **/
	public static final String CUSTOMER = "customer";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n CUSTOMER's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedCustomerSocialAccount> CUSTOMERHANDLER = new BidirectionalOneToManyHandler<GeneratedCustomerSocialAccount>(
	SslCoreConstants.TC.CUSTOMERSOCIALACCOUNT,
	false,
	"customer",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(SOCIALACCOUNTUID, AttributeMode.INITIAL);
		tmp.put(SOCIALACCOUNTEMAILID, AttributeMode.INITIAL);
		tmp.put(CURRENTIDP, AttributeMode.INITIAL);
		tmp.put(CUSTOMER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		CUSTOMERHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerSocialAccount.currentIDP</code> attribute.
	 * @return the currentIDP - Account provider
	 */
	public String getCurrentIDP(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CURRENTIDP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerSocialAccount.currentIDP</code> attribute.
	 * @return the currentIDP - Account provider
	 */
	public String getCurrentIDP()
	{
		return getCurrentIDP( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerSocialAccount.currentIDP</code> attribute. 
	 * @param value the currentIDP - Account provider
	 */
	public void setCurrentIDP(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CURRENTIDP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerSocialAccount.currentIDP</code> attribute. 
	 * @param value the currentIDP - Account provider
	 */
	public void setCurrentIDP(final String value)
	{
		setCurrentIDP( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerSocialAccount.customer</code> attribute.
	 * @return the customer
	 */
	public Customer getCustomer(final SessionContext ctx)
	{
		return (Customer)getProperty( ctx, CUSTOMER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerSocialAccount.customer</code> attribute.
	 * @return the customer
	 */
	public Customer getCustomer()
	{
		return getCustomer( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerSocialAccount.customer</code> attribute. 
	 * @param value the customer
	 */
	public void setCustomer(final SessionContext ctx, final Customer value)
	{
		CUSTOMERHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerSocialAccount.customer</code> attribute. 
	 * @param value the customer
	 */
	public void setCustomer(final Customer value)
	{
		setCustomer( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerSocialAccount.socialAccountEmailId</code> attribute.
	 * @return the socialAccountEmailId - Social Account email id
	 */
	public String getSocialAccountEmailId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SOCIALACCOUNTEMAILID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerSocialAccount.socialAccountEmailId</code> attribute.
	 * @return the socialAccountEmailId - Social Account email id
	 */
	public String getSocialAccountEmailId()
	{
		return getSocialAccountEmailId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerSocialAccount.socialAccountEmailId</code> attribute. 
	 * @param value the socialAccountEmailId - Social Account email id
	 */
	public void setSocialAccountEmailId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SOCIALACCOUNTEMAILID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerSocialAccount.socialAccountEmailId</code> attribute. 
	 * @param value the socialAccountEmailId - Social Account email id
	 */
	public void setSocialAccountEmailId(final String value)
	{
		setSocialAccountEmailId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerSocialAccount.socialAccountUid</code> attribute.
	 * @return the socialAccountUid
	 */
	public String getSocialAccountUid(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SOCIALACCOUNTUID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerSocialAccount.socialAccountUid</code> attribute.
	 * @return the socialAccountUid
	 */
	public String getSocialAccountUid()
	{
		return getSocialAccountUid( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerSocialAccount.socialAccountUid</code> attribute. 
	 * @param value the socialAccountUid
	 */
	public void setSocialAccountUid(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SOCIALACCOUNTUID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerSocialAccount.socialAccountUid</code> attribute. 
	 * @param value the socialAccountUid
	 */
	public void setSocialAccountUid(final String value)
	{
		setSocialAccountUid( getSession().getSessionContext(), value );
	}
	
}
