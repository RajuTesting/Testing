/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLBannerWidgetComponent;
import com.borngroup.ssl.core.jalo.SSLButtonWidgetComponent;
import com.borngroup.ssl.core.jalo.SSLMobileWidgetComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLBannerWidgetWithOptionsComponent SSLBannerWidgetWithOptionsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBannerWidgetWithOptionsComponent extends SSLMobileWidgetComponent
{
	/** Qualifier of the <code>SSLBannerWidgetWithOptionsComponent.banner</code> attribute **/
	public static final String BANNER = "banner";
	/** Qualifier of the <code>SSLBannerWidgetWithOptionsComponent.buttonList</code> attribute **/
	public static final String BUTTONLIST = "buttonList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SSLMobileWidgetComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BANNER, AttributeMode.INITIAL);
		tmp.put(BUTTONLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetWithOptionsComponent.banner</code> attribute.
	 * @return the banner - Banner
	 */
	public SSLBannerWidgetComponent getBanner(final SessionContext ctx)
	{
		return (SSLBannerWidgetComponent)getProperty( ctx, BANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetWithOptionsComponent.banner</code> attribute.
	 * @return the banner - Banner
	 */
	public SSLBannerWidgetComponent getBanner()
	{
		return getBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetWithOptionsComponent.banner</code> attribute. 
	 * @param value the banner - Banner
	 */
	public void setBanner(final SessionContext ctx, final SSLBannerWidgetComponent value)
	{
		setProperty(ctx, BANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetWithOptionsComponent.banner</code> attribute. 
	 * @param value the banner - Banner
	 */
	public void setBanner(final SSLBannerWidgetComponent value)
	{
		setBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetWithOptionsComponent.buttonList</code> attribute.
	 * @return the buttonList - Button List
	 */
	public List<SSLButtonWidgetComponent> getButtonList(final SessionContext ctx)
	{
		List<SSLButtonWidgetComponent> coll = (List<SSLButtonWidgetComponent>)getProperty( ctx, BUTTONLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBannerWidgetWithOptionsComponent.buttonList</code> attribute.
	 * @return the buttonList - Button List
	 */
	public List<SSLButtonWidgetComponent> getButtonList()
	{
		return getButtonList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetWithOptionsComponent.buttonList</code> attribute. 
	 * @param value the buttonList - Button List
	 */
	public void setButtonList(final SessionContext ctx, final List<SSLButtonWidgetComponent> value)
	{
		setProperty(ctx, BUTTONLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBannerWidgetWithOptionsComponent.buttonList</code> attribute. 
	 * @param value the buttonList - Button List
	 */
	public void setButtonList(final List<SSLButtonWidgetComponent> value)
	{
		setButtonList( getSession().getSessionContext(), value );
	}
	
}
