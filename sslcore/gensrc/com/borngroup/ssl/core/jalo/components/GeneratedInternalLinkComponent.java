/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.InternalLinkComponent InternalLinkComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedInternalLinkComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>InternalLinkComponent.linkHeader</code> attribute **/
	public static final String LINKHEADER = "linkHeader";
	/** Qualifier of the <code>InternalLinkComponent.listLink</code> attribute **/
	public static final String LISTLINK = "listLink";
	/** Qualifier of the <code>InternalLinkComponent.linkCounts</code> attribute **/
	public static final String LINKCOUNTS = "linkCounts";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LINKHEADER, AttributeMode.INITIAL);
		tmp.put(LISTLINK, AttributeMode.INITIAL);
		tmp.put(LINKCOUNTS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InternalLinkComponent.linkCounts</code> attribute.
	 * @return the linkCounts - Number of Link Components
	 */
	public Integer getLinkCounts(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, LINKCOUNTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InternalLinkComponent.linkCounts</code> attribute.
	 * @return the linkCounts - Number of Link Components
	 */
	public Integer getLinkCounts()
	{
		return getLinkCounts( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InternalLinkComponent.linkCounts</code> attribute. 
	 * @return the linkCounts - Number of Link Components
	 */
	public int getLinkCountsAsPrimitive(final SessionContext ctx)
	{
		Integer value = getLinkCounts( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InternalLinkComponent.linkCounts</code> attribute. 
	 * @return the linkCounts - Number of Link Components
	 */
	public int getLinkCountsAsPrimitive()
	{
		return getLinkCountsAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InternalLinkComponent.linkCounts</code> attribute. 
	 * @param value the linkCounts - Number of Link Components
	 */
	public void setLinkCounts(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, LINKCOUNTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InternalLinkComponent.linkCounts</code> attribute. 
	 * @param value the linkCounts - Number of Link Components
	 */
	public void setLinkCounts(final Integer value)
	{
		setLinkCounts( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InternalLinkComponent.linkCounts</code> attribute. 
	 * @param value the linkCounts - Number of Link Components
	 */
	public void setLinkCounts(final SessionContext ctx, final int value)
	{
		setLinkCounts( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InternalLinkComponent.linkCounts</code> attribute. 
	 * @param value the linkCounts - Number of Link Components
	 */
	public void setLinkCounts(final int value)
	{
		setLinkCounts( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InternalLinkComponent.linkHeader</code> attribute.
	 * @return the linkHeader
	 */
	public String getLinkHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKHEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InternalLinkComponent.linkHeader</code> attribute.
	 * @return the linkHeader
	 */
	public String getLinkHeader()
	{
		return getLinkHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InternalLinkComponent.linkHeader</code> attribute. 
	 * @param value the linkHeader
	 */
	public void setLinkHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKHEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InternalLinkComponent.linkHeader</code> attribute. 
	 * @param value the linkHeader
	 */
	public void setLinkHeader(final String value)
	{
		setLinkHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InternalLinkComponent.listLink</code> attribute.
	 * @return the listLink - List of Link
	 */
	public List<CMSLinkComponent> getListLink(final SessionContext ctx)
	{
		List<CMSLinkComponent> coll = (List<CMSLinkComponent>)getProperty( ctx, LISTLINK);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InternalLinkComponent.listLink</code> attribute.
	 * @return the listLink - List of Link
	 */
	public List<CMSLinkComponent> getListLink()
	{
		return getListLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InternalLinkComponent.listLink</code> attribute. 
	 * @param value the listLink - List of Link
	 */
	public void setListLink(final SessionContext ctx, final List<CMSLinkComponent> value)
	{
		setProperty(ctx, LISTLINK,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InternalLinkComponent.listLink</code> attribute. 
	 * @param value the listLink - List of Link
	 */
	public void setListLink(final List<CMSLinkComponent> value)
	{
		setListLink( getSession().getSessionContext(), value );
	}
	
}
