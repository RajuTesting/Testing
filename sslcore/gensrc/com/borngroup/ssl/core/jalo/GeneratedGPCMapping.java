/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem GPCMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedGPCMapping extends GenericItem
{
	/** Qualifier of the <code>GPCMapping.categoryCode</code> attribute **/
	public static final String CATEGORYCODE = "categoryCode";
	/** Qualifier of the <code>GPCMapping.gpcId</code> attribute **/
	public static final String GPCID = "gpcId";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CATEGORYCODE, AttributeMode.INITIAL);
		tmp.put(GPCID, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GPCMapping.categoryCode</code> attribute.
	 * @return the categoryCode - Category code
	 */
	public String getCategoryCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CATEGORYCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GPCMapping.categoryCode</code> attribute.
	 * @return the categoryCode - Category code
	 */
	public String getCategoryCode()
	{
		return getCategoryCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GPCMapping.categoryCode</code> attribute. 
	 * @param value the categoryCode - Category code
	 */
	public void setCategoryCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CATEGORYCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GPCMapping.categoryCode</code> attribute. 
	 * @param value the categoryCode - Category code
	 */
	public void setCategoryCode(final String value)
	{
		setCategoryCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GPCMapping.gpcId</code> attribute.
	 * @return the gpcId - Google Product Category ID
	 */
	public String getGpcId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, GPCID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GPCMapping.gpcId</code> attribute.
	 * @return the gpcId - Google Product Category ID
	 */
	public String getGpcId()
	{
		return getGpcId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GPCMapping.gpcId</code> attribute. 
	 * @param value the gpcId - Google Product Category ID
	 */
	public void setGpcId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, GPCID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GPCMapping.gpcId</code> attribute. 
	 * @param value the gpcId - Google Product Category ID
	 */
	public void setGpcId(final String value)
	{
		setGpcId( getSession().getSessionContext(), value );
	}
	
}
