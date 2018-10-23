/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SSLImageLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLImageRollOverLinkComponent SSLImageRollOverLinkComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLImageRollOverLinkComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLImageRollOverLinkComponent.sslImageLinkList</code> attribute **/
	public static final String SSLIMAGELINKLIST = "sslImageLinkList";
	/** Qualifier of the <code>SSLImageRollOverLinkComponent.defaultSSLImageLink</code> attribute **/
	public static final String DEFAULTSSLIMAGELINK = "defaultSSLImageLink";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SSLIMAGELINKLIST, AttributeMode.INITIAL);
		tmp.put(DEFAULTSSLIMAGELINK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverLinkComponent.defaultSSLImageLink</code> attribute.
	 * @return the defaultSSLImageLink
	 */
	public SSLImageLinkComponent getDefaultSSLImageLink(final SessionContext ctx)
	{
		return (SSLImageLinkComponent)getProperty( ctx, DEFAULTSSLIMAGELINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverLinkComponent.defaultSSLImageLink</code> attribute.
	 * @return the defaultSSLImageLink
	 */
	public SSLImageLinkComponent getDefaultSSLImageLink()
	{
		return getDefaultSSLImageLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverLinkComponent.defaultSSLImageLink</code> attribute. 
	 * @param value the defaultSSLImageLink
	 */
	public void setDefaultSSLImageLink(final SessionContext ctx, final SSLImageLinkComponent value)
	{
		setProperty(ctx, DEFAULTSSLIMAGELINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverLinkComponent.defaultSSLImageLink</code> attribute. 
	 * @param value the defaultSSLImageLink
	 */
	public void setDefaultSSLImageLink(final SSLImageLinkComponent value)
	{
		setDefaultSSLImageLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverLinkComponent.sslImageLinkList</code> attribute.
	 * @return the sslImageLinkList
	 */
	public List<SSLImageLinkComponent> getSslImageLinkList(final SessionContext ctx)
	{
		List<SSLImageLinkComponent> coll = (List<SSLImageLinkComponent>)getProperty( ctx, SSLIMAGELINKLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverLinkComponent.sslImageLinkList</code> attribute.
	 * @return the sslImageLinkList
	 */
	public List<SSLImageLinkComponent> getSslImageLinkList()
	{
		return getSslImageLinkList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverLinkComponent.sslImageLinkList</code> attribute. 
	 * @param value the sslImageLinkList
	 */
	public void setSslImageLinkList(final SessionContext ctx, final List<SSLImageLinkComponent> value)
	{
		setProperty(ctx, SSLIMAGELINKLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverLinkComponent.sslImageLinkList</code> attribute. 
	 * @param value the sslImageLinkList
	 */
	public void setSslImageLinkList(final List<SSLImageLinkComponent> value)
	{
		setSslImageLinkList( getSession().getSessionContext(), value );
	}
	
}
