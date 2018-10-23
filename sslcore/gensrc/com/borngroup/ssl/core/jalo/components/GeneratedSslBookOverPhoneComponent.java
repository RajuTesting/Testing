/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleResponsiveBannerComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslBookOverPhoneComponent SslBookOverPhoneComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslBookOverPhoneComponent extends SimpleResponsiveBannerComponent
{
	/** Qualifier of the <code>SslBookOverPhoneComponent.componentHeader</code> attribute **/
	public static final String COMPONENTHEADER = "componentHeader";
	/** Qualifier of the <code>SslBookOverPhoneComponent.phoneNumber</code> attribute **/
	public static final String PHONENUMBER = "phoneNumber";
	/** Qualifier of the <code>SslBookOverPhoneComponent.textParagraph</code> attribute **/
	public static final String TEXTPARAGRAPH = "textParagraph";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleResponsiveBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(COMPONENTHEADER, AttributeMode.INITIAL);
		tmp.put(PHONENUMBER, AttributeMode.INITIAL);
		tmp.put(TEXTPARAGRAPH, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBookOverPhoneComponent.componentHeader</code> attribute.
	 * @return the componentHeader
	 */
	public String getComponentHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COMPONENTHEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBookOverPhoneComponent.componentHeader</code> attribute.
	 * @return the componentHeader
	 */
	public String getComponentHeader()
	{
		return getComponentHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBookOverPhoneComponent.componentHeader</code> attribute. 
	 * @param value the componentHeader
	 */
	public void setComponentHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COMPONENTHEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBookOverPhoneComponent.componentHeader</code> attribute. 
	 * @param value the componentHeader
	 */
	public void setComponentHeader(final String value)
	{
		setComponentHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBookOverPhoneComponent.phoneNumber</code> attribute.
	 * @return the phoneNumber
	 */
	public String getPhoneNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PHONENUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBookOverPhoneComponent.phoneNumber</code> attribute.
	 * @return the phoneNumber
	 */
	public String getPhoneNumber()
	{
		return getPhoneNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBookOverPhoneComponent.phoneNumber</code> attribute. 
	 * @param value the phoneNumber
	 */
	public void setPhoneNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PHONENUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBookOverPhoneComponent.phoneNumber</code> attribute. 
	 * @param value the phoneNumber
	 */
	public void setPhoneNumber(final String value)
	{
		setPhoneNumber( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBookOverPhoneComponent.textParagraph</code> attribute.
	 * @return the textParagraph
	 */
	public String getTextParagraph(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXTPARAGRAPH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslBookOverPhoneComponent.textParagraph</code> attribute.
	 * @return the textParagraph
	 */
	public String getTextParagraph()
	{
		return getTextParagraph( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBookOverPhoneComponent.textParagraph</code> attribute. 
	 * @param value the textParagraph
	 */
	public void setTextParagraph(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXTPARAGRAPH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslBookOverPhoneComponent.textParagraph</code> attribute. 
	 * @param value the textParagraph
	 */
	public void setTextParagraph(final String value)
	{
		setTextParagraph( getSession().getSessionContext(), value );
	}
	
}
