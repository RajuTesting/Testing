/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.CMSMediaParagraphComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent CMSMediaParagraphContainer}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCMSMediaParagraphContainer extends CMSParagraphComponent
{
	/** Qualifier of the <code>CMSMediaParagraphContainer.CMSMediaParagraphlist</code> attribute **/
	public static final String CMSMEDIAPARAGRAPHLIST = "CMSMediaParagraphlist";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSParagraphComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CMSMEDIAPARAGRAPHLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphContainer.CMSMediaParagraphlist</code> attribute.
	 * @return the CMSMediaParagraphlist - Attribute for List of Media Paragraph
	 */
	public List<CMSMediaParagraphComponent> getCMSMediaParagraphlist(final SessionContext ctx)
	{
		List<CMSMediaParagraphComponent> coll = (List<CMSMediaParagraphComponent>)getProperty( ctx, CMSMEDIAPARAGRAPHLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSMediaParagraphContainer.CMSMediaParagraphlist</code> attribute.
	 * @return the CMSMediaParagraphlist - Attribute for List of Media Paragraph
	 */
	public List<CMSMediaParagraphComponent> getCMSMediaParagraphlist()
	{
		return getCMSMediaParagraphlist( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphContainer.CMSMediaParagraphlist</code> attribute. 
	 * @param value the CMSMediaParagraphlist - Attribute for List of Media Paragraph
	 */
	public void setCMSMediaParagraphlist(final SessionContext ctx, final List<CMSMediaParagraphComponent> value)
	{
		setProperty(ctx, CMSMEDIAPARAGRAPHLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSMediaParagraphContainer.CMSMediaParagraphlist</code> attribute. 
	 * @param value the CMSMediaParagraphlist - Attribute for List of Media Paragraph
	 */
	public void setCMSMediaParagraphlist(final List<CMSMediaParagraphComponent> value)
	{
		setCMSMediaParagraphlist( getSession().getSessionContext(), value );
	}
	
}
