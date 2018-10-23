/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SslImageMapComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SlimBannerCarouselComponent SlimBannerCarouselComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSlimBannerCarouselComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SlimBannerCarouselComponent.outfitsBanner</code> attribute **/
	public static final String OUTFITSBANNER = "outfitsBanner";
	/** Qualifier of the <code>SlimBannerCarouselComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	/** Qualifier of the <code>SlimBannerCarouselComponent.viewPort</code> attribute **/
	public static final String VIEWPORT = "viewPort";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(OUTFITSBANNER, AttributeMode.INITIAL);
		tmp.put(DEFAULTBANNER, AttributeMode.INITIAL);
		tmp.put(VIEWPORT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SlimBannerCarouselComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public SslImageMapComponent getDefaultBanner(final SessionContext ctx)
	{
		return (SslImageMapComponent)getProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SlimBannerCarouselComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public SslImageMapComponent getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SlimBannerCarouselComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final SslImageMapComponent value)
	{
		setProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SlimBannerCarouselComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SslImageMapComponent value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SlimBannerCarouselComponent.outfitsBanner</code> attribute.
	 * @return the outfitsBanner
	 */
	public List<SslImageMapComponent> getOutfitsBanner(final SessionContext ctx)
	{
		List<SslImageMapComponent> coll = (List<SslImageMapComponent>)getProperty( ctx, OUTFITSBANNER);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SlimBannerCarouselComponent.outfitsBanner</code> attribute.
	 * @return the outfitsBanner
	 */
	public List<SslImageMapComponent> getOutfitsBanner()
	{
		return getOutfitsBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SlimBannerCarouselComponent.outfitsBanner</code> attribute. 
	 * @param value the outfitsBanner
	 */
	public void setOutfitsBanner(final SessionContext ctx, final List<SslImageMapComponent> value)
	{
		setProperty(ctx, OUTFITSBANNER,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SlimBannerCarouselComponent.outfitsBanner</code> attribute. 
	 * @param value the outfitsBanner
	 */
	public void setOutfitsBanner(final List<SslImageMapComponent> value)
	{
		setOutfitsBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SlimBannerCarouselComponent.viewPort</code> attribute.
	 * @return the viewPort - View Port on which the component has to be displayed
	 */
	public EnumerationValue getViewPort(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, VIEWPORT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SlimBannerCarouselComponent.viewPort</code> attribute.
	 * @return the viewPort - View Port on which the component has to be displayed
	 */
	public EnumerationValue getViewPort()
	{
		return getViewPort( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SlimBannerCarouselComponent.viewPort</code> attribute. 
	 * @param value the viewPort - View Port on which the component has to be displayed
	 */
	public void setViewPort(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, VIEWPORT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SlimBannerCarouselComponent.viewPort</code> attribute. 
	 * @param value the viewPort - View Port on which the component has to be displayed
	 */
	public void setViewPort(final EnumerationValue value)
	{
		setViewPort( getSession().getSessionContext(), value );
	}
	
}
