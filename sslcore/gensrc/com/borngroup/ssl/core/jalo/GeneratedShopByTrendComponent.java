/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.ShopByTrendComponent ShopByTrendComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedShopByTrendComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>ShopByTrendComponent.brandImageOne</code> attribute **/
	public static final String BRANDIMAGEONE = "brandImageOne";
	/** Qualifier of the <code>ShopByTrendComponent.URLOne</code> attribute **/
	public static final String URLONE = "URLOne";
	/** Qualifier of the <code>ShopByTrendComponent.header</code> attribute **/
	public static final String HEADER = "header";
	/** Qualifier of the <code>ShopByTrendComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>ShopByTrendComponent.URLTwo</code> attribute **/
	public static final String URLTWO = "URLTwo";
	/** Qualifier of the <code>ShopByTrendComponent.brandImageTwo</code> attribute **/
	public static final String BRANDIMAGETWO = "brandImageTwo";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BRANDIMAGEONE, AttributeMode.INITIAL);
		tmp.put(URLONE, AttributeMode.INITIAL);
		tmp.put(HEADER, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(URLTWO, AttributeMode.INITIAL);
		tmp.put(BRANDIMAGETWO, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.brandImageOne</code> attribute.
	 * @return the brandImageOne - Image to display
	 */
	public CMSImageComponent getBrandImageOne(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, BRANDIMAGEONE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.brandImageOne</code> attribute.
	 * @return the brandImageOne - Image to display
	 */
	public CMSImageComponent getBrandImageOne()
	{
		return getBrandImageOne( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.brandImageOne</code> attribute. 
	 * @param value the brandImageOne - Image to display
	 */
	public void setBrandImageOne(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, BRANDIMAGEONE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.brandImageOne</code> attribute. 
	 * @param value the brandImageOne - Image to display
	 */
	public void setBrandImageOne(final CMSImageComponent value)
	{
		setBrandImageOne( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.brandImageTwo</code> attribute.
	 * @return the brandImageTwo - Image to display
	 */
	public CMSImageComponent getBrandImageTwo(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, BRANDIMAGETWO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.brandImageTwo</code> attribute.
	 * @return the brandImageTwo - Image to display
	 */
	public CMSImageComponent getBrandImageTwo()
	{
		return getBrandImageTwo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.brandImageTwo</code> attribute. 
	 * @param value the brandImageTwo - Image to display
	 */
	public void setBrandImageTwo(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, BRANDIMAGETWO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.brandImageTwo</code> attribute. 
	 * @param value the brandImageTwo - Image to display
	 */
	public void setBrandImageTwo(final CMSImageComponent value)
	{
		setBrandImageTwo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.header</code> attribute.
	 * @return the header - Image to display
	 */
	public String getHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.header</code> attribute.
	 * @return the header - Image to display
	 */
	public String getHeader()
	{
		return getHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.header</code> attribute. 
	 * @param value the header - Image to display
	 */
	public void setHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.header</code> attribute. 
	 * @param value the header - Image to display
	 */
	public void setHeader(final String value)
	{
		setHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.text</code> attribute.
	 * @return the text - Url link for the image
	 */
	public String getText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.text</code> attribute.
	 * @return the text - Url link for the image
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.text</code> attribute. 
	 * @param value the text - Url link for the image
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.text</code> attribute. 
	 * @param value the text - Url link for the image
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.URLOne</code> attribute.
	 * @return the URLOne - Url link for the image
	 */
	public String getURLOne(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URLONE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.URLOne</code> attribute.
	 * @return the URLOne - Url link for the image
	 */
	public String getURLOne()
	{
		return getURLOne( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.URLOne</code> attribute. 
	 * @param value the URLOne - Url link for the image
	 */
	public void setURLOne(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URLONE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.URLOne</code> attribute. 
	 * @param value the URLOne - Url link for the image
	 */
	public void setURLOne(final String value)
	{
		setURLOne( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.URLTwo</code> attribute.
	 * @return the URLTwo - Url link for the image
	 */
	public String getURLTwo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URLTWO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByTrendComponent.URLTwo</code> attribute.
	 * @return the URLTwo - Url link for the image
	 */
	public String getURLTwo()
	{
		return getURLTwo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.URLTwo</code> attribute. 
	 * @param value the URLTwo - Url link for the image
	 */
	public void setURLTwo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URLTWO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByTrendComponent.URLTwo</code> attribute. 
	 * @param value the URLTwo - Url link for the image
	 */
	public void setURLTwo(final String value)
	{
		setURLTwo( getSession().getSessionContext(), value );
	}
	
}
