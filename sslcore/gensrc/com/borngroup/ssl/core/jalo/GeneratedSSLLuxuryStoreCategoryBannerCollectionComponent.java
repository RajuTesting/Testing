/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SSLLuxuryStoreCategoryBannerCMSComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLLuxuryStoreCategoryBannerCollectionComponent SSLLuxuryStoreCategoryBannerCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLLuxuryStoreCategoryBannerCollectionComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLLuxuryStoreCategoryBannerCollectionComponent.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>SSLLuxuryStoreCategoryBannerCollectionComponent.luxuryStoreCategoryBannerComponent</code> attribute **/
	public static final String LUXURYSTORECATEGORYBANNERCOMPONENT = "luxuryStoreCategoryBannerComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TITLE, AttributeMode.INITIAL);
		tmp.put(LUXURYSTORECATEGORYBANNERCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreCategoryBannerCollectionComponent.luxuryStoreCategoryBannerComponent</code> attribute.
	 * @return the luxuryStoreCategoryBannerComponent
	 */
	public List<SSLLuxuryStoreCategoryBannerCMSComponent> getLuxuryStoreCategoryBannerComponent(final SessionContext ctx)
	{
		List<SSLLuxuryStoreCategoryBannerCMSComponent> coll = (List<SSLLuxuryStoreCategoryBannerCMSComponent>)getProperty( ctx, LUXURYSTORECATEGORYBANNERCOMPONENT);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreCategoryBannerCollectionComponent.luxuryStoreCategoryBannerComponent</code> attribute.
	 * @return the luxuryStoreCategoryBannerComponent
	 */
	public List<SSLLuxuryStoreCategoryBannerCMSComponent> getLuxuryStoreCategoryBannerComponent()
	{
		return getLuxuryStoreCategoryBannerComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreCategoryBannerCollectionComponent.luxuryStoreCategoryBannerComponent</code> attribute. 
	 * @param value the luxuryStoreCategoryBannerComponent
	 */
	public void setLuxuryStoreCategoryBannerComponent(final SessionContext ctx, final List<SSLLuxuryStoreCategoryBannerCMSComponent> value)
	{
		setProperty(ctx, LUXURYSTORECATEGORYBANNERCOMPONENT,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreCategoryBannerCollectionComponent.luxuryStoreCategoryBannerComponent</code> attribute. 
	 * @param value the luxuryStoreCategoryBannerComponent
	 */
	public void setLuxuryStoreCategoryBannerComponent(final List<SSLLuxuryStoreCategoryBannerCMSComponent> value)
	{
		setLuxuryStoreCategoryBannerComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreCategoryBannerCollectionComponent.title</code> attribute.
	 * @return the title - Title above Banners
	 */
	public String getTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreCategoryBannerCollectionComponent.title</code> attribute.
	 * @return the title - Title above Banners
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreCategoryBannerCollectionComponent.title</code> attribute. 
	 * @param value the title - Title above Banners
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreCategoryBannerCollectionComponent.title</code> attribute. 
	 * @param value the title - Title above Banners
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
}
