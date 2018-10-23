/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLLuxuryStoreCategoryBannerCMSComponent SSLLuxuryStoreCategoryBannerCMSComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLLuxuryStoreCategoryBannerCMSComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLLuxuryStoreCategoryBannerCMSComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>SSLLuxuryStoreCategoryBannerCMSComponent.bannerImage</code> attribute **/
	public static final String BANNERIMAGE = "bannerImage";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(BANNERIMAGE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreCategoryBannerCMSComponent.bannerImage</code> attribute.
	 * @return the bannerImage - Banner Image
	 */
	public Media getBannerImage(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, BANNERIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreCategoryBannerCMSComponent.bannerImage</code> attribute.
	 * @return the bannerImage - Banner Image
	 */
	public Media getBannerImage()
	{
		return getBannerImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreCategoryBannerCMSComponent.bannerImage</code> attribute. 
	 * @param value the bannerImage - Banner Image
	 */
	public void setBannerImage(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, BANNERIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreCategoryBannerCMSComponent.bannerImage</code> attribute. 
	 * @param value the bannerImage - Banner Image
	 */
	public void setBannerImage(final Media value)
	{
		setBannerImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreCategoryBannerCMSComponent.text</code> attribute.
	 * @return the text - Text On Banners
	 */
	public String getText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreCategoryBannerCMSComponent.text</code> attribute.
	 * @return the text - Text On Banners
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreCategoryBannerCMSComponent.text</code> attribute. 
	 * @param value the text - Text On Banners
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreCategoryBannerCMSComponent.text</code> attribute. 
	 * @param value the text - Text On Banners
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
}
