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
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLProductAttrTranslationData SSLProductAttrMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLProductAttrTranslationData extends GenericItem
{
	/** Qualifier of the <code>SSLProductAttrMapping.hybrisName</code> attribute **/
	public static final String HYBRISNAME = "hybrisName";
	/** Qualifier of the <code>SSLProductAttrMapping.mdmName</code> attribute **/
	public static final String MDMNAME = "mdmName";
	/** Qualifier of the <code>SSLProductAttrMapping.parentName</code> attribute **/
	public static final String PARENTNAME = "parentName";
	/** Qualifier of the <code>SSLProductAttrMapping.productAttributeType</code> attribute **/
	public static final String PRODUCTATTRIBUTETYPE = "productAttributeType";
	/** Qualifier of the <code>SSLProductAttrMapping.isVariantAttribute</code> attribute **/
	public static final String ISVARIANTATTRIBUTE = "isVariantAttribute";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(HYBRISNAME, AttributeMode.INITIAL);
		tmp.put(MDMNAME, AttributeMode.INITIAL);
		tmp.put(PARENTNAME, AttributeMode.INITIAL);
		tmp.put(PRODUCTATTRIBUTETYPE, AttributeMode.INITIAL);
		tmp.put(ISVARIANTATTRIBUTE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.hybrisName</code> attribute.
	 * @return the hybrisName - Name of property in Hybris
	 */
	public String getHybrisName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HYBRISNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.hybrisName</code> attribute.
	 * @return the hybrisName - Name of property in Hybris
	 */
	public String getHybrisName()
	{
		return getHybrisName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.hybrisName</code> attribute. 
	 * @param value the hybrisName - Name of property in Hybris
	 */
	public void setHybrisName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HYBRISNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.hybrisName</code> attribute. 
	 * @param value the hybrisName - Name of property in Hybris
	 */
	public void setHybrisName(final String value)
	{
		setHybrisName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.isVariantAttribute</code> attribute.
	 * @return the isVariantAttribute - Does attribute belong to Variants.
	 */
	public Boolean isIsVariantAttribute(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ISVARIANTATTRIBUTE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.isVariantAttribute</code> attribute.
	 * @return the isVariantAttribute - Does attribute belong to Variants.
	 */
	public Boolean isIsVariantAttribute()
	{
		return isIsVariantAttribute( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.isVariantAttribute</code> attribute. 
	 * @return the isVariantAttribute - Does attribute belong to Variants.
	 */
	public boolean isIsVariantAttributeAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIsVariantAttribute( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.isVariantAttribute</code> attribute. 
	 * @return the isVariantAttribute - Does attribute belong to Variants.
	 */
	public boolean isIsVariantAttributeAsPrimitive()
	{
		return isIsVariantAttributeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.isVariantAttribute</code> attribute. 
	 * @param value the isVariantAttribute - Does attribute belong to Variants.
	 */
	public void setIsVariantAttribute(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ISVARIANTATTRIBUTE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.isVariantAttribute</code> attribute. 
	 * @param value the isVariantAttribute - Does attribute belong to Variants.
	 */
	public void setIsVariantAttribute(final Boolean value)
	{
		setIsVariantAttribute( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.isVariantAttribute</code> attribute. 
	 * @param value the isVariantAttribute - Does attribute belong to Variants.
	 */
	public void setIsVariantAttribute(final SessionContext ctx, final boolean value)
	{
		setIsVariantAttribute( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.isVariantAttribute</code> attribute. 
	 * @param value the isVariantAttribute - Does attribute belong to Variants.
	 */
	public void setIsVariantAttribute(final boolean value)
	{
		setIsVariantAttribute( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.mdmName</code> attribute.
	 * @return the mdmName - Name of property in MDM
	 */
	public String getMdmName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MDMNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.mdmName</code> attribute.
	 * @return the mdmName - Name of property in MDM
	 */
	public String getMdmName()
	{
		return getMdmName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.mdmName</code> attribute. 
	 * @param value the mdmName - Name of property in MDM
	 */
	public void setMdmName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MDMNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.mdmName</code> attribute. 
	 * @param value the mdmName - Name of property in MDM
	 */
	public void setMdmName(final String value)
	{
		setMdmName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.parentName</code> attribute.
	 * @return the parentName - Name of property in MDM
	 */
	public String getParentName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PARENTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.parentName</code> attribute.
	 * @return the parentName - Name of property in MDM
	 */
	public String getParentName()
	{
		return getParentName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.parentName</code> attribute. 
	 * @param value the parentName - Name of property in MDM
	 */
	public void setParentName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PARENTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.parentName</code> attribute. 
	 * @param value the parentName - Name of property in MDM
	 */
	public void setParentName(final String value)
	{
		setParentName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.productAttributeType</code> attribute.
	 * @return the productAttributeType - Type of the property like Common,Technical etc.
	 */
	public EnumerationValue getProductAttributeType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, PRODUCTATTRIBUTETYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductAttrMapping.productAttributeType</code> attribute.
	 * @return the productAttributeType - Type of the property like Common,Technical etc.
	 */
	public EnumerationValue getProductAttributeType()
	{
		return getProductAttributeType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.productAttributeType</code> attribute. 
	 * @param value the productAttributeType - Type of the property like Common,Technical etc.
	 */
	public void setProductAttributeType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, PRODUCTATTRIBUTETYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductAttrMapping.productAttributeType</code> attribute. 
	 * @param value the productAttributeType - Type of the property like Common,Technical etc.
	 */
	public void setProductAttributeType(final EnumerationValue value)
	{
		setProductAttributeType( getSession().getSessionContext(), value );
	}
	
}
