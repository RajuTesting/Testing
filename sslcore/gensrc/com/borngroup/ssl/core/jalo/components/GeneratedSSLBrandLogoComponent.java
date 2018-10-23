/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2lib.components.AbstractBannerComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLBrandLogoComponent SSLBrandLogoComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBrandLogoComponent extends AbstractBannerComponent
{
	/** Qualifier of the <code>SSLBrandLogoComponent.svgPath</code> attribute **/
	public static final String SVGPATH = "svgPath";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SVGPATH, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandLogoComponent.svgPath</code> attribute.
	 * @return the svgPath - SVG image relative path inside directory -
	 *                             'sslstorefront/web/webroot/_ui/responsive/common/'
	 */
	public String getSvgPath(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SVGPATH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandLogoComponent.svgPath</code> attribute.
	 * @return the svgPath - SVG image relative path inside directory -
	 *                             'sslstorefront/web/webroot/_ui/responsive/common/'
	 */
	public String getSvgPath()
	{
		return getSvgPath( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandLogoComponent.svgPath</code> attribute. 
	 * @param value the svgPath - SVG image relative path inside directory -
	 *                             'sslstorefront/web/webroot/_ui/responsive/common/'
	 */
	public void setSvgPath(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SVGPATH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandLogoComponent.svgPath</code> attribute. 
	 * @param value the svgPath - SVG image relative path inside directory -
	 *                             'sslstorefront/web/webroot/_ui/responsive/common/'
	 */
	public void setSvgPath(final String value)
	{
		setSvgPath( getSession().getSessionContext(), value );
	}
	
}
