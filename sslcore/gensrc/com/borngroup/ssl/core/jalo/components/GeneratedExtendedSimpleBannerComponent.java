/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleBannerComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.ExtendedSimpleBannerComponent ExtendedSimpleBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedExtendedSimpleBannerComponent extends SimpleBannerComponent
{
	/** Qualifier of the <code>ExtendedSimpleBannerComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>ExtendedSimpleBannerComponent.firstContent</code> attribute **/
	public static final String FIRSTCONTENT = "firstContent";
	/** Qualifier of the <code>ExtendedSimpleBannerComponent.secondContent</code> attribute **/
	public static final String SECONDCONTENT = "secondContent";
	/** Qualifier of the <code>ExtendedSimpleBannerComponent.firstLink</code> attribute **/
	public static final String FIRSTLINK = "firstLink";
	/** Qualifier of the <code>ExtendedSimpleBannerComponent.secondLink</code> attribute **/
	public static final String SECONDLINK = "secondLink";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(FIRSTCONTENT, AttributeMode.INITIAL);
		tmp.put(SECONDCONTENT, AttributeMode.INITIAL);
		tmp.put(FIRSTLINK, AttributeMode.INITIAL);
		tmp.put(SECONDLINK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.firstContent</code> attribute.
	 * @return the firstContent - Localized First Content of the component.
	 */
	public String getFirstContent(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedSimpleBannerComponent.getFirstContent requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, FIRSTCONTENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.firstContent</code> attribute.
	 * @return the firstContent - Localized First Content of the component.
	 */
	public String getFirstContent()
	{
		return getFirstContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.firstContent</code> attribute. 
	 * @return the localized firstContent - Localized First Content of the component.
	 */
	public Map<Language,String> getAllFirstContent(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,FIRSTCONTENT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.firstContent</code> attribute. 
	 * @return the localized firstContent - Localized First Content of the component.
	 */
	public Map<Language,String> getAllFirstContent()
	{
		return getAllFirstContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.firstContent</code> attribute. 
	 * @param value the firstContent - Localized First Content of the component.
	 */
	public void setFirstContent(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedSimpleBannerComponent.setFirstContent requires a session language", 0 );
		}
		setLocalizedProperty(ctx, FIRSTCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.firstContent</code> attribute. 
	 * @param value the firstContent - Localized First Content of the component.
	 */
	public void setFirstContent(final String value)
	{
		setFirstContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.firstContent</code> attribute. 
	 * @param value the firstContent - Localized First Content of the component.
	 */
	public void setAllFirstContent(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,FIRSTCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.firstContent</code> attribute. 
	 * @param value the firstContent - Localized First Content of the component.
	 */
	public void setAllFirstContent(final Map<Language,String> value)
	{
		setAllFirstContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.firstLink</code> attribute.
	 * @return the firstLink - First Link of the component.
	 */
	public CMSLinkComponent getFirstLink(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, FIRSTLINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.firstLink</code> attribute.
	 * @return the firstLink - First Link of the component.
	 */
	public CMSLinkComponent getFirstLink()
	{
		return getFirstLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.firstLink</code> attribute. 
	 * @param value the firstLink - First Link of the component.
	 */
	public void setFirstLink(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, FIRSTLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.firstLink</code> attribute. 
	 * @param value the firstLink - First Link of the component.
	 */
	public void setFirstLink(final CMSLinkComponent value)
	{
		setFirstLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.heading</code> attribute.
	 * @return the heading - Localized heading of the component.
	 */
	public String getHeading(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedSimpleBannerComponent.getHeading requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.heading</code> attribute.
	 * @return the heading - Localized heading of the component.
	 */
	public String getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.heading</code> attribute. 
	 * @return the localized heading - Localized heading of the component.
	 */
	public Map<Language,String> getAllHeading(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADING,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.heading</code> attribute. 
	 * @return the localized heading - Localized heading of the component.
	 */
	public Map<Language,String> getAllHeading()
	{
		return getAllHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.heading</code> attribute. 
	 * @param value the heading - Localized heading of the component.
	 */
	public void setHeading(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedSimpleBannerComponent.setHeading requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.heading</code> attribute. 
	 * @param value the heading - Localized heading of the component.
	 */
	public void setHeading(final String value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.heading</code> attribute. 
	 * @param value the heading - Localized heading of the component.
	 */
	public void setAllHeading(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.heading</code> attribute. 
	 * @param value the heading - Localized heading of the component.
	 */
	public void setAllHeading(final Map<Language,String> value)
	{
		setAllHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.secondContent</code> attribute.
	 * @return the secondContent - Localized Second Content of the component.
	 */
	public String getSecondContent(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedSimpleBannerComponent.getSecondContent requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, SECONDCONTENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.secondContent</code> attribute.
	 * @return the secondContent - Localized Second Content of the component.
	 */
	public String getSecondContent()
	{
		return getSecondContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.secondContent</code> attribute. 
	 * @return the localized secondContent - Localized Second Content of the component.
	 */
	public Map<Language,String> getAllSecondContent(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,SECONDCONTENT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.secondContent</code> attribute. 
	 * @return the localized secondContent - Localized Second Content of the component.
	 */
	public Map<Language,String> getAllSecondContent()
	{
		return getAllSecondContent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setSecondContent(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedExtendedSimpleBannerComponent.setSecondContent requires a session language", 0 );
		}
		setLocalizedProperty(ctx, SECONDCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setSecondContent(final String value)
	{
		setSecondContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setAllSecondContent(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,SECONDCONTENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.secondContent</code> attribute. 
	 * @param value the secondContent - Localized Second Content of the component.
	 */
	public void setAllSecondContent(final Map<Language,String> value)
	{
		setAllSecondContent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.secondLink</code> attribute.
	 * @return the secondLink - Second Link of the component.
	 */
	public CMSLinkComponent getSecondLink(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, SECONDLINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExtendedSimpleBannerComponent.secondLink</code> attribute.
	 * @return the secondLink - Second Link of the component.
	 */
	public CMSLinkComponent getSecondLink()
	{
		return getSecondLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.secondLink</code> attribute. 
	 * @param value the secondLink - Second Link of the component.
	 */
	public void setSecondLink(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, SECONDLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExtendedSimpleBannerComponent.secondLink</code> attribute. 
	 * @param value the secondLink - Second Link of the component.
	 */
	public void setSecondLink(final CMSLinkComponent value)
	{
		setSecondLink( getSession().getSessionContext(), value );
	}
	
}
