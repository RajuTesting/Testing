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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.NewArrivalsCMSComponent NewArrivalsCMSComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedNewArrivalsCMSComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>NewArrivalsCMSComponent.newArrivalsSSImage</code> attribute **/
	public static final String NEWARRIVALSSSIMAGE = "newArrivalsSSImage";
	/** Qualifier of the <code>NewArrivalsCMSComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>NewArrivalsCMSComponent.linkUrl</code> attribute **/
	public static final String LINKURL = "linkUrl";
	/** Qualifier of the <code>NewArrivalsCMSComponent.linkText</code> attribute **/
	public static final String LINKTEXT = "linkText";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(NEWARRIVALSSSIMAGE, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(LINKURL, AttributeMode.INITIAL);
		tmp.put(LINKTEXT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.linkText</code> attribute.
	 * @return the linkText - Link Text link for the image
	 */
	public String getLinkText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedNewArrivalsCMSComponent.getLinkText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, LINKTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.linkText</code> attribute.
	 * @return the linkText - Link Text link for the image
	 */
	public String getLinkText()
	{
		return getLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.linkText</code> attribute. 
	 * @return the localized linkText - Link Text link for the image
	 */
	public Map<Language,String> getAllLinkText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,LINKTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.linkText</code> attribute. 
	 * @return the localized linkText - Link Text link for the image
	 */
	public Map<Language,String> getAllLinkText()
	{
		return getAllLinkText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.linkText</code> attribute. 
	 * @param value the linkText - Link Text link for the image
	 */
	public void setLinkText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedNewArrivalsCMSComponent.setLinkText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.linkText</code> attribute. 
	 * @param value the linkText - Link Text link for the image
	 */
	public void setLinkText(final String value)
	{
		setLinkText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.linkText</code> attribute. 
	 * @param value the linkText - Link Text link for the image
	 */
	public void setAllLinkText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,LINKTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.linkText</code> attribute. 
	 * @param value the linkText - Link Text link for the image
	 */
	public void setAllLinkText(final Map<Language,String> value)
	{
		setAllLinkText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.linkUrl</code> attribute.
	 * @return the linkUrl - Link Url link for the image
	 */
	public String getLinkUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.linkUrl</code> attribute.
	 * @return the linkUrl - Link Url link for the image
	 */
	public String getLinkUrl()
	{
		return getLinkUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.linkUrl</code> attribute. 
	 * @param value the linkUrl - Link Url link for the image
	 */
	public void setLinkUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.linkUrl</code> attribute. 
	 * @param value the linkUrl - Link Url link for the image
	 */
	public void setLinkUrl(final String value)
	{
		setLinkUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.newArrivalsSSImage</code> attribute.
	 * @return the newArrivalsSSImage - Image to display
	 */
	public CMSImageComponent getNewArrivalsSSImage(final SessionContext ctx)
	{
		return (CMSImageComponent)getProperty( ctx, NEWARRIVALSSSIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.newArrivalsSSImage</code> attribute.
	 * @return the newArrivalsSSImage - Image to display
	 */
	public CMSImageComponent getNewArrivalsSSImage()
	{
		return getNewArrivalsSSImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.newArrivalsSSImage</code> attribute. 
	 * @param value the newArrivalsSSImage - Image to display
	 */
	public void setNewArrivalsSSImage(final SessionContext ctx, final CMSImageComponent value)
	{
		setProperty(ctx, NEWARRIVALSSSIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.newArrivalsSSImage</code> attribute. 
	 * @param value the newArrivalsSSImage - Image to display
	 */
	public void setNewArrivalsSSImage(final CMSImageComponent value)
	{
		setNewArrivalsSSImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedNewArrivalsCMSComponent.getText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>NewArrivalsCMSComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText()
	{
		return getAllText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedNewArrivalsCMSComponent.setText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>NewArrivalsCMSComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final Map<Language,String> value)
	{
		setAllText( getSession().getSessionContext(), value );
	}
	
}
