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
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLLinkGridWidgetComponent SSLLinkGridWidgetComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLLinkGridWidgetComponent extends SSLMobileWidgetComponent
{
	/** Qualifier of the <code>SSLLinkGridWidgetComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>SSLLinkGridWidgetComponent.banner</code> attribute **/
	public static final String BANNER = "banner";
	/** Qualifier of the <code>SSLLinkGridWidgetComponent.linkGrid</code> attribute **/
	public static final String LINKGRID = "linkGrid";
	/** Qualifier of the <code>SSLLinkGridWidgetComponent.gridType</code> attribute **/
	public static final String GRIDTYPE = "gridType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SSLMobileWidgetComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(BANNER, AttributeMode.INITIAL);
		tmp.put(LINKGRID, AttributeMode.INITIAL);
		tmp.put(GRIDTYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLinkGridWidgetComponent.banner</code> attribute.
	 * @return the banner - Banner
	 */
	public SSLBannerWidgetComponent getBanner(final SessionContext ctx)
	{
		return (SSLBannerWidgetComponent)getProperty( ctx, BANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLinkGridWidgetComponent.banner</code> attribute.
	 * @return the banner - Banner
	 */
	public SSLBannerWidgetComponent getBanner()
	{
		return getBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLinkGridWidgetComponent.banner</code> attribute. 
	 * @param value the banner - Banner
	 */
	public void setBanner(final SessionContext ctx, final SSLBannerWidgetComponent value)
	{
		setProperty(ctx, BANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLinkGridWidgetComponent.banner</code> attribute. 
	 * @param value the banner - Banner
	 */
	public void setBanner(final SSLBannerWidgetComponent value)
	{
		setBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLinkGridWidgetComponent.gridType</code> attribute.
	 * @return the gridType - Link Grid Type
	 */
	public EnumerationValue getGridType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, GRIDTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLinkGridWidgetComponent.gridType</code> attribute.
	 * @return the gridType - Link Grid Type
	 */
	public EnumerationValue getGridType()
	{
		return getGridType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLinkGridWidgetComponent.gridType</code> attribute. 
	 * @param value the gridType - Link Grid Type
	 */
	public void setGridType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, GRIDTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLinkGridWidgetComponent.gridType</code> attribute. 
	 * @param value the gridType - Link Grid Type
	 */
	public void setGridType(final EnumerationValue value)
	{
		setGridType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLinkGridWidgetComponent.heading</code> attribute.
	 * @return the heading - Heading Component
	 */
	public SSLHeadingWidgetComponent getHeading(final SessionContext ctx)
	{
		return (SSLHeadingWidgetComponent)getProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLinkGridWidgetComponent.heading</code> attribute.
	 * @return the heading - Heading Component
	 */
	public SSLHeadingWidgetComponent getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLinkGridWidgetComponent.heading</code> attribute. 
	 * @param value the heading - Heading Component
	 */
	public void setHeading(final SessionContext ctx, final SSLHeadingWidgetComponent value)
	{
		setProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLinkGridWidgetComponent.heading</code> attribute. 
	 * @param value the heading - Heading Component
	 */
	public void setHeading(final SSLHeadingWidgetComponent value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLinkGridWidgetComponent.linkGrid</code> attribute.
	 * @return the linkGrid - Link Grid
	 */
	public List<CMSLinkComponent> getLinkGrid(final SessionContext ctx)
	{
		List<CMSLinkComponent> coll = (List<CMSLinkComponent>)getProperty( ctx, LINKGRID);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLinkGridWidgetComponent.linkGrid</code> attribute.
	 * @return the linkGrid - Link Grid
	 */
	public List<CMSLinkComponent> getLinkGrid()
	{
		return getLinkGrid( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLinkGridWidgetComponent.linkGrid</code> attribute. 
	 * @param value the linkGrid - Link Grid
	 */
	public void setLinkGrid(final SessionContext ctx, final List<CMSLinkComponent> value)
	{
		setProperty(ctx, LINKGRID,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLinkGridWidgetComponent.linkGrid</code> attribute. 
	 * @param value the linkGrid - Link Grid
	 */
	public void setLinkGrid(final List<CMSLinkComponent> value)
	{
		setLinkGrid( getSession().getSessionContext(), value );
	}
	
}
