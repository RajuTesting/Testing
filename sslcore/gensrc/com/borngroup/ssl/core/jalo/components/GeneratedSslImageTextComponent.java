/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleResponsiveBannerComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslImageTextComponent SslImageTextComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslImageTextComponent extends SimpleResponsiveBannerComponent
{
	/** Qualifier of the <code>SslImageTextComponent.componentHeader</code> attribute **/
	public static final String COMPONENTHEADER = "componentHeader";
	/** Qualifier of the <code>SslImageTextComponent.textHeading</code> attribute **/
	public static final String TEXTHEADING = "textHeading";
	/** Qualifier of the <code>SslImageTextComponent.textParagraph</code> attribute **/
	public static final String TEXTPARAGRAPH = "textParagraph";
	/** Qualifier of the <code>SslImageTextComponent.imagePosition</code> attribute **/
	public static final String IMAGEPOSITION = "imagePosition";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleResponsiveBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(COMPONENTHEADER, AttributeMode.INITIAL);
		tmp.put(TEXTHEADING, AttributeMode.INITIAL);
		tmp.put(TEXTPARAGRAPH, AttributeMode.INITIAL);
		tmp.put(IMAGEPOSITION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageTextComponent.componentHeader</code> attribute.
	 * @return the componentHeader
	 */
	public String getComponentHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COMPONENTHEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageTextComponent.componentHeader</code> attribute.
	 * @return the componentHeader
	 */
	public String getComponentHeader()
	{
		return getComponentHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageTextComponent.componentHeader</code> attribute. 
	 * @param value the componentHeader
	 */
	public void setComponentHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COMPONENTHEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageTextComponent.componentHeader</code> attribute. 
	 * @param value the componentHeader
	 */
	public void setComponentHeader(final String value)
	{
		setComponentHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageTextComponent.imagePosition</code> attribute.
	 * @return the imagePosition - Display Position of Image
	 */
	public EnumerationValue getImagePosition(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, IMAGEPOSITION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageTextComponent.imagePosition</code> attribute.
	 * @return the imagePosition - Display Position of Image
	 */
	public EnumerationValue getImagePosition()
	{
		return getImagePosition( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageTextComponent.imagePosition</code> attribute. 
	 * @param value the imagePosition - Display Position of Image
	 */
	public void setImagePosition(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, IMAGEPOSITION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageTextComponent.imagePosition</code> attribute. 
	 * @param value the imagePosition - Display Position of Image
	 */
	public void setImagePosition(final EnumerationValue value)
	{
		setImagePosition( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageTextComponent.textHeading</code> attribute.
	 * @return the textHeading
	 */
	public String getTextHeading(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXTHEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageTextComponent.textHeading</code> attribute.
	 * @return the textHeading
	 */
	public String getTextHeading()
	{
		return getTextHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageTextComponent.textHeading</code> attribute. 
	 * @param value the textHeading
	 */
	public void setTextHeading(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXTHEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageTextComponent.textHeading</code> attribute. 
	 * @param value the textHeading
	 */
	public void setTextHeading(final String value)
	{
		setTextHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageTextComponent.textParagraph</code> attribute.
	 * @return the textParagraph
	 */
	public String getTextParagraph(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXTPARAGRAPH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslImageTextComponent.textParagraph</code> attribute.
	 * @return the textParagraph
	 */
	public String getTextParagraph()
	{
		return getTextParagraph( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageTextComponent.textParagraph</code> attribute. 
	 * @param value the textParagraph
	 */
	public void setTextParagraph(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXTPARAGRAPH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslImageTextComponent.textParagraph</code> attribute. 
	 * @param value the textParagraph
	 */
	public void setTextParagraph(final String value)
	{
		setTextParagraph( getSession().getSessionContext(), value );
	}
	
}
