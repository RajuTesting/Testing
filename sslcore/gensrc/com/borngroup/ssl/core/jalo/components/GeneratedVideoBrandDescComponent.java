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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.VideoBrandDescComponent VideoBrandDescComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedVideoBrandDescComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>VideoBrandDescComponent.textParagraph</code> attribute **/
	public static final String TEXTPARAGRAPH = "textParagraph";
	/** Qualifier of the <code>VideoBrandDescComponent.url</code> attribute **/
	public static final String URL = "url";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TEXTPARAGRAPH, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VideoBrandDescComponent.textParagraph</code> attribute.
	 * @return the textParagraph - Text including HTML
	 */
	public String getTextParagraph(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXTPARAGRAPH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VideoBrandDescComponent.textParagraph</code> attribute.
	 * @return the textParagraph - Text including HTML
	 */
	public String getTextParagraph()
	{
		return getTextParagraph( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VideoBrandDescComponent.textParagraph</code> attribute. 
	 * @param value the textParagraph - Text including HTML
	 */
	public void setTextParagraph(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXTPARAGRAPH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VideoBrandDescComponent.textParagraph</code> attribute. 
	 * @param value the textParagraph - Text including HTML
	 */
	public void setTextParagraph(final String value)
	{
		setTextParagraph( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VideoBrandDescComponent.url</code> attribute.
	 * @return the url - Youtube embed Url
	 */
	public String getUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VideoBrandDescComponent.url</code> attribute.
	 * @return the url - Youtube embed Url
	 */
	public String getUrl()
	{
		return getUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VideoBrandDescComponent.url</code> attribute. 
	 * @param value the url - Youtube embed Url
	 */
	public void setUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VideoBrandDescComponent.url</code> attribute. 
	 * @param value the url - Youtube embed Url
	 */
	public void setUrl(final String value)
	{
		setUrl( getSession().getSessionContext(), value );
	}
	
}
