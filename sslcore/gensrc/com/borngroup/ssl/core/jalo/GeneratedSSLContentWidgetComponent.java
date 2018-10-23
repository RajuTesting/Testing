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
import com.borngroup.ssl.core.jalo.SSLTextWidgetComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLContentWidgetComponent SSLContentWidgetComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLContentWidgetComponent extends SSLMobileWidgetComponent
{
	/** Qualifier of the <code>SSLContentWidgetComponent.banner</code> attribute **/
	public static final String BANNER = "banner";
	/** Qualifier of the <code>SSLContentWidgetComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>SSLContentWidgetComponent.bannerList</code> attribute **/
	public static final String BANNERLIST = "bannerList";
	/** Qualifier of the <code>SSLContentWidgetComponent.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SSLMobileWidgetComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BANNER, AttributeMode.INITIAL);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(BANNERLIST, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLContentWidgetComponent.banner</code> attribute.
	 * @return the banner - Banner
	 */
	public SSLBannerWidgetComponent getBanner(final SessionContext ctx)
	{
		return (SSLBannerWidgetComponent)getProperty( ctx, BANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLContentWidgetComponent.banner</code> attribute.
	 * @return the banner - Banner
	 */
	public SSLBannerWidgetComponent getBanner()
	{
		return getBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLContentWidgetComponent.banner</code> attribute. 
	 * @param value the banner - Banner
	 */
	public void setBanner(final SessionContext ctx, final SSLBannerWidgetComponent value)
	{
		setProperty(ctx, BANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLContentWidgetComponent.banner</code> attribute. 
	 * @param value the banner - Banner
	 */
	public void setBanner(final SSLBannerWidgetComponent value)
	{
		setBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLContentWidgetComponent.bannerList</code> attribute.
	 * @return the bannerList - Banner List
	 */
	public List<SSLBannerWidgetComponent> getBannerList(final SessionContext ctx)
	{
		List<SSLBannerWidgetComponent> coll = (List<SSLBannerWidgetComponent>)getProperty( ctx, BANNERLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLContentWidgetComponent.bannerList</code> attribute.
	 * @return the bannerList - Banner List
	 */
	public List<SSLBannerWidgetComponent> getBannerList()
	{
		return getBannerList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLContentWidgetComponent.bannerList</code> attribute. 
	 * @param value the bannerList - Banner List
	 */
	public void setBannerList(final SessionContext ctx, final List<SSLBannerWidgetComponent> value)
	{
		setProperty(ctx, BANNERLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLContentWidgetComponent.bannerList</code> attribute. 
	 * @param value the bannerList - Banner List
	 */
	public void setBannerList(final List<SSLBannerWidgetComponent> value)
	{
		setBannerList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLContentWidgetComponent.description</code> attribute.
	 * @return the description - Description
	 */
	public SSLTextWidgetComponent getDescription(final SessionContext ctx)
	{
		return (SSLTextWidgetComponent)getProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLContentWidgetComponent.description</code> attribute.
	 * @return the description - Description
	 */
	public SSLTextWidgetComponent getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLContentWidgetComponent.description</code> attribute. 
	 * @param value the description - Description
	 */
	public void setDescription(final SessionContext ctx, final SSLTextWidgetComponent value)
	{
		setProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLContentWidgetComponent.description</code> attribute. 
	 * @param value the description - Description
	 */
	public void setDescription(final SSLTextWidgetComponent value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLContentWidgetComponent.heading</code> attribute.
	 * @return the heading - Heading Component
	 */
	public SSLHeadingWidgetComponent getHeading(final SessionContext ctx)
	{
		return (SSLHeadingWidgetComponent)getProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLContentWidgetComponent.heading</code> attribute.
	 * @return the heading - Heading Component
	 */
	public SSLHeadingWidgetComponent getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLContentWidgetComponent.heading</code> attribute. 
	 * @param value the heading - Heading Component
	 */
	public void setHeading(final SessionContext ctx, final SSLHeadingWidgetComponent value)
	{
		setProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLContentWidgetComponent.heading</code> attribute. 
	 * @param value the heading - Heading Component
	 */
	public void setHeading(final SSLHeadingWidgetComponent value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
}
