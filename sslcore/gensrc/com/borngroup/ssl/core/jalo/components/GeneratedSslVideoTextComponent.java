/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.BrandVideoComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslVideoTextComponent SslVideoTextComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslVideoTextComponent extends BrandVideoComponent
{
	/** Qualifier of the <code>SslVideoTextComponent.height</code> attribute **/
	public static final String HEIGHT = "height";
	/** Qualifier of the <code>SslVideoTextComponent.width</code> attribute **/
	public static final String WIDTH = "width";
	/** Qualifier of the <code>SslVideoTextComponent.componentHeader</code> attribute **/
	public static final String COMPONENTHEADER = "componentHeader";
	/** Qualifier of the <code>SslVideoTextComponent.textParagraph</code> attribute **/
	public static final String TEXTPARAGRAPH = "textParagraph";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(BrandVideoComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEIGHT, AttributeMode.INITIAL);
		tmp.put(WIDTH, AttributeMode.INITIAL);
		tmp.put(COMPONENTHEADER, AttributeMode.INITIAL);
		tmp.put(TEXTPARAGRAPH, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVideoTextComponent.componentHeader</code> attribute.
	 * @return the componentHeader
	 */
	public String getComponentHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COMPONENTHEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVideoTextComponent.componentHeader</code> attribute.
	 * @return the componentHeader
	 */
	public String getComponentHeader()
	{
		return getComponentHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVideoTextComponent.componentHeader</code> attribute. 
	 * @param value the componentHeader
	 */
	public void setComponentHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COMPONENTHEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVideoTextComponent.componentHeader</code> attribute. 
	 * @param value the componentHeader
	 */
	public void setComponentHeader(final String value)
	{
		setComponentHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVideoTextComponent.height</code> attribute.
	 * @return the height - Height of video
	 */
	public String getHeight(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVideoTextComponent.height</code> attribute.
	 * @return the height - Height of video
	 */
	public String getHeight()
	{
		return getHeight( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVideoTextComponent.height</code> attribute. 
	 * @param value the height - Height of video
	 */
	public void setHeight(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVideoTextComponent.height</code> attribute. 
	 * @param value the height - Height of video
	 */
	public void setHeight(final String value)
	{
		setHeight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVideoTextComponent.textParagraph</code> attribute.
	 * @return the textParagraph
	 */
	public String getTextParagraph(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXTPARAGRAPH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVideoTextComponent.textParagraph</code> attribute.
	 * @return the textParagraph
	 */
	public String getTextParagraph()
	{
		return getTextParagraph( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVideoTextComponent.textParagraph</code> attribute. 
	 * @param value the textParagraph
	 */
	public void setTextParagraph(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXTPARAGRAPH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVideoTextComponent.textParagraph</code> attribute. 
	 * @param value the textParagraph
	 */
	public void setTextParagraph(final String value)
	{
		setTextParagraph( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVideoTextComponent.width</code> attribute.
	 * @return the width - width of video
	 */
	public String getWidth(final SessionContext ctx)
	{
		return (String)getProperty( ctx, WIDTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslVideoTextComponent.width</code> attribute.
	 * @return the width - width of video
	 */
	public String getWidth()
	{
		return getWidth( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVideoTextComponent.width</code> attribute. 
	 * @param value the width - width of video
	 */
	public void setWidth(final SessionContext ctx, final String value)
	{
		setProperty(ctx, WIDTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslVideoTextComponent.width</code> attribute. 
	 * @param value the width - width of video
	 */
	public void setWidth(final String value)
	{
		setWidth( getSession().getSessionContext(), value );
	}
	
}
