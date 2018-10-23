/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem ColorMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedColorMapping extends GenericItem
{
	/** Qualifier of the <code>ColorMapping.colourCode</code> attribute **/
	public static final String COLOURCODE = "colourCode";
	/** Qualifier of the <code>ColorMapping.colourDesc</code> attribute **/
	public static final String COLOURDESC = "colourDesc";
	/** Qualifier of the <code>ColorMapping.altColorCode</code> attribute **/
	public static final String ALTCOLORCODE = "altColorCode";
	/** Qualifier of the <code>ColorMapping.altColourDesc</code> attribute **/
	public static final String ALTCOLOURDESC = "altColourDesc";
	/** Qualifier of the <code>ColorMapping.colorFamily</code> attribute **/
	public static final String COLORFAMILY = "colorFamily";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(COLOURCODE, AttributeMode.INITIAL);
		tmp.put(COLOURDESC, AttributeMode.INITIAL);
		tmp.put(ALTCOLORCODE, AttributeMode.INITIAL);
		tmp.put(ALTCOLOURDESC, AttributeMode.INITIAL);
		tmp.put(COLORFAMILY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ColorMapping.altColorCode</code> attribute.
	 * @return the altColorCode
	 */
	public String getAltColorCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ALTCOLORCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ColorMapping.altColorCode</code> attribute.
	 * @return the altColorCode
	 */
	public String getAltColorCode()
	{
		return getAltColorCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ColorMapping.altColorCode</code> attribute. 
	 * @param value the altColorCode
	 */
	public void setAltColorCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ALTCOLORCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ColorMapping.altColorCode</code> attribute. 
	 * @param value the altColorCode
	 */
	public void setAltColorCode(final String value)
	{
		setAltColorCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ColorMapping.altColourDesc</code> attribute.
	 * @return the altColourDesc
	 */
	public String getAltColourDesc(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ALTCOLOURDESC);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ColorMapping.altColourDesc</code> attribute.
	 * @return the altColourDesc
	 */
	public String getAltColourDesc()
	{
		return getAltColourDesc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ColorMapping.altColourDesc</code> attribute. 
	 * @param value the altColourDesc
	 */
	public void setAltColourDesc(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ALTCOLOURDESC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ColorMapping.altColourDesc</code> attribute. 
	 * @param value the altColourDesc
	 */
	public void setAltColourDesc(final String value)
	{
		setAltColourDesc( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ColorMapping.colorFamily</code> attribute.
	 * @return the colorFamily
	 */
	public String getColorFamily(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COLORFAMILY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ColorMapping.colorFamily</code> attribute.
	 * @return the colorFamily
	 */
	public String getColorFamily()
	{
		return getColorFamily( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ColorMapping.colorFamily</code> attribute. 
	 * @param value the colorFamily
	 */
	public void setColorFamily(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COLORFAMILY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ColorMapping.colorFamily</code> attribute. 
	 * @param value the colorFamily
	 */
	public void setColorFamily(final String value)
	{
		setColorFamily( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ColorMapping.colourCode</code> attribute.
	 * @return the colourCode
	 */
	public String getColourCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COLOURCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ColorMapping.colourCode</code> attribute.
	 * @return the colourCode
	 */
	public String getColourCode()
	{
		return getColourCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ColorMapping.colourCode</code> attribute. 
	 * @param value the colourCode
	 */
	public void setColourCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COLOURCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ColorMapping.colourCode</code> attribute. 
	 * @param value the colourCode
	 */
	public void setColourCode(final String value)
	{
		setColourCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ColorMapping.colourDesc</code> attribute.
	 * @return the colourDesc
	 */
	public String getColourDesc(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COLOURDESC);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ColorMapping.colourDesc</code> attribute.
	 * @return the colourDesc
	 */
	public String getColourDesc()
	{
		return getColourDesc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ColorMapping.colourDesc</code> attribute. 
	 * @param value the colourDesc
	 */
	public void setColourDesc(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COLOURDESC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ColorMapping.colourDesc</code> attribute. 
	 * @param value the colourDesc
	 */
	public void setColourDesc(final String value)
	{
		setColourDesc( getSession().getSessionContext(), value );
	}
	
}
