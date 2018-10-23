/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.CMSMediaParagraphLinkTabComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.InstantGiftingTabListComponent InstantGiftingTabListComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedInstantGiftingTabListComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>InstantGiftingTabListComponent.instantGiftingTabsList</code> attribute **/
	public static final String INSTANTGIFTINGTABSLIST = "instantGiftingTabsList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(INSTANTGIFTINGTABSLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InstantGiftingTabListComponent.instantGiftingTabsList</code> attribute.
	 * @return the instantGiftingTabsList - List of tab components for instant gifting paragraph.
	 */
	public List<CMSMediaParagraphLinkTabComponent> getInstantGiftingTabsList(final SessionContext ctx)
	{
		List<CMSMediaParagraphLinkTabComponent> coll = (List<CMSMediaParagraphLinkTabComponent>)getProperty( ctx, INSTANTGIFTINGTABSLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>InstantGiftingTabListComponent.instantGiftingTabsList</code> attribute.
	 * @return the instantGiftingTabsList - List of tab components for instant gifting paragraph.
	 */
	public List<CMSMediaParagraphLinkTabComponent> getInstantGiftingTabsList()
	{
		return getInstantGiftingTabsList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InstantGiftingTabListComponent.instantGiftingTabsList</code> attribute. 
	 * @param value the instantGiftingTabsList - List of tab components for instant gifting paragraph.
	 */
	public void setInstantGiftingTabsList(final SessionContext ctx, final List<CMSMediaParagraphLinkTabComponent> value)
	{
		setProperty(ctx, INSTANTGIFTINGTABSLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>InstantGiftingTabListComponent.instantGiftingTabsList</code> attribute. 
	 * @param value the instantGiftingTabsList - List of tab components for instant gifting paragraph.
	 */
	public void setInstantGiftingTabsList(final List<CMSMediaParagraphLinkTabComponent> value)
	{
		setInstantGiftingTabsList( getSession().getSessionContext(), value );
	}
	
}
