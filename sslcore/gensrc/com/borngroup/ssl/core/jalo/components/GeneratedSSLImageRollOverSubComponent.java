/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.SSLImageRollOverLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SSLImageRollOverSubComponent SSLImageRollOverSubComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLImageRollOverSubComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLImageRollOverSubComponent.topLeft</code> attribute **/
	public static final String TOPLEFT = "topLeft";
	/** Qualifier of the <code>SSLImageRollOverSubComponent.topRight</code> attribute **/
	public static final String TOPRIGHT = "topRight";
	/** Qualifier of the <code>SSLImageRollOverSubComponent.bottomLeft</code> attribute **/
	public static final String BOTTOMLEFT = "bottomLeft";
	/** Qualifier of the <code>SSLImageRollOverSubComponent.bottomRight</code> attribute **/
	public static final String BOTTOMRIGHT = "bottomRight";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TOPLEFT, AttributeMode.INITIAL);
		tmp.put(TOPRIGHT, AttributeMode.INITIAL);
		tmp.put(BOTTOMLEFT, AttributeMode.INITIAL);
		tmp.put(BOTTOMRIGHT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverSubComponent.bottomLeft</code> attribute.
	 * @return the bottomLeft
	 */
	public SSLImageRollOverLinkComponent getBottomLeft(final SessionContext ctx)
	{
		return (SSLImageRollOverLinkComponent)getProperty( ctx, BOTTOMLEFT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverSubComponent.bottomLeft</code> attribute.
	 * @return the bottomLeft
	 */
	public SSLImageRollOverLinkComponent getBottomLeft()
	{
		return getBottomLeft( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverSubComponent.bottomLeft</code> attribute. 
	 * @param value the bottomLeft
	 */
	public void setBottomLeft(final SessionContext ctx, final SSLImageRollOverLinkComponent value)
	{
		setProperty(ctx, BOTTOMLEFT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverSubComponent.bottomLeft</code> attribute. 
	 * @param value the bottomLeft
	 */
	public void setBottomLeft(final SSLImageRollOverLinkComponent value)
	{
		setBottomLeft( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverSubComponent.bottomRight</code> attribute.
	 * @return the bottomRight
	 */
	public SSLImageRollOverLinkComponent getBottomRight(final SessionContext ctx)
	{
		return (SSLImageRollOverLinkComponent)getProperty( ctx, BOTTOMRIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverSubComponent.bottomRight</code> attribute.
	 * @return the bottomRight
	 */
	public SSLImageRollOverLinkComponent getBottomRight()
	{
		return getBottomRight( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverSubComponent.bottomRight</code> attribute. 
	 * @param value the bottomRight
	 */
	public void setBottomRight(final SessionContext ctx, final SSLImageRollOverLinkComponent value)
	{
		setProperty(ctx, BOTTOMRIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverSubComponent.bottomRight</code> attribute. 
	 * @param value the bottomRight
	 */
	public void setBottomRight(final SSLImageRollOverLinkComponent value)
	{
		setBottomRight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverSubComponent.topLeft</code> attribute.
	 * @return the topLeft
	 */
	public SSLImageRollOverLinkComponent getTopLeft(final SessionContext ctx)
	{
		return (SSLImageRollOverLinkComponent)getProperty( ctx, TOPLEFT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverSubComponent.topLeft</code> attribute.
	 * @return the topLeft
	 */
	public SSLImageRollOverLinkComponent getTopLeft()
	{
		return getTopLeft( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverSubComponent.topLeft</code> attribute. 
	 * @param value the topLeft
	 */
	public void setTopLeft(final SessionContext ctx, final SSLImageRollOverLinkComponent value)
	{
		setProperty(ctx, TOPLEFT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverSubComponent.topLeft</code> attribute. 
	 * @param value the topLeft
	 */
	public void setTopLeft(final SSLImageRollOverLinkComponent value)
	{
		setTopLeft( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverSubComponent.topRight</code> attribute.
	 * @return the topRight
	 */
	public SSLImageRollOverLinkComponent getTopRight(final SessionContext ctx)
	{
		return (SSLImageRollOverLinkComponent)getProperty( ctx, TOPRIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLImageRollOverSubComponent.topRight</code> attribute.
	 * @return the topRight
	 */
	public SSLImageRollOverLinkComponent getTopRight()
	{
		return getTopRight( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverSubComponent.topRight</code> attribute. 
	 * @param value the topRight
	 */
	public void setTopRight(final SessionContext ctx, final SSLImageRollOverLinkComponent value)
	{
		setProperty(ctx, TOPRIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLImageRollOverSubComponent.topRight</code> attribute. 
	 * @param value the topRight
	 */
	public void setTopRight(final SSLImageRollOverLinkComponent value)
	{
		setTopRight( getSession().getSessionContext(), value );
	}
	
}
