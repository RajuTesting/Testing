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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.PromotionsHeaderBannerComponent PromotionsHeaderBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionsHeaderBannerComponent extends PromotionBannerComponent
{
	/** Qualifier of the <code>PromotionsHeaderBannerComponent.offerText</code> attribute **/
	public static final String OFFERTEXT = "offerText";
	/** Qualifier of the <code>PromotionsHeaderBannerComponent.offerTarget</code> attribute **/
	public static final String OFFERTARGET = "offerTarget";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(PromotionBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(OFFERTEXT, AttributeMode.INITIAL);
		tmp.put(OFFERTARGET, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsHeaderBannerComponent.offerTarget</code> attribute.
	 * @return the offerTarget - Promotion Banner Offer Target
	 */
	public String getOfferTarget(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionsHeaderBannerComponent.getOfferTarget requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, OFFERTARGET);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsHeaderBannerComponent.offerTarget</code> attribute.
	 * @return the offerTarget - Promotion Banner Offer Target
	 */
	public String getOfferTarget()
	{
		return getOfferTarget( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsHeaderBannerComponent.offerTarget</code> attribute. 
	 * @return the localized offerTarget - Promotion Banner Offer Target
	 */
	public Map<Language,String> getAllOfferTarget(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,OFFERTARGET,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsHeaderBannerComponent.offerTarget</code> attribute. 
	 * @return the localized offerTarget - Promotion Banner Offer Target
	 */
	public Map<Language,String> getAllOfferTarget()
	{
		return getAllOfferTarget( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsHeaderBannerComponent.offerTarget</code> attribute. 
	 * @param value the offerTarget - Promotion Banner Offer Target
	 */
	public void setOfferTarget(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionsHeaderBannerComponent.setOfferTarget requires a session language", 0 );
		}
		setLocalizedProperty(ctx, OFFERTARGET,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsHeaderBannerComponent.offerTarget</code> attribute. 
	 * @param value the offerTarget - Promotion Banner Offer Target
	 */
	public void setOfferTarget(final String value)
	{
		setOfferTarget( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsHeaderBannerComponent.offerTarget</code> attribute. 
	 * @param value the offerTarget - Promotion Banner Offer Target
	 */
	public void setAllOfferTarget(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,OFFERTARGET,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsHeaderBannerComponent.offerTarget</code> attribute. 
	 * @param value the offerTarget - Promotion Banner Offer Target
	 */
	public void setAllOfferTarget(final Map<Language,String> value)
	{
		setAllOfferTarget( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsHeaderBannerComponent.offerText</code> attribute.
	 * @return the offerText - Promotion Banner Offer Text
	 */
	public String getOfferText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionsHeaderBannerComponent.getOfferText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, OFFERTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsHeaderBannerComponent.offerText</code> attribute.
	 * @return the offerText - Promotion Banner Offer Text
	 */
	public String getOfferText()
	{
		return getOfferText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsHeaderBannerComponent.offerText</code> attribute. 
	 * @return the localized offerText - Promotion Banner Offer Text
	 */
	public Map<Language,String> getAllOfferText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,OFFERTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsHeaderBannerComponent.offerText</code> attribute. 
	 * @return the localized offerText - Promotion Banner Offer Text
	 */
	public Map<Language,String> getAllOfferText()
	{
		return getAllOfferText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsHeaderBannerComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Banner Offer Text
	 */
	public void setOfferText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionsHeaderBannerComponent.setOfferText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, OFFERTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsHeaderBannerComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Banner Offer Text
	 */
	public void setOfferText(final String value)
	{
		setOfferText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsHeaderBannerComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Banner Offer Text
	 */
	public void setAllOfferText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,OFFERTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsHeaderBannerComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Banner Offer Text
	 */
	public void setAllOfferText(final Map<Language,String> value)
	{
		setAllOfferText( getSession().getSessionContext(), value );
	}
	
}
