/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.NavigationBarImageComponent;
import de.hybris.platform.acceleratorcms.jalo.components.NavigationBarComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.NavigationBarWithImageComponent NavigationBarWithImageComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedNavigationBarWithImageComponent extends NavigationBarComponent
{
	/** Qualifier of the <code>NavigationBarWithImageComponent.imageLink</code> attribute **/
	public static final String IMAGELINK = "imageLink";
	/** Qualifier of the <code>NavigationBarWithImageComponent.image</code> attribute **/
	public static final String IMAGE = "image";
	/** Qualifier of the <code>NavigationBarWithImageComponent.imageHeadline</code> attribute **/
	public static final String IMAGEHEADLINE = "imageHeadline";
	/** Qualifier of the <code>NavigationBarWithImageComponent.imageDescription</code> attribute **/
	public static final String IMAGEDESCRIPTION = "imageDescription";
	/** Qualifier of the <code>NavigationBarWithImageComponent.imageList</code> attribute **/
	public static final String IMAGELIST = "imageList";
	/** Qualifier of the <code>NavigationBarWithImageComponent.categoryIcon</code> attribute **/
	public static final String CATEGORYICON = "categoryIcon";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(NavigationBarComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(IMAGELINK, AttributeMode.INITIAL);
		tmp.put(IMAGE, AttributeMode.INITIAL);
		tmp.put(IMAGEHEADLINE, AttributeMode.INITIAL);
		tmp.put(IMAGEDESCRIPTION, AttributeMode.INITIAL);
		tmp.put(IMAGELIST, AttributeMode.INITIAL);
		tmp.put(CATEGORYICON, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.categoryIcon</code> attribute.
	 * @return the categoryIcon - The image which will be shown in front of category in
	 *                             mobile app view.
	 */
	public Media getCategoryIcon(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, CATEGORYICON);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.categoryIcon</code> attribute.
	 * @return the categoryIcon - The image which will be shown in front of category in
	 *                             mobile app view.
	 */
	public Media getCategoryIcon()
	{
		return getCategoryIcon( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.categoryIcon</code> attribute. 
	 * @param value the categoryIcon - The image which will be shown in front of category in
	 *                             mobile app view.
	 */
	public void setCategoryIcon(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, CATEGORYICON,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.categoryIcon</code> attribute. 
	 * @param value the categoryIcon - The image which will be shown in front of category in
	 *                             mobile app view.
	 */
	public void setCategoryIcon(final Media value)
	{
		setCategoryIcon( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.image</code> attribute.
	 * @return the image - The image which can be attached to this navigation
	 *                             bar component.
	 */
	public Media getImage(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, IMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.image</code> attribute.
	 * @return the image - The image which can be attached to this navigation
	 *                             bar component.
	 */
	public Media getImage()
	{
		return getImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.image</code> attribute. 
	 * @param value the image - The image which can be attached to this navigation
	 *                             bar component.
	 */
	public void setImage(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, IMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.image</code> attribute. 
	 * @param value the image - The image which can be attached to this navigation
	 *                             bar component.
	 */
	public void setImage(final Media value)
	{
		setImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.imageDescription</code> attribute.
	 * @return the imageDescription - The image description which can be attached to this
	 *                             navigation bar component.
	 */
	public String getImageDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, IMAGEDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.imageDescription</code> attribute.
	 * @return the imageDescription - The image description which can be attached to this
	 *                             navigation bar component.
	 */
	public String getImageDescription()
	{
		return getImageDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.imageDescription</code> attribute. 
	 * @param value the imageDescription - The image description which can be attached to this
	 *                             navigation bar component.
	 */
	public void setImageDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, IMAGEDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.imageDescription</code> attribute. 
	 * @param value the imageDescription - The image description which can be attached to this
	 *                             navigation bar component.
	 */
	public void setImageDescription(final String value)
	{
		setImageDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.imageHeadline</code> attribute.
	 * @return the imageHeadline - The image headline which can be attached to this
	 *                             navigation bar component.
	 */
	public String getImageHeadline(final SessionContext ctx)
	{
		return (String)getProperty( ctx, IMAGEHEADLINE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.imageHeadline</code> attribute.
	 * @return the imageHeadline - The image headline which can be attached to this
	 *                             navigation bar component.
	 */
	public String getImageHeadline()
	{
		return getImageHeadline( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.imageHeadline</code> attribute. 
	 * @param value the imageHeadline - The image headline which can be attached to this
	 *                             navigation bar component.
	 */
	public void setImageHeadline(final SessionContext ctx, final String value)
	{
		setProperty(ctx, IMAGEHEADLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.imageHeadline</code> attribute. 
	 * @param value the imageHeadline - The image headline which can be attached to this
	 *                             navigation bar component.
	 */
	public void setImageHeadline(final String value)
	{
		setImageHeadline( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.imageLink</code> attribute.
	 * @return the imageLink - The cms link component that is attached to this bar
	 *                             component's image.
	 */
	public CMSLinkComponent getImageLink(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, IMAGELINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.imageLink</code> attribute.
	 * @return the imageLink - The cms link component that is attached to this bar
	 *                             component's image.
	 */
	public CMSLinkComponent getImageLink()
	{
		return getImageLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.imageLink</code> attribute. 
	 * @param value the imageLink - The cms link component that is attached to this bar
	 *                             component's image.
	 */
	public void setImageLink(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, IMAGELINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.imageLink</code> attribute. 
	 * @param value the imageLink - The cms link component that is attached to this bar
	 *                             component's image.
	 */
	public void setImageLink(final CMSLinkComponent value)
	{
		setImageLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.imageList</code> attribute.
	 * @return the imageList
	 */
	public List<NavigationBarImageComponent> getImageList(final SessionContext ctx)
	{
		List<NavigationBarImageComponent> coll = (List<NavigationBarImageComponent>)getProperty( ctx, IMAGELIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NavigationBarWithImageComponent.imageList</code> attribute.
	 * @return the imageList
	 */
	public List<NavigationBarImageComponent> getImageList()
	{
		return getImageList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.imageList</code> attribute. 
	 * @param value the imageList
	 */
	public void setImageList(final SessionContext ctx, final List<NavigationBarImageComponent> value)
	{
		setProperty(ctx, IMAGELIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NavigationBarWithImageComponent.imageList</code> attribute. 
	 * @param value the imageList
	 */
	public void setImageList(final List<NavigationBarImageComponent> value)
	{
		setImageList( getSession().getSessionContext(), value );
	}
	
}
