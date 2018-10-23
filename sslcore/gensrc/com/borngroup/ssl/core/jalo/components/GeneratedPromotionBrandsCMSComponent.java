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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.PromotionBrandsCMSComponent PromotionBrandsCMSComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPromotionBrandsCMSComponent extends PromotionBannerComponent
{
	/** Qualifier of the <code>PromotionBrandsCMSComponent.logo</code> attribute **/
	public static final String LOGO = "logo";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(PromotionBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LOGO, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBrandsCMSComponent.logo</code> attribute.
	 * @return the logo
	 */
	public Media getLogo(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionBrandsCMSComponent.getLogo requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, LOGO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBrandsCMSComponent.logo</code> attribute.
	 * @return the logo
	 */
	public Media getLogo()
	{
		return getLogo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBrandsCMSComponent.logo</code> attribute. 
	 * @return the localized logo
	 */
	public Map<Language,Media> getAllLogo(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,LOGO,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PromotionBrandsCMSComponent.logo</code> attribute. 
	 * @return the localized logo
	 */
	public Map<Language,Media> getAllLogo()
	{
		return getAllLogo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBrandsCMSComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setLogo(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPromotionBrandsCMSComponent.setLogo requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LOGO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBrandsCMSComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setLogo(final Media value)
	{
		setLogo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBrandsCMSComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setAllLogo(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,LOGO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PromotionBrandsCMSComponent.logo</code> attribute. 
	 * @param value the logo
	 */
	public void setAllLogo(final Map<Language,Media> value)
	{
		setAllLogo( getSession().getSessionContext(), value );
	}
	
}
