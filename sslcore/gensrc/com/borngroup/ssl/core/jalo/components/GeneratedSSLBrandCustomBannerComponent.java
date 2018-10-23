/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SSLBrandButtonBannerComponent;
import com.borngroup.ssl.core.jalo.components.SSLBrandContentImageComponent;
import com.borngroup.ssl.core.jalo.components.SSLBrandProductCarouselComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLBrandCustomBannerComponent SSLBrandCustomBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBrandCustomBannerComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLBrandCustomBannerComponent.bannerComponent</code> attribute **/
	public static final String BANNERCOMPONENT = "bannerComponent";
	/** Qualifier of the <code>SSLBrandCustomBannerComponent.contentImageList</code> attribute **/
	public static final String CONTENTIMAGELIST = "contentImageList";
	/** Qualifier of the <code>SSLBrandCustomBannerComponent.carouselComponent</code> attribute **/
	public static final String CAROUSELCOMPONENT = "carouselComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BANNERCOMPONENT, AttributeMode.INITIAL);
		tmp.put(CONTENTIMAGELIST, AttributeMode.INITIAL);
		tmp.put(CAROUSELCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandCustomBannerComponent.bannerComponent</code> attribute.
	 * @return the bannerComponent - Ssl Banner Component
	 */
	public SSLBrandButtonBannerComponent getBannerComponent(final SessionContext ctx)
	{
		return (SSLBrandButtonBannerComponent)getProperty( ctx, BANNERCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandCustomBannerComponent.bannerComponent</code> attribute.
	 * @return the bannerComponent - Ssl Banner Component
	 */
	public SSLBrandButtonBannerComponent getBannerComponent()
	{
		return getBannerComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandCustomBannerComponent.bannerComponent</code> attribute. 
	 * @param value the bannerComponent - Ssl Banner Component
	 */
	public void setBannerComponent(final SessionContext ctx, final SSLBrandButtonBannerComponent value)
	{
		setProperty(ctx, BANNERCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandCustomBannerComponent.bannerComponent</code> attribute. 
	 * @param value the bannerComponent - Ssl Banner Component
	 */
	public void setBannerComponent(final SSLBrandButtonBannerComponent value)
	{
		setBannerComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandCustomBannerComponent.carouselComponent</code> attribute.
	 * @return the carouselComponent - Ssl Product Carousel Component
	 */
	public SSLBrandProductCarouselComponent getCarouselComponent(final SessionContext ctx)
	{
		return (SSLBrandProductCarouselComponent)getProperty( ctx, CAROUSELCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandCustomBannerComponent.carouselComponent</code> attribute.
	 * @return the carouselComponent - Ssl Product Carousel Component
	 */
	public SSLBrandProductCarouselComponent getCarouselComponent()
	{
		return getCarouselComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandCustomBannerComponent.carouselComponent</code> attribute. 
	 * @param value the carouselComponent - Ssl Product Carousel Component
	 */
	public void setCarouselComponent(final SessionContext ctx, final SSLBrandProductCarouselComponent value)
	{
		setProperty(ctx, CAROUSELCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandCustomBannerComponent.carouselComponent</code> attribute. 
	 * @param value the carouselComponent - Ssl Product Carousel Component
	 */
	public void setCarouselComponent(final SSLBrandProductCarouselComponent value)
	{
		setCarouselComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandCustomBannerComponent.contentImageList</code> attribute.
	 * @return the contentImageList - Collections of Banner Component
	 */
	public List<SSLBrandContentImageComponent> getContentImageList(final SessionContext ctx)
	{
		List<SSLBrandContentImageComponent> coll = (List<SSLBrandContentImageComponent>)getProperty( ctx, CONTENTIMAGELIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandCustomBannerComponent.contentImageList</code> attribute.
	 * @return the contentImageList - Collections of Banner Component
	 */
	public List<SSLBrandContentImageComponent> getContentImageList()
	{
		return getContentImageList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandCustomBannerComponent.contentImageList</code> attribute. 
	 * @param value the contentImageList - Collections of Banner Component
	 */
	public void setContentImageList(final SessionContext ctx, final List<SSLBrandContentImageComponent> value)
	{
		setProperty(ctx, CONTENTIMAGELIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandCustomBannerComponent.contentImageList</code> attribute. 
	 * @param value the contentImageList - Collections of Banner Component
	 */
	public void setContentImageList(final List<SSLBrandContentImageComponent> value)
	{
		setContentImageList( getSession().getSessionContext(), value );
	}
	
}
