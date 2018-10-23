/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.ColorExplosionComponent;
import com.borngroup.ssl.core.jalo.components.InStoreAndOnlineComponent;
import com.borngroup.ssl.core.jalo.components.NewShorterLengthComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.CategoryListingComponent CategoryListingComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCategoryListingComponent extends CMSImageComponent
{
	/** Qualifier of the <code>CategoryListingComponent.newShorterLengthComponent</code> attribute **/
	public static final String NEWSHORTERLENGTHCOMPONENT = "newShorterLengthComponent";
	/** Qualifier of the <code>CategoryListingComponent.colorExplosionComponent</code> attribute **/
	public static final String COLOREXPLOSIONCOMPONENT = "colorExplosionComponent";
	/** Qualifier of the <code>CategoryListingComponent.inStoreAndOutlineComponent</code> attribute **/
	public static final String INSTOREANDOUTLINECOMPONENT = "inStoreAndOutlineComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(NEWSHORTERLENGTHCOMPONENT, AttributeMode.INITIAL);
		tmp.put(COLOREXPLOSIONCOMPONENT, AttributeMode.INITIAL);
		tmp.put(INSTOREANDOUTLINECOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryListingComponent.colorExplosionComponent</code> attribute.
	 * @return the colorExplosionComponent
	 */
	public ColorExplosionComponent getColorExplosionComponent(final SessionContext ctx)
	{
		return (ColorExplosionComponent)getProperty( ctx, COLOREXPLOSIONCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryListingComponent.colorExplosionComponent</code> attribute.
	 * @return the colorExplosionComponent
	 */
	public ColorExplosionComponent getColorExplosionComponent()
	{
		return getColorExplosionComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryListingComponent.colorExplosionComponent</code> attribute. 
	 * @param value the colorExplosionComponent
	 */
	public void setColorExplosionComponent(final SessionContext ctx, final ColorExplosionComponent value)
	{
		setProperty(ctx, COLOREXPLOSIONCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryListingComponent.colorExplosionComponent</code> attribute. 
	 * @param value the colorExplosionComponent
	 */
	public void setColorExplosionComponent(final ColorExplosionComponent value)
	{
		setColorExplosionComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryListingComponent.inStoreAndOutlineComponent</code> attribute.
	 * @return the inStoreAndOutlineComponent
	 */
	public InStoreAndOnlineComponent getInStoreAndOutlineComponent(final SessionContext ctx)
	{
		return (InStoreAndOnlineComponent)getProperty( ctx, INSTOREANDOUTLINECOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryListingComponent.inStoreAndOutlineComponent</code> attribute.
	 * @return the inStoreAndOutlineComponent
	 */
	public InStoreAndOnlineComponent getInStoreAndOutlineComponent()
	{
		return getInStoreAndOutlineComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryListingComponent.inStoreAndOutlineComponent</code> attribute. 
	 * @param value the inStoreAndOutlineComponent
	 */
	public void setInStoreAndOutlineComponent(final SessionContext ctx, final InStoreAndOnlineComponent value)
	{
		setProperty(ctx, INSTOREANDOUTLINECOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryListingComponent.inStoreAndOutlineComponent</code> attribute. 
	 * @param value the inStoreAndOutlineComponent
	 */
	public void setInStoreAndOutlineComponent(final InStoreAndOnlineComponent value)
	{
		setInStoreAndOutlineComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryListingComponent.newShorterLengthComponent</code> attribute.
	 * @return the newShorterLengthComponent
	 */
	public NewShorterLengthComponent getNewShorterLengthComponent(final SessionContext ctx)
	{
		return (NewShorterLengthComponent)getProperty( ctx, NEWSHORTERLENGTHCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CategoryListingComponent.newShorterLengthComponent</code> attribute.
	 * @return the newShorterLengthComponent
	 */
	public NewShorterLengthComponent getNewShorterLengthComponent()
	{
		return getNewShorterLengthComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryListingComponent.newShorterLengthComponent</code> attribute. 
	 * @param value the newShorterLengthComponent
	 */
	public void setNewShorterLengthComponent(final SessionContext ctx, final NewShorterLengthComponent value)
	{
		setProperty(ctx, NEWSHORTERLENGTHCOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CategoryListingComponent.newShorterLengthComponent</code> attribute. 
	 * @param value the newShorterLengthComponent
	 */
	public void setNewShorterLengthComponent(final NewShorterLengthComponent value)
	{
		setNewShorterLengthComponent( getSession().getSessionContext(), value );
	}
	
}
