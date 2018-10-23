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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem DisableReturnEntries}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedDisableReturnEntries extends GenericItem
{
	/** Qualifier of the <code>DisableReturnEntries.sliderNo</code> attribute **/
	public static final String SLIDERNO = "sliderNo";
	/** Qualifier of the <code>DisableReturnEntries.variantType</code> attribute **/
	public static final String VARIANTTYPE = "variantType";
	/** Qualifier of the <code>DisableReturnEntries.variantCode</code> attribute **/
	public static final String VARIANTCODE = "variantCode";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(SLIDERNO, AttributeMode.INITIAL);
		tmp.put(VARIANTTYPE, AttributeMode.INITIAL);
		tmp.put(VARIANTCODE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DisableReturnEntries.sliderNo</code> attribute.
	 * @return the sliderNo - Slider No
	 */
	public String getSliderNo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SLIDERNO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DisableReturnEntries.sliderNo</code> attribute.
	 * @return the sliderNo - Slider No
	 */
	public String getSliderNo()
	{
		return getSliderNo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DisableReturnEntries.sliderNo</code> attribute. 
	 * @param value the sliderNo - Slider No
	 */
	public void setSliderNo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SLIDERNO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DisableReturnEntries.sliderNo</code> attribute. 
	 * @param value the sliderNo - Slider No
	 */
	public void setSliderNo(final String value)
	{
		setSliderNo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DisableReturnEntries.variantCode</code> attribute.
	 * @return the variantCode - Slider No
	 */
	public String getVariantCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, VARIANTCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DisableReturnEntries.variantCode</code> attribute.
	 * @return the variantCode - Slider No
	 */
	public String getVariantCode()
	{
		return getVariantCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DisableReturnEntries.variantCode</code> attribute. 
	 * @param value the variantCode - Slider No
	 */
	public void setVariantCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, VARIANTCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DisableReturnEntries.variantCode</code> attribute. 
	 * @param value the variantCode - Slider No
	 */
	public void setVariantCode(final String value)
	{
		setVariantCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DisableReturnEntries.variantType</code> attribute.
	 * @return the variantType - Slider No
	 */
	public String getVariantType(final SessionContext ctx)
	{
		return (String)getProperty( ctx, VARIANTTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DisableReturnEntries.variantType</code> attribute.
	 * @return the variantType - Slider No
	 */
	public String getVariantType()
	{
		return getVariantType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DisableReturnEntries.variantType</code> attribute. 
	 * @param value the variantType - Slider No
	 */
	public void setVariantType(final SessionContext ctx, final String value)
	{
		setProperty(ctx, VARIANTTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DisableReturnEntries.variantType</code> attribute. 
	 * @param value the variantType - Slider No
	 */
	public void setVariantType(final String value)
	{
		setVariantType( getSession().getSessionContext(), value );
	}
	
}
