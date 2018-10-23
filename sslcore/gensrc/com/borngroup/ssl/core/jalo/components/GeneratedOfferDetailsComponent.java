/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleBannerComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.OfferDetailsComponent OfferDetailsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOfferDetailsComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>OfferDetailsComponent.imagePattern</code> attribute **/
	public static final String IMAGEPATTERN = "imagePattern";
	/** Qualifier of the <code>OfferDetailsComponent.smallSizeImages</code> attribute **/
	public static final String SMALLSIZEIMAGES = "smallSizeImages";
	/** Qualifier of the <code>OfferDetailsComponent.mediumSizeImages</code> attribute **/
	public static final String MEDIUMSIZEIMAGES = "mediumSizeImages";
	/** Qualifier of the <code>OfferDetailsComponent.largeSizeImages</code> attribute **/
	public static final String LARGESIZEIMAGES = "largeSizeImages";
	/** Qualifier of the <code>OfferDetailsComponent.singleBanner</code> attribute **/
	public static final String SINGLEBANNER = "singleBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(IMAGEPATTERN, AttributeMode.INITIAL);
		tmp.put(SMALLSIZEIMAGES, AttributeMode.INITIAL);
		tmp.put(MEDIUMSIZEIMAGES, AttributeMode.INITIAL);
		tmp.put(LARGESIZEIMAGES, AttributeMode.INITIAL);
		tmp.put(SINGLEBANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferDetailsComponent.imagePattern</code> attribute.
	 * @return the imagePattern
	 */
	public EnumerationValue getImagePattern(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, IMAGEPATTERN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferDetailsComponent.imagePattern</code> attribute.
	 * @return the imagePattern
	 */
	public EnumerationValue getImagePattern()
	{
		return getImagePattern( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferDetailsComponent.imagePattern</code> attribute. 
	 * @param value the imagePattern
	 */
	public void setImagePattern(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, IMAGEPATTERN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferDetailsComponent.imagePattern</code> attribute. 
	 * @param value the imagePattern
	 */
	public void setImagePattern(final EnumerationValue value)
	{
		setImagePattern( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferDetailsComponent.largeSizeImages</code> attribute.
	 * @return the largeSizeImages
	 */
	public List<SimpleBannerComponent> getLargeSizeImages(final SessionContext ctx)
	{
		List<SimpleBannerComponent> coll = (List<SimpleBannerComponent>)getProperty( ctx, LARGESIZEIMAGES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferDetailsComponent.largeSizeImages</code> attribute.
	 * @return the largeSizeImages
	 */
	public List<SimpleBannerComponent> getLargeSizeImages()
	{
		return getLargeSizeImages( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferDetailsComponent.largeSizeImages</code> attribute. 
	 * @param value the largeSizeImages
	 */
	public void setLargeSizeImages(final SessionContext ctx, final List<SimpleBannerComponent> value)
	{
		setProperty(ctx, LARGESIZEIMAGES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferDetailsComponent.largeSizeImages</code> attribute. 
	 * @param value the largeSizeImages
	 */
	public void setLargeSizeImages(final List<SimpleBannerComponent> value)
	{
		setLargeSizeImages( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferDetailsComponent.mediumSizeImages</code> attribute.
	 * @return the mediumSizeImages
	 */
	public List<SimpleBannerComponent> getMediumSizeImages(final SessionContext ctx)
	{
		List<SimpleBannerComponent> coll = (List<SimpleBannerComponent>)getProperty( ctx, MEDIUMSIZEIMAGES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferDetailsComponent.mediumSizeImages</code> attribute.
	 * @return the mediumSizeImages
	 */
	public List<SimpleBannerComponent> getMediumSizeImages()
	{
		return getMediumSizeImages( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferDetailsComponent.mediumSizeImages</code> attribute. 
	 * @param value the mediumSizeImages
	 */
	public void setMediumSizeImages(final SessionContext ctx, final List<SimpleBannerComponent> value)
	{
		setProperty(ctx, MEDIUMSIZEIMAGES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferDetailsComponent.mediumSizeImages</code> attribute. 
	 * @param value the mediumSizeImages
	 */
	public void setMediumSizeImages(final List<SimpleBannerComponent> value)
	{
		setMediumSizeImages( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferDetailsComponent.singleBanner</code> attribute.
	 * @return the singleBanner
	 */
	public SimpleBannerComponent getSingleBanner(final SessionContext ctx)
	{
		return (SimpleBannerComponent)getProperty( ctx, SINGLEBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferDetailsComponent.singleBanner</code> attribute.
	 * @return the singleBanner
	 */
	public SimpleBannerComponent getSingleBanner()
	{
		return getSingleBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferDetailsComponent.singleBanner</code> attribute. 
	 * @param value the singleBanner
	 */
	public void setSingleBanner(final SessionContext ctx, final SimpleBannerComponent value)
	{
		setProperty(ctx, SINGLEBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferDetailsComponent.singleBanner</code> attribute. 
	 * @param value the singleBanner
	 */
	public void setSingleBanner(final SimpleBannerComponent value)
	{
		setSingleBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferDetailsComponent.smallSizeImages</code> attribute.
	 * @return the smallSizeImages
	 */
	public List<SimpleBannerComponent> getSmallSizeImages(final SessionContext ctx)
	{
		List<SimpleBannerComponent> coll = (List<SimpleBannerComponent>)getProperty( ctx, SMALLSIZEIMAGES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferDetailsComponent.smallSizeImages</code> attribute.
	 * @return the smallSizeImages
	 */
	public List<SimpleBannerComponent> getSmallSizeImages()
	{
		return getSmallSizeImages( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferDetailsComponent.smallSizeImages</code> attribute. 
	 * @param value the smallSizeImages
	 */
	public void setSmallSizeImages(final SessionContext ctx, final List<SimpleBannerComponent> value)
	{
		setProperty(ctx, SMALLSIZEIMAGES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferDetailsComponent.smallSizeImages</code> attribute. 
	 * @param value the smallSizeImages
	 */
	public void setSmallSizeImages(final List<SimpleBannerComponent> value)
	{
		setSmallSizeImages( getSession().getSessionContext(), value );
	}
	
}
