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
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLLuxuryStoreParallaxComponent SSLLuxuryStoreParallaxComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLLuxuryStoreParallaxComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLLuxuryStoreParallaxComponent.parallaxBanner</code> attribute **/
	public static final String PARALLAXBANNER = "parallaxBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(PARALLAXBANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreParallaxComponent.parallaxBanner</code> attribute.
	 * @return the parallaxBanner
	 */
	public Media getParallaxBanner(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, PARALLAXBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreParallaxComponent.parallaxBanner</code> attribute.
	 * @return the parallaxBanner
	 */
	public Media getParallaxBanner()
	{
		return getParallaxBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreParallaxComponent.parallaxBanner</code> attribute. 
	 * @param value the parallaxBanner
	 */
	public void setParallaxBanner(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, PARALLAXBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreParallaxComponent.parallaxBanner</code> attribute. 
	 * @param value the parallaxBanner
	 */
	public void setParallaxBanner(final Media value)
	{
		setParallaxBanner( getSession().getSessionContext(), value );
	}
	
}
