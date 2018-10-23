/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleBannerComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.ExtendedCitizenSimpleBannerComponent ExtendedCitizenSimpleBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedExtendedCitizenSimpleBannerComponent extends SimpleBannerComponent
{
	/** Qualifier of the <code>ExtendedCitizenSimpleBannerComponent.firstContent</code> attribute **/
	public static final String FIRSTCONTENT = "firstContent";
	/** Qualifier of the <code>ExtendedCitizenSimpleBannerComponent.secondContent</code> attribute **/
	public static final String SECONDCONTENT = "secondContent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(FIRSTCONTENT, AttributeMode.INITIAL);
		tmp.put(SECONDCONTENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedCitizenSimpleBannerComponent.firstContent</code> attribute.
	 * @return the firstContent - Localized First Content of the component.
	 */
	public String getFirstContent(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedCitizenSimpleBannerComponent.getFirstContent requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, FIRSTCONTENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedCitizenSimpleBannerComponent.firstContent</code> attribute.
	 * @return the firstContent - Localized First Content of the component.
	 */
	public String getFirstContent()
	{
		return getFirstContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedCitizenSimpleBannerComponent.firstContent</code> attribute. 
	 * @return the localized firstContent - Localized First Content of the component.
	 */
	public Map<Language,String> getAllFirstContent(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,FIRSTCONTENT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedCitizenSimpleBannerComponent.firstContent</code> attribute. 
	 * @return the localized firstContent - Localized First Content of the component.
	 */
	public Map<Language,String> getAllFirstContent()
	{
		return getAllFirstContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedCitizenSimpleBannerComponent.firstContent</code> attribute. 
	 * @param value the firstContent - Localized First Content of the component.
	 */
	public void setFirstContent(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedCitizenSimpleBannerComponent.setFirstContent requires a session language", 0 );
		}
		setLocalizedProperty(ctx, FIRSTCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedCitizenSimpleBannerComponent.firstContent</code> attribute. 
	 * @param value the firstContent - Localized First Content of the component.
	 */
	public void setFirstContent(final String value)
	{
		setFirstContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedCitizenSimpleBannerComponent.firstContent</code> attribute. 
	 * @param value the firstContent - Localized First Content of the component.
	 */
	public void setAllFirstContent(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,FIRSTCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedCitizenSimpleBannerComponent.firstContent</code> attribute. 
	 * @param value the firstContent - Localized First Content of the component.
	 */
	public void setAllFirstContent(final Map<Language,String> value)
	{
		setAllFirstContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedCitizenSimpleBannerComponent.secondContent</code> attribute.
	 * @return the secondContent - Localized Second Content of the component.
	 */
	public String getSecondContent(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedCitizenSimpleBannerComponent.getSecondContent requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, SECONDCONTENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedCitizenSimpleBannerComponent.secondContent</code> attribute.
	 * @return the secondContent - Localized Second Content of the component.
	 */
	public String getSecondContent()
	{
		return getSecondContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedCitizenSimpleBannerComponent.secondContent</code> attribute. 
	 * @return the localized secondContent - Localized Second Content of the component.
	 */
	public Map<Language,String> getAllSecondContent(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,SECONDCONTENT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedCitizenSimpleBannerComponent.secondContent</code> attribute. 
	 * @return the localized secondContent - Localized Second Content of the component.
	 */
	public Map<Language,String> getAllSecondContent()
	{
		return getAllSecondContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedCitizenSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setSecondContent(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedCitizenSimpleBannerComponent.setSecondContent requires a session language", 0 );
		}
		setLocalizedProperty(ctx, SECONDCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedCitizenSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setSecondContent(final String value)
	{
		setSecondContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedCitizenSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setAllSecondContent(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,SECONDCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedCitizenSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setAllSecondContent(final Map<Language,String> value)
	{
		setAllSecondContent( getSession().getSessionContext(), value );
	}
	
}
