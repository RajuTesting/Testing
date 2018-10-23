/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.FirstCitizenCmsComponent FirstCitizenCmsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedFirstCitizenCmsComponent extends CMSImageComponent
{
	/** Qualifier of the <code>FirstCitizenCmsComponent.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>FirstCitizenCmsComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>FirstCitizenCmsComponent.linkText</code> attribute **/
	public static final String LINKTEXT = "linkText";
	/** Qualifier of the <code>FirstCitizenCmsComponent.link</code> attribute **/
	public static final String LINK = "link";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(LINKTEXT, AttributeMode.INITIAL);
		tmp.put(LINK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.description</code> attribute.
	 * @return the description - description for the image
	 */
	public String getDescription(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedFirstCitizenCmsComponent.getDescription requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.description</code> attribute.
	 * @return the description - description for the image
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.description</code> attribute. 
	 * @return the localized description - description for the image
	 */
	public Map<Language,String> getAllDescription(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,DESCRIPTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.description</code> attribute. 
	 * @return the localized description - description for the image
	 */
	public Map<Language,String> getAllDescription()
	{
		return getAllDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.description</code> attribute. 
	 * @param value the description - description for the image
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedFirstCitizenCmsComponent.setDescription requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.description</code> attribute. 
	 * @param value the description - description for the image
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.description</code> attribute. 
	 * @param value the description - description for the image
	 */
	public void setAllDescription(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.description</code> attribute. 
	 * @param value the description - description for the image
	 */
	public void setAllDescription(final Map<Language,String> value)
	{
		setAllDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.heading</code> attribute.
	 * @return the heading - description for the image
	 */
	public String getHeading(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedFirstCitizenCmsComponent.getHeading requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.heading</code> attribute.
	 * @return the heading - description for the image
	 */
	public String getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.heading</code> attribute. 
	 * @return the localized heading - description for the image
	 */
	public Map<Language,String> getAllHeading(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADING,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.heading</code> attribute. 
	 * @return the localized heading - description for the image
	 */
	public Map<Language,String> getAllHeading()
	{
		return getAllHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.heading</code> attribute. 
	 * @param value the heading - description for the image
	 */
	public void setHeading(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedFirstCitizenCmsComponent.setHeading requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.heading</code> attribute. 
	 * @param value the heading - description for the image
	 */
	public void setHeading(final String value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.heading</code> attribute. 
	 * @param value the heading - description for the image
	 */
	public void setAllHeading(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.heading</code> attribute. 
	 * @param value the heading - description for the image
	 */
	public void setAllHeading(final Map<Language,String> value)
	{
		setAllHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.link</code> attribute.
	 * @return the link - description for the image
	 */
	public String getLink(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.link</code> attribute.
	 * @return the link - description for the image
	 */
	public String getLink()
	{
		return getLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.link</code> attribute. 
	 * @param value the link - description for the image
	 */
	public void setLink(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.link</code> attribute. 
	 * @param value the link - description for the image
	 */
	public void setLink(final String value)
	{
		setLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.linkText</code> attribute.
	 * @return the linkText - description for the image
	 */
	public String getLinkText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedFirstCitizenCmsComponent.getLinkText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, LINKTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.linkText</code> attribute.
	 * @return the linkText - description for the image
	 */
	public String getLinkText()
	{
		return getLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.linkText</code> attribute. 
	 * @return the localized linkText - description for the image
	 */
	public Map<Language,String> getAllLinkText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,LINKTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FirstCitizenCmsComponent.linkText</code> attribute. 
	 * @return the localized linkText - description for the image
	 */
	public Map<Language,String> getAllLinkText()
	{
		return getAllLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.linkText</code> attribute. 
	 * @param value the linkText - description for the image
	 */
	public void setLinkText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedFirstCitizenCmsComponent.setLinkText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.linkText</code> attribute. 
	 * @param value the linkText - description for the image
	 */
	public void setLinkText(final String value)
	{
		setLinkText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.linkText</code> attribute. 
	 * @param value the linkText - description for the image
	 */
	public void setAllLinkText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FirstCitizenCmsComponent.linkText</code> attribute. 
	 * @param value the linkText - description for the image
	 */
	public void setAllLinkText(final Map<Language,String> value)
	{
		setAllLinkText( getSession().getSessionContext(), value );
	}
	
}
