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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.GiftWrapComponent GiftWrapComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedGiftWrapComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>GiftWrapComponent.image</code> attribute **/
	public static final String IMAGE = "image";
	/** Qualifier of the <code>GiftWrapComponent.msgCharLimit</code> attribute **/
	public static final String MSGCHARLIMIT = "msgCharLimit";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(IMAGE, AttributeMode.INITIAL);
		tmp.put(MSGCHARLIMIT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftWrapComponent.image</code> attribute.
	 * @return the image - GiftWrap Image Component
	 */
	public List<Media> getImage(final SessionContext ctx)
	{
		List<Media> coll = (List<Media>)getProperty( ctx, IMAGE);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftWrapComponent.image</code> attribute.
	 * @return the image - GiftWrap Image Component
	 */
	public List<Media> getImage()
	{
		return getImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftWrapComponent.image</code> attribute. 
	 * @param value the image - GiftWrap Image Component
	 */
	public void setImage(final SessionContext ctx, final List<Media> value)
	{
		setProperty(ctx, IMAGE,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftWrapComponent.image</code> attribute. 
	 * @param value the image - GiftWrap Image Component
	 */
	public void setImage(final List<Media> value)
	{
		setImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftWrapComponent.msgCharLimit</code> attribute.
	 * @return the msgCharLimit
	 */
	public String getMsgCharLimit(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MSGCHARLIMIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftWrapComponent.msgCharLimit</code> attribute.
	 * @return the msgCharLimit
	 */
	public String getMsgCharLimit()
	{
		return getMsgCharLimit( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftWrapComponent.msgCharLimit</code> attribute. 
	 * @param value the msgCharLimit
	 */
	public void setMsgCharLimit(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MSGCHARLIMIT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftWrapComponent.msgCharLimit</code> attribute. 
	 * @param value the msgCharLimit
	 */
	public void setMsgCharLimit(final String value)
	{
		setMsgCharLimit( getSession().getSessionContext(), value );
	}
	
}
