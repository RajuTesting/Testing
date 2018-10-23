/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.LatestFromCMSComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.LatestFromSSComponent LatestFromSSComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedLatestFromSSComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>LatestFromSSComponent.latestFromSSList</code> attribute **/
	public static final String LATESTFROMSSLIST = "latestFromSSList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LATESTFROMSSLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromSSComponent.latestFromSSList</code> attribute.
	 * @return the latestFromSSList
	 */
	public List<LatestFromCMSComponent> getLatestFromSSList(final SessionContext ctx)
	{
		List<LatestFromCMSComponent> coll = (List<LatestFromCMSComponent>)getProperty( ctx, LATESTFROMSSLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromSSComponent.latestFromSSList</code> attribute.
	 * @return the latestFromSSList
	 */
	public List<LatestFromCMSComponent> getLatestFromSSList()
	{
		return getLatestFromSSList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromSSComponent.latestFromSSList</code> attribute. 
	 * @param value the latestFromSSList
	 */
	public void setLatestFromSSList(final SessionContext ctx, final List<LatestFromCMSComponent> value)
	{
		setProperty(ctx, LATESTFROMSSLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromSSComponent.latestFromSSList</code> attribute. 
	 * @param value the latestFromSSList
	 */
	public void setLatestFromSSList(final List<LatestFromCMSComponent> value)
	{
		setLatestFromSSList( getSession().getSessionContext(), value );
	}
	
}
