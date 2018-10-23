/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.JspIncludeComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslProductDetailJspIncludeComponent SslProductDetailJspIncludeComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslProductDetailJspIncludeComponent extends JspIncludeComponent
{
	/** Qualifier of the <code>SslProductDetailJspIncludeComponent.showRatingsRecommendations</code> attribute **/
	public static final String SHOWRATINGSRECOMMENDATIONS = "showRatingsRecommendations";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(JspIncludeComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SHOWRATINGSRECOMMENDATIONS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductDetailJspIncludeComponent.showRatingsRecommendations</code> attribute.
	 * @return the showRatingsRecommendations
	 */
	public Boolean isShowRatingsRecommendations(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, SHOWRATINGSRECOMMENDATIONS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductDetailJspIncludeComponent.showRatingsRecommendations</code> attribute.
	 * @return the showRatingsRecommendations
	 */
	public Boolean isShowRatingsRecommendations()
	{
		return isShowRatingsRecommendations( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductDetailJspIncludeComponent.showRatingsRecommendations</code> attribute. 
	 * @return the showRatingsRecommendations
	 */
	public boolean isShowRatingsRecommendationsAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isShowRatingsRecommendations( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslProductDetailJspIncludeComponent.showRatingsRecommendations</code> attribute. 
	 * @return the showRatingsRecommendations
	 */
	public boolean isShowRatingsRecommendationsAsPrimitive()
	{
		return isShowRatingsRecommendationsAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductDetailJspIncludeComponent.showRatingsRecommendations</code> attribute. 
	 * @param value the showRatingsRecommendations
	 */
	public void setShowRatingsRecommendations(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, SHOWRATINGSRECOMMENDATIONS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductDetailJspIncludeComponent.showRatingsRecommendations</code> attribute. 
	 * @param value the showRatingsRecommendations
	 */
	public void setShowRatingsRecommendations(final Boolean value)
	{
		setShowRatingsRecommendations( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductDetailJspIncludeComponent.showRatingsRecommendations</code> attribute. 
	 * @param value the showRatingsRecommendations
	 */
	public void setShowRatingsRecommendations(final SessionContext ctx, final boolean value)
	{
		setShowRatingsRecommendations( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslProductDetailJspIncludeComponent.showRatingsRecommendations</code> attribute. 
	 * @param value the showRatingsRecommendations
	 */
	public void setShowRatingsRecommendations(final boolean value)
	{
		setShowRatingsRecommendations( getSession().getSessionContext(), value );
	}
	
}
