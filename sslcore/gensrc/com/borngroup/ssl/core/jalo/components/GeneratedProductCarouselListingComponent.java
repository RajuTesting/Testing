/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.ProductRecommendationComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.ProductCarouselListingComponent ProductCarouselListingComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedProductCarouselListingComponent extends ProductRecommendationComponent
{
	/** Qualifier of the <code>ProductCarouselListingComponent.linkCounts</code> attribute **/
	public static final String LINKCOUNTS = "linkCounts";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(ProductRecommendationComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LINKCOUNTS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductCarouselListingComponent.linkCounts</code> attribute.
	 * @return the linkCounts - Number of Link Components
	 */
	public Integer getLinkCounts(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, LINKCOUNTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductCarouselListingComponent.linkCounts</code> attribute.
	 * @return the linkCounts - Number of Link Components
	 */
	public Integer getLinkCounts()
	{
		return getLinkCounts( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductCarouselListingComponent.linkCounts</code> attribute. 
	 * @return the linkCounts - Number of Link Components
	 */
	public int getLinkCountsAsPrimitive(final SessionContext ctx)
	{
		Integer value = getLinkCounts( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductCarouselListingComponent.linkCounts</code> attribute. 
	 * @return the linkCounts - Number of Link Components
	 */
	public int getLinkCountsAsPrimitive()
	{
		return getLinkCountsAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductCarouselListingComponent.linkCounts</code> attribute. 
	 * @param value the linkCounts - Number of Link Components
	 */
	public void setLinkCounts(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, LINKCOUNTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductCarouselListingComponent.linkCounts</code> attribute. 
	 * @param value the linkCounts - Number of Link Components
	 */
	public void setLinkCounts(final Integer value)
	{
		setLinkCounts( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductCarouselListingComponent.linkCounts</code> attribute. 
	 * @param value the linkCounts - Number of Link Components
	 */
	public void setLinkCounts(final SessionContext ctx, final int value)
	{
		setLinkCounts( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductCarouselListingComponent.linkCounts</code> attribute. 
	 * @param value the linkCounts - Number of Link Components
	 */
	public void setLinkCounts(final int value)
	{
		setLinkCounts( getSession().getSessionContext(), value );
	}
	
}
