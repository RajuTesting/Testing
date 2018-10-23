/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.PromotionsProductsCarouselComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.PromotionsTabParentComponent PromotionsTabParentComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionsTabParentComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>PromotionsTabParentComponent.tabs</code> attribute **/
	public static final String TABS = "tabs";
	/** Qualifier of the <code>PromotionsTabParentComponent.tabsContent</code> attribute **/
	public static final String TABSCONTENT = "tabsContent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TABS, AttributeMode.INITIAL);
		tmp.put(TABSCONTENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsTabParentComponent.tabs</code> attribute.
	 * @return the tabs - Tabs
	 */
	public List<CMSParagraphComponent> getTabs(final SessionContext ctx)
	{
		List<CMSParagraphComponent> coll = (List<CMSParagraphComponent>)getProperty( ctx, TABS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsTabParentComponent.tabs</code> attribute.
	 * @return the tabs - Tabs
	 */
	public List<CMSParagraphComponent> getTabs()
	{
		return getTabs( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsTabParentComponent.tabs</code> attribute. 
	 * @param value the tabs - Tabs
	 */
	public void setTabs(final SessionContext ctx, final List<CMSParagraphComponent> value)
	{
		setProperty(ctx, TABS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsTabParentComponent.tabs</code> attribute. 
	 * @param value the tabs - Tabs
	 */
	public void setTabs(final List<CMSParagraphComponent> value)
	{
		setTabs( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsTabParentComponent.tabsContent</code> attribute.
	 * @return the tabsContent - Tab contents
	 */
	public PromotionsProductsCarouselComponent getTabsContent(final SessionContext ctx)
	{
		return (PromotionsProductsCarouselComponent)getProperty( ctx, TABSCONTENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsTabParentComponent.tabsContent</code> attribute.
	 * @return the tabsContent - Tab contents
	 */
	public PromotionsProductsCarouselComponent getTabsContent()
	{
		return getTabsContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsTabParentComponent.tabsContent</code> attribute. 
	 * @param value the tabsContent - Tab contents
	 */
	public void setTabsContent(final SessionContext ctx, final PromotionsProductsCarouselComponent value)
	{
		setProperty(ctx, TABSCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsTabParentComponent.tabsContent</code> attribute. 
	 * @param value the tabsContent - Tab contents
	 */
	public void setTabsContent(final PromotionsProductsCarouselComponent value)
	{
		setTabsContent( getSession().getSessionContext(), value );
	}
	
}
