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
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslExclusiveBrandLinksComponent SslExclusiveBrandLinksComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslExclusiveBrandLinksComponent extends CMSParagraphComponent
{
	/** Qualifier of the <code>SslExclusiveBrandLinksComponent.listLink</code> attribute **/
	public static final String LISTLINK = "listLink";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSParagraphComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LISTLINK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslExclusiveBrandLinksComponent.listLink</code> attribute.
	 * @return the listLink - List of Links
	 */
	public List<CMSLinkComponent> getListLink(final SessionContext ctx)
	{
		List<CMSLinkComponent> coll = (List<CMSLinkComponent>)getProperty( ctx, LISTLINK);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslExclusiveBrandLinksComponent.listLink</code> attribute.
	 * @return the listLink - List of Links
	 */
	public List<CMSLinkComponent> getListLink()
	{
		return getListLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslExclusiveBrandLinksComponent.listLink</code> attribute. 
	 * @param value the listLink - List of Links
	 */
	public void setListLink(final SessionContext ctx, final List<CMSLinkComponent> value)
	{
		setProperty(ctx, LISTLINK,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslExclusiveBrandLinksComponent.listLink</code> attribute. 
	 * @param value the listLink - List of Links
	 */
	public void setListLink(final List<CMSLinkComponent> value)
	{
		setListLink( getSession().getSessionContext(), value );
	}
	
}
