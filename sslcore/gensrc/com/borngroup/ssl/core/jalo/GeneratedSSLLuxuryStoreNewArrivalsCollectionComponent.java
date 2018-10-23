/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SSLLuxuryStoreNewArrivalsCMSComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLLuxuryStoreNewArrivalsCollectionComponent SSLLuxuryStoreNewArrivalsCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLLuxuryStoreNewArrivalsCollectionComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLLuxuryStoreNewArrivalsCollectionComponent.luxuryStoreNewArrivalsCollectionComponent</code> attribute **/
	public static final String LUXURYSTORENEWARRIVALSCOLLECTIONCOMPONENT = "luxuryStoreNewArrivalsCollectionComponent";
	/** Qualifier of the <code>SSLLuxuryStoreNewArrivalsCollectionComponent.titleNewArrivals</code> attribute **/
	public static final String TITLENEWARRIVALS = "titleNewArrivals";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LUXURYSTORENEWARRIVALSCOLLECTIONCOMPONENT, AttributeMode.INITIAL);
		tmp.put(TITLENEWARRIVALS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCollectionComponent.luxuryStoreNewArrivalsCollectionComponent</code> attribute.
	 * @return the luxuryStoreNewArrivalsCollectionComponent
	 */
	public List<SSLLuxuryStoreNewArrivalsCMSComponent> getLuxuryStoreNewArrivalsCollectionComponent(final SessionContext ctx)
	{
		List<SSLLuxuryStoreNewArrivalsCMSComponent> coll = (List<SSLLuxuryStoreNewArrivalsCMSComponent>)getProperty( ctx, LUXURYSTORENEWARRIVALSCOLLECTIONCOMPONENT);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCollectionComponent.luxuryStoreNewArrivalsCollectionComponent</code> attribute.
	 * @return the luxuryStoreNewArrivalsCollectionComponent
	 */
	public List<SSLLuxuryStoreNewArrivalsCMSComponent> getLuxuryStoreNewArrivalsCollectionComponent()
	{
		return getLuxuryStoreNewArrivalsCollectionComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCollectionComponent.luxuryStoreNewArrivalsCollectionComponent</code> attribute. 
	 * @param value the luxuryStoreNewArrivalsCollectionComponent
	 */
	public void setLuxuryStoreNewArrivalsCollectionComponent(final SessionContext ctx, final List<SSLLuxuryStoreNewArrivalsCMSComponent> value)
	{
		setProperty(ctx, LUXURYSTORENEWARRIVALSCOLLECTIONCOMPONENT,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCollectionComponent.luxuryStoreNewArrivalsCollectionComponent</code> attribute. 
	 * @param value the luxuryStoreNewArrivalsCollectionComponent
	 */
	public void setLuxuryStoreNewArrivalsCollectionComponent(final List<SSLLuxuryStoreNewArrivalsCMSComponent> value)
	{
		setLuxuryStoreNewArrivalsCollectionComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCollectionComponent.titleNewArrivals</code> attribute.
	 * @return the titleNewArrivals - Title
	 */
	public String getTitleNewArrivals(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TITLENEWARRIVALS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreNewArrivalsCollectionComponent.titleNewArrivals</code> attribute.
	 * @return the titleNewArrivals - Title
	 */
	public String getTitleNewArrivals()
	{
		return getTitleNewArrivals( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCollectionComponent.titleNewArrivals</code> attribute. 
	 * @param value the titleNewArrivals - Title
	 */
	public void setTitleNewArrivals(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TITLENEWARRIVALS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreNewArrivalsCollectionComponent.titleNewArrivals</code> attribute. 
	 * @param value the titleNewArrivals - Title
	 */
	public void setTitleNewArrivals(final String value)
	{
		setTitleNewArrivals( getSession().getSessionContext(), value );
	}
	
}
