/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLBrandBannerTitleComponent SSLBrandBannerTitleComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBrandBannerTitleComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLBrandBannerTitleComponent.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>SSLBrandBannerTitleComponent.bannerDesktop</code> attribute **/
	public static final String BANNERDESKTOP = "bannerDesktop";
	/** Qualifier of the <code>SSLBrandBannerTitleComponent.bannerMobile</code> attribute **/
	public static final String BANNERMOBILE = "bannerMobile";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TITLE, AttributeMode.INITIAL);
		tmp.put(BANNERDESKTOP, AttributeMode.INITIAL);
		tmp.put(BANNERMOBILE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerTitleComponent.bannerDesktop</code> attribute.
	 * @return the bannerDesktop
	 */
	public Media getBannerDesktop(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, BANNERDESKTOP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerTitleComponent.bannerDesktop</code> attribute.
	 * @return the bannerDesktop
	 */
	public Media getBannerDesktop()
	{
		return getBannerDesktop( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerTitleComponent.bannerDesktop</code> attribute. 
	 * @param value the bannerDesktop
	 */
	public void setBannerDesktop(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, BANNERDESKTOP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerTitleComponent.bannerDesktop</code> attribute. 
	 * @param value the bannerDesktop
	 */
	public void setBannerDesktop(final Media value)
	{
		setBannerDesktop( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerTitleComponent.bannerMobile</code> attribute.
	 * @return the bannerMobile
	 */
	public Media getBannerMobile(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, BANNERMOBILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerTitleComponent.bannerMobile</code> attribute.
	 * @return the bannerMobile
	 */
	public Media getBannerMobile()
	{
		return getBannerMobile( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerTitleComponent.bannerMobile</code> attribute. 
	 * @param value the bannerMobile
	 */
	public void setBannerMobile(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, BANNERMOBILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerTitleComponent.bannerMobile</code> attribute. 
	 * @param value the bannerMobile
	 */
	public void setBannerMobile(final Media value)
	{
		setBannerMobile( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerTitleComponent.title</code> attribute.
	 * @return the title
	 */
	public String getTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandBannerTitleComponent.title</code> attribute.
	 * @return the title
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerTitleComponent.title</code> attribute. 
	 * @param value the title
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandBannerTitleComponent.title</code> attribute. 
	 * @param value the title
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
}
