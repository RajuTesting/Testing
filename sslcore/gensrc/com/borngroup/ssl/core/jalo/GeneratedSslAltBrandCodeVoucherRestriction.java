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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SslAltBrandCodeVoucherRestriction AltBrandCodeVoucherRestriction}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslAltBrandCodeVoucherRestriction extends Restriction
{
	/** Qualifier of the <code>AltBrandCodeVoucherRestriction.include</code> attribute **/
	public static final String INCLUDE = "include";
	/** Qualifier of the <code>AltBrandCodeVoucherRestriction.altBrandCodeList</code> attribute **/
	public static final String ALTBRANDCODELIST = "altBrandCodeList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Restriction.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(INCLUDE, AttributeMode.INITIAL);
		tmp.put(ALTBRANDCODELIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AltBrandCodeVoucherRestriction.altBrandCodeList</code> attribute.
	 * @return the altBrandCodeList - Specifies the associated Alt Brand Description.
	 */
	public Collection<String> getAltBrandCodeList(final SessionContext ctx)
	{
		Collection<String> coll = (Collection<String>)getProperty( ctx, ALTBRANDCODELIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AltBrandCodeVoucherRestriction.altBrandCodeList</code> attribute.
	 * @return the altBrandCodeList - Specifies the associated Alt Brand Description.
	 */
	public Collection<String> getAltBrandCodeList()
	{
		return getAltBrandCodeList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AltBrandCodeVoucherRestriction.altBrandCodeList</code> attribute. 
	 * @param value the altBrandCodeList - Specifies the associated Alt Brand Description.
	 */
	public void setAltBrandCodeList(final SessionContext ctx, final Collection<String> value)
	{
		setProperty(ctx, ALTBRANDCODELIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AltBrandCodeVoucherRestriction.altBrandCodeList</code> attribute. 
	 * @param value the altBrandCodeList - Specifies the associated Alt Brand Description.
	 */
	public void setAltBrandCodeList(final Collection<String> value)
	{
		setAltBrandCodeList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AltBrandCodeVoucherRestriction.include</code> attribute.
	 * @return the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public Boolean isInclude(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, INCLUDE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AltBrandCodeVoucherRestriction.include</code> attribute.
	 * @return the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public Boolean isInclude()
	{
		return isInclude( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AltBrandCodeVoucherRestriction.include</code> attribute. 
	 * @return the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public boolean isIncludeAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isInclude( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AltBrandCodeVoucherRestriction.include</code> attribute. 
	 * @return the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public boolean isIncludeAsPrimitive()
	{
		return isIncludeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AltBrandCodeVoucherRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public void setInclude(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, INCLUDE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AltBrandCodeVoucherRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public void setInclude(final Boolean value)
	{
		setInclude( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AltBrandCodeVoucherRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public void setInclude(final SessionContext ctx, final boolean value)
	{
		setInclude( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AltBrandCodeVoucherRestriction.include</code> attribute. 
	 * @param value the include - Specifies if this restriction is a positive (true) or
	 *                             negative (false) one.
	 */
	public void setInclude(final boolean value)
	{
		setInclude( getSession().getSessionContext(), value );
	}
	
}
