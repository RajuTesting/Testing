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
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLLuxuryStoreInstagramCMSComponent SSLLuxuryStoreInstagramCMSComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLLuxuryStoreInstagramCMSComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLLuxuryStoreInstagramCMSComponent.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>SSLLuxuryStoreInstagramCMSComponent.instagramImages</code> attribute **/
	public static final String INSTAGRAMIMAGES = "instagramImages";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TITLE, AttributeMode.INITIAL);
		tmp.put(INSTAGRAMIMAGES, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreInstagramCMSComponent.instagramImages</code> attribute.
	 * @return the instagramImages - List of instagram images
	 */
	public List<Media> getInstagramImages(final SessionContext ctx)
	{
		List<Media> coll = (List<Media>)getProperty( ctx, INSTAGRAMIMAGES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreInstagramCMSComponent.instagramImages</code> attribute.
	 * @return the instagramImages - List of instagram images
	 */
	public List<Media> getInstagramImages()
	{
		return getInstagramImages( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreInstagramCMSComponent.instagramImages</code> attribute. 
	 * @param value the instagramImages - List of instagram images
	 */
	public void setInstagramImages(final SessionContext ctx, final List<Media> value)
	{
		setProperty(ctx, INSTAGRAMIMAGES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreInstagramCMSComponent.instagramImages</code> attribute. 
	 * @param value the instagramImages - List of instagram images
	 */
	public void setInstagramImages(final List<Media> value)
	{
		setInstagramImages( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreInstagramCMSComponent.title</code> attribute.
	 * @return the title - Title of the component
	 */
	public String getTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLLuxuryStoreInstagramCMSComponent.title</code> attribute.
	 * @return the title - Title of the component
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreInstagramCMSComponent.title</code> attribute. 
	 * @param value the title - Title of the component
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLLuxuryStoreInstagramCMSComponent.title</code> attribute. 
	 * @param value the title - Title of the component
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
}
