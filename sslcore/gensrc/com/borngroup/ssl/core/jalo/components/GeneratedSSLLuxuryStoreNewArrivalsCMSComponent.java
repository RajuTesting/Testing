/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLLuxuryStoreNewArrivalsCMSComponent SSLLuxuryStoreNewArrivalsCMSComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLLuxuryStoreNewArrivalsCMSComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.firstBanner</code> attribute **/
	public static final String FIRSTBANNER = "firstBanner";
	/** Qualifier of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.secondBanner</code> attribute **/
	public static final String SECONDBANNER = "secondBanner";
	/** Qualifier of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.tooltip</code> attribute **/
	public static final String TOOLTIP = "tooltip";
	/** Qualifier of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.bannerLink</code> attribute **/
	public static final String BANNERLINK = "bannerLink";
	/** Qualifier of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.title</code> attribute **/
	public static final String TITLE = "title";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(FIRSTBANNER, AttributeMode.INITIAL);
		tmp.put(SECONDBANNER, AttributeMode.INITIAL);
		tmp.put(TOOLTIP, AttributeMode.INITIAL);
		tmp.put(BANNERLINK, AttributeMode.INITIAL);
		tmp.put(TITLE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.bannerLink</code> attribute.
	 * @return the bannerLink - Link
	 */
	public CMSLinkComponent getBannerLink(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, BANNERLINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.bannerLink</code> attribute.
	 * @return the bannerLink - Link
	 */
	public CMSLinkComponent getBannerLink()
	{
		return getBannerLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.bannerLink</code> attribute. 
	 * @param value the bannerLink - Link
	 */
	public void setBannerLink(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, BANNERLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.bannerLink</code> attribute. 
	 * @param value the bannerLink - Link
	 */
	public void setBannerLink(final CMSLinkComponent value)
	{
		setBannerLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.firstBanner</code> attribute.
	 * @return the firstBanner - First banner
	 */
	public Media getFirstBanner(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, FIRSTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.firstBanner</code> attribute.
	 * @return the firstBanner - First banner
	 */
	public Media getFirstBanner()
	{
		return getFirstBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.firstBanner</code> attribute. 
	 * @param value the firstBanner - First banner
	 */
	public void setFirstBanner(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, FIRSTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.firstBanner</code> attribute. 
	 * @param value the firstBanner - First banner
	 */
	public void setFirstBanner(final Media value)
	{
		setFirstBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.secondBanner</code> attribute.
	 * @return the secondBanner - Second banner
	 */
	public Media getSecondBanner(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, SECONDBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.secondBanner</code> attribute.
	 * @return the secondBanner - Second banner
	 */
	public Media getSecondBanner()
	{
		return getSecondBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.secondBanner</code> attribute. 
	 * @param value the secondBanner - Second banner
	 */
	public void setSecondBanner(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, SECONDBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.secondBanner</code> attribute. 
	 * @param value the secondBanner - Second banner
	 */
	public void setSecondBanner(final Media value)
	{
		setSecondBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.title</code> attribute.
	 * @return the title - Title below each component
	 */
	public String getTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.title</code> attribute.
	 * @return the title - Title below each component
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.title</code> attribute. 
	 * @param value the title - Title below each component
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.title</code> attribute. 
	 * @param value the title - Title below each component
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.tooltip</code> attribute.
	 * @return the tooltip - Tooltip appearing on hover
	 */
	public String getTooltip(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TOOLTIP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.tooltip</code> attribute.
	 * @return the tooltip - Tooltip appearing on hover
	 */
	public String getTooltip()
	{
		return getTooltip( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.tooltip</code> attribute. 
	 * @param value the tooltip - Tooltip appearing on hover
	 */
	public void setTooltip(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TOOLTIP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCMSComponent.tooltip</code> attribute. 
	 * @param value the tooltip - Tooltip appearing on hover
	 */
	public void setTooltip(final String value)
	{
		setTooltip( getSession().getSessionContext(), value );
	}
	
}
