/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLTestimonialComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLTestimonialCollectionComponent SSLTestimonialCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLTestimonialCollectionComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLTestimonialCollectionComponent.testimonialCollectionTitle</code> attribute **/
	public static final String TESTIMONIALCOLLECTIONTITLE = "testimonialCollectionTitle";
	/** Qualifier of the <code>SSLTestimonialCollectionComponent.testimonialCollectionImageComponent</code> attribute **/
	public static final String TESTIMONIALCOLLECTIONIMAGECOMPONENT = "testimonialCollectionImageComponent";
	/** Qualifier of the <code>SSLTestimonialCollectionComponent.testimonialCollectionComponent</code> attribute **/
	public static final String TESTIMONIALCOLLECTIONCOMPONENT = "testimonialCollectionComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TESTIMONIALCOLLECTIONTITLE, AttributeMode.INITIAL);
		tmp.put(TESTIMONIALCOLLECTIONIMAGECOMPONENT, AttributeMode.INITIAL);
		tmp.put(TESTIMONIALCOLLECTIONCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionComponent</code> attribute.
	 * @return the testimonialCollectionComponent
	 */
	public List<SSLTestimonialComponent> getTestimonialCollectionComponent(final SessionContext ctx)
	{
		List<SSLTestimonialComponent> coll = (List<SSLTestimonialComponent>)getProperty( ctx, TESTIMONIALCOLLECTIONCOMPONENT);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionComponent</code> attribute.
	 * @return the testimonialCollectionComponent
	 */
	public List<SSLTestimonialComponent> getTestimonialCollectionComponent()
	{
		return getTestimonialCollectionComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionComponent</code> attribute. 
	 * @param value the testimonialCollectionComponent
	 */
	public void setTestimonialCollectionComponent(final SessionContext ctx, final List<SSLTestimonialComponent> value)
	{
		setProperty(ctx, TESTIMONIALCOLLECTIONCOMPONENT,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionComponent</code> attribute. 
	 * @param value the testimonialCollectionComponent
	 */
	public void setTestimonialCollectionComponent(final List<SSLTestimonialComponent> value)
	{
		setTestimonialCollectionComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionImageComponent</code> attribute.
	 * @return the testimonialCollectionImageComponent
	 */
	public Media getTestimonialCollectionImageComponent(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSLTestimonialCollectionComponent.getTestimonialCollectionImageComponent requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, TESTIMONIALCOLLECTIONIMAGECOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionImageComponent</code> attribute.
	 * @return the testimonialCollectionImageComponent
	 */
	public Media getTestimonialCollectionImageComponent()
	{
		return getTestimonialCollectionImageComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionImageComponent</code> attribute. 
	 * @return the localized testimonialCollectionImageComponent
	 */
	public Map<Language,Media> getAllTestimonialCollectionImageComponent(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,TESTIMONIALCOLLECTIONIMAGECOMPONENT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionImageComponent</code> attribute. 
	 * @return the localized testimonialCollectionImageComponent
	 */
	public Map<Language,Media> getAllTestimonialCollectionImageComponent()
	{
		return getAllTestimonialCollectionImageComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionImageComponent</code> attribute. 
	 * @param value the testimonialCollectionImageComponent
	 */
	public void setTestimonialCollectionImageComponent(final SessionContext ctx, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSLTestimonialCollectionComponent.setTestimonialCollectionImageComponent requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TESTIMONIALCOLLECTIONIMAGECOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionImageComponent</code> attribute. 
	 * @param value the testimonialCollectionImageComponent
	 */
	public void setTestimonialCollectionImageComponent(final Media value)
	{
		setTestimonialCollectionImageComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionImageComponent</code> attribute. 
	 * @param value the testimonialCollectionImageComponent
	 */
	public void setAllTestimonialCollectionImageComponent(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,TESTIMONIALCOLLECTIONIMAGECOMPONENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionImageComponent</code> attribute. 
	 * @param value the testimonialCollectionImageComponent
	 */
	public void setAllTestimonialCollectionImageComponent(final Map<Language,Media> value)
	{
		setAllTestimonialCollectionImageComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionTitle</code> attribute.
	 * @return the testimonialCollectionTitle
	 */
	public String getTestimonialCollectionTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TESTIMONIALCOLLECTIONTITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionTitle</code> attribute.
	 * @return the testimonialCollectionTitle
	 */
	public String getTestimonialCollectionTitle()
	{
		return getTestimonialCollectionTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionTitle</code> attribute. 
	 * @param value the testimonialCollectionTitle
	 */
	public void setTestimonialCollectionTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TESTIMONIALCOLLECTIONTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialCollectionComponent.testimonialCollectionTitle</code> attribute. 
	 * @param value the testimonialCollectionTitle
	 */
	public void setTestimonialCollectionTitle(final String value)
	{
		setTestimonialCollectionTitle( getSession().getSessionContext(), value );
	}
	
}
