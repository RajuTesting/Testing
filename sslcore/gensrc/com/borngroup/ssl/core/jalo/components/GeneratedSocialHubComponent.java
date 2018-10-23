/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.CMSImageLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SocialHubComponent SocialHubComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSocialHubComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SocialHubComponent.socialHubImageList</code> attribute **/
	public static final String SOCIALHUBIMAGELIST = "socialHubImageList";
	/** Qualifier of the <code>SocialHubComponent.socialHubHeading</code> attribute **/
	public static final String SOCIALHUBHEADING = "socialHubHeading";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SOCIALHUBIMAGELIST, AttributeMode.INITIAL);
		tmp.put(SOCIALHUBHEADING, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SocialHubComponent.socialHubHeading</code> attribute.
	 * @return the socialHubHeading
	 */
	public String getSocialHubHeading(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSocialHubComponent.getSocialHubHeading requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, SOCIALHUBHEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SocialHubComponent.socialHubHeading</code> attribute.
	 * @return the socialHubHeading
	 */
	public String getSocialHubHeading()
	{
		return getSocialHubHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SocialHubComponent.socialHubHeading</code> attribute. 
	 * @return the localized socialHubHeading
	 */
	public Map<Language,String> getAllSocialHubHeading(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,SOCIALHUBHEADING,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SocialHubComponent.socialHubHeading</code> attribute. 
	 * @return the localized socialHubHeading
	 */
	public Map<Language,String> getAllSocialHubHeading()
	{
		return getAllSocialHubHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SocialHubComponent.socialHubHeading</code> attribute. 
	 * @param value the socialHubHeading
	 */
	public void setSocialHubHeading(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSocialHubComponent.setSocialHubHeading requires a session language", 0 );
		}
		setLocalizedProperty(ctx, SOCIALHUBHEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SocialHubComponent.socialHubHeading</code> attribute. 
	 * @param value the socialHubHeading
	 */
	public void setSocialHubHeading(final String value)
	{
		setSocialHubHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SocialHubComponent.socialHubHeading</code> attribute. 
	 * @param value the socialHubHeading
	 */
	public void setAllSocialHubHeading(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,SOCIALHUBHEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SocialHubComponent.socialHubHeading</code> attribute. 
	 * @param value the socialHubHeading
	 */
	public void setAllSocialHubHeading(final Map<Language,String> value)
	{
		setAllSocialHubHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SocialHubComponent.socialHubImageList</code> attribute.
	 * @return the socialHubImageList
	 */
	public List<CMSImageLinkComponent> getSocialHubImageList(final SessionContext ctx)
	{
		List<CMSImageLinkComponent> coll = (List<CMSImageLinkComponent>)getProperty( ctx, SOCIALHUBIMAGELIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SocialHubComponent.socialHubImageList</code> attribute.
	 * @return the socialHubImageList
	 */
	public List<CMSImageLinkComponent> getSocialHubImageList()
	{
		return getSocialHubImageList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SocialHubComponent.socialHubImageList</code> attribute. 
	 * @param value the socialHubImageList
	 */
	public void setSocialHubImageList(final SessionContext ctx, final List<CMSImageLinkComponent> value)
	{
		setProperty(ctx, SOCIALHUBIMAGELIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SocialHubComponent.socialHubImageList</code> attribute. 
	 * @param value the socialHubImageList
	 */
	public void setSocialHubImageList(final List<CMSImageLinkComponent> value)
	{
		setSocialHubImageList( getSession().getSessionContext(), value );
	}
	
}
