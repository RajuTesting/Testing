/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLPDPSimpleCMSTabComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSlCMSTabContainer SSlCMSTabContainer}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSlCMSTabContainer extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSlCMSTabContainer.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>SSlCMSTabContainer.tabComponentlist</code> attribute **/
	public static final String TABCOMPONENTLIST = "tabComponentlist";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(TABCOMPONENTLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSlCMSTabContainer.heading</code> attribute.
	 * @return the heading
	 */
	public String getHeading(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSlCMSTabContainer.heading</code> attribute.
	 * @return the heading
	 */
	public String getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSlCMSTabContainer.heading</code> attribute. 
	 * @param value the heading
	 */
	public void setHeading(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSlCMSTabContainer.heading</code> attribute. 
	 * @param value the heading
	 */
	public void setHeading(final String value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSlCMSTabContainer.tabComponentlist</code> attribute.
	 * @return the tabComponentlist - Tab List for the container.
	 */
	public List<SSLPDPSimpleCMSTabComponent> getTabComponentlist(final SessionContext ctx)
	{
		List<SSLPDPSimpleCMSTabComponent> coll = (List<SSLPDPSimpleCMSTabComponent>)getProperty( ctx, TABCOMPONENTLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSlCMSTabContainer.tabComponentlist</code> attribute.
	 * @return the tabComponentlist - Tab List for the container.
	 */
	public List<SSLPDPSimpleCMSTabComponent> getTabComponentlist()
	{
		return getTabComponentlist( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSlCMSTabContainer.tabComponentlist</code> attribute. 
	 * @param value the tabComponentlist - Tab List for the container.
	 */
	public void setTabComponentlist(final SessionContext ctx, final List<SSLPDPSimpleCMSTabComponent> value)
	{
		setProperty(ctx, TABCOMPONENTLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSlCMSTabContainer.tabComponentlist</code> attribute. 
	 * @param value the tabComponentlist - Tab List for the container.
	 */
	public void setTabComponentlist(final List<SSLPDPSimpleCMSTabComponent> value)
	{
		setTabComponentlist( getSession().getSessionContext(), value );
	}
	
}
