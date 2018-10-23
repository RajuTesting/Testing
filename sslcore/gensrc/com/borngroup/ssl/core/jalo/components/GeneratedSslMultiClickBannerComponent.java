/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslMultiClickBannerComponent SslMultiClickBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslMultiClickBannerComponent extends CMSImageComponent
{
	/** Qualifier of the <code>SslMultiClickBannerComponent.imageMapHTML</code> attribute **/
	public static final String IMAGEMAPHTML = "imageMapHTML";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(IMAGEMAPHTML, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslMultiClickBannerComponent.imageMapHTML</code> attribute.
	 * @return the imageMapHTML
	 */
	public String getImageMapHTML(final SessionContext ctx)
	{
		return (String)getProperty( ctx, IMAGEMAPHTML);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslMultiClickBannerComponent.imageMapHTML</code> attribute.
	 * @return the imageMapHTML
	 */
	public String getImageMapHTML()
	{
		return getImageMapHTML( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslMultiClickBannerComponent.imageMapHTML</code> attribute. 
	 * @param value the imageMapHTML
	 */
	public void setImageMapHTML(final SessionContext ctx, final String value)
	{
		setProperty(ctx, IMAGEMAPHTML,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslMultiClickBannerComponent.imageMapHTML</code> attribute. 
	 * @param value the imageMapHTML
	 */
	public void setImageMapHTML(final String value)
	{
		setImageMapHTML( getSession().getSessionContext(), value );
	}
	
}
