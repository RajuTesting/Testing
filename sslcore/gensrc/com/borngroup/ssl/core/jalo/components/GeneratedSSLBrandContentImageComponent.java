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
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.media.MediaContainer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLBrandContentImageComponent SSLBrandContentImageComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLBrandContentImageComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLBrandContentImageComponent.mediaContainer</code> attribute **/
	public static final String MEDIACONTAINER = "mediaContainer";
	/** Qualifier of the <code>SSLBrandContentImageComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>SSLBrandContentImageComponent.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(MEDIACONTAINER, AttributeMode.INITIAL);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandContentImageComponent.description</code> attribute.
	 * @return the description - Description
	 */
	public String getDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandContentImageComponent.description</code> attribute.
	 * @return the description - Description
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandContentImageComponent.description</code> attribute. 
	 * @param value the description - Description
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandContentImageComponent.description</code> attribute. 
	 * @param value the description - Description
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandContentImageComponent.heading</code> attribute.
	 * @return the heading - Heading
	 */
	public String getHeading(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandContentImageComponent.heading</code> attribute.
	 * @return the heading - Heading
	 */
	public String getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandContentImageComponent.heading</code> attribute. 
	 * @param value the heading - Heading
	 */
	public void setHeading(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandContentImageComponent.heading</code> attribute. 
	 * @param value the heading - Heading
	 */
	public void setHeading(final String value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandContentImageComponent.mediaContainer</code> attribute.
	 * @return the mediaContainer - Media container
	 */
	public MediaContainer getMediaContainer(final SessionContext ctx)
	{
		return (MediaContainer)getProperty( ctx, MEDIACONTAINER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLBrandContentImageComponent.mediaContainer</code> attribute.
	 * @return the mediaContainer - Media container
	 */
	public MediaContainer getMediaContainer()
	{
		return getMediaContainer( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandContentImageComponent.mediaContainer</code> attribute. 
	 * @param value the mediaContainer - Media container
	 */
	public void setMediaContainer(final SessionContext ctx, final MediaContainer value)
	{
		setProperty(ctx, MEDIACONTAINER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLBrandContentImageComponent.mediaContainer</code> attribute. 
	 * @param value the mediaContainer - Media container
	 */
	public void setMediaContainer(final MediaContainer value)
	{
		setMediaContainer( getSession().getSessionContext(), value );
	}
	
}
