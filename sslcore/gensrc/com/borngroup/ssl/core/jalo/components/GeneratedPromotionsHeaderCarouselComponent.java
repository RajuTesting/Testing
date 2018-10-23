/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SslImageMapComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.PromotionsHeaderCarouselComponent PromotionsHeaderCarouselComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionsHeaderCarouselComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>PromotionsHeaderCarouselComponent.children</code> attribute **/
	public static final String CHILDREN = "children";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CHILDREN, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsHeaderCarouselComponent.children</code> attribute.
	 * @return the children - Promotion Banner Header Component
	 */
	public List<SslImageMapComponent> getChildren(final SessionContext ctx)
	{
		List<SslImageMapComponent> coll = (List<SslImageMapComponent>)getProperty( ctx, CHILDREN);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsHeaderCarouselComponent.children</code> attribute.
	 * @return the children - Promotion Banner Header Component
	 */
	public List<SslImageMapComponent> getChildren()
	{
		return getChildren( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsHeaderCarouselComponent.children</code> attribute. 
	 * @param value the children - Promotion Banner Header Component
	 */
	public void setChildren(final SessionContext ctx, final List<SslImageMapComponent> value)
	{
		setProperty(ctx, CHILDREN,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsHeaderCarouselComponent.children</code> attribute. 
	 * @param value the children - Promotion Banner Header Component
	 */
	public void setChildren(final List<SslImageMapComponent> value)
	{
		setChildren( getSession().getSessionContext(), value );
	}
	
}
