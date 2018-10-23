/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.NavigationBarImageComponent NavigationBarImageComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedNavigationBarImageComponent extends CMSImageComponent
{
	/** Qualifier of the <code>NavigationBarImageComponent.imageLink</code> attribute **/
	public static final String IMAGELINK = "imageLink";
	/** Qualifier of the <code>NavigationBarImageComponent.imageDescription</code> attribute **/
	public static final String IMAGEDESCRIPTION = "imageDescription";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(IMAGELINK, AttributeMode.INITIAL);
		tmp.put(IMAGEDESCRIPTION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarImageComponent.imageDescription</code> attribute.
	 * @return the imageDescription - The image description which can be attached to this
	 *                             navigation bar component.
	 */
	public String getImageDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, IMAGEDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarImageComponent.imageDescription</code> attribute.
	 * @return the imageDescription - The image description which can be attached to this
	 *                             navigation bar component.
	 */
	public String getImageDescription()
	{
		return getImageDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarImageComponent.imageDescription</code> attribute. 
	 * @param value the imageDescription - The image description which can be attached to this
	 *                             navigation bar component.
	 */
	public void setImageDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, IMAGEDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarImageComponent.imageDescription</code> attribute. 
	 * @param value the imageDescription - The image description which can be attached to this
	 *                             navigation bar component.
	 */
	public void setImageDescription(final String value)
	{
		setImageDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarImageComponent.imageLink</code> attribute.
	 * @return the imageLink - The cms link component that is attached to this bar
	 *                             component's image.
	 */
	public CMSLinkComponent getImageLink(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, IMAGELINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarImageComponent.imageLink</code> attribute.
	 * @return the imageLink - The cms link component that is attached to this bar
	 *                             component's image.
	 */
	public CMSLinkComponent getImageLink()
	{
		return getImageLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarImageComponent.imageLink</code> attribute. 
	 * @param value the imageLink - The cms link component that is attached to this bar
	 *                             component's image.
	 */
	public void setImageLink(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, IMAGELINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarImageComponent.imageLink</code> attribute. 
	 * @param value the imageLink - The cms link component that is attached to this bar
	 *                             component's image.
	 */
	public void setImageLink(final CMSLinkComponent value)
	{
		setImageLink( getSession().getSessionContext(), value );
	}
	
}
