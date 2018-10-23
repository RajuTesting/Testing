/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.PromotionBannerComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.PromotionOfferComponent PromotionOfferComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionOfferComponent extends PromotionBannerComponent
{
	/** Qualifier of the <code>PromotionOfferComponent.offerText</code> attribute **/
	public static final String OFFERTEXT = "offerText";
	/** Qualifier of the <code>PromotionOfferComponent.offerTarget</code> attribute **/
	public static final String OFFERTARGET = "offerTarget";
	/** Qualifier of the <code>PromotionOfferComponent.logo</code> attribute **/
	public static final String LOGO = "logo";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(PromotionBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(OFFERTEXT, AttributeMode.INITIAL);
		tmp.put(OFFERTARGET, AttributeMode.INITIAL);
		tmp.put(LOGO, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.logo</code> attribute.
	 * @return the logo
	 */
	public Media getLogo(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionOfferComponent.getLogo requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, LOGO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.logo</code> attribute.
	 * @return the logo
	 */
	public Media getLogo()
	{
		return getLogo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.logo</code> attribute. 
	 * @return the localized logo
	 */
	public Map<Language,Media> getAllLogo(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,LOGO,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.logo</code> attribute. 
	 * @return the localized logo
	 */
	public Map<Language,Media> getAllLogo()
	{
		return getAllLogo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setLogo(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionOfferComponent.setLogo requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LOGO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setLogo(final Media value)
	{
		setLogo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setAllLogo(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,LOGO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setAllLogo(final Map<Language,Media> value)
	{
		setAllLogo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.offerTarget</code> attribute.
	 * @return the offerTarget - Promotion Banner Offer Target
	 */
	public Double getOfferTarget(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, OFFERTARGET);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.offerTarget</code> attribute.
	 * @return the offerTarget - Promotion Banner Offer Target
	 */
	public Double getOfferTarget()
	{
		return getOfferTarget( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.offerTarget</code> attribute. 
	 * @return the offerTarget - Promotion Banner Offer Target
	 */
	public double getOfferTargetAsPrimitive(final SessionContext ctx)
	{
		Double value = getOfferTarget( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.offerTarget</code> attribute. 
	 * @return the offerTarget - Promotion Banner Offer Target
	 */
	public double getOfferTargetAsPrimitive()
	{
		return getOfferTargetAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.offerTarget</code> attribute. 
	 * @param value the offerTarget - Promotion Banner Offer Target
	 */
	public void setOfferTarget(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, OFFERTARGET,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.offerTarget</code> attribute. 
	 * @param value the offerTarget - Promotion Banner Offer Target
	 */
	public void setOfferTarget(final Double value)
	{
		setOfferTarget( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.offerTarget</code> attribute. 
	 * @param value the offerTarget - Promotion Banner Offer Target
	 */
	public void setOfferTarget(final SessionContext ctx, final double value)
	{
		setOfferTarget( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.offerTarget</code> attribute. 
	 * @param value the offerTarget - Promotion Banner Offer Target
	 */
	public void setOfferTarget(final double value)
	{
		setOfferTarget( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.offerText</code> attribute.
	 * @return the offerText - Promotion Banner Offer Text
	 */
	public String getOfferText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionOfferComponent.getOfferText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, OFFERTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.offerText</code> attribute.
	 * @return the offerText - Promotion Banner Offer Text
	 */
	public String getOfferText()
	{
		return getOfferText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.offerText</code> attribute. 
	 * @return the localized offerText - Promotion Banner Offer Text
	 */
	public Map<Language,String> getAllOfferText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,OFFERTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionOfferComponent.offerText</code> attribute. 
	 * @return the localized offerText - Promotion Banner Offer Text
	 */
	public Map<Language,String> getAllOfferText()
	{
		return getAllOfferText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Banner Offer Text
	 */
	public void setOfferText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionOfferComponent.setOfferText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, OFFERTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Banner Offer Text
	 */
	public void setOfferText(final String value)
	{
		setOfferText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Banner Offer Text
	 */
	public void setAllOfferText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,OFFERTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionOfferComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Banner Offer Text
	 */
	public void setAllOfferText(final Map<Language,String> value)
	{
		setAllOfferText( getSession().getSessionContext(), value );
	}
	
}
