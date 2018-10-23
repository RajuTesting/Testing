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
import de.hybris.platform.voucher.jalo.Restriction;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.ssl.core.jalo.SSLUserDomainRestriction SSLUserDomainRestriction}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLUserDomainRestriction extends Restriction
{
	/** Qualifier of the <code>SSLUserDomainRestriction.verifiedEmail</code> attribute **/
	public static final String VERIFIEDEMAIL = "verifiedEmail";
	/** Qualifier of the <code>SSLUserDomainRestriction.domainsList</code> attribute **/
	public static final String DOMAINSLIST = "domainsList";
	/** Qualifier of the <code>SSLUserDomainRestriction.originalPrice</code> attribute **/
	public static final String ORIGINALPRICE = "originalPrice";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Restriction.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(VERIFIEDEMAIL, AttributeMode.INITIAL);
		tmp.put(DOMAINSLIST, AttributeMode.INITIAL);
		tmp.put(ORIGINALPRICE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserDomainRestriction.domainsList</code> attribute.
	 * @return the domainsList
	 */
	public Collection<String> getDomainsList(final SessionContext ctx)
	{
		Collection<String> coll = (Collection<String>)getProperty( ctx, DOMAINSLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserDomainRestriction.domainsList</code> attribute.
	 * @return the domainsList
	 */
	public Collection<String> getDomainsList()
	{
		return getDomainsList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserDomainRestriction.domainsList</code> attribute. 
	 * @param value the domainsList
	 */
	public void setDomainsList(final SessionContext ctx, final Collection<String> value)
	{
		setProperty(ctx, DOMAINSLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserDomainRestriction.domainsList</code> attribute. 
	 * @param value the domainsList
	 */
	public void setDomainsList(final Collection<String> value)
	{
		setDomainsList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserDomainRestriction.originalPrice</code> attribute.
	 * @return the originalPrice
	 */
	public Boolean isOriginalPrice(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ORIGINALPRICE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserDomainRestriction.originalPrice</code> attribute.
	 * @return the originalPrice
	 */
	public Boolean isOriginalPrice()
	{
		return isOriginalPrice( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserDomainRestriction.originalPrice</code> attribute. 
	 * @return the originalPrice
	 */
	public boolean isOriginalPriceAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isOriginalPrice( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserDomainRestriction.originalPrice</code> attribute. 
	 * @return the originalPrice
	 */
	public boolean isOriginalPriceAsPrimitive()
	{
		return isOriginalPriceAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserDomainRestriction.originalPrice</code> attribute. 
	 * @param value the originalPrice
	 */
	public void setOriginalPrice(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ORIGINALPRICE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserDomainRestriction.originalPrice</code> attribute. 
	 * @param value the originalPrice
	 */
	public void setOriginalPrice(final Boolean value)
	{
		setOriginalPrice( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserDomainRestriction.originalPrice</code> attribute. 
	 * @param value the originalPrice
	 */
	public void setOriginalPrice(final SessionContext ctx, final boolean value)
	{
		setOriginalPrice( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserDomainRestriction.originalPrice</code> attribute. 
	 * @param value the originalPrice
	 */
	public void setOriginalPrice(final boolean value)
	{
		setOriginalPrice( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserDomainRestriction.verifiedEmail</code> attribute.
	 * @return the verifiedEmail
	 */
	public Boolean isVerifiedEmail(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, VERIFIEDEMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserDomainRestriction.verifiedEmail</code> attribute.
	 * @return the verifiedEmail
	 */
	public Boolean isVerifiedEmail()
	{
		return isVerifiedEmail( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserDomainRestriction.verifiedEmail</code> attribute. 
	 * @return the verifiedEmail
	 */
	public boolean isVerifiedEmailAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isVerifiedEmail( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLUserDomainRestriction.verifiedEmail</code> attribute. 
	 * @return the verifiedEmail
	 */
	public boolean isVerifiedEmailAsPrimitive()
	{
		return isVerifiedEmailAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserDomainRestriction.verifiedEmail</code> attribute. 
	 * @param value the verifiedEmail
	 */
	public void setVerifiedEmail(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, VERIFIEDEMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserDomainRestriction.verifiedEmail</code> attribute. 
	 * @param value the verifiedEmail
	 */
	public void setVerifiedEmail(final Boolean value)
	{
		setVerifiedEmail( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserDomainRestriction.verifiedEmail</code> attribute. 
	 * @param value the verifiedEmail
	 */
	public void setVerifiedEmail(final SessionContext ctx, final boolean value)
	{
		setVerifiedEmail( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLUserDomainRestriction.verifiedEmail</code> attribute. 
	 * @param value the verifiedEmail
	 */
	public void setVerifiedEmail(final boolean value)
	{
		setVerifiedEmail( getSession().getSessionContext(), value );
	}
	
}
