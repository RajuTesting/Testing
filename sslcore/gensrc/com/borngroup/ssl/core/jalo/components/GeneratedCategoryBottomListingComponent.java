/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.AccessoriesComponent;
import com.borngroup.ssl.core.jalo.components.BrandsWeLoveCMSComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.CategoryBottomListingComponent CategoryBottomListingComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCategoryBottomListingComponent extends CMSImageComponent
{
	/** Qualifier of the <code>CategoryBottomListingComponent.accessoriesComponent</code> attribute **/
	public static final String ACCESSORIESCOMPONENT = "accessoriesComponent";
	/** Qualifier of the <code>CategoryBottomListingComponent.brandsWeLoveCMSComponent</code> attribute **/
	public static final String BRANDSWELOVECMSCOMPONENT = "brandsWeLoveCMSComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(ACCESSORIESCOMPONENT, AttributeMode.INITIAL);
		tmp.put(BRANDSWELOVECMSCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryBottomListingComponent.accessoriesComponent</code> attribute.
	 * @return the accessoriesComponent
	 */
	public AccessoriesComponent getAccessoriesComponent(final SessionContext ctx)
	{
		return (AccessoriesComponent)getProperty( ctx, ACCESSORIESCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryBottomListingComponent.accessoriesComponent</code> attribute.
	 * @return the accessoriesComponent
	 */
	public AccessoriesComponent getAccessoriesComponent()
	{
		return getAccessoriesComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryBottomListingComponent.accessoriesComponent</code> attribute. 
	 * @param value the accessoriesComponent
	 */
	public void setAccessoriesComponent(final SessionContext ctx, final AccessoriesComponent value)
	{
		setProperty(ctx, ACCESSORIESCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryBottomListingComponent.accessoriesComponent</code> attribute. 
	 * @param value the accessoriesComponent
	 */
	public void setAccessoriesComponent(final AccessoriesComponent value)
	{
		setAccessoriesComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryBottomListingComponent.brandsWeLoveCMSComponent</code> attribute.
	 * @return the brandsWeLoveCMSComponent
	 */
	public BrandsWeLoveCMSComponent getBrandsWeLoveCMSComponent(final SessionContext ctx)
	{
		return (BrandsWeLoveCMSComponent)getProperty( ctx, BRANDSWELOVECMSCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryBottomListingComponent.brandsWeLoveCMSComponent</code> attribute.
	 * @return the brandsWeLoveCMSComponent
	 */
	public BrandsWeLoveCMSComponent getBrandsWeLoveCMSComponent()
	{
		return getBrandsWeLoveCMSComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryBottomListingComponent.brandsWeLoveCMSComponent</code> attribute. 
	 * @param value the brandsWeLoveCMSComponent
	 */
	public void setBrandsWeLoveCMSComponent(final SessionContext ctx, final BrandsWeLoveCMSComponent value)
	{
		setProperty(ctx, BRANDSWELOVECMSCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryBottomListingComponent.brandsWeLoveCMSComponent</code> attribute. 
	 * @param value the brandsWeLoveCMSComponent
	 */
	public void setBrandsWeLoveCMSComponent(final BrandsWeLoveCMSComponent value)
	{
		setBrandsWeLoveCMSComponent( getSession().getSessionContext(), value );
	}
	
}
