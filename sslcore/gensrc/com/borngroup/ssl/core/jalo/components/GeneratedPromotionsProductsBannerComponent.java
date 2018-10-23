/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.PromotionBannerComponent;
import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.PromotionsProductsBannerComponent PromotionsProductsBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionsProductsBannerComponent extends PromotionBannerComponent
{
	/** Qualifier of the <code>PromotionsProductsBannerComponent.category</code> attribute **/
	public static final String CATEGORY = "category";
	/** Qualifier of the <code>PromotionsProductsBannerComponent.tabLink</code> attribute **/
	public static final String TABLINK = "tabLink";
	/** Qualifier of the <code>PromotionsProductsBannerComponent.offerText</code> attribute **/
	public static final String OFFERTEXT = "offerText";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(PromotionBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CATEGORY, AttributeMode.INITIAL);
		tmp.put(TABLINK, AttributeMode.INITIAL);
		tmp.put(OFFERTEXT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsBannerComponent.category</code> attribute.
	 * @return the category - Promotion's Category
	 */
	public Category getCategory(final SessionContext ctx)
	{
		return (Category)getProperty( ctx, CATEGORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsBannerComponent.category</code> attribute.
	 * @return the category - Promotion's Category
	 */
	public Category getCategory()
	{
		return getCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsBannerComponent.category</code> attribute. 
	 * @param value the category - Promotion's Category
	 */
	public void setCategory(final SessionContext ctx, final Category value)
	{
		setProperty(ctx, CATEGORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsBannerComponent.category</code> attribute. 
	 * @param value the category - Promotion's Category
	 */
	public void setCategory(final Category value)
	{
		setCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsBannerComponent.offerText</code> attribute.
	 * @return the offerText - Promotion Product Banner Offer Text
	 */
	public String getOfferText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionsProductsBannerComponent.getOfferText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, OFFERTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsBannerComponent.offerText</code> attribute.
	 * @return the offerText - Promotion Product Banner Offer Text
	 */
	public String getOfferText()
	{
		return getOfferText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsBannerComponent.offerText</code> attribute. 
	 * @return the localized offerText - Promotion Product Banner Offer Text
	 */
	public Map<Language,String> getAllOfferText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,OFFERTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsBannerComponent.offerText</code> attribute. 
	 * @return the localized offerText - Promotion Product Banner Offer Text
	 */
	public Map<Language,String> getAllOfferText()
	{
		return getAllOfferText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsBannerComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Product Banner Offer Text
	 */
	public void setOfferText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionsProductsBannerComponent.setOfferText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, OFFERTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsBannerComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Product Banner Offer Text
	 */
	public void setOfferText(final String value)
	{
		setOfferText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsBannerComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Product Banner Offer Text
	 */
	public void setAllOfferText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,OFFERTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsBannerComponent.offerText</code> attribute. 
	 * @param value the offerText - Promotion Product Banner Offer Text
	 */
	public void setAllOfferText(final Map<Language,String> value)
	{
		setAllOfferText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsBannerComponent.tabLink</code> attribute.
	 * @return the tabLink - tab to Link Component
	 */
	public String getTabLink(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionsProductsBannerComponent.getTabLink requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TABLINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsBannerComponent.tabLink</code> attribute.
	 * @return the tabLink - tab to Link Component
	 */
	public String getTabLink()
	{
		return getTabLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsBannerComponent.tabLink</code> attribute. 
	 * @return the localized tabLink - tab to Link Component
	 */
	public Map<Language,String> getAllTabLink(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TABLINK,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionsProductsBannerComponent.tabLink</code> attribute. 
	 * @return the localized tabLink - tab to Link Component
	 */
	public Map<Language,String> getAllTabLink()
	{
		return getAllTabLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsBannerComponent.tabLink</code> attribute. 
	 * @param value the tabLink - tab to Link Component
	 */
	public void setTabLink(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionsProductsBannerComponent.setTabLink requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TABLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsBannerComponent.tabLink</code> attribute. 
	 * @param value the tabLink - tab to Link Component
	 */
	public void setTabLink(final String value)
	{
		setTabLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsBannerComponent.tabLink</code> attribute. 
	 * @param value the tabLink - tab to Link Component
	 */
	public void setAllTabLink(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TABLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionsProductsBannerComponent.tabLink</code> attribute. 
	 * @param value the tabLink - tab to Link Component
	 */
	public void setAllTabLink(final Map<Language,String> value)
	{
		setAllTabLink( getSession().getSessionContext(), value );
	}
	
}
