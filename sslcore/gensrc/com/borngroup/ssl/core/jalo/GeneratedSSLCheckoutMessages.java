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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SSLCheckoutMessages}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLCheckoutMessages extends GenericItem
{
	/** Qualifier of the <code>SSLCheckoutMessages.messageCode</code> attribute **/
	public static final String MESSAGECODE = "messageCode";
	/** Qualifier of the <code>SSLCheckoutMessages.messageValue</code> attribute **/
	public static final String MESSAGEVALUE = "messageValue";
	/** Qualifier of the <code>SSLCheckoutMessages.messageType</code> attribute **/
	public static final String MESSAGETYPE = "messageType";
	/** Qualifier of the <code>SSLCheckoutMessages.messageLevel</code> attribute **/
	public static final String MESSAGELEVEL = "messageLevel";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(MESSAGECODE, AttributeMode.INITIAL);
		tmp.put(MESSAGEVALUE, AttributeMode.INITIAL);
		tmp.put(MESSAGETYPE, AttributeMode.INITIAL);
		tmp.put(MESSAGELEVEL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCheckoutMessages.messageCode</code> attribute.
	 * @return the messageCode
	 */
	public String getMessageCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MESSAGECODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCheckoutMessages.messageCode</code> attribute.
	 * @return the messageCode
	 */
	public String getMessageCode()
	{
		return getMessageCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCheckoutMessages.messageCode</code> attribute. 
	 * @param value the messageCode
	 */
	public void setMessageCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MESSAGECODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCheckoutMessages.messageCode</code> attribute. 
	 * @param value the messageCode
	 */
	public void setMessageCode(final String value)
	{
		setMessageCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCheckoutMessages.messageLevel</code> attribute.
	 * @return the messageLevel
	 */
	public String getMessageLevel(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MESSAGELEVEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCheckoutMessages.messageLevel</code> attribute.
	 * @return the messageLevel
	 */
	public String getMessageLevel()
	{
		return getMessageLevel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCheckoutMessages.messageLevel</code> attribute. 
	 * @param value the messageLevel
	 */
	public void setMessageLevel(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MESSAGELEVEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCheckoutMessages.messageLevel</code> attribute. 
	 * @param value the messageLevel
	 */
	public void setMessageLevel(final String value)
	{
		setMessageLevel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCheckoutMessages.messageType</code> attribute.
	 * @return the messageType
	 */
	public String getMessageType(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MESSAGETYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCheckoutMessages.messageType</code> attribute.
	 * @return the messageType
	 */
	public String getMessageType()
	{
		return getMessageType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCheckoutMessages.messageType</code> attribute. 
	 * @param value the messageType
	 */
	public void setMessageType(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MESSAGETYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCheckoutMessages.messageType</code> attribute. 
	 * @param value the messageType
	 */
	public void setMessageType(final String value)
	{
		setMessageType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCheckoutMessages.messageValue</code> attribute.
	 * @return the messageValue
	 */
	public String getMessageValue(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MESSAGEVALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCheckoutMessages.messageValue</code> attribute.
	 * @return the messageValue
	 */
	public String getMessageValue()
	{
		return getMessageValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCheckoutMessages.messageValue</code> attribute. 
	 * @param value the messageValue
	 */
	public void setMessageValue(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MESSAGEVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCheckoutMessages.messageValue</code> attribute. 
	 * @param value the messageValue
	 */
	public void setMessageValue(final String value)
	{
		setMessageValue( getSession().getSessionContext(), value );
	}
	
}
