/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLBannerWidgetComponent;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SSLConfigurableTab}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLConfigurableTab extends GenericItem
{
	/** Qualifier of the <code>SSLConfigurableTab.page</code> attribute **/
	public static final String PAGE = "page";
	/** Qualifier of the <code>SSLConfigurableTab.tabType</code> attribute **/
	public static final String TABTYPE = "tabType";
	/** Qualifier of the <code>SSLConfigurableTab.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>SSLConfigurableTab.banner</code> attribute **/
	public static final String BANNER = "banner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PAGE, AttributeMode.INITIAL);
		tmp.put(TABTYPE, AttributeMode.INITIAL);
		tmp.put(TITLE, AttributeMode.INITIAL);
		tmp.put(BANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLConfigurableTab.banner</code> attribute.
	 * @return the banner
	 */
	public SSLBannerWidgetComponent getBanner(final SessionContext ctx)
	{
		return (SSLBannerWidgetComponent)getProperty( ctx, BANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLConfigurableTab.banner</code> attribute.
	 * @return the banner
	 */
	public SSLBannerWidgetComponent getBanner()
	{
		return getBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLConfigurableTab.banner</code> attribute. 
	 * @param value the banner
	 */
	public void setBanner(final SessionContext ctx, final SSLBannerWidgetComponent value)
	{
		setProperty(ctx, BANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLConfigurableTab.banner</code> attribute. 
	 * @param value the banner
	 */
	public void setBanner(final SSLBannerWidgetComponent value)
	{
		setBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLConfigurableTab.page</code> attribute.
	 * @return the page
	 */
	public EnumerationValue getPage(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, PAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLConfigurableTab.page</code> attribute.
	 * @return the page
	 */
	public EnumerationValue getPage()
	{
		return getPage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLConfigurableTab.page</code> attribute. 
	 * @param value the page
	 */
	public void setPage(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, PAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLConfigurableTab.page</code> attribute. 
	 * @param value the page
	 */
	public void setPage(final EnumerationValue value)
	{
		setPage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLConfigurableTab.tabType</code> attribute.
	 * @return the tabType
	 */
	public EnumerationValue getTabType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, TABTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLConfigurableTab.tabType</code> attribute.
	 * @return the tabType
	 */
	public EnumerationValue getTabType()
	{
		return getTabType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLConfigurableTab.tabType</code> attribute. 
	 * @param value the tabType
	 */
	public void setTabType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, TABTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLConfigurableTab.tabType</code> attribute. 
	 * @param value the tabType
	 */
	public void setTabType(final EnumerationValue value)
	{
		setTabType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLConfigurableTab.title</code> attribute.
	 * @return the title
	 */
	public String getTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLConfigurableTab.title</code> attribute.
	 * @return the title
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLConfigurableTab.title</code> attribute. 
	 * @param value the title
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLConfigurableTab.title</code> attribute. 
	 * @param value the title
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
}
