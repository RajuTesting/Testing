/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.JspIncludeComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslProductVariantJspIncludeComponent SslProductVariantJspIncludeComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslProductVariantJspIncludeComponent extends JspIncludeComponent
{
	/** Qualifier of the <code>SslProductVariantJspIncludeComponent.sizeGuideComponent</code> attribute **/
	public static final String SIZEGUIDECOMPONENT = "sizeGuideComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(JspIncludeComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SIZEGUIDECOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductVariantJspIncludeComponent.sizeGuideComponent</code> attribute.
	 * @return the sizeGuideComponent
	 */
	public SimpleCMSComponent getSizeGuideComponent(final SessionContext ctx)
	{
		return (SimpleCMSComponent)getProperty( ctx, SIZEGUIDECOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductVariantJspIncludeComponent.sizeGuideComponent</code> attribute.
	 * @return the sizeGuideComponent
	 */
	public SimpleCMSComponent getSizeGuideComponent()
	{
		return getSizeGuideComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductVariantJspIncludeComponent.sizeGuideComponent</code> attribute. 
	 * @param value the sizeGuideComponent
	 */
	public void setSizeGuideComponent(final SessionContext ctx, final SimpleCMSComponent value)
	{
		setProperty(ctx, SIZEGUIDECOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductVariantJspIncludeComponent.sizeGuideComponent</code> attribute. 
	 * @param value the sizeGuideComponent
	 */
	public void setSizeGuideComponent(final SimpleCMSComponent value)
	{
		setSizeGuideComponent( getSession().getSessionContext(), value );
	}
	
}
