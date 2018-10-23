/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLBannerWidgetComponent;
import com.borngroup.ssl.core.jalo.SSLHeadingWidgetComponent;
import com.borngroup.ssl.core.jalo.SSLMobileWidgetComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLBannerCarouselComponent SSLBannerCarouselComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBannerCarouselComponent extends SSLMobileWidgetComponent
{
	/** Qualifier of the <code>SSLBannerCarouselComponent.fullWidth</code> attribute **/
	public static final String FULLWIDTH = "fullWidth";
	/** Qualifier of the <code>SSLBannerCarouselComponent.widthPercentage</code> attribute **/
	public static final String WIDTHPERCENTAGE = "widthPercentage";
	/** Qualifier of the <code>SSLBannerCarouselComponent.screenTime</code> attribute **/
	public static final String SCREENTIME = "screenTime";
	/** Qualifier of the <code>SSLBannerCarouselComponent.bannerList</code> attribute **/
	public static final String BANNERLIST = "bannerList";
	/** Qualifier of the <code>SSLBannerCarouselComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SSLMobileWidgetComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(FULLWIDTH, AttributeMode.INITIAL);
		tmp.put(WIDTHPERCENTAGE, AttributeMode.INITIAL);
		tmp.put(SCREENTIME, AttributeMode.INITIAL);
		tmp.put(BANNERLIST, AttributeMode.INITIAL);
		tmp.put(HEADING, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.bannerList</code> attribute.
	 * @return the bannerList - Banner List
	 */
	public List<SSLBannerWidgetComponent> getBannerList(final SessionContext ctx)
	{
		List<SSLBannerWidgetComponent> coll = (List<SSLBannerWidgetComponent>)getProperty( ctx, BANNERLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.bannerList</code> attribute.
	 * @return the bannerList - Banner List
	 */
	public List<SSLBannerWidgetComponent> getBannerList()
	{
		return getBannerList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.bannerList</code> attribute. 
	 * @param value the bannerList - Banner List
	 */
	public void setBannerList(final SessionContext ctx, final List<SSLBannerWidgetComponent> value)
	{
		setProperty(ctx, BANNERLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.bannerList</code> attribute. 
	 * @param value the bannerList - Banner List
	 */
	public void setBannerList(final List<SSLBannerWidgetComponent> value)
	{
		setBannerList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.fullWidth</code> attribute.
	 * @return the fullWidth - To check if full width
	 */
	public Boolean isFullWidth(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, FULLWIDTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.fullWidth</code> attribute.
	 * @return the fullWidth - To check if full width
	 */
	public Boolean isFullWidth()
	{
		return isFullWidth( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.fullWidth</code> attribute. 
	 * @return the fullWidth - To check if full width
	 */
	public boolean isFullWidthAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isFullWidth( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.fullWidth</code> attribute. 
	 * @return the fullWidth - To check if full width
	 */
	public boolean isFullWidthAsPrimitive()
	{
		return isFullWidthAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.fullWidth</code> attribute. 
	 * @param value the fullWidth - To check if full width
	 */
	public void setFullWidth(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, FULLWIDTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.fullWidth</code> attribute. 
	 * @param value the fullWidth - To check if full width
	 */
	public void setFullWidth(final Boolean value)
	{
		setFullWidth( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.fullWidth</code> attribute. 
	 * @param value the fullWidth - To check if full width
	 */
	public void setFullWidth(final SessionContext ctx, final boolean value)
	{
		setFullWidth( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.fullWidth</code> attribute. 
	 * @param value the fullWidth - To check if full width
	 */
	public void setFullWidth(final boolean value)
	{
		setFullWidth( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.heading</code> attribute.
	 * @return the heading - Heading Component
	 */
	public SSLHeadingWidgetComponent getHeading(final SessionContext ctx)
	{
		return (SSLHeadingWidgetComponent)getProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.heading</code> attribute.
	 * @return the heading - Heading Component
	 */
	public SSLHeadingWidgetComponent getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.heading</code> attribute. 
	 * @param value the heading - Heading Component
	 */
	public void setHeading(final SessionContext ctx, final SSLHeadingWidgetComponent value)
	{
		setProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.heading</code> attribute. 
	 * @param value the heading - Heading Component
	 */
	public void setHeading(final SSLHeadingWidgetComponent value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.screenTime</code> attribute.
	 * @return the screenTime - Screen time
	 */
	public Integer getScreenTime(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, SCREENTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.screenTime</code> attribute.
	 * @return the screenTime - Screen time
	 */
	public Integer getScreenTime()
	{
		return getScreenTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.screenTime</code> attribute. 
	 * @return the screenTime - Screen time
	 */
	public int getScreenTimeAsPrimitive(final SessionContext ctx)
	{
		Integer value = getScreenTime( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.screenTime</code> attribute. 
	 * @return the screenTime - Screen time
	 */
	public int getScreenTimeAsPrimitive()
	{
		return getScreenTimeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.screenTime</code> attribute. 
	 * @param value the screenTime - Screen time
	 */
	public void setScreenTime(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, SCREENTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.screenTime</code> attribute. 
	 * @param value the screenTime - Screen time
	 */
	public void setScreenTime(final Integer value)
	{
		setScreenTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.screenTime</code> attribute. 
	 * @param value the screenTime - Screen time
	 */
	public void setScreenTime(final SessionContext ctx, final int value)
	{
		setScreenTime( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.screenTime</code> attribute. 
	 * @param value the screenTime - Screen time
	 */
	public void setScreenTime(final int value)
	{
		setScreenTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.widthPercentage</code> attribute.
	 * @return the widthPercentage - Width Percentage
	 */
	public Double getWidthPercentage(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, WIDTHPERCENTAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.widthPercentage</code> attribute.
	 * @return the widthPercentage - Width Percentage
	 */
	public Double getWidthPercentage()
	{
		return getWidthPercentage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.widthPercentage</code> attribute. 
	 * @return the widthPercentage - Width Percentage
	 */
	public double getWidthPercentageAsPrimitive(final SessionContext ctx)
	{
		Double value = getWidthPercentage( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerCarouselComponent.widthPercentage</code> attribute. 
	 * @return the widthPercentage - Width Percentage
	 */
	public double getWidthPercentageAsPrimitive()
	{
		return getWidthPercentageAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.widthPercentage</code> attribute. 
	 * @param value the widthPercentage - Width Percentage
	 */
	public void setWidthPercentage(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, WIDTHPERCENTAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.widthPercentage</code> attribute. 
	 * @param value the widthPercentage - Width Percentage
	 */
	public void setWidthPercentage(final Double value)
	{
		setWidthPercentage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.widthPercentage</code> attribute. 
	 * @param value the widthPercentage - Width Percentage
	 */
	public void setWidthPercentage(final SessionContext ctx, final double value)
	{
		setWidthPercentage( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerCarouselComponent.widthPercentage</code> attribute. 
	 * @param value the widthPercentage - Width Percentage
	 */
	public void setWidthPercentage(final double value)
	{
		setWidthPercentage( getSession().getSessionContext(), value );
	}
	
}
