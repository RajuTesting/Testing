/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLButtonWidgetComponent SSLButtonWidgetComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLButtonWidgetComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLButtonWidgetComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>SSLButtonWidgetComponent.backgroundColor</code> attribute **/
	public static final String BACKGROUNDCOLOR = "backgroundColor";
	/** Qualifier of the <code>SSLButtonWidgetComponent.opacity</code> attribute **/
	public static final String OPACITY = "opacity";
	/** Qualifier of the <code>SSLButtonWidgetComponent.linkComponent</code> attribute **/
	public static final String LINKCOMPONENT = "linkComponent";
	/** Qualifier of the <code>SSLButtonWidgetComponent.color</code> attribute **/
	public static final String COLOR = "color";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(BACKGROUNDCOLOR, AttributeMode.INITIAL);
		tmp.put(OPACITY, AttributeMode.INITIAL);
		tmp.put(LINKCOMPONENT, AttributeMode.INITIAL);
		tmp.put(COLOR, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.backgroundColor</code> attribute.
	 * @return the backgroundColor - For editing the background color
	 */
	public String getBackgroundColor(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BACKGROUNDCOLOR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.backgroundColor</code> attribute.
	 * @return the backgroundColor - For editing the background color
	 */
	public String getBackgroundColor()
	{
		return getBackgroundColor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.backgroundColor</code> attribute. 
	 * @param value the backgroundColor - For editing the background color
	 */
	public void setBackgroundColor(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BACKGROUNDCOLOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.backgroundColor</code> attribute. 
	 * @param value the backgroundColor - For editing the background color
	 */
	public void setBackgroundColor(final String value)
	{
		setBackgroundColor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.color</code> attribute.
	 * @return the color - For editing the color
	 */
	public String getColor(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COLOR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.color</code> attribute.
	 * @return the color - For editing the color
	 */
	public String getColor()
	{
		return getColor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.color</code> attribute. 
	 * @param value the color - For editing the color
	 */
	public void setColor(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COLOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.color</code> attribute. 
	 * @param value the color - For editing the color
	 */
	public void setColor(final String value)
	{
		setColor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.linkComponent</code> attribute.
	 * @return the linkComponent - Link
	 */
	public CMSLinkComponent getLinkComponent(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, LINKCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.linkComponent</code> attribute.
	 * @return the linkComponent - Link
	 */
	public CMSLinkComponent getLinkComponent()
	{
		return getLinkComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.linkComponent</code> attribute. 
	 * @param value the linkComponent - Link
	 */
	public void setLinkComponent(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, LINKCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.linkComponent</code> attribute. 
	 * @param value the linkComponent - Link
	 */
	public void setLinkComponent(final CMSLinkComponent value)
	{
		setLinkComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.opacity</code> attribute.
	 * @return the opacity - For editing the opacity
	 */
	public Double getOpacity(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, OPACITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.opacity</code> attribute.
	 * @return the opacity - For editing the opacity
	 */
	public Double getOpacity()
	{
		return getOpacity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.opacity</code> attribute. 
	 * @return the opacity - For editing the opacity
	 */
	public double getOpacityAsPrimitive(final SessionContext ctx)
	{
		Double value = getOpacity( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.opacity</code> attribute. 
	 * @return the opacity - For editing the opacity
	 */
	public double getOpacityAsPrimitive()
	{
		return getOpacityAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.opacity</code> attribute. 
	 * @param value the opacity - For editing the opacity
	 */
	public void setOpacity(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, OPACITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.opacity</code> attribute. 
	 * @param value the opacity - For editing the opacity
	 */
	public void setOpacity(final Double value)
	{
		setOpacity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.opacity</code> attribute. 
	 * @param value the opacity - For editing the opacity
	 */
	public void setOpacity(final SessionContext ctx, final double value)
	{
		setOpacity( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.opacity</code> attribute. 
	 * @param value the opacity - For editing the opacity
	 */
	public void setOpacity(final double value)
	{
		setOpacity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.text</code> attribute.
	 * @return the text - For editing the text
	 */
	public String getText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLButtonWidgetComponent.text</code> attribute.
	 * @return the text - For editing the text
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.text</code> attribute. 
	 * @param value the text - For editing the text
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLButtonWidgetComponent.text</code> attribute. 
	 * @param value the text - For editing the text
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
}
