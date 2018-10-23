/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLStyleHubComponent SSLStyleHubComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLStyleHubComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLStyleHubComponent.styleHubImageComponent</code> attribute **/
	public static final String STYLEHUBIMAGECOMPONENT = "styleHubImageComponent";
	/** Qualifier of the <code>SSLStyleHubComponent.styleHubTitle</code> attribute **/
	public static final String STYLEHUBTITLE = "styleHubTitle";
	/** Qualifier of the <code>SSLStyleHubComponent.styleHubDescription</code> attribute **/
	public static final String STYLEHUBDESCRIPTION = "styleHubDescription";
	/** Qualifier of the <code>SSLStyleHubComponent.styleHubReadMoreLink</code> attribute **/
	public static final String STYLEHUBREADMORELINK = "styleHubReadMoreLink";
	/** Qualifier of the <code>SSLStyleHubComponent.postedDate</code> attribute **/
	public static final String POSTEDDATE = "postedDate";
	/** Qualifier of the <code>SSLStyleHubComponent.styleHubSubTitle</code> attribute **/
	public static final String STYLEHUBSUBTITLE = "styleHubSubTitle";
	/** Qualifier of the <code>SSLStyleHubComponent.activeFrom</code> attribute **/
	public static final String ACTIVEFROM = "activeFrom";
	/** Qualifier of the <code>SSLStyleHubComponent.activeUntil</code> attribute **/
	public static final String ACTIVEUNTIL = "activeUntil";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(STYLEHUBIMAGECOMPONENT, AttributeMode.INITIAL);
		tmp.put(STYLEHUBTITLE, AttributeMode.INITIAL);
		tmp.put(STYLEHUBDESCRIPTION, AttributeMode.INITIAL);
		tmp.put(STYLEHUBREADMORELINK, AttributeMode.INITIAL);
		tmp.put(POSTEDDATE, AttributeMode.INITIAL);
		tmp.put(STYLEHUBSUBTITLE, AttributeMode.INITIAL);
		tmp.put(ACTIVEFROM, AttributeMode.INITIAL);
		tmp.put(ACTIVEUNTIL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEFROM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.activeFrom</code> attribute.
	 * @return the activeFrom
	 */
	public Date getActiveFrom()
	{
		return getActiveFrom( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEFROM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.activeFrom</code> attribute. 
	 * @param value the activeFrom
	 */
	public void setActiveFrom(final Date value)
	{
		setActiveFrom( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ACTIVEUNTIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.activeUntil</code> attribute.
	 * @return the activeUntil
	 */
	public Date getActiveUntil()
	{
		return getActiveUntil( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ACTIVEUNTIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.activeUntil</code> attribute. 
	 * @param value the activeUntil
	 */
	public void setActiveUntil(final Date value)
	{
		setActiveUntil( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.postedDate</code> attribute.
	 * @return the postedDate
	 */
	public Date getPostedDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, POSTEDDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.postedDate</code> attribute.
	 * @return the postedDate
	 */
	public Date getPostedDate()
	{
		return getPostedDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.postedDate</code> attribute. 
	 * @param value the postedDate
	 */
	public void setPostedDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, POSTEDDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.postedDate</code> attribute. 
	 * @param value the postedDate
	 */
	public void setPostedDate(final Date value)
	{
		setPostedDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubDescription</code> attribute.
	 * @return the styleHubDescription
	 */
	public CMSParagraphComponent getStyleHubDescription(final SessionContext ctx)
	{
		return (CMSParagraphComponent)getProperty( ctx, STYLEHUBDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubDescription</code> attribute.
	 * @return the styleHubDescription
	 */
	public CMSParagraphComponent getStyleHubDescription()
	{
		return getStyleHubDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubDescription</code> attribute. 
	 * @param value the styleHubDescription
	 */
	public void setStyleHubDescription(final SessionContext ctx, final CMSParagraphComponent value)
	{
		setProperty(ctx, STYLEHUBDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubDescription</code> attribute. 
	 * @param value the styleHubDescription
	 */
	public void setStyleHubDescription(final CMSParagraphComponent value)
	{
		setStyleHubDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubImageComponent</code> attribute.
	 * @return the styleHubImageComponent
	 */
	public Media getStyleHubImageComponent(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSLStyleHubComponent.getStyleHubImageComponent requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, STYLEHUBIMAGECOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubImageComponent</code> attribute.
	 * @return the styleHubImageComponent
	 */
	public Media getStyleHubImageComponent()
	{
		return getStyleHubImageComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubImageComponent</code> attribute. 
	 * @return the localized styleHubImageComponent
	 */
	public Map<Language,Media> getAllStyleHubImageComponent(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,STYLEHUBIMAGECOMPONENT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubImageComponent</code> attribute. 
	 * @return the localized styleHubImageComponent
	 */
	public Map<Language,Media> getAllStyleHubImageComponent()
	{
		return getAllStyleHubImageComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubImageComponent</code> attribute. 
	 * @param value the styleHubImageComponent
	 */
	public void setStyleHubImageComponent(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSLStyleHubComponent.setStyleHubImageComponent requires a session language", 0 );
		}
		setLocalizedProperty(ctx, STYLEHUBIMAGECOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubImageComponent</code> attribute. 
	 * @param value the styleHubImageComponent
	 */
	public void setStyleHubImageComponent(final Media value)
	{
		setStyleHubImageComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubImageComponent</code> attribute. 
	 * @param value the styleHubImageComponent
	 */
	public void setAllStyleHubImageComponent(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,STYLEHUBIMAGECOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubImageComponent</code> attribute. 
	 * @param value the styleHubImageComponent
	 */
	public void setAllStyleHubImageComponent(final Map<Language,Media> value)
	{
		setAllStyleHubImageComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubReadMoreLink</code> attribute.
	 * @return the styleHubReadMoreLink
	 */
	public CMSLinkComponent getStyleHubReadMoreLink(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, STYLEHUBREADMORELINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubReadMoreLink</code> attribute.
	 * @return the styleHubReadMoreLink
	 */
	public CMSLinkComponent getStyleHubReadMoreLink()
	{
		return getStyleHubReadMoreLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubReadMoreLink</code> attribute. 
	 * @param value the styleHubReadMoreLink
	 */
	public void setStyleHubReadMoreLink(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, STYLEHUBREADMORELINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubReadMoreLink</code> attribute. 
	 * @param value the styleHubReadMoreLink
	 */
	public void setStyleHubReadMoreLink(final CMSLinkComponent value)
	{
		setStyleHubReadMoreLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubSubTitle</code> attribute.
	 * @return the styleHubSubTitle
	 */
	public String getStyleHubSubTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STYLEHUBSUBTITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubSubTitle</code> attribute.
	 * @return the styleHubSubTitle
	 */
	public String getStyleHubSubTitle()
	{
		return getStyleHubSubTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubSubTitle</code> attribute. 
	 * @param value the styleHubSubTitle
	 */
	public void setStyleHubSubTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STYLEHUBSUBTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubSubTitle</code> attribute. 
	 * @param value the styleHubSubTitle
	 */
	public void setStyleHubSubTitle(final String value)
	{
		setStyleHubSubTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubTitle</code> attribute.
	 * @return the styleHubTitle
	 */
	public String getStyleHubTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STYLEHUBTITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubComponent.styleHubTitle</code> attribute.
	 * @return the styleHubTitle
	 */
	public String getStyleHubTitle()
	{
		return getStyleHubTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubTitle</code> attribute. 
	 * @param value the styleHubTitle
	 */
	public void setStyleHubTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STYLEHUBTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubComponent.styleHubTitle</code> attribute. 
	 * @param value the styleHubTitle
	 */
	public void setStyleHubTitle(final String value)
	{
		setStyleHubTitle( getSession().getSessionContext(), value );
	}
	
}
