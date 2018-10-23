/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLPDPProductInfoCollectionComponent SSLPDPProductInfoCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLPDPProductInfoCollectionComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLPDPProductInfoCollectionComponent.productInfo</code> attribute **/
	public static final String PRODUCTINFO = "productInfo";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(PRODUCTINFO, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPDPProductInfoCollectionComponent.productInfo</code> attribute.
	 * @return the productInfo
	 */
	public List<SimpleCMSComponent> getProductInfo(final SessionContext ctx)
	{
		List<SimpleCMSComponent> coll = (List<SimpleCMSComponent>)getProperty( ctx, PRODUCTINFO);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLPDPProductInfoCollectionComponent.productInfo</code> attribute.
	 * @return the productInfo
	 */
	public List<SimpleCMSComponent> getProductInfo()
	{
		return getProductInfo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPDPProductInfoCollectionComponent.productInfo</code> attribute. 
	 * @param value the productInfo
	 */
	public void setProductInfo(final SessionContext ctx, final List<SimpleCMSComponent> value)
	{
		setProperty(ctx, PRODUCTINFO,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLPDPProductInfoCollectionComponent.productInfo</code> attribute. 
	 * @param value the productInfo
	 */
	public void setProductInfo(final List<SimpleCMSComponent> value)
	{
		setProductInfo( getSession().getSessionContext(), value );
	}
	
}
