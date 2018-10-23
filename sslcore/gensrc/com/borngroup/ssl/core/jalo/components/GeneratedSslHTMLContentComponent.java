/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslHTMLContentComponent SslHTMLContentComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslHTMLContentComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SslHTMLContentComponent.htmlContent</code> attribute **/
	public static final String HTMLCONTENT = "htmlContent";
	/** Qualifier of the <code>SslHTMLContentComponent.cssContent</code> attribute **/
	public static final String CSSCONTENT = "cssContent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HTMLCONTENT, AttributeMode.INITIAL);
		tmp.put(CSSCONTENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslHTMLContentComponent.cssContent</code> attribute.
	 * @return the cssContent
	 */
	public String getCssContent(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CSSCONTENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslHTMLContentComponent.cssContent</code> attribute.
	 * @return the cssContent
	 */
	public String getCssContent()
	{
		return getCssContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslHTMLContentComponent.cssContent</code> attribute. 
	 * @param value the cssContent
	 */
	public void setCssContent(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CSSCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslHTMLContentComponent.cssContent</code> attribute. 
	 * @param value the cssContent
	 */
	public void setCssContent(final String value)
	{
		setCssContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslHTMLContentComponent.htmlContent</code> attribute.
	 * @return the htmlContent
	 */
	public String getHtmlContent(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HTMLCONTENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslHTMLContentComponent.htmlContent</code> attribute.
	 * @return the htmlContent
	 */
	public String getHtmlContent()
	{
		return getHtmlContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslHTMLContentComponent.htmlContent</code> attribute. 
	 * @param value the htmlContent
	 */
	public void setHtmlContent(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HTMLCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslHTMLContentComponent.htmlContent</code> attribute. 
	 * @param value the htmlContent
	 */
	public void setHtmlContent(final String value)
	{
		setHtmlContent( getSession().getSessionContext(), value );
	}
	
}
