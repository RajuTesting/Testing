/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.AccessoriesComponent;
import com.borngroup.ssl.core.jalo.components.BrandsWeLoveComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.AccessoriesBrandsParentComponent AccessoriesBrandsParentComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedAccessoriesBrandsParentComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>AccessoriesBrandsParentComponent.accessoriesComponent</code> attribute **/
	public static final String ACCESSORIESCOMPONENT = "accessoriesComponent";
	/** Qualifier of the <code>AccessoriesBrandsParentComponent.brandsWeLoveComponent</code> attribute **/
	public static final String BRANDSWELOVECOMPONENT = "brandsWeLoveComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(ACCESSORIESCOMPONENT, AttributeMode.INITIAL);
		tmp.put(BRANDSWELOVECOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AccessoriesBrandsParentComponent.accessoriesComponent</code> attribute.
	 * @return the accessoriesComponent - Accessories Banner Component
	 */
	public AccessoriesComponent getAccessoriesComponent(final SessionContext ctx)
	{
		return (AccessoriesComponent)getProperty( ctx, ACCESSORIESCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AccessoriesBrandsParentComponent.accessoriesComponent</code> attribute.
	 * @return the accessoriesComponent - Accessories Banner Component
	 */
	public AccessoriesComponent getAccessoriesComponent()
	{
		return getAccessoriesComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AccessoriesBrandsParentComponent.accessoriesComponent</code> attribute. 
	 * @param value the accessoriesComponent - Accessories Banner Component
	 */
	public void setAccessoriesComponent(final SessionContext ctx, final AccessoriesComponent value)
	{
		setProperty(ctx, ACCESSORIESCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AccessoriesBrandsParentComponent.accessoriesComponent</code> attribute. 
	 * @param value the accessoriesComponent - Accessories Banner Component
	 */
	public void setAccessoriesComponent(final AccessoriesComponent value)
	{
		setAccessoriesComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AccessoriesBrandsParentComponent.brandsWeLoveComponent</code> attribute.
	 * @return the brandsWeLoveComponent - Brands we love Component
	 */
	public BrandsWeLoveComponent getBrandsWeLoveComponent(final SessionContext ctx)
	{
		return (BrandsWeLoveComponent)getProperty( ctx, BRANDSWELOVECOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AccessoriesBrandsParentComponent.brandsWeLoveComponent</code> attribute.
	 * @return the brandsWeLoveComponent - Brands we love Component
	 */
	public BrandsWeLoveComponent getBrandsWeLoveComponent()
	{
		return getBrandsWeLoveComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AccessoriesBrandsParentComponent.brandsWeLoveComponent</code> attribute. 
	 * @param value the brandsWeLoveComponent - Brands we love Component
	 */
	public void setBrandsWeLoveComponent(final SessionContext ctx, final BrandsWeLoveComponent value)
	{
		setProperty(ctx, BRANDSWELOVECOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AccessoriesBrandsParentComponent.brandsWeLoveComponent</code> attribute. 
	 * @param value the brandsWeLoveComponent - Brands we love Component
	 */
	public void setBrandsWeLoveComponent(final BrandsWeLoveComponent value)
	{
		setBrandsWeLoveComponent( getSession().getSessionContext(), value );
	}
	
}
