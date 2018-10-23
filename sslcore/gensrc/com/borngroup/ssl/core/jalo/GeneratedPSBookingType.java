/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem PSBookingType}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPSBookingType extends GenericItem
{
	/** Qualifier of the <code>PSBookingType.bookingTypeId</code> attribute **/
	public static final String BOOKINGTYPEID = "bookingTypeId";
	/** Qualifier of the <code>PSBookingType.bookingTypeName</code> attribute **/
	public static final String BOOKINGTYPENAME = "bookingTypeName";
	/** Qualifier of the <code>PSBookingType.isBookingTypeActive</code> attribute **/
	public static final String ISBOOKINGTYPEACTIVE = "isBookingTypeActive";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(BOOKINGTYPEID, AttributeMode.INITIAL);
		tmp.put(BOOKINGTYPENAME, AttributeMode.INITIAL);
		tmp.put(ISBOOKINGTYPEACTIVE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSBookingType.bookingTypeId</code> attribute.
	 * @return the bookingTypeId
	 */
	public String getBookingTypeId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BOOKINGTYPEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSBookingType.bookingTypeId</code> attribute.
	 * @return the bookingTypeId
	 */
	public String getBookingTypeId()
	{
		return getBookingTypeId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSBookingType.bookingTypeId</code> attribute. 
	 * @param value the bookingTypeId
	 */
	public void setBookingTypeId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BOOKINGTYPEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSBookingType.bookingTypeId</code> attribute. 
	 * @param value the bookingTypeId
	 */
	public void setBookingTypeId(final String value)
	{
		setBookingTypeId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSBookingType.bookingTypeName</code> attribute.
	 * @return the bookingTypeName
	 */
	public String getBookingTypeName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BOOKINGTYPENAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSBookingType.bookingTypeName</code> attribute.
	 * @return the bookingTypeName
	 */
	public String getBookingTypeName()
	{
		return getBookingTypeName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSBookingType.bookingTypeName</code> attribute. 
	 * @param value the bookingTypeName
	 */
	public void setBookingTypeName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BOOKINGTYPENAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSBookingType.bookingTypeName</code> attribute. 
	 * @param value the bookingTypeName
	 */
	public void setBookingTypeName(final String value)
	{
		setBookingTypeName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSBookingType.isBookingTypeActive</code> attribute.
	 * @return the isBookingTypeActive
	 */
	public Boolean isIsBookingTypeActive(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ISBOOKINGTYPEACTIVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSBookingType.isBookingTypeActive</code> attribute.
	 * @return the isBookingTypeActive
	 */
	public Boolean isIsBookingTypeActive()
	{
		return isIsBookingTypeActive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSBookingType.isBookingTypeActive</code> attribute. 
	 * @return the isBookingTypeActive
	 */
	public boolean isIsBookingTypeActiveAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIsBookingTypeActive( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSBookingType.isBookingTypeActive</code> attribute. 
	 * @return the isBookingTypeActive
	 */
	public boolean isIsBookingTypeActiveAsPrimitive()
	{
		return isIsBookingTypeActiveAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSBookingType.isBookingTypeActive</code> attribute. 
	 * @param value the isBookingTypeActive
	 */
	public void setIsBookingTypeActive(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ISBOOKINGTYPEACTIVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSBookingType.isBookingTypeActive</code> attribute. 
	 * @param value the isBookingTypeActive
	 */
	public void setIsBookingTypeActive(final Boolean value)
	{
		setIsBookingTypeActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSBookingType.isBookingTypeActive</code> attribute. 
	 * @param value the isBookingTypeActive
	 */
	public void setIsBookingTypeActive(final SessionContext ctx, final boolean value)
	{
		setIsBookingTypeActive( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSBookingType.isBookingTypeActive</code> attribute. 
	 * @param value the isBookingTypeActive
	 */
	public void setIsBookingTypeActive(final boolean value)
	{
		setIsBookingTypeActive( getSession().getSessionContext(), value );
	}
	
}
