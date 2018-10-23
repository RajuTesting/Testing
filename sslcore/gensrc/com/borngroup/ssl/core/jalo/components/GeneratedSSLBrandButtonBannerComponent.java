/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.AbstractMediaContainerComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLBrandButtonBannerComponent SSLBrandButtonBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBrandButtonBannerComponent extends AbstractMediaContainerComponent
{
	/** Qualifier of the <code>SSLBrandButtonBannerComponent.link</code> attribute **/
	public static final String LINK = "link";
	/** Qualifier of the <code>SSLBrandButtonBannerComponent.activeFrom</code> attribute **/
	public static final String ACTIVEFROM = "activeFrom";
	/** Qualifier of the <code>SSLBrandButtonBannerComponent.activeUntil</code> attribute **/
	public static final String ACTIVEUNTIL = "activeUntil";
	/** Qualifier of the <code>SSLBrandButtonBannerComponent.buttonBeforeText</code> attribute **/
	public static final String BUTTONBEFORETEXT = "buttonBeforeText";
	/** Qualifier of the <code>SSLBrandButtonBannerComponent.buttonAfterText</code> attribute **/
	public static final String BUTTONAFTERTEXT = "buttonAfterText";
	/** Qualifier of the <code>SSLBrandButtonBannerComponent.leftAlignment</code> attribute **/
	public static final String LEFTALIGNMENT = "leftAlignment";
	/** Qualifier of the <code>SSLBrandButtonBannerComponent.rightAlignment</code> attribute **/
	public static final String RIGHTALIGNMENT = "rightAlignment";
	/** Qualifier of the <code>SSLBrandButtonBannerComponent.topAlignment</code> attribute **/
	public static final String TOPALIGNMENT = "topAlignment";
	/** Qualifier of the <code>SSLBrandButtonBannerComponent.bottomAlignment</code> attribute **/
	public static final String BOTTOMALIGNMENT = "bottomAlignment";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractMediaContainerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LINK, AttributeMode.INITIAL);
		tmp.put(ACTIVEFROM, AttributeMode.INITIAL);
		tmp.put(ACTIVEUNTIL, AttributeMode.INITIAL);
		tmp.put(BUTTONBEFORETEXT, AttributeMode.INITIAL);
		tmp.put(BUTTONAFTERTEXT, AttributeMode.INITIAL);
		tmp.put(LEFTALIGNMENT, AttributeMode.INITIAL);
		tmp.put(RIGHTALIGNMENT, AttributeMode.INITIAL);
		tmp.put(TOPALIGNMENT, AttributeMode.INITIAL);
		tmp.put(BOTTOMALIGNMENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEFROM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom()
	{
		return getActiveFrom( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEFROM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final Date value)
	{
		setActiveFrom( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEUNTIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil()
	{
		return getActiveUntil( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEUNTIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final Date value)
	{
		setActiveUntil( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.bottomAlignment</code> attribute.
	 * @return the bottomAlignment
	 */
	public Double getBottomAlignment(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, BOTTOMALIGNMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.bottomAlignment</code> attribute.
	 * @return the bottomAlignment
	 */
	public Double getBottomAlignment()
	{
		return getBottomAlignment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.bottomAlignment</code> attribute. 
	 * @return the bottomAlignment
	 */
	public double getBottomAlignmentAsPrimitive(final SessionContext ctx)
	{
		Double value = getBottomAlignment( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.bottomAlignment</code> attribute. 
	 * @return the bottomAlignment
	 */
	public double getBottomAlignmentAsPrimitive()
	{
		return getBottomAlignmentAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.bottomAlignment</code> attribute. 
	 * @param value the bottomAlignment
	 */
	public void setBottomAlignment(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, BOTTOMALIGNMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.bottomAlignment</code> attribute. 
	 * @param value the bottomAlignment
	 */
	public void setBottomAlignment(final Double value)
	{
		setBottomAlignment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.bottomAlignment</code> attribute. 
	 * @param value the bottomAlignment
	 */
	public void setBottomAlignment(final SessionContext ctx, final double value)
	{
		setBottomAlignment( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.bottomAlignment</code> attribute. 
	 * @param value the bottomAlignment
	 */
	public void setBottomAlignment(final double value)
	{
		setBottomAlignment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.buttonAfterText</code> attribute.
	 * @return the buttonAfterText
	 */
	public String getButtonAfterText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BUTTONAFTERTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.buttonAfterText</code> attribute.
	 * @return the buttonAfterText
	 */
	public String getButtonAfterText()
	{
		return getButtonAfterText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.buttonAfterText</code> attribute. 
	 * @param value the buttonAfterText
	 */
	public void setButtonAfterText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BUTTONAFTERTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.buttonAfterText</code> attribute. 
	 * @param value the buttonAfterText
	 */
	public void setButtonAfterText(final String value)
	{
		setButtonAfterText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.buttonBeforeText</code> attribute.
	 * @return the buttonBeforeText
	 */
	public String getButtonBeforeText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BUTTONBEFORETEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.buttonBeforeText</code> attribute.
	 * @return the buttonBeforeText
	 */
	public String getButtonBeforeText()
	{
		return getButtonBeforeText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.buttonBeforeText</code> attribute. 
	 * @param value the buttonBeforeText
	 */
	public void setButtonBeforeText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BUTTONBEFORETEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.buttonBeforeText</code> attribute. 
	 * @param value the buttonBeforeText
	 */
	public void setButtonBeforeText(final String value)
	{
		setButtonBeforeText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.leftAlignment</code> attribute.
	 * @return the leftAlignment
	 */
	public Double getLeftAlignment(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, LEFTALIGNMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.leftAlignment</code> attribute.
	 * @return the leftAlignment
	 */
	public Double getLeftAlignment()
	{
		return getLeftAlignment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.leftAlignment</code> attribute. 
	 * @return the leftAlignment
	 */
	public double getLeftAlignmentAsPrimitive(final SessionContext ctx)
	{
		Double value = getLeftAlignment( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.leftAlignment</code> attribute. 
	 * @return the leftAlignment
	 */
	public double getLeftAlignmentAsPrimitive()
	{
		return getLeftAlignmentAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.leftAlignment</code> attribute. 
	 * @param value the leftAlignment
	 */
	public void setLeftAlignment(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, LEFTALIGNMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.leftAlignment</code> attribute. 
	 * @param value the leftAlignment
	 */
	public void setLeftAlignment(final Double value)
	{
		setLeftAlignment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.leftAlignment</code> attribute. 
	 * @param value the leftAlignment
	 */
	public void setLeftAlignment(final SessionContext ctx, final double value)
	{
		setLeftAlignment( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.leftAlignment</code> attribute. 
	 * @param value the leftAlignment
	 */
	public void setLeftAlignment(final double value)
	{
		setLeftAlignment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.link</code> attribute.
	 * @return the link - Link
	 */
	public String getLink(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.link</code> attribute.
	 * @return the link - Link
	 */
	public String getLink()
	{
		return getLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.link</code> attribute. 
	 * @param value the link - Link
	 */
	public void setLink(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.link</code> attribute. 
	 * @param value the link - Link
	 */
	public void setLink(final String value)
	{
		setLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.rightAlignment</code> attribute.
	 * @return the rightAlignment
	 */
	public Double getRightAlignment(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, RIGHTALIGNMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.rightAlignment</code> attribute.
	 * @return the rightAlignment
	 */
	public Double getRightAlignment()
	{
		return getRightAlignment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.rightAlignment</code> attribute. 
	 * @return the rightAlignment
	 */
	public double getRightAlignmentAsPrimitive(final SessionContext ctx)
	{
		Double value = getRightAlignment( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.rightAlignment</code> attribute. 
	 * @return the rightAlignment
	 */
	public double getRightAlignmentAsPrimitive()
	{
		return getRightAlignmentAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.rightAlignment</code> attribute. 
	 * @param value the rightAlignment
	 */
	public void setRightAlignment(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, RIGHTALIGNMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.rightAlignment</code> attribute. 
	 * @param value the rightAlignment
	 */
	public void setRightAlignment(final Double value)
	{
		setRightAlignment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.rightAlignment</code> attribute. 
	 * @param value the rightAlignment
	 */
	public void setRightAlignment(final SessionContext ctx, final double value)
	{
		setRightAlignment( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.rightAlignment</code> attribute. 
	 * @param value the rightAlignment
	 */
	public void setRightAlignment(final double value)
	{
		setRightAlignment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.topAlignment</code> attribute.
	 * @return the topAlignment
	 */
	public Double getTopAlignment(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, TOPALIGNMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.topAlignment</code> attribute.
	 * @return the topAlignment
	 */
	public Double getTopAlignment()
	{
		return getTopAlignment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.topAlignment</code> attribute. 
	 * @return the topAlignment
	 */
	public double getTopAlignmentAsPrimitive(final SessionContext ctx)
	{
		Double value = getTopAlignment( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandButtonBannerComponent.topAlignment</code> attribute. 
	 * @return the topAlignment
	 */
	public double getTopAlignmentAsPrimitive()
	{
		return getTopAlignmentAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.topAlignment</code> attribute. 
	 * @param value the topAlignment
	 */
	public void setTopAlignment(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, TOPALIGNMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.topAlignment</code> attribute. 
	 * @param value the topAlignment
	 */
	public void setTopAlignment(final Double value)
	{
		setTopAlignment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.topAlignment</code> attribute. 
	 * @param value the topAlignment
	 */
	public void setTopAlignment(final SessionContext ctx, final double value)
	{
		setTopAlignment( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandButtonBannerComponent.topAlignment</code> attribute. 
	 * @param value the topAlignment
	 */
	public void setTopAlignment(final double value)
	{
		setTopAlignment( getSession().getSessionContext(), value );
	}
	
}
