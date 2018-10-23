/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.ApparelStyleVariantProduct;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.ApparelSizeVariantProduct ApparelSizeVariantProduct}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedApparelSizeVariantProduct extends ApparelStyleVariantProduct
{
	/** Qualifier of the <code>ApparelSizeVariantProduct.size</code> attribute **/
	public static final String SIZE = "size";
	/** Qualifier of the <code>ApparelSizeVariantProduct.alt_size_desc</code> attribute **/
	public static final String ALT_SIZE_DESC = "alt_size_desc";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(ApparelStyleVariantProduct.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SIZE, AttributeMode.INITIAL);
		tmp.put(ALT_SIZE_DESC, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelSizeVariantProduct.alt_size_desc</code> attribute.
	 * @return the alt_size_desc - Display Size of the product.
	 */
	public String getAlt_size_desc(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedApparelSizeVariantProduct.getAlt_size_desc requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, ALT_SIZE_DESC);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelSizeVariantProduct.alt_size_desc</code> attribute.
	 * @return the alt_size_desc - Display Size of the product.
	 */
	public String getAlt_size_desc()
	{
		return getAlt_size_desc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelSizeVariantProduct.alt_size_desc</code> attribute. 
	 * @return the localized alt_size_desc - Display Size of the product.
	 */
	public Map<Language,String> getAllAlt_size_desc(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,ALT_SIZE_DESC,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelSizeVariantProduct.alt_size_desc</code> attribute. 
	 * @return the localized alt_size_desc - Display Size of the product.
	 */
	public Map<Language,String> getAllAlt_size_desc()
	{
		return getAllAlt_size_desc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelSizeVariantProduct.alt_size_desc</code> attribute. 
	 * @param value the alt_size_desc - Display Size of the product.
	 */
	public void setAlt_size_desc(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedApparelSizeVariantProduct.setAlt_size_desc requires a session language", 0 );
		}
		setLocalizedProperty(ctx, ALT_SIZE_DESC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelSizeVariantProduct.alt_size_desc</code> attribute. 
	 * @param value the alt_size_desc - Display Size of the product.
	 */
	public void setAlt_size_desc(final String value)
	{
		setAlt_size_desc( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelSizeVariantProduct.alt_size_desc</code> attribute. 
	 * @param value the alt_size_desc - Display Size of the product.
	 */
	public void setAllAlt_size_desc(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,ALT_SIZE_DESC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelSizeVariantProduct.alt_size_desc</code> attribute. 
	 * @param value the alt_size_desc - Display Size of the product.
	 */
	public void setAllAlt_size_desc(final Map<Language,String> value)
	{
		setAllAlt_size_desc( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelSizeVariantProduct.size</code> attribute.
	 * @return the size - Size of the product.
	 */
	public String getSize(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedApparelSizeVariantProduct.getSize requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, SIZE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelSizeVariantProduct.size</code> attribute.
	 * @return the size - Size of the product.
	 */
	public String getSize()
	{
		return getSize( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelSizeVariantProduct.size</code> attribute. 
	 * @return the localized size - Size of the product.
	 */
	public Map<Language,String> getAllSize(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,SIZE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ApparelSizeVariantProduct.size</code> attribute. 
	 * @return the localized size - Size of the product.
	 */
	public Map<Language,String> getAllSize()
	{
		return getAllSize( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelSizeVariantProduct.size</code> attribute. 
	 * @param value the size - Size of the product.
	 */
	public void setSize(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedApparelSizeVariantProduct.setSize requires a session language", 0 );
		}
		setLocalizedProperty(ctx, SIZE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelSizeVariantProduct.size</code> attribute. 
	 * @param value the size - Size of the product.
	 */
	public void setSize(final String value)
	{
		setSize( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelSizeVariantProduct.size</code> attribute. 
	 * @param value the size - Size of the product.
	 */
	public void setAllSize(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,SIZE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ApparelSizeVariantProduct.size</code> attribute. 
	 * @param value the size - Size of the product.
	 */
	public void setAllSize(final Map<Language,String> value)
	{
		setAllSize( getSession().getSessionContext(), value );
	}
	
}
