/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.WomenTopCollectionComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.ShopByComponent ShopByComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedShopByComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>ShopByComponent.header</code> attribute **/
	public static final String HEADER = "header";
	/** Qualifier of the <code>ShopByComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>ShopByComponent.brandImageOne</code> attribute **/
	public static final String BRANDIMAGEONE = "brandImageOne";
	/** Qualifier of the <code>ShopByComponent.URLOne</code> attribute **/
	public static final String URLONE = "URLOne";
	/** Qualifier of the <code>ShopByComponent.textone</code> attribute **/
	public static final String TEXTONE = "textone";
	/** Qualifier of the <code>ShopByComponent.brandImageTwo</code> attribute **/
	public static final String BRANDIMAGETWO = "brandImageTwo";
	/** Qualifier of the <code>ShopByComponent.URLTwo</code> attribute **/
	public static final String URLTWO = "URLTwo";
	/** Qualifier of the <code>ShopByComponent.texttwo</code> attribute **/
	public static final String TEXTTWO = "texttwo";
	/** Qualifier of the <code>ShopByComponent.brandImageThree</code> attribute **/
	public static final String BRANDIMAGETHREE = "brandImageThree";
	/** Qualifier of the <code>ShopByComponent.URLThree</code> attribute **/
	public static final String URLTHREE = "URLThree";
	/** Qualifier of the <code>ShopByComponent.textthree</code> attribute **/
	public static final String TEXTTHREE = "textthree";
	/** Qualifier of the <code>ShopByComponent.brandImageFour</code> attribute **/
	public static final String BRANDIMAGEFOUR = "brandImageFour";
	/** Qualifier of the <code>ShopByComponent.URLFour</code> attribute **/
	public static final String URLFOUR = "URLFour";
	/** Qualifier of the <code>ShopByComponent.shopbytextfour</code> attribute **/
	public static final String SHOPBYTEXTFOUR = "shopbytextfour";
	/** Qualifier of the <code>ShopByComponent.brandImageFive</code> attribute **/
	public static final String BRANDIMAGEFIVE = "brandImageFive";
	/** Qualifier of the <code>ShopByComponent.URLFive</code> attribute **/
	public static final String URLFIVE = "URLFive";
	/** Qualifier of the <code>ShopByComponent.textfive</code> attribute **/
	public static final String TEXTFIVE = "textfive";
	/** Qualifier of the <code>ShopByComponent.womenTopCollectionComponent</code> attribute **/
	public static final String WOMENTOPCOLLECTIONCOMPONENT = "womenTopCollectionComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADER, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(BRANDIMAGEONE, AttributeMode.INITIAL);
		tmp.put(URLONE, AttributeMode.INITIAL);
		tmp.put(TEXTONE, AttributeMode.INITIAL);
		tmp.put(BRANDIMAGETWO, AttributeMode.INITIAL);
		tmp.put(URLTWO, AttributeMode.INITIAL);
		tmp.put(TEXTTWO, AttributeMode.INITIAL);
		tmp.put(BRANDIMAGETHREE, AttributeMode.INITIAL);
		tmp.put(URLTHREE, AttributeMode.INITIAL);
		tmp.put(TEXTTHREE, AttributeMode.INITIAL);
		tmp.put(BRANDIMAGEFOUR, AttributeMode.INITIAL);
		tmp.put(URLFOUR, AttributeMode.INITIAL);
		tmp.put(SHOPBYTEXTFOUR, AttributeMode.INITIAL);
		tmp.put(BRANDIMAGEFIVE, AttributeMode.INITIAL);
		tmp.put(URLFIVE, AttributeMode.INITIAL);
		tmp.put(TEXTFIVE, AttributeMode.INITIAL);
		tmp.put(WOMENTOPCOLLECTIONCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.brandImageFive</code> attribute.
	 * @return the brandImageFive - Image to display
	 */
	public CMSImageComponent getBrandImageFive(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, BRANDIMAGEFIVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.brandImageFive</code> attribute.
	 * @return the brandImageFive - Image to display
	 */
	public CMSImageComponent getBrandImageFive()
	{
		return getBrandImageFive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.brandImageFive</code> attribute. 
	 * @param value the brandImageFive - Image to display
	 */
	public void setBrandImageFive(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, BRANDIMAGEFIVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.brandImageFive</code> attribute. 
	 * @param value the brandImageFive - Image to display
	 */
	public void setBrandImageFive(final CMSImageComponent value)
	{
		setBrandImageFive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.brandImageFour</code> attribute.
	 * @return the brandImageFour - Image to display
	 */
	public CMSImageComponent getBrandImageFour(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, BRANDIMAGEFOUR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.brandImageFour</code> attribute.
	 * @return the brandImageFour - Image to display
	 */
	public CMSImageComponent getBrandImageFour()
	{
		return getBrandImageFour( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.brandImageFour</code> attribute. 
	 * @param value the brandImageFour - Image to display
	 */
	public void setBrandImageFour(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, BRANDIMAGEFOUR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.brandImageFour</code> attribute. 
	 * @param value the brandImageFour - Image to display
	 */
	public void setBrandImageFour(final CMSImageComponent value)
	{
		setBrandImageFour( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.brandImageOne</code> attribute.
	 * @return the brandImageOne - Image to display
	 */
	public CMSImageComponent getBrandImageOne(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, BRANDIMAGEONE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.brandImageOne</code> attribute.
	 * @return the brandImageOne - Image to display
	 */
	public CMSImageComponent getBrandImageOne()
	{
		return getBrandImageOne( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.brandImageOne</code> attribute. 
	 * @param value the brandImageOne - Image to display
	 */
	public void setBrandImageOne(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, BRANDIMAGEONE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.brandImageOne</code> attribute. 
	 * @param value the brandImageOne - Image to display
	 */
	public void setBrandImageOne(final CMSImageComponent value)
	{
		setBrandImageOne( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.brandImageThree</code> attribute.
	 * @return the brandImageThree - Image to display
	 */
	public CMSImageComponent getBrandImageThree(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, BRANDIMAGETHREE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.brandImageThree</code> attribute.
	 * @return the brandImageThree - Image to display
	 */
	public CMSImageComponent getBrandImageThree()
	{
		return getBrandImageThree( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.brandImageThree</code> attribute. 
	 * @param value the brandImageThree - Image to display
	 */
	public void setBrandImageThree(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, BRANDIMAGETHREE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.brandImageThree</code> attribute. 
	 * @param value the brandImageThree - Image to display
	 */
	public void setBrandImageThree(final CMSImageComponent value)
	{
		setBrandImageThree( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.brandImageTwo</code> attribute.
	 * @return the brandImageTwo - Image to display
	 */
	public CMSImageComponent getBrandImageTwo(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, BRANDIMAGETWO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.brandImageTwo</code> attribute.
	 * @return the brandImageTwo - Image to display
	 */
	public CMSImageComponent getBrandImageTwo()
	{
		return getBrandImageTwo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.brandImageTwo</code> attribute. 
	 * @param value the brandImageTwo - Image to display
	 */
	public void setBrandImageTwo(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, BRANDIMAGETWO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.brandImageTwo</code> attribute. 
	 * @param value the brandImageTwo - Image to display
	 */
	public void setBrandImageTwo(final CMSImageComponent value)
	{
		setBrandImageTwo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.header</code> attribute.
	 * @return the header
	 */
	public String getHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.header</code> attribute.
	 * @return the header
	 */
	public String getHeader()
	{
		return getHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.header</code> attribute. 
	 * @param value the header
	 */
	public void setHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.header</code> attribute. 
	 * @param value the header
	 */
	public void setHeader(final String value)
	{
		setHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.shopbytextfour</code> attribute.
	 * @return the shopbytextfour
	 */
	public CMSParagraphComponent getShopbytextfour(final SessionContext ctx)
	{
		return (CMSParagraphComponent)getProperty( ctx, SHOPBYTEXTFOUR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.shopbytextfour</code> attribute.
	 * @return the shopbytextfour
	 */
	public CMSParagraphComponent getShopbytextfour()
	{
		return getShopbytextfour( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.shopbytextfour</code> attribute. 
	 * @param value the shopbytextfour
	 */
	public void setShopbytextfour(final SessionContext ctx, final CMSParagraphComponent value)
	{
		setProperty(ctx, SHOPBYTEXTFOUR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.shopbytextfour</code> attribute. 
	 * @param value the shopbytextfour
	 */
	public void setShopbytextfour(final CMSParagraphComponent value)
	{
		setShopbytextfour( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.text</code> attribute.
	 * @return the text
	 */
	public String getText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.text</code> attribute.
	 * @return the text
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.text</code> attribute. 
	 * @param value the text
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.text</code> attribute. 
	 * @param value the text
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.textfive</code> attribute.
	 * @return the textfive
	 */
	public CMSParagraphComponent getTextfive(final SessionContext ctx)
	{
		return (CMSParagraphComponent)getProperty( ctx, TEXTFIVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.textfive</code> attribute.
	 * @return the textfive
	 */
	public CMSParagraphComponent getTextfive()
	{
		return getTextfive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.textfive</code> attribute. 
	 * @param value the textfive
	 */
	public void setTextfive(final SessionContext ctx, final CMSParagraphComponent value)
	{
		setProperty(ctx, TEXTFIVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.textfive</code> attribute. 
	 * @param value the textfive
	 */
	public void setTextfive(final CMSParagraphComponent value)
	{
		setTextfive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.textone</code> attribute.
	 * @return the textone
	 */
	public CMSParagraphComponent getTextone(final SessionContext ctx)
	{
		return (CMSParagraphComponent)getProperty( ctx, TEXTONE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.textone</code> attribute.
	 * @return the textone
	 */
	public CMSParagraphComponent getTextone()
	{
		return getTextone( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.textone</code> attribute. 
	 * @param value the textone
	 */
	public void setTextone(final SessionContext ctx, final CMSParagraphComponent value)
	{
		setProperty(ctx, TEXTONE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.textone</code> attribute. 
	 * @param value the textone
	 */
	public void setTextone(final CMSParagraphComponent value)
	{
		setTextone( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.textthree</code> attribute.
	 * @return the textthree
	 */
	public CMSParagraphComponent getTextthree(final SessionContext ctx)
	{
		return (CMSParagraphComponent)getProperty( ctx, TEXTTHREE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.textthree</code> attribute.
	 * @return the textthree
	 */
	public CMSParagraphComponent getTextthree()
	{
		return getTextthree( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.textthree</code> attribute. 
	 * @param value the textthree
	 */
	public void setTextthree(final SessionContext ctx, final CMSParagraphComponent value)
	{
		setProperty(ctx, TEXTTHREE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.textthree</code> attribute. 
	 * @param value the textthree
	 */
	public void setTextthree(final CMSParagraphComponent value)
	{
		setTextthree( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.texttwo</code> attribute.
	 * @return the texttwo
	 */
	public CMSParagraphComponent getTexttwo(final SessionContext ctx)
	{
		return (CMSParagraphComponent)getProperty( ctx, TEXTTWO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.texttwo</code> attribute.
	 * @return the texttwo
	 */
	public CMSParagraphComponent getTexttwo()
	{
		return getTexttwo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.texttwo</code> attribute. 
	 * @param value the texttwo
	 */
	public void setTexttwo(final SessionContext ctx, final CMSParagraphComponent value)
	{
		setProperty(ctx, TEXTTWO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.texttwo</code> attribute. 
	 * @param value the texttwo
	 */
	public void setTexttwo(final CMSParagraphComponent value)
	{
		setTexttwo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.URLFive</code> attribute.
	 * @return the URLFive - Url link for the image
	 */
	public String getURLFive(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URLFIVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.URLFive</code> attribute.
	 * @return the URLFive - Url link for the image
	 */
	public String getURLFive()
	{
		return getURLFive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.URLFive</code> attribute. 
	 * @param value the URLFive - Url link for the image
	 */
	public void setURLFive(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URLFIVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.URLFive</code> attribute. 
	 * @param value the URLFive - Url link for the image
	 */
	public void setURLFive(final String value)
	{
		setURLFive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.URLFour</code> attribute.
	 * @return the URLFour - Url link for the image
	 */
	public String getURLFour(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URLFOUR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.URLFour</code> attribute.
	 * @return the URLFour - Url link for the image
	 */
	public String getURLFour()
	{
		return getURLFour( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.URLFour</code> attribute. 
	 * @param value the URLFour - Url link for the image
	 */
	public void setURLFour(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URLFOUR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.URLFour</code> attribute. 
	 * @param value the URLFour - Url link for the image
	 */
	public void setURLFour(final String value)
	{
		setURLFour( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.URLOne</code> attribute.
	 * @return the URLOne - Url link for the image
	 */
	public String getURLOne(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URLONE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.URLOne</code> attribute.
	 * @return the URLOne - Url link for the image
	 */
	public String getURLOne()
	{
		return getURLOne( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.URLOne</code> attribute. 
	 * @param value the URLOne - Url link for the image
	 */
	public void setURLOne(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URLONE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.URLOne</code> attribute. 
	 * @param value the URLOne - Url link for the image
	 */
	public void setURLOne(final String value)
	{
		setURLOne( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.URLThree</code> attribute.
	 * @return the URLThree - Url link for the image
	 */
	public String getURLThree(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URLTHREE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.URLThree</code> attribute.
	 * @return the URLThree - Url link for the image
	 */
	public String getURLThree()
	{
		return getURLThree( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.URLThree</code> attribute. 
	 * @param value the URLThree - Url link for the image
	 */
	public void setURLThree(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URLTHREE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.URLThree</code> attribute. 
	 * @param value the URLThree - Url link for the image
	 */
	public void setURLThree(final String value)
	{
		setURLThree( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.URLTwo</code> attribute.
	 * @return the URLTwo - Url link for the image
	 */
	public String getURLTwo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URLTWO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.URLTwo</code> attribute.
	 * @return the URLTwo - Url link for the image
	 */
	public String getURLTwo()
	{
		return getURLTwo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.URLTwo</code> attribute. 
	 * @param value the URLTwo - Url link for the image
	 */
	public void setURLTwo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URLTWO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.URLTwo</code> attribute. 
	 * @param value the URLTwo - Url link for the image
	 */
	public void setURLTwo(final String value)
	{
		setURLTwo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.womenTopCollectionComponent</code> attribute.
	 * @return the womenTopCollectionComponent
	 */
	public WomenTopCollectionComponent getWomenTopCollectionComponent(final SessionContext ctx)
	{
		return (WomenTopCollectionComponent)getProperty( ctx, WOMENTOPCOLLECTIONCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShopByComponent.womenTopCollectionComponent</code> attribute.
	 * @return the womenTopCollectionComponent
	 */
	public WomenTopCollectionComponent getWomenTopCollectionComponent()
	{
		return getWomenTopCollectionComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.womenTopCollectionComponent</code> attribute. 
	 * @param value the womenTopCollectionComponent
	 */
	public void setWomenTopCollectionComponent(final SessionContext ctx, final WomenTopCollectionComponent value)
	{
		setProperty(ctx, WOMENTOPCOLLECTIONCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShopByComponent.womenTopCollectionComponent</code> attribute. 
	 * @param value the womenTopCollectionComponent
	 */
	public void setWomenTopCollectionComponent(final WomenTopCollectionComponent value)
	{
		setWomenTopCollectionComponent( getSession().getSessionContext(), value );
	}
	
}
