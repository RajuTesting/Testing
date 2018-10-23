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
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLProductDetailReviewRatingComponent SSLProductDetailReviewRatingComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLProductDetailReviewRatingComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLProductDetailReviewRatingComponent.reviewLimit</code> attribute **/
	public static final String REVIEWLIMIT = "reviewLimit";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(REVIEWLIMIT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailReviewRatingComponent.reviewLimit</code> attribute.
	 * @return the reviewLimit - Limit for reviews to be displayed
	 */
	public Integer getReviewLimit(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, REVIEWLIMIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailReviewRatingComponent.reviewLimit</code> attribute.
	 * @return the reviewLimit - Limit for reviews to be displayed
	 */
	public Integer getReviewLimit()
	{
		return getReviewLimit( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailReviewRatingComponent.reviewLimit</code> attribute. 
	 * @return the reviewLimit - Limit for reviews to be displayed
	 */
	public int getReviewLimitAsPrimitive(final SessionContext ctx)
	{
		Integer value = getReviewLimit( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailReviewRatingComponent.reviewLimit</code> attribute. 
	 * @return the reviewLimit - Limit for reviews to be displayed
	 */
	public int getReviewLimitAsPrimitive()
	{
		return getReviewLimitAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailReviewRatingComponent.reviewLimit</code> attribute. 
	 * @param value the reviewLimit - Limit for reviews to be displayed
	 */
	public void setReviewLimit(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, REVIEWLIMIT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailReviewRatingComponent.reviewLimit</code> attribute. 
	 * @param value the reviewLimit - Limit for reviews to be displayed
	 */
	public void setReviewLimit(final Integer value)
	{
		setReviewLimit( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailReviewRatingComponent.reviewLimit</code> attribute. 
	 * @param value the reviewLimit - Limit for reviews to be displayed
	 */
	public void setReviewLimit(final SessionContext ctx, final int value)
	{
		setReviewLimit( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailReviewRatingComponent.reviewLimit</code> attribute. 
	 * @param value the reviewLimit - Limit for reviews to be displayed
	 */
	public void setReviewLimit(final int value)
	{
		setReviewLimit( getSession().getSessionContext(), value );
	}
	
}
