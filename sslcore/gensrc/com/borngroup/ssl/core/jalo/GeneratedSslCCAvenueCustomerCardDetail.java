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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SslCCAvenueCustomerCardDetail}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslCCAvenueCustomerCardDetail extends GenericItem
{
	/** Qualifier of the <code>SslCCAvenueCustomerCardDetail.customerCardId</code> attribute **/
	public static final String CUSTOMERCARDID = "customerCardId";
	/** Qualifier of the <code>SslCCAvenueCustomerCardDetail.customerCardLabel</code> attribute **/
	public static final String CUSTOMERCARDLABEL = "customerCardLabel";
	/** Qualifier of the <code>SslCCAvenueCustomerCardDetail.isDefault</code> attribute **/
	public static final String ISDEFAULT = "isDefault";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CUSTOMERCARDID, AttributeMode.INITIAL);
		tmp.put(CUSTOMERCARDLABEL, AttributeMode.INITIAL);
		tmp.put(ISDEFAULT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCCAvenueCustomerCardDetail.customerCardId</code> attribute.
	 * @return the customerCardId
	 */
	public Integer getCustomerCardId(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, CUSTOMERCARDID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCCAvenueCustomerCardDetail.customerCardId</code> attribute.
	 * @return the customerCardId
	 */
	public Integer getCustomerCardId()
	{
		return getCustomerCardId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCCAvenueCustomerCardDetail.customerCardId</code> attribute. 
	 * @return the customerCardId
	 */
	public int getCustomerCardIdAsPrimitive(final SessionContext ctx)
	{
		Integer value = getCustomerCardId( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCCAvenueCustomerCardDetail.customerCardId</code> attribute. 
	 * @return the customerCardId
	 */
	public int getCustomerCardIdAsPrimitive()
	{
		return getCustomerCardIdAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCCAvenueCustomerCardDetail.customerCardId</code> attribute. 
	 * @param value the customerCardId
	 */
	public void setCustomerCardId(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, CUSTOMERCARDID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCCAvenueCustomerCardDetail.customerCardId</code> attribute. 
	 * @param value the customerCardId
	 */
	public void setCustomerCardId(final Integer value)
	{
		setCustomerCardId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCCAvenueCustomerCardDetail.customerCardId</code> attribute. 
	 * @param value the customerCardId
	 */
	public void setCustomerCardId(final SessionContext ctx, final int value)
	{
		setCustomerCardId( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCCAvenueCustomerCardDetail.customerCardId</code> attribute. 
	 * @param value the customerCardId
	 */
	public void setCustomerCardId(final int value)
	{
		setCustomerCardId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCCAvenueCustomerCardDetail.customerCardLabel</code> attribute.
	 * @return the customerCardLabel
	 */
	public String getCustomerCardLabel(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CUSTOMERCARDLABEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCCAvenueCustomerCardDetail.customerCardLabel</code> attribute.
	 * @return the customerCardLabel
	 */
	public String getCustomerCardLabel()
	{
		return getCustomerCardLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCCAvenueCustomerCardDetail.customerCardLabel</code> attribute. 
	 * @param value the customerCardLabel
	 */
	public void setCustomerCardLabel(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CUSTOMERCARDLABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCCAvenueCustomerCardDetail.customerCardLabel</code> attribute. 
	 * @param value the customerCardLabel
	 */
	public void setCustomerCardLabel(final String value)
	{
		setCustomerCardLabel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCCAvenueCustomerCardDetail.isDefault</code> attribute.
	 * @return the isDefault
	 */
	public Boolean isIsDefault(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ISDEFAULT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCCAvenueCustomerCardDetail.isDefault</code> attribute.
	 * @return the isDefault
	 */
	public Boolean isIsDefault()
	{
		return isIsDefault( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCCAvenueCustomerCardDetail.isDefault</code> attribute. 
	 * @return the isDefault
	 */
	public boolean isIsDefaultAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIsDefault( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCCAvenueCustomerCardDetail.isDefault</code> attribute. 
	 * @return the isDefault
	 */
	public boolean isIsDefaultAsPrimitive()
	{
		return isIsDefaultAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCCAvenueCustomerCardDetail.isDefault</code> attribute. 
	 * @param value the isDefault
	 */
	public void setIsDefault(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ISDEFAULT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCCAvenueCustomerCardDetail.isDefault</code> attribute. 
	 * @param value the isDefault
	 */
	public void setIsDefault(final Boolean value)
	{
		setIsDefault( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCCAvenueCustomerCardDetail.isDefault</code> attribute. 
	 * @param value the isDefault
	 */
	public void setIsDefault(final SessionContext ctx, final boolean value)
	{
		setIsDefault( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCCAvenueCustomerCardDetail.isDefault</code> attribute. 
	 * @param value the isDefault
	 */
	public void setIsDefault(final boolean value)
	{
		setIsDefault( getSession().getSessionContext(), value );
	}
	
}
