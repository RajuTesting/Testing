/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.ProductDetailFCCComponent ProductDetailFCCComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedProductDetailFCCComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>ProductDetailFCCComponent.fccBanner</code> attribute **/
	public static final String FCCBANNER = "fccBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(FCCBANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductDetailFCCComponent.fccBanner</code> attribute.
	 * @return the fccBanner
	 */
	public SimpleCMSComponent getFccBanner(final SessionContext ctx)
	{
		return (SimpleCMSComponent)getProperty( ctx, FCCBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductDetailFCCComponent.fccBanner</code> attribute.
	 * @return the fccBanner
	 */
	public SimpleCMSComponent getFccBanner()
	{
		return getFccBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductDetailFCCComponent.fccBanner</code> attribute. 
	 * @param value the fccBanner
	 */
	public void setFccBanner(final SessionContext ctx, final SimpleCMSComponent value)
	{
		setProperty(ctx, FCCBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductDetailFCCComponent.fccBanner</code> attribute. 
	 * @param value the fccBanner
	 */
	public void setFccBanner(final SimpleCMSComponent value)
	{
		setFccBanner( getSession().getSessionContext(), value );
	}
	
}
