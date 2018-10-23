/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.CMSMediaParagraphComponent;
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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslGiftImageTextComponent SslGiftImageTextComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslGiftImageTextComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SslGiftImageTextComponent.headerLeft</code> attribute **/
	public static final String HEADERLEFT = "headerLeft";
	/** Qualifier of the <code>SslGiftImageTextComponent.headerRight</code> attribute **/
	public static final String HEADERRIGHT = "headerRight";
	/** Qualifier of the <code>SslGiftImageTextComponent.mediaParagraphLeft</code> attribute **/
	public static final String MEDIAPARAGRAPHLEFT = "mediaParagraphLeft";
	/** Qualifier of the <code>SslGiftImageTextComponent.mediaParagraphRight</code> attribute **/
	public static final String MEDIAPARAGRAPHRIGHT = "mediaParagraphRight";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADERLEFT, AttributeMode.INITIAL);
		tmp.put(HEADERRIGHT, AttributeMode.INITIAL);
		tmp.put(MEDIAPARAGRAPHLEFT, AttributeMode.INITIAL);
		tmp.put(MEDIAPARAGRAPHRIGHT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.headerLeft</code> attribute.
	 * @return the headerLeft - Header Label
	 */
	public String getHeaderLeft(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslGiftImageTextComponent.getHeaderLeft requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADERLEFT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.headerLeft</code> attribute.
	 * @return the headerLeft - Header Label
	 */
	public String getHeaderLeft()
	{
		return getHeaderLeft( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.headerLeft</code> attribute. 
	 * @return the localized headerLeft - Header Label
	 */
	public Map<Language,String> getAllHeaderLeft(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADERLEFT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.headerLeft</code> attribute. 
	 * @return the localized headerLeft - Header Label
	 */
	public Map<Language,String> getAllHeaderLeft()
	{
		return getAllHeaderLeft( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.headerLeft</code> attribute. 
	 * @param value the headerLeft - Header Label
	 */
	public void setHeaderLeft(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslGiftImageTextComponent.setHeaderLeft requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADERLEFT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.headerLeft</code> attribute. 
	 * @param value the headerLeft - Header Label
	 */
	public void setHeaderLeft(final String value)
	{
		setHeaderLeft( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.headerLeft</code> attribute. 
	 * @param value the headerLeft - Header Label
	 */
	public void setAllHeaderLeft(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADERLEFT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.headerLeft</code> attribute. 
	 * @param value the headerLeft - Header Label
	 */
	public void setAllHeaderLeft(final Map<Language,String> value)
	{
		setAllHeaderLeft( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.headerRight</code> attribute.
	 * @return the headerRight - Header Label
	 */
	public String getHeaderRight(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslGiftImageTextComponent.getHeaderRight requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADERRIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.headerRight</code> attribute.
	 * @return the headerRight - Header Label
	 */
	public String getHeaderRight()
	{
		return getHeaderRight( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.headerRight</code> attribute. 
	 * @return the localized headerRight - Header Label
	 */
	public Map<Language,String> getAllHeaderRight(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADERRIGHT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.headerRight</code> attribute. 
	 * @return the localized headerRight - Header Label
	 */
	public Map<Language,String> getAllHeaderRight()
	{
		return getAllHeaderRight( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.headerRight</code> attribute. 
	 * @param value the headerRight - Header Label
	 */
	public void setHeaderRight(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSslGiftImageTextComponent.setHeaderRight requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADERRIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.headerRight</code> attribute. 
	 * @param value the headerRight - Header Label
	 */
	public void setHeaderRight(final String value)
	{
		setHeaderRight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.headerRight</code> attribute. 
	 * @param value the headerRight - Header Label
	 */
	public void setAllHeaderRight(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADERRIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.headerRight</code> attribute. 
	 * @param value the headerRight - Header Label
	 */
	public void setAllHeaderRight(final Map<Language,String> value)
	{
		setAllHeaderRight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.mediaParagraphLeft</code> attribute.
	 * @return the mediaParagraphLeft - Media Paragraph component
	 */
	public CMSMediaParagraphComponent getMediaParagraphLeft(final SessionContext ctx)
	{
		return (CMSMediaParagraphComponent)getProperty( ctx, MEDIAPARAGRAPHLEFT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.mediaParagraphLeft</code> attribute.
	 * @return the mediaParagraphLeft - Media Paragraph component
	 */
	public CMSMediaParagraphComponent getMediaParagraphLeft()
	{
		return getMediaParagraphLeft( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.mediaParagraphLeft</code> attribute. 
	 * @param value the mediaParagraphLeft - Media Paragraph component
	 */
	public void setMediaParagraphLeft(final SessionContext ctx, final CMSMediaParagraphComponent value)
	{
		setProperty(ctx, MEDIAPARAGRAPHLEFT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.mediaParagraphLeft</code> attribute. 
	 * @param value the mediaParagraphLeft - Media Paragraph component
	 */
	public void setMediaParagraphLeft(final CMSMediaParagraphComponent value)
	{
		setMediaParagraphLeft( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.mediaParagraphRight</code> attribute.
	 * @return the mediaParagraphRight - Media Paragraph component
	 */
	public CMSMediaParagraphComponent getMediaParagraphRight(final SessionContext ctx)
	{
		return (CMSMediaParagraphComponent)getProperty( ctx, MEDIAPARAGRAPHRIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslGiftImageTextComponent.mediaParagraphRight</code> attribute.
	 * @return the mediaParagraphRight - Media Paragraph component
	 */
	public CMSMediaParagraphComponent getMediaParagraphRight()
	{
		return getMediaParagraphRight( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.mediaParagraphRight</code> attribute. 
	 * @param value the mediaParagraphRight - Media Paragraph component
	 */
	public void setMediaParagraphRight(final SessionContext ctx, final CMSMediaParagraphComponent value)
	{
		setProperty(ctx, MEDIAPARAGRAPHRIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslGiftImageTextComponent.mediaParagraphRight</code> attribute. 
	 * @param value the mediaParagraphRight - Media Paragraph component
	 */
	public void setMediaParagraphRight(final CMSMediaParagraphComponent value)
	{
		setMediaParagraphRight( getSession().getSessionContext(), value );
	}
	
}
