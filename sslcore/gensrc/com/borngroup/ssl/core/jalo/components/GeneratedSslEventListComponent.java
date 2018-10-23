/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslEventListComponent SslEventListComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslEventListComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SslEventListComponent.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>SslEventListComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>SslEventListComponent.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>SslEventListComponent.startTime</code> attribute **/
	public static final String STARTTIME = "startTime";
	/** Qualifier of the <code>SslEventListComponent.endTime</code> attribute **/
	public static final String ENDTIME = "endTime";
	/** Qualifier of the <code>SslEventListComponent.url</code> attribute **/
	public static final String URL = "url";
	/** Qualifier of the <code>SslEventListComponent.image</code> attribute **/
	public static final String IMAGE = "image";
	/** Qualifier of the <code>SslEventListComponent.buttonText</code> attribute **/
	public static final String BUTTONTEXT = "buttonText";
	/** Qualifier of the <code>SslEventListComponent.buttonUrl</code> attribute **/
	public static final String BUTTONURL = "buttonUrl";
	/** Qualifier of the <code>SslEventListComponent.discountPercentage</code> attribute **/
	public static final String DISCOUNTPERCENTAGE = "discountPercentage";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TITLE, AttributeMode.INITIAL);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(STARTTIME, AttributeMode.INITIAL);
		tmp.put(ENDTIME, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(IMAGE, AttributeMode.INITIAL);
		tmp.put(BUTTONTEXT, AttributeMode.INITIAL);
		tmp.put(BUTTONURL, AttributeMode.INITIAL);
		tmp.put(DISCOUNTPERCENTAGE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.buttonText</code> attribute.
	 * @return the buttonText - Localized button text of the component.
	 */
	public String getButtonText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslEventListComponent.getButtonText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, BUTTONTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.buttonText</code> attribute.
	 * @return the buttonText - Localized button text of the component.
	 */
	public String getButtonText()
	{
		return getButtonText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.buttonText</code> attribute. 
	 * @return the localized buttonText - Localized button text of the component.
	 */
	public Map<Language,String> getAllButtonText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,BUTTONTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.buttonText</code> attribute. 
	 * @return the localized buttonText - Localized button text of the component.
	 */
	public Map<Language,String> getAllButtonText()
	{
		return getAllButtonText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.buttonText</code> attribute. 
	 * @param value the buttonText - Localized button text of the component.
	 */
	public void setButtonText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslEventListComponent.setButtonText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, BUTTONTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.buttonText</code> attribute. 
	 * @param value the buttonText - Localized button text of the component.
	 */
	public void setButtonText(final String value)
	{
		setButtonText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.buttonText</code> attribute. 
	 * @param value the buttonText - Localized button text of the component.
	 */
	public void setAllButtonText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,BUTTONTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.buttonText</code> attribute. 
	 * @param value the buttonText - Localized button text of the component.
	 */
	public void setAllButtonText(final Map<Language,String> value)
	{
		setAllButtonText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.buttonUrl</code> attribute.
	 * @return the buttonUrl - Localized button url of the component.
	 */
	public String getButtonUrl(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslEventListComponent.getButtonUrl requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, BUTTONURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.buttonUrl</code> attribute.
	 * @return the buttonUrl - Localized button url of the component.
	 */
	public String getButtonUrl()
	{
		return getButtonUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.buttonUrl</code> attribute. 
	 * @return the localized buttonUrl - Localized button url of the component.
	 */
	public Map<Language,String> getAllButtonUrl(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,BUTTONURL,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.buttonUrl</code> attribute. 
	 * @return the localized buttonUrl - Localized button url of the component.
	 */
	public Map<Language,String> getAllButtonUrl()
	{
		return getAllButtonUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.buttonUrl</code> attribute. 
	 * @param value the buttonUrl - Localized button url of the component.
	 */
	public void setButtonUrl(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslEventListComponent.setButtonUrl requires a session language", 0 );
		}
		setLocalizedProperty(ctx, BUTTONURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.buttonUrl</code> attribute. 
	 * @param value the buttonUrl - Localized button url of the component.
	 */
	public void setButtonUrl(final String value)
	{
		setButtonUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.buttonUrl</code> attribute. 
	 * @param value the buttonUrl - Localized button url of the component.
	 */
	public void setAllButtonUrl(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,BUTTONURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.buttonUrl</code> attribute. 
	 * @param value the buttonUrl - Localized button url of the component.
	 */
	public void setAllButtonUrl(final Map<Language,String> value)
	{
		setAllButtonUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.description</code> attribute.
	 * @return the description
	 */
	public String getDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.description</code> attribute.
	 * @return the description
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.discountPercentage</code> attribute.
	 * @return the discountPercentage - Localized percentage range of the component.
	 */
	public String getDiscountPercentage(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslEventListComponent.getDiscountPercentage requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, DISCOUNTPERCENTAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.discountPercentage</code> attribute.
	 * @return the discountPercentage - Localized percentage range of the component.
	 */
	public String getDiscountPercentage()
	{
		return getDiscountPercentage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.discountPercentage</code> attribute. 
	 * @return the localized discountPercentage - Localized percentage range of the component.
	 */
	public Map<Language,String> getAllDiscountPercentage(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,DISCOUNTPERCENTAGE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.discountPercentage</code> attribute. 
	 * @return the localized discountPercentage - Localized percentage range of the component.
	 */
	public Map<Language,String> getAllDiscountPercentage()
	{
		return getAllDiscountPercentage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.discountPercentage</code> attribute. 
	 * @param value the discountPercentage - Localized percentage range of the component.
	 */
	public void setDiscountPercentage(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslEventListComponent.setDiscountPercentage requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DISCOUNTPERCENTAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.discountPercentage</code> attribute. 
	 * @param value the discountPercentage - Localized percentage range of the component.
	 */
	public void setDiscountPercentage(final String value)
	{
		setDiscountPercentage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.discountPercentage</code> attribute. 
	 * @param value the discountPercentage - Localized percentage range of the component.
	 */
	public void setAllDiscountPercentage(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,DISCOUNTPERCENTAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.discountPercentage</code> attribute. 
	 * @param value the discountPercentage - Localized percentage range of the component.
	 */
	public void setAllDiscountPercentage(final Map<Language,String> value)
	{
		setAllDiscountPercentage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.endTime</code> attribute.
	 * @return the endTime
	 */
	public Date getEndTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ENDTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.endTime</code> attribute.
	 * @return the endTime
	 */
	public Date getEndTime()
	{
		return getEndTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.endTime</code> attribute. 
	 * @param value the endTime
	 */
	public void setEndTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ENDTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.endTime</code> attribute. 
	 * @param value the endTime
	 */
	public void setEndTime(final Date value)
	{
		setEndTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.heading</code> attribute.
	 * @return the heading
	 */
	public String getHeading(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.heading</code> attribute.
	 * @return the heading
	 */
	public String getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.heading</code> attribute. 
	 * @param value the heading
	 */
	public void setHeading(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.heading</code> attribute. 
	 * @param value the heading
	 */
	public void setHeading(final String value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.image</code> attribute.
	 * @return the image
	 */
	public Media getImage(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, IMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.image</code> attribute.
	 * @return the image
	 */
	public Media getImage()
	{
		return getImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.image</code> attribute. 
	 * @param value the image
	 */
	public void setImage(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, IMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.image</code> attribute. 
	 * @param value the image
	 */
	public void setImage(final Media value)
	{
		setImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.startTime</code> attribute.
	 * @return the startTime
	 */
	public Date getStartTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, STARTTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.startTime</code> attribute.
	 * @return the startTime
	 */
	public Date getStartTime()
	{
		return getStartTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.startTime</code> attribute. 
	 * @param value the startTime
	 */
	public void setStartTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, STARTTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.startTime</code> attribute. 
	 * @param value the startTime
	 */
	public void setStartTime(final Date value)
	{
		setStartTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.title</code> attribute.
	 * @return the title - Localized title of the component.
	 */
	public String getTitle(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslEventListComponent.getTitle requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.title</code> attribute.
	 * @return the title - Localized title of the component.
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.title</code> attribute. 
	 * @return the localized title - Localized title of the component.
	 */
	public Map<Language,String> getAllTitle(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TITLE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.title</code> attribute. 
	 * @return the localized title - Localized title of the component.
	 */
	public Map<Language,String> getAllTitle()
	{
		return getAllTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.title</code> attribute. 
	 * @param value the title - Localized title of the component.
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslEventListComponent.setTitle requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.title</code> attribute. 
	 * @param value the title - Localized title of the component.
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.title</code> attribute. 
	 * @param value the title - Localized title of the component.
	 */
	public void setAllTitle(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.title</code> attribute. 
	 * @param value the title - Localized title of the component.
	 */
	public void setAllTitle(final Map<Language,String> value)
	{
		setAllTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.url</code> attribute.
	 * @return the url
	 */
	public String getUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslEventListComponent.url</code> attribute.
	 * @return the url
	 */
	public String getUrl()
	{
		return getUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.url</code> attribute. 
	 * @param value the url
	 */
	public void setUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslEventListComponent.url</code> attribute. 
	 * @param value the url
	 */
	public void setUrl(final String value)
	{
		setUrl( getSession().getSessionContext(), value );
	}
	
}
