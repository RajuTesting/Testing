/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.OutsideStoreBenefitsComponent OutsideStoreBenefitsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOutsideStoreBenefitsComponent extends CMSParagraphComponent
{
	/** Qualifier of the <code>OutsideStoreBenefitsComponent.buttonLink</code> attribute **/
	public static final String BUTTONLINK = "buttonLink";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSParagraphComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BUTTONLINK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OutsideStoreBenefitsComponent.buttonLink</code> attribute.
	 * @return the buttonLink - Button Link of the component.
	 */
	public CMSLinkComponent getButtonLink(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, BUTTONLINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OutsideStoreBenefitsComponent.buttonLink</code> attribute.
	 * @return the buttonLink - Button Link of the component.
	 */
	public CMSLinkComponent getButtonLink()
	{
		return getButtonLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OutsideStoreBenefitsComponent.buttonLink</code> attribute. 
	 * @param value the buttonLink - Button Link of the component.
	 */
	public void setButtonLink(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, BUTTONLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OutsideStoreBenefitsComponent.buttonLink</code> attribute. 
	 * @param value the buttonLink - Button Link of the component.
	 */
	public void setButtonLink(final CMSLinkComponent value)
	{
		setButtonLink( getSession().getSessionContext(), value );
	}
	
}
