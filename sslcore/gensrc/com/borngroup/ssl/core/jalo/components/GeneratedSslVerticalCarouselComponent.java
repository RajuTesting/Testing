/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SslSimpleBannerComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslVerticalCarouselComponent SslVerticalCarouselComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslVerticalCarouselComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SslVerticalCarouselComponent.sslSimpleBanner</code> attribute **/
	public static final String SSLSIMPLEBANNER = "sslSimpleBanner";
	/** Qualifier of the <code>SslVerticalCarouselComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SSLSIMPLEBANNER, AttributeMode.INITIAL);
		tmp.put(DEFAULTBANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVerticalCarouselComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public SslSimpleBannerComponent getDefaultBanner(final SessionContext ctx)
	{
		return (SslSimpleBannerComponent)getProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVerticalCarouselComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public SslSimpleBannerComponent getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVerticalCarouselComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final SslSimpleBannerComponent value)
	{
		setProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVerticalCarouselComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SslSimpleBannerComponent value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVerticalCarouselComponent.sslSimpleBanner</code> attribute.
	 * @return the sslSimpleBanner
	 */
	public List<SslSimpleBannerComponent> getSslSimpleBanner(final SessionContext ctx)
	{
		List<SslSimpleBannerComponent> coll = (List<SslSimpleBannerComponent>)getProperty( ctx, SSLSIMPLEBANNER);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVerticalCarouselComponent.sslSimpleBanner</code> attribute.
	 * @return the sslSimpleBanner
	 */
	public List<SslSimpleBannerComponent> getSslSimpleBanner()
	{
		return getSslSimpleBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVerticalCarouselComponent.sslSimpleBanner</code> attribute. 
	 * @param value the sslSimpleBanner
	 */
	public void setSslSimpleBanner(final SessionContext ctx, final List<SslSimpleBannerComponent> value)
	{
		setProperty(ctx, SSLSIMPLEBANNER,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVerticalCarouselComponent.sslSimpleBanner</code> attribute. 
	 * @param value the sslSimpleBanner
	 */
	public void setSslSimpleBanner(final List<SslSimpleBannerComponent> value)
	{
		setSslSimpleBanner( getSession().getSessionContext(), value );
	}
	
}
