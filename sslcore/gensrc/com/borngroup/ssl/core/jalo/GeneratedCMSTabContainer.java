/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.CMSTabParagraphComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.acceleratorcms.jalo.components.CMSTabParagraphComponent CMSTabContainer}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCMSTabContainer extends CMSTabParagraphComponent
{
	/** Qualifier of the <code>CMSTabContainer.Heading</code> attribute **/
	public static final String HEADING = "Heading";
	/** Qualifier of the <code>CMSTabContainer.CMStablist</code> attribute **/
	public static final String CMSTABLIST = "CMStablist";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSTabParagraphComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(CMSTABLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSTabContainer.CMStablist</code> attribute.
	 * @return the CMStablist - Attribute that Tab List
	 */
	public List<CMSTabParagraphComponent> getCMStablist(final SessionContext ctx)
	{
		List<CMSTabParagraphComponent> coll = (List<CMSTabParagraphComponent>)getProperty( ctx, CMSTABLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSTabContainer.CMStablist</code> attribute.
	 * @return the CMStablist - Attribute that Tab List
	 */
	public List<CMSTabParagraphComponent> getCMStablist()
	{
		return getCMStablist( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSTabContainer.CMStablist</code> attribute. 
	 * @param value the CMStablist - Attribute that Tab List
	 */
	public void setCMStablist(final SessionContext ctx, final List<CMSTabParagraphComponent> value)
	{
		setProperty(ctx, CMSTABLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSTabContainer.CMStablist</code> attribute. 
	 * @param value the CMStablist - Attribute that Tab List
	 */
	public void setCMStablist(final List<CMSTabParagraphComponent> value)
	{
		setCMStablist( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSTabContainer.Heading</code> attribute.
	 * @return the Heading
	 */
	public String getHeading(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSTabContainer.Heading</code> attribute.
	 * @return the Heading
	 */
	public String getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSTabContainer.Heading</code> attribute. 
	 * @param value the Heading
	 */
	public void setHeading(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSTabContainer.Heading</code> attribute. 
	 * @param value the Heading
	 */
	public void setHeading(final String value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
}
