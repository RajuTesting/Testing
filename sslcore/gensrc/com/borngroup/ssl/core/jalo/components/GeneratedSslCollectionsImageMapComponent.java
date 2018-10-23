/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SslImageMapComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslCollectionsImageMapComponent SslCollectionsImageMapComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslCollectionsImageMapComponent extends SslImageMapComponent
{
	/** Qualifier of the <code>SslCollectionsImageMapComponent.collectionsSectionLink</code> attribute **/
	public static final String COLLECTIONSSECTIONLINK = "collectionsSectionLink";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SslImageMapComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(COLLECTIONSSECTIONLINK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsImageMapComponent.collectionsSectionLink</code> attribute.
	 * @return the collectionsSectionLink - Collections Gender Section to be pointed by Banner
	 */
	public EnumerationValue getCollectionsSectionLink(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, COLLECTIONSSECTIONLINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsImageMapComponent.collectionsSectionLink</code> attribute.
	 * @return the collectionsSectionLink - Collections Gender Section to be pointed by Banner
	 */
	public EnumerationValue getCollectionsSectionLink()
	{
		return getCollectionsSectionLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsImageMapComponent.collectionsSectionLink</code> attribute. 
	 * @param value the collectionsSectionLink - Collections Gender Section to be pointed by Banner
	 */
	public void setCollectionsSectionLink(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, COLLECTIONSSECTIONLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsImageMapComponent.collectionsSectionLink</code> attribute. 
	 * @param value the collectionsSectionLink - Collections Gender Section to be pointed by Banner
	 */
	public void setCollectionsSectionLink(final EnumerationValue value)
	{
		setCollectionsSectionLink( getSession().getSessionContext(), value );
	}
	
}
