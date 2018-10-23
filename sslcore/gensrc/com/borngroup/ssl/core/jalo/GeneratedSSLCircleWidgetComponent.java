/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLBannerWidgetComponent;
import com.borngroup.ssl.core.jalo.SSLMobileWidgetComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLCircleWidgetComponent SSLCircleWidgetComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLCircleWidgetComponent extends SSLMobileWidgetComponent
{
	/** Qualifier of the <code>SSLCircleWidgetComponent.bannerList</code> attribute **/
	public static final String BANNERLIST = "bannerList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SSLMobileWidgetComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BANNERLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCircleWidgetComponent.bannerList</code> attribute.
	 * @return the bannerList - Banner List
	 */
	public List<SSLBannerWidgetComponent> getBannerList(final SessionContext ctx)
	{
		List<SSLBannerWidgetComponent> coll = (List<SSLBannerWidgetComponent>)getProperty( ctx, BANNERLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLCircleWidgetComponent.bannerList</code> attribute.
	 * @return the bannerList - Banner List
	 */
	public List<SSLBannerWidgetComponent> getBannerList()
	{
		return getBannerList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCircleWidgetComponent.bannerList</code> attribute. 
	 * @param value the bannerList - Banner List
	 */
	public void setBannerList(final SessionContext ctx, final List<SSLBannerWidgetComponent> value)
	{
		setProperty(ctx, BANNERLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLCircleWidgetComponent.bannerList</code> attribute. 
	 * @param value the bannerList - Banner List
	 */
	public void setBannerList(final List<SSLBannerWidgetComponent> value)
	{
		setBannerList( getSession().getSessionContext(), value );
	}
	
}
