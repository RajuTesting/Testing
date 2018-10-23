/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.CMSLinkTextComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.OutfitFooterComponent OutfitFooterComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOutfitFooterComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>OutfitFooterComponent.outFitFooterList</code> attribute **/
	public static final String OUTFITFOOTERLIST = "outFitFooterList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(OUTFITFOOTERLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OutfitFooterComponent.outFitFooterList</code> attribute.
	 * @return the outFitFooterList
	 */
	public List<CMSLinkTextComponent> getOutFitFooterList(final SessionContext ctx)
	{
		List<CMSLinkTextComponent> coll = (List<CMSLinkTextComponent>)getProperty( ctx, OUTFITFOOTERLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OutfitFooterComponent.outFitFooterList</code> attribute.
	 * @return the outFitFooterList
	 */
	public List<CMSLinkTextComponent> getOutFitFooterList()
	{
		return getOutFitFooterList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OutfitFooterComponent.outFitFooterList</code> attribute. 
	 * @param value the outFitFooterList
	 */
	public void setOutFitFooterList(final SessionContext ctx, final List<CMSLinkTextComponent> value)
	{
		setProperty(ctx, OUTFITFOOTERLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OutfitFooterComponent.outFitFooterList</code> attribute. 
	 * @param value the outFitFooterList
	 */
	public void setOutFitFooterList(final List<CMSLinkTextComponent> value)
	{
		setOutFitFooterList( getSession().getSessionContext(), value );
	}
	
}
