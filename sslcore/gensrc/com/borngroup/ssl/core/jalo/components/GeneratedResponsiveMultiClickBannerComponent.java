/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.CMSLinkTextComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.ResponsiveMultiClickBannerComponent ResponsiveMultiClickBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedResponsiveMultiClickBannerComponent extends CMSImageComponent
{
	/** Qualifier of the <code>ResponsiveMultiClickBannerComponent.headerLabel</code> attribute **/
	public static final String HEADERLABEL = "headerLabel";
	/** Qualifier of the <code>ResponsiveMultiClickBannerComponent.links</code> attribute **/
	public static final String LINKS = "links";
	/** Qualifier of the <code>ResponsiveMultiClickBannerComponent.footerLabel</code> attribute **/
	public static final String FOOTERLABEL = "footerLabel";
	/** Qualifier of the <code>ResponsiveMultiClickBannerComponent.position</code> attribute **/
	public static final String POSITION = "position";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADERLABEL, AttributeMode.INITIAL);
		tmp.put(LINKS, AttributeMode.INITIAL);
		tmp.put(FOOTERLABEL, AttributeMode.INITIAL);
		tmp.put(POSITION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveMultiClickBannerComponent.footerLabel</code> attribute.
	 * @return the footerLabel - Footer Label
	 */
	public CMSLinkTextComponent getFooterLabel(final SessionContext ctx)
	{
		return (CMSLinkTextComponent)getProperty( ctx, FOOTERLABEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveMultiClickBannerComponent.footerLabel</code> attribute.
	 * @return the footerLabel - Footer Label
	 */
	public CMSLinkTextComponent getFooterLabel()
	{
		return getFooterLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveMultiClickBannerComponent.footerLabel</code> attribute. 
	 * @param value the footerLabel - Footer Label
	 */
	public void setFooterLabel(final SessionContext ctx, final CMSLinkTextComponent value)
	{
		setProperty(ctx, FOOTERLABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveMultiClickBannerComponent.footerLabel</code> attribute. 
	 * @param value the footerLabel - Footer Label
	 */
	public void setFooterLabel(final CMSLinkTextComponent value)
	{
		setFooterLabel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveMultiClickBannerComponent.headerLabel</code> attribute.
	 * @return the headerLabel - Header Label
	 */
	public String getHeaderLabel(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedResponsiveMultiClickBannerComponent.getHeaderLabel requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADERLABEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveMultiClickBannerComponent.headerLabel</code> attribute.
	 * @return the headerLabel - Header Label
	 */
	public String getHeaderLabel()
	{
		return getHeaderLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveMultiClickBannerComponent.headerLabel</code> attribute. 
	 * @return the localized headerLabel - Header Label
	 */
	public Map<Language,String> getAllHeaderLabel(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADERLABEL,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveMultiClickBannerComponent.headerLabel</code> attribute. 
	 * @return the localized headerLabel - Header Label
	 */
	public Map<Language,String> getAllHeaderLabel()
	{
		return getAllHeaderLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveMultiClickBannerComponent.headerLabel</code> attribute. 
	 * @param value the headerLabel - Header Label
	 */
	public void setHeaderLabel(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedResponsiveMultiClickBannerComponent.setHeaderLabel requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADERLABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveMultiClickBannerComponent.headerLabel</code> attribute. 
	 * @param value the headerLabel - Header Label
	 */
	public void setHeaderLabel(final String value)
	{
		setHeaderLabel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveMultiClickBannerComponent.headerLabel</code> attribute. 
	 * @param value the headerLabel - Header Label
	 */
	public void setAllHeaderLabel(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADERLABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveMultiClickBannerComponent.headerLabel</code> attribute. 
	 * @param value the headerLabel - Header Label
	 */
	public void setAllHeaderLabel(final Map<Language,String> value)
	{
		setAllHeaderLabel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveMultiClickBannerComponent.links</code> attribute.
	 * @return the links - List of Links
	 */
	public List<CMSLinkTextComponent> getLinks(final SessionContext ctx)
	{
		List<CMSLinkTextComponent> coll = (List<CMSLinkTextComponent>)getProperty( ctx, LINKS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveMultiClickBannerComponent.links</code> attribute.
	 * @return the links - List of Links
	 */
	public List<CMSLinkTextComponent> getLinks()
	{
		return getLinks( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveMultiClickBannerComponent.links</code> attribute. 
	 * @param value the links - List of Links
	 */
	public void setLinks(final SessionContext ctx, final List<CMSLinkTextComponent> value)
	{
		setProperty(ctx, LINKS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveMultiClickBannerComponent.links</code> attribute. 
	 * @param value the links - List of Links
	 */
	public void setLinks(final List<CMSLinkTextComponent> value)
	{
		setLinks( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveMultiClickBannerComponent.position</code> attribute.
	 * @return the position - Position of Links
	 */
	public EnumerationValue getPosition(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, POSITION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveMultiClickBannerComponent.position</code> attribute.
	 * @return the position - Position of Links
	 */
	public EnumerationValue getPosition()
	{
		return getPosition( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveMultiClickBannerComponent.position</code> attribute. 
	 * @param value the position - Position of Links
	 */
	public void setPosition(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, POSITION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveMultiClickBannerComponent.position</code> attribute. 
	 * @param value the position - Position of Links
	 */
	public void setPosition(final EnumerationValue value)
	{
		setPosition( getSession().getSessionContext(), value );
	}
	
}
