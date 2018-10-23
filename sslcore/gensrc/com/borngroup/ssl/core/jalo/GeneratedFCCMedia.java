/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.media.Media FCCMedia}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedFCCMedia extends Media
{
	/** Qualifier of the <code>FCCMedia.tier</code> attribute **/
	public static final String TIER = "tier";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Media.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TIER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FCCMedia.tier</code> attribute.
	 * @return the tier - FCC Card Holder Tier
	 */
	public String getTier(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TIER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FCCMedia.tier</code> attribute.
	 * @return the tier - FCC Card Holder Tier
	 */
	public String getTier()
	{
		return getTier( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMedia.tier</code> attribute. 
	 * @param value the tier - FCC Card Holder Tier
	 */
	public void setTier(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TIER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FCCMedia.tier</code> attribute. 
	 * @param value the tier - FCC Card Holder Tier
	 */
	public void setTier(final String value)
	{
		setTier( getSession().getSessionContext(), value );
	}
	
}
