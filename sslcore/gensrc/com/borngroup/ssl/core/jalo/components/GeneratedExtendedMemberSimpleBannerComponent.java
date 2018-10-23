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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.ExtendedMemberSimpleBannerComponent ExtendedMemberSimpleBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedExtendedMemberSimpleBannerComponent extends SimpleBannerComponent
{
	/** Qualifier of the <code>ExtendedMemberSimpleBannerComponent.header</code> attribute **/
	public static final String HEADER = "header";
	/** Qualifier of the <code>ExtendedMemberSimpleBannerComponent.secondContent</code> attribute **/
	public static final String SECONDCONTENT = "secondContent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADER, AttributeMode.INITIAL);
		tmp.put(SECONDCONTENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedMemberSimpleBannerComponent.header</code> attribute.
	 * @return the header - Localized First Content of the component.
	 */
	public String getHeader(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedMemberSimpleBannerComponent.getHeader requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedMemberSimpleBannerComponent.header</code> attribute.
	 * @return the header - Localized First Content of the component.
	 */
	public String getHeader()
	{
		return getHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedMemberSimpleBannerComponent.header</code> attribute. 
	 * @return the localized header - Localized First Content of the component.
	 */
	public Map<Language,String> getAllHeader(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADER,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedMemberSimpleBannerComponent.header</code> attribute. 
	 * @return the localized header - Localized First Content of the component.
	 */
	public Map<Language,String> getAllHeader()
	{
		return getAllHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedMemberSimpleBannerComponent.header</code> attribute. 
	 * @param value the header - Localized First Content of the component.
	 */
	public void setHeader(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedMemberSimpleBannerComponent.setHeader requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedMemberSimpleBannerComponent.header</code> attribute. 
	 * @param value the header - Localized First Content of the component.
	 */
	public void setHeader(final String value)
	{
		setHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedMemberSimpleBannerComponent.header</code> attribute. 
	 * @param value the header - Localized First Content of the component.
	 */
	public void setAllHeader(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedMemberSimpleBannerComponent.header</code> attribute. 
	 * @param value the header - Localized First Content of the component.
	 */
	public void setAllHeader(final Map<Language,String> value)
	{
		setAllHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedMemberSimpleBannerComponent.secondContent</code> attribute.
	 * @return the secondContent - Localized Second Content of the component.
	 */
	public String getSecondContent(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedMemberSimpleBannerComponent.getSecondContent requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, SECONDCONTENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedMemberSimpleBannerComponent.secondContent</code> attribute.
	 * @return the secondContent - Localized Second Content of the component.
	 */
	public String getSecondContent()
	{
		return getSecondContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedMemberSimpleBannerComponent.secondContent</code> attribute. 
	 * @return the localized secondContent - Localized Second Content of the component.
	 */
	public Map<Language,String> getAllSecondContent(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,SECONDCONTENT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedMemberSimpleBannerComponent.secondContent</code> attribute. 
	 * @return the localized secondContent - Localized Second Content of the component.
	 */
	public Map<Language,String> getAllSecondContent()
	{
		return getAllSecondContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedMemberSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setSecondContent(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedMemberSimpleBannerComponent.setSecondContent requires a session language", 0 );
		}
		setLocalizedProperty(ctx, SECONDCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedMemberSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setSecondContent(final String value)
	{
		setSecondContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedMemberSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setAllSecondContent(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,SECONDCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedMemberSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setAllSecondContent(final Map<Language,String> value)
	{
		setAllSecondContent( getSession().getSessionContext(), value );
	}
	
}
