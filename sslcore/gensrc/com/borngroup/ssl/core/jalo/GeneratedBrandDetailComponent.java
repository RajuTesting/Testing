/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.BrandDetailComponent BrandExploreCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBrandDetailComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>BrandExploreCollectionComponent.ExploreUrl</code> attribute **/
	public static final String EXPLOREURL = "ExploreUrl";
	/** Qualifier of the <code>BrandExploreCollectionComponent.header</code> attribute **/
	public static final String HEADER = "header";
	/** Qualifier of the <code>BrandExploreCollectionComponent.text</code> attribute **/
	public static final String TEXT = "text";
	/** Qualifier of the <code>BrandExploreCollectionComponent.ExploreText</code> attribute **/
	public static final String EXPLORETEXT = "ExploreText";
	/** Qualifier of the <code>BrandExploreCollectionComponent.CollectionText</code> attribute **/
	public static final String COLLECTIONTEXT = "CollectionText";
	/** Qualifier of the <code>BrandExploreCollectionComponent.CollectionUrl</code> attribute **/
	public static final String COLLECTIONURL = "CollectionUrl";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(EXPLOREURL, AttributeMode.INITIAL);
		tmp.put(HEADER, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		tmp.put(EXPLORETEXT, AttributeMode.INITIAL);
		tmp.put(COLLECTIONTEXT, AttributeMode.INITIAL);
		tmp.put(COLLECTIONURL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.CollectionText</code> attribute.
	 * @return the CollectionText - Text to display for the image
	 */
	public String getCollectionText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandDetailComponent.getCollectionText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, COLLECTIONTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.CollectionText</code> attribute.
	 * @return the CollectionText - Text to display for the image
	 */
	public String getCollectionText()
	{
		return getCollectionText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.CollectionText</code> attribute. 
	 * @return the localized CollectionText - Text to display for the image
	 */
	public Map<Language,String> getAllCollectionText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,COLLECTIONTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.CollectionText</code> attribute. 
	 * @return the localized CollectionText - Text to display for the image
	 */
	public Map<Language,String> getAllCollectionText()
	{
		return getAllCollectionText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.CollectionText</code> attribute. 
	 * @param value the CollectionText - Text to display for the image
	 */
	public void setCollectionText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandDetailComponent.setCollectionText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, COLLECTIONTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.CollectionText</code> attribute. 
	 * @param value the CollectionText - Text to display for the image
	 */
	public void setCollectionText(final String value)
	{
		setCollectionText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.CollectionText</code> attribute. 
	 * @param value the CollectionText - Text to display for the image
	 */
	public void setAllCollectionText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,COLLECTIONTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.CollectionText</code> attribute. 
	 * @param value the CollectionText - Text to display for the image
	 */
	public void setAllCollectionText(final Map<Language,String> value)
	{
		setAllCollectionText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.CollectionUrl</code> attribute.
	 * @return the CollectionUrl - Url link for the image
	 */
	public String getCollectionUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COLLECTIONURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.CollectionUrl</code> attribute.
	 * @return the CollectionUrl - Url link for the image
	 */
	public String getCollectionUrl()
	{
		return getCollectionUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.CollectionUrl</code> attribute. 
	 * @param value the CollectionUrl - Url link for the image
	 */
	public void setCollectionUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COLLECTIONURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.CollectionUrl</code> attribute. 
	 * @param value the CollectionUrl - Url link for the image
	 */
	public void setCollectionUrl(final String value)
	{
		setCollectionUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.ExploreText</code> attribute.
	 * @return the ExploreText - Text to display for the image
	 */
	public String getExploreText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandDetailComponent.getExploreText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, EXPLORETEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.ExploreText</code> attribute.
	 * @return the ExploreText - Text to display for the image
	 */
	public String getExploreText()
	{
		return getExploreText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.ExploreText</code> attribute. 
	 * @return the localized ExploreText - Text to display for the image
	 */
	public Map<Language,String> getAllExploreText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,EXPLORETEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.ExploreText</code> attribute. 
	 * @return the localized ExploreText - Text to display for the image
	 */
	public Map<Language,String> getAllExploreText()
	{
		return getAllExploreText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.ExploreText</code> attribute. 
	 * @param value the ExploreText - Text to display for the image
	 */
	public void setExploreText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandDetailComponent.setExploreText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, EXPLORETEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.ExploreText</code> attribute. 
	 * @param value the ExploreText - Text to display for the image
	 */
	public void setExploreText(final String value)
	{
		setExploreText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.ExploreText</code> attribute. 
	 * @param value the ExploreText - Text to display for the image
	 */
	public void setAllExploreText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,EXPLORETEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.ExploreText</code> attribute. 
	 * @param value the ExploreText - Text to display for the image
	 */
	public void setAllExploreText(final Map<Language,String> value)
	{
		setAllExploreText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.ExploreUrl</code> attribute.
	 * @return the ExploreUrl - Image to display
	 */
	public String getExploreUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EXPLOREURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.ExploreUrl</code> attribute.
	 * @return the ExploreUrl - Image to display
	 */
	public String getExploreUrl()
	{
		return getExploreUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.ExploreUrl</code> attribute. 
	 * @param value the ExploreUrl - Image to display
	 */
	public void setExploreUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EXPLOREURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.ExploreUrl</code> attribute. 
	 * @param value the ExploreUrl - Image to display
	 */
	public void setExploreUrl(final String value)
	{
		setExploreUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.header</code> attribute.
	 * @return the header - Text to display for the image
	 */
	public String getHeader(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandDetailComponent.getHeader requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.header</code> attribute.
	 * @return the header - Text to display for the image
	 */
	public String getHeader()
	{
		return getHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.header</code> attribute. 
	 * @return the localized header - Text to display for the image
	 */
	public Map<Language,String> getAllHeader(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADER,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.header</code> attribute. 
	 * @return the localized header - Text to display for the image
	 */
	public Map<Language,String> getAllHeader()
	{
		return getAllHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.header</code> attribute. 
	 * @param value the header - Text to display for the image
	 */
	public void setHeader(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandDetailComponent.setHeader requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.header</code> attribute. 
	 * @param value the header - Text to display for the image
	 */
	public void setHeader(final String value)
	{
		setHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.header</code> attribute. 
	 * @param value the header - Text to display for the image
	 */
	public void setAllHeader(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.header</code> attribute. 
	 * @param value the header - Text to display for the image
	 */
	public void setAllHeader(final Map<Language,String> value)
	{
		setAllHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandDetailComponent.getText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.text</code> attribute.
	 * @return the text - Text to display for the image
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BrandExploreCollectionComponent.text</code> attribute. 
	 * @return the localized text - Text to display for the image
	 */
	public Map<Language,String> getAllText()
	{
		return getAllText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBrandDetailComponent.setText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BrandExploreCollectionComponent.text</code> attribute. 
	 * @param value the text - Text to display for the image
	 */
	public void setAllText(final Map<Language,String> value)
	{
		setAllText( getSession().getSessionContext(), value );
	}
	
}
