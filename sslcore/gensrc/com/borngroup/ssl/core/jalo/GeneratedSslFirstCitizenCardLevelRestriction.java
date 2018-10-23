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
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.voucher.jalo.Restriction;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SslFirstCitizenCardLevelRestriction SslFirstCitizenCardLevelRestriction}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslFirstCitizenCardLevelRestriction extends Restriction
{
	/** Qualifier of the <code>SslFirstCitizenCardLevelRestriction.firstCitizenCardLevel</code> attribute **/
	public static final String FIRSTCITIZENCARDLEVEL = "firstCitizenCardLevel";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Restriction.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(FIRSTCITIZENCARDLEVEL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslFirstCitizenCardLevelRestriction.firstCitizenCardLevel</code> attribute.
	 * @return the firstCitizenCardLevel - The first citizen card level for which restriction
	 *                             will be applied.
	 */
	public EnumerationValue getFirstCitizenCardLevel(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, FIRSTCITIZENCARDLEVEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslFirstCitizenCardLevelRestriction.firstCitizenCardLevel</code> attribute.
	 * @return the firstCitizenCardLevel - The first citizen card level for which restriction
	 *                             will be applied.
	 */
	public EnumerationValue getFirstCitizenCardLevel()
	{
		return getFirstCitizenCardLevel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslFirstCitizenCardLevelRestriction.firstCitizenCardLevel</code> attribute. 
	 * @param value the firstCitizenCardLevel - The first citizen card level for which restriction
	 *                             will be applied.
	 */
	public void setFirstCitizenCardLevel(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, FIRSTCITIZENCARDLEVEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslFirstCitizenCardLevelRestriction.firstCitizenCardLevel</code> attribute. 
	 * @param value the firstCitizenCardLevel - The first citizen card level for which restriction
	 *                             will be applied.
	 */
	public void setFirstCitizenCardLevel(final EnumerationValue value)
	{
		setFirstCitizenCardLevel( getSession().getSessionContext(), value );
	}
	
}
