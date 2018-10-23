/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SslCollectionsImageMapComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslCollectionsCarouselComponent SslCollectionsCarouselComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslCollectionsCarouselComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SslCollectionsCarouselComponent.collectionsBanner</code> attribute **/
	public static final String COLLECTIONSBANNER = "collectionsBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(COLLECTIONSBANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsCarouselComponent.collectionsBanner</code> attribute.
	 * @return the collectionsBanner
	 */
	public List<SslCollectionsImageMapComponent> getCollectionsBanner(final SessionContext ctx)
	{
		List<SslCollectionsImageMapComponent> coll = (List<SslCollectionsImageMapComponent>)getProperty( ctx, COLLECTIONSBANNER);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsCarouselComponent.collectionsBanner</code> attribute.
	 * @return the collectionsBanner
	 */
	public List<SslCollectionsImageMapComponent> getCollectionsBanner()
	{
		return getCollectionsBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsCarouselComponent.collectionsBanner</code> attribute. 
	 * @param value the collectionsBanner
	 */
	public void setCollectionsBanner(final SessionContext ctx, final List<SslCollectionsImageMapComponent> value)
	{
		setProperty(ctx, COLLECTIONSBANNER,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsCarouselComponent.collectionsBanner</code> attribute. 
	 * @param value the collectionsBanner
	 */
	public void setCollectionsBanner(final List<SslCollectionsImageMapComponent> value)
	{
		setCollectionsBanner( getSession().getSessionContext(), value );
	}
	
}
