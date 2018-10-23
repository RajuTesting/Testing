/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.catalog.jalo.classification.ClassAttributeAssignment;
import de.hybris.platform.catalog.jalo.classification.ClassificationAttributeValue;
import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem VirtualCategoryEntry}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedVirtualCategoryEntry extends GenericItem
{
	/** Qualifier of the <code>VirtualCategoryEntry.category</code> attribute **/
	public static final String CATEGORY = "category";
	/** Qualifier of the <code>VirtualCategoryEntry.brandCode</code> attribute **/
	public static final String BRANDCODE = "brandCode";
	/** Qualifier of the <code>VirtualCategoryEntry.classificationAttributeValue</code> attribute **/
	public static final String CLASSIFICATIONATTRIBUTEVALUE = "classificationAttributeValue";
	/** Qualifier of the <code>VirtualCategoryEntry.classAttributeAssignment</code> attribute **/
	public static final String CLASSATTRIBUTEASSIGNMENT = "classAttributeAssignment";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CATEGORY, AttributeMode.INITIAL);
		tmp.put(BRANDCODE, AttributeMode.INITIAL);
		tmp.put(CLASSIFICATIONATTRIBUTEVALUE, AttributeMode.INITIAL);
		tmp.put(CLASSATTRIBUTEASSIGNMENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VirtualCategoryEntry.brandCode</code> attribute.
	 * @return the brandCode - Brand Code
	 */
	public String getBrandCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BRANDCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VirtualCategoryEntry.brandCode</code> attribute.
	 * @return the brandCode - Brand Code
	 */
	public String getBrandCode()
	{
		return getBrandCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VirtualCategoryEntry.brandCode</code> attribute. 
	 * @param value the brandCode - Brand Code
	 */
	public void setBrandCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BRANDCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VirtualCategoryEntry.brandCode</code> attribute. 
	 * @param value the brandCode - Brand Code
	 */
	public void setBrandCode(final String value)
	{
		setBrandCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VirtualCategoryEntry.category</code> attribute.
	 * @return the category - Category
	 */
	public Category getCategory(final SessionContext ctx)
	{
		return (Category)getProperty( ctx, CATEGORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VirtualCategoryEntry.category</code> attribute.
	 * @return the category - Category
	 */
	public Category getCategory()
	{
		return getCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VirtualCategoryEntry.category</code> attribute. 
	 * @param value the category - Category
	 */
	public void setCategory(final SessionContext ctx, final Category value)
	{
		setProperty(ctx, CATEGORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VirtualCategoryEntry.category</code> attribute. 
	 * @param value the category - Category
	 */
	public void setCategory(final Category value)
	{
		setCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VirtualCategoryEntry.classAttributeAssignment</code> attribute.
	 * @return the classAttributeAssignment - Classification Attribute Value
	 */
	public ClassAttributeAssignment getClassAttributeAssignment(final SessionContext ctx)
	{
		return (ClassAttributeAssignment)getProperty( ctx, CLASSATTRIBUTEASSIGNMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VirtualCategoryEntry.classAttributeAssignment</code> attribute.
	 * @return the classAttributeAssignment - Classification Attribute Value
	 */
	public ClassAttributeAssignment getClassAttributeAssignment()
	{
		return getClassAttributeAssignment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VirtualCategoryEntry.classAttributeAssignment</code> attribute. 
	 * @param value the classAttributeAssignment - Classification Attribute Value
	 */
	public void setClassAttributeAssignment(final SessionContext ctx, final ClassAttributeAssignment value)
	{
		setProperty(ctx, CLASSATTRIBUTEASSIGNMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VirtualCategoryEntry.classAttributeAssignment</code> attribute. 
	 * @param value the classAttributeAssignment - Classification Attribute Value
	 */
	public void setClassAttributeAssignment(final ClassAttributeAssignment value)
	{
		setClassAttributeAssignment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VirtualCategoryEntry.classificationAttributeValue</code> attribute.
	 * @return the classificationAttributeValue - Classification Attribute Value
	 */
	public ClassificationAttributeValue getClassificationAttributeValue(final SessionContext ctx)
	{
		return (ClassificationAttributeValue)getProperty( ctx, CLASSIFICATIONATTRIBUTEVALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VirtualCategoryEntry.classificationAttributeValue</code> attribute.
	 * @return the classificationAttributeValue - Classification Attribute Value
	 */
	public ClassificationAttributeValue getClassificationAttributeValue()
	{
		return getClassificationAttributeValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VirtualCategoryEntry.classificationAttributeValue</code> attribute. 
	 * @param value the classificationAttributeValue - Classification Attribute Value
	 */
	public void setClassificationAttributeValue(final SessionContext ctx, final ClassificationAttributeValue value)
	{
		setProperty(ctx, CLASSIFICATIONATTRIBUTEVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VirtualCategoryEntry.classificationAttributeValue</code> attribute. 
	 * @param value the classificationAttributeValue - Classification Attribute Value
	 */
	public void setClassificationAttributeValue(final ClassificationAttributeValue value)
	{
		setClassificationAttributeValue( getSession().getSessionContext(), value );
	}
	
}
