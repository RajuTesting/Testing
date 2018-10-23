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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.HomeCouponComponent HomeCouponComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedHomeCouponComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>HomeCouponComponent.couponBanners</code> attribute **/
	public static final String COUPONBANNERS = "couponBanners";
	/** Qualifier of the <code>HomeCouponComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	/** Qualifier of the <code>HomeCouponComponent.slimOnDesktop</code> attribute **/
	public static final String SLIMONDESKTOP = "slimOnDesktop";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(COUPONBANNERS, AttributeMode.INITIAL);
		tmp.put(DEFAULTBANNER, AttributeMode.INITIAL);
		tmp.put(SLIMONDESKTOP, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HomeCouponComponent.couponBanners</code> attribute.
	 * @return the couponBanners
	 */
	public List<SslImageMapComponent> getCouponBanners(final SessionContext ctx)
	{
		List<SslImageMapComponent> coll = (List<SslImageMapComponent>)getProperty( ctx, COUPONBANNERS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HomeCouponComponent.couponBanners</code> attribute.
	 * @return the couponBanners
	 */
	public List<SslImageMapComponent> getCouponBanners()
	{
		return getCouponBanners( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HomeCouponComponent.couponBanners</code> attribute. 
	 * @param value the couponBanners
	 */
	public void setCouponBanners(final SessionContext ctx, final List<SslImageMapComponent> value)
	{
		setProperty(ctx, COUPONBANNERS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HomeCouponComponent.couponBanners</code> attribute. 
	 * @param value the couponBanners
	 */
	public void setCouponBanners(final List<SslImageMapComponent> value)
	{
		setCouponBanners( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HomeCouponComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public SslImageMapComponent getDefaultBanner(final SessionContext ctx)
	{
		return (SslImageMapComponent)getProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HomeCouponComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public SslImageMapComponent getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HomeCouponComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final SslImageMapComponent value)
	{
		setProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HomeCouponComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SslImageMapComponent value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HomeCouponComponent.slimOnDesktop</code> attribute.
	 * @return the slimOnDesktop
	 */
	public Boolean isSlimOnDesktop(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, SLIMONDESKTOP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HomeCouponComponent.slimOnDesktop</code> attribute.
	 * @return the slimOnDesktop
	 */
	public Boolean isSlimOnDesktop()
	{
		return isSlimOnDesktop( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HomeCouponComponent.slimOnDesktop</code> attribute. 
	 * @return the slimOnDesktop
	 */
	public boolean isSlimOnDesktopAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isSlimOnDesktop( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HomeCouponComponent.slimOnDesktop</code> attribute. 
	 * @return the slimOnDesktop
	 */
	public boolean isSlimOnDesktopAsPrimitive()
	{
		return isSlimOnDesktopAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HomeCouponComponent.slimOnDesktop</code> attribute. 
	 * @param value the slimOnDesktop
	 */
	public void setSlimOnDesktop(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, SLIMONDESKTOP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HomeCouponComponent.slimOnDesktop</code> attribute. 
	 * @param value the slimOnDesktop
	 */
	public void setSlimOnDesktop(final Boolean value)
	{
		setSlimOnDesktop( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HomeCouponComponent.slimOnDesktop</code> attribute. 
	 * @param value the slimOnDesktop
	 */
	public void setSlimOnDesktop(final SessionContext ctx, final boolean value)
	{
		setSlimOnDesktop( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HomeCouponComponent.slimOnDesktop</code> attribute. 
	 * @param value the slimOnDesktop
	 */
	public void setSlimOnDesktop(final boolean value)
	{
		setSlimOnDesktop( getSession().getSessionContext(), value );
	}
	
}
