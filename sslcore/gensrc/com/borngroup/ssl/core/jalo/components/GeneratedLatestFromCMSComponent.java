/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.LatestFromCMSComponent LatestFromCMSComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedLatestFromCMSComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>LatestFromCMSComponent.latestFromSSImage</code> attribute **/
	public static final String LATESTFROMSSIMAGE = "latestFromSSImage";
	/** Qualifier of the <code>LatestFromCMSComponent.linkText</code> attribute **/
	public static final String LINKTEXT = "linkText";
	/** Qualifier of the <code>LatestFromCMSComponent.bottomText</code> attribute **/
	public static final String BOTTOMTEXT = "bottomText";
	/** Qualifier of the <code>LatestFromCMSComponent.linkUrl</code> attribute **/
	public static final String LINKURL = "linkUrl";
	/** Qualifier of the <code>LatestFromCMSComponent.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>LatestFromCMSComponent.defaultBanner</code> attribute **/
	public static final String DEFAULTBANNER = "defaultBanner";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LATESTFROMSSIMAGE, AttributeMode.INITIAL);
		tmp.put(LINKTEXT, AttributeMode.INITIAL);
		tmp.put(BOTTOMTEXT, AttributeMode.INITIAL);
		tmp.put(LINKURL, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(DEFAULTBANNER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.bottomText</code> attribute.
	 * @return the bottomText - Text to display for the image
	 */
	public String getBottomText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedLatestFromCMSComponent.getBottomText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, BOTTOMTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.bottomText</code> attribute.
	 * @return the bottomText - Text to display for the image
	 */
	public String getBottomText()
	{
		return getBottomText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.bottomText</code> attribute. 
	 * @return the localized bottomText - Text to display for the image
	 */
	public Map<Language,String> getAllBottomText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,BOTTOMTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.bottomText</code> attribute. 
	 * @return the localized bottomText - Text to display for the image
	 */
	public Map<Language,String> getAllBottomText()
	{
		return getAllBottomText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.bottomText</code> attribute. 
	 * @param value the bottomText - Text to display for the image
	 */
	public void setBottomText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedLatestFromCMSComponent.setBottomText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, BOTTOMTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.bottomText</code> attribute. 
	 * @param value the bottomText - Text to display for the image
	 */
	public void setBottomText(final String value)
	{
		setBottomText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.bottomText</code> attribute. 
	 * @param value the bottomText - Text to display for the image
	 */
	public void setAllBottomText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,BOTTOMTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.bottomText</code> attribute. 
	 * @param value the bottomText - Text to display for the image
	 */
	public void setAllBottomText(final Map<Language,String> value)
	{
		setAllBottomText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public CMSImageComponent getDefaultBanner(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, DEFAULTBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.defaultBanner</code> attribute.
	 * @return the defaultBanner
	 */
	public CMSImageComponent getDefaultBanner()
	{
		return getDefaultBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, DEFAULTBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.defaultBanner</code> attribute. 
	 * @param value the defaultBanner
	 */
	public void setDefaultBanner(final CMSImageComponent value)
	{
		setDefaultBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.description</code> attribute.
	 * @return the description - Url link for the image
	 */
	public String getDescription(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedLatestFromCMSComponent.getDescription requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.description</code> attribute.
	 * @return the description - Url link for the image
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.description</code> attribute. 
	 * @return the localized description - Url link for the image
	 */
	public Map<Language,String> getAllDescription(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,DESCRIPTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.description</code> attribute. 
	 * @return the localized description - Url link for the image
	 */
	public Map<Language,String> getAllDescription()
	{
		return getAllDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.description</code> attribute. 
	 * @param value the description - Url link for the image
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedLatestFromCMSComponent.setDescription requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.description</code> attribute. 
	 * @param value the description - Url link for the image
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.description</code> attribute. 
	 * @param value the description - Url link for the image
	 */
	public void setAllDescription(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.description</code> attribute. 
	 * @param value the description - Url link for the image
	 */
	public void setAllDescription(final Map<Language,String> value)
	{
		setAllDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.latestFromSSImage</code> attribute.
	 * @return the latestFromSSImage - Image to display
	 */
	public CMSImageComponent getLatestFromSSImage(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, LATESTFROMSSIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.latestFromSSImage</code> attribute.
	 * @return the latestFromSSImage - Image to display
	 */
	public CMSImageComponent getLatestFromSSImage()
	{
		return getLatestFromSSImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.latestFromSSImage</code> attribute. 
	 * @param value the latestFromSSImage - Image to display
	 */
	public void setLatestFromSSImage(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, LATESTFROMSSIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.latestFromSSImage</code> attribute. 
	 * @param value the latestFromSSImage - Image to display
	 */
	public void setLatestFromSSImage(final CMSImageComponent value)
	{
		setLatestFromSSImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.linkText</code> attribute.
	 * @return the linkText - Text to display for the image
	 */
	public String getLinkText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedLatestFromCMSComponent.getLinkText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, LINKTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.linkText</code> attribute.
	 * @return the linkText - Text to display for the image
	 */
	public String getLinkText()
	{
		return getLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.linkText</code> attribute. 
	 * @return the localized linkText - Text to display for the image
	 */
	public Map<Language,String> getAllLinkText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,LINKTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.linkText</code> attribute. 
	 * @return the localized linkText - Text to display for the image
	 */
	public Map<Language,String> getAllLinkText()
	{
		return getAllLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.linkText</code> attribute. 
	 * @param value the linkText - Text to display for the image
	 */
	public void setLinkText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedLatestFromCMSComponent.setLinkText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.linkText</code> attribute. 
	 * @param value the linkText - Text to display for the image
	 */
	public void setLinkText(final String value)
	{
		setLinkText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.linkText</code> attribute. 
	 * @param value the linkText - Text to display for the image
	 */
	public void setAllLinkText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.linkText</code> attribute. 
	 * @param value the linkText - Text to display for the image
	 */
	public void setAllLinkText(final Map<Language,String> value)
	{
		setAllLinkText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.linkUrl</code> attribute.
	 * @return the linkUrl - Url link for the image
	 */
	public String getLinkUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LatestFromCMSComponent.linkUrl</code> attribute.
	 * @return the linkUrl - Url link for the image
	 */
	public String getLinkUrl()
	{
		return getLinkUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.linkUrl</code> attribute. 
	 * @param value the linkUrl - Url link for the image
	 */
	public void setLinkUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LatestFromCMSComponent.linkUrl</code> attribute. 
	 * @param value the linkUrl - Url link for the image
	 */
	public void setLinkUrl(final String value)
	{
		setLinkUrl( getSession().getSessionContext(), value );
	}
	
}
