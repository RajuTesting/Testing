/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.variants.jalo.VariantProduct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.ApparelStyleVariantProduct ApparelStyleVariantProduct}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedApparelStyleVariantProduct extends VariantProduct
{
	/** Qualifier of the <code>ApparelStyleVariantProduct.style</code> attribute **/
	public static final String STYLE = "style";
	/** Qualifier of the <code>ApparelStyleVariantProduct.swatchColors</code> attribute **/
	public static final String SWATCHCOLORS = "swatchColors";
	/** Qualifier of the <code>ApparelStyleVariantProduct.colourCode</code> attribute **/
	public static final String COLOURCODE = "colourCode";
	/** Qualifier of the <code>ApparelStyleVariantProduct.colorName</code> attribute **/
	public static final String COLORNAME = "colorName";
	/** Qualifier of the <code>ApparelStyleVariantProduct.sizeCode</code> attribute **/
	public static final String SIZECODE = "sizeCode";
	/** Qualifier of the <code>ApparelStyleVariantProduct.colorSwatchImage</code> attribute **/
	public static final String COLORSWATCHIMAGE = "colorSwatchImage";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(VariantProduct.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(STYLE, AttributeMode.INITIAL);
		tmp.put(SWATCHCOLORS, AttributeMode.INITIAL);
		tmp.put(COLOURCODE, AttributeMode.INITIAL);
		tmp.put(COLORNAME, AttributeMode.INITIAL);
		tmp.put(SIZECODE, AttributeMode.INITIAL);
		tmp.put(COLORSWATCHIMAGE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.colorName</code> attribute.
	 * @return the colorName
	 */
	public String getColorName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COLORNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.colorName</code> attribute.
	 * @return the colorName
	 */
	public String getColorName()
	{
		return getColorName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.colorName</code> attribute. 
	 * @param value the colorName
	 */
	public void setColorName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COLORNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.colorName</code> attribute. 
	 * @param value the colorName
	 */
	public void setColorName(final String value)
	{
		setColorName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.colorSwatchImage</code> attribute.
	 * @return the colorSwatchImage
	 */
	public Media getColorSwatchImage(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, COLORSWATCHIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.colorSwatchImage</code> attribute.
	 * @return the colorSwatchImage
	 */
	public Media getColorSwatchImage()
	{
		return getColorSwatchImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.colorSwatchImage</code> attribute. 
	 * @param value the colorSwatchImage
	 */
	public void setColorSwatchImage(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, COLORSWATCHIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.colorSwatchImage</code> attribute. 
	 * @param value the colorSwatchImage
	 */
	public void setColorSwatchImage(final Media value)
	{
		setColorSwatchImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.colourCode</code> attribute.
	 * @return the colourCode - Transformed using Color Lookup. Pick Alt_Colour_CD
	 *                             of ColorMapping
	 */
	public String getColourCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COLOURCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.colourCode</code> attribute.
	 * @return the colourCode - Transformed using Color Lookup. Pick Alt_Colour_CD
	 *                             of ColorMapping
	 */
	public String getColourCode()
	{
		return getColourCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.colourCode</code> attribute. 
	 * @param value the colourCode - Transformed using Color Lookup. Pick Alt_Colour_CD
	 *                             of ColorMapping
	 */
	public void setColourCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COLOURCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.colourCode</code> attribute. 
	 * @param value the colourCode - Transformed using Color Lookup. Pick Alt_Colour_CD
	 *                             of ColorMapping
	 */
	public void setColourCode(final String value)
	{
		setColourCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.sizeCode</code> attribute.
	 * @return the sizeCode
	 */
	public String getSizeCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SIZECODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.sizeCode</code> attribute.
	 * @return the sizeCode
	 */
	public String getSizeCode()
	{
		return getSizeCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.sizeCode</code> attribute. 
	 * @param value the sizeCode
	 */
	public void setSizeCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SIZECODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.sizeCode</code> attribute. 
	 * @param value the sizeCode
	 */
	public void setSizeCode(final String value)
	{
		setSizeCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.style</code> attribute.
	 * @return the style - Transform using Color Lookup. Pick Alt_ColourDesc of
	 *                             ColorMapping
	 */
	public String getStyle(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedApparelStyleVariantProduct.getStyle requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, STYLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.style</code> attribute.
	 * @return the style - Transform using Color Lookup. Pick Alt_ColourDesc of
	 *                             ColorMapping
	 */
	public String getStyle()
	{
		return getStyle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.style</code> attribute. 
	 * @return the localized style - Transform using Color Lookup. Pick Alt_ColourDesc of
	 *                             ColorMapping
	 */
	public Map<Language,String> getAllStyle(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,STYLE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.style</code> attribute. 
	 * @return the localized style - Transform using Color Lookup. Pick Alt_ColourDesc of
	 *                             ColorMapping
	 */
	public Map<Language,String> getAllStyle()
	{
		return getAllStyle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.style</code> attribute. 
	 * @param value the style - Transform using Color Lookup. Pick Alt_ColourDesc of
	 *                             ColorMapping
	 */
	public void setStyle(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedApparelStyleVariantProduct.setStyle requires a session language", 0 );
		}
		setLocalizedProperty(ctx, STYLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.style</code> attribute. 
	 * @param value the style - Transform using Color Lookup. Pick Alt_ColourDesc of
	 *                             ColorMapping
	 */
	public void setStyle(final String value)
	{
		setStyle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.style</code> attribute. 
	 * @param value the style - Transform using Color Lookup. Pick Alt_ColourDesc of
	 *                             ColorMapping
	 */
	public void setAllStyle(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,STYLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.style</code> attribute. 
	 * @param value the style - Transform using Color Lookup. Pick Alt_ColourDesc of
	 *                             ColorMapping
	 */
	public void setAllStyle(final Map<Language,String> value)
	{
		setAllStyle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.swatchColors</code> attribute.
	 * @return the swatchColors - A normalized color mapping to a standardized
	 *                             front-end navigable name.
	 */
	public Set<EnumerationValue> getSwatchColors(final SessionContext ctx)
	{
		Set<EnumerationValue> coll = (Set<EnumerationValue>)getProperty( ctx, SWATCHCOLORS);
		return coll != null ? coll : Collections.EMPTY_SET;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelStyleVariantProduct.swatchColors</code> attribute.
	 * @return the swatchColors - A normalized color mapping to a standardized
	 *                             front-end navigable name.
	 */
	public Set<EnumerationValue> getSwatchColors()
	{
		return getSwatchColors( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.swatchColors</code> attribute. 
	 * @param value the swatchColors - A normalized color mapping to a standardized
	 *                             front-end navigable name.
	 */
	public void setSwatchColors(final SessionContext ctx, final Set<EnumerationValue> value)
	{
		setProperty(ctx, SWATCHCOLORS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelStyleVariantProduct.swatchColors</code> attribute. 
	 * @param value the swatchColors - A normalized color mapping to a standardized
	 *                             front-end navigable name.
	 */
	public void setSwatchColors(final Set<EnumerationValue> value)
	{
		setSwatchColors( getSession().getSessionContext(), value );
	}
	
}
