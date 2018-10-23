/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SSLBrandBannerTitleComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLBrandBannerTitleComponentCollection SSLBrandBannerTitleComponentCollection}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBrandBannerTitleComponentCollection extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLBrandBannerTitleComponentCollection.brandBannerCollection</code> attribute **/
	public static final String BRANDBANNERCOLLECTION = "brandBannerCollection";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BRANDBANNERCOLLECTION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerTitleComponentCollection.brandBannerCollection</code> attribute.
	 * @return the brandBannerCollection
	 */
	public List<SSLBrandBannerTitleComponent> getBrandBannerCollection(final SessionContext ctx)
	{
		List<SSLBrandBannerTitleComponent> coll = (List<SSLBrandBannerTitleComponent>)getProperty( ctx, BRANDBANNERCOLLECTION);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerTitleComponentCollection.brandBannerCollection</code> attribute.
	 * @return the brandBannerCollection
	 */
	public List<SSLBrandBannerTitleComponent> getBrandBannerCollection()
	{
		return getBrandBannerCollection( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerTitleComponentCollection.brandBannerCollection</code> attribute. 
	 * @param value the brandBannerCollection
	 */
	public void setBrandBannerCollection(final SessionContext ctx, final List<SSLBrandBannerTitleComponent> value)
	{
		setProperty(ctx, BRANDBANNERCOLLECTION,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerTitleComponentCollection.brandBannerCollection</code> attribute. 
	 * @param value the brandBannerCollection
	 */
	public void setBrandBannerCollection(final List<SSLBrandBannerTitleComponent> value)
	{
		setBrandBannerCollection( getSession().getSessionContext(), value );
	}
	
}
